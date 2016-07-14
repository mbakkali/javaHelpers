package tp;

import static spark.Spark.*;
import java.util.Map;
import java.util.HashMap;
import java.util.concurrent.Future;

//pour utiliser unirest
import com.mashape.unirest.http.*;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;

public class ServeurApplication {
    private static final int MASTER = 8080;
    private static Map<String,String> storage = new HashMap<>();
    private static int concensus = 0; // -1 error, 0 = en attente, 1 = ok
    private static boolean loop = true;




    public static void main(String[] args) {
        port(Integer.parseInt(args[0]));


        get("/:key", (request, response) -> {
            System.out.println("get method...");
            return storage.get(request.params(":key"))+ "\n";
        });

        put("/:key", (request, response) -> {
            System.out.println("put on.. " + request.params(":key"));

            if (request.port() == MASTER) {
                System.out.println("Réplication... slave 1");
                Unirest.put("http://localhost:8081/" + request.params(":key")).queryString("state", "canCommit").body(request.body()).asStringAsync(new Callback<String>() {
                    @Override
                    public void completed(HttpResponse<String> httpResponse) {

                        Unirest.put("http://localhost:8081/" + request.params(":key")).queryString("state", "doCommit").body(request.body()).asStringAsync(new Callback<String>() {
                            @Override
                            public void completed(HttpResponse<String> httpResponse) {
                                concensus = concensus + 1;
                            }

                            @Override
                            public void failed(UnirestException e) {
                                concensus = -2;
                            }

                            @Override
                            public void cancelled() {
                                concensus = -2;
                            }
                        });

                    }
                    @Override
                    public void failed(UnirestException e) {
                        concensus = -1;
                        loop = false;
                    }
                    @Override
                    public void cancelled() {
                        concensus = -1;
                        loop = false;
                    }
                });

                System.out.println("Réplication... slave 2");
                Unirest.put("http://localhost:8082/" + request.params(":key")).queryString("state", "canCommit").body(request.body()).asStringAsync(new Callback<String>() {
                    @Override
                    public void completed(HttpResponse<String> httpResponse) {
                        System.out.println("Req 1 ok");
                        Unirest.put("http://localhost:8082/" + request.params(":key")).queryString("state", "doCommit").body(request.body()).asStringAsync(new Callback<String>() {
                            @Override
                            public void completed(HttpResponse<String> httpResponse) {
                                System.out.println("req 2..ok");
                                concensus = concensus + 1;
                            }

                            @Override
                            public void failed(UnirestException e) {
                                concensus = -2;
                                loop = false;
                            }

                            @Override
                            public void cancelled() {
                                concensus = -2;
                                loop = false;
                            }
                        });

                    }
                    @Override
                    public void failed(UnirestException e) {
                        concensus = -2;
                    }
                    @Override
                    public void cancelled() {
                        concensus = -2;
                    }
                });

                //On attend un état convergeant OK or NOK
                while(loop) {
                    System.err.println("Wait for an answer...");
                    switch (concensus){
                        case 2:
                            System.out.println("Concensus OK.. Ecriture...");
                            storage.put(request.params(":key"), request.body());
                            loop = false;
                            return "ok\n";
                        case -1:
                            loop = false;
                            return "no\n";
                    }
                }
                return "no\n";
            }else{

                //SLAVE SERVERS.
                if(request.queryParams("state").equalsIgnoreCase("doCommit")) {
                    System.out.println("On écrit sur le slave...");
                    storage.put(request.params(":key"), request.body());
                }
                return "yes\n";
            }
        });


        delete("/:key", (request, response) -> {
            storage.remove(request.params(":key"));
            return "yes\n";
        });
    }
}

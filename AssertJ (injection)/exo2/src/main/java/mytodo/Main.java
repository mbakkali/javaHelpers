package mytodo;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.freemarker.FreeMarkerRoute;

import java.util.ArrayList;
import java.util.HashMap;

import static spark.Spark.get;
import static spark.Spark.post;

public class Main {

    public static void main(String... args) {

        get(new FreeMarkerRoute("/") {
            @Override
            public Object handle(Request request, Response response) {
                if (request.session(true).isNew()) {
                    request.session().attribute("items", new ArrayList<String>() {
                        {
                            add("Buy some bread");
                            add("Go to swimming");
                            add("Get rich");
                        }
                    });
                }
                HashMap<String, Object> model = new HashMap<String, Object>();
                model.put("items", request.session().attribute("items"));
                response.type("text/html");
                return modelAndView(model, "main.ftl.html");
            }
        });

        get(new Route("/complete/:id") {
            @Override
            public Object handle(Request request, Response response) {
                int id = Integer.valueOf(request.params(":id"));
                ArrayList<String> items = request.session().attribute("items");
                items.remove(id);
                response.redirect("/");
                return "";
            }
        });

        post(new Route("/create") {
            @Override
            public Object handle(Request request, Response response) {
                if (request.body().startsWith("newItem=")) {
                    ArrayList<String> items = request.session().attribute("items");
                    items.add(request.body().substring(8));
                }
                response.redirect("/");
                return "";
            }
        });
    }
}
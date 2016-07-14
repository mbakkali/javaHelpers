package fr.insalyon.telecom.chat.services;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class GistService {

    public GistResponse publish(String login, MessageBoard messageBoard){
        RestTemplate restTemplate = new RestTemplate();
        GistResponse gistResponse = restTemplate.postForObject(
                "https://api.github.com/gists",
                new GistRequest(login, false, messageBoard),
                GistResponse.class
        );
        return gistResponse;
    }

}

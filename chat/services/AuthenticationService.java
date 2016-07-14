package fr.insalyon.telecom.chat.services;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AuthenticationService {

    public boolean authenticate(String login, String password) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        AuthenticationResponse response = restTemplate.postForObject(
                "http://localhost:8666/auth",
                new AuthenticationRequest(login, password),AuthenticationResponse.class);
        return response.isSuccess();
    }
}
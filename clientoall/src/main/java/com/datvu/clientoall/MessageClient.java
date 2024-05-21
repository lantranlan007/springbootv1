package com.datvu.clientoall;

import org.springframework.web.client.RestTemplate;

public class MessageClient {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();

        String message = restTemplate.getForObject("http://localhost:8080/message", String.class);

        System.out.println("Message from server: " + message);
    }
}
package com.example.lab7.client;

import org.springframework.web.client.RestClient;

public class ClientTest {
    public static void main(String[] args) {
        RestClient restClient = RestClient.create();
        String url = "http://localhost:8081/movies";

        try {
            String result = restClient.get()
                    .uri(url)
                    .retrieve()
                    .body(String.class);
            System.out.println(result);
        } catch (Exception e) {
            System.out.println("Serverul este oprit sau adresa e greșită " + e.getMessage());
        }
    }
}
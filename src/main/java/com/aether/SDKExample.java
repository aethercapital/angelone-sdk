package com.aether;

import io.github.cdimascio.dotenv.Dotenv;

public class SDKExample {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();

        // Get values from .env
        String apiKey = dotenv.get("API_KEY");
        String username = dotenv.get("CLIENT_CODE");
        String password = dotenv.get("PASSWORD");

        System.out.println(apiKey + username + password);
//        System.out.println(angelOne.toString());
    }
}

package com.aether;

import io.github.cdimascio.dotenv.Dotenv;
import org.jboss.aerogear.security.otp.Totp;

public class SDKExample {

    public static void main(String[] args) {
        Dotenv dotenv = Dotenv.load();
        String apiKey = dotenv.get("API_KEY");
        String username = dotenv.get("CLIENT_CODE");
        String password = dotenv.get("PASSWORD");
        String totp_code = dotenv.get("TOTP");

        Totp totp = new Totp(totp_code);
        String otp = totp.now();

        AngelOne angelOne = new AngelOne.SmartAPI().username(username).password(password).otp(otp).apiKey(apiKey).state("abide").login();
        System.out.println(angelOne.getProfile());
        System.out.println(angelOne.getFundsAndMargin());

    }
}

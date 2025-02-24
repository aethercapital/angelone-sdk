package com.aether;

import com.aether.contracts.UserAPI;
import com.aether.exception.AuthenticationFailure;
import com.aether.types.AuthTokens;
import com.aether.uility.RequestHandler;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * SDK Implementation of Angelone.
 * Singleton class, so only one object should be created by user.
 */
public class AngelOne implements UserAPI {

    private static volatile AngelOne instance;

    private AuthTokens authTokens;

    private String apiKey;

    private String clientCode;

    private AngelOne(String username, String password, String otp, String state, String apiKey) {

        Map<String, String> requestData = new HashMap<>();
        requestData.put("clientcode", username);
        requestData.put("password", password);
        requestData.put("totp", otp);
        requestData.put("state", state);
        requestData.put("api_key", apiKey);

        try {
            this.apiKey = apiKey;
            this.clientCode = username;
            this.authTokens = RequestHandler.handleLogin(requestData);
        } catch (IOException e) {
            throw new AuthenticationFailure(e.getMessage());
        }
    }

    static class SmartAPI {
        String username;
        String password;
        String otp;
        String state;
        String apiKey;
        AngelOne angelOne = null;

        public AngelOne login(){
            if (AngelOne.instance == null) {
                synchronized (AngelOne.class) {
                    if (AngelOne.instance == null) {
                        AngelOne.instance = new AngelOne(username, password, otp, state, apiKey);
                    }
                }
            }
            return AngelOne.instance;
        }

        public SmartAPI apiKey(String apiKey) {
            this.apiKey = apiKey;
            return this;
        }
        public SmartAPI username(String username) {
            this.username = username;
            return this;
        }

        public SmartAPI password(String password) {
            this.password = password;
            return this;
        }

        public SmartAPI otp(String otp) {
            this.otp = otp;
            return this;
        }

        public SmartAPI state(String state) {
            this.state = state;
            return this;
        }

    }

    @Override
    public void logout() {

    }

    @Override
    public void getProfile() {

    }

    @Override
    public void getFundsAndMargin() {

    }

    @Override
    public String toString() {
        return "AngelOne{" +
                "authTokens=" + authTokens.getFeedToken() +
                ", apiKey='" + apiKey + '\'' +
                ", clientCode='" + clientCode + '\'' +
                '}';
    }
}

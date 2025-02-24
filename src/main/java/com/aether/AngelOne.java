package com.aether;

import com.aether.contracts.UserAPI;
import com.aether.exception.AuthenticationFailure;
import com.aether.types.AuthTokens;
import com.aether.types.Profile;
import com.aether.types.RMSLimit;
import com.aether.uility.RequestHandler;
import com.aether.uility.RestPaths;
import com.fasterxml.jackson.databind.JsonNode;

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

    @Override
    public void logout() {

    }

    @Override
    public Profile getProfile() {
        try {
            JsonNode node = RequestHandler.handleAnyGET(apiKey, authTokens.getJwtToken(), RestPaths.PROFILE.getPath());
            Profile profile = new Profile();
            profile.setEmail(extractValue(node,"email"));
            profile.setName(extractValue(node,"name"));
            profile.setClientCode(extractValue(node,"clientcode"));
            profile.setMobileNo(extractValue(node,"mobileno"));
            return profile;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String extractValue(JsonNode node, String field){
        return node.get("data").get(field).textValue();
    }

    @Override
    public RMSLimit getFundsAndMargin() {

        try {
            JsonNode node = RequestHandler.handleAnyGET(apiKey, authTokens.getJwtToken(), RestPaths.FUNDS_MARGIN.getPath());
            RMSLimit rmsLimit = new RMSLimit();
            rmsLimit.setNet(extractValue(node,"net"));
            rmsLimit.setAvailablecash(extractValue(node,"availablecash"));
            rmsLimit.setAvailableintradaypayin(extractValue(node,"availableintradaypayin"));
            rmsLimit.setAvailablelimitmargin(extractValue(node,"availablelimitmargin"));
            rmsLimit.setCollateral(extractValue(node,"collateral"));
            rmsLimit.setM2munrealized(extractValue(node,"m2munrealized"));
            rmsLimit.setM2mrealized(extractValue(node,"m2mrealized"));
            rmsLimit.setUtiliseddebits(extractValue(node,"utiliseddebits"));
            rmsLimit.setUtilisedspan(extractValue(node,"utilisedspan"));
            rmsLimit.setUtilisedoptionpremium(extractValue(node,"utilisedoptionpremium"));
            rmsLimit.setUtilisedholdingsales(extractValue(node,"utilisedholdingsales"));
            rmsLimit.setUtilisedexposure(extractValue(node,"utilisedexposure"));
            rmsLimit.setUtilisedturnover(extractValue(node,"utilisedturnover"));
            rmsLimit.setUtilisedpayout(extractValue(node,"utilisedpayout"));
            return rmsLimit;
        } catch (IOException e) {
            throw new RuntimeException(e);
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
    public String toString() {
        return "AngelOne{" +
                "authTokens=" + authTokens.getFeedToken() +
                ", apiKey='" + apiKey + '\'' +
                ", clientCode='" + clientCode + '\'' +
                '}';
    }
}

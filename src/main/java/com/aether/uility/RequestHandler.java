package com.aether.uility;

import com.aether.exception.ResponseFailure;
import com.aether.types.AuthTokens;
import com.fasterxml.jackson.databind.JsonNode;
import okhttp3.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

public class RequestHandler {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final OkHttpClient client = new OkHttpClient().newBuilder().build();

    private static final MediaType mediaType = MediaType.parse("application/json");

    private static final String basePath = "https://apiconnect.angelone.in";

    private static final String type = "application/json";

    public static AuthTokens handleLogin(Map<String, String> requestBody) throws IOException {

        System.out.println(requestBody.toString());
        String jsonRequestBody = objectMapper.writeValueAsString(requestBody);
        RequestBody body = RequestBody.create(jsonRequestBody, mediaType);

        Request request = new Request.Builder()
                .url(basePath + RestPaths.LOGIN.getPath())
                .post(body)
                .addHeader("Content-Type", type)
                .addHeader("Accept", type)
                .addHeader("X-UserType", "USER")
                .addHeader("X-SourceID", "WEB")
                .addHeader("X-ClientLocalIP", "CLIENT_LOCAL_IP")
                .addHeader("X-ClientPublicIP", "CLIENT_PUBLIC_IP")
                .addHeader("X-MACAddress", "MAC_ADDRESS")
                .addHeader("X-PrivateKey", requestBody.get("api_key"))
                .build();

        Response response = client.newCall(request).execute();
        JsonNode jsonNode = objectMapper.readTree(response.body().string());
        AuthTokens authTokens = new AuthTokens();
        authTokens.setJwtToken(jsonNode.get("data").get("jwtToken").textValue());
        authTokens.setFeedToken(jsonNode.get("data").get("feedToken").textValue());
        authTokens.setRefreshToken(jsonNode.get("data").get("refreshToken").textValue());

        return authTokens;
    }


    public static JsonNode handleAny(Map<String, String> requestBody) throws IOException {
        String jsonRequestBody = objectMapper.writeValueAsString(requestBody);
        RequestBody body = RequestBody.create(jsonRequestBody, mediaType);

        Request request = new Request.Builder()
                .url(basePath + RestPaths.LOGIN.getPath())
                .post(body)
                .addHeader("Content-Type", type)
                .addHeader("Accept", type)
                .addHeader("Authorization", "Bearer "+requestBody.get("jwt_token"))
                .addHeader("X-UserType", "USER")
                .addHeader("X-SourceID", "WEB")
                .addHeader("X-ClientLocalIP", "CLIENT_LOCAL_IP")
                .addHeader("X-ClientPublicIP", "CLIENT_PUBLIC_IP")
                .addHeader("X-MACAddress", "MAC_ADDRESS")
                .addHeader("X-PrivateKey", requestBody.get("api_key"))
                .build();

        Response response = client.newCall(request).execute();

        if(response.body() == null){
            throw new ResponseFailure("Response Body is Null");
        }
        return objectMapper.readTree(response.body().string());
    }
}

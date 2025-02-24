package com.aether.types;

public class AuthTokens {

    private String jwtToken;

    private String feedToken;

    private String refreshToken;

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getFeedToken() {
        return feedToken;
    }

    public void setFeedToken(String feedToken) {
        this.feedToken = feedToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}

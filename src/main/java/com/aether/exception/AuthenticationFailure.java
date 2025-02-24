package com.aether.exception;

public class AuthenticationFailure extends RuntimeException{

    public AuthenticationFailure(String message){
        super(message);
    }
}

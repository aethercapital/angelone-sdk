package com.aether.exception;

public class ResponseFailure extends RuntimeException{

    public ResponseFailure(String message){
        super(message);
    }
}

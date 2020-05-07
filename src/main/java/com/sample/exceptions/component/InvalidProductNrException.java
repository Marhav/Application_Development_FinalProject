package com.sample.exceptions.component;

public class InvalidProductNrException extends IllegalArgumentException {
    public InvalidProductNrException(String msg){
        super(msg);
    }
}



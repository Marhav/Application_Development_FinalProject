package com.sample.exceptions.component;

public class InvalidNameException extends IllegalArgumentException {
    public InvalidNameException(String msg){
        super(msg);
    }
}



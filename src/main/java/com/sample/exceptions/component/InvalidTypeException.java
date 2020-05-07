package com.sample.exceptions.component;

public class InvalidTypeException extends IllegalArgumentException {
    public InvalidTypeException(String msg){
        super(msg);
    }
}

package com.sample.exceptions.component;

public class InvalidDescriptionException extends IllegalArgumentException {
    public InvalidDescriptionException(String msg){
        super(msg);
    }
}

package com.sample.exceptions.component;

public class InvalidPriceException extends IllegalArgumentException {
    public InvalidPriceException(String msg){
        super(msg);
    }
}



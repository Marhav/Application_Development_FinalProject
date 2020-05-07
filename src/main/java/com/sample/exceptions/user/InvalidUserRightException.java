package com.sample.exceptions.user;

public class InvalidUserRightException extends IllegalArgumentException {
    public InvalidUserRightException(String msg){
        super(msg);
    }
}

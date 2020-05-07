package com.sample.exceptions.user;

import java.io.IOException;

public class InvalidUserPasswordException extends IllegalArgumentException {
    public InvalidUserPasswordException(String msg){
        super(msg);
    }
}

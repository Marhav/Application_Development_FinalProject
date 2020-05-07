package com.sample.exceptions.user;

import java.io.IOException;

public class InvalidUserFirstNameException extends IllegalArgumentException {
    public InvalidUserFirstNameException(String msg){
        super(msg);
    }
}

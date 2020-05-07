package com.sample.exceptions.user;

import java.io.IOException;

public class InvalidUserLastNameException extends IllegalArgumentException {
    public InvalidUserLastNameException(String msg){
        super(msg);
    }
}

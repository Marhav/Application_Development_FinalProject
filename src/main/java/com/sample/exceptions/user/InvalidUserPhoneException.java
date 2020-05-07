package com.sample.exceptions.user;

import java.io.IOException;

public class InvalidUserPhoneException extends IllegalArgumentException {
    public InvalidUserPhoneException(String msg){
        super(msg);
    }
}

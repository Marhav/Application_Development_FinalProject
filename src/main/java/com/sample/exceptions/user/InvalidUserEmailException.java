package com.sample.exceptions.user;

import java.io.IOException;

public class InvalidUserEmailException extends IllegalArgumentException {
    public InvalidUserEmailException(String msg){
        super(msg);
    }

}

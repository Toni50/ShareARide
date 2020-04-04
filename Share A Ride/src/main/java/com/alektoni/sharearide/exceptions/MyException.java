package com.alektoni.sharearide.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


//todo dynamically change response code depending on response message
@ResponseStatus(value = HttpStatus.NOT_FOUND )
public class MyException extends RuntimeException {
    public MyException(String msg) {
        super(msg);
    }
}
package com.fullstack.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class RecordExistException extends RuntimeException{

    public RecordExistException(String msg){
        super(msg);
    }
}

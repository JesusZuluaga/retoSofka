package com.sofka.backend.domain.generic;

public class BusinessException extends RuntimeException {

    public BusinessException(String message){
        super(message);
    }
    public BusinessException(String message, Throwable e){
        super(message, e);
    }
}

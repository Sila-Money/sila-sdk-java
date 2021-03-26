package com.silamoney.clientrefactored.exceptions;

public class BadRequestException extends Exception{

    /**
     *
     */
    private static final Long serialVersionUID = 1L;

    public BadRequestException(String message){
        super(message);
    }
    
}

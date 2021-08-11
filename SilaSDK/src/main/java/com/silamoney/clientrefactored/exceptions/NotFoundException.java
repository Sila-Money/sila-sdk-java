package com.silamoney.clientrefactored.exceptions;

public class NotFoundException extends Exception{

    /**
     *
     */
    private static final Long serialVersionUID = 1L;

    public NotFoundException(String message){
        super(message);
    }
    
}

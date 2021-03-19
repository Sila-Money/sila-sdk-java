package com.silamoney.clientrefactored.exceptions;

public class InvalidAuthSignatureException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public InvalidAuthSignatureException(String message){
        super(message);
    }

}

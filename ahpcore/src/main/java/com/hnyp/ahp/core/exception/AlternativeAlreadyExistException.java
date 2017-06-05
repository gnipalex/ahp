package com.hnyp.ahp.core.exception;

public class AlternativeAlreadyExistException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public AlternativeAlreadyExistException() {
        super();
    }

    public AlternativeAlreadyExistException(String message) {
        super(message);
    }
    
}

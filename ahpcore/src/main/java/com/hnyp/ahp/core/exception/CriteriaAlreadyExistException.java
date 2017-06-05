package com.hnyp.ahp.core.exception;

public class CriteriaAlreadyExistException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CriteriaAlreadyExistException() {
        super();
    }

    public CriteriaAlreadyExistException(String message) {
        super(message);
    }
    
}

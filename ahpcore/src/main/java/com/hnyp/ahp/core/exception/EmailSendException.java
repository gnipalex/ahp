package com.hnyp.ahp.core.exception;

public class EmailSendException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EmailSendException() {
        super();
    }

    public EmailSendException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmailSendException(String message) {
        super(message);
    }
    
}

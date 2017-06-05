package com.hnyp.ahp.web.response;

import java.util.HashMap;
import java.util.Map;

public class ResponseWraper<T> {

    private T data;
    private boolean error;
    private Map<String, String> errorMessages = new HashMap<>();

    public ResponseWraper<T> addErrorMessage(String field, String errorMessage) {
        errorMessages.put(field, errorMessage);
        return this;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Map<String, String> getErrorMessages() {
        return errorMessages;
    }

    public void setErrorMessages(Map<String, String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

}

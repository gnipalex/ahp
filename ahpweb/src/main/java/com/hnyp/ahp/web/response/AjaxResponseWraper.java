package com.hnyp.ahp.web.response;

import java.util.HashMap;
import java.util.Map;

public class AjaxResponseWraper<T> {

    private T data;
    private boolean error;
    private String errorMessage;
    private Map<String, String> fieldErrorMessages = new HashMap<>();

    public static <T> AjaxResponseWraper<T> wrap(T data) {
        AjaxResponseWraper<T> response = new AjaxResponseWraper<>();
        response.setData(data);
        return response;
    }
    
    public AjaxResponseWraper<T> addErrorMessage(String field, String errorMessage) {
        fieldErrorMessages.put(field, errorMessage);
        return this;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Map<String, String> getFieldErrorMessages() {
        return fieldErrorMessages;
    }

    public void setFieldErrorMessages(Map<String, String> fieldErrorMessages) {
        this.fieldErrorMessages = fieldErrorMessages;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
}

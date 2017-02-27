package com.sourabhkarkal.movieapp.service;

/**
 * Created by sourabhkarkal on 24/02/17.
 */
public class DataResponse {

    Object object;
    boolean isError;
    String message;
    int responseCode;

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }
}

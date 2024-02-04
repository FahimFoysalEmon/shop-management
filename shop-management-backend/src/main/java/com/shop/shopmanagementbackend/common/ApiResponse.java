package com.shop.shopmanagementbackend.common;

import java.util.ArrayList;
import java.util.List;

public class ApiResponse {

    private String message;
    private Object data;
    private List<String> errors = new ArrayList<>();


    public ApiResponse(String message, Object data, List<String> errors) {
        this.message = message;
        this.data = data;
        this.errors = errors;
    }

    public ApiResponse(String message, List<String> errors) {
        this.message = message;
        this.errors = errors;
    }

    public ApiResponse(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public ApiResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}

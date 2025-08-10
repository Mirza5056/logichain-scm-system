package com.logichain.common.dto;
public class AuthResponse {
    private boolean success;
    private String message;
    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    private String token;
    public AuthResponse(boolean success,String message,String token) {
        this.token = token;
        this.success=success;
        this.message=message;
    }
    public String getToken() {
        return token;
    }
}
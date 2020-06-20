package com.example.vehicle.model;

public class MessageHeaders {
    public String Authorization;

    public String getAuthorization() {
        return Authorization;
    }

    public void setAuthorization(String authorization) {
        Authorization = authorization;
    }

    public MessageHeaders() {
    }

    public MessageHeaders(String authorization) {
        Authorization = authorization;
    }
}

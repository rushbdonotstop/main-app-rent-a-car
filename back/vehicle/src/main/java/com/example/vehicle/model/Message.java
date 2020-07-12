package com.example.vehicle.model;

public class Message {

    private MessageHeaders headers;
    private String body;

    public MessageHeaders getHeaders() {
        return headers;
    }

    public void setHeaders(MessageHeaders headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Message() {
    }

    public Message(MessageHeaders headers, String body) {
        this.headers = headers;
        this.body = body;
    }
}

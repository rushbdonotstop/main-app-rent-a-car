package com.example.review.model;

public class Notification {

    private String text;

    public Notification() {
    }

    public Notification(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "text='" + text + '\'' +
                '}';
    }
}

package com.example.user.model;

public class Notification {

    private String text;

    private boolean success;

    public Notification() {
    }

    public Notification(String text, boolean success) {
        this.text = text;
        this.success = success;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "text='" + text + '\'' +
                ", success=" + success +
                '}';
    }
}

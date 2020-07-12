package com.example.message.model.enums;

public enum MessageType {
    SENT_MESSAGE,
    RECEIVED_MESSAGE;

    public static MessageType toEnum(String type) {
        switch (type.toUpperCase()) {
            case "SENT_MESSAGE":
                return SENT_MESSAGE;
            case "RECEIVED_MESSAGE":
                return RECEIVED_MESSAGE;
            default:
                throw new IllegalArgumentException("Message type " + type + " does not exist.");
        }
    }

    public static int toInt(String type) {
        switch (type.toUpperCase()) {
            case "SENT_MESSAGE":
                return 0;
            case "RECEIVED_MESSAGE":
                return 1;
            default:
                throw new IllegalArgumentException("Message type " + type + " does not exist.");
        }
    }
}

package com.example.user.model.enums;

public enum UserType {
    ADMINISTRATOR,
    AGENT,
    END_USER;

    public static UserType toEnum(String type) {
        switch (type.toUpperCase()) {
            case "ADMINISTRATOR":
                return ADMINISTRATOR;
            case "AGENT":
                return AGENT;
            case "END_USER":
                return END_USER;
            default:
                throw new IllegalArgumentException("Status " + type + " does not exist.");
        }
    }
}

package com.example.user.model.enums;

public enum Privilege {
    RENT_VEHICLE,
    ADD_DISCOUNT,
    GET_STATISTIC,
    ADD_VEHICLE;

    public static Privilege toEnum(String type) {
        switch (type.toUpperCase()) {
            case "RENT_VEHICLE":
                return RENT_VEHICLE;
            case "ADD_DISCOUNT":
                return ADD_DISCOUNT;
            case "GET_STATISTIC":
                return GET_STATISTIC;
            case "ADD_VEHICLE":
                return ADD_VEHICLE;
            default:
                throw new IllegalArgumentException("Status " + type + " does not exist.");
        }
    }
}

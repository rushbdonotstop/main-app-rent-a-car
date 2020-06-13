package com.example.user.model.enums;

import java.util.HashMap;
import java.util.Map;

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

    public static int toInt(String type) {
        switch (type.toUpperCase()) {
            case "RENT_VEHICLE":
                return 0;
            case "ADD_DISCOUNT":
                return 1;
            case "GET_STATISTIC":
                return 2;
            case "ADD_VEHICLE":
                return 3;
            default:
                throw new IllegalArgumentException("Status " + type + " does not exist.");
        }
    }
}

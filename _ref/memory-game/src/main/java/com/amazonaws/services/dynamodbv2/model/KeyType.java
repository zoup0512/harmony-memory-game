package com.amazonaws.services.dynamodbv2.model;

import java.util.HashMap;
import java.util.Map;

public enum KeyType {
    HASH("HASH"),
    RANGE("RANGE");
    
    private static final Map<String, KeyType> enumMap = null;
    private String value;

    static {
        enumMap = new HashMap();
        enumMap.put("HASH", HASH);
        enumMap.put("RANGE", RANGE);
    }

    private KeyType(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }

    public static KeyType fromValue(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        } else if (enumMap.containsKey(value)) {
            return (KeyType) enumMap.get(value);
        } else {
            throw new IllegalArgumentException("Cannot create enum from " + value + " value!");
        }
    }
}

package com.amazonaws.services.dynamodbv2.model;

import java.util.HashMap;
import java.util.Map;

public enum ReturnValue {
    NONE("NONE"),
    ALL_OLD("ALL_OLD"),
    UPDATED_OLD("UPDATED_OLD"),
    ALL_NEW("ALL_NEW"),
    UPDATED_NEW("UPDATED_NEW");
    
    private static final Map<String, ReturnValue> enumMap = null;
    private String value;

    static {
        enumMap = new HashMap();
        enumMap.put("NONE", NONE);
        enumMap.put("ALL_OLD", ALL_OLD);
        enumMap.put("UPDATED_OLD", UPDATED_OLD);
        enumMap.put("ALL_NEW", ALL_NEW);
        enumMap.put("UPDATED_NEW", UPDATED_NEW);
    }

    private ReturnValue(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }

    public static ReturnValue fromValue(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        } else if (enumMap.containsKey(value)) {
            return (ReturnValue) enumMap.get(value);
        } else {
            throw new IllegalArgumentException("Cannot create enum from " + value + " value!");
        }
    }
}

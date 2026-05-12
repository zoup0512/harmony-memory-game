package com.amazonaws.services.dynamodbv2.model;

import java.util.HashMap;
import java.util.Map;

public enum ReturnConsumedCapacity {
    INDEXES("INDEXES"),
    TOTAL("TOTAL"),
    NONE("NONE");
    
    private static final Map<String, ReturnConsumedCapacity> enumMap = null;
    private String value;

    static {
        enumMap = new HashMap();
        enumMap.put("INDEXES", INDEXES);
        enumMap.put("TOTAL", TOTAL);
        enumMap.put("NONE", NONE);
    }

    private ReturnConsumedCapacity(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }

    public static ReturnConsumedCapacity fromValue(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        } else if (enumMap.containsKey(value)) {
            return (ReturnConsumedCapacity) enumMap.get(value);
        } else {
            throw new IllegalArgumentException("Cannot create enum from " + value + " value!");
        }
    }
}

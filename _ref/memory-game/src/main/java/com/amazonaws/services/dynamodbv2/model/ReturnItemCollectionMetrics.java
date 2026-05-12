package com.amazonaws.services.dynamodbv2.model;

import java.util.HashMap;
import java.util.Map;

public enum ReturnItemCollectionMetrics {
    SIZE("SIZE"),
    NONE("NONE");
    
    private static final Map<String, ReturnItemCollectionMetrics> enumMap = null;
    private String value;

    static {
        enumMap = new HashMap();
        enumMap.put("SIZE", SIZE);
        enumMap.put("NONE", NONE);
    }

    private ReturnItemCollectionMetrics(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }

    public static ReturnItemCollectionMetrics fromValue(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        } else if (enumMap.containsKey(value)) {
            return (ReturnItemCollectionMetrics) enumMap.get(value);
        } else {
            throw new IllegalArgumentException("Cannot create enum from " + value + " value!");
        }
    }
}

package com.amazonaws.services.dynamodbv2.model;

import java.util.HashMap;
import java.util.Map;

public enum ProjectionType {
    ALL("ALL"),
    KEYS_ONLY("KEYS_ONLY"),
    INCLUDE("INCLUDE");
    
    private static final Map<String, ProjectionType> enumMap = null;
    private String value;

    static {
        enumMap = new HashMap();
        enumMap.put("ALL", ALL);
        enumMap.put("KEYS_ONLY", KEYS_ONLY);
        enumMap.put("INCLUDE", INCLUDE);
    }

    private ProjectionType(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }

    public static ProjectionType fromValue(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        } else if (enumMap.containsKey(value)) {
            return (ProjectionType) enumMap.get(value);
        } else {
            throw new IllegalArgumentException("Cannot create enum from " + value + " value!");
        }
    }
}

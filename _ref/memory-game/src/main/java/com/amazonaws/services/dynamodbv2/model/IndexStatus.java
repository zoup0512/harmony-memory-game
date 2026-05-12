package com.amazonaws.services.dynamodbv2.model;

import java.util.HashMap;
import java.util.Map;

public enum IndexStatus {
    CREATING("CREATING"),
    UPDATING("UPDATING"),
    DELETING("DELETING"),
    ACTIVE("ACTIVE");
    
    private static final Map<String, IndexStatus> enumMap = null;
    private String value;

    static {
        enumMap = new HashMap();
        enumMap.put("CREATING", CREATING);
        enumMap.put("UPDATING", UPDATING);
        enumMap.put("DELETING", DELETING);
        enumMap.put("ACTIVE", ACTIVE);
    }

    private IndexStatus(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }

    public static IndexStatus fromValue(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        } else if (enumMap.containsKey(value)) {
            return (IndexStatus) enumMap.get(value);
        } else {
            throw new IllegalArgumentException("Cannot create enum from " + value + " value!");
        }
    }
}

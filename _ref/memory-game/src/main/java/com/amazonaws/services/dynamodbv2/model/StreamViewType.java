package com.amazonaws.services.dynamodbv2.model;

import java.util.HashMap;
import java.util.Map;

public enum StreamViewType {
    NEW_IMAGE("NEW_IMAGE"),
    OLD_IMAGE("OLD_IMAGE"),
    NEW_AND_OLD_IMAGES("NEW_AND_OLD_IMAGES"),
    KEYS_ONLY("KEYS_ONLY");
    
    private static final Map<String, StreamViewType> enumMap = null;
    private String value;

    static {
        enumMap = new HashMap();
        enumMap.put("NEW_IMAGE", NEW_IMAGE);
        enumMap.put("OLD_IMAGE", OLD_IMAGE);
        enumMap.put("NEW_AND_OLD_IMAGES", NEW_AND_OLD_IMAGES);
        enumMap.put("KEYS_ONLY", KEYS_ONLY);
    }

    private StreamViewType(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }

    public static StreamViewType fromValue(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        } else if (enumMap.containsKey(value)) {
            return (StreamViewType) enumMap.get(value);
        } else {
            throw new IllegalArgumentException("Cannot create enum from " + value + " value!");
        }
    }
}

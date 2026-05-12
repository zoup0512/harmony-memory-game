package com.amazonaws.services.dynamodbv2.model;

import java.util.HashMap;
import java.util.Map;

public enum Select {
    ALL_ATTRIBUTES("ALL_ATTRIBUTES"),
    ALL_PROJECTED_ATTRIBUTES("ALL_PROJECTED_ATTRIBUTES"),
    SPECIFIC_ATTRIBUTES("SPECIFIC_ATTRIBUTES"),
    COUNT("COUNT");
    
    private static final Map<String, Select> enumMap = null;
    private String value;

    static {
        enumMap = new HashMap();
        enumMap.put("ALL_ATTRIBUTES", ALL_ATTRIBUTES);
        enumMap.put("ALL_PROJECTED_ATTRIBUTES", ALL_PROJECTED_ATTRIBUTES);
        enumMap.put("SPECIFIC_ATTRIBUTES", SPECIFIC_ATTRIBUTES);
        enumMap.put("COUNT", COUNT);
    }

    private Select(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }

    public static Select fromValue(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        } else if (enumMap.containsKey(value)) {
            return (Select) enumMap.get(value);
        } else {
            throw new IllegalArgumentException("Cannot create enum from " + value + " value!");
        }
    }
}

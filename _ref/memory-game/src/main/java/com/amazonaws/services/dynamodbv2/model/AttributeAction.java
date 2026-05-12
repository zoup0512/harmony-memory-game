package com.amazonaws.services.dynamodbv2.model;

import io.fabric.sdk.android.services.network.HttpRequest;
import java.util.HashMap;
import java.util.Map;

public enum AttributeAction {
    ADD("ADD"),
    PUT(HttpRequest.METHOD_PUT),
    DELETE(HttpRequest.METHOD_DELETE);
    
    private static final Map<String, AttributeAction> enumMap = null;
    private String value;

    static {
        enumMap = new HashMap();
        enumMap.put("ADD", ADD);
        enumMap.put(HttpRequest.METHOD_PUT, PUT);
        enumMap.put(HttpRequest.METHOD_DELETE, DELETE);
    }

    private AttributeAction(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }

    public static AttributeAction fromValue(String value) {
        if (value == null || value.isEmpty()) {
            throw new IllegalArgumentException("Value cannot be null or empty!");
        } else if (enumMap.containsKey(value)) {
            return (AttributeAction) enumMap.get(value);
        } else {
            throw new IllegalArgumentException("Cannot create enum from " + value + " value!");
        }
    }
}

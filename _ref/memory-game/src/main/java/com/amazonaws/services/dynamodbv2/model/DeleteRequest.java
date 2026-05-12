package com.amazonaws.services.dynamodbv2.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class DeleteRequest implements Serializable {
    private Map<String, AttributeValue> key;

    public DeleteRequest(Map<String, AttributeValue> key) {
        setKey(key);
    }

    public Map<String, AttributeValue> getKey() {
        return this.key;
    }

    public void setKey(Map<String, AttributeValue> key) {
        this.key = key;
    }

    public DeleteRequest withKey(Map<String, AttributeValue> key) {
        this.key = key;
        return this;
    }

    public DeleteRequest addKeyEntry(String key, AttributeValue value) {
        if (this.key == null) {
            this.key = new HashMap();
        }
        if (this.key.containsKey(key)) {
            throw new IllegalArgumentException("Duplicated keys (" + key.toString() + ") are provided.");
        }
        this.key.put(key, value);
        return this;
    }

    public DeleteRequest clearKeyEntries() {
        this.key = null;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getKey() != null) {
            sb.append("Key: " + getKey());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (getKey() == null ? 0 : getKey().hashCode()) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DeleteRequest)) {
            return false;
        }
        int i;
        DeleteRequest other = (DeleteRequest) obj;
        if (other.getKey() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getKey() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getKey() == null || other.getKey().equals(getKey())) {
            return true;
        }
        return false;
    }
}

package com.amazonaws.services.dynamodbv2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KeysAndAttributes implements Serializable {
    private List<String> attributesToGet;
    private Boolean consistentRead;
    private Map<String, String> expressionAttributeNames;
    private List<Map<String, AttributeValue>> keys;
    private String projectionExpression;

    public List<Map<String, AttributeValue>> getKeys() {
        return this.keys;
    }

    public void setKeys(Collection<Map<String, AttributeValue>> keys) {
        if (keys == null) {
            this.keys = null;
        } else {
            this.keys = new ArrayList(keys);
        }
    }

    public KeysAndAttributes withKeys(Map<String, AttributeValue>... keys) {
        if (getKeys() == null) {
            this.keys = new ArrayList(keys.length);
        }
        for (Map<String, AttributeValue> value : keys) {
            this.keys.add(value);
        }
        return this;
    }

    public KeysAndAttributes withKeys(Collection<Map<String, AttributeValue>> keys) {
        setKeys(keys);
        return this;
    }

    public List<String> getAttributesToGet() {
        return this.attributesToGet;
    }

    public void setAttributesToGet(Collection<String> attributesToGet) {
        if (attributesToGet == null) {
            this.attributesToGet = null;
        } else {
            this.attributesToGet = new ArrayList(attributesToGet);
        }
    }

    public KeysAndAttributes withAttributesToGet(String... attributesToGet) {
        if (getAttributesToGet() == null) {
            this.attributesToGet = new ArrayList(attributesToGet.length);
        }
        for (String value : attributesToGet) {
            this.attributesToGet.add(value);
        }
        return this;
    }

    public KeysAndAttributes withAttributesToGet(Collection<String> attributesToGet) {
        setAttributesToGet(attributesToGet);
        return this;
    }

    public Boolean isConsistentRead() {
        return this.consistentRead;
    }

    public Boolean getConsistentRead() {
        return this.consistentRead;
    }

    public void setConsistentRead(Boolean consistentRead) {
        this.consistentRead = consistentRead;
    }

    public KeysAndAttributes withConsistentRead(Boolean consistentRead) {
        this.consistentRead = consistentRead;
        return this;
    }

    public String getProjectionExpression() {
        return this.projectionExpression;
    }

    public void setProjectionExpression(String projectionExpression) {
        this.projectionExpression = projectionExpression;
    }

    public KeysAndAttributes withProjectionExpression(String projectionExpression) {
        this.projectionExpression = projectionExpression;
        return this;
    }

    public Map<String, String> getExpressionAttributeNames() {
        return this.expressionAttributeNames;
    }

    public void setExpressionAttributeNames(Map<String, String> expressionAttributeNames) {
        this.expressionAttributeNames = expressionAttributeNames;
    }

    public KeysAndAttributes withExpressionAttributeNames(Map<String, String> expressionAttributeNames) {
        this.expressionAttributeNames = expressionAttributeNames;
        return this;
    }

    public KeysAndAttributes addExpressionAttributeNamesEntry(String key, String value) {
        if (this.expressionAttributeNames == null) {
            this.expressionAttributeNames = new HashMap();
        }
        if (this.expressionAttributeNames.containsKey(key)) {
            throw new IllegalArgumentException("Duplicated keys (" + key.toString() + ") are provided.");
        }
        this.expressionAttributeNames.put(key, value);
        return this;
    }

    public KeysAndAttributes clearExpressionAttributeNamesEntries() {
        this.expressionAttributeNames = null;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getKeys() != null) {
            sb.append("Keys: " + getKeys() + ",");
        }
        if (getAttributesToGet() != null) {
            sb.append("AttributesToGet: " + getAttributesToGet() + ",");
        }
        if (getConsistentRead() != null) {
            sb.append("ConsistentRead: " + getConsistentRead() + ",");
        }
        if (getProjectionExpression() != null) {
            sb.append("ProjectionExpression: " + getProjectionExpression() + ",");
        }
        if (getExpressionAttributeNames() != null) {
            sb.append("ExpressionAttributeNames: " + getExpressionAttributeNames());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((getKeys() == null ? 0 : getKeys().hashCode()) + 31) * 31) + (getAttributesToGet() == null ? 0 : getAttributesToGet().hashCode())) * 31) + (getConsistentRead() == null ? 0 : getConsistentRead().hashCode())) * 31) + (getProjectionExpression() == null ? 0 : getProjectionExpression().hashCode())) * 31;
        if (getExpressionAttributeNames() != null) {
            i = getExpressionAttributeNames().hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof KeysAndAttributes)) {
            return false;
        }
        KeysAndAttributes other = (KeysAndAttributes) obj;
        if (((other.getKeys() == null ? 1 : 0) ^ (getKeys() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getKeys() != null && !other.getKeys().equals(getKeys())) {
            return false;
        }
        int i;
        if (other.getAttributesToGet() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getAttributesToGet() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getAttributesToGet() != null && !other.getAttributesToGet().equals(getAttributesToGet())) {
            return false;
        }
        if (other.getConsistentRead() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getConsistentRead() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getConsistentRead() != null && !other.getConsistentRead().equals(getConsistentRead())) {
            return false;
        }
        if (other.getProjectionExpression() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getProjectionExpression() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getProjectionExpression() != null && !other.getProjectionExpression().equals(getProjectionExpression())) {
            return false;
        }
        int i2;
        if (other.getExpressionAttributeNames() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if (getExpressionAttributeNames() == null) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        if ((i ^ i2) != 0) {
            return false;
        }
        if (other.getExpressionAttributeNames() == null || other.getExpressionAttributeNames().equals(getExpressionAttributeNames())) {
            return true;
        }
        return false;
    }
}

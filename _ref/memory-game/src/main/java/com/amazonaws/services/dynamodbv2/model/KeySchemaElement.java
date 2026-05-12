package com.amazonaws.services.dynamodbv2.model;

import java.io.Serializable;

public class KeySchemaElement implements Serializable {
    private String attributeName;
    private String keyType;

    public KeySchemaElement(String attributeName, String keyType) {
        setAttributeName(attributeName);
        setKeyType(keyType);
    }

    public KeySchemaElement(String attributeName, KeyType keyType) {
        setAttributeName(attributeName);
        setKeyType(keyType.toString());
    }

    public String getAttributeName() {
        return this.attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public KeySchemaElement withAttributeName(String attributeName) {
        this.attributeName = attributeName;
        return this;
    }

    public String getKeyType() {
        return this.keyType;
    }

    public void setKeyType(String keyType) {
        this.keyType = keyType;
    }

    public KeySchemaElement withKeyType(String keyType) {
        this.keyType = keyType;
        return this;
    }

    public void setKeyType(KeyType keyType) {
        this.keyType = keyType.toString();
    }

    public KeySchemaElement withKeyType(KeyType keyType) {
        this.keyType = keyType.toString();
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getAttributeName() != null) {
            sb.append("AttributeName: " + getAttributeName() + ",");
        }
        if (getKeyType() != null) {
            sb.append("KeyType: " + getKeyType());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getAttributeName() == null ? 0 : getAttributeName().hashCode()) + 31) * 31;
        if (getKeyType() != null) {
            i = getKeyType().hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof KeySchemaElement)) {
            return false;
        }
        KeySchemaElement other = (KeySchemaElement) obj;
        if (((other.getAttributeName() == null ? 1 : 0) ^ (getAttributeName() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getAttributeName() != null && !other.getAttributeName().equals(getAttributeName())) {
            return false;
        }
        int i;
        if (other.getKeyType() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getKeyType() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getKeyType() == null || other.getKeyType().equals(getKeyType())) {
            return true;
        }
        return false;
    }
}

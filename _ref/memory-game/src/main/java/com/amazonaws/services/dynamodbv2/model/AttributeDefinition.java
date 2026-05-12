package com.amazonaws.services.dynamodbv2.model;

import java.io.Serializable;

public class AttributeDefinition implements Serializable {
    private String attributeName;
    private String attributeType;

    public AttributeDefinition(String attributeName, String attributeType) {
        setAttributeName(attributeName);
        setAttributeType(attributeType);
    }

    public AttributeDefinition(String attributeName, ScalarAttributeType attributeType) {
        setAttributeName(attributeName);
        setAttributeType(attributeType.toString());
    }

    public String getAttributeName() {
        return this.attributeName;
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

    public AttributeDefinition withAttributeName(String attributeName) {
        this.attributeName = attributeName;
        return this;
    }

    public String getAttributeType() {
        return this.attributeType;
    }

    public void setAttributeType(String attributeType) {
        this.attributeType = attributeType;
    }

    public AttributeDefinition withAttributeType(String attributeType) {
        this.attributeType = attributeType;
        return this;
    }

    public void setAttributeType(ScalarAttributeType attributeType) {
        this.attributeType = attributeType.toString();
    }

    public AttributeDefinition withAttributeType(ScalarAttributeType attributeType) {
        this.attributeType = attributeType.toString();
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getAttributeName() != null) {
            sb.append("AttributeName: " + getAttributeName() + ",");
        }
        if (getAttributeType() != null) {
            sb.append("AttributeType: " + getAttributeType());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getAttributeName() == null ? 0 : getAttributeName().hashCode()) + 31) * 31;
        if (getAttributeType() != null) {
            i = getAttributeType().hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AttributeDefinition)) {
            return false;
        }
        AttributeDefinition other = (AttributeDefinition) obj;
        if (((other.getAttributeName() == null ? 1 : 0) ^ (getAttributeName() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getAttributeName() != null && !other.getAttributeName().equals(getAttributeName())) {
            return false;
        }
        int i;
        if (other.getAttributeType() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getAttributeType() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getAttributeType() == null || other.getAttributeType().equals(getAttributeType())) {
            return true;
        }
        return false;
    }
}

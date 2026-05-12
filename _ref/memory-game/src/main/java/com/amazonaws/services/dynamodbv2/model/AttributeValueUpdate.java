package com.amazonaws.services.dynamodbv2.model;

import java.io.Serializable;

public class AttributeValueUpdate implements Serializable {
    private String action;
    private AttributeValue value;

    public AttributeValueUpdate(AttributeValue value, String action) {
        setValue(value);
        setAction(action);
    }

    public AttributeValueUpdate(AttributeValue value, AttributeAction action) {
        setValue(value);
        setAction(action.toString());
    }

    public AttributeValue getValue() {
        return this.value;
    }

    public void setValue(AttributeValue value) {
        this.value = value;
    }

    public AttributeValueUpdate withValue(AttributeValue value) {
        this.value = value;
        return this;
    }

    public String getAction() {
        return this.action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public AttributeValueUpdate withAction(String action) {
        this.action = action;
        return this;
    }

    public void setAction(AttributeAction action) {
        this.action = action.toString();
    }

    public AttributeValueUpdate withAction(AttributeAction action) {
        this.action = action.toString();
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getValue() != null) {
            sb.append("Value: " + getValue() + ",");
        }
        if (getAction() != null) {
            sb.append("Action: " + getAction());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getValue() == null ? 0 : getValue().hashCode()) + 31) * 31;
        if (getAction() != null) {
            i = getAction().hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AttributeValueUpdate)) {
            return false;
        }
        AttributeValueUpdate other = (AttributeValueUpdate) obj;
        if (((other.getValue() == null ? 1 : 0) ^ (getValue() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getValue() != null && !other.getValue().equals(getValue())) {
            return false;
        }
        int i;
        if (other.getAction() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getAction() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getAction() == null || other.getAction().equals(getAction())) {
            return true;
        }
        return false;
    }
}

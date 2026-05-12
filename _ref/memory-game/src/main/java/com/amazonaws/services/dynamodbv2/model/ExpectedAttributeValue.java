package com.amazonaws.services.dynamodbv2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ExpectedAttributeValue implements Serializable {
    private List<AttributeValue> attributeValueList;
    private String comparisonOperator;
    private Boolean exists;
    private AttributeValue value;

    public ExpectedAttributeValue(AttributeValue value) {
        setValue(value);
    }

    public ExpectedAttributeValue(Boolean exists) {
        setExists(exists);
    }

    public AttributeValue getValue() {
        return this.value;
    }

    public void setValue(AttributeValue value) {
        this.value = value;
    }

    public ExpectedAttributeValue withValue(AttributeValue value) {
        this.value = value;
        return this;
    }

    public Boolean isExists() {
        return this.exists;
    }

    public Boolean getExists() {
        return this.exists;
    }

    public void setExists(Boolean exists) {
        this.exists = exists;
    }

    public ExpectedAttributeValue withExists(Boolean exists) {
        this.exists = exists;
        return this;
    }

    public String getComparisonOperator() {
        return this.comparisonOperator;
    }

    public void setComparisonOperator(String comparisonOperator) {
        this.comparisonOperator = comparisonOperator;
    }

    public ExpectedAttributeValue withComparisonOperator(String comparisonOperator) {
        this.comparisonOperator = comparisonOperator;
        return this;
    }

    public void setComparisonOperator(ComparisonOperator comparisonOperator) {
        this.comparisonOperator = comparisonOperator.toString();
    }

    public ExpectedAttributeValue withComparisonOperator(ComparisonOperator comparisonOperator) {
        this.comparisonOperator = comparisonOperator.toString();
        return this;
    }

    public List<AttributeValue> getAttributeValueList() {
        return this.attributeValueList;
    }

    public void setAttributeValueList(Collection<AttributeValue> attributeValueList) {
        if (attributeValueList == null) {
            this.attributeValueList = null;
        } else {
            this.attributeValueList = new ArrayList(attributeValueList);
        }
    }

    public ExpectedAttributeValue withAttributeValueList(AttributeValue... attributeValueList) {
        if (getAttributeValueList() == null) {
            this.attributeValueList = new ArrayList(attributeValueList.length);
        }
        for (AttributeValue value : attributeValueList) {
            this.attributeValueList.add(value);
        }
        return this;
    }

    public ExpectedAttributeValue withAttributeValueList(Collection<AttributeValue> attributeValueList) {
        setAttributeValueList(attributeValueList);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getValue() != null) {
            sb.append("Value: " + getValue() + ",");
        }
        if (getExists() != null) {
            sb.append("Exists: " + getExists() + ",");
        }
        if (getComparisonOperator() != null) {
            sb.append("ComparisonOperator: " + getComparisonOperator() + ",");
        }
        if (getAttributeValueList() != null) {
            sb.append("AttributeValueList: " + getAttributeValueList());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((getValue() == null ? 0 : getValue().hashCode()) + 31) * 31) + (getExists() == null ? 0 : getExists().hashCode())) * 31) + (getComparisonOperator() == null ? 0 : getComparisonOperator().hashCode())) * 31;
        if (getAttributeValueList() != null) {
            i = getAttributeValueList().hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ExpectedAttributeValue)) {
            return false;
        }
        ExpectedAttributeValue other = (ExpectedAttributeValue) obj;
        if (((other.getValue() == null ? 1 : 0) ^ (getValue() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getValue() != null && !other.getValue().equals(getValue())) {
            return false;
        }
        int i;
        if (other.getExists() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getExists() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getExists() != null && !other.getExists().equals(getExists())) {
            return false;
        }
        if (other.getComparisonOperator() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getComparisonOperator() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getComparisonOperator() != null && !other.getComparisonOperator().equals(getComparisonOperator())) {
            return false;
        }
        if (other.getAttributeValueList() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getAttributeValueList() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getAttributeValueList() == null || other.getAttributeValueList().equals(getAttributeValueList())) {
            return true;
        }
        return false;
    }
}

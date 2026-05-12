package com.amazonaws.services.dynamodbv2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Condition implements Serializable {
    private List<AttributeValue> attributeValueList;
    private String comparisonOperator;

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

    public Condition withAttributeValueList(AttributeValue... attributeValueList) {
        if (getAttributeValueList() == null) {
            this.attributeValueList = new ArrayList(attributeValueList.length);
        }
        for (AttributeValue value : attributeValueList) {
            this.attributeValueList.add(value);
        }
        return this;
    }

    public Condition withAttributeValueList(Collection<AttributeValue> attributeValueList) {
        setAttributeValueList(attributeValueList);
        return this;
    }

    public String getComparisonOperator() {
        return this.comparisonOperator;
    }

    public void setComparisonOperator(String comparisonOperator) {
        this.comparisonOperator = comparisonOperator;
    }

    public Condition withComparisonOperator(String comparisonOperator) {
        this.comparisonOperator = comparisonOperator;
        return this;
    }

    public void setComparisonOperator(ComparisonOperator comparisonOperator) {
        this.comparisonOperator = comparisonOperator.toString();
    }

    public Condition withComparisonOperator(ComparisonOperator comparisonOperator) {
        this.comparisonOperator = comparisonOperator.toString();
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getAttributeValueList() != null) {
            sb.append("AttributeValueList: " + getAttributeValueList() + ",");
        }
        if (getComparisonOperator() != null) {
            sb.append("ComparisonOperator: " + getComparisonOperator());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getAttributeValueList() == null ? 0 : getAttributeValueList().hashCode()) + 31) * 31;
        if (getComparisonOperator() != null) {
            i = getComparisonOperator().hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Condition)) {
            return false;
        }
        Condition other = (Condition) obj;
        if (((other.getAttributeValueList() == null ? 1 : 0) ^ (getAttributeValueList() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getAttributeValueList() != null && !other.getAttributeValueList().equals(getAttributeValueList())) {
            return false;
        }
        int i;
        if (other.getComparisonOperator() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getComparisonOperator() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getComparisonOperator() == null || other.getComparisonOperator().equals(getComparisonOperator())) {
            return true;
        }
        return false;
    }
}

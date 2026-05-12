package com.amazonaws.services.dynamodbv2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemCollectionMetrics implements Serializable {
    private Map<String, AttributeValue> itemCollectionKey;
    private List<Double> sizeEstimateRangeGB;

    public Map<String, AttributeValue> getItemCollectionKey() {
        return this.itemCollectionKey;
    }

    public void setItemCollectionKey(Map<String, AttributeValue> itemCollectionKey) {
        this.itemCollectionKey = itemCollectionKey;
    }

    public ItemCollectionMetrics withItemCollectionKey(Map<String, AttributeValue> itemCollectionKey) {
        this.itemCollectionKey = itemCollectionKey;
        return this;
    }

    public ItemCollectionMetrics addItemCollectionKeyEntry(String key, AttributeValue value) {
        if (this.itemCollectionKey == null) {
            this.itemCollectionKey = new HashMap();
        }
        if (this.itemCollectionKey.containsKey(key)) {
            throw new IllegalArgumentException("Duplicated keys (" + key.toString() + ") are provided.");
        }
        this.itemCollectionKey.put(key, value);
        return this;
    }

    public ItemCollectionMetrics clearItemCollectionKeyEntries() {
        this.itemCollectionKey = null;
        return this;
    }

    public List<Double> getSizeEstimateRangeGB() {
        return this.sizeEstimateRangeGB;
    }

    public void setSizeEstimateRangeGB(Collection<Double> sizeEstimateRangeGB) {
        if (sizeEstimateRangeGB == null) {
            this.sizeEstimateRangeGB = null;
        } else {
            this.sizeEstimateRangeGB = new ArrayList(sizeEstimateRangeGB);
        }
    }

    public ItemCollectionMetrics withSizeEstimateRangeGB(Double... sizeEstimateRangeGB) {
        if (getSizeEstimateRangeGB() == null) {
            this.sizeEstimateRangeGB = new ArrayList(sizeEstimateRangeGB.length);
        }
        for (Double value : sizeEstimateRangeGB) {
            this.sizeEstimateRangeGB.add(value);
        }
        return this;
    }

    public ItemCollectionMetrics withSizeEstimateRangeGB(Collection<Double> sizeEstimateRangeGB) {
        setSizeEstimateRangeGB(sizeEstimateRangeGB);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getItemCollectionKey() != null) {
            sb.append("ItemCollectionKey: " + getItemCollectionKey() + ",");
        }
        if (getSizeEstimateRangeGB() != null) {
            sb.append("SizeEstimateRangeGB: " + getSizeEstimateRangeGB());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getItemCollectionKey() == null ? 0 : getItemCollectionKey().hashCode()) + 31) * 31;
        if (getSizeEstimateRangeGB() != null) {
            i = getSizeEstimateRangeGB().hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ItemCollectionMetrics)) {
            return false;
        }
        ItemCollectionMetrics other = (ItemCollectionMetrics) obj;
        if (((other.getItemCollectionKey() == null ? 1 : 0) ^ (getItemCollectionKey() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getItemCollectionKey() != null && !other.getItemCollectionKey().equals(getItemCollectionKey())) {
            return false;
        }
        int i;
        if (other.getSizeEstimateRangeGB() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getSizeEstimateRangeGB() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getSizeEstimateRangeGB() == null || other.getSizeEstimateRangeGB().equals(getSizeEstimateRangeGB())) {
            return true;
        }
        return false;
    }
}

package com.amazonaws.services.dynamodbv2.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class GetItemResult implements Serializable {
    private ConsumedCapacity consumedCapacity;
    private Map<String, AttributeValue> item;

    public Map<String, AttributeValue> getItem() {
        return this.item;
    }

    public void setItem(Map<String, AttributeValue> item) {
        this.item = item;
    }

    public GetItemResult withItem(Map<String, AttributeValue> item) {
        this.item = item;
        return this;
    }

    public GetItemResult addItemEntry(String key, AttributeValue value) {
        if (this.item == null) {
            this.item = new HashMap();
        }
        if (this.item.containsKey(key)) {
            throw new IllegalArgumentException("Duplicated keys (" + key.toString() + ") are provided.");
        }
        this.item.put(key, value);
        return this;
    }

    public GetItemResult clearItemEntries() {
        this.item = null;
        return this;
    }

    public ConsumedCapacity getConsumedCapacity() {
        return this.consumedCapacity;
    }

    public void setConsumedCapacity(ConsumedCapacity consumedCapacity) {
        this.consumedCapacity = consumedCapacity;
    }

    public GetItemResult withConsumedCapacity(ConsumedCapacity consumedCapacity) {
        this.consumedCapacity = consumedCapacity;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getItem() != null) {
            sb.append("Item: " + getItem() + ",");
        }
        if (getConsumedCapacity() != null) {
            sb.append("ConsumedCapacity: " + getConsumedCapacity());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getItem() == null ? 0 : getItem().hashCode()) + 31) * 31;
        if (getConsumedCapacity() != null) {
            i = getConsumedCapacity().hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetItemResult)) {
            return false;
        }
        GetItemResult other = (GetItemResult) obj;
        if (((other.getItem() == null ? 1 : 0) ^ (getItem() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getItem() != null && !other.getItem().equals(getItem())) {
            return false;
        }
        int i;
        if (other.getConsumedCapacity() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getConsumedCapacity() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getConsumedCapacity() == null || other.getConsumedCapacity().equals(getConsumedCapacity())) {
            return true;
        }
        return false;
    }
}

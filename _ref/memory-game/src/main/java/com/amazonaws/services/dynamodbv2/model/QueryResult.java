package com.amazonaws.services.dynamodbv2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryResult implements Serializable {
    private ConsumedCapacity consumedCapacity;
    private Integer count;
    private List<Map<String, AttributeValue>> items;
    private Map<String, AttributeValue> lastEvaluatedKey;
    private Integer scannedCount;

    public List<Map<String, AttributeValue>> getItems() {
        return this.items;
    }

    public void setItems(Collection<Map<String, AttributeValue>> items) {
        if (items == null) {
            this.items = null;
        } else {
            this.items = new ArrayList(items);
        }
    }

    public QueryResult withItems(Map<String, AttributeValue>... items) {
        if (getItems() == null) {
            this.items = new ArrayList(items.length);
        }
        for (Map<String, AttributeValue> value : items) {
            this.items.add(value);
        }
        return this;
    }

    public QueryResult withItems(Collection<Map<String, AttributeValue>> items) {
        setItems(items);
        return this;
    }

    public Integer getCount() {
        return this.count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public QueryResult withCount(Integer count) {
        this.count = count;
        return this;
    }

    public Integer getScannedCount() {
        return this.scannedCount;
    }

    public void setScannedCount(Integer scannedCount) {
        this.scannedCount = scannedCount;
    }

    public QueryResult withScannedCount(Integer scannedCount) {
        this.scannedCount = scannedCount;
        return this;
    }

    public Map<String, AttributeValue> getLastEvaluatedKey() {
        return this.lastEvaluatedKey;
    }

    public void setLastEvaluatedKey(Map<String, AttributeValue> lastEvaluatedKey) {
        this.lastEvaluatedKey = lastEvaluatedKey;
    }

    public QueryResult withLastEvaluatedKey(Map<String, AttributeValue> lastEvaluatedKey) {
        this.lastEvaluatedKey = lastEvaluatedKey;
        return this;
    }

    public QueryResult addLastEvaluatedKeyEntry(String key, AttributeValue value) {
        if (this.lastEvaluatedKey == null) {
            this.lastEvaluatedKey = new HashMap();
        }
        if (this.lastEvaluatedKey.containsKey(key)) {
            throw new IllegalArgumentException("Duplicated keys (" + key.toString() + ") are provided.");
        }
        this.lastEvaluatedKey.put(key, value);
        return this;
    }

    public QueryResult clearLastEvaluatedKeyEntries() {
        this.lastEvaluatedKey = null;
        return this;
    }

    public ConsumedCapacity getConsumedCapacity() {
        return this.consumedCapacity;
    }

    public void setConsumedCapacity(ConsumedCapacity consumedCapacity) {
        this.consumedCapacity = consumedCapacity;
    }

    public QueryResult withConsumedCapacity(ConsumedCapacity consumedCapacity) {
        this.consumedCapacity = consumedCapacity;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getItems() != null) {
            sb.append("Items: " + getItems() + ",");
        }
        if (getCount() != null) {
            sb.append("Count: " + getCount() + ",");
        }
        if (getScannedCount() != null) {
            sb.append("ScannedCount: " + getScannedCount() + ",");
        }
        if (getLastEvaluatedKey() != null) {
            sb.append("LastEvaluatedKey: " + getLastEvaluatedKey() + ",");
        }
        if (getConsumedCapacity() != null) {
            sb.append("ConsumedCapacity: " + getConsumedCapacity());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((getItems() == null ? 0 : getItems().hashCode()) + 31) * 31) + (getCount() == null ? 0 : getCount().hashCode())) * 31) + (getScannedCount() == null ? 0 : getScannedCount().hashCode())) * 31) + (getLastEvaluatedKey() == null ? 0 : getLastEvaluatedKey().hashCode())) * 31;
        if (getConsumedCapacity() != null) {
            i = getConsumedCapacity().hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof QueryResult)) {
            return false;
        }
        QueryResult other = (QueryResult) obj;
        if (((other.getItems() == null ? 1 : 0) ^ (getItems() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getItems() != null && !other.getItems().equals(getItems())) {
            return false;
        }
        int i;
        if (other.getCount() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getCount() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getCount() != null && !other.getCount().equals(getCount())) {
            return false;
        }
        if (other.getScannedCount() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getScannedCount() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getScannedCount() != null && !other.getScannedCount().equals(getScannedCount())) {
            return false;
        }
        if (other.getLastEvaluatedKey() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getLastEvaluatedKey() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getLastEvaluatedKey() != null && !other.getLastEvaluatedKey().equals(getLastEvaluatedKey())) {
            return false;
        }
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

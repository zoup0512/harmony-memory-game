package com.amazonaws.services.dynamodbv2.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ConsumedCapacity implements Serializable {
    private Double capacityUnits;
    private Map<String, Capacity> globalSecondaryIndexes;
    private Map<String, Capacity> localSecondaryIndexes;
    private Capacity table;
    private String tableName;

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public ConsumedCapacity withTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public Double getCapacityUnits() {
        return this.capacityUnits;
    }

    public void setCapacityUnits(Double capacityUnits) {
        this.capacityUnits = capacityUnits;
    }

    public ConsumedCapacity withCapacityUnits(Double capacityUnits) {
        this.capacityUnits = capacityUnits;
        return this;
    }

    public Capacity getTable() {
        return this.table;
    }

    public void setTable(Capacity table) {
        this.table = table;
    }

    public ConsumedCapacity withTable(Capacity table) {
        this.table = table;
        return this;
    }

    public Map<String, Capacity> getLocalSecondaryIndexes() {
        return this.localSecondaryIndexes;
    }

    public void setLocalSecondaryIndexes(Map<String, Capacity> localSecondaryIndexes) {
        this.localSecondaryIndexes = localSecondaryIndexes;
    }

    public ConsumedCapacity withLocalSecondaryIndexes(Map<String, Capacity> localSecondaryIndexes) {
        this.localSecondaryIndexes = localSecondaryIndexes;
        return this;
    }

    public ConsumedCapacity addLocalSecondaryIndexesEntry(String key, Capacity value) {
        if (this.localSecondaryIndexes == null) {
            this.localSecondaryIndexes = new HashMap();
        }
        if (this.localSecondaryIndexes.containsKey(key)) {
            throw new IllegalArgumentException("Duplicated keys (" + key.toString() + ") are provided.");
        }
        this.localSecondaryIndexes.put(key, value);
        return this;
    }

    public ConsumedCapacity clearLocalSecondaryIndexesEntries() {
        this.localSecondaryIndexes = null;
        return this;
    }

    public Map<String, Capacity> getGlobalSecondaryIndexes() {
        return this.globalSecondaryIndexes;
    }

    public void setGlobalSecondaryIndexes(Map<String, Capacity> globalSecondaryIndexes) {
        this.globalSecondaryIndexes = globalSecondaryIndexes;
    }

    public ConsumedCapacity withGlobalSecondaryIndexes(Map<String, Capacity> globalSecondaryIndexes) {
        this.globalSecondaryIndexes = globalSecondaryIndexes;
        return this;
    }

    public ConsumedCapacity addGlobalSecondaryIndexesEntry(String key, Capacity value) {
        if (this.globalSecondaryIndexes == null) {
            this.globalSecondaryIndexes = new HashMap();
        }
        if (this.globalSecondaryIndexes.containsKey(key)) {
            throw new IllegalArgumentException("Duplicated keys (" + key.toString() + ") are provided.");
        }
        this.globalSecondaryIndexes.put(key, value);
        return this;
    }

    public ConsumedCapacity clearGlobalSecondaryIndexesEntries() {
        this.globalSecondaryIndexes = null;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTableName() != null) {
            sb.append("TableName: " + getTableName() + ",");
        }
        if (getCapacityUnits() != null) {
            sb.append("CapacityUnits: " + getCapacityUnits() + ",");
        }
        if (getTable() != null) {
            sb.append("Table: " + getTable() + ",");
        }
        if (getLocalSecondaryIndexes() != null) {
            sb.append("LocalSecondaryIndexes: " + getLocalSecondaryIndexes() + ",");
        }
        if (getGlobalSecondaryIndexes() != null) {
            sb.append("GlobalSecondaryIndexes: " + getGlobalSecondaryIndexes());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((getTableName() == null ? 0 : getTableName().hashCode()) + 31) * 31) + (getCapacityUnits() == null ? 0 : getCapacityUnits().hashCode())) * 31) + (getTable() == null ? 0 : getTable().hashCode())) * 31) + (getLocalSecondaryIndexes() == null ? 0 : getLocalSecondaryIndexes().hashCode())) * 31;
        if (getGlobalSecondaryIndexes() != null) {
            i = getGlobalSecondaryIndexes().hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ConsumedCapacity)) {
            return false;
        }
        ConsumedCapacity other = (ConsumedCapacity) obj;
        if (((other.getTableName() == null ? 1 : 0) ^ (getTableName() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getTableName() != null && !other.getTableName().equals(getTableName())) {
            return false;
        }
        int i;
        if (other.getCapacityUnits() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getCapacityUnits() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getCapacityUnits() != null && !other.getCapacityUnits().equals(getCapacityUnits())) {
            return false;
        }
        if (other.getTable() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getTable() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getTable() != null && !other.getTable().equals(getTable())) {
            return false;
        }
        if (other.getLocalSecondaryIndexes() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getLocalSecondaryIndexes() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getLocalSecondaryIndexes() != null && !other.getLocalSecondaryIndexes().equals(getLocalSecondaryIndexes())) {
            return false;
        }
        if (other.getGlobalSecondaryIndexes() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getGlobalSecondaryIndexes() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getGlobalSecondaryIndexes() == null || other.getGlobalSecondaryIndexes().equals(getGlobalSecondaryIndexes())) {
            return true;
        }
        return false;
    }
}

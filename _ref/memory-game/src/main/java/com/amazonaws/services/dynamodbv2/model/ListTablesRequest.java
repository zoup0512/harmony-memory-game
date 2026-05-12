package com.amazonaws.services.dynamodbv2.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

public class ListTablesRequest extends AmazonWebServiceRequest implements Serializable {
    private String exclusiveStartTableName;
    private Integer limit;

    public ListTablesRequest(String exclusiveStartTableName) {
        setExclusiveStartTableName(exclusiveStartTableName);
    }

    public ListTablesRequest(String exclusiveStartTableName, Integer limit) {
        setExclusiveStartTableName(exclusiveStartTableName);
        setLimit(limit);
    }

    public String getExclusiveStartTableName() {
        return this.exclusiveStartTableName;
    }

    public void setExclusiveStartTableName(String exclusiveStartTableName) {
        this.exclusiveStartTableName = exclusiveStartTableName;
    }

    public ListTablesRequest withExclusiveStartTableName(String exclusiveStartTableName) {
        this.exclusiveStartTableName = exclusiveStartTableName;
        return this;
    }

    public Integer getLimit() {
        return this.limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public ListTablesRequest withLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getExclusiveStartTableName() != null) {
            sb.append("ExclusiveStartTableName: " + getExclusiveStartTableName() + ",");
        }
        if (getLimit() != null) {
            sb.append("Limit: " + getLimit());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        int i;
        int i2 = 0;
        if (getExclusiveStartTableName() == null) {
            i = 0;
        } else {
            i = getExclusiveStartTableName().hashCode();
        }
        i = (i + 31) * 31;
        if (getLimit() != null) {
            i2 = getLimit().hashCode();
        }
        return i + i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListTablesRequest)) {
            return false;
        }
        ListTablesRequest other = (ListTablesRequest) obj;
        if (((other.getExclusiveStartTableName() == null ? 1 : 0) ^ (getExclusiveStartTableName() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getExclusiveStartTableName() != null && !other.getExclusiveStartTableName().equals(getExclusiveStartTableName())) {
            return false;
        }
        int i;
        if (other.getLimit() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getLimit() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getLimit() == null || other.getLimit().equals(getLimit())) {
            return true;
        }
        return false;
    }
}

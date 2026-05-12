package com.amazonaws.services.dynamodbv2.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

public class DescribeTableRequest extends AmazonWebServiceRequest implements Serializable {
    private String tableName;

    public DescribeTableRequest(String tableName) {
        setTableName(tableName);
    }

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public DescribeTableRequest withTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTableName() != null) {
            sb.append("TableName: " + getTableName());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (getTableName() == null ? 0 : getTableName().hashCode()) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeTableRequest)) {
            return false;
        }
        DescribeTableRequest other = (DescribeTableRequest) obj;
        if (((other.getTableName() == null ? 1 : 0) ^ (getTableName() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getTableName() == null || other.getTableName().equals(getTableName())) {
            return true;
        }
        return false;
    }
}

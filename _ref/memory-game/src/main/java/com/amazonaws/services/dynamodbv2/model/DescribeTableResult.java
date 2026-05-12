package com.amazonaws.services.dynamodbv2.model;

import java.io.Serializable;

public class DescribeTableResult implements Serializable {
    private TableDescription table;

    public TableDescription getTable() {
        return this.table;
    }

    public void setTable(TableDescription table) {
        this.table = table;
    }

    public DescribeTableResult withTable(TableDescription table) {
        this.table = table;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTable() != null) {
            sb.append("Table: " + getTable());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (getTable() == null ? 0 : getTable().hashCode()) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeTableResult)) {
            return false;
        }
        int i;
        DescribeTableResult other = (DescribeTableResult) obj;
        if (other.getTable() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getTable() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getTable() == null || other.getTable().equals(getTable())) {
            return true;
        }
        return false;
    }
}

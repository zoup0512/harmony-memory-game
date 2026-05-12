package com.amazonaws.services.dynamodbv2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListTablesResult implements Serializable {
    private String lastEvaluatedTableName;
    private List<String> tableNames;

    public List<String> getTableNames() {
        return this.tableNames;
    }

    public void setTableNames(Collection<String> tableNames) {
        if (tableNames == null) {
            this.tableNames = null;
        } else {
            this.tableNames = new ArrayList(tableNames);
        }
    }

    public ListTablesResult withTableNames(String... tableNames) {
        if (getTableNames() == null) {
            this.tableNames = new ArrayList(tableNames.length);
        }
        for (String value : tableNames) {
            this.tableNames.add(value);
        }
        return this;
    }

    public ListTablesResult withTableNames(Collection<String> tableNames) {
        setTableNames(tableNames);
        return this;
    }

    public String getLastEvaluatedTableName() {
        return this.lastEvaluatedTableName;
    }

    public void setLastEvaluatedTableName(String lastEvaluatedTableName) {
        this.lastEvaluatedTableName = lastEvaluatedTableName;
    }

    public ListTablesResult withLastEvaluatedTableName(String lastEvaluatedTableName) {
        this.lastEvaluatedTableName = lastEvaluatedTableName;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTableNames() != null) {
            sb.append("TableNames: " + getTableNames() + ",");
        }
        if (getLastEvaluatedTableName() != null) {
            sb.append("LastEvaluatedTableName: " + getLastEvaluatedTableName());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getTableNames() == null ? 0 : getTableNames().hashCode()) + 31) * 31;
        if (getLastEvaluatedTableName() != null) {
            i = getLastEvaluatedTableName().hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ListTablesResult)) {
            return false;
        }
        ListTablesResult other = (ListTablesResult) obj;
        if (((other.getTableNames() == null ? 1 : 0) ^ (getTableNames() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getTableNames() != null && !other.getTableNames().equals(getTableNames())) {
            return false;
        }
        int i;
        if (other.getLastEvaluatedTableName() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getLastEvaluatedTableName() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getLastEvaluatedTableName() == null || other.getLastEvaluatedTableName().equals(getLastEvaluatedTableName())) {
            return true;
        }
        return false;
    }
}

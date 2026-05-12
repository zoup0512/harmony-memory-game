package com.amazonaws.services.dynamodbv2.model;

import java.io.Serializable;

public class CreateTableResult implements Serializable {
    private TableDescription tableDescription;

    public TableDescription getTableDescription() {
        return this.tableDescription;
    }

    public void setTableDescription(TableDescription tableDescription) {
        this.tableDescription = tableDescription;
    }

    public CreateTableResult withTableDescription(TableDescription tableDescription) {
        this.tableDescription = tableDescription;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTableDescription() != null) {
            sb.append("TableDescription: " + getTableDescription());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (getTableDescription() == null ? 0 : getTableDescription().hashCode()) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateTableResult)) {
            return false;
        }
        CreateTableResult other = (CreateTableResult) obj;
        if (((other.getTableDescription() == null ? 1 : 0) ^ (getTableDescription() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getTableDescription() == null || other.getTableDescription().equals(getTableDescription())) {
            return true;
        }
        return false;
    }
}

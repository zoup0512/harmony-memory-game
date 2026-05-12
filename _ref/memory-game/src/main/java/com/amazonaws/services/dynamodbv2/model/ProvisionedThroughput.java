package com.amazonaws.services.dynamodbv2.model;

import java.io.Serializable;

public class ProvisionedThroughput implements Serializable {
    private Long readCapacityUnits;
    private Long writeCapacityUnits;

    public ProvisionedThroughput(Long readCapacityUnits, Long writeCapacityUnits) {
        setReadCapacityUnits(readCapacityUnits);
        setWriteCapacityUnits(writeCapacityUnits);
    }

    public Long getReadCapacityUnits() {
        return this.readCapacityUnits;
    }

    public void setReadCapacityUnits(Long readCapacityUnits) {
        this.readCapacityUnits = readCapacityUnits;
    }

    public ProvisionedThroughput withReadCapacityUnits(Long readCapacityUnits) {
        this.readCapacityUnits = readCapacityUnits;
        return this;
    }

    public Long getWriteCapacityUnits() {
        return this.writeCapacityUnits;
    }

    public void setWriteCapacityUnits(Long writeCapacityUnits) {
        this.writeCapacityUnits = writeCapacityUnits;
    }

    public ProvisionedThroughput withWriteCapacityUnits(Long writeCapacityUnits) {
        this.writeCapacityUnits = writeCapacityUnits;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getReadCapacityUnits() != null) {
            sb.append("ReadCapacityUnits: " + getReadCapacityUnits() + ",");
        }
        if (getWriteCapacityUnits() != null) {
            sb.append("WriteCapacityUnits: " + getWriteCapacityUnits());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getReadCapacityUnits() == null ? 0 : getReadCapacityUnits().hashCode()) + 31) * 31;
        if (getWriteCapacityUnits() != null) {
            i = getWriteCapacityUnits().hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ProvisionedThroughput)) {
            return false;
        }
        ProvisionedThroughput other = (ProvisionedThroughput) obj;
        if (((other.getReadCapacityUnits() == null ? 1 : 0) ^ (getReadCapacityUnits() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getReadCapacityUnits() != null && !other.getReadCapacityUnits().equals(getReadCapacityUnits())) {
            return false;
        }
        int i;
        if (other.getWriteCapacityUnits() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getWriteCapacityUnits() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getWriteCapacityUnits() == null || other.getWriteCapacityUnits().equals(getWriteCapacityUnits())) {
            return true;
        }
        return false;
    }
}

package com.amazonaws.services.dynamodbv2.model;

import java.io.Serializable;
import java.util.Date;

public class ProvisionedThroughputDescription implements Serializable {
    private Date lastDecreaseDateTime;
    private Date lastIncreaseDateTime;
    private Long numberOfDecreasesToday;
    private Long readCapacityUnits;
    private Long writeCapacityUnits;

    public Date getLastIncreaseDateTime() {
        return this.lastIncreaseDateTime;
    }

    public void setLastIncreaseDateTime(Date lastIncreaseDateTime) {
        this.lastIncreaseDateTime = lastIncreaseDateTime;
    }

    public ProvisionedThroughputDescription withLastIncreaseDateTime(Date lastIncreaseDateTime) {
        this.lastIncreaseDateTime = lastIncreaseDateTime;
        return this;
    }

    public Date getLastDecreaseDateTime() {
        return this.lastDecreaseDateTime;
    }

    public void setLastDecreaseDateTime(Date lastDecreaseDateTime) {
        this.lastDecreaseDateTime = lastDecreaseDateTime;
    }

    public ProvisionedThroughputDescription withLastDecreaseDateTime(Date lastDecreaseDateTime) {
        this.lastDecreaseDateTime = lastDecreaseDateTime;
        return this;
    }

    public Long getNumberOfDecreasesToday() {
        return this.numberOfDecreasesToday;
    }

    public void setNumberOfDecreasesToday(Long numberOfDecreasesToday) {
        this.numberOfDecreasesToday = numberOfDecreasesToday;
    }

    public ProvisionedThroughputDescription withNumberOfDecreasesToday(Long numberOfDecreasesToday) {
        this.numberOfDecreasesToday = numberOfDecreasesToday;
        return this;
    }

    public Long getReadCapacityUnits() {
        return this.readCapacityUnits;
    }

    public void setReadCapacityUnits(Long readCapacityUnits) {
        this.readCapacityUnits = readCapacityUnits;
    }

    public ProvisionedThroughputDescription withReadCapacityUnits(Long readCapacityUnits) {
        this.readCapacityUnits = readCapacityUnits;
        return this;
    }

    public Long getWriteCapacityUnits() {
        return this.writeCapacityUnits;
    }

    public void setWriteCapacityUnits(Long writeCapacityUnits) {
        this.writeCapacityUnits = writeCapacityUnits;
    }

    public ProvisionedThroughputDescription withWriteCapacityUnits(Long writeCapacityUnits) {
        this.writeCapacityUnits = writeCapacityUnits;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getLastIncreaseDateTime() != null) {
            sb.append("LastIncreaseDateTime: " + getLastIncreaseDateTime() + ",");
        }
        if (getLastDecreaseDateTime() != null) {
            sb.append("LastDecreaseDateTime: " + getLastDecreaseDateTime() + ",");
        }
        if (getNumberOfDecreasesToday() != null) {
            sb.append("NumberOfDecreasesToday: " + getNumberOfDecreasesToday() + ",");
        }
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
        int i;
        int i2 = 0;
        int hashCode = ((((getLastIncreaseDateTime() == null ? 0 : getLastIncreaseDateTime().hashCode()) + 31) * 31) + (getLastDecreaseDateTime() == null ? 0 : getLastDecreaseDateTime().hashCode())) * 31;
        if (getNumberOfDecreasesToday() == null) {
            i = 0;
        } else {
            i = getNumberOfDecreasesToday().hashCode();
        }
        i = (((hashCode + i) * 31) + (getReadCapacityUnits() == null ? 0 : getReadCapacityUnits().hashCode())) * 31;
        if (getWriteCapacityUnits() != null) {
            i2 = getWriteCapacityUnits().hashCode();
        }
        return i + i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ProvisionedThroughputDescription)) {
            return false;
        }
        ProvisionedThroughputDescription other = (ProvisionedThroughputDescription) obj;
        if (((other.getLastIncreaseDateTime() == null ? 1 : 0) ^ (getLastIncreaseDateTime() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getLastIncreaseDateTime() != null && !other.getLastIncreaseDateTime().equals(getLastIncreaseDateTime())) {
            return false;
        }
        int i;
        if (other.getLastDecreaseDateTime() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getLastDecreaseDateTime() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getLastDecreaseDateTime() != null && !other.getLastDecreaseDateTime().equals(getLastDecreaseDateTime())) {
            return false;
        }
        if (other.getNumberOfDecreasesToday() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getNumberOfDecreasesToday() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getNumberOfDecreasesToday() != null && !other.getNumberOfDecreasesToday().equals(getNumberOfDecreasesToday())) {
            return false;
        }
        if (other.getReadCapacityUnits() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getReadCapacityUnits() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getReadCapacityUnits() != null && !other.getReadCapacityUnits().equals(getReadCapacityUnits())) {
            return false;
        }
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

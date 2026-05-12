package com.amazonaws.services.dynamodbv2.model;

import java.io.Serializable;

public class DescribeLimitsResult implements Serializable {
    private Long accountMaxReadCapacityUnits;
    private Long accountMaxWriteCapacityUnits;
    private Long tableMaxReadCapacityUnits;
    private Long tableMaxWriteCapacityUnits;

    public Long getAccountMaxReadCapacityUnits() {
        return this.accountMaxReadCapacityUnits;
    }

    public void setAccountMaxReadCapacityUnits(Long accountMaxReadCapacityUnits) {
        this.accountMaxReadCapacityUnits = accountMaxReadCapacityUnits;
    }

    public DescribeLimitsResult withAccountMaxReadCapacityUnits(Long accountMaxReadCapacityUnits) {
        this.accountMaxReadCapacityUnits = accountMaxReadCapacityUnits;
        return this;
    }

    public Long getAccountMaxWriteCapacityUnits() {
        return this.accountMaxWriteCapacityUnits;
    }

    public void setAccountMaxWriteCapacityUnits(Long accountMaxWriteCapacityUnits) {
        this.accountMaxWriteCapacityUnits = accountMaxWriteCapacityUnits;
    }

    public DescribeLimitsResult withAccountMaxWriteCapacityUnits(Long accountMaxWriteCapacityUnits) {
        this.accountMaxWriteCapacityUnits = accountMaxWriteCapacityUnits;
        return this;
    }

    public Long getTableMaxReadCapacityUnits() {
        return this.tableMaxReadCapacityUnits;
    }

    public void setTableMaxReadCapacityUnits(Long tableMaxReadCapacityUnits) {
        this.tableMaxReadCapacityUnits = tableMaxReadCapacityUnits;
    }

    public DescribeLimitsResult withTableMaxReadCapacityUnits(Long tableMaxReadCapacityUnits) {
        this.tableMaxReadCapacityUnits = tableMaxReadCapacityUnits;
        return this;
    }

    public Long getTableMaxWriteCapacityUnits() {
        return this.tableMaxWriteCapacityUnits;
    }

    public void setTableMaxWriteCapacityUnits(Long tableMaxWriteCapacityUnits) {
        this.tableMaxWriteCapacityUnits = tableMaxWriteCapacityUnits;
    }

    public DescribeLimitsResult withTableMaxWriteCapacityUnits(Long tableMaxWriteCapacityUnits) {
        this.tableMaxWriteCapacityUnits = tableMaxWriteCapacityUnits;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getAccountMaxReadCapacityUnits() != null) {
            sb.append("AccountMaxReadCapacityUnits: " + getAccountMaxReadCapacityUnits() + ",");
        }
        if (getAccountMaxWriteCapacityUnits() != null) {
            sb.append("AccountMaxWriteCapacityUnits: " + getAccountMaxWriteCapacityUnits() + ",");
        }
        if (getTableMaxReadCapacityUnits() != null) {
            sb.append("TableMaxReadCapacityUnits: " + getTableMaxReadCapacityUnits() + ",");
        }
        if (getTableMaxWriteCapacityUnits() != null) {
            sb.append("TableMaxWriteCapacityUnits: " + getTableMaxWriteCapacityUnits());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        int i;
        int i2 = 0;
        if (getAccountMaxReadCapacityUnits() == null) {
            i = 0;
        } else {
            i = getAccountMaxReadCapacityUnits().hashCode();
        }
        int i3 = (i + 31) * 31;
        if (getAccountMaxWriteCapacityUnits() == null) {
            i = 0;
        } else {
            i = getAccountMaxWriteCapacityUnits().hashCode();
        }
        i3 = (i3 + i) * 31;
        if (getTableMaxReadCapacityUnits() == null) {
            i = 0;
        } else {
            i = getTableMaxReadCapacityUnits().hashCode();
        }
        i = (i3 + i) * 31;
        if (getTableMaxWriteCapacityUnits() != null) {
            i2 = getTableMaxWriteCapacityUnits().hashCode();
        }
        return i + i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof DescribeLimitsResult)) {
            return false;
        }
        int i;
        DescribeLimitsResult other = (DescribeLimitsResult) obj;
        int i2 = other.getAccountMaxReadCapacityUnits() == null ? 1 : 0;
        if (getAccountMaxReadCapacityUnits() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i2 ^ i) != 0) {
            return false;
        }
        if (other.getAccountMaxReadCapacityUnits() != null && !other.getAccountMaxReadCapacityUnits().equals(getAccountMaxReadCapacityUnits())) {
            return false;
        }
        if (other.getAccountMaxWriteCapacityUnits() == null) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        if (getAccountMaxWriteCapacityUnits() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i2 ^ i) != 0) {
            return false;
        }
        if (other.getAccountMaxWriteCapacityUnits() != null && !other.getAccountMaxWriteCapacityUnits().equals(getAccountMaxWriteCapacityUnits())) {
            return false;
        }
        if (other.getTableMaxReadCapacityUnits() == null) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        if (getTableMaxReadCapacityUnits() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i2 ^ i) != 0) {
            return false;
        }
        if (other.getTableMaxReadCapacityUnits() != null && !other.getTableMaxReadCapacityUnits().equals(getTableMaxReadCapacityUnits())) {
            return false;
        }
        if (other.getTableMaxWriteCapacityUnits() == null) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        if (getTableMaxWriteCapacityUnits() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i2 ^ i) != 0) {
            return false;
        }
        if (other.getTableMaxWriteCapacityUnits() == null || other.getTableMaxWriteCapacityUnits().equals(getTableMaxWriteCapacityUnits())) {
            return true;
        }
        return false;
    }
}

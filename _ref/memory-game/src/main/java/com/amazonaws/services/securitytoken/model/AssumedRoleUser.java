package com.amazonaws.services.securitytoken.model;

import java.io.Serializable;

public class AssumedRoleUser implements Serializable {
    private String arn;
    private String assumedRoleId;

    public String getAssumedRoleId() {
        return this.assumedRoleId;
    }

    public void setAssumedRoleId(String assumedRoleId) {
        this.assumedRoleId = assumedRoleId;
    }

    public AssumedRoleUser withAssumedRoleId(String assumedRoleId) {
        this.assumedRoleId = assumedRoleId;
        return this;
    }

    public String getArn() {
        return this.arn;
    }

    public void setArn(String arn) {
        this.arn = arn;
    }

    public AssumedRoleUser withArn(String arn) {
        this.arn = arn;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getAssumedRoleId() != null) {
            sb.append("AssumedRoleId: " + getAssumedRoleId() + ",");
        }
        if (getArn() != null) {
            sb.append("Arn: " + getArn());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getAssumedRoleId() == null ? 0 : getAssumedRoleId().hashCode()) + 31) * 31;
        if (getArn() != null) {
            i = getArn().hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AssumedRoleUser)) {
            return false;
        }
        AssumedRoleUser other = (AssumedRoleUser) obj;
        if (((other.getAssumedRoleId() == null ? 1 : 0) ^ (getAssumedRoleId() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getAssumedRoleId() != null && !other.getAssumedRoleId().equals(getAssumedRoleId())) {
            return false;
        }
        int i;
        if (other.getArn() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getArn() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getArn() == null || other.getArn().equals(getArn())) {
            return true;
        }
        return false;
    }
}

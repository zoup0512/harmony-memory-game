package com.amazonaws.services.securitytoken.model;

import java.io.Serializable;

public class AssumeRoleResult implements Serializable {
    private AssumedRoleUser assumedRoleUser;
    private Credentials credentials;
    private Integer packedPolicySize;

    public Credentials getCredentials() {
        return this.credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public AssumeRoleResult withCredentials(Credentials credentials) {
        this.credentials = credentials;
        return this;
    }

    public AssumedRoleUser getAssumedRoleUser() {
        return this.assumedRoleUser;
    }

    public void setAssumedRoleUser(AssumedRoleUser assumedRoleUser) {
        this.assumedRoleUser = assumedRoleUser;
    }

    public AssumeRoleResult withAssumedRoleUser(AssumedRoleUser assumedRoleUser) {
        this.assumedRoleUser = assumedRoleUser;
        return this;
    }

    public Integer getPackedPolicySize() {
        return this.packedPolicySize;
    }

    public void setPackedPolicySize(Integer packedPolicySize) {
        this.packedPolicySize = packedPolicySize;
    }

    public AssumeRoleResult withPackedPolicySize(Integer packedPolicySize) {
        this.packedPolicySize = packedPolicySize;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getCredentials() != null) {
            sb.append("Credentials: " + getCredentials() + ",");
        }
        if (getAssumedRoleUser() != null) {
            sb.append("AssumedRoleUser: " + getAssumedRoleUser() + ",");
        }
        if (getPackedPolicySize() != null) {
            sb.append("PackedPolicySize: " + getPackedPolicySize());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getCredentials() == null ? 0 : getCredentials().hashCode()) + 31) * 31) + (getAssumedRoleUser() == null ? 0 : getAssumedRoleUser().hashCode())) * 31;
        if (getPackedPolicySize() != null) {
            i = getPackedPolicySize().hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AssumeRoleResult)) {
            return false;
        }
        AssumeRoleResult other = (AssumeRoleResult) obj;
        if (((other.getCredentials() == null ? 1 : 0) ^ (getCredentials() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getCredentials() != null && !other.getCredentials().equals(getCredentials())) {
            return false;
        }
        int i;
        if (other.getAssumedRoleUser() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getAssumedRoleUser() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getAssumedRoleUser() != null && !other.getAssumedRoleUser().equals(getAssumedRoleUser())) {
            return false;
        }
        if (other.getPackedPolicySize() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getPackedPolicySize() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getPackedPolicySize() == null || other.getPackedPolicySize().equals(getPackedPolicySize())) {
            return true;
        }
        return false;
    }
}

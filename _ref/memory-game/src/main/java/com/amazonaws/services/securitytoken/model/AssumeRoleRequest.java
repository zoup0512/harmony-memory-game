package com.amazonaws.services.securitytoken.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

public class AssumeRoleRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer durationSeconds;
    private String externalId;
    private String policy;
    private String roleArn;
    private String roleSessionName;
    private String serialNumber;
    private String tokenCode;

    public String getRoleArn() {
        return this.roleArn;
    }

    public void setRoleArn(String roleArn) {
        this.roleArn = roleArn;
    }

    public AssumeRoleRequest withRoleArn(String roleArn) {
        this.roleArn = roleArn;
        return this;
    }

    public String getRoleSessionName() {
        return this.roleSessionName;
    }

    public void setRoleSessionName(String roleSessionName) {
        this.roleSessionName = roleSessionName;
    }

    public AssumeRoleRequest withRoleSessionName(String roleSessionName) {
        this.roleSessionName = roleSessionName;
        return this;
    }

    public String getPolicy() {
        return this.policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public AssumeRoleRequest withPolicy(String policy) {
        this.policy = policy;
        return this;
    }

    public Integer getDurationSeconds() {
        return this.durationSeconds;
    }

    public void setDurationSeconds(Integer durationSeconds) {
        this.durationSeconds = durationSeconds;
    }

    public AssumeRoleRequest withDurationSeconds(Integer durationSeconds) {
        this.durationSeconds = durationSeconds;
        return this;
    }

    public String getExternalId() {
        return this.externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public AssumeRoleRequest withExternalId(String externalId) {
        this.externalId = externalId;
        return this;
    }

    public String getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public AssumeRoleRequest withSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    public String getTokenCode() {
        return this.tokenCode;
    }

    public void setTokenCode(String tokenCode) {
        this.tokenCode = tokenCode;
    }

    public AssumeRoleRequest withTokenCode(String tokenCode) {
        this.tokenCode = tokenCode;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getRoleArn() != null) {
            sb.append("RoleArn: " + getRoleArn() + ",");
        }
        if (getRoleSessionName() != null) {
            sb.append("RoleSessionName: " + getRoleSessionName() + ",");
        }
        if (getPolicy() != null) {
            sb.append("Policy: " + getPolicy() + ",");
        }
        if (getDurationSeconds() != null) {
            sb.append("DurationSeconds: " + getDurationSeconds() + ",");
        }
        if (getExternalId() != null) {
            sb.append("ExternalId: " + getExternalId() + ",");
        }
        if (getSerialNumber() != null) {
            sb.append("SerialNumber: " + getSerialNumber() + ",");
        }
        if (getTokenCode() != null) {
            sb.append("TokenCode: " + getTokenCode());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((((getRoleArn() == null ? 0 : getRoleArn().hashCode()) + 31) * 31) + (getRoleSessionName() == null ? 0 : getRoleSessionName().hashCode())) * 31) + (getPolicy() == null ? 0 : getPolicy().hashCode())) * 31) + (getDurationSeconds() == null ? 0 : getDurationSeconds().hashCode())) * 31) + (getExternalId() == null ? 0 : getExternalId().hashCode())) * 31) + (getSerialNumber() == null ? 0 : getSerialNumber().hashCode())) * 31;
        if (getTokenCode() != null) {
            i = getTokenCode().hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AssumeRoleRequest)) {
            return false;
        }
        AssumeRoleRequest other = (AssumeRoleRequest) obj;
        if (((other.getRoleArn() == null ? 1 : 0) ^ (getRoleArn() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getRoleArn() != null && !other.getRoleArn().equals(getRoleArn())) {
            return false;
        }
        int i;
        if (other.getRoleSessionName() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getRoleSessionName() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getRoleSessionName() != null && !other.getRoleSessionName().equals(getRoleSessionName())) {
            return false;
        }
        if (other.getPolicy() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getPolicy() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getPolicy() != null && !other.getPolicy().equals(getPolicy())) {
            return false;
        }
        if (other.getDurationSeconds() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getDurationSeconds() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getDurationSeconds() != null && !other.getDurationSeconds().equals(getDurationSeconds())) {
            return false;
        }
        if (other.getExternalId() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getExternalId() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getExternalId() != null && !other.getExternalId().equals(getExternalId())) {
            return false;
        }
        if (other.getSerialNumber() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getSerialNumber() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getSerialNumber() != null && !other.getSerialNumber().equals(getSerialNumber())) {
            return false;
        }
        if (other.getTokenCode() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getTokenCode() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getTokenCode() == null || other.getTokenCode().equals(getTokenCode())) {
            return true;
        }
        return false;
    }
}

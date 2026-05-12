package com.amazonaws.services.securitytoken.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

public class GetSessionTokenRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer durationSeconds;
    private String serialNumber;
    private String tokenCode;

    public Integer getDurationSeconds() {
        return this.durationSeconds;
    }

    public void setDurationSeconds(Integer durationSeconds) {
        this.durationSeconds = durationSeconds;
    }

    public GetSessionTokenRequest withDurationSeconds(Integer durationSeconds) {
        this.durationSeconds = durationSeconds;
        return this;
    }

    public String getSerialNumber() {
        return this.serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public GetSessionTokenRequest withSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    public String getTokenCode() {
        return this.tokenCode;
    }

    public void setTokenCode(String tokenCode) {
        this.tokenCode = tokenCode;
    }

    public GetSessionTokenRequest withTokenCode(String tokenCode) {
        this.tokenCode = tokenCode;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getDurationSeconds() != null) {
            sb.append("DurationSeconds: " + getDurationSeconds() + ",");
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
        int hashCode = ((((getDurationSeconds() == null ? 0 : getDurationSeconds().hashCode()) + 31) * 31) + (getSerialNumber() == null ? 0 : getSerialNumber().hashCode())) * 31;
        if (getTokenCode() != null) {
            i = getTokenCode().hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetSessionTokenRequest)) {
            return false;
        }
        GetSessionTokenRequest other = (GetSessionTokenRequest) obj;
        if (((other.getDurationSeconds() == null ? 1 : 0) ^ (getDurationSeconds() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getDurationSeconds() != null && !other.getDurationSeconds().equals(getDurationSeconds())) {
            return false;
        }
        int i;
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

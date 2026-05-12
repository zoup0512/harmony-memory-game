package com.amazonaws.services.securitytoken.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

public class GetFederationTokenRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer durationSeconds;
    private String name;
    private String policy;

    public GetFederationTokenRequest(String name) {
        setName(name);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GetFederationTokenRequest withName(String name) {
        this.name = name;
        return this;
    }

    public String getPolicy() {
        return this.policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public GetFederationTokenRequest withPolicy(String policy) {
        this.policy = policy;
        return this;
    }

    public Integer getDurationSeconds() {
        return this.durationSeconds;
    }

    public void setDurationSeconds(Integer durationSeconds) {
        this.durationSeconds = durationSeconds;
    }

    public GetFederationTokenRequest withDurationSeconds(Integer durationSeconds) {
        this.durationSeconds = durationSeconds;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getName() != null) {
            sb.append("Name: " + getName() + ",");
        }
        if (getPolicy() != null) {
            sb.append("Policy: " + getPolicy() + ",");
        }
        if (getDurationSeconds() != null) {
            sb.append("DurationSeconds: " + getDurationSeconds());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getName() == null ? 0 : getName().hashCode()) + 31) * 31) + (getPolicy() == null ? 0 : getPolicy().hashCode())) * 31;
        if (getDurationSeconds() != null) {
            i = getDurationSeconds().hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetFederationTokenRequest)) {
            return false;
        }
        GetFederationTokenRequest other = (GetFederationTokenRequest) obj;
        if (((other.getName() == null ? 1 : 0) ^ (getName() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getName() != null && !other.getName().equals(getName())) {
            return false;
        }
        int i;
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
        if (other.getDurationSeconds() == null || other.getDurationSeconds().equals(getDurationSeconds())) {
            return true;
        }
        return false;
    }
}

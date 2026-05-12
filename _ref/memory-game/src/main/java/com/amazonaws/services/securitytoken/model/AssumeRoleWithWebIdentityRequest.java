package com.amazonaws.services.securitytoken.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

public class AssumeRoleWithWebIdentityRequest extends AmazonWebServiceRequest implements Serializable {
    private Integer durationSeconds;
    private String policy;
    private String providerId;
    private String roleArn;
    private String roleSessionName;
    private String webIdentityToken;

    public String getRoleArn() {
        return this.roleArn;
    }

    public void setRoleArn(String roleArn) {
        this.roleArn = roleArn;
    }

    public AssumeRoleWithWebIdentityRequest withRoleArn(String roleArn) {
        this.roleArn = roleArn;
        return this;
    }

    public String getRoleSessionName() {
        return this.roleSessionName;
    }

    public void setRoleSessionName(String roleSessionName) {
        this.roleSessionName = roleSessionName;
    }

    public AssumeRoleWithWebIdentityRequest withRoleSessionName(String roleSessionName) {
        this.roleSessionName = roleSessionName;
        return this;
    }

    public String getWebIdentityToken() {
        return this.webIdentityToken;
    }

    public void setWebIdentityToken(String webIdentityToken) {
        this.webIdentityToken = webIdentityToken;
    }

    public AssumeRoleWithWebIdentityRequest withWebIdentityToken(String webIdentityToken) {
        this.webIdentityToken = webIdentityToken;
        return this;
    }

    public String getProviderId() {
        return this.providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public AssumeRoleWithWebIdentityRequest withProviderId(String providerId) {
        this.providerId = providerId;
        return this;
    }

    public String getPolicy() {
        return this.policy;
    }

    public void setPolicy(String policy) {
        this.policy = policy;
    }

    public AssumeRoleWithWebIdentityRequest withPolicy(String policy) {
        this.policy = policy;
        return this;
    }

    public Integer getDurationSeconds() {
        return this.durationSeconds;
    }

    public void setDurationSeconds(Integer durationSeconds) {
        this.durationSeconds = durationSeconds;
    }

    public AssumeRoleWithWebIdentityRequest withDurationSeconds(Integer durationSeconds) {
        this.durationSeconds = durationSeconds;
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
        if (getWebIdentityToken() != null) {
            sb.append("WebIdentityToken: " + getWebIdentityToken() + ",");
        }
        if (getProviderId() != null) {
            sb.append("ProviderId: " + getProviderId() + ",");
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
        int hashCode = ((((((((((getRoleArn() == null ? 0 : getRoleArn().hashCode()) + 31) * 31) + (getRoleSessionName() == null ? 0 : getRoleSessionName().hashCode())) * 31) + (getWebIdentityToken() == null ? 0 : getWebIdentityToken().hashCode())) * 31) + (getProviderId() == null ? 0 : getProviderId().hashCode())) * 31) + (getPolicy() == null ? 0 : getPolicy().hashCode())) * 31;
        if (getDurationSeconds() != null) {
            i = getDurationSeconds().hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AssumeRoleWithWebIdentityRequest)) {
            return false;
        }
        AssumeRoleWithWebIdentityRequest other = (AssumeRoleWithWebIdentityRequest) obj;
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
        if (other.getWebIdentityToken() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getWebIdentityToken() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getWebIdentityToken() != null && !other.getWebIdentityToken().equals(getWebIdentityToken())) {
            return false;
        }
        if (other.getProviderId() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getProviderId() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getProviderId() != null && !other.getProviderId().equals(getProviderId())) {
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
        if (other.getDurationSeconds() == null || other.getDurationSeconds().equals(getDurationSeconds())) {
            return true;
        }
        return false;
    }
}

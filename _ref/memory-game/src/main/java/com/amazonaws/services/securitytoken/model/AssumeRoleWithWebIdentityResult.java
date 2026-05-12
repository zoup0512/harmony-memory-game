package com.amazonaws.services.securitytoken.model;

import java.io.Serializable;

public class AssumeRoleWithWebIdentityResult implements Serializable {
    private AssumedRoleUser assumedRoleUser;
    private String audience;
    private Credentials credentials;
    private Integer packedPolicySize;
    private String provider;
    private String subjectFromWebIdentityToken;

    public Credentials getCredentials() {
        return this.credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public AssumeRoleWithWebIdentityResult withCredentials(Credentials credentials) {
        this.credentials = credentials;
        return this;
    }

    public String getSubjectFromWebIdentityToken() {
        return this.subjectFromWebIdentityToken;
    }

    public void setSubjectFromWebIdentityToken(String subjectFromWebIdentityToken) {
        this.subjectFromWebIdentityToken = subjectFromWebIdentityToken;
    }

    public AssumeRoleWithWebIdentityResult withSubjectFromWebIdentityToken(String subjectFromWebIdentityToken) {
        this.subjectFromWebIdentityToken = subjectFromWebIdentityToken;
        return this;
    }

    public AssumedRoleUser getAssumedRoleUser() {
        return this.assumedRoleUser;
    }

    public void setAssumedRoleUser(AssumedRoleUser assumedRoleUser) {
        this.assumedRoleUser = assumedRoleUser;
    }

    public AssumeRoleWithWebIdentityResult withAssumedRoleUser(AssumedRoleUser assumedRoleUser) {
        this.assumedRoleUser = assumedRoleUser;
        return this;
    }

    public Integer getPackedPolicySize() {
        return this.packedPolicySize;
    }

    public void setPackedPolicySize(Integer packedPolicySize) {
        this.packedPolicySize = packedPolicySize;
    }

    public AssumeRoleWithWebIdentityResult withPackedPolicySize(Integer packedPolicySize) {
        this.packedPolicySize = packedPolicySize;
        return this;
    }

    public String getProvider() {
        return this.provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public AssumeRoleWithWebIdentityResult withProvider(String provider) {
        this.provider = provider;
        return this;
    }

    public String getAudience() {
        return this.audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }

    public AssumeRoleWithWebIdentityResult withAudience(String audience) {
        this.audience = audience;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getCredentials() != null) {
            sb.append("Credentials: " + getCredentials() + ",");
        }
        if (getSubjectFromWebIdentityToken() != null) {
            sb.append("SubjectFromWebIdentityToken: " + getSubjectFromWebIdentityToken() + ",");
        }
        if (getAssumedRoleUser() != null) {
            sb.append("AssumedRoleUser: " + getAssumedRoleUser() + ",");
        }
        if (getPackedPolicySize() != null) {
            sb.append("PackedPolicySize: " + getPackedPolicySize() + ",");
        }
        if (getProvider() != null) {
            sb.append("Provider: " + getProvider() + ",");
        }
        if (getAudience() != null) {
            sb.append("Audience: " + getAudience());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        int i;
        int i2 = 0;
        int hashCode = ((getCredentials() == null ? 0 : getCredentials().hashCode()) + 31) * 31;
        if (getSubjectFromWebIdentityToken() == null) {
            i = 0;
        } else {
            i = getSubjectFromWebIdentityToken().hashCode();
        }
        i = (((((((hashCode + i) * 31) + (getAssumedRoleUser() == null ? 0 : getAssumedRoleUser().hashCode())) * 31) + (getPackedPolicySize() == null ? 0 : getPackedPolicySize().hashCode())) * 31) + (getProvider() == null ? 0 : getProvider().hashCode())) * 31;
        if (getAudience() != null) {
            i2 = getAudience().hashCode();
        }
        return i + i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof AssumeRoleWithWebIdentityResult)) {
            return false;
        }
        AssumeRoleWithWebIdentityResult other = (AssumeRoleWithWebIdentityResult) obj;
        if (((other.getCredentials() == null ? 1 : 0) ^ (getCredentials() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getCredentials() != null && !other.getCredentials().equals(getCredentials())) {
            return false;
        }
        int i;
        int i2;
        if (other.getSubjectFromWebIdentityToken() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if (getSubjectFromWebIdentityToken() == null) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        if ((i ^ i2) != 0) {
            return false;
        }
        if (other.getSubjectFromWebIdentityToken() != null && !other.getSubjectFromWebIdentityToken().equals(getSubjectFromWebIdentityToken())) {
            return false;
        }
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
        if (other.getPackedPolicySize() != null && !other.getPackedPolicySize().equals(getPackedPolicySize())) {
            return false;
        }
        if (other.getProvider() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getProvider() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getProvider() != null && !other.getProvider().equals(getProvider())) {
            return false;
        }
        if (other.getAudience() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getAudience() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getAudience() == null || other.getAudience().equals(getAudience())) {
            return true;
        }
        return false;
    }
}

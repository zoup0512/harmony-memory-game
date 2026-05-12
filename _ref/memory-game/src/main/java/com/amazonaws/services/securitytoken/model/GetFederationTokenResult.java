package com.amazonaws.services.securitytoken.model;

import java.io.Serializable;

public class GetFederationTokenResult implements Serializable {
    private Credentials credentials;
    private FederatedUser federatedUser;
    private Integer packedPolicySize;

    public Credentials getCredentials() {
        return this.credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public GetFederationTokenResult withCredentials(Credentials credentials) {
        this.credentials = credentials;
        return this;
    }

    public FederatedUser getFederatedUser() {
        return this.federatedUser;
    }

    public void setFederatedUser(FederatedUser federatedUser) {
        this.federatedUser = federatedUser;
    }

    public GetFederationTokenResult withFederatedUser(FederatedUser federatedUser) {
        this.federatedUser = federatedUser;
        return this;
    }

    public Integer getPackedPolicySize() {
        return this.packedPolicySize;
    }

    public void setPackedPolicySize(Integer packedPolicySize) {
        this.packedPolicySize = packedPolicySize;
    }

    public GetFederationTokenResult withPackedPolicySize(Integer packedPolicySize) {
        this.packedPolicySize = packedPolicySize;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getCredentials() != null) {
            sb.append("Credentials: " + getCredentials() + ",");
        }
        if (getFederatedUser() != null) {
            sb.append("FederatedUser: " + getFederatedUser() + ",");
        }
        if (getPackedPolicySize() != null) {
            sb.append("PackedPolicySize: " + getPackedPolicySize());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getCredentials() == null ? 0 : getCredentials().hashCode()) + 31) * 31) + (getFederatedUser() == null ? 0 : getFederatedUser().hashCode())) * 31;
        if (getPackedPolicySize() != null) {
            i = getPackedPolicySize().hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetFederationTokenResult)) {
            return false;
        }
        GetFederationTokenResult other = (GetFederationTokenResult) obj;
        if (((other.getCredentials() == null ? 1 : 0) ^ (getCredentials() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getCredentials() != null && !other.getCredentials().equals(getCredentials())) {
            return false;
        }
        int i;
        if (other.getFederatedUser() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getFederatedUser() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getFederatedUser() != null && !other.getFederatedUser().equals(getFederatedUser())) {
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

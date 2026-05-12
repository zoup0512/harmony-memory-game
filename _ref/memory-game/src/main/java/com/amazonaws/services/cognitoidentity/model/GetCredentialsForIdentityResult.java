package com.amazonaws.services.cognitoidentity.model;

import java.io.Serializable;

public class GetCredentialsForIdentityResult implements Serializable {
    private Credentials credentials;
    private String identityId;

    public String getIdentityId() {
        return this.identityId;
    }

    public void setIdentityId(String identityId) {
        this.identityId = identityId;
    }

    public GetCredentialsForIdentityResult withIdentityId(String identityId) {
        this.identityId = identityId;
        return this;
    }

    public Credentials getCredentials() {
        return this.credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public GetCredentialsForIdentityResult withCredentials(Credentials credentials) {
        this.credentials = credentials;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getIdentityId() != null) {
            sb.append("IdentityId: " + getIdentityId() + ",");
        }
        if (getCredentials() != null) {
            sb.append("Credentials: " + getCredentials());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getIdentityId() == null ? 0 : getIdentityId().hashCode()) + 31) * 31;
        if (getCredentials() != null) {
            i = getCredentials().hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetCredentialsForIdentityResult)) {
            return false;
        }
        GetCredentialsForIdentityResult other = (GetCredentialsForIdentityResult) obj;
        if (((other.getIdentityId() == null ? 1 : 0) ^ (getIdentityId() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getIdentityId() != null && !other.getIdentityId().equals(getIdentityId())) {
            return false;
        }
        int i;
        if (other.getCredentials() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getCredentials() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getCredentials() == null || other.getCredentials().equals(getCredentials())) {
            return true;
        }
        return false;
    }
}

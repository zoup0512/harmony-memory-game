package com.amazonaws.services.cognitoidentity.model;

import java.io.Serializable;

public class GetOpenIdTokenResult implements Serializable {
    private String identityId;
    private String token;

    public String getIdentityId() {
        return this.identityId;
    }

    public void setIdentityId(String identityId) {
        this.identityId = identityId;
    }

    public GetOpenIdTokenResult withIdentityId(String identityId) {
        this.identityId = identityId;
        return this;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public GetOpenIdTokenResult withToken(String token) {
        this.token = token;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getIdentityId() != null) {
            sb.append("IdentityId: " + getIdentityId() + ",");
        }
        if (getToken() != null) {
            sb.append("Token: " + getToken());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getIdentityId() == null ? 0 : getIdentityId().hashCode()) + 31) * 31;
        if (getToken() != null) {
            i = getToken().hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetOpenIdTokenResult)) {
            return false;
        }
        GetOpenIdTokenResult other = (GetOpenIdTokenResult) obj;
        if (((other.getIdentityId() == null ? 1 : 0) ^ (getIdentityId() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getIdentityId() != null && !other.getIdentityId().equals(getIdentityId())) {
            return false;
        }
        int i;
        if (other.getToken() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getToken() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getToken() == null || other.getToken().equals(getToken())) {
            return true;
        }
        return false;
    }
}

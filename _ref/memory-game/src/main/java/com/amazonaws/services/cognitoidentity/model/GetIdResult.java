package com.amazonaws.services.cognitoidentity.model;

import java.io.Serializable;

public class GetIdResult implements Serializable {
    private String identityId;

    public String getIdentityId() {
        return this.identityId;
    }

    public void setIdentityId(String identityId) {
        this.identityId = identityId;
    }

    public GetIdResult withIdentityId(String identityId) {
        this.identityId = identityId;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getIdentityId() != null) {
            sb.append("IdentityId: " + getIdentityId());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (getIdentityId() == null ? 0 : getIdentityId().hashCode()) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetIdResult)) {
            return false;
        }
        GetIdResult other = (GetIdResult) obj;
        if (((other.getIdentityId() == null ? 1 : 0) ^ (getIdentityId() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getIdentityId() == null || other.getIdentityId().equals(getIdentityId())) {
            return true;
        }
        return false;
    }
}

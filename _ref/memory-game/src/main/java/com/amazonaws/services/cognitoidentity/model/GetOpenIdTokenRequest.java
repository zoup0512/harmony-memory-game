package com.amazonaws.services.cognitoidentity.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class GetOpenIdTokenRequest extends AmazonWebServiceRequest implements Serializable {
    private String identityId;
    private Map<String, String> logins;

    public String getIdentityId() {
        return this.identityId;
    }

    public void setIdentityId(String identityId) {
        this.identityId = identityId;
    }

    public GetOpenIdTokenRequest withIdentityId(String identityId) {
        this.identityId = identityId;
        return this;
    }

    public Map<String, String> getLogins() {
        return this.logins;
    }

    public void setLogins(Map<String, String> logins) {
        this.logins = logins;
    }

    public GetOpenIdTokenRequest withLogins(Map<String, String> logins) {
        this.logins = logins;
        return this;
    }

    public GetOpenIdTokenRequest addLoginsEntry(String key, String value) {
        if (this.logins == null) {
            this.logins = new HashMap();
        }
        if (this.logins.containsKey(key)) {
            throw new IllegalArgumentException("Duplicated keys (" + key.toString() + ") are provided.");
        }
        this.logins.put(key, value);
        return this;
    }

    public GetOpenIdTokenRequest clearLoginsEntries() {
        this.logins = null;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getIdentityId() != null) {
            sb.append("IdentityId: " + getIdentityId() + ",");
        }
        if (getLogins() != null) {
            sb.append("Logins: " + getLogins());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getIdentityId() == null ? 0 : getIdentityId().hashCode()) + 31) * 31;
        if (getLogins() != null) {
            i = getLogins().hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetOpenIdTokenRequest)) {
            return false;
        }
        GetOpenIdTokenRequest other = (GetOpenIdTokenRequest) obj;
        if (((other.getIdentityId() == null ? 1 : 0) ^ (getIdentityId() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getIdentityId() != null && !other.getIdentityId().equals(getIdentityId())) {
            return false;
        }
        int i;
        if (other.getLogins() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getLogins() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getLogins() == null || other.getLogins().equals(getLogins())) {
            return true;
        }
        return false;
    }
}

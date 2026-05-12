package com.amazonaws.services.cognitoidentity.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class GetIdRequest extends AmazonWebServiceRequest implements Serializable {
    private String accountId;
    private String identityPoolId;
    private Map<String, String> logins;

    public String getAccountId() {
        return this.accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public GetIdRequest withAccountId(String accountId) {
        this.accountId = accountId;
        return this;
    }

    public String getIdentityPoolId() {
        return this.identityPoolId;
    }

    public void setIdentityPoolId(String identityPoolId) {
        this.identityPoolId = identityPoolId;
    }

    public GetIdRequest withIdentityPoolId(String identityPoolId) {
        this.identityPoolId = identityPoolId;
        return this;
    }

    public Map<String, String> getLogins() {
        return this.logins;
    }

    public void setLogins(Map<String, String> logins) {
        this.logins = logins;
    }

    public GetIdRequest withLogins(Map<String, String> logins) {
        this.logins = logins;
        return this;
    }

    public GetIdRequest addLoginsEntry(String key, String value) {
        if (this.logins == null) {
            this.logins = new HashMap();
        }
        if (this.logins.containsKey(key)) {
            throw new IllegalArgumentException("Duplicated keys (" + key.toString() + ") are provided.");
        }
        this.logins.put(key, value);
        return this;
    }

    public GetIdRequest clearLoginsEntries() {
        this.logins = null;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getAccountId() != null) {
            sb.append("AccountId: " + getAccountId() + ",");
        }
        if (getIdentityPoolId() != null) {
            sb.append("IdentityPoolId: " + getIdentityPoolId() + ",");
        }
        if (getLogins() != null) {
            sb.append("Logins: " + getLogins());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getAccountId() == null ? 0 : getAccountId().hashCode()) + 31) * 31) + (getIdentityPoolId() == null ? 0 : getIdentityPoolId().hashCode())) * 31;
        if (getLogins() != null) {
            i = getLogins().hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetIdRequest)) {
            return false;
        }
        GetIdRequest other = (GetIdRequest) obj;
        if (((other.getAccountId() == null ? 1 : 0) ^ (getAccountId() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getAccountId() != null && !other.getAccountId().equals(getAccountId())) {
            return false;
        }
        int i;
        if (other.getIdentityPoolId() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getIdentityPoolId() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getIdentityPoolId() != null && !other.getIdentityPoolId().equals(getIdentityPoolId())) {
            return false;
        }
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

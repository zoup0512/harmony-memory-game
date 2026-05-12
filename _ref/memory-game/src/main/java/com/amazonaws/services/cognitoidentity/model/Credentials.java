package com.amazonaws.services.cognitoidentity.model;

import java.io.Serializable;
import java.util.Date;

public class Credentials implements Serializable {
    private String accessKeyId;
    private Date expiration;
    private String secretKey;
    private String sessionToken;

    public String getAccessKeyId() {
        return this.accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public Credentials withAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
        return this;
    }

    public String getSecretKey() {
        return this.secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Credentials withSecretKey(String secretKey) {
        this.secretKey = secretKey;
        return this;
    }

    public String getSessionToken() {
        return this.sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }

    public Credentials withSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
        return this;
    }

    public Date getExpiration() {
        return this.expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public Credentials withExpiration(Date expiration) {
        this.expiration = expiration;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getAccessKeyId() != null) {
            sb.append("AccessKeyId: " + getAccessKeyId() + ",");
        }
        if (getSecretKey() != null) {
            sb.append("SecretKey: " + getSecretKey() + ",");
        }
        if (getSessionToken() != null) {
            sb.append("SessionToken: " + getSessionToken() + ",");
        }
        if (getExpiration() != null) {
            sb.append("Expiration: " + getExpiration());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((getAccessKeyId() == null ? 0 : getAccessKeyId().hashCode()) + 31) * 31) + (getSecretKey() == null ? 0 : getSecretKey().hashCode())) * 31) + (getSessionToken() == null ? 0 : getSessionToken().hashCode())) * 31;
        if (getExpiration() != null) {
            i = getExpiration().hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Credentials)) {
            return false;
        }
        Credentials other = (Credentials) obj;
        if (((other.getAccessKeyId() == null ? 1 : 0) ^ (getAccessKeyId() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getAccessKeyId() != null && !other.getAccessKeyId().equals(getAccessKeyId())) {
            return false;
        }
        int i;
        if (other.getSecretKey() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getSecretKey() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getSecretKey() != null && !other.getSecretKey().equals(getSecretKey())) {
            return false;
        }
        if (other.getSessionToken() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getSessionToken() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getSessionToken() != null && !other.getSessionToken().equals(getSessionToken())) {
            return false;
        }
        if (other.getExpiration() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getExpiration() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getExpiration() == null || other.getExpiration().equals(getExpiration())) {
            return true;
        }
        return false;
    }
}

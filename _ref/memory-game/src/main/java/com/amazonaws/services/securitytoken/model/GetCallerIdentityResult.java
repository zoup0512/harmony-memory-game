package com.amazonaws.services.securitytoken.model;

import java.io.Serializable;

public class GetCallerIdentityResult implements Serializable {
    private String account;
    private String arn;
    private String userId;

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public GetCallerIdentityResult withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getAccount() {
        return this.account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public GetCallerIdentityResult withAccount(String account) {
        this.account = account;
        return this;
    }

    public String getArn() {
        return this.arn;
    }

    public void setArn(String arn) {
        this.arn = arn;
    }

    public GetCallerIdentityResult withArn(String arn) {
        this.arn = arn;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getUserId() != null) {
            sb.append("UserId: " + getUserId() + ",");
        }
        if (getAccount() != null) {
            sb.append("Account: " + getAccount() + ",");
        }
        if (getArn() != null) {
            sb.append("Arn: " + getArn());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getUserId() == null ? 0 : getUserId().hashCode()) + 31) * 31) + (getAccount() == null ? 0 : getAccount().hashCode())) * 31;
        if (getArn() != null) {
            i = getArn().hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetCallerIdentityResult)) {
            return false;
        }
        GetCallerIdentityResult other = (GetCallerIdentityResult) obj;
        if (((other.getUserId() == null ? 1 : 0) ^ (getUserId() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getUserId() != null && !other.getUserId().equals(getUserId())) {
            return false;
        }
        int i;
        if (other.getAccount() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getAccount() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getAccount() != null && !other.getAccount().equals(getAccount())) {
            return false;
        }
        if (other.getArn() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getArn() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getArn() == null || other.getArn().equals(getArn())) {
            return true;
        }
        return false;
    }
}

package com.amazonaws.services.securitytoken.model;

import java.io.Serializable;

public class FederatedUser implements Serializable {
    private String arn;
    private String federatedUserId;

    public FederatedUser(String federatedUserId, String arn) {
        setFederatedUserId(federatedUserId);
        setArn(arn);
    }

    public String getFederatedUserId() {
        return this.federatedUserId;
    }

    public void setFederatedUserId(String federatedUserId) {
        this.federatedUserId = federatedUserId;
    }

    public FederatedUser withFederatedUserId(String federatedUserId) {
        this.federatedUserId = federatedUserId;
        return this;
    }

    public String getArn() {
        return this.arn;
    }

    public void setArn(String arn) {
        this.arn = arn;
    }

    public FederatedUser withArn(String arn) {
        this.arn = arn;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getFederatedUserId() != null) {
            sb.append("FederatedUserId: " + getFederatedUserId() + ",");
        }
        if (getArn() != null) {
            sb.append("Arn: " + getArn());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getFederatedUserId() == null ? 0 : getFederatedUserId().hashCode()) + 31) * 31;
        if (getArn() != null) {
            i = getArn().hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof FederatedUser)) {
            return false;
        }
        FederatedUser other = (FederatedUser) obj;
        if (((other.getFederatedUserId() == null ? 1 : 0) ^ (getFederatedUserId() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getFederatedUserId() != null && !other.getFederatedUserId().equals(getFederatedUserId())) {
            return false;
        }
        int i;
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

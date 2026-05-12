package com.amazonaws.services.dynamodbv2.model;

import java.io.Serializable;

public class WriteRequest implements Serializable {
    private DeleteRequest deleteRequest;
    private PutRequest putRequest;

    public WriteRequest(PutRequest putRequest) {
        setPutRequest(putRequest);
    }

    public WriteRequest(DeleteRequest deleteRequest) {
        setDeleteRequest(deleteRequest);
    }

    public PutRequest getPutRequest() {
        return this.putRequest;
    }

    public void setPutRequest(PutRequest putRequest) {
        this.putRequest = putRequest;
    }

    public WriteRequest withPutRequest(PutRequest putRequest) {
        this.putRequest = putRequest;
        return this;
    }

    public DeleteRequest getDeleteRequest() {
        return this.deleteRequest;
    }

    public void setDeleteRequest(DeleteRequest deleteRequest) {
        this.deleteRequest = deleteRequest;
    }

    public WriteRequest withDeleteRequest(DeleteRequest deleteRequest) {
        this.deleteRequest = deleteRequest;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getPutRequest() != null) {
            sb.append("PutRequest: " + getPutRequest() + ",");
        }
        if (getDeleteRequest() != null) {
            sb.append("DeleteRequest: " + getDeleteRequest());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getPutRequest() == null ? 0 : getPutRequest().hashCode()) + 31) * 31;
        if (getDeleteRequest() != null) {
            i = getDeleteRequest().hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof WriteRequest)) {
            return false;
        }
        WriteRequest other = (WriteRequest) obj;
        if (((other.getPutRequest() == null ? 1 : 0) ^ (getPutRequest() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getPutRequest() != null && !other.getPutRequest().equals(getPutRequest())) {
            return false;
        }
        int i;
        if (other.getDeleteRequest() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getDeleteRequest() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getDeleteRequest() == null || other.getDeleteRequest().equals(getDeleteRequest())) {
            return true;
        }
        return false;
    }
}

package com.amazonaws.services.dynamodbv2.model;

import java.io.Serializable;

public class StreamSpecification implements Serializable {
    private Boolean streamEnabled;
    private String streamViewType;

    public Boolean isStreamEnabled() {
        return this.streamEnabled;
    }

    public Boolean getStreamEnabled() {
        return this.streamEnabled;
    }

    public void setStreamEnabled(Boolean streamEnabled) {
        this.streamEnabled = streamEnabled;
    }

    public StreamSpecification withStreamEnabled(Boolean streamEnabled) {
        this.streamEnabled = streamEnabled;
        return this;
    }

    public String getStreamViewType() {
        return this.streamViewType;
    }

    public void setStreamViewType(String streamViewType) {
        this.streamViewType = streamViewType;
    }

    public StreamSpecification withStreamViewType(String streamViewType) {
        this.streamViewType = streamViewType;
        return this;
    }

    public void setStreamViewType(StreamViewType streamViewType) {
        this.streamViewType = streamViewType.toString();
    }

    public StreamSpecification withStreamViewType(StreamViewType streamViewType) {
        this.streamViewType = streamViewType.toString();
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getStreamEnabled() != null) {
            sb.append("StreamEnabled: " + getStreamEnabled() + ",");
        }
        if (getStreamViewType() != null) {
            sb.append("StreamViewType: " + getStreamViewType());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getStreamEnabled() == null ? 0 : getStreamEnabled().hashCode()) + 31) * 31;
        if (getStreamViewType() != null) {
            i = getStreamViewType().hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof StreamSpecification)) {
            return false;
        }
        StreamSpecification other = (StreamSpecification) obj;
        if (((other.getStreamEnabled() == null ? 1 : 0) ^ (getStreamEnabled() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getStreamEnabled() != null && !other.getStreamEnabled().equals(getStreamEnabled())) {
            return false;
        }
        int i;
        if (other.getStreamViewType() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getStreamViewType() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getStreamViewType() == null || other.getStreamViewType().equals(getStreamViewType())) {
            return true;
        }
        return false;
    }
}

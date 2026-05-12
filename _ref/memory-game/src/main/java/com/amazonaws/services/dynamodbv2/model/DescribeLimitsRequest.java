package com.amazonaws.services.dynamodbv2.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;

public class DescribeLimitsRequest extends AmazonWebServiceRequest implements Serializable {
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return 1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof DescribeLimitsRequest)) {
            return false;
        }
        DescribeLimitsRequest other = (DescribeLimitsRequest) obj;
        return true;
    }
}

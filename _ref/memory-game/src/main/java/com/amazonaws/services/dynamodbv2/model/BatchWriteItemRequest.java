package com.amazonaws.services.dynamodbv2.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BatchWriteItemRequest extends AmazonWebServiceRequest implements Serializable {
    private Map<String, List<WriteRequest>> requestItems;
    private String returnConsumedCapacity;
    private String returnItemCollectionMetrics;

    public BatchWriteItemRequest(Map<String, List<WriteRequest>> requestItems) {
        setRequestItems(requestItems);
    }

    public Map<String, List<WriteRequest>> getRequestItems() {
        return this.requestItems;
    }

    public void setRequestItems(Map<String, List<WriteRequest>> requestItems) {
        this.requestItems = requestItems;
    }

    public BatchWriteItemRequest withRequestItems(Map<String, List<WriteRequest>> requestItems) {
        this.requestItems = requestItems;
        return this;
    }

    public BatchWriteItemRequest addRequestItemsEntry(String key, List<WriteRequest> value) {
        if (this.requestItems == null) {
            this.requestItems = new HashMap();
        }
        if (this.requestItems.containsKey(key)) {
            throw new IllegalArgumentException("Duplicated keys (" + key.toString() + ") are provided.");
        }
        this.requestItems.put(key, value);
        return this;
    }

    public BatchWriteItemRequest clearRequestItemsEntries() {
        this.requestItems = null;
        return this;
    }

    public String getReturnConsumedCapacity() {
        return this.returnConsumedCapacity;
    }

    public void setReturnConsumedCapacity(String returnConsumedCapacity) {
        this.returnConsumedCapacity = returnConsumedCapacity;
    }

    public BatchWriteItemRequest withReturnConsumedCapacity(String returnConsumedCapacity) {
        this.returnConsumedCapacity = returnConsumedCapacity;
        return this;
    }

    public void setReturnConsumedCapacity(ReturnConsumedCapacity returnConsumedCapacity) {
        this.returnConsumedCapacity = returnConsumedCapacity.toString();
    }

    public BatchWriteItemRequest withReturnConsumedCapacity(ReturnConsumedCapacity returnConsumedCapacity) {
        this.returnConsumedCapacity = returnConsumedCapacity.toString();
        return this;
    }

    public String getReturnItemCollectionMetrics() {
        return this.returnItemCollectionMetrics;
    }

    public void setReturnItemCollectionMetrics(String returnItemCollectionMetrics) {
        this.returnItemCollectionMetrics = returnItemCollectionMetrics;
    }

    public BatchWriteItemRequest withReturnItemCollectionMetrics(String returnItemCollectionMetrics) {
        this.returnItemCollectionMetrics = returnItemCollectionMetrics;
        return this;
    }

    public void setReturnItemCollectionMetrics(ReturnItemCollectionMetrics returnItemCollectionMetrics) {
        this.returnItemCollectionMetrics = returnItemCollectionMetrics.toString();
    }

    public BatchWriteItemRequest withReturnItemCollectionMetrics(ReturnItemCollectionMetrics returnItemCollectionMetrics) {
        this.returnItemCollectionMetrics = returnItemCollectionMetrics.toString();
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getRequestItems() != null) {
            sb.append("RequestItems: " + getRequestItems() + ",");
        }
        if (getReturnConsumedCapacity() != null) {
            sb.append("ReturnConsumedCapacity: " + getReturnConsumedCapacity() + ",");
        }
        if (getReturnItemCollectionMetrics() != null) {
            sb.append("ReturnItemCollectionMetrics: " + getReturnItemCollectionMetrics());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        int i;
        int i2 = 0;
        int hashCode = ((getRequestItems() == null ? 0 : getRequestItems().hashCode()) + 31) * 31;
        if (getReturnConsumedCapacity() == null) {
            i = 0;
        } else {
            i = getReturnConsumedCapacity().hashCode();
        }
        i = (hashCode + i) * 31;
        if (getReturnItemCollectionMetrics() != null) {
            i2 = getReturnItemCollectionMetrics().hashCode();
        }
        return i + i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof BatchWriteItemRequest)) {
            return false;
        }
        BatchWriteItemRequest other = (BatchWriteItemRequest) obj;
        if (((other.getRequestItems() == null ? 1 : 0) ^ (getRequestItems() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getRequestItems() != null && !other.getRequestItems().equals(getRequestItems())) {
            return false;
        }
        int i;
        if (other.getReturnConsumedCapacity() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getReturnConsumedCapacity() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getReturnConsumedCapacity() != null && !other.getReturnConsumedCapacity().equals(getReturnConsumedCapacity())) {
            return false;
        }
        int i2;
        if (other.getReturnItemCollectionMetrics() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if (getReturnItemCollectionMetrics() == null) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        if ((i ^ i2) != 0) {
            return false;
        }
        if (other.getReturnItemCollectionMetrics() == null || other.getReturnItemCollectionMetrics().equals(getReturnItemCollectionMetrics())) {
            return true;
        }
        return false;
    }
}

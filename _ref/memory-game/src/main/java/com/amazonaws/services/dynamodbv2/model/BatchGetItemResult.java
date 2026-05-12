package com.amazonaws.services.dynamodbv2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BatchGetItemResult implements Serializable {
    private List<ConsumedCapacity> consumedCapacity;
    private Map<String, List<Map<String, AttributeValue>>> responses;
    private Map<String, KeysAndAttributes> unprocessedKeys;

    public Map<String, List<Map<String, AttributeValue>>> getResponses() {
        return this.responses;
    }

    public void setResponses(Map<String, List<Map<String, AttributeValue>>> responses) {
        this.responses = responses;
    }

    public BatchGetItemResult withResponses(Map<String, List<Map<String, AttributeValue>>> responses) {
        this.responses = responses;
        return this;
    }

    public BatchGetItemResult addResponsesEntry(String key, List<Map<String, AttributeValue>> value) {
        if (this.responses == null) {
            this.responses = new HashMap();
        }
        if (this.responses.containsKey(key)) {
            throw new IllegalArgumentException("Duplicated keys (" + key.toString() + ") are provided.");
        }
        this.responses.put(key, value);
        return this;
    }

    public BatchGetItemResult clearResponsesEntries() {
        this.responses = null;
        return this;
    }

    public Map<String, KeysAndAttributes> getUnprocessedKeys() {
        return this.unprocessedKeys;
    }

    public void setUnprocessedKeys(Map<String, KeysAndAttributes> unprocessedKeys) {
        this.unprocessedKeys = unprocessedKeys;
    }

    public BatchGetItemResult withUnprocessedKeys(Map<String, KeysAndAttributes> unprocessedKeys) {
        this.unprocessedKeys = unprocessedKeys;
        return this;
    }

    public BatchGetItemResult addUnprocessedKeysEntry(String key, KeysAndAttributes value) {
        if (this.unprocessedKeys == null) {
            this.unprocessedKeys = new HashMap();
        }
        if (this.unprocessedKeys.containsKey(key)) {
            throw new IllegalArgumentException("Duplicated keys (" + key.toString() + ") are provided.");
        }
        this.unprocessedKeys.put(key, value);
        return this;
    }

    public BatchGetItemResult clearUnprocessedKeysEntries() {
        this.unprocessedKeys = null;
        return this;
    }

    public List<ConsumedCapacity> getConsumedCapacity() {
        return this.consumedCapacity;
    }

    public void setConsumedCapacity(Collection<ConsumedCapacity> consumedCapacity) {
        if (consumedCapacity == null) {
            this.consumedCapacity = null;
        } else {
            this.consumedCapacity = new ArrayList(consumedCapacity);
        }
    }

    public BatchGetItemResult withConsumedCapacity(ConsumedCapacity... consumedCapacity) {
        if (getConsumedCapacity() == null) {
            this.consumedCapacity = new ArrayList(consumedCapacity.length);
        }
        for (ConsumedCapacity value : consumedCapacity) {
            this.consumedCapacity.add(value);
        }
        return this;
    }

    public BatchGetItemResult withConsumedCapacity(Collection<ConsumedCapacity> consumedCapacity) {
        setConsumedCapacity(consumedCapacity);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getResponses() != null) {
            sb.append("Responses: " + getResponses() + ",");
        }
        if (getUnprocessedKeys() != null) {
            sb.append("UnprocessedKeys: " + getUnprocessedKeys() + ",");
        }
        if (getConsumedCapacity() != null) {
            sb.append("ConsumedCapacity: " + getConsumedCapacity());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getResponses() == null ? 0 : getResponses().hashCode()) + 31) * 31) + (getUnprocessedKeys() == null ? 0 : getUnprocessedKeys().hashCode())) * 31;
        if (getConsumedCapacity() != null) {
            i = getConsumedCapacity().hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof BatchGetItemResult)) {
            return false;
        }
        BatchGetItemResult other = (BatchGetItemResult) obj;
        if (((other.getResponses() == null ? 1 : 0) ^ (getResponses() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getResponses() != null && !other.getResponses().equals(getResponses())) {
            return false;
        }
        int i;
        if (other.getUnprocessedKeys() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getUnprocessedKeys() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getUnprocessedKeys() != null && !other.getUnprocessedKeys().equals(getUnprocessedKeys())) {
            return false;
        }
        if (other.getConsumedCapacity() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getConsumedCapacity() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getConsumedCapacity() == null || other.getConsumedCapacity().equals(getConsumedCapacity())) {
            return true;
        }
        return false;
    }
}

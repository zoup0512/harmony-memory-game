package com.amazonaws.services.dynamodbv2.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetItemRequest extends AmazonWebServiceRequest implements Serializable {
    private List<String> attributesToGet;
    private Boolean consistentRead;
    private Map<String, String> expressionAttributeNames;
    private Map<String, AttributeValue> key;
    private String projectionExpression;
    private String returnConsumedCapacity;
    private String tableName;

    public GetItemRequest(String tableName, Map<String, AttributeValue> key) {
        setTableName(tableName);
        setKey(key);
    }

    public GetItemRequest(String tableName, Map<String, AttributeValue> key, Boolean consistentRead) {
        setTableName(tableName);
        setKey(key);
        setConsistentRead(consistentRead);
    }

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public GetItemRequest withTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public Map<String, AttributeValue> getKey() {
        return this.key;
    }

    public void setKey(Map<String, AttributeValue> key) {
        this.key = key;
    }

    public GetItemRequest withKey(Map<String, AttributeValue> key) {
        this.key = key;
        return this;
    }

    public GetItemRequest addKeyEntry(String key, AttributeValue value) {
        if (this.key == null) {
            this.key = new HashMap();
        }
        if (this.key.containsKey(key)) {
            throw new IllegalArgumentException("Duplicated keys (" + key.toString() + ") are provided.");
        }
        this.key.put(key, value);
        return this;
    }

    public GetItemRequest clearKeyEntries() {
        this.key = null;
        return this;
    }

    public List<String> getAttributesToGet() {
        return this.attributesToGet;
    }

    public void setAttributesToGet(Collection<String> attributesToGet) {
        if (attributesToGet == null) {
            this.attributesToGet = null;
        } else {
            this.attributesToGet = new ArrayList(attributesToGet);
        }
    }

    public GetItemRequest withAttributesToGet(String... attributesToGet) {
        if (getAttributesToGet() == null) {
            this.attributesToGet = new ArrayList(attributesToGet.length);
        }
        for (String value : attributesToGet) {
            this.attributesToGet.add(value);
        }
        return this;
    }

    public GetItemRequest withAttributesToGet(Collection<String> attributesToGet) {
        setAttributesToGet(attributesToGet);
        return this;
    }

    public Boolean isConsistentRead() {
        return this.consistentRead;
    }

    public Boolean getConsistentRead() {
        return this.consistentRead;
    }

    public void setConsistentRead(Boolean consistentRead) {
        this.consistentRead = consistentRead;
    }

    public GetItemRequest withConsistentRead(Boolean consistentRead) {
        this.consistentRead = consistentRead;
        return this;
    }

    public String getReturnConsumedCapacity() {
        return this.returnConsumedCapacity;
    }

    public void setReturnConsumedCapacity(String returnConsumedCapacity) {
        this.returnConsumedCapacity = returnConsumedCapacity;
    }

    public GetItemRequest withReturnConsumedCapacity(String returnConsumedCapacity) {
        this.returnConsumedCapacity = returnConsumedCapacity;
        return this;
    }

    public void setReturnConsumedCapacity(ReturnConsumedCapacity returnConsumedCapacity) {
        this.returnConsumedCapacity = returnConsumedCapacity.toString();
    }

    public GetItemRequest withReturnConsumedCapacity(ReturnConsumedCapacity returnConsumedCapacity) {
        this.returnConsumedCapacity = returnConsumedCapacity.toString();
        return this;
    }

    public String getProjectionExpression() {
        return this.projectionExpression;
    }

    public void setProjectionExpression(String projectionExpression) {
        this.projectionExpression = projectionExpression;
    }

    public GetItemRequest withProjectionExpression(String projectionExpression) {
        this.projectionExpression = projectionExpression;
        return this;
    }

    public Map<String, String> getExpressionAttributeNames() {
        return this.expressionAttributeNames;
    }

    public void setExpressionAttributeNames(Map<String, String> expressionAttributeNames) {
        this.expressionAttributeNames = expressionAttributeNames;
    }

    public GetItemRequest withExpressionAttributeNames(Map<String, String> expressionAttributeNames) {
        this.expressionAttributeNames = expressionAttributeNames;
        return this;
    }

    public GetItemRequest addExpressionAttributeNamesEntry(String key, String value) {
        if (this.expressionAttributeNames == null) {
            this.expressionAttributeNames = new HashMap();
        }
        if (this.expressionAttributeNames.containsKey(key)) {
            throw new IllegalArgumentException("Duplicated keys (" + key.toString() + ") are provided.");
        }
        this.expressionAttributeNames.put(key, value);
        return this;
    }

    public GetItemRequest clearExpressionAttributeNamesEntries() {
        this.expressionAttributeNames = null;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTableName() != null) {
            sb.append("TableName: " + getTableName() + ",");
        }
        if (getKey() != null) {
            sb.append("Key: " + getKey() + ",");
        }
        if (getAttributesToGet() != null) {
            sb.append("AttributesToGet: " + getAttributesToGet() + ",");
        }
        if (getConsistentRead() != null) {
            sb.append("ConsistentRead: " + getConsistentRead() + ",");
        }
        if (getReturnConsumedCapacity() != null) {
            sb.append("ReturnConsumedCapacity: " + getReturnConsumedCapacity() + ",");
        }
        if (getProjectionExpression() != null) {
            sb.append("ProjectionExpression: " + getProjectionExpression() + ",");
        }
        if (getExpressionAttributeNames() != null) {
            sb.append("ExpressionAttributeNames: " + getExpressionAttributeNames());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        int i;
        int i2 = 0;
        int hashCode = ((((((((getTableName() == null ? 0 : getTableName().hashCode()) + 31) * 31) + (getKey() == null ? 0 : getKey().hashCode())) * 31) + (getAttributesToGet() == null ? 0 : getAttributesToGet().hashCode())) * 31) + (getConsistentRead() == null ? 0 : getConsistentRead().hashCode())) * 31;
        if (getReturnConsumedCapacity() == null) {
            i = 0;
        } else {
            i = getReturnConsumedCapacity().hashCode();
        }
        i = (((hashCode + i) * 31) + (getProjectionExpression() == null ? 0 : getProjectionExpression().hashCode())) * 31;
        if (getExpressionAttributeNames() != null) {
            i2 = getExpressionAttributeNames().hashCode();
        }
        return i + i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GetItemRequest)) {
            return false;
        }
        GetItemRequest other = (GetItemRequest) obj;
        if (((other.getTableName() == null ? 1 : 0) ^ (getTableName() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getTableName() != null && !other.getTableName().equals(getTableName())) {
            return false;
        }
        int i;
        if (other.getKey() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getKey() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getKey() != null && !other.getKey().equals(getKey())) {
            return false;
        }
        if (other.getAttributesToGet() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getAttributesToGet() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getAttributesToGet() != null && !other.getAttributesToGet().equals(getAttributesToGet())) {
            return false;
        }
        if (other.getConsistentRead() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getConsistentRead() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getConsistentRead() != null && !other.getConsistentRead().equals(getConsistentRead())) {
            return false;
        }
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
        if (other.getProjectionExpression() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getProjectionExpression() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getProjectionExpression() != null && !other.getProjectionExpression().equals(getProjectionExpression())) {
            return false;
        }
        int i2;
        if (other.getExpressionAttributeNames() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if (getExpressionAttributeNames() == null) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        if ((i ^ i2) != 0) {
            return false;
        }
        if (other.getExpressionAttributeNames() == null || other.getExpressionAttributeNames().equals(getExpressionAttributeNames())) {
            return true;
        }
        return false;
    }
}

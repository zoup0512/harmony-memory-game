package com.amazonaws.services.dynamodbv2.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class UpdateItemRequest extends AmazonWebServiceRequest implements Serializable {
    private Map<String, AttributeValueUpdate> attributeUpdates;
    private String conditionExpression;
    private String conditionalOperator;
    private Map<String, ExpectedAttributeValue> expected;
    private Map<String, String> expressionAttributeNames;
    private Map<String, AttributeValue> expressionAttributeValues;
    private Map<String, AttributeValue> key;
    private String returnConsumedCapacity;
    private String returnItemCollectionMetrics;
    private String returnValues;
    private String tableName;
    private String updateExpression;

    public UpdateItemRequest(String tableName, Map<String, AttributeValue> key, Map<String, AttributeValueUpdate> attributeUpdates) {
        setTableName(tableName);
        setKey(key);
        setAttributeUpdates(attributeUpdates);
    }

    public UpdateItemRequest(String tableName, Map<String, AttributeValue> key, Map<String, AttributeValueUpdate> attributeUpdates, String returnValues) {
        setTableName(tableName);
        setKey(key);
        setAttributeUpdates(attributeUpdates);
        setReturnValues(returnValues);
    }

    public UpdateItemRequest(String tableName, Map<String, AttributeValue> key, Map<String, AttributeValueUpdate> attributeUpdates, ReturnValue returnValues) {
        setTableName(tableName);
        setKey(key);
        setAttributeUpdates(attributeUpdates);
        setReturnValues(returnValues.toString());
    }

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public UpdateItemRequest withTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public Map<String, AttributeValue> getKey() {
        return this.key;
    }

    public void setKey(Map<String, AttributeValue> key) {
        this.key = key;
    }

    public UpdateItemRequest withKey(Map<String, AttributeValue> key) {
        this.key = key;
        return this;
    }

    public UpdateItemRequest addKeyEntry(String key, AttributeValue value) {
        if (this.key == null) {
            this.key = new HashMap();
        }
        if (this.key.containsKey(key)) {
            throw new IllegalArgumentException("Duplicated keys (" + key.toString() + ") are provided.");
        }
        this.key.put(key, value);
        return this;
    }

    public UpdateItemRequest clearKeyEntries() {
        this.key = null;
        return this;
    }

    public Map<String, AttributeValueUpdate> getAttributeUpdates() {
        return this.attributeUpdates;
    }

    public void setAttributeUpdates(Map<String, AttributeValueUpdate> attributeUpdates) {
        this.attributeUpdates = attributeUpdates;
    }

    public UpdateItemRequest withAttributeUpdates(Map<String, AttributeValueUpdate> attributeUpdates) {
        this.attributeUpdates = attributeUpdates;
        return this;
    }

    public UpdateItemRequest addAttributeUpdatesEntry(String key, AttributeValueUpdate value) {
        if (this.attributeUpdates == null) {
            this.attributeUpdates = new HashMap();
        }
        if (this.attributeUpdates.containsKey(key)) {
            throw new IllegalArgumentException("Duplicated keys (" + key.toString() + ") are provided.");
        }
        this.attributeUpdates.put(key, value);
        return this;
    }

    public UpdateItemRequest clearAttributeUpdatesEntries() {
        this.attributeUpdates = null;
        return this;
    }

    public Map<String, ExpectedAttributeValue> getExpected() {
        return this.expected;
    }

    public void setExpected(Map<String, ExpectedAttributeValue> expected) {
        this.expected = expected;
    }

    public UpdateItemRequest withExpected(Map<String, ExpectedAttributeValue> expected) {
        this.expected = expected;
        return this;
    }

    public UpdateItemRequest addExpectedEntry(String key, ExpectedAttributeValue value) {
        if (this.expected == null) {
            this.expected = new HashMap();
        }
        if (this.expected.containsKey(key)) {
            throw new IllegalArgumentException("Duplicated keys (" + key.toString() + ") are provided.");
        }
        this.expected.put(key, value);
        return this;
    }

    public UpdateItemRequest clearExpectedEntries() {
        this.expected = null;
        return this;
    }

    public String getConditionalOperator() {
        return this.conditionalOperator;
    }

    public void setConditionalOperator(String conditionalOperator) {
        this.conditionalOperator = conditionalOperator;
    }

    public UpdateItemRequest withConditionalOperator(String conditionalOperator) {
        this.conditionalOperator = conditionalOperator;
        return this;
    }

    public void setConditionalOperator(ConditionalOperator conditionalOperator) {
        this.conditionalOperator = conditionalOperator.toString();
    }

    public UpdateItemRequest withConditionalOperator(ConditionalOperator conditionalOperator) {
        this.conditionalOperator = conditionalOperator.toString();
        return this;
    }

    public String getReturnValues() {
        return this.returnValues;
    }

    public void setReturnValues(String returnValues) {
        this.returnValues = returnValues;
    }

    public UpdateItemRequest withReturnValues(String returnValues) {
        this.returnValues = returnValues;
        return this;
    }

    public void setReturnValues(ReturnValue returnValues) {
        this.returnValues = returnValues.toString();
    }

    public UpdateItemRequest withReturnValues(ReturnValue returnValues) {
        this.returnValues = returnValues.toString();
        return this;
    }

    public String getReturnConsumedCapacity() {
        return this.returnConsumedCapacity;
    }

    public void setReturnConsumedCapacity(String returnConsumedCapacity) {
        this.returnConsumedCapacity = returnConsumedCapacity;
    }

    public UpdateItemRequest withReturnConsumedCapacity(String returnConsumedCapacity) {
        this.returnConsumedCapacity = returnConsumedCapacity;
        return this;
    }

    public void setReturnConsumedCapacity(ReturnConsumedCapacity returnConsumedCapacity) {
        this.returnConsumedCapacity = returnConsumedCapacity.toString();
    }

    public UpdateItemRequest withReturnConsumedCapacity(ReturnConsumedCapacity returnConsumedCapacity) {
        this.returnConsumedCapacity = returnConsumedCapacity.toString();
        return this;
    }

    public String getReturnItemCollectionMetrics() {
        return this.returnItemCollectionMetrics;
    }

    public void setReturnItemCollectionMetrics(String returnItemCollectionMetrics) {
        this.returnItemCollectionMetrics = returnItemCollectionMetrics;
    }

    public UpdateItemRequest withReturnItemCollectionMetrics(String returnItemCollectionMetrics) {
        this.returnItemCollectionMetrics = returnItemCollectionMetrics;
        return this;
    }

    public void setReturnItemCollectionMetrics(ReturnItemCollectionMetrics returnItemCollectionMetrics) {
        this.returnItemCollectionMetrics = returnItemCollectionMetrics.toString();
    }

    public UpdateItemRequest withReturnItemCollectionMetrics(ReturnItemCollectionMetrics returnItemCollectionMetrics) {
        this.returnItemCollectionMetrics = returnItemCollectionMetrics.toString();
        return this;
    }

    public String getUpdateExpression() {
        return this.updateExpression;
    }

    public void setUpdateExpression(String updateExpression) {
        this.updateExpression = updateExpression;
    }

    public UpdateItemRequest withUpdateExpression(String updateExpression) {
        this.updateExpression = updateExpression;
        return this;
    }

    public String getConditionExpression() {
        return this.conditionExpression;
    }

    public void setConditionExpression(String conditionExpression) {
        this.conditionExpression = conditionExpression;
    }

    public UpdateItemRequest withConditionExpression(String conditionExpression) {
        this.conditionExpression = conditionExpression;
        return this;
    }

    public Map<String, String> getExpressionAttributeNames() {
        return this.expressionAttributeNames;
    }

    public void setExpressionAttributeNames(Map<String, String> expressionAttributeNames) {
        this.expressionAttributeNames = expressionAttributeNames;
    }

    public UpdateItemRequest withExpressionAttributeNames(Map<String, String> expressionAttributeNames) {
        this.expressionAttributeNames = expressionAttributeNames;
        return this;
    }

    public UpdateItemRequest addExpressionAttributeNamesEntry(String key, String value) {
        if (this.expressionAttributeNames == null) {
            this.expressionAttributeNames = new HashMap();
        }
        if (this.expressionAttributeNames.containsKey(key)) {
            throw new IllegalArgumentException("Duplicated keys (" + key.toString() + ") are provided.");
        }
        this.expressionAttributeNames.put(key, value);
        return this;
    }

    public UpdateItemRequest clearExpressionAttributeNamesEntries() {
        this.expressionAttributeNames = null;
        return this;
    }

    public Map<String, AttributeValue> getExpressionAttributeValues() {
        return this.expressionAttributeValues;
    }

    public void setExpressionAttributeValues(Map<String, AttributeValue> expressionAttributeValues) {
        this.expressionAttributeValues = expressionAttributeValues;
    }

    public UpdateItemRequest withExpressionAttributeValues(Map<String, AttributeValue> expressionAttributeValues) {
        this.expressionAttributeValues = expressionAttributeValues;
        return this;
    }

    public UpdateItemRequest addExpressionAttributeValuesEntry(String key, AttributeValue value) {
        if (this.expressionAttributeValues == null) {
            this.expressionAttributeValues = new HashMap();
        }
        if (this.expressionAttributeValues.containsKey(key)) {
            throw new IllegalArgumentException("Duplicated keys (" + key.toString() + ") are provided.");
        }
        this.expressionAttributeValues.put(key, value);
        return this;
    }

    public UpdateItemRequest clearExpressionAttributeValuesEntries() {
        this.expressionAttributeValues = null;
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
        if (getAttributeUpdates() != null) {
            sb.append("AttributeUpdates: " + getAttributeUpdates() + ",");
        }
        if (getExpected() != null) {
            sb.append("Expected: " + getExpected() + ",");
        }
        if (getConditionalOperator() != null) {
            sb.append("ConditionalOperator: " + getConditionalOperator() + ",");
        }
        if (getReturnValues() != null) {
            sb.append("ReturnValues: " + getReturnValues() + ",");
        }
        if (getReturnConsumedCapacity() != null) {
            sb.append("ReturnConsumedCapacity: " + getReturnConsumedCapacity() + ",");
        }
        if (getReturnItemCollectionMetrics() != null) {
            sb.append("ReturnItemCollectionMetrics: " + getReturnItemCollectionMetrics() + ",");
        }
        if (getUpdateExpression() != null) {
            sb.append("UpdateExpression: " + getUpdateExpression() + ",");
        }
        if (getConditionExpression() != null) {
            sb.append("ConditionExpression: " + getConditionExpression() + ",");
        }
        if (getExpressionAttributeNames() != null) {
            sb.append("ExpressionAttributeNames: " + getExpressionAttributeNames() + ",");
        }
        if (getExpressionAttributeValues() != null) {
            sb.append("ExpressionAttributeValues: " + getExpressionAttributeValues());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        int i;
        int i2 = 0;
        int hashCode = ((((((((((((getTableName() == null ? 0 : getTableName().hashCode()) + 31) * 31) + (getKey() == null ? 0 : getKey().hashCode())) * 31) + (getAttributeUpdates() == null ? 0 : getAttributeUpdates().hashCode())) * 31) + (getExpected() == null ? 0 : getExpected().hashCode())) * 31) + (getConditionalOperator() == null ? 0 : getConditionalOperator().hashCode())) * 31) + (getReturnValues() == null ? 0 : getReturnValues().hashCode())) * 31;
        if (getReturnConsumedCapacity() == null) {
            i = 0;
        } else {
            i = getReturnConsumedCapacity().hashCode();
        }
        hashCode = (hashCode + i) * 31;
        if (getReturnItemCollectionMetrics() == null) {
            i = 0;
        } else {
            i = getReturnItemCollectionMetrics().hashCode();
        }
        hashCode = (((((hashCode + i) * 31) + (getUpdateExpression() == null ? 0 : getUpdateExpression().hashCode())) * 31) + (getConditionExpression() == null ? 0 : getConditionExpression().hashCode())) * 31;
        if (getExpressionAttributeNames() == null) {
            i = 0;
        } else {
            i = getExpressionAttributeNames().hashCode();
        }
        i = (hashCode + i) * 31;
        if (getExpressionAttributeValues() != null) {
            i2 = getExpressionAttributeValues().hashCode();
        }
        return i + i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateItemRequest)) {
            return false;
        }
        UpdateItemRequest other = (UpdateItemRequest) obj;
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
        if (other.getAttributeUpdates() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getAttributeUpdates() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getAttributeUpdates() != null && !other.getAttributeUpdates().equals(getAttributeUpdates())) {
            return false;
        }
        if (other.getExpected() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getExpected() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getExpected() != null && !other.getExpected().equals(getExpected())) {
            return false;
        }
        if (other.getConditionalOperator() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getConditionalOperator() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getConditionalOperator() != null && !other.getConditionalOperator().equals(getConditionalOperator())) {
            return false;
        }
        if (other.getReturnValues() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getReturnValues() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getReturnValues() != null && !other.getReturnValues().equals(getReturnValues())) {
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
        if (other.getReturnItemCollectionMetrics() != null && !other.getReturnItemCollectionMetrics().equals(getReturnItemCollectionMetrics())) {
            return false;
        }
        if (other.getUpdateExpression() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getUpdateExpression() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getUpdateExpression() != null && !other.getUpdateExpression().equals(getUpdateExpression())) {
            return false;
        }
        if (other.getConditionExpression() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getConditionExpression() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getConditionExpression() != null && !other.getConditionExpression().equals(getConditionExpression())) {
            return false;
        }
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
        if (other.getExpressionAttributeNames() != null && !other.getExpressionAttributeNames().equals(getExpressionAttributeNames())) {
            return false;
        }
        if (other.getExpressionAttributeValues() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if (getExpressionAttributeValues() == null) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        if ((i ^ i2) != 0) {
            return false;
        }
        if (other.getExpressionAttributeValues() == null || other.getExpressionAttributeValues().equals(getExpressionAttributeValues())) {
            return true;
        }
        return false;
    }
}

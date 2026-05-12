package com.amazonaws.services.dynamodbv2.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QueryRequest extends AmazonWebServiceRequest implements Serializable {
    private List<String> attributesToGet;
    private String conditionalOperator;
    private Boolean consistentRead;
    private Map<String, AttributeValue> exclusiveStartKey;
    private Map<String, String> expressionAttributeNames;
    private Map<String, AttributeValue> expressionAttributeValues;
    private String filterExpression;
    private String indexName;
    private String keyConditionExpression;
    private Map<String, Condition> keyConditions;
    private Integer limit;
    private String projectionExpression;
    private Map<String, Condition> queryFilter;
    private String returnConsumedCapacity;
    private Boolean scanIndexForward;
    private String select;
    private String tableName;

    public QueryRequest(String tableName) {
        setTableName(tableName);
    }

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public QueryRequest withTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public String getIndexName() {
        return this.indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public QueryRequest withIndexName(String indexName) {
        this.indexName = indexName;
        return this;
    }

    public String getSelect() {
        return this.select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public QueryRequest withSelect(String select) {
        this.select = select;
        return this;
    }

    public void setSelect(Select select) {
        this.select = select.toString();
    }

    public QueryRequest withSelect(Select select) {
        this.select = select.toString();
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

    public QueryRequest withAttributesToGet(String... attributesToGet) {
        if (getAttributesToGet() == null) {
            this.attributesToGet = new ArrayList(attributesToGet.length);
        }
        for (String value : attributesToGet) {
            this.attributesToGet.add(value);
        }
        return this;
    }

    public QueryRequest withAttributesToGet(Collection<String> attributesToGet) {
        setAttributesToGet(attributesToGet);
        return this;
    }

    public Integer getLimit() {
        return this.limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public QueryRequest withLimit(Integer limit) {
        this.limit = limit;
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

    public QueryRequest withConsistentRead(Boolean consistentRead) {
        this.consistentRead = consistentRead;
        return this;
    }

    public Map<String, Condition> getKeyConditions() {
        return this.keyConditions;
    }

    public void setKeyConditions(Map<String, Condition> keyConditions) {
        this.keyConditions = keyConditions;
    }

    public QueryRequest withKeyConditions(Map<String, Condition> keyConditions) {
        this.keyConditions = keyConditions;
        return this;
    }

    public QueryRequest addKeyConditionsEntry(String key, Condition value) {
        if (this.keyConditions == null) {
            this.keyConditions = new HashMap();
        }
        if (this.keyConditions.containsKey(key)) {
            throw new IllegalArgumentException("Duplicated keys (" + key.toString() + ") are provided.");
        }
        this.keyConditions.put(key, value);
        return this;
    }

    public QueryRequest clearKeyConditionsEntries() {
        this.keyConditions = null;
        return this;
    }

    public Map<String, Condition> getQueryFilter() {
        return this.queryFilter;
    }

    public void setQueryFilter(Map<String, Condition> queryFilter) {
        this.queryFilter = queryFilter;
    }

    public QueryRequest withQueryFilter(Map<String, Condition> queryFilter) {
        this.queryFilter = queryFilter;
        return this;
    }

    public QueryRequest addQueryFilterEntry(String key, Condition value) {
        if (this.queryFilter == null) {
            this.queryFilter = new HashMap();
        }
        if (this.queryFilter.containsKey(key)) {
            throw new IllegalArgumentException("Duplicated keys (" + key.toString() + ") are provided.");
        }
        this.queryFilter.put(key, value);
        return this;
    }

    public QueryRequest clearQueryFilterEntries() {
        this.queryFilter = null;
        return this;
    }

    public String getConditionalOperator() {
        return this.conditionalOperator;
    }

    public void setConditionalOperator(String conditionalOperator) {
        this.conditionalOperator = conditionalOperator;
    }

    public QueryRequest withConditionalOperator(String conditionalOperator) {
        this.conditionalOperator = conditionalOperator;
        return this;
    }

    public void setConditionalOperator(ConditionalOperator conditionalOperator) {
        this.conditionalOperator = conditionalOperator.toString();
    }

    public QueryRequest withConditionalOperator(ConditionalOperator conditionalOperator) {
        this.conditionalOperator = conditionalOperator.toString();
        return this;
    }

    public Boolean isScanIndexForward() {
        return this.scanIndexForward;
    }

    public Boolean getScanIndexForward() {
        return this.scanIndexForward;
    }

    public void setScanIndexForward(Boolean scanIndexForward) {
        this.scanIndexForward = scanIndexForward;
    }

    public QueryRequest withScanIndexForward(Boolean scanIndexForward) {
        this.scanIndexForward = scanIndexForward;
        return this;
    }

    public Map<String, AttributeValue> getExclusiveStartKey() {
        return this.exclusiveStartKey;
    }

    public void setExclusiveStartKey(Map<String, AttributeValue> exclusiveStartKey) {
        this.exclusiveStartKey = exclusiveStartKey;
    }

    public QueryRequest withExclusiveStartKey(Map<String, AttributeValue> exclusiveStartKey) {
        this.exclusiveStartKey = exclusiveStartKey;
        return this;
    }

    public QueryRequest addExclusiveStartKeyEntry(String key, AttributeValue value) {
        if (this.exclusiveStartKey == null) {
            this.exclusiveStartKey = new HashMap();
        }
        if (this.exclusiveStartKey.containsKey(key)) {
            throw new IllegalArgumentException("Duplicated keys (" + key.toString() + ") are provided.");
        }
        this.exclusiveStartKey.put(key, value);
        return this;
    }

    public QueryRequest clearExclusiveStartKeyEntries() {
        this.exclusiveStartKey = null;
        return this;
    }

    public String getReturnConsumedCapacity() {
        return this.returnConsumedCapacity;
    }

    public void setReturnConsumedCapacity(String returnConsumedCapacity) {
        this.returnConsumedCapacity = returnConsumedCapacity;
    }

    public QueryRequest withReturnConsumedCapacity(String returnConsumedCapacity) {
        this.returnConsumedCapacity = returnConsumedCapacity;
        return this;
    }

    public void setReturnConsumedCapacity(ReturnConsumedCapacity returnConsumedCapacity) {
        this.returnConsumedCapacity = returnConsumedCapacity.toString();
    }

    public QueryRequest withReturnConsumedCapacity(ReturnConsumedCapacity returnConsumedCapacity) {
        this.returnConsumedCapacity = returnConsumedCapacity.toString();
        return this;
    }

    public String getProjectionExpression() {
        return this.projectionExpression;
    }

    public void setProjectionExpression(String projectionExpression) {
        this.projectionExpression = projectionExpression;
    }

    public QueryRequest withProjectionExpression(String projectionExpression) {
        this.projectionExpression = projectionExpression;
        return this;
    }

    public String getFilterExpression() {
        return this.filterExpression;
    }

    public void setFilterExpression(String filterExpression) {
        this.filterExpression = filterExpression;
    }

    public QueryRequest withFilterExpression(String filterExpression) {
        this.filterExpression = filterExpression;
        return this;
    }

    public String getKeyConditionExpression() {
        return this.keyConditionExpression;
    }

    public void setKeyConditionExpression(String keyConditionExpression) {
        this.keyConditionExpression = keyConditionExpression;
    }

    public QueryRequest withKeyConditionExpression(String keyConditionExpression) {
        this.keyConditionExpression = keyConditionExpression;
        return this;
    }

    public Map<String, String> getExpressionAttributeNames() {
        return this.expressionAttributeNames;
    }

    public void setExpressionAttributeNames(Map<String, String> expressionAttributeNames) {
        this.expressionAttributeNames = expressionAttributeNames;
    }

    public QueryRequest withExpressionAttributeNames(Map<String, String> expressionAttributeNames) {
        this.expressionAttributeNames = expressionAttributeNames;
        return this;
    }

    public QueryRequest addExpressionAttributeNamesEntry(String key, String value) {
        if (this.expressionAttributeNames == null) {
            this.expressionAttributeNames = new HashMap();
        }
        if (this.expressionAttributeNames.containsKey(key)) {
            throw new IllegalArgumentException("Duplicated keys (" + key.toString() + ") are provided.");
        }
        this.expressionAttributeNames.put(key, value);
        return this;
    }

    public QueryRequest clearExpressionAttributeNamesEntries() {
        this.expressionAttributeNames = null;
        return this;
    }

    public Map<String, AttributeValue> getExpressionAttributeValues() {
        return this.expressionAttributeValues;
    }

    public void setExpressionAttributeValues(Map<String, AttributeValue> expressionAttributeValues) {
        this.expressionAttributeValues = expressionAttributeValues;
    }

    public QueryRequest withExpressionAttributeValues(Map<String, AttributeValue> expressionAttributeValues) {
        this.expressionAttributeValues = expressionAttributeValues;
        return this;
    }

    public QueryRequest addExpressionAttributeValuesEntry(String key, AttributeValue value) {
        if (this.expressionAttributeValues == null) {
            this.expressionAttributeValues = new HashMap();
        }
        if (this.expressionAttributeValues.containsKey(key)) {
            throw new IllegalArgumentException("Duplicated keys (" + key.toString() + ") are provided.");
        }
        this.expressionAttributeValues.put(key, value);
        return this;
    }

    public QueryRequest clearExpressionAttributeValuesEntries() {
        this.expressionAttributeValues = null;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getTableName() != null) {
            sb.append("TableName: " + getTableName() + ",");
        }
        if (getIndexName() != null) {
            sb.append("IndexName: " + getIndexName() + ",");
        }
        if (getSelect() != null) {
            sb.append("Select: " + getSelect() + ",");
        }
        if (getAttributesToGet() != null) {
            sb.append("AttributesToGet: " + getAttributesToGet() + ",");
        }
        if (getLimit() != null) {
            sb.append("Limit: " + getLimit() + ",");
        }
        if (getConsistentRead() != null) {
            sb.append("ConsistentRead: " + getConsistentRead() + ",");
        }
        if (getKeyConditions() != null) {
            sb.append("KeyConditions: " + getKeyConditions() + ",");
        }
        if (getQueryFilter() != null) {
            sb.append("QueryFilter: " + getQueryFilter() + ",");
        }
        if (getConditionalOperator() != null) {
            sb.append("ConditionalOperator: " + getConditionalOperator() + ",");
        }
        if (getScanIndexForward() != null) {
            sb.append("ScanIndexForward: " + getScanIndexForward() + ",");
        }
        if (getExclusiveStartKey() != null) {
            sb.append("ExclusiveStartKey: " + getExclusiveStartKey() + ",");
        }
        if (getReturnConsumedCapacity() != null) {
            sb.append("ReturnConsumedCapacity: " + getReturnConsumedCapacity() + ",");
        }
        if (getProjectionExpression() != null) {
            sb.append("ProjectionExpression: " + getProjectionExpression() + ",");
        }
        if (getFilterExpression() != null) {
            sb.append("FilterExpression: " + getFilterExpression() + ",");
        }
        if (getKeyConditionExpression() != null) {
            sb.append("KeyConditionExpression: " + getKeyConditionExpression() + ",");
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
        int hashCode = ((((((((((((((((((((((getTableName() == null ? 0 : getTableName().hashCode()) + 31) * 31) + (getIndexName() == null ? 0 : getIndexName().hashCode())) * 31) + (getSelect() == null ? 0 : getSelect().hashCode())) * 31) + (getAttributesToGet() == null ? 0 : getAttributesToGet().hashCode())) * 31) + (getLimit() == null ? 0 : getLimit().hashCode())) * 31) + (getConsistentRead() == null ? 0 : getConsistentRead().hashCode())) * 31) + (getKeyConditions() == null ? 0 : getKeyConditions().hashCode())) * 31) + (getQueryFilter() == null ? 0 : getQueryFilter().hashCode())) * 31) + (getConditionalOperator() == null ? 0 : getConditionalOperator().hashCode())) * 31) + (getScanIndexForward() == null ? 0 : getScanIndexForward().hashCode())) * 31) + (getExclusiveStartKey() == null ? 0 : getExclusiveStartKey().hashCode())) * 31;
        if (getReturnConsumedCapacity() == null) {
            i = 0;
        } else {
            i = getReturnConsumedCapacity().hashCode();
        }
        hashCode = (((((hashCode + i) * 31) + (getProjectionExpression() == null ? 0 : getProjectionExpression().hashCode())) * 31) + (getFilterExpression() == null ? 0 : getFilterExpression().hashCode())) * 31;
        if (getKeyConditionExpression() == null) {
            i = 0;
        } else {
            i = getKeyConditionExpression().hashCode();
        }
        hashCode = (hashCode + i) * 31;
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
        if (obj == null || !(obj instanceof QueryRequest)) {
            return false;
        }
        QueryRequest other = (QueryRequest) obj;
        if (((other.getTableName() == null ? 1 : 0) ^ (getTableName() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getTableName() != null && !other.getTableName().equals(getTableName())) {
            return false;
        }
        int i;
        if (other.getIndexName() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getIndexName() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getIndexName() != null && !other.getIndexName().equals(getIndexName())) {
            return false;
        }
        if (other.getSelect() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getSelect() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getSelect() != null && !other.getSelect().equals(getSelect())) {
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
        if (other.getLimit() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getLimit() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getLimit() != null && !other.getLimit().equals(getLimit())) {
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
        if (other.getKeyConditions() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getKeyConditions() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getKeyConditions() != null && !other.getKeyConditions().equals(getKeyConditions())) {
            return false;
        }
        if (other.getQueryFilter() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getQueryFilter() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getQueryFilter() != null && !other.getQueryFilter().equals(getQueryFilter())) {
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
        if (other.getScanIndexForward() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getScanIndexForward() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getScanIndexForward() != null && !other.getScanIndexForward().equals(getScanIndexForward())) {
            return false;
        }
        if (other.getExclusiveStartKey() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getExclusiveStartKey() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getExclusiveStartKey() != null && !other.getExclusiveStartKey().equals(getExclusiveStartKey())) {
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
        if (other.getFilterExpression() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getFilterExpression() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getFilterExpression() != null && !other.getFilterExpression().equals(getFilterExpression())) {
            return false;
        }
        if (other.getKeyConditionExpression() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getKeyConditionExpression() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getKeyConditionExpression() != null && !other.getKeyConditionExpression().equals(getKeyConditionExpression())) {
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

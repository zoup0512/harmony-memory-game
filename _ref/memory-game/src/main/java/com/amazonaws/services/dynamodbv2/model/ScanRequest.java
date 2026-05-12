package com.amazonaws.services.dynamodbv2.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScanRequest extends AmazonWebServiceRequest implements Serializable {
    private List<String> attributesToGet;
    private String conditionalOperator;
    private Boolean consistentRead;
    private Map<String, AttributeValue> exclusiveStartKey;
    private Map<String, String> expressionAttributeNames;
    private Map<String, AttributeValue> expressionAttributeValues;
    private String filterExpression;
    private String indexName;
    private Integer limit;
    private String projectionExpression;
    private String returnConsumedCapacity;
    private Map<String, Condition> scanFilter;
    private Integer segment;
    private String select;
    private String tableName;
    private Integer totalSegments;

    public ScanRequest(String tableName) {
        setTableName(tableName);
    }

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public ScanRequest withTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public String getIndexName() {
        return this.indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public ScanRequest withIndexName(String indexName) {
        this.indexName = indexName;
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

    public ScanRequest withAttributesToGet(String... attributesToGet) {
        if (getAttributesToGet() == null) {
            this.attributesToGet = new ArrayList(attributesToGet.length);
        }
        for (String value : attributesToGet) {
            this.attributesToGet.add(value);
        }
        return this;
    }

    public ScanRequest withAttributesToGet(Collection<String> attributesToGet) {
        setAttributesToGet(attributesToGet);
        return this;
    }

    public Integer getLimit() {
        return this.limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public ScanRequest withLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public String getSelect() {
        return this.select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public ScanRequest withSelect(String select) {
        this.select = select;
        return this;
    }

    public void setSelect(Select select) {
        this.select = select.toString();
    }

    public ScanRequest withSelect(Select select) {
        this.select = select.toString();
        return this;
    }

    public Map<String, Condition> getScanFilter() {
        return this.scanFilter;
    }

    public void setScanFilter(Map<String, Condition> scanFilter) {
        this.scanFilter = scanFilter;
    }

    public ScanRequest withScanFilter(Map<String, Condition> scanFilter) {
        this.scanFilter = scanFilter;
        return this;
    }

    public ScanRequest addScanFilterEntry(String key, Condition value) {
        if (this.scanFilter == null) {
            this.scanFilter = new HashMap();
        }
        if (this.scanFilter.containsKey(key)) {
            throw new IllegalArgumentException("Duplicated keys (" + key.toString() + ") are provided.");
        }
        this.scanFilter.put(key, value);
        return this;
    }

    public ScanRequest clearScanFilterEntries() {
        this.scanFilter = null;
        return this;
    }

    public String getConditionalOperator() {
        return this.conditionalOperator;
    }

    public void setConditionalOperator(String conditionalOperator) {
        this.conditionalOperator = conditionalOperator;
    }

    public ScanRequest withConditionalOperator(String conditionalOperator) {
        this.conditionalOperator = conditionalOperator;
        return this;
    }

    public void setConditionalOperator(ConditionalOperator conditionalOperator) {
        this.conditionalOperator = conditionalOperator.toString();
    }

    public ScanRequest withConditionalOperator(ConditionalOperator conditionalOperator) {
        this.conditionalOperator = conditionalOperator.toString();
        return this;
    }

    public Map<String, AttributeValue> getExclusiveStartKey() {
        return this.exclusiveStartKey;
    }

    public void setExclusiveStartKey(Map<String, AttributeValue> exclusiveStartKey) {
        this.exclusiveStartKey = exclusiveStartKey;
    }

    public ScanRequest withExclusiveStartKey(Map<String, AttributeValue> exclusiveStartKey) {
        this.exclusiveStartKey = exclusiveStartKey;
        return this;
    }

    public ScanRequest addExclusiveStartKeyEntry(String key, AttributeValue value) {
        if (this.exclusiveStartKey == null) {
            this.exclusiveStartKey = new HashMap();
        }
        if (this.exclusiveStartKey.containsKey(key)) {
            throw new IllegalArgumentException("Duplicated keys (" + key.toString() + ") are provided.");
        }
        this.exclusiveStartKey.put(key, value);
        return this;
    }

    public ScanRequest clearExclusiveStartKeyEntries() {
        this.exclusiveStartKey = null;
        return this;
    }

    public String getReturnConsumedCapacity() {
        return this.returnConsumedCapacity;
    }

    public void setReturnConsumedCapacity(String returnConsumedCapacity) {
        this.returnConsumedCapacity = returnConsumedCapacity;
    }

    public ScanRequest withReturnConsumedCapacity(String returnConsumedCapacity) {
        this.returnConsumedCapacity = returnConsumedCapacity;
        return this;
    }

    public void setReturnConsumedCapacity(ReturnConsumedCapacity returnConsumedCapacity) {
        this.returnConsumedCapacity = returnConsumedCapacity.toString();
    }

    public ScanRequest withReturnConsumedCapacity(ReturnConsumedCapacity returnConsumedCapacity) {
        this.returnConsumedCapacity = returnConsumedCapacity.toString();
        return this;
    }

    public Integer getTotalSegments() {
        return this.totalSegments;
    }

    public void setTotalSegments(Integer totalSegments) {
        this.totalSegments = totalSegments;
    }

    public ScanRequest withTotalSegments(Integer totalSegments) {
        this.totalSegments = totalSegments;
        return this;
    }

    public Integer getSegment() {
        return this.segment;
    }

    public void setSegment(Integer segment) {
        this.segment = segment;
    }

    public ScanRequest withSegment(Integer segment) {
        this.segment = segment;
        return this;
    }

    public String getProjectionExpression() {
        return this.projectionExpression;
    }

    public void setProjectionExpression(String projectionExpression) {
        this.projectionExpression = projectionExpression;
    }

    public ScanRequest withProjectionExpression(String projectionExpression) {
        this.projectionExpression = projectionExpression;
        return this;
    }

    public String getFilterExpression() {
        return this.filterExpression;
    }

    public void setFilterExpression(String filterExpression) {
        this.filterExpression = filterExpression;
    }

    public ScanRequest withFilterExpression(String filterExpression) {
        this.filterExpression = filterExpression;
        return this;
    }

    public Map<String, String> getExpressionAttributeNames() {
        return this.expressionAttributeNames;
    }

    public void setExpressionAttributeNames(Map<String, String> expressionAttributeNames) {
        this.expressionAttributeNames = expressionAttributeNames;
    }

    public ScanRequest withExpressionAttributeNames(Map<String, String> expressionAttributeNames) {
        this.expressionAttributeNames = expressionAttributeNames;
        return this;
    }

    public ScanRequest addExpressionAttributeNamesEntry(String key, String value) {
        if (this.expressionAttributeNames == null) {
            this.expressionAttributeNames = new HashMap();
        }
        if (this.expressionAttributeNames.containsKey(key)) {
            throw new IllegalArgumentException("Duplicated keys (" + key.toString() + ") are provided.");
        }
        this.expressionAttributeNames.put(key, value);
        return this;
    }

    public ScanRequest clearExpressionAttributeNamesEntries() {
        this.expressionAttributeNames = null;
        return this;
    }

    public Map<String, AttributeValue> getExpressionAttributeValues() {
        return this.expressionAttributeValues;
    }

    public void setExpressionAttributeValues(Map<String, AttributeValue> expressionAttributeValues) {
        this.expressionAttributeValues = expressionAttributeValues;
    }

    public ScanRequest withExpressionAttributeValues(Map<String, AttributeValue> expressionAttributeValues) {
        this.expressionAttributeValues = expressionAttributeValues;
        return this;
    }

    public ScanRequest addExpressionAttributeValuesEntry(String key, AttributeValue value) {
        if (this.expressionAttributeValues == null) {
            this.expressionAttributeValues = new HashMap();
        }
        if (this.expressionAttributeValues.containsKey(key)) {
            throw new IllegalArgumentException("Duplicated keys (" + key.toString() + ") are provided.");
        }
        this.expressionAttributeValues.put(key, value);
        return this;
    }

    public ScanRequest clearExpressionAttributeValuesEntries() {
        this.expressionAttributeValues = null;
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

    public ScanRequest withConsistentRead(Boolean consistentRead) {
        this.consistentRead = consistentRead;
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
        if (getAttributesToGet() != null) {
            sb.append("AttributesToGet: " + getAttributesToGet() + ",");
        }
        if (getLimit() != null) {
            sb.append("Limit: " + getLimit() + ",");
        }
        if (getSelect() != null) {
            sb.append("Select: " + getSelect() + ",");
        }
        if (getScanFilter() != null) {
            sb.append("ScanFilter: " + getScanFilter() + ",");
        }
        if (getConditionalOperator() != null) {
            sb.append("ConditionalOperator: " + getConditionalOperator() + ",");
        }
        if (getExclusiveStartKey() != null) {
            sb.append("ExclusiveStartKey: " + getExclusiveStartKey() + ",");
        }
        if (getReturnConsumedCapacity() != null) {
            sb.append("ReturnConsumedCapacity: " + getReturnConsumedCapacity() + ",");
        }
        if (getTotalSegments() != null) {
            sb.append("TotalSegments: " + getTotalSegments() + ",");
        }
        if (getSegment() != null) {
            sb.append("Segment: " + getSegment() + ",");
        }
        if (getProjectionExpression() != null) {
            sb.append("ProjectionExpression: " + getProjectionExpression() + ",");
        }
        if (getFilterExpression() != null) {
            sb.append("FilterExpression: " + getFilterExpression() + ",");
        }
        if (getExpressionAttributeNames() != null) {
            sb.append("ExpressionAttributeNames: " + getExpressionAttributeNames() + ",");
        }
        if (getExpressionAttributeValues() != null) {
            sb.append("ExpressionAttributeValues: " + getExpressionAttributeValues() + ",");
        }
        if (getConsistentRead() != null) {
            sb.append("ConsistentRead: " + getConsistentRead());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        int i;
        int i2 = 0;
        int hashCode = ((((((((((((((((getTableName() == null ? 0 : getTableName().hashCode()) + 31) * 31) + (getIndexName() == null ? 0 : getIndexName().hashCode())) * 31) + (getAttributesToGet() == null ? 0 : getAttributesToGet().hashCode())) * 31) + (getLimit() == null ? 0 : getLimit().hashCode())) * 31) + (getSelect() == null ? 0 : getSelect().hashCode())) * 31) + (getScanFilter() == null ? 0 : getScanFilter().hashCode())) * 31) + (getConditionalOperator() == null ? 0 : getConditionalOperator().hashCode())) * 31) + (getExclusiveStartKey() == null ? 0 : getExclusiveStartKey().hashCode())) * 31;
        if (getReturnConsumedCapacity() == null) {
            i = 0;
        } else {
            i = getReturnConsumedCapacity().hashCode();
        }
        hashCode = (((((((((hashCode + i) * 31) + (getTotalSegments() == null ? 0 : getTotalSegments().hashCode())) * 31) + (getSegment() == null ? 0 : getSegment().hashCode())) * 31) + (getProjectionExpression() == null ? 0 : getProjectionExpression().hashCode())) * 31) + (getFilterExpression() == null ? 0 : getFilterExpression().hashCode())) * 31;
        if (getExpressionAttributeNames() == null) {
            i = 0;
        } else {
            i = getExpressionAttributeNames().hashCode();
        }
        hashCode = (hashCode + i) * 31;
        if (getExpressionAttributeValues() == null) {
            i = 0;
        } else {
            i = getExpressionAttributeValues().hashCode();
        }
        i = (hashCode + i) * 31;
        if (getConsistentRead() != null) {
            i2 = getConsistentRead().hashCode();
        }
        return i + i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ScanRequest)) {
            return false;
        }
        ScanRequest other = (ScanRequest) obj;
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
        if (other.getScanFilter() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getScanFilter() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getScanFilter() != null && !other.getScanFilter().equals(getScanFilter())) {
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
        if (other.getTotalSegments() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getTotalSegments() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getTotalSegments() != null && !other.getTotalSegments().equals(getTotalSegments())) {
            return false;
        }
        if (other.getSegment() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getSegment() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getSegment() != null && !other.getSegment().equals(getSegment())) {
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
        if (other.getExpressionAttributeValues() != null && !other.getExpressionAttributeValues().equals(getExpressionAttributeValues())) {
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
        if (other.getConsistentRead() == null || other.getConsistentRead().equals(getConsistentRead())) {
            return true;
        }
        return false;
    }
}

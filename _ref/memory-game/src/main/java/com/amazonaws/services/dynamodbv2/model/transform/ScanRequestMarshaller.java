package com.amazonaws.services.dynamodbv2.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.ScanRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ScanRequestMarshaller implements Marshaller<Request<ScanRequest>, ScanRequest> {
    public Request<ScanRequest> marshall(ScanRequest scanRequest) {
        if (scanRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(ScanRequest)");
        }
        Request<ScanRequest> defaultRequest = new DefaultRequest(scanRequest, "AmazonDynamoDB");
        defaultRequest.addHeader("X-Amz-Target", "DynamoDB_20120810.Scan");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/");
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (scanRequest.getTableName() != null) {
                String tableName = scanRequest.getTableName();
                jsonWriter.name("TableName");
                jsonWriter.value(tableName);
            }
            if (scanRequest.getIndexName() != null) {
                String indexName = scanRequest.getIndexName();
                jsonWriter.name("IndexName");
                jsonWriter.value(indexName);
            }
            if (scanRequest.getAttributesToGet() != null) {
                List<String> attributesToGet = scanRequest.getAttributesToGet();
                jsonWriter.name("AttributesToGet");
                jsonWriter.beginArray();
                for (String attributesToGetItem : attributesToGet) {
                    if (attributesToGetItem != null) {
                        jsonWriter.value(attributesToGetItem);
                    }
                }
                jsonWriter.endArray();
            }
            if (scanRequest.getLimit() != null) {
                Number limit = scanRequest.getLimit();
                jsonWriter.name("Limit");
                jsonWriter.value(limit);
            }
            if (scanRequest.getSelect() != null) {
                String select = scanRequest.getSelect();
                jsonWriter.name("Select");
                jsonWriter.value(select);
            }
            if (scanRequest.getScanFilter() != null) {
                Map<String, Condition> scanFilter = scanRequest.getScanFilter();
                jsonWriter.name("ScanFilter");
                jsonWriter.beginObject();
                for (Entry<String, Condition> scanFilterEntry : scanFilter.entrySet()) {
                    Condition scanFilterValue = (Condition) scanFilterEntry.getValue();
                    if (scanFilterValue != null) {
                        jsonWriter.name((String) scanFilterEntry.getKey());
                        ConditionJsonMarshaller.getInstance().marshall(scanFilterValue, jsonWriter);
                    }
                }
                jsonWriter.endObject();
            }
            if (scanRequest.getConditionalOperator() != null) {
                String conditionalOperator = scanRequest.getConditionalOperator();
                jsonWriter.name("ConditionalOperator");
                jsonWriter.value(conditionalOperator);
            }
            if (scanRequest.getExclusiveStartKey() != null) {
                Map<String, AttributeValue> exclusiveStartKey = scanRequest.getExclusiveStartKey();
                jsonWriter.name("ExclusiveStartKey");
                jsonWriter.beginObject();
                for (Entry<String, AttributeValue> exclusiveStartKeyEntry : exclusiveStartKey.entrySet()) {
                    AttributeValue exclusiveStartKeyValue = (AttributeValue) exclusiveStartKeyEntry.getValue();
                    if (exclusiveStartKeyValue != null) {
                        jsonWriter.name((String) exclusiveStartKeyEntry.getKey());
                        AttributeValueJsonMarshaller.getInstance().marshall(exclusiveStartKeyValue, jsonWriter);
                    }
                }
                jsonWriter.endObject();
            }
            if (scanRequest.getReturnConsumedCapacity() != null) {
                String returnConsumedCapacity = scanRequest.getReturnConsumedCapacity();
                jsonWriter.name("ReturnConsumedCapacity");
                jsonWriter.value(returnConsumedCapacity);
            }
            if (scanRequest.getTotalSegments() != null) {
                Number totalSegments = scanRequest.getTotalSegments();
                jsonWriter.name("TotalSegments");
                jsonWriter.value(totalSegments);
            }
            if (scanRequest.getSegment() != null) {
                Number segment = scanRequest.getSegment();
                jsonWriter.name("Segment");
                jsonWriter.value(segment);
            }
            if (scanRequest.getProjectionExpression() != null) {
                String projectionExpression = scanRequest.getProjectionExpression();
                jsonWriter.name("ProjectionExpression");
                jsonWriter.value(projectionExpression);
            }
            if (scanRequest.getFilterExpression() != null) {
                String filterExpression = scanRequest.getFilterExpression();
                jsonWriter.name("FilterExpression");
                jsonWriter.value(filterExpression);
            }
            if (scanRequest.getExpressionAttributeNames() != null) {
                Map<String, String> expressionAttributeNames = scanRequest.getExpressionAttributeNames();
                jsonWriter.name("ExpressionAttributeNames");
                jsonWriter.beginObject();
                for (Entry<String, String> expressionAttributeNamesEntry : expressionAttributeNames.entrySet()) {
                    String expressionAttributeNamesValue = (String) expressionAttributeNamesEntry.getValue();
                    if (expressionAttributeNamesValue != null) {
                        jsonWriter.name((String) expressionAttributeNamesEntry.getKey());
                        jsonWriter.value(expressionAttributeNamesValue);
                    }
                }
                jsonWriter.endObject();
            }
            if (scanRequest.getExpressionAttributeValues() != null) {
                Map<String, AttributeValue> expressionAttributeValues = scanRequest.getExpressionAttributeValues();
                jsonWriter.name("ExpressionAttributeValues");
                jsonWriter.beginObject();
                for (Entry<String, AttributeValue> expressionAttributeValuesEntry : expressionAttributeValues.entrySet()) {
                    AttributeValue expressionAttributeValuesValue = (AttributeValue) expressionAttributeValuesEntry.getValue();
                    if (expressionAttributeValuesValue != null) {
                        jsonWriter.name((String) expressionAttributeValuesEntry.getKey());
                        AttributeValueJsonMarshaller.getInstance().marshall(expressionAttributeValuesValue, jsonWriter);
                    }
                }
                jsonWriter.endObject();
            }
            if (scanRequest.getConsistentRead() != null) {
                Boolean consistentRead = scanRequest.getConsistentRead();
                jsonWriter.name("ConsistentRead");
                jsonWriter.value(consistentRead.booleanValue());
            }
            jsonWriter.endObject();
            jsonWriter.close();
            String snippet = stringWriter.toString();
            byte[] content = snippet.getBytes(StringUtils.UTF8);
            defaultRequest.setContent(new StringInputStream(snippet));
            defaultRequest.addHeader("Content-Length", Integer.toString(content.length));
            if (!defaultRequest.getHeaders().containsKey("Content-Type")) {
                defaultRequest.addHeader("Content-Type", "application/x-amz-json-1.0");
            }
            return defaultRequest;
        } catch (Throwable t) {
            AmazonClientException amazonClientException = new AmazonClientException("Unable to marshall request to JSON: " + t.getMessage(), t);
        }
    }
}

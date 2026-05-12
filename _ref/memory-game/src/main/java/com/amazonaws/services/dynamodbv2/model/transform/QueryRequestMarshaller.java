package com.amazonaws.services.dynamodbv2.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class QueryRequestMarshaller implements Marshaller<Request<QueryRequest>, QueryRequest> {
    public Request<QueryRequest> marshall(QueryRequest queryRequest) {
        if (queryRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(QueryRequest)");
        }
        Request<QueryRequest> defaultRequest = new DefaultRequest(queryRequest, "AmazonDynamoDB");
        defaultRequest.addHeader("X-Amz-Target", "DynamoDB_20120810.Query");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/");
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (queryRequest.getTableName() != null) {
                String tableName = queryRequest.getTableName();
                jsonWriter.name("TableName");
                jsonWriter.value(tableName);
            }
            if (queryRequest.getIndexName() != null) {
                String indexName = queryRequest.getIndexName();
                jsonWriter.name("IndexName");
                jsonWriter.value(indexName);
            }
            if (queryRequest.getSelect() != null) {
                String select = queryRequest.getSelect();
                jsonWriter.name("Select");
                jsonWriter.value(select);
            }
            if (queryRequest.getAttributesToGet() != null) {
                List<String> attributesToGet = queryRequest.getAttributesToGet();
                jsonWriter.name("AttributesToGet");
                jsonWriter.beginArray();
                for (String attributesToGetItem : attributesToGet) {
                    if (attributesToGetItem != null) {
                        jsonWriter.value(attributesToGetItem);
                    }
                }
                jsonWriter.endArray();
            }
            if (queryRequest.getLimit() != null) {
                Number limit = queryRequest.getLimit();
                jsonWriter.name("Limit");
                jsonWriter.value(limit);
            }
            if (queryRequest.getConsistentRead() != null) {
                Boolean consistentRead = queryRequest.getConsistentRead();
                jsonWriter.name("ConsistentRead");
                jsonWriter.value(consistentRead.booleanValue());
            }
            if (queryRequest.getKeyConditions() != null) {
                Map<String, Condition> keyConditions = queryRequest.getKeyConditions();
                jsonWriter.name("KeyConditions");
                jsonWriter.beginObject();
                for (Entry<String, Condition> keyConditionsEntry : keyConditions.entrySet()) {
                    Condition keyConditionsValue = (Condition) keyConditionsEntry.getValue();
                    if (keyConditionsValue != null) {
                        jsonWriter.name((String) keyConditionsEntry.getKey());
                        ConditionJsonMarshaller.getInstance().marshall(keyConditionsValue, jsonWriter);
                    }
                }
                jsonWriter.endObject();
            }
            if (queryRequest.getQueryFilter() != null) {
                Map<String, Condition> queryFilter = queryRequest.getQueryFilter();
                jsonWriter.name("QueryFilter");
                jsonWriter.beginObject();
                for (Entry<String, Condition> queryFilterEntry : queryFilter.entrySet()) {
                    Condition queryFilterValue = (Condition) queryFilterEntry.getValue();
                    if (queryFilterValue != null) {
                        jsonWriter.name((String) queryFilterEntry.getKey());
                        ConditionJsonMarshaller.getInstance().marshall(queryFilterValue, jsonWriter);
                    }
                }
                jsonWriter.endObject();
            }
            if (queryRequest.getConditionalOperator() != null) {
                String conditionalOperator = queryRequest.getConditionalOperator();
                jsonWriter.name("ConditionalOperator");
                jsonWriter.value(conditionalOperator);
            }
            if (queryRequest.getScanIndexForward() != null) {
                Boolean scanIndexForward = queryRequest.getScanIndexForward();
                jsonWriter.name("ScanIndexForward");
                jsonWriter.value(scanIndexForward.booleanValue());
            }
            if (queryRequest.getExclusiveStartKey() != null) {
                Map<String, AttributeValue> exclusiveStartKey = queryRequest.getExclusiveStartKey();
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
            if (queryRequest.getReturnConsumedCapacity() != null) {
                String returnConsumedCapacity = queryRequest.getReturnConsumedCapacity();
                jsonWriter.name("ReturnConsumedCapacity");
                jsonWriter.value(returnConsumedCapacity);
            }
            if (queryRequest.getProjectionExpression() != null) {
                String projectionExpression = queryRequest.getProjectionExpression();
                jsonWriter.name("ProjectionExpression");
                jsonWriter.value(projectionExpression);
            }
            if (queryRequest.getFilterExpression() != null) {
                String filterExpression = queryRequest.getFilterExpression();
                jsonWriter.name("FilterExpression");
                jsonWriter.value(filterExpression);
            }
            if (queryRequest.getKeyConditionExpression() != null) {
                String keyConditionExpression = queryRequest.getKeyConditionExpression();
                jsonWriter.name("KeyConditionExpression");
                jsonWriter.value(keyConditionExpression);
            }
            if (queryRequest.getExpressionAttributeNames() != null) {
                Map<String, String> expressionAttributeNames = queryRequest.getExpressionAttributeNames();
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
            if (queryRequest.getExpressionAttributeValues() != null) {
                Map<String, AttributeValue> expressionAttributeValues = queryRequest.getExpressionAttributeValues();
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

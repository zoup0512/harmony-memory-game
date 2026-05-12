package com.amazonaws.services.dynamodbv2.model.transform;

import com.amazonaws.AmazonClientException;
import com.amazonaws.DefaultRequest;
import com.amazonaws.Request;
import com.amazonaws.http.HttpMethodName;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.AttributeValueUpdate;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.amazonaws.services.dynamodbv2.model.UpdateItemRequest;
import com.amazonaws.transform.Marshaller;
import com.amazonaws.util.StringInputStream;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.AwsJsonWriter;
import com.amazonaws.util.json.JsonUtils;
import java.io.StringWriter;
import java.util.Map;
import java.util.Map.Entry;

public class UpdateItemRequestMarshaller implements Marshaller<Request<UpdateItemRequest>, UpdateItemRequest> {
    public Request<UpdateItemRequest> marshall(UpdateItemRequest updateItemRequest) {
        if (updateItemRequest == null) {
            throw new AmazonClientException("Invalid argument passed to marshall(UpdateItemRequest)");
        }
        Request<UpdateItemRequest> defaultRequest = new DefaultRequest(updateItemRequest, "AmazonDynamoDB");
        defaultRequest.addHeader("X-Amz-Target", "DynamoDB_20120810.UpdateItem");
        defaultRequest.setHttpMethod(HttpMethodName.POST);
        defaultRequest.setResourcePath("/");
        try {
            StringWriter stringWriter = new StringWriter();
            AwsJsonWriter jsonWriter = JsonUtils.getJsonWriter(stringWriter);
            jsonWriter.beginObject();
            if (updateItemRequest.getTableName() != null) {
                String tableName = updateItemRequest.getTableName();
                jsonWriter.name("TableName");
                jsonWriter.value(tableName);
            }
            if (updateItemRequest.getKey() != null) {
                Map<String, AttributeValue> key = updateItemRequest.getKey();
                jsonWriter.name("Key");
                jsonWriter.beginObject();
                for (Entry<String, AttributeValue> keyEntry : key.entrySet()) {
                    AttributeValue keyValue = (AttributeValue) keyEntry.getValue();
                    if (keyValue != null) {
                        jsonWriter.name((String) keyEntry.getKey());
                        AttributeValueJsonMarshaller.getInstance().marshall(keyValue, jsonWriter);
                    }
                }
                jsonWriter.endObject();
            }
            if (updateItemRequest.getAttributeUpdates() != null) {
                Map<String, AttributeValueUpdate> attributeUpdates = updateItemRequest.getAttributeUpdates();
                jsonWriter.name("AttributeUpdates");
                jsonWriter.beginObject();
                for (Entry<String, AttributeValueUpdate> attributeUpdatesEntry : attributeUpdates.entrySet()) {
                    AttributeValueUpdate attributeUpdatesValue = (AttributeValueUpdate) attributeUpdatesEntry.getValue();
                    if (attributeUpdatesValue != null) {
                        jsonWriter.name((String) attributeUpdatesEntry.getKey());
                        AttributeValueUpdateJsonMarshaller.getInstance().marshall(attributeUpdatesValue, jsonWriter);
                    }
                }
                jsonWriter.endObject();
            }
            if (updateItemRequest.getExpected() != null) {
                Map<String, ExpectedAttributeValue> expected = updateItemRequest.getExpected();
                jsonWriter.name("Expected");
                jsonWriter.beginObject();
                for (Entry<String, ExpectedAttributeValue> expectedEntry : expected.entrySet()) {
                    ExpectedAttributeValue expectedValue = (ExpectedAttributeValue) expectedEntry.getValue();
                    if (expectedValue != null) {
                        jsonWriter.name((String) expectedEntry.getKey());
                        ExpectedAttributeValueJsonMarshaller.getInstance().marshall(expectedValue, jsonWriter);
                    }
                }
                jsonWriter.endObject();
            }
            if (updateItemRequest.getConditionalOperator() != null) {
                String conditionalOperator = updateItemRequest.getConditionalOperator();
                jsonWriter.name("ConditionalOperator");
                jsonWriter.value(conditionalOperator);
            }
            if (updateItemRequest.getReturnValues() != null) {
                String returnValues = updateItemRequest.getReturnValues();
                jsonWriter.name("ReturnValues");
                jsonWriter.value(returnValues);
            }
            if (updateItemRequest.getReturnConsumedCapacity() != null) {
                String returnConsumedCapacity = updateItemRequest.getReturnConsumedCapacity();
                jsonWriter.name("ReturnConsumedCapacity");
                jsonWriter.value(returnConsumedCapacity);
            }
            if (updateItemRequest.getReturnItemCollectionMetrics() != null) {
                String returnItemCollectionMetrics = updateItemRequest.getReturnItemCollectionMetrics();
                jsonWriter.name("ReturnItemCollectionMetrics");
                jsonWriter.value(returnItemCollectionMetrics);
            }
            if (updateItemRequest.getUpdateExpression() != null) {
                String updateExpression = updateItemRequest.getUpdateExpression();
                jsonWriter.name("UpdateExpression");
                jsonWriter.value(updateExpression);
            }
            if (updateItemRequest.getConditionExpression() != null) {
                String conditionExpression = updateItemRequest.getConditionExpression();
                jsonWriter.name("ConditionExpression");
                jsonWriter.value(conditionExpression);
            }
            if (updateItemRequest.getExpressionAttributeNames() != null) {
                Map<String, String> expressionAttributeNames = updateItemRequest.getExpressionAttributeNames();
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
            if (updateItemRequest.getExpressionAttributeValues() != null) {
                Map<String, AttributeValue> expressionAttributeValues = updateItemRequest.getExpressionAttributeValues();
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

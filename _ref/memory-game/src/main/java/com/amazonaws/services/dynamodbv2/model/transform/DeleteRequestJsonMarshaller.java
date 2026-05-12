package com.amazonaws.services.dynamodbv2.model.transform;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.DeleteRequest;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Map;
import java.util.Map.Entry;

class DeleteRequestJsonMarshaller {
    private static DeleteRequestJsonMarshaller instance;

    DeleteRequestJsonMarshaller() {
    }

    public void marshall(DeleteRequest deleteRequest, AwsJsonWriter jsonWriter) throws Exception {
        jsonWriter.beginObject();
        if (deleteRequest.getKey() != null) {
            Map<String, AttributeValue> key = deleteRequest.getKey();
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
        jsonWriter.endObject();
    }

    public static DeleteRequestJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new DeleteRequestJsonMarshaller();
        }
        return instance;
    }
}

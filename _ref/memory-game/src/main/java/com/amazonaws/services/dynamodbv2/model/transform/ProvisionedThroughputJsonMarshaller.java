package com.amazonaws.services.dynamodbv2.model.transform;

import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.util.json.AwsJsonWriter;

class ProvisionedThroughputJsonMarshaller {
    private static ProvisionedThroughputJsonMarshaller instance;

    ProvisionedThroughputJsonMarshaller() {
    }

    public void marshall(ProvisionedThroughput provisionedThroughput, AwsJsonWriter jsonWriter) throws Exception {
        jsonWriter.beginObject();
        if (provisionedThroughput.getReadCapacityUnits() != null) {
            Number readCapacityUnits = provisionedThroughput.getReadCapacityUnits();
            jsonWriter.name("ReadCapacityUnits");
            jsonWriter.value(readCapacityUnits);
        }
        if (provisionedThroughput.getWriteCapacityUnits() != null) {
            Number writeCapacityUnits = provisionedThroughput.getWriteCapacityUnits();
            jsonWriter.name("WriteCapacityUnits");
            jsonWriter.value(writeCapacityUnits);
        }
        jsonWriter.endObject();
    }

    public static ProvisionedThroughputJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new ProvisionedThroughputJsonMarshaller();
        }
        return instance;
    }
}

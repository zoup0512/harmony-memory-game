package com.amazonaws.services.dynamodbv2.model.transform;

import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughputDescription;
import com.amazonaws.transform.JsonUnmarshallerContext;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers.DateJsonUnmarshaller;
import com.amazonaws.transform.SimpleTypeJsonUnmarshallers.LongJsonUnmarshaller;
import com.amazonaws.transform.Unmarshaller;
import com.amazonaws.util.json.AwsJsonReader;

class ProvisionedThroughputDescriptionJsonUnmarshaller implements Unmarshaller<ProvisionedThroughputDescription, JsonUnmarshallerContext> {
    private static ProvisionedThroughputDescriptionJsonUnmarshaller instance;

    ProvisionedThroughputDescriptionJsonUnmarshaller() {
    }

    public ProvisionedThroughputDescription unmarshall(JsonUnmarshallerContext context) throws Exception {
        ProvisionedThroughputDescription provisionedThroughputDescription = new ProvisionedThroughputDescription();
        AwsJsonReader reader = context.getReader();
        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equals("LastIncreaseDateTime")) {
                provisionedThroughputDescription.setLastIncreaseDateTime(DateJsonUnmarshaller.getInstance().unmarshall(context));
            } else if (name.equals("LastDecreaseDateTime")) {
                provisionedThroughputDescription.setLastDecreaseDateTime(DateJsonUnmarshaller.getInstance().unmarshall(context));
            } else if (name.equals("NumberOfDecreasesToday")) {
                provisionedThroughputDescription.setNumberOfDecreasesToday(LongJsonUnmarshaller.getInstance().unmarshall(context));
            } else if (name.equals("ReadCapacityUnits")) {
                provisionedThroughputDescription.setReadCapacityUnits(LongJsonUnmarshaller.getInstance().unmarshall(context));
            } else if (name.equals("WriteCapacityUnits")) {
                provisionedThroughputDescription.setWriteCapacityUnits(LongJsonUnmarshaller.getInstance().unmarshall(context));
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return provisionedThroughputDescription;
    }

    public static ProvisionedThroughputDescriptionJsonUnmarshaller getInstance() {
        if (instance == null) {
            instance = new ProvisionedThroughputDescriptionJsonUnmarshaller();
        }
        return instance;
    }
}

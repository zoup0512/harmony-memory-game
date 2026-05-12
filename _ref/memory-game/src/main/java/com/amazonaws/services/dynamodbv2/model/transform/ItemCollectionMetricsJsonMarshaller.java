package com.amazonaws.services.dynamodbv2.model.transform;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ItemCollectionMetrics;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

class ItemCollectionMetricsJsonMarshaller {
    private static ItemCollectionMetricsJsonMarshaller instance;

    ItemCollectionMetricsJsonMarshaller() {
    }

    public void marshall(ItemCollectionMetrics itemCollectionMetrics, AwsJsonWriter jsonWriter) throws Exception {
        jsonWriter.beginObject();
        if (itemCollectionMetrics.getItemCollectionKey() != null) {
            Map<String, AttributeValue> itemCollectionKey = itemCollectionMetrics.getItemCollectionKey();
            jsonWriter.name("ItemCollectionKey");
            jsonWriter.beginObject();
            for (Entry<String, AttributeValue> itemCollectionKeyEntry : itemCollectionKey.entrySet()) {
                AttributeValue itemCollectionKeyValue = (AttributeValue) itemCollectionKeyEntry.getValue();
                if (itemCollectionKeyValue != null) {
                    jsonWriter.name((String) itemCollectionKeyEntry.getKey());
                    AttributeValueJsonMarshaller.getInstance().marshall(itemCollectionKeyValue, jsonWriter);
                }
            }
            jsonWriter.endObject();
        }
        if (itemCollectionMetrics.getSizeEstimateRangeGB() != null) {
            List<Double> sizeEstimateRangeGB = itemCollectionMetrics.getSizeEstimateRangeGB();
            jsonWriter.name("SizeEstimateRangeGB");
            jsonWriter.beginArray();
            for (Number sizeEstimateRangeGBItem : sizeEstimateRangeGB) {
                if (sizeEstimateRangeGBItem != null) {
                    jsonWriter.value(sizeEstimateRangeGBItem);
                }
            }
            jsonWriter.endArray();
        }
        jsonWriter.endObject();
    }

    public static ItemCollectionMetricsJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new ItemCollectionMetricsJsonMarshaller();
        }
        return instance;
    }
}

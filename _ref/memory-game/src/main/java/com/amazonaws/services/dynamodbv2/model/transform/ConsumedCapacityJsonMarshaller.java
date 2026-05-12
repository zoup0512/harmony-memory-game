package com.amazonaws.services.dynamodbv2.model.transform;

import com.amazonaws.services.dynamodbv2.model.Capacity;
import com.amazonaws.services.dynamodbv2.model.ConsumedCapacity;
import com.amazonaws.util.json.AwsJsonWriter;
import java.util.Map;
import java.util.Map.Entry;

class ConsumedCapacityJsonMarshaller {
    private static ConsumedCapacityJsonMarshaller instance;

    ConsumedCapacityJsonMarshaller() {
    }

    public void marshall(ConsumedCapacity consumedCapacity, AwsJsonWriter jsonWriter) throws Exception {
        jsonWriter.beginObject();
        if (consumedCapacity.getTableName() != null) {
            String tableName = consumedCapacity.getTableName();
            jsonWriter.name("TableName");
            jsonWriter.value(tableName);
        }
        if (consumedCapacity.getCapacityUnits() != null) {
            Number capacityUnits = consumedCapacity.getCapacityUnits();
            jsonWriter.name("CapacityUnits");
            jsonWriter.value(capacityUnits);
        }
        if (consumedCapacity.getTable() != null) {
            Capacity table = consumedCapacity.getTable();
            jsonWriter.name("Table");
            CapacityJsonMarshaller.getInstance().marshall(table, jsonWriter);
        }
        if (consumedCapacity.getLocalSecondaryIndexes() != null) {
            Map<String, Capacity> localSecondaryIndexes = consumedCapacity.getLocalSecondaryIndexes();
            jsonWriter.name("LocalSecondaryIndexes");
            jsonWriter.beginObject();
            for (Entry<String, Capacity> localSecondaryIndexesEntry : localSecondaryIndexes.entrySet()) {
                Capacity localSecondaryIndexesValue = (Capacity) localSecondaryIndexesEntry.getValue();
                if (localSecondaryIndexesValue != null) {
                    jsonWriter.name((String) localSecondaryIndexesEntry.getKey());
                    CapacityJsonMarshaller.getInstance().marshall(localSecondaryIndexesValue, jsonWriter);
                }
            }
            jsonWriter.endObject();
        }
        if (consumedCapacity.getGlobalSecondaryIndexes() != null) {
            Map<String, Capacity> globalSecondaryIndexes = consumedCapacity.getGlobalSecondaryIndexes();
            jsonWriter.name("GlobalSecondaryIndexes");
            jsonWriter.beginObject();
            for (Entry<String, Capacity> globalSecondaryIndexesEntry : globalSecondaryIndexes.entrySet()) {
                Capacity globalSecondaryIndexesValue = (Capacity) globalSecondaryIndexesEntry.getValue();
                if (globalSecondaryIndexesValue != null) {
                    jsonWriter.name((String) globalSecondaryIndexesEntry.getKey());
                    CapacityJsonMarshaller.getInstance().marshall(globalSecondaryIndexesValue, jsonWriter);
                }
            }
            jsonWriter.endObject();
        }
        jsonWriter.endObject();
    }

    public static ConsumedCapacityJsonMarshaller getInstance() {
        if (instance == null) {
            instance = new ConsumedCapacityJsonMarshaller();
        }
        return instance;
    }
}

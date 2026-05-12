package com.amazonaws.services.dynamodbv2.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CreateTableRequest extends AmazonWebServiceRequest implements Serializable {
    private List<AttributeDefinition> attributeDefinitions;
    private List<GlobalSecondaryIndex> globalSecondaryIndexes;
    private List<KeySchemaElement> keySchema;
    private List<LocalSecondaryIndex> localSecondaryIndexes;
    private ProvisionedThroughput provisionedThroughput;
    private StreamSpecification streamSpecification;
    private String tableName;

    public CreateTableRequest(String tableName, List<KeySchemaElement> keySchema) {
        setTableName(tableName);
        setKeySchema(keySchema);
    }

    public CreateTableRequest(List<AttributeDefinition> attributeDefinitions, String tableName, List<KeySchemaElement> keySchema, ProvisionedThroughput provisionedThroughput) {
        setAttributeDefinitions(attributeDefinitions);
        setTableName(tableName);
        setKeySchema(keySchema);
        setProvisionedThroughput(provisionedThroughput);
    }

    public List<AttributeDefinition> getAttributeDefinitions() {
        return this.attributeDefinitions;
    }

    public void setAttributeDefinitions(Collection<AttributeDefinition> attributeDefinitions) {
        if (attributeDefinitions == null) {
            this.attributeDefinitions = null;
        } else {
            this.attributeDefinitions = new ArrayList(attributeDefinitions);
        }
    }

    public CreateTableRequest withAttributeDefinitions(AttributeDefinition... attributeDefinitions) {
        if (getAttributeDefinitions() == null) {
            this.attributeDefinitions = new ArrayList(attributeDefinitions.length);
        }
        for (AttributeDefinition value : attributeDefinitions) {
            this.attributeDefinitions.add(value);
        }
        return this;
    }

    public CreateTableRequest withAttributeDefinitions(Collection<AttributeDefinition> attributeDefinitions) {
        setAttributeDefinitions(attributeDefinitions);
        return this;
    }

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public CreateTableRequest withTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public List<KeySchemaElement> getKeySchema() {
        return this.keySchema;
    }

    public void setKeySchema(Collection<KeySchemaElement> keySchema) {
        if (keySchema == null) {
            this.keySchema = null;
        } else {
            this.keySchema = new ArrayList(keySchema);
        }
    }

    public CreateTableRequest withKeySchema(KeySchemaElement... keySchema) {
        if (getKeySchema() == null) {
            this.keySchema = new ArrayList(keySchema.length);
        }
        for (KeySchemaElement value : keySchema) {
            this.keySchema.add(value);
        }
        return this;
    }

    public CreateTableRequest withKeySchema(Collection<KeySchemaElement> keySchema) {
        setKeySchema(keySchema);
        return this;
    }

    public List<LocalSecondaryIndex> getLocalSecondaryIndexes() {
        return this.localSecondaryIndexes;
    }

    public void setLocalSecondaryIndexes(Collection<LocalSecondaryIndex> localSecondaryIndexes) {
        if (localSecondaryIndexes == null) {
            this.localSecondaryIndexes = null;
        } else {
            this.localSecondaryIndexes = new ArrayList(localSecondaryIndexes);
        }
    }

    public CreateTableRequest withLocalSecondaryIndexes(LocalSecondaryIndex... localSecondaryIndexes) {
        if (getLocalSecondaryIndexes() == null) {
            this.localSecondaryIndexes = new ArrayList(localSecondaryIndexes.length);
        }
        for (LocalSecondaryIndex value : localSecondaryIndexes) {
            this.localSecondaryIndexes.add(value);
        }
        return this;
    }

    public CreateTableRequest withLocalSecondaryIndexes(Collection<LocalSecondaryIndex> localSecondaryIndexes) {
        setLocalSecondaryIndexes(localSecondaryIndexes);
        return this;
    }

    public List<GlobalSecondaryIndex> getGlobalSecondaryIndexes() {
        return this.globalSecondaryIndexes;
    }

    public void setGlobalSecondaryIndexes(Collection<GlobalSecondaryIndex> globalSecondaryIndexes) {
        if (globalSecondaryIndexes == null) {
            this.globalSecondaryIndexes = null;
        } else {
            this.globalSecondaryIndexes = new ArrayList(globalSecondaryIndexes);
        }
    }

    public CreateTableRequest withGlobalSecondaryIndexes(GlobalSecondaryIndex... globalSecondaryIndexes) {
        if (getGlobalSecondaryIndexes() == null) {
            this.globalSecondaryIndexes = new ArrayList(globalSecondaryIndexes.length);
        }
        for (GlobalSecondaryIndex value : globalSecondaryIndexes) {
            this.globalSecondaryIndexes.add(value);
        }
        return this;
    }

    public CreateTableRequest withGlobalSecondaryIndexes(Collection<GlobalSecondaryIndex> globalSecondaryIndexes) {
        setGlobalSecondaryIndexes(globalSecondaryIndexes);
        return this;
    }

    public ProvisionedThroughput getProvisionedThroughput() {
        return this.provisionedThroughput;
    }

    public void setProvisionedThroughput(ProvisionedThroughput provisionedThroughput) {
        this.provisionedThroughput = provisionedThroughput;
    }

    public CreateTableRequest withProvisionedThroughput(ProvisionedThroughput provisionedThroughput) {
        this.provisionedThroughput = provisionedThroughput;
        return this;
    }

    public StreamSpecification getStreamSpecification() {
        return this.streamSpecification;
    }

    public void setStreamSpecification(StreamSpecification streamSpecification) {
        this.streamSpecification = streamSpecification;
    }

    public CreateTableRequest withStreamSpecification(StreamSpecification streamSpecification) {
        this.streamSpecification = streamSpecification;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getAttributeDefinitions() != null) {
            sb.append("AttributeDefinitions: " + getAttributeDefinitions() + ",");
        }
        if (getTableName() != null) {
            sb.append("TableName: " + getTableName() + ",");
        }
        if (getKeySchema() != null) {
            sb.append("KeySchema: " + getKeySchema() + ",");
        }
        if (getLocalSecondaryIndexes() != null) {
            sb.append("LocalSecondaryIndexes: " + getLocalSecondaryIndexes() + ",");
        }
        if (getGlobalSecondaryIndexes() != null) {
            sb.append("GlobalSecondaryIndexes: " + getGlobalSecondaryIndexes() + ",");
        }
        if (getProvisionedThroughput() != null) {
            sb.append("ProvisionedThroughput: " + getProvisionedThroughput() + ",");
        }
        if (getStreamSpecification() != null) {
            sb.append("StreamSpecification: " + getStreamSpecification());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        int i;
        int i2 = 0;
        int hashCode = ((((((((getAttributeDefinitions() == null ? 0 : getAttributeDefinitions().hashCode()) + 31) * 31) + (getTableName() == null ? 0 : getTableName().hashCode())) * 31) + (getKeySchema() == null ? 0 : getKeySchema().hashCode())) * 31) + (getLocalSecondaryIndexes() == null ? 0 : getLocalSecondaryIndexes().hashCode())) * 31;
        if (getGlobalSecondaryIndexes() == null) {
            i = 0;
        } else {
            i = getGlobalSecondaryIndexes().hashCode();
        }
        i = (((hashCode + i) * 31) + (getProvisionedThroughput() == null ? 0 : getProvisionedThroughput().hashCode())) * 31;
        if (getStreamSpecification() != null) {
            i2 = getStreamSpecification().hashCode();
        }
        return i + i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateTableRequest)) {
            return false;
        }
        CreateTableRequest other = (CreateTableRequest) obj;
        if (((other.getAttributeDefinitions() == null ? 1 : 0) ^ (getAttributeDefinitions() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getAttributeDefinitions() != null && !other.getAttributeDefinitions().equals(getAttributeDefinitions())) {
            return false;
        }
        int i;
        if (other.getTableName() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getTableName() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getTableName() != null && !other.getTableName().equals(getTableName())) {
            return false;
        }
        if (other.getKeySchema() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getKeySchema() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getKeySchema() != null && !other.getKeySchema().equals(getKeySchema())) {
            return false;
        }
        if (other.getLocalSecondaryIndexes() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getLocalSecondaryIndexes() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getLocalSecondaryIndexes() != null && !other.getLocalSecondaryIndexes().equals(getLocalSecondaryIndexes())) {
            return false;
        }
        if (other.getGlobalSecondaryIndexes() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getGlobalSecondaryIndexes() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getGlobalSecondaryIndexes() != null && !other.getGlobalSecondaryIndexes().equals(getGlobalSecondaryIndexes())) {
            return false;
        }
        if (other.getProvisionedThroughput() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getProvisionedThroughput() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getProvisionedThroughput() != null && !other.getProvisionedThroughput().equals(getProvisionedThroughput())) {
            return false;
        }
        if (other.getStreamSpecification() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getStreamSpecification() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getStreamSpecification() == null || other.getStreamSpecification().equals(getStreamSpecification())) {
            return true;
        }
        return false;
    }
}

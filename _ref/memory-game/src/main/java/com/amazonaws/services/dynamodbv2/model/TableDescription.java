package com.amazonaws.services.dynamodbv2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class TableDescription implements Serializable {
    private List<AttributeDefinition> attributeDefinitions;
    private Date creationDateTime;
    private List<GlobalSecondaryIndexDescription> globalSecondaryIndexes;
    private Long itemCount;
    private List<KeySchemaElement> keySchema;
    private String latestStreamArn;
    private String latestStreamLabel;
    private List<LocalSecondaryIndexDescription> localSecondaryIndexes;
    private ProvisionedThroughputDescription provisionedThroughput;
    private StreamSpecification streamSpecification;
    private String tableArn;
    private String tableName;
    private Long tableSizeBytes;
    private String tableStatus;

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

    public TableDescription withAttributeDefinitions(AttributeDefinition... attributeDefinitions) {
        if (getAttributeDefinitions() == null) {
            this.attributeDefinitions = new ArrayList(attributeDefinitions.length);
        }
        for (AttributeDefinition value : attributeDefinitions) {
            this.attributeDefinitions.add(value);
        }
        return this;
    }

    public TableDescription withAttributeDefinitions(Collection<AttributeDefinition> attributeDefinitions) {
        setAttributeDefinitions(attributeDefinitions);
        return this;
    }

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public TableDescription withTableName(String tableName) {
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

    public TableDescription withKeySchema(KeySchemaElement... keySchema) {
        if (getKeySchema() == null) {
            this.keySchema = new ArrayList(keySchema.length);
        }
        for (KeySchemaElement value : keySchema) {
            this.keySchema.add(value);
        }
        return this;
    }

    public TableDescription withKeySchema(Collection<KeySchemaElement> keySchema) {
        setKeySchema(keySchema);
        return this;
    }

    public String getTableStatus() {
        return this.tableStatus;
    }

    public void setTableStatus(String tableStatus) {
        this.tableStatus = tableStatus;
    }

    public TableDescription withTableStatus(String tableStatus) {
        this.tableStatus = tableStatus;
        return this;
    }

    public void setTableStatus(TableStatus tableStatus) {
        this.tableStatus = tableStatus.toString();
    }

    public TableDescription withTableStatus(TableStatus tableStatus) {
        this.tableStatus = tableStatus.toString();
        return this;
    }

    public Date getCreationDateTime() {
        return this.creationDateTime;
    }

    public void setCreationDateTime(Date creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    public TableDescription withCreationDateTime(Date creationDateTime) {
        this.creationDateTime = creationDateTime;
        return this;
    }

    public ProvisionedThroughputDescription getProvisionedThroughput() {
        return this.provisionedThroughput;
    }

    public void setProvisionedThroughput(ProvisionedThroughputDescription provisionedThroughput) {
        this.provisionedThroughput = provisionedThroughput;
    }

    public TableDescription withProvisionedThroughput(ProvisionedThroughputDescription provisionedThroughput) {
        this.provisionedThroughput = provisionedThroughput;
        return this;
    }

    public Long getTableSizeBytes() {
        return this.tableSizeBytes;
    }

    public void setTableSizeBytes(Long tableSizeBytes) {
        this.tableSizeBytes = tableSizeBytes;
    }

    public TableDescription withTableSizeBytes(Long tableSizeBytes) {
        this.tableSizeBytes = tableSizeBytes;
        return this;
    }

    public Long getItemCount() {
        return this.itemCount;
    }

    public void setItemCount(Long itemCount) {
        this.itemCount = itemCount;
    }

    public TableDescription withItemCount(Long itemCount) {
        this.itemCount = itemCount;
        return this;
    }

    public String getTableArn() {
        return this.tableArn;
    }

    public void setTableArn(String tableArn) {
        this.tableArn = tableArn;
    }

    public TableDescription withTableArn(String tableArn) {
        this.tableArn = tableArn;
        return this;
    }

    public List<LocalSecondaryIndexDescription> getLocalSecondaryIndexes() {
        return this.localSecondaryIndexes;
    }

    public void setLocalSecondaryIndexes(Collection<LocalSecondaryIndexDescription> localSecondaryIndexes) {
        if (localSecondaryIndexes == null) {
            this.localSecondaryIndexes = null;
        } else {
            this.localSecondaryIndexes = new ArrayList(localSecondaryIndexes);
        }
    }

    public TableDescription withLocalSecondaryIndexes(LocalSecondaryIndexDescription... localSecondaryIndexes) {
        if (getLocalSecondaryIndexes() == null) {
            this.localSecondaryIndexes = new ArrayList(localSecondaryIndexes.length);
        }
        for (LocalSecondaryIndexDescription value : localSecondaryIndexes) {
            this.localSecondaryIndexes.add(value);
        }
        return this;
    }

    public TableDescription withLocalSecondaryIndexes(Collection<LocalSecondaryIndexDescription> localSecondaryIndexes) {
        setLocalSecondaryIndexes(localSecondaryIndexes);
        return this;
    }

    public List<GlobalSecondaryIndexDescription> getGlobalSecondaryIndexes() {
        return this.globalSecondaryIndexes;
    }

    public void setGlobalSecondaryIndexes(Collection<GlobalSecondaryIndexDescription> globalSecondaryIndexes) {
        if (globalSecondaryIndexes == null) {
            this.globalSecondaryIndexes = null;
        } else {
            this.globalSecondaryIndexes = new ArrayList(globalSecondaryIndexes);
        }
    }

    public TableDescription withGlobalSecondaryIndexes(GlobalSecondaryIndexDescription... globalSecondaryIndexes) {
        if (getGlobalSecondaryIndexes() == null) {
            this.globalSecondaryIndexes = new ArrayList(globalSecondaryIndexes.length);
        }
        for (GlobalSecondaryIndexDescription value : globalSecondaryIndexes) {
            this.globalSecondaryIndexes.add(value);
        }
        return this;
    }

    public TableDescription withGlobalSecondaryIndexes(Collection<GlobalSecondaryIndexDescription> globalSecondaryIndexes) {
        setGlobalSecondaryIndexes(globalSecondaryIndexes);
        return this;
    }

    public StreamSpecification getStreamSpecification() {
        return this.streamSpecification;
    }

    public void setStreamSpecification(StreamSpecification streamSpecification) {
        this.streamSpecification = streamSpecification;
    }

    public TableDescription withStreamSpecification(StreamSpecification streamSpecification) {
        this.streamSpecification = streamSpecification;
        return this;
    }

    public String getLatestStreamLabel() {
        return this.latestStreamLabel;
    }

    public void setLatestStreamLabel(String latestStreamLabel) {
        this.latestStreamLabel = latestStreamLabel;
    }

    public TableDescription withLatestStreamLabel(String latestStreamLabel) {
        this.latestStreamLabel = latestStreamLabel;
        return this;
    }

    public String getLatestStreamArn() {
        return this.latestStreamArn;
    }

    public void setLatestStreamArn(String latestStreamArn) {
        this.latestStreamArn = latestStreamArn;
    }

    public TableDescription withLatestStreamArn(String latestStreamArn) {
        this.latestStreamArn = latestStreamArn;
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
        if (getTableStatus() != null) {
            sb.append("TableStatus: " + getTableStatus() + ",");
        }
        if (getCreationDateTime() != null) {
            sb.append("CreationDateTime: " + getCreationDateTime() + ",");
        }
        if (getProvisionedThroughput() != null) {
            sb.append("ProvisionedThroughput: " + getProvisionedThroughput() + ",");
        }
        if (getTableSizeBytes() != null) {
            sb.append("TableSizeBytes: " + getTableSizeBytes() + ",");
        }
        if (getItemCount() != null) {
            sb.append("ItemCount: " + getItemCount() + ",");
        }
        if (getTableArn() != null) {
            sb.append("TableArn: " + getTableArn() + ",");
        }
        if (getLocalSecondaryIndexes() != null) {
            sb.append("LocalSecondaryIndexes: " + getLocalSecondaryIndexes() + ",");
        }
        if (getGlobalSecondaryIndexes() != null) {
            sb.append("GlobalSecondaryIndexes: " + getGlobalSecondaryIndexes() + ",");
        }
        if (getStreamSpecification() != null) {
            sb.append("StreamSpecification: " + getStreamSpecification() + ",");
        }
        if (getLatestStreamLabel() != null) {
            sb.append("LatestStreamLabel: " + getLatestStreamLabel() + ",");
        }
        if (getLatestStreamArn() != null) {
            sb.append("LatestStreamArn: " + getLatestStreamArn());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        int i;
        int i2 = 0;
        int hashCode = ((((((((((((((((((((getAttributeDefinitions() == null ? 0 : getAttributeDefinitions().hashCode()) + 31) * 31) + (getTableName() == null ? 0 : getTableName().hashCode())) * 31) + (getKeySchema() == null ? 0 : getKeySchema().hashCode())) * 31) + (getTableStatus() == null ? 0 : getTableStatus().hashCode())) * 31) + (getCreationDateTime() == null ? 0 : getCreationDateTime().hashCode())) * 31) + (getProvisionedThroughput() == null ? 0 : getProvisionedThroughput().hashCode())) * 31) + (getTableSizeBytes() == null ? 0 : getTableSizeBytes().hashCode())) * 31) + (getItemCount() == null ? 0 : getItemCount().hashCode())) * 31) + (getTableArn() == null ? 0 : getTableArn().hashCode())) * 31) + (getLocalSecondaryIndexes() == null ? 0 : getLocalSecondaryIndexes().hashCode())) * 31;
        if (getGlobalSecondaryIndexes() == null) {
            i = 0;
        } else {
            i = getGlobalSecondaryIndexes().hashCode();
        }
        i = (((((hashCode + i) * 31) + (getStreamSpecification() == null ? 0 : getStreamSpecification().hashCode())) * 31) + (getLatestStreamLabel() == null ? 0 : getLatestStreamLabel().hashCode())) * 31;
        if (getLatestStreamArn() != null) {
            i2 = getLatestStreamArn().hashCode();
        }
        return i + i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof TableDescription)) {
            return false;
        }
        TableDescription other = (TableDescription) obj;
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
        if (other.getTableStatus() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getTableStatus() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getTableStatus() != null && !other.getTableStatus().equals(getTableStatus())) {
            return false;
        }
        if (other.getCreationDateTime() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getCreationDateTime() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getCreationDateTime() != null && !other.getCreationDateTime().equals(getCreationDateTime())) {
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
        if (other.getTableSizeBytes() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getTableSizeBytes() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getTableSizeBytes() != null && !other.getTableSizeBytes().equals(getTableSizeBytes())) {
            return false;
        }
        if (other.getItemCount() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getItemCount() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getItemCount() != null && !other.getItemCount().equals(getItemCount())) {
            return false;
        }
        if (other.getTableArn() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getTableArn() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getTableArn() != null && !other.getTableArn().equals(getTableArn())) {
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
        if (other.getStreamSpecification() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getStreamSpecification() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getStreamSpecification() != null && !other.getStreamSpecification().equals(getStreamSpecification())) {
            return false;
        }
        if (other.getLatestStreamLabel() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getLatestStreamLabel() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getLatestStreamLabel() != null && !other.getLatestStreamLabel().equals(getLatestStreamLabel())) {
            return false;
        }
        if (other.getLatestStreamArn() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getLatestStreamArn() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getLatestStreamArn() == null || other.getLatestStreamArn().equals(getLatestStreamArn())) {
            return true;
        }
        return false;
    }
}

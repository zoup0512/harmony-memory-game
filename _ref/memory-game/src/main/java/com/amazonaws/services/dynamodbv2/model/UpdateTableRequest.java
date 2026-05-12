package com.amazonaws.services.dynamodbv2.model;

import com.amazonaws.AmazonWebServiceRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UpdateTableRequest extends AmazonWebServiceRequest implements Serializable {
    private List<AttributeDefinition> attributeDefinitions;
    private List<GlobalSecondaryIndexUpdate> globalSecondaryIndexUpdates;
    private ProvisionedThroughput provisionedThroughput;
    private StreamSpecification streamSpecification;
    private String tableName;

    public UpdateTableRequest(String tableName, ProvisionedThroughput provisionedThroughput) {
        setTableName(tableName);
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

    public UpdateTableRequest withAttributeDefinitions(AttributeDefinition... attributeDefinitions) {
        if (getAttributeDefinitions() == null) {
            this.attributeDefinitions = new ArrayList(attributeDefinitions.length);
        }
        for (AttributeDefinition value : attributeDefinitions) {
            this.attributeDefinitions.add(value);
        }
        return this;
    }

    public UpdateTableRequest withAttributeDefinitions(Collection<AttributeDefinition> attributeDefinitions) {
        setAttributeDefinitions(attributeDefinitions);
        return this;
    }

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public UpdateTableRequest withTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public ProvisionedThroughput getProvisionedThroughput() {
        return this.provisionedThroughput;
    }

    public void setProvisionedThroughput(ProvisionedThroughput provisionedThroughput) {
        this.provisionedThroughput = provisionedThroughput;
    }

    public UpdateTableRequest withProvisionedThroughput(ProvisionedThroughput provisionedThroughput) {
        this.provisionedThroughput = provisionedThroughput;
        return this;
    }

    public List<GlobalSecondaryIndexUpdate> getGlobalSecondaryIndexUpdates() {
        return this.globalSecondaryIndexUpdates;
    }

    public void setGlobalSecondaryIndexUpdates(Collection<GlobalSecondaryIndexUpdate> globalSecondaryIndexUpdates) {
        if (globalSecondaryIndexUpdates == null) {
            this.globalSecondaryIndexUpdates = null;
        } else {
            this.globalSecondaryIndexUpdates = new ArrayList(globalSecondaryIndexUpdates);
        }
    }

    public UpdateTableRequest withGlobalSecondaryIndexUpdates(GlobalSecondaryIndexUpdate... globalSecondaryIndexUpdates) {
        if (getGlobalSecondaryIndexUpdates() == null) {
            this.globalSecondaryIndexUpdates = new ArrayList(globalSecondaryIndexUpdates.length);
        }
        for (GlobalSecondaryIndexUpdate value : globalSecondaryIndexUpdates) {
            this.globalSecondaryIndexUpdates.add(value);
        }
        return this;
    }

    public UpdateTableRequest withGlobalSecondaryIndexUpdates(Collection<GlobalSecondaryIndexUpdate> globalSecondaryIndexUpdates) {
        setGlobalSecondaryIndexUpdates(globalSecondaryIndexUpdates);
        return this;
    }

    public StreamSpecification getStreamSpecification() {
        return this.streamSpecification;
    }

    public void setStreamSpecification(StreamSpecification streamSpecification) {
        this.streamSpecification = streamSpecification;
    }

    public UpdateTableRequest withStreamSpecification(StreamSpecification streamSpecification) {
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
        if (getProvisionedThroughput() != null) {
            sb.append("ProvisionedThroughput: " + getProvisionedThroughput() + ",");
        }
        if (getGlobalSecondaryIndexUpdates() != null) {
            sb.append("GlobalSecondaryIndexUpdates: " + getGlobalSecondaryIndexUpdates() + ",");
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
        int hashCode = ((((((getAttributeDefinitions() == null ? 0 : getAttributeDefinitions().hashCode()) + 31) * 31) + (getTableName() == null ? 0 : getTableName().hashCode())) * 31) + (getProvisionedThroughput() == null ? 0 : getProvisionedThroughput().hashCode())) * 31;
        if (getGlobalSecondaryIndexUpdates() == null) {
            i = 0;
        } else {
            i = getGlobalSecondaryIndexUpdates().hashCode();
        }
        i = (hashCode + i) * 31;
        if (getStreamSpecification() != null) {
            i2 = getStreamSpecification().hashCode();
        }
        return i + i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof UpdateTableRequest)) {
            return false;
        }
        UpdateTableRequest other = (UpdateTableRequest) obj;
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
        int i2;
        if (other.getGlobalSecondaryIndexUpdates() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if (getGlobalSecondaryIndexUpdates() == null) {
            i2 = 1;
        } else {
            i2 = 0;
        }
        if ((i ^ i2) != 0) {
            return false;
        }
        if (other.getGlobalSecondaryIndexUpdates() != null && !other.getGlobalSecondaryIndexUpdates().equals(getGlobalSecondaryIndexUpdates())) {
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

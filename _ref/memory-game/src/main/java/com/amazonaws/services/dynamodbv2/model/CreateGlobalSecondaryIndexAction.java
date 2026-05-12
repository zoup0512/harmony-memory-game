package com.amazonaws.services.dynamodbv2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CreateGlobalSecondaryIndexAction implements Serializable {
    private String indexName;
    private List<KeySchemaElement> keySchema;
    private Projection projection;
    private ProvisionedThroughput provisionedThroughput;

    public String getIndexName() {
        return this.indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public CreateGlobalSecondaryIndexAction withIndexName(String indexName) {
        this.indexName = indexName;
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

    public CreateGlobalSecondaryIndexAction withKeySchema(KeySchemaElement... keySchema) {
        if (getKeySchema() == null) {
            this.keySchema = new ArrayList(keySchema.length);
        }
        for (KeySchemaElement value : keySchema) {
            this.keySchema.add(value);
        }
        return this;
    }

    public CreateGlobalSecondaryIndexAction withKeySchema(Collection<KeySchemaElement> keySchema) {
        setKeySchema(keySchema);
        return this;
    }

    public Projection getProjection() {
        return this.projection;
    }

    public void setProjection(Projection projection) {
        this.projection = projection;
    }

    public CreateGlobalSecondaryIndexAction withProjection(Projection projection) {
        this.projection = projection;
        return this;
    }

    public ProvisionedThroughput getProvisionedThroughput() {
        return this.provisionedThroughput;
    }

    public void setProvisionedThroughput(ProvisionedThroughput provisionedThroughput) {
        this.provisionedThroughput = provisionedThroughput;
    }

    public CreateGlobalSecondaryIndexAction withProvisionedThroughput(ProvisionedThroughput provisionedThroughput) {
        this.provisionedThroughput = provisionedThroughput;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getIndexName() != null) {
            sb.append("IndexName: " + getIndexName() + ",");
        }
        if (getKeySchema() != null) {
            sb.append("KeySchema: " + getKeySchema() + ",");
        }
        if (getProjection() != null) {
            sb.append("Projection: " + getProjection() + ",");
        }
        if (getProvisionedThroughput() != null) {
            sb.append("ProvisionedThroughput: " + getProvisionedThroughput());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((getIndexName() == null ? 0 : getIndexName().hashCode()) + 31) * 31) + (getKeySchema() == null ? 0 : getKeySchema().hashCode())) * 31) + (getProjection() == null ? 0 : getProjection().hashCode())) * 31;
        if (getProvisionedThroughput() != null) {
            i = getProvisionedThroughput().hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof CreateGlobalSecondaryIndexAction)) {
            return false;
        }
        CreateGlobalSecondaryIndexAction other = (CreateGlobalSecondaryIndexAction) obj;
        if (((other.getIndexName() == null ? 1 : 0) ^ (getIndexName() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getIndexName() != null && !other.getIndexName().equals(getIndexName())) {
            return false;
        }
        int i;
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
        if (other.getProjection() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getProjection() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getProjection() != null && !other.getProjection().equals(getProjection())) {
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
        if (other.getProvisionedThroughput() == null || other.getProvisionedThroughput().equals(getProvisionedThroughput())) {
            return true;
        }
        return false;
    }
}

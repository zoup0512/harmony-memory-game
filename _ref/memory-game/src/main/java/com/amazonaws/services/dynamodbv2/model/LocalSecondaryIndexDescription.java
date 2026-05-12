package com.amazonaws.services.dynamodbv2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LocalSecondaryIndexDescription implements Serializable {
    private String indexArn;
    private String indexName;
    private Long indexSizeBytes;
    private Long itemCount;
    private List<KeySchemaElement> keySchema;
    private Projection projection;

    public String getIndexName() {
        return this.indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public LocalSecondaryIndexDescription withIndexName(String indexName) {
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

    public LocalSecondaryIndexDescription withKeySchema(KeySchemaElement... keySchema) {
        if (getKeySchema() == null) {
            this.keySchema = new ArrayList(keySchema.length);
        }
        for (KeySchemaElement value : keySchema) {
            this.keySchema.add(value);
        }
        return this;
    }

    public LocalSecondaryIndexDescription withKeySchema(Collection<KeySchemaElement> keySchema) {
        setKeySchema(keySchema);
        return this;
    }

    public Projection getProjection() {
        return this.projection;
    }

    public void setProjection(Projection projection) {
        this.projection = projection;
    }

    public LocalSecondaryIndexDescription withProjection(Projection projection) {
        this.projection = projection;
        return this;
    }

    public Long getIndexSizeBytes() {
        return this.indexSizeBytes;
    }

    public void setIndexSizeBytes(Long indexSizeBytes) {
        this.indexSizeBytes = indexSizeBytes;
    }

    public LocalSecondaryIndexDescription withIndexSizeBytes(Long indexSizeBytes) {
        this.indexSizeBytes = indexSizeBytes;
        return this;
    }

    public Long getItemCount() {
        return this.itemCount;
    }

    public void setItemCount(Long itemCount) {
        this.itemCount = itemCount;
    }

    public LocalSecondaryIndexDescription withItemCount(Long itemCount) {
        this.itemCount = itemCount;
        return this;
    }

    public String getIndexArn() {
        return this.indexArn;
    }

    public void setIndexArn(String indexArn) {
        this.indexArn = indexArn;
    }

    public LocalSecondaryIndexDescription withIndexArn(String indexArn) {
        this.indexArn = indexArn;
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
        if (getIndexSizeBytes() != null) {
            sb.append("IndexSizeBytes: " + getIndexSizeBytes() + ",");
        }
        if (getItemCount() != null) {
            sb.append("ItemCount: " + getItemCount() + ",");
        }
        if (getIndexArn() != null) {
            sb.append("IndexArn: " + getIndexArn());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((((((((getIndexName() == null ? 0 : getIndexName().hashCode()) + 31) * 31) + (getKeySchema() == null ? 0 : getKeySchema().hashCode())) * 31) + (getProjection() == null ? 0 : getProjection().hashCode())) * 31) + (getIndexSizeBytes() == null ? 0 : getIndexSizeBytes().hashCode())) * 31) + (getItemCount() == null ? 0 : getItemCount().hashCode())) * 31;
        if (getIndexArn() != null) {
            i = getIndexArn().hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof LocalSecondaryIndexDescription)) {
            return false;
        }
        LocalSecondaryIndexDescription other = (LocalSecondaryIndexDescription) obj;
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
        if (other.getIndexSizeBytes() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getIndexSizeBytes() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getIndexSizeBytes() != null && !other.getIndexSizeBytes().equals(getIndexSizeBytes())) {
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
        if (other.getIndexArn() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getIndexArn() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getIndexArn() == null || other.getIndexArn().equals(getIndexArn())) {
            return true;
        }
        return false;
    }
}

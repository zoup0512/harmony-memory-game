package com.amazonaws.services.dynamodbv2.model;

import java.io.Serializable;

public class GlobalSecondaryIndexUpdate implements Serializable {
    private CreateGlobalSecondaryIndexAction create;
    private DeleteGlobalSecondaryIndexAction delete;
    private UpdateGlobalSecondaryIndexAction update;

    public UpdateGlobalSecondaryIndexAction getUpdate() {
        return this.update;
    }

    public void setUpdate(UpdateGlobalSecondaryIndexAction update) {
        this.update = update;
    }

    public GlobalSecondaryIndexUpdate withUpdate(UpdateGlobalSecondaryIndexAction update) {
        this.update = update;
        return this;
    }

    public CreateGlobalSecondaryIndexAction getCreate() {
        return this.create;
    }

    public void setCreate(CreateGlobalSecondaryIndexAction create) {
        this.create = create;
    }

    public GlobalSecondaryIndexUpdate withCreate(CreateGlobalSecondaryIndexAction create) {
        this.create = create;
        return this;
    }

    public DeleteGlobalSecondaryIndexAction getDelete() {
        return this.delete;
    }

    public void setDelete(DeleteGlobalSecondaryIndexAction delete) {
        this.delete = delete;
    }

    public GlobalSecondaryIndexUpdate withDelete(DeleteGlobalSecondaryIndexAction delete) {
        this.delete = delete;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getUpdate() != null) {
            sb.append("Update: " + getUpdate() + ",");
        }
        if (getCreate() != null) {
            sb.append("Create: " + getCreate() + ",");
        }
        if (getDelete() != null) {
            sb.append("Delete: " + getDelete());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((getUpdate() == null ? 0 : getUpdate().hashCode()) + 31) * 31) + (getCreate() == null ? 0 : getCreate().hashCode())) * 31;
        if (getDelete() != null) {
            i = getDelete().hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof GlobalSecondaryIndexUpdate)) {
            return false;
        }
        GlobalSecondaryIndexUpdate other = (GlobalSecondaryIndexUpdate) obj;
        if (((other.getUpdate() == null ? 1 : 0) ^ (getUpdate() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getUpdate() != null && !other.getUpdate().equals(getUpdate())) {
            return false;
        }
        int i;
        if (other.getCreate() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getCreate() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getCreate() != null && !other.getCreate().equals(getCreate())) {
            return false;
        }
        if (other.getDelete() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getDelete() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getDelete() == null || other.getDelete().equals(getDelete())) {
            return true;
        }
        return false;
    }
}

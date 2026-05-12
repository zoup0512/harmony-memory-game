package com.amazonaws.services.dynamodbv2.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Projection implements Serializable {
    private List<String> nonKeyAttributes;
    private String projectionType;

    public String getProjectionType() {
        return this.projectionType;
    }

    public void setProjectionType(String projectionType) {
        this.projectionType = projectionType;
    }

    public Projection withProjectionType(String projectionType) {
        this.projectionType = projectionType;
        return this;
    }

    public void setProjectionType(ProjectionType projectionType) {
        this.projectionType = projectionType.toString();
    }

    public Projection withProjectionType(ProjectionType projectionType) {
        this.projectionType = projectionType.toString();
        return this;
    }

    public List<String> getNonKeyAttributes() {
        return this.nonKeyAttributes;
    }

    public void setNonKeyAttributes(Collection<String> nonKeyAttributes) {
        if (nonKeyAttributes == null) {
            this.nonKeyAttributes = null;
        } else {
            this.nonKeyAttributes = new ArrayList(nonKeyAttributes);
        }
    }

    public Projection withNonKeyAttributes(String... nonKeyAttributes) {
        if (getNonKeyAttributes() == null) {
            this.nonKeyAttributes = new ArrayList(nonKeyAttributes.length);
        }
        for (String value : nonKeyAttributes) {
            this.nonKeyAttributes.add(value);
        }
        return this;
    }

    public Projection withNonKeyAttributes(Collection<String> nonKeyAttributes) {
        setNonKeyAttributes(nonKeyAttributes);
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getProjectionType() != null) {
            sb.append("ProjectionType: " + getProjectionType() + ",");
        }
        if (getNonKeyAttributes() != null) {
            sb.append("NonKeyAttributes: " + getNonKeyAttributes());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((getProjectionType() == null ? 0 : getProjectionType().hashCode()) + 31) * 31;
        if (getNonKeyAttributes() != null) {
            i = getNonKeyAttributes().hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Projection)) {
            return false;
        }
        Projection other = (Projection) obj;
        if (((other.getProjectionType() == null ? 1 : 0) ^ (getProjectionType() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getProjectionType() != null && !other.getProjectionType().equals(getProjectionType())) {
            return false;
        }
        int i;
        if (other.getNonKeyAttributes() == null) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i ^ (getNonKeyAttributes() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getNonKeyAttributes() == null || other.getNonKeyAttributes().equals(getNonKeyAttributes())) {
            return true;
        }
        return false;
    }
}

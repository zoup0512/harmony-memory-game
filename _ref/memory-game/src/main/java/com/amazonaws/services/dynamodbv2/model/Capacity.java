package com.amazonaws.services.dynamodbv2.model;

import java.io.Serializable;

public class Capacity implements Serializable {
    private Double capacityUnits;

    public Double getCapacityUnits() {
        return this.capacityUnits;
    }

    public void setCapacityUnits(Double capacityUnits) {
        this.capacityUnits = capacityUnits;
    }

    public Capacity withCapacityUnits(Double capacityUnits) {
        this.capacityUnits = capacityUnits;
        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getCapacityUnits() != null) {
            sb.append("CapacityUnits: " + getCapacityUnits());
        }
        sb.append("}");
        return sb.toString();
    }

    public int hashCode() {
        return (getCapacityUnits() == null ? 0 : getCapacityUnits().hashCode()) + 31;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof Capacity)) {
            return false;
        }
        Capacity other = (Capacity) obj;
        if (((other.getCapacityUnits() == null ? 1 : 0) ^ (getCapacityUnits() == null ? 1 : 0)) != 0) {
            return false;
        }
        if (other.getCapacityUnits() == null || other.getCapacityUnits().equals(getCapacityUnits())) {
            return true;
        }
        return false;
    }
}

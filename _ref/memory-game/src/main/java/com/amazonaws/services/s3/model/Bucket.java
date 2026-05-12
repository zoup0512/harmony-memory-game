package com.amazonaws.services.s3.model;

import java.util.Date;

public class Bucket {
    private static final long serialVersionUID = -8646831898339939580L;
    private Date creationDate = null;
    private String name = null;
    private Owner owner = null;

    public Bucket(String name) {
        this.name = name;
    }

    public String toString() {
        return "S3Bucket [name=" + getName() + ", creationDate=" + getCreationDate() + ", owner=" + getOwner() + "]";
    }

    public Owner getOwner() {
        return this.owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.amazonaws.services.s3.model;

public class CanonicalGrantee implements Grantee {
    private String displayName = null;
    private String id = null;

    public String getTypeIdentifier() {
        return "id";
    }

    public CanonicalGrantee(String identifier) {
        setIdentifier(identifier);
    }

    public void setIdentifier(String id) {
        this.id = id;
    }

    public String getIdentifier() {
        return this.id;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CanonicalGrantee)) {
            return false;
        }
        return this.id.equals(((CanonicalGrantee) obj).id);
    }

    public int hashCode() {
        return this.id.hashCode();
    }
}

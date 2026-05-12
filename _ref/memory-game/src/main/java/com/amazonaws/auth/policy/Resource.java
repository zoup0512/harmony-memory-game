package com.amazonaws.auth.policy;

public class Resource {
    private final String resource;

    public Resource(String resource) {
        this.resource = resource;
    }

    public String getId() {
        return this.resource;
    }
}

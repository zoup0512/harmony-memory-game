package com.amazonaws.internal;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;

public class StaticCredentialsProvider implements AWSCredentialsProvider {
    private final AWSCredentials credentials;

    public StaticCredentialsProvider(AWSCredentials credentials) {
        this.credentials = credentials;
    }

    public AWSCredentials getCredentials() {
        return this.credentials;
    }

    public void refresh() {
    }
}

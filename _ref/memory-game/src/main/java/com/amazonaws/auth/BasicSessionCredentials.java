package com.amazonaws.auth;

public class BasicSessionCredentials implements AWSSessionCredentials {
    private final String awsAccessKey;
    private final String awsSecretKey;
    private final String sessionToken;

    public BasicSessionCredentials(String awsAccessKey, String awsSecretKey, String sessionToken) {
        this.awsAccessKey = awsAccessKey;
        this.awsSecretKey = awsSecretKey;
        this.sessionToken = sessionToken;
    }

    public String getAWSAccessKeyId() {
        return this.awsAccessKey;
    }

    public String getAWSSecretKey() {
        return this.awsSecretKey;
    }

    public String getSessionToken() {
        return this.sessionToken;
    }
}

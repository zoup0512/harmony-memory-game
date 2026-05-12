package com.amazonaws.auth;

import com.amazonaws.AmazonClientException;
import java.io.IOException;
import java.io.InputStream;

@Deprecated
public class ClasspathPropertiesFileCredentialsProvider implements AWSCredentialsProvider {
    private static String DEFAULT_PROPERTIES_FILE = "AwsCredentials.properties";
    private final String credentialsFilePath;

    public ClasspathPropertiesFileCredentialsProvider() {
        this(DEFAULT_PROPERTIES_FILE);
    }

    public ClasspathPropertiesFileCredentialsProvider(String credentialsFilePath) {
        if (credentialsFilePath == null) {
            throw new IllegalArgumentException("Credentials file path cannot be null");
        } else if (credentialsFilePath.startsWith("/")) {
            this.credentialsFilePath = credentialsFilePath;
        } else {
            this.credentialsFilePath = "/" + credentialsFilePath;
        }
    }

    public AWSCredentials getCredentials() {
        InputStream inputStream = getClass().getResourceAsStream(this.credentialsFilePath);
        if (inputStream == null) {
            throw new AmazonClientException("Unable to load AWS credentials from the " + this.credentialsFilePath + " file on the classpath");
        }
        try {
            return new PropertiesCredentials(inputStream);
        } catch (IOException e) {
            throw new AmazonClientException("Unable to load AWS credentials from the " + this.credentialsFilePath + " file on the classpath", e);
        }
    }

    public void refresh() {
    }

    public String toString() {
        return getClass().getSimpleName() + "(" + this.credentialsFilePath + ")";
    }
}

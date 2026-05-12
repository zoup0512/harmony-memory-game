package com.amazonaws.services.s3.model;

public class BucketLoggingConfiguration {
    private String destinationBucketName = null;
    private String logFilePrefix = null;

    public BucketLoggingConfiguration(String destinationBucketName, String logFilePrefix) {
        setLogFilePrefix(logFilePrefix);
        setDestinationBucketName(destinationBucketName);
    }

    public boolean isLoggingEnabled() {
        return (this.destinationBucketName == null || this.logFilePrefix == null) ? false : true;
    }

    public String getLogFilePrefix() {
        return this.logFilePrefix;
    }

    public void setLogFilePrefix(String logFilePrefix) {
        if (logFilePrefix == null) {
            logFilePrefix = "";
        }
        this.logFilePrefix = logFilePrefix;
    }

    public String getDestinationBucketName() {
        return this.destinationBucketName;
    }

    public void setDestinationBucketName(String destinationBucketName) {
        this.destinationBucketName = destinationBucketName;
    }

    public String toString() {
        String result = "LoggingConfiguration enabled=" + isLoggingEnabled();
        if (isLoggingEnabled()) {
            return result + ", destinationBucketName=" + getDestinationBucketName() + ", logFilePrefix=" + getLogFilePrefix();
        }
        return result;
    }
}

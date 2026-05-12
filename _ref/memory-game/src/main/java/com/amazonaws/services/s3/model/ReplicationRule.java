package com.amazonaws.services.s3.model;

public class ReplicationRule {
    private ReplicationDestinationConfig destinationConfig;
    private String prefix;
    private String status;

    public String getPrefix() {
        return this.prefix;
    }

    public void setPrefix(String prefix) {
        if (prefix == null) {
            throw new IllegalArgumentException("Prefix cannot be null for a replication rule");
        }
        this.prefix = prefix;
    }

    public ReplicationRule withPrefix(String prefix) {
        setPrefix(prefix);
        return this;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ReplicationRule withStatus(String status) {
        setStatus(status);
        return this;
    }

    public void setStatus(ReplicationRuleStatus status) {
        setStatus(status.getStatus());
    }

    public ReplicationRule withStatus(ReplicationRuleStatus status) {
        setStatus(status.getStatus());
        return this;
    }

    public ReplicationDestinationConfig getDestinationConfig() {
        return this.destinationConfig;
    }

    public void setDestinationConfig(ReplicationDestinationConfig destinationConfig) {
        if (destinationConfig == null) {
            throw new IllegalArgumentException("Destination cannot be null in the replication rule");
        }
        this.destinationConfig = destinationConfig;
    }

    public ReplicationRule withDestinationConfig(ReplicationDestinationConfig destinationConfig) {
        setDestinationConfig(destinationConfig);
        return this;
    }
}

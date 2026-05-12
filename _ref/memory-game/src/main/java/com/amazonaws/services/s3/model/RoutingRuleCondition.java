package com.amazonaws.services.s3.model;

public class RoutingRuleCondition {
    String httpErrorCodeReturnedEquals;
    String keyPrefixEquals;

    public void setKeyPrefixEquals(String keyPrefixEquals) {
        this.keyPrefixEquals = keyPrefixEquals;
    }

    public String getKeyPrefixEquals() {
        return this.keyPrefixEquals;
    }

    public RoutingRuleCondition withKeyPrefixEquals(String keyPrefixEquals) {
        setKeyPrefixEquals(keyPrefixEquals);
        return this;
    }

    public void setHttpErrorCodeReturnedEquals(String httpErrorCodeReturnedEquals) {
        this.httpErrorCodeReturnedEquals = httpErrorCodeReturnedEquals;
    }

    public String getHttpErrorCodeReturnedEquals() {
        return this.httpErrorCodeReturnedEquals;
    }

    public RoutingRuleCondition withHttpErrorCodeReturnedEquals(String httpErrorCodeReturnedEquals) {
        setHttpErrorCodeReturnedEquals(httpErrorCodeReturnedEquals);
        return this;
    }
}

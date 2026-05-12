package com.amazonaws.services.s3.model;

import java.util.LinkedList;
import java.util.List;

public class BucketWebsiteConfiguration {
    private String errorDocument;
    private String indexDocumentSuffix;
    private RedirectRule redirectAllRequestsTo;
    private List<RoutingRule> routingRules = new LinkedList();

    public BucketWebsiteConfiguration(String indexDocumentSuffix) {
        this.indexDocumentSuffix = indexDocumentSuffix;
    }

    public BucketWebsiteConfiguration(String indexDocumentSuffix, String errorDocument) {
        this.indexDocumentSuffix = indexDocumentSuffix;
        this.errorDocument = errorDocument;
    }

    public String getIndexDocumentSuffix() {
        return this.indexDocumentSuffix;
    }

    public void setIndexDocumentSuffix(String indexDocumentSuffix) {
        this.indexDocumentSuffix = indexDocumentSuffix;
    }

    public String getErrorDocument() {
        return this.errorDocument;
    }

    public void setErrorDocument(String errorDocument) {
        this.errorDocument = errorDocument;
    }

    public void setRedirectAllRequestsTo(RedirectRule redirectAllRequestsTo) {
        this.redirectAllRequestsTo = redirectAllRequestsTo;
    }

    public RedirectRule getRedirectAllRequestsTo() {
        return this.redirectAllRequestsTo;
    }

    public BucketWebsiteConfiguration withRedirectAllRequestsTo(RedirectRule redirectAllRequestsTo) {
        this.redirectAllRequestsTo = redirectAllRequestsTo;
        return this;
    }

    public void setRoutingRules(List<RoutingRule> routingRules) {
        this.routingRules = routingRules;
    }

    public List<RoutingRule> getRoutingRules() {
        return this.routingRules;
    }

    public BucketWebsiteConfiguration withRoutingRules(List<RoutingRule> routingRules) {
        this.routingRules = routingRules;
        return this;
    }
}

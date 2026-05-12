package com.amazonaws.services.s3.model;

import java.util.Arrays;
import java.util.List;

public class BucketCrossOriginConfiguration {
    private List<CORSRule> rules;

    public List<CORSRule> getRules() {
        return this.rules;
    }

    public void setRules(List<CORSRule> rules) {
        this.rules = rules;
    }

    public BucketCrossOriginConfiguration withRules(List<CORSRule> rules) {
        setRules(rules);
        return this;
    }

    public BucketCrossOriginConfiguration withRules(CORSRule... rules) {
        setRules(Arrays.asList(rules));
        return this;
    }

    public BucketCrossOriginConfiguration(List<CORSRule> rules) {
        this.rules = rules;
    }
}

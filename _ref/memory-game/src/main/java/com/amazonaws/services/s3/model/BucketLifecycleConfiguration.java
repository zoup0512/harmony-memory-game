package com.amazonaws.services.s3.model;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class BucketLifecycleConfiguration {
    public static final String DISABLED = "Disabled";
    public static final String ENABLED = "Enabled";
    private List<Rule> rules;

    public static class NoncurrentVersionTransition {
        private int days = -1;
        private StorageClass storageClass;

        public void setDays(int expirationInDays) {
            this.days = expirationInDays;
        }

        public int getDays() {
            return this.days;
        }

        public NoncurrentVersionTransition withDays(int expirationInDays) {
            this.days = expirationInDays;
            return this;
        }

        public void setStorageClass(StorageClass storageClass) {
            this.storageClass = storageClass;
        }

        public StorageClass getStorageClass() {
            return this.storageClass;
        }

        public NoncurrentVersionTransition withStorageClass(StorageClass storageClass) {
            this.storageClass = storageClass;
            return this;
        }
    }

    public static class Rule {
        private Date expirationDate;
        private int expirationInDays = -1;
        private String id;
        private int noncurrentVersionExpirationInDays = -1;
        private NoncurrentVersionTransition noncurrentVersionTransition;
        private String prefix;
        private String status;
        private Transition transition;

        public void setId(String id) {
            this.id = id;
        }

        public void setPrefix(String prefix) {
            this.prefix = prefix;
        }

        public void setExpirationInDays(int expirationInDays) {
            this.expirationInDays = expirationInDays;
        }

        public void setNoncurrentVersionExpirationInDays(int value) {
            this.noncurrentVersionExpirationInDays = value;
        }

        public String getId() {
            return this.id;
        }

        public Rule withId(String id) {
            this.id = id;
            return this;
        }

        public String getPrefix() {
            return this.prefix;
        }

        public Rule withPrefix(String prefix) {
            this.prefix = prefix;
            return this;
        }

        public int getExpirationInDays() {
            return this.expirationInDays;
        }

        public Rule withExpirationInDays(int expirationInDays) {
            this.expirationInDays = expirationInDays;
            return this;
        }

        public int getNoncurrentVersionExpirationInDays() {
            return this.noncurrentVersionExpirationInDays;
        }

        public Rule withNoncurrentVersionExpirationInDays(int value) {
            setNoncurrentVersionExpirationInDays(value);
            return this;
        }

        public String getStatus() {
            return this.status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public Rule withStatus(String status) {
            setStatus(status);
            return this;
        }

        public void setExpirationDate(Date expirationDate) {
            this.expirationDate = expirationDate;
        }

        public Date getExpirationDate() {
            return this.expirationDate;
        }

        public Rule withExpirationDate(Date expirationDate) {
            this.expirationDate = expirationDate;
            return this;
        }

        public void setTransition(Transition transition) {
            this.transition = transition;
        }

        public Transition getTransition() {
            return this.transition;
        }

        public Rule withTransition(Transition transition) {
            this.transition = transition;
            return this;
        }

        public void setNoncurrentVersionTransition(NoncurrentVersionTransition value) {
            this.noncurrentVersionTransition = value;
        }

        public NoncurrentVersionTransition getNoncurrentVersionTransition() {
            return this.noncurrentVersionTransition;
        }

        public Rule withNoncurrentVersionTransition(NoncurrentVersionTransition value) {
            setNoncurrentVersionTransition(value);
            return this;
        }
    }

    public static class Transition {
        private Date date;
        private int days = -1;
        private StorageClass storageClass;

        public void setDays(int expirationInDays) {
            this.days = expirationInDays;
        }

        public int getDays() {
            return this.days;
        }

        public Transition withDays(int expirationInDays) {
            this.days = expirationInDays;
            return this;
        }

        public void setStorageClass(StorageClass storageClass) {
            this.storageClass = storageClass;
        }

        public StorageClass getStorageClass() {
            return this.storageClass;
        }

        public Transition withStorageClass(StorageClass storageClass) {
            this.storageClass = storageClass;
            return this;
        }

        public void setDate(Date expirationDate) {
            this.date = expirationDate;
        }

        public Date getDate() {
            return this.date;
        }

        public Transition withDate(Date expirationDate) {
            this.date = expirationDate;
            return this;
        }
    }

    public List<Rule> getRules() {
        return this.rules;
    }

    public void setRules(List<Rule> rules) {
        this.rules = rules;
    }

    public BucketLifecycleConfiguration withRules(List<Rule> rules) {
        setRules(rules);
        return this;
    }

    public BucketLifecycleConfiguration withRules(Rule... rules) {
        setRules(Arrays.asList(rules));
        return this;
    }

    public BucketLifecycleConfiguration(List<Rule> rules) {
        this.rules = rules;
    }
}

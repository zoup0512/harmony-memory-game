package com.amazonaws.retry;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonWebServiceRequest;

public final class RetryPolicy {
    private final BackoffStrategy backoffStrategy;
    private final boolean honorMaxErrorRetryInClientConfig;
    private final int maxErrorRetry;
    private final RetryCondition retryCondition;

    public interface BackoffStrategy {
        long delayBeforeNextRetry(AmazonWebServiceRequest amazonWebServiceRequest, AmazonClientException amazonClientException, int i);
    }

    public interface RetryCondition {
        boolean shouldRetry(AmazonWebServiceRequest amazonWebServiceRequest, AmazonClientException amazonClientException, int i);
    }

    public RetryPolicy(RetryCondition retryCondition, BackoffStrategy backoffStrategy, int maxErrorRetry, boolean honorMaxErrorRetryInClientConfig) {
        if (retryCondition == null) {
            retryCondition = PredefinedRetryPolicies.DEFAULT_RETRY_CONDITION;
        }
        if (backoffStrategy == null) {
            backoffStrategy = PredefinedRetryPolicies.DEFAULT_BACKOFF_STRATEGY;
        }
        if (maxErrorRetry < 0) {
            throw new IllegalArgumentException("Please provide a non-negative value for maxErrorRetry.");
        }
        this.retryCondition = retryCondition;
        this.backoffStrategy = backoffStrategy;
        this.maxErrorRetry = maxErrorRetry;
        this.honorMaxErrorRetryInClientConfig = honorMaxErrorRetryInClientConfig;
    }

    public RetryCondition getRetryCondition() {
        return this.retryCondition;
    }

    public BackoffStrategy getBackoffStrategy() {
        return this.backoffStrategy;
    }

    public int getMaxErrorRetry() {
        return this.maxErrorRetry;
    }

    public boolean isMaxErrorRetryInClientConfigHonored() {
        return this.honorMaxErrorRetryInClientConfig;
    }
}

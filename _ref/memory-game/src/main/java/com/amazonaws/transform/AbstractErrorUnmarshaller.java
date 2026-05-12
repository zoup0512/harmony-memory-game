package com.amazonaws.transform;

import com.amazonaws.AmazonServiceException;

public abstract class AbstractErrorUnmarshaller<T> implements Unmarshaller<AmazonServiceException, T> {
    protected final Class<? extends AmazonServiceException> exceptionClass;

    public AbstractErrorUnmarshaller() {
        this(AmazonServiceException.class);
    }

    public AbstractErrorUnmarshaller(Class<? extends AmazonServiceException> exceptionClass) {
        this.exceptionClass = exceptionClass;
    }

    protected AmazonServiceException newException(String message) throws Exception {
        return (AmazonServiceException) this.exceptionClass.getConstructor(new Class[]{String.class}).newInstance(new Object[]{message});
    }
}

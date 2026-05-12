package com.amazonaws;

public class AmazonWebServiceResponse<T> {
    private ResponseMetadata responseMetadata;
    private T result;

    public T getResult() {
        return this.result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public void setResponseMetadata(ResponseMetadata responseMetadata) {
        this.responseMetadata = responseMetadata;
    }

    public ResponseMetadata getResponseMetadata() {
        return this.responseMetadata;
    }

    public String getRequestId() {
        if (this.responseMetadata == null) {
            return null;
        }
        return this.responseMetadata.getRequestId();
    }
}

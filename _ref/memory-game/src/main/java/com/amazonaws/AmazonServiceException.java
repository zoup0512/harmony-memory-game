package com.amazonaws;

public class AmazonServiceException extends AmazonClientException {
    private static final long serialVersionUID = 1;
    private String errorCode;
    private String errorMessage;
    private ErrorType errorType = ErrorType.Unknown;
    private String requestId;
    private String serviceName;
    private int statusCode;

    public enum ErrorType {
        Client,
        Service,
        Unknown
    }

    public AmazonServiceException(String errorMessage) {
        super(null);
        this.errorMessage = errorMessage;
    }

    public AmazonServiceException(String errorMessage, Exception cause) {
        super(null, cause);
        this.errorMessage = errorMessage;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }

    public ErrorType getErrorType() {
        return this.errorType;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getMessage() {
        return getErrorMessage() + " (Service: " + getServiceName() + "; Status Code: " + getStatusCode() + "; Error Code: " + getErrorCode() + "; Request ID: " + getRequestId() + ")";
    }
}

package com.amazonaws.services.dynamodbv2.model.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.http.JsonErrorResponseHandler.JsonErrorResponse;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughputExceededException;
import com.amazonaws.transform.JsonErrorUnmarshaller;

public class ProvisionedThroughputExceededExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public ProvisionedThroughputExceededExceptionUnmarshaller() {
        super(ProvisionedThroughputExceededException.class);
    }

    public boolean match(JsonErrorResponse error) throws Exception {
        return error.getErrorCode().equals("ProvisionedThroughputExceededException");
    }

    public AmazonServiceException unmarshall(JsonErrorResponse error) throws Exception {
        ProvisionedThroughputExceededException e = (ProvisionedThroughputExceededException) super.unmarshall(error);
        e.setErrorCode("ProvisionedThroughputExceededException");
        return e;
    }
}

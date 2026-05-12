package com.amazonaws.services.dynamodbv2.model.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.http.JsonErrorResponseHandler.JsonErrorResponse;
import com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException;
import com.amazonaws.transform.JsonErrorUnmarshaller;

public class ResourceNotFoundExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public ResourceNotFoundExceptionUnmarshaller() {
        super(ResourceNotFoundException.class);
    }

    public boolean match(JsonErrorResponse error) throws Exception {
        return error.getErrorCode().equals("ResourceNotFoundException");
    }

    public AmazonServiceException unmarshall(JsonErrorResponse error) throws Exception {
        ResourceNotFoundException e = (ResourceNotFoundException) super.unmarshall(error);
        e.setErrorCode("ResourceNotFoundException");
        return e;
    }
}

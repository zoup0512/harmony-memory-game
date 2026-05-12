package com.amazonaws.services.dynamodbv2.model.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.http.JsonErrorResponseHandler.JsonErrorResponse;
import com.amazonaws.services.dynamodbv2.model.InternalServerErrorException;
import com.amazonaws.transform.JsonErrorUnmarshaller;

public class InternalServerErrorExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public InternalServerErrorExceptionUnmarshaller() {
        super(InternalServerErrorException.class);
    }

    public boolean match(JsonErrorResponse error) throws Exception {
        return error.getErrorCode().equals("InternalServerError");
    }

    public AmazonServiceException unmarshall(JsonErrorResponse error) throws Exception {
        InternalServerErrorException e = (InternalServerErrorException) super.unmarshall(error);
        e.setErrorCode("InternalServerError");
        return e;
    }
}

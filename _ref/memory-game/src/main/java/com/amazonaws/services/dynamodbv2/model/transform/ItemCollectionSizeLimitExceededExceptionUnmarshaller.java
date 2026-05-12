package com.amazonaws.services.dynamodbv2.model.transform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.http.JsonErrorResponseHandler.JsonErrorResponse;
import com.amazonaws.services.dynamodbv2.model.ItemCollectionSizeLimitExceededException;
import com.amazonaws.transform.JsonErrorUnmarshaller;

public class ItemCollectionSizeLimitExceededExceptionUnmarshaller extends JsonErrorUnmarshaller {
    public ItemCollectionSizeLimitExceededExceptionUnmarshaller() {
        super(ItemCollectionSizeLimitExceededException.class);
    }

    public boolean match(JsonErrorResponse error) throws Exception {
        return error.getErrorCode().equals("ItemCollectionSizeLimitExceededException");
    }

    public AmazonServiceException unmarshall(JsonErrorResponse error) throws Exception {
        ItemCollectionSizeLimitExceededException e = (ItemCollectionSizeLimitExceededException) super.unmarshall(error);
        e.setErrorCode("ItemCollectionSizeLimitExceededException");
        return e;
    }
}

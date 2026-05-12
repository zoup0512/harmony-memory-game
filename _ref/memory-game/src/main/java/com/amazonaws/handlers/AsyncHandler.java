package com.amazonaws.handlers;

import com.amazonaws.AmazonWebServiceRequest;

public interface AsyncHandler<REQUEST extends AmazonWebServiceRequest, RESULT> {
    void onError(Exception exception);

    void onSuccess(REQUEST request, RESULT result);
}

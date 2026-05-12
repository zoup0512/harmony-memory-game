package com.amazonaws.services.s3.internal;

import com.amazonaws.services.s3.model.DeleteObjectsResult.DeletedObject;
import com.amazonaws.services.s3.model.MultiObjectDeleteException.DeleteError;
import java.util.ArrayList;
import java.util.List;

public class DeleteObjectsResponse {
    private List<DeletedObject> deletedObjects;
    private List<DeleteError> errors;

    public DeleteObjectsResponse() {
        this(new ArrayList(), new ArrayList());
    }

    public DeleteObjectsResponse(List<DeletedObject> deletedObjects, List<DeleteError> errors) {
        this.deletedObjects = deletedObjects;
        this.errors = errors;
    }

    public List<DeletedObject> getDeletedObjects() {
        return this.deletedObjects;
    }

    public void setDeletedObjects(List<DeletedObject> deletedObjects) {
        this.deletedObjects = deletedObjects;
    }

    public List<DeleteError> getErrors() {
        return this.errors;
    }

    public void setErrors(List<DeleteError> errors) {
        this.errors = errors;
    }
}

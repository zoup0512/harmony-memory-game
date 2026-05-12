package com.amazonaws.services.s3.model;

import com.amazonaws.services.s3.model.DeleteObjectsResult.DeletedObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MultiObjectDeleteException extends AmazonS3Exception {
    private static final long serialVersionUID = -2004213552302446866L;
    private final List<DeletedObject> deletedObjects = new ArrayList();
    private final List<DeleteError> errors = new ArrayList();

    public static class DeleteError {
        private String code;
        private String key;
        private String message;
        private String versionId;

        public String getKey() {
            return this.key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getVersionId() {
            return this.versionId;
        }

        public void setVersionId(String versionId) {
            this.versionId = versionId;
        }

        public String getCode() {
            return this.code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return this.message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

    public MultiObjectDeleteException(Collection<DeleteError> errors, Collection<DeletedObject> deletedObjects) {
        super("One or more objects could not be deleted");
        this.deletedObjects.addAll(deletedObjects);
        this.errors.addAll(errors);
    }

    public List<DeletedObject> getDeletedObjects() {
        return this.deletedObjects;
    }

    public List<DeleteError> getErrors() {
        return this.errors;
    }
}

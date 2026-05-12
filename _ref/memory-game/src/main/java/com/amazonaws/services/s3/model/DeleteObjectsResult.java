package com.amazonaws.services.s3.model;

import java.util.ArrayList;
import java.util.List;

public class DeleteObjectsResult {
    private final List<DeletedObject> deletedObjects = new ArrayList();

    public static class DeletedObject {
        private boolean deleteMarker;
        private String deleteMarkerVersionId;
        private String key;
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

        public boolean isDeleteMarker() {
            return this.deleteMarker;
        }

        public void setDeleteMarker(boolean deleteMarker) {
            this.deleteMarker = deleteMarker;
        }

        public String getDeleteMarkerVersionId() {
            return this.deleteMarkerVersionId;
        }

        public void setDeleteMarkerVersionId(String deleteMarkerVersionId) {
            this.deleteMarkerVersionId = deleteMarkerVersionId;
        }
    }

    public DeleteObjectsResult(List<DeletedObject> deletedObjects) {
        this.deletedObjects.addAll(deletedObjects);
    }

    public List<DeletedObject> getDeletedObjects() {
        return this.deletedObjects;
    }
}

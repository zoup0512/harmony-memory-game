package com.amazonaws.services.s3.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class EncryptedInitiateMultipartUploadRequest extends InitiateMultipartUploadRequest implements MaterialsDescriptionProvider {
    private Map<String, String> materialsDescription;

    public EncryptedInitiateMultipartUploadRequest(String bucketName, String key) {
        super(bucketName, key);
    }

    public EncryptedInitiateMultipartUploadRequest(String bucketName, String key, ObjectMetadata objectMetadata) {
        super(bucketName, key, objectMetadata);
    }

    public Map<String, String> getMaterialsDescription() {
        return this.materialsDescription;
    }

    public void setMaterialsDescription(Map<String, String> materialsDescription) {
        Map map;
        if (materialsDescription == null) {
            map = null;
        } else {
            map = Collections.unmodifiableMap(new HashMap(materialsDescription));
        }
        this.materialsDescription = map;
    }

    public EncryptedInitiateMultipartUploadRequest withMaterialsDescription(Map<String, String> materialsDescription) {
        setMaterialsDescription(materialsDescription);
        return this;
    }
}

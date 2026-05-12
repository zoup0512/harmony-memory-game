package com.amazonaws.services.s3.model;

import java.io.File;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class EncryptedPutObjectRequest extends PutObjectRequest implements MaterialsDescriptionProvider {
    private Map<String, String> materialsDescription;

    public EncryptedPutObjectRequest(String bucketName, String key, File file) {
        super(bucketName, key, file);
    }

    public EncryptedPutObjectRequest(String bucketName, String key, String redirectLocation) {
        super(bucketName, key, redirectLocation);
    }

    public EncryptedPutObjectRequest(String bucketName, String key, InputStream input, ObjectMetadata metadata) {
        super(bucketName, key, input, metadata);
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

    public EncryptedPutObjectRequest withMaterialsDescription(Map<String, String> materialsDescription) {
        setMaterialsDescription(materialsDescription);
        return this;
    }
}

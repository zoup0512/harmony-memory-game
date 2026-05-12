package com.amazonaws.auth.policy.resources;

import com.amazonaws.auth.policy.Resource;

public class S3ObjectResource extends Resource {
    public S3ObjectResource(String bucketName, String keyPattern) {
        super("arn:aws:s3:::" + bucketName + "/" + keyPattern);
    }
}

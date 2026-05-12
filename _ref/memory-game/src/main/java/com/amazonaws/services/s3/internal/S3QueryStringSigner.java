package com.amazonaws.services.s3.internal;

import com.amazonaws.AmazonClientException;
import com.amazonaws.Request;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSSessionCredentials;
import com.amazonaws.auth.AbstractAWSSigner;
import com.amazonaws.auth.SigningAlgorithm;
import com.amazonaws.services.s3.Headers;
import java.util.Date;

public class S3QueryStringSigner extends AbstractAWSSigner {
    private final Date expiration;
    private final String httpVerb;
    private final String resourcePath;

    public S3QueryStringSigner(String httpVerb, String resourcePath, Date expiration) {
        this.httpVerb = httpVerb;
        this.resourcePath = resourcePath;
        this.expiration = expiration;
        if (resourcePath == null) {
            throw new IllegalArgumentException("Parameter resourcePath is empty");
        }
    }

    public void sign(Request<?> request, AWSCredentials credentials) throws AmazonClientException {
        AWSCredentials sanitizedCredentials = sanitizeCredentials(credentials);
        if (sanitizedCredentials instanceof AWSSessionCredentials) {
            addSessionCredentials(request, (AWSSessionCredentials) sanitizedCredentials);
        }
        String expirationInSeconds = Long.toString(this.expiration.getTime() / 1000);
        String signature = super.signAndBase64Encode(RestUtils.makeS3CanonicalString(this.httpVerb, this.resourcePath, request, expirationInSeconds), sanitizedCredentials.getAWSSecretKey(), SigningAlgorithm.HmacSHA1);
        request.addParameter("AWSAccessKeyId", sanitizedCredentials.getAWSAccessKeyId());
        request.addParameter("Expires", expirationInSeconds);
        request.addParameter("Signature", signature);
    }

    protected void addSessionCredentials(Request<?> request, AWSSessionCredentials credentials) {
        request.addParameter(Headers.SECURITY_TOKEN, credentials.getSessionToken());
    }
}

package com.amazonaws.services.s3.internal;

import com.amazonaws.Request;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSSessionCredentials;
import com.amazonaws.auth.AbstractAWSSigner;
import com.amazonaws.auth.SigningAlgorithm;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.util.HttpUtils;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class S3Signer extends AbstractAWSSigner {
    private static final Log log = LogFactory.getLog(S3Signer.class);
    private final String httpVerb;
    private final String resourcePath;

    public S3Signer() {
        this.httpVerb = null;
        this.resourcePath = null;
    }

    public S3Signer(String httpVerb, String resourcePath) {
        this.httpVerb = httpVerb;
        this.resourcePath = resourcePath;
        if (resourcePath == null) {
            throw new IllegalArgumentException("Parameter resourcePath is empty");
        }
    }

    void sign(Request<?> request, AWSCredentials credentials, Date overrideDate) {
        if (this.resourcePath == null) {
            throw new UnsupportedOperationException("Cannot sign a request using a dummy S3Signer instance with no resource path");
        } else if (credentials == null || credentials.getAWSSecretKey() == null) {
            log.debug("Canonical string will not be signed, as no AWS Secret Key was provided");
        } else {
            AWSCredentials sanitizedCredentials = sanitizeCredentials(credentials);
            if (sanitizedCredentials instanceof AWSSessionCredentials) {
                addSessionCredentials(request, (AWSSessionCredentials) sanitizedCredentials);
            }
            String encodedResourcePath = HttpUtils.appendUri(request.getEndpoint().getPath(), this.resourcePath, true);
            Date date = getSignatureDate(getTimeOffset(request));
            if (overrideDate != null) {
                date = overrideDate;
            }
            request.addHeader("Date", ServiceUtils.formatRfc822Date(date));
            String canonicalString = RestUtils.makeS3CanonicalString(this.httpVerb, encodedResourcePath, request, null);
            log.debug("Calculated string to sign:\n\"" + canonicalString + "\"");
            request.addHeader("Authorization", "AWS " + sanitizedCredentials.getAWSAccessKeyId() + ":" + super.signAndBase64Encode(canonicalString, sanitizedCredentials.getAWSSecretKey(), SigningAlgorithm.HmacSHA1));
        }
    }

    public void sign(Request<?> request, AWSCredentials credentials) {
        sign(request, credentials, null);
    }

    protected void addSessionCredentials(Request<?> request, AWSSessionCredentials credentials) {
        request.addHeader(Headers.SECURITY_TOKEN, credentials.getSessionToken());
    }
}

package com.amazonaws.services.s3.model;

import com.amazonaws.AmazonWebServiceRequest;

public class ResponseHeaderOverrides extends AmazonWebServiceRequest {
    private static final String[] PARAMETER_ORDER = new String[]{RESPONSE_HEADER_CACHE_CONTROL, RESPONSE_HEADER_CONTENT_DISPOSITION, RESPONSE_HEADER_CONTENT_ENCODING, RESPONSE_HEADER_CONTENT_LANGUAGE, RESPONSE_HEADER_CONTENT_TYPE, RESPONSE_HEADER_EXPIRES};
    public static final String RESPONSE_HEADER_CACHE_CONTROL = "response-cache-control";
    public static final String RESPONSE_HEADER_CONTENT_DISPOSITION = "response-content-disposition";
    public static final String RESPONSE_HEADER_CONTENT_ENCODING = "response-content-encoding";
    public static final String RESPONSE_HEADER_CONTENT_LANGUAGE = "response-content-language";
    public static final String RESPONSE_HEADER_CONTENT_TYPE = "response-content-type";
    public static final String RESPONSE_HEADER_EXPIRES = "response-expires";
    private String cacheControl;
    private String contentDisposition;
    private String contentEncoding;
    private String contentLanguage;
    private String contentType;
    private String expires;

    public String getContentType() {
        return this.contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public ResponseHeaderOverrides withContentType(String contentType) {
        setContentType(contentType);
        return this;
    }

    public String getContentLanguage() {
        return this.contentLanguage;
    }

    public void setContentLanguage(String contentLanguage) {
        this.contentLanguage = contentLanguage;
    }

    public ResponseHeaderOverrides withContentLanguage(String contentLanguage) {
        setContentLanguage(contentLanguage);
        return this;
    }

    public String getExpires() {
        return this.expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public ResponseHeaderOverrides withExpires(String expires) {
        setExpires(expires);
        return this;
    }

    public String getCacheControl() {
        return this.cacheControl;
    }

    public void setCacheControl(String cacheControl) {
        this.cacheControl = cacheControl;
    }

    public ResponseHeaderOverrides withCacheControl(String cacheControl) {
        setCacheControl(cacheControl);
        return this;
    }

    public String getContentDisposition() {
        return this.contentDisposition;
    }

    public void setContentDisposition(String contentDisposition) {
        this.contentDisposition = contentDisposition;
    }

    public ResponseHeaderOverrides withContentDisposition(String contentDisposition) {
        setContentDisposition(contentDisposition);
        return this;
    }

    public String getContentEncoding() {
        return this.contentEncoding;
    }

    public void setContentEncoding(String contentEncoding) {
        this.contentEncoding = contentEncoding;
    }

    public ResponseHeaderOverrides withContentEncoding(String contentEncoding) {
        setContentEncoding(contentEncoding);
        return this;
    }
}

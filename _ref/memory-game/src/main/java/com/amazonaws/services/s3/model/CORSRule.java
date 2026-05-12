package com.amazonaws.services.s3.model;

import io.fabric.sdk.android.services.network.HttpRequest;
import java.util.Arrays;
import java.util.List;

public class CORSRule {
    private List<String> allowedHeaders;
    private List<AllowedMethods> allowedMethods;
    private List<String> allowedOrigins;
    private List<String> exposedHeaders;
    private String id;
    private int maxAgeSeconds;

    public enum AllowedMethods {
        GET(HttpRequest.METHOD_GET),
        PUT(HttpRequest.METHOD_PUT),
        HEAD(HttpRequest.METHOD_HEAD),
        POST(HttpRequest.METHOD_POST),
        DELETE(HttpRequest.METHOD_DELETE);
        
        private final String AllowedMethod;

        private AllowedMethods(String AllowedMethod) {
            this.AllowedMethod = AllowedMethod;
        }

        public String toString() {
            return this.AllowedMethod;
        }

        public static AllowedMethods fromValue(String allowedMethod) throws IllegalArgumentException {
            for (AllowedMethods method : values()) {
                String methodString = method.toString();
                if ((methodString == null && allowedMethod == null) || (methodString != null && methodString.equals(allowedMethod))) {
                    return method;
                }
            }
            throw new IllegalArgumentException("Cannot create enum from " + allowedMethod + " value!");
        }
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return this.id;
    }

    public CORSRule withId(String id) {
        this.id = id;
        return this;
    }

    public void setAllowedMethods(List<AllowedMethods> allowedMethods) {
        this.allowedMethods = allowedMethods;
    }

    public void setAllowedMethods(AllowedMethods... allowedMethods) {
        this.allowedMethods = Arrays.asList(allowedMethods);
    }

    public List<AllowedMethods> getAllowedMethods() {
        return this.allowedMethods;
    }

    public CORSRule withAllowedMethods(List<AllowedMethods> allowedMethods) {
        this.allowedMethods = allowedMethods;
        return this;
    }

    public void setAllowedOrigins(List<String> allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
    }

    public void setAllowedOrigins(String... allowedOrigins) {
        this.allowedOrigins = Arrays.asList(allowedOrigins);
    }

    public List<String> getAllowedOrigins() {
        return this.allowedOrigins;
    }

    public CORSRule withAllowedOrigins(List<String> allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
        return this;
    }

    public void setMaxAgeSeconds(int maxAgeSeconds) {
        this.maxAgeSeconds = maxAgeSeconds;
    }

    public int getMaxAgeSeconds() {
        return this.maxAgeSeconds;
    }

    public CORSRule withMaxAgeSeconds(int maxAgeSeconds) {
        this.maxAgeSeconds = maxAgeSeconds;
        return this;
    }

    public void setExposedHeaders(List<String> exposedHeaders) {
        this.exposedHeaders = exposedHeaders;
    }

    public void setExposedHeaders(String... exposedHeaders) {
        this.exposedHeaders = Arrays.asList(exposedHeaders);
    }

    public List<String> getExposedHeaders() {
        return this.exposedHeaders;
    }

    public CORSRule withExposedHeaders(List<String> exposedHeaders) {
        this.exposedHeaders = exposedHeaders;
        return this;
    }

    public void setAllowedHeaders(List<String> allowedHeaders) {
        this.allowedHeaders = allowedHeaders;
    }

    public void setAllowedHeaders(String... allowedHeaders) {
        this.allowedHeaders = Arrays.asList(allowedHeaders);
    }

    public List<String> getAllowedHeaders() {
        return this.allowedHeaders;
    }

    public CORSRule withAllowedHeaders(List<String> allowedHeaders) {
        this.allowedHeaders = allowedHeaders;
        return this;
    }
}

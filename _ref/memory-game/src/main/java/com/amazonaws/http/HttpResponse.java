package com.amazonaws.http;

import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;

public class HttpResponse {
    private InputStream content;
    private final Map<String, String> headers;
    private final InputStream rawContent;
    private final int statusCode;
    private final String statusText;

    public static class Builder {
        private InputStream content;
        private final Map<String, String> headers = new HashMap();
        private int statusCode;
        private String statusText;

        public Builder statusText(String statusText) {
            this.statusText = statusText;
            return this;
        }

        public Builder statusCode(int statusCode) {
            this.statusCode = statusCode;
            return this;
        }

        public Builder content(InputStream content) {
            this.content = content;
            return this;
        }

        public Builder header(String key, String value) {
            this.headers.put(key, value);
            return this;
        }

        public HttpResponse build() {
            return new HttpResponse(this.statusText, this.statusCode, Collections.unmodifiableMap(this.headers), this.content);
        }
    }

    private HttpResponse(String statusText, int statusCode, Map<String, String> headers, InputStream rawContent) {
        this.statusText = statusText;
        this.statusCode = statusCode;
        this.headers = headers;
        this.rawContent = rawContent;
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }

    public InputStream getContent() throws IOException {
        if (this.content == null) {
            synchronized (this) {
                if (this.rawContent == null || !HttpRequest.ENCODING_GZIP.equals(this.headers.get("Content-Encoding"))) {
                    this.content = this.rawContent;
                } else {
                    this.content = new GZIPInputStream(this.rawContent);
                }
            }
        }
        return this.content;
    }

    public InputStream getRawContent() throws IOException {
        return this.rawContent;
    }

    public String getStatusText() {
        return this.statusText;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public static Builder builder() {
        return new Builder();
    }
}

package com.amazonaws.http;

import com.amazonaws.util.StringUtils;
import java.io.InputStream;
import java.net.URI;
import java.util.Collections;
import java.util.Map;

public class HttpRequest {
    private final InputStream content;
    private final Map<String, String> headers;
    private final String method;
    private URI uri;

    public HttpRequest(String method, URI uri) {
        this(method, uri, null, null);
    }

    public HttpRequest(String method, URI uri, Map<String, String> headers, InputStream content) {
        Map map;
        this.method = StringUtils.upperCase(method);
        this.uri = uri;
        if (headers == null) {
            map = Collections.EMPTY_MAP;
        } else {
            map = Collections.unmodifiableMap(headers);
        }
        this.headers = map;
        this.content = content;
    }

    public String getMethod() {
        return this.method;
    }

    public URI getUri() {
        return this.uri;
    }

    void setUri(URI uri) {
        this.uri = uri;
    }

    public Map<String, String> getHeaders() {
        return this.headers;
    }

    public InputStream getContent() {
        return this.content;
    }

    public long getContentLength() {
        if (this.headers == null) {
            return 0;
        }
        String len = (String) this.headers.get("Content-Length");
        if (len == null || len.isEmpty()) {
            return 0;
        }
        return Long.valueOf(len).longValue();
    }
}

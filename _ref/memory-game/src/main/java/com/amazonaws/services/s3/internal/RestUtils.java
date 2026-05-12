package com.amazonaws.services.s3.internal;

import com.amazonaws.Request;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.model.ResponseHeaderOverrides;
import com.amazonaws.util.StringUtils;
import com.facebook.places.model.PlaceFields;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

public class RestUtils {
    private static final List<String> SIGNED_PARAMETERS = Arrays.asList(new String[]{"acl", "torrent", "logging", "location", "policy", "requestPayment", "versioning", "versions", "versionId", "notification", "uploadId", "uploads", "partNumber", PlaceFields.WEBSITE, "delete", "lifecycle", "tagging", "cors", "restore", "accelerate", ResponseHeaderOverrides.RESPONSE_HEADER_CACHE_CONTROL, ResponseHeaderOverrides.RESPONSE_HEADER_CONTENT_DISPOSITION, ResponseHeaderOverrides.RESPONSE_HEADER_CONTENT_ENCODING, ResponseHeaderOverrides.RESPONSE_HEADER_CONTENT_LANGUAGE, ResponseHeaderOverrides.RESPONSE_HEADER_CONTENT_TYPE, ResponseHeaderOverrides.RESPONSE_HEADER_EXPIRES});

    public static <T> String makeS3CanonicalString(String method, String resource, Request<T> request, String expires) {
        String key;
        String value;
        StringBuilder buf = new StringBuilder();
        buf.append(method + "\n");
        Map<String, String> headersMap = request.getHeaders();
        SortedMap<String, String> interestingHeaders = new TreeMap();
        if (headersMap != null && headersMap.size() > 0) {
            for (Entry<String, String> entry : headersMap.entrySet()) {
                key = (String) entry.getKey();
                value = (String) entry.getValue();
                if (key != null) {
                    String lk = StringUtils.lowerCase(key.toString());
                    if (lk.equals("content-type") || lk.equals("content-md5") || lk.equals("date") || lk.startsWith(Headers.AMAZON_PREFIX)) {
                        interestingHeaders.put(lk, value);
                    }
                }
            }
        }
        if (interestingHeaders.containsKey(Headers.S3_ALTERNATE_DATE)) {
            interestingHeaders.put("date", "");
        }
        if (expires != null) {
            interestingHeaders.put("date", expires);
        }
        if (!interestingHeaders.containsKey("content-type")) {
            interestingHeaders.put("content-type", "");
        }
        if (!interestingHeaders.containsKey("content-md5")) {
            interestingHeaders.put("content-md5", "");
        }
        for (Entry<String, String> parameter : request.getParameters().entrySet()) {
            if (((String) parameter.getKey()).startsWith(Headers.AMAZON_PREFIX)) {
                interestingHeaders.put(parameter.getKey(), parameter.getValue());
            }
        }
        for (Entry<String, String> entry2 : interestingHeaders.entrySet()) {
            key = (String) entry2.getKey();
            value = (String) entry2.getValue();
            if (key.startsWith(Headers.AMAZON_PREFIX)) {
                buf.append(key).append(':');
                if (value != null) {
                    buf.append(value);
                }
            } else if (value != null) {
                buf.append(value);
            }
            buf.append("\n");
        }
        buf.append(resource);
        String[] parameterNames = (String[]) request.getParameters().keySet().toArray(new String[request.getParameters().size()]);
        Arrays.sort(parameterNames);
        char separator = '?';
        for (String parameterName : parameterNames) {
            if (SIGNED_PARAMETERS.contains(parameterName)) {
                buf.append(separator);
                buf.append(parameterName);
                String parameterValue = (String) request.getParameters().get(parameterName);
                if (parameterValue != null) {
                    buf.append("=").append(parameterValue);
                }
                separator = '&';
            }
        }
        return buf.toString();
    }
}

package com.amazonaws.http;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.AmazonServiceException.ErrorType;
import com.amazonaws.transform.JsonErrorUnmarshaller;
import com.amazonaws.util.StringUtils;
import com.amazonaws.util.json.JsonUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class JsonErrorResponseHandler implements HttpResponseHandler<AmazonServiceException> {
    private static final String X_AMZN_ERROR_TYPE = "x-amzn-ErrorType";
    private final List<? extends JsonErrorUnmarshaller> unmarshallerList;

    public static class JsonErrorResponse {
        private final String errorCode;
        private final Map<String, String> map;
        private final String message = get("message");
        private final int statusCode;

        private JsonErrorResponse(int statusCode, String errorCode, Map<String, String> map) {
            this.statusCode = statusCode;
            this.errorCode = errorCode;
            this.map = map;
        }

        public int getStatusCode() {
            return this.statusCode;
        }

        public String getErrorCode() {
            return this.errorCode;
        }

        public String getMessage() {
            return this.message;
        }

        public String get(String key) {
            if (key == null || key.length() == 0) {
                return null;
            }
            String firstLetterLowercaseKey = StringUtils.lowerCase(key.substring(0, 1)) + key.substring(1);
            String firstLetterUppercaseKey = StringUtils.upperCase(key.substring(0, 1)) + key.substring(1);
            String value = "";
            if (this.map.containsKey(firstLetterUppercaseKey)) {
                return (String) this.map.get(firstLetterUppercaseKey);
            }
            if (this.map.containsKey(firstLetterLowercaseKey)) {
                return (String) this.map.get(firstLetterLowercaseKey);
            }
            return value;
        }

        public static JsonErrorResponse fromResponse(HttpResponse response) throws IOException {
            int statusCode = response.getStatusCode();
            Map<String, String> map = JsonUtils.jsonToMap(new BufferedReader(new InputStreamReader(response.getContent(), StringUtils.UTF8)));
            String errorCode = (String) response.getHeaders().get(JsonErrorResponseHandler.X_AMZN_ERROR_TYPE);
            if (errorCode != null) {
                int separator = errorCode.indexOf(58);
                if (separator != -1) {
                    errorCode = errorCode.substring(0, separator);
                }
            } else if (map.containsKey("__type")) {
                String type = (String) map.get("__type");
                errorCode = type.substring(type.lastIndexOf("#") + 1);
            }
            return new JsonErrorResponse(statusCode, errorCode, map);
        }
    }

    public JsonErrorResponseHandler(List<? extends JsonErrorUnmarshaller> exceptionUnmarshallers) {
        this.unmarshallerList = exceptionUnmarshallers;
    }

    public AmazonServiceException handle(HttpResponse response) throws Exception {
        try {
            JsonErrorResponse error = JsonErrorResponse.fromResponse(response);
            AmazonServiceException ase = runErrorUnmarshallers(error);
            if (ase == null) {
                return null;
            }
            ase.setStatusCode(response.getStatusCode());
            if (response.getStatusCode() < 500) {
                ase.setErrorType(ErrorType.Client);
            } else {
                ase.setErrorType(ErrorType.Service);
            }
            ase.setErrorCode(error.getErrorCode());
            for (Entry<String, String> headerEntry : response.getHeaders().entrySet()) {
                if (((String) headerEntry.getKey()).equalsIgnoreCase("X-Amzn-RequestId")) {
                    ase.setRequestId((String) headerEntry.getValue());
                }
            }
            return ase;
        } catch (IOException e) {
            throw new AmazonClientException("Unable to parse error response", e);
        }
    }

    private AmazonServiceException runErrorUnmarshallers(JsonErrorResponse error) throws Exception {
        for (JsonErrorUnmarshaller unmarshaller : this.unmarshallerList) {
            if (unmarshaller.match(error)) {
                return unmarshaller.unmarshall(error);
            }
        }
        return null;
    }

    public boolean needsConnectionLeftOpen() {
        return false;
    }
}

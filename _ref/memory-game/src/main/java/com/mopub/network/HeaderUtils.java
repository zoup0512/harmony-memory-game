package com.mopub.network;

import android.support.annotation.Nullable;
import com.facebook.appevents.AppEventsConstants;
import com.mopub.common.util.ResponseHeader;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.HttpResponse;

public class HeaderUtils {
    @Nullable
    public static String extractHeader(Map<String, String> map, ResponseHeader responseHeader) {
        return (String) map.get(responseHeader.getKey());
    }

    public static Integer extractIntegerHeader(Map<String, String> map, ResponseHeader responseHeader) {
        return formatIntHeader(extractHeader((Map) map, responseHeader));
    }

    public static boolean extractBooleanHeader(Map<String, String> map, ResponseHeader responseHeader, boolean z) {
        return formatBooleanHeader(extractHeader((Map) map, responseHeader), z);
    }

    public static Integer extractPercentHeader(Map<String, String> map, ResponseHeader responseHeader) {
        return formatPercentHeader(extractHeader((Map) map, responseHeader));
    }

    @Nullable
    public static String extractPercentHeaderString(Map<String, String> map, ResponseHeader responseHeader) {
        Integer extractPercentHeader = extractPercentHeader(map, responseHeader);
        return extractPercentHeader != null ? extractPercentHeader.toString() : null;
    }

    public static String extractHeader(HttpResponse httpResponse, ResponseHeader responseHeader) {
        Header firstHeader = httpResponse.getFirstHeader(responseHeader.getKey());
        return firstHeader != null ? firstHeader.getValue() : null;
    }

    public static boolean extractBooleanHeader(HttpResponse httpResponse, ResponseHeader responseHeader, boolean z) {
        return formatBooleanHeader(extractHeader(httpResponse, responseHeader), z);
    }

    public static Integer extractIntegerHeader(HttpResponse httpResponse, ResponseHeader responseHeader) {
        return formatIntHeader(extractHeader(httpResponse, responseHeader));
    }

    public static int extractIntHeader(HttpResponse httpResponse, ResponseHeader responseHeader, int i) {
        Integer extractIntegerHeader = extractIntegerHeader(httpResponse, responseHeader);
        return extractIntegerHeader == null ? i : extractIntegerHeader.intValue();
    }

    private static boolean formatBooleanHeader(@Nullable String str, boolean z) {
        return str == null ? z : str.equals(AppEventsConstants.EVENT_PARAM_VALUE_YES);
    }

    private static Integer formatIntHeader(String str) {
        Integer num = null;
        NumberFormat instance = NumberFormat.getInstance(Locale.US);
        instance.setParseIntegerOnly(true);
        if (str != null) {
            try {
                num = Integer.valueOf(instance.parse(str.trim()).intValue());
            } catch (Exception e) {
            }
        }
        return num;
    }

    @Nullable
    private static Integer formatPercentHeader(@Nullable String str) {
        if (str == null) {
            return null;
        }
        Integer formatIntHeader = formatIntHeader(str.replace("%", ""));
        if (formatIntHeader == null || formatIntHeader.intValue() < 0 || formatIntHeader.intValue() > 100) {
            return null;
        }
        return formatIntHeader;
    }
}

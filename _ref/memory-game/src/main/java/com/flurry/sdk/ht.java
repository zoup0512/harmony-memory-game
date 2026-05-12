package com.flurry.sdk;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ht {
    private static final String a = ht.class.getSimpleName();

    public static Map<String, List<String>> a(String str) {
        km.a(3, a, "Parsing referrer map");
        if (str == null) {
            return Collections.emptyMap();
        }
        Map<String, List<String>> hashMap = new HashMap();
        for (String str2 : str.split("&")) {
            String str22;
            String[] split = str22.split("=");
            if (split.length != 2) {
                km.a(5, a, "Invalid referrer Element: " + str22 + " in referrer tag " + str);
            } else {
                str22 = URLDecoder.decode(split[0]);
                String decode = URLDecoder.decode(split[1]);
                if (hashMap.get(str22) == null) {
                    hashMap.put(str22, new ArrayList());
                }
                ((List) hashMap.get(str22)).add(decode);
            }
        }
        for (Entry entry : hashMap.entrySet()) {
            km.a(3, a, "entry: " + ((String) entry.getKey()) + "=" + entry.getValue());
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (hashMap.get("utm_source") == null) {
            stringBuilder.append("Campaign Source is missing.\n");
        }
        if (hashMap.get("utm_medium") == null) {
            stringBuilder.append("Campaign Medium is missing.\n");
        }
        if (hashMap.get("utm_campaign") == null) {
            stringBuilder.append("Campaign Name is missing.\n");
        }
        if (stringBuilder.length() > 0) {
            km.a(5, a, "Detected missing referrer keys : " + stringBuilder.toString());
        }
        return hashMap;
    }
}

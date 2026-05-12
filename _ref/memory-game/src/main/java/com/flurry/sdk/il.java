package com.flurry.sdk;

import bolts.MeasurementEvent;
import java.util.Map;

public class il extends kr {
    private static final String a = il.class.getSimpleName();

    public final String a(String str, Map<String, String> map) {
        CharSequence a = a(str);
        while (a != null) {
            String valueOf;
            if (kr.a("timestamp_epoch_millis", a)) {
                valueOf = String.valueOf(System.currentTimeMillis());
                km.a(3, a, "Replacing param timestamp_epoch_millis with: " + valueOf);
                valueOf = str.replace(a, ly.c(valueOf));
            } else if (kr.a("session_duration_millis", a)) {
                jk.a();
                valueOf = Long.toString(jk.f());
                km.a(3, a, "Replacing param session_duration_millis with: " + valueOf);
                valueOf = str.replace(a, ly.c(valueOf));
            } else if (kr.a("fg_timespent_millis", a)) {
                jk.a();
                valueOf = Long.toString(jk.f());
                km.a(3, a, "Replacing param fg_timespent_millis with: " + valueOf);
                valueOf = str.replace(a, ly.c(valueOf));
            } else if (kr.a("install_referrer", a)) {
                valueOf = new hs().b();
                if (valueOf == null) {
                    valueOf = "";
                }
                km.a(3, a, "Replacing param install_referrer with: " + valueOf);
                valueOf = str.replace(a, ly.c(valueOf));
            } else if (kr.a("geo_latitude", a)) {
                r2 = jp.a().g();
                valueOf = "";
                if (r2 != null) {
                    valueOf = valueOf + ly.a(r2.getLatitude(), jp.d());
                }
                km.a(3, a, "Replacing param geo_latitude with: " + valueOf);
                valueOf = str.replace(a, ly.c(valueOf));
            } else if (kr.a("geo_longitude", a)) {
                r2 = jp.a().g();
                valueOf = "";
                if (r2 != null) {
                    valueOf = valueOf + ly.a(r2.getLongitude(), jp.d());
                }
                km.a(3, a, "Replacing param geo_longitude with: " + valueOf);
                valueOf = str.replace(a, ly.c(valueOf));
            } else if (kr.a("publisher_user_id", a)) {
                valueOf = (String) lp.a().a("UserId");
                km.a(3, a, "Replacing param publisher_user_id with: " + valueOf);
                valueOf = str.replace(a, ly.c(valueOf));
            } else if (kr.a(MeasurementEvent.MEASUREMENT_EVENT_NAME_KEY, a)) {
                if (map.containsKey(MeasurementEvent.MEASUREMENT_EVENT_NAME_KEY)) {
                    km.a(3, a, "Replacing param event_name with: " + ((String) map.get(MeasurementEvent.MEASUREMENT_EVENT_NAME_KEY)));
                    valueOf = str.replace(a, ly.c((String) map.get(MeasurementEvent.MEASUREMENT_EVENT_NAME_KEY)));
                } else {
                    km.a(3, a, "Replacing param event_name with empty string");
                    valueOf = str.replace(a, "");
                }
            } else if (!kr.a("event_time_millis", a)) {
                km.a(3, a, "Unknown param: " + a);
                valueOf = str.replace(a, "");
            } else if (map.containsKey("event_time_millis")) {
                km.a(3, a, "Replacing param event_time_millis with: " + ((String) map.get("event_time_millis")));
                valueOf = str.replace(a, ly.c((String) map.get("event_time_millis")));
            } else {
                km.a(3, a, "Replacing param event_time_millis with empty string");
                valueOf = str.replace(a, "");
            }
            a = a(valueOf);
            str = valueOf;
        }
        return str;
    }
}

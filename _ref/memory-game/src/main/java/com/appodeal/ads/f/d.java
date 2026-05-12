package com.appodeal.ads.f;

import io.branch.referral.Branch;
import java.util.Map;
import java.util.TreeMap;
import org.json.JSONObject;

public class d {
    static final Map<String, c> a = new TreeMap();
    private static c b;

    public static c a(String str) {
        if (a.containsKey(str)) {
            return (c) a.get(str);
        }
        b();
        return b;
    }

    private static void b() {
        if (b == null) {
            b = new c(-1, Branch.REFERRAL_BUCKET_DEFAULT, new JSONObject());
            a.put(Branch.REFERRAL_BUCKET_DEFAULT, b);
        }
    }

    public static c a() {
        return a(Branch.REFERRAL_BUCKET_DEFAULT);
    }
}

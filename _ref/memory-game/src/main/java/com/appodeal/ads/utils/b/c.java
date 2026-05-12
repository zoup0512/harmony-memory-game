package com.appodeal.ads.utils.b;

import android.content.SharedPreferences;
import android.support.annotation.VisibleForTesting;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.m;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONObject;

public class c {
    @VisibleForTesting
    final int a = 2;
    @VisibleForTesting
    final int b = 10;
    private SharedPreferences c;

    @VisibleForTesting
    SharedPreferences a() {
        if (this.c == null && Appodeal.b != null) {
            this.c = Appodeal.b.getSharedPreferences("exceptions", 0);
        }
        return this.c;
    }

    public synchronized void a(String str) {
        if (!(a() == null || str == null || str.length() <= 0)) {
            Collection a = a(true);
            if (a.size() >= 10) {
                a((List) a, str);
            }
            a.add(str);
            a().edit().putString("exceptions", a(a, ":::")).apply();
        }
    }

    private synchronized void a(List<String> list, String str) {
        Object obj = null;
        synchronized (this) {
            try {
                Iterator it = list.iterator();
                while (it.hasNext() && obj == null) {
                    Object obj2;
                    if (new JSONObject((String) it.next()).optBoolean("fatal")) {
                        obj2 = obj;
                    } else {
                        it.remove();
                        obj2 = 1;
                    }
                    obj = obj2;
                }
                if (obj == null && new JSONObject(str).optBoolean("fatal")) {
                    list.remove(0);
                }
            } catch (Exception e) {
            }
        }
    }

    @VisibleForTesting
    synchronized void a(List<String> list) {
        if (!(a() == null || list == null || list.size() <= 0)) {
            Collection a = a(true);
            if (a.removeAll(list)) {
                a().edit().putString("exceptions", a(a, ":::")).apply();
            }
        }
    }

    public List<String> a(boolean z) {
        if (a() == null) {
            return new ArrayList();
        }
        String string = a().getString("exceptions", "");
        if (string.length() == 0) {
            return new ArrayList();
        }
        return a(string, z ? 0 : 2, ":::");
    }

    @VisibleForTesting
    List<String> a(String str, int i, String str2) {
        Matcher matcher = Pattern.compile(str2, 2).matcher(str);
        int i2 = 0;
        List arrayList = new ArrayList(i);
        while (matcher.find() && (arrayList.size() < i || i == 0)) {
            arrayList.add(str.subSequence(i2, matcher.start()).toString());
            i2 = matcher.end();
        }
        if (i == 0 && i2 > 0) {
            arrayList.add(str.subSequence(i2, str.length()).toString());
        }
        if (str.length() > 0 && arrayList.size() == 0) {
            arrayList.add(str);
        }
        return arrayList;
    }

    static String a(Collection<String> collection, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        for (String append : collection) {
            stringBuilder.append(append);
            int i2 = i + 1;
            if (i2 < collection.size()) {
                stringBuilder.append(str);
            }
            i = i2;
        }
        return stringBuilder.toString();
    }

    public boolean b() {
        if (a() == null || a().getString("exceptions", "").length() == 0) {
            return true;
        }
        return false;
    }

    public boolean c() {
        return !g().equals(m.b);
    }

    public boolean d() {
        return g().equals(m.d);
    }

    public void e() {
        if (a() != null) {
            a().edit().remove("exceptions").apply();
            a().edit().remove("active").apply();
        }
    }

    @VisibleForTesting
    void b(String str) {
        if (a() != null) {
            long time;
            Date date;
            Date date2 = null;
            try {
                date2 = new SimpleDateFormat("EEE, dd MMM yyyy hh:mm:ss z", Locale.ENGLISH).parse(str);
                Date date3 = date2;
                time = date2.getTime();
                date = date3;
            } catch (Exception e) {
                date = date2;
                time = 0;
            }
            if (date == null) {
                time = (Long.parseLong(str) * 1000) + System.currentTimeMillis();
            }
            if (time != 0) {
                a().edit().putLong("retry", time).apply();
            }
        }
    }

    public boolean f() {
        if (a() == null || a().getLong("retry", System.currentTimeMillis()) <= System.currentTimeMillis()) {
            return true;
        }
        return false;
    }

    public String g() {
        try {
            if (a() != null) {
                return a().getString("active", m.b);
            }
        } catch (Exception e) {
            e();
        }
        return m.b;
    }

    public void c(String str) {
        if (a() != null) {
            a().edit().putString("active", str).apply();
        }
    }
}

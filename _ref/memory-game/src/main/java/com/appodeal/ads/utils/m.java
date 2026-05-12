package com.appodeal.ads.utils;

import android.os.AsyncTask;
import android.os.Build.VERSION;
import com.appodeal.ads.Appodeal;
import com.cmcm.adsdk.nativead.RequestResultLogger.Model;
import java.util.ArrayList;
import org.json.JSONObject;

public class m {
    int a;
    int b;
    int c;
    int d;
    int e;
    int f;
    ArrayList<Long> g;
    ArrayList<Long> h;
    private int i;
    private int j;
    private int k;
    private String l;
    private String m;
    private String n;
    private String o;

    public m(JSONObject jSONObject) {
        this.a = jSONObject.optInt("click_time");
        JSONObject optJSONObject = jSONObject.optJSONObject("show_interval");
        if (optJSONObject != null) {
            this.b = optJSONObject.optInt(Model.KEY_loadtime);
            this.c = optJSONObject.optInt("count");
        }
        optJSONObject = jSONObject.optJSONObject("click_interval");
        if (optJSONObject != null) {
            this.d = optJSONObject.optInt(Model.KEY_loadtime);
            this.e = optJSONObject.optInt("count");
        }
        this.f = jSONObject.optInt("show_eq_click_count");
        this.i = Math.max(this.c + 1, this.f);
        this.j = Math.max(this.e + 1, this.f);
        this.g = new ArrayList(this.i);
        this.h = new ArrayList(this.j);
    }

    public void a(int i) {
        this.k = i;
    }

    public void a(String str) {
        this.l = str;
    }

    public void b(String str) {
        this.m = str;
    }

    public void c(String str) {
        this.n = str;
    }

    public void d(String str) {
        this.o = str;
    }

    long a() {
        if (this.g.isEmpty()) {
            return 0;
        }
        return ((Long) this.g.get(this.g.size() - 1)).longValue();
    }

    long b() {
        if (this.h.isEmpty()) {
            return 0;
        }
        return ((Long) this.h.get(this.h.size() - 1)).longValue();
    }

    public void a(long j) {
        if (this.g.size() == this.i) {
            this.g.remove(0);
        }
        this.g.add(Long.valueOf(j));
        if (c()) {
            e("sus_show_interval");
        }
    }

    public void b(long j) {
        if (this.h.size() == this.j) {
            this.h.remove(0);
        }
        this.h.add(Long.valueOf(j));
        if (d()) {
            e("sus_click_interval");
        }
        if (e()) {
            e("show_eq_click");
        }
        if (f()) {
            e("sus_click");
        }
    }

    boolean c() {
        if (!(this.g.isEmpty() || this.c == 0 || this.b == 0)) {
            int size = this.g.size() - 1;
            int i = 0;
            while (size > 0) {
                int i2;
                if ((((Long) this.g.get(size)).longValue() - ((Long) this.g.get(size - 1)).longValue()) / 1000 < ((long) this.b)) {
                    i2 = i + 1;
                } else {
                    i2 = i;
                }
                if (i2 >= this.c) {
                    return true;
                }
                size--;
                i = i2;
            }
        }
        return false;
    }

    boolean d() {
        if (!(this.h.isEmpty() || this.e == 0 || this.d == 0)) {
            int size = this.h.size() - 1;
            int i = 0;
            while (size > 0) {
                int i2;
                if ((((Long) this.h.get(size)).longValue() - ((Long) this.h.get(size - 1)).longValue()) / 1000 < ((long) this.d)) {
                    i2 = i + 1;
                } else {
                    i2 = i;
                }
                size--;
                i = i2;
            }
            if (i >= this.e) {
                return true;
            }
        }
        return false;
    }

    boolean e() {
        if (!(this.h.isEmpty() || this.g.isEmpty() || this.f == 0)) {
            int size = this.h.size() - 2;
            int i = 0;
            int size2 = this.g.size() - 1;
            while (size >= 0) {
                int i2 = 0;
                int i3 = size2;
                while (i3 >= 0 && ((Long) this.g.get(i3)).longValue() > ((Long) this.h.get(size)).longValue()) {
                    i3--;
                    i2++;
                }
                if (i2 != 1) {
                    return false;
                }
                i2 = i + 1;
                if (i2 >= this.f) {
                    return true;
                }
                size--;
                i = i2;
                size2 = i3;
            }
            if (i + 1 >= this.f) {
                return true;
            }
        }
        return false;
    }

    boolean f() {
        return (this.g.isEmpty() || this.h.isEmpty() || (b() - a()) / 1000 >= ((long) this.a)) ? false : true;
    }

    public String g() {
        return this.l != null ? this.l : "";
    }

    public String h() {
        return this.m != null ? this.m : "";
    }

    public String i() {
        return this.n != null ? this.n : "";
    }

    public String j() {
        return this.o != null ? this.o : "";
    }

    void e(String str) {
        try {
            if (Appodeal.b != null) {
                final JSONObject a = b.a(Appodeal.b, j(), this.k, i(), g(), h());
                a.put("reason", str);
                AsyncTask anonymousClass1 = new AsyncTask<Void, Void, Void>(this) {
                    final /* synthetic */ m b;

                    protected /* synthetic */ Object doInBackground(Object[] objArr) {
                        return a((Void[]) objArr);
                    }

                    /* JADX WARNING: inconsistent code. */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    protected java.lang.Void a(java.lang.Void... r7) {
                        /*
                        r6 = this;
                        r2 = 0;
                        r0 = new java.net.URL;	 Catch:{ MalformedURLException -> 0x0063 }
                        r1 = "http://adwatch.appodeal.com/api/v1/complains/submit";
                        r0.<init>(r1);	 Catch:{ MalformedURLException -> 0x0063 }
                        r0 = r0.openConnection();	 Catch:{ IOException -> 0x006d, all -> 0x0078 }
                        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ IOException -> 0x006d, all -> 0x0078 }
                        r1 = "Accept";
                        r3 = "application/json";
                        r0.setRequestProperty(r1, r3);	 Catch:{ IOException -> 0x0087, all -> 0x0080 }
                        r1 = "Content-type";
                        r3 = "application/json";
                        r0.setRequestProperty(r1, r3);	 Catch:{ IOException -> 0x0087, all -> 0x0080 }
                        r1 = 2000; // 0x7d0 float:2.803E-42 double:9.88E-321;
                        r0.setConnectTimeout(r1);	 Catch:{ IOException -> 0x0087, all -> 0x0080 }
                        r1 = 2000; // 0x7d0 float:2.803E-42 double:9.88E-321;
                        r0.setReadTimeout(r1);	 Catch:{ IOException -> 0x0087, all -> 0x0080 }
                        r1 = "POST";
                        r0.setRequestMethod(r1);	 Catch:{ IOException -> 0x0087, all -> 0x0080 }
                        r1 = 1;
                        r0.setDoOutput(r1);	 Catch:{ IOException -> 0x0087, all -> 0x0080 }
                        r1 = new java.io.DataOutputStream;	 Catch:{ IOException -> 0x0087, all -> 0x0080 }
                        r3 = r0.getOutputStream();	 Catch:{ IOException -> 0x0087, all -> 0x0080 }
                        r1.<init>(r3);	 Catch:{ IOException -> 0x0087, all -> 0x0080 }
                        r3 = r0;	 Catch:{ IOException -> 0x0087, all -> 0x0080 }
                        r3 = r3.toString();	 Catch:{ IOException -> 0x0087, all -> 0x0080 }
                        r4 = "UTF-8";
                        r4 = java.nio.charset.Charset.forName(r4);	 Catch:{ IOException -> 0x0087, all -> 0x0080 }
                        r3 = r3.getBytes(r4);	 Catch:{ IOException -> 0x0087, all -> 0x0080 }
                        r1.write(r3);	 Catch:{ IOException -> 0x0087, all -> 0x0080 }
                        r1.flush();	 Catch:{ IOException -> 0x0087, all -> 0x0080 }
                        r1.close();	 Catch:{ IOException -> 0x0087, all -> 0x0080 }
                        r1 = r0.getResponseCode();	 Catch:{ IOException -> 0x0087, all -> 0x0080 }
                        switch(r1) {
                            case 200: goto L_0x005d;
                            default: goto L_0x0058;
                        };	 Catch:{ IOException -> 0x0087, all -> 0x0080 }
                    L_0x0058:
                        r1 = "rtb_banner_report_failed";
                        com.appodeal.ads.Appodeal.a(r1);	 Catch:{ IOException -> 0x0087, all -> 0x0080 }
                    L_0x005d:
                        if (r0 == 0) goto L_0x0062;
                    L_0x005f:
                        r0.disconnect();	 Catch:{ Exception -> 0x0068 }
                    L_0x0062:
                        return r2;
                    L_0x0063:
                        r0 = move-exception;
                        com.appodeal.ads.Appodeal.a(r0);	 Catch:{ Exception -> 0x0068 }
                        goto L_0x0062;
                    L_0x0068:
                        r0 = move-exception;
                        com.appodeal.ads.Appodeal.a(r0);
                        goto L_0x0062;
                    L_0x006d:
                        r0 = move-exception;
                        r1 = r2;
                    L_0x006f:
                        com.appodeal.ads.Appodeal.a(r0);	 Catch:{ all -> 0x0085 }
                        if (r1 == 0) goto L_0x0062;
                    L_0x0074:
                        r1.disconnect();	 Catch:{ Exception -> 0x0068 }
                        goto L_0x0062;
                    L_0x0078:
                        r0 = move-exception;
                        r1 = r2;
                    L_0x007a:
                        if (r1 == 0) goto L_0x007f;
                    L_0x007c:
                        r1.disconnect();	 Catch:{ Exception -> 0x0068 }
                    L_0x007f:
                        throw r0;	 Catch:{ Exception -> 0x0068 }
                    L_0x0080:
                        r1 = move-exception;
                        r5 = r1;
                        r1 = r0;
                        r0 = r5;
                        goto L_0x007a;
                    L_0x0085:
                        r0 = move-exception;
                        goto L_0x007a;
                    L_0x0087:
                        r1 = move-exception;
                        r5 = r1;
                        r1 = r0;
                        r0 = r5;
                        goto L_0x006f;
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.appodeal.ads.utils.m.1.a(java.lang.Void[]):java.lang.Void");
                    }
                };
                if (VERSION.SDK_INT >= 11) {
                    anonymousClass1.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                } else {
                    anonymousClass1.execute(new Void[0]);
                }
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }
}

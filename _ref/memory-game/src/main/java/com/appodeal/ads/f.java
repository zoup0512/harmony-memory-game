package com.appodeal.ads;

import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.preference.PreferenceManager;
import android.support.annotation.VisibleForTesting;
import android.util.Pair;
import android.util.SparseArray;
import com.facebook.places.model.PlaceFields;
import com.mopub.common.GpsHelper;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

public class f {
    @VisibleForTesting
    SparseArray<JSONObject> a = new SparseArray();
    @VisibleForTesting
    JSONObject b;
    private SparseArray<Pair<String, Long>> c = new SparseArray();

    private class a extends AsyncTask<JSONObject, Void, Void> {
        JSONObject a;
        final /* synthetic */ f b;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((JSONObject[]) objArr);
        }

        public a(f fVar, JSONObject jSONObject) {
            this.b = fVar;
            this.a = jSONObject;
        }

        protected Void a(JSONObject... jSONObjectArr) {
            HttpURLConnection httpURLConnection;
            Throwable th;
            HttpURLConnection httpURLConnection2 = null;
            try {
                HttpURLConnection httpURLConnection3 = (HttpURLConnection) this.b.a().openConnection();
                try {
                    httpURLConnection3.setConnectTimeout(20000);
                    httpURLConnection3.setReadTimeout(20000);
                    httpURLConnection3.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                    httpURLConnection3.setDoOutput(true);
                    httpURLConnection3.setRequestMethod(HttpRequest.METHOD_POST);
                    DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection3.getOutputStream());
                    dataOutputStream.write(this.a.toString().getBytes(Charset.forName("UTF-8")));
                    dataOutputStream.flush();
                    dataOutputStream.close();
                    httpURLConnection3.getResponseCode();
                    if (httpURLConnection3 != null) {
                        httpURLConnection3.disconnect();
                    }
                } catch (Throwable e) {
                    Throwable th2 = e;
                    httpURLConnection = httpURLConnection3;
                    th = th2;
                    try {
                        Appodeal.a(th);
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        return null;
                    } catch (Throwable th3) {
                        th = th3;
                        httpURLConnection2 = httpURLConnection;
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        throw th;
                    }
                } catch (Throwable e2) {
                    httpURLConnection2 = httpURLConnection3;
                    th = e2;
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    throw th;
                }
            } catch (Exception e3) {
                th = e3;
                httpURLConnection = null;
                Appodeal.a(th);
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                return null;
            } catch (Throwable th4) {
                th = th4;
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                throw th;
            }
            return null;
        }
    }

    public void a(int i) {
        if (b(i)) {
            this.a.put(i, c(i));
        }
    }

    public void a(int i, String str, String str2) {
        if (b(i)) {
            this.c.put(i, new Pair(str2, Long.valueOf(System.currentTimeMillis())));
        }
    }

    public void a(int i, String str, boolean z) {
        try {
            if (b(i)) {
                Pair pair = (Pair) this.c.get(i);
                if (pair != null) {
                    String str2 = (String) pair.first;
                    Long l = (Long) pair.second;
                    JSONObject jSONObject = (JSONObject) this.a.get(i);
                    if (l != null && jSONObject != null) {
                        l = Long.valueOf(System.currentTimeMillis() - l.longValue());
                        JSONArray jSONArray = jSONObject.getJSONArray("ad_units");
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("aid", str2);
                        jSONObject2.put("network_name", str);
                        jSONObject2.put("fill", z);
                        jSONObject2.put("delta", l);
                        jSONArray.put(jSONObject2);
                    }
                }
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    public void a(int i, boolean z) {
        try {
            if (b(i)) {
                JSONObject jSONObject = (JSONObject) this.a.get(i);
                if (jSONObject != null) {
                    jSONObject.put("result", z);
                    a(jSONObject, i);
                }
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    private boolean b(int i) {
        switch (i) {
            case 1:
                return n.t;
            case 2:
                return ah.r;
            case 4:
                return g.B;
            case 128:
                return ak.s;
            case 256:
                return v.x;
            case 512:
                return Native.r;
            default:
                return false;
        }
    }

    private synchronized JSONObject c(int i) {
        JSONObject jSONObject;
        try {
            if (this.b == null) {
                this.b = new JSONObject();
                Object string = PreferenceManager.getDefaultSharedPreferences(Appodeal.b).getString(GpsHelper.ADVERTISING_ID_KEY, null);
                if (string == null) {
                    string = an.l(Appodeal.b);
                }
                this.b.put("device_id", string);
                this.b.put("package_name", Appodeal.b.getPackageName());
                this.b.put("uuid", Appodeal.b.getSharedPreferences("appodeal", 0).getString("advertisingTracking", null));
                this.b.put("os", "Android");
                this.b.put("sdk_version", "1.15.7");
                this.b.put("os_version", VERSION.RELEASE);
                if (an.n(Appodeal.b)) {
                    this.b.put("device_type", "tablet");
                } else {
                    this.b.put("device_type", PlaceFields.PHONE);
                }
                this.b.put("connection_type", an.b(Appodeal.b).a);
                this.b.put("user_agent", System.getProperty("http.agent"));
                this.b.put("model", String.format("%s %s", new Object[]{Build.MANUFACTURER, Build.MODEL}));
            }
            JSONObject jSONObject2 = new JSONObject();
            Iterator keys = this.b.keys();
            while (keys.hasNext()) {
                String str = (String) keys.next();
                jSONObject2.put(str, this.b.get(str));
            }
            jSONObject2.put("waterfall_ad_type", i);
            jSONObject2.put("waterfall_start_time", System.currentTimeMillis());
            jSONObject2.put("ad_units", new JSONArray());
            jSONObject = jSONObject2;
        } catch (Throwable e) {
            Appodeal.a(e);
            jSONObject = null;
        }
        return jSONObject;
    }

    @VisibleForTesting
    void a(final JSONObject jSONObject, int i) {
        this.a.remove(i);
        this.c.remove(i);
        if (Appodeal.b != null) {
            Appodeal.b.runOnUiThread(new Runnable(this) {
                final /* synthetic */ f b;

                public void run() {
                    if (VERSION.SDK_INT >= 11) {
                        new a(this.b, jSONObject).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new JSONObject[0]);
                    } else {
                        new a(this.b, jSONObject).execute(new JSONObject[0]);
                    }
                }
            });
        } else if (VERSION.SDK_INT >= 11) {
            new a(this, jSONObject).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new JSONObject[0]);
        } else {
            new a(this, jSONObject).execute(new JSONObject[0]);
        }
    }

    URL a() {
        return new URL("https://rri.appodeal.com/api/stat");
    }
}

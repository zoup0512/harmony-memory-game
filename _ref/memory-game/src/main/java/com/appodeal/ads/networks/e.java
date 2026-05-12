package com.appodeal.ads.networks;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.an;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class e extends AsyncTask<Void, Void, JSONArray> {
    private final a a;
    private String b;
    private final int c;
    private final int d;
    private final int e;

    public interface a {
        void a(int i, int i2);

        void a(JSONArray jSONArray, int i, int i2, int i3);
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        a((JSONArray) obj);
    }

    public e(Activity activity, a aVar, int i, int i2, String str, int i3) {
        this.a = aVar;
        this.c = i;
        this.d = i2;
        this.e = i3;
        this.b = str;
        activity.runOnUiThread(new Runnable(this) {
            final /* synthetic */ e a;

            {
                this.a = r1;
            }

            public void run() {
                if (VERSION.SDK_INT >= 11) {
                    this.a.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
                } else {
                    this.a.execute(new Void[0]);
                }
            }
        });
    }

    protected JSONArray a(Void... voidArr) {
        Throwable th;
        HttpURLConnection httpURLConnection;
        Throwable th2;
        HttpURLConnection httpURLConnection2 = null;
        if (this.b == null) {
            return null;
        }
        try {
            HttpURLConnection httpURLConnection3 = (HttpURLConnection) new URL(this.b).openConnection();
            try {
                httpURLConnection3.setConnectTimeout(20000);
                httpURLConnection3.setReadTimeout(20000);
                String a = an.a(httpURLConnection3.getInputStream());
                if (a == null || a.isEmpty() || a.equals(" ")) {
                    if (httpURLConnection3 != null) {
                        httpURLConnection3.disconnect();
                    }
                    return null;
                }
                JSONObject jSONObject = new JSONObject(a);
                if (jSONObject.getInt("status") != 200) {
                    if (httpURLConnection3 != null) {
                        httpURLConnection3.disconnect();
                    }
                    return null;
                }
                JSONArray jSONArray = jSONObject.getJSONArray("native_ads");
                JSONObject jSONObject2 = jSONObject.getJSONObject("native_settings");
                JSONObject jSONObject3 = jSONObject.getJSONObject("settings");
                String string = jSONObject2.getString("click_url");
                String string2 = jSONObject2.getString("simp_url");
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject4 = jSONArray.getJSONObject(i);
                    CharSequence string3 = jSONObject4.getString("clcode");
                    CharSequence string4 = jSONObject4.getString("event_id");
                    jSONObject4.put("click_url", string.replace("{CLCODE}", string3).replace("{EVENT_ID}", string4));
                    jSONObject4.put("simp_url", string2.replace("{CLCODE}", string3).replace("{EVENT_ID}", string4));
                    jSONObject4.put("settings", jSONObject3);
                }
                if (httpURLConnection3 != null) {
                    httpURLConnection3.disconnect();
                }
                return jSONArray;
            } catch (Throwable e) {
                th = e;
                httpURLConnection = httpURLConnection3;
                th2 = th;
                try {
                    Appodeal.a(th2);
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    return null;
                } catch (Throwable th3) {
                    th2 = th3;
                    httpURLConnection2 = httpURLConnection;
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    throw th2;
                }
            } catch (Throwable th4) {
                th = th4;
                httpURLConnection2 = httpURLConnection3;
                th2 = th;
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                throw th2;
            }
        } catch (Exception e2) {
            th2 = e2;
            httpURLConnection = null;
            Appodeal.a(th2);
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            return null;
        } catch (Throwable th5) {
            th2 = th5;
            if (httpURLConnection2 != null) {
                httpURLConnection2.disconnect();
            }
            throw th2;
        }
    }

    protected void a(JSONArray jSONArray) {
        super.onPostExecute(jSONArray);
        try {
            if (!isCancelled() && this.a != null) {
                if (jSONArray == null || jSONArray.length() == 0) {
                    this.a.a(this.c, this.d);
                } else {
                    this.a.a(jSONArray, this.c, this.d, this.e);
                }
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }
}

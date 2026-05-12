package com.appodeal.ads.networks;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.an;
import com.cmcm.adsdk.Const;
import com.mopub.common.AdType;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class q extends AsyncTask<Void, Void, p> {
    private final a a;
    private final int b;
    private final int c;
    private final String d;
    private final String e;
    private final boolean f;

    public interface a {
        void a(int i, int i2);

        void a(p pVar, int i, int i2);
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        a((p) obj);
    }

    public q(Activity activity, a aVar, int i, int i2, String str, String str2, boolean z) {
        this.a = aVar;
        this.b = i;
        this.c = i2;
        this.d = str;
        this.e = str2;
        this.f = z;
        activity.runOnUiThread(new Runnable(this) {
            final /* synthetic */ q a;

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

    protected p a(Void... voidArr) {
        Throwable th;
        HttpURLConnection httpURLConnection;
        Throwable th2;
        HttpURLConnection httpURLConnection2 = null;
        if (this.d == null) {
            return null;
        }
        try {
            HttpURLConnection httpURLConnection3 = (HttpURLConnection) new URL(this.d).openConnection();
            try {
                httpURLConnection3.setConnectTimeout(20000);
                httpURLConnection3.setReadTimeout(20000);
                httpURLConnection3.setRequestProperty("User-Agent", System.getProperty("http.agent"));
                if (this.e != null) {
                    httpURLConnection3.setRequestProperty("Cookie", this.e);
                }
                String headerField = httpURLConnection3.getHeaderField("Set-Cookie");
                String a = an.a(httpURLConnection3.getInputStream());
                if (!this.f) {
                    if (httpURLConnection3 != null) {
                        httpURLConnection3.disconnect();
                    }
                    return null;
                } else if (a == null || a.isEmpty() || a.equals(" ")) {
                    if (httpURLConnection3 != null) {
                        httpURLConnection3.disconnect();
                    }
                    return null;
                } else {
                    JSONArray jSONArray = new JSONObject(a).getJSONObject("ads").getJSONArray(Const.KEY_JUHE);
                    if (jSONArray.length() == 0) {
                        if (httpURLConnection3 != null) {
                            httpURLConnection3.disconnect();
                        }
                        return null;
                    }
                    JSONObject jSONObject = jSONArray.getJSONObject(0);
                    JSONObject jSONObject2 = jSONObject.getJSONArray("creative").getJSONObject(0);
                    JSONObject jSONObject3 = jSONObject2.getJSONObject("tracking");
                    String string = jSONObject3.getString("impression");
                    String string2 = jSONObject3.getString("click");
                    p pVar = new p();
                    pVar.a = headerField;
                    pVar.b = string;
                    pVar.c = string2;
                    headerField = jSONObject.getString(AdType.HTML);
                    if (headerField == null || headerField.isEmpty() || headerField.equals(" ") || headerField.contains("INSERT_RANDOM_NUMBER_HERE")) {
                        if (httpURLConnection3 != null) {
                            httpURLConnection3.disconnect();
                        }
                        return null;
                    }
                    int i = jSONObject2.getInt("width");
                    int i2 = jSONObject2.getInt("height");
                    pVar.d = headerField;
                    pVar.e = i;
                    pVar.f = i2;
                    if (httpURLConnection3 != null) {
                        httpURLConnection3.disconnect();
                    }
                    return pVar;
                }
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

    protected void a(p pVar) {
        super.onPostExecute(pVar);
        try {
            if (this.a == null) {
                return;
            }
            if (pVar == null) {
                this.a.a(this.b, this.c);
            } else {
                this.a.a(pVar, this.b, this.c);
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }
}

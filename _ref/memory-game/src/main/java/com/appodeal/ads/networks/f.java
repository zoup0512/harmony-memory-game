package com.appodeal.ads.networks;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.util.Pair;
import com.applovin.sdk.AppLovinEventTypes;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.an;
import com.my.target.ads.MyTargetVideoView;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONObject;

public class f extends AsyncTask<Void, Void, Pair<String, Pair<Integer, Integer>>> {
    private final a a;
    private final String b;
    private final int c;
    private final int d;

    public interface a {
        void a(int i, int i2);

        void a(Pair<String, Pair<Integer, Integer>> pair, int i, int i2);
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        a((Pair) obj);
    }

    public f(Activity activity, a aVar, int i, int i2, String str) {
        this.a = aVar;
        this.c = i;
        this.d = i2;
        this.b = str;
        activity.runOnUiThread(new Runnable(this) {
            final /* synthetic */ f a;

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

    protected Pair<String, Pair<Integer, Integer>> a(Void... voidArr) {
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
                if (jSONObject.has("status") && jSONObject.has("ads")) {
                    a = jSONObject.getString("status");
                    JSONArray jSONArray = jSONObject.getJSONArray("ads");
                    if (!a.equals(MyTargetVideoView.COMPLETE_STATUS_OK) || jSONArray.length() == 0) {
                        if (httpURLConnection3 != null) {
                            httpURLConnection3.disconnect();
                        }
                        return null;
                    }
                    JSONObject jSONObject2 = jSONArray.getJSONObject(0);
                    Pair<String, Pair<Integer, Integer>> pair = new Pair(jSONObject2.getString(AppLovinEventTypes.USER_VIEWED_CONTENT), new Pair(Integer.valueOf(jSONObject2.getInt("width")), Integer.valueOf(jSONObject2.getInt("height"))));
                    if (httpURLConnection3 != null) {
                        httpURLConnection3.disconnect();
                    }
                    return pair;
                }
                if (httpURLConnection3 != null) {
                    httpURLConnection3.disconnect();
                }
                return null;
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

    protected void a(Pair<String, Pair<Integer, Integer>> pair) {
        super.onPostExecute(pair);
        try {
            if (!isCancelled() && this.a != null) {
                if (pair == null) {
                    this.a.a(this.c, this.d);
                } else {
                    this.a.a(pair, this.c, this.d);
                }
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }
}

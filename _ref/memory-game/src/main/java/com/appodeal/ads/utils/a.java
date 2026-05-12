package com.appodeal.ads.utils;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import com.appodeal.ads.Appodeal;
import com.mopub.common.VisibleForTesting;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import org.json.JSONObject;

public class a {
    private JSONObject a;

    private class a extends AsyncTask<JSONObject, Void, Void> {
        final /* synthetic */ a a;

        private a(a aVar) {
            this.a = aVar;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((JSONObject[]) objArr);
        }

        protected Void a(JSONObject... jSONObjectArr) {
            HttpURLConnection httpURLConnection;
            Throwable th;
            HttpURLConnection httpURLConnection2 = null;
            if (!(jSONObjectArr == null || jSONObjectArr.length == 0)) {
                try {
                    JSONObject jSONObject = jSONObjectArr[0];
                    HttpURLConnection httpURLConnection3 = (HttpURLConnection) new URL(this.a.a()).openConnection();
                    try {
                        httpURLConnection3.setConnectTimeout(20000);
                        httpURLConnection3.setReadTimeout(20000);
                        httpURLConnection3.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                        httpURLConnection3.setDoOutput(true);
                        httpURLConnection3.setRequestMethod(HttpRequest.METHOD_POST);
                        DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection3.getOutputStream());
                        dataOutputStream.write(jSONObject.toString().getBytes(Charset.forName("UTF-8")));
                        dataOutputStream.flush();
                        dataOutputStream.close();
                        switch (httpURLConnection3.getResponseCode()) {
                            case 200:
                                Appodeal.a("Stats send");
                                break;
                        }
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
            }
            return null;
        }
    }

    @VisibleForTesting
    String a() {
        return "http://adwatch.appodeal.com/api/v1/impressions/submit";
    }

    public a(Context context, String str, String str2, String str3, String str4, int i) {
        try {
            this.a = b.a(context, str3, i, str4, str2, str);
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    public void b() {
        if (VERSION.SDK_INT >= 11) {
            new a().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new JSONObject[]{this.a});
            return;
        }
        new a().execute(new JSONObject[]{this.a});
    }
}

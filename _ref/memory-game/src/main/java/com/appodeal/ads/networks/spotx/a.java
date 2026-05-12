package com.appodeal.ads.networks.spotx;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.an;
import java.net.HttpURLConnection;
import java.net.URL;

public class a extends AsyncTask<Void, Void, String> {
    private final a a;
    private final int b;
    private final int c;
    private final String d;

    public interface a {
        void a(int i, int i2);

        void a(String str, int i, int i2);
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        a((String) obj);
    }

    public a(Activity activity, a aVar, int i, int i2, String str) {
        this.a = aVar;
        this.b = i;
        this.c = i2;
        this.d = str;
        activity.runOnUiThread(new Runnable(this) {
            final /* synthetic */ a a;

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

    protected String a(Void... voidArr) {
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
                String a = an.a(httpURLConnection3.getInputStream());
                if (a == null || a.isEmpty() || a.equals(" ")) {
                    if (httpURLConnection3 != null) {
                        httpURLConnection3.disconnect();
                    }
                    return null;
                }
                if (httpURLConnection3 != null) {
                    httpURLConnection3.disconnect();
                }
                return a;
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

    protected void a(String str) {
        super.onPostExecute(str);
        try {
            if (this.a == null) {
                return;
            }
            if (str == null) {
                this.a.a(this.b, this.c);
            } else {
                this.a.a(str, this.b, this.c);
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }
}

package com.appodeal.ads.networks;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.util.Pair;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.an;
import java.net.HttpURLConnection;
import java.net.URL;

public class v extends AsyncTask<Void, Void, Pair<String, String>> {
    private final a a;
    private final int b;
    private final int c;
    private final String d;
    private final String e;

    public interface a {
        void a(int i, int i2);

        void a(Pair<String, String> pair, int i, int i2);
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        a((Pair) obj);
    }

    public v(Activity activity, a aVar, int i, int i2, String str, String str2) {
        this.a = aVar;
        this.b = i;
        this.c = i2;
        this.d = str;
        this.e = str2;
        activity.runOnUiThread(new Runnable(this) {
            final /* synthetic */ v a;

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

    protected Pair<String, String> a(Void... voidArr) {
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
                    httpURLConnection3.setRequestProperty("SomaUserID", this.e);
                }
                if (httpURLConnection3.getHeaderField("SomaError") != null) {
                    if (httpURLConnection3 != null) {
                        httpURLConnection3.disconnect();
                    }
                    return null;
                }
                String headerField = httpURLConnection3.getHeaderField("SomaUserID");
                String a = an.a(httpURLConnection3.getInputStream());
                if (a == null || a.isEmpty() || a.equals(" ")) {
                    if (httpURLConnection3 != null) {
                        httpURLConnection3.disconnect();
                    }
                    return null;
                }
                Pair<String, String> pair = new Pair(a, headerField);
                if (httpURLConnection3 != null) {
                    httpURLConnection3.disconnect();
                }
                return pair;
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

    protected void a(Pair<String, String> pair) {
        super.onPostExecute(pair);
        try {
            if (this.a == null) {
                return;
            }
            if (pair == null) {
                this.a.a(this.b, this.c);
            } else {
                this.a.a(pair, this.b, this.c);
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }
}

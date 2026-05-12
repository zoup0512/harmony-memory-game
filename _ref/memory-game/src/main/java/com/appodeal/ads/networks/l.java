package com.appodeal.ads.networks;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.util.Pair;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.UserSettings;
import com.appodeal.ads.an;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class l extends AsyncTask<Void, Void, String> {
    private final a a;
    private final String b;
    private final int c;
    private final int d;
    private final int e;

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

    public l(Activity activity, a aVar, int i, int i2, String str, Integer num) {
        this.a = aVar;
        this.c = i;
        this.d = i2;
        this.b = str;
        this.e = num.intValue();
        activity.runOnUiThread(new Runnable(this) {
            final /* synthetic */ l a;

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
        HttpURLConnection httpURLConnection;
        Throwable th;
        HttpURLConnection httpURLConnection2 = null;
        if (this.b == null) {
            return null;
        }
        try {
            HttpURLConnection httpURLConnection3 = (HttpURLConnection) new URL(this.b).openConnection();
            try {
                String str;
                httpURLConnection3.setConnectTimeout(20000);
                httpURLConnection3.setReadTimeout(20000);
                httpURLConnection3.setRequestProperty("User-Agent", URLEncoder.encode(System.getProperty("http.agent"), "utf-8"));
                if (UserSettings.userData != null && UserSettings.userData.has("ip")) {
                    httpURLConnection3.setRequestProperty("IP", UserSettings.userData.getString("ip"));
                }
                if (this.e > 0) {
                    Pair b = an.b(httpURLConnection3.getInputStream());
                    str = (String) b.first;
                    if (((Integer) b.second).intValue() < this.e) {
                        if (httpURLConnection3 != null) {
                            httpURLConnection3.disconnect();
                        }
                        return null;
                    }
                }
                str = an.a(httpURLConnection3.getInputStream());
                if (str == null || str.isEmpty() || str.equals(" ") || str.equals("NoAd")) {
                    if (httpURLConnection3 != null) {
                        httpURLConnection3.disconnect();
                    }
                    return null;
                }
                if (httpURLConnection3 != null) {
                    httpURLConnection3.disconnect();
                }
                return str;
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

    protected void a(String str) {
        super.onPostExecute(str);
        try {
            if (this.a == null) {
                return;
            }
            if (str == null) {
                this.a.a(this.c, this.d);
            } else {
                this.a.a(str, this.c, this.d);
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }
}

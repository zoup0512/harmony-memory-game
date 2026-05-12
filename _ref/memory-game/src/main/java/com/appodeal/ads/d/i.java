package com.appodeal.ads.d;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.util.DisplayMetrics;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.AppodealSettings;
import com.appodeal.ads.Native;
import com.appodeal.ads.ah;
import com.appodeal.ads.ak;
import com.appodeal.ads.an;
import com.appodeal.ads.ao;
import com.mopub.common.AdType;
import com.mopub.common.GpsHelper;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import org.json.JSONException;
import org.json.JSONObject;

public class i extends AsyncTask<Void, Object, a> {
    private h a;
    private String b;
    private int c = 0;
    private int d;
    private Context e;
    private g f;
    private List<c> g;
    private a h;
    private a i;
    private Double j;
    private List<Thread> k;
    private int l;
    private int m;

    public interface a {
        void a(int i, a aVar, h hVar);

        void a(c cVar);

        void a(f fVar);
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        a((a) obj);
    }

    public i(@NonNull Activity activity, int i, int i2, @NonNull g gVar, a aVar, Double d, String str, String str2) {
        this.i = aVar;
        if (gVar.e()) {
            if (aVar != null) {
                this.i.a(i2, null, null);
            }
        } else if (d != null && d.doubleValue() <= 100.0d) {
            this.e = activity;
            this.c = i;
            this.b = str;
            this.d = i2;
            this.f = gVar;
            this.g = new ArrayList();
            this.j = d;
            this.a = new h();
            this.a.g();
            this.a.d(str2);
            DisplayMetrics displayMetrics = this.e.getResources().getDisplayMetrics();
            this.l = displayMetrics.widthPixels;
            this.m = displayMetrics.heightPixels;
            activity.runOnUiThread(new Runnable(this) {
                final /* synthetic */ i a;

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
        } else if (aVar != null) {
            this.i.a(i2, null, null);
        }
    }

    protected a a(Void... voidArr) {
        int i = 0;
        try {
            if (this.f.c() == null || this.f.c().isEmpty()) {
                return null;
            }
            try {
                b a = a();
                BlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
                this.k = new ArrayList();
                for (e eVar : this.f.c()) {
                    JSONObject a2;
                    String m = eVar.m();
                    switch (this.c) {
                        case 1:
                            a2 = a.a(eVar.k(), 320, 50, false);
                            break;
                        case 2:
                            if (m != null && m.equals(AdType.MRAID)) {
                                a2 = a.a(eVar.k(), this.l, this.m, true);
                                break;
                            }
                            a2 = a.a(ao.a() ? ao.a() : eVar.i().booleanValue(), ah.v / 1000, eVar);
                            break;
                            break;
                        case 3:
                            a2 = a.a(eVar.k(), this.l, this.m, true);
                            break;
                        case 4:
                            if (m != null && m.equals(AdType.MRAID)) {
                                a2 = a.a(eVar.k(), this.l, this.m, true);
                                break;
                            }
                            a2 = a.a(ao.a() ? ao.a() : eVar.i().booleanValue(), ak.w / 1000, eVar);
                            break;
                        case 5:
                            a2 = a.a(Native.m, eVar.k(), eVar.n());
                            break;
                        case 7:
                            a2 = a.a(eVar.k(), 300, (int) Callback.DEFAULT_SWIPE_ANIMATION_DURATION, false);
                            break;
                        default:
                            throw new f("wrong_type");
                    }
                    a.a(eVar.a(), a2);
                    Thread thread = new Thread(new d(linkedBlockingQueue, a2, eVar, this.b), "BidThread");
                    thread.setDaemon(true);
                    thread.start();
                    this.k.add(thread);
                    if (!this.a.h()) {
                        this.a.c(a2);
                    }
                }
                while (!isCancelled()) {
                    int i2;
                    Serializable serializable = (Serializable) linkedBlockingQueue.take();
                    if (serializable != null) {
                        int i3 = i + 1;
                        if (serializable instanceof c) {
                            this.g.add((c) serializable);
                            for (j a3 : ((c) serializable).b()) {
                                for (a aVar : a3.a()) {
                                    if (this.h == null) {
                                        if (aVar.b().doubleValue() > this.j.doubleValue() * ((double) aVar.i().a().floatValue())) {
                                            this.h = aVar;
                                            this.j = Double.valueOf(this.j.doubleValue() * ((double) aVar.i().a().floatValue()));
                                        } else {
                                            try {
                                                this.a.a(aVar);
                                            } catch (InterruptedException e) {
                                            }
                                        }
                                    } else if (this.h.b().doubleValue() < aVar.b().doubleValue()) {
                                        this.j = this.h.b();
                                        this.a.a(this.h);
                                        this.h = aVar;
                                    } else if (this.h.b().doubleValue() > aVar.b().doubleValue()) {
                                        this.a.a(aVar);
                                        if (this.j.doubleValue() < aVar.b().doubleValue()) {
                                            this.j = aVar.b();
                                        }
                                    } else {
                                        this.a.a(aVar);
                                    }
                                }
                            }
                        }
                        publishProgress(new Object[]{serializable});
                        if (i3 < this.f.c().size()) {
                            i2 = i3;
                        }
                    } else {
                        i2 = i;
                    }
                    i = i2;
                }
                try {
                    if (this.h != null) {
                        this.a.a(this.h);
                        String b = b(this.h);
                        if (!(b == null || b.isEmpty() || !this.h.e().isEmpty())) {
                            this.h.a(b);
                        }
                        this.h.d();
                        return this.h;
                    }
                } catch (f e2) {
                    publishProgress(new Object[]{e2});
                }
                return null;
            } catch (f e22) {
                publishProgress(new Object[]{e22});
                return null;
            }
        } catch (Throwable e3) {
            Appodeal.a(e3);
        }
    }

    protected void onCancelled() {
        super.onCancelled();
        try {
            if (this.k != null) {
                for (Thread thread : this.k) {
                    if (thread != null && thread.isAlive()) {
                        thread.interrupt();
                    }
                }
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    protected void onProgressUpdate(Object... objArr) {
        super.onProgressUpdate(objArr);
        try {
            if (this.i != null && objArr.length > 0) {
                Object obj = objArr[0];
                if (obj instanceof c) {
                    this.i.a((c) obj);
                } else if (obj instanceof f) {
                    this.i.a((f) obj);
                }
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    protected void a(a aVar) {
        super.onPostExecute(aVar);
        try {
            if (this.i != null) {
                if (aVar != null) {
                    this.a.a(aVar.a());
                    this.a.a(aVar.a().optString("mfr_id"));
                    this.a.b(aVar.i().c());
                    if (aVar.f() != null && aVar.f().length > 0) {
                        this.a.c(aVar.f()[0]);
                    }
                }
                if (this.g != null && this.g.size() > 0) {
                    for (c cVar : this.g) {
                        if (cVar.f().l()) {
                            if (aVar != null && cVar.f().c().equals(aVar.i().c())) {
                                cVar.a(true);
                            }
                            this.a.a(cVar);
                        }
                    }
                }
                this.a.a(this.e);
                this.f.a(this.a);
                this.i.a(this.d, aVar, this.a);
            }
        } catch (Throwable e) {
            Appodeal.a(e);
            this.i.a(this.d, null, null);
        }
    }

    private b a() {
        try {
            String string = PreferenceManager.getDefaultSharedPreferences(this.e).getString(GpsHelper.ADVERTISING_ID_KEY, null);
            if (string == null) {
                string = an.l(this.e);
            }
            String str = string + System.currentTimeMillis();
            String packageName = this.e.getPackageName();
            String str2 = "https://play.google.com/store/apps/details?id=" + packageName;
            if (this.f.g() != null) {
                str2 = this.f.g().optString("store_url", str2);
            }
            return new b(this.e, this.j, str, string, packageName, str2, Integer.valueOf(2), this.f.b(), this.f.d(), this.f.f(), this.f.g(), true, AppodealSettings.h);
        } catch (JSONException e) {
            throw new f("bid_json_create_exception");
        } catch (Exception e2) {
            throw new f("some_exception");
        }
    }

    private String b(a aVar) {
        try {
            aVar.a(Double.valueOf(this.j.doubleValue() + 0.009999999776482582d));
            String c = aVar.c();
            if (c.isEmpty()) {
                return "";
            }
            String str = "";
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(c).openConnection();
            httpURLConnection.setRequestProperty("Accept", "application/json");
            httpURLConnection.setRequestProperty("Content-type", "application/json");
            httpURLConnection.setRequestProperty("x-openrtb-version", "2.3");
            httpURLConnection.setRequestMethod(HttpRequest.METHOD_GET);
            switch (httpURLConnection.getResponseCode()) {
                case 200:
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                    c = str;
                    while (true) {
                        str = bufferedReader.readLine();
                        if (str == null) {
                            return c;
                        }
                        c = c + str;
                    }
                default:
                    throw new f("response_code " + httpURLConnection.getResponseCode());
            }
            throw new f("connection_exception");
        } catch (MalformedURLException e) {
            throw new f("wrong_url");
        } catch (IOException e2) {
            throw new f("connection_exception");
        } catch (Exception e3) {
            throw new f("some_exception");
        }
    }
}

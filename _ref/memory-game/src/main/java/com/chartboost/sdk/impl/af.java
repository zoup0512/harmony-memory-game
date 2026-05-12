package com.chartboost.sdk.impl;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Model.CBError.CBClickError;
import com.chartboost.sdk.Model.a.e;
import com.chartboost.sdk.c;
import com.chartboost.sdk.f;
import com.mopub.common.Constants;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class af {
    private final a a;
    private com.chartboost.sdk.Model.a b;

    public interface a {
        void a(com.chartboost.sdk.Model.a aVar, boolean z, String str, CBClickError cBClickError, com.chartboost.sdk.d.a aVar2);
    }

    public af(a aVar) {
        this.a = aVar;
    }

    public a a() {
        return this.a;
    }

    public void a(com.chartboost.sdk.Model.a aVar, final String str, final Activity activity, final com.chartboost.sdk.d.a aVar2) {
        this.b = aVar;
        try {
            String scheme = new URI(str).getScheme();
            if (scheme == null) {
                if (this.a != null) {
                    this.a.a(aVar, false, str, CBClickError.URI_INVALID, aVar2);
                }
            } else if (scheme.equals(Constants.HTTP) || scheme.equals(Constants.HTTPS)) {
                u.a().execute(new Runnable(this) {
                    final /* synthetic */ af d;

                    public void run() {
                        HttpURLConnection httpURLConnection;
                        Throwable e;
                        String str;
                        Throwable th;
                        try {
                            String str2 = str;
                            if (f.h().b()) {
                                HttpURLConnection httpURLConnection2 = null;
                                try {
                                    httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                                } catch (Exception e2) {
                                    e = e2;
                                    try {
                                        CBLogging.b("CBURLOpener", "Exception raised while opening a HTTP Conection", e);
                                        if (httpURLConnection2 != null) {
                                            httpURLConnection2.disconnect();
                                            str = str2;
                                            a(str);
                                        }
                                        str = str2;
                                        a(str);
                                    } catch (Throwable th2) {
                                        e = th2;
                                        if (httpURLConnection2 != null) {
                                            httpURLConnection2.disconnect();
                                        }
                                        throw e;
                                    }
                                }
                                try {
                                    httpURLConnection.setInstanceFollowRedirects(false);
                                    httpURLConnection.setConnectTimeout(10000);
                                    httpURLConnection.setReadTimeout(10000);
                                    String headerField = httpURLConnection.getHeaderField("Location");
                                    if (headerField == null) {
                                        headerField = str2;
                                    }
                                    if (httpURLConnection != null) {
                                        httpURLConnection.disconnect();
                                        str = headerField;
                                    } else {
                                        str = headerField;
                                    }
                                } catch (Throwable e3) {
                                    th = e3;
                                    httpURLConnection2 = httpURLConnection;
                                    e = th;
                                    CBLogging.b("CBURLOpener", "Exception raised while opening a HTTP Conection", e);
                                    if (httpURLConnection2 != null) {
                                        httpURLConnection2.disconnect();
                                        str = str2;
                                        a(str);
                                    }
                                    str = str2;
                                    a(str);
                                } catch (Throwable e32) {
                                    th = e32;
                                    httpURLConnection2 = httpURLConnection;
                                    e = th;
                                    if (httpURLConnection2 != null) {
                                        httpURLConnection2.disconnect();
                                    }
                                    throw e;
                                }
                                a(str);
                            }
                            str = str2;
                            a(str);
                        } catch (Exception e4) {
                            com.chartboost.sdk.Tracking.a.a(af.class, "open followTask", e4);
                        }
                    }

                    public void a(final String str) {
                        Runnable anonymousClass1 = new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 b;

                            public void run() {
                                this.b.d.a(str, activity, aVar2);
                            }
                        };
                        if (activity != null) {
                            activity.runOnUiThread(anonymousClass1);
                        } else {
                            CBUtility.c().post(anonymousClass1);
                        }
                    }
                });
            } else {
                a(str, activity, aVar2);
            }
        } catch (URISyntaxException e) {
            if (this.a != null) {
                this.a.a(aVar, false, str, CBClickError.URI_INVALID, aVar2);
            }
        }
    }

    private void a(String str, Context context, com.chartboost.sdk.d.a aVar) {
        Intent intent;
        String str2;
        if (this.b != null && this.b.a()) {
            this.b.c = e.NONE;
        }
        if (context == null) {
            context = c.x();
        }
        if (context != null) {
            try {
                intent = new Intent("android.intent.action.VIEW");
                if (!(context instanceof Activity)) {
                    intent.addFlags(268435456);
                }
                intent.setData(Uri.parse(str));
                context.startActivity(intent);
                str2 = str;
            } catch (Exception e) {
                if (str.startsWith("market://")) {
                    try {
                        str = "http://market.android.com/" + str.substring(9);
                        intent = new Intent("android.intent.action.VIEW");
                        if (!(context instanceof Activity)) {
                            intent.addFlags(268435456);
                        }
                        intent.setData(Uri.parse(str));
                        context.startActivity(intent);
                        str2 = str;
                    } catch (Throwable e2) {
                        str2 = str;
                        CBLogging.b("CBURLOpener", "Exception raised openeing an inavld playstore URL", e2);
                        if (this.a != null) {
                            this.a.a(this.b, false, str2, CBClickError.URI_UNRECOGNIZED, aVar);
                            return;
                        }
                        return;
                    }
                }
                if (this.a != null) {
                    this.a.a(this.b, false, str, CBClickError.URI_UNRECOGNIZED, aVar);
                }
                str2 = str;
            }
            if (this.a != null) {
                this.a.a(this.b, true, str2, null, aVar);
            }
        } else if (this.a != null) {
            this.a.a(this.b, false, str, CBClickError.NO_HOST_ACTIVITY, aVar);
        }
    }

    public static boolean a(String str) {
        try {
            Context x = c.x();
            Intent intent = new Intent("android.intent.action.VIEW");
            if (!(x instanceof Activity)) {
                intent.addFlags(268435456);
            }
            intent.setData(Uri.parse(str));
            if (x.getPackageManager().queryIntentActivities(intent, 65536).size() > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            CBLogging.b("CBURLOpener", "Cannot open URL", e);
            com.chartboost.sdk.Tracking.a.a(af.class, "canOpenURL", e);
            return false;
        }
    }
}

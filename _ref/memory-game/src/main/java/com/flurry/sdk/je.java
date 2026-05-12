package com.flurry.sdk;

import android.widget.Toast;
import com.amazonaws.services.s3.util.Mimetypes;
import com.flurry.sdk.lq.a;
import java.util.Arrays;

public class je extends ky implements a {
    private static final String a = je.class.getSimpleName();
    private String f;
    private boolean g;

    public je() {
        this((byte) 0);
    }

    private je(byte b) {
        super("Analytics", je.class.getSimpleName());
        this.c = "AnalyticsData_";
        lq a = lp.a();
        this.g = ((Boolean) a.a("UseHttps")).booleanValue();
        a.a("UseHttps", (a) this);
        km.a(4, a, "initSettings, UseHttps = " + this.g);
        String str = (String) a.a("ReportUrl");
        a.a("ReportUrl", (a) this);
        b(str);
        km.a(4, a, "initSettings, ReportUrl = " + str);
        b();
    }

    private void b(String str) {
        if (!(str == null || str.endsWith(".do"))) {
            km.a(5, a, "overriding analytics agent report URL without an endpoint, are you sure?");
        }
        this.f = str;
    }

    public final void a(String str, Object obj) {
        Object obj2 = -1;
        switch (str.hashCode()) {
            case -239660092:
                if (str.equals("UseHttps")) {
                    obj2 = null;
                    break;
                }
                break;
            case 1650629499:
                if (str.equals("ReportUrl")) {
                    obj2 = 1;
                    break;
                }
                break;
        }
        switch (obj2) {
            case null:
                this.g = ((Boolean) obj).booleanValue();
                km.a(4, a, "onSettingUpdate, UseHttps = " + this.g);
                return;
            case 1:
                String str2 = (String) obj;
                b(str2);
                km.a(4, a, "onSettingUpdate, ReportUrl = " + str2);
                return;
            default:
                km.a(6, a, "onSettingUpdate internal error!");
                return;
        }
    }

    protected final void a(String str, String str2, final int i) {
        jy.a().b(new ma(this) {
            final /* synthetic */ je b;

            public final void a() {
                if (i == 200) {
                    hr.a();
                    jh b = hr.b();
                    if (b != null) {
                        b.j = true;
                    }
                }
            }
        });
        super.a(str, str2, i);
    }

    protected final void a(byte[] bArr, final String str, final String str2) {
        String str3;
        if (this.f != null) {
            str3 = this.f;
        } else if (this.g) {
            str3 = "https://data.flurry.com/aap.do";
        } else {
            str3 = "http://data.flurry.com/aap.do";
        }
        km.a(4, a, "FlurryDataSender: start upload data " + Arrays.toString(bArr) + " with id = " + str + " to " + str3);
        mb ksVar = new ks();
        ksVar.g = str3;
        ksVar.u = 100000;
        ksVar.h = ku.a.kPost;
        ksVar.a("Content-Type", Mimetypes.MIMETYPE_OCTET_STREAM);
        ksVar.c = new lc();
        ksVar.b = bArr;
        ksVar.a = new ks.a<byte[], Void>(this) {
            final /* synthetic */ je c;

            public final /* synthetic */ void a(ks ksVar, Object obj) {
                final int i = ksVar.q;
                if (i > 0) {
                    km.e(je.a, "Analytics report sent.");
                    km.a(3, je.a, "FlurryDataSender: report " + str + " sent. HTTP response: " + i);
                    if (km.c() <= 3 && km.d()) {
                        jy.a().a(new Runnable(this) {
                            final /* synthetic */ AnonymousClass1 b;

                            public final void run() {
                                Toast.makeText(jy.a().a, "SD HTTP Response Code: " + i, 0).show();
                            }
                        });
                    }
                    this.c.a(str, str2, i);
                    this.c.b();
                    return;
                }
                this.c.a(str);
            }
        };
        jw.a().a((Object) this, ksVar);
    }
}

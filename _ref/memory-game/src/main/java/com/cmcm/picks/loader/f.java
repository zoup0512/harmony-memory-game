package com.cmcm.picks.loader;

import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.cmcm.adsdk.CMAdManager;
import com.cmcm.adsdk.Const;
import com.cmcm.utils.Commons;
import com.cmcm.utils.i;
import com.mopub.common.Constants;
import com.mopub.mobileads.VastIconXmlManager;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.http.client.utils.URIUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

/* compiled from: CmMarketHttpClient */
public class f {
    public static String a = ("http://" + g.e() + ":80" + "/b/?action=get_config&mid=");
    private static f b = new f();

    /* compiled from: CmMarketHttpClient */
    public static class a extends c {
        String d = Constants.HTTPS;
        String e = Constants.HTTP;

        public a() {
            int i = 1;
            Context context = CMAdManager.getContext();
            String language = Commons.getLanguage(context);
            String country = Commons.getCountry(context);
            a("mid", CMAdManager.getMid());
            a("sdkt", Integer.valueOf(1));
            a("lan", String.format("%s_%s", new Object[]{language, country}));
            a("brand", Commons.SP2("ro.product.brand", "unknow"));
            a("model", Commons.SP2("ro.product.model", "unknow"));
            a("androidid", Commons.getAndroidId());
            a("cver", Integer.valueOf(Commons.getAppVersionCode(context)));
            a("mcc", Commons.getMCC(context));
            a("mnc", Commons.getMNC(context));
            a("ov", Integer.valueOf(VERSION.SDK_INT));
            language = "nt";
            if (!i.a(context)) {
                i = 2;
            }
            a(language, Integer.valueOf(i));
            a("ch", CMAdManager.getChannelId());
            a("resolution", Commons.getResolution(context));
            a("dpi", Float.valueOf(Commons.getScreenDensity(context)));
            a("gaid", com.cmcm.picks.gaid.a.c().a());
            a("pl", "2");
            a("sdkv", Const.VERSION);
            a("tz", b());
            a("sspid", Integer.valueOf(2));
            if (CMAdManager.isSetDebugCounutry) {
                a("test_country", "us");
            }
            if (CMAdManager.getGender() != null) {
                a("gender", CMAdManager.getGender().getGender());
            }
            if (CMAdManager.getAge() != null) {
                a("age", Integer.valueOf(CMAdManager.getAge().getAge()));
            }
        }

        private String b() {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("Z", Locale.US);
            simpleDateFormat.setTimeZone(TimeZone.getDefault());
            return simpleDateFormat.format(new Date());
        }

        public a a(String str) {
            a("posid", str);
            return this;
        }

        public a b(int i) {
            a("adn", Integer.valueOf(i));
            return this;
        }

        public a c(int i) {
            a("pg", Integer.valueOf(i));
            return this;
        }

        public a d(int i) {
            a(VastIconXmlManager.OFFSET, Integer.valueOf(i));
            return this;
        }

        private void a(String str, Object obj) {
            if (!TextUtils.isEmpty(str)) {
                this.c.add(new BasicNameValuePair(str, obj != null ? String.valueOf(obj) : ""));
            }
        }

        public URI a() {
            try {
                String str = Constants.HTTP;
                String str2 = this.a;
                String str3 = "/b/";
                str = g.b();
                if (!(this.e.equalsIgnoreCase(str) || this.d.equalsIgnoreCase(str))) {
                    str = Constants.HTTPS;
                }
                return URIUtils.createURI(str, str2, this.b, str3, URLEncodedUtils.format(this.c, "UTF-8"), null);
            } catch (Exception e) {
                return null;
            }
        }

        public String toString() {
            return String.valueOf(a());
        }
    }

    private f() {
    }

    public static f a() {
        return b;
    }

    public boolean a(String str) {
        String b = com.cmcm.utils.f.b(null, a + str, true);
        if (b == null) {
            return false;
        }
        return g.a(b);
    }
}

package com.cmcm.picks.loader;

import android.text.TextUtils;
import com.cmcm.adsdk.CMAdManager;
import com.cmcm.picks.gaid.a;
import com.cmcm.utils.Commons;
import java.util.Map;

/* compiled from: BuinessPublicData */
public class e {
    private String a;
    private int b;
    private int c;
    private String d;
    private String e;
    private String f = "";
    private int g = 0;
    private String h = null;
    private String i = "";
    private String j = "";
    private String k = "";
    private String l;
    private int m = 0;
    private Map<String, String> n;

    public static e a(String str, int i) {
        e eVar = new e();
        eVar.a = str;
        eVar.b = Integer.parseInt(CMAdManager.getMid());
        eVar.c = i;
        eVar.d = Commons.getAndroidId();
        String language = Commons.getLanguage(CMAdManager.getContext());
        String country = Commons.getCountry(CMAdManager.getContext());
        eVar.e = String.format("%s_%s", new Object[]{language, country});
        eVar.g = Commons.getAppVersionCode(CMAdManager.getContext());
        eVar.i = Commons.getMCC(CMAdManager.getContext());
        eVar.j = a.c().a();
        eVar.k = Commons.getMNC(CMAdManager.getContext());
        eVar.l = CMAdManager.getChannelId();
        return eVar;
    }

    public void a(int i) {
        this.m = i;
    }

    public void a(Map<String, String> map) {
        this.n = map;
    }

    public e a(String str) {
        this.h = str;
        return this;
    }

    public String a() {
        String str;
        StringBuilder stringBuilder = new StringBuilder();
        StringBuilder append = stringBuilder.append("ac=" + this.c).append("&pos=" + this.a).append("&mid=" + this.b).append("&aid=" + this.d).append("&lan=" + this.e).append("&ext=" + this.f).append("&cmver=" + this.g);
        StringBuilder append2 = new StringBuilder().append("&mcc=");
        if (TextUtils.isEmpty(this.i)) {
            str = "";
        } else {
            str = this.i;
        }
        append = append.append(append2.append(str).toString());
        append2 = new StringBuilder().append("&mnc=");
        if (TextUtils.isEmpty(this.k)) {
            str = "";
        } else {
            str = this.k;
        }
        append.append(append2.append(str).toString()).append("&gaid=" + this.j).append("&pl=2").append("&v=20").append("&channelid=" + this.l).append("&lp=" + this.m);
        if (this.h != null) {
            stringBuilder.append("&rf=" + this.h);
        }
        if (!(this.n == null || this.n.isEmpty())) {
            for (String str2 : this.n.keySet()) {
                stringBuilder.append("&").append(str2).append("=").append((String) this.n.get(str2));
            }
        }
        return stringBuilder.toString();
    }
}

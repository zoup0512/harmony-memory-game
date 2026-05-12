package com.google.android.gms.internal;

import android.content.Context;
import android.webkit.WebView;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@zzin
public class zzhm implements zzhk {
    private final Context mContext;
    final Set<WebView> zzbwh = Collections.synchronizedSet(new HashSet());

    public zzhm(Context context) {
        this.mContext = context;
    }

    public void zza(String str, String str2, String str3) {
        zzb.zzcv("Fetching assets for the given html");
        zzkh.zzclc.post(new 1(this, str2, str3));
    }

    public WebView zzpl() {
        WebView webView = new WebView(this.mContext);
        webView.getSettings().setJavaScriptEnabled(true);
        return webView;
    }
}

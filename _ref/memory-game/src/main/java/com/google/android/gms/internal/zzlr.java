package com.google.android.gms.internal;

import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.common.internal.zzaa;
import java.net.URI;
import java.net.URISyntaxException;

@zzin
public class zzlr extends WebViewClient {
    private final zzlh zzbgf;
    private final zzhz zzbyg;
    private final String zzcqm;
    private boolean zzcqn = false;

    public zzlr(zzhz com_google_android_gms_internal_zzhz, zzlh com_google_android_gms_internal_zzlh, String str) {
        this.zzcqm = zzdf(str);
        this.zzbgf = com_google_android_gms_internal_zzlh;
        this.zzbyg = com_google_android_gms_internal_zzhz;
    }

    private String zzdf(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                if (str.endsWith("/")) {
                    str = str.substring(0, str.length() - 1);
                }
            } catch (IndexOutOfBoundsException e) {
                zzb.e(e.getMessage());
            }
        }
        return str;
    }

    public void onLoadResource(WebView webView, String str) {
        String str2 = "JavascriptAdWebViewClient::onLoadResource: ";
        String valueOf = String.valueOf(str);
        zzb.zzcv(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        if (!zzde(str)) {
            this.zzbgf.zzuj().onLoadResource(this.zzbgf.getWebView(), str);
        }
    }

    public void onPageFinished(WebView webView, String str) {
        String str2 = "JavascriptAdWebViewClient::onPageFinished: ";
        String valueOf = String.valueOf(str);
        zzb.zzcv(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        if (!this.zzcqn) {
            this.zzbyg.zzpz();
            this.zzcqn = true;
        }
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        String str2 = "JavascriptAdWebViewClient::shouldOverrideUrlLoading: ";
        String valueOf = String.valueOf(str);
        zzb.zzcv(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        if (!zzde(str)) {
            return this.zzbgf.zzuj().shouldOverrideUrlLoading(this.zzbgf.getWebView(), str);
        }
        zzb.zzcv("shouldOverrideUrlLoading: received passback url");
        return true;
    }

    protected boolean zzde(String str) {
        Object zzdf = zzdf(str);
        if (TextUtils.isEmpty(zzdf)) {
            return false;
        }
        try {
            URI uri = new URI(zzdf);
            if ("passback".equals(uri.getScheme())) {
                zzb.zzcv("Passback received");
                this.zzbyg.zzqa();
                return true;
            } else if (TextUtils.isEmpty(this.zzcqm)) {
                return false;
            } else {
                URI uri2 = new URI(this.zzcqm);
                String host = uri2.getHost();
                String host2 = uri.getHost();
                String path = uri2.getPath();
                String path2 = uri.getPath();
                if (!zzaa.equal(host, host2) || !zzaa.equal(path, path2)) {
                    return false;
                }
                zzb.zzcv("Passback received");
                this.zzbyg.zzqa();
                return true;
            }
        } catch (URISyntaxException e) {
            zzb.e(e.getMessage());
            return false;
        }
    }
}

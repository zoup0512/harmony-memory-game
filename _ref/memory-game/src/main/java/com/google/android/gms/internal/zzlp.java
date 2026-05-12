package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@TargetApi(11)
@zzin
public class zzlp extends zzli {
    public zzlp(zzlh com_google_android_gms_internal_zzlh, boolean z) {
        super(com_google_android_gms_internal_zzlh, z);
    }

    public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        Exception e;
        String valueOf;
        if (this.zzcot != null) {
            this.zzcot.zzcj(str);
        }
        String str2;
        try {
            if (!"mraid.js".equalsIgnoreCase(new File(str).getName())) {
                return super.shouldInterceptRequest(webView, str);
            }
            if (webView instanceof zzlh) {
                zzlh com_google_android_gms_internal_zzlh = (zzlh) webView;
                com_google_android_gms_internal_zzlh.zzuj().zznx();
                str2 = com_google_android_gms_internal_zzlh.zzdn().zzaus ? (String) zzdc.zzazd.get() : com_google_android_gms_internal_zzlh.zzun() ? (String) zzdc.zzazc.get() : (String) zzdc.zzazb.get();
                zzkd.v(new StringBuilder(String.valueOf(str2).length() + 24).append("shouldInterceptRequest(").append(str2).append(")").toString());
                return zzd(com_google_android_gms_internal_zzlh.getContext(), this.zzbgf.zzum().zzcs, str2);
            }
            zzb.zzcx("Tried to intercept request from a WebView that wasn't an AdWebView.");
            return super.shouldInterceptRequest(webView, str);
        } catch (IOException e2) {
            e = e2;
            str2 = "Could not fetch MRAID JS. ";
            valueOf = String.valueOf(e.getMessage());
            zzb.zzcx(valueOf.length() == 0 ? str2.concat(valueOf) : new String(str2));
            return super.shouldInterceptRequest(webView, str);
        } catch (ExecutionException e3) {
            e = e3;
            str2 = "Could not fetch MRAID JS. ";
            valueOf = String.valueOf(e.getMessage());
            if (valueOf.length() == 0) {
            }
            zzb.zzcx(valueOf.length() == 0 ? str2.concat(valueOf) : new String(str2));
            return super.shouldInterceptRequest(webView, str);
        } catch (InterruptedException e4) {
            e = e4;
            str2 = "Could not fetch MRAID JS. ";
            valueOf = String.valueOf(e.getMessage());
            if (valueOf.length() == 0) {
            }
            zzb.zzcx(valueOf.length() == 0 ? str2.concat(valueOf) : new String(str2));
            return super.shouldInterceptRequest(webView, str);
        } catch (TimeoutException e5) {
            e = e5;
            str2 = "Could not fetch MRAID JS. ";
            valueOf = String.valueOf(e.getMessage());
            if (valueOf.length() == 0) {
            }
            zzb.zzcx(valueOf.length() == 0 ? str2.concat(valueOf) : new String(str2));
            return super.shouldInterceptRequest(webView, str);
        }
    }

    protected WebResourceResponse zzd(Context context, String str, String str2) throws IOException, ExecutionException, InterruptedException, TimeoutException {
        Map hashMap = new HashMap();
        hashMap.put("User-Agent", zzu.zzfq().zzg(context, str));
        hashMap.put("Cache-Control", "max-stale=3600");
        String str3 = (String) new zzkn(context).zzc(str2, hashMap).get(60, TimeUnit.SECONDS);
        return str3 == null ? null : new WebResourceResponse(WebRequest.CONTENT_TYPE_JAVASCRIPT, "UTF-8", new ByteArrayInputStream(str3.getBytes("UTF-8")));
    }
}

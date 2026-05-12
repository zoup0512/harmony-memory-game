package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.DownloadManager.Request;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.net.http.SslError;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@TargetApi(8)
@zzin
public class zzki {
    private zzki() {
    }

    public static zzki zzay(int i) {
        return i >= 21 ? new zzh() : i >= 19 ? new zzg() : i >= 18 ? new zze() : i >= 17 ? new zzd() : i >= 16 ? new zzf() : i >= 14 ? new zzc() : i >= 11 ? new zzb() : i >= 9 ? new zza() : new zzki();
    }

    public String getDefaultUserAgent(Context context) {
        return "";
    }

    public boolean isAttachedToWindow(View view) {
        return (view.getWindowToken() == null && view.getWindowVisibility() == 8) ? false : true;
    }

    public Drawable zza(Context context, Bitmap bitmap, boolean z, float f) {
        return new BitmapDrawable(context.getResources(), bitmap);
    }

    public String zza(SslError sslError) {
        return "";
    }

    public void zza(View view, Drawable drawable) {
        view.setBackgroundDrawable(drawable);
    }

    public void zza(ViewTreeObserver viewTreeObserver, OnGlobalLayoutListener onGlobalLayoutListener) {
        viewTreeObserver.removeGlobalOnLayoutListener(onGlobalLayoutListener);
    }

    public boolean zza(Request request) {
        return false;
    }

    public boolean zza(Context context, WebSettings webSettings) {
        return false;
    }

    public boolean zza(Window window) {
        return false;
    }

    public CookieManager zzao(Context context) {
        CookieSyncManager.createInstance(context);
        return CookieManager.getInstance();
    }

    public zzli zzb(zzlh com_google_android_gms_internal_zzlh, boolean z) {
        return new zzli(com_google_android_gms_internal_zzlh, z);
    }

    public void zzb(Activity activity, OnGlobalLayoutListener onGlobalLayoutListener) {
        Window window = activity.getWindow();
        if (window != null && window.getDecorView() != null && window.getDecorView().getViewTreeObserver() != null) {
            zza(window.getDecorView().getViewTreeObserver(), onGlobalLayoutListener);
        }
    }

    public Set<String> zzg(Uri uri) {
        if (uri.isOpaque()) {
            return Collections.emptySet();
        }
        String encodedQuery = uri.getEncodedQuery();
        if (encodedQuery == null) {
            return Collections.emptySet();
        }
        Set linkedHashSet = new LinkedHashSet();
        int i = 0;
        do {
            int indexOf = encodedQuery.indexOf(38, i);
            if (indexOf == -1) {
                indexOf = encodedQuery.length();
            }
            int indexOf2 = encodedQuery.indexOf(61, i);
            if (indexOf2 > indexOf || indexOf2 == -1) {
                indexOf2 = indexOf;
            }
            linkedHashSet.add(Uri.decode(encodedQuery.substring(i, indexOf2)));
            i = indexOf + 1;
        } while (i < encodedQuery.length());
        return Collections.unmodifiableSet(linkedHashSet);
    }

    public boolean zzi(zzlh com_google_android_gms_internal_zzlh) {
        if (com_google_android_gms_internal_zzlh == null) {
            return false;
        }
        com_google_android_gms_internal_zzlh.onPause();
        return true;
    }

    public boolean zzj(zzlh com_google_android_gms_internal_zzlh) {
        if (com_google_android_gms_internal_zzlh == null) {
            return false;
        }
        com_google_android_gms_internal_zzlh.onResume();
        return true;
    }

    public WebChromeClient zzk(zzlh com_google_android_gms_internal_zzlh) {
        return null;
    }

    public boolean zzo(View view) {
        return false;
    }

    public boolean zzp(View view) {
        return false;
    }

    public int zztj() {
        return 0;
    }

    public int zztk() {
        return 1;
    }

    public int zztl() {
        return 5;
    }

    public LayoutParams zztm() {
        return new LayoutParams(-2, -2);
    }
}

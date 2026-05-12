package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import java.util.Map;
import org.json.JSONObject;

@zzin
class zzlk extends FrameLayout implements zzlh {
    private final zzlh zzcpa;
    private final zzlg zzcpb;

    public zzlk(zzlh com_google_android_gms_internal_zzlh) {
        super(com_google_android_gms_internal_zzlh.getContext());
        this.zzcpa = com_google_android_gms_internal_zzlh;
        this.zzcpb = new zzlg(com_google_android_gms_internal_zzlh.zzuf(), this, this);
        zzli zzuj = this.zzcpa.zzuj();
        if (zzuj != null) {
            zzuj.zzl(this);
        }
        addView(this.zzcpa.getView());
    }

    public void destroy() {
        this.zzcpa.destroy();
    }

    public String getRequestId() {
        return this.zzcpa.getRequestId();
    }

    public int getRequestedOrientation() {
        return this.zzcpa.getRequestedOrientation();
    }

    public View getView() {
        return this;
    }

    public WebView getWebView() {
        return this.zzcpa.getWebView();
    }

    public boolean isDestroyed() {
        return this.zzcpa.isDestroyed();
    }

    public void loadData(String str, String str2, String str3) {
        this.zzcpa.loadData(str, str2, str3);
    }

    public void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        this.zzcpa.loadDataWithBaseURL(str, str2, str3, str4, str5);
    }

    public void loadUrl(String str) {
        this.zzcpa.loadUrl(str);
    }

    public void onPause() {
        this.zzcpb.onPause();
        this.zzcpa.onPause();
    }

    public void onResume() {
        this.zzcpa.onResume();
    }

    public void setBackgroundColor(int i) {
        this.zzcpa.setBackgroundColor(i);
    }

    public void setContext(Context context) {
        this.zzcpa.setContext(context);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.zzcpa.setOnClickListener(onClickListener);
    }

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        this.zzcpa.setOnTouchListener(onTouchListener);
    }

    public void setRequestedOrientation(int i) {
        this.zzcpa.setRequestedOrientation(i);
    }

    public void setWebChromeClient(WebChromeClient webChromeClient) {
        this.zzcpa.setWebChromeClient(webChromeClient);
    }

    public void setWebViewClient(WebViewClient webViewClient) {
        this.zzcpa.setWebViewClient(webViewClient);
    }

    public void stopLoading() {
        this.zzcpa.stopLoading();
    }

    public void zza(Context context, AdSizeParcel adSizeParcel, zzdk com_google_android_gms_internal_zzdk) {
        this.zzcpb.onDestroy();
        this.zzcpa.zza(context, adSizeParcel, com_google_android_gms_internal_zzdk);
    }

    public void zza(AdSizeParcel adSizeParcel) {
        this.zzcpa.zza(adSizeParcel);
    }

    public void zza(zzcd com_google_android_gms_internal_zzcd, boolean z) {
        this.zzcpa.zza(com_google_android_gms_internal_zzcd, z);
    }

    public void zza(zzlm com_google_android_gms_internal_zzlm) {
        this.zzcpa.zza(com_google_android_gms_internal_zzlm);
    }

    public void zza(String str, zzep com_google_android_gms_internal_zzep) {
        this.zzcpa.zza(str, com_google_android_gms_internal_zzep);
    }

    public void zza(String str, Map<String, ?> map) {
        this.zzcpa.zza(str, (Map) map);
    }

    public void zza(String str, JSONObject jSONObject) {
        this.zzcpa.zza(str, jSONObject);
    }

    public void zzaf(int i) {
        this.zzcpa.zzaf(i);
    }

    public void zzah(boolean z) {
        this.zzcpa.zzah(z);
    }

    public void zzai(boolean z) {
        this.zzcpa.zzai(z);
    }

    public void zzaj(boolean z) {
        this.zzcpa.zzaj(z);
    }

    public void zzb(zzd com_google_android_gms_ads_internal_overlay_zzd) {
        this.zzcpa.zzb(com_google_android_gms_ads_internal_overlay_zzd);
    }

    public void zzb(String str, zzep com_google_android_gms_internal_zzep) {
        this.zzcpa.zzb(str, com_google_android_gms_internal_zzep);
    }

    public void zzb(String str, JSONObject jSONObject) {
        this.zzcpa.zzb(str, jSONObject);
    }

    public void zzc(zzd com_google_android_gms_ads_internal_overlay_zzd) {
        this.zzcpa.zzc(com_google_android_gms_ads_internal_overlay_zzd);
    }

    public void zzcy(String str) {
        this.zzcpa.zzcy(str);
    }

    public void zzcz(String str) {
        this.zzcpa.zzcz(str);
    }

    public AdSizeParcel zzdn() {
        return this.zzcpa.zzdn();
    }

    public void zzef() {
        this.zzcpa.zzef();
    }

    public void zzeg() {
        this.zzcpa.zzeg();
    }

    public void zzj(String str, String str2) {
        this.zzcpa.zzj(str, str2);
    }

    public void zzoa() {
        this.zzcpa.zzoa();
    }

    public boolean zzou() {
        return this.zzcpa.zzou();
    }

    public void zzuc() {
        this.zzcpa.zzuc();
    }

    public void zzud() {
        this.zzcpa.zzud();
    }

    public Activity zzue() {
        return this.zzcpa.zzue();
    }

    public Context zzuf() {
        return this.zzcpa.zzuf();
    }

    public com.google.android.gms.ads.internal.zzd zzug() {
        return this.zzcpa.zzug();
    }

    public zzd zzuh() {
        return this.zzcpa.zzuh();
    }

    public zzd zzui() {
        return this.zzcpa.zzui();
    }

    public zzli zzuj() {
        return this.zzcpa.zzuj();
    }

    public boolean zzuk() {
        return this.zzcpa.zzuk();
    }

    public zzas zzul() {
        return this.zzcpa.zzul();
    }

    public VersionInfoParcel zzum() {
        return this.zzcpa.zzum();
    }

    public boolean zzun() {
        return this.zzcpa.zzun();
    }

    public void zzuo() {
        this.zzcpb.onDestroy();
        this.zzcpa.zzuo();
    }

    public boolean zzup() {
        return this.zzcpa.zzup();
    }

    public zzlg zzuq() {
        return this.zzcpb;
    }

    public zzdi zzur() {
        return this.zzcpa.zzur();
    }

    public zzdj zzus() {
        return this.zzcpa.zzus();
    }

    public zzlm zzut() {
        return this.zzcpa.zzut();
    }

    public void zzuu() {
        this.zzcpa.zzuu();
    }

    public void zzuv() {
        this.zzcpa.zzuv();
    }

    public OnClickListener zzuw() {
        return this.zzcpa.zzuw();
    }
}

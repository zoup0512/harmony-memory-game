package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.MutableContextWrapper;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzd;
import com.google.android.gms.ads.internal.zzs;
import com.google.android.gms.ads.internal.zzu;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@zzin
class zzll extends WebView implements OnGlobalLayoutListener, DownloadListener, zzlh {
    private final Object zzail = new Object();
    private final zzd zzajv;
    private final VersionInfoParcel zzalo;
    private AdSizeParcel zzani;
    private zzku zzaqg;
    private final WindowManager zzaqm;
    @Nullable
    private final zzas zzbgd;
    private int zzbrf = -1;
    private int zzbrg = -1;
    private int zzbri = -1;
    private int zzbrj = -1;
    private String zzbvq = "";
    private Boolean zzcjw;
    private final zza zzcpc;
    private final zzs zzcpd;
    private zzli zzcpe;
    private com.google.android.gms.ads.internal.overlay.zzd zzcpf;
    private boolean zzcpg;
    private boolean zzcph;
    private boolean zzcpi;
    private boolean zzcpj;
    private int zzcpk;
    private boolean zzcpl = true;
    boolean zzcpm = false;
    private zzlm zzcpn;
    private boolean zzcpo;
    private zzdi zzcpp;
    private zzdi zzcpq;
    private zzdi zzcpr;
    private zzdj zzcps;
    private WeakReference<OnClickListener> zzcpt;
    private com.google.android.gms.ads.internal.overlay.zzd zzcpu;
    private Map<String, zzfd> zzcpv;

    @zzin
    public static class zza extends MutableContextWrapper {
        private Context zzaql;
        private Activity zzcmv;
        private Context zzcpx;

        public zza(Context context) {
            super(context);
            setBaseContext(context);
        }

        public Object getSystemService(String str) {
            return this.zzcpx.getSystemService(str);
        }

        public void setBaseContext(Context context) {
            this.zzaql = context.getApplicationContext();
            this.zzcmv = context instanceof Activity ? (Activity) context : null;
            this.zzcpx = context;
            super.setBaseContext(this.zzaql);
        }

        public void startActivity(Intent intent) {
            if (this.zzcmv != null) {
                this.zzcmv.startActivity(intent);
                return;
            }
            intent.setFlags(268435456);
            this.zzaql.startActivity(intent);
        }

        public Activity zzue() {
            return this.zzcmv;
        }

        public Context zzuf() {
            return this.zzcpx;
        }
    }

    protected zzll(zza com_google_android_gms_internal_zzll_zza, AdSizeParcel adSizeParcel, boolean z, boolean z2, @Nullable zzas com_google_android_gms_internal_zzas, VersionInfoParcel versionInfoParcel, zzdk com_google_android_gms_internal_zzdk, zzs com_google_android_gms_ads_internal_zzs, zzd com_google_android_gms_ads_internal_zzd) {
        super(com_google_android_gms_internal_zzll_zza);
        this.zzcpc = com_google_android_gms_internal_zzll_zza;
        this.zzani = adSizeParcel;
        this.zzcpi = z;
        this.zzcpk = -1;
        this.zzbgd = com_google_android_gms_internal_zzas;
        this.zzalo = versionInfoParcel;
        this.zzcpd = com_google_android_gms_ads_internal_zzs;
        this.zzajv = com_google_android_gms_ads_internal_zzd;
        this.zzaqm = (WindowManager) getContext().getSystemService("window");
        setBackgroundColor(0);
        WebSettings settings = getSettings();
        settings.setAllowFileAccess(false);
        settings.setJavaScriptEnabled(true);
        settings.setSavePassword(false);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        if (VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(2);
        }
        zzu.zzfq().zza((Context) com_google_android_gms_internal_zzll_zza, versionInfoParcel.zzcs, settings);
        zzu.zzfs().zza(getContext(), settings);
        setDownloadListener(this);
        zzvj();
        if (com.google.android.gms.common.util.zzs.zzavs()) {
            addJavascriptInterface(new zzln(this), "googleAdsJsInterface");
        }
        if (com.google.android.gms.common.util.zzs.zzavn()) {
            removeJavascriptInterface("accessibility");
            removeJavascriptInterface("accessibilityTraversal");
        }
        this.zzaqg = new zzku(this.zzcpc.zzue(), this, this, null);
        zzd(com_google_android_gms_internal_zzdk);
    }

    private void zzal(boolean z) {
        Map hashMap = new HashMap();
        hashMap.put("isVisible", z ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        zza("onAdVisibilityChanged", hashMap);
    }

    static zzll zzb(Context context, AdSizeParcel adSizeParcel, boolean z, boolean z2, @Nullable zzas com_google_android_gms_internal_zzas, VersionInfoParcel versionInfoParcel, zzdk com_google_android_gms_internal_zzdk, zzs com_google_android_gms_ads_internal_zzs, zzd com_google_android_gms_ads_internal_zzd) {
        return new zzll(new zza(context), adSizeParcel, z, z2, com_google_android_gms_internal_zzas, versionInfoParcel, com_google_android_gms_internal_zzdk, com_google_android_gms_ads_internal_zzs, com_google_android_gms_ads_internal_zzd);
    }

    private void zzd(zzdk com_google_android_gms_internal_zzdk) {
        zzvn();
        this.zzcps = new zzdj(new zzdk(true, "make_wv", this.zzani.zzaur));
        this.zzcps.zzkf().zzc(com_google_android_gms_internal_zzdk);
        this.zzcpq = zzdg.zzb(this.zzcps.zzkf());
        this.zzcps.zza("native:view_create", this.zzcpq);
        this.zzcpr = null;
        this.zzcpp = null;
    }

    private void zzvh() {
        synchronized (this.zzail) {
            this.zzcjw = zzu.zzft().zzsq();
            if (this.zzcjw == null) {
                try {
                    evaluateJavascript("(function(){})()", null);
                    zzb(Boolean.valueOf(true));
                } catch (IllegalStateException e) {
                    zzb(Boolean.valueOf(false));
                }
            }
        }
    }

    private void zzvi() {
        zzdg.zza(this.zzcps.zzkf(), this.zzcpq, "aeh2");
    }

    private void zzvj() {
        synchronized (this.zzail) {
            if (this.zzcpi || this.zzani.zzaus) {
                if (VERSION.SDK_INT < 14) {
                    zzb.zzcv("Disabling hardware acceleration on an overlay.");
                    zzvk();
                } else {
                    zzb.zzcv("Enabling hardware acceleration on an overlay.");
                    zzvl();
                }
            } else if (VERSION.SDK_INT < 18) {
                zzb.zzcv("Disabling hardware acceleration on an AdView.");
                zzvk();
            } else {
                zzb.zzcv("Enabling hardware acceleration on an AdView.");
                zzvl();
            }
        }
    }

    private void zzvk() {
        synchronized (this.zzail) {
            if (!this.zzcpj) {
                zzu.zzfs().zzp(this);
            }
            this.zzcpj = true;
        }
    }

    private void zzvl() {
        synchronized (this.zzail) {
            if (this.zzcpj) {
                zzu.zzfs().zzo(this);
            }
            this.zzcpj = false;
        }
    }

    private void zzvm() {
        synchronized (this.zzail) {
            this.zzcpv = null;
        }
    }

    private void zzvn() {
        if (this.zzcps != null) {
            zzdk zzkf = this.zzcps.zzkf();
            if (zzkf != null && zzu.zzft().zzsl() != null) {
                zzu.zzft().zzsl().zza(zzkf);
            }
        }
    }

    public void destroy() {
        synchronized (this.zzail) {
            zzvn();
            this.zzaqg.zztt();
            if (this.zzcpf != null) {
                this.zzcpf.close();
                this.zzcpf.onDestroy();
                this.zzcpf = null;
            }
            this.zzcpe.reset();
            if (this.zzcph) {
                return;
            }
            zzu.zzgj().zzd(this);
            zzvm();
            this.zzcph = true;
            zzkd.v("Initiating WebView self destruct sequence in 3...");
            this.zzcpe.zzuz();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.annotation.TargetApi(19)
    public void evaluateJavascript(java.lang.String r3, android.webkit.ValueCallback<java.lang.String> r4) {
        /*
        r2 = this;
        r1 = r2.zzail;
        monitor-enter(r1);
        r0 = r2.isDestroyed();	 Catch:{ all -> 0x001b }
        if (r0 == 0) goto L_0x0016;
    L_0x0009:
        r0 = "The webview is destroyed. Ignoring action.";
        com.google.android.gms.ads.internal.util.client.zzb.zzcx(r0);	 Catch:{ all -> 0x001b }
        if (r4 == 0) goto L_0x0014;
    L_0x0010:
        r0 = 0;
        r4.onReceiveValue(r0);	 Catch:{ all -> 0x001b }
    L_0x0014:
        monitor-exit(r1);	 Catch:{ all -> 0x001b }
    L_0x0015:
        return;
    L_0x0016:
        super.evaluateJavascript(r3, r4);	 Catch:{ all -> 0x001b }
        monitor-exit(r1);	 Catch:{ all -> 0x001b }
        goto L_0x0015;
    L_0x001b:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x001b }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzll.evaluateJavascript(java.lang.String, android.webkit.ValueCallback):void");
    }

    protected void finalize() throws Throwable {
        synchronized (this.zzail) {
            if (!this.zzcph) {
                this.zzcpe.reset();
                zzu.zzgj().zzd(this);
                zzvm();
            }
        }
        super.finalize();
    }

    public String getRequestId() {
        String str;
        synchronized (this.zzail) {
            str = this.zzbvq;
        }
        return str;
    }

    public int getRequestedOrientation() {
        int i;
        synchronized (this.zzail) {
            i = this.zzcpk;
        }
        return i;
    }

    public View getView() {
        return this;
    }

    public WebView getWebView() {
        return this;
    }

    public boolean isDestroyed() {
        boolean z;
        synchronized (this.zzail) {
            z = this.zzcph;
        }
        return z;
    }

    public void loadData(String str, String str2, String str3) {
        synchronized (this.zzail) {
            if (isDestroyed()) {
                zzb.zzcx("The webview is destroyed. Ignoring action.");
            } else {
                super.loadData(str, str2, str3);
            }
        }
    }

    public void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        synchronized (this.zzail) {
            if (isDestroyed()) {
                zzb.zzcx("The webview is destroyed. Ignoring action.");
            } else {
                super.loadDataWithBaseURL(str, str2, str3, str4, str5);
            }
        }
    }

    public void loadUrl(String str) {
        synchronized (this.zzail) {
            if (isDestroyed()) {
                zzb.zzcx("The webview is destroyed. Ignoring action.");
            } else {
                try {
                    super.loadUrl(str);
                } catch (Throwable th) {
                    String valueOf = String.valueOf(th);
                    zzb.zzcx(new StringBuilder(String.valueOf(valueOf).length() + 24).append("Could not call loadUrl. ").append(valueOf).toString());
                }
            }
        }
    }

    protected void onAttachedToWindow() {
        synchronized (this.zzail) {
            super.onAttachedToWindow();
            if (!isDestroyed()) {
                this.zzaqg.onAttachedToWindow();
            }
            zzal(this.zzcpo);
        }
    }

    protected void onDetachedFromWindow() {
        synchronized (this.zzail) {
            if (!isDestroyed()) {
                this.zzaqg.onDetachedFromWindow();
            }
            super.onDetachedFromWindow();
        }
        zzal(false);
    }

    public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.parse(str), str4);
            zzu.zzfq().zzb(getContext(), intent);
        } catch (ActivityNotFoundException e) {
            zzb.zzcv(new StringBuilder((String.valueOf(str).length() + 51) + String.valueOf(str4).length()).append("Couldn't find an Activity to view url/mimetype: ").append(str).append(" / ").append(str4).toString());
        }
    }

    @TargetApi(21)
    protected void onDraw(Canvas canvas) {
        if (!isDestroyed()) {
            if (VERSION.SDK_INT != 21 || !canvas.isHardwareAccelerated() || isAttachedToWindow()) {
                super.onDraw(canvas);
                if (zzuj() != null && zzuj().zzvf() != null) {
                    zzuj().zzvf().zzem();
                }
            }
        }
    }

    public void onGlobalLayout() {
        boolean zzvg = zzvg();
        com.google.android.gms.ads.internal.overlay.zzd zzuh = zzuh();
        if (zzuh != null && zzvg) {
            zzuh.zznz();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void onMeasure(int r10, int r11) {
        /*
        r9 = this;
        r0 = 2147483647; // 0x7fffffff float:NaN double:1.060997895E-314;
        r8 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r7 = 8;
        r6 = -2147483648; // 0xffffffff80000000 float:-0.0 double:NaN;
        r4 = r9.zzail;
        monitor-enter(r4);
        r1 = r9.isDestroyed();	 Catch:{ all -> 0x0034 }
        if (r1 == 0) goto L_0x0019;
    L_0x0012:
        r0 = 0;
        r1 = 0;
        r9.setMeasuredDimension(r0, r1);	 Catch:{ all -> 0x0034 }
        monitor-exit(r4);	 Catch:{ all -> 0x0034 }
    L_0x0018:
        return;
    L_0x0019:
        r1 = r9.isInEditMode();	 Catch:{ all -> 0x0034 }
        if (r1 != 0) goto L_0x002f;
    L_0x001f:
        r1 = r9.zzcpi;	 Catch:{ all -> 0x0034 }
        if (r1 != 0) goto L_0x002f;
    L_0x0023:
        r1 = r9.zzani;	 Catch:{ all -> 0x0034 }
        r1 = r1.zzauu;	 Catch:{ all -> 0x0034 }
        if (r1 != 0) goto L_0x002f;
    L_0x0029:
        r1 = r9.zzani;	 Catch:{ all -> 0x0034 }
        r1 = r1.zzauv;	 Catch:{ all -> 0x0034 }
        if (r1 == 0) goto L_0x0037;
    L_0x002f:
        super.onMeasure(r10, r11);	 Catch:{ all -> 0x0034 }
        monitor-exit(r4);	 Catch:{ all -> 0x0034 }
        goto L_0x0018;
    L_0x0034:
        r0 = move-exception;
        monitor-exit(r4);	 Catch:{ all -> 0x0034 }
        throw r0;
    L_0x0037:
        r1 = r9.zzani;	 Catch:{ all -> 0x0034 }
        r1 = r1.zzaus;	 Catch:{ all -> 0x0034 }
        if (r1 == 0) goto L_0x0054;
    L_0x003d:
        r0 = new android.util.DisplayMetrics;	 Catch:{ all -> 0x0034 }
        r0.<init>();	 Catch:{ all -> 0x0034 }
        r1 = r9.zzaqm;	 Catch:{ all -> 0x0034 }
        r1 = r1.getDefaultDisplay();	 Catch:{ all -> 0x0034 }
        r1.getMetrics(r0);	 Catch:{ all -> 0x0034 }
        r1 = r0.widthPixels;	 Catch:{ all -> 0x0034 }
        r0 = r0.heightPixels;	 Catch:{ all -> 0x0034 }
        r9.setMeasuredDimension(r1, r0);	 Catch:{ all -> 0x0034 }
        monitor-exit(r4);	 Catch:{ all -> 0x0034 }
        goto L_0x0018;
    L_0x0054:
        r2 = android.view.View.MeasureSpec.getMode(r10);	 Catch:{ all -> 0x0034 }
        r3 = android.view.View.MeasureSpec.getSize(r10);	 Catch:{ all -> 0x0034 }
        r5 = android.view.View.MeasureSpec.getMode(r11);	 Catch:{ all -> 0x0034 }
        r1 = android.view.View.MeasureSpec.getSize(r11);	 Catch:{ all -> 0x0034 }
        if (r2 == r6) goto L_0x0068;
    L_0x0066:
        if (r2 != r8) goto L_0x0101;
    L_0x0068:
        r2 = r3;
    L_0x0069:
        if (r5 == r6) goto L_0x006d;
    L_0x006b:
        if (r5 != r8) goto L_0x006e;
    L_0x006d:
        r0 = r1;
    L_0x006e:
        r5 = r9.zzani;	 Catch:{ all -> 0x0034 }
        r5 = r5.widthPixels;	 Catch:{ all -> 0x0034 }
        if (r5 > r2) goto L_0x007a;
    L_0x0074:
        r2 = r9.zzani;	 Catch:{ all -> 0x0034 }
        r2 = r2.heightPixels;	 Catch:{ all -> 0x0034 }
        if (r2 <= r0) goto L_0x00eb;
    L_0x007a:
        r0 = r9.zzcpc;	 Catch:{ all -> 0x0034 }
        r0 = r0.getResources();	 Catch:{ all -> 0x0034 }
        r0 = r0.getDisplayMetrics();	 Catch:{ all -> 0x0034 }
        r0 = r0.density;	 Catch:{ all -> 0x0034 }
        r2 = r9.zzani;	 Catch:{ all -> 0x0034 }
        r2 = r2.widthPixels;	 Catch:{ all -> 0x0034 }
        r2 = (float) r2;	 Catch:{ all -> 0x0034 }
        r2 = r2 / r0;
        r2 = (int) r2;	 Catch:{ all -> 0x0034 }
        r5 = r9.zzani;	 Catch:{ all -> 0x0034 }
        r5 = r5.heightPixels;	 Catch:{ all -> 0x0034 }
        r5 = (float) r5;	 Catch:{ all -> 0x0034 }
        r5 = r5 / r0;
        r5 = (int) r5;	 Catch:{ all -> 0x0034 }
        r3 = (float) r3;	 Catch:{ all -> 0x0034 }
        r3 = r3 / r0;
        r3 = (int) r3;	 Catch:{ all -> 0x0034 }
        r1 = (float) r1;	 Catch:{ all -> 0x0034 }
        r0 = r1 / r0;
        r0 = (int) r0;	 Catch:{ all -> 0x0034 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0034 }
        r6 = 103; // 0x67 float:1.44E-43 double:5.1E-322;
        r1.<init>(r6);	 Catch:{ all -> 0x0034 }
        r6 = "Not enough space to show ad. Needs ";
        r1 = r1.append(r6);	 Catch:{ all -> 0x0034 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0034 }
        r2 = "x";
        r1 = r1.append(r2);	 Catch:{ all -> 0x0034 }
        r1 = r1.append(r5);	 Catch:{ all -> 0x0034 }
        r2 = " dp, but only has ";
        r1 = r1.append(r2);	 Catch:{ all -> 0x0034 }
        r1 = r1.append(r3);	 Catch:{ all -> 0x0034 }
        r2 = "x";
        r1 = r1.append(r2);	 Catch:{ all -> 0x0034 }
        r0 = r1.append(r0);	 Catch:{ all -> 0x0034 }
        r1 = " dp.";
        r0 = r0.append(r1);	 Catch:{ all -> 0x0034 }
        r0 = r0.toString();	 Catch:{ all -> 0x0034 }
        com.google.android.gms.ads.internal.util.client.zzb.zzcx(r0);	 Catch:{ all -> 0x0034 }
        r0 = r9.getVisibility();	 Catch:{ all -> 0x0034 }
        if (r0 == r7) goto L_0x00e3;
    L_0x00df:
        r0 = 4;
        r9.setVisibility(r0);	 Catch:{ all -> 0x0034 }
    L_0x00e3:
        r0 = 0;
        r1 = 0;
        r9.setMeasuredDimension(r0, r1);	 Catch:{ all -> 0x0034 }
    L_0x00e8:
        monitor-exit(r4);	 Catch:{ all -> 0x0034 }
        goto L_0x0018;
    L_0x00eb:
        r0 = r9.getVisibility();	 Catch:{ all -> 0x0034 }
        if (r0 == r7) goto L_0x00f5;
    L_0x00f1:
        r0 = 0;
        r9.setVisibility(r0);	 Catch:{ all -> 0x0034 }
    L_0x00f5:
        r0 = r9.zzani;	 Catch:{ all -> 0x0034 }
        r0 = r0.widthPixels;	 Catch:{ all -> 0x0034 }
        r1 = r9.zzani;	 Catch:{ all -> 0x0034 }
        r1 = r1.heightPixels;	 Catch:{ all -> 0x0034 }
        r9.setMeasuredDimension(r0, r1);	 Catch:{ all -> 0x0034 }
        goto L_0x00e8;
    L_0x0101:
        r2 = r0;
        goto L_0x0069;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzll.onMeasure(int, int):void");
    }

    public void onPause() {
        if (!isDestroyed()) {
            try {
                if (com.google.android.gms.common.util.zzs.zzavn()) {
                    super.onPause();
                }
            } catch (Throwable e) {
                zzb.zzb("Could not pause webview.", e);
            }
        }
    }

    public void onResume() {
        if (!isDestroyed()) {
            try {
                if (com.google.android.gms.common.util.zzs.zzavn()) {
                    super.onResume();
                }
            } catch (Throwable e) {
                zzb.zzb("Could not resume webview.", e);
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.zzbgd != null) {
            this.zzbgd.zza(motionEvent);
        }
        return isDestroyed() ? false : super.onTouchEvent(motionEvent);
    }

    public void setContext(Context context) {
        this.zzcpc.setBaseContext(context);
        this.zzaqg.zzl(this.zzcpc.zzue());
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.zzcpt = new WeakReference(onClickListener);
        super.setOnClickListener(onClickListener);
    }

    public void setRequestedOrientation(int i) {
        synchronized (this.zzail) {
            this.zzcpk = i;
            if (this.zzcpf != null) {
                this.zzcpf.setRequestedOrientation(this.zzcpk);
            }
        }
    }

    public void setWebViewClient(WebViewClient webViewClient) {
        super.setWebViewClient(webViewClient);
        if (webViewClient instanceof zzli) {
            this.zzcpe = (zzli) webViewClient;
        }
    }

    public void stopLoading() {
        if (!isDestroyed()) {
            try {
                super.stopLoading();
            } catch (Throwable e) {
                zzb.zzb("Could not stop loading webview.", e);
            }
        }
    }

    public void zza(Context context, AdSizeParcel adSizeParcel, zzdk com_google_android_gms_internal_zzdk) {
        synchronized (this.zzail) {
            this.zzaqg.zztt();
            setContext(context);
            this.zzcpf = null;
            this.zzani = adSizeParcel;
            this.zzcpi = false;
            this.zzcpg = false;
            this.zzbvq = "";
            this.zzcpk = -1;
            zzu.zzfs().zzj(this);
            loadUrl("about:blank");
            this.zzcpe.reset();
            setOnTouchListener(null);
            setOnClickListener(null);
            this.zzcpl = true;
            this.zzcpm = false;
            this.zzcpn = null;
            zzd(com_google_android_gms_internal_zzdk);
            this.zzcpo = false;
            zzu.zzgj().zzd(this);
            zzvm();
        }
    }

    public void zza(AdSizeParcel adSizeParcel) {
        synchronized (this.zzail) {
            this.zzani = adSizeParcel;
            requestLayout();
        }
    }

    public void zza(zzcd com_google_android_gms_internal_zzcd, boolean z) {
        synchronized (this.zzail) {
            this.zzcpo = z;
        }
        zzal(z);
    }

    public void zza(zzlm com_google_android_gms_internal_zzlm) {
        synchronized (this.zzail) {
            if (this.zzcpn != null) {
                zzb.e("Attempt to create multiple AdWebViewVideoControllers.");
                return;
            }
            this.zzcpn = com_google_android_gms_internal_zzlm;
        }
    }

    @TargetApi(19)
    protected void zza(String str, ValueCallback<String> valueCallback) {
        synchronized (this.zzail) {
            if (isDestroyed()) {
                zzb.zzcx("The webview is destroyed. Ignoring action.");
                if (valueCallback != null) {
                    valueCallback.onReceiveValue(null);
                }
            } else {
                evaluateJavascript(str, valueCallback);
            }
        }
    }

    public void zza(String str, zzep com_google_android_gms_internal_zzep) {
        if (this.zzcpe != null) {
            this.zzcpe.zza(str, com_google_android_gms_internal_zzep);
        }
    }

    public void zza(String str, Map<String, ?> map) {
        try {
            zzb(str, zzu.zzfq().zzam((Map) map));
        } catch (JSONException e) {
            zzb.zzcx("Could not convert parameters to JSON.");
        }
    }

    public void zza(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        zzj(str, jSONObject.toString());
    }

    public void zzaf(int i) {
        zzvi();
        Map hashMap = new HashMap(2);
        hashMap.put("closetype", String.valueOf(i));
        hashMap.put("version", this.zzalo.zzcs);
        zza("onhide", hashMap);
    }

    public void zzah(boolean z) {
        synchronized (this.zzail) {
            this.zzcpi = z;
            zzvj();
        }
    }

    public void zzai(boolean z) {
        synchronized (this.zzail) {
            if (this.zzcpf != null) {
                this.zzcpf.zza(this.zzcpe.zzho(), z);
            } else {
                this.zzcpg = z;
            }
        }
    }

    public void zzaj(boolean z) {
        synchronized (this.zzail) {
            this.zzcpl = z;
        }
    }

    public void zzb(com.google.android.gms.ads.internal.overlay.zzd com_google_android_gms_ads_internal_overlay_zzd) {
        synchronized (this.zzail) {
            this.zzcpf = com_google_android_gms_ads_internal_overlay_zzd;
        }
    }

    void zzb(Boolean bool) {
        synchronized (this.zzail) {
            this.zzcjw = bool;
        }
        zzu.zzft().zzb(bool);
    }

    public void zzb(String str, zzep com_google_android_gms_internal_zzep) {
        if (this.zzcpe != null) {
            this.zzcpe.zzb(str, com_google_android_gms_internal_zzep);
        }
    }

    public void zzb(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        String jSONObject2 = jSONObject.toString();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("AFMA_ReceiveMessage('");
        stringBuilder.append(str);
        stringBuilder.append("'");
        stringBuilder.append(",");
        stringBuilder.append(jSONObject2);
        stringBuilder.append(");");
        String str2 = "Dispatching AFMA event: ";
        jSONObject2 = String.valueOf(stringBuilder.toString());
        zzkd.v(jSONObject2.length() != 0 ? str2.concat(jSONObject2) : new String(str2));
        zzdc(stringBuilder.toString());
    }

    public void zzc(com.google.android.gms.ads.internal.overlay.zzd com_google_android_gms_ads_internal_overlay_zzd) {
        synchronized (this.zzail) {
            this.zzcpu = com_google_android_gms_ads_internal_overlay_zzd;
        }
    }

    public void zzcy(String str) {
        synchronized (this.zzail) {
            try {
                super.loadUrl(str);
            } catch (Throwable th) {
                String valueOf = String.valueOf(th);
                zzb.zzcx(new StringBuilder(String.valueOf(valueOf).length() + 24).append("Could not call loadUrl. ").append(valueOf).toString());
            }
        }
    }

    public void zzcz(String str) {
        synchronized (this.zzail) {
            if (str == null) {
                str = "";
            }
            this.zzbvq = str;
        }
    }

    protected void zzdb(String str) {
        synchronized (this.zzail) {
            if (isDestroyed()) {
                zzb.zzcx("The webview is destroyed. Ignoring action.");
            } else {
                loadUrl(str);
            }
        }
    }

    protected void zzdc(String str) {
        if (com.google.android.gms.common.util.zzs.zzavu()) {
            if (zzsq() == null) {
                zzvh();
            }
            if (zzsq().booleanValue()) {
                zza(str, null);
                return;
            }
            String str2 = "javascript:";
            String valueOf = String.valueOf(str);
            zzdb(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            return;
        }
        str2 = "javascript:";
        valueOf = String.valueOf(str);
        zzdb(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
    }

    public AdSizeParcel zzdn() {
        AdSizeParcel adSizeParcel;
        synchronized (this.zzail) {
            adSizeParcel = this.zzani;
        }
        return adSizeParcel;
    }

    public void zzef() {
        synchronized (this.zzail) {
            this.zzcpm = true;
            if (this.zzcpd != null) {
                this.zzcpd.zzef();
            }
        }
    }

    public void zzeg() {
        synchronized (this.zzail) {
            this.zzcpm = false;
            if (this.zzcpd != null) {
                this.zzcpd.zzeg();
            }
        }
    }

    public void zzj(String str, String str2) {
        zzdc(new StringBuilder((String.valueOf(str).length() + 3) + String.valueOf(str2).length()).append(str).append("(").append(str2).append(");").toString());
    }

    public void zzoa() {
        if (this.zzcpp == null) {
            zzdg.zza(this.zzcps.zzkf(), this.zzcpr, "aes");
            this.zzcpp = zzdg.zzb(this.zzcps.zzkf());
            this.zzcps.zza("native:view_show", this.zzcpp);
        }
        Map hashMap = new HashMap(1);
        hashMap.put("version", this.zzalo.zzcs);
        zza("onshow", hashMap);
    }

    public boolean zzou() {
        boolean z;
        synchronized (this.zzail) {
            zzdg.zza(this.zzcps.zzkf(), this.zzcpq, "aebb2");
            z = this.zzcpl;
        }
        return z;
    }

    Boolean zzsq() {
        Boolean bool;
        synchronized (this.zzail) {
            bool = this.zzcjw;
        }
        return bool;
    }

    public void zzuc() {
        zzvi();
        Map hashMap = new HashMap(1);
        hashMap.put("version", this.zzalo.zzcs);
        zza("onhide", hashMap);
    }

    public void zzud() {
        Map hashMap = new HashMap(3);
        hashMap.put("app_muted", String.valueOf(zzu.zzfq().zzfa()));
        hashMap.put("app_volume", String.valueOf(zzu.zzfq().zzey()));
        hashMap.put("device_volume", String.valueOf(zzu.zzfq().zzal(getContext())));
        zza("volume", hashMap);
    }

    public Activity zzue() {
        return this.zzcpc.zzue();
    }

    public Context zzuf() {
        return this.zzcpc.zzuf();
    }

    public zzd zzug() {
        return this.zzajv;
    }

    public com.google.android.gms.ads.internal.overlay.zzd zzuh() {
        com.google.android.gms.ads.internal.overlay.zzd com_google_android_gms_ads_internal_overlay_zzd;
        synchronized (this.zzail) {
            com_google_android_gms_ads_internal_overlay_zzd = this.zzcpf;
        }
        return com_google_android_gms_ads_internal_overlay_zzd;
    }

    public com.google.android.gms.ads.internal.overlay.zzd zzui() {
        com.google.android.gms.ads.internal.overlay.zzd com_google_android_gms_ads_internal_overlay_zzd;
        synchronized (this.zzail) {
            com_google_android_gms_ads_internal_overlay_zzd = this.zzcpu;
        }
        return com_google_android_gms_ads_internal_overlay_zzd;
    }

    public zzli zzuj() {
        return this.zzcpe;
    }

    public boolean zzuk() {
        boolean z;
        synchronized (this.zzail) {
            z = this.zzcpg;
        }
        return z;
    }

    public zzas zzul() {
        return this.zzbgd;
    }

    public VersionInfoParcel zzum() {
        return this.zzalo;
    }

    public boolean zzun() {
        boolean z;
        synchronized (this.zzail) {
            z = this.zzcpi;
        }
        return z;
    }

    public void zzuo() {
        synchronized (this.zzail) {
            zzkd.v("Destroying WebView!");
            zzkh.zzclc.post(new 1(this));
        }
    }

    public boolean zzup() {
        boolean z;
        synchronized (this.zzail) {
            z = this.zzcpm;
        }
        return z;
    }

    public zzlg zzuq() {
        return null;
    }

    public zzdi zzur() {
        return this.zzcpr;
    }

    public zzdj zzus() {
        return this.zzcps;
    }

    public zzlm zzut() {
        zzlm com_google_android_gms_internal_zzlm;
        synchronized (this.zzail) {
            com_google_android_gms_internal_zzlm = this.zzcpn;
        }
        return com_google_android_gms_internal_zzlm;
    }

    public void zzuu() {
        this.zzaqg.zzts();
    }

    public void zzuv() {
        if (this.zzcpr == null) {
            this.zzcpr = zzdg.zzb(this.zzcps.zzkf());
            this.zzcps.zza("native:view_load", this.zzcpr);
        }
    }

    public OnClickListener zzuw() {
        return (OnClickListener) this.zzcpt.get();
    }

    public boolean zzvg() {
        if (!zzuj().zzho()) {
            return false;
        }
        int i;
        int i2;
        DisplayMetrics zza = zzu.zzfq().zza(this.zzaqm);
        int zzb = zzm.zziw().zzb(zza, zza.widthPixels);
        int zzb2 = zzm.zziw().zzb(zza, zza.heightPixels);
        Activity zzue = zzue();
        if (zzue == null || zzue.getWindow() == null) {
            i = zzb2;
            i2 = zzb;
        } else {
            int[] zzh = zzu.zzfq().zzh(zzue);
            i2 = zzm.zziw().zzb(zza, zzh[0]);
            i = zzm.zziw().zzb(zza, zzh[1]);
        }
        if (this.zzbrf == zzb && this.zzbrg == zzb2 && this.zzbri == i2 && this.zzbrj == i) {
            return false;
        }
        boolean z = (this.zzbrf == zzb && this.zzbrg == zzb2) ? false : true;
        this.zzbrf = zzb;
        this.zzbrg = zzb2;
        this.zzbri = i2;
        this.zzbrj = i;
        new zzhf(this).zza(zzb, zzb2, i2, i, zza.density, this.zzaqm.getDefaultDisplay().getRotation());
        return z;
    }
}

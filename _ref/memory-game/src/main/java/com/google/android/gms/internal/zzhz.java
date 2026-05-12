package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.internal.zzli.zza;

@zzin
public class zzhz implements Runnable {
    private final int zzaie;
    private final int zzaif;
    protected final zzlh zzbgf;
    private final Handler zzbxx;
    private final long zzbxy;
    private long zzbxz;
    private zza zzbya;
    protected boolean zzbyb;
    protected boolean zzbyc;

    public zzhz(zza com_google_android_gms_internal_zzli_zza, zzlh com_google_android_gms_internal_zzlh, int i, int i2) {
        this(com_google_android_gms_internal_zzli_zza, com_google_android_gms_internal_zzlh, i, i2, 200, 50);
    }

    public zzhz(zza com_google_android_gms_internal_zzli_zza, zzlh com_google_android_gms_internal_zzlh, int i, int i2, long j, long j2) {
        this.zzbxy = j;
        this.zzbxz = j2;
        this.zzbxx = new Handler(Looper.getMainLooper());
        this.zzbgf = com_google_android_gms_internal_zzlh;
        this.zzbya = com_google_android_gms_internal_zzli_zza;
        this.zzbyb = false;
        this.zzbyc = false;
        this.zzaif = i2;
        this.zzaie = i;
    }

    static /* synthetic */ long zzc(zzhz com_google_android_gms_internal_zzhz) {
        long j = com_google_android_gms_internal_zzhz.zzbxz - 1;
        com_google_android_gms_internal_zzhz.zzbxz = j;
        return j;
    }

    public void run() {
        if (this.zzbgf == null || zzqb()) {
            this.zzbya.zza(this.zzbgf, true);
        } else {
            new zza(this, this.zzbgf.getWebView()).execute(new Void[0]);
        }
    }

    public void zza(AdResponseParcel adResponseParcel) {
        zza(adResponseParcel, new zzlr(this, this.zzbgf, adResponseParcel.zzccf));
    }

    public void zza(AdResponseParcel adResponseParcel, zzlr com_google_android_gms_internal_zzlr) {
        this.zzbgf.setWebViewClient(com_google_android_gms_internal_zzlr);
        this.zzbgf.loadDataWithBaseURL(TextUtils.isEmpty(adResponseParcel.zzbto) ? null : zzu.zzfq().zzco(adResponseParcel.zzbto), adResponseParcel.body, "text/html", "UTF-8", null);
    }

    public void zzpz() {
        this.zzbxx.postDelayed(this, this.zzbxy);
    }

    public synchronized void zzqa() {
        this.zzbyb = true;
    }

    public synchronized boolean zzqb() {
        return this.zzbyb;
    }

    public boolean zzqc() {
        return this.zzbyc;
    }
}

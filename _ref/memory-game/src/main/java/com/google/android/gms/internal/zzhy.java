package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.internal.zzli.zza;
import java.util.concurrent.atomic.AtomicBoolean;

@zzin
public abstract class zzhy implements zzkj<Void>, zza {
    protected final Context mContext;
    protected final zzlh zzbgf;
    protected final zzic.zza zzbxq;
    protected final zzju.zza zzbxr;
    protected AdResponseParcel zzbxs;
    private Runnable zzbxt;
    protected final Object zzbxu = new Object();
    private AtomicBoolean zzbxv = new AtomicBoolean(true);

    protected zzhy(Context context, zzju.zza com_google_android_gms_internal_zzju_zza, zzlh com_google_android_gms_internal_zzlh, zzic.zza com_google_android_gms_internal_zzic_zza) {
        this.mContext = context;
        this.zzbxr = com_google_android_gms_internal_zzju_zza;
        this.zzbxs = this.zzbxr.zzciq;
        this.zzbgf = com_google_android_gms_internal_zzlh;
        this.zzbxq = com_google_android_gms_internal_zzic_zza;
    }

    private zzju zzak(int i) {
        AdRequestInfoParcel adRequestInfoParcel = this.zzbxr.zzcip;
        return new zzju(adRequestInfoParcel.zzcar, this.zzbgf, this.zzbxs.zzbnm, i, this.zzbxs.zzbnn, this.zzbxs.zzcca, this.zzbxs.orientation, this.zzbxs.zzbns, adRequestInfoParcel.zzcau, this.zzbxs.zzcby, null, null, null, null, null, this.zzbxs.zzcbz, this.zzbxr.zzapa, this.zzbxs.zzcbx, this.zzbxr.zzcik, this.zzbxs.zzccc, this.zzbxs.zzccd, this.zzbxr.zzcie, null, this.zzbxs.zzccn, this.zzbxs.zzcco, this.zzbxs.zzccp, this.zzbxs.zzccq, this.zzbxs.zzccr, null, this.zzbxs.zzbnp);
    }

    public void cancel() {
        if (this.zzbxv.getAndSet(false)) {
            this.zzbgf.stopLoading();
            zzu.zzfs().zzi(this.zzbgf);
            zzaj(-1);
            zzkh.zzclc.removeCallbacks(this.zzbxt);
        }
    }

    public void zza(zzlh com_google_android_gms_internal_zzlh, boolean z) {
        int i = 0;
        zzb.zzcv("WebView finished loading.");
        if (this.zzbxv.getAndSet(false)) {
            if (z) {
                i = zzpx();
            }
            zzaj(i);
            zzkh.zzclc.removeCallbacks(this.zzbxt);
        }
    }

    protected void zzaj(int i) {
        if (i != -2) {
            this.zzbxs = new AdResponseParcel(i, this.zzbxs.zzbns);
        }
        this.zzbgf.zzud();
        this.zzbxq.zzb(zzak(i));
    }

    public final Void zzpv() {
        zzab.zzhi("Webview render task needs to be called on UI thread.");
        this.zzbxt = new 1(this);
        zzkh.zzclc.postDelayed(this.zzbxt, ((Long) zzdc.zzbbh.get()).longValue());
        zzpw();
        return null;
    }

    protected abstract void zzpw();

    protected int zzpx() {
        return -2;
    }

    public /* synthetic */ Object zzpy() {
        return zzpv();
    }
}

package com.google.android.gms.ads.internal;

import android.content.Context;
import android.graphics.Rect;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.util.SimpleArrayMap;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.VideoOptionsParcel;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.ads.internal.client.zzp;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.ads.internal.client.zzw;
import com.google.android.gms.ads.internal.client.zzy;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.internal.purchase.zzk;
import com.google.android.gms.ads.internal.reward.client.zzd;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzas;
import com.google.android.gms.internal.zzdc;
import com.google.android.gms.internal.zzdo;
import com.google.android.gms.internal.zzeb;
import com.google.android.gms.internal.zzec;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzho;
import com.google.android.gms.internal.zzhs;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzju;
import com.google.android.gms.internal.zzju.zza;
import com.google.android.gms.internal.zzjv;
import com.google.android.gms.internal.zzka;
import com.google.android.gms.internal.zzkc;
import com.google.android.gms.internal.zzkj;
import com.google.android.gms.internal.zzkr;
import com.google.android.gms.internal.zzli;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

@zzin
public final class zzv implements OnGlobalLayoutListener, OnScrollChangedListener {
    public final Context zzagf;
    boolean zzame;
    final String zzaot;
    public String zzaou;
    final zzas zzaov;
    public final VersionInfoParcel zzaow;
    @Nullable
    zza zzaox;
    @Nullable
    public zzkc zzaoy;
    @Nullable
    public zzkj zzaoz;
    public AdSizeParcel zzapa;
    @Nullable
    public zzju zzapb;
    public zza zzapc;
    public zzjv zzapd;
    @Nullable
    zzp zzape;
    @Nullable
    zzq zzapf;
    @Nullable
    zzw zzapg;
    @Nullable
    zzy zzaph;
    @Nullable
    zzho zzapi;
    @Nullable
    zzhs zzapj;
    @Nullable
    zzeb zzapk;
    @Nullable
    zzec zzapl;
    SimpleArrayMap<String, zzed> zzapm;
    SimpleArrayMap<String, zzee> zzapn;
    NativeAdOptionsParcel zzapo;
    @Nullable
    VideoOptionsParcel zzapp;
    @Nullable
    zzdo zzapq;
    @Nullable
    zzd zzapr;
    @Nullable
    List<String> zzaps;
    @Nullable
    zzk zzapt;
    @Nullable
    public zzka zzapu;
    @Nullable
    View zzapv;
    public int zzapw;
    boolean zzapx;
    private HashSet<zzjv> zzapy;
    private int zzapz;
    private int zzaqa;
    private zzkr zzaqb;
    private boolean zzaqc;
    private boolean zzaqd;
    private boolean zzaqe;

    public zzv(Context context, AdSizeParcel adSizeParcel, String str, VersionInfoParcel versionInfoParcel) {
        this(context, adSizeParcel, str, versionInfoParcel, null);
    }

    zzv(Context context, AdSizeParcel adSizeParcel, String str, VersionInfoParcel versionInfoParcel, zzas com_google_android_gms_internal_zzas) {
        this.zzapu = null;
        this.zzapv = null;
        this.zzapw = 0;
        this.zzapx = false;
        this.zzame = false;
        this.zzapy = null;
        this.zzapz = -1;
        this.zzaqa = -1;
        this.zzaqc = true;
        this.zzaqd = true;
        this.zzaqe = false;
        zzdc.initialize(context);
        if (zzu.zzft().zzsl() != null) {
            List zzjy = zzdc.zzjy();
            if (versionInfoParcel.zzcnk != 0) {
                zzjy.add(Integer.toString(versionInfoParcel.zzcnk));
            }
            zzu.zzft().zzsl().zzc(zzjy);
        }
        this.zzaot = UUID.randomUUID().toString();
        if (adSizeParcel.zzaus || adSizeParcel.zzauu) {
            this.zzaox = null;
        } else {
            this.zzaox = new zza(context, this, this);
            this.zzaox.setMinimumWidth(adSizeParcel.widthPixels);
            this.zzaox.setMinimumHeight(adSizeParcel.heightPixels);
            this.zzaox.setVisibility(4);
        }
        this.zzapa = adSizeParcel;
        this.zzaou = str;
        this.zzagf = context;
        this.zzaow = versionInfoParcel;
        if (com_google_android_gms_internal_zzas == null) {
            com_google_android_gms_internal_zzas = new zzas(new zzi(this));
        }
        this.zzaov = com_google_android_gms_internal_zzas;
        this.zzaqb = new zzkr(200);
        this.zzapn = new SimpleArrayMap();
    }

    private void zzgs() {
        if (this.zzaox != null) {
            View findViewById = this.zzaox.getRootView().findViewById(16908290);
            if (findViewById != null) {
                Rect rect = new Rect();
                Rect rect2 = new Rect();
                this.zzaox.getGlobalVisibleRect(rect);
                findViewById.getGlobalVisibleRect(rect2);
                if (rect.top != rect2.top) {
                    this.zzaqc = false;
                }
                if (rect.bottom != rect2.bottom) {
                    this.zzaqd = false;
                }
            }
        }
    }

    private void zzh(boolean z) {
        boolean z2 = true;
        if (this.zzaox != null && this.zzapb != null && this.zzapb.zzbtm != null && this.zzapb.zzbtm.zzuj() != null) {
            if (!z || this.zzaqb.tryAcquire()) {
                if (this.zzapb.zzbtm.zzuj().zzho()) {
                    int[] iArr = new int[2];
                    this.zzaox.getLocationOnScreen(iArr);
                    int zzb = zzm.zziw().zzb(this.zzagf, iArr[0]);
                    int zzb2 = zzm.zziw().zzb(this.zzagf, iArr[1]);
                    if (!(zzb == this.zzapz && zzb2 == this.zzaqa)) {
                        this.zzapz = zzb;
                        this.zzaqa = zzb2;
                        zzli zzuj = this.zzapb.zzbtm.zzuj();
                        zzb = this.zzapz;
                        int i = this.zzaqa;
                        if (z) {
                            z2 = false;
                        }
                        zzuj.zza(zzb, i, z2);
                    }
                }
                zzgs();
            }
        }
    }

    public void destroy() {
        zzgr();
        this.zzapf = null;
        this.zzapg = null;
        this.zzapj = null;
        this.zzapi = null;
        this.zzapq = null;
        this.zzaph = null;
        zzi(false);
        if (this.zzaox != null) {
            this.zzaox.removeAllViews();
        }
        zzgm();
        zzgo();
        this.zzapb = null;
    }

    public void onGlobalLayout() {
        zzh(false);
    }

    public void onScrollChanged() {
        zzh(true);
        this.zzaqe = true;
    }

    public void zza(HashSet<zzjv> hashSet) {
        this.zzapy = hashSet;
    }

    public HashSet<zzjv> zzgl() {
        return this.zzapy;
    }

    public void zzgm() {
        if (this.zzapb != null && this.zzapb.zzbtm != null) {
            this.zzapb.zzbtm.destroy();
        }
    }

    public void zzgn() {
        if (this.zzapb != null && this.zzapb.zzbtm != null) {
            this.zzapb.zzbtm.stopLoading();
        }
    }

    public void zzgo() {
        if (this.zzapb != null && this.zzapb.zzboo != null) {
            try {
                this.zzapb.zzboo.destroy();
            } catch (RemoteException e) {
                zzb.zzcx("Could not destroy mediation adapter.");
            }
        }
    }

    public boolean zzgp() {
        return this.zzapw == 0;
    }

    public boolean zzgq() {
        return this.zzapw == 1;
    }

    public void zzgr() {
        if (this.zzaox != null) {
            this.zzaox.zzgr();
        }
    }

    public String zzgt() {
        return (this.zzaqc && this.zzaqd) ? "" : this.zzaqc ? this.zzaqe ? "top-scrollable" : "top-locked" : this.zzaqd ? this.zzaqe ? "bottom-scrollable" : "bottom-locked" : "";
    }

    public void zzgu() {
        if (this.zzapb != null) {
            this.zzapd.zzl(this.zzapb.zzcik);
            this.zzapd.zzm(this.zzapb.zzcil);
            this.zzapd.zzad(this.zzapb.zzcby);
        }
        this.zzapd.zzac(this.zzapa.zzaus);
    }

    public void zzi(boolean z) {
        if (this.zzapw == 0) {
            zzgn();
        }
        if (this.zzaoy != null) {
            this.zzaoy.cancel();
        }
        if (this.zzaoz != null) {
            this.zzaoz.cancel();
        }
        if (z) {
            this.zzapb = null;
        }
    }
}

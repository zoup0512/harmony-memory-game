package com.google.android.gms.ads.internal.formats;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzas;
import com.google.android.gms.internal.zzgn;
import com.google.android.gms.internal.zzgo;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzlh;
import java.lang.ref.WeakReference;
import java.util.Map;
import org.json.JSONObject;

@zzin
public class zzg extends zzi {
    private Object zzail;
    @Nullable
    private zzgn zzbfv;
    @Nullable
    private zzgo zzbfw;
    private final zzq zzbfx;
    @Nullable
    private zzh zzbfy;
    private boolean zzbfz;

    private zzg(Context context, zzq com_google_android_gms_ads_internal_zzq, zzas com_google_android_gms_internal_zzas) {
        super(context, com_google_android_gms_ads_internal_zzq, null, com_google_android_gms_internal_zzas, null, null, null, null);
        this.zzbfz = false;
        this.zzail = new Object();
        this.zzbfx = com_google_android_gms_ads_internal_zzq;
    }

    public zzg(Context context, zzq com_google_android_gms_ads_internal_zzq, zzas com_google_android_gms_internal_zzas, zzgn com_google_android_gms_internal_zzgn) {
        this(context, com_google_android_gms_ads_internal_zzq, com_google_android_gms_internal_zzas);
        this.zzbfv = com_google_android_gms_internal_zzgn;
    }

    public zzg(Context context, zzq com_google_android_gms_ads_internal_zzq, zzas com_google_android_gms_internal_zzas, zzgo com_google_android_gms_internal_zzgo) {
        this(context, com_google_android_gms_ads_internal_zzq, com_google_android_gms_internal_zzas);
        this.zzbfw = com_google_android_gms_internal_zzgo;
    }

    public void recordImpression() {
        zzab.zzhi("recordImpression must be called on the main UI thread.");
        synchronized (this.zzail) {
            zzq(true);
            if (this.zzbfy != null) {
                this.zzbfy.recordImpression();
                this.zzbfx.recordImpression();
            } else {
                try {
                    if (this.zzbfv != null && !this.zzbfv.getOverrideImpressionRecording()) {
                        this.zzbfv.recordImpression();
                        this.zzbfx.recordImpression();
                    } else if (!(this.zzbfw == null || this.zzbfw.getOverrideImpressionRecording())) {
                        this.zzbfw.recordImpression();
                        this.zzbfx.recordImpression();
                    }
                } catch (Throwable e) {
                    zzb.zzd("Failed to call recordImpression", e);
                }
            }
        }
    }

    @Nullable
    public zzb zza(OnClickListener onClickListener) {
        return null;
    }

    public void zza(View view, Map<String, WeakReference<View>> map, OnTouchListener onTouchListener, OnClickListener onClickListener) {
        synchronized (this.zzail) {
            this.zzbfz = true;
            try {
                if (this.zzbfv != null) {
                    this.zzbfv.zzl(zze.zzac(view));
                } else if (this.zzbfw != null) {
                    this.zzbfw.zzl(zze.zzac(view));
                }
            } catch (Throwable e) {
                zzb.zzd("Failed to call prepareAd", e);
            }
            this.zzbfz = false;
        }
    }

    public void zza(View view, Map<String, WeakReference<View>> map, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3) {
        zzab.zzhi("performClick must be called on the main UI thread.");
        synchronized (this.zzail) {
            if (this.zzbfy != null) {
                this.zzbfy.zza(view, map, jSONObject, jSONObject2, jSONObject3);
                this.zzbfx.onAdClicked();
            } else {
                try {
                    if (!(this.zzbfv == null || this.zzbfv.getOverrideClickHandling())) {
                        this.zzbfv.zzk(zze.zzac(view));
                        this.zzbfx.onAdClicked();
                    }
                    if (!(this.zzbfw == null || this.zzbfw.getOverrideClickHandling())) {
                        this.zzbfw.zzk(zze.zzac(view));
                        this.zzbfx.onAdClicked();
                    }
                } catch (Throwable e) {
                    zzb.zzd("Failed to call performClick", e);
                }
            }
        }
    }

    public void zzb(View view, Map<String, WeakReference<View>> map) {
        synchronized (this.zzail) {
            try {
                if (this.zzbfv != null) {
                    this.zzbfv.zzm(zze.zzac(view));
                } else if (this.zzbfw != null) {
                    this.zzbfw.zzm(zze.zzac(view));
                }
            } catch (Throwable e) {
                zzb.zzd("Failed to call untrackView", e);
            }
        }
    }

    public void zzc(@Nullable zzh com_google_android_gms_ads_internal_formats_zzh) {
        synchronized (this.zzail) {
            this.zzbfy = com_google_android_gms_ads_internal_formats_zzh;
        }
    }

    public boolean zzkz() {
        boolean z;
        synchronized (this.zzail) {
            z = this.zzbfz;
        }
        return z;
    }

    public zzh zzla() {
        zzh com_google_android_gms_ads_internal_formats_zzh;
        synchronized (this.zzail) {
            com_google_android_gms_ads_internal_formats_zzh = this.zzbfy;
        }
        return com_google_android_gms_ads_internal_formats_zzh;
    }

    @Nullable
    public zzlh zzlb() {
        return null;
    }
}

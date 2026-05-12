package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.formats.zzh;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.internal.zzcd.zza;
import com.google.android.gms.internal.zzcd.zzd;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.WeakHashMap;

@zzin
public class zzcg implements zzch {
    private final Object zzail = new Object();
    private final VersionInfoParcel zzalo;
    private final Context zzaql;
    private final WeakHashMap<zzju, zzcd> zzarm = new WeakHashMap();
    private final ArrayList<zzcd> zzarn = new ArrayList();
    private final zzfs zzaro;

    public zzcg(Context context, VersionInfoParcel versionInfoParcel, zzfs com_google_android_gms_internal_zzfs) {
        this.zzaql = context.getApplicationContext();
        this.zzalo = versionInfoParcel;
        this.zzaro = com_google_android_gms_internal_zzfs;
    }

    public zzcd zza(AdSizeParcel adSizeParcel, zzju com_google_android_gms_internal_zzju) {
        return zza(adSizeParcel, com_google_android_gms_internal_zzju, com_google_android_gms_internal_zzju.zzbtm.getView());
    }

    public zzcd zza(AdSizeParcel adSizeParcel, zzju com_google_android_gms_internal_zzju, View view) {
        return zza(adSizeParcel, com_google_android_gms_internal_zzju, new zzd(view, com_google_android_gms_internal_zzju), null);
    }

    public zzcd zza(AdSizeParcel adSizeParcel, zzju com_google_android_gms_internal_zzju, View view, zzft com_google_android_gms_internal_zzft) {
        return zza(adSizeParcel, com_google_android_gms_internal_zzju, new zzd(view, com_google_android_gms_internal_zzju), com_google_android_gms_internal_zzft);
    }

    public zzcd zza(AdSizeParcel adSizeParcel, zzju com_google_android_gms_internal_zzju, zzh com_google_android_gms_ads_internal_formats_zzh) {
        return zza(adSizeParcel, com_google_android_gms_internal_zzju, new zza(com_google_android_gms_ads_internal_formats_zzh), null);
    }

    public zzcd zza(AdSizeParcel adSizeParcel, zzju com_google_android_gms_internal_zzju, zzck com_google_android_gms_internal_zzck, @Nullable zzft com_google_android_gms_internal_zzft) {
        zzcd com_google_android_gms_internal_zzcd;
        synchronized (this.zzail) {
            if (zzh(com_google_android_gms_internal_zzju)) {
                com_google_android_gms_internal_zzcd = (zzcd) this.zzarm.get(com_google_android_gms_internal_zzju);
            } else {
                if (com_google_android_gms_internal_zzft != null) {
                    com_google_android_gms_internal_zzcd = new zzci(this.zzaql, adSizeParcel, com_google_android_gms_internal_zzju, this.zzalo, com_google_android_gms_internal_zzck, com_google_android_gms_internal_zzft);
                } else {
                    com_google_android_gms_internal_zzcd = new zzcj(this.zzaql, adSizeParcel, com_google_android_gms_internal_zzju, this.zzalo, com_google_android_gms_internal_zzck, this.zzaro);
                }
                com_google_android_gms_internal_zzcd.zza((zzch) this);
                this.zzarm.put(com_google_android_gms_internal_zzju, com_google_android_gms_internal_zzcd);
                this.zzarn.add(com_google_android_gms_internal_zzcd);
            }
        }
        return com_google_android_gms_internal_zzcd;
    }

    public void zza(zzcd com_google_android_gms_internal_zzcd) {
        synchronized (this.zzail) {
            if (!com_google_android_gms_internal_zzcd.zzha()) {
                this.zzarn.remove(com_google_android_gms_internal_zzcd);
                Iterator it = this.zzarm.entrySet().iterator();
                while (it.hasNext()) {
                    if (((Entry) it.next()).getValue() == com_google_android_gms_internal_zzcd) {
                        it.remove();
                    }
                }
            }
        }
    }

    public boolean zzh(zzju com_google_android_gms_internal_zzju) {
        boolean z;
        synchronized (this.zzail) {
            zzcd com_google_android_gms_internal_zzcd = (zzcd) this.zzarm.get(com_google_android_gms_internal_zzju);
            z = com_google_android_gms_internal_zzcd != null && com_google_android_gms_internal_zzcd.zzha();
        }
        return z;
    }

    public void zzi(zzju com_google_android_gms_internal_zzju) {
        synchronized (this.zzail) {
            zzcd com_google_android_gms_internal_zzcd = (zzcd) this.zzarm.get(com_google_android_gms_internal_zzju);
            if (com_google_android_gms_internal_zzcd != null) {
                com_google_android_gms_internal_zzcd.zzgy();
            }
        }
    }

    public void zzj(zzju com_google_android_gms_internal_zzju) {
        synchronized (this.zzail) {
            zzcd com_google_android_gms_internal_zzcd = (zzcd) this.zzarm.get(com_google_android_gms_internal_zzju);
            if (com_google_android_gms_internal_zzcd != null) {
                com_google_android_gms_internal_zzcd.stop();
            }
        }
    }

    public void zzk(zzju com_google_android_gms_internal_zzju) {
        synchronized (this.zzail) {
            zzcd com_google_android_gms_internal_zzcd = (zzcd) this.zzarm.get(com_google_android_gms_internal_zzju);
            if (com_google_android_gms_internal_zzcd != null) {
                com_google_android_gms_internal_zzcd.pause();
            }
        }
    }

    public void zzl(zzju com_google_android_gms_internal_zzju) {
        synchronized (this.zzail) {
            zzcd com_google_android_gms_internal_zzcd = (zzcd) this.zzarm.get(com_google_android_gms_internal_zzju);
            if (com_google_android_gms_internal_zzcd != null) {
                com_google_android_gms_internal_zzcd.resume();
            }
        }
    }
}

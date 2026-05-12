package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.internal.zzla.zza;
import com.google.android.gms.internal.zzla.zzb;
import com.google.android.gms.internal.zzla.zzc;

public class zzfs$zzd extends zzlb<zzfp> {
    private final Object zzail = new Object();
    private zzkl<zzfp> zzblt;
    private boolean zzbml;
    private int zzbmm;

    public zzfs$zzd(zzkl<zzfp> com_google_android_gms_internal_zzkl_com_google_android_gms_internal_zzfp) {
        this.zzblt = com_google_android_gms_internal_zzkl_com_google_android_gms_internal_zzfp;
        this.zzbml = false;
        this.zzbmm = 0;
    }

    public zzfs$zzc zzmb() {
        final zzfs$zzc com_google_android_gms_internal_zzfs_zzc = new zzfs$zzc(this);
        synchronized (this.zzail) {
            zza(new zzc<zzfp>(this) {
                final /* synthetic */ zzfs$zzd zzbmo;

                public void zza(zzfp com_google_android_gms_internal_zzfp) {
                    zzkd.v("Getting a new session for JS Engine.");
                    com_google_android_gms_internal_zzfs_zzc.zzg(com_google_android_gms_internal_zzfp.zzly());
                }

                public /* synthetic */ void zzd(Object obj) {
                    zza((zzfp) obj);
                }
            }, new zza(this) {
                final /* synthetic */ zzfs$zzd zzbmo;

                public void run() {
                    zzkd.v("Rejecting reference for JS Engine.");
                    com_google_android_gms_internal_zzfs_zzc.reject();
                }
            });
            zzab.zzbn(this.zzbmm >= 0);
            this.zzbmm++;
        }
        return com_google_android_gms_internal_zzfs_zzc;
    }

    protected void zzmc() {
        boolean z = true;
        synchronized (this.zzail) {
            if (this.zzbmm < 1) {
                z = false;
            }
            zzab.zzbn(z);
            zzkd.v("Releasing 1 reference for JS Engine");
            this.zzbmm--;
            zzme();
        }
    }

    public void zzmd() {
        boolean z = true;
        synchronized (this.zzail) {
            if (this.zzbmm < 0) {
                z = false;
            }
            zzab.zzbn(z);
            zzkd.v("Releasing root reference. JS Engine will be destroyed once other references are released.");
            this.zzbml = true;
            zzme();
        }
    }

    protected void zzme() {
        synchronized (this.zzail) {
            zzab.zzbn(this.zzbmm >= 0);
            if (this.zzbml && this.zzbmm == 0) {
                zzkd.v("No reference is left (including root). Cleaning up engine.");
                zza(new zzc<zzfp>(this) {
                    final /* synthetic */ zzfs$zzd zzbmo;

                    {
                        this.zzbmo = r1;
                    }

                    public void zza(final zzfp com_google_android_gms_internal_zzfp) {
                        zzu.zzfq().runOnUiThread(new Runnable(this) {
                            final /* synthetic */ AnonymousClass3 zzbmq;

                            public void run() {
                                this.zzbmq.zzbmo.zzblt.zzd(com_google_android_gms_internal_zzfp);
                                com_google_android_gms_internal_zzfp.destroy();
                            }
                        });
                    }

                    public /* synthetic */ void zzd(Object obj) {
                        zza((zzfp) obj);
                    }
                }, new zzb());
            } else {
                zzkd.v("There are still references to the engine. Not destroying.");
            }
        }
    }
}

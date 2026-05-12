package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import com.google.android.gms.internal.zzju.zza;
import com.yalantis.ucrop.util.FileUtils;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@zzin
public class zzif extends zzib {
    private final zzdk zzajn;
    private zzgj zzajz;
    private final zzlh zzbgf;
    private zzga zzboe;
    zzfy zzbym;
    protected zzge zzbyn;
    private boolean zzbyo;

    zzif(Context context, zza com_google_android_gms_internal_zzju_zza, zzgj com_google_android_gms_internal_zzgj, zzic.zza com_google_android_gms_internal_zzic_zza, zzdk com_google_android_gms_internal_zzdk, zzlh com_google_android_gms_internal_zzlh) {
        super(context, com_google_android_gms_internal_zzju_zza, com_google_android_gms_internal_zzic_zza);
        this.zzajz = com_google_android_gms_internal_zzgj;
        this.zzboe = com_google_android_gms_internal_zzju_zza.zzcig;
        this.zzajn = com_google_android_gms_internal_zzdk;
        this.zzbgf = com_google_android_gms_internal_zzlh;
    }

    private static String zza(zzge com_google_android_gms_internal_zzge) {
        String str = com_google_android_gms_internal_zzge.zzbon.zzbmx;
        int zzal = zzal(com_google_android_gms_internal_zzge.zzbom);
        return new StringBuilder(String.valueOf(str).length() + 33).append(str).append(FileUtils.HIDDEN_PREFIX).append(zzal).append(FileUtils.HIDDEN_PREFIX).append(com_google_android_gms_internal_zzge.zzbos).toString();
    }

    private static int zzal(int i) {
        switch (i) {
            case -1:
                return 4;
            case 0:
                return 0;
            case 1:
                return 1;
            case 3:
                return 2;
            case 4:
                return 3;
            case 5:
                return 5;
            default:
                return 6;
        }
    }

    private static String zzg(List<zzge> list) {
        String str = "";
        if (list == null) {
            return str.toString();
        }
        String str2 = str;
        for (zzge com_google_android_gms_internal_zzge : list) {
            if (!(com_google_android_gms_internal_zzge == null || com_google_android_gms_internal_zzge.zzbon == null || TextUtils.isEmpty(com_google_android_gms_internal_zzge.zzbon.zzbmx))) {
                str2 = String.valueOf(str2);
                str = String.valueOf(zza(com_google_android_gms_internal_zzge));
                str2 = new StringBuilder((String.valueOf(str2).length() + 1) + String.valueOf(str).length()).append(str2).append(str).append(EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR).toString();
            }
        }
        return str2.substring(0, Math.max(0, str2.length() - 1));
    }

    private void zzqf() throws zzib.zza {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        zzkh.zzclc.post(new 1(this, countDownLatch));
        try {
            countDownLatch.await(10, TimeUnit.SECONDS);
            synchronized (this.zzbxu) {
                if (!this.zzbyo) {
                    throw new zzib.zza("View could not be prepared", 0);
                } else if (this.zzbgf.isDestroyed()) {
                    throw new zzib.zza("Assets not loaded, web view is destroyed", 0);
                }
            }
        } catch (InterruptedException e) {
            String valueOf = String.valueOf(e);
            throw new zzib.zza(new StringBuilder(String.valueOf(valueOf).length() + 38).append("Interrupted while waiting for latch : ").append(valueOf).toString(), 0);
        }
    }

    public void onStop() {
        synchronized (this.zzbxu) {
            super.onStop();
            if (this.zzbym != null) {
                this.zzbym.cancel();
            }
        }
    }

    protected zzju zzak(int i) {
        AdRequestInfoParcel adRequestInfoParcel = this.zzbxr.zzcip;
        return new zzju(adRequestInfoParcel.zzcar, this.zzbgf, this.zzbxs.zzbnm, i, this.zzbxs.zzbnn, this.zzbxs.zzcca, this.zzbxs.orientation, this.zzbxs.zzbns, adRequestInfoParcel.zzcau, this.zzbxs.zzcby, this.zzbyn != null ? this.zzbyn.zzbon : null, this.zzbyn != null ? this.zzbyn.zzboo : null, this.zzbyn != null ? this.zzbyn.zzbop : AdMobAdapter.class.getName(), this.zzboe, this.zzbyn != null ? this.zzbyn.zzboq : null, this.zzbxs.zzcbz, this.zzbxr.zzapa, this.zzbxs.zzcbx, this.zzbxr.zzcik, this.zzbxs.zzccc, this.zzbxs.zzccd, this.zzbxr.zzcie, null, this.zzbxs.zzccn, this.zzbxs.zzcco, this.zzbxs.zzccp, this.zzboe != null ? this.zzboe.zzbnx : false, this.zzbxs.zzccr, this.zzbym != null ? zzg(this.zzbym.zzmg()) : null, this.zzbxs.zzbnp);
    }

    protected void zzh(long j) throws zzib.zza {
        boolean z;
        ListIterator listIterator;
        synchronized (this.zzbxu) {
            this.zzbym = zzi(j);
        }
        List arrayList = new ArrayList(this.zzboe.zzbnk);
        Bundle bundle = this.zzbxr.zzcip.zzcar.zzatw;
        String str = "com.google.ads.mediation.admob.AdMobAdapter";
        if (bundle != null) {
            bundle = bundle.getBundle(str);
            if (bundle != null) {
                z = bundle.getBoolean("_skipMediation");
                if (z) {
                    listIterator = arrayList.listIterator();
                    while (listIterator.hasNext()) {
                        if (!((zzfz) listIterator.next()).zzbmw.contains(str)) {
                            listIterator.remove();
                        }
                    }
                }
                this.zzbyn = this.zzbym.zzd(arrayList);
                switch (this.zzbyn.zzbom) {
                    case 0:
                        if (this.zzbyn.zzbon != null && this.zzbyn.zzbon.zzbnf != null) {
                            zzqf();
                            return;
                        }
                        return;
                    case 1:
                        throw new zzib.zza("No fill from any mediation ad networks.", 3);
                    default:
                        throw new zzib.zza("Unexpected mediation result: " + this.zzbyn.zzbom, 0);
                }
            }
        }
        z = false;
        if (z) {
            listIterator = arrayList.listIterator();
            while (listIterator.hasNext()) {
                if (!((zzfz) listIterator.next()).zzbmw.contains(str)) {
                    listIterator.remove();
                }
            }
        }
        this.zzbyn = this.zzbym.zzd(arrayList);
        switch (this.zzbyn.zzbom) {
            case 0:
                if (this.zzbyn.zzbon != null) {
                    return;
                }
                return;
            case 1:
                throw new zzib.zza("No fill from any mediation ad networks.", 3);
            default:
                throw new zzib.zza("Unexpected mediation result: " + this.zzbyn.zzbom, 0);
        }
    }

    zzfy zzi(long j) {
        if (this.zzboe.zzbnv != -1) {
            return new zzgg(this.mContext, this.zzbxr.zzcip, this.zzajz, this.zzboe, this.zzbxs.zzauu, this.zzbxs.zzauw, j, ((Long) zzdc.zzbbh.get()).longValue(), 2);
        }
        return new zzgh(this.mContext, this.zzbxr.zzcip, this.zzajz, this.zzboe, this.zzbxs.zzauu, this.zzbxs.zzauw, j, ((Long) zzdc.zzbbh.get()).longValue(), this.zzajn);
    }
}

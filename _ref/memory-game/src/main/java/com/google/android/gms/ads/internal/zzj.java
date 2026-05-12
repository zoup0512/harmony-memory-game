package com.google.android.gms.ads.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.util.SimpleArrayMap;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.ads.internal.client.zzr.zza;
import com.google.android.gms.ads.internal.client.zzy;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.internal.zzeb;
import com.google.android.gms.internal.zzec;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzgj;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzkh;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

@zzin
public class zzj extends zza {
    private final Context mContext;
    private final Object zzail = new Object();
    private final zzd zzajv;
    private final zzgj zzajz;
    private final zzq zzalf;
    @Nullable
    private final zzeb zzalg;
    @Nullable
    private final zzec zzalh;
    private final SimpleArrayMap<String, zzee> zzali;
    private final SimpleArrayMap<String, zzed> zzalj;
    private final NativeAdOptionsParcel zzalk;
    private final List<String> zzall;
    private final zzy zzalm;
    private final String zzaln;
    private final VersionInfoParcel zzalo;
    @Nullable
    private WeakReference<zzq> zzalp;

    zzj(Context context, String str, zzgj com_google_android_gms_internal_zzgj, VersionInfoParcel versionInfoParcel, zzq com_google_android_gms_ads_internal_client_zzq, zzeb com_google_android_gms_internal_zzeb, zzec com_google_android_gms_internal_zzec, SimpleArrayMap<String, zzee> simpleArrayMap, SimpleArrayMap<String, zzed> simpleArrayMap2, NativeAdOptionsParcel nativeAdOptionsParcel, zzy com_google_android_gms_ads_internal_client_zzy, zzd com_google_android_gms_ads_internal_zzd) {
        this.mContext = context;
        this.zzaln = str;
        this.zzajz = com_google_android_gms_internal_zzgj;
        this.zzalo = versionInfoParcel;
        this.zzalf = com_google_android_gms_ads_internal_client_zzq;
        this.zzalh = com_google_android_gms_internal_zzec;
        this.zzalg = com_google_android_gms_internal_zzeb;
        this.zzali = simpleArrayMap;
        this.zzalj = simpleArrayMap2;
        this.zzalk = nativeAdOptionsParcel;
        this.zzall = zzeq();
        this.zzalm = com_google_android_gms_ads_internal_client_zzy;
        this.zzajv = com_google_android_gms_ads_internal_zzd;
    }

    private List<String> zzeq() {
        List<String> arrayList = new ArrayList();
        if (this.zzalh != null) {
            arrayList.add(AppEventsConstants.EVENT_PARAM_VALUE_YES);
        }
        if (this.zzalg != null) {
            arrayList.add("2");
        }
        if (this.zzali.size() > 0) {
            arrayList.add("3");
        }
        return arrayList;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.support.annotation.Nullable
    public java.lang.String getMediationAdapterClassName() {
        /*
        r3 = this;
        r1 = 0;
        r2 = r3.zzail;
        monitor-enter(r2);
        r0 = r3.zzalp;	 Catch:{ all -> 0x001d }
        if (r0 == 0) goto L_0x001a;
    L_0x0008:
        r0 = r3.zzalp;	 Catch:{ all -> 0x001d }
        r0 = r0.get();	 Catch:{ all -> 0x001d }
        r0 = (com.google.android.gms.ads.internal.zzq) r0;	 Catch:{ all -> 0x001d }
        if (r0 == 0) goto L_0x0018;
    L_0x0012:
        r0 = r0.getMediationAdapterClassName();	 Catch:{ all -> 0x001d }
    L_0x0016:
        monitor-exit(r2);	 Catch:{ all -> 0x001d }
    L_0x0017:
        return r0;
    L_0x0018:
        r0 = r1;
        goto L_0x0016;
    L_0x001a:
        monitor-exit(r2);	 Catch:{ all -> 0x001d }
        r0 = r1;
        goto L_0x0017;
    L_0x001d:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x001d }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.zzj.getMediationAdapterClassName():java.lang.String");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean isLoading() {
        /*
        r3 = this;
        r1 = 0;
        r2 = r3.zzail;
        monitor-enter(r2);
        r0 = r3.zzalp;	 Catch:{ all -> 0x001d }
        if (r0 == 0) goto L_0x001a;
    L_0x0008:
        r0 = r3.zzalp;	 Catch:{ all -> 0x001d }
        r0 = r0.get();	 Catch:{ all -> 0x001d }
        r0 = (com.google.android.gms.ads.internal.zzq) r0;	 Catch:{ all -> 0x001d }
        if (r0 == 0) goto L_0x0018;
    L_0x0012:
        r0 = r0.isLoading();	 Catch:{ all -> 0x001d }
    L_0x0016:
        monitor-exit(r2);	 Catch:{ all -> 0x001d }
    L_0x0017:
        return r0;
    L_0x0018:
        r0 = r1;
        goto L_0x0016;
    L_0x001a:
        monitor-exit(r2);	 Catch:{ all -> 0x001d }
        r0 = r1;
        goto L_0x0017;
    L_0x001d:
        r0 = move-exception;
        monitor-exit(r2);	 Catch:{ all -> 0x001d }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.zzj.isLoading():boolean");
    }

    protected void runOnUiThread(Runnable runnable) {
        zzkh.zzclc.post(runnable);
    }

    protected zzq zzer() {
        return new zzq(this.mContext, this.zzajv, AdSizeParcel.zzk(this.mContext), this.zzaln, this.zzajz, this.zzalo);
    }

    public void zzf(AdRequestParcel adRequestParcel) {
        runOnUiThread(new 1(this, adRequestParcel));
    }
}

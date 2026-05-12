package com.google.android.gms.ads.internal;

import android.content.Context;
import android.support.v4.util.SimpleArrayMap;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.client.zzq;
import com.google.android.gms.ads.internal.client.zzr;
import com.google.android.gms.ads.internal.client.zzs.zza;
import com.google.android.gms.ads.internal.client.zzy;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.internal.zzeb;
import com.google.android.gms.internal.zzec;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzee;
import com.google.android.gms.internal.zzgj;
import com.google.android.gms.internal.zzin;

@zzin
public class zzk extends zza {
    private final Context mContext;
    private final zzd zzajv;
    private final zzgj zzajz;
    private zzq zzalf;
    private NativeAdOptionsParcel zzalk;
    private zzy zzalm;
    private final String zzaln;
    private final VersionInfoParcel zzalo;
    private zzeb zzals;
    private zzec zzalt;
    private SimpleArrayMap<String, zzed> zzalu = new SimpleArrayMap();
    private SimpleArrayMap<String, zzee> zzalv = new SimpleArrayMap();

    public zzk(Context context, String str, zzgj com_google_android_gms_internal_zzgj, VersionInfoParcel versionInfoParcel, zzd com_google_android_gms_ads_internal_zzd) {
        this.mContext = context;
        this.zzaln = str;
        this.zzajz = com_google_android_gms_internal_zzgj;
        this.zzalo = versionInfoParcel;
        this.zzajv = com_google_android_gms_ads_internal_zzd;
    }

    public void zza(NativeAdOptionsParcel nativeAdOptionsParcel) {
        this.zzalk = nativeAdOptionsParcel;
    }

    public void zza(zzeb com_google_android_gms_internal_zzeb) {
        this.zzals = com_google_android_gms_internal_zzeb;
    }

    public void zza(zzec com_google_android_gms_internal_zzec) {
        this.zzalt = com_google_android_gms_internal_zzec;
    }

    public void zza(String str, zzee com_google_android_gms_internal_zzee, zzed com_google_android_gms_internal_zzed) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Custom template ID for native custom template ad is empty. Please provide a valid template id.");
        }
        this.zzalv.put(str, com_google_android_gms_internal_zzee);
        this.zzalu.put(str, com_google_android_gms_internal_zzed);
    }

    public void zzb(zzq com_google_android_gms_ads_internal_client_zzq) {
        this.zzalf = com_google_android_gms_ads_internal_client_zzq;
    }

    public void zzb(zzy com_google_android_gms_ads_internal_client_zzy) {
        this.zzalm = com_google_android_gms_ads_internal_client_zzy;
    }

    public zzr zzes() {
        return new zzj(this.mContext, this.zzaln, this.zzajz, this.zzalo, this.zzalf, this.zzals, this.zzalt, this.zzalv, this.zzalu, this.zzalk, this.zzalm, this.zzajv);
    }
}

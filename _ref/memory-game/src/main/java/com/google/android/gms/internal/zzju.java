package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.request.AutoClickProtectionConfigurationParcel;
import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import java.util.Collections;
import java.util.List;
import org.json.JSONObject;

@zzin
public class zzju {
    public final int errorCode;
    public final int orientation;
    public final List<String> zzbnm;
    public final List<String> zzbnn;
    @Nullable
    public final List<String> zzbnp;
    public final long zzbns;
    @Nullable
    public final zzfz zzbon;
    @Nullable
    public final zzgk zzboo;
    @Nullable
    public final String zzbop;
    @Nullable
    public final zzgc zzboq;
    @Nullable
    public final zzlh zzbtm;
    public final AdRequestParcel zzcar;
    public final String zzcau;
    public final long zzcbx;
    public final boolean zzcby;
    public final long zzcbz;
    public final List<String> zzcca;
    public final String zzccd;
    @Nullable
    public final RewardItemParcel zzccn;
    @Nullable
    public final List<String> zzccp;
    public final boolean zzccq;
    public final AutoClickProtectionConfigurationParcel zzccr;
    public final JSONObject zzcie;
    public boolean zzcif;
    public final zzga zzcig;
    @Nullable
    public final String zzcih;
    public final AdSizeParcel zzcii;
    @Nullable
    public final List<String> zzcij;
    public final long zzcik;
    public final long zzcil;
    @Nullable
    public final com.google.android.gms.ads.internal.formats.zzh.zza zzcim;
    public boolean zzcin;
    public boolean zzcio;

    @zzin
    public static final class zza {
        public final int errorCode;
        @Nullable
        public final AdSizeParcel zzapa;
        @Nullable
        public final JSONObject zzcie;
        public final zzga zzcig;
        public final long zzcik;
        public final long zzcil;
        public final AdRequestInfoParcel zzcip;
        public final AdResponseParcel zzciq;

        public zza(AdRequestInfoParcel adRequestInfoParcel, AdResponseParcel adResponseParcel, zzga com_google_android_gms_internal_zzga, AdSizeParcel adSizeParcel, int i, long j, long j2, JSONObject jSONObject) {
            this.zzcip = adRequestInfoParcel;
            this.zzciq = adResponseParcel;
            this.zzcig = com_google_android_gms_internal_zzga;
            this.zzapa = adSizeParcel;
            this.errorCode = i;
            this.zzcik = j;
            this.zzcil = j2;
            this.zzcie = jSONObject;
        }
    }

    public zzju(AdRequestParcel adRequestParcel, @Nullable zzlh com_google_android_gms_internal_zzlh, List<String> list, int i, List<String> list2, List<String> list3, int i2, long j, String str, boolean z, @Nullable zzfz com_google_android_gms_internal_zzfz, @Nullable zzgk com_google_android_gms_internal_zzgk, @Nullable String str2, zzga com_google_android_gms_internal_zzga, @Nullable zzgc com_google_android_gms_internal_zzgc, long j2, AdSizeParcel adSizeParcel, long j3, long j4, long j5, String str3, JSONObject jSONObject, @Nullable com.google.android.gms.ads.internal.formats.zzh.zza com_google_android_gms_ads_internal_formats_zzh_zza, RewardItemParcel rewardItemParcel, List<String> list4, List<String> list5, boolean z2, AutoClickProtectionConfigurationParcel autoClickProtectionConfigurationParcel, @Nullable String str4, List<String> list6) {
        this.zzcin = false;
        this.zzcio = false;
        this.zzcar = adRequestParcel;
        this.zzbtm = com_google_android_gms_internal_zzlh;
        this.zzbnm = zzl(list);
        this.errorCode = i;
        this.zzbnn = zzl(list2);
        this.zzcca = zzl(list3);
        this.orientation = i2;
        this.zzbns = j;
        this.zzcau = str;
        this.zzcby = z;
        this.zzbon = com_google_android_gms_internal_zzfz;
        this.zzboo = com_google_android_gms_internal_zzgk;
        this.zzbop = str2;
        this.zzcig = com_google_android_gms_internal_zzga;
        this.zzboq = com_google_android_gms_internal_zzgc;
        this.zzcbz = j2;
        this.zzcii = adSizeParcel;
        this.zzcbx = j3;
        this.zzcik = j4;
        this.zzcil = j5;
        this.zzccd = str3;
        this.zzcie = jSONObject;
        this.zzcim = com_google_android_gms_ads_internal_formats_zzh_zza;
        this.zzccn = rewardItemParcel;
        this.zzcij = zzl(list4);
        this.zzccp = zzl(list5);
        this.zzccq = z2;
        this.zzccr = autoClickProtectionConfigurationParcel;
        this.zzcih = str4;
        this.zzbnp = zzl(list6);
    }

    public zzju(zza com_google_android_gms_internal_zzju_zza, @Nullable zzlh com_google_android_gms_internal_zzlh, @Nullable zzfz com_google_android_gms_internal_zzfz, @Nullable zzgk com_google_android_gms_internal_zzgk, @Nullable String str, @Nullable zzgc com_google_android_gms_internal_zzgc, @Nullable com.google.android.gms.ads.internal.formats.zzh.zza com_google_android_gms_ads_internal_formats_zzh_zza, @Nullable String str2) {
        this(com_google_android_gms_internal_zzju_zza.zzcip.zzcar, com_google_android_gms_internal_zzlh, com_google_android_gms_internal_zzju_zza.zzciq.zzbnm, com_google_android_gms_internal_zzju_zza.errorCode, com_google_android_gms_internal_zzju_zza.zzciq.zzbnn, com_google_android_gms_internal_zzju_zza.zzciq.zzcca, com_google_android_gms_internal_zzju_zza.zzciq.orientation, com_google_android_gms_internal_zzju_zza.zzciq.zzbns, com_google_android_gms_internal_zzju_zza.zzcip.zzcau, com_google_android_gms_internal_zzju_zza.zzciq.zzcby, com_google_android_gms_internal_zzfz, com_google_android_gms_internal_zzgk, str, com_google_android_gms_internal_zzju_zza.zzcig, com_google_android_gms_internal_zzgc, com_google_android_gms_internal_zzju_zza.zzciq.zzcbz, com_google_android_gms_internal_zzju_zza.zzapa, com_google_android_gms_internal_zzju_zza.zzciq.zzcbx, com_google_android_gms_internal_zzju_zza.zzcik, com_google_android_gms_internal_zzju_zza.zzcil, com_google_android_gms_internal_zzju_zza.zzciq.zzccd, com_google_android_gms_internal_zzju_zza.zzcie, com_google_android_gms_ads_internal_formats_zzh_zza, com_google_android_gms_internal_zzju_zza.zzciq.zzccn, com_google_android_gms_internal_zzju_zza.zzciq.zzcco, com_google_android_gms_internal_zzju_zza.zzciq.zzcco, com_google_android_gms_internal_zzju_zza.zzciq.zzccq, com_google_android_gms_internal_zzju_zza.zzciq.zzccr, str2, com_google_android_gms_internal_zzju_zza.zzciq.zzbnp);
    }

    @Nullable
    private static <T> List<T> zzl(@Nullable List<T> list) {
        return list == null ? null : Collections.unmodifiableList(list);
    }

    public boolean zzho() {
        return (this.zzbtm == null || this.zzbtm.zzuj() == null) ? false : this.zzbtm.zzuj().zzho();
    }
}

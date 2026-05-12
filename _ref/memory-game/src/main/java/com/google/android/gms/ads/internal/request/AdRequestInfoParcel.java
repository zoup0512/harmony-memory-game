package com.google.android.gms.ads.internal.request;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Messenger;
import android.os.Parcel;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.internal.zzin;
import java.util.Collections;
import java.util.List;

@zzin
public final class AdRequestInfoParcel extends AbstractSafeParcelable {
    public static final zzf CREATOR = new zzf();
    public final ApplicationInfo applicationInfo;
    public final int versionCode;
    public final String zzaot;
    public final String zzaou;
    public final VersionInfoParcel zzaow;
    public final AdSizeParcel zzapa;
    public final NativeAdOptionsParcel zzapo;
    public final List<String> zzaps;
    public final boolean zzbnq;
    @Nullable
    public final Bundle zzcaq;
    public final AdRequestParcel zzcar;
    @Nullable
    public final PackageInfo zzcas;
    public final String zzcat;
    public final String zzcau;
    public final String zzcav;
    public final Bundle zzcaw;
    public final int zzcax;
    public final Bundle zzcay;
    public final boolean zzcaz;
    public final Messenger zzcba;
    public final int zzcbb;
    public final int zzcbc;
    public final float zzcbd;
    public final String zzcbe;
    public final long zzcbf;
    public final String zzcbg;
    @Nullable
    public final List<String> zzcbh;
    public final List<String> zzcbi;
    public final long zzcbj;
    public final CapabilityParcel zzcbk;
    public final String zzcbl;
    public final float zzcbm;
    public final int zzcbn;
    public final int zzcbo;
    public final boolean zzcbp;
    public final boolean zzcbq;
    public final String zzcbr;
    public final boolean zzcbs;
    public final String zzcbt;
    public final int zzcbu;
    public final Bundle zzcbv;

    @zzin
    public static final class zza {
        public final ApplicationInfo applicationInfo;
        public final String zzaot;
        public final String zzaou;
        public final VersionInfoParcel zzaow;
        public final AdSizeParcel zzapa;
        public final NativeAdOptionsParcel zzapo;
        public final List<String> zzaps;
        public final boolean zzbnq;
        @Nullable
        public final Bundle zzcaq;
        public final AdRequestParcel zzcar;
        @Nullable
        public final PackageInfo zzcas;
        public final String zzcau;
        public final String zzcav;
        public final Bundle zzcaw;
        public final int zzcax;
        public final Bundle zzcay;
        public final boolean zzcaz;
        public final Messenger zzcba;
        public final int zzcbb;
        public final int zzcbc;
        public final float zzcbd;
        public final String zzcbe;
        public final long zzcbf;
        public final String zzcbg;
        @Nullable
        public final List<String> zzcbh;
        public final List<String> zzcbi;
        public final CapabilityParcel zzcbk;
        public final String zzcbl;
        public final float zzcbm;
        public final int zzcbn;
        public final int zzcbo;
        public final boolean zzcbp;
        public final boolean zzcbq;
        public final String zzcbr;
        public final boolean zzcbs;
        public final String zzcbt;
        public final int zzcbu;
        public final Bundle zzcbv;

        public zza(@Nullable Bundle bundle, AdRequestParcel adRequestParcel, AdSizeParcel adSizeParcel, String str, ApplicationInfo applicationInfo, @Nullable PackageInfo packageInfo, String str2, String str3, VersionInfoParcel versionInfoParcel, Bundle bundle2, List<String> list, List<String> list2, Bundle bundle3, boolean z, Messenger messenger, int i, int i2, float f, String str4, long j, String str5, @Nullable List<String> list3, String str6, NativeAdOptionsParcel nativeAdOptionsParcel, CapabilityParcel capabilityParcel, String str7, float f2, boolean z2, int i3, int i4, boolean z3, boolean z4, String str8, String str9, boolean z5, int i5, Bundle bundle4) {
            this.zzcaq = bundle;
            this.zzcar = adRequestParcel;
            this.zzapa = adSizeParcel;
            this.zzaou = str;
            this.applicationInfo = applicationInfo;
            this.zzcas = packageInfo;
            this.zzcau = str2;
            this.zzcav = str3;
            this.zzaow = versionInfoParcel;
            this.zzcaw = bundle2;
            this.zzcaz = z;
            this.zzcba = messenger;
            this.zzcbb = i;
            this.zzcbc = i2;
            this.zzcbd = f;
            if (list == null || list.size() <= 0) {
                if (adSizeParcel.zzauw) {
                    this.zzcax = 4;
                } else {
                    this.zzcax = 0;
                }
                this.zzaps = null;
                this.zzcbi = null;
            } else {
                this.zzcax = 3;
                this.zzaps = list;
                this.zzcbi = list2;
            }
            this.zzcay = bundle3;
            this.zzcbe = str4;
            this.zzcbf = j;
            this.zzcbg = str5;
            this.zzcbh = list3;
            this.zzaot = str6;
            this.zzapo = nativeAdOptionsParcel;
            this.zzcbk = capabilityParcel;
            this.zzcbl = str7;
            this.zzcbm = f2;
            this.zzcbs = z2;
            this.zzcbn = i3;
            this.zzcbo = i4;
            this.zzcbp = z3;
            this.zzcbq = z4;
            this.zzcbr = str8;
            this.zzcbt = str9;
            this.zzbnq = z5;
            this.zzcbu = i5;
            this.zzcbv = bundle4;
        }
    }

    AdRequestInfoParcel(int i, Bundle bundle, AdRequestParcel adRequestParcel, AdSizeParcel adSizeParcel, String str, ApplicationInfo applicationInfo, PackageInfo packageInfo, String str2, String str3, String str4, VersionInfoParcel versionInfoParcel, Bundle bundle2, int i2, List<String> list, Bundle bundle3, boolean z, Messenger messenger, int i3, int i4, float f, String str5, long j, String str6, List<String> list2, String str7, NativeAdOptionsParcel nativeAdOptionsParcel, List<String> list3, long j2, CapabilityParcel capabilityParcel, String str8, float f2, boolean z2, int i5, int i6, boolean z3, boolean z4, String str9, String str10, boolean z5, int i7, Bundle bundle4) {
        this.versionCode = i;
        this.zzcaq = bundle;
        this.zzcar = adRequestParcel;
        this.zzapa = adSizeParcel;
        this.zzaou = str;
        this.applicationInfo = applicationInfo;
        this.zzcas = packageInfo;
        this.zzcat = str2;
        this.zzcau = str3;
        this.zzcav = str4;
        this.zzaow = versionInfoParcel;
        this.zzcaw = bundle2;
        this.zzcax = i2;
        this.zzaps = list;
        this.zzcbi = list3 == null ? Collections.emptyList() : Collections.unmodifiableList(list3);
        this.zzcay = bundle3;
        this.zzcaz = z;
        this.zzcba = messenger;
        this.zzcbb = i3;
        this.zzcbc = i4;
        this.zzcbd = f;
        this.zzcbe = str5;
        this.zzcbf = j;
        this.zzcbg = str6;
        this.zzcbh = list2 == null ? Collections.emptyList() : Collections.unmodifiableList(list2);
        this.zzaot = str7;
        this.zzapo = nativeAdOptionsParcel;
        this.zzcbj = j2;
        this.zzcbk = capabilityParcel;
        this.zzcbl = str8;
        this.zzcbm = f2;
        this.zzcbs = z2;
        this.zzcbn = i5;
        this.zzcbo = i6;
        this.zzcbp = z3;
        this.zzcbq = z4;
        this.zzcbr = str9;
        this.zzcbt = str10;
        this.zzbnq = z5;
        this.zzcbu = i7;
        this.zzcbv = bundle4;
    }

    public AdRequestInfoParcel(@Nullable Bundle bundle, AdRequestParcel adRequestParcel, AdSizeParcel adSizeParcel, String str, ApplicationInfo applicationInfo, @Nullable PackageInfo packageInfo, String str2, String str3, String str4, VersionInfoParcel versionInfoParcel, Bundle bundle2, int i, List<String> list, List<String> list2, Bundle bundle3, boolean z, Messenger messenger, int i2, int i3, float f, String str5, long j, String str6, @Nullable List<String> list3, String str7, NativeAdOptionsParcel nativeAdOptionsParcel, long j2, CapabilityParcel capabilityParcel, String str8, float f2, boolean z2, int i4, int i5, boolean z3, boolean z4, String str9, String str10, boolean z5, int i6, Bundle bundle4) {
        this(18, bundle, adRequestParcel, adSizeParcel, str, applicationInfo, packageInfo, str2, str3, str4, versionInfoParcel, bundle2, i, list, bundle3, z, messenger, i2, i3, f, str5, j, str6, list3, str7, nativeAdOptionsParcel, list2, j2, capabilityParcel, str8, f2, z2, i4, i5, z3, z4, str9, str10, z5, i6, bundle4);
    }

    public AdRequestInfoParcel(zza com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza, String str, long j) {
        this(com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcaq, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcar, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzapa, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzaou, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.applicationInfo, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcas, str, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcau, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcav, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzaow, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcaw, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcax, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzaps, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcbi, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcay, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcaz, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcba, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcbb, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcbc, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcbd, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcbe, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcbf, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcbg, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcbh, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzaot, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzapo, j, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcbk, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcbl, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcbm, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcbs, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcbn, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcbo, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcbp, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcbq, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcbr, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcbt, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzbnq, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcbu, com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzcbv);
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzf.zza(this, parcel, i);
    }
}

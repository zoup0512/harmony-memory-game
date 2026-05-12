package com.google.android.gms.ads.internal.formats;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzdr;
import com.google.android.gms.internal.zzdv.zza;
import com.google.android.gms.internal.zzin;
import java.util.List;

@zzin
public class zzd extends zza implements zzh.zza {
    private Bundle mExtras;
    private Object zzail = new Object();
    private String zzbfg;
    private List<zzc> zzbfh;
    private String zzbfi;
    private zzdr zzbfj;
    private String zzbfk;
    private double zzbfl;
    private String zzbfm;
    private String zzbfn;
    @Nullable
    private zza zzbfo;
    private zzh zzbfp;

    public zzd(String str, List list, String str2, zzdr com_google_android_gms_internal_zzdr, String str3, double d, String str4, String str5, @Nullable zza com_google_android_gms_ads_internal_formats_zza, Bundle bundle) {
        this.zzbfg = str;
        this.zzbfh = list;
        this.zzbfi = str2;
        this.zzbfj = com_google_android_gms_internal_zzdr;
        this.zzbfk = str3;
        this.zzbfl = d;
        this.zzbfm = str4;
        this.zzbfn = str5;
        this.zzbfo = com_google_android_gms_ads_internal_formats_zza;
        this.mExtras = bundle;
    }

    public void destroy() {
        this.zzbfg = null;
        this.zzbfh = null;
        this.zzbfi = null;
        this.zzbfj = null;
        this.zzbfk = null;
        this.zzbfl = 0.0d;
        this.zzbfm = null;
        this.zzbfn = null;
        this.zzbfo = null;
        this.mExtras = null;
        this.zzail = null;
        this.zzbfp = null;
    }

    public String getBody() {
        return this.zzbfi;
    }

    public String getCallToAction() {
        return this.zzbfk;
    }

    public String getCustomTemplateId() {
        return "";
    }

    public Bundle getExtras() {
        return this.mExtras;
    }

    public String getHeadline() {
        return this.zzbfg;
    }

    public List getImages() {
        return this.zzbfh;
    }

    public String getPrice() {
        return this.zzbfn;
    }

    public double getStarRating() {
        return this.zzbfl;
    }

    public String getStore() {
        return this.zzbfm;
    }

    public void zzb(zzh com_google_android_gms_ads_internal_formats_zzh) {
        synchronized (this.zzail) {
            this.zzbfp = com_google_android_gms_ads_internal_formats_zzh;
        }
    }

    public zzdr zzku() {
        return this.zzbfj;
    }

    public com.google.android.gms.dynamic.zzd zzkv() {
        return zze.zzac(this.zzbfp);
    }

    public String zzkw() {
        return "2";
    }

    public zza zzkx() {
        return this.zzbfo;
    }
}

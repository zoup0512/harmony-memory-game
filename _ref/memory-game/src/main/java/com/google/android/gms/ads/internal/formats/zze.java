package com.google.android.gms.ads.internal.formats;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zzdr;
import com.google.android.gms.internal.zzdx.zza;
import com.google.android.gms.internal.zzin;
import java.util.List;

@zzin
public class zze extends zza implements zzh.zza {
    private Bundle mExtras;
    private Object zzail = new Object();
    private String zzbfg;
    private List<zzc> zzbfh;
    private String zzbfi;
    private String zzbfk;
    @Nullable
    private zza zzbfo;
    private zzh zzbfp;
    private zzdr zzbfq;
    private String zzbfr;

    public zze(String str, List list, String str2, zzdr com_google_android_gms_internal_zzdr, String str3, String str4, @Nullable zza com_google_android_gms_ads_internal_formats_zza, Bundle bundle) {
        this.zzbfg = str;
        this.zzbfh = list;
        this.zzbfi = str2;
        this.zzbfq = com_google_android_gms_internal_zzdr;
        this.zzbfk = str3;
        this.zzbfr = str4;
        this.zzbfo = com_google_android_gms_ads_internal_formats_zza;
        this.mExtras = bundle;
    }

    public void destroy() {
        this.zzbfg = null;
        this.zzbfh = null;
        this.zzbfi = null;
        this.zzbfq = null;
        this.zzbfk = null;
        this.zzbfr = null;
        this.zzbfo = null;
        this.mExtras = null;
        this.zzail = null;
        this.zzbfp = null;
    }

    public String getAdvertiser() {
        return this.zzbfr;
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

    public void zzb(zzh com_google_android_gms_ads_internal_formats_zzh) {
        synchronized (this.zzail) {
            this.zzbfp = com_google_android_gms_ads_internal_formats_zzh;
        }
    }

    public zzd zzkv() {
        return com.google.android.gms.dynamic.zze.zzac(this.zzbfp);
    }

    public String zzkw() {
        return AppEventsConstants.EVENT_PARAM_VALUE_YES;
    }

    public zza zzkx() {
        return this.zzbfo;
    }

    public zzdr zzky() {
        return this.zzbfq;
    }
}

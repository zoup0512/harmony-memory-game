package com.google.android.gms.internal;

import android.text.TextUtils;
import com.applovin.sdk.AppLovinEventTypes;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.analytics.zzg;
import java.util.HashMap;
import java.util.Map;

public final class zzlv extends zzg<zzlv> {
    private String mName;
    private String zzbem;
    private String zzbgg;
    private String zzcup;
    private String zzcuq;
    private String zzcur;
    private String zzcus;
    private String zzcut;
    private String zzcuu;
    private String zzcuv;

    public String getContent() {
        return this.zzbem;
    }

    public String getId() {
        return this.zzbgg;
    }

    public String getName() {
        return this.mName;
    }

    public String getSource() {
        return this.zzcup;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public String toString() {
        Map hashMap = new HashMap();
        hashMap.put("name", this.mName);
        hashMap.put(ShareConstants.FEED_SOURCE_PARAM, this.zzcup);
        hashMap.put("medium", this.zzcuq);
        hashMap.put("keyword", this.zzcur);
        hashMap.put(AppLovinEventTypes.USER_VIEWED_CONTENT, this.zzbem);
        hashMap.put("id", this.zzbgg);
        hashMap.put("adNetworkId", this.zzcus);
        hashMap.put("gclid", this.zzcut);
        hashMap.put("dclid", this.zzcuu);
        hashMap.put("aclid", this.zzcuv);
        return zzg.zzj(hashMap);
    }

    public void zza(zzlv com_google_android_gms_internal_zzlv) {
        if (!TextUtils.isEmpty(this.mName)) {
            com_google_android_gms_internal_zzlv.setName(this.mName);
        }
        if (!TextUtils.isEmpty(this.zzcup)) {
            com_google_android_gms_internal_zzlv.zzdj(this.zzcup);
        }
        if (!TextUtils.isEmpty(this.zzcuq)) {
            com_google_android_gms_internal_zzlv.zzdk(this.zzcuq);
        }
        if (!TextUtils.isEmpty(this.zzcur)) {
            com_google_android_gms_internal_zzlv.zzdl(this.zzcur);
        }
        if (!TextUtils.isEmpty(this.zzbem)) {
            com_google_android_gms_internal_zzlv.zzdm(this.zzbem);
        }
        if (!TextUtils.isEmpty(this.zzbgg)) {
            com_google_android_gms_internal_zzlv.zzdn(this.zzbgg);
        }
        if (!TextUtils.isEmpty(this.zzcus)) {
            com_google_android_gms_internal_zzlv.zzdo(this.zzcus);
        }
        if (!TextUtils.isEmpty(this.zzcut)) {
            com_google_android_gms_internal_zzlv.zzdp(this.zzcut);
        }
        if (!TextUtils.isEmpty(this.zzcuu)) {
            com_google_android_gms_internal_zzlv.zzdq(this.zzcuu);
        }
        if (!TextUtils.isEmpty(this.zzcuv)) {
            com_google_android_gms_internal_zzlv.zzdr(this.zzcuv);
        }
    }

    public /* synthetic */ void zzb(zzg com_google_android_gms_analytics_zzg) {
        zza((zzlv) com_google_android_gms_analytics_zzg);
    }

    public void zzdj(String str) {
        this.zzcup = str;
    }

    public void zzdk(String str) {
        this.zzcuq = str;
    }

    public void zzdl(String str) {
        this.zzcur = str;
    }

    public void zzdm(String str) {
        this.zzbem = str;
    }

    public void zzdn(String str) {
        this.zzbgg = str;
    }

    public void zzdo(String str) {
        this.zzcus = str;
    }

    public void zzdp(String str) {
        this.zzcut = str;
    }

    public void zzdq(String str) {
        this.zzcuu = str;
    }

    public void zzdr(String str) {
        this.zzcuv = str;
    }

    public String zzxe() {
        return this.zzcuq;
    }

    public String zzxf() {
        return this.zzcur;
    }

    public String zzxg() {
        return this.zzcus;
    }

    public String zzxh() {
        return this.zzcut;
    }

    public String zzxi() {
        return this.zzcuu;
    }

    public String zzxj() {
        return this.zzcuv;
    }
}

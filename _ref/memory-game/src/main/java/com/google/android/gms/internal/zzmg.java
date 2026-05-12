package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.analytics.zzg;
import com.google.android.gms.plus.PlusShare;
import java.util.HashMap;
import java.util.Map;

public final class zzmg extends zzg<zzmg> {
    public String mCategory;
    public String zzcvd;
    public String zzcvy;
    public long zzcvz;

    public String getCategory() {
        return this.mCategory;
    }

    public String getLabel() {
        return this.zzcvd;
    }

    public long getTimeInMillis() {
        return this.zzcvz;
    }

    public void setTimeInMillis(long j) {
        this.zzcvz = j;
    }

    public String toString() {
        Map hashMap = new HashMap();
        hashMap.put("variableName", this.zzcvy);
        hashMap.put("timeInMillis", Long.valueOf(this.zzcvz));
        hashMap.put("category", this.mCategory);
        hashMap.put(PlusShare.KEY_CALL_TO_ACTION_LABEL, this.zzcvd);
        return zzg.zzj(hashMap);
    }

    public void zza(zzmg com_google_android_gms_internal_zzmg) {
        if (!TextUtils.isEmpty(this.zzcvy)) {
            com_google_android_gms_internal_zzmg.zzed(this.zzcvy);
        }
        if (this.zzcvz != 0) {
            com_google_android_gms_internal_zzmg.setTimeInMillis(this.zzcvz);
        }
        if (!TextUtils.isEmpty(this.mCategory)) {
            com_google_android_gms_internal_zzmg.zzdt(this.mCategory);
        }
        if (!TextUtils.isEmpty(this.zzcvd)) {
            com_google_android_gms_internal_zzmg.zzdv(this.zzcvd);
        }
    }

    public /* synthetic */ void zzb(zzg com_google_android_gms_analytics_zzg) {
        zza((zzmg) com_google_android_gms_analytics_zzg);
    }

    public void zzdt(String str) {
        this.mCategory = str;
    }

    public void zzdv(String str) {
        this.zzcvd = str;
    }

    public void zzed(String str) {
        this.zzcvy = str;
    }

    public String zzyj() {
        return this.zzcvy;
    }
}

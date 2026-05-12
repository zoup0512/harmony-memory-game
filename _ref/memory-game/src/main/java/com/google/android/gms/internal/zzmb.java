package com.google.android.gms.internal;

import android.text.TextUtils;
import com.facebook.internal.NativeProtocol;
import com.google.android.gms.analytics.zzg;
import com.google.android.gms.plus.PlusShare;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.util.HashMap;
import java.util.Map;

public final class zzmb extends zzg<zzmb> {
    private String mCategory;
    private String zzcvc;
    private String zzcvd;
    private long zzcve;

    public String getAction() {
        return this.zzcvc;
    }

    public String getCategory() {
        return this.mCategory;
    }

    public String getLabel() {
        return this.zzcvd;
    }

    public long getValue() {
        return this.zzcve;
    }

    public String toString() {
        Map hashMap = new HashMap();
        hashMap.put("category", this.mCategory);
        hashMap.put(NativeProtocol.WEB_DIALOG_ACTION, this.zzcvc);
        hashMap.put(PlusShare.KEY_CALL_TO_ACTION_LABEL, this.zzcvd);
        hashMap.put(Param.VALUE, Long.valueOf(this.zzcve));
        return zzg.zzj(hashMap);
    }

    public void zza(zzmb com_google_android_gms_internal_zzmb) {
        if (!TextUtils.isEmpty(this.mCategory)) {
            com_google_android_gms_internal_zzmb.zzdt(this.mCategory);
        }
        if (!TextUtils.isEmpty(this.zzcvc)) {
            com_google_android_gms_internal_zzmb.zzdu(this.zzcvc);
        }
        if (!TextUtils.isEmpty(this.zzcvd)) {
            com_google_android_gms_internal_zzmb.zzdv(this.zzcvd);
        }
        if (this.zzcve != 0) {
            com_google_android_gms_internal_zzmb.zzo(this.zzcve);
        }
    }

    public /* synthetic */ void zzb(zzg com_google_android_gms_analytics_zzg) {
        zza((zzmb) com_google_android_gms_analytics_zzg);
    }

    public void zzdt(String str) {
        this.mCategory = str;
    }

    public void zzdu(String str) {
        this.zzcvc = str;
    }

    public void zzdv(String str) {
        this.zzcvd = str;
    }

    public void zzo(long j) {
        this.zzcve = j;
    }
}

package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzab;
import java.util.Iterator;

public class zzh {
    final long aiA;
    final EventParams aiB;
    final String aiz;
    final String mName;
    final long pJ;
    final String zzcjf;

    zzh(zzx com_google_android_gms_measurement_internal_zzx, String str, String str2, String str3, long j, long j2, Bundle bundle) {
        zzab.zzhr(str2);
        zzab.zzhr(str3);
        this.zzcjf = str2;
        this.mName = str3;
        if (TextUtils.isEmpty(str)) {
            str = null;
        }
        this.aiz = str;
        this.pJ = j;
        this.aiA = j2;
        if (this.aiA != 0 && this.aiA > this.pJ) {
            com_google_android_gms_measurement_internal_zzx.zzbsd().zzbsx().log("Event created with reverse previous/current timestamps");
        }
        this.aiB = zza(com_google_android_gms_measurement_internal_zzx, bundle);
    }

    private zzh(zzx com_google_android_gms_measurement_internal_zzx, String str, String str2, String str3, long j, long j2, EventParams eventParams) {
        zzab.zzhr(str2);
        zzab.zzhr(str3);
        zzab.zzy(eventParams);
        this.zzcjf = str2;
        this.mName = str3;
        if (TextUtils.isEmpty(str)) {
            str = null;
        }
        this.aiz = str;
        this.pJ = j;
        this.aiA = j2;
        if (this.aiA != 0 && this.aiA > this.pJ) {
            com_google_android_gms_measurement_internal_zzx.zzbsd().zzbsx().log("Event created with reverse previous/current timestamps");
        }
        this.aiB = eventParams;
    }

    public String toString() {
        String str = this.zzcjf;
        String str2 = this.mName;
        String valueOf = String.valueOf(this.aiB);
        return new StringBuilder(((String.valueOf(str).length() + 33) + String.valueOf(str2).length()) + String.valueOf(valueOf).length()).append("Event{appId='").append(str).append("'").append(", name='").append(str2).append("'").append(", params=").append(valueOf).append("}").toString();
    }

    EventParams zza(zzx com_google_android_gms_measurement_internal_zzx, Bundle bundle) {
        if (bundle == null || bundle.isEmpty()) {
            return new EventParams(new Bundle());
        }
        Bundle bundle2 = new Bundle(bundle);
        Iterator it = bundle2.keySet().iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (str == null) {
                com_google_android_gms_measurement_internal_zzx.zzbsd().zzbsv().log("Param name can't be null");
                it.remove();
            } else {
                Object zzl = com_google_android_gms_measurement_internal_zzx.zzbrz().zzl(str, bundle2.get(str));
                if (zzl == null) {
                    com_google_android_gms_measurement_internal_zzx.zzbsd().zzbsx().zzj("Param value can't be null", str);
                    it.remove();
                } else {
                    com_google_android_gms_measurement_internal_zzx.zzbrz().zza(bundle2, str, zzl);
                }
            }
        }
        return new EventParams(bundle2);
    }

    zzh zza(zzx com_google_android_gms_measurement_internal_zzx, long j) {
        return new zzh(com_google_android_gms_measurement_internal_zzx, this.aiz, this.zzcjf, this.mName, this.pJ, j, this.aiB);
    }
}

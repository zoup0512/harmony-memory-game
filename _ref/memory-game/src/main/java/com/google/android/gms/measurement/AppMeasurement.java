package com.google.android.gms.measurement;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Keep;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.measurement.internal.UserAttributeParcel;
import com.google.android.gms.measurement.internal.zzx;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Deprecated
public class AppMeasurement {
    private final zzx ahD;

    public AppMeasurement(zzx com_google_android_gms_measurement_internal_zzx) {
        zzab.zzy(com_google_android_gms_measurement_internal_zzx);
        this.ahD = com_google_android_gms_measurement_internal_zzx;
    }

    @Keep
    @Deprecated
    public static AppMeasurement getInstance(Context context) {
        return zzx.zzdo(context).zzbtr();
    }

    private void zzc(String str, String str2, Object obj) {
        this.ahD.zzbru().zzd(str, str2, obj);
    }

    @Deprecated
    public void logEvent(@Size(max = 32, min = 1) @NonNull String str, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        if (this.ahD.zzbsf().zzabc() || !"_iap".equals(str)) {
            int zzmk = this.ahD.zzbrz().zzmk(str);
            if (zzmk != 0) {
                this.ahD.zzbrz().zze(zzmk, "_ev", this.ahD.zzbrz().zza(str, this.ahD.zzbsf().zzbqn(), true));
                return;
            }
        }
        this.ahD.zzbru().zza(SettingsJsonConstants.APP_KEY, str, bundle, true);
    }

    @Deprecated
    public void setMeasurementEnabled(boolean z) {
        this.ahD.zzbru().setMeasurementEnabled(z);
    }

    @Deprecated
    public void setMinimumSessionDuration(long j) {
        this.ahD.zzbru().setMinimumSessionDuration(j);
    }

    @Deprecated
    public void setSessionTimeoutDuration(long j) {
        this.ahD.zzbru().setSessionTimeoutDuration(j);
    }

    @Deprecated
    public void setUserId(String str) {
        zzb(SettingsJsonConstants.APP_KEY, TransferTable.COLUMN_ID, str);
    }

    @Deprecated
    public void setUserProperty(@Size(max = 24, min = 1) @NonNull String str, @Nullable @Size(max = 36) String str2) {
        int zzmm = this.ahD.zzbrz().zzmm(str);
        if (zzmm != 0) {
            this.ahD.zzbrz().zze(zzmm, "_ev", this.ahD.zzbrz().zza(str, this.ahD.zzbsf().zzbqo(), true));
            return;
        }
        zzb(SettingsJsonConstants.APP_KEY, str, str2);
    }

    @WorkerThread
    public void zza(zzb com_google_android_gms_measurement_AppMeasurement_zzb) {
        this.ahD.zzbru().zza(com_google_android_gms_measurement_AppMeasurement_zzb);
    }

    @WorkerThread
    public void zza(zzc com_google_android_gms_measurement_AppMeasurement_zzc) {
        this.ahD.zzbru().zza(com_google_android_gms_measurement_AppMeasurement_zzc);
    }

    public void zza(String str, String str2, Bundle bundle, long j) {
        this.ahD.zzbru().zzd(str, str2, bundle == null ? new Bundle() : bundle, j);
    }

    public void zzb(String str, String str2, Object obj) {
        zzc(str, str2, obj);
    }

    @WorkerThread
    public Map<String, Object> zzca(boolean z) {
        List<UserAttributeParcel> zzce = this.ahD.zzbru().zzce(z);
        Map<String, Object> hashMap = new HashMap(zzce.size());
        for (UserAttributeParcel userAttributeParcel : zzce) {
            hashMap.put(userAttributeParcel.name, userAttributeParcel.getValue());
        }
        return hashMap;
    }

    public void zzd(String str, String str2, Bundle bundle) {
        if (bundle == null) {
            bundle = new Bundle();
        }
        this.ahD.zzbru().zze(str, str2, bundle);
    }
}

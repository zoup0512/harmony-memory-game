package com.google.android.gms.internal;

import android.text.TextUtils;
import com.facebook.internal.NativeProtocol;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.plus.PlusShare;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.util.Map;

@zzin
public final class zzen implements zzep {
    private void zzb(zzlh com_google_android_gms_internal_zzlh, Map<String, String> map) {
        String str = (String) map.get(PlusShare.KEY_CALL_TO_ACTION_LABEL);
        String str2 = (String) map.get("start_label");
        String str3 = (String) map.get("timestamp");
        if (TextUtils.isEmpty(str)) {
            zzb.zzcx("No label given for CSI tick.");
        } else if (TextUtils.isEmpty(str3)) {
            zzb.zzcx("No timestamp given for CSI tick.");
        } else {
            try {
                long zzd = zzd(Long.parseLong(str3));
                if (TextUtils.isEmpty(str2)) {
                    str2 = "native:view_load";
                }
                com_google_android_gms_internal_zzlh.zzus().zza(str, str2, zzd);
            } catch (Throwable e) {
                zzb.zzd("Malformed timestamp for CSI tick.", e);
            }
        }
    }

    private void zzc(zzlh com_google_android_gms_internal_zzlh, Map<String, String> map) {
        String str = (String) map.get(Param.VALUE);
        if (TextUtils.isEmpty(str)) {
            zzb.zzcx("No value given for CSI experiment.");
            return;
        }
        zzdk zzkf = com_google_android_gms_internal_zzlh.zzus().zzkf();
        if (zzkf == null) {
            zzb.zzcx("No ticker for WebView, dropping experiment ID.");
        } else {
            zzkf.zzh("e", str);
        }
    }

    private long zzd(long j) {
        return (j - zzu.zzfu().currentTimeMillis()) + zzu.zzfu().elapsedRealtime();
    }

    private void zzd(zzlh com_google_android_gms_internal_zzlh, Map<String, String> map) {
        String str = (String) map.get("name");
        String str2 = (String) map.get(Param.VALUE);
        if (TextUtils.isEmpty(str2)) {
            zzb.zzcx("No value given for CSI extra.");
        } else if (TextUtils.isEmpty(str)) {
            zzb.zzcx("No name given for CSI extra.");
        } else {
            zzdk zzkf = com_google_android_gms_internal_zzlh.zzus().zzkf();
            if (zzkf == null) {
                zzb.zzcx("No ticker for WebView, dropping extra parameter.");
            } else {
                zzkf.zzh(str, str2);
            }
        }
    }

    public void zza(zzlh com_google_android_gms_internal_zzlh, Map<String, String> map) {
        String str = (String) map.get(NativeProtocol.WEB_DIALOG_ACTION);
        if ("tick".equals(str)) {
            zzb(com_google_android_gms_internal_zzlh, map);
        } else if ("experiment".equals(str)) {
            zzc(com_google_android_gms_internal_zzlh, map);
        } else if ("extra".equals(str)) {
            zzd(com_google_android_gms_internal_zzlh, map);
        }
    }
}

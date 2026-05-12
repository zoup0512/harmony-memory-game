package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzai.zza;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

class zzdp {
    private static zzcd<zza> zza(zzcd<zza> com_google_android_gms_tagmanager_zzcd_com_google_android_gms_internal_zzai_zza) {
        try {
            return new zzcd(zzdl.zzap(zzpp(zzdl.zzg((zza) com_google_android_gms_tagmanager_zzcd_com_google_android_gms_internal_zzai_zza.getObject()))), com_google_android_gms_tagmanager_zzcd_com_google_android_gms_internal_zzai_zza.zzccd());
        } catch (Throwable e) {
            zzbn.zzb("Escape URI: unsupported encoding", e);
            return com_google_android_gms_tagmanager_zzcd_com_google_android_gms_internal_zzai_zza;
        }
    }

    private static zzcd<zza> zza(zzcd<zza> com_google_android_gms_tagmanager_zzcd_com_google_android_gms_internal_zzai_zza, int i) {
        if (zzn((zza) com_google_android_gms_tagmanager_zzcd_com_google_android_gms_internal_zzai_zza.getObject())) {
            switch (i) {
                case 12:
                    return zza(com_google_android_gms_tagmanager_zzcd_com_google_android_gms_internal_zzai_zza);
                default:
                    zzbn.e("Unsupported Value Escaping: " + i);
                    return com_google_android_gms_tagmanager_zzcd_com_google_android_gms_internal_zzai_zza;
            }
        }
        zzbn.e("Escaping can only be applied to strings.");
        return com_google_android_gms_tagmanager_zzcd_com_google_android_gms_internal_zzai_zza;
    }

    static zzcd<zza> zza(zzcd<zza> com_google_android_gms_tagmanager_zzcd_com_google_android_gms_internal_zzai_zza, int... iArr) {
        zzcd zza;
        for (int zza2 : iArr) {
            zza = zza(zza, zza2);
        }
        return zza;
    }

    private static boolean zzn(zza com_google_android_gms_internal_zzai_zza) {
        return zzdl.zzl(com_google_android_gms_internal_zzai_zza) instanceof String;
    }

    static String zzpp(String str) throws UnsupportedEncodingException {
        return URLEncoder.encode(str, "UTF-8").replaceAll("\\+", "%20");
    }
}

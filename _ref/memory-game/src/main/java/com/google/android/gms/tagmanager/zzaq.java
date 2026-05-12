package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzai.zza;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

class zzaq extends zzal {
    private static final String ID = zzaf.HASH.toString();
    private static final String avP = zzag.ARG0.toString();
    private static final String avR = zzag.INPUT_FORMAT.toString();
    private static final String avV = zzag.ALGORITHM.toString();

    public zzaq() {
        super(ID, avP);
    }

    private byte[] zzf(String str, byte[] bArr) throws NoSuchAlgorithmException {
        MessageDigest instance = MessageDigest.getInstance(str);
        instance.update(bArr);
        return instance.digest();
    }

    public zza zzav(Map<String, zza> map) {
        zza com_google_android_gms_internal_zzai_zza = (zza) map.get(avP);
        if (com_google_android_gms_internal_zzai_zza == null || com_google_android_gms_internal_zzai_zza == zzdl.zzcdu()) {
            return zzdl.zzcdu();
        }
        byte[] bytes;
        String zzg = zzdl.zzg(com_google_android_gms_internal_zzai_zza);
        com_google_android_gms_internal_zzai_zza = (zza) map.get(avV);
        String zzg2 = com_google_android_gms_internal_zzai_zza == null ? CommonUtils.MD5_INSTANCE : zzdl.zzg(com_google_android_gms_internal_zzai_zza);
        com_google_android_gms_internal_zzai_zza = (zza) map.get(avR);
        Object zzg3 = com_google_android_gms_internal_zzai_zza == null ? "text" : zzdl.zzg(com_google_android_gms_internal_zzai_zza);
        if ("text".equals(zzg3)) {
            bytes = zzg.getBytes();
        } else if ("base16".equals(zzg3)) {
            bytes = zzk.zzod(zzg);
        } else {
            zzg2 = "Hash: unknown input format: ";
            String valueOf = String.valueOf(zzg3);
            zzbn.e(valueOf.length() != 0 ? zzg2.concat(valueOf) : new String(zzg2));
            return zzdl.zzcdu();
        }
        try {
            return zzdl.zzap(zzk.zzp(zzf(zzg2, bytes)));
        } catch (NoSuchAlgorithmException e) {
            zzg = "Hash: unknown algorithm: ";
            valueOf = String.valueOf(zzg2);
            zzbn.e(valueOf.length() != 0 ? zzg.concat(valueOf) : new String(zzg));
            return zzdl.zzcdu();
        }
    }

    public boolean zzcag() {
        return true;
    }
}

package com.google.android.gms.internal;

import com.google.android.gms.internal.zzm.zza;
import com.google.android.gms.internal.zzm.zzb;
import java.util.Map;

class zzkn$3 extends zzab {
    final /* synthetic */ zzkn zzcmg;
    final /* synthetic */ byte[] zzcmh;
    final /* synthetic */ Map zzcmi;

    zzkn$3(zzkn com_google_android_gms_internal_zzkn, int i, String str, zzb com_google_android_gms_internal_zzm_zzb, zza com_google_android_gms_internal_zzm_zza, byte[] bArr, Map map) {
        this.zzcmg = com_google_android_gms_internal_zzkn;
        this.zzcmh = bArr;
        this.zzcmi = map;
        super(i, str, com_google_android_gms_internal_zzm_zzb, com_google_android_gms_internal_zzm_zza);
    }

    public Map<String, String> getHeaders() throws zza {
        return this.zzcmi == null ? super.getHeaders() : this.zzcmi;
    }

    public byte[] zzp() throws zza {
        return this.zzcmh == null ? super.zzp() : this.zzcmh;
    }
}

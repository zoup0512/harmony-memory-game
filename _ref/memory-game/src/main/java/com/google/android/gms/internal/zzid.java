package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.internal.zzli.zza;

@zzin
public class zzid extends zzhy implements zza {
    zzid(Context context, zzju.zza com_google_android_gms_internal_zzju_zza, zzlh com_google_android_gms_internal_zzlh, zzic.zza com_google_android_gms_internal_zzic_zza) {
        super(context, com_google_android_gms_internal_zzju_zza, com_google_android_gms_internal_zzlh, com_google_android_gms_internal_zzic_zza);
    }

    protected void zzpw() {
        if (this.zzbxs.errorCode == -2) {
            this.zzbgf.zzuj().zza((zza) this);
            zzqd();
            zzb.zzcv("Loading HTML in WebView.");
            this.zzbgf.loadDataWithBaseURL(zzu.zzfq().zzco(this.zzbxs.zzbto), this.zzbxs.body, "text/html", "UTF-8", null);
        }
    }

    protected void zzqd() {
    }
}

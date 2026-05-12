package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import org.json.JSONObject;

@zzin
public class zzci extends zzcd {
    private final zzft zzarp;

    public zzci(Context context, AdSizeParcel adSizeParcel, zzju com_google_android_gms_internal_zzju, VersionInfoParcel versionInfoParcel, zzck com_google_android_gms_internal_zzck, zzft com_google_android_gms_internal_zzft) {
        super(context, adSizeParcel, com_google_android_gms_internal_zzju, versionInfoParcel, com_google_android_gms_internal_zzck);
        this.zzarp = com_google_android_gms_internal_zzft;
        zzc(this.zzarp);
        zzgw();
        zzk(3);
        String str = "Tracking ad unit: ";
        String valueOf = String.valueOf(this.zzaqk.zzhn());
        zzb.zzcv(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }

    protected void destroy() {
        synchronized (this.zzail) {
            super.destroy();
            zzd(this.zzarp);
        }
    }

    protected void zzb(JSONObject jSONObject) {
        this.zzarp.zza("AFMA_updateActiveView", jSONObject);
    }

    public void zzgy() {
        destroy();
    }

    protected boolean zzhe() {
        return true;
    }
}

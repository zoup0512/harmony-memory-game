package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzfs.zzc;
import org.json.JSONException;
import org.json.JSONObject;

@zzin
public class zzcj extends zzcd {
    private zzc zzarq;
    private boolean zzarr;

    public zzcj(Context context, AdSizeParcel adSizeParcel, zzju com_google_android_gms_internal_zzju, VersionInfoParcel versionInfoParcel, zzck com_google_android_gms_internal_zzck, zzfs com_google_android_gms_internal_zzfs) {
        super(context, adSizeParcel, com_google_android_gms_internal_zzju, versionInfoParcel, com_google_android_gms_internal_zzck);
        this.zzarq = com_google_android_gms_internal_zzfs.zzma();
        try {
            this.zzarq.zza(new 1(this, zzd(com_google_android_gms_internal_zzck.zzhj().zzhh())), new 2(this));
        } catch (JSONException e) {
        } catch (Throwable e2) {
            zzb.zzb("Failure while processing active view data.", e2);
        }
        this.zzarq.zza(new 3(this), new 4(this));
        String str = "Tracking ad unit: ";
        String valueOf = String.valueOf(this.zzaqk.zzhn());
        zzb.zzcv(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }

    protected void destroy() {
        synchronized (this.zzail) {
            super.destroy();
            this.zzarq.zza(new 6(this), new zzla.zzb());
            this.zzarq.release();
        }
    }

    protected void zzb(JSONObject jSONObject) {
        this.zzarq.zza(new 5(this, jSONObject), new zzla.zzb());
    }

    protected boolean zzhe() {
        return this.zzarr;
    }
}

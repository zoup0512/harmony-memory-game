package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.zza;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.ads.internal.overlay.zzg;
import com.google.android.gms.ads.internal.overlay.zzp;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.zze;
import com.google.android.gms.ads.internal.zzu;
import org.json.JSONObject;

@zzin
public class zzfr implements zzfp {
    private final zzlh zzbgf;

    public zzfr(Context context, VersionInfoParcel versionInfoParcel, @Nullable zzas com_google_android_gms_internal_zzas) {
        this.zzbgf = zzu.zzfr().zza(context, new AdSizeParcel(), false, false, com_google_android_gms_internal_zzas, versionInfoParcel);
        this.zzbgf.getWebView().setWillNotDraw(true);
    }

    private void runOnUiThread(Runnable runnable) {
        if (zzm.zziw().zztx()) {
            runnable.run();
        } else {
            zzkh.zzclc.post(runnable);
        }
    }

    public void destroy() {
        this.zzbgf.destroy();
    }

    public void zza(zza com_google_android_gms_ads_internal_client_zza, zzg com_google_android_gms_ads_internal_overlay_zzg, zzel com_google_android_gms_internal_zzel, zzp com_google_android_gms_ads_internal_overlay_zzp, boolean z, zzer com_google_android_gms_internal_zzer, zzet com_google_android_gms_internal_zzet, zze com_google_android_gms_ads_internal_zze, zzhg com_google_android_gms_internal_zzhg) {
        this.zzbgf.zzuj().zza(com_google_android_gms_ads_internal_client_zza, com_google_android_gms_ads_internal_overlay_zzg, com_google_android_gms_internal_zzel, com_google_android_gms_ads_internal_overlay_zzp, z, com_google_android_gms_internal_zzer, com_google_android_gms_internal_zzet, new zze(this.zzbgf.getContext(), false), com_google_android_gms_internal_zzhg, null);
    }

    public void zza(zzfp.zza com_google_android_gms_internal_zzfp_zza) {
        this.zzbgf.zzuj().zza(new 6(this, com_google_android_gms_internal_zzfp_zza));
    }

    public void zza(String str, zzep com_google_android_gms_internal_zzep) {
        this.zzbgf.zzuj().zza(str, com_google_android_gms_internal_zzep);
    }

    public void zza(String str, JSONObject jSONObject) {
        runOnUiThread(new 1(this, str, jSONObject));
    }

    public void zzb(String str, zzep com_google_android_gms_internal_zzep) {
        this.zzbgf.zzuj().zzb(str, com_google_android_gms_internal_zzep);
    }

    public void zzb(String str, JSONObject jSONObject) {
        this.zzbgf.zzb(str, jSONObject);
    }

    public void zzbg(String str) {
        runOnUiThread(new 3(this, String.format("<!DOCTYPE html><html><head><script src=\"%s\"></script></head><body></body></html>", new Object[]{str})));
    }

    public void zzbh(String str) {
        runOnUiThread(new 5(this, str));
    }

    public void zzbi(String str) {
        runOnUiThread(new 4(this, str));
    }

    public void zzj(String str, String str2) {
        runOnUiThread(new 2(this, str, str2));
    }

    public zzfu zzly() {
        return new zzfv(this);
    }
}

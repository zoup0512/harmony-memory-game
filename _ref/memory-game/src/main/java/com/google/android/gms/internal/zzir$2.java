package com.google.android.gms.internal;

import java.util.Map;

class zzir$2 implements zzep {
    final /* synthetic */ zzir zzces;

    zzir$2(zzir com_google_android_gms_internal_zzir) {
        this.zzces = com_google_android_gms_internal_zzir;
    }

    public void zza(zzlh com_google_android_gms_internal_zzlh, Map<String, String> map) {
        synchronized (zzir.zza(this.zzces)) {
            if (zzir.zzb(this.zzces).isDone()) {
                return;
            }
            zziu com_google_android_gms_internal_zziu = new zziu(-2, map);
            if (zzir.zzc(this.zzces).equals(com_google_android_gms_internal_zziu.getRequestId())) {
                String url = com_google_android_gms_internal_zziu.getUrl();
                if (url == null) {
                    zzkd.zzcx("URL missing in loadAdUrl GMSG.");
                    return;
                }
                if (url.contains("%40mediation_adapters%40")) {
                    String replaceAll = url.replaceAll("%40mediation_adapters%40", zzkb.zza(com_google_android_gms_internal_zzlh.getContext(), (String) map.get("check_adapters"), zzir.zzd(this.zzces)));
                    com_google_android_gms_internal_zziu.setUrl(replaceAll);
                    url = "Ad request URL modified to ";
                    replaceAll = String.valueOf(replaceAll);
                    zzkd.v(replaceAll.length() != 0 ? url.concat(replaceAll) : new String(url));
                }
                zzir.zzb(this.zzces).zzh(com_google_android_gms_internal_zziu);
                return;
            }
        }
    }
}

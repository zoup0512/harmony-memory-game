package com.google.android.gms.ads.internal.formats;

import android.text.TextUtils;
import com.google.android.gms.internal.zzep;
import com.google.android.gms.internal.zzft;
import com.google.android.gms.internal.zzih$zza;
import com.google.android.gms.internal.zzkd;
import com.google.android.gms.internal.zzlh;
import com.google.android.gms.internal.zzli$zza;
import java.util.Map;
import org.json.JSONObject;

class zzi$3 extends zzih$zza {
    final /* synthetic */ zzi zzbgk;

    zzi$3(zzi com_google_android_gms_ads_internal_formats_zzi) {
        this.zzbgk = com_google_android_gms_ads_internal_formats_zzi;
    }

    public void zze(final zzft com_google_android_gms_internal_zzft) {
        com_google_android_gms_internal_zzft.zza("/loadHtml", new zzep(this) {
            final /* synthetic */ zzi$3 zzbgm;

            public void zza(zzlh com_google_android_gms_internal_zzlh, final Map<String, String> map) {
                zzi.zzb(this.zzbgm.zzbgk).zzuj().zza(new zzli$zza(this) {
                    final /* synthetic */ AnonymousClass1 zzbgo;

                    public void zza(zzlh com_google_android_gms_internal_zzlh, boolean z) {
                        zzi.zza(this.zzbgo.zzbgm.zzbgk, (String) map.get("id"));
                        JSONObject jSONObject = new JSONObject();
                        try {
                            jSONObject.put("messageType", "htmlLoaded");
                            jSONObject.put("id", zzi.zza(this.zzbgo.zzbgm.zzbgk));
                            com_google_android_gms_internal_zzft.zzb("sendMessageToNativeJs", jSONObject);
                        } catch (Throwable e) {
                            zzkd.zzb("Unable to dispatch sendMessageToNativeJs event", e);
                        }
                    }
                });
                String str = (String) map.get("overlayHtml");
                String str2 = (String) map.get("baseUrl");
                if (TextUtils.isEmpty(str2)) {
                    zzi.zzb(this.zzbgm.zzbgk).loadData(str, "text/html", "UTF-8");
                } else {
                    zzi.zzb(this.zzbgm.zzbgk).loadDataWithBaseURL(str2, str, "text/html", "UTF-8", null);
                }
            }
        });
        com_google_android_gms_internal_zzft.zza("/showOverlay", new zzep(this) {
            final /* synthetic */ zzi$3 zzbgm;

            {
                this.zzbgm = r1;
            }

            public void zza(zzlh com_google_android_gms_internal_zzlh, Map<String, String> map) {
                zzi.zzb(this.zzbgm.zzbgk).getView().setVisibility(0);
            }
        });
        com_google_android_gms_internal_zzft.zza("/hideOverlay", new zzep(this) {
            final /* synthetic */ zzi$3 zzbgm;

            {
                this.zzbgm = r1;
            }

            public void zza(zzlh com_google_android_gms_internal_zzlh, Map<String, String> map) {
                zzi.zzb(this.zzbgm.zzbgk).getView().setVisibility(8);
            }
        });
        zzi.zzb(this.zzbgk).zzuj().zza("/hideOverlay", new zzep(this) {
            final /* synthetic */ zzi$3 zzbgm;

            {
                this.zzbgm = r1;
            }

            public void zza(zzlh com_google_android_gms_internal_zzlh, Map<String, String> map) {
                zzi.zzb(this.zzbgm.zzbgk).getView().setVisibility(8);
            }
        });
        zzi.zzb(this.zzbgk).zzuj().zza("/sendMessageToSdk", new zzep(this) {
            final /* synthetic */ zzi$3 zzbgm;

            public void zza(zzlh com_google_android_gms_internal_zzlh, Map<String, String> map) {
                JSONObject jSONObject = new JSONObject();
                try {
                    for (String str : map.keySet()) {
                        jSONObject.put(str, map.get(str));
                    }
                    jSONObject.put("id", zzi.zza(this.zzbgm.zzbgk));
                    com_google_android_gms_internal_zzft.zzb("sendMessageToNativeJs", jSONObject);
                } catch (Throwable e) {
                    zzkd.zzb("Unable to dispatch sendMessageToNativeJs event", e);
                }
            }
        });
    }
}

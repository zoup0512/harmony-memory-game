package com.google.android.gms.internal;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.text.TextUtils;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.ServerProtocol;
import com.google.android.gms.ads.internal.overlay.AdLauncherIntentInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zze;
import com.google.android.gms.ads.internal.zzu;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.mopub.common.AdType;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

@zzin
public final class zzew implements zzep {
    private final zze zzbit;
    private final zzha zzbiu;
    private final zzer zzbiw;

    public zzew(zzer com_google_android_gms_internal_zzer, zze com_google_android_gms_ads_internal_zze, zzha com_google_android_gms_internal_zzha) {
        this.zzbiw = com_google_android_gms_internal_zzer;
        this.zzbit = com_google_android_gms_ads_internal_zze;
        this.zzbiu = com_google_android_gms_internal_zzha;
    }

    private static boolean zzc(Map<String, String> map) {
        return AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(map.get("custom_close"));
    }

    private static int zzd(Map<String, String> map) {
        String str = (String) map.get("o");
        if (str != null) {
            if ("p".equalsIgnoreCase(str)) {
                return zzu.zzfs().zztk();
            }
            if ("l".equalsIgnoreCase(str)) {
                return zzu.zzfs().zztj();
            }
            if ("c".equalsIgnoreCase(str)) {
                return zzu.zzfs().zztl();
            }
        }
        return -1;
    }

    private static void zze(zzlh com_google_android_gms_internal_zzlh, Map<String, String> map) {
        Context context = com_google_android_gms_internal_zzlh.getContext();
        if (TextUtils.isEmpty((String) map.get("u"))) {
            zzb.zzcx("Destination url cannot be empty.");
            return;
        }
        try {
            com_google_android_gms_internal_zzlh.zzuj().zza(new AdLauncherIntentInfoParcel(new zza(com_google_android_gms_internal_zzlh).zza(context, map)));
        } catch (ActivityNotFoundException e) {
            zzb.zzcx(e.getMessage());
        }
    }

    private void zzr(boolean z) {
        if (this.zzbiu != null) {
            this.zzbiu.zzs(z);
        }
    }

    public void zza(zzlh com_google_android_gms_internal_zzlh, Map<String, String> map) {
        String str = (String) map.get("a");
        if (str == null) {
            zzb.zzcx("Action missing from an open GMSG.");
        } else if (this.zzbit == null || this.zzbit.zzel()) {
            zzli zzuj = com_google_android_gms_internal_zzlh.zzuj();
            if ("expand".equalsIgnoreCase(str)) {
                if (com_google_android_gms_internal_zzlh.zzun()) {
                    zzb.zzcx("Cannot expand WebView that is already expanded.");
                    return;
                }
                zzr(false);
                zzuj.zza(zzc(map), zzd(map));
            } else if ("webapp".equalsIgnoreCase(str)) {
                str = (String) map.get("u");
                zzr(false);
                if (str != null) {
                    zzuj.zza(zzc(map), zzd(map), str);
                } else {
                    zzuj.zza(zzc(map), zzd(map), (String) map.get(AdType.HTML), (String) map.get("baseurl"));
                }
            } else if ("in_app_purchase".equalsIgnoreCase(str)) {
                str = (String) map.get(Param.PRODUCT_ID);
                String str2 = (String) map.get("report_urls");
                if (this.zzbiw == null) {
                    return;
                }
                if (str2 == null || str2.isEmpty()) {
                    this.zzbiw.zza(str, new ArrayList());
                } else {
                    this.zzbiw.zza(str, new ArrayList(Arrays.asList(str2.split(" "))));
                }
            } else if (SettingsJsonConstants.APP_KEY.equalsIgnoreCase(str) && ServerProtocol.DIALOG_RETURN_SCOPES_TRUE.equalsIgnoreCase((String) map.get("system_browser"))) {
                zzr(true);
                zze(com_google_android_gms_internal_zzlh, map);
            } else {
                zzr(true);
                str = (String) map.get("u");
                zzuj.zza(new AdLauncherIntentInfoParcel((String) map.get("i"), !TextUtils.isEmpty(str) ? zzu.zzfq().zza(com_google_android_gms_internal_zzlh, str) : str, (String) map.get("m"), (String) map.get("p"), (String) map.get("c"), (String) map.get("f"), (String) map.get("e")));
            }
        } else {
            this.zzbit.zzt((String) map.get("u"));
        }
    }
}

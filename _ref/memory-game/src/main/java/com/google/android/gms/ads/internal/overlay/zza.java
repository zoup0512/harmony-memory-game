package com.google.android.gms.ads.internal.overlay;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzkd;

@zzin
public class zza {
    public boolean zza(Context context, Intent intent, zzp com_google_android_gms_ads_internal_overlay_zzp) {
        try {
            String str = "Launching an intent: ";
            String valueOf = String.valueOf(intent.toURI());
            zzkd.v(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            zzu.zzfq().zzb(context, intent);
            if (com_google_android_gms_ads_internal_overlay_zzp != null) {
                com_google_android_gms_ads_internal_overlay_zzp.zzdo();
            }
            return true;
        } catch (ActivityNotFoundException e) {
            zzb.zzcx(e.getMessage());
            return false;
        }
    }

    public boolean zza(Context context, AdLauncherIntentInfoParcel adLauncherIntentInfoParcel, zzp com_google_android_gms_ads_internal_overlay_zzp) {
        if (adLauncherIntentInfoParcel == null) {
            zzb.zzcx("No intent data for launcher overlay.");
            return false;
        } else if (adLauncherIntentInfoParcel.intent != null) {
            return zza(context, adLauncherIntentInfoParcel.intent, com_google_android_gms_ads_internal_overlay_zzp);
        } else {
            Intent intent = new Intent();
            if (TextUtils.isEmpty(adLauncherIntentInfoParcel.url)) {
                zzb.zzcx("Open GMSG did not contain a URL.");
                return false;
            }
            if (TextUtils.isEmpty(adLauncherIntentInfoParcel.mimeType)) {
                intent.setData(Uri.parse(adLauncherIntentInfoParcel.url));
            } else {
                intent.setDataAndType(Uri.parse(adLauncherIntentInfoParcel.url), adLauncherIntentInfoParcel.mimeType);
            }
            intent.setAction("android.intent.action.VIEW");
            if (!TextUtils.isEmpty(adLauncherIntentInfoParcel.packageName)) {
                intent.setPackage(adLauncherIntentInfoParcel.packageName);
            }
            if (!TextUtils.isEmpty(adLauncherIntentInfoParcel.zzbro)) {
                String[] split = adLauncherIntentInfoParcel.zzbro.split("/", 2);
                if (split.length < 2) {
                    String str = "Could not parse component name from open GMSG: ";
                    String valueOf = String.valueOf(adLauncherIntentInfoParcel.zzbro);
                    zzb.zzcx(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                    return false;
                }
                intent.setClassName(split[0], split[1]);
            }
            Object obj = adLauncherIntentInfoParcel.zzbrp;
            if (!TextUtils.isEmpty(obj)) {
                int parseInt;
                try {
                    parseInt = Integer.parseInt(obj);
                } catch (NumberFormatException e) {
                    zzb.zzcx("Could not parse intent flags.");
                    parseInt = 0;
                }
                intent.addFlags(parseInt);
            }
            return zza(context, intent, com_google_android_gms_ads_internal_overlay_zzp);
        }
    }
}

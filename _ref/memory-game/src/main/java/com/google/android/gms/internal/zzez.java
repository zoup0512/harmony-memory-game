package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Color;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MotionEvent;
import com.cmcm.adsdk.nativead.RequestResultLogger.Model;
import com.facebook.internal.NativeProtocol;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.ads.internal.overlay.zzk;
import com.google.android.gms.ads.internal.util.client.zzb;
import io.fabric.sdk.android.services.settings.AppSettingsData;
import java.util.Map;
import java.util.WeakHashMap;
import org.json.JSONObject;

@zzin
public final class zzez implements zzep {
    private final Map<zzlh, Integer> zzbiz = new WeakHashMap();
    private boolean zzbja;

    private static int zza(Context context, Map<String, String> map, String str, int i) {
        String str2 = (String) map.get(str);
        if (str2 != null) {
            try {
                i = zzm.zziw().zza(context, Integer.parseInt(str2));
            } catch (NumberFormatException e) {
                zzb.zzcx(new StringBuilder((String.valueOf(str).length() + 34) + String.valueOf(str2).length()).append("Could not parse ").append(str).append(" in a video GMSG: ").append(str2).toString());
            }
        }
        return i;
    }

    public void zza(zzlh com_google_android_gms_internal_zzlh, Map<String, String> map) {
        String str = (String) map.get(NativeProtocol.WEB_DIALOG_ACTION);
        if (str == null) {
            zzb.zzcx("Action missing from video GMSG.");
            return;
        }
        if (zzb.zzaz(3)) {
            JSONObject jSONObject = new JSONObject(map);
            jSONObject.remove("google.afma.Notify_dt");
            String valueOf = String.valueOf(jSONObject.toString());
            zzb.zzcv(new StringBuilder((String.valueOf(str).length() + 13) + String.valueOf(valueOf).length()).append("Video GMSG: ").append(str).append(" ").append(valueOf).toString());
        }
        if ("background".equals(str)) {
            valueOf = (String) map.get("color");
            if (TextUtils.isEmpty(valueOf)) {
                zzb.zzcx("Color parameter missing from color video GMSG.");
                return;
            }
            try {
                int parseColor = Color.parseColor(valueOf);
                zzlg zzuq = com_google_android_gms_internal_zzlh.zzuq();
                if (zzuq != null) {
                    zzk zzub = zzuq.zzub();
                    if (zzub != null) {
                        zzub.setBackgroundColor(parseColor);
                        return;
                    }
                }
                this.zzbiz.put(com_google_android_gms_internal_zzlh, Integer.valueOf(parseColor));
                return;
            } catch (IllegalArgumentException e) {
                zzb.zzcx("Invalid color parameter in video GMSG.");
                return;
            }
        }
        zzlg zzuq2 = com_google_android_gms_internal_zzlh.zzuq();
        if (zzuq2 == null) {
            zzb.zzcx("Could not get underlay container for a video GMSG.");
            return;
        }
        boolean equals = AppSettingsData.STATUS_NEW.equals(str);
        boolean equals2 = "position".equals(str);
        int zza;
        int zza2;
        if (equals || equals2) {
            int parseInt;
            Context context = com_google_android_gms_internal_zzlh.getContext();
            int zza3 = zza(context, map, "x", 0);
            zza = zza(context, map, "y", 0);
            zza2 = zza(context, map, "w", -1);
            int zza4 = zza(context, map, "h", -1);
            try {
                parseInt = Integer.parseInt((String) map.get("player"));
            } catch (NumberFormatException e2) {
                parseInt = 0;
            }
            boolean parseBoolean = Boolean.parseBoolean((String) map.get("spherical"));
            if (equals && zzuq2.zzub() == null) {
                zzuq2.zza(zza3, zza, zza2, zza4, parseInt, parseBoolean);
                if (this.zzbiz.containsKey(com_google_android_gms_internal_zzlh)) {
                    zzuq2.zzub().setBackgroundColor(((Integer) this.zzbiz.get(com_google_android_gms_internal_zzlh)).intValue());
                    return;
                }
                return;
            }
            zzuq2.zze(zza3, zza, zza2, zza4);
            return;
        }
        zzk zzub2 = zzuq2.zzub();
        if (zzub2 == null) {
            zzk.zzh(com_google_android_gms_internal_zzlh);
        } else if ("click".equals(str)) {
            r0 = com_google_android_gms_internal_zzlh.getContext();
            zza = zza(r0, map, "x", 0);
            zza2 = zza(r0, map, "y", 0);
            long uptimeMillis = SystemClock.uptimeMillis();
            MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 0, (float) zza, (float) zza2, 0);
            zzub2.zzd(obtain);
            obtain.recycle();
        } else if ("currentTime".equals(str)) {
            valueOf = (String) map.get(Model.KEY_loadtime);
            if (valueOf == null) {
                zzb.zzcx("Time parameter missing from currentTime video GMSG.");
                return;
            }
            try {
                zzub2.seekTo((int) (Float.parseFloat(valueOf) * 1000.0f));
            } catch (NumberFormatException e3) {
                str = "Could not parse time parameter from currentTime video GMSG: ";
                valueOf = String.valueOf(valueOf);
                zzb.zzcx(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            }
        } else if ("hide".equals(str)) {
            zzub2.setVisibility(4);
        } else if ("load".equals(str)) {
            zzub2.zzlv();
        } else if ("mimetype".equals(str)) {
            zzub2.setMimeType((String) map.get("mimetype"));
        } else if ("muted".equals(str)) {
            if (Boolean.parseBoolean((String) map.get("muted"))) {
                zzub2.zzno();
            } else {
                zzub2.zznp();
            }
        } else if ("pause".equals(str)) {
            zzub2.pause();
        } else if ("play".equals(str)) {
            zzub2.play();
        } else if ("show".equals(str)) {
            zzub2.setVisibility(0);
        } else if ("src".equals(str)) {
            zzub2.zzbw((String) map.get("src"));
        } else if ("touchMove".equals(str)) {
            r0 = com_google_android_gms_internal_zzlh.getContext();
            zzub2.zza((float) zza(r0, map, "dx", 0), (float) zza(r0, map, "dy", 0));
            if (!this.zzbja) {
                com_google_android_gms_internal_zzlh.zzuh().zzob();
                this.zzbja = true;
            }
        } else if ("volume".equals(str)) {
            valueOf = (String) map.get("volume");
            if (valueOf == null) {
                zzb.zzcx("Level parameter missing from volume video GMSG.");
                return;
            }
            try {
                zzub2.zza(Float.parseFloat(valueOf));
            } catch (NumberFormatException e4) {
                str = "Could not parse volume parameter from volume video GMSG: ";
                valueOf = String.valueOf(valueOf);
                zzb.zzcx(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            }
        } else if ("watermark".equals(str)) {
            zzub2.zzon();
        } else {
            String str2 = "Unknown video action: ";
            valueOf = String.valueOf(str);
            zzb.zzcx(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        }
    }
}

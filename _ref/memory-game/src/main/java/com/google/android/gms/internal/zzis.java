package com.google.android.gms.internal;

import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.request.AutoClickProtectionConfigurationParcel;
import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

@zzin
public final class zzis {
    private int mOrientation = -1;
    private boolean zzawn = false;
    private String zzbfi;
    private final AdRequestInfoParcel zzbot;
    private List<String> zzbzf;
    private String zzcet;
    private String zzceu;
    private List<String> zzcev;
    private String zzcew;
    private String zzcex;
    private List<String> zzcey;
    private long zzcez = -1;
    private boolean zzcfa = false;
    private final long zzcfb = -1;
    private long zzcfc = -1;
    private boolean zzcfd = false;
    private boolean zzcfe = false;
    private boolean zzcff = false;
    private boolean zzcfg = true;
    private String zzcfh = "";
    private boolean zzcfi = false;
    private RewardItemParcel zzcfj;
    private List<String> zzcfk;
    private List<String> zzcfl;
    private boolean zzcfm = false;
    private AutoClickProtectionConfigurationParcel zzcfn;
    private boolean zzcfo = false;
    private String zzcfp;
    private List<String> zzcfq;
    private String zzcfr;
    private boolean zzcfs;
    private String zzcft;

    public zzis(AdRequestInfoParcel adRequestInfoParcel) {
        this.zzbot = adRequestInfoParcel;
    }

    private void zzaa(Map<String, List<String>> map) {
        List list = (List) map.get("X-Afma-Gws-Query-Id");
        if (list != null && !list.isEmpty()) {
            this.zzcfh = (String) list.get(0);
        }
    }

    private void zzab(Map<String, List<String>> map) {
        String zzd = zzd(map, "X-Afma-Fluid");
        if (zzd != null && zzd.equals("height")) {
            this.zzcfi = true;
        }
    }

    private void zzac(Map<String, List<String>> map) {
        this.zzawn = "native_express".equals(zzd(map, "X-Afma-Ad-Format"));
    }

    private void zzad(Map<String, List<String>> map) {
        this.zzcfj = RewardItemParcel.zzch(zzd(map, "X-Afma-Rewards"));
    }

    private void zzae(Map<String, List<String>> map) {
        if (this.zzcfk == null) {
            this.zzcfk = zzf(map, "X-Afma-Reward-Video-Start-Urls");
        }
    }

    private void zzaf(Map<String, List<String>> map) {
        if (this.zzcfl == null) {
            this.zzcfl = zzf(map, "X-Afma-Reward-Video-Complete-Urls");
        }
    }

    private void zzag(Map<String, List<String>> map) {
        this.zzcfm |= zzg(map, "X-Afma-Use-Displayed-Impression");
    }

    private void zzah(Map<String, List<String>> map) {
        this.zzcfo |= zzg(map, "X-Afma-Auto-Collect-Location");
    }

    private void zzai(Map<String, List<String>> map) {
        List zzf = zzf(map, "X-Afma-Remote-Ping-Urls");
        if (zzf != null) {
            this.zzcfq = zzf;
        }
    }

    private void zzaj(Map<String, List<String>> map) {
        Object zzd = zzd(map, "X-Afma-Auto-Protection-Configuration");
        if (zzd == null || TextUtils.isEmpty(zzd)) {
            Builder buildUpon = Uri.parse("https://pagead2.googlesyndication.com/pagead/gen_204").buildUpon();
            buildUpon.appendQueryParameter("id", "gmob-apps-blocked-navigation");
            if (!TextUtils.isEmpty(this.zzcew)) {
                buildUpon.appendQueryParameter("debugDialog", this.zzcew);
            }
            boolean booleanValue = ((Boolean) zzdc.zzayg.get()).booleanValue();
            String[] strArr = new String[1];
            String valueOf = String.valueOf(buildUpon.toString());
            String valueOf2 = String.valueOf("navigationURL");
            strArr[0] = new StringBuilder((String.valueOf(valueOf).length() + 18) + String.valueOf(valueOf2).length()).append(valueOf).append("&").append(valueOf2).append("={NAVIGATION_URL}").toString();
            this.zzcfn = new AutoClickProtectionConfigurationParcel(booleanValue, Arrays.asList(strArr));
            return;
        }
        try {
            this.zzcfn = AutoClickProtectionConfigurationParcel.zzh(new JSONObject(zzd));
        } catch (Throwable e) {
            zzb.zzd("Error parsing configuration JSON", e);
            this.zzcfn = new AutoClickProtectionConfigurationParcel();
        }
    }

    private void zzak(Map<String, List<String>> map) {
        this.zzcfp = zzd(map, "Set-Cookie");
    }

    private void zzal(Map<String, List<String>> map) {
        this.zzcfr = zzd(map, "X-Afma-Safe-Browsing");
    }

    static String zzd(Map<String, List<String>> map, String str) {
        List list = (List) map.get(str);
        return (list == null || list.isEmpty()) ? null : (String) list.get(0);
    }

    static long zze(Map<String, List<String>> map, String str) {
        List list = (List) map.get(str);
        if (!(list == null || list.isEmpty())) {
            String str2 = (String) list.get(0);
            try {
                return (long) (Float.parseFloat(str2) * 1000.0f);
            } catch (NumberFormatException e) {
                zzb.zzcx(new StringBuilder((String.valueOf(str).length() + 36) + String.valueOf(str2).length()).append("Could not parse float from ").append(str).append(" header: ").append(str2).toString());
            }
        }
        return -1;
    }

    static List<String> zzf(Map<String, List<String>> map, String str) {
        List list = (List) map.get(str);
        if (!(list == null || list.isEmpty())) {
            String str2 = (String) list.get(0);
            if (str2 != null) {
                return Arrays.asList(str2.trim().split("\\s+"));
            }
        }
        return null;
    }

    private boolean zzg(Map<String, List<String>> map, String str) {
        List list = (List) map.get(str);
        return (list == null || list.isEmpty() || !Boolean.valueOf((String) list.get(0)).booleanValue()) ? false : true;
    }

    private void zzk(Map<String, List<String>> map) {
        this.zzcet = zzd(map, "X-Afma-Ad-Size");
    }

    private void zzl(Map<String, List<String>> map) {
        this.zzcft = zzd(map, "X-Afma-Ad-Slot-Size");
    }

    private void zzm(Map<String, List<String>> map) {
        List zzf = zzf(map, "X-Afma-Click-Tracking-Urls");
        if (zzf != null) {
            this.zzcev = zzf;
        }
    }

    private void zzn(Map<String, List<String>> map) {
        List list = (List) map.get("X-Afma-Debug-Dialog");
        if (list != null && !list.isEmpty()) {
            this.zzcew = (String) list.get(0);
        }
    }

    private void zzo(Map<String, List<String>> map) {
        List zzf = zzf(map, "X-Afma-Tracking-Urls");
        if (zzf != null) {
            this.zzcey = zzf;
        }
    }

    private void zzp(Map<String, List<String>> map) {
        long zze = zze(map, "X-Afma-Interstitial-Timeout");
        if (zze != -1) {
            this.zzcez = zze;
        }
    }

    private void zzq(Map<String, List<String>> map) {
        this.zzcex = zzd(map, "X-Afma-ActiveView");
    }

    private void zzr(Map<String, List<String>> map) {
        this.zzcfe = AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE.equals(zzd(map, "X-Afma-Ad-Format"));
    }

    private void zzs(Map<String, List<String>> map) {
        this.zzcfd |= zzg(map, "X-Afma-Custom-Rendering-Allowed");
    }

    private void zzt(Map<String, List<String>> map) {
        this.zzcfa |= zzg(map, "X-Afma-Mediation");
    }

    private void zzu(Map<String, List<String>> map) {
        this.zzcfs |= zzg(map, "X-Afma-Render-In-Browser");
    }

    private void zzv(Map<String, List<String>> map) {
        List zzf = zzf(map, "X-Afma-Manual-Tracking-Urls");
        if (zzf != null) {
            this.zzbzf = zzf;
        }
    }

    private void zzw(Map<String, List<String>> map) {
        long zze = zze(map, "X-Afma-Refresh-Rate");
        if (zze != -1) {
            this.zzcfc = zze;
        }
    }

    private void zzx(Map<String, List<String>> map) {
        List list = (List) map.get("X-Afma-Orientation");
        if (list != null && !list.isEmpty()) {
            String str = (String) list.get(0);
            if (DeviceInfo.ORIENTATION_PORTRAIT.equalsIgnoreCase(str)) {
                this.mOrientation = zzu.zzfs().zztk();
            } else if (DeviceInfo.ORIENTATION_LANDSCAPE.equalsIgnoreCase(str)) {
                this.mOrientation = zzu.zzfs().zztj();
            }
        }
    }

    private void zzy(Map<String, List<String>> map) {
        List list = (List) map.get("X-Afma-Use-HTTPS");
        if (list != null && !list.isEmpty()) {
            this.zzcff = Boolean.valueOf((String) list.get(0)).booleanValue();
        }
    }

    private void zzz(Map<String, List<String>> map) {
        List list = (List) map.get("X-Afma-Content-Url-Opted-Out");
        if (list != null && !list.isEmpty()) {
            this.zzcfg = Boolean.valueOf((String) list.get(0)).booleanValue();
        }
    }

    public void zzb(String str, Map<String, List<String>> map, String str2) {
        this.zzceu = str;
        this.zzbfi = str2;
        zzj((Map) map);
    }

    public AdResponseParcel zzj(long j) {
        return new AdResponseParcel(this.zzbot, this.zzceu, this.zzbfi, this.zzcev, this.zzcey, this.zzcez, this.zzcfa, -1, this.zzbzf, this.zzcfc, this.mOrientation, this.zzcet, j, this.zzcew, this.zzcex, this.zzcfd, this.zzcfe, this.zzcff, this.zzcfg, false, this.zzcfh, this.zzcfi, this.zzawn, this.zzcfj, this.zzcfk, this.zzcfl, this.zzcfm, this.zzcfn, this.zzcfo, this.zzcfp, this.zzcfq, this.zzcfr, this.zzcfs, this.zzcft);
    }

    public void zzj(Map<String, List<String>> map) {
        zzk(map);
        zzl(map);
        zzm(map);
        zzn(map);
        zzo(map);
        zzp(map);
        zzt(map);
        zzv(map);
        zzw(map);
        zzx(map);
        zzq(map);
        zzy(map);
        zzs(map);
        zzr(map);
        zzz(map);
        zzaa(map);
        zzab(map);
        zzac(map);
        zzad(map);
        zzae(map);
        zzaf(map);
        zzag(map);
        zzah(map);
        zzak(map);
        zzaj(map);
        zzai(map);
        zzal(map);
        zzu(map);
    }
}

package com.google.android.gms.internal;

import android.text.TextUtils;
import android.util.Log;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.gms.analytics.zzg;
import com.google.android.gms.common.internal.zzab;
import com.mopub.common.AdType;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class zzme extends zzg<zzme> {
    private String zzcvp;
    private int zzcvq;
    private int zzcvr;
    private String zzcvs;
    private String zzcvt;
    private boolean zzcvu;
    private boolean zzcvv;

    public zzme() {
        this(false);
    }

    public zzme(boolean z) {
        this(z, zzyd());
    }

    public zzme(boolean z, int i) {
        zzab.zzgh(i);
        this.zzcvq = i;
        this.zzcvv = z;
    }

    static int zzyd() {
        UUID randomUUID = UUID.randomUUID();
        int leastSignificantBits = (int) (randomUUID.getLeastSignificantBits() & 2147483647L);
        if (leastSignificantBits != 0) {
            return leastSignificantBits;
        }
        leastSignificantBits = (int) (randomUUID.getMostSignificantBits() & 2147483647L);
        if (leastSignificantBits != 0) {
            return leastSignificantBits;
        }
        Log.e("GAv4", "UUID.randomUUID() returned 0.");
        return Integer.MAX_VALUE;
    }

    private void zzyh() {
    }

    public void setScreenName(String str) {
        zzyh();
        this.zzcvp = str;
    }

    public String toString() {
        Map hashMap = new HashMap();
        hashMap.put("screenName", this.zzcvp);
        hashMap.put(AdType.INTERSTITIAL, Boolean.valueOf(this.zzcvu));
        hashMap.put(AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_AUTOMATIC, Boolean.valueOf(this.zzcvv));
        hashMap.put("screenId", Integer.valueOf(this.zzcvq));
        hashMap.put("referrerScreenId", Integer.valueOf(this.zzcvr));
        hashMap.put("referrerScreenName", this.zzcvs);
        hashMap.put("referrerUri", this.zzcvt);
        return zzg.zzj(hashMap);
    }

    public void zza(zzme com_google_android_gms_internal_zzme) {
        if (!TextUtils.isEmpty(this.zzcvp)) {
            com_google_android_gms_internal_zzme.setScreenName(this.zzcvp);
        }
        if (this.zzcvq != 0) {
            com_google_android_gms_internal_zzme.zzbu(this.zzcvq);
        }
        if (this.zzcvr != 0) {
            com_google_android_gms_internal_zzme.zzbv(this.zzcvr);
        }
        if (!TextUtils.isEmpty(this.zzcvs)) {
            com_google_android_gms_internal_zzme.zzdz(this.zzcvs);
        }
        if (!TextUtils.isEmpty(this.zzcvt)) {
            com_google_android_gms_internal_zzme.zzea(this.zzcvt);
        }
        if (this.zzcvu) {
            com_google_android_gms_internal_zzme.zzar(this.zzcvu);
        }
        if (this.zzcvv) {
            com_google_android_gms_internal_zzme.zzaq(this.zzcvv);
        }
    }

    public void zzaq(boolean z) {
        zzyh();
        this.zzcvv = z;
    }

    public void zzar(boolean z) {
        zzyh();
        this.zzcvu = z;
    }

    public /* synthetic */ void zzb(zzg com_google_android_gms_analytics_zzg) {
        zza((zzme) com_google_android_gms_analytics_zzg);
    }

    public void zzbu(int i) {
        zzyh();
        this.zzcvq = i;
    }

    public void zzbv(int i) {
        zzyh();
        this.zzcvr = i;
    }

    public void zzdz(String str) {
        zzyh();
        this.zzcvs = str;
    }

    public void zzea(String str) {
        zzyh();
        if (TextUtils.isEmpty(str)) {
            this.zzcvt = null;
        } else {
            this.zzcvt = str;
        }
    }

    public String zzye() {
        return this.zzcvp;
    }

    public int zzyf() {
        return this.zzcvq;
    }

    public String zzyg() {
        return this.zzcvt;
    }
}

package com.google.android.gms.analytics;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import com.google.android.gms.analytics.internal.zzd;
import com.google.android.gms.analytics.internal.zzf;
import java.util.HashMap;
import java.util.Map;

class Tracker$zza extends zzd implements GoogleAnalytics$zza {
    final /* synthetic */ Tracker zzcug;
    private boolean zzcuh;
    private int zzcui;
    private long zzcuj = -1;
    private boolean zzcuk;
    private long zzcul;

    protected Tracker$zza(Tracker tracker, zzf com_google_android_gms_analytics_internal_zzf) {
        this.zzcug = tracker;
        super(com_google_android_gms_analytics_internal_zzf);
    }

    private void zzwz() {
        if (this.zzcuj >= 0 || this.zzcuh) {
            zzvx().zza(Tracker.zza(this.zzcug));
        } else {
            zzvx().zzb(Tracker.zza(this.zzcug));
        }
    }

    public void enableAutoActivityTracking(boolean z) {
        this.zzcuh = z;
        zzwz();
    }

    public void setSessionTimeout(long j) {
        this.zzcuj = j;
        zzwz();
    }

    public void zzo(Activity activity) {
        if (this.zzcui == 0 && zzxa()) {
            this.zzcuk = true;
        }
        this.zzcui++;
        if (this.zzcuh) {
            Intent intent = activity.getIntent();
            if (intent != null) {
                this.zzcug.setCampaignParamsOnNextHit(intent.getData());
            }
            Map hashMap = new HashMap();
            hashMap.put("&t", "screenview");
            this.zzcug.set("&cd", Tracker.zzk(this.zzcug) != null ? Tracker.zzk(this.zzcug).zzr(activity) : activity.getClass().getCanonicalName());
            if (TextUtils.isEmpty((CharSequence) hashMap.get("&dr"))) {
                CharSequence zzq = Tracker.zzq(activity);
                if (!TextUtils.isEmpty(zzq)) {
                    hashMap.put("&dr", zzq);
                }
            }
            this.zzcug.send(hashMap);
        }
    }

    public void zzp(Activity activity) {
        this.zzcui--;
        this.zzcui = Math.max(0, this.zzcui);
        if (this.zzcui == 0) {
            this.zzcul = zzyw().elapsedRealtime();
        }
    }

    protected void zzwv() {
    }

    public synchronized boolean zzwy() {
        boolean z;
        z = this.zzcuk;
        this.zzcuk = false;
        return z;
    }

    boolean zzxa() {
        return zzyw().elapsedRealtime() >= this.zzcul + Math.max(1000, this.zzcuj);
    }
}

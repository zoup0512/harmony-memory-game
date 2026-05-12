package com.google.android.gms.analytics;

import android.text.TextUtils;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.analytics.internal.zza;
import com.google.android.gms.analytics.internal.zzab;
import com.google.android.gms.analytics.internal.zzao;
import com.google.android.gms.analytics.internal.zze;
import com.google.android.gms.analytics.internal.zzh;
import com.google.android.gms.internal.zzlu;
import java.util.HashMap;
import java.util.Map;

class Tracker$1 implements Runnable {
    final /* synthetic */ Map zzctz;
    final /* synthetic */ boolean zzcua;
    final /* synthetic */ String zzcub;
    final /* synthetic */ long zzcuc;
    final /* synthetic */ boolean zzcud;
    final /* synthetic */ boolean zzcue;
    final /* synthetic */ String zzcuf;
    final /* synthetic */ Tracker zzcug;

    Tracker$1(Tracker tracker, Map map, boolean z, String str, long j, boolean z2, boolean z3, String str2) {
        this.zzcug = tracker;
        this.zzctz = map;
        this.zzcua = z;
        this.zzcub = str;
        this.zzcuc = j;
        this.zzcud = z2;
        this.zzcue = z3;
        this.zzcuf = str2;
    }

    public void run() {
        boolean z = true;
        if (Tracker.zza(this.zzcug).zzwy()) {
            this.zzctz.put("sc", "start");
        }
        zzao.zzd(this.zzctz, "cid", this.zzcug.zzvx().zzwb());
        String str = (String) this.zzctz.get("sf");
        if (str != null) {
            double zza = zzao.zza(str, 100.0d);
            if (zzao.zza(zza, (String) this.zzctz.get("cid"))) {
                this.zzcug.zzb("Sampling enabled. Hit sampled out. sample rate", Double.valueOf(zza));
                return;
            }
        }
        zza zzb = Tracker.zzb(this.zzcug);
        if (this.zzcua) {
            zzao.zzb(this.zzctz, "ate", zzb.zzxz());
            zzao.zzc(this.zzctz, "adid", zzb.zzyk());
        } else {
            this.zzctz.remove("ate");
            this.zzctz.remove("adid");
        }
        zzlu zzaad = Tracker.zzc(this.zzcug).zzaad();
        zzao.zzc(this.zzctz, "an", zzaad.zzxb());
        zzao.zzc(this.zzctz, "av", zzaad.zzxc());
        zzao.zzc(this.zzctz, "aid", zzaad.zzsh());
        zzao.zzc(this.zzctz, "aiid", zzaad.zzxd());
        this.zzctz.put("v", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        this.zzctz.put("_v", zze.zzcwr);
        zzao.zzc(this.zzctz, "ul", Tracker.zzd(this.zzcug).zzack().getLanguage());
        zzao.zzc(this.zzctz, "sr", Tracker.zze(this.zzcug).zzacl());
        boolean z2 = this.zzcub.equals("transaction") || this.zzcub.equals("item");
        if (z2 || Tracker.zzf(this.zzcug).zzade()) {
            long zzey = zzao.zzey((String) this.zzctz.get("ht"));
            if (zzey == 0) {
                zzey = this.zzcuc;
            }
            if (this.zzcud) {
                Tracker.zzh(this.zzcug).zzc("Dry run enabled. Would have sent hit", new zzab(this.zzcug, this.zzctz, zzey, this.zzcue));
                return;
            }
            String str2 = (String) this.zzctz.get("cid");
            Map hashMap = new HashMap();
            zzao.zza(hashMap, "uid", this.zzctz);
            zzao.zza(hashMap, "an", this.zzctz);
            zzao.zza(hashMap, "aid", this.zzctz);
            zzao.zza(hashMap, "av", this.zzctz);
            zzao.zza(hashMap, "aiid", this.zzctz);
            String str3 = this.zzcuf;
            if (TextUtils.isEmpty((CharSequence) this.zzctz.get("adid"))) {
                z = false;
            }
            this.zzctz.put("_s", String.valueOf(Tracker.zzi(this.zzcug).zza(new zzh(0, str2, str3, z, 0, hashMap))));
            Tracker.zzj(this.zzcug).zza(new zzab(this.zzcug, this.zzctz, zzey, this.zzcue));
            return;
        }
        Tracker.zzg(this.zzcug).zzh(this.zzctz, "Too many hits sent too quickly, rate limiting invoked");
    }
}

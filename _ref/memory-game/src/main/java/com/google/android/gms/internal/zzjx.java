package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.security.NetworkSecurityPolicy;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.dynamite.descriptors.com.google.android.gms.ads.dynamite.ModuleDescriptor;
import com.google.android.gms.internal.zzkf.zzb;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.Future;

@zzin
public class zzjx implements zzb {
    private Context mContext;
    private final Object zzail = new Object();
    private zzcg zzaju;
    private VersionInfoParcel zzalo;
    private boolean zzamt = false;
    private zzcn zzask = null;
    private zzcm zzasl = null;
    private String zzbjf;
    private boolean zzcff = true;
    private boolean zzcfg = true;
    private boolean zzcfo = false;
    private final String zzcjm;
    private final zzjy zzcjn;
    private BigInteger zzcjo = BigInteger.ONE;
    private final HashSet<zzjv> zzcjp = new HashSet();
    private final HashMap<String, zzka> zzcjq = new HashMap();
    private boolean zzcjr = false;
    private int zzcjs = 0;
    private zzde zzcjt = null;
    private zzco zzcju = null;
    private String zzcjv;
    private Boolean zzcjw = null;
    private boolean zzcjx = false;
    private boolean zzcjy = false;
    private boolean zzcjz = false;
    private String zzcka = "";
    private long zzckb = 0;

    public zzjx(zzkh com_google_android_gms_internal_zzkh) {
        this.zzcjm = com_google_android_gms_internal_zzkh.zztf();
        this.zzcjn = new zzjy(this.zzcjm);
    }

    public Resources getResources() {
        if (this.zzalo.zzcnm) {
            return this.mContext.getResources();
        }
        try {
            zzsb zza = zzsb.zza(this.mContext, zzsb.KI, ModuleDescriptor.MODULE_ID);
            return zza != null ? zza.zzbby().getResources() : null;
        } catch (Throwable e) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Cannot load resource from dynamite apk or local jar", e);
            return null;
        }
    }

    public String getSessionId() {
        return this.zzcjm;
    }

    public Bundle zza(Context context, zzjz com_google_android_gms_internal_zzjz, String str) {
        Bundle bundle;
        synchronized (this.zzail) {
            bundle = new Bundle();
            bundle.putBundle(SettingsJsonConstants.APP_KEY, this.zzcjn.zze(context, str));
            Bundle bundle2 = new Bundle();
            for (String str2 : this.zzcjq.keySet()) {
                bundle2.putBundle(str2, ((zzka) this.zzcjq.get(str2)).toBundle());
            }
            bundle.putBundle("slots", bundle2);
            ArrayList arrayList = new ArrayList();
            Iterator it = this.zzcjp.iterator();
            while (it.hasNext()) {
                arrayList.add(((zzjv) it.next()).toBundle());
            }
            bundle.putParcelableArrayList("ads", arrayList);
            com_google_android_gms_internal_zzjz.zza(this.zzcjp);
            this.zzcjp.clear();
        }
        return bundle;
    }

    public void zza(zzjv com_google_android_gms_internal_zzjv) {
        synchronized (this.zzail) {
            this.zzcjp.add(com_google_android_gms_internal_zzjv);
        }
    }

    public void zza(String str, zzka com_google_android_gms_internal_zzka) {
        synchronized (this.zzail) {
            this.zzcjq.put(str, com_google_android_gms_internal_zzka);
        }
    }

    public void zza(Thread thread) {
        zzim.zza(this.mContext, thread, this.zzalo);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.internal.zzco zzaa(android.content.Context r11) {
        /*
        r10 = this;
        r2 = 0;
        r1 = com.google.android.gms.internal.zzdc.zzazh;
        r1 = r1.get();
        r1 = (java.lang.Boolean) r1;
        r1 = r1.booleanValue();
        if (r1 == 0) goto L_0x001b;
    L_0x000f:
        r1 = com.google.android.gms.common.util.zzs.zzavq();
        if (r1 == 0) goto L_0x001b;
    L_0x0015:
        r1 = r10.zzsi();
        if (r1 == 0) goto L_0x001d;
    L_0x001b:
        r1 = r2;
    L_0x001c:
        return r1;
    L_0x001d:
        r3 = r10.zzail;
        monitor-enter(r3);
        r1 = android.os.Looper.getMainLooper();	 Catch:{ all -> 0x0070 }
        if (r1 == 0) goto L_0x0028;
    L_0x0026:
        if (r11 != 0) goto L_0x002b;
    L_0x0028:
        monitor-exit(r3);	 Catch:{ all -> 0x0070 }
        r1 = r2;
        goto L_0x001c;
    L_0x002b:
        r1 = r10.zzask;	 Catch:{ all -> 0x0070 }
        if (r1 != 0) goto L_0x0042;
    L_0x002f:
        r1 = r11.getApplicationContext();	 Catch:{ all -> 0x0070 }
        r1 = (android.app.Application) r1;	 Catch:{ all -> 0x0070 }
        r2 = new com.google.android.gms.internal.zzcn;	 Catch:{ all -> 0x0070 }
        if (r1 != 0) goto L_0x003d;
    L_0x0039:
        r0 = r11;
        r0 = (android.app.Application) r0;	 Catch:{ all -> 0x0070 }
        r1 = r0;
    L_0x003d:
        r2.<init>(r1, r11);	 Catch:{ all -> 0x0070 }
        r10.zzask = r2;	 Catch:{ all -> 0x0070 }
    L_0x0042:
        r1 = r10.zzasl;	 Catch:{ all -> 0x0070 }
        if (r1 != 0) goto L_0x004d;
    L_0x0046:
        r1 = new com.google.android.gms.internal.zzcm;	 Catch:{ all -> 0x0070 }
        r1.<init>();	 Catch:{ all -> 0x0070 }
        r10.zzasl = r1;	 Catch:{ all -> 0x0070 }
    L_0x004d:
        r1 = r10.zzcju;	 Catch:{ all -> 0x0070 }
        if (r1 != 0) goto L_0x0067;
    L_0x0051:
        r1 = new com.google.android.gms.internal.zzco;	 Catch:{ all -> 0x0070 }
        r2 = r10.zzask;	 Catch:{ all -> 0x0070 }
        r4 = r10.zzasl;	 Catch:{ all -> 0x0070 }
        r5 = new com.google.android.gms.internal.zzim;	 Catch:{ all -> 0x0070 }
        r6 = r10.mContext;	 Catch:{ all -> 0x0070 }
        r7 = r10.zzalo;	 Catch:{ all -> 0x0070 }
        r8 = 0;
        r9 = 0;
        r5.<init>(r6, r7, r8, r9);	 Catch:{ all -> 0x0070 }
        r1.<init>(r2, r4, r5);	 Catch:{ all -> 0x0070 }
        r10.zzcju = r1;	 Catch:{ all -> 0x0070 }
    L_0x0067:
        r1 = r10.zzcju;	 Catch:{ all -> 0x0070 }
        r1.zzhz();	 Catch:{ all -> 0x0070 }
        r1 = r10.zzcju;	 Catch:{ all -> 0x0070 }
        monitor-exit(r3);	 Catch:{ all -> 0x0070 }
        goto L_0x001c;
    L_0x0070:
        r1 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0070 }
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzjx.zzaa(android.content.Context):com.google.android.gms.internal.zzco");
    }

    public void zzae(boolean z) {
        synchronized (this.zzail) {
            if (this.zzcfg != z) {
                zzkf.zze(this.mContext, z);
            }
            this.zzcfg = z;
            zzco zzaa = zzaa(this.mContext);
            if (!(zzaa == null || zzaa.isAlive())) {
                com.google.android.gms.ads.internal.util.client.zzb.zzcw("start fetching content...");
                zzaa.zzhz();
            }
        }
    }

    public void zzaf(boolean z) {
        this.zzcjz = z;
    }

    public void zzag(boolean z) {
        synchronized (this.zzail) {
            this.zzcjx = z;
        }
    }

    @TargetApi(23)
    public void zzb(Context context, VersionInfoParcel versionInfoParcel) {
        synchronized (this.zzail) {
            if (!this.zzamt) {
                this.mContext = context.getApplicationContext();
                this.zzalo = versionInfoParcel;
                zzkf.zza(context, this);
                zzkf.zzb(context, this);
                zzkf.zzc(context, (zzb) this);
                zzkf.zzd(context, this);
                zzkf.zze(context, (zzb) this);
                zzkf.zzf(context, (zzb) this);
                zza(Thread.currentThread());
                this.zzbjf = zzu.zzfq().zzg(context, versionInfoParcel.zzcs);
                if (zzs.zzavy() && !NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted()) {
                    this.zzcjy = true;
                }
                this.zzaju = new zzcg(context.getApplicationContext(), this.zzalo, zzu.zzfq().zzc(context, versionInfoParcel));
                zzsw();
                zzu.zzga().zzt(this.mContext);
                this.zzamt = true;
            }
        }
    }

    public void zzb(Boolean bool) {
        synchronized (this.zzail) {
            this.zzcjw = bool;
        }
    }

    public void zzb(Throwable th, boolean z) {
        new zzim(this.mContext, this.zzalo, null, null).zza(th, z);
    }

    public void zzb(HashSet<zzjv> hashSet) {
        synchronized (this.zzail) {
            this.zzcjp.addAll(hashSet);
        }
    }

    public Future zzc(Context context, boolean z) {
        Future zzc;
        synchronized (this.zzail) {
            if (z != this.zzcff) {
                this.zzcff = z;
                zzc = zzkf.zzc(context, z);
            } else {
                zzc = null;
            }
        }
        return zzc;
    }

    public Future zzcm(String str) {
        Future zzf;
        synchronized (this.zzail) {
            if (str != null) {
                if (!str.equals(this.zzcjv)) {
                    this.zzcjv = str;
                    zzf = zzkf.zzf(this.mContext, str);
                }
            }
            zzf = null;
        }
        return zzf;
    }

    public Future zzd(Context context, String str) {
        Future zza;
        this.zzckb = zzu.zzfu().currentTimeMillis();
        synchronized (this.zzail) {
            if (str != null) {
                if (!str.equals(this.zzcka)) {
                    this.zzcka = str;
                    zza = zzkf.zza(context, str, this.zzckb);
                }
            }
            zza = null;
        }
        return zza;
    }

    public Future zzd(Context context, boolean z) {
        Future zzf;
        synchronized (this.zzail) {
            if (z != this.zzcfo) {
                this.zzcfo = z;
                zzf = zzkf.zzf(context, z);
            } else {
                zzf = null;
            }
        }
        return zzf;
    }

    public void zzg(Bundle bundle) {
        synchronized (this.zzail) {
            this.zzcff = bundle.containsKey("use_https") ? bundle.getBoolean("use_https") : this.zzcff;
            this.zzcjs = bundle.containsKey("webview_cache_version") ? bundle.getInt("webview_cache_version") : this.zzcjs;
            if (bundle.containsKey("content_url_opted_out")) {
                zzae(bundle.getBoolean("content_url_opted_out"));
            }
            if (bundle.containsKey("content_url_hashes")) {
                this.zzcjv = bundle.getString("content_url_hashes");
            }
            this.zzcfo = bundle.containsKey("auto_collect_location") ? bundle.getBoolean("auto_collect_location") : this.zzcfo;
            this.zzcka = bundle.containsKey("app_settings_json") ? bundle.getString("app_settings_json") : this.zzcka;
            this.zzckb = bundle.containsKey("app_settings_last_update_ms") ? bundle.getLong("app_settings_last_update_ms") : 0;
        }
    }

    public boolean zzsi() {
        boolean z;
        synchronized (this.zzail) {
            z = this.zzcfg;
        }
        return z;
    }

    public String zzsj() {
        String bigInteger;
        synchronized (this.zzail) {
            bigInteger = this.zzcjo.toString();
            this.zzcjo = this.zzcjo.add(BigInteger.ONE);
        }
        return bigInteger;
    }

    public zzjy zzsk() {
        zzjy com_google_android_gms_internal_zzjy;
        synchronized (this.zzail) {
            com_google_android_gms_internal_zzjy = this.zzcjn;
        }
        return com_google_android_gms_internal_zzjy;
    }

    public zzde zzsl() {
        zzde com_google_android_gms_internal_zzde;
        synchronized (this.zzail) {
            com_google_android_gms_internal_zzde = this.zzcjt;
        }
        return com_google_android_gms_internal_zzde;
    }

    public boolean zzsm() {
        boolean z;
        synchronized (this.zzail) {
            z = this.zzcjr;
            this.zzcjr = true;
        }
        return z;
    }

    public boolean zzsn() {
        boolean z;
        synchronized (this.zzail) {
            z = this.zzcff || this.zzcjy;
        }
        return z;
    }

    public String zzso() {
        String str;
        synchronized (this.zzail) {
            str = this.zzbjf;
        }
        return str;
    }

    public String zzsp() {
        String str;
        synchronized (this.zzail) {
            str = this.zzcjv;
        }
        return str;
    }

    public Boolean zzsq() {
        Boolean bool;
        synchronized (this.zzail) {
            bool = this.zzcjw;
        }
        return bool;
    }

    public boolean zzsr() {
        boolean z;
        synchronized (this.zzail) {
            z = this.zzcfo;
        }
        return z;
    }

    public boolean zzss() {
        return this.zzcjz;
    }

    public zzjw zzst() {
        zzjw com_google_android_gms_internal_zzjw;
        synchronized (this.zzail) {
            com_google_android_gms_internal_zzjw = new zzjw(this.zzcka, this.zzckb);
        }
        return com_google_android_gms_internal_zzjw;
    }

    public zzcg zzsu() {
        return this.zzaju;
    }

    public boolean zzsv() {
        boolean z;
        synchronized (this.zzail) {
            z = this.zzcjx;
        }
        return z;
    }

    void zzsw() {
        try {
            this.zzcjt = zzu.zzfv().zza(new zzdd(this.mContext, this.zzalo.zzcs));
        } catch (Throwable e) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Cannot initialize CSI reporter.", e);
        }
    }
}

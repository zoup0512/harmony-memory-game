package com.google.android.gms.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import com.google.ads.mediation.AdUrlAdapter;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeAdOptions.Builder;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.zzf;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzge.zza;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

@zzin
public class zzgd implements zza {
    private final Context mContext;
    private final Object zzail = new Object();
    private final zzgj zzajz;
    private final NativeAdOptionsParcel zzalk;
    private final List<String> zzall;
    private final VersionInfoParcel zzalo;
    private AdRequestParcel zzanc;
    private final AdSizeParcel zzani;
    private final boolean zzarl;
    private final boolean zzawn;
    private final String zzboc;
    private final long zzbod;
    private final zzga zzboe;
    private final zzfz zzbof;
    private zzgk zzbog;
    private int zzboh = -2;
    private zzgm zzboi;

    public zzgd(Context context, String str, zzgj com_google_android_gms_internal_zzgj, zzga com_google_android_gms_internal_zzga, zzfz com_google_android_gms_internal_zzfz, AdRequestParcel adRequestParcel, AdSizeParcel adSizeParcel, VersionInfoParcel versionInfoParcel, boolean z, boolean z2, NativeAdOptionsParcel nativeAdOptionsParcel, List<String> list) {
        this.mContext = context;
        this.zzajz = com_google_android_gms_internal_zzgj;
        this.zzbof = com_google_android_gms_internal_zzfz;
        if ("com.google.ads.mediation.customevent.CustomEventAdapter".equals(str)) {
            this.zzboc = zzmh();
        } else {
            this.zzboc = str;
        }
        this.zzboe = com_google_android_gms_internal_zzga;
        this.zzbod = com_google_android_gms_internal_zzga.zzbnl != -1 ? com_google_android_gms_internal_zzga.zzbnl : 10000;
        this.zzanc = adRequestParcel;
        this.zzani = adSizeParcel;
        this.zzalo = versionInfoParcel;
        this.zzarl = z;
        this.zzawn = z2;
        this.zzalk = nativeAdOptionsParcel;
        this.zzall = list;
    }

    private long zza(long j, long j2, long j3, long j4) {
        while (this.zzboh == -2) {
            zzb(j, j2, j3, j4);
        }
        return zzu.zzfu().elapsedRealtime() - j;
    }

    private void zza(zzgc com_google_android_gms_internal_zzgc) {
        if ("com.google.ads.mediation.AdUrlAdapter".equals(this.zzboc)) {
            if (this.zzanc.zzatw == null) {
                this.zzanc = new zzf(this.zzanc).zzc(new Bundle()).zzig();
            }
            Bundle bundle = this.zzanc.zzatw.getBundle(this.zzboc);
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putString("sdk_less_network_id", this.zzbof.zzbmv);
            this.zzanc.zzatw.putBundle(this.zzboc, bundle);
        }
        String zzbj = zzbj(this.zzbof.zzbnc);
        try {
            if (this.zzalo.zzcnl < 4100000) {
                if (this.zzani.zzaus) {
                    this.zzbog.zza(zze.zzac(this.mContext), this.zzanc, zzbj, com_google_android_gms_internal_zzgc);
                } else {
                    this.zzbog.zza(zze.zzac(this.mContext), this.zzani, this.zzanc, zzbj, com_google_android_gms_internal_zzgc);
                }
            } else if (this.zzarl) {
                this.zzbog.zza(zze.zzac(this.mContext), this.zzanc, zzbj, this.zzbof.zzbmu, com_google_android_gms_internal_zzgc, this.zzalk, this.zzall);
            } else if (this.zzani.zzaus) {
                this.zzbog.zza(zze.zzac(this.mContext), this.zzanc, zzbj, this.zzbof.zzbmu, com_google_android_gms_internal_zzgc);
            } else if (!this.zzawn) {
                this.zzbog.zza(zze.zzac(this.mContext), this.zzani, this.zzanc, zzbj, this.zzbof.zzbmu, com_google_android_gms_internal_zzgc);
            } else if (this.zzbof.zzbnf != null) {
                this.zzbog.zza(zze.zzac(this.mContext), this.zzanc, zzbj, this.zzbof.zzbmu, com_google_android_gms_internal_zzgc, new NativeAdOptionsParcel(zzbk(this.zzbof.zzbnj)), this.zzbof.zzbni);
            } else {
                this.zzbog.zza(zze.zzac(this.mContext), this.zzani, this.zzanc, zzbj, this.zzbof.zzbmu, com_google_android_gms_internal_zzgc);
            }
        } catch (Throwable e) {
            zzb.zzd("Could not request ad from mediation adapter.", e);
            zzy(5);
        }
    }

    private static zzgm zzaa(int i) {
        return new 2(i);
    }

    private void zzb(long j, long j2, long j3, long j4) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j5 = j2 - (elapsedRealtime - j);
        elapsedRealtime = j4 - (elapsedRealtime - j3);
        if (j5 <= 0 || elapsedRealtime <= 0) {
            zzb.zzcw("Timed out waiting for adapter.");
            this.zzboh = 3;
            return;
        }
        try {
            this.zzail.wait(Math.min(j5, elapsedRealtime));
        } catch (InterruptedException e) {
            this.zzboh = -1;
        }
    }

    private String zzbj(String str) {
        if (!(str == null || !zzmk() || zzz(2))) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                jSONObject.remove("cpm_floor_cents");
                str = jSONObject.toString();
            } catch (JSONException e) {
                zzb.zzcx("Could not remove field. Returning the original value");
            }
        }
        return str;
    }

    private static NativeAdOptions zzbk(String str) {
        Builder builder = new Builder();
        if (str == null) {
            return builder.build();
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            builder.setRequestMultipleImages(jSONObject.optBoolean("multiple_images", false));
            builder.setReturnUrlsForImageAssets(jSONObject.optBoolean("only_urls", false));
            builder.setImageOrientation(zzbl(jSONObject.optString("native_image_orientation", "any")));
        } catch (Throwable e) {
            zzb.zzd("Exception occurred when creating native ad options", e);
        }
        return builder.build();
    }

    private static int zzbl(String str) {
        return DeviceInfo.ORIENTATION_LANDSCAPE.equals(str) ? 2 : DeviceInfo.ORIENTATION_PORTRAIT.equals(str) ? 1 : 0;
    }

    private String zzmh() {
        try {
            if (!TextUtils.isEmpty(this.zzbof.zzbmy)) {
                return this.zzajz.zzbn(this.zzbof.zzbmy) ? "com.google.android.gms.ads.mediation.customevent.CustomEventAdapter" : "com.google.ads.mediation.customevent.CustomEventAdapter";
            }
        } catch (RemoteException e) {
            zzb.zzcx("Fail to determine the custom event's version, assuming the old one.");
        }
        return "com.google.ads.mediation.customevent.CustomEventAdapter";
    }

    private zzgm zzmi() {
        if (this.zzboh != 0 || !zzmk()) {
            return null;
        }
        try {
            if (!(!zzz(4) || this.zzboi == null || this.zzboi.zzmm() == 0)) {
                return this.zzboi;
            }
        } catch (RemoteException e) {
            zzb.zzcx("Could not get cpm value from MediationResponseMetadata");
        }
        return zzaa(zzml());
    }

    private zzgk zzmj() {
        String str = "Instantiating mediation adapter: ";
        String valueOf = String.valueOf(this.zzboc);
        zzb.zzcw(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        if (!this.zzarl) {
            if (((Boolean) zzdc.zzbbe.get()).booleanValue() && "com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzboc)) {
                return zza(new AdMobAdapter());
            }
            if (((Boolean) zzdc.zzbbf.get()).booleanValue() && "com.google.ads.mediation.AdUrlAdapter".equals(this.zzboc)) {
                return zza(new AdUrlAdapter());
            }
            if ("com.google.ads.mediation.admob.AdMobCustomTabsAdapter".equals(this.zzboc)) {
                return new zzgq(new zzgy());
            }
        }
        try {
            return this.zzajz.zzbm(this.zzboc);
        } catch (Throwable e) {
            Throwable th = e;
            String str2 = "Could not instantiate mediation adapter: ";
            valueOf = String.valueOf(this.zzboc);
            zzb.zza(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), th);
            return null;
        }
    }

    private boolean zzmk() {
        return this.zzboe.zzbnv != -1;
    }

    private int zzml() {
        if (this.zzbof.zzbnc == null) {
            return 0;
        }
        try {
            JSONObject jSONObject = new JSONObject(this.zzbof.zzbnc);
            if ("com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzboc)) {
                return jSONObject.optInt("cpm_cents", 0);
            }
            int optInt = zzz(2) ? jSONObject.optInt("cpm_floor_cents", 0) : 0;
            return optInt == 0 ? jSONObject.optInt("penalized_average_cpm_cents", 0) : optInt;
        } catch (JSONException e) {
            zzb.zzcx("Could not convert to json. Returning 0");
            return 0;
        }
    }

    private boolean zzz(int i) {
        try {
            Bundle zzmr = this.zzarl ? this.zzbog.zzmr() : this.zzani.zzaus ? this.zzbog.getInterstitialAdapterInfo() : this.zzbog.zzmq();
            if (zzmr == null) {
                return false;
            }
            return (zzmr.getInt("capabilities", 0) & i) == i;
        } catch (RemoteException e) {
            zzb.zzcx("Could not get adapter info. Returning false");
            return false;
        }
    }

    public void cancel() {
        synchronized (this.zzail) {
            try {
                if (this.zzbog != null) {
                    this.zzbog.destroy();
                }
            } catch (Throwable e) {
                zzb.zzd("Could not destroy mediation adapter.", e);
            }
            this.zzboh = -1;
            this.zzail.notify();
        }
    }

    public zzge zza(long j, long j2) {
        zzge com_google_android_gms_internal_zzge;
        synchronized (this.zzail) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            zzgc com_google_android_gms_internal_zzgc = new zzgc();
            zzkh.zzclc.post(new 1(this, com_google_android_gms_internal_zzgc));
            zzgc com_google_android_gms_internal_zzgc2 = com_google_android_gms_internal_zzgc;
            com_google_android_gms_internal_zzge = new zzge(this.zzbof, this.zzbog, this.zzboc, com_google_android_gms_internal_zzgc2, this.zzboh, zzmi(), zza(elapsedRealtime, this.zzbod, j, j2));
        }
        return com_google_android_gms_internal_zzge;
    }

    protected zzgk zza(MediationAdapter mediationAdapter) {
        return new zzgq(mediationAdapter);
    }

    public void zza(int i, zzgm com_google_android_gms_internal_zzgm) {
        synchronized (this.zzail) {
            this.zzboh = i;
            this.zzboi = com_google_android_gms_internal_zzgm;
            this.zzail.notify();
        }
    }

    public void zzy(int i) {
        synchronized (this.zzail) {
            this.zzboh = i;
            this.zzail.notify();
        }
    }
}

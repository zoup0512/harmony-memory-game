package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.webkit.CookieManager;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.ads.internal.overlay.zzg;
import com.google.android.gms.ads.internal.purchase.GInAppPurchaseManagerInfoParcel;
import com.google.android.gms.ads.internal.purchase.zzc;
import com.google.android.gms.ads.internal.purchase.zzd;
import com.google.android.gms.ads.internal.purchase.zzf;
import com.google.android.gms.ads.internal.purchase.zzj;
import com.google.android.gms.ads.internal.purchase.zzk;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel.zza;
import com.google.android.gms.ads.internal.request.CapabilityParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.internal.zzdc;
import com.google.android.gms.internal.zzdk;
import com.google.android.gms.internal.zzer;
import com.google.android.gms.internal.zzgb;
import com.google.android.gms.internal.zzgj;
import com.google.android.gms.internal.zzhl;
import com.google.android.gms.internal.zzhn;
import com.google.android.gms.internal.zzho;
import com.google.android.gms.internal.zzhs;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzju;
import com.google.android.gms.internal.zzjv;
import com.google.android.gms.internal.zzjw;
import com.google.android.gms.internal.zzkh;
import com.google.android.gms.internal.zzlh;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Future;

@zzin
public abstract class zzb extends zza implements zzg, zzj, zzs, zzer, zzgb {
    private final Messenger mMessenger;
    protected final zzgj zzajz;
    protected transient boolean zzaka;

    public zzb(Context context, AdSizeParcel adSizeParcel, String str, zzgj com_google_android_gms_internal_zzgj, VersionInfoParcel versionInfoParcel, zzd com_google_android_gms_ads_internal_zzd) {
        this(new zzv(context, adSizeParcel, str, versionInfoParcel), com_google_android_gms_internal_zzgj, null, com_google_android_gms_ads_internal_zzd);
    }

    protected zzb(zzv com_google_android_gms_ads_internal_zzv, zzgj com_google_android_gms_internal_zzgj, @Nullable zzr com_google_android_gms_ads_internal_zzr, zzd com_google_android_gms_ads_internal_zzd) {
        super(com_google_android_gms_ads_internal_zzv, com_google_android_gms_ads_internal_zzr, com_google_android_gms_ads_internal_zzd);
        this.zzajz = com_google_android_gms_internal_zzgj;
        this.mMessenger = new Messenger(new zzhl(this.zzajs.zzagf));
        this.zzaka = false;
    }

    private zza zza(AdRequestParcel adRequestParcel, Bundle bundle, zzjw com_google_android_gms_internal_zzjw) {
        PackageInfo packageInfo;
        ApplicationInfo applicationInfo = this.zzajs.zzagf.getApplicationInfo();
        try {
            packageInfo = this.zzajs.zzagf.getPackageManager().getPackageInfo(applicationInfo.packageName, 0);
        } catch (NameNotFoundException e) {
            packageInfo = null;
        }
        DisplayMetrics displayMetrics = this.zzajs.zzagf.getResources().getDisplayMetrics();
        Bundle bundle2 = null;
        if (!(this.zzajs.zzaox == null || this.zzajs.zzaox.getParent() == null)) {
            int[] iArr = new int[2];
            this.zzajs.zzaox.getLocationOnScreen(iArr);
            int i = iArr[0];
            int i2 = iArr[1];
            int width = this.zzajs.zzaox.getWidth();
            int height = this.zzajs.zzaox.getHeight();
            int i3 = 0;
            if (this.zzajs.zzaox.isShown() && i + width > 0 && i2 + height > 0 && i <= displayMetrics.widthPixels && i2 <= displayMetrics.heightPixels) {
                i3 = 1;
            }
            bundle2 = new Bundle(5);
            bundle2.putInt("x", i);
            bundle2.putInt("y", i2);
            bundle2.putInt("width", width);
            bundle2.putInt("height", height);
            bundle2.putInt("visible", i3);
        }
        String zzsj = zzu.zzft().zzsj();
        this.zzajs.zzapd = new zzjv(zzsj, this.zzajs.zzaou);
        this.zzajs.zzapd.zzq(adRequestParcel);
        String zza = zzu.zzfq().zza(this.zzajs.zzagf, this.zzajs.zzaox, this.zzajs.zzapa);
        long j = 0;
        if (this.zzajs.zzaph != null) {
            try {
                j = this.zzajs.zzaph.getValue();
            } catch (RemoteException e2) {
                com.google.android.gms.ads.internal.util.client.zzb.zzcx("Cannot get correlation id, default to 0.");
            }
        }
        String uuid = UUID.randomUUID().toString();
        Bundle zza2 = zzu.zzft().zza(this.zzajs.zzagf, this, zzsj);
        List arrayList = new ArrayList();
        for (i = 0; i < this.zzajs.zzapn.size(); i++) {
            arrayList.add((String) this.zzajs.zzapn.keyAt(i));
        }
        boolean z = this.zzajs.zzapi != null;
        boolean z2 = this.zzajs.zzapj != null && zzu.zzft().zzsv();
        boolean zzr = this.zzajv.zzakl.zzr(this.zzajs.zzagf);
        String str = "";
        if (((Boolean) zzdc.zzbdn.get()).booleanValue()) {
            com.google.android.gms.ads.internal.util.client.zzb.zzcv("Getting webview cookie from CookieManager.");
            CookieManager zzao = zzu.zzfs().zzao(this.zzajs.zzagf);
            if (zzao != null) {
                str = zzao.getCookie("googleads.g.doubleclick.net");
            }
        }
        String str2 = null;
        if (com_google_android_gms_internal_zzjw != null) {
            str2 = com_google_android_gms_internal_zzjw.zzsg();
        }
        return new zza(bundle2, adRequestParcel, this.zzajs.zzapa, this.zzajs.zzaou, applicationInfo, packageInfo, zzsj, zzu.zzft().getSessionId(), this.zzajs.zzaow, zza2, this.zzajs.zzaps, arrayList, bundle, zzu.zzft().zzsn(), this.mMessenger, displayMetrics.widthPixels, displayMetrics.heightPixels, displayMetrics.density, zza, j, uuid, zzdc.zzjx(), this.zzajs.zzaot, this.zzajs.zzapo, new CapabilityParcel(z, z2, zzr), this.zzajs.zzgt(), zzu.zzfq().zzey(), zzu.zzfq().zzfa(), zzu.zzfq().zzam(this.zzajs.zzagf), zzu.zzfq().zzn(this.zzajs.zzaox), this.zzajs.zzagf instanceof Activity, zzu.zzft().zzsr(), str, str2, zzu.zzft().zzss(), zzu.zzgj().zzlk(), zzu.zzfq().zzti());
    }

    public String getMediationAdapterClassName() {
        return this.zzajs.zzapb == null ? null : this.zzajs.zzapb.zzbop;
    }

    public void onAdClicked() {
        if (this.zzajs.zzapb == null) {
            com.google.android.gms.ads.internal.util.client.zzb.zzcx("Ad state was null when trying to ping click URLs.");
            return;
        }
        if (!(this.zzajs.zzapb.zzcig == null || this.zzajs.zzapb.zzcig.zzbnm == null)) {
            zzu.zzgf().zza(this.zzajs.zzagf, this.zzajs.zzaow.zzcs, this.zzajs.zzapb, this.zzajs.zzaou, false, this.zzajs.zzapb.zzcig.zzbnm);
        }
        if (!(this.zzajs.zzapb.zzbon == null || this.zzajs.zzapb.zzbon.zzbmz == null)) {
            zzu.zzgf().zza(this.zzajs.zzagf, this.zzajs.zzaow.zzcs, this.zzajs.zzapb, this.zzajs.zzaou, false, this.zzajs.zzapb.zzbon.zzbmz);
        }
        super.onAdClicked();
    }

    public void onPause() {
        this.zzaju.zzk(this.zzajs.zzapb);
    }

    public void onResume() {
        this.zzaju.zzl(this.zzajs.zzapb);
    }

    public void pause() {
        zzab.zzhi("pause must be called on the main UI thread.");
        if (!(this.zzajs.zzapb == null || this.zzajs.zzapb.zzbtm == null || !this.zzajs.zzgp())) {
            zzu.zzfs().zzi(this.zzajs.zzapb.zzbtm);
        }
        if (!(this.zzajs.zzapb == null || this.zzajs.zzapb.zzboo == null)) {
            try {
                this.zzajs.zzapb.zzboo.pause();
            } catch (RemoteException e) {
                com.google.android.gms.ads.internal.util.client.zzb.zzcx("Could not pause mediation adapter.");
            }
        }
        this.zzaju.zzk(this.zzajs.zzapb);
        this.zzajr.pause();
    }

    public void recordImpression() {
        zza(this.zzajs.zzapb, false);
    }

    public void resume() {
        zzab.zzhi("resume must be called on the main UI thread.");
        zzlh com_google_android_gms_internal_zzlh = null;
        if (!(this.zzajs.zzapb == null || this.zzajs.zzapb.zzbtm == null)) {
            com_google_android_gms_internal_zzlh = this.zzajs.zzapb.zzbtm;
        }
        if (com_google_android_gms_internal_zzlh != null && this.zzajs.zzgp()) {
            zzu.zzfs().zzj(this.zzajs.zzapb.zzbtm);
        }
        if (!(this.zzajs.zzapb == null || this.zzajs.zzapb.zzboo == null)) {
            try {
                this.zzajs.zzapb.zzboo.resume();
            } catch (RemoteException e) {
                com.google.android.gms.ads.internal.util.client.zzb.zzcx("Could not resume mediation adapter.");
            }
        }
        if (com_google_android_gms_internal_zzlh == null || !com_google_android_gms_internal_zzlh.zzup()) {
            this.zzajr.resume();
        }
        this.zzaju.zzl(this.zzajs.zzapb);
    }

    public void showInterstitial() {
        throw new IllegalStateException("showInterstitial is not supported for current ad type");
    }

    public void zza(zzho com_google_android_gms_internal_zzho) {
        zzab.zzhi("setInAppPurchaseListener must be called on the main UI thread.");
        this.zzajs.zzapi = com_google_android_gms_internal_zzho;
    }

    public void zza(zzhs com_google_android_gms_internal_zzhs, @Nullable String str) {
        zzab.zzhi("setPlayStorePurchaseParams must be called on the main UI thread.");
        this.zzajs.zzapt = new zzk(str);
        this.zzajs.zzapj = com_google_android_gms_internal_zzhs;
        if (!zzu.zzft().zzsm() && com_google_android_gms_internal_zzhs != null) {
            Future future = (Future) new zzc(this.zzajs.zzagf, this.zzajs.zzapj, this.zzajs.zzapt).zzpy();
        }
    }

    protected void zza(@Nullable zzju com_google_android_gms_internal_zzju, boolean z) {
        if (com_google_android_gms_internal_zzju == null) {
            com.google.android.gms.ads.internal.util.client.zzb.zzcx("Ad state was null when trying to ping impression URLs.");
            return;
        }
        super.zzc(com_google_android_gms_internal_zzju);
        if (!(com_google_android_gms_internal_zzju.zzcig == null || com_google_android_gms_internal_zzju.zzcig.zzbnn == null)) {
            zzu.zzgf().zza(this.zzajs.zzagf, this.zzajs.zzaow.zzcs, com_google_android_gms_internal_zzju, this.zzajs.zzaou, z, com_google_android_gms_internal_zzju.zzcig.zzbnn);
        }
        if (com_google_android_gms_internal_zzju.zzbon != null && com_google_android_gms_internal_zzju.zzbon.zzbna != null) {
            zzu.zzgf().zza(this.zzajs.zzagf, this.zzajs.zzaow.zzcs, com_google_android_gms_internal_zzju, this.zzajs.zzaou, z, com_google_android_gms_internal_zzju.zzbon.zzbna);
        }
    }

    public void zza(String str, ArrayList<String> arrayList) {
        zzhn com_google_android_gms_ads_internal_purchase_zzd = new zzd(str, arrayList, this.zzajs.zzagf, this.zzajs.zzaow.zzcs);
        if (this.zzajs.zzapi == null) {
            com.google.android.gms.ads.internal.util.client.zzb.zzcx("InAppPurchaseListener is not set. Try to launch default purchase flow.");
            if (!zzm.zziw().zzar(this.zzajs.zzagf)) {
                com.google.android.gms.ads.internal.util.client.zzb.zzcx("Google Play Service unavailable, cannot launch default purchase flow.");
                return;
            } else if (this.zzajs.zzapj == null) {
                com.google.android.gms.ads.internal.util.client.zzb.zzcx("PlayStorePurchaseListener is not set.");
                return;
            } else if (this.zzajs.zzapt == null) {
                com.google.android.gms.ads.internal.util.client.zzb.zzcx("PlayStorePurchaseVerifier is not initialized.");
                return;
            } else if (this.zzajs.zzapx) {
                com.google.android.gms.ads.internal.util.client.zzb.zzcx("An in-app purchase request is already in progress, abort");
                return;
            } else {
                this.zzajs.zzapx = true;
                try {
                    if (this.zzajs.zzapj.isValidPurchase(str)) {
                        zzu.zzga().zza(this.zzajs.zzagf, this.zzajs.zzaow.zzcnm, new GInAppPurchaseManagerInfoParcel(this.zzajs.zzagf, this.zzajs.zzapt, com_google_android_gms_ads_internal_purchase_zzd, this));
                        return;
                    } else {
                        this.zzajs.zzapx = false;
                        return;
                    }
                } catch (RemoteException e) {
                    com.google.android.gms.ads.internal.util.client.zzb.zzcx("Could not start In-App purchase.");
                    this.zzajs.zzapx = false;
                    return;
                }
            }
        }
        try {
            this.zzajs.zzapi.zza(com_google_android_gms_ads_internal_purchase_zzd);
        } catch (RemoteException e2) {
            com.google.android.gms.ads.internal.util.client.zzb.zzcx("Could not start In-App purchase.");
        }
    }

    public void zza(String str, boolean z, int i, Intent intent, zzf com_google_android_gms_ads_internal_purchase_zzf) {
        try {
            if (this.zzajs.zzapj != null) {
                this.zzajs.zzapj.zza(new com.google.android.gms.ads.internal.purchase.zzg(this.zzajs.zzagf, str, z, i, intent, com_google_android_gms_ads_internal_purchase_zzf));
            }
        } catch (RemoteException e) {
            com.google.android.gms.ads.internal.util.client.zzb.zzcx("Fail to invoke PlayStorePurchaseListener.");
        }
        zzkh.zzclc.postDelayed(new 1(this, intent), 500);
    }

    public boolean zza(AdRequestParcel adRequestParcel, zzdk com_google_android_gms_internal_zzdk) {
        String str = null;
        if (!zzdw()) {
            return false;
        }
        zzjw zzst;
        Bundle zza = zza(zzu.zzft().zzaa(this.zzajs.zzagf));
        this.zzajr.cancel();
        this.zzajs.zzapw = 0;
        if (((Boolean) zzdc.zzbct.get()).booleanValue()) {
            zzst = zzu.zzft().zzst();
            zzg zzgi = zzu.zzgi();
            Context context = this.zzajs.zzagf;
            VersionInfoParcel versionInfoParcel = this.zzajs.zzaow;
            if (zzst != null) {
                str = zzst.zzsh();
            }
            zzgi.zza(context, versionInfoParcel, false, zzst, str, this.zzajs.zzaou);
        } else {
            zzst = null;
        }
        zza zza2 = zza(adRequestParcel, zza, zzst);
        com_google_android_gms_internal_zzdk.zzh("seq_num", zza2.zzcau);
        com_google_android_gms_internal_zzdk.zzh("request_id", zza2.zzcbg);
        com_google_android_gms_internal_zzdk.zzh("session_id", zza2.zzcav);
        if (zza2.zzcas != null) {
            com_google_android_gms_internal_zzdk.zzh("app_version", String.valueOf(zza2.zzcas.versionCode));
        }
        this.zzajs.zzaoy = zzu.zzfm().zza(this.zzajs.zzagf, zza2, this.zzajs.zzaov, this);
        return true;
    }

    protected boolean zza(AdRequestParcel adRequestParcel, zzju com_google_android_gms_internal_zzju, boolean z) {
        if (!z && this.zzajs.zzgp()) {
            if (com_google_android_gms_internal_zzju.zzbns > 0) {
                this.zzajr.zza(adRequestParcel, com_google_android_gms_internal_zzju.zzbns);
            } else if (com_google_android_gms_internal_zzju.zzcig != null && com_google_android_gms_internal_zzju.zzcig.zzbns > 0) {
                this.zzajr.zza(adRequestParcel, com_google_android_gms_internal_zzju.zzcig.zzbns);
            } else if (!com_google_android_gms_internal_zzju.zzcby && com_google_android_gms_internal_zzju.errorCode == 2) {
                this.zzajr.zzg(adRequestParcel);
            }
        }
        return this.zzajr.zzfc();
    }

    boolean zza(zzju com_google_android_gms_internal_zzju) {
        AdRequestParcel adRequestParcel;
        boolean z = false;
        if (this.zzajt != null) {
            adRequestParcel = this.zzajt;
            this.zzajt = null;
        } else {
            adRequestParcel = com_google_android_gms_internal_zzju.zzcar;
            if (adRequestParcel.extras != null) {
                z = adRequestParcel.extras.getBoolean("_noRefresh", false);
            }
        }
        return zza(adRequestParcel, com_google_android_gms_internal_zzju, z);
    }

    protected boolean zza(@Nullable zzju com_google_android_gms_internal_zzju, zzju com_google_android_gms_internal_zzju2) {
        int i;
        int i2 = 0;
        if (!(com_google_android_gms_internal_zzju == null || com_google_android_gms_internal_zzju.zzboq == null)) {
            com_google_android_gms_internal_zzju.zzboq.zza(null);
        }
        if (com_google_android_gms_internal_zzju2.zzboq != null) {
            com_google_android_gms_internal_zzju2.zzboq.zza((zzgb) this);
        }
        if (com_google_android_gms_internal_zzju2.zzcig != null) {
            i = com_google_android_gms_internal_zzju2.zzcig.zzbny;
            i2 = com_google_android_gms_internal_zzju2.zzcig.zzbnz;
        } else {
            i = 0;
        }
        this.zzajs.zzapu.zzh(i, i2);
        return true;
    }

    public void zzb(zzju com_google_android_gms_internal_zzju) {
        super.zzb(com_google_android_gms_internal_zzju);
        if (com_google_android_gms_internal_zzju.zzbon != null) {
            com.google.android.gms.ads.internal.util.client.zzb.zzcv("Pinging network fill URLs.");
            zzu.zzgf().zza(this.zzajs.zzagf, this.zzajs.zzaow.zzcs, com_google_android_gms_internal_zzju, this.zzajs.zzaou, false, com_google_android_gms_internal_zzju.zzbon.zzbnb);
            if (com_google_android_gms_internal_zzju.zzcig.zzbnp != null && com_google_android_gms_internal_zzju.zzcig.zzbnp.size() > 0) {
                com.google.android.gms.ads.internal.util.client.zzb.zzcv("Pinging urls remotely");
                zzu.zzfq().zza(this.zzajs.zzagf, com_google_android_gms_internal_zzju.zzcig.zzbnp);
            }
        }
        if (com_google_android_gms_internal_zzju.errorCode == 3 && com_google_android_gms_internal_zzju.zzcig != null && com_google_android_gms_internal_zzju.zzcig.zzbno != null) {
            com.google.android.gms.ads.internal.util.client.zzb.zzcv("Pinging no fill URLs.");
            zzu.zzgf().zza(this.zzajs.zzagf, this.zzajs.zzaow.zzcs, com_google_android_gms_internal_zzju, this.zzajs.zzaou, false, com_google_android_gms_internal_zzju.zzcig.zzbno);
        }
    }

    protected boolean zzc(AdRequestParcel adRequestParcel) {
        return super.zzc(adRequestParcel) && !this.zzaka;
    }

    protected boolean zzdw() {
        return zzu.zzfq().zza(this.zzajs.zzagf.getPackageManager(), this.zzajs.zzagf.getPackageName(), "android.permission.INTERNET") && zzu.zzfq().zzac(this.zzajs.zzagf);
    }

    public void zzdx() {
        this.zzaju.zzi(this.zzajs.zzapb);
        this.zzaka = false;
        zzdr();
        this.zzajs.zzapd.zzsa();
    }

    public void zzdy() {
        this.zzaka = true;
        zzdt();
    }

    public void zzdz() {
        onAdClicked();
    }

    public void zzea() {
        zzdx();
    }

    public void zzeb() {
        zzdo();
    }

    public void zzec() {
        zzdy();
    }

    public void zzed() {
        if (this.zzajs.zzapb != null) {
            String str = this.zzajs.zzapb.zzbop;
            com.google.android.gms.ads.internal.util.client.zzb.zzcx(new StringBuilder(String.valueOf(str).length() + 74).append("Mediation adapter ").append(str).append(" refreshed, but mediation adapters should never refresh.").toString());
        }
        zza(this.zzajs.zzapb, true);
        zzdu();
    }

    public void zzee() {
        recordImpression();
    }

    public void zzef() {
        zzu.zzfq().runOnUiThread(new 2(this));
    }

    public void zzeg() {
        zzu.zzfq().runOnUiThread(new 3(this));
    }
}

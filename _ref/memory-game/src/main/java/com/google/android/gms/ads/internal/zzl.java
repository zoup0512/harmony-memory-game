package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Window;
import com.cmcm.adsdk.base.CMBaseNativeAd;
import com.facebook.internal.NativeProtocol;
import com.google.ads.mediation.AbstractAdViewAdapter;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.reward.mediation.client.RewardItemParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.internal.zzdc;
import com.google.android.gms.internal.zzdk;
import com.google.android.gms.internal.zzet;
import com.google.android.gms.internal.zzey;
import com.google.android.gms.internal.zzft;
import com.google.android.gms.internal.zzfz;
import com.google.android.gms.internal.zzga;
import com.google.android.gms.internal.zzgj;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zziq;
import com.google.android.gms.internal.zzjo;
import com.google.android.gms.internal.zzju;
import com.google.android.gms.internal.zzkc;
import com.google.android.gms.internal.zzkh;
import com.google.android.gms.internal.zzlh;
import com.google.android.gms.internal.zzli;
import java.util.Collections;
import java.util.concurrent.Future;
import org.json.JSONObject;

@zzin
public class zzl extends zzc implements zzet, com.google.android.gms.internal.zzey.zza {
    protected transient boolean zzalw = false;
    private int zzalx = -1;
    private boolean zzaly;
    private float zzalz;

    @zzin
    private class zza extends zzkc {
        private final int zzama;
        final /* synthetic */ zzl zzamb;

        public zza(zzl com_google_android_gms_ads_internal_zzl, int i) {
            this.zzamb = com_google_android_gms_ads_internal_zzl;
            this.zzama = i;
        }

        public void onStop() {
        }

        public void zzew() {
            InterstitialAdParameterParcel interstitialAdParameterParcel = new InterstitialAdParameterParcel(this.zzamb.zzajs.zzame, this.zzamb.zzet(), this.zzamb.zzaly, this.zzamb.zzalz, this.zzamb.zzajs.zzame ? this.zzama : -1);
            int requestedOrientation = this.zzamb.zzajs.zzapb.zzbtm.getRequestedOrientation();
            zzkh.zzclc.post(new 1(this, new AdOverlayInfoParcel(this.zzamb, this.zzamb, this.zzamb, this.zzamb.zzajs.zzapb.zzbtm, requestedOrientation == -1 ? this.zzamb.zzajs.zzapb.orientation : requestedOrientation, this.zzamb.zzajs.zzaow, this.zzamb.zzajs.zzapb.zzccd, interstitialAdParameterParcel)));
        }
    }

    public zzl(Context context, AdSizeParcel adSizeParcel, String str, zzgj com_google_android_gms_internal_zzgj, VersionInfoParcel versionInfoParcel, zzd com_google_android_gms_ads_internal_zzd) {
        super(context, adSizeParcel, str, com_google_android_gms_internal_zzgj, versionInfoParcel, com_google_android_gms_ads_internal_zzd);
    }

    private void zzb(Bundle bundle) {
        zzu.zzfq().zzb(this.zzajs.zzagf, this.zzajs.zzaow.zzcs, "gmob-apps", bundle, false);
    }

    private com.google.android.gms.internal.zzju.zza zzc(com.google.android.gms.internal.zzju.zza com_google_android_gms_internal_zzju_zza) {
        try {
            String jSONObject = zziq.zzc(com_google_android_gms_internal_zzju_zza.zzciq).toString();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(AbstractAdViewAdapter.AD_UNIT_ID_PARAMETER, com_google_android_gms_internal_zzju_zza.zzcip.zzaou);
            zzga com_google_android_gms_internal_zzga = new zzga(Collections.singletonList(new zzfz(jSONObject, null, Collections.singletonList("com.google.ads.mediation.admob.AdMobAdapter"), null, null, Collections.emptyList(), Collections.emptyList(), jSONObject2.toString(), null, Collections.emptyList(), Collections.emptyList(), null, null, null, null, null, Collections.emptyList())), -1, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), false, "", -1, 0, 1, null, 0, -1, -1, false);
            AdResponseParcel adResponseParcel = com_google_android_gms_internal_zzju_zza.zzciq;
            return new com.google.android.gms.internal.zzju.zza(com_google_android_gms_internal_zzju_zza.zzcip, new AdResponseParcel(com_google_android_gms_internal_zzju_zza.zzcip, adResponseParcel.zzbto, adResponseParcel.body, adResponseParcel.zzbnm, adResponseParcel.zzbnn, adResponseParcel.zzcbx, true, adResponseParcel.zzcbz, adResponseParcel.zzcca, adResponseParcel.zzbns, adResponseParcel.orientation, adResponseParcel.zzccb, adResponseParcel.zzccc, adResponseParcel.zzccd, adResponseParcel.zzcce, adResponseParcel.zzccf, adResponseParcel.zzccg, adResponseParcel.zzcch, adResponseParcel.zzauu, adResponseParcel.zzcaz, adResponseParcel.zzcci, adResponseParcel.zzccj, adResponseParcel.zzccm, adResponseParcel.zzauv, adResponseParcel.zzauw, adResponseParcel.zzccn, adResponseParcel.zzcco, adResponseParcel.zzccp, adResponseParcel.zzccq, adResponseParcel.zzccr, adResponseParcel.zzcbq, adResponseParcel.zzcbr, adResponseParcel.zzbnp, adResponseParcel.zzccs, adResponseParcel.zzbnq, adResponseParcel.zzcct), com_google_android_gms_internal_zzga, com_google_android_gms_internal_zzju_zza.zzapa, com_google_android_gms_internal_zzju_zza.errorCode, com_google_android_gms_internal_zzju_zza.zzcik, com_google_android_gms_internal_zzju_zza.zzcil, com_google_android_gms_internal_zzju_zza.zzcie);
        } catch (Throwable e) {
            zzb.zzb("Unable to generate ad state for an interstitial ad with pooling.", e);
            return com_google_android_gms_internal_zzju_zza;
        }
    }

    public void showInterstitial() {
        zzab.zzhi("showInterstitial must be called on the main UI thread.");
        if (this.zzajs.zzapb == null) {
            zzb.zzcx("The interstitial has not loaded.");
            return;
        }
        if (((Boolean) zzdc.zzbau.get()).booleanValue()) {
            Bundle bundle;
            String packageName = this.zzajs.zzagf.getApplicationContext() != null ? this.zzajs.zzagf.getApplicationContext().getPackageName() : this.zzajs.zzagf.getPackageName();
            if (!this.zzalw) {
                zzb.zzcx("It is not recommended to show an interstitial before onAdLoaded completes.");
                bundle = new Bundle();
                bundle.putString(CMBaseNativeAd.KEY_APP_ID, packageName);
                bundle.putString(NativeProtocol.WEB_DIALOG_ACTION, "show_interstitial_before_load_finish");
                zzb(bundle);
            }
            if (!zzu.zzfq().zzai(this.zzajs.zzagf)) {
                zzb.zzcx("It is not recommended to show an interstitial when app is not in foreground.");
                bundle = new Bundle();
                bundle.putString(CMBaseNativeAd.KEY_APP_ID, packageName);
                bundle.putString(NativeProtocol.WEB_DIALOG_ACTION, "show_interstitial_app_not_in_foreground");
                zzb(bundle);
            }
        }
        if (!this.zzajs.zzgq()) {
            if (this.zzajs.zzapb.zzcby && this.zzajs.zzapb.zzboo != null) {
                try {
                    this.zzajs.zzapb.zzboo.showInterstitial();
                } catch (Throwable e) {
                    zzb.zzd("Could not show interstitial.", e);
                    zzeu();
                }
            } else if (this.zzajs.zzapb.zzbtm == null) {
                zzb.zzcx("The interstitial failed to load.");
            } else if (this.zzajs.zzapb.zzbtm.zzun()) {
                zzb.zzcx("The interstitial is already showing.");
            } else {
                this.zzajs.zzapb.zzbtm.zzah(true);
                if (this.zzajs.zzapb.zzcie != null) {
                    this.zzaju.zza(this.zzajs.zzapa, this.zzajs.zzapb);
                }
                Bitmap zzaj = this.zzajs.zzame ? zzu.zzfq().zzaj(this.zzajs.zzagf) : null;
                this.zzalx = zzu.zzgh().zzb(zzaj);
                if (!((Boolean) zzdc.zzbca.get()).booleanValue() || zzaj == null) {
                    InterstitialAdParameterParcel interstitialAdParameterParcel = new InterstitialAdParameterParcel(this.zzajs.zzame, zzet(), false, 0.0f, -1);
                    int requestedOrientation = this.zzajs.zzapb.zzbtm.getRequestedOrientation();
                    if (requestedOrientation == -1) {
                        requestedOrientation = this.zzajs.zzapb.orientation;
                    }
                    zzu.zzfo().zza(this.zzajs.zzagf, new AdOverlayInfoParcel(this, this, this, this.zzajs.zzapb.zzbtm, requestedOrientation, this.zzajs.zzaow, this.zzajs.zzapb.zzccd, interstitialAdParameterParcel));
                    return;
                }
                Future future = (Future) new zza(this, this.zzalx).zzpy();
            }
        }
    }

    protected zzlh zza(com.google.android.gms.internal.zzju.zza com_google_android_gms_internal_zzju_zza, @Nullable zze com_google_android_gms_ads_internal_zze, @Nullable zzjo com_google_android_gms_internal_zzjo) {
        zzlh zza = zzu.zzfr().zza(this.zzajs.zzagf, this.zzajs.zzapa, false, false, this.zzajs.zzaov, this.zzajs.zzaow, this.zzajn, this, this.zzajv);
        zza.zzuj().zza(this, null, this, this, ((Boolean) zzdc.zzazt.get()).booleanValue(), this, this, com_google_android_gms_ads_internal_zze, null, com_google_android_gms_internal_zzjo);
        zza((zzft) zza);
        zza.zzcz(com_google_android_gms_internal_zzju_zza.zzcip.zzcbg);
        zzey.zza(zza, (com.google.android.gms.internal.zzey.zza) this);
        return zza;
    }

    public void zza(com.google.android.gms.internal.zzju.zza com_google_android_gms_internal_zzju_zza, zzdk com_google_android_gms_internal_zzdk) {
        Object obj = 1;
        if (!((Boolean) zzdc.zzbae.get()).booleanValue()) {
            super.zza(com_google_android_gms_internal_zzju_zza, com_google_android_gms_internal_zzdk);
        } else if (com_google_android_gms_internal_zzju_zza.errorCode != -2) {
            super.zza(com_google_android_gms_internal_zzju_zza, com_google_android_gms_internal_zzdk);
        } else {
            Bundle bundle = com_google_android_gms_internal_zzju_zza.zzcip.zzcar.zzatw.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
            Object obj2 = (bundle == null || !bundle.containsKey("gw")) ? 1 : null;
            if (com_google_android_gms_internal_zzju_zza.zzciq.zzcby) {
                obj = null;
            }
            if (!(obj2 == null || r2 == null)) {
                this.zzajs.zzapc = zzc(com_google_android_gms_internal_zzju_zza);
            }
            super.zza(this.zzajs.zzapc, com_google_android_gms_internal_zzdk);
        }
    }

    public void zza(boolean z, float f) {
        this.zzaly = z;
        this.zzalz = f;
    }

    public boolean zza(AdRequestParcel adRequestParcel, zzdk com_google_android_gms_internal_zzdk) {
        if (this.zzajs.zzapb == null) {
            return super.zza(adRequestParcel, com_google_android_gms_internal_zzdk);
        }
        zzb.zzcx("An interstitial is already loading. Aborting.");
        return false;
    }

    protected boolean zza(AdRequestParcel adRequestParcel, zzju com_google_android_gms_internal_zzju, boolean z) {
        if (this.zzajs.zzgp() && com_google_android_gms_internal_zzju.zzbtm != null) {
            zzu.zzfs().zzi(com_google_android_gms_internal_zzju.zzbtm);
        }
        return this.zzajr.zzfc();
    }

    public boolean zza(@Nullable zzju com_google_android_gms_internal_zzju, zzju com_google_android_gms_internal_zzju2) {
        if (!super.zza(com_google_android_gms_internal_zzju, com_google_android_gms_internal_zzju2)) {
            return false;
        }
        if (!(this.zzajs.zzgp() || this.zzajs.zzapv == null || com_google_android_gms_internal_zzju2.zzcie == null)) {
            this.zzaju.zza(this.zzajs.zzapa, com_google_android_gms_internal_zzju2, this.zzajs.zzapv);
        }
        return true;
    }

    public void zzb(RewardItemParcel rewardItemParcel) {
        if (this.zzajs.zzapb != null) {
            if (this.zzajs.zzapb.zzccp != null) {
                zzu.zzfq().zza(this.zzajs.zzagf, this.zzajs.zzaow.zzcs, this.zzajs.zzapb.zzccp);
            }
            if (this.zzajs.zzapb.zzccn != null) {
                rewardItemParcel = this.zzajs.zzapb.zzccn;
            }
        }
        zza(rewardItemParcel);
    }

    protected void zzdr() {
        zzeu();
        super.zzdr();
    }

    protected void zzdu() {
        super.zzdu();
        this.zzalw = true;
    }

    public void zzdy() {
        recordImpression();
        super.zzdy();
        if (this.zzajs.zzapb != null && this.zzajs.zzapb.zzbtm != null) {
            zzli zzuj = this.zzajs.zzapb.zzbtm.zzuj();
            if (zzuj != null) {
                zzuj.zzva();
            }
        }
    }

    protected boolean zzet() {
        if (!(this.zzajs.zzagf instanceof Activity)) {
            return false;
        }
        Window window = ((Activity) this.zzajs.zzagf).getWindow();
        if (window == null || window.getDecorView() == null) {
            return false;
        }
        Rect rect = new Rect();
        Rect rect2 = new Rect();
        window.getDecorView().getGlobalVisibleRect(rect, null);
        window.getDecorView().getWindowVisibleDisplayFrame(rect2);
        boolean z = (rect.bottom == 0 || rect2.bottom == 0 || rect.top != rect2.top) ? false : true;
        return z;
    }

    public void zzeu() {
        zzu.zzgh().zzb(Integer.valueOf(this.zzalx));
        if (this.zzajs.zzgp()) {
            this.zzajs.zzgm();
            this.zzajs.zzapb = null;
            this.zzajs.zzame = false;
            this.zzalw = false;
        }
    }

    public void zzev() {
        if (!(this.zzajs.zzapb == null || this.zzajs.zzapb.zzcij == null)) {
            zzu.zzfq().zza(this.zzajs.zzagf, this.zzajs.zzaow.zzcs, this.zzajs.zzapb.zzcij);
        }
        zzdv();
    }

    public void zzg(boolean z) {
        this.zzajs.zzame = z;
    }
}

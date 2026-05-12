package com.google.android.gms.ads.internal;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.internal.zzdc;
import com.google.android.gms.internal.zzgj;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzjo;
import com.google.android.gms.internal.zzju;
import com.google.android.gms.internal.zzju.zza;
import com.google.android.gms.internal.zzlh;
import com.google.android.gms.internal.zzli;
import com.google.android.gms.internal.zzlm;
import java.util.List;

@zzin
public class zzf extends zzc implements OnGlobalLayoutListener, OnScrollChangedListener {
    private boolean zzakp;

    public zzf(Context context, AdSizeParcel adSizeParcel, String str, zzgj com_google_android_gms_internal_zzgj, VersionInfoParcel versionInfoParcel, zzd com_google_android_gms_ads_internal_zzd) {
        super(context, adSizeParcel, str, com_google_android_gms_internal_zzgj, versionInfoParcel, com_google_android_gms_ads_internal_zzd);
    }

    private AdSizeParcel zzb(zza com_google_android_gms_internal_zzju_zza) {
        if (com_google_android_gms_internal_zzju_zza.zzciq.zzauv) {
            return this.zzajs.zzapa;
        }
        AdSize adSize;
        String str = com_google_android_gms_internal_zzju_zza.zzciq.zzccb;
        if (str != null) {
            String[] split = str.split("[xX]");
            split[0] = split[0].trim();
            split[1] = split[1].trim();
            adSize = new AdSize(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
        } else {
            adSize = this.zzajs.zzapa.zzij();
        }
        return new AdSizeParcel(this.zzajs.zzagf, adSize);
    }

    private boolean zzb(@Nullable zzju com_google_android_gms_internal_zzju, zzju com_google_android_gms_internal_zzju2) {
        if (com_google_android_gms_internal_zzju2.zzcby) {
            View zzf = zzn.zzf(com_google_android_gms_internal_zzju2);
            if (zzf == null) {
                zzb.zzcx("Could not get mediation view");
                return false;
            }
            View nextView = this.zzajs.zzaox.getNextView();
            if (nextView != null) {
                if (nextView instanceof zzlh) {
                    ((zzlh) nextView).destroy();
                }
                this.zzajs.zzaox.removeView(nextView);
            }
            if (!zzn.zzg(com_google_android_gms_internal_zzju2)) {
                try {
                    zzb(zzf);
                } catch (Throwable th) {
                    zzb.zzd("Could not add mediation view to view hierarchy.", th);
                    return false;
                }
            }
        } else if (!(com_google_android_gms_internal_zzju2.zzcii == null || com_google_android_gms_internal_zzju2.zzbtm == null)) {
            com_google_android_gms_internal_zzju2.zzbtm.zza(com_google_android_gms_internal_zzju2.zzcii);
            this.zzajs.zzaox.removeAllViews();
            this.zzajs.zzaox.setMinimumWidth(com_google_android_gms_internal_zzju2.zzcii.widthPixels);
            this.zzajs.zzaox.setMinimumHeight(com_google_android_gms_internal_zzju2.zzcii.heightPixels);
            zzb(com_google_android_gms_internal_zzju2.zzbtm.getView());
        }
        if (this.zzajs.zzaox.getChildCount() > 1) {
            this.zzajs.zzaox.showNext();
        }
        if (com_google_android_gms_internal_zzju != null) {
            View nextView2 = this.zzajs.zzaox.getNextView();
            if (nextView2 instanceof zzlh) {
                ((zzlh) nextView2).zza(this.zzajs.zzagf, this.zzajs.zzapa, this.zzajn);
            } else if (nextView2 != null) {
                this.zzajs.zzaox.removeView(nextView2);
            }
            this.zzajs.zzgo();
        }
        this.zzajs.zzaox.setVisibility(0);
        return true;
    }

    private void zzd(zzju com_google_android_gms_internal_zzju) {
        if (this.zzajs.zzgp()) {
            if (com_google_android_gms_internal_zzju.zzbtm != null) {
                if (com_google_android_gms_internal_zzju.zzcie != null) {
                    this.zzaju.zza(this.zzajs.zzapa, com_google_android_gms_internal_zzju);
                }
                if (com_google_android_gms_internal_zzju.zzho()) {
                    this.zzaju.zza(this.zzajs.zzapa, com_google_android_gms_internal_zzju).zza(com_google_android_gms_internal_zzju.zzbtm);
                } else {
                    com_google_android_gms_internal_zzju.zzbtm.zzuj().zza(new 3(this, com_google_android_gms_internal_zzju));
                }
            }
        } else if (this.zzajs.zzapv != null && com_google_android_gms_internal_zzju.zzcie != null) {
            this.zzaju.zza(this.zzajs.zzapa, com_google_android_gms_internal_zzju, this.zzajs.zzapv);
        }
    }

    public void onGlobalLayout() {
        zze(this.zzajs.zzapb);
    }

    public void onScrollChanged() {
        zze(this.zzajs.zzapb);
    }

    public void setManualImpressionsEnabled(boolean z) {
        zzab.zzhi("setManualImpressionsEnabled must be called from the main thread.");
        this.zzakp = z;
    }

    public void showInterstitial() {
        throw new IllegalStateException("Interstitial is NOT supported by BannerAdManager.");
    }

    protected zzlh zza(zza com_google_android_gms_internal_zzju_zza, @Nullable zze com_google_android_gms_ads_internal_zze, @Nullable zzjo com_google_android_gms_internal_zzjo) {
        if (this.zzajs.zzapa.zzaut == null && this.zzajs.zzapa.zzauv) {
            this.zzajs.zzapa = zzb(com_google_android_gms_internal_zzju_zza);
        }
        return super.zza(com_google_android_gms_internal_zzju_zza, com_google_android_gms_ads_internal_zze, com_google_android_gms_internal_zzjo);
    }

    protected void zza(@Nullable zzju com_google_android_gms_internal_zzju, boolean z) {
        super.zza(com_google_android_gms_internal_zzju, z);
        if (zzn.zzg(com_google_android_gms_internal_zzju)) {
            zzn.zza(com_google_android_gms_internal_zzju, new zza(this));
        }
    }

    public boolean zza(@Nullable zzju com_google_android_gms_internal_zzju, zzju com_google_android_gms_internal_zzju2) {
        if (!super.zza(com_google_android_gms_internal_zzju, com_google_android_gms_internal_zzju2)) {
            return false;
        }
        if (!this.zzajs.zzgp() || zzb(com_google_android_gms_internal_zzju, com_google_android_gms_internal_zzju2)) {
            zzlm zzut;
            if (com_google_android_gms_internal_zzju2.zzccq) {
                zze(com_google_android_gms_internal_zzju2);
                zzu.zzgk().zza(this.zzajs.zzaox, (OnGlobalLayoutListener) this);
                zzu.zzgk().zza(this.zzajs.zzaox, (OnScrollChangedListener) this);
                if (!com_google_android_gms_internal_zzju2.zzcif) {
                    Runnable 1 = new 1(this);
                    zzli zzuj = com_google_android_gms_internal_zzju2.zzbtm != null ? com_google_android_gms_internal_zzju2.zzbtm.zzuj() : null;
                    if (zzuj != null) {
                        zzuj.zza(new 2(this, com_google_android_gms_internal_zzju2, 1));
                    }
                }
            } else if (!this.zzajs.zzgq() || ((Boolean) zzdc.zzbce.get()).booleanValue()) {
                zza(com_google_android_gms_internal_zzju2, false);
            }
            if (com_google_android_gms_internal_zzju2.zzbtm != null) {
                zzut = com_google_android_gms_internal_zzju2.zzbtm.zzut();
                zzli zzuj2 = com_google_android_gms_internal_zzju2.zzbtm.zzuj();
                if (zzuj2 != null) {
                    zzuj2.zzva();
                }
            } else {
                zzut = null;
            }
            if (!(this.zzajs.zzapp == null || zzut == null)) {
                zzut.zzam(this.zzajs.zzapp.zzaxm);
            }
            zzd(com_google_android_gms_internal_zzju2);
            return true;
        }
        zzh(0);
        return false;
    }

    public boolean zzb(AdRequestParcel adRequestParcel) {
        return super.zzb(zze(adRequestParcel));
    }

    @Nullable
    public com.google.android.gms.ads.internal.client.zzab zzdq() {
        zzab.zzhi("getVideoController must be called from the main thread.");
        return (this.zzajs.zzapb == null || this.zzajs.zzapb.zzbtm == null) ? null : this.zzajs.zzapb.zzbtm.zzut();
    }

    protected boolean zzdw() {
        boolean z = true;
        if (!zzu.zzfq().zza(this.zzajs.zzagf.getPackageManager(), this.zzajs.zzagf.getPackageName(), "android.permission.INTERNET")) {
            zzm.zziw().zza(this.zzajs.zzaox, this.zzajs.zzapa, "Missing internet permission in AndroidManifest.xml.", "Missing internet permission in AndroidManifest.xml. You must have the following declaration: <uses-permission android:name=\"android.permission.INTERNET\" />");
            z = false;
        }
        if (!zzu.zzfq().zzac(this.zzajs.zzagf)) {
            zzm.zziw().zza(this.zzajs.zzaox, this.zzajs.zzapa, "Missing AdActivity with android:configChanges in AndroidManifest.xml.", "Missing AdActivity with android:configChanges in AndroidManifest.xml. You must have the following declaration within the <application> element: <activity android:name=\"com.google.android.gms.ads.AdActivity\" android:configChanges=\"keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize\" />");
            z = false;
        }
        if (!(z || this.zzajs.zzaox == null)) {
            this.zzajs.zzaox.setVisibility(0);
        }
        return z;
    }

    AdRequestParcel zze(AdRequestParcel adRequestParcel) {
        if (adRequestParcel.zzatr == this.zzakp) {
            return adRequestParcel;
        }
        int i = adRequestParcel.versionCode;
        long j = adRequestParcel.zzatm;
        Bundle bundle = adRequestParcel.extras;
        int i2 = adRequestParcel.zzatn;
        List list = adRequestParcel.zzato;
        boolean z = adRequestParcel.zzatp;
        int i3 = adRequestParcel.zzatq;
        boolean z2 = adRequestParcel.zzatr || this.zzakp;
        return new AdRequestParcel(i, j, bundle, i2, list, z, i3, z2, adRequestParcel.zzats, adRequestParcel.zzatt, adRequestParcel.zzatu, adRequestParcel.zzatv, adRequestParcel.zzatw, adRequestParcel.zzatx, adRequestParcel.zzaty, adRequestParcel.zzatz, adRequestParcel.zzaua, adRequestParcel.zzaub);
    }

    void zze(@Nullable zzju com_google_android_gms_internal_zzju) {
        if (com_google_android_gms_internal_zzju != null && !com_google_android_gms_internal_zzju.zzcif && this.zzajs.zzaox != null && zzu.zzfq().zza(this.zzajs.zzaox, this.zzajs.zzagf) && this.zzajs.zzaox.getGlobalVisibleRect(new Rect(), null)) {
            if (!(com_google_android_gms_internal_zzju == null || com_google_android_gms_internal_zzju.zzbtm == null || com_google_android_gms_internal_zzju.zzbtm.zzuj() == null)) {
                com_google_android_gms_internal_zzju.zzbtm.zzuj().zza(null);
            }
            zza(com_google_android_gms_internal_zzju, false);
            com_google_android_gms_internal_zzju.zzcif = true;
        }
    }
}

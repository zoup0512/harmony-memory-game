package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsIntent.Builder;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.overlay.AdLauncherIntentInfoParcel;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialListener;

@zzin
public class zzgy implements MediationInterstitialAdapter {
    private Uri mUri;
    private Activity zzbpu;
    private zzdq zzbpv;
    private MediationInterstitialListener zzbpw;

    public static boolean zzp(Context context) {
        return zzdq.zzo(context);
    }

    public void onDestroy() {
        zzb.zzcv("Destroying AdMobCustomTabsAdapter adapter.");
        try {
            this.zzbpv.zzd(this.zzbpu);
        } catch (Throwable e) {
            zzb.zzb("Exception while unbinding from CustomTabsService.", e);
        }
    }

    public void onPause() {
        zzb.zzcv("Pausing AdMobCustomTabsAdapter adapter.");
    }

    public void onResume() {
        zzb.zzcv("Resuming AdMobCustomTabsAdapter adapter.");
    }

    public void requestInterstitialAd(Context context, MediationInterstitialListener mediationInterstitialListener, Bundle bundle, MediationAdRequest mediationAdRequest, Bundle bundle2) {
        this.zzbpw = mediationInterstitialListener;
        if (this.zzbpw == null) {
            zzb.zzcx("Listener not set for mediation. Returning.");
        } else if (!(context instanceof Activity)) {
            zzb.zzcx("AdMobCustomTabs can only work with Activity context. Bailing out.");
            this.zzbpw.onAdFailedToLoad(this, 0);
        } else if (zzp(context)) {
            Object string = bundle.getString("tab_url");
            if (TextUtils.isEmpty(string)) {
                zzb.zzcx("The tab_url retrieved from mediation metadata is empty. Bailing out.");
                this.zzbpw.onAdFailedToLoad(this, 0);
                return;
            }
            this.zzbpu = (Activity) context;
            this.mUri = Uri.parse(string);
            this.zzbpv = new zzdq();
            this.zzbpv.zza(new 1(this));
            this.zzbpv.zze(this.zzbpu);
            this.zzbpw.onAdLoaded(this);
        } else {
            zzb.zzcx("Default browser does not support custom tabs. Bailing out.");
            this.zzbpw.onAdFailedToLoad(this, 0);
        }
    }

    public void showInterstitial() {
        CustomTabsIntent build = new Builder(this.zzbpv.zzkl()).build();
        build.intent.setData(this.mUri);
        zzkh.zzclc.post(new 3(this, new AdOverlayInfoParcel(new AdLauncherIntentInfoParcel(build.intent), null, new 2(this), null, new VersionInfoParcel(0, 0, false))));
        zzu.zzft().zzaf(false);
    }
}

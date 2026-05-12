package com.google.android.gms.ads.internal.request;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.webkit.CookieManager;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.internal.zzas;
import com.google.android.gms.internal.zzdc;
import com.google.android.gms.internal.zzga;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzkc;
import com.google.android.gms.internal.zzkg;
import com.google.android.gms.internal.zzkh;
import com.google.android.gms.internal.zzkj;
import com.google.android.gms.internal.zzla;
import com.google.android.gms.internal.zzlb;
import org.json.JSONObject;

@zzin
public class zzb extends zzkc implements com.google.android.gms.ads.internal.request.zzc.zza {
    private final Context mContext;
    private final zzas zzbgd;
    zzga zzboe;
    private AdRequestInfoParcel zzbot;
    AdResponseParcel zzbxs;
    private Runnable zzbxt;
    private final Object zzbxu = new Object();
    private final com.google.android.gms.ads.internal.request.zza.zza zzcae;
    private final com.google.android.gms.ads.internal.request.AdRequestInfoParcel.zza zzcaf;
    zzkj zzcag;

    @zzin
    static final class zza extends Exception {
        private final int zzbyi;

        public zza(String str, int i) {
            super(str);
            this.zzbyi = i;
        }

        public int getErrorCode() {
            return this.zzbyi;
        }
    }

    public zzb(Context context, com.google.android.gms.ads.internal.request.AdRequestInfoParcel.zza com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza, zzas com_google_android_gms_internal_zzas, com.google.android.gms.ads.internal.request.zza.zza com_google_android_gms_ads_internal_request_zza_zza) {
        this.zzcae = com_google_android_gms_ads_internal_request_zza_zza;
        this.mContext = context;
        this.zzcaf = com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza;
        this.zzbgd = com_google_android_gms_internal_zzas;
    }

    private void zzd(int i, String str) {
        if (i == 3 || i == -1) {
            com.google.android.gms.ads.internal.util.client.zzb.zzcw(str);
        } else {
            com.google.android.gms.ads.internal.util.client.zzb.zzcx(str);
        }
        if (this.zzbxs == null) {
            this.zzbxs = new AdResponseParcel(i);
        } else {
            this.zzbxs = new AdResponseParcel(i, this.zzbxs.zzbns);
        }
        this.zzcae.zza(new com.google.android.gms.internal.zzju.zza(this.zzbot != null ? this.zzbot : new AdRequestInfoParcel(this.zzcaf, null, -1), this.zzbxs, this.zzboe, null, i, -1, this.zzbxs.zzccc, null));
    }

    public void onStop() {
        synchronized (this.zzbxu) {
            if (this.zzcag != null) {
                this.zzcag.cancel();
            }
        }
    }

    zzkj zza(VersionInfoParcel versionInfoParcel, zzla<AdRequestInfoParcel> com_google_android_gms_internal_zzla_com_google_android_gms_ads_internal_request_AdRequestInfoParcel) {
        return zzc.zza(this.mContext, versionInfoParcel, com_google_android_gms_internal_zzla_com_google_android_gms_ads_internal_request_AdRequestInfoParcel, this);
    }

    protected AdSizeParcel zzb(AdRequestInfoParcel adRequestInfoParcel) throws zza {
        int i;
        if (this.zzbxs.zzauv) {
            for (AdSizeParcel adSizeParcel : adRequestInfoParcel.zzapa.zzaut) {
                if (adSizeParcel.zzauv) {
                    return new AdSizeParcel(adSizeParcel, adRequestInfoParcel.zzapa.zzaut);
                }
            }
        }
        if (this.zzbxs.zzccb == null) {
            throw new zza("The ad response must specify one of the supported ad sizes.", 0);
        }
        String[] split = this.zzbxs.zzccb.split("x");
        if (split.length != 2) {
            String str = "Invalid ad size format from the ad response: ";
            String valueOf = String.valueOf(this.zzbxs.zzccb);
            throw new zza(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), 0);
        }
        try {
            int parseInt = Integer.parseInt(split[0]);
            int parseInt2 = Integer.parseInt(split[1]);
            for (AdSizeParcel adSizeParcel2 : adRequestInfoParcel.zzapa.zzaut) {
                float f = this.mContext.getResources().getDisplayMetrics().density;
                i = adSizeParcel2.width == -1 ? (int) (((float) adSizeParcel2.widthPixels) / f) : adSizeParcel2.width;
                int i2 = adSizeParcel2.height == -2 ? (int) (((float) adSizeParcel2.heightPixels) / f) : adSizeParcel2.height;
                if (parseInt == i && parseInt2 == i2) {
                    return new AdSizeParcel(adSizeParcel2, adRequestInfoParcel.zzapa.zzaut);
                }
            }
            str = "The ad size from the ad response was not one of the requested sizes: ";
            valueOf = String.valueOf(this.zzbxs.zzccb);
            throw new zza(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), 0);
        } catch (NumberFormatException e) {
            str = "Invalid ad size number from the ad response: ";
            valueOf = String.valueOf(this.zzbxs.zzccb);
            throw new zza(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), 0);
        }
    }

    public void zzb(@NonNull AdResponseParcel adResponseParcel) {
        com.google.android.gms.ads.internal.util.client.zzb.zzcv("Received ad response.");
        this.zzbxs = adResponseParcel;
        long elapsedRealtime = zzu.zzfu().elapsedRealtime();
        synchronized (this.zzbxu) {
            this.zzcag = null;
        }
        zzu.zzft().zzd(this.mContext, this.zzbxs.zzcbq);
        try {
            if (this.zzbxs.errorCode == -2 || this.zzbxs.errorCode == -3) {
                JSONObject jSONObject;
                zzqv();
                AdSizeParcel zzb = this.zzbot.zzapa.zzaut != null ? zzb(this.zzbot) : null;
                zzu.zzft().zzae(this.zzbxs.zzcci);
                if (!TextUtils.isEmpty(this.zzbxs.zzccg)) {
                    try {
                        jSONObject = new JSONObject(this.zzbxs.zzccg);
                    } catch (Throwable e) {
                        com.google.android.gms.ads.internal.util.client.zzb.zzb("Error parsing the JSON for Active View.", e);
                    }
                    this.zzcae.zza(new com.google.android.gms.internal.zzju.zza(this.zzbot, this.zzbxs, this.zzboe, zzb, -2, elapsedRealtime, this.zzbxs.zzccc, jSONObject));
                    zzkh.zzclc.removeCallbacks(this.zzbxt);
                    return;
                }
                jSONObject = null;
                this.zzcae.zza(new com.google.android.gms.internal.zzju.zza(this.zzbot, this.zzbxs, this.zzboe, zzb, -2, elapsedRealtime, this.zzbxs.zzccc, jSONObject));
                zzkh.zzclc.removeCallbacks(this.zzbxt);
                return;
            }
            throw new zza("There was a problem getting an ad response. ErrorCode: " + this.zzbxs.errorCode, this.zzbxs.errorCode);
        } catch (zza e2) {
            zzd(e2.getErrorCode(), e2.getMessage());
            zzkh.zzclc.removeCallbacks(this.zzbxt);
        }
    }

    public void zzew() {
        com.google.android.gms.ads.internal.util.client.zzb.zzcv("AdLoaderBackgroundTask started.");
        this.zzbxt = new 1(this);
        zzkh.zzclc.postDelayed(this.zzbxt, ((Long) zzdc.zzbbg.get()).longValue());
        zzla com_google_android_gms_internal_zzlb = new zzlb();
        long elapsedRealtime = zzu.zzfu().elapsedRealtime();
        zzkg.zza(new 2(this, com_google_android_gms_internal_zzlb));
        this.zzbot = new AdRequestInfoParcel(this.zzcaf, this.zzbgd.zzaw().zzb(this.mContext), elapsedRealtime);
        com_google_android_gms_internal_zzlb.zzg(this.zzbot);
    }

    protected void zzqv() throws zza {
        if (this.zzbxs.errorCode != -3) {
            if (TextUtils.isEmpty(this.zzbxs.body)) {
                throw new zza("No fill from ad server.", 3);
            }
            zzu.zzft().zzc(this.mContext, this.zzbxs.zzcaz);
            if (this.zzbxs.zzcby) {
                try {
                    this.zzboe = new zzga(this.zzbxs.body);
                    zzu.zzft().zzaf(this.zzboe.zzbnq);
                } catch (Throwable e) {
                    com.google.android.gms.ads.internal.util.client.zzb.zzb("Could not parse mediation config.", e);
                    String str = "Could not parse mediation config: ";
                    String valueOf = String.valueOf(this.zzbxs.body);
                    throw new zza(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), 0);
                }
            }
            zzu.zzft().zzaf(this.zzbxs.zzbnq);
            if (!TextUtils.isEmpty(this.zzbxs.zzcbr) && ((Boolean) zzdc.zzbdn.get()).booleanValue()) {
                com.google.android.gms.ads.internal.util.client.zzb.zzcv("Received cookie from server. Setting webview cookie in CookieManager.");
                CookieManager zzao = zzu.zzfs().zzao(this.mContext);
                if (zzao != null) {
                    zzao.setCookie("googleads.g.doubleclick.net", this.zzbxs.zzcbr);
                }
            }
        }
    }
}

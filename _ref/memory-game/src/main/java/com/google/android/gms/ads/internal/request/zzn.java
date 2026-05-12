package com.google.android.gms.ads.internal.request;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.request.zza.zza;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.internal.zzcv;
import com.google.android.gms.internal.zzdc;
import com.google.android.gms.internal.zzep;
import com.google.android.gms.internal.zzeq;
import com.google.android.gms.internal.zzeu;
import com.google.android.gms.internal.zzfp;
import com.google.android.gms.internal.zzfs;
import com.google.android.gms.internal.zzfs.zzc;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zziq;
import com.google.android.gms.internal.zzju;
import com.google.android.gms.internal.zzkc;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

@zzin
public class zzn extends zzkc {
    private static final Object zzamr = new Object();
    private static zzfs zzbyv = null;
    static final long zzcdf = TimeUnit.SECONDS.toMillis(10);
    static boolean zzcdg = false;
    private static zzeq zzcdh = null;
    private static zzeu zzcdi = null;
    private static zzep zzcdj = null;
    private final Context mContext;
    private final Object zzbxu = new Object();
    private final zza zzcae;
    private final AdRequestInfoParcel.zza zzcaf;
    private zzc zzcdk;

    public zzn(Context context, AdRequestInfoParcel.zza com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza, zza com_google_android_gms_ads_internal_request_zza_zza) {
        super(true);
        this.zzcae = com_google_android_gms_ads_internal_request_zza_zza;
        this.mContext = context;
        this.zzcaf = com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza;
        synchronized (zzamr) {
            if (!zzcdg) {
                zzcdi = new zzeu();
                zzcdh = new zzeq(context.getApplicationContext(), com_google_android_gms_ads_internal_request_AdRequestInfoParcel_zza.zzaow);
                zzcdj = new zzc();
                zzbyv = new zzfs(this.mContext.getApplicationContext(), this.zzcaf.zzaow, (String) zzdc.zzaxy.get(), new zzb(), new zza());
                zzcdg = true;
            }
        }
    }

    private JSONObject zza(AdRequestInfoParcel adRequestInfoParcel, String str) {
        Info advertisingIdInfo;
        Throwable e;
        Object obj;
        Map hashMap;
        JSONObject jSONObject = null;
        Bundle bundle = adRequestInfoParcel.zzcar.extras.getBundle("sdk_less_server_data");
        String string = adRequestInfoParcel.zzcar.extras.getString("sdk_less_network_id");
        if (bundle != null) {
            JSONObject zza = zziq.zza(this.mContext, adRequestInfoParcel, zzu.zzfw().zzy(this.mContext), jSONObject, jSONObject, new zzcv((String) zzdc.zzaxy.get()), jSONObject, new ArrayList(), jSONObject, jSONObject);
            if (zza != null) {
                try {
                    advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(this.mContext);
                } catch (IOException e2) {
                    e = e2;
                    zzb.zzd("Cannot get advertising id info", e);
                    obj = jSONObject;
                    hashMap = new HashMap();
                    hashMap.put("request_id", str);
                    hashMap.put("network_id", string);
                    hashMap.put("request_param", zza);
                    hashMap.put(ShareConstants.WEB_DIALOG_PARAM_DATA, bundle);
                    if (advertisingIdInfo != null) {
                        hashMap.put("adid", advertisingIdInfo.getId());
                        hashMap.put("lat", Integer.valueOf(advertisingIdInfo.isLimitAdTrackingEnabled() ? 0 : 1));
                    }
                    jSONObject = zzu.zzfq().zzam(hashMap);
                    return jSONObject;
                } catch (IllegalStateException e3) {
                    e = e3;
                    zzb.zzd("Cannot get advertising id info", e);
                    obj = jSONObject;
                    hashMap = new HashMap();
                    hashMap.put("request_id", str);
                    hashMap.put("network_id", string);
                    hashMap.put("request_param", zza);
                    hashMap.put(ShareConstants.WEB_DIALOG_PARAM_DATA, bundle);
                    if (advertisingIdInfo != null) {
                        hashMap.put("adid", advertisingIdInfo.getId());
                        if (advertisingIdInfo.isLimitAdTrackingEnabled()) {
                        }
                        hashMap.put("lat", Integer.valueOf(advertisingIdInfo.isLimitAdTrackingEnabled() ? 0 : 1));
                    }
                    jSONObject = zzu.zzfq().zzam(hashMap);
                    return jSONObject;
                } catch (GooglePlayServicesNotAvailableException e4) {
                    e = e4;
                    zzb.zzd("Cannot get advertising id info", e);
                    obj = jSONObject;
                    hashMap = new HashMap();
                    hashMap.put("request_id", str);
                    hashMap.put("network_id", string);
                    hashMap.put("request_param", zza);
                    hashMap.put(ShareConstants.WEB_DIALOG_PARAM_DATA, bundle);
                    if (advertisingIdInfo != null) {
                        hashMap.put("adid", advertisingIdInfo.getId());
                        if (advertisingIdInfo.isLimitAdTrackingEnabled()) {
                        }
                        hashMap.put("lat", Integer.valueOf(advertisingIdInfo.isLimitAdTrackingEnabled() ? 0 : 1));
                    }
                    jSONObject = zzu.zzfq().zzam(hashMap);
                    return jSONObject;
                } catch (GooglePlayServicesRepairableException e5) {
                    e = e5;
                    zzb.zzd("Cannot get advertising id info", e);
                    obj = jSONObject;
                    hashMap = new HashMap();
                    hashMap.put("request_id", str);
                    hashMap.put("network_id", string);
                    hashMap.put("request_param", zza);
                    hashMap.put(ShareConstants.WEB_DIALOG_PARAM_DATA, bundle);
                    if (advertisingIdInfo != null) {
                        hashMap.put("adid", advertisingIdInfo.getId());
                        if (advertisingIdInfo.isLimitAdTrackingEnabled()) {
                        }
                        hashMap.put("lat", Integer.valueOf(advertisingIdInfo.isLimitAdTrackingEnabled() ? 0 : 1));
                    }
                    jSONObject = zzu.zzfq().zzam(hashMap);
                    return jSONObject;
                }
                hashMap = new HashMap();
                hashMap.put("request_id", str);
                hashMap.put("network_id", string);
                hashMap.put("request_param", zza);
                hashMap.put(ShareConstants.WEB_DIALOG_PARAM_DATA, bundle);
                if (advertisingIdInfo != null) {
                    hashMap.put("adid", advertisingIdInfo.getId());
                    if (advertisingIdInfo.isLimitAdTrackingEnabled()) {
                    }
                    hashMap.put("lat", Integer.valueOf(advertisingIdInfo.isLimitAdTrackingEnabled() ? 0 : 1));
                }
                try {
                    jSONObject = zzu.zzfq().zzam(hashMap);
                } catch (JSONException e6) {
                }
            }
        }
        return jSONObject;
    }

    protected static void zzb(zzfp com_google_android_gms_internal_zzfp) {
        com_google_android_gms_internal_zzfp.zza("/loadAd", zzcdi);
        com_google_android_gms_internal_zzfp.zza("/fetchHttpRequest", zzcdh);
        com_google_android_gms_internal_zzfp.zza("/invalidRequest", zzcdj);
    }

    protected static void zzc(zzfp com_google_android_gms_internal_zzfp) {
        com_google_android_gms_internal_zzfp.zzb("/loadAd", zzcdi);
        com_google_android_gms_internal_zzfp.zzb("/fetchHttpRequest", zzcdh);
        com_google_android_gms_internal_zzfp.zzb("/invalidRequest", zzcdj);
    }

    private AdResponseParcel zze(AdRequestInfoParcel adRequestInfoParcel) {
        String zzte = zzu.zzfq().zzte();
        JSONObject zza = zza(adRequestInfoParcel, zzte);
        if (zza == null) {
            return new AdResponseParcel(0);
        }
        long elapsedRealtime = zzu.zzfu().elapsedRealtime();
        Future zzaw = zzcdi.zzaw(zzte);
        com.google.android.gms.ads.internal.util.client.zza.zzcnb.post(new 2(this, zza, zzte));
        try {
            JSONObject jSONObject = (JSONObject) zzaw.get(zzcdf - (zzu.zzfu().elapsedRealtime() - elapsedRealtime), TimeUnit.MILLISECONDS);
            if (jSONObject == null) {
                return new AdResponseParcel(-1);
            }
            AdResponseParcel zza2 = zziq.zza(this.mContext, adRequestInfoParcel, jSONObject.toString());
            return (zza2.errorCode == -3 || !TextUtils.isEmpty(zza2.body)) ? zza2 : new AdResponseParcel(3);
        } catch (CancellationException e) {
            return new AdResponseParcel(-1);
        } catch (InterruptedException e2) {
            return new AdResponseParcel(-1);
        } catch (TimeoutException e3) {
            return new AdResponseParcel(2);
        } catch (ExecutionException e4) {
            return new AdResponseParcel(0);
        }
    }

    public void onStop() {
        synchronized (this.zzbxu) {
            com.google.android.gms.ads.internal.util.client.zza.zzcnb.post(new 3(this));
        }
    }

    public void zzew() {
        zzb.zzcv("SdkLessAdLoaderBackgroundTask started.");
        AdRequestInfoParcel adRequestInfoParcel = new AdRequestInfoParcel(this.zzcaf, null, -1);
        AdResponseParcel zze = zze(adRequestInfoParcel);
        AdSizeParcel adSizeParcel = null;
        com.google.android.gms.ads.internal.util.client.zza.zzcnb.post(new 1(this, new zzju.zza(adRequestInfoParcel, zze, null, adSizeParcel, zze.errorCode, zzu.zzfu().elapsedRealtime(), zze.zzccc, null)));
    }
}

package com.google.android.gms.ads.internal.purchase;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.SystemClock;
import com.applovin.sdk.AppLovinEventParameters;
import com.cmcm.adsdk.base.CMBaseNativeAd;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.internal.zzhn.zza;
import com.google.android.gms.internal.zzin;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@zzin
public class zzd extends zza {
    private Context mContext;
    private String zzarj;
    private String zzbwy;
    private ArrayList<String> zzbwz;

    public zzd(String str, ArrayList<String> arrayList, Context context, String str2) {
        this.zzbwy = str;
        this.zzbwz = arrayList;
        this.zzarj = str2;
        this.mContext = context;
    }

    public String getProductId() {
        return this.zzbwy;
    }

    public void recordPlayBillingResolution(int i) {
        if (i == 0) {
            zzps();
        }
        Map zzpr = zzpr();
        zzpr.put("google_play_status", String.valueOf(i));
        zzpr.put(AppLovinEventParameters.PRODUCT_IDENTIFIER, this.zzbwy);
        zzpr.put("status", String.valueOf(zzai(i)));
        List linkedList = new LinkedList();
        Iterator it = this.zzbwz.iterator();
        while (it.hasNext()) {
            linkedList.add(zzu.zzfq().zzb((String) it.next(), zzpr));
        }
        zzu.zzfq().zza(this.mContext, this.zzarj, linkedList);
    }

    public void recordResolution(int i) {
        if (i == 1) {
            zzps();
        }
        Map zzpr = zzpr();
        zzpr.put("status", String.valueOf(i));
        zzpr.put(AppLovinEventParameters.PRODUCT_IDENTIFIER, this.zzbwy);
        List linkedList = new LinkedList();
        Iterator it = this.zzbwz.iterator();
        while (it.hasNext()) {
            linkedList.add(zzu.zzfq().zzb((String) it.next(), zzpr));
        }
        zzu.zzfq().zza(this.mContext, this.zzarj, linkedList);
    }

    protected int zzai(int i) {
        return i == 0 ? 1 : i == 1 ? 2 : i == 4 ? 3 : 0;
    }

    Map<String, String> zzpr() {
        String packageName = this.mContext.getPackageName();
        Object obj = "";
        try {
            obj = this.mContext.getPackageManager().getPackageInfo(packageName, 0).versionName;
        } catch (Throwable e) {
            zzb.zzd("Error to retrieve app version", e);
        }
        long elapsedRealtime = SystemClock.elapsedRealtime() - zzu.zzft().zzsk().zzsx();
        Map<String, String> hashMap = new HashMap();
        hashMap.put("sessionid", zzu.zzft().getSessionId());
        hashMap.put(CMBaseNativeAd.KEY_APP_ID, packageName);
        hashMap.put("osversion", String.valueOf(VERSION.SDK_INT));
        hashMap.put("sdkversion", this.zzarj);
        hashMap.put("appversion", obj);
        hashMap.put("timestamp", String.valueOf(elapsedRealtime));
        return hashMap;
    }

    void zzps() {
        try {
            this.mContext.getClassLoader().loadClass("com.google.ads.conversiontracking.IAPConversionReporter").getDeclaredMethod("reportWithProductId", new Class[]{Context.class, String.class, String.class, Boolean.TYPE}).invoke(null, new Object[]{this.mContext, this.zzbwy, "", Boolean.valueOf(true)});
        } catch (ClassNotFoundException e) {
            zzb.zzcx("Google Conversion Tracking SDK 1.2.0 or above is required to report a conversion.");
        } catch (NoSuchMethodException e2) {
            zzb.zzcx("Google Conversion Tracking SDK 1.2.0 or above is required to report a conversion.");
        } catch (Throwable e3) {
            zzb.zzd("Fail to report a conversion.", e3);
        }
    }
}

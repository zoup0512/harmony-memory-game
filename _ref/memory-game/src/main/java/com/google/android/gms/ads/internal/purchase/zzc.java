package com.google.android.gms.ads.internal.purchase;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemClock;
import com.cube.memorygames.billing.IabHelper;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.internal.zzhs;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzkc;
import com.google.android.gms.internal.zzkd;
import com.google.android.gms.internal.zzkh;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@zzin
public class zzc extends zzkc implements ServiceConnection {
    private Context mContext;
    private final Object zzail;
    private zzhs zzbld;
    private boolean zzbwr;
    private zzb zzbws;
    private zzh zzbwt;
    private List<zzf> zzbwu;
    private zzk zzbwv;

    public zzc(Context context, zzhs com_google_android_gms_internal_zzhs, zzk com_google_android_gms_ads_internal_purchase_zzk) {
        this(context, com_google_android_gms_internal_zzhs, com_google_android_gms_ads_internal_purchase_zzk, new zzb(context), zzh.zzs(context.getApplicationContext()));
    }

    zzc(Context context, zzhs com_google_android_gms_internal_zzhs, zzk com_google_android_gms_ads_internal_purchase_zzk, zzb com_google_android_gms_ads_internal_purchase_zzb, zzh com_google_android_gms_ads_internal_purchase_zzh) {
        this.zzail = new Object();
        this.zzbwr = false;
        this.zzbwu = null;
        this.mContext = context;
        this.zzbld = com_google_android_gms_internal_zzhs;
        this.zzbwv = com_google_android_gms_ads_internal_purchase_zzk;
        this.zzbws = com_google_android_gms_ads_internal_purchase_zzb;
        this.zzbwt = com_google_android_gms_ads_internal_purchase_zzh;
        this.zzbwu = this.zzbwt.zzg(10);
    }

    private void zze(long j) {
        do {
            if (!zzf(j)) {
                zzkd.v("Timeout waiting for pending transaction to be processed.");
            }
        } while (!this.zzbwr);
    }

    private boolean zzf(long j) {
        long elapsedRealtime = 60000 - (SystemClock.elapsedRealtime() - j);
        if (elapsedRealtime <= 0) {
            return false;
        }
        try {
            this.zzail.wait(elapsedRealtime);
        } catch (InterruptedException e) {
            zzb.zzcx("waitWithTimeout_lock interrupted");
        }
        return true;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        synchronized (this.zzail) {
            this.zzbws.zzas(iBinder);
            zzpq();
            this.zzbwr = true;
            this.zzail.notify();
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        zzb.zzcw("In-app billing service disconnected.");
        this.zzbws.destroy();
    }

    public void onStop() {
        synchronized (this.zzail) {
            com.google.android.gms.common.stats.zzb.zzaux().zza(this.mContext, this);
            this.zzbws.destroy();
        }
    }

    protected void zza(zzf com_google_android_gms_ads_internal_purchase_zzf, String str, String str2) {
        Intent intent = new Intent();
        zzu.zzga();
        intent.putExtra(IabHelper.RESPONSE_CODE, 0);
        zzu.zzga();
        intent.putExtra(IabHelper.RESPONSE_INAPP_PURCHASE_DATA, str);
        zzu.zzga();
        intent.putExtra(IabHelper.RESPONSE_INAPP_SIGNATURE, str2);
        zzkh.zzclc.post(new 1(this, com_google_android_gms_ads_internal_purchase_zzf, intent));
    }

    public void zzew() {
        synchronized (this.zzail) {
            Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
            intent.setPackage("com.android.vending");
            com.google.android.gms.common.stats.zzb.zzaux().zza(this.mContext, intent, this, 1);
            zze(SystemClock.elapsedRealtime());
            com.google.android.gms.common.stats.zzb.zzaux().zza(this.mContext, this);
            this.zzbws.destroy();
        }
    }

    protected void zzpq() {
        if (!this.zzbwu.isEmpty()) {
            HashMap hashMap = new HashMap();
            for (zzf com_google_android_gms_ads_internal_purchase_zzf : this.zzbwu) {
                hashMap.put(com_google_android_gms_ads_internal_purchase_zzf.zzbxh, com_google_android_gms_ads_internal_purchase_zzf);
            }
            String str = null;
            while (true) {
                Bundle zzn = this.zzbws.zzn(this.mContext.getPackageName(), str);
                if (zzn == null || zzu.zzga().zze(zzn) != 0) {
                    break;
                }
                ArrayList stringArrayList = zzn.getStringArrayList(IabHelper.RESPONSE_INAPP_ITEM_LIST);
                ArrayList stringArrayList2 = zzn.getStringArrayList(IabHelper.RESPONSE_INAPP_PURCHASE_DATA_LIST);
                ArrayList stringArrayList3 = zzn.getStringArrayList(IabHelper.RESPONSE_INAPP_SIGNATURE_LIST);
                String string = zzn.getString(IabHelper.INAPP_CONTINUATION_TOKEN);
                for (int i = 0; i < stringArrayList.size(); i++) {
                    if (hashMap.containsKey(stringArrayList.get(i))) {
                        str = (String) stringArrayList.get(i);
                        String str2 = (String) stringArrayList2.get(i);
                        String str3 = (String) stringArrayList3.get(i);
                        zzf com_google_android_gms_ads_internal_purchase_zzf2 = (zzf) hashMap.get(str);
                        if (com_google_android_gms_ads_internal_purchase_zzf2.zzbxg.equals(zzu.zzga().zzby(str2))) {
                            zza(com_google_android_gms_ads_internal_purchase_zzf2, str2, str3);
                            hashMap.remove(str);
                        }
                    }
                }
                if (string == null || hashMap.isEmpty()) {
                    break;
                }
                str = string;
            }
            for (String str4 : hashMap.keySet()) {
                this.zzbwt.zza((zzf) hashMap.get(str4));
            }
        }
    }
}

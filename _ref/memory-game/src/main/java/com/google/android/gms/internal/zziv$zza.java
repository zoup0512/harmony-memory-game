package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import com.facebook.places.model.PlaceFields;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.common.util.zzi;
import com.yalantis.ucrop.util.FileUtils;
import java.util.Locale;

public final class zziv$zza {
    private int zzcbb;
    private int zzcbc;
    private float zzcbd;
    private int zzcgd;
    private boolean zzcge;
    private boolean zzcgf;
    private String zzcgg;
    private String zzcgh;
    private boolean zzcgi;
    private boolean zzcgj;
    private boolean zzcgk;
    private boolean zzcgl;
    private String zzcgm;
    private String zzcgn;
    private int zzcgo;
    private int zzcgp;
    private int zzcgq;
    private int zzcgr;
    private int zzcgs;
    private int zzcgt;
    private double zzcgu;
    private boolean zzcgv;
    private boolean zzcgw;
    private int zzcgx;
    private String zzcgy;
    private boolean zzcgz;

    public zziv$zza(Context context) {
        boolean z = true;
        PackageManager packageManager = context.getPackageManager();
        zzv(context);
        zza(context, packageManager);
        zzw(context);
        Locale locale = Locale.getDefault();
        this.zzcge = zza(packageManager, "geo:0,0?q=donuts") != null;
        if (zza(packageManager, "http://www.google.com") == null) {
            z = false;
        }
        this.zzcgf = z;
        this.zzcgh = locale.getCountry();
        this.zzcgi = zzm.zziw().zztw();
        this.zzcgj = zzi.zzcl(context);
        this.zzcgm = locale.getLanguage();
        this.zzcgn = zza(packageManager);
        Resources resources = context.getResources();
        if (resources != null) {
            DisplayMetrics displayMetrics = resources.getDisplayMetrics();
            if (displayMetrics != null) {
                this.zzcbd = displayMetrics.density;
                this.zzcbb = displayMetrics.widthPixels;
                this.zzcbc = displayMetrics.heightPixels;
            }
        }
    }

    public zziv$zza(Context context, zziv com_google_android_gms_internal_zziv) {
        PackageManager packageManager = context.getPackageManager();
        zzv(context);
        zza(context, packageManager);
        zzw(context);
        zzx(context);
        this.zzcge = com_google_android_gms_internal_zziv.zzcge;
        this.zzcgf = com_google_android_gms_internal_zziv.zzcgf;
        this.zzcgh = com_google_android_gms_internal_zziv.zzcgh;
        this.zzcgi = com_google_android_gms_internal_zziv.zzcgi;
        this.zzcgj = com_google_android_gms_internal_zziv.zzcgj;
        this.zzcgm = com_google_android_gms_internal_zziv.zzcgm;
        this.zzcgn = com_google_android_gms_internal_zziv.zzcgn;
        this.zzcbd = com_google_android_gms_internal_zziv.zzcbd;
        this.zzcbb = com_google_android_gms_internal_zziv.zzcbb;
        this.zzcbc = com_google_android_gms_internal_zziv.zzcbc;
    }

    private static ResolveInfo zza(PackageManager packageManager, String str) {
        return packageManager.resolveActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)), 65536);
    }

    private static String zza(PackageManager packageManager) {
        String str = null;
        ResolveInfo zza = zza(packageManager, "market://details?id=com.google.android.gms.ads");
        if (zza != null) {
            ActivityInfo activityInfo = zza.activityInfo;
            if (activityInfo != null) {
                try {
                    PackageInfo packageInfo = packageManager.getPackageInfo(activityInfo.packageName, 0);
                    if (packageInfo != null) {
                        int i = packageInfo.versionCode;
                        String valueOf = String.valueOf(activityInfo.packageName);
                        str = new StringBuilder(String.valueOf(valueOf).length() + 12).append(i).append(FileUtils.HIDDEN_PREFIX).append(valueOf).toString();
                    }
                } catch (NameNotFoundException e) {
                }
            }
        }
        return str;
    }

    @TargetApi(16)
    private void zza(Context context, PackageManager packageManager) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(PlaceFields.PHONE);
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        this.zzcgg = telephonyManager.getNetworkOperator();
        this.zzcgq = telephonyManager.getNetworkType();
        this.zzcgr = telephonyManager.getPhoneType();
        this.zzcgp = -2;
        this.zzcgw = false;
        this.zzcgx = -1;
        if (zzu.zzfq().zza(packageManager, context.getPackageName(), "android.permission.ACCESS_NETWORK_STATE")) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                this.zzcgp = activeNetworkInfo.getType();
                this.zzcgx = activeNetworkInfo.getDetailedState().ordinal();
            } else {
                this.zzcgp = -1;
            }
            if (VERSION.SDK_INT >= 16) {
                this.zzcgw = connectivityManager.isActiveNetworkMetered();
            }
        }
    }

    private void zzv(Context context) {
        AudioManager zzak = zzu.zzfq().zzak(context);
        if (zzak != null) {
            try {
                this.zzcgd = zzak.getMode();
                this.zzcgk = zzak.isMusicActive();
                this.zzcgl = zzak.isSpeakerphoneOn();
                this.zzcgo = zzak.getStreamVolume(3);
                this.zzcgs = zzak.getRingerMode();
                this.zzcgt = zzak.getStreamVolume(2);
                return;
            } catch (Throwable th) {
                zzu.zzft().zzb(th, true);
            }
        }
        this.zzcgd = -2;
        this.zzcgk = false;
        this.zzcgl = false;
        this.zzcgo = 0;
        this.zzcgs = 0;
        this.zzcgt = 0;
    }

    private void zzw(Context context) {
        boolean z = false;
        Intent registerReceiver = context.registerReceiver(null, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        if (registerReceiver != null) {
            int intExtra = registerReceiver.getIntExtra("status", -1);
            this.zzcgu = (double) (((float) registerReceiver.getIntExtra("level", -1)) / ((float) registerReceiver.getIntExtra("scale", -1)));
            if (intExtra == 2 || intExtra == 5) {
                z = true;
            }
            this.zzcgv = z;
            return;
        }
        this.zzcgu = -1.0d;
        this.zzcgv = false;
    }

    private void zzx(Context context) {
        this.zzcgy = Build.FINGERPRINT;
        this.zzcgz = zzdq.zzo(context);
    }

    public zziv zzrn() {
        return new zziv(this.zzcgd, this.zzcge, this.zzcgf, this.zzcgg, this.zzcgh, this.zzcgi, this.zzcgj, this.zzcgk, this.zzcgl, this.zzcgm, this.zzcgn, this.zzcgo, this.zzcgp, this.zzcgq, this.zzcgr, this.zzcgs, this.zzcgt, this.zzcbd, this.zzcbb, this.zzcbc, this.zzcgu, this.zzcgv, this.zzcgw, this.zzcgx, this.zzcgy, this.zzcgz);
    }
}

package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsServiceConnection;
import android.support.customtabs.CustomTabsSession;
import java.util.List;

@zzin
public class zzdq implements zzaqc {
    @Nullable
    private CustomTabsSession zzbeo;
    @Nullable
    private CustomTabsClient zzbep;
    @Nullable
    private CustomTabsServiceConnection zzbeq;
    @Nullable
    private zza zzber;

    public static boolean zzo(Context context) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return false;
        }
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.example.com"));
        ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 0);
        List queryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
        if (queryIntentActivities == null || resolveActivity == null) {
            return false;
        }
        for (int i = 0; i < queryIntentActivities.size(); i++) {
            if (resolveActivity.activityInfo.name.equals(((ResolveInfo) queryIntentActivities.get(i)).activityInfo.name)) {
                return resolveActivity.activityInfo.packageName.equals(zzaqa.zzex(context));
            }
        }
        return false;
    }

    public boolean mayLaunchUrl(Uri uri, Bundle bundle, List<Bundle> list) {
        if (this.zzbep == null) {
            return false;
        }
        CustomTabsSession zzkl = zzkl();
        return zzkl != null ? zzkl.mayLaunchUrl(uri, bundle, list) : false;
    }

    public void zza(CustomTabsClient customTabsClient) {
        this.zzbep = customTabsClient;
        this.zzbep.warmup(0);
        if (this.zzber != null) {
            this.zzber.zzkn();
        }
    }

    public void zza(zza com_google_android_gms_internal_zzdq_zza) {
        this.zzber = com_google_android_gms_internal_zzdq_zza;
    }

    public void zzd(Activity activity) {
        if (this.zzbeq != null) {
            activity.unbindService(this.zzbeq);
            this.zzbep = null;
            this.zzbeo = null;
            this.zzbeq = null;
        }
    }

    public void zze(Activity activity) {
        if (this.zzbep == null) {
            String zzex = zzaqa.zzex(activity);
            if (zzex != null) {
                this.zzbeq = new zzaqb(this);
                CustomTabsClient.bindCustomTabsService(activity, zzex, this.zzbeq);
            }
        }
    }

    @Nullable
    public CustomTabsSession zzkl() {
        if (this.zzbep == null) {
            this.zzbeo = null;
        } else if (this.zzbeo == null) {
            this.zzbeo = this.zzbep.newSession(null);
        }
        return this.zzbeo;
    }

    public void zzkm() {
        this.zzbep = null;
        this.zzbeo = null;
        if (this.zzber != null) {
            this.zzber.zzko();
        }
    }
}

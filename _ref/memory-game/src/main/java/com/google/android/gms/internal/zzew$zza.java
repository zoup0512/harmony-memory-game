package com.google.android.gms.internal;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzu;
import com.mopub.common.Constants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class zzew$zza {
    private final zzlh zzbgf;

    public zzew$zza(zzlh com_google_android_gms_internal_zzlh) {
        this.zzbgf = com_google_android_gms_internal_zzlh;
    }

    public Intent zza(Context context, Map<String, String> map) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        String str = (String) map.get("u");
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (this.zzbgf != null) {
            str = zzu.zzfq().zza(this.zzbgf, str);
        }
        Uri parse = Uri.parse(str);
        boolean parseBoolean = Boolean.parseBoolean((String) map.get("use_first_package"));
        boolean parseBoolean2 = Boolean.parseBoolean((String) map.get("use_running_process"));
        Uri build = Constants.HTTP.equalsIgnoreCase(parse.getScheme()) ? parse.buildUpon().scheme(Constants.HTTPS).build() : Constants.HTTPS.equalsIgnoreCase(parse.getScheme()) ? parse.buildUpon().scheme(Constants.HTTP).build() : null;
        ArrayList arrayList = new ArrayList();
        Intent zze = zze(parse);
        Intent zze2 = zze(build);
        ResolveInfo zza = zza(context, zze, arrayList);
        if (zza != null) {
            return zza(zze, zza);
        }
        if (zze2 != null) {
            ResolveInfo zza2 = zza(context, zze2);
            if (zza2 != null) {
                Intent zza3 = zza(zze, zza2);
                if (zza(context, zza3) != null) {
                    return zza3;
                }
            }
        }
        if (arrayList.size() == 0) {
            return zze;
        }
        if (parseBoolean2 && activityManager != null) {
            List<RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
            if (runningAppProcesses != null) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    ResolveInfo resolveInfo = (ResolveInfo) it.next();
                    for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                        if (runningAppProcessInfo.processName.equals(resolveInfo.activityInfo.packageName)) {
                            return zza(zze, resolveInfo);
                        }
                    }
                }
            }
        }
        return parseBoolean ? zza(zze, (ResolveInfo) arrayList.get(0)) : zze;
    }

    public Intent zza(Intent intent, ResolveInfo resolveInfo) {
        Intent intent2 = new Intent(intent);
        intent2.setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
        return intent2;
    }

    public ResolveInfo zza(Context context, Intent intent) {
        return zza(context, intent, new ArrayList());
    }

    public ResolveInfo zza(Context context, Intent intent, ArrayList<ResolveInfo> arrayList) {
        PackageManager packageManager = context.getPackageManager();
        if (packageManager == null) {
            return null;
        }
        ResolveInfo resolveInfo;
        Collection queryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
        ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 65536);
        if (!(queryIntentActivities == null || resolveActivity == null)) {
            for (int i = 0; i < queryIntentActivities.size(); i++) {
                resolveInfo = (ResolveInfo) queryIntentActivities.get(i);
                if (resolveActivity != null && resolveActivity.activityInfo.name.equals(resolveInfo.activityInfo.name)) {
                    resolveInfo = resolveActivity;
                    break;
                }
            }
        }
        resolveInfo = null;
        arrayList.addAll(queryIntentActivities);
        return resolveInfo;
    }

    public Intent zze(Uri uri) {
        if (uri == null) {
            return null;
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(268435456);
        intent.setData(uri);
        intent.setAction("android.intent.action.VIEW");
        return intent;
    }
}

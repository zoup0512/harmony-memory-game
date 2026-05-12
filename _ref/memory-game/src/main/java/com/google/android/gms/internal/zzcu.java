package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Environment;
import com.google.android.gms.common.internal.zzab;

@zzin
public class zzcu {
    private final Context mContext;

    public zzcu(Context context) {
        zzab.zzb(context, "Context can not be null");
        this.mContext = context;
    }

    public static boolean zzjt() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    public boolean zza(Intent intent) {
        zzab.zzb(intent, "Intent can not be null");
        return !this.mContext.getPackageManager().queryIntentActivities(intent, 0).isEmpty();
    }

    public boolean zzjp() {
        Intent intent = new Intent("android.intent.action.DIAL");
        intent.setData(Uri.parse("tel:"));
        return zza(intent);
    }

    public boolean zzjq() {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setData(Uri.parse("sms:"));
        return zza(intent);
    }

    public boolean zzjr() {
        return zzjt() && this.mContext.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }

    public boolean zzjs() {
        return true;
    }

    @TargetApi(14)
    public boolean zzju() {
        return VERSION.SDK_INT >= 14 && zza(new Intent("android.intent.action.INSERT").setType("vnd.android.cursor.dir/event"));
    }
}

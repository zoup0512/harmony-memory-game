package com.google.android.gms.ads.internal.purchase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.google.android.gms.internal.zzkd;

public class zzh$zza extends SQLiteOpenHelper {
    final /* synthetic */ zzh zzbxn;

    public zzh$zza(zzh com_google_android_gms_ads_internal_purchase_zzh, Context context, String str) {
        this.zzbxn = com_google_android_gms_ads_internal_purchase_zzh;
        super(context, str, null, 4);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(zzh.access$000());
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        zzkd.zzcw("Database updated from version " + i + " to version " + i2);
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS InAppPurchase");
        onCreate(sQLiteDatabase);
    }
}

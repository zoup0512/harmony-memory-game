package com.google.android.gms.ads.internal.purchase;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import com.cube.memorygames.billing.IabHelper;
import com.google.android.gms.internal.zzin;

@zzin
public class zzb {
    private final Context mContext;
    Object zzbwp;
    private final boolean zzbwq;

    public zzb(Context context) {
        this(context, true);
    }

    public zzb(Context context, boolean z) {
        this.mContext = context;
        this.zzbwq = z;
    }

    public void destroy() {
        this.zzbwp = null;
    }

    public void zzas(IBinder iBinder) {
        try {
            this.zzbwp = this.mContext.getClassLoader().loadClass("com.android.vending.billing.IInAppBillingService$Stub").getDeclaredMethod("asInterface", new Class[]{IBinder.class}).invoke(null, new Object[]{iBinder});
        } catch (Exception e) {
            if (this.zzbwq) {
                com.google.android.gms.ads.internal.util.client.zzb.zzcx("IInAppBillingService is not available, please add com.android.vending.billing.IInAppBillingService to project.");
            }
        }
    }

    public int zzb(int i, String str, String str2) {
        try {
            Class loadClass = this.mContext.getClassLoader().loadClass("com.android.vending.billing.IInAppBillingService");
            return ((Integer) loadClass.getDeclaredMethod("isBillingSupported", new Class[]{Integer.TYPE, String.class, String.class}).invoke(loadClass.cast(this.zzbwp), new Object[]{Integer.valueOf(i), str, str2})).intValue();
        } catch (Throwable e) {
            if (this.zzbwq) {
                com.google.android.gms.ads.internal.util.client.zzb.zzd("IInAppBillingService is not available, please add com.android.vending.billing.IInAppBillingService to project.", e);
            }
            return 5;
        }
    }

    public Bundle zzb(String str, String str2, String str3) {
        try {
            Class loadClass = this.mContext.getClassLoader().loadClass("com.android.vending.billing.IInAppBillingService");
            return (Bundle) loadClass.getDeclaredMethod("getBuyIntent", new Class[]{Integer.TYPE, String.class, String.class, String.class, String.class}).invoke(loadClass.cast(this.zzbwp), new Object[]{Integer.valueOf(3), str, str2, IabHelper.ITEM_TYPE_INAPP, str3});
        } catch (Throwable e) {
            if (this.zzbwq) {
                com.google.android.gms.ads.internal.util.client.zzb.zzd("IInAppBillingService is not available, please add com.android.vending.billing.IInAppBillingService to project.", e);
            }
            return null;
        }
    }

    public int zzm(String str, String str2) {
        try {
            Class loadClass = this.mContext.getClassLoader().loadClass("com.android.vending.billing.IInAppBillingService");
            return ((Integer) loadClass.getDeclaredMethod("consumePurchase", new Class[]{Integer.TYPE, String.class, String.class}).invoke(loadClass.cast(this.zzbwp), new Object[]{Integer.valueOf(3), str, str2})).intValue();
        } catch (Throwable e) {
            if (this.zzbwq) {
                com.google.android.gms.ads.internal.util.client.zzb.zzd("IInAppBillingService is not available, please add com.android.vending.billing.IInAppBillingService to project.", e);
            }
            return 5;
        }
    }

    public Bundle zzn(String str, String str2) {
        try {
            Class loadClass = this.mContext.getClassLoader().loadClass("com.android.vending.billing.IInAppBillingService");
            return (Bundle) loadClass.getDeclaredMethod("getPurchases", new Class[]{Integer.TYPE, String.class, String.class, String.class}).invoke(loadClass.cast(this.zzbwp), new Object[]{Integer.valueOf(3), str, IabHelper.ITEM_TYPE_INAPP, str2});
        } catch (Throwable e) {
            if (this.zzbwq) {
                com.google.android.gms.ads.internal.util.client.zzb.zzd("IInAppBillingService is not available, please add com.android.vending.billing.IInAppBillingService to project.", e);
            }
            return null;
        }
    }
}

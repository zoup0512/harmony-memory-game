package com.google.android.gms.ads.internal.purchase;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import com.cube.memorygames.billing.IabHelper;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.ads.purchase.InAppPurchaseActivity;
import com.google.android.gms.internal.zzin;
import org.json.JSONException;
import org.json.JSONObject;

@zzin
public class zzi {
    public void zza(Context context, boolean z, GInAppPurchaseManagerInfoParcel gInAppPurchaseManagerInfoParcel) {
        Intent intent = new Intent();
        intent.setClassName(context, InAppPurchaseActivity.CLASS_NAME);
        intent.putExtra("com.google.android.gms.ads.internal.purchase.useClientJar", z);
        GInAppPurchaseManagerInfoParcel.zza(intent, gInAppPurchaseManagerInfoParcel);
        zzu.zzfq().zzb(context, intent);
    }

    public String zzby(String str) {
        String str2 = null;
        if (str != null) {
            try {
                str2 = new JSONObject(str).getString("developerPayload");
            } catch (JSONException e) {
                zzb.zzcx("Fail to parse purchase data");
            }
        }
        return str2;
    }

    public String zzbz(String str) {
        String str2 = null;
        if (str != null) {
            try {
                str2 = new JSONObject(str).getString("purchaseToken");
            } catch (JSONException e) {
                zzb.zzcx("Fail to parse purchase data");
            }
        }
        return str2;
    }

    public int zzd(Intent intent) {
        if (intent == null) {
            return 5;
        }
        Object obj = intent.getExtras().get(IabHelper.RESPONSE_CODE);
        if (obj == null) {
            zzb.zzcx("Intent with no response code, assuming OK (known issue)");
            return 0;
        } else if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        } else {
            if (obj instanceof Long) {
                return (int) ((Long) obj).longValue();
            }
            String str = "Unexpected type for intent response code. ";
            String valueOf = String.valueOf(obj.getClass().getName());
            zzb.zzcx(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            return 5;
        }
    }

    public int zze(Bundle bundle) {
        Object obj = bundle.get(IabHelper.RESPONSE_CODE);
        if (obj == null) {
            zzb.zzcx("Bundle with null response code, assuming OK (known issue)");
            return 0;
        } else if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        } else {
            if (obj instanceof Long) {
                return (int) ((Long) obj).longValue();
            }
            String str = "Unexpected type for intent response code. ";
            String valueOf = String.valueOf(obj.getClass().getName());
            zzb.zzcx(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            return 5;
        }
    }

    public String zze(Intent intent) {
        return intent == null ? null : intent.getStringExtra(IabHelper.RESPONSE_INAPP_PURCHASE_DATA);
    }

    public String zzf(Intent intent) {
        return intent == null ? null : intent.getStringExtra(IabHelper.RESPONSE_INAPP_SIGNATURE);
    }

    public void zzt(Context context) {
        ServiceConnection 1 = new 1(this, context);
        Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        intent.setPackage("com.android.vending");
        context.bindService(intent, 1, 1);
    }
}

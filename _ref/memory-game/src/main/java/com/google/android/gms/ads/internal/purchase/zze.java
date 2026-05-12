package com.google.android.gms.ads.internal.purchase;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.cube.memorygames.billing.IabHelper;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.internal.zzhn;
import com.google.android.gms.internal.zzhp.zza;
import com.google.android.gms.internal.zzin;

@zzin
public class zze extends zza implements ServiceConnection {
    private final Activity mActivity;
    private zzb zzbws;
    zzh zzbwt;
    private zzk zzbwv;
    private Context zzbxa;
    private zzhn zzbxb;
    private zzf zzbxc;
    private zzj zzbxd;
    private String zzbxe = null;

    public zze(Activity activity) {
        this.mActivity = activity;
        this.zzbwt = zzh.zzs(this.mActivity.getApplicationContext());
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 1001) {
            boolean z = false;
            try {
                int zzd = zzu.zzga().zzd(intent);
                if (i2 == -1) {
                    zzu.zzga();
                    if (zzd == 0) {
                        if (this.zzbwv.zza(this.zzbxe, i2, intent)) {
                            z = true;
                        }
                        this.zzbxb.recordPlayBillingResolution(zzd);
                        this.mActivity.finish();
                        zza(this.zzbxb.getProductId(), z, i2, intent);
                    }
                }
                this.zzbwt.zza(this.zzbxc);
                this.zzbxb.recordPlayBillingResolution(zzd);
                this.mActivity.finish();
                zza(this.zzbxb.getProductId(), z, i2, intent);
            } catch (RemoteException e) {
                zzb.zzcx("Fail to process purchase result.");
                this.mActivity.finish();
            } finally {
                this.zzbxe = null;
            }
        }
    }

    public void onCreate() {
        GInAppPurchaseManagerInfoParcel zzc = GInAppPurchaseManagerInfoParcel.zzc(this.mActivity.getIntent());
        this.zzbxd = zzc.zzbwo;
        this.zzbwv = zzc.zzapt;
        this.zzbxb = zzc.zzbwm;
        this.zzbws = new zzb(this.mActivity.getApplicationContext());
        this.zzbxa = zzc.zzbwn;
        if (this.mActivity.getResources().getConfiguration().orientation == 2) {
            this.mActivity.setRequestedOrientation(zzu.zzfs().zztj());
        } else {
            this.mActivity.setRequestedOrientation(zzu.zzfs().zztk());
        }
        Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        intent.setPackage("com.android.vending");
        this.mActivity.bindService(intent, this, 1);
    }

    public void onDestroy() {
        this.mActivity.unbindService(this);
        this.zzbws.destroy();
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        Throwable e;
        this.zzbws.zzas(iBinder);
        try {
            this.zzbxe = this.zzbwv.zzpu();
            Bundle zzb = this.zzbws.zzb(this.mActivity.getPackageName(), this.zzbxb.getProductId(), this.zzbxe);
            PendingIntent pendingIntent = (PendingIntent) zzb.getParcelable(IabHelper.RESPONSE_BUY_INTENT);
            if (pendingIntent == null) {
                int zze = zzu.zzga().zze(zzb);
                this.zzbxb.recordPlayBillingResolution(zze);
                zza(this.zzbxb.getProductId(), false, zze, null);
                this.mActivity.finish();
                return;
            }
            this.zzbxc = new zzf(this.zzbxb.getProductId(), this.zzbxe);
            this.zzbwt.zzb(this.zzbxc);
            this.mActivity.startIntentSenderForResult(pendingIntent.getIntentSender(), 1001, new Intent(), Integer.valueOf(0).intValue(), Integer.valueOf(0).intValue(), Integer.valueOf(0).intValue());
        } catch (RemoteException e2) {
            e = e2;
            zzb.zzd("Error when connecting in-app billing service", e);
            this.mActivity.finish();
        } catch (SendIntentException e3) {
            e = e3;
            zzb.zzd("Error when connecting in-app billing service", e);
            this.mActivity.finish();
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        zzb.zzcw("In-app billing service disconnected.");
        this.zzbws.destroy();
    }

    protected void zza(String str, boolean z, int i, Intent intent) {
        if (this.zzbxd != null) {
            this.zzbxd.zza(str, z, i, intent, this.zzbxc);
        }
    }
}

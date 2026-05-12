package com.google.android.gms.gass.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.HandlerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzd.zzb;
import com.google.android.gms.common.internal.zzd.zzc;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class zza {

    static class zza implements zzb, zzc {
        protected zzb YS;
        private final String YT;
        private final LinkedBlockingQueue<com.google.android.gms.internal.zzae.zza> YU;
        private final HandlerThread YV = new HandlerThread("GassClient");
        private final String packageName;

        public zza(Context context, String str, String str2) {
            this.packageName = str;
            this.YT = str2;
            this.YV.start();
            this.YS = new zzb(context, this.YV.getLooper(), this, this);
            this.YU = new LinkedBlockingQueue();
            connect();
        }

        protected void connect() {
            this.YS.zzarx();
        }

        public void onConnected(Bundle bundle) {
            zze zzbla = zzbla();
            if (zzbla != null) {
                try {
                    this.YU.put(zzbla.zza(new GassRequestParcel(this.packageName, this.YT)).zzbld());
                } catch (Throwable th) {
                } finally {
                    zzqw();
                    this.YV.quit();
                }
            }
        }

        public void onConnectionFailed(ConnectionResult connectionResult) {
            try {
                this.YU.put(new com.google.android.gms.internal.zzae.zza());
            } catch (InterruptedException e) {
            }
        }

        public void onConnectionSuspended(int i) {
            try {
                this.YU.put(new com.google.android.gms.internal.zzae.zza());
            } catch (InterruptedException e) {
            }
        }

        protected zze zzbla() {
            try {
                return this.YS.zzblb();
            } catch (IllegalStateException e) {
                return null;
            } catch (DeadObjectException e2) {
                return null;
            }
        }

        public com.google.android.gms.internal.zzae.zza zzcl() {
            return zzsi(2000);
        }

        public void zzqw() {
            if (this.YS == null) {
                return;
            }
            if (this.YS.isConnected() || this.YS.isConnecting()) {
                this.YS.disconnect();
            }
        }

        public com.google.android.gms.internal.zzae.zza zzsi(int i) {
            com.google.android.gms.internal.zzae.zza com_google_android_gms_internal_zzae_zza;
            try {
                com_google_android_gms_internal_zzae_zza = (com.google.android.gms.internal.zzae.zza) this.YU.poll((long) i, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                com_google_android_gms_internal_zzae_zza = null;
            }
            return com_google_android_gms_internal_zzae_zza == null ? new com.google.android.gms.internal.zzae.zza() : com_google_android_gms_internal_zzae_zza;
        }
    }

    public static com.google.android.gms.internal.zzae.zza zzg(Context context, String str, String str2) {
        return new zza(context, str, str2).zzcl();
    }
}

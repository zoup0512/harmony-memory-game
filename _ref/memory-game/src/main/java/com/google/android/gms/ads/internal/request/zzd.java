package com.google.android.gms.ads.internal.request;

import android.content.Context;
import android.os.Binder;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.facebook.internal.NativeProtocol;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzd.zzc;
import com.google.android.gms.internal.zzcv;
import com.google.android.gms.internal.zzdc;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzio;
import com.google.android.gms.internal.zzip;
import com.google.android.gms.internal.zzkj;
import com.google.android.gms.internal.zzla;

@zzin
public abstract class zzd implements com.google.android.gms.ads.internal.request.zzc.zza, zzkj<Void> {
    private final Object zzail = new Object();
    private final zzla<AdRequestInfoParcel> zzcaj;
    private final com.google.android.gms.ads.internal.request.zzc.zza zzcak;

    @zzin
    public static final class zza extends zzd {
        private final Context mContext;

        public zza(Context context, zzla<AdRequestInfoParcel> com_google_android_gms_internal_zzla_com_google_android_gms_ads_internal_request_AdRequestInfoParcel, com.google.android.gms.ads.internal.request.zzc.zza com_google_android_gms_ads_internal_request_zzc_zza) {
            super(com_google_android_gms_internal_zzla_com_google_android_gms_ads_internal_request_AdRequestInfoParcel, com_google_android_gms_ads_internal_request_zzc_zza);
            this.mContext = context;
        }

        public /* synthetic */ Object zzpy() {
            return super.zzpv();
        }

        public void zzqw() {
        }

        public zzk zzqx() {
            return zzip.zza(this.mContext, new zzcv((String) zzdc.zzaxy.get()), zzio.zzrf());
        }
    }

    @zzin
    public static class zzb extends zzd implements com.google.android.gms.common.internal.zzd.zzb, zzc {
        private Context mContext;
        private final Object zzail = new Object();
        private VersionInfoParcel zzalo;
        private zzla<AdRequestInfoParcel> zzcaj;
        private final com.google.android.gms.ads.internal.request.zzc.zza zzcak;
        protected zze zzcan;
        private boolean zzcao;

        public zzb(Context context, VersionInfoParcel versionInfoParcel, zzla<AdRequestInfoParcel> com_google_android_gms_internal_zzla_com_google_android_gms_ads_internal_request_AdRequestInfoParcel, com.google.android.gms.ads.internal.request.zzc.zza com_google_android_gms_ads_internal_request_zzc_zza) {
            Looper zztq;
            super(com_google_android_gms_internal_zzla_com_google_android_gms_ads_internal_request_AdRequestInfoParcel, com_google_android_gms_ads_internal_request_zzc_zza);
            this.mContext = context;
            this.zzalo = versionInfoParcel;
            this.zzcaj = com_google_android_gms_internal_zzla_com_google_android_gms_ads_internal_request_AdRequestInfoParcel;
            this.zzcak = com_google_android_gms_ads_internal_request_zzc_zza;
            if (((Boolean) zzdc.zzayy.get()).booleanValue()) {
                this.zzcao = true;
                zztq = zzu.zzgc().zztq();
            } else {
                zztq = context.getMainLooper();
            }
            this.zzcan = new zze(context, zztq, this, this, this.zzalo.zzcnl);
            connect();
        }

        protected void connect() {
            this.zzcan.zzarx();
        }

        public void onConnected(Bundle bundle) {
            Void voidR = (Void) zzpy();
        }

        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            com.google.android.gms.ads.internal.util.client.zzb.zzcv("Cannot connect to remote service, fallback to local instance.");
            zzqy().zzpy();
            Bundle bundle = new Bundle();
            bundle.putString(NativeProtocol.WEB_DIALOG_ACTION, "gms_connection_failed_fallback_to_local");
            zzu.zzfq().zzb(this.mContext, this.zzalo.zzcs, "gmob-apps", bundle, true);
        }

        public void onConnectionSuspended(int i) {
            com.google.android.gms.ads.internal.util.client.zzb.zzcv("Disconnected from remote ad request service.");
        }

        public /* synthetic */ Object zzpy() {
            return super.zzpv();
        }

        public void zzqw() {
            synchronized (this.zzail) {
                if (this.zzcan.isConnected() || this.zzcan.isConnecting()) {
                    this.zzcan.disconnect();
                }
                Binder.flushPendingCommands();
                if (this.zzcao) {
                    zzu.zzgc().zztr();
                    this.zzcao = false;
                }
            }
        }

        public zzk zzqx() {
            zzk zzrb;
            synchronized (this.zzail) {
                try {
                    zzrb = this.zzcan.zzrb();
                } catch (IllegalStateException e) {
                    zzrb = null;
                    return zzrb;
                } catch (DeadObjectException e2) {
                    zzrb = null;
                    return zzrb;
                }
            }
            return zzrb;
        }

        zzkj zzqy() {
            return new zza(this.mContext, this.zzcaj, this.zzcak);
        }
    }

    public zzd(zzla<AdRequestInfoParcel> com_google_android_gms_internal_zzla_com_google_android_gms_ads_internal_request_AdRequestInfoParcel, com.google.android.gms.ads.internal.request.zzc.zza com_google_android_gms_ads_internal_request_zzc_zza) {
        this.zzcaj = com_google_android_gms_internal_zzla_com_google_android_gms_ads_internal_request_AdRequestInfoParcel;
        this.zzcak = com_google_android_gms_ads_internal_request_zzc_zza;
    }

    public void cancel() {
        zzqw();
    }

    boolean zza(zzk com_google_android_gms_ads_internal_request_zzk, AdRequestInfoParcel adRequestInfoParcel) {
        try {
            com_google_android_gms_ads_internal_request_zzk.zza(adRequestInfoParcel, new zzg(this));
            return true;
        } catch (Throwable e) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not fetch ad response from ad request service.", e);
            zzu.zzft().zzb(e, true);
        } catch (Throwable e2) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not fetch ad response from ad request service due to an Exception.", e2);
            zzu.zzft().zzb(e2, true);
        } catch (Throwable e22) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not fetch ad response from ad request service due to an Exception.", e22);
            zzu.zzft().zzb(e22, true);
        } catch (Throwable e222) {
            com.google.android.gms.ads.internal.util.client.zzb.zzd("Could not fetch ad response from ad request service due to an Exception.", e222);
            zzu.zzft().zzb(e222, true);
        }
        this.zzcak.zzb(new AdResponseParcel(0));
        return false;
    }

    public void zzb(AdResponseParcel adResponseParcel) {
        synchronized (this.zzail) {
            this.zzcak.zzb(adResponseParcel);
            zzqw();
        }
    }

    public Void zzpv() {
        zzk zzqx = zzqx();
        if (zzqx == null) {
            this.zzcak.zzb(new AdResponseParcel(0));
            zzqw();
        } else {
            this.zzcaj.zza(new 1(this, zzqx), new 2(this));
        }
        return null;
    }

    public /* synthetic */ Object zzpy() {
        return zzpv();
    }

    public abstract void zzqw();

    public abstract zzk zzqx();
}

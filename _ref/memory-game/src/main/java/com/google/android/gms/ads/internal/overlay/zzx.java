package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.TextureView;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.internal.zzdc;
import com.google.android.gms.internal.zzdg;
import com.google.android.gms.internal.zzdi;
import com.google.android.gms.internal.zzdk;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzkm;
import com.google.android.gms.internal.zzkm.zza;
import com.google.android.gms.internal.zzkm.zzb;
import java.util.concurrent.TimeUnit;

@zzin
public class zzx {
    private final Context mContext;
    private final VersionInfoParcel zzamw;
    private final String zzbvq;
    @Nullable
    private final zzdi zzbvr;
    @Nullable
    private final zzdk zzbvs;
    private final zzkm zzbvt = new zzb().zza("min_1", Double.MIN_VALUE, 1.0d).zza("1_5", 1.0d, 5.0d).zza("5_10", 5.0d, 10.0d).zza("10_20", 10.0d, 20.0d).zza("20_30", 20.0d, 30.0d).zza("30_max", 30.0d, Double.MAX_VALUE).zzto();
    private final long[] zzbvu;
    private final String[] zzbvv;
    @Nullable
    private zzdi zzbvw;
    @Nullable
    private zzdi zzbvx;
    @Nullable
    private zzdi zzbvy;
    @Nullable
    private zzdi zzbvz;
    private boolean zzbwa;
    private zzi zzbwb;
    private boolean zzbwc;
    private boolean zzbwd;
    private long zzbwe = -1;

    public zzx(Context context, VersionInfoParcel versionInfoParcel, String str, @Nullable zzdk com_google_android_gms_internal_zzdk, @Nullable zzdi com_google_android_gms_internal_zzdi) {
        this.mContext = context;
        this.zzamw = versionInfoParcel;
        this.zzbvq = str;
        this.zzbvs = com_google_android_gms_internal_zzdk;
        this.zzbvr = com_google_android_gms_internal_zzdi;
        String str2 = (String) zzdc.zzayt.get();
        if (str2 == null) {
            this.zzbvv = new String[0];
            this.zzbvu = new long[0];
            return;
        }
        String[] split = TextUtils.split(str2, ",");
        this.zzbvv = new String[split.length];
        this.zzbvu = new long[split.length];
        for (int i = 0; i < split.length; i++) {
            try {
                this.zzbvu[i] = Long.parseLong(split[i]);
            } catch (Throwable e) {
                com.google.android.gms.ads.internal.util.client.zzb.zzd("Unable to parse frame hash target time number.", e);
                this.zzbvu[i] = -1;
            }
        }
    }

    private void zzc(zzi com_google_android_gms_ads_internal_overlay_zzi) {
        long longValue = ((Long) zzdc.zzayu.get()).longValue();
        long currentPosition = (long) com_google_android_gms_ads_internal_overlay_zzi.getCurrentPosition();
        int i = 0;
        while (i < this.zzbvv.length) {
            if (this.zzbvv[i] == null && longValue > Math.abs(currentPosition - this.zzbvu[i])) {
                this.zzbvv[i] = zza((TextureView) com_google_android_gms_ads_internal_overlay_zzi);
                return;
            }
            i++;
        }
    }

    private void zzph() {
        if (this.zzbvy != null && this.zzbvz == null) {
            zzdg.zza(this.zzbvs, this.zzbvy, "vff");
            zzdg.zza(this.zzbvs, this.zzbvr, "vtt");
            this.zzbvz = zzdg.zzb(this.zzbvs);
        }
        long nanoTime = zzu.zzfu().nanoTime();
        if (this.zzbwa && this.zzbwd && this.zzbwe != -1) {
            this.zzbvt.zza(((double) TimeUnit.SECONDS.toNanos(1)) / ((double) (nanoTime - this.zzbwe)));
        }
        this.zzbwd = this.zzbwa;
        this.zzbwe = nanoTime;
    }

    public void onStop() {
        if (((Boolean) zzdc.zzays.get()).booleanValue() && !this.zzbwc) {
            String valueOf;
            String valueOf2;
            Bundle bundle = new Bundle();
            bundle.putString("type", "native-player-metrics");
            bundle.putString(ShareConstants.WEB_DIALOG_RESULT_PARAM_REQUEST_ID, this.zzbvq);
            bundle.putString("player", this.zzbwb.zzni());
            for (zza com_google_android_gms_internal_zzkm_zza : this.zzbvt.getBuckets()) {
                valueOf = String.valueOf("fps_c_");
                valueOf2 = String.valueOf(com_google_android_gms_internal_zzkm_zza.name);
                bundle.putString(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf), Integer.toString(com_google_android_gms_internal_zzkm_zza.count));
                valueOf = String.valueOf("fps_p_");
                valueOf2 = String.valueOf(com_google_android_gms_internal_zzkm_zza.name);
                bundle.putString(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf), Double.toString(com_google_android_gms_internal_zzkm_zza.zzcly));
            }
            for (int i = 0; i < this.zzbvu.length; i++) {
                valueOf2 = this.zzbvv[i];
                if (valueOf2 != null) {
                    String valueOf3 = String.valueOf("fh_");
                    valueOf = String.valueOf(Long.valueOf(this.zzbvu[i]));
                    bundle.putString(new StringBuilder((String.valueOf(valueOf3).length() + 0) + String.valueOf(valueOf).length()).append(valueOf3).append(valueOf).toString(), valueOf2);
                }
            }
            zzu.zzfq().zza(this.mContext, this.zzamw.zzcs, "gmob-apps", bundle, true);
            this.zzbwc = true;
        }
    }

    String zza(TextureView textureView) {
        Bitmap bitmap = textureView.getBitmap(8, 8);
        long j = 0;
        long j2 = 63;
        int i = 0;
        while (i < 8) {
            long j3 = j;
            j = j2;
            for (int i2 = 0; i2 < 8; i2++) {
                int pixel = bitmap.getPixel(i2, i);
                j3 |= (Color.green(pixel) + (Color.blue(pixel) + Color.red(pixel)) > 128 ? 1 : 0) << ((int) j);
                j--;
            }
            i++;
            j2 = j;
            j = j3;
        }
        return String.format("%016X", new Object[]{Long.valueOf(j)});
    }

    public void zza(zzi com_google_android_gms_ads_internal_overlay_zzi) {
        zzdg.zza(this.zzbvs, this.zzbvr, "vpc");
        this.zzbvw = zzdg.zzb(this.zzbvs);
        if (this.zzbvs != null) {
            this.zzbvs.zzh("vpn", com_google_android_gms_ads_internal_overlay_zzi.zzni());
        }
        this.zzbwb = com_google_android_gms_ads_internal_overlay_zzi;
    }

    public void zzb(zzi com_google_android_gms_ads_internal_overlay_zzi) {
        zzph();
        zzc(com_google_android_gms_ads_internal_overlay_zzi);
    }

    public void zzoj() {
        if (this.zzbvw != null && this.zzbvx == null) {
            zzdg.zza(this.zzbvs, this.zzbvw, "vfr");
            this.zzbvx = zzdg.zzb(this.zzbvs);
        }
    }

    public void zzpi() {
        this.zzbwa = true;
        if (this.zzbvx != null && this.zzbvy == null) {
            zzdg.zza(this.zzbvs, this.zzbvx, "vfp");
            this.zzbvy = zzdg.zzb(this.zzbvs);
        }
    }

    public void zzpj() {
        this.zzbwa = false;
    }
}

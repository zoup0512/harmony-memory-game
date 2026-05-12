package com.google.android.gms.internal;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import com.google.android.gms.ads.internal.formats.zzc;
import com.google.android.gms.common.util.zzo;
import java.io.IOException;
import java.io.InputStream;

class zzii$6 implements zzkn$zza<zzc> {
    final /* synthetic */ String zzbqu;
    final /* synthetic */ zzii zzbzk;
    final /* synthetic */ boolean zzbzx;
    final /* synthetic */ double zzbzy;

    zzii$6(zzii com_google_android_gms_internal_zzii, boolean z, double d, String str) {
        this.zzbzk = com_google_android_gms_internal_zzii;
        this.zzbzx = z;
        this.zzbzy = d;
        this.zzbqu = str;
    }

    public zzc zzg(InputStream inputStream) {
        byte[] zzk;
        try {
            zzk = zzo.zzk(inputStream);
        } catch (IOException e) {
            zzk = null;
        }
        if (zzk == null) {
            this.zzbzk.zza(2, this.zzbzx);
            return null;
        }
        Bitmap decodeByteArray = BitmapFactory.decodeByteArray(zzk, 0, zzk.length);
        if (decodeByteArray == null) {
            this.zzbzk.zza(2, this.zzbzx);
            return null;
        }
        decodeByteArray.setDensity((int) (160.0d * this.zzbzy));
        return new zzc(new BitmapDrawable(Resources.getSystem(), decodeByteArray), Uri.parse(this.zzbqu), this.zzbzy);
    }

    public /* synthetic */ Object zzh(InputStream inputStream) {
        return zzg(inputStream);
    }

    public zzc zzqt() {
        this.zzbzk.zza(2, this.zzbzx);
        return null;
    }

    public /* synthetic */ Object zzqu() {
        return zzqt();
    }
}

package com.google.android.gms.internal;

import android.os.Parcel;
import android.util.Base64;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@zzin
class zzfo {
    final String zzaln;
    final AdRequestParcel zzanc;
    final int zzbkt;

    zzfo(AdRequestParcel adRequestParcel, String str, int i) {
        this.zzanc = adRequestParcel;
        this.zzaln = str;
        this.zzbkt = i;
    }

    zzfo(zzfm com_google_android_gms_internal_zzfm) {
        this(com_google_android_gms_internal_zzfm.zzlq(), com_google_android_gms_internal_zzfm.getAdUnitId(), com_google_android_gms_internal_zzfm.getNetworkType());
    }

    zzfo(String str) throws IOException {
        String[] split = str.split("\u0000");
        if (split.length != 3) {
            throw new IOException("Incorrect field count for QueueSeed.");
        }
        Parcel obtain = Parcel.obtain();
        try {
            this.zzaln = new String(Base64.decode(split[0], 0), "UTF-8");
            this.zzbkt = Integer.parseInt(split[1]);
            byte[] decode = Base64.decode(split[2], 0);
            obtain.unmarshall(decode, 0, decode.length);
            obtain.setDataPosition(0);
            this.zzanc = (AdRequestParcel) AdRequestParcel.CREATOR.createFromParcel(obtain);
            obtain.recycle();
        } catch (Throwable th) {
            obtain.recycle();
        }
    }

    String zzlx() {
        Parcel obtain = Parcel.obtain();
        String encodeToString;
        try {
            encodeToString = Base64.encodeToString(this.zzaln.getBytes("UTF-8"), 0);
            String num = Integer.toString(this.zzbkt);
            this.zzanc.writeToParcel(obtain, 0);
            String encodeToString2 = Base64.encodeToString(obtain.marshall(), 0);
            encodeToString = new StringBuilder(((String.valueOf(encodeToString).length() + 2) + String.valueOf(num).length()) + String.valueOf(encodeToString2).length()).append(encodeToString).append("\u0000").append(num).append("\u0000").append(encodeToString2).toString();
            return encodeToString;
        } catch (UnsupportedEncodingException e) {
            encodeToString = "QueueSeed encode failed because UTF-8 is not available.";
            zzb.e(encodeToString);
            return "";
        } finally {
            obtain.recycle();
        }
    }
}

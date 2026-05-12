package com.google.android.gms.internal;

import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.MotionEventCompat;
import java.security.MessageDigest;

@zzin
public class zzcs extends zzcp {
    private MessageDigest zzath;

    byte[] zza(String[] strArr) {
        int i = 0;
        if (strArr.length == 1) {
            return zzcr.zzn(zzcr.zzac(strArr[0]));
        }
        if (strArr.length < 5) {
            byte[] bArr = new byte[(strArr.length * 2)];
            for (int i2 = 0; i2 < strArr.length; i2++) {
                byte[] zzq = zzq(zzcr.zzac(strArr[i2]));
                bArr[i2 * 2] = zzq[0];
                bArr[(i2 * 2) + 1] = zzq[1];
            }
            return bArr;
        }
        byte[] bArr2 = new byte[strArr.length];
        while (i < strArr.length) {
            bArr2[i] = zzp(zzcr.zzac(strArr[i]));
            i++;
        }
        return bArr2;
    }

    public byte[] zzaa(String str) {
        byte[] bArr;
        int i = 4;
        byte[] zza = zza(str.split(" "));
        this.zzath = zzie();
        synchronized (this.zzail) {
            if (this.zzath == null) {
                bArr = new byte[0];
            } else {
                this.zzath.reset();
                this.zzath.update(zza);
                Object digest = this.zzath.digest();
                if (digest.length <= 4) {
                    i = digest.length;
                }
                bArr = new byte[i];
                System.arraycopy(digest, 0, bArr, 0, bArr.length);
            }
        }
        return bArr;
    }

    byte zzp(int i) {
        return (byte) ((((i & 255) ^ ((MotionEventCompat.ACTION_POINTER_INDEX_MASK & i) >> 8)) ^ ((16711680 & i) >> 16)) ^ ((-16777216 & i) >> 24));
    }

    byte[] zzq(int i) {
        int i2 = (SupportMenu.USER_MASK & i) ^ ((SupportMenu.CATEGORY_MASK & i) >> 16);
        return new byte[]{(byte) i2, (byte) (i2 >> 8)};
    }
}

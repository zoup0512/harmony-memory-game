package com.google.android.gms.internal;

import android.util.Base64OutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

class zzcq$zza {
    ByteArrayOutputStream zzatf = new ByteArrayOutputStream(4096);
    Base64OutputStream zzatg = new Base64OutputStream(this.zzatf, 10);

    public String toString() {
        String byteArrayOutputStream;
        try {
            this.zzatg.close();
        } catch (Throwable e) {
            zzkd.zzb("HashManager: Unable to convert to Base64.", e);
        }
        try {
            this.zzatf.close();
            byteArrayOutputStream = this.zzatf.toString();
        } catch (Throwable e2) {
            zzkd.zzb("HashManager: Unable to convert to Base64.", e2);
            byteArrayOutputStream = "";
        } finally {
            this.zzatf = null;
            this.zzatg = null;
        }
        return byteArrayOutputStream;
    }

    public void write(byte[] bArr) throws IOException {
        this.zzatg.write(bArr);
    }
}

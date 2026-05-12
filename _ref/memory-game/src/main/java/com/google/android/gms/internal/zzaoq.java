package com.google.android.gms.internal;

public class zzaoq {
    private final byte[] bhE = new byte[256];
    private int bhF;
    private int bhG;

    public zzaoq(byte[] bArr) {
        int i;
        for (i = 0; i < 256; i++) {
            this.bhE[i] = (byte) i;
        }
        i = 0;
        for (int i2 = 0; i2 < 256; i2++) {
            i = ((i + this.bhE[i2]) + bArr[i2 % bArr.length]) & 255;
            byte b = this.bhE[i2];
            this.bhE[i2] = this.bhE[i];
            this.bhE[i] = b;
        }
        this.bhF = 0;
        this.bhG = 0;
    }

    public void zzax(byte[] bArr) {
        int i = this.bhF;
        int i2 = this.bhG;
        for (int i3 = 0; i3 < bArr.length; i3++) {
            i = (i + 1) & 255;
            i2 = (i2 + this.bhE[i]) & 255;
            byte b = this.bhE[i];
            this.bhE[i] = this.bhE[i2];
            this.bhE[i2] = b;
            bArr[i3] = (byte) (bArr[i3] ^ this.bhE[(this.bhE[i] + this.bhE[i2]) & 255]);
        }
        this.bhF = i;
        this.bhG = i2;
    }
}

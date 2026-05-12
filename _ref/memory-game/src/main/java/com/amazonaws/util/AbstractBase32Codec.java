package com.amazonaws.util;

abstract class AbstractBase32Codec implements Codec {
    private static final int MASK_2BITS = 3;
    private static final int MASK_3BITS = 7;
    private static final int MASK_4BITS = 15;
    private static final int MASK_5BITS = 31;
    private static final byte PAD = (byte) 61;
    private final byte[] ALPAHBETS;

    protected abstract int pos(byte b);

    protected AbstractBase32Codec(byte[] alphabets) {
        this.ALPAHBETS = alphabets;
    }

    public final byte[] encode(byte[] src) {
        int num5bytes = src.length / 5;
        int remainder = src.length % 5;
        byte[] dest;
        int s;
        int d;
        if (remainder == 0) {
            dest = new byte[(num5bytes * 8)];
            s = 0;
            d = 0;
            while (s < src.length) {
                encode5bytes(src, s, dest, d);
                s += 5;
                d += 8;
            }
            return dest;
        }
        dest = new byte[((num5bytes + 1) * 8)];
        s = 0;
        d = 0;
        while (s < src.length - remainder) {
            encode5bytes(src, s, dest, d);
            s += 5;
            d += 8;
        }
        switch (remainder) {
            case 1:
                encode1byte(src, s, dest, d);
                break;
            case 2:
                encode2bytes(src, s, dest, d);
                break;
            case 3:
                encode3bytes(src, s, dest, d);
                break;
            case 4:
                encode4bytes(src, s, dest, d);
                break;
        }
        return dest;
    }

    private final void encode5bytes(byte[] src, int s, byte[] dest, int d) {
        int i = d + 1;
        byte[] bArr = this.ALPAHBETS;
        int s2 = s + 1;
        byte p = src[s];
        dest[d] = bArr[(p >>> 3) & 31];
        d = i + 1;
        bArr = this.ALPAHBETS;
        int i2 = (p & 7) << 2;
        s = s2 + 1;
        p = src[s2];
        dest[i] = bArr[i2 | ((p >>> 6) & 3)];
        i = d + 1;
        dest[d] = this.ALPAHBETS[(p >>> 1) & 31];
        d = i + 1;
        bArr = this.ALPAHBETS;
        i2 = (p & 1) << 4;
        s2 = s + 1;
        p = src[s];
        dest[i] = bArr[i2 | ((p >>> 4) & 15)];
        i = d + 1;
        bArr = this.ALPAHBETS;
        i2 = (p & 15) << 1;
        s = s2 + 1;
        p = src[s2];
        dest[d] = bArr[i2 | ((p >>> 7) & 1)];
        d = i + 1;
        dest[i] = this.ALPAHBETS[(p >>> 2) & 31];
        i = d + 1;
        bArr = this.ALPAHBETS;
        i2 = (p & 3) << 3;
        p = src[s];
        dest[d] = bArr[i2 | ((p >>> 5) & 7)];
        dest[i] = this.ALPAHBETS[p & 31];
    }

    private final void encode4bytes(byte[] src, int s, byte[] dest, int d) {
        int i = d + 1;
        byte[] bArr = this.ALPAHBETS;
        int s2 = s + 1;
        byte p = src[s];
        dest[d] = bArr[(p >>> 3) & 31];
        d = i + 1;
        bArr = this.ALPAHBETS;
        int i2 = (p & 7) << 2;
        s = s2 + 1;
        p = src[s2];
        dest[i] = bArr[i2 | ((p >>> 6) & 3)];
        i = d + 1;
        dest[d] = this.ALPAHBETS[(p >>> 1) & 31];
        d = i + 1;
        bArr = this.ALPAHBETS;
        i2 = (p & 1) << 4;
        s2 = s + 1;
        p = src[s];
        dest[i] = bArr[i2 | ((p >>> 4) & 15)];
        i = d + 1;
        bArr = this.ALPAHBETS;
        i2 = (p & 15) << 1;
        p = src[s2];
        dest[d] = bArr[i2 | ((p >>> 7) & 1)];
        d = i + 1;
        dest[i] = this.ALPAHBETS[(p >>> 2) & 31];
        i = d + 1;
        dest[d] = this.ALPAHBETS[(p & 3) << 3];
        dest[i] = PAD;
    }

    private final void encode3bytes(byte[] src, int s, byte[] dest, int d) {
        int i = d + 1;
        byte[] bArr = this.ALPAHBETS;
        int s2 = s + 1;
        byte p = src[s];
        dest[d] = bArr[(p >>> 3) & 31];
        d = i + 1;
        bArr = this.ALPAHBETS;
        int i2 = (p & 7) << 2;
        s = s2 + 1;
        p = src[s2];
        dest[i] = bArr[i2 | ((p >>> 6) & 3)];
        i = d + 1;
        dest[d] = this.ALPAHBETS[(p >>> 1) & 31];
        d = i + 1;
        bArr = this.ALPAHBETS;
        i2 = (p & 1) << 4;
        p = src[s];
        dest[i] = bArr[i2 | ((p >>> 4) & 15)];
        i = d + 1;
        dest[d] = this.ALPAHBETS[(p & 15) << 1];
        int i3 = 0;
        while (i3 < 3) {
            d = i + 1;
            dest[i] = PAD;
            i3++;
            i = d;
        }
    }

    private final void encode2bytes(byte[] src, int s, byte[] dest, int d) {
        int i = d + 1;
        byte[] bArr = this.ALPAHBETS;
        int s2 = s + 1;
        byte p = src[s];
        dest[d] = bArr[(p >>> 3) & 31];
        d = i + 1;
        bArr = this.ALPAHBETS;
        int i2 = (p & 7) << 2;
        p = src[s2];
        dest[i] = bArr[i2 | ((p >>> 6) & 3)];
        i = d + 1;
        dest[d] = this.ALPAHBETS[(p >>> 1) & 31];
        d = i + 1;
        dest[i] = this.ALPAHBETS[(p & 1) << 4];
        int i3 = 0;
        i = d;
        while (i3 < 4) {
            d = i + 1;
            dest[i] = PAD;
            i3++;
            i = d;
        }
    }

    private final void encode1byte(byte[] src, int s, byte[] dest, int d) {
        int i = d + 1;
        byte[] bArr = this.ALPAHBETS;
        byte p = src[s];
        dest[d] = bArr[(p >>> 3) & 31];
        d = i + 1;
        dest[i] = this.ALPAHBETS[(p & 7) << 2];
        int i2 = 0;
        i = d;
        while (i2 < 6) {
            d = i + 1;
            dest[i] = PAD;
            i2++;
            i = d;
        }
    }

    private final void decode5bytes(byte[] src, int s, byte[] dest, int d) {
        int i = d + 1;
        int s2 = s + 1;
        int pos = pos(src[s]) << 3;
        s = s2 + 1;
        int p = pos(src[s2]);
        dest[d] = (byte) (pos | ((p >>> 2) & 7));
        d = i + 1;
        s2 = s + 1;
        pos = ((p & 3) << 6) | (pos(src[s]) << 1);
        s = s2 + 1;
        p = pos(src[s2]);
        dest[i] = (byte) (pos | ((p >>> 4) & 1));
        i = d + 1;
        pos = (p & 15) << 4;
        s2 = s + 1;
        p = pos(src[s]);
        dest[d] = (byte) (pos | ((p >>> 1) & 15));
        d = i + 1;
        s = s2 + 1;
        pos = ((p & 1) << 7) | (pos(src[s2]) << 2);
        s2 = s + 1;
        p = pos(src[s]);
        dest[i] = (byte) (pos | ((p >>> 3) & 3));
        dest[d] = (byte) (((p & 7) << 5) | pos(src[s2]));
    }

    private final void decode1to4bytes(int n, byte[] src, int s, byte[] dest, int d) {
        int i = d + 1;
        int s2 = s + 1;
        int pos = pos(src[s]) << 3;
        s = s2 + 1;
        int p = pos(src[s2]);
        dest[d] = (byte) (pos | ((p >>> 2) & 7));
        if (n == 1) {
            CodecUtils.sanityCheckLastPos(p, 3);
            d = i;
            return;
        }
        d = i + 1;
        s2 = s + 1;
        pos = ((p & 3) << 6) | (pos(src[s]) << 1);
        s = s2 + 1;
        p = pos(src[s2]);
        dest[i] = (byte) (pos | ((p >>> 4) & 1));
        if (n == 2) {
            CodecUtils.sanityCheckLastPos(p, 15);
            return;
        }
        i = d + 1;
        pos = (p & 15) << 4;
        s2 = s + 1;
        p = pos(src[s]);
        dest[d] = (byte) (pos | ((p >>> 1) & 15));
        if (n == 3) {
            CodecUtils.sanityCheckLastPos(p, 1);
            d = i;
            s = s2;
            return;
        }
        pos = ((p & 1) << 7) | (pos(src[s2]) << 2);
        p = pos(src[s2 + 1]);
        dest[i] = (byte) (pos | ((p >>> 3) & 3));
        CodecUtils.sanityCheckLastPos(p, 7);
        d = i;
    }

    public final byte[] decode(byte[] src, int length) {
        if (length % 8 != 0) {
            throw new IllegalArgumentException("Input is expected to be encoded in multiple of 8 bytes but found: " + length);
        }
        int fq;
        int pads = 0;
        int last = length - 1;
        while (pads < 6 && last > -1 && src[last] == PAD) {
            last--;
            pads++;
        }
        switch (pads) {
            case 0:
                fq = 5;
                break;
            case 1:
                fq = 4;
                break;
            case 3:
                fq = 3;
                break;
            case 4:
                fq = 2;
                break;
            case 6:
                fq = 1;
                break;
            default:
                throw new IllegalArgumentException("Invalid number of paddings " + pads);
        }
        byte[] dest = new byte[(((length / 8) * 5) - (5 - fq))];
        int s = 0;
        int d = 0;
        while (d < dest.length - (fq % 5)) {
            decode5bytes(src, s, dest, d);
            s += 8;
            d += 5;
        }
        if (fq < 5) {
            decode1to4bytes(fq, src, s, dest, d);
        }
        return dest;
    }
}

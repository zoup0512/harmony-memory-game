package com.amazonaws.util;

import com.cube.memorygames.activity.ProfileActivity;

class Base64Codec implements Codec {
    private static final int MASK_2BITS = 3;
    private static final int MASK_4BITS = 15;
    private static final int MASK_6BITS = 63;
    private static final int OFFSET_OF_0 = -4;
    private static final int OFFSET_OF_PLUS = -19;
    private static final int OFFSET_OF_SLASH = -16;
    private static final int OFFSET_OF_a = 71;
    private static final byte PAD = (byte) 61;
    private final byte[] ALPAHBETS;

    private static class LazyHolder {
        private static final byte[] DECODED = decodeTable();

        private LazyHolder() {
        }

        private static byte[] decodeTable() {
            byte[] dest = new byte[ProfileActivity.REQUEST_CODE_ASK_PERMISSIONS];
            int i = 0;
            while (i <= 122) {
                if (i >= 65 && i <= 90) {
                    dest[i] = (byte) (i - 65);
                } else if (i >= 48 && i <= 57) {
                    dest[i] = (byte) (i + 4);
                } else if (i == 43) {
                    dest[i] = (byte) (i + 19);
                } else if (i == 47) {
                    dest[i] = (byte) (i + 16);
                } else if (i < 97 || i > 122) {
                    dest[i] = (byte) -1;
                } else {
                    dest[i] = (byte) (i - 71);
                }
                i++;
            }
            return dest;
        }
    }

    Base64Codec() {
        this.ALPAHBETS = CodecUtils.toBytesDirect("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");
    }

    protected Base64Codec(byte[] alphabets) {
        this.ALPAHBETS = alphabets;
    }

    public byte[] encode(byte[] src) {
        int num3bytes = src.length / 3;
        int remainder = src.length % 3;
        byte[] dest;
        int s;
        int d;
        if (remainder == 0) {
            dest = new byte[(num3bytes * 4)];
            s = 0;
            d = 0;
            while (s < src.length) {
                encode3bytes(src, s, dest, d);
                s += 3;
                d += 4;
            }
            return dest;
        }
        dest = new byte[((num3bytes + 1) * 4)];
        s = 0;
        d = 0;
        while (s < src.length - remainder) {
            encode3bytes(src, s, dest, d);
            s += 3;
            d += 4;
        }
        switch (remainder) {
            case 1:
                encode1byte(src, s, dest, d);
                break;
            case 2:
                encode2bytes(src, s, dest, d);
                break;
        }
        return dest;
    }

    void encode3bytes(byte[] src, int s, byte[] dest, int d) {
        int i = d + 1;
        byte[] bArr = this.ALPAHBETS;
        int s2 = s + 1;
        byte p = src[s];
        dest[d] = bArr[(p >>> 2) & 63];
        d = i + 1;
        bArr = this.ALPAHBETS;
        int i2 = (p & 3) << 4;
        s = s2 + 1;
        p = src[s2];
        dest[i] = bArr[i2 | ((p >>> 4) & 15)];
        i = d + 1;
        bArr = this.ALPAHBETS;
        i2 = (p & 15) << 2;
        p = src[s];
        dest[d] = bArr[i2 | ((p >>> 6) & 3)];
        dest[i] = this.ALPAHBETS[p & 63];
    }

    void encode2bytes(byte[] src, int s, byte[] dest, int d) {
        int i = d + 1;
        byte[] bArr = this.ALPAHBETS;
        int s2 = s + 1;
        byte p = src[s];
        dest[d] = bArr[(p >>> 2) & 63];
        d = i + 1;
        bArr = this.ALPAHBETS;
        int i2 = (p & 3) << 4;
        p = src[s2];
        dest[i] = bArr[i2 | ((p >>> 4) & 15)];
        i = d + 1;
        dest[d] = this.ALPAHBETS[(p & 15) << 2];
        dest[i] = PAD;
    }

    void encode1byte(byte[] src, int s, byte[] dest, int d) {
        int i = d + 1;
        byte[] bArr = this.ALPAHBETS;
        byte p = src[s];
        dest[d] = bArr[(p >>> 2) & 63];
        d = i + 1;
        dest[i] = this.ALPAHBETS[(p & 3) << 4];
        i = d + 1;
        dest[d] = PAD;
        dest[i] = PAD;
    }

    void decode4bytes(byte[] src, int s, byte[] dest, int d) {
        int i = d + 1;
        int s2 = s + 1;
        int pos = pos(src[s]) << 2;
        s = s2 + 1;
        int p = pos(src[s2]);
        dest[d] = (byte) (pos | ((p >>> 4) & 3));
        d = i + 1;
        pos = (p & 15) << 4;
        s2 = s + 1;
        p = pos(src[s]);
        dest[i] = (byte) (pos | ((p >>> 2) & 15));
        dest[d] = (byte) (((p & 3) << 6) | pos(src[s2]));
    }

    void decode1to3bytes(int n, byte[] src, int s, byte[] dest, int d) {
        int i = d + 1;
        int s2 = s + 1;
        int pos = pos(src[s]) << 2;
        s = s2 + 1;
        int p = pos(src[s2]);
        dest[d] = (byte) (pos | ((p >>> 4) & 3));
        if (n == 1) {
            CodecUtils.sanityCheckLastPos(p, 15);
            d = i;
            return;
        }
        d = i + 1;
        pos = (p & 15) << 4;
        s2 = s + 1;
        p = pos(src[s]);
        dest[i] = (byte) (pos | ((p >>> 2) & 15));
        if (n == 2) {
            CodecUtils.sanityCheckLastPos(p, 3);
            s = s2;
            return;
        }
        dest[d] = (byte) (((p & 3) << 6) | pos(src[s2]));
        s = s2;
    }

    public byte[] decode(byte[] src, int length) {
        if (length % 4 != 0) {
            throw new IllegalArgumentException("Input is expected to be encoded in multiple of 4 bytes but found: " + length);
        }
        int fq;
        int pads = 0;
        int last = length - 1;
        while (pads < 2 && last > -1 && src[last] == PAD) {
            last--;
            pads++;
        }
        switch (pads) {
            case 0:
                fq = 3;
                break;
            case 1:
                fq = 2;
                break;
            case 2:
                fq = 1;
                break;
            default:
                throw new Error("Impossible");
        }
        byte[] dest = new byte[(((length / 4) * 3) - (3 - fq))];
        int s = 0;
        int d = 0;
        while (d < dest.length - (fq % 3)) {
            decode4bytes(src, s, dest, d);
            s += 4;
            d += 3;
        }
        if (fq < 3) {
            decode1to3bytes(fq, src, s, dest, d);
        }
        return dest;
    }

    protected int pos(byte in) {
        int pos = LazyHolder.DECODED[in];
        if (pos > -1) {
            return pos;
        }
        throw new IllegalArgumentException("Invalid base 64 character: '" + ((char) in) + "'");
    }
}

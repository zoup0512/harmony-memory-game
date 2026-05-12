package com.amazonaws.util;

class Base16Codec implements Codec {
    private static final int MASK_4BITS = 15;
    private static final int OFFSET_OF_A = 55;
    private static final int OFFSET_OF_a = 87;
    private final byte[] ALPAHBETS = CodecUtils.toBytesDirect("0123456789ABCDEF");

    private static class LazyHolder {
        private static final byte[] DECODED = decodeTable();

        private LazyHolder() {
        }

        private static byte[] decodeTable() {
            byte[] dest = new byte[103];
            int i = 0;
            while (i <= 102) {
                if (i >= 48 && i <= 57) {
                    dest[i] = (byte) (i - 48);
                } else if (i >= 65 && i <= 70) {
                    dest[i] = (byte) (i - 55);
                } else if (i < 97 || i > 102) {
                    dest[i] = (byte) -1;
                } else {
                    dest[i] = (byte) (i - 87);
                }
                i++;
            }
            return dest;
        }
    }

    Base16Codec() {
    }

    public byte[] encode(byte[] src) {
        byte[] dest = new byte[(src.length * 2)];
        int j = 0;
        for (byte p : src) {
            int i = j + 1;
            byte[] bArr = this.ALPAHBETS;
            dest[j] = bArr[(p >>> 4) & 15];
            j = i + 1;
            dest[i] = this.ALPAHBETS[p & 15];
        }
        return dest;
    }

    public byte[] decode(byte[] src, int length) {
        if (length % 2 != 0) {
            throw new IllegalArgumentException("Input is expected to be encoded in multiple of 2 bytes but found: " + length);
        }
        byte[] dest = new byte[(length / 2)];
        int i = 0;
        for (int j = 0; j < dest.length; j++) {
            int i2 = i + 1;
            i = i2 + 1;
            dest[j] = (byte) ((pos(src[i]) << 4) | pos(src[i2]));
        }
        return dest;
    }

    protected int pos(byte in) {
        int pos = LazyHolder.DECODED[in];
        if (pos > -1) {
            return pos;
        }
        throw new IllegalArgumentException("Invalid base 16 character: '" + ((char) in) + "'");
    }
}

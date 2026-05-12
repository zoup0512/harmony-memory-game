package com.amazonaws.util;

import com.cube.memorygames.activity.ProfileActivity;

class Base32Codec extends AbstractBase32Codec {
    private static final int OFFSET_OF_2 = 24;

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
                } else if (i >= 50 && i <= 55) {
                    dest[i] = (byte) (i - 24);
                } else if (i < 97 || i > 122) {
                    dest[i] = (byte) -1;
                } else {
                    dest[i] = (byte) (i - 97);
                }
                i++;
            }
            return dest;
        }
    }

    private static byte[] alphabets() {
        return CodecUtils.toBytesDirect("ABCDEFGHIJKLMNOPQRSTUVWXYZ234567");
    }

    Base32Codec() {
        super(alphabets());
    }

    protected int pos(byte in) {
        int pos = LazyHolder.DECODED[in];
        if (pos > -1) {
            return pos;
        }
        throw new IllegalArgumentException("Invalid base 32 character: '" + ((char) in) + "'");
    }
}

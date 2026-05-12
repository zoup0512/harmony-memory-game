package com.amazon.device.ads;

class Base64 {
    private static final String ENCODE_CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";

    Base64() {
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static byte[] decode(java.lang.String r8) {
        /*
        r0 = 0;
        r1 = com.amazon.device.ads.StringUtils.isNullOrWhiteSpace(r8);
        if (r1 == 0) goto L_0x000f;
    L_0x0007:
        r0 = new java.lang.IllegalArgumentException;
        r1 = "Encoded String must not be null or white space";
        r0.<init>(r1);
        throw r0;
    L_0x000f:
        r3 = getDecodedLength(r8);
        if (r3 > 0) goto L_0x001d;
    L_0x0015:
        r0 = new java.lang.IllegalArgumentException;
        r1 = "Encoded String decodes to zero bytes";
        r0.<init>(r1);
        throw r0;
    L_0x001d:
        r4 = new byte[r3];
        r1 = r0;
    L_0x0020:
        r2 = r8.length();
        if (r0 >= r2) goto L_0x0028;
    L_0x0026:
        if (r1 < r3) goto L_0x0029;
    L_0x0028:
        return r4;
    L_0x0029:
        r2 = r0 % 4;
        if (r2 != 0) goto L_0x0035;
    L_0x002d:
        r2 = r8.length();
        r5 = r0 + 4;
        if (r2 < r5) goto L_0x0028;
    L_0x0035:
        r2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
        r5 = r8.charAt(r0);
        r5 = r2.indexOf(r5);
        r2 = -1;
        if (r5 == r2) goto L_0x0028;
    L_0x0042:
        r2 = r0 % 4;
        switch(r2) {
            case 0: goto L_0x004a;
            case 1: goto L_0x0050;
            case 2: goto L_0x0066;
            case 3: goto L_0x007c;
            default: goto L_0x0047;
        };
    L_0x0047:
        r0 = r0 + 1;
        goto L_0x0020;
    L_0x004a:
        r2 = r5 << 2;
        r2 = (byte) r2;
        r4[r1] = r2;
        goto L_0x0047;
    L_0x0050:
        r2 = r1 + 1;
        r6 = r4[r1];
        r7 = r5 >> 4;
        r7 = r7 & 3;
        r7 = (byte) r7;
        r6 = r6 | r7;
        r6 = (byte) r6;
        r4[r1] = r6;
        if (r2 >= r3) goto L_0x0087;
    L_0x005f:
        r1 = r5 << 4;
        r1 = (byte) r1;
        r4[r2] = r1;
        r1 = r2;
        goto L_0x0047;
    L_0x0066:
        r2 = r1 + 1;
        r6 = r4[r1];
        r7 = r5 >> 2;
        r7 = r7 & 15;
        r7 = (byte) r7;
        r6 = r6 | r7;
        r6 = (byte) r6;
        r4[r1] = r6;
        if (r2 >= r3) goto L_0x0087;
    L_0x0075:
        r1 = r5 << 6;
        r1 = (byte) r1;
        r4[r2] = r1;
        r1 = r2;
        goto L_0x0047;
    L_0x007c:
        r2 = r1 + 1;
        r6 = r4[r1];
        r5 = r5 & 63;
        r5 = (byte) r5;
        r5 = r5 | r6;
        r5 = (byte) r5;
        r4[r1] = r5;
    L_0x0087:
        r1 = r2;
        goto L_0x0047;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.device.ads.Base64.decode(java.lang.String):byte[]");
    }

    private static int getDecodedLength(String str) {
        int indexOf = str.indexOf("=");
        int i = 0;
        if (indexOf > -1) {
            i = str.length() - indexOf;
        }
        return (((str.length() + 3) / 4) * 3) - i;
    }
}

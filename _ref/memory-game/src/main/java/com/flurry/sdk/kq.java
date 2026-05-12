package com.flurry.sdk;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class kq<ObjectType> {
    private static final String a = kq.class.getSimpleName();
    private static final byte[] b = new byte[]{(byte) 113, (byte) -92, (byte) -8, (byte) 125, (byte) 121, (byte) 107, (byte) -65, (byte) -61, (byte) -74, (byte) -114, (byte) -32, (byte) 0, (byte) -57, (byte) -87, (byte) -35, (byte) -56, (byte) -6, (byte) -52, (byte) 51, (byte) 126, (byte) -104, (byte) 49, (byte) 79, (byte) -52, (byte) 118, (byte) -84, (byte) 99, (byte) -52, (byte) -14, (byte) -126, (byte) -27, (byte) -64};
    private String c;
    private lg<ObjectType> d;

    public kq(String str, lg<ObjectType> lgVar) {
        this.c = str;
        this.d = lgVar;
    }

    public static int a(byte[] bArr) {
        if (bArr == null) {
            return 0;
        }
        ke keVar = new ke();
        keVar.update(bArr);
        return keVar.b();
    }

    public final byte[] a(ObjectType objectType) throws IOException {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        this.d.a(byteArrayOutputStream, objectType);
        Object toByteArray = byteArrayOutputStream.toByteArray();
        km.a(3, a, "Encoding " + this.c + ": " + new String(toByteArray));
        lg leVar = new le(new lc());
        OutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        leVar.a(byteArrayOutputStream2, toByteArray);
        byte[] toByteArray2 = byteArrayOutputStream2.toByteArray();
        c(toByteArray2);
        return toByteArray2;
    }

    private static void c(byte[] bArr) {
        if (bArr != null) {
            int length = bArr.length;
            int length2 = b.length;
            for (int i = 0; i < length; i++) {
                bArr[i] = (byte) ((bArr[i] ^ b[i % length2]) ^ ((i * 31) % 251));
            }
        }
    }

    public final ObjectType b(byte[] bArr) throws IOException {
        if (bArr == null) {
            throw new IOException("Decoding: " + this.c + ": Nothing to decode");
        }
        c(bArr);
        byte[] bArr2 = (byte[]) new le(new lc()).a(new ByteArrayInputStream(bArr));
        km.a(3, a, "Decoding: " + this.c + ": " + new String(bArr2));
        return this.d.a(new ByteArrayInputStream(bArr2));
    }
}

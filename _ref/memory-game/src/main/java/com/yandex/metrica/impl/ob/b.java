package com.yandex.metrica.impl.ob;

import android.support.v4.media.TransportMediator;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public final class b {
    private final byte[] a;
    private final int b;
    private int c;

    public static class a extends IOException {
        private static final long serialVersionUID = -6947486886997889499L;

        a(int i, int i2) {
            super("CodedOutputStream was writing to a flat byte array and ran out of space (pos " + i + " limit " + i2 + ").");
        }
    }

    private b(byte[] bArr, int i, int i2) {
        this.a = bArr;
        this.c = i;
        this.b = i + i2;
    }

    public static b a(byte[] bArr, int i, int i2) {
        return new b(bArr, i, i2);
    }

    public void a(int i, double d) throws IOException {
        g(i, 1);
        a(d);
    }

    public void a(int i, long j) throws IOException {
        g(i, 0);
        a(j);
    }

    public void b(int i, long j) throws IOException {
        g(i, 0);
        b(j);
    }

    public void a(int i, int i2) throws IOException {
        g(i, 0);
        a(i2);
    }

    public void a(int i, boolean z) throws IOException {
        g(i, 0);
        a(z);
    }

    public void a(int i, String str) throws IOException {
        g(i, 2);
        a(str);
    }

    public void a(int i, d dVar) throws IOException {
        g(i, 2);
        a(dVar);
    }

    public void a(int i, byte[] bArr) throws IOException {
        g(i, 2);
        a(bArr);
    }

    public void b(int i, int i2) throws IOException {
        g(i, 0);
        b(i2);
    }

    public void c(int i, int i2) throws IOException {
        g(i, 0);
        c(i2);
    }

    public void a(double d) throws IOException {
        e(Double.doubleToLongBits(d));
    }

    public void a(long j) throws IOException {
        c(j);
    }

    public void b(long j) throws IOException {
        c(j);
    }

    public void a(int i) throws IOException {
        if (i >= 0) {
            h(i);
        } else {
            c((long) i);
        }
    }

    public void a(boolean z) throws IOException {
        f(z ? 1 : 0);
    }

    public void a(String str) throws IOException {
        byte[] bytes = str.getBytes("UTF-8");
        h(bytes.length);
        b(bytes);
    }

    public void a(d dVar) throws IOException {
        h(dVar.a());
        dVar.a(this);
    }

    public void a(byte[] bArr) throws IOException {
        h(bArr.length);
        b(bArr);
    }

    public void b(int i) throws IOException {
        h(i);
    }

    public void c(int i) throws IOException {
        h(j(i));
    }

    public static int d(int i) {
        return g(i) + 8;
    }

    public static int c(int i, long j) {
        return g(i) + d(j);
    }

    public static int d(int i, long j) {
        return g(i) + d(j);
    }

    public static int d(int i, int i2) {
        int i3;
        int g = g(i);
        if (i2 >= 0) {
            i3 = i(i2);
        } else {
            i3 = 10;
        }
        return i3 + g;
    }

    public static int e(int i) {
        return g(i) + 1;
    }

    public static int b(int i, String str) {
        return g(i) + b(str);
    }

    public static int b(int i, d dVar) {
        return g(i) + b(dVar);
    }

    public static int b(int i, byte[] bArr) {
        return g(i) + (i(bArr.length) + bArr.length);
    }

    public static int e(int i, int i2) {
        return g(i) + i(i2);
    }

    public static int f(int i, int i2) {
        return g(i) + i(j(i2));
    }

    public static int b(String str) {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            return bytes.length + i(bytes.length);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 not supported.");
        }
    }

    public static int b(d dVar) {
        int b = dVar.b();
        return b + i(b);
    }

    public int a() {
        return this.b - this.c;
    }

    public void b() {
        if (a() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    public void a(byte b) throws IOException {
        if (this.c == this.b) {
            throw new a(this.c, this.b);
        }
        byte[] bArr = this.a;
        int i = this.c;
        this.c = i + 1;
        bArr[i] = b;
    }

    public void f(int i) throws IOException {
        a((byte) i);
    }

    public void b(byte[] bArr) throws IOException {
        b(bArr, 0, bArr.length);
    }

    public void b(byte[] bArr, int i, int i2) throws IOException {
        if (this.b - this.c >= i2) {
            System.arraycopy(bArr, i, this.a, this.c, i2);
            this.c += i2;
            return;
        }
        throw new a(this.c, this.b);
    }

    public void g(int i, int i2) throws IOException {
        h(f.a(i, i2));
    }

    public static int g(int i) {
        return i(f.a(i, 0));
    }

    public void h(int i) throws IOException {
        while ((i & -128) != 0) {
            f((i & TransportMediator.KEYCODE_MEDIA_PAUSE) | 128);
            i >>>= 7;
        }
        f(i);
    }

    public static int i(int i) {
        if ((i & -128) == 0) {
            return 1;
        }
        if ((i & -16384) == 0) {
            return 2;
        }
        if ((-2097152 & i) == 0) {
            return 3;
        }
        if ((-268435456 & i) == 0) {
            return 4;
        }
        return 5;
    }

    public void c(long j) throws IOException {
        while ((-128 & j) != 0) {
            f((((int) j) & TransportMediator.KEYCODE_MEDIA_PAUSE) | 128);
            j >>>= 7;
        }
        f((int) j);
    }

    public static int d(long j) {
        if ((-128 & j) == 0) {
            return 1;
        }
        if ((-16384 & j) == 0) {
            return 2;
        }
        if ((-2097152 & j) == 0) {
            return 3;
        }
        if ((-268435456 & j) == 0) {
            return 4;
        }
        if ((-34359738368L & j) == 0) {
            return 5;
        }
        if ((-4398046511104L & j) == 0) {
            return 6;
        }
        if ((-562949953421312L & j) == 0) {
            return 7;
        }
        if ((-72057594037927936L & j) == 0) {
            return 8;
        }
        if ((Long.MIN_VALUE & j) == 0) {
            return 9;
        }
        return 10;
    }

    public void e(long j) throws IOException {
        f(((int) j) & 255);
        f(((int) (j >> 8)) & 255);
        f(((int) (j >> 16)) & 255);
        f(((int) (j >> 24)) & 255);
        f(((int) (j >> 32)) & 255);
        f(((int) (j >> 40)) & 255);
        f(((int) (j >> 48)) & 255);
        f(((int) (j >> 56)) & 255);
    }

    public static int j(int i) {
        return (i << 1) ^ (i >> 31);
    }
}

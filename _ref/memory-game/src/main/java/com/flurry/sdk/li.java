package com.flurry.sdk;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class li<T> implements lg<T> {
    private final String a;
    private final int b;
    private final lj<T> c;

    public li(String str, int i, lj<T> ljVar) {
        this.a = str;
        this.b = i;
        this.c = ljVar;
    }

    public final void a(OutputStream outputStream, T t) throws IOException {
        if (outputStream != null && this.c != null) {
            OutputStream anonymousClass1 = new DataOutputStream(this, outputStream) {
                final /* synthetic */ li a;

                public final void close() {
                }
            };
            anonymousClass1.writeUTF(this.a);
            anonymousClass1.writeInt(this.b);
            this.c.a(this.b).a(anonymousClass1, t);
            anonymousClass1.flush();
        }
    }

    public final T a(InputStream inputStream) throws IOException {
        if (inputStream == null || this.c == null) {
            return null;
        }
        InputStream anonymousClass2 = new DataInputStream(this, inputStream) {
            final /* synthetic */ li a;

            public final void close() {
            }
        };
        String readUTF = anonymousClass2.readUTF();
        if (this.a.equals(readUTF)) {
            return this.c.a(anonymousClass2.readInt()).a(anonymousClass2);
        }
        throw new IOException("Signature: " + readUTF + " is invalid");
    }
}

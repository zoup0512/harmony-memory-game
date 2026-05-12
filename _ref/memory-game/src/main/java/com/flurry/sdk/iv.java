package com.flurry.sdk;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class iv {
    byte[] a;

    public static class a implements lg<iv> {
        public final /* synthetic */ Object a(InputStream inputStream) throws IOException {
            if (inputStream == null) {
                return null;
            }
            DataInputStream anonymousClass2 = new DataInputStream(this, inputStream) {
                final /* synthetic */ a a;

                public final void close() {
                }
            };
            short readShort = anonymousClass2.readShort();
            if (readShort == (short) 0) {
                return null;
            }
            Object ivVar = new iv();
            ivVar.a = new byte[readShort];
            anonymousClass2.readFully(ivVar.a);
            anonymousClass2.readUnsignedShort();
            return ivVar;
        }

        public final /* synthetic */ void a(OutputStream outputStream, Object obj) throws IOException {
            iv ivVar = (iv) obj;
            if (outputStream != null && ivVar != null) {
                DataOutputStream anonymousClass1 = new DataOutputStream(this, outputStream) {
                    final /* synthetic */ a a;

                    public final void close() {
                    }
                };
                anonymousClass1.writeShort(ivVar.a.length);
                anonymousClass1.write(ivVar.a);
                anonymousClass1.writeShort(0);
                anonymousClass1.flush();
            }
        }
    }

    public iv(byte[] bArr) {
        this.a = bArr;
    }
}

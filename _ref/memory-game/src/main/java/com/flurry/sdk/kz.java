package com.flurry.sdk;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public final class kz {
    String a;
    byte[] b;

    public static class a implements lg<kz> {
        public final /* synthetic */ Object a(InputStream inputStream) throws IOException {
            if (inputStream == null) {
                return null;
            }
            DataInputStream anonymousClass2 = new DataInputStream(this, inputStream) {
                final /* synthetic */ a a;

                public final void close() {
                }
            };
            kz kzVar = new kz();
            short readShort = anonymousClass2.readShort();
            if (readShort == (short) 0) {
                return null;
            }
            kzVar.b = new byte[readShort];
            anonymousClass2.readFully(kzVar.b);
            anonymousClass2.readUnsignedShort();
            return kzVar;
        }

        public final /* synthetic */ void a(OutputStream outputStream, Object obj) throws IOException {
            kz kzVar = (kz) obj;
            if (outputStream != null && kzVar != null) {
                DataOutputStream anonymousClass1 = new DataOutputStream(this, outputStream) {
                    final /* synthetic */ a a;

                    public final void close() {
                    }
                };
                anonymousClass1.writeShort(kzVar.b.length);
                anonymousClass1.write(kzVar.b);
                anonymousClass1.writeShort(0);
                anonymousClass1.flush();
            }
        }
    }

    private kz() {
        this.a = null;
        this.b = null;
    }

    public kz(byte[] bArr) {
        this.a = null;
        this.b = null;
        this.a = UUID.randomUUID().toString();
        this.b = bArr;
    }

    public static String a(String str) {
        return ".yflurrydatasenderblock." + str;
    }
}

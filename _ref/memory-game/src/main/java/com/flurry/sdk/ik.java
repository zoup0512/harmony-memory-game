package com.flurry.sdk;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class ik {
    long a;
    boolean b;
    byte[] c;

    public static class a implements lg<ik> {
        public final /* synthetic */ void a(OutputStream outputStream, Object obj) throws IOException {
            ik ikVar = (ik) obj;
            if (outputStream != null && ikVar != null) {
                DataOutputStream anonymousClass1 = new DataOutputStream(this, outputStream) {
                    final /* synthetic */ a a;

                    public final void close() {
                    }
                };
                anonymousClass1.writeLong(ikVar.a);
                anonymousClass1.writeBoolean(ikVar.b);
                anonymousClass1.writeInt(ikVar.c.length);
                anonymousClass1.write(ikVar.c);
                anonymousClass1.flush();
            }
        }

        public final /* synthetic */ Object a(InputStream inputStream) throws IOException {
            if (inputStream == null) {
                return null;
            }
            DataInputStream anonymousClass2 = new DataInputStream(this, inputStream) {
                final /* synthetic */ a a;

                public final void close() {
                }
            };
            Object ikVar = new ik();
            ikVar.a = anonymousClass2.readLong();
            ikVar.b = anonymousClass2.readBoolean();
            ikVar.c = new byte[anonymousClass2.readInt()];
            anonymousClass2.readFully(ikVar.c);
            return ikVar;
        }
    }
}

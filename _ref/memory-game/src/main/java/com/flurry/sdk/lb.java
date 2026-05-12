package com.flurry.sdk;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class lb {
    String a;

    public static class a implements lg<lb> {
        public final /* synthetic */ void a(OutputStream outputStream, Object obj) throws IOException {
            lb lbVar = (lb) obj;
            if (outputStream != null && lbVar != null) {
                DataOutputStream anonymousClass1 = new DataOutputStream(this, outputStream) {
                    final /* synthetic */ a a;

                    public final void close() {
                    }
                };
                anonymousClass1.writeUTF(lbVar.a);
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
            Object lbVar = new lb();
            lbVar.a = anonymousClass2.readUTF();
            return lbVar;
        }
    }

    private lb() {
    }

    public lb(String str) {
        this.a = str;
    }
}

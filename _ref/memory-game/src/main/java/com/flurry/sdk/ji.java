package com.flurry.sdk;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class ji {
    private static final String d = ji.class.getSimpleName();
    final List<jf> a = new ArrayList();
    boolean b;
    long c;

    public static class a implements lg<ji> {
        public final /* synthetic */ Object a(InputStream inputStream) throws IOException {
            if (inputStream == null) {
                return null;
            }
            DataInputStream anonymousClass1 = new DataInputStream(this, inputStream) {
                final /* synthetic */ a a;

                public final void close() {
                }
            };
            ji jiVar = new ji();
            anonymousClass1.readUTF();
            anonymousClass1.readUTF();
            jiVar.b = anonymousClass1.readBoolean();
            jiVar.c = anonymousClass1.readLong();
            while (true) {
                int readUnsignedShort = anonymousClass1.readUnsignedShort();
                if (readUnsignedShort == 0) {
                    return jiVar;
                }
                byte[] bArr = new byte[readUnsignedShort];
                anonymousClass1.readFully(bArr);
                jiVar.a.add(0, new jf(bArr));
            }
        }

        public final /* synthetic */ void a(OutputStream outputStream, Object obj) throws IOException {
            throw new UnsupportedOperationException("Serialization not supported");
        }
    }
}

package com.flurry.sdk;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public final class lf<T> implements lg<List<T>> {
    lg<T> a;

    public final /* synthetic */ Object a(InputStream inputStream) throws IOException {
        return b(inputStream);
    }

    public lf(lg<T> lgVar) {
        this.a = lgVar;
    }

    public final void a(OutputStream outputStream, List<T> list) throws IOException {
        int i = 0;
        if (outputStream != null) {
            int size;
            DataOutputStream anonymousClass1 = new DataOutputStream(this, outputStream) {
                final /* synthetic */ lf a;

                public final void close() {
                }
            };
            if (list != null) {
                size = list.size();
            } else {
                size = 0;
            }
            anonymousClass1.writeInt(size);
            while (i < size) {
                this.a.a(outputStream, list.get(i));
                i++;
            }
            anonymousClass1.flush();
        }
    }

    public final List<T> b(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        int readInt = new DataInputStream(this, inputStream) {
            final /* synthetic */ lf a;

            public final void close() {
            }
        }.readInt();
        List<T> arrayList = new ArrayList(readInt);
        for (int i = 0; i < readInt; i++) {
            Object a = this.a.a(inputStream);
            if (a == null) {
                throw new IOException("Missing record.");
            }
            arrayList.add(a);
        }
        return arrayList;
    }
}

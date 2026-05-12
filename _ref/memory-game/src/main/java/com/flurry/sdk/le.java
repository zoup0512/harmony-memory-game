package com.flurry.sdk;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public final class le<ObjectType> extends ld<ObjectType> {
    public le(lg<ObjectType> lgVar) {
        super(lgVar);
    }

    public final void a(OutputStream outputStream, ObjectType objectType) throws IOException {
        Closeable gZIPOutputStream;
        Throwable th;
        if (outputStream != null) {
            try {
                gZIPOutputStream = new GZIPOutputStream(outputStream);
                try {
                    super.a(gZIPOutputStream, objectType);
                    ly.a(gZIPOutputStream);
                } catch (Throwable th2) {
                    th = th2;
                    ly.a(gZIPOutputStream);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                gZIPOutputStream = null;
                ly.a(gZIPOutputStream);
                throw th;
            }
        }
    }

    public final ObjectType a(InputStream inputStream) throws IOException {
        Throwable th;
        ObjectType objectType = null;
        if (inputStream != null) {
            Closeable gZIPInputStream;
            try {
                gZIPInputStream = new GZIPInputStream(inputStream);
                try {
                    objectType = super.a(gZIPInputStream);
                    ly.a(gZIPInputStream);
                } catch (Throwable th2) {
                    th = th2;
                    ly.a(gZIPInputStream);
                    throw th;
                }
            } catch (Throwable th3) {
                Throwable th4 = th3;
                gZIPInputStream = null;
                th = th4;
                ly.a(gZIPInputStream);
                throw th;
            }
        }
        return objectType;
    }
}

package com.flurry.sdk;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ld<ObjectType> implements lg<ObjectType> {
    protected final lg<ObjectType> a;

    public ld(lg<ObjectType> lgVar) {
        this.a = lgVar;
    }

    public void a(OutputStream outputStream, ObjectType objectType) throws IOException {
        if (this.a != null && outputStream != null && objectType != null) {
            this.a.a(outputStream, objectType);
        }
    }

    public ObjectType a(InputStream inputStream) throws IOException {
        if (this.a == null || inputStream == null) {
            return null;
        }
        return this.a.a(inputStream);
    }
}

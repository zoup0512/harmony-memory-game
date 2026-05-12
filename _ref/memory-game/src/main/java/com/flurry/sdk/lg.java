package com.flurry.sdk;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface lg<ObjectType> {
    ObjectType a(InputStream inputStream) throws IOException;

    void a(OutputStream outputStream, ObjectType objectType) throws IOException;
}

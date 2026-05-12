package com.flurry.sdk;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class lh implements lg<String> {
    public final /* synthetic */ void a(OutputStream outputStream, Object obj) throws IOException {
        String str = (String) obj;
        if (outputStream != null && str != null) {
            byte[] bytes = str.getBytes("utf-8");
            outputStream.write(bytes, 0, bytes.length);
        }
    }

    public final /* synthetic */ Object a(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        }
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ly.a(inputStream, byteArrayOutputStream);
        return byteArrayOutputStream.toString();
    }
}

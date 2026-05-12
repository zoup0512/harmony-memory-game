package com.flurry.sdk;

import com.flurry.sdk.ji.a;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;

public final class hu {
    private static final String a = hu.class.getSimpleName();

    public static ji a(File file) {
        Closeable fileInputStream;
        Throwable e;
        ji jiVar;
        if (file == null || !file.exists()) {
            return null;
        }
        lg aVar = new a();
        Closeable dataInputStream;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                dataInputStream = new DataInputStream(fileInputStream);
            } catch (Exception e2) {
                e = e2;
                dataInputStream = null;
                try {
                    km.a(3, a, "Error loading legacy agent data.", e);
                    ly.a(dataInputStream);
                    ly.a(fileInputStream);
                    jiVar = null;
                    return jiVar;
                } catch (Throwable th) {
                    e = th;
                    ly.a(dataInputStream);
                    ly.a(fileInputStream);
                    throw e;
                }
            } catch (Throwable th2) {
                e = th2;
                dataInputStream = null;
                ly.a(dataInputStream);
                ly.a(fileInputStream);
                throw e;
            }
            try {
                if (dataInputStream.readUnsignedShort() != 46586) {
                    km.a(3, a, "Unexpected file type");
                    ly.a(dataInputStream);
                    ly.a(fileInputStream);
                    return null;
                }
                int readUnsignedShort = dataInputStream.readUnsignedShort();
                if (readUnsignedShort != 2) {
                    km.a(6, a, "Unknown agent file version: " + readUnsignedShort);
                    ly.a(dataInputStream);
                    ly.a(fileInputStream);
                    return null;
                }
                jiVar = (ji) aVar.a(dataInputStream);
                ly.a(dataInputStream);
                ly.a(fileInputStream);
                return jiVar;
            } catch (Exception e3) {
                e = e3;
                km.a(3, a, "Error loading legacy agent data.", e);
                ly.a(dataInputStream);
                ly.a(fileInputStream);
                jiVar = null;
                return jiVar;
            }
        } catch (Exception e4) {
            e = e4;
            dataInputStream = null;
            fileInputStream = null;
            km.a(3, a, "Error loading legacy agent data.", e);
            ly.a(dataInputStream);
            ly.a(fileInputStream);
            jiVar = null;
            return jiVar;
        } catch (Throwable th3) {
            e = th3;
            dataInputStream = null;
            fileInputStream = null;
            ly.a(dataInputStream);
            ly.a(fileInputStream);
            throw e;
        }
    }
}

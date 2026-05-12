package com.flurry.sdk;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public final class jc {
    public String a;
    public boolean b;
    public boolean c;
    public long d;
    private final Map<String, String> e = new HashMap();
    private int f;
    private long g;

    public jc(int i, String str, Map<String, String> map, long j, boolean z) {
        this.f = i;
        this.a = str;
        if (map != null) {
            this.e.putAll(map);
        }
        this.g = j;
        this.b = z;
        this.c = !this.b;
    }

    public final void a(long j) {
        this.c = true;
        this.d = j - this.g;
        km.a(3, "FlurryAgent", "Ended event '" + this.a + "' (" + this.g + ") after " + this.d + "ms");
    }

    public final synchronized void a(Map<String, String> map) {
        if (map != null) {
            this.e.putAll(map);
        }
    }

    public final synchronized Map<String, String> a() {
        return new HashMap(this.e);
    }

    public final synchronized void b(Map<String, String> map) {
        this.e.clear();
        if (map != null) {
            this.e.putAll(map);
        }
    }

    public final synchronized byte[] b() {
        Closeable dataOutputStream;
        byte[] toByteArray;
        Closeable closeable;
        Throwable th;
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            try {
                dataOutputStream.writeShort(this.f);
                dataOutputStream.writeUTF(this.a);
                dataOutputStream.writeShort(this.e.size());
                for (Entry entry : this.e.entrySet()) {
                    dataOutputStream.writeUTF(ly.b((String) entry.getKey()));
                    dataOutputStream.writeUTF(ly.b((String) entry.getValue()));
                }
                dataOutputStream.writeLong(this.g);
                dataOutputStream.writeLong(this.d);
                dataOutputStream.flush();
                toByteArray = byteArrayOutputStream.toByteArray();
                ly.a(dataOutputStream);
            } catch (IOException e) {
                closeable = dataOutputStream;
                try {
                    toByteArray = new byte[0];
                    ly.a(closeable);
                    return toByteArray;
                } catch (Throwable th2) {
                    th = th2;
                    dataOutputStream = closeable;
                    ly.a(dataOutputStream);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                ly.a(dataOutputStream);
                throw th;
            }
        } catch (IOException e2) {
            closeable = null;
            toByteArray = new byte[0];
            ly.a(closeable);
            return toByteArray;
        } catch (Throwable th4) {
            dataOutputStream = null;
            th = th4;
            ly.a(dataOutputStream);
            throw th;
        }
        return toByteArray;
    }
}

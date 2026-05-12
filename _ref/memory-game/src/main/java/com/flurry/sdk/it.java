package com.flurry.sdk;

import com.cube.memorygames.games.Game1MemoryGridActivity;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;

public final class it {
    private static final String e = iu.class.getName();
    public long a;
    int b;
    public String c;
    Map<Long, ip> d;
    private long f = System.currentTimeMillis();
    private long g;
    private ix h;
    private boolean i;
    private int j;
    private AtomicInteger k;

    public static class a implements lg<it> {
        lf<ip> a = new lf(new com.flurry.sdk.ip.a());

        public final /* synthetic */ Object a(InputStream inputStream) throws IOException {
            if (inputStream == null) {
                return null;
            }
            DataInputStream anonymousClass2 = new DataInputStream(this, inputStream) {
                final /* synthetic */ a a;

                public final void close() {
                }
            };
            long readLong = anonymousClass2.readLong();
            long readLong2 = anonymousClass2.readLong();
            long readLong3 = anonymousClass2.readLong();
            ix a = ix.a(anonymousClass2.readInt());
            boolean readBoolean = anonymousClass2.readBoolean();
            int readInt = anonymousClass2.readInt();
            String readUTF = anonymousClass2.readUTF();
            int readInt2 = anonymousClass2.readInt();
            int readInt3 = anonymousClass2.readInt();
            it itVar = new it(readUTF, readBoolean, readLong, readLong3, a, null);
            itVar.f = readLong2;
            itVar.b = readInt;
            itVar.j = readInt2;
            itVar.k = new AtomicInteger(readInt3);
            List<ip> b = this.a.b(inputStream);
            if (b != null) {
                itVar.d = new HashMap();
                for (ip ipVar : b) {
                    ipVar.g = itVar;
                    itVar.d.put(Long.valueOf(ipVar.a), ipVar);
                }
            }
            return itVar;
        }

        public final /* synthetic */ void a(OutputStream outputStream, Object obj) throws IOException {
            it itVar = (it) obj;
            if (outputStream != null && itVar != null) {
                DataOutputStream anonymousClass1 = new DataOutputStream(this, outputStream) {
                    final /* synthetic */ a a;

                    public final void close() {
                    }
                };
                anonymousClass1.writeLong(itVar.a);
                anonymousClass1.writeLong(itVar.f);
                anonymousClass1.writeLong(itVar.g);
                anonymousClass1.writeInt(itVar.h.e);
                anonymousClass1.writeBoolean(itVar.i);
                anonymousClass1.writeInt(itVar.b);
                if (itVar.c != null) {
                    anonymousClass1.writeUTF(itVar.c);
                } else {
                    anonymousClass1.writeUTF("");
                }
                anonymousClass1.writeInt(itVar.j);
                anonymousClass1.writeInt(itVar.k.intValue());
                anonymousClass1.flush();
                this.a.a(outputStream, itVar.a());
            }
        }
    }

    public it(String str, boolean z, long j, long j2, ix ixVar, Map<Long, ip> map) {
        this.c = str;
        this.i = z;
        this.a = j;
        this.g = j2;
        this.h = ixVar;
        this.d = map;
        if (map != null) {
            for (Long l : map.keySet()) {
                ((ip) map.get(l)).g = this;
            }
            this.j = map.size();
        } else {
            this.j = 0;
        }
        this.k = new AtomicInteger(0);
    }

    public final List<ip> a() {
        if (this.d != null) {
            return new ArrayList(this.d.values());
        }
        return Collections.emptyList();
    }

    public final synchronized boolean b() {
        return this.k.intValue() >= this.j;
    }

    public final synchronized void c() {
        this.k.incrementAndGet();
    }

    public final byte[] d() throws IOException {
        Throwable e;
        Closeable closeable = null;
        Closeable dataOutputStream;
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            try {
                dataOutputStream.writeShort(this.h.e);
                dataOutputStream.writeLong(this.a);
                dataOutputStream.writeLong(this.g);
                dataOutputStream.writeBoolean(this.i);
                if (this.i) {
                    dataOutputStream.writeShort(this.b);
                    dataOutputStream.writeUTF(this.c);
                }
                dataOutputStream.writeShort(this.d.size());
                if (this.d != null) {
                    for (Entry entry : this.d.entrySet()) {
                        ip ipVar = (ip) entry.getValue();
                        dataOutputStream.writeLong(((Long) entry.getKey()).longValue());
                        dataOutputStream.writeUTF(ipVar.r);
                        dataOutputStream.writeShort(ipVar.f.size());
                        Iterator it = ipVar.f.iterator();
                        while (it.hasNext()) {
                            iq iqVar = (iq) it.next();
                            dataOutputStream.writeShort(iqVar.a);
                            dataOutputStream.writeLong(iqVar.b);
                            dataOutputStream.writeLong(iqVar.c);
                            dataOutputStream.writeBoolean(iqVar.d);
                            dataOutputStream.writeShort(iqVar.e);
                            dataOutputStream.writeShort(iqVar.f.e);
                            if ((iqVar.e < 200 || iqVar.e >= Game1MemoryGridActivity.START_ANIMATION_DURATION) && iqVar.g != null) {
                                byte[] bytes = iqVar.g.getBytes();
                                dataOutputStream.writeShort(bytes.length);
                                dataOutputStream.write(bytes);
                            }
                            dataOutputStream.writeShort(iqVar.h);
                            dataOutputStream.writeInt((int) iqVar.k);
                        }
                    }
                }
                byte[] toByteArray = byteArrayOutputStream.toByteArray();
                ly.a(dataOutputStream);
                return toByteArray;
            } catch (IOException e2) {
                e = e2;
                closeable = dataOutputStream;
                try {
                    km.a(6, e, "Error when generating report", e);
                    throw e;
                } catch (Throwable th) {
                    e = th;
                    dataOutputStream = closeable;
                    ly.a(dataOutputStream);
                    throw e;
                }
            } catch (Throwable th2) {
                e = th2;
                ly.a(dataOutputStream);
                throw e;
            }
        } catch (IOException e3) {
            e = e3;
            km.a(6, e, "Error when generating report", e);
            throw e;
        } catch (Throwable th3) {
            e = th3;
            dataOutputStream = null;
            ly.a(dataOutputStream);
            throw e;
        }
    }
}

package com.flurry.sdk;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ip extends kw {
    private static final String t = ip.class.getName();
    final long a;
    final int b;
    final int c;
    final iw d;
    final Map<String, String> e;
    public ArrayList<iq> f = new ArrayList();
    public it g;
    long h = 30000;
    int i;
    int j;
    String k;
    String l;
    boolean m;

    public static class a implements lg<ip> {
        lf<iq> a = new lf(new com.flurry.sdk.iq.a());

        public final /* synthetic */ void a(OutputStream outputStream, Object obj) throws IOException {
            ip ipVar = (ip) obj;
            if (outputStream != null && ipVar != null) {
                DataOutputStream anonymousClass1 = new DataOutputStream(this, outputStream) {
                    final /* synthetic */ a a;

                    public final void close() {
                    }
                };
                if (ipVar.l != null) {
                    anonymousClass1.writeUTF(ipVar.l);
                } else {
                    anonymousClass1.writeUTF("");
                }
                if (ipVar.r != null) {
                    anonymousClass1.writeUTF(ipVar.r);
                } else {
                    anonymousClass1.writeUTF("");
                }
                anonymousClass1.writeLong(ipVar.n);
                anonymousClass1.writeInt(ipVar.p);
                anonymousClass1.writeLong(ipVar.a);
                anonymousClass1.writeInt(ipVar.b);
                anonymousClass1.writeInt(ipVar.c);
                anonymousClass1.writeInt(ipVar.d.e);
                Map f = ipVar.e;
                if (f != null) {
                    anonymousClass1.writeInt(ipVar.e.size());
                    for (String str : ipVar.e.keySet()) {
                        anonymousClass1.writeUTF(str);
                        anonymousClass1.writeUTF((String) f.get(str));
                    }
                } else {
                    anonymousClass1.writeInt(0);
                }
                anonymousClass1.writeLong(ipVar.h);
                anonymousClass1.writeInt(ipVar.i);
                anonymousClass1.writeInt(ipVar.j);
                if (ipVar.k != null) {
                    anonymousClass1.writeUTF(ipVar.k);
                } else {
                    anonymousClass1.writeUTF("");
                }
                anonymousClass1.writeBoolean(ipVar.m);
                anonymousClass1.flush();
                this.a.a(outputStream, ipVar.f);
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
            String readUTF = anonymousClass2.readUTF();
            if (readUTF.equals("")) {
                readUTF = null;
            }
            String readUTF2 = anonymousClass2.readUTF();
            long readLong = anonymousClass2.readLong();
            int readInt = anonymousClass2.readInt();
            long readLong2 = anonymousClass2.readLong();
            int readInt2 = anonymousClass2.readInt();
            int readInt3 = anonymousClass2.readInt();
            iw a = iw.a(anonymousClass2.readInt());
            Map map = null;
            int readInt4 = anonymousClass2.readInt();
            if (readInt4 != 0) {
                map = new HashMap();
                for (int i = 0; i < readInt4; i++) {
                    map.put(anonymousClass2.readUTF(), anonymousClass2.readUTF());
                }
            }
            long readLong3 = anonymousClass2.readLong();
            readInt4 = anonymousClass2.readInt();
            int readInt5 = anonymousClass2.readInt();
            String readUTF3 = anonymousClass2.readUTF();
            if (readUTF3.equals("")) {
                readUTF3 = null;
            }
            boolean readBoolean = anonymousClass2.readBoolean();
            ip ipVar = new ip(readUTF, readLong2, readUTF2, readLong, readInt2, readInt3, a, map, readInt4, readInt5, readUTF3);
            ipVar.h = readLong3;
            ipVar.m = readBoolean;
            ipVar.p = readInt;
            ipVar.f = (ArrayList) this.a.b(inputStream);
            ipVar.d();
            return ipVar;
        }
    }

    public ip(String str, long j, String str2, long j2, int i, int i2, iw iwVar, Map<String, String> map, int i3, int i4, String str3) {
        a(str2);
        this.n = j2;
        a_();
        this.l = str;
        this.a = j;
        this.s = i;
        this.b = i;
        this.c = i2;
        this.d = iwVar;
        this.e = map;
        this.i = i3;
        this.j = i4;
        this.k = str3;
    }

    public final void a_() {
        super.a_();
        if (this.p != 1) {
            this.h *= 3;
        }
    }

    public final synchronized void c() {
        this.g.c();
    }

    public final void d() {
        Iterator it = this.f.iterator();
        while (it.hasNext()) {
            ((iq) it.next()).l = this;
        }
    }
}

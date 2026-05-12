package com.flurry.sdk;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class iq {
    private static final String m = iq.class.getName();
    public int a;
    public long b;
    public long c;
    public boolean d;
    public int e;
    public ir f;
    public String g;
    public int h;
    public long i;
    public boolean j;
    public long k = 0;
    public ip l;

    public static class a implements lg<iq> {
        public final /* synthetic */ void a(OutputStream outputStream, Object obj) throws IOException {
            iq iqVar = (iq) obj;
            if (outputStream != null && iqVar != null) {
                DataOutputStream anonymousClass1 = new DataOutputStream(this, outputStream) {
                    final /* synthetic */ a a;

                    public final void close() {
                    }
                };
                anonymousClass1.writeInt(iqVar.a);
                anonymousClass1.writeLong(iqVar.b);
                anonymousClass1.writeLong(iqVar.c);
                anonymousClass1.writeBoolean(iqVar.d);
                anonymousClass1.writeInt(iqVar.e);
                anonymousClass1.writeInt(iqVar.f.e);
                if (iqVar.g != null) {
                    anonymousClass1.writeUTF(iqVar.g);
                } else {
                    anonymousClass1.writeUTF("");
                }
                anonymousClass1.writeInt(iqVar.h);
                anonymousClass1.writeLong(iqVar.i);
                anonymousClass1.writeBoolean(iqVar.j);
                anonymousClass1.writeLong(iqVar.k);
                anonymousClass1.flush();
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
            int readInt = anonymousClass2.readInt();
            long readLong = anonymousClass2.readLong();
            long readLong2 = anonymousClass2.readLong();
            boolean readBoolean = anonymousClass2.readBoolean();
            int readInt2 = anonymousClass2.readInt();
            ir a = ir.a(anonymousClass2.readInt());
            String readUTF = anonymousClass2.readUTF();
            int readInt3 = anonymousClass2.readInt();
            long readLong3 = anonymousClass2.readLong();
            boolean readBoolean2 = anonymousClass2.readBoolean();
            long readLong4 = anonymousClass2.readLong();
            Object iqVar = new iq(null, readLong, readLong2, readInt);
            iqVar.d = readBoolean;
            iqVar.e = readInt2;
            iqVar.f = a;
            iqVar.g = readUTF;
            iqVar.h = readInt3;
            iqVar.i = readLong3;
            iqVar.j = readBoolean2;
            iqVar.k = readLong4;
            return iqVar;
        }
    }

    public iq(ip ipVar, long j, long j2, int i) {
        this.l = ipVar;
        this.b = j;
        this.c = j2;
        this.a = i;
        this.e = 0;
        this.f = ir.PENDING_COMPLETION;
    }

    public final void a() {
        this.l.f.add(this);
        if (this.d) {
            this.l.m = true;
        }
    }
}

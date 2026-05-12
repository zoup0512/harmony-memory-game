package com.flurry.sdk;

import android.text.TextUtils;
import io.fabric.sdk.android.services.network.UrlUtils;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class jf {
    private static final String b = jf.class.getSimpleName();
    byte[] a;

    public static class a implements lg<jf> {
        public final /* synthetic */ void a(OutputStream outputStream, Object obj) throws IOException {
            jf jfVar = (jf) obj;
            if (outputStream != null && jfVar != null) {
                DataOutputStream anonymousClass1 = new DataOutputStream(this, outputStream) {
                    final /* synthetic */ a a;

                    public final void close() {
                    }
                };
                int i = 0;
                if (jfVar.a != null) {
                    i = jfVar.a.length;
                }
                anonymousClass1.writeShort(i);
                if (i > 0) {
                    anonymousClass1.write(jfVar.a);
                }
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
            Object jfVar = new jf();
            int readUnsignedShort = anonymousClass2.readUnsignedShort();
            if (readUnsignedShort <= 0) {
                return jfVar;
            }
            byte[] bArr = new byte[readUnsignedShort];
            anonymousClass2.readFully(bArr);
            jfVar.a = bArr;
            return jfVar;
        }
    }

    private jf() {
    }

    public jf(byte[] bArr) {
        this.a = bArr;
    }

    public jf(jg jgVar) throws IOException {
        Throwable e;
        Closeable dataOutputStream;
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            try {
                Closeable closeable;
                int b;
                boolean z;
                List<byte[]> arrayList;
                List<jc> list;
                Map a;
                String str;
                String str2;
                List<ja> list2;
                int i;
                int i2;
                dataOutputStream.writeShort(9);
                dataOutputStream.writeUTF(jgVar.a);
                dataOutputStream.writeLong(jgVar.b);
                dataOutputStream.writeLong(jgVar.c);
                dataOutputStream.writeLong(jgVar.d);
                dataOutputStream.writeBoolean(true);
                dataOutputStream.writeByte(-1);
                if (TextUtils.isEmpty(jgVar.f)) {
                    dataOutputStream.writeBoolean(false);
                } else {
                    dataOutputStream.writeBoolean(true);
                    dataOutputStream.writeUTF(jgVar.f);
                }
                if (TextUtils.isEmpty(jgVar.g)) {
                    dataOutputStream.writeBoolean(false);
                } else {
                    dataOutputStream.writeBoolean(true);
                    dataOutputStream.writeUTF(jgVar.g);
                }
                Map map = jgVar.h;
                if (map == null) {
                    dataOutputStream.writeShort(0);
                } else {
                    dataOutputStream.writeShort(map.size());
                    for (Entry entry : map.entrySet()) {
                        dataOutputStream.writeUTF((String) entry.getKey());
                        dataOutputStream.writeUTF((String) entry.getValue());
                    }
                }
                map = jgVar.e;
                if (map == null) {
                    dataOutputStream.writeShort(0);
                } else {
                    dataOutputStream.writeShort(map.size());
                    for (Entry entry2 : map.entrySet()) {
                        dataOutputStream.writeUTF((String) entry2.getKey());
                        dataOutputStream.writeUTF((String) entry2.getValue());
                        dataOutputStream.writeByte(0);
                    }
                }
                dataOutputStream.writeUTF(jgVar.i);
                dataOutputStream.writeUTF(jgVar.j);
                dataOutputStream.writeByte(jgVar.k);
                dataOutputStream.writeByte(jgVar.l);
                dataOutputStream.writeUTF(jgVar.m);
                if (jgVar.n == null) {
                    closeable = dataOutputStream;
                } else {
                    b = jp.b();
                    dataOutputStream.writeBoolean(true);
                    dataOutputStream.writeDouble(ly.a(jgVar.n.getLatitude(), b));
                    dataOutputStream.writeDouble(ly.a(jgVar.n.getLongitude(), b));
                    dataOutputStream.writeFloat(jgVar.n.getAccuracy());
                    if (b != -1) {
                        z = true;
                        Closeable closeable2 = dataOutputStream;
                        r3.writeBoolean(z);
                        dataOutputStream.writeInt(jgVar.o);
                        dataOutputStream.writeByte(-1);
                        dataOutputStream.writeByte(-1);
                        dataOutputStream.writeByte(jgVar.p);
                        if (jgVar.q != null) {
                            dataOutputStream.writeBoolean(false);
                        } else {
                            dataOutputStream.writeBoolean(true);
                            dataOutputStream.writeLong(jgVar.q.longValue());
                        }
                        map = jgVar.r;
                        if (map != null) {
                            dataOutputStream.writeShort(0);
                        } else {
                            dataOutputStream.writeShort(map.size());
                            for (Entry entry22 : map.entrySet()) {
                                dataOutputStream.writeUTF((String) entry22.getKey());
                                dataOutputStream.writeInt(((jb) entry22.getValue()).a);
                            }
                        }
                        arrayList = new ArrayList();
                        list = jgVar.s;
                        if (list != null) {
                            dataOutputStream.writeShort(0);
                        } else {
                            dataOutputStream.writeShort(list.size());
                            for (jc jcVar : list) {
                                a = jcVar.a();
                                str = (String) a.get("fl.OrderJSON");
                                str2 = (String) a.get("fl.OrderJSONSignature");
                                if (!(str == null || str2 == null)) {
                                    a.remove("fl.OrderJSON");
                                    a.remove("fl.OrderJSONSignature");
                                    jcVar.b(a);
                                    arrayList.add((str2 + '\n' + str).getBytes(UrlUtils.UTF8));
                                }
                                dataOutputStream.write(jcVar.b());
                            }
                        }
                        dataOutputStream.writeBoolean(jgVar.t);
                        list2 = jgVar.v;
                        if (list2 == null) {
                            i = 0;
                            i2 = 0;
                            for (ja a2 : list2) {
                                b = a2.a().length + i;
                                if (b > 160000) {
                                    km.a(5, b, "Error Log size exceeded. No more event details logged.");
                                    i = i2;
                                    break;
                                }
                                i2++;
                                i = b;
                            }
                            i = i2;
                        } else {
                            i = 0;
                        }
                        dataOutputStream.writeInt(jgVar.u);
                        dataOutputStream.writeShort(i);
                        for (i2 = 0; i2 < i; i2++) {
                            dataOutputStream.write(((ja) list2.get(i2)).a());
                        }
                        dataOutputStream.writeInt(-1);
                        dataOutputStream.writeShort(0);
                        dataOutputStream.writeShort(0);
                        dataOutputStream.writeShort(0);
                        dataOutputStream.writeShort(arrayList.size());
                        for (byte[] bArr : arrayList) {
                            dataOutputStream.writeByte(2);
                            dataOutputStream.write(bArr);
                        }
                        this.a = byteArrayOutputStream.toByteArray();
                        ly.a(dataOutputStream);
                    }
                    closeable = dataOutputStream;
                }
                DataOutputStream dataOutputStream2 = closeable;
                z = false;
                dataOutputStream2.writeBoolean(z);
                dataOutputStream.writeInt(jgVar.o);
                dataOutputStream.writeByte(-1);
                dataOutputStream.writeByte(-1);
                dataOutputStream.writeByte(jgVar.p);
                if (jgVar.q != null) {
                    dataOutputStream.writeBoolean(true);
                    dataOutputStream.writeLong(jgVar.q.longValue());
                } else {
                    dataOutputStream.writeBoolean(false);
                }
                map = jgVar.r;
                if (map != null) {
                    dataOutputStream.writeShort(map.size());
                    for (Entry entry222 : map.entrySet()) {
                        dataOutputStream.writeUTF((String) entry222.getKey());
                        dataOutputStream.writeInt(((jb) entry222.getValue()).a);
                    }
                } else {
                    dataOutputStream.writeShort(0);
                }
                arrayList = new ArrayList();
                list = jgVar.s;
                if (list != null) {
                    dataOutputStream.writeShort(list.size());
                    for (jc jcVar2 : list) {
                        a = jcVar2.a();
                        str = (String) a.get("fl.OrderJSON");
                        str2 = (String) a.get("fl.OrderJSONSignature");
                        a.remove("fl.OrderJSON");
                        a.remove("fl.OrderJSONSignature");
                        jcVar2.b(a);
                        arrayList.add((str2 + '\n' + str).getBytes(UrlUtils.UTF8));
                        dataOutputStream.write(jcVar2.b());
                    }
                } else {
                    dataOutputStream.writeShort(0);
                }
                dataOutputStream.writeBoolean(jgVar.t);
                list2 = jgVar.v;
                if (list2 == null) {
                    i = 0;
                } else {
                    i = 0;
                    i2 = 0;
                    while (r10.hasNext()) {
                        b = a2.a().length + i;
                        if (b > 160000) {
                            km.a(5, b, "Error Log size exceeded. No more event details logged.");
                            i = i2;
                            break;
                        }
                        i2++;
                        i = b;
                    }
                    i = i2;
                }
                dataOutputStream.writeInt(jgVar.u);
                dataOutputStream.writeShort(i);
                for (i2 = 0; i2 < i; i2++) {
                    dataOutputStream.write(((ja) list2.get(i2)).a());
                }
                dataOutputStream.writeInt(-1);
                dataOutputStream.writeShort(0);
                dataOutputStream.writeShort(0);
                dataOutputStream.writeShort(0);
                dataOutputStream.writeShort(arrayList.size());
                for (byte[] bArr2 : arrayList) {
                    dataOutputStream.writeByte(2);
                    dataOutputStream.write(bArr2);
                }
                this.a = byteArrayOutputStream.toByteArray();
                ly.a(dataOutputStream);
            } catch (IOException e2) {
                e = e2;
                try {
                    km.a(6, b, "", e);
                    throw e;
                } catch (Throwable th) {
                    e = th;
                    ly.a(dataOutputStream);
                    throw e;
                }
            }
        } catch (IOException e3) {
            e = e3;
            dataOutputStream = null;
            km.a(6, b, "", e);
            throw e;
        } catch (Throwable th2) {
            e = th2;
            dataOutputStream = null;
            ly.a(dataOutputStream);
            throw e;
        }
    }
}

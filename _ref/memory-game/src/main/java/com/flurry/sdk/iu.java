package com.flurry.sdk;

import android.os.Build;
import android.os.Build.VERSION;
import com.amazonaws.services.s3.util.Mimetypes;
import com.flurry.sdk.iv.a;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.zip.CRC32;

public class iu {
    public static final String a = iu.class.getName();
    private static iu c = null;
    public String b;
    private kf<List<iv>> d;
    private List<iv> e;
    private boolean f;

    private iu() {
    }

    public static synchronized iu a() {
        iu iuVar;
        synchronized (iu.class) {
            if (c == null) {
                iu iuVar2 = new iu();
                c = iuVar2;
                iuVar2.d = new kf(jy.a().a.getFileStreamPath(".yflurrypulselogging." + Long.toString(ly.i(jy.a().d), 16)), ".yflurrypulselogging.", 1, new lj<List<iv>>(iuVar2) {
                    final /* synthetic */ iu a;

                    {
                        this.a = r1;
                    }

                    public final lg<List<iv>> a(int i) {
                        return new lf(new a());
                    }
                });
                iuVar2.f = ((Boolean) lp.a().a("UseHttps")).booleanValue();
                km.a(4, a, "initSettings, UseHttps = " + iuVar2.f);
                iuVar2.e = (List) iuVar2.d.a();
                if (iuVar2.e == null) {
                    iuVar2.e = new ArrayList();
                }
            }
            iuVar = c;
        }
        return iuVar;
    }

    public final synchronized void a(it itVar) {
        try {
            this.e.add(new iv(itVar.d()));
            km.a(4, a, "Saving persistent Pulse logging data.");
            this.d.a(this.e);
        } catch (IOException e) {
            km.a(6, a, "Error when generating pulse log report in addReport part");
        }
    }

    public final synchronized void b() {
        try {
            a(d());
        } catch (IOException e) {
            km.a(6, a, "Report not send due to exception in generate data");
        }
    }

    private synchronized void a(byte[] bArr) {
        if (jr.a().b) {
            if (bArr != null) {
                if (bArr.length != 0) {
                    String str;
                    if (this.b != null) {
                        str = this.b;
                    } else {
                        str = "https://data.flurry.com/pcr.do";
                    }
                    km.a(4, a, "PulseLoggingManager: start upload data " + Arrays.toString(bArr) + " to " + str);
                    mb ksVar = new ks();
                    ksVar.g = str;
                    ksVar.u = 100000;
                    ksVar.h = ku.a.kPost;
                    ksVar.k = true;
                    ksVar.a("Content-Type", Mimetypes.MIMETYPE_OCTET_STREAM);
                    ksVar.c = new lc();
                    ksVar.b = bArr;
                    ksVar.a = new ks.a<byte[], Void>(this) {
                        final /* synthetic */ iu a;

                        {
                            this.a = r1;
                        }

                        public final /* synthetic */ void a(ks ksVar, Object obj) {
                            int i = ksVar.q;
                            if (i <= 0) {
                                km.e(iu.a, "Server Error: " + i);
                            } else if (i < 200 || i >= 300) {
                                km.a(3, iu.a, "Pulse logging report sent unsuccessfully, HTTP response:" + i);
                            } else {
                                km.a(3, iu.a, "Pulse logging report sent successfully HTTP response:" + i);
                                this.a.e.clear();
                                this.a.d.a(this.a.e);
                            }
                        }
                    };
                    jw.a().a((Object) this, ksVar);
                }
            }
            km.a(3, a, "No report need be sent");
        } else {
            km.a(5, a, "Reports were not sent! No Internet connection!");
        }
    }

    private byte[] d() throws IOException {
        Closeable dataOutputStream;
        Throwable e;
        Closeable closeable = null;
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            try {
                byte[] toByteArray;
                if (this.e == null || this.e.isEmpty()) {
                    toByteArray = byteArrayOutputStream.toByteArray();
                    ly.a(dataOutputStream);
                } else {
                    dataOutputStream.writeShort(1);
                    dataOutputStream.writeShort(1);
                    dataOutputStream.writeLong(System.currentTimeMillis());
                    dataOutputStream.writeUTF(jy.a().d);
                    dataOutputStream.writeUTF(ju.a().g());
                    dataOutputStream.writeShort(jz.b());
                    dataOutputStream.writeShort(3);
                    ju.a();
                    dataOutputStream.writeUTF(ju.c());
                    dataOutputStream.writeBoolean(jl.a().c());
                    List<ia> arrayList = new ArrayList();
                    for (Entry entry : Collections.unmodifiableMap(jl.a().a).entrySet()) {
                        ia iaVar = new ia();
                        iaVar.a = ((jt) entry.getKey()).c;
                        if (((jt) entry.getKey()).d) {
                            iaVar.b = new String((byte[]) entry.getValue());
                        } else {
                            iaVar.b = ly.b((byte[]) entry.getValue());
                        }
                        arrayList.add(iaVar);
                    }
                    dataOutputStream.writeShort(arrayList.size());
                    for (ia iaVar2 : arrayList) {
                        dataOutputStream.writeShort(iaVar2.a);
                        toByteArray = iaVar2.b.getBytes();
                        dataOutputStream.writeShort(toByteArray.length);
                        dataOutputStream.write(toByteArray);
                    }
                    dataOutputStream.writeShort(6);
                    dataOutputStream.writeShort(in.b - 1);
                    dataOutputStream.writeUTF(Build.MODEL);
                    dataOutputStream.writeShort(in.c - 1);
                    dataOutputStream.writeUTF(Build.BOARD);
                    dataOutputStream.writeShort(in.d - 1);
                    dataOutputStream.writeUTF(Build.ID);
                    dataOutputStream.writeShort(in.e - 1);
                    dataOutputStream.writeUTF(Build.DEVICE);
                    dataOutputStream.writeShort(in.f - 1);
                    dataOutputStream.writeUTF(Build.PRODUCT);
                    dataOutputStream.writeShort(in.g - 1);
                    dataOutputStream.writeUTF(VERSION.RELEASE);
                    dataOutputStream.writeShort(this.e.size());
                    for (iv ivVar : this.e) {
                        dataOutputStream.write(ivVar.a);
                    }
                    toByteArray = byteArrayOutputStream.toByteArray();
                    CRC32 crc32 = new CRC32();
                    crc32.update(toByteArray);
                    dataOutputStream.writeInt((int) crc32.getValue());
                    toByteArray = byteArrayOutputStream.toByteArray();
                    ly.a(dataOutputStream);
                }
                return toByteArray;
            } catch (IOException e2) {
                e = e2;
                closeable = dataOutputStream;
            } catch (Throwable th) {
                e = th;
            }
        } catch (IOException e3) {
            e = e3;
            try {
                km.a(6, a, "Error when generating report", e);
                throw e;
            } catch (Throwable th2) {
                e = th2;
                dataOutputStream = closeable;
                ly.a(dataOutputStream);
                throw e;
            }
        } catch (Throwable th3) {
            e = th3;
            dataOutputStream = null;
            ly.a(dataOutputStream);
            throw e;
        }
    }
}

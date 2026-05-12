package com.flurry.sdk;

import com.flurry.sdk.lb.a;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class la {
    public static final Integer a = Integer.valueOf(50);
    private static final String d = la.class.getSimpleName();
    String b;
    LinkedHashMap<String, List<String>> c;

    public la(String str) {
        this.b = str + "Main";
        b(this.b);
    }

    private void b(String str) {
        this.c = new LinkedHashMap();
        List<String> arrayList = new ArrayList();
        if (c(str)) {
            Collection d = d(str);
            if (d != null && d.size() > 0) {
                arrayList.addAll(d);
                for (String e : arrayList) {
                    e(e);
                }
            }
            f(str);
        } else {
            List<lb> list = (List) new kf(jy.a().a.getFileStreamPath(g(this.b)), str, 1, new lj<List<lb>>(this) {
                final /* synthetic */ la a;

                {
                    this.a = r1;
                }

                public final lg<List<lb>> a(int i) {
                    return new lf(new a());
                }
            }).a();
            if (list == null) {
                km.c(d, "New main file also not found. returning..");
                return;
            }
            for (lb lbVar : list) {
                arrayList.add(lbVar.a);
            }
        }
        for (String e2 : arrayList) {
            this.c.put(e2, h(e2));
        }
    }

    private synchronized boolean c(String str) {
        File fileStreamPath;
        fileStreamPath = jy.a().a.getFileStreamPath(".FlurrySenderIndex.info." + str);
        km.a(5, d, "isOldIndexFilePresent: for " + str + fileStreamPath.exists());
        return fileStreamPath.exists();
    }

    private synchronized List<String> d(String str) {
        Closeable dataInputStream;
        Throwable th;
        Throwable th2;
        Throwable th3;
        List<String> list = null;
        synchronized (this) {
            ly.b();
            km.a(5, d, "Reading Index File for " + str + " file name:" + jy.a().a.getFileStreamPath(".FlurrySenderIndex.info." + str));
            File fileStreamPath = jy.a().a.getFileStreamPath(".FlurrySenderIndex.info." + str);
            List<String> arrayList;
            if (fileStreamPath.exists()) {
                km.a(5, d, "Reading Index File for " + str + " Found file.");
                try {
                    dataInputStream = new DataInputStream(new FileInputStream(fileStreamPath));
                    try {
                        int readUnsignedShort = dataInputStream.readUnsignedShort();
                        if (readUnsignedShort == 0) {
                            ly.a(dataInputStream);
                        } else {
                            arrayList = new ArrayList(readUnsignedShort);
                            int i = 0;
                            while (i < readUnsignedShort) {
                                try {
                                    int readUnsignedShort2 = dataInputStream.readUnsignedShort();
                                    km.a(4, d, "read iter " + i + " dataLength = " + readUnsignedShort2);
                                    byte[] bArr = new byte[readUnsignedShort2];
                                    dataInputStream.readFully(bArr);
                                    arrayList.add(new String(bArr));
                                    i++;
                                } catch (Throwable th4) {
                                    th = th4;
                                }
                            }
                            dataInputStream.readUnsignedShort();
                            ly.a(dataInputStream);
                            list = arrayList;
                        }
                    } catch (Throwable th32) {
                        th2 = th32;
                        arrayList = null;
                        th = th2;
                        try {
                            km.a(6, d, "Error when loading persistent file", th);
                            ly.a(dataInputStream);
                            list = arrayList;
                            return list;
                        } catch (Throwable th5) {
                            th32 = th5;
                            ly.a(dataInputStream);
                            throw th32;
                        }
                    }
                } catch (Throwable th6) {
                    th32 = th6;
                    dataInputStream = null;
                    ly.a(dataInputStream);
                    throw th32;
                }
            }
            km.a(5, d, "Agent cache file doesn't exist.");
            arrayList = null;
            list = arrayList;
        }
        return list;
    }

    private void e(String str) {
        List<String> d = d(str);
        if (d == null) {
            km.c(d, "No old file to replace");
            return;
        }
        for (String str2 : d) {
            byte[] i = i(str2);
            if (i == null) {
                km.a(6, d, "File does not exist");
            } else {
                a(str2, i);
                ly.b();
                km.a(5, d, "Deleting  block File for " + str2 + " file name:" + jy.a().a.getFileStreamPath(".flurrydatasenderblock." + str2));
                File fileStreamPath = jy.a().a.getFileStreamPath(".flurrydatasenderblock." + str2);
                if (fileStreamPath.exists()) {
                    km.a(5, d, "Found file for " + str2 + ". Deleted - " + fileStreamPath.delete());
                }
            }
        }
        a(str, d, ".YFlurrySenderIndex.info.");
        f(str);
    }

    private static void f(String str) {
        ly.b();
        km.a(5, d, "Deleting Index File for " + str + " file name:" + jy.a().a.getFileStreamPath(".FlurrySenderIndex.info." + str));
        File fileStreamPath = jy.a().a.getFileStreamPath(".FlurrySenderIndex.info." + str);
        if (fileStreamPath.exists()) {
            km.a(5, d, "Found file for " + str + ". Deleted - " + fileStreamPath.delete());
        }
    }

    private static String g(String str) {
        return ".YFlurrySenderIndex.info." + str;
    }

    private synchronized List<String> h(String str) {
        List<String> arrayList;
        ly.b();
        km.a(5, d, "Reading Index File for " + str + " file name:" + jy.a().a.getFileStreamPath(g(str)));
        List<lb> list = (List) new kf(jy.a().a.getFileStreamPath(g(str)), ".YFlurrySenderIndex.info.", 1, new lj<List<lb>>(this) {
            final /* synthetic */ la a;

            {
                this.a = r1;
            }

            public final lg<List<lb>> a(int i) {
                return new lf(new a());
            }
        }).a();
        arrayList = new ArrayList();
        for (lb lbVar : list) {
            arrayList.add(lbVar.a);
        }
        return arrayList;
    }

    private static byte[] i(String str) {
        Closeable dataInputStream;
        Throwable th;
        Throwable th2;
        byte[] bArr = null;
        ly.b();
        km.a(5, d, "Reading block File for " + str + " file name:" + jy.a().a.getFileStreamPath(".flurrydatasenderblock." + str));
        File fileStreamPath = jy.a().a.getFileStreamPath(".flurrydatasenderblock." + str);
        if (fileStreamPath.exists()) {
            km.a(5, d, "Reading Index File for " + str + " Found file.");
            try {
                dataInputStream = new DataInputStream(new FileInputStream(fileStreamPath));
                try {
                    int readUnsignedShort = dataInputStream.readUnsignedShort();
                    if (readUnsignedShort == 0) {
                        ly.a(dataInputStream);
                    } else {
                        bArr = new byte[readUnsignedShort];
                        dataInputStream.readFully(bArr);
                        dataInputStream.readUnsignedShort();
                        ly.a(dataInputStream);
                    }
                } catch (Throwable th3) {
                    th = th3;
                    try {
                        km.a(6, d, "Error when loading persistent file", th);
                        ly.a(dataInputStream);
                        return bArr;
                    } catch (Throwable th4) {
                        th2 = th4;
                        ly.a(dataInputStream);
                        throw th2;
                    }
                }
            } catch (Throwable th5) {
                dataInputStream = null;
                th2 = th5;
                ly.a(dataInputStream);
                throw th2;
            }
        }
        km.a(4, d, "Agent cache file doesn't exist.");
        return bArr;
    }

    private synchronized void a(String str, byte[] bArr) {
        ly.b();
        km.a(5, d, "Saving Block File for " + str + " file name:" + jy.a().a.getFileStreamPath(kz.a(str)));
        new kf(jy.a().a.getFileStreamPath(kz.a(str)), ".yflurrydatasenderblock.", 1, new lj<kz>(this) {
            final /* synthetic */ la a;

            {
                this.a = r1;
            }

            public final lg<kz> a(int i) {
                return new kz.a();
            }
        }).a(new kz(bArr));
    }

    private synchronized void a(String str, List<String> list, String str2) {
        ly.b();
        km.a(5, d, "Saving Index File for " + str + " file name:" + jy.a().a.getFileStreamPath(g(str)));
        kf kfVar = new kf(jy.a().a.getFileStreamPath(g(str)), str2, 1, new lj<List<lb>>(this) {
            final /* synthetic */ la a;

            {
                this.a = r1;
            }

            public final lg<List<lb>> a(int i) {
                return new lf(new a());
            }
        });
        List arrayList = new ArrayList();
        for (String lbVar : list) {
            arrayList.add(new lb(lbVar));
        }
        kfVar.a(arrayList);
    }

    public final synchronized void a(kz kzVar, String str) {
        Object obj = null;
        synchronized (this) {
            List linkedList;
            km.a(4, d, "addBlockInfo" + str);
            String str2 = kzVar.a;
            List list = (List) this.c.get(str);
            if (list == null) {
                km.a(4, d, "New Data Key");
                linkedList = new LinkedList();
                obj = 1;
            } else {
                linkedList = list;
            }
            linkedList.add(str2);
            if (linkedList.size() > a.intValue()) {
                j((String) linkedList.get(0));
                linkedList.remove(0);
            }
            this.c.put(str, linkedList);
            a(str, linkedList, ".YFlurrySenderIndex.info.");
            if (obj != null) {
                a();
            }
        }
    }

    private boolean j(String str) {
        return new kf(jy.a().a.getFileStreamPath(kz.a(str)), ".yflurrydatasenderblock.", 1, new lj<kz>(this) {
            final /* synthetic */ la a;

            {
                this.a = r1;
            }

            public final lg<kz> a(int i) {
                return new kz.a();
            }
        }).b();
    }

    private synchronized void a() {
        List linkedList = new LinkedList(this.c.keySet());
        new kf(jy.a().a.getFileStreamPath(g(this.b)), ".YFlurrySenderIndex.info.", 1, new lj<List<lb>>(this) {
            final /* synthetic */ la a;

            {
                this.a = r1;
            }

            public final lg<List<lb>> a(int i) {
                return new lf(new a());
            }
        }).b();
        if (!linkedList.isEmpty()) {
            a(this.b, linkedList, this.b);
        }
    }

    public final boolean a(String str, String str2) {
        List list = (List) this.c.get(str2);
        boolean z = false;
        if (list != null) {
            j(str);
            z = list.remove(str);
        }
        if (list == null || list.isEmpty()) {
            k(str2);
        } else {
            this.c.put(str2, list);
            a(str2, list, ".YFlurrySenderIndex.info.");
        }
        return z;
    }

    private synchronized boolean k(String str) {
        boolean b;
        ly.b();
        kf kfVar = new kf(jy.a().a.getFileStreamPath(g(str)), ".YFlurrySenderIndex.info.", 1, new lj<List<lb>>(this) {
            final /* synthetic */ la a;

            {
                this.a = r1;
            }

            public final lg<List<lb>> a(int i) {
                return new lf(new a());
            }
        });
        List<String> a = a(str);
        if (a != null) {
            km.a(4, d, "discardOutdatedBlocksForDataKey: notSentBlocks = " + a.size());
            for (String str2 : a) {
                j(str2);
                km.a(4, d, "discardOutdatedBlocksForDataKey: removed block = " + str2);
            }
        }
        this.c.remove(str);
        b = kfVar.b();
        a();
        return b;
    }

    public final List<String> a(String str) {
        return (List) this.c.get(str);
    }
}

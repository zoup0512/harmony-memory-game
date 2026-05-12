package com.flurry.sdk;

import com.amazonaws.services.s3.internal.Constants;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class jl {
    private static final String b = jl.class.getSimpleName();
    private static jl c;
    public final Map<jt, byte[]> a = new HashMap();
    private final Set<String> d;
    private a e = a.NONE;
    private jv f;
    private String g;
    private final kh<ll> h = new kh<ll>(this) {
        final /* synthetic */ jl a;

        {
            this.a = r1;
        }

        public final /* synthetic */ void a(kg kgVar) {
            switch (AnonymousClass4.a[((ll) kgVar).c - 1]) {
                case 1:
                    if (this.a.b()) {
                        jy.a().b(new ma(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public final void a() {
                                this.a.a.d();
                            }
                        });
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };

    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] a = new int[com.flurry.sdk.ll.a.a().length];

        static {
            b = new int[a.values().length];
            try {
                b[a.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                b[a.ADVERTISING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                b[a.DEVICE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                b[a.REPORTED_IDS.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                a[com.flurry.sdk.ll.a.a - 1] = 1;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    enum a {
        NONE,
        ADVERTISING,
        DEVICE,
        REPORTED_IDS,
        FINISHED
    }

    private jl() {
        Set hashSet = new HashSet();
        hashSet.add(Constants.NULL_VERSION_ID);
        hashSet.add("9774d56d682e549c");
        hashSet.add("dead00beef");
        this.d = Collections.unmodifiableSet(hashSet);
        ki.a().a("com.flurry.android.sdk.FlurrySessionEvent", this.h);
        jy.a().b(new ma(this) {
            final /* synthetic */ jl a;

            {
                this.a = r1;
            }

            public final void a() {
                jl.b(this.a);
            }
        });
    }

    public static synchronized jl a() {
        jl jlVar;
        synchronized (jl.class) {
            if (c == null) {
                c = new jl();
            }
            jlVar = c;
        }
        return jlVar;
    }

    public final boolean b() {
        return a.FINISHED.equals(this.e);
    }

    public final boolean c() {
        if (this.f != null && this.f.b) {
            return false;
        }
        return true;
    }

    private void d() {
        ly.b();
        this.f = e();
        if (b()) {
            h();
            ki.a().a(new jn());
        }
    }

    private static jv e() {
        try {
            Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(jy.a().a);
            return new jv(advertisingIdInfo.getId(), advertisingIdInfo.isLimitAdTrackingEnabled());
        } catch (NoClassDefFoundError e) {
            km.b(b, "There is a problem with the Google Play Services library, which is required for Android Advertising ID support. The Google Play Services library should be integrated in any app shipping in the Play Store that uses analytics or advertising.");
            return null;
        } catch (Exception e2) {
            km.b(b, "GOOGLE PLAY SERVICES ERROR: " + e2.getMessage());
            km.b(b, "There is a problem with the Google Play Services library, which is required for Android Advertising ID support. The Google Play Services library should be integrated in any app shipping in the Play Store that uses analytics or advertising.");
            return null;
        }
    }

    private static void a(String str, File file) {
        Closeable dataOutputStream;
        Throwable th;
        try {
            dataOutputStream = new DataOutputStream(new FileOutputStream(file));
            try {
                dataOutputStream.writeInt(1);
                dataOutputStream.writeUTF(str);
                ly.a(dataOutputStream);
            } catch (Throwable th2) {
                th = th2;
                try {
                    km.a(6, b, "Error when saving deviceId", th);
                    ly.a(dataOutputStream);
                } catch (Throwable th3) {
                    th = th3;
                    ly.a(dataOutputStream);
                    throw th;
                }
            }
        } catch (Throwable th4) {
            th = th4;
            dataOutputStream = null;
            ly.a(dataOutputStream);
            throw th;
        }
    }

    private static String f() {
        Closeable dataInputStream;
        Throwable th;
        Throwable th2;
        String str = null;
        File fileStreamPath = jy.a().a.getFileStreamPath(".flurryb.");
        if (fileStreamPath != null && fileStreamPath.exists()) {
            try {
                dataInputStream = new DataInputStream(new FileInputStream(fileStreamPath));
                try {
                    if (1 == dataInputStream.readInt()) {
                        str = dataInputStream.readUTF();
                    }
                    ly.a(dataInputStream);
                } catch (Throwable th3) {
                    th = th3;
                    try {
                        km.a(6, b, "Error when loading deviceId", th);
                        ly.a(dataInputStream);
                        return str;
                    } catch (Throwable th4) {
                        th2 = th4;
                        ly.a(dataInputStream);
                        throw th2;
                    }
                }
            } catch (Throwable th5) {
                dataInputStream = str;
                th2 = th5;
                ly.a(dataInputStream);
                throw th2;
            }
        }
        return str;
    }

    private String g() {
        Closeable dataInputStream;
        Throwable th;
        Throwable th2;
        String str = null;
        File filesDir = jy.a().a.getFilesDir();
        if (filesDir != null) {
            String[] list = filesDir.list(new FilenameFilter(this) {
                final /* synthetic */ jl a;

                {
                    this.a = r1;
                }

                public final boolean accept(File file, String str) {
                    return str.startsWith(".flurryagent.");
                }
            });
            if (!(list == null || list.length == 0)) {
                filesDir = jy.a().a.getFileStreamPath(list[0]);
                if (filesDir != null && filesDir.exists()) {
                    try {
                        dataInputStream = new DataInputStream(new FileInputStream(filesDir));
                        try {
                            if (46586 == dataInputStream.readUnsignedShort()) {
                                if (2 == dataInputStream.readUnsignedShort()) {
                                    dataInputStream.readUTF();
                                    str = dataInputStream.readUTF();
                                }
                            }
                            ly.a(dataInputStream);
                        } catch (Throwable th3) {
                            th = th3;
                            try {
                                km.a(6, b, "Error when loading deviceId", th);
                                ly.a(dataInputStream);
                                return str;
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
            }
        }
        return str;
    }

    private void h() {
        String str;
        if (this.f == null) {
            str = null;
        } else {
            str = this.f.a;
        }
        if (str != null) {
            km.a(3, b, "Fetched advertising id");
            this.a.put(jt.AndroidAdvertisingId, ly.d(str));
        }
        str = this.g;
        if (str != null) {
            km.a(3, b, "Fetched device id");
            this.a.put(jt.DeviceId, ly.d(str));
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ void b(com.flurry.sdk.jl r8) {
        /*
        r6 = 37;
    L_0x0002:
        r0 = com.flurry.sdk.jl.a.FINISHED;
        r1 = r8.e;
        r0 = r0.equals(r1);
        if (r0 != 0) goto L_0x0111;
    L_0x000c:
        r0 = com.flurry.sdk.jl.AnonymousClass4.b;
        r1 = r8.e;
        r1 = r1.ordinal();
        r0 = r0[r1];
        switch(r0) {
            case 1: goto L_0x004e;
            case 2: goto L_0x0053;
            case 3: goto L_0x0058;
            case 4: goto L_0x005d;
            default: goto L_0x0019;
        };
    L_0x0019:
        r0 = com.flurry.sdk.jl.AnonymousClass4.b;	 Catch:{ Exception -> 0x002b }
        r1 = r8.e;	 Catch:{ Exception -> 0x002b }
        r1 = r1.ordinal();	 Catch:{ Exception -> 0x002b }
        r0 = r0[r1];	 Catch:{ Exception -> 0x002b }
        switch(r0) {
            case 2: goto L_0x0027;
            case 3: goto L_0x0062;
            case 4: goto L_0x010c;
            default: goto L_0x0026;
        };	 Catch:{ Exception -> 0x002b }
    L_0x0026:
        goto L_0x0002;
    L_0x0027:
        r8.d();	 Catch:{ Exception -> 0x002b }
        goto L_0x0002;
    L_0x002b:
        r0 = move-exception;
        r1 = 4;
        r2 = b;
        r3 = new java.lang.StringBuilder;
        r4 = "Exception during id fetch:";
        r3.<init>(r4);
        r4 = r8.e;
        r3 = r3.append(r4);
        r4 = ", ";
        r3 = r3.append(r4);
        r0 = r3.append(r0);
        r0 = r0.toString();
        com.flurry.sdk.km.a(r1, r2, r0);
        goto L_0x0002;
    L_0x004e:
        r0 = com.flurry.sdk.jl.a.ADVERTISING;
        r8.e = r0;
        goto L_0x0019;
    L_0x0053:
        r0 = com.flurry.sdk.jl.a.DEVICE;
        r8.e = r0;
        goto L_0x0019;
    L_0x0058:
        r0 = com.flurry.sdk.jl.a.REPORTED_IDS;
        r8.e = r0;
        goto L_0x0019;
    L_0x005d:
        r0 = com.flurry.sdk.jl.a.FINISHED;
        r8.e = r0;
        goto L_0x0019;
    L_0x0062:
        com.flurry.sdk.ly.b();	 Catch:{ Exception -> 0x002b }
        r0 = com.flurry.sdk.jy.a();	 Catch:{ Exception -> 0x002b }
        r0 = r0.a;	 Catch:{ Exception -> 0x002b }
        r0 = r0.getContentResolver();	 Catch:{ Exception -> 0x002b }
        r1 = "android_id";
        r1 = android.provider.Settings.Secure.getString(r0, r1);	 Catch:{ Exception -> 0x002b }
        r0 = android.text.TextUtils.isEmpty(r1);	 Catch:{ Exception -> 0x002b }
        if (r0 != 0) goto L_0x0097;
    L_0x007b:
        r0 = java.util.Locale.US;	 Catch:{ Exception -> 0x002b }
        r0 = r1.toLowerCase(r0);	 Catch:{ Exception -> 0x002b }
        r2 = r8.d;	 Catch:{ Exception -> 0x002b }
        r0 = r2.contains(r0);	 Catch:{ Exception -> 0x002b }
        if (r0 != 0) goto L_0x0097;
    L_0x0089:
        r0 = 1;
    L_0x008a:
        if (r0 != 0) goto L_0x0099;
    L_0x008c:
        r0 = 0;
    L_0x008d:
        r1 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x002b }
        if (r1 != 0) goto L_0x00a9;
    L_0x0093:
        r8.g = r0;	 Catch:{ Exception -> 0x002b }
        goto L_0x0002;
    L_0x0097:
        r0 = 0;
        goto L_0x008a;
    L_0x0099:
        r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x002b }
        r2 = "AND";
        r0.<init>(r2);	 Catch:{ Exception -> 0x002b }
        r0 = r0.append(r1);	 Catch:{ Exception -> 0x002b }
        r0 = r0.toString();	 Catch:{ Exception -> 0x002b }
        goto L_0x008d;
    L_0x00a9:
        r0 = f();	 Catch:{ Exception -> 0x002b }
        r1 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x002b }
        if (r1 == 0) goto L_0x0093;
    L_0x00b3:
        r0 = r8.g();	 Catch:{ Exception -> 0x002b }
        r1 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x002b }
        if (r1 == 0) goto L_0x00f0;
    L_0x00bd:
        r0 = java.lang.Math.random();	 Catch:{ Exception -> 0x002b }
        r0 = java.lang.Double.doubleToLongBits(r0);	 Catch:{ Exception -> 0x002b }
        r2 = java.lang.System.nanoTime();	 Catch:{ Exception -> 0x002b }
        r4 = com.flurry.sdk.jy.a();	 Catch:{ Exception -> 0x002b }
        r4 = r4.a;	 Catch:{ Exception -> 0x002b }
        r4 = com.flurry.sdk.lv.a(r4);	 Catch:{ Exception -> 0x002b }
        r4 = com.flurry.sdk.ly.i(r4);	 Catch:{ Exception -> 0x002b }
        r4 = r4 * r6;
        r2 = r2 + r4;
        r2 = r2 * r6;
        r0 = r0 + r2;
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x002b }
        r3 = "ID";
        r2.<init>(r3);	 Catch:{ Exception -> 0x002b }
        r3 = 16;
        r0 = java.lang.Long.toString(r0, r3);	 Catch:{ Exception -> 0x002b }
        r0 = r2.append(r0);	 Catch:{ Exception -> 0x002b }
        r0 = r0.toString();	 Catch:{ Exception -> 0x002b }
    L_0x00f0:
        r1 = android.text.TextUtils.isEmpty(r0);	 Catch:{ Exception -> 0x002b }
        if (r1 != 0) goto L_0x0093;
    L_0x00f6:
        r1 = com.flurry.sdk.jy.a();	 Catch:{ Exception -> 0x002b }
        r1 = r1.a;	 Catch:{ Exception -> 0x002b }
        r2 = ".flurryb.";
        r1 = r1.getFileStreamPath(r2);	 Catch:{ Exception -> 0x002b }
        r2 = com.flurry.sdk.lx.a(r1);	 Catch:{ Exception -> 0x002b }
        if (r2 == 0) goto L_0x0093;
    L_0x0108:
        a(r0, r1);	 Catch:{ Exception -> 0x002b }
        goto L_0x0093;
    L_0x010c:
        r8.h();	 Catch:{ Exception -> 0x002b }
        goto L_0x0002;
    L_0x0111:
        r0 = new com.flurry.sdk.jm;
        r0.<init>();
        r1 = com.flurry.sdk.ki.a();
        r1.a(r0);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.flurry.sdk.jl.b(com.flurry.sdk.jl):void");
    }
}

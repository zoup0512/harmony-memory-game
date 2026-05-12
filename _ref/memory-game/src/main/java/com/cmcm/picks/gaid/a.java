package com.cmcm.picks.gaid;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import io.fabric.sdk.android.services.common.AdvertisingInfoServiceStrategy;
import io.fabric.sdk.android.services.common.AdvertisingInfoServiceStrategy.AdvertisingInterface;

/* compiled from: AdvertisingIdHelper */
public class a {
    private static a c = null;
    private String a = "";
    private boolean b = false;

    /* compiled from: AdvertisingIdHelper */
    private static class a implements b {
        private IBinder a;

        public boolean a(boolean r8) throws android.os.RemoteException {
            /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.ssa.SSATransform.placePhi(SSATransform.java:82)
	at jadx.core.dex.visitors.ssa.SSATransform.process(SSATransform.java:50)
	at jadx.core.dex.visitors.ssa.SSATransform.visit(SSATransform.java:42)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
*/
            /*
            r7 = this;
            r0 = 1;
            r1 = 0;
            r3 = android.os.Parcel.obtain();
            r4 = android.os.Parcel.obtain();
            r2 = "com.google.android.gms.ads.identifier.internal.IAdvertisingIdService";	 Catch:{ Exception -> 0x0030, all -> 0x0039 }
            r3.writeInterfaceToken(r2);	 Catch:{ Exception -> 0x0030, all -> 0x0039 }
            if (r8 == 0) goto L_0x002c;	 Catch:{ Exception -> 0x0030, all -> 0x0039 }
        L_0x0011:
            r2 = r0;	 Catch:{ Exception -> 0x0030, all -> 0x0039 }
        L_0x0012:
            r3.writeInt(r2);	 Catch:{ Exception -> 0x0030, all -> 0x0039 }
            r2 = r7.a;	 Catch:{ Exception -> 0x0030, all -> 0x0039 }
            r5 = 2;	 Catch:{ Exception -> 0x0030, all -> 0x0039 }
            r6 = 0;	 Catch:{ Exception -> 0x0030, all -> 0x0039 }
            r2.transact(r5, r3, r4, r6);	 Catch:{ Exception -> 0x0030, all -> 0x0039 }
            r4.readException();	 Catch:{ Exception -> 0x0030, all -> 0x0039 }
            r2 = r4.readInt();	 Catch:{ Exception -> 0x0030, all -> 0x0039 }
            if (r2 == 0) goto L_0x002e;
        L_0x0025:
            r4.recycle();
            r3.recycle();
        L_0x002b:
            return r0;
        L_0x002c:
            r2 = r1;
            goto L_0x0012;
        L_0x002e:
            r0 = r1;
            goto L_0x0025;
        L_0x0030:
            r0 = move-exception;
            r4.recycle();
            r3.recycle();
            r0 = r1;
            goto L_0x002b;
        L_0x0039:
            r0 = move-exception;
            r4.recycle();
            r3.recycle();
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.cmcm.picks.gaid.a.a.a(boolean):boolean");
        }

        a(IBinder iBinder) {
            this.a = iBinder;
        }

        public IBinder asBinder() {
            return this.a;
        }

        public String a() throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken(AdvertisingInterface.ADVERTISING_ID_SERVICE_INTERFACE_TOKEN);
                this.a.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                String readString = obtain2.readString();
                return readString;
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
    }

    static c a(Context context) {
        try {
            context.getPackageManager().getPackageInfo("com.android.vending", 0);
            try {
                Object cVar = new c();
                Intent intent = new Intent(AdvertisingInfoServiceStrategy.GOOGLE_PLAY_SERVICES_INTENT);
                intent.setPackage("com.google.android.gms");
                if (context.bindService(intent, cVar, 1)) {
                    return cVar;
                }
                return null;
            } catch (Exception e) {
                return null;
            }
        } catch (Exception e2) {
            return null;
        }
    }

    public static IInterface a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface(AdvertisingInterface.ADVERTISING_ID_SERVICE_INTERFACE_TOKEN);
        return (queryLocalInterface == null || !(queryLocalInterface instanceof b)) ? new a(iBinder) : queryLocalInterface;
    }

    public String a() {
        return this.a;
    }

    public void b() {
        new Thread(new Runnable(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void run() {
                /*
                r7 = this;
                r1 = 0;
                r3 = com.cmcm.adsdk.CMAdManager.getContext();
                r4 = com.cmcm.picks.gaid.a.a(r3);
                if (r4 != 0) goto L_0x000c;
            L_0x000b:
                return;
            L_0x000c:
                r2 = 0;
                r0 = r4.a();	 Catch:{ Exception -> 0x0045, all -> 0x0055 }
                r0 = com.cmcm.picks.gaid.a.a(r0);	 Catch:{ Exception -> 0x0045, all -> 0x0055 }
                r0 = (com.cmcm.picks.gaid.b) r0;	 Catch:{ Exception -> 0x0045, all -> 0x0055 }
                r2 = r0.a();	 Catch:{ Exception -> 0x0045, all -> 0x0055 }
                r5 = 0;
                r0 = r0.a(r5);	 Catch:{ Exception -> 0x005e, all -> 0x0055 }
                if (r4 == 0) goto L_0x0025;
            L_0x0022:
                r3.unbindService(r4);	 Catch:{ Exception -> 0x0042 }
            L_0x0025:
                r1 = r2;
            L_0x0026:
                r2 = android.text.TextUtils.isEmpty(r1);
                if (r2 != 0) goto L_0x000b;
            L_0x002c:
                r2 = r7.a;
                r2 = r2.a;
                monitor-enter(r2);
                r3 = r7.a;	 Catch:{ all -> 0x003f }
                r3.a = r1;	 Catch:{ all -> 0x003f }
                r1 = r7.a;	 Catch:{ all -> 0x003f }
                r1.b = r0;	 Catch:{ all -> 0x003f }
                monitor-exit(r2);	 Catch:{ all -> 0x003f }
                goto L_0x000b;
            L_0x003f:
                r0 = move-exception;
                monitor-exit(r2);	 Catch:{ all -> 0x003f }
                throw r0;
            L_0x0042:
                r1 = move-exception;
                r1 = r2;
                goto L_0x0026;
            L_0x0045:
                r0 = move-exception;
                r0 = r2;
            L_0x0047:
                if (r4 == 0) goto L_0x004c;
            L_0x0049:
                r3.unbindService(r4);	 Catch:{ Exception -> 0x0050 }
            L_0x004c:
                r6 = r1;
                r1 = r0;
                r0 = r6;
                goto L_0x0026;
            L_0x0050:
                r2 = move-exception;
                r6 = r1;
                r1 = r0;
                r0 = r6;
                goto L_0x0026;
            L_0x0055:
                r0 = move-exception;
                if (r4 == 0) goto L_0x005b;
            L_0x0058:
                r3.unbindService(r4);	 Catch:{ Exception -> 0x005c }
            L_0x005b:
                throw r0;
            L_0x005c:
                r1 = move-exception;
                goto L_0x005b;
            L_0x005e:
                r0 = move-exception;
                r0 = r2;
                goto L_0x0047;
                */
                throw new UnsupportedOperationException("Method not decompiled: com.cmcm.picks.gaid.a.1.run():void");
            }
        }).start();
    }

    public static a c() {
        if (c == null) {
            c = new a();
        }
        return c;
    }

    private a() {
    }
}

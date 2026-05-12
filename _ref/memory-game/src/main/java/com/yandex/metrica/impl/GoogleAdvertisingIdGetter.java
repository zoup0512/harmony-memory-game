package com.yandex.metrica.impl;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Pair;
import com.mopub.common.GpsHelper;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;

public class GoogleAdvertisingIdGetter {
    private volatile String a = null;
    private volatile Boolean b = null;
    private final Object c = new Object();
    private volatile FutureTask<Pair<String, Boolean>> d;

    private interface c<T> {
        T b(Future<Pair<String, Boolean>> future) throws InterruptedException, ExecutionException;
    }

    private interface GoogleAdvertisingInfo extends IInterface {

        public static abstract class GoogleAdvertisingInfoBinder extends Binder implements GoogleAdvertisingInfo {

            private static class GoogleAdvertisingInfoImplementation implements GoogleAdvertisingInfo {
                private IBinder a;

                GoogleAdvertisingInfoImplementation(IBinder binder) {
                    this.a = binder;
                }

                public IBinder asBinder() {
                    return this.a;
                }

                public String getId() throws RemoteException {
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

                public boolean getEnabled(boolean paramBoolean) throws RemoteException {
                    boolean z = true;
                    Parcel obtain = Parcel.obtain();
                    Parcel obtain2 = Parcel.obtain();
                    try {
                        obtain.writeInterfaceToken(AdvertisingInterface.ADVERTISING_ID_SERVICE_INTERFACE_TOKEN);
                        obtain.writeInt(paramBoolean ? 1 : 0);
                        this.a.transact(2, obtain, obtain2, 0);
                        obtain2.readException();
                        if (obtain2.readInt() == 0) {
                            z = false;
                        }
                        obtain2.recycle();
                        obtain.recycle();
                        return z;
                    } catch (Throwable th) {
                        obtain2.recycle();
                        obtain.recycle();
                    }
                }
            }

            public static GoogleAdvertisingInfo Create(IBinder binder) {
                if (binder == null) {
                    return null;
                }
                IInterface queryLocalInterface = binder.queryLocalInterface(AdvertisingInterface.ADVERTISING_ID_SERVICE_INTERFACE_TOKEN);
                if (queryLocalInterface == null || !(queryLocalInterface instanceof GoogleAdvertisingInfo)) {
                    return new GoogleAdvertisingInfoImplementation(binder);
                }
                return (GoogleAdvertisingInfo) queryLocalInterface;
            }

            public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
                int i = 0;
                switch (code) {
                    case 1:
                        data.enforceInterface(AdvertisingInterface.ADVERTISING_ID_SERVICE_INTERFACE_TOKEN);
                        String id = getId();
                        reply.writeNoException();
                        reply.writeString(id);
                        return true;
                    case 2:
                        boolean z;
                        data.enforceInterface(AdvertisingInterface.ADVERTISING_ID_SERVICE_INTERFACE_TOKEN);
                        if (data.readInt() != 0) {
                            z = true;
                        } else {
                            z = false;
                        }
                        z = getEnabled(z);
                        reply.writeNoException();
                        if (z) {
                            i = 1;
                        }
                        reply.writeInt(i);
                        return true;
                    default:
                        return super.onTransact(code, data, reply, flags);
                }
            }
        }

        boolean getEnabled(boolean z) throws RemoteException;

        String getId() throws RemoteException;
    }

    private class a implements ServiceConnection {
        private boolean a;
        private final BlockingQueue<IBinder> b;

        private a() {
            this.a = false;
            this.b = new LinkedBlockingQueue();
        }

        public void onServiceConnected(ComponentName name, IBinder service) {
            try {
                this.b.put(service);
            } catch (InterruptedException e) {
            }
        }

        public void onServiceDisconnected(ComponentName name) {
        }

        public IBinder a() throws InterruptedException {
            if (this.a) {
                throw new IllegalStateException();
            }
            this.a = true;
            return (IBinder) this.b.take();
        }
    }

    private static class b {
        static final GoogleAdvertisingIdGetter a = new GoogleAdvertisingIdGetter();
    }

    static /* synthetic */ void b(GoogleAdvertisingIdGetter googleAdvertisingIdGetter, Context context) {
        ServiceConnection aVar = new a();
        Intent intent = new Intent(AdvertisingInfoServiceStrategy.GOOGLE_PLAY_SERVICES_INTENT);
        intent.setPackage("com.google.android.gms");
        if (context.bindService(intent, aVar, 1)) {
            try {
                GoogleAdvertisingInfo Create = GoogleAdvertisingInfoBinder.Create(aVar.a());
                String id = Create.getId();
                Boolean valueOf = Boolean.valueOf(Create.getEnabled(true));
                synchronized (googleAdvertisingIdGetter) {
                    googleAdvertisingIdGetter.a = id;
                    googleAdvertisingIdGetter.b = valueOf;
                }
            } catch (Exception e) {
            } finally {
                context.unbindService(aVar);
            }
        }
    }

    public static GoogleAdvertisingIdGetter a() {
        return b.a;
    }

    public void a(final Context context) {
        if (this.d == null) {
            synchronized (this.c) {
                if (this.d == null) {
                    this.d = new FutureTask(new Callable<Pair<String, Boolean>>(this) {
                        final /* synthetic */ GoogleAdvertisingIdGetter b;

                        public /* synthetic */ Object call() throws Exception {
                            return a();
                        }

                        public Pair<String, Boolean> a() {
                            Context applicationContext = context.getApplicationContext();
                            if (GoogleAdvertisingIdGetter.d(applicationContext)) {
                                GoogleAdvertisingIdGetter.a(b.a, applicationContext);
                            }
                            if (!this.b.d()) {
                                GoogleAdvertisingIdGetter.b(b.a, applicationContext);
                            }
                            return new Pair(this.b.a, this.b.b);
                        }
                    });
                    new Thread(this.d).start();
                }
            }
        }
    }

    private <T> T a(Context context, c<T> cVar) {
        a(context);
        try {
            return cVar.b(this.d);
        } catch (InterruptedException e) {
            return null;
        } catch (ExecutionException e2) {
            return null;
        }
    }

    public String b(Context context) {
        return (String) a(context, new c<String>() {
            public /* synthetic */ Object b(Future future) throws InterruptedException, ExecutionException {
                return a(future);
            }

            public String a(Future<Pair<String, Boolean>> future) throws InterruptedException, ExecutionException {
                return (String) ((Pair) future.get()).first;
            }
        });
    }

    public String b() {
        return this.a;
    }

    public Boolean c() {
        return this.b;
    }

    public synchronized boolean d() {
        boolean z;
        z = (this.a == null || this.b == null) ? false : true;
        return z;
    }

    private static boolean d(Context context) {
        boolean z = false;
        try {
            z = Class.forName("com.google.android.gms.common.GooglePlayServicesUtil").getMethod("isGooglePlayServicesAvailable", new Class[]{Context.class}).invoke(null, new Object[]{context}).equals(Integer.valueOf(0));
        } catch (Exception e) {
        }
        return z;
    }

    static /* synthetic */ void a(GoogleAdvertisingIdGetter googleAdvertisingIdGetter, Context context) {
        try {
            Object invoke = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient").getMethod("getAdvertisingIdInfo", new Class[]{Context.class}).invoke(null, new Object[]{context});
            Class cls = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient$Info");
            String str = (String) cls.getMethod("getId", new Class[0]).invoke(invoke, new Object[0]);
            Boolean bool = (Boolean) cls.getMethod(GpsHelper.IS_LIMIT_AD_TRACKING_ENABLED_KEY, new Class[0]).invoke(invoke, new Object[0]);
            synchronized (googleAdvertisingIdGetter) {
                googleAdvertisingIdGetter.a = str;
                googleAdvertisingIdGetter.b = bool;
            }
        } catch (Exception e) {
        }
    }
}

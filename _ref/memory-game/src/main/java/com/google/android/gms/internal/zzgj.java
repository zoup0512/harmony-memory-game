package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface zzgj extends IInterface {

    public static abstract class zza extends Binder implements zzgj {

        private static class zza implements zzgj {
            private IBinder zzahn;

            zza(IBinder iBinder) {
                this.zzahn = iBinder;
            }

            public IBinder asBinder() {
                return this.zzahn;
            }

            public zzgk zzbm(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
                    obtain.writeString(str);
                    this.zzahn.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    zzgk zzak = com.google.android.gms.internal.zzgk.zza.zzak(obtain2.readStrongBinder());
                    return zzak;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean zzbn(String str) throws RemoteException {
                boolean z = false;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
                    obtain.writeString(str);
                    this.zzahn.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
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

        public zza() {
            attachInterface(this, "com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
        }

        public static zzgj zzaj(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzgj)) ? new zza(iBinder) : (zzgj) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
                    zzgk zzbm = zzbm(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(zzbm != null ? zzbm.asBinder() : null);
                    return true;
                case 2:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
                    boolean zzbn = zzbn(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(zzbn ? 1 : 0);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.ads.internal.mediation.client.IAdapterCreator");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    zzgk zzbm(String str) throws RemoteException;

    boolean zzbn(String str) throws RemoteException;
}

package com.google.android.gms.ads.internal.client;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zzgj;

public interface zzv extends IInterface {

    public static abstract class zza extends Binder implements zzv {

        private static class zza implements zzv {
            private IBinder zzahn;

            zza(IBinder iBinder) {
                this.zzahn = iBinder;
            }

            public IBinder asBinder() {
                return this.zzahn;
            }

            public IBinder zza(zzd com_google_android_gms_dynamic_zzd, AdSizeParcel adSizeParcel, String str, zzgj com_google_android_gms_internal_zzgj, int i) throws RemoteException {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManagerCreator");
                    obtain.writeStrongBinder(com_google_android_gms_dynamic_zzd != null ? com_google_android_gms_dynamic_zzd.asBinder() : null);
                    if (adSizeParcel != null) {
                        obtain.writeInt(1);
                        adSizeParcel.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    if (com_google_android_gms_internal_zzgj != null) {
                        iBinder = com_google_android_gms_internal_zzgj.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeInt(i);
                    this.zzahn.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    iBinder = obtain2.readStrongBinder();
                    return iBinder;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public IBinder zza(zzd com_google_android_gms_dynamic_zzd, AdSizeParcel adSizeParcel, String str, zzgj com_google_android_gms_internal_zzgj, int i, int i2) throws RemoteException {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IAdManagerCreator");
                    obtain.writeStrongBinder(com_google_android_gms_dynamic_zzd != null ? com_google_android_gms_dynamic_zzd.asBinder() : null);
                    if (adSizeParcel != null) {
                        obtain.writeInt(1);
                        adSizeParcel.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    if (com_google_android_gms_internal_zzgj != null) {
                        iBinder = com_google_android_gms_internal_zzgj.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.zzahn.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    iBinder = obtain2.readStrongBinder();
                    return iBinder;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static zzv zzo(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IAdManagerCreator");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzv)) ? new zza(iBinder) : (zzv) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            AdSizeParcel adSizeParcel = null;
            zzd zzfc;
            IBinder zza;
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManagerCreator");
                    zzfc = com.google.android.gms.dynamic.zzd.zza.zzfc(parcel.readStrongBinder());
                    if (parcel.readInt() != 0) {
                        adSizeParcel = (AdSizeParcel) AdSizeParcel.CREATOR.createFromParcel(parcel);
                    }
                    zza = zza(zzfc, adSizeParcel, parcel.readString(), com.google.android.gms.internal.zzgj.zza.zzaj(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(zza);
                    return true;
                case 2:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IAdManagerCreator");
                    zzfc = com.google.android.gms.dynamic.zzd.zza.zzfc(parcel.readStrongBinder());
                    if (parcel.readInt() != 0) {
                        adSizeParcel = (AdSizeParcel) AdSizeParcel.CREATOR.createFromParcel(parcel);
                    }
                    zza = zza(zzfc, adSizeParcel, parcel.readString(), com.google.android.gms.internal.zzgj.zza.zzaj(parcel.readStrongBinder()), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(zza);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.ads.internal.client.IAdManagerCreator");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    IBinder zza(zzd com_google_android_gms_dynamic_zzd, AdSizeParcel adSizeParcel, String str, zzgj com_google_android_gms_internal_zzgj, int i) throws RemoteException;

    IBinder zza(zzd com_google_android_gms_dynamic_zzd, AdSizeParcel adSizeParcel, String str, zzgj com_google_android_gms_internal_zzgj, int i, int i2) throws RemoteException;
}

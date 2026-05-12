package com.google.android.gms.ads.internal.client;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.reward.client.zzb;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.internal.zzdt;
import com.google.android.gms.internal.zzgj;
import com.google.android.gms.internal.zzhi;
import com.google.android.gms.internal.zzhp;

public interface zzx extends IInterface {

    public static abstract class zza extends Binder implements zzx {

        private static class zza implements zzx {
            private IBinder zzahn;

            zza(IBinder iBinder) {
                this.zzahn = iBinder;
            }

            public IBinder asBinder() {
                return this.zzahn;
            }

            public zzs createAdLoaderBuilder(zzd com_google_android_gms_dynamic_zzd, String str, zzgj com_google_android_gms_internal_zzgj, int i) throws RemoteException {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IClientApi");
                    obtain.writeStrongBinder(com_google_android_gms_dynamic_zzd != null ? com_google_android_gms_dynamic_zzd.asBinder() : null);
                    obtain.writeString(str);
                    if (com_google_android_gms_internal_zzgj != null) {
                        iBinder = com_google_android_gms_internal_zzgj.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeInt(i);
                    this.zzahn.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    zzs zzl = com.google.android.gms.ads.internal.client.zzs.zza.zzl(obtain2.readStrongBinder());
                    return zzl;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzhi createAdOverlay(zzd com_google_android_gms_dynamic_zzd) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IClientApi");
                    obtain.writeStrongBinder(com_google_android_gms_dynamic_zzd != null ? com_google_android_gms_dynamic_zzd.asBinder() : null);
                    this.zzahn.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    zzhi zzaq = com.google.android.gms.internal.zzhi.zza.zzaq(obtain2.readStrongBinder());
                    return zzaq;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzu createBannerAdManager(zzd com_google_android_gms_dynamic_zzd, AdSizeParcel adSizeParcel, String str, zzgj com_google_android_gms_internal_zzgj, int i) throws RemoteException {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IClientApi");
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
                    zzu zzn = com.google.android.gms.ads.internal.client.zzu.zza.zzn(obtain2.readStrongBinder());
                    return zzn;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzhp createInAppPurchaseManager(zzd com_google_android_gms_dynamic_zzd) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IClientApi");
                    obtain.writeStrongBinder(com_google_android_gms_dynamic_zzd != null ? com_google_android_gms_dynamic_zzd.asBinder() : null);
                    this.zzahn.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    zzhp zzav = com.google.android.gms.internal.zzhp.zza.zzav(obtain2.readStrongBinder());
                    return zzav;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzu createInterstitialAdManager(zzd com_google_android_gms_dynamic_zzd, AdSizeParcel adSizeParcel, String str, zzgj com_google_android_gms_internal_zzgj, int i) throws RemoteException {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IClientApi");
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
                    this.zzahn.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    zzu zzn = com.google.android.gms.ads.internal.client.zzu.zza.zzn(obtain2.readStrongBinder());
                    return zzn;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzdt createNativeAdViewDelegate(zzd com_google_android_gms_dynamic_zzd, zzd com_google_android_gms_dynamic_zzd2) throws RemoteException {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IClientApi");
                    obtain.writeStrongBinder(com_google_android_gms_dynamic_zzd != null ? com_google_android_gms_dynamic_zzd.asBinder() : null);
                    if (com_google_android_gms_dynamic_zzd2 != null) {
                        iBinder = com_google_android_gms_dynamic_zzd2.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    this.zzahn.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    zzdt zzz = com.google.android.gms.internal.zzdt.zza.zzz(obtain2.readStrongBinder());
                    return zzz;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzb createRewardedVideoAd(zzd com_google_android_gms_dynamic_zzd, zzgj com_google_android_gms_internal_zzgj, int i) throws RemoteException {
                IBinder iBinder = null;
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IClientApi");
                    obtain.writeStrongBinder(com_google_android_gms_dynamic_zzd != null ? com_google_android_gms_dynamic_zzd.asBinder() : null);
                    if (com_google_android_gms_internal_zzgj != null) {
                        iBinder = com_google_android_gms_internal_zzgj.asBinder();
                    }
                    obtain.writeStrongBinder(iBinder);
                    obtain.writeInt(i);
                    this.zzahn.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    zzb zzbf = com.google.android.gms.ads.internal.reward.client.zzb.zza.zzbf(obtain2.readStrongBinder());
                    return zzbf;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzu createSearchAdManager(zzd com_google_android_gms_dynamic_zzd, AdSizeParcel adSizeParcel, String str, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IClientApi");
                    obtain.writeStrongBinder(com_google_android_gms_dynamic_zzd != null ? com_google_android_gms_dynamic_zzd.asBinder() : null);
                    if (adSizeParcel != null) {
                        obtain.writeInt(1);
                        adSizeParcel.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeString(str);
                    obtain.writeInt(i);
                    this.zzahn.transact(10, obtain, obtain2, 0);
                    obtain2.readException();
                    zzu zzn = com.google.android.gms.ads.internal.client.zzu.zza.zzn(obtain2.readStrongBinder());
                    return zzn;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzz getMobileAdsSettingsManager(zzd com_google_android_gms_dynamic_zzd) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IClientApi");
                    obtain.writeStrongBinder(com_google_android_gms_dynamic_zzd != null ? com_google_android_gms_dynamic_zzd.asBinder() : null);
                    this.zzahn.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    zzz zzr = com.google.android.gms.ads.internal.client.zzz.zza.zzr(obtain2.readStrongBinder());
                    return zzr;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public zzz getMobileAdsSettingsManagerWithClientJarVersion(zzd com_google_android_gms_dynamic_zzd, int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.google.android.gms.ads.internal.client.IClientApi");
                    obtain.writeStrongBinder(com_google_android_gms_dynamic_zzd != null ? com_google_android_gms_dynamic_zzd.asBinder() : null);
                    obtain.writeInt(i);
                    this.zzahn.transact(9, obtain, obtain2, 0);
                    obtain2.readException();
                    zzz zzr = com.google.android.gms.ads.internal.client.zzz.zza.zzr(obtain2.readStrongBinder());
                    return zzr;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public zza() {
            attachInterface(this, "com.google.android.gms.ads.internal.client.IClientApi");
        }

        public static zzx asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.ads.internal.client.IClientApi");
            return (queryLocalInterface == null || !(queryLocalInterface instanceof zzx)) ? new zza(iBinder) : (zzx) queryLocalInterface;
        }

        public IBinder asBinder() {
            return this;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            IBinder iBinder = null;
            zzu createBannerAdManager;
            zzz mobileAdsSettingsManager;
            switch (i) {
                case 1:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IClientApi");
                    createBannerAdManager = createBannerAdManager(com.google.android.gms.dynamic.zzd.zza.zzfc(parcel.readStrongBinder()), parcel.readInt() != 0 ? (AdSizeParcel) AdSizeParcel.CREATOR.createFromParcel(parcel) : null, parcel.readString(), com.google.android.gms.internal.zzgj.zza.zzaj(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    if (createBannerAdManager != null) {
                        iBinder = createBannerAdManager.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 2:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IClientApi");
                    createBannerAdManager = createInterstitialAdManager(com.google.android.gms.dynamic.zzd.zza.zzfc(parcel.readStrongBinder()), parcel.readInt() != 0 ? (AdSizeParcel) AdSizeParcel.CREATOR.createFromParcel(parcel) : null, parcel.readString(), com.google.android.gms.internal.zzgj.zza.zzaj(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    if (createBannerAdManager != null) {
                        iBinder = createBannerAdManager.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 3:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IClientApi");
                    zzs createAdLoaderBuilder = createAdLoaderBuilder(com.google.android.gms.dynamic.zzd.zza.zzfc(parcel.readStrongBinder()), parcel.readString(), com.google.android.gms.internal.zzgj.zza.zzaj(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    if (createAdLoaderBuilder != null) {
                        iBinder = createAdLoaderBuilder.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 4:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IClientApi");
                    mobileAdsSettingsManager = getMobileAdsSettingsManager(com.google.android.gms.dynamic.zzd.zza.zzfc(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (mobileAdsSettingsManager != null) {
                        iBinder = mobileAdsSettingsManager.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 5:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IClientApi");
                    zzdt createNativeAdViewDelegate = createNativeAdViewDelegate(com.google.android.gms.dynamic.zzd.zza.zzfc(parcel.readStrongBinder()), com.google.android.gms.dynamic.zzd.zza.zzfc(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (createNativeAdViewDelegate != null) {
                        iBinder = createNativeAdViewDelegate.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 6:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IClientApi");
                    zzb createRewardedVideoAd = createRewardedVideoAd(com.google.android.gms.dynamic.zzd.zza.zzfc(parcel.readStrongBinder()), com.google.android.gms.internal.zzgj.zza.zzaj(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    if (createRewardedVideoAd != null) {
                        iBinder = createRewardedVideoAd.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 7:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IClientApi");
                    zzhp createInAppPurchaseManager = createInAppPurchaseManager(com.google.android.gms.dynamic.zzd.zza.zzfc(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (createInAppPurchaseManager != null) {
                        iBinder = createInAppPurchaseManager.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 8:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IClientApi");
                    zzhi createAdOverlay = createAdOverlay(com.google.android.gms.dynamic.zzd.zza.zzfc(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    if (createAdOverlay != null) {
                        iBinder = createAdOverlay.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 9:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IClientApi");
                    mobileAdsSettingsManager = getMobileAdsSettingsManagerWithClientJarVersion(com.google.android.gms.dynamic.zzd.zza.zzfc(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    if (mobileAdsSettingsManager != null) {
                        iBinder = mobileAdsSettingsManager.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 10:
                    parcel.enforceInterface("com.google.android.gms.ads.internal.client.IClientApi");
                    createBannerAdManager = createSearchAdManager(com.google.android.gms.dynamic.zzd.zza.zzfc(parcel.readStrongBinder()), parcel.readInt() != 0 ? (AdSizeParcel) AdSizeParcel.CREATOR.createFromParcel(parcel) : null, parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    if (createBannerAdManager != null) {
                        iBinder = createBannerAdManager.asBinder();
                    }
                    parcel2.writeStrongBinder(iBinder);
                    return true;
                case 1598968902:
                    parcel2.writeString("com.google.android.gms.ads.internal.client.IClientApi");
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }
    }

    zzs createAdLoaderBuilder(zzd com_google_android_gms_dynamic_zzd, String str, zzgj com_google_android_gms_internal_zzgj, int i) throws RemoteException;

    zzhi createAdOverlay(zzd com_google_android_gms_dynamic_zzd) throws RemoteException;

    zzu createBannerAdManager(zzd com_google_android_gms_dynamic_zzd, AdSizeParcel adSizeParcel, String str, zzgj com_google_android_gms_internal_zzgj, int i) throws RemoteException;

    zzhp createInAppPurchaseManager(zzd com_google_android_gms_dynamic_zzd) throws RemoteException;

    zzu createInterstitialAdManager(zzd com_google_android_gms_dynamic_zzd, AdSizeParcel adSizeParcel, String str, zzgj com_google_android_gms_internal_zzgj, int i) throws RemoteException;

    zzdt createNativeAdViewDelegate(zzd com_google_android_gms_dynamic_zzd, zzd com_google_android_gms_dynamic_zzd2) throws RemoteException;

    zzb createRewardedVideoAd(zzd com_google_android_gms_dynamic_zzd, zzgj com_google_android_gms_internal_zzgj, int i) throws RemoteException;

    zzu createSearchAdManager(zzd com_google_android_gms_dynamic_zzd, AdSizeParcel adSizeParcel, String str, int i) throws RemoteException;

    zzz getMobileAdsSettingsManager(zzd com_google_android_gms_dynamic_zzd) throws RemoteException;

    zzz getMobileAdsSettingsManagerWithClientJarVersion(zzd com_google_android_gms_dynamic_zzd, int i) throws RemoteException;
}

package com.google.android.gms.ads.internal.purchase;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zza implements Creator<GInAppPurchaseManagerInfoParcel> {
    static void zza(GInAppPurchaseManagerInfoParcel gInAppPurchaseManagerInfoParcel, Parcel parcel, int i) {
        int zzcn = zzb.zzcn(parcel);
        zzb.zzc(parcel, 1, gInAppPurchaseManagerInfoParcel.versionCode);
        zzb.zza(parcel, 3, gInAppPurchaseManagerInfoParcel.zzpn(), false);
        zzb.zza(parcel, 4, gInAppPurchaseManagerInfoParcel.zzpo(), false);
        zzb.zza(parcel, 5, gInAppPurchaseManagerInfoParcel.zzpp(), false);
        zzb.zza(parcel, 6, gInAppPurchaseManagerInfoParcel.zzpm(), false);
        zzb.zzaj(parcel, zzcn);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzj(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzah(i);
    }

    public GInAppPurchaseManagerInfoParcel[] zzah(int i) {
        return new GInAppPurchaseManagerInfoParcel[i];
    }

    public GInAppPurchaseManagerInfoParcel zzj(Parcel parcel) {
        IBinder iBinder = null;
        int zzcm = com.google.android.gms.common.internal.safeparcel.zza.zzcm(parcel);
        int i = 0;
        IBinder iBinder2 = null;
        IBinder iBinder3 = null;
        IBinder iBinder4 = null;
        while (parcel.dataPosition() < zzcm) {
            int zzcl = com.google.android.gms.common.internal.safeparcel.zza.zzcl(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzgm(zzcl)) {
                case 1:
                    i = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzcl);
                    break;
                case 3:
                    iBinder4 = com.google.android.gms.common.internal.safeparcel.zza.zzr(parcel, zzcl);
                    break;
                case 4:
                    iBinder3 = com.google.android.gms.common.internal.safeparcel.zza.zzr(parcel, zzcl);
                    break;
                case 5:
                    iBinder2 = com.google.android.gms.common.internal.safeparcel.zza.zzr(parcel, zzcl);
                    break;
                case 6:
                    iBinder = com.google.android.gms.common.internal.safeparcel.zza.zzr(parcel, zzcl);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzcl);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcm) {
            return new GInAppPurchaseManagerInfoParcel(i, iBinder4, iBinder3, iBinder2, iBinder);
        }
        throw new com.google.android.gms.common.internal.safeparcel.zza.zza("Overread allowed size end=" + zzcm, parcel);
    }
}

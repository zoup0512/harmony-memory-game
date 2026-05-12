package com.google.android.gms.plus.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzf implements Creator<PlusCommonExtras> {
    static void zza(PlusCommonExtras plusCommonExtras, Parcel parcel, int i) {
        int zzcn = zzb.zzcn(parcel);
        zzb.zza(parcel, 1, plusCommonExtras.zzbyd(), false);
        zzb.zza(parcel, 2, plusCommonExtras.zzbye(), false);
        zzb.zzc(parcel, 1000, plusCommonExtras.getVersionCode());
        zzb.zzaj(parcel, zzcn);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzra(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzyi(i);
    }

    public PlusCommonExtras zzra(Parcel parcel) {
        String str = null;
        int zzcm = zza.zzcm(parcel);
        int i = 0;
        String str2 = null;
        while (parcel.dataPosition() < zzcm) {
            int zzcl = zza.zzcl(parcel);
            switch (zza.zzgm(zzcl)) {
                case 1:
                    str2 = zza.zzq(parcel, zzcl);
                    break;
                case 2:
                    str = zza.zzq(parcel, zzcl);
                    break;
                case 1000:
                    i = zza.zzg(parcel, zzcl);
                    break;
                default:
                    zza.zzb(parcel, zzcl);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcm) {
            return new PlusCommonExtras(i, str2, str);
        }
        throw new zza.zza("Overread allowed size end=" + zzcm, parcel);
    }

    public PlusCommonExtras[] zzyi(int i) {
        return new PlusCommonExtras[i];
    }
}

package com.google.android.gms.ads.internal.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzo implements Creator<StringParcel> {
    static void zza(StringParcel stringParcel, Parcel parcel, int i) {
        int zzcn = zzb.zzcn(parcel);
        zzb.zzc(parcel, 1, stringParcel.mVersionCode);
        zzb.zza(parcel, 2, stringParcel.zzbem, false);
        zzb.zzaj(parcel, zzcn);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzp(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzat(i);
    }

    public StringParcel[] zzat(int i) {
        return new StringParcel[i];
    }

    public StringParcel zzp(Parcel parcel) {
        int zzcm = zza.zzcm(parcel);
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < zzcm) {
            int zzcl = zza.zzcl(parcel);
            switch (zza.zzgm(zzcl)) {
                case 1:
                    i = zza.zzg(parcel, zzcl);
                    break;
                case 2:
                    str = zza.zzq(parcel, zzcl);
                    break;
                default:
                    zza.zzb(parcel, zzcl);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcm) {
            return new StringParcel(i, str);
        }
        throw new zza.zza("Overread allowed size end=" + zzcm, parcel);
    }
}

package com.google.android.gms.gass.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzd implements Creator<GassResponseParcel> {
    static void zza(GassResponseParcel gassResponseParcel, Parcel parcel, int i) {
        int zzcn = zzb.zzcn(parcel);
        zzb.zzc(parcel, 1, gassResponseParcel.versionCode);
        zzb.zza(parcel, 2, gassResponseParcel.zzblc(), false);
        zzb.zzaj(parcel, zzcn);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzmh(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzsk(i);
    }

    public GassResponseParcel zzmh(Parcel parcel) {
        int zzcm = zza.zzcm(parcel);
        int i = 0;
        byte[] bArr = null;
        while (parcel.dataPosition() < zzcm) {
            int zzcl = zza.zzcl(parcel);
            switch (zza.zzgm(zzcl)) {
                case 1:
                    i = zza.zzg(parcel, zzcl);
                    break;
                case 2:
                    bArr = zza.zzt(parcel, zzcl);
                    break;
                default:
                    zza.zzb(parcel, zzcl);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcm) {
            return new GassResponseParcel(i, bArr);
        }
        throw new zza.zza("Overread allowed size end=" + zzcm, parcel);
    }

    public GassResponseParcel[] zzsk(int i) {
        return new GassResponseParcel[i];
    }
}

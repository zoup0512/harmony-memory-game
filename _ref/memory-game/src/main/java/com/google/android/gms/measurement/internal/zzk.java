package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzk implements Creator<EventParcel> {
    static void zza(EventParcel eventParcel, Parcel parcel, int i) {
        int zzcn = zzb.zzcn(parcel);
        zzb.zzc(parcel, 1, eventParcel.versionCode);
        zzb.zza(parcel, 2, eventParcel.name, false);
        zzb.zza(parcel, 3, eventParcel.aiI, i, false);
        zzb.zza(parcel, 4, eventParcel.aiJ, false);
        zzb.zza(parcel, 5, eventParcel.aiK);
        zzb.zzaj(parcel, zzcn);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzop(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzvk(i);
    }

    public EventParcel zzop(Parcel parcel) {
        String str = null;
        int zzcm = zza.zzcm(parcel);
        int i = 0;
        long j = 0;
        EventParams eventParams = null;
        String str2 = null;
        while (parcel.dataPosition() < zzcm) {
            int zzcl = zza.zzcl(parcel);
            switch (zza.zzgm(zzcl)) {
                case 1:
                    i = zza.zzg(parcel, zzcl);
                    break;
                case 2:
                    str2 = zza.zzq(parcel, zzcl);
                    break;
                case 3:
                    eventParams = (EventParams) zza.zza(parcel, zzcl, EventParams.CREATOR);
                    break;
                case 4:
                    str = zza.zzq(parcel, zzcl);
                    break;
                case 5:
                    j = zza.zzi(parcel, zzcl);
                    break;
                default:
                    zza.zzb(parcel, zzcl);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcm) {
            return new EventParcel(i, str2, eventParams, str, j);
        }
        throw new zza.zza("Overread allowed size end=" + zzcm, parcel);
    }

    public EventParcel[] zzvk(int i) {
        return new EventParcel[i];
    }
}

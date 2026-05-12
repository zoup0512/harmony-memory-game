package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzj implements Creator<EventParams> {
    static void zza(EventParams eventParams, Parcel parcel, int i) {
        int zzcn = zzb.zzcn(parcel);
        zzb.zzc(parcel, 1, eventParams.versionCode);
        zzb.zza(parcel, 2, eventParams.zzbss(), false);
        zzb.zzaj(parcel, zzcn);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzoo(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzvj(i);
    }

    public EventParams zzoo(Parcel parcel) {
        int zzcm = zza.zzcm(parcel);
        int i = 0;
        Bundle bundle = null;
        while (parcel.dataPosition() < zzcm) {
            int zzcl = zza.zzcl(parcel);
            switch (zza.zzgm(zzcl)) {
                case 1:
                    i = zza.zzg(parcel, zzcl);
                    break;
                case 2:
                    bundle = zza.zzs(parcel, zzcl);
                    break;
                default:
                    zza.zzb(parcel, zzcl);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcm) {
            return new EventParams(i, bundle);
        }
        throw new zza.zza("Overread allowed size end=" + zzcm, parcel);
    }

    public EventParams[] zzvj(int i) {
        return new EventParams[i];
    }
}

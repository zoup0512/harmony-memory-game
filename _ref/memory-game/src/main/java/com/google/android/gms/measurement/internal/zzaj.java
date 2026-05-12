package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzaj implements Creator<UserAttributeParcel> {
    static void zza(UserAttributeParcel userAttributeParcel, Parcel parcel, int i) {
        int zzcn = zzb.zzcn(parcel);
        zzb.zzc(parcel, 1, userAttributeParcel.versionCode);
        zzb.zza(parcel, 2, userAttributeParcel.name, false);
        zzb.zza(parcel, 3, userAttributeParcel.amt);
        zzb.zza(parcel, 4, userAttributeParcel.amu, false);
        zzb.zza(parcel, 5, userAttributeParcel.amv, false);
        zzb.zza(parcel, 6, userAttributeParcel.zD, false);
        zzb.zza(parcel, 7, userAttributeParcel.aiJ, false);
        zzb.zza(parcel, 8, userAttributeParcel.amw, false);
        zzb.zzaj(parcel, zzcn);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzoq(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzvl(i);
    }

    public UserAttributeParcel zzoq(Parcel parcel) {
        Double d = null;
        int zzcm = zza.zzcm(parcel);
        int i = 0;
        long j = 0;
        String str = null;
        String str2 = null;
        Float f = null;
        Long l = null;
        String str3 = null;
        while (parcel.dataPosition() < zzcm) {
            int zzcl = zza.zzcl(parcel);
            switch (zza.zzgm(zzcl)) {
                case 1:
                    i = zza.zzg(parcel, zzcl);
                    break;
                case 2:
                    str3 = zza.zzq(parcel, zzcl);
                    break;
                case 3:
                    j = zza.zzi(parcel, zzcl);
                    break;
                case 4:
                    l = zza.zzj(parcel, zzcl);
                    break;
                case 5:
                    f = zza.zzm(parcel, zzcl);
                    break;
                case 6:
                    str2 = zza.zzq(parcel, zzcl);
                    break;
                case 7:
                    str = zza.zzq(parcel, zzcl);
                    break;
                case 8:
                    d = zza.zzo(parcel, zzcl);
                    break;
                default:
                    zza.zzb(parcel, zzcl);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcm) {
            return new UserAttributeParcel(i, str3, j, l, f, str2, str, d);
        }
        throw new zza.zza("Overread allowed size end=" + zzcm, parcel);
    }

    public UserAttributeParcel[] zzvl(int i) {
        return new UserAttributeParcel[i];
    }
}

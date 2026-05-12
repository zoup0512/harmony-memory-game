package com.google.android.gms.plus.internal.model.people;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.plus.internal.model.people.PersonEntity.AgeRangeEntity;
import java.util.HashSet;
import java.util.Set;

public class zzb implements Creator<AgeRangeEntity> {
    static void zza(AgeRangeEntity ageRangeEntity, Parcel parcel, int i) {
        int zzcn = com.google.android.gms.common.internal.safeparcel.zzb.zzcn(parcel);
        Set set = ageRangeEntity.asg;
        if (set.contains(Integer.valueOf(1))) {
            com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, ageRangeEntity.mVersionCode);
        }
        if (set.contains(Integer.valueOf(2))) {
            com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 2, ageRangeEntity.asA);
        }
        if (set.contains(Integer.valueOf(3))) {
            com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 3, ageRangeEntity.asB);
        }
        com.google.android.gms.common.internal.safeparcel.zzb.zzaj(parcel, zzcn);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzrd(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzyl(i);
    }

    public AgeRangeEntity zzrd(Parcel parcel) {
        int i = 0;
        int zzcm = zza.zzcm(parcel);
        Set hashSet = new HashSet();
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < zzcm) {
            int zzcl = zza.zzcl(parcel);
            switch (zza.zzgm(zzcl)) {
                case 1:
                    i3 = zza.zzg(parcel, zzcl);
                    hashSet.add(Integer.valueOf(1));
                    break;
                case 2:
                    i2 = zza.zzg(parcel, zzcl);
                    hashSet.add(Integer.valueOf(2));
                    break;
                case 3:
                    i = zza.zzg(parcel, zzcl);
                    hashSet.add(Integer.valueOf(3));
                    break;
                default:
                    zza.zzb(parcel, zzcl);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcm) {
            return new AgeRangeEntity(hashSet, i3, i2, i);
        }
        throw new zza.zza("Overread allowed size end=" + zzcm, parcel);
    }

    public AgeRangeEntity[] zzyl(int i) {
        return new AgeRangeEntity[i];
    }
}

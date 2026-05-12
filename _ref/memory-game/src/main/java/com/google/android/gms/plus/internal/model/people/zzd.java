package com.google.android.gms.plus.internal.model.people;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.plus.internal.model.people.PersonEntity.CoverEntity.CoverInfoEntity;
import java.util.HashSet;
import java.util.Set;

public class zzd implements Creator<CoverInfoEntity> {
    static void zza(CoverInfoEntity coverInfoEntity, Parcel parcel, int i) {
        int zzcn = zzb.zzcn(parcel);
        Set set = coverInfoEntity.asg;
        if (set.contains(Integer.valueOf(1))) {
            zzb.zzc(parcel, 1, coverInfoEntity.mVersionCode);
        }
        if (set.contains(Integer.valueOf(2))) {
            zzb.zzc(parcel, 2, coverInfoEntity.asF);
        }
        if (set.contains(Integer.valueOf(3))) {
            zzb.zzc(parcel, 3, coverInfoEntity.asG);
        }
        zzb.zzaj(parcel, zzcn);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzrf(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzyn(i);
    }

    public CoverInfoEntity zzrf(Parcel parcel) {
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
            return new CoverInfoEntity(hashSet, i3, i2, i);
        }
        throw new zza.zza("Overread allowed size end=" + zzcm, parcel);
    }

    public CoverInfoEntity[] zzyn(int i) {
        return new CoverInfoEntity[i];
    }
}

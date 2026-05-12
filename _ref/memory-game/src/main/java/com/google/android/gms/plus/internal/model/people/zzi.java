package com.google.android.gms.plus.internal.model.people;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.plus.internal.model.people.PersonEntity.PlacesLivedEntity;
import java.util.HashSet;
import java.util.Set;

public class zzi implements Creator<PlacesLivedEntity> {
    static void zza(PlacesLivedEntity placesLivedEntity, Parcel parcel, int i) {
        int zzcn = zzb.zzcn(parcel);
        Set set = placesLivedEntity.asg;
        if (set.contains(Integer.valueOf(1))) {
            zzb.zzc(parcel, 1, placesLivedEntity.mVersionCode);
        }
        if (set.contains(Integer.valueOf(2))) {
            zzb.zza(parcel, 2, placesLivedEntity.asO);
        }
        if (set.contains(Integer.valueOf(3))) {
            zzb.zza(parcel, 3, placesLivedEntity.mValue, true);
        }
        zzb.zzaj(parcel, zzcn);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzrk(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzys(i);
    }

    public PlacesLivedEntity zzrk(Parcel parcel) {
        boolean z = false;
        int zzcm = zza.zzcm(parcel);
        Set hashSet = new HashSet();
        String str = null;
        int i = 0;
        while (parcel.dataPosition() < zzcm) {
            int zzcl = zza.zzcl(parcel);
            switch (zza.zzgm(zzcl)) {
                case 1:
                    i = zza.zzg(parcel, zzcl);
                    hashSet.add(Integer.valueOf(1));
                    break;
                case 2:
                    z = zza.zzc(parcel, zzcl);
                    hashSet.add(Integer.valueOf(2));
                    break;
                case 3:
                    str = zza.zzq(parcel, zzcl);
                    hashSet.add(Integer.valueOf(3));
                    break;
                default:
                    zza.zzb(parcel, zzcl);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcm) {
            return new PlacesLivedEntity(hashSet, i, z, str);
        }
        throw new zza.zza("Overread allowed size end=" + zzcm, parcel);
    }

    public PlacesLivedEntity[] zzys(int i) {
        return new PlacesLivedEntity[i];
    }
}

package com.google.android.gms.plus.internal.model.people;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.plus.internal.model.people.PersonEntity.CoverEntity.CoverPhotoEntity;
import java.util.HashSet;
import java.util.Set;

public class zze implements Creator<CoverPhotoEntity> {
    static void zza(CoverPhotoEntity coverPhotoEntity, Parcel parcel, int i) {
        int zzcn = zzb.zzcn(parcel);
        Set set = coverPhotoEntity.asg;
        if (set.contains(Integer.valueOf(1))) {
            zzb.zzc(parcel, 1, coverPhotoEntity.mVersionCode);
        }
        if (set.contains(Integer.valueOf(2))) {
            zzb.zzc(parcel, 2, coverPhotoEntity.zzaif);
        }
        if (set.contains(Integer.valueOf(3))) {
            zzb.zza(parcel, 3, coverPhotoEntity.zzae, true);
        }
        if (set.contains(Integer.valueOf(4))) {
            zzb.zzc(parcel, 4, coverPhotoEntity.zzaie);
        }
        zzb.zzaj(parcel, zzcn);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzrg(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzyo(i);
    }

    public CoverPhotoEntity zzrg(Parcel parcel) {
        int i = 0;
        int zzcm = zza.zzcm(parcel);
        Set hashSet = new HashSet();
        String str = null;
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
                    str = zza.zzq(parcel, zzcl);
                    hashSet.add(Integer.valueOf(3));
                    break;
                case 4:
                    i = zza.zzg(parcel, zzcl);
                    hashSet.add(Integer.valueOf(4));
                    break;
                default:
                    zza.zzb(parcel, zzcl);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcm) {
            return new CoverPhotoEntity(hashSet, i3, i2, str, i);
        }
        throw new zza.zza("Overread allowed size end=" + zzcm, parcel);
    }

    public CoverPhotoEntity[] zzyo(int i) {
        return new CoverPhotoEntity[i];
    }
}

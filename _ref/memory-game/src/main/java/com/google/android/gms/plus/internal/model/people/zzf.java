package com.google.android.gms.plus.internal.model.people;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.plus.internal.model.people.PersonEntity.ImageEntity;
import java.util.HashSet;
import java.util.Set;

public class zzf implements Creator<ImageEntity> {
    static void zza(ImageEntity imageEntity, Parcel parcel, int i) {
        int zzcn = zzb.zzcn(parcel);
        Set set = imageEntity.asg;
        if (set.contains(Integer.valueOf(1))) {
            zzb.zzc(parcel, 1, imageEntity.mVersionCode);
        }
        if (set.contains(Integer.valueOf(2))) {
            zzb.zza(parcel, 2, imageEntity.zzae, true);
        }
        zzb.zzaj(parcel, zzcn);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzrh(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzyp(i);
    }

    public ImageEntity zzrh(Parcel parcel) {
        int zzcm = zza.zzcm(parcel);
        Set hashSet = new HashSet();
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < zzcm) {
            int zzcl = zza.zzcl(parcel);
            switch (zza.zzgm(zzcl)) {
                case 1:
                    i = zza.zzg(parcel, zzcl);
                    hashSet.add(Integer.valueOf(1));
                    break;
                case 2:
                    str = zza.zzq(parcel, zzcl);
                    hashSet.add(Integer.valueOf(2));
                    break;
                default:
                    zza.zzb(parcel, zzcl);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcm) {
            return new ImageEntity(hashSet, i, str);
        }
        throw new zza.zza("Overread allowed size end=" + zzcm, parcel);
    }

    public ImageEntity[] zzyp(int i) {
        return new ImageEntity[i];
    }
}

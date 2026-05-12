package com.google.android.gms.plus.internal.model.people;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.plus.internal.model.people.PersonEntity.UrlsEntity;
import java.util.HashSet;
import java.util.Set;

public class zzj implements Creator<UrlsEntity> {
    static void zza(UrlsEntity urlsEntity, Parcel parcel, int i) {
        int zzcn = zzb.zzcn(parcel);
        Set set = urlsEntity.asg;
        if (set.contains(Integer.valueOf(1))) {
            zzb.zzc(parcel, 1, urlsEntity.mVersionCode);
        }
        if (set.contains(Integer.valueOf(3))) {
            zzb.zzc(parcel, 3, urlsEntity.zzbyx());
        }
        if (set.contains(Integer.valueOf(4))) {
            zzb.zza(parcel, 4, urlsEntity.mValue, true);
        }
        if (set.contains(Integer.valueOf(5))) {
            zzb.zza(parcel, 5, urlsEntity.zzcvd, true);
        }
        if (set.contains(Integer.valueOf(6))) {
            zzb.zzc(parcel, 6, urlsEntity.iq);
        }
        zzb.zzaj(parcel, zzcn);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzrl(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzyt(i);
    }

    public UrlsEntity zzrl(Parcel parcel) {
        String str = null;
        int i = 0;
        int zzcm = zza.zzcm(parcel);
        Set hashSet = new HashSet();
        int i2 = 0;
        String str2 = null;
        int i3 = 0;
        while (parcel.dataPosition() < zzcm) {
            int zzcl = zza.zzcl(parcel);
            switch (zza.zzgm(zzcl)) {
                case 1:
                    i3 = zza.zzg(parcel, zzcl);
                    hashSet.add(Integer.valueOf(1));
                    break;
                case 3:
                    i = zza.zzg(parcel, zzcl);
                    hashSet.add(Integer.valueOf(3));
                    break;
                case 4:
                    str = zza.zzq(parcel, zzcl);
                    hashSet.add(Integer.valueOf(4));
                    break;
                case 5:
                    str2 = zza.zzq(parcel, zzcl);
                    hashSet.add(Integer.valueOf(5));
                    break;
                case 6:
                    i2 = zza.zzg(parcel, zzcl);
                    hashSet.add(Integer.valueOf(6));
                    break;
                default:
                    zza.zzb(parcel, zzcl);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcm) {
            return new UrlsEntity(hashSet, i3, str2, i2, str, i);
        }
        throw new zza.zza("Overread allowed size end=" + zzcm, parcel);
    }

    public UrlsEntity[] zzyt(int i) {
        return new UrlsEntity[i];
    }
}

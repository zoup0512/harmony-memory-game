package com.google.android.gms.plus.internal.model.people;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.plus.internal.model.people.PersonEntity.OrganizationsEntity;
import java.util.HashSet;
import java.util.Set;

public class zzh implements Creator<OrganizationsEntity> {
    static void zza(OrganizationsEntity organizationsEntity, Parcel parcel, int i) {
        int zzcn = zzb.zzcn(parcel);
        Set set = organizationsEntity.asg;
        if (set.contains(Integer.valueOf(1))) {
            zzb.zzc(parcel, 1, organizationsEntity.mVersionCode);
        }
        if (set.contains(Integer.valueOf(2))) {
            zzb.zza(parcel, 2, organizationsEntity.asL, true);
        }
        if (set.contains(Integer.valueOf(3))) {
            zzb.zza(parcel, 3, organizationsEntity.zzcvf, true);
        }
        if (set.contains(Integer.valueOf(4))) {
            zzb.zza(parcel, 4, organizationsEntity.asM, true);
        }
        if (set.contains(Integer.valueOf(5))) {
            zzb.zza(parcel, 5, organizationsEntity.asN, true);
        }
        if (set.contains(Integer.valueOf(6))) {
            zzb.zza(parcel, 6, organizationsEntity.mName, true);
        }
        if (set.contains(Integer.valueOf(7))) {
            zzb.zza(parcel, 7, organizationsEntity.asO);
        }
        if (set.contains(Integer.valueOf(8))) {
            zzb.zza(parcel, 8, organizationsEntity.asP, true);
        }
        if (set.contains(Integer.valueOf(9))) {
            zzb.zza(parcel, 9, organizationsEntity.DZ, true);
        }
        if (set.contains(Integer.valueOf(10))) {
            zzb.zzc(parcel, 10, organizationsEntity.iq);
        }
        zzb.zzaj(parcel, zzcn);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzrj(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzyr(i);
    }

    public OrganizationsEntity zzrj(Parcel parcel) {
        int i = 0;
        String str = null;
        int zzcm = zza.zzcm(parcel);
        Set hashSet = new HashSet();
        String str2 = null;
        boolean z = false;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        String str7 = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzcm) {
            int zzcl = zza.zzcl(parcel);
            switch (zza.zzgm(zzcl)) {
                case 1:
                    i2 = zza.zzg(parcel, zzcl);
                    hashSet.add(Integer.valueOf(1));
                    break;
                case 2:
                    str7 = zza.zzq(parcel, zzcl);
                    hashSet.add(Integer.valueOf(2));
                    break;
                case 3:
                    str6 = zza.zzq(parcel, zzcl);
                    hashSet.add(Integer.valueOf(3));
                    break;
                case 4:
                    str5 = zza.zzq(parcel, zzcl);
                    hashSet.add(Integer.valueOf(4));
                    break;
                case 5:
                    str4 = zza.zzq(parcel, zzcl);
                    hashSet.add(Integer.valueOf(5));
                    break;
                case 6:
                    str3 = zza.zzq(parcel, zzcl);
                    hashSet.add(Integer.valueOf(6));
                    break;
                case 7:
                    z = zza.zzc(parcel, zzcl);
                    hashSet.add(Integer.valueOf(7));
                    break;
                case 8:
                    str2 = zza.zzq(parcel, zzcl);
                    hashSet.add(Integer.valueOf(8));
                    break;
                case 9:
                    str = zza.zzq(parcel, zzcl);
                    hashSet.add(Integer.valueOf(9));
                    break;
                case 10:
                    i = zza.zzg(parcel, zzcl);
                    hashSet.add(Integer.valueOf(10));
                    break;
                default:
                    zza.zzb(parcel, zzcl);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcm) {
            return new OrganizationsEntity(hashSet, i2, str7, str6, str5, str4, str3, z, str2, str, i);
        }
        throw new zza.zza("Overread allowed size end=" + zzcm, parcel);
    }

    public OrganizationsEntity[] zzyr(int i) {
        return new OrganizationsEntity[i];
    }
}

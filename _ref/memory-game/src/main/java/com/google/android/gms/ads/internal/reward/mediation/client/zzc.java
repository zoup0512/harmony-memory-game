package com.google.android.gms.ads.internal.reward.mediation.client;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzc implements Creator<RewardItemParcel> {
    static void zza(RewardItemParcel rewardItemParcel, Parcel parcel, int i) {
        int zzcn = zzb.zzcn(parcel);
        zzb.zzc(parcel, 1, rewardItemParcel.versionCode);
        zzb.zza(parcel, 2, rewardItemParcel.type, false);
        zzb.zzc(parcel, 3, rewardItemParcel.zzcid);
        zzb.zzaj(parcel, zzcn);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzr(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzax(i);
    }

    public RewardItemParcel[] zzax(int i) {
        return new RewardItemParcel[i];
    }

    public RewardItemParcel zzr(Parcel parcel) {
        int i = 0;
        int zzcm = zza.zzcm(parcel);
        String str = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzcm) {
            int zzcl = zza.zzcl(parcel);
            switch (zza.zzgm(zzcl)) {
                case 1:
                    i2 = zza.zzg(parcel, zzcl);
                    break;
                case 2:
                    str = zza.zzq(parcel, zzcl);
                    break;
                case 3:
                    i = zza.zzg(parcel, zzcl);
                    break;
                default:
                    zza.zzb(parcel, zzcl);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcm) {
            return new RewardItemParcel(i2, str, i);
        }
        throw new zza.zza("Overread allowed size end=" + zzcm, parcel);
    }
}

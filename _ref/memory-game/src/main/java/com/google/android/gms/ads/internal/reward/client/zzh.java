package com.google.android.gms.ads.internal.reward.client;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzh implements Creator<RewardedVideoAdRequestParcel> {
    static void zza(RewardedVideoAdRequestParcel rewardedVideoAdRequestParcel, Parcel parcel, int i) {
        int zzcn = zzb.zzcn(parcel);
        zzb.zzc(parcel, 1, rewardedVideoAdRequestParcel.versionCode);
        zzb.zza(parcel, 2, rewardedVideoAdRequestParcel.zzcar, i, false);
        zzb.zza(parcel, 3, rewardedVideoAdRequestParcel.zzaou, false);
        zzb.zzaj(parcel, zzcn);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzq(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzav(i);
    }

    public RewardedVideoAdRequestParcel[] zzav(int i) {
        return new RewardedVideoAdRequestParcel[i];
    }

    public RewardedVideoAdRequestParcel zzq(Parcel parcel) {
        String str = null;
        int zzcm = zza.zzcm(parcel);
        int i = 0;
        AdRequestParcel adRequestParcel = null;
        while (parcel.dataPosition() < zzcm) {
            AdRequestParcel adRequestParcel2;
            int zzg;
            String str2;
            int zzcl = zza.zzcl(parcel);
            switch (zza.zzgm(zzcl)) {
                case 1:
                    String str3 = str;
                    adRequestParcel2 = adRequestParcel;
                    zzg = zza.zzg(parcel, zzcl);
                    str2 = str3;
                    break;
                case 2:
                    zzg = i;
                    AdRequestParcel adRequestParcel3 = (AdRequestParcel) zza.zza(parcel, zzcl, AdRequestParcel.CREATOR);
                    str2 = str;
                    adRequestParcel2 = adRequestParcel3;
                    break;
                case 3:
                    str2 = zza.zzq(parcel, zzcl);
                    adRequestParcel2 = adRequestParcel;
                    zzg = i;
                    break;
                default:
                    zza.zzb(parcel, zzcl);
                    str2 = str;
                    adRequestParcel2 = adRequestParcel;
                    zzg = i;
                    break;
            }
            i = zzg;
            adRequestParcel = adRequestParcel2;
            str = str2;
        }
        if (parcel.dataPosition() == zzcm) {
            return new RewardedVideoAdRequestParcel(i, adRequestParcel, str);
        }
        throw new zza.zza("Overread allowed size end=" + zzcm, parcel);
    }
}

package com.google.android.gms.ads.internal.client;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzaq implements Creator<VideoOptionsParcel> {
    static void zza(VideoOptionsParcel videoOptionsParcel, Parcel parcel, int i) {
        int zzcn = zzb.zzcn(parcel);
        zzb.zzc(parcel, 1, videoOptionsParcel.versionCode);
        zzb.zza(parcel, 2, videoOptionsParcel.zzaxm);
        zzb.zzaj(parcel, zzcn);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzf(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzv(i);
    }

    public VideoOptionsParcel zzf(Parcel parcel) {
        boolean z = false;
        int zzcm = zza.zzcm(parcel);
        int i = 0;
        while (parcel.dataPosition() < zzcm) {
            int zzcl = zza.zzcl(parcel);
            switch (zza.zzgm(zzcl)) {
                case 1:
                    i = zza.zzg(parcel, zzcl);
                    break;
                case 2:
                    z = zza.zzc(parcel, zzcl);
                    break;
                default:
                    zza.zzb(parcel, zzcl);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcm) {
            return new VideoOptionsParcel(i, z);
        }
        throw new zza.zza("Overread allowed size end=" + zzcm, parcel);
    }

    public VideoOptionsParcel[] zzv(int i) {
        return new VideoOptionsParcel[i];
    }
}

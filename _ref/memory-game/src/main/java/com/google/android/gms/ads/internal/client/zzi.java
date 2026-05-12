package com.google.android.gms.ads.internal.client;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzi implements Creator<AdSizeParcel> {
    static void zza(AdSizeParcel adSizeParcel, Parcel parcel, int i) {
        int zzcn = zzb.zzcn(parcel);
        zzb.zzc(parcel, 1, adSizeParcel.versionCode);
        zzb.zza(parcel, 2, adSizeParcel.zzaur, false);
        zzb.zzc(parcel, 3, adSizeParcel.height);
        zzb.zzc(parcel, 4, adSizeParcel.heightPixels);
        zzb.zza(parcel, 5, adSizeParcel.zzaus);
        zzb.zzc(parcel, 6, adSizeParcel.width);
        zzb.zzc(parcel, 7, adSizeParcel.widthPixels);
        zzb.zza(parcel, 8, adSizeParcel.zzaut, i, false);
        zzb.zza(parcel, 9, adSizeParcel.zzauu);
        zzb.zza(parcel, 10, adSizeParcel.zzauv);
        zzb.zza(parcel, 11, adSizeParcel.zzauw);
        zzb.zzaj(parcel, zzcn);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzd(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzs(i);
    }

    public AdSizeParcel zzd(Parcel parcel) {
        AdSizeParcel[] adSizeParcelArr = null;
        boolean z = false;
        int zzcm = zza.zzcm(parcel);
        boolean z2 = false;
        boolean z3 = false;
        int i = 0;
        int i2 = 0;
        boolean z4 = false;
        int i3 = 0;
        int i4 = 0;
        String str = null;
        int i5 = 0;
        while (parcel.dataPosition() < zzcm) {
            int zzcl = zza.zzcl(parcel);
            switch (zza.zzgm(zzcl)) {
                case 1:
                    i5 = zza.zzg(parcel, zzcl);
                    break;
                case 2:
                    str = zza.zzq(parcel, zzcl);
                    break;
                case 3:
                    i4 = zza.zzg(parcel, zzcl);
                    break;
                case 4:
                    i3 = zza.zzg(parcel, zzcl);
                    break;
                case 5:
                    z4 = zza.zzc(parcel, zzcl);
                    break;
                case 6:
                    i2 = zza.zzg(parcel, zzcl);
                    break;
                case 7:
                    i = zza.zzg(parcel, zzcl);
                    break;
                case 8:
                    adSizeParcelArr = (AdSizeParcel[]) zza.zzb(parcel, zzcl, AdSizeParcel.CREATOR);
                    break;
                case 9:
                    z3 = zza.zzc(parcel, zzcl);
                    break;
                case 10:
                    z2 = zza.zzc(parcel, zzcl);
                    break;
                case 11:
                    z = zza.zzc(parcel, zzcl);
                    break;
                default:
                    zza.zzb(parcel, zzcl);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcm) {
            return new AdSizeParcel(i5, str, i4, i3, z4, i2, i, adSizeParcelArr, z3, z2, z);
        }
        throw new zza.zza("Overread allowed size end=" + zzcm, parcel);
    }

    public AdSizeParcel[] zzs(int i) {
        return new AdSizeParcel[i];
    }
}

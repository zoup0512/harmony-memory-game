package com.google.android.gms.ads.internal.request;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzm implements Creator<LargeParcelTeleporter> {
    static void zza(LargeParcelTeleporter largeParcelTeleporter, Parcel parcel, int i) {
        int zzcn = zzb.zzcn(parcel);
        zzb.zzc(parcel, 1, largeParcelTeleporter.mVersionCode);
        zzb.zza(parcel, 2, largeParcelTeleporter.zzccz, i, false);
        zzb.zzaj(parcel, zzcn);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzo(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzas(i);
    }

    public LargeParcelTeleporter[] zzas(int i) {
        return new LargeParcelTeleporter[i];
    }

    public LargeParcelTeleporter zzo(Parcel parcel) {
        int zzcm = zza.zzcm(parcel);
        int i = 0;
        ParcelFileDescriptor parcelFileDescriptor = null;
        while (parcel.dataPosition() < zzcm) {
            int zzcl = zza.zzcl(parcel);
            switch (zza.zzgm(zzcl)) {
                case 1:
                    i = zza.zzg(parcel, zzcl);
                    break;
                case 2:
                    parcelFileDescriptor = (ParcelFileDescriptor) zza.zza(parcel, zzcl, ParcelFileDescriptor.CREATOR);
                    break;
                default:
                    zza.zzb(parcel, zzcl);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcm) {
            return new LargeParcelTeleporter(i, parcelFileDescriptor);
        }
        throw new zza.zza("Overread allowed size end=" + zzcm, parcel);
    }
}

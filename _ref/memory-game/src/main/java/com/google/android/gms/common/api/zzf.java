package com.google.android.gms.common.api;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzf implements Creator<Status> {
    static void zza(Status status, Parcel parcel, int i) {
        int zzcn = zzb.zzcn(parcel);
        zzb.zzc(parcel, 1, status.getStatusCode());
        zzb.zza(parcel, 2, status.getStatusMessage(), false);
        zzb.zza(parcel, 3, status.zzaol(), i, false);
        zzb.zzc(parcel, 1000, status.getVersionCode());
        zzb.zzaj(parcel, zzcn);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzca(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzfg(i);
    }

    public Status zzca(Parcel parcel) {
        PendingIntent pendingIntent = null;
        int i = 0;
        int zzcm = zza.zzcm(parcel);
        String str = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzcm) {
            int zzcl = zza.zzcl(parcel);
            switch (zza.zzgm(zzcl)) {
                case 1:
                    i = zza.zzg(parcel, zzcl);
                    break;
                case 2:
                    str = zza.zzq(parcel, zzcl);
                    break;
                case 3:
                    pendingIntent = (PendingIntent) zza.zza(parcel, zzcl, PendingIntent.CREATOR);
                    break;
                case 1000:
                    i2 = zza.zzg(parcel, zzcl);
                    break;
                default:
                    zza.zzb(parcel, zzcl);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcm) {
            return new Status(i2, i, str, pendingIntent);
        }
        throw new zza.zza("Overread allowed size end=" + zzcm, parcel);
    }

    public Status[] zzfg(int i) {
        return new Status[i];
    }
}

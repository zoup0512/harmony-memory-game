package com.google.android.gms.plus.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzh implements Creator<PlusSession> {
    static void zza(PlusSession plusSession, Parcel parcel, int i) {
        int zzcn = zzb.zzcn(parcel);
        zzb.zza(parcel, 1, plusSession.getAccountName(), false);
        zzb.zza(parcel, 2, plusSession.zzbyf(), false);
        zzb.zza(parcel, 3, plusSession.zzbyg(), false);
        zzb.zza(parcel, 4, plusSession.zzbyh(), false);
        zzb.zza(parcel, 5, plusSession.zzbyi(), false);
        zzb.zza(parcel, 6, plusSession.zzbyj(), false);
        zzb.zza(parcel, 7, plusSession.zzalw(), false);
        zzb.zzc(parcel, 1000, plusSession.getVersionCode());
        zzb.zza(parcel, 8, plusSession.zzbyk(), false);
        zzb.zza(parcel, 9, plusSession.zzbyl(), i, false);
        zzb.zzaj(parcel, zzcn);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzrb(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzyj(i);
    }

    public PlusSession zzrb(Parcel parcel) {
        PlusCommonExtras plusCommonExtras = null;
        int zzcm = zza.zzcm(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String[] strArr = null;
        String[] strArr2 = null;
        String[] strArr3 = null;
        String str5 = null;
        while (parcel.dataPosition() < zzcm) {
            int zzcl = zza.zzcl(parcel);
            switch (zza.zzgm(zzcl)) {
                case 1:
                    str5 = zza.zzq(parcel, zzcl);
                    break;
                case 2:
                    strArr3 = zza.zzac(parcel, zzcl);
                    break;
                case 3:
                    strArr2 = zza.zzac(parcel, zzcl);
                    break;
                case 4:
                    strArr = zza.zzac(parcel, zzcl);
                    break;
                case 5:
                    str4 = zza.zzq(parcel, zzcl);
                    break;
                case 6:
                    str3 = zza.zzq(parcel, zzcl);
                    break;
                case 7:
                    str2 = zza.zzq(parcel, zzcl);
                    break;
                case 8:
                    str = zza.zzq(parcel, zzcl);
                    break;
                case 9:
                    plusCommonExtras = (PlusCommonExtras) zza.zza(parcel, zzcl, PlusCommonExtras.CREATOR);
                    break;
                case 1000:
                    i = zza.zzg(parcel, zzcl);
                    break;
                default:
                    zza.zzb(parcel, zzcl);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcm) {
            return new PlusSession(i, str5, strArr3, strArr2, strArr, str4, str3, str2, str, plusCommonExtras);
        }
        throw new zza.zza("Overread allowed size end=" + zzcm, parcel);
    }

    public PlusSession[] zzyj(int i) {
        return new PlusSession[i];
    }
}

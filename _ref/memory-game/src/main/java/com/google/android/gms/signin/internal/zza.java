package com.google.android.gms.signin.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zza implements Creator<AuthAccountResult> {
    static void zza(AuthAccountResult authAccountResult, Parcel parcel, int i) {
        int zzcn = zzb.zzcn(parcel);
        zzb.zzc(parcel, 1, authAccountResult.mVersionCode);
        zzb.zzc(parcel, 2, authAccountResult.zzbzu());
        zzb.zza(parcel, 3, authAccountResult.zzbzv(), i, false);
        zzb.zzaj(parcel, zzcn);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzrp(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzyy(i);
    }

    public AuthAccountResult zzrp(Parcel parcel) {
        int i = 0;
        int zzcm = com.google.android.gms.common.internal.safeparcel.zza.zzcm(parcel);
        Intent intent = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzcm) {
            int zzcl = com.google.android.gms.common.internal.safeparcel.zza.zzcl(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzgm(zzcl)) {
                case 1:
                    i2 = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzcl);
                    break;
                case 2:
                    i = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzcl);
                    break;
                case 3:
                    intent = (Intent) com.google.android.gms.common.internal.safeparcel.zza.zza(parcel, zzcl, Intent.CREATOR);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzcl);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcm) {
            return new AuthAccountResult(i2, i, intent);
        }
        throw new com.google.android.gms.common.internal.safeparcel.zza.zza("Overread allowed size end=" + zzcm, parcel);
    }

    public AuthAccountResult[] zzyy(int i) {
        return new AuthAccountResult[i];
    }
}

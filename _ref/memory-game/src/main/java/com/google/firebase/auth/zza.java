package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zza implements Creator<UserProfileChangeRequest> {
    static void zza(UserProfileChangeRequest userProfileChangeRequest, Parcel parcel, int i) {
        int zzcn = zzb.zzcn(parcel);
        zzb.zzc(parcel, 1, userProfileChangeRequest.mVersionCode);
        zzb.zza(parcel, 2, userProfileChangeRequest.getDisplayName(), false);
        zzb.zza(parcel, 3, userProfileChangeRequest.zzckv(), false);
        zzb.zza(parcel, 4, userProfileChangeRequest.zzckw());
        zzb.zza(parcel, 5, userProfileChangeRequest.zzckx());
        zzb.zzaj(parcel, zzcn);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzvt(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzaef(i);
    }

    public UserProfileChangeRequest[] zzaef(int i) {
        return new UserProfileChangeRequest[i];
    }

    public UserProfileChangeRequest zzvt(Parcel parcel) {
        String str = null;
        boolean z = false;
        int zzcm = com.google.android.gms.common.internal.safeparcel.zza.zzcm(parcel);
        boolean z2 = false;
        String str2 = null;
        int i = 0;
        while (parcel.dataPosition() < zzcm) {
            int zzcl = com.google.android.gms.common.internal.safeparcel.zza.zzcl(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzgm(zzcl)) {
                case 1:
                    i = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzcl);
                    break;
                case 2:
                    str2 = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzcl);
                    break;
                case 3:
                    str = com.google.android.gms.common.internal.safeparcel.zza.zzq(parcel, zzcl);
                    break;
                case 4:
                    z2 = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzcl);
                    break;
                case 5:
                    z = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzcl);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzcl);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcm) {
            return new UserProfileChangeRequest(i, str2, str, z2, z);
        }
        throw new com.google.android.gms.common.internal.safeparcel.zza.zza("Overread allowed size end=" + zzcm, parcel);
    }
}

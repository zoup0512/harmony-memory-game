package com.google.android.gms.auth.api.signin;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zza;
import java.util.ArrayList;

public class zzb implements Creator<GoogleSignInOptions> {
    static void zza(GoogleSignInOptions googleSignInOptions, Parcel parcel, int i) {
        int zzcn = com.google.android.gms.common.internal.safeparcel.zzb.zzcn(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, googleSignInOptions.versionCode);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 2, googleSignInOptions.zzafq(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 3, googleSignInOptions.getAccount(), i, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 4, googleSignInOptions.zzafr());
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 5, googleSignInOptions.zzafs());
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 6, googleSignInOptions.zzaft());
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 7, googleSignInOptions.zzafu(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 8, googleSignInOptions.zzafv(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzaj(parcel, zzcn);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzat(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzdb(i);
    }

    public GoogleSignInOptions zzat(Parcel parcel) {
        String str = null;
        boolean z = false;
        int zzcm = zza.zzcm(parcel);
        String str2 = null;
        boolean z2 = false;
        boolean z3 = false;
        Account account = null;
        ArrayList arrayList = null;
        int i = 0;
        while (parcel.dataPosition() < zzcm) {
            int zzcl = zza.zzcl(parcel);
            switch (zza.zzgm(zzcl)) {
                case 1:
                    i = zza.zzg(parcel, zzcl);
                    break;
                case 2:
                    arrayList = zza.zzc(parcel, zzcl, Scope.CREATOR);
                    break;
                case 3:
                    account = (Account) zza.zza(parcel, zzcl, Account.CREATOR);
                    break;
                case 4:
                    z3 = zza.zzc(parcel, zzcl);
                    break;
                case 5:
                    z2 = zza.zzc(parcel, zzcl);
                    break;
                case 6:
                    z = zza.zzc(parcel, zzcl);
                    break;
                case 7:
                    str2 = zza.zzq(parcel, zzcl);
                    break;
                case 8:
                    str = zza.zzq(parcel, zzcl);
                    break;
                default:
                    zza.zzb(parcel, zzcl);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcm) {
            return new GoogleSignInOptions(i, arrayList, account, z3, z2, z, str2, str);
        }
        throw new zza.zza("Overread allowed size end=" + zzcm, parcel);
    }

    public GoogleSignInOptions[] zzdb(int i) {
        return new GoogleSignInOptions[i];
    }
}

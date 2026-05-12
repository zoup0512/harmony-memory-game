package com.google.android.gms.ads.internal.request;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.List;

public class zzi implements Creator<AutoClickProtectionConfigurationParcel> {
    static void zza(AutoClickProtectionConfigurationParcel autoClickProtectionConfigurationParcel, Parcel parcel, int i) {
        int zzcn = zzb.zzcn(parcel);
        zzb.zzc(parcel, 1, autoClickProtectionConfigurationParcel.versionCode);
        zzb.zza(parcel, 2, autoClickProtectionConfigurationParcel.zzccu);
        zzb.zzb(parcel, 3, autoClickProtectionConfigurationParcel.zzccv, false);
        zzb.zzaj(parcel, zzcn);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzm(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzaq(i);
    }

    public AutoClickProtectionConfigurationParcel[] zzaq(int i) {
        return new AutoClickProtectionConfigurationParcel[i];
    }

    public AutoClickProtectionConfigurationParcel zzm(Parcel parcel) {
        boolean z = false;
        int zzcm = zza.zzcm(parcel);
        List list = null;
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
                case 3:
                    list = zza.zzae(parcel, zzcl);
                    break;
                default:
                    zza.zzb(parcel, zzcl);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcm) {
            return new AutoClickProtectionConfigurationParcel(i, z, list);
        }
        throw new zza.zza("Overread allowed size end=" + zzcm, parcel);
    }
}

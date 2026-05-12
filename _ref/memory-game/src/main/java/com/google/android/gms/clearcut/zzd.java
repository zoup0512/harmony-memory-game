package com.google.android.gms.clearcut;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.playlog.internal.PlayLoggerContext;

public class zzd implements Creator<LogEventParcelable> {
    static void zza(LogEventParcelable logEventParcelable, Parcel parcel, int i) {
        int zzcn = zzb.zzcn(parcel);
        zzb.zzc(parcel, 1, logEventParcelable.versionCode);
        zzb.zza(parcel, 2, logEventParcelable.qu, i, false);
        zzb.zza(parcel, 3, logEventParcelable.qv, false);
        zzb.zza(parcel, 4, logEventParcelable.qw, false);
        zzb.zza(parcel, 5, logEventParcelable.qx, false);
        zzb.zza(parcel, 6, logEventParcelable.qy, false);
        zzb.zza(parcel, 7, logEventParcelable.qz, false);
        zzb.zza(parcel, 8, logEventParcelable.qA);
        zzb.zzaj(parcel, zzcn);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzbx(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzfa(i);
    }

    public LogEventParcelable zzbx(Parcel parcel) {
        byte[][] bArr = null;
        int zzcm = zza.zzcm(parcel);
        int i = 0;
        boolean z = true;
        int[] iArr = null;
        String[] strArr = null;
        int[] iArr2 = null;
        byte[] bArr2 = null;
        PlayLoggerContext playLoggerContext = null;
        while (parcel.dataPosition() < zzcm) {
            int zzcl = zza.zzcl(parcel);
            switch (zza.zzgm(zzcl)) {
                case 1:
                    i = zza.zzg(parcel, zzcl);
                    break;
                case 2:
                    playLoggerContext = (PlayLoggerContext) zza.zza(parcel, zzcl, PlayLoggerContext.CREATOR);
                    break;
                case 3:
                    bArr2 = zza.zzt(parcel, zzcl);
                    break;
                case 4:
                    iArr2 = zza.zzw(parcel, zzcl);
                    break;
                case 5:
                    strArr = zza.zzac(parcel, zzcl);
                    break;
                case 6:
                    iArr = zza.zzw(parcel, zzcl);
                    break;
                case 7:
                    bArr = zza.zzu(parcel, zzcl);
                    break;
                case 8:
                    z = zza.zzc(parcel, zzcl);
                    break;
                default:
                    zza.zzb(parcel, zzcl);
                    break;
            }
        }
        if (parcel.dataPosition() == zzcm) {
            return new LogEventParcelable(i, playLoggerContext, bArr2, iArr2, strArr, iArr, bArr, z);
        }
        throw new zza.zza("Overread allowed size end=" + zzcm, parcel);
    }

    public LogEventParcelable[] zzfa(int i) {
        return new LogEventParcelable[i];
    }
}

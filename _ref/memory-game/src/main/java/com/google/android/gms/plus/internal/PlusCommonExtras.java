package com.google.android.gms.plus.internal;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.common.internal.zzaa;

@KeepName
public class PlusCommonExtras extends AbstractSafeParcelable {
    public static final zzf CREATOR = new zzf();
    private String arO;
    private String arP;
    private final int mVersionCode;

    public PlusCommonExtras() {
        this.mVersionCode = 1;
        this.arO = "";
        this.arP = "";
    }

    PlusCommonExtras(int i, String str, String str2) {
        this.mVersionCode = i;
        this.arO = str;
        this.arP = str2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PlusCommonExtras)) {
            return false;
        }
        PlusCommonExtras plusCommonExtras = (PlusCommonExtras) obj;
        return this.mVersionCode == plusCommonExtras.mVersionCode && zzaa.equal(this.arO, plusCommonExtras.arO) && zzaa.equal(this.arP, plusCommonExtras.arP);
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return zzaa.hashCode(Integer.valueOf(this.mVersionCode), this.arO, this.arP);
    }

    public String toString() {
        return zzaa.zzx(this).zzg("versionCode", Integer.valueOf(this.mVersionCode)).zzg("Gpsrc", this.arO).zzg("ClientCallingPackage", this.arP).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzf.zza(this, parcel, i);
    }

    public void zzan(Bundle bundle) {
        bundle.putByteArray("android.gms.plus.internal.PlusCommonExtras.extraPlusCommon", zzc.zza(this));
    }

    public String zzbyd() {
        return this.arO;
    }

    public String zzbye() {
        return this.arP;
    }
}

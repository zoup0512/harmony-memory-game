package com.google.android.gms.plus.internal;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzaa;
import java.util.Arrays;

public class PlusSession extends AbstractSafeParcelable {
    public static final zzh CREATOR = new zzh();
    private final String[] arR;
    private final String[] arS;
    private final String[] arT;
    private final String arU;
    private final String arV;
    private final String arW;
    private final PlusCommonExtras arX;
    private final String cb;
    private final int mVersionCode;
    private final String og;

    PlusSession(int i, String str, String[] strArr, String[] strArr2, String[] strArr3, String str2, String str3, String str4, String str5, PlusCommonExtras plusCommonExtras) {
        this.mVersionCode = i;
        this.cb = str;
        this.arR = strArr;
        this.arS = strArr2;
        this.arT = strArr3;
        this.arU = str2;
        this.arV = str3;
        this.og = str4;
        this.arW = str5;
        this.arX = plusCommonExtras;
    }

    public PlusSession(String str, String[] strArr, String[] strArr2, String[] strArr3, String str2, String str3, String str4, PlusCommonExtras plusCommonExtras) {
        this.mVersionCode = 1;
        this.cb = str;
        this.arR = strArr;
        this.arS = strArr2;
        this.arT = strArr3;
        this.arU = str2;
        this.arV = str3;
        this.og = str4;
        this.arW = null;
        this.arX = plusCommonExtras;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PlusSession)) {
            return false;
        }
        PlusSession plusSession = (PlusSession) obj;
        return this.mVersionCode == plusSession.mVersionCode && zzaa.equal(this.cb, plusSession.cb) && Arrays.equals(this.arR, plusSession.arR) && Arrays.equals(this.arS, plusSession.arS) && Arrays.equals(this.arT, plusSession.arT) && zzaa.equal(this.arU, plusSession.arU) && zzaa.equal(this.arV, plusSession.arV) && zzaa.equal(this.og, plusSession.og) && zzaa.equal(this.arW, plusSession.arW) && zzaa.equal(this.arX, plusSession.arX);
    }

    public String getAccountName() {
        return this.cb;
    }

    public int getVersionCode() {
        return this.mVersionCode;
    }

    public int hashCode() {
        return zzaa.hashCode(Integer.valueOf(this.mVersionCode), this.cb, this.arR, this.arS, this.arT, this.arU, this.arV, this.og, this.arW, this.arX);
    }

    public String toString() {
        return zzaa.zzx(this).zzg("versionCode", Integer.valueOf(this.mVersionCode)).zzg("accountName", this.cb).zzg("requestedScopes", this.arR).zzg("visibleActivities", this.arS).zzg("requiredFeatures", this.arT).zzg("packageNameForAuth", this.arU).zzg("callingPackageName", this.arV).zzg("applicationName", this.og).zzg("extra", this.arX.toString()).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzh.zza(this, parcel, i);
    }

    public String zzalw() {
        return this.og;
    }

    public String[] zzbyf() {
        return this.arR;
    }

    public String[] zzbyg() {
        return this.arS;
    }

    public String[] zzbyh() {
        return this.arT;
    }

    public String zzbyi() {
        return this.arU;
    }

    public String zzbyj() {
        return this.arV;
    }

    public String zzbyk() {
        return this.arW;
    }

    public PlusCommonExtras zzbyl() {
        return this.arX;
    }

    public Bundle zzbym() {
        Bundle bundle = new Bundle();
        bundle.setClassLoader(PlusCommonExtras.class.getClassLoader());
        this.arX.zzan(bundle);
        return bundle;
    }
}

package com.google.android.gms.measurement.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzab;

public class UserAttributeParcel extends AbstractSafeParcelable {
    public static final zzaj CREATOR = new zzaj();
    public final String aiJ;
    public final long amt;
    public final Long amu;
    public final Float amv;
    public final Double amw;
    public final String name;
    public final int versionCode;
    public final String zD;

    UserAttributeParcel(int i, String str, long j, Long l, Float f, String str2, String str3, Double d) {
        Double d2 = null;
        this.versionCode = i;
        this.name = str;
        this.amt = j;
        this.amu = l;
        this.amv = null;
        if (i == 1) {
            if (f != null) {
                d2 = Double.valueOf(f.doubleValue());
            }
            this.amw = d2;
        } else {
            this.amw = d;
        }
        this.zD = str2;
        this.aiJ = str3;
    }

    UserAttributeParcel(zzak com_google_android_gms_measurement_internal_zzak) {
        this(com_google_android_gms_measurement_internal_zzak.mName, com_google_android_gms_measurement_internal_zzak.amx, com_google_android_gms_measurement_internal_zzak.zzcnn, com_google_android_gms_measurement_internal_zzak.zzcjf);
    }

    UserAttributeParcel(String str, long j, Object obj, String str2) {
        zzab.zzhr(str);
        this.versionCode = 2;
        this.name = str;
        this.amt = j;
        this.aiJ = str2;
        if (obj == null) {
            this.amu = null;
            this.amv = null;
            this.amw = null;
            this.zD = null;
        } else if (obj instanceof Long) {
            this.amu = (Long) obj;
            this.amv = null;
            this.amw = null;
            this.zD = null;
        } else if (obj instanceof String) {
            this.amu = null;
            this.amv = null;
            this.amw = null;
            this.zD = (String) obj;
        } else if (obj instanceof Double) {
            this.amu = null;
            this.amv = null;
            this.amw = (Double) obj;
            this.zD = null;
        } else {
            throw new IllegalArgumentException("User attribute given of un-supported type");
        }
    }

    public Object getValue() {
        return this.amu != null ? this.amu : this.amw != null ? this.amw : this.zD != null ? this.zD : null;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzaj.zza(this, parcel, i);
    }
}

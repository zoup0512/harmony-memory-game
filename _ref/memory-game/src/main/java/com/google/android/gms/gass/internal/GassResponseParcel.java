package com.google.android.gms.gass.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.internal.zzae.zza;
import com.google.android.gms.internal.zzapv;

public final class GassResponseParcel extends AbstractSafeParcelable {
    public static final Creator<GassResponseParcel> CREATOR = new zzd();
    private zza YX = null;
    private byte[] YY;
    public final int versionCode;

    GassResponseParcel(int i, byte[] bArr) {
        this.versionCode = i;
        this.YY = bArr;
        zzaww();
    }

    private void zzawu() {
        if (!zzawv()) {
            try {
                this.YX = zza.zzc(this.YY);
                this.YY = null;
            } catch (Throwable e) {
                throw new IllegalStateException(e);
            }
        }
        zzaww();
    }

    private boolean zzawv() {
        return this.YX != null;
    }

    private void zzaww() {
        if (this.YX == null && this.YY != null) {
            return;
        }
        if (this.YX != null && this.YY == null) {
            return;
        }
        if (this.YX != null && this.YY != null) {
            throw new IllegalStateException("Invalid internal representation - full");
        } else if (this.YX == null && this.YY == null) {
            throw new IllegalStateException("Invalid internal representation - empty");
        } else {
            throw new IllegalStateException("Impossible");
        }
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzd.zza(this, parcel, i);
    }

    public byte[] zzblc() {
        return this.YY != null ? this.YY : zzapv.zzf(this.YX);
    }

    public zza zzbld() {
        zzawu();
        return this.YX;
    }
}

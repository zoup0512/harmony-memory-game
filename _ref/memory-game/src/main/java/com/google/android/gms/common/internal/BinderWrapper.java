package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.annotation.KeepName;

@KeepName
public final class BinderWrapper implements Parcelable {
    public static final Creator<BinderWrapper> CREATOR = new Creator<BinderWrapper>() {
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return zzcf(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return zzgd(i);
        }

        public BinderWrapper zzcf(Parcel parcel) {
            return new BinderWrapper(parcel);
        }

        public BinderWrapper[] zzgd(int i) {
            return new BinderWrapper[i];
        }
    };
    private IBinder xL;

    public BinderWrapper() {
        this.xL = null;
    }

    public BinderWrapper(IBinder iBinder) {
        this.xL = null;
        this.xL = iBinder;
    }

    private BinderWrapper(Parcel parcel) {
        this.xL = null;
        this.xL = parcel.readStrongBinder();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStrongBinder(this.xL);
    }
}

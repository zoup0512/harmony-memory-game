package com.google.android.gms.analytics.internal;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Command implements Parcelable {
    @Deprecated
    public static final Creator<Command> CREATOR = new Creator<Command>() {
        @Deprecated
        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return zzt(parcel);
        }

        @Deprecated
        public /* synthetic */ Object[] newArray(int i) {
            return zzbw(i);
        }

        @Deprecated
        public Command[] zzbw(int i) {
            return new Command[i];
        }

        @Deprecated
        public Command zzt(Parcel parcel) {
            return new Command(parcel);
        }
    };
    private String mValue;
    private String zzbgg;
    private String zzcyw;

    @Deprecated
    Command(Parcel parcel) {
        readFromParcel(parcel);
    }

    @Deprecated
    private void readFromParcel(Parcel parcel) {
        this.zzbgg = parcel.readString();
        this.zzcyw = parcel.readString();
        this.mValue = parcel.readString();
    }

    @Deprecated
    public int describeContents() {
        return 0;
    }

    public String getId() {
        return this.zzbgg;
    }

    public String getValue() {
        return this.mValue;
    }

    @Deprecated
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.zzbgg);
        parcel.writeString(this.zzcyw);
        parcel.writeString(this.mValue);
    }
}

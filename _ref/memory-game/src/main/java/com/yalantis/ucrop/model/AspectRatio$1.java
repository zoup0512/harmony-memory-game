package com.yalantis.ucrop.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class AspectRatio$1 implements Creator<AspectRatio> {
    AspectRatio$1() {
    }

    public AspectRatio createFromParcel(Parcel in) {
        return new AspectRatio(in);
    }

    public AspectRatio[] newArray(int size) {
        return new AspectRatio[size];
    }
}

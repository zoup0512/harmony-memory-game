package com.cube.memorygames.s3;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Image implements Parcelable {
    public static final Creator<Image> CREATOR = new Creator<Image>() {
        public Image createFromParcel(Parcel parcel) {
            return new Image().readFromParcel(parcel);
        }

        public Image[] newArray(int size) {
            return new Image[size];
        }
    };
    private String mId = "";
    private String mUrl = "";

    Image() {
    }

    public Image(String id, String url) {
        this.mId = id;
        this.mUrl = url;
    }

    public String getId() {
        return this.mId;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public Image readFromParcel(Parcel parcel) {
        this.mId = parcel.readString();
        this.mUrl = parcel.readString();
        return this;
    }

    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(this.mId);
        parcel.writeString(this.mUrl);
    }

    public int describeContents() {
        return 0;
    }
}

package com.cube.memorygames.pushes.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class OnlineMatchUser implements Parcelable {
    public static final Creator<OnlineMatchUser> CREATOR = new Creator<OnlineMatchUser>() {
        public OnlineMatchUser createFromParcel(Parcel source) {
            return new OnlineMatchUser(source);
        }

        public OnlineMatchUser[] newArray(int size) {
            return new OnlineMatchUser[size];
        }
    };
    private String country;
    private String displayName;
    private String id;
    private int onlineGamesDraw;
    private int onlineGamesLost;
    private int onlineGamesWon;
    private float onlineRating;
    private String photoUrl;
    private float rating;

    public String getId() {
        return this.id;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public String getCountry() {
        return this.country;
    }

    public float getOnlineRating() {
        return this.onlineRating;
    }

    public String getPhotoUrl() {
        return this.photoUrl;
    }

    public int getOnlineGamesWon() {
        return this.onlineGamesWon;
    }

    public int getOnlineGamesLost() {
        return this.onlineGamesLost;
    }

    public float getRating() {
        return this.rating;
    }

    public int getOnlineGamesDraw() {
        return this.onlineGamesDraw;
    }

    public void setOnlineRating(float onlineRating) {
        this.onlineRating = onlineRating;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.displayName);
        dest.writeString(this.country);
        dest.writeFloat(this.onlineRating);
        dest.writeString(this.photoUrl);
        dest.writeInt(this.onlineGamesWon);
        dest.writeInt(this.onlineGamesLost);
        dest.writeInt(this.onlineGamesDraw);
        dest.writeFloat(this.rating);
    }

    protected OnlineMatchUser(Parcel in) {
        this.id = in.readString();
        this.displayName = in.readString();
        this.country = in.readString();
        this.onlineRating = in.readFloat();
        this.photoUrl = in.readString();
        this.onlineGamesWon = in.readInt();
        this.onlineGamesLost = in.readInt();
        this.onlineGamesDraw = in.readInt();
        this.rating = in.readFloat();
    }
}

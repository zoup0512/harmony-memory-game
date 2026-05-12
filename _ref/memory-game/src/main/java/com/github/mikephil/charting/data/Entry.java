package com.github.mikephil.charting.data;

import android.os.Parcel;
import android.os.ParcelFormatException;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class Entry extends BaseEntry implements Parcelable {
    public static final Creator<Entry> CREATOR = new Creator<Entry>() {
        public Entry createFromParcel(Parcel source) {
            return new Entry(source);
        }

        public Entry[] newArray(int size) {
            return new Entry[size];
        }
    };
    private float x = 0.0f;

    public Entry(float x, float y) {
        super(y);
        this.x = x;
    }

    public Entry(float x, float y, Object data) {
        super(y, data);
        this.x = x;
    }

    public float getX() {
        return this.x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public Entry copy() {
        return new Entry(this.x, getY(), getData());
    }

    public boolean equalTo(Entry e) {
        if (e != null && e.getData() == getData() && Math.abs(e.x - this.x) <= 1.0E-6f && Math.abs(e.getY() - getY()) <= 1.0E-6f) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "Entry, x: " + this.x + " y (sum): " + getY();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(this.x);
        dest.writeFloat(getY());
        if (getData() == null) {
            dest.writeInt(0);
        } else if (getData() instanceof Parcelable) {
            dest.writeInt(1);
            dest.writeParcelable((Parcelable) getData(), flags);
        } else {
            throw new ParcelFormatException("Cannot parcel an Entry with non-parcelable data");
        }
    }

    protected Entry(Parcel in) {
        this.x = in.readFloat();
        setY(in.readFloat());
        if (in.readInt() == 1) {
            setData(in.readParcelable(Object.class.getClassLoader()));
        }
    }
}

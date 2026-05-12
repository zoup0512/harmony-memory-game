package com.mopub.nativeads;

import android.support.annotation.Nullable;

public class IntInterval implements Comparable<IntInterval> {
    private int length;
    private int start;

    public IntInterval(int i, int i2) {
        this.start = i;
        this.length = i2;
    }

    public int getStart() {
        return this.start;
    }

    public int getLength() {
        return this.length;
    }

    public void setStart(int i) {
        this.start = i;
    }

    public void setLength(int i) {
        this.length = i;
    }

    public boolean equals(int i, int i2) {
        return this.start == i && this.length == i2;
    }

    public String toString() {
        return "{start : " + this.start + ", length : " + this.length + "}";
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IntInterval)) {
            return false;
        }
        IntInterval intInterval = (IntInterval) obj;
        if (this.start == intInterval.start && this.length == intInterval.length) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((this.start + 899) * 31) + this.length;
    }

    public int compareTo(@Nullable IntInterval intInterval) {
        if (this.start == intInterval.start) {
            return this.length - intInterval.length;
        }
        return this.start - intInterval.start;
    }
}

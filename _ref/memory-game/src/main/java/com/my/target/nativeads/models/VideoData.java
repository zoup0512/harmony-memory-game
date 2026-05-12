package com.my.target.nativeads.models;

import com.my.target.core.models.a;

public class VideoData extends a<String> {
    private int bitrate;

    public VideoData(String str) {
        this.url = str;
    }

    public int getBitrate() {
        return this.bitrate;
    }

    public void setBitrate(int i) {
        this.bitrate = i;
    }
}

package com.my.target.core.utils;

import com.my.target.Tracer;
import com.my.target.nativeads.models.VideoData;
import java.util.List;

/* compiled from: VideoUtils */
public final class n {
    public static VideoData a(List<VideoData> list, int i) {
        VideoData videoData = null;
        int i2 = 0;
        for (VideoData videoData2 : list) {
            VideoData videoData3;
            int i3;
            int height = videoData2.getHeight();
            if (videoData == null || ((height <= i && i2 > i) || ((height <= i && height > i2) || (height > i && height < i2)))) {
                videoData3 = videoData2;
                i3 = height;
            } else {
                i3 = i2;
                videoData3 = videoData;
            }
            videoData = videoData3;
            i2 = i3;
        }
        Tracer.d("Accepted videoData quality = " + i2 + "p");
        return videoData;
    }
}

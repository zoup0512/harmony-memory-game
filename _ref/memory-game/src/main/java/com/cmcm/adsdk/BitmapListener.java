package com.cmcm.adsdk;

import android.graphics.Bitmap;

public interface BitmapListener {
    void onFailed(String str);

    void onSuccessed(Bitmap bitmap);
}

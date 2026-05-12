package com.appodeal.ads;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;

public interface NativeAd {
    boolean containsVideo();

    String getAdProvider();

    String getAgeRestrictions();

    String getCallToAction();

    String getDescription();

    Bitmap getIcon();

    String getIconUrl();

    Bitmap getImage();

    String getMainImageUrl();

    View getProviderView(Context context);

    float getRating();

    String getTitle();

    void registerViewForInteraction(View view);

    void setAppodealMediaView(AppodealMediaView appodealMediaView);

    void unregisterViewForInteraction();
}

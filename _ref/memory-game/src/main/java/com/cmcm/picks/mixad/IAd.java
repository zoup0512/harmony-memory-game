package com.cmcm.picks.mixad;

import android.view.View;

public interface IAd {
    public static final int TYPE_BOX_GIF = 2;
    public static final int TYPE_BOX_STATIC = 1;
    public static final int TYPE_COMMON_BIG_CARD = 3;
    public static final int TYPE_COMMON_SMALL_CARD = 4;
    public static final int TYPE_SPLASH_GIF = 6;
    public static final int TYPE_SPLASH_STATIC = 5;

    String getAdCallToAction();

    String getAdDesc();

    int getAdType();

    String getBackGroundUrl();

    int getGifShowTimes();

    String getImageOrGifUrl();

    int getSource();

    String getSplashAdUrl();

    int getSplashShowtime();

    String getTitle();

    boolean isClickedToday();

    boolean isShowedToday();

    void registerView(View view);

    void setMixBoxDelegate(IMixBoxDelegate iMixBoxDelegate);

    void setMixBoxDownloadListener(IMixBoxDownloadListener iMixBoxDownloadListener);

    void unregisterView();
}

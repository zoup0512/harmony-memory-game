package com.cmcm.baseapi.ads;

import android.view.View;
import com.cmcm.picks.loader.MpaModule;
import java.util.List;
import java.util.Map;

public interface INativeAd {

    public interface IAdOnClickListener {
        void onAdClick(INativeAd iNativeAd);
    }

    public interface ImpressionListener {
        void onLoggingImpression();
    }

    String getAdBody();

    String getAdCallToAction();

    String getAdCoverImageUrl();

    String getAdIconUrl();

    Object getAdObject();

    IAdOnClickListener getAdOnClickListener();

    String getAdSocialContext();

    double getAdStarRating();

    String getAdTitle();

    String getAdTypeName();

    List<String> getExtPics();

    MpaModule getMpaModule();

    void handleClick();

    boolean hasExpired();

    Boolean isDownLoadApp();

    boolean isNativeAd();

    boolean isPriority();

    boolean registerViewForInteraction(View view);

    boolean registerViewForInteraction_withExtraReportParams(View view, Map<String, String> map);

    void setAdOnClickListener(IAdOnClickListener iAdOnClickListener);

    void setImpressionListener(ImpressionListener impressionListener);

    void unregisterView();
}

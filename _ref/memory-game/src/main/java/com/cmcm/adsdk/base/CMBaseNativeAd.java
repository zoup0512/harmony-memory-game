package com.cmcm.adsdk.base;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.cmcm.baseapi.ads.INativeAd;
import com.cmcm.baseapi.ads.INativeAd.IAdOnClickListener;
import com.cmcm.baseapi.ads.INativeAd.ImpressionListener;
import com.cmcm.picks.loader.MpaModule;
import java.util.List;
import java.util.Map;

public abstract class CMBaseNativeAd implements INativeAd {
    public static final String KEY_AD_TYPE_NAME = "ad_type_name";
    public static final String KEY_APP_ID = "appid";
    public static final String KEY_BANNER_VIEW_SIZE = "banner_view_size";
    public static final String KEY_CACHE_TIME = "cache_time";
    public static final String KEY_CHECK_VIEW = "cm_check_view";
    public static final String KEY_JUHE_POSID = "juhe_posid";
    public static final String KEY_LOAD_LIST = "ad_load_list";
    public static final String KEY_LOAD_SIZE = "load_size";
    public static final String KEY_PLACEMENT_ID = "placementid";
    public static final String KEY_REPORT_PKGNAME = "report_pkg_name";
    public static final String KEY_REPORT_RES = "report_res";
    @Nullable
    private String mAdDescription;
    @Nullable
    protected IAdOnClickListener mAdOnClickListener;
    @Nullable
    private String mAdSocialContext;
    @Nullable
    private double mAdStartRate;
    protected long mCacheTime;
    @Nullable
    private String mCallToAction;
    protected long mCreateTime = System.currentTimeMillis();
    private List<String> mExtPicks;
    protected Map<String, String> mExtraReportParams;
    protected IClickPreHanleListener mIClickPreHanleListener;
    @NonNull
    private String mIconImageUrl;
    @Nullable
    protected ImpressionListener mImpressionListener;
    @Nullable
    private Boolean mIsDownloadApp;
    @Nullable
    private boolean mIsPriority;
    @NonNull
    private String mMainImageUrl;
    private MpaModule mMpaModule;
    @NonNull
    private String mTitle;
    @Nullable
    public OpenDegBrowserListener openDegBrowserListener;

    public interface IClickPreHanleListener {
        boolean preHandle(INativeAd iNativeAd);
    }

    public interface OpenDegBrowserListener {
        void toGetADUrl(String str);
    }

    public void setAdOnClickListener(IAdOnClickListener adOnClickListener) {
        this.mAdOnClickListener = adOnClickListener;
    }

    public void notifyNativeAdClick(INativeAd nativeAd) {
        if (this.mAdOnClickListener != null) {
            this.mAdOnClickListener.onAdClick(nativeAd);
        }
    }

    public void setClickPreHanlerListener(@Nullable IClickPreHanleListener hanler) {
        this.mIClickPreHanleListener = hanler;
    }

    public IAdOnClickListener getAdOnClickListener() {
        return this.mAdOnClickListener;
    }

    public void setImpressionListener(@Nullable ImpressionListener impressionListener) {
        this.mImpressionListener = impressionListener;
    }

    public void setOnClickToLBListener(@Nullable OpenDegBrowserListener openDegBrowserListener) {
        this.openDegBrowserListener = openDegBrowserListener;
    }

    public boolean isNativeAd() {
        return true;
    }

    public String getAdTitle() {
        return this.mTitle;
    }

    public String getAdCoverImageUrl() {
        return this.mMainImageUrl;
    }

    public String getAdIconUrl() {
        return this.mIconImageUrl;
    }

    public String getAdSocialContext() {
        return this.mAdSocialContext;
    }

    public String getAdCallToAction() {
        return this.mCallToAction;
    }

    public Boolean isDownLoadApp() {
        return this.mIsDownloadApp;
    }

    public void setIsDownloadApp(@Nullable Boolean isDownloadApp) {
        this.mIsDownloadApp = isDownloadApp;
    }

    public void setTitle(@NonNull String mTitle) {
        this.mTitle = mTitle;
    }

    public void setAdCoverImageUrl(@NonNull String mainImageUrl) {
        this.mMainImageUrl = mainImageUrl;
    }

    public void setAdIconUrl(@NonNull String iconImageUrl) {
        this.mIconImageUrl = iconImageUrl;
    }

    public void setAdCallToAction(@Nullable String callToAction) {
        this.mCallToAction = callToAction;
    }

    public void setAdSocialContext(@Nullable String adSocialContext) {
        this.mAdSocialContext = adSocialContext;
    }

    public void setAdBody(@Nullable String adDescription) {
        this.mAdDescription = adDescription;
    }

    public String getAdBody() {
        return this.mAdDescription;
    }

    public double getAdStarRating() {
        return this.mAdStartRate;
    }

    public void setAdStarRate(@Nullable double starRate) {
        this.mAdStartRate = starRate;
    }

    public boolean isPriority() {
        return this.mIsPriority;
    }

    public void setIsPriority(@Nullable boolean isPriority) {
        this.mIsPriority = isPriority;
    }

    public void setExtPics(List<String> extPicks) {
        this.mExtPicks = extPicks;
    }

    public List<String> getExtPics() {
        return this.mExtPicks;
    }

    public void setCacheTime(long cacheTime) {
        this.mCacheTime = cacheTime;
    }

    public void setExtraReportParams(Map<String, String> reportParams) {
        this.mExtraReportParams = reportParams;
    }

    public Map<String, String> getExtraReportParams() {
        return this.mExtraReportParams;
    }

    public boolean registerViewForInteraction_withExtraReportParams(View view, Map<String, String> map) {
        return false;
    }

    public boolean hasExpired() {
        return System.currentTimeMillis() - this.mCreateTime >= this.mCacheTime;
    }

    public void setMpaModule(MpaModule mpaModule) {
        this.mMpaModule = mpaModule;
    }

    public MpaModule getMpaModule() {
        return this.mMpaModule;
    }

    public String getRawString(int operation) {
        return "";
    }
}

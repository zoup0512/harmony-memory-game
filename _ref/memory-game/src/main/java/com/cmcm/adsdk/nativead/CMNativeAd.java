package com.cmcm.adsdk.nativead;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import com.cmcm.adsdk.Const;
import com.cmcm.adsdk.base.CMBaseNativeAd;
import com.cmcm.baseapi.ads.INativeAd;
import com.cmcm.baseapi.ads.INativeAd.IAdOnClickListener;
import com.cmcm.baseapi.ads.INativeAd.ImpressionListener;
import com.cmcm.picks.loader.Ad;
import com.cmcm.picks.loader.MpaModule;
import com.cmcm.picks.market.MarketUtils;
import com.cmcm.utils.ReportFactory;
import java.util.Map;

public class CMNativeAd extends CMBaseNativeAd implements OnClickListener, OnTouchListener, IAdOnClickListener, ImpressionListener {
    final CMBaseNativeAd mAd;
    private IAdOnClickListener mAdClickListener = null;
    private String mAdTypeName;
    private View mAdView;
    private IAdOnClickListener mAdapterAdClickListener = null;
    final Context mContext;
    protected boolean mHasReportShow = false;
    private String mPlacementId;
    private String mPosid;
    private String mReportPkgName;
    private int mReportRes;

    public void setReUseAd() {
        this.mHasReportShow = false;
    }

    public CMNativeAd(Context context, IAdOnClickListener adClickListener, Map<String, Object> extras, CMBaseNativeAd ad) {
        this.mContext = context;
        this.mAd = ad;
        this.mAdapterAdClickListener = adClickListener;
        if (extras.containsKey(CMBaseNativeAd.KEY_CACHE_TIME)) {
            this.mAd.setCacheTime(((Long) extras.get(CMBaseNativeAd.KEY_CACHE_TIME)).longValue());
            setCacheTime(((Long) extras.get(CMBaseNativeAd.KEY_CACHE_TIME)).longValue());
        }
        if (extras.containsKey(CMBaseNativeAd.KEY_JUHE_POSID)) {
            setJuhePosid((String) extras.get(CMBaseNativeAd.KEY_JUHE_POSID));
        }
        if (extras.containsKey(CMBaseNativeAd.KEY_REPORT_RES)) {
            setReportRes(((Integer) extras.get(CMBaseNativeAd.KEY_REPORT_RES)).intValue());
        }
        if (extras.containsKey(CMBaseNativeAd.KEY_REPORT_PKGNAME)) {
            setReportPkgName((String) extras.get(CMBaseNativeAd.KEY_REPORT_PKGNAME));
        }
        if (extras.containsKey(CMBaseNativeAd.KEY_PLACEMENT_ID)) {
            setPlacementId((String) extras.get(CMBaseNativeAd.KEY_PLACEMENT_ID));
        }
        if (extras.containsKey(CMBaseNativeAd.KEY_AD_TYPE_NAME)) {
            this.mAdTypeName = (String) extras.get(CMBaseNativeAd.KEY_AD_TYPE_NAME);
        }
        setTitle(ad.getAdTitle());
        setAdCoverImageUrl(ad.getAdCoverImageUrl());
        setAdIconUrl(ad.getAdIconUrl());
        setAdSocialContext(ad.getAdSocialContext());
        setAdCallToAction(ad.getAdCallToAction());
        setAdBody(ad.getAdBody());
        setAdStarRate(ad.getAdStarRating());
        setIsDownloadApp(ad.isDownLoadApp());
        setAdOnClickListener(ad.getAdOnClickListener());
        setIsPriority(ad.isPriority());
        setExtPics(ad.getExtPics());
        setMpaModule(ad.getMpaModule());
        this.mAd.setImpressionListener(this);
    }

    public String getAdTypeName() {
        return !TextUtils.isEmpty(this.mAdTypeName) ? this.mAdTypeName : this.mAd.getAdTypeName();
    }

    public boolean registerViewForInteraction(View view) {
        return registerViewForInteraction_withExtraReportParams(view, null);
    }

    public MpaModule getMpaModule() {
        return this.mAd.getMpaModule();
    }

    public String getRawString(int operation) {
        if (this.mAd != null) {
            return this.mAd.getRawString(operation);
        }
        return "";
    }

    public boolean registerViewForInteraction_withExtraReportParams(View view, Map<String, String> reportParam) {
        this.mExtraReportParams = reportParam;
        this.mAd.setExtraReportParams(reportParam);
        if (this.mAd.registerViewForInteraction(view)) {
            this.mAd.setAdOnClickListener(this);
        } else {
            this.mAdView = view;
            setListener(view, this, this);
        }
        return true;
    }

    public void unregisterView() {
        this.mAd.unregisterView();
        if (this.mAdView != null) {
            setListener(this.mAdView, null, null);
            this.mAdView = null;
        }
        if (this.mAd != null) {
            this.mAd.setAdOnClickListener(null);
        }
    }

    public Object getAdObject() {
        return this.mAd.getAdObject();
    }

    public void handleClick() {
        this.mAd.handleClick();
        onAdClick(this);
    }

    public boolean hasExpired() {
        return this.mAd.hasExpired();
    }

    public void onClick(View view) {
        handleClick();
    }

    public void onAdClick(INativeAd nativeAd) {
        if (this.mAdapterAdClickListener != null) {
            this.mAdapterAdClickListener.onAdClick(this);
        }
        if (this.mAdClickListener != null) {
            this.mAdClickListener.onAdClick(this);
        }
    }

    public void setAdOnClickListener(IAdOnClickListener adOnClickListener) {
        this.mAdClickListener = adOnClickListener;
    }

    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    public void onLoggingImpression() {
        recordImpression();
    }

    private void recordImpression() {
        if (!this.mHasReportShow) {
            if (getAdTypeName().startsWith(Const.KEY_CM) && (this.mAd.getAdObject() instanceof Ad)) {
                ReportFactory.report("view", (Ad) this.mAd.getAdObject(), this.mPosid, null, getExtraReportParams());
            } else {
                MarketUtils.reportExtra("view", this.mReportPkgName, this.mPosid, this.mReportRes, getExtraReportParams(), this.mPlacementId, this, getRawString(1));
            }
            if (this.mImpressionListener != null) {
                this.mImpressionListener.onLoggingImpression();
            }
            this.mHasReportShow = true;
        }
    }

    public void setJuhePosid(@NonNull String posid) {
        this.mPosid = posid;
    }

    public void setReportRes(@Nullable int res) {
        this.mReportRes = res;
    }

    public void setReportPkgName(@Nullable String pkgName) {
        this.mReportPkgName = pkgName;
    }

    public void setPlacementId(@Nullable String placementId) {
        this.mPlacementId = placementId;
    }

    public void setListener(View view, OnClickListener onClickListener, @Nullable OnTouchListener touchListener) {
        if (view != null) {
            view.setOnClickListener(onClickListener);
            view.setOnTouchListener(touchListener);
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    setListener(viewGroup.getChildAt(i), onClickListener, touchListener);
                }
            }
        }
    }
}

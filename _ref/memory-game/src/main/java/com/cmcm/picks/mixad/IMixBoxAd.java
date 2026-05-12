package com.cmcm.picks.mixad;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.cmcm.picks.loader.Ad;
import com.cmcm.picks.market.MarketUtils;
import com.cmcm.utils.ReportFactory;

public class IMixBoxAd implements IAd {
    private Ad ad;
    private boolean isImpressionReport = false;
    private IMixBoxDelegate mAdDelegate = null;
    private Context mContext;
    private IMixBoxDownloadListener mIMixBoxDownloadListener = null;
    private MixBeans mMixBeans;
    private String mPosId;
    private int mViewId;

    public IMixBoxAd(Context context, String posId, MixBeans mMixBeans) {
        this.mContext = context;
        this.mPosId = posId;
        this.mMixBeans = mMixBeans;
        initParams();
    }

    private void initParams() {
        if (this.mMixBeans != null) {
            this.ad = Ad.createAd(this.mMixBeans.getPkg());
            this.ad.setDeepLink(this.mMixBeans.getDeeplink());
            this.ad.setPkg(this.mMixBeans.getPkg());
            this.ad.setPkgUrl(this.mMixBeans.getPkg_url());
            this.ad.setMtType(this.mMixBeans.getMt_type());
            this.ad.setThirdImpUrl(this.mMixBeans.getThird_imp_url());
            this.ad.setClickTrackingUrl(this.mMixBeans.getClick_tracking_url());
            this.ad.setDes(this.mMixBeans.getDes());
            this.ad.setResType(this.mMixBeans.getRes_type());
            this.ad.setTitle(this.mMixBeans.getTitle());
        }
    }

    public int getSource() {
        if (this.mMixBeans != null) {
            return this.mMixBeans.getSource();
        }
        return 0;
    }

    public int getAdType() {
        if (this.mMixBeans != null) {
            return this.mMixBeans.getType();
        }
        return 0;
    }

    public String getImageOrGifUrl() {
        if (this.mMixBeans != null) {
            return this.mMixBeans.getPic_url();
        }
        return null;
    }

    public String getTitle() {
        if (this.mMixBeans != null) {
            return this.mMixBeans.getTitle();
        }
        return null;
    }

    public String getBackGroundUrl() {
        if (this.mMixBeans != null) {
            return this.mMixBeans.getBackground();
        }
        return null;
    }

    public String getAdDesc() {
        if (this.mMixBeans != null) {
            return this.mMixBeans.getDesc();
        }
        return null;
    }

    public String getAdCallToAction() {
        if (this.mMixBeans != null) {
            return this.mMixBeans.getButton_txt();
        }
        return null;
    }

    public int getSplashShowtime() {
        if (this.mMixBeans != null) {
            return this.mMixBeans.getSplashShowtime();
        }
        return 0;
    }

    public String getSplashAdUrl() {
        if (this.mMixBeans != null) {
            return this.mMixBeans.getSplashImageUrl();
        }
        return null;
    }

    public int getGifShowTimes() {
        if (this.mMixBeans != null) {
            return this.mMixBeans.getGiftimes();
        }
        return 0;
    }

    public boolean isClickedToday() {
        if (this.mMixBeans != null) {
            return this.mMixBeans.isClickedInOneDay(this.mPosId);
        }
        return false;
    }

    public boolean isShowedToday() {
        if (this.mMixBeans != null) {
            return this.mMixBeans.isShowedInOneDay(this.mPosId);
        }
        return false;
    }

    public void registerView(View view) {
        if (view != null && this.mMixBeans != null && view.hashCode() != this.mViewId) {
            this.mViewId = view.hashCode();
            impression();
            OnClickListener anonymousClass1 = new OnClickListener() {
                public void onClick(View view) {
                    IMixBoxAd.this.clicked();
                }
            };
            view.setOnClickListener(anonymousClass1);
            if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                for (int i = 0; i < viewGroup.getChildCount(); i++) {
                    viewGroup.getChildAt(i).setOnClickListener(anonymousClass1);
                }
            }
        }
    }

    public void unregisterView() {
        this.mViewId = -1;
    }

    private void impression() {
        if (this.mMixBeans != null) {
            if (!this.isImpressionReport) {
                this.mMixBeans.savePkg(this.mPosId, this.mMixBeans.getPkg(), this.mMixBeans.getTitle(), System.currentTimeMillis());
                if (!(this.mContext == null || TextUtils.isEmpty(this.mPosId) || this.ad == null)) {
                    ReportFactory.report("view", this.ad, this.mPosId, "");
                }
                this.isImpressionReport = true;
            }
            if (this.mAdDelegate != null) {
                this.mAdDelegate.onImpressioned();
                if (this.mMixBeans.getType() == 1 && this.mMixBeans.getBox_reddot() == 1 && !this.mMixBeans.isClickedInOneDay(this.mPosId)) {
                    this.mAdDelegate.onShowReddot();
                } else {
                    this.mAdDelegate.onHideReddot();
                }
            }
        }
    }

    private void clicked() {
        if (this.mContext != null && !TextUtils.isEmpty(this.mPosId) && this.ad != null) {
            MarketUtils.openOrDownloadAdNoDialog(this.mContext, this.mPosId, this.ad, null, null);
            if (this.mMixBeans != null) {
                this.mMixBeans.setClicked(this.mPosId, true);
            }
            if (this.mAdDelegate != null) {
                this.mAdDelegate.onClicked();
                this.mAdDelegate.onHideReddot();
            }
        }
    }

    public void setMixBoxDelegate(IMixBoxDelegate delegate) {
        if (this.mAdDelegate == null) {
            this.mAdDelegate = delegate;
        }
    }

    public void setMixBoxDownloadListener(IMixBoxDownloadListener listener) {
        this.mIMixBoxDownloadListener = listener;
    }
}

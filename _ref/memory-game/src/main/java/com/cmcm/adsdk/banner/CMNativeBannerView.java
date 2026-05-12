package com.cmcm.adsdk.banner;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cmcm.adsdk.BitmapListener;
import com.cmcm.adsdk.CMAdError;
import com.cmcm.adsdk.CMAdManagerFactory;
import com.cmcm.adsdk.Const;
import com.cmcm.adsdk.ImageDownloadListener;
import com.cmcm.adsdk.R;
import com.cmcm.adsdk.nativead.NativeAdManager;
import com.cmcm.adsdk.utils.ImgAsynTask;
import com.cmcm.baseapi.ads.INativeAd;
import com.cmcm.baseapi.ads.INativeAdLoaderListener;
import com.cmcm.utils.g;
import io.fabric.sdk.android.services.events.EventsFilesManager;

public class CMNativeBannerView extends CMAdView {
    private static final String TAG = "CMNativeBannerView";
    private ImageView mCmIcon;
    private Context mContext;
    private ImageView mImgIcon;
    private RelativeLayout mLLOriginBanner;
    private ImageView mMainImg;
    private NativeAdManager mNativeAdManager;
    private TextView mTvDescibe;
    private TextView mTvTitle;
    private TextView mTv_buttonText;
    private View mView;

    class ImgBitMapListener implements BitmapListener {
        private ImageView mImg;

        public ImgBitMapListener(ImageView imagView) {
            this.mImg = imagView;
        }

        public void onFailed(String errorCode) {
            g.b(CMNativeBannerView.TAG, "CMNative nativeBanner icon load error " + errorCode);
        }

        public void onSuccessed(Bitmap bitmap) {
            g.b(CMNativeBannerView.TAG, "CMNative nativeBanner icon load success ");
            if (bitmap != null && this.mImg != null) {
                this.mImg.setImageBitmap(bitmap);
            }
        }
    }

    public CMNativeBannerView(Context context) {
        this(context, null);
    }

    public CMNativeBannerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CMNativeBannerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
    }

    public void loadAd() {
        if (TextUtils.isEmpty(this.posid)) {
            g.b(TAG, "posId is null");
            callBackFailed(CMAdError.PARAMS_ERROR);
            return;
        }
        if (this.mNativeAdManager == null) {
            this.mNativeAdManager = new NativeAdManager(this.mContext, this.posid);
        }
        this.mNativeAdManager.setNativeAdListener(new INativeAdLoaderListener() {
            public void adLoaded() {
                INativeAd ad = CMNativeBannerView.this.mNativeAdManager.getAd();
                if (ad != null) {
                    if (ad.getAdTypeName().equals(Const.KEY_CM) || ad.getAdTypeName().split(EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR)[0].equals(Const.KEY_FB)) {
                        if (CMNativeBannerView.this.isFirstLoaded) {
                            CMNativeBannerView.this.scheduleRefreshTimerIfEnabled();
                        }
                        CMNativeBannerView.this.renderBannerView();
                        CMNativeBannerView.this.renderBannerData(ad);
                        return;
                    }
                    CMNativeBannerView.this.callBackFailed(CMAdError.NO_AD_TYPE_EROOR);
                }
            }

            public void adFailedToLoad(int errorcode) {
                g.b(CMNativeBannerView.TAG, "CMNative ad load failed :" + errorcode);
                CMNativeBannerView.this.callBackFailed(errorcode);
                CMNativeBannerView.this.scheduleRefreshTimerIfEnabled();
            }

            public void adClicked(INativeAd nativeAd) {
                if (CMNativeBannerView.this.mBannerAdListener != null) {
                    CMNativeBannerView.this.mBannerAdListener.onAdClicked(CMNativeBannerView.this);
                }
            }
        });
        this.mAdWasLoaded = true;
        this.mNativeAdManager.loadAd();
    }

    protected void internalLoadAd() {
        g.b(TAG, " CMNative internalLoadAd");
        invalidateView();
        if (this.mNativeAdManager != null) {
            this.mNativeAdManager.preloadAd();
        }
    }

    public void renderBannerView() {
        if (this.adSize == null || !(this.adSize == CMBannerAdSize.BANNER_300_250 || this.adSize == CMBannerAdSize.BANNER_320_50)) {
            g.b(TAG, "adSize must be set");
            callBackFailed(CMAdError.SIZE_ERROR);
            return;
        }
        if (this.adSize == CMBannerAdSize.BANNER_300_250) {
            this.mView = LayoutInflater.from(this.mContext).inflate(R.layout.origin_picks_banner_mid, null);
            this.mMainImg = (ImageView) this.mView.findViewById(R.id.img_mainbackground);
        } else if (this.adSize == CMBannerAdSize.BANNER_320_50) {
            this.mView = LayoutInflater.from(this.mContext).inflate(R.layout.origin_picks_banner_smail, null);
        }
        this.mLLOriginBanner = (RelativeLayout) this.mView.findViewById(R.id.ll_parentGroup);
        this.mImgIcon = (ImageView) this.mView.findViewById(R.id.img_icon);
        this.mTvTitle = (TextView) this.mView.findViewById(R.id.tv_title);
        this.mTvDescibe = (TextView) this.mView.findViewById(R.id.tv_description);
        this.mCmIcon = (ImageView) this.mView.findViewById(R.id.cm_icon);
        this.mTv_buttonText = (TextView) this.mView.findViewById(R.id.tv_btn);
        removeAllViews();
        addView(this.mView);
        setLayoutParams(new LayoutParams(-2, -2));
    }

    public void renderBannerData(INativeAd iNativeAd) {
        CharSequence adTitle = iNativeAd.getAdTitle();
        CharSequence adBody = iNativeAd.getAdBody();
        if (!TextUtils.isEmpty(adTitle)) {
            this.mTvTitle.setText(adTitle);
        }
        if (!TextUtils.isEmpty(adBody)) {
            this.mTvDescibe.setText(adBody);
        }
        Object adIconUrl = iNativeAd.getAdIconUrl();
        if (!TextUtils.isEmpty(adIconUrl)) {
            downLoadImg(adIconUrl, this.mImgIcon);
        }
        adIconUrl = iNativeAd.getAdCoverImageUrl();
        if (!(this.mMainImg == null || TextUtils.isEmpty(adIconUrl))) {
            downLoadImg(adIconUrl, this.mMainImg);
        }
        adTitle = iNativeAd.getAdCallToAction();
        if (TextUtils.isEmpty(adTitle)) {
            this.mTv_buttonText.setText("LEARN MORE");
        } else {
            this.mTv_buttonText.setText(adTitle);
        }
        iNativeAd.registerViewForInteraction(this.mLLOriginBanner);
        if (this.mBannerAdListener != null) {
            this.mBannerAdListener.onAdLoaded(this);
        }
        if (iNativeAd.getAdTypeName().equals(Const.KEY_CM)) {
            this.mCmIcon.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                    try {
                        CMNativeBannerView.this.mContext.startActivity(Intent.parseUri(Const.CM_AD_DETAIL_URL, 0));
                    } catch (Exception e) {
                        if (g.a) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        } else {
            this.mCmIcon.setVisibility(4);
        }
    }

    private void downLoadImg(String uri, ImageView img) {
        ImageDownloadListener imageDownloadListener = CMAdManagerFactory.getImageDownloadListener();
        if (imageDownloadListener == null) {
            new ImgAsynTask(uri, img).execute(new String[0]);
        } else {
            imageDownloadListener.getBitmap(uri, new ImgBitMapListener(img));
        }
    }

    private void callBackFailed(int error) {
        if (this.mBannerAdListener != null) {
            this.mBannerAdListener.adFailedToLoad(this, error);
        }
    }

    protected void invalidateView() {
        this.isFirstLoaded = true;
        this.mView = null;
    }

    public void onDestroy() {
        invalidateView();
        super.onDestroy();
    }
}

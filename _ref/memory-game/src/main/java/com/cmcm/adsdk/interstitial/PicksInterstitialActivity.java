package com.cmcm.adsdk.interstitial;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cmcm.adsdk.BitmapListener;
import com.cmcm.adsdk.CMAdManagerFactory;
import com.cmcm.adsdk.Const;
import com.cmcm.adsdk.R;
import com.cmcm.baseapi.ads.INativeAd;
import com.cmcm.utils.Commons;
import com.cmcm.utils.g;
import com.yalantis.ucrop.view.CropImageView;
import java.net.URISyntaxException;

public class PicksInterstitialActivity extends Activity {
    private static InterstitialAdCallBack sInterstitialAdCallBack = null;
    private static INativeAd sNativeAd;
    private static boolean sOverClickEnable = false;
    private View mAdBodyView;
    private Button mAdCallToActionBtn;
    private ImageView mAdCloseView;
    private ImageView mAdCoverImageView;
    private TextView mAdDes;
    private View mAdDetailView;
    private ImageView mAdIconView;
    private TextView mAdTitleTV;
    private MyHanlderClickListener mHanldeClickListener = new MyHanlderClickListener();
    private View mRootView;

    class MyHanlderClickListener implements OnClickListener {
        MyHanlderClickListener() {
        }

        public void onClick(View v) {
            if (PicksInterstitialActivity.sNativeAd != null) {
                PicksInterstitialActivity.sNativeAd.handleClick();
                if (PicksInterstitialActivity.sInterstitialAdCallBack != null) {
                    PicksInterstitialActivity.sInterstitialAdCallBack.onAdClicked();
                }
            }
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView(R.layout.cm_activity_picks_interstitial);
        initUI();
        fillAdData();
    }

    public void initUI() {
        this.mRootView = findViewById(R.id.root_view);
        this.mAdIconView = (ImageView) findViewById(R.id.iv_icon);
        this.mAdCoverImageView = (ImageView) findViewById(R.id.iv_coverimage);
        this.mAdCloseView = (ImageView) findViewById(R.id.iv_close);
        this.mAdTitleTV = (TextView) findViewById(R.id.tv_title);
        this.mAdDetailView = findViewById(R.id.ll_ad_detail);
        this.mAdDes = (TextView) findViewById(R.id.tv_des);
        this.mAdCallToActionBtn = (Button) findViewById(R.id.btn_calltoaction);
        this.mAdBodyView = findViewById(R.id.ll_ad_body);
        this.mRootView.setClickable(true);
        this.mAdIconView.setOnClickListener(this.mHanldeClickListener);
        this.mAdTitleTV.setOnClickListener(this.mHanldeClickListener);
        this.mAdDes.setOnClickListener(this.mHanldeClickListener);
        this.mAdBodyView.setOnClickListener(this.mHanldeClickListener);
        this.mAdCallToActionBtn.setOnClickListener(this.mHanldeClickListener);
        this.mAdDetailView.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                try {
                    PicksInterstitialActivity.this.startActivity(Intent.parseUri(Const.CM_AD_DETAIL_URL, 0));
                } catch (URISyntaxException e) {
                    if (g.a) {
                        e.printStackTrace();
                    }
                }
            }
        });
        this.mAdCloseView.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                if (PicksInterstitialActivity.sInterstitialAdCallBack != null) {
                    PicksInterstitialActivity.sInterstitialAdCallBack.onAdDismissed();
                }
                PicksInterstitialActivity.this.finish();
            }
        });
        if (sOverClickEnable) {
            this.mRootView.setOnClickListener(this.mHanldeClickListener);
        }
        setCoverImageView();
    }

    private void setCoverImageView() {
        int screenWidth = Commons.getScreenWidth(this) - Commons.dip2px(this, 16.0f);
        int i = (int) (((double) screenWidth) / 1.9d);
        int dip2px = Commons.dip2px(this, CropImageView.DEFAULT_MAX_SCALE_MULTIPLIER);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(screenWidth, i);
        layoutParams.setMargins(0, dip2px, 0, 0);
        this.mAdCoverImageView.setLayoutParams(layoutParams);
    }

    public void fillAdData() {
        if (sNativeAd == null || CMAdManagerFactory.getImageDownloadListener() == null) {
            finish();
            return;
        }
        CMAdManagerFactory.getImageDownloadListener().getBitmap(sNativeAd.getAdCoverImageUrl(), new BitmapListener() {
            public void onFailed(String s) {
            }

            public void onSuccessed(Bitmap bitmap) {
                PicksInterstitialActivity.this.mAdCoverImageView.setImageBitmap(bitmap);
            }
        });
        CMAdManagerFactory.getImageDownloadListener().getBitmap(sNativeAd.getAdIconUrl(), new BitmapListener() {
            public void onFailed(String s) {
            }

            public void onSuccessed(Bitmap bitmap) {
                PicksInterstitialActivity.this.mAdIconView.setImageBitmap(bitmap);
            }
        });
        this.mAdTitleTV.setText(sNativeAd.getAdTitle());
        this.mAdDes.setText(sNativeAd.getAdBody());
        this.mAdCallToActionBtn.setText(sNativeAd.getAdCallToAction());
        if (sInterstitialAdCallBack != null) {
            sInterstitialAdCallBack.onAdDisplayed();
        }
    }

    public static void setNativeAd(INativeAd ad) {
        sNativeAd = ad;
    }

    public void onBackPressed() {
        super.onBackPressed();
        if (sInterstitialAdCallBack != null) {
            sInterstitialAdCallBack.onAdDismissed();
        }
    }

    protected void onResume() {
        super.onResume();
        if (getRequestedOrientation() != 1) {
            setRequestedOrientation(1);
        }
    }

    public static void setOverClickEnable(boolean enable) {
        sOverClickEnable = enable;
    }

    public static void setInterstitialAdCallBack(InterstitialAdCallBack callBack) {
        sInterstitialAdCallBack = callBack;
    }
}

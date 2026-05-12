package com.cmcm.adsdk.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import com.cmcm.adsdk.BitmapListener;
import com.cmcm.adsdk.CMAdManagerFactory;
import com.cmcm.adsdk.Const;
import com.cmcm.adsdk.interstitial.PicksInterstitialActivity;
import com.cmcm.baseapi.ads.INativeAd;
import com.cmcm.picks.loader.Ad;
import com.cmcm.utils.g;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class PicksInterstatialAdapter extends PicksNativeAdapter {
    private List<PicksInterstatialAd> mAdPool = new ArrayList();

    class PicksInterstatialAd extends PicksNativeAd {
        public PicksInterstatialAd(Ad ad, Context context) {
            super(ad, context);
        }

        public boolean registerViewForInteraction(View view) {
            PicksInterstitialActivity.setNativeAd(this);
            Intent intent = new Intent(PicksInterstatialAdapter.this.mContext, PicksInterstitialActivity.class);
            intent.addFlags(268435456);
            PicksInterstatialAdapter.this.mContext.startActivity(intent);
            onLoggingImpression();
            return true;
        }
    }

    public void loadNativeAd(@NonNull Context context, @NonNull Map<String, Object> extras) {
        removeExpiredAds(this.mAdPool);
        if (this.mAdPool.isEmpty()) {
            super.loadNativeAd(context, extras);
        } else {
            issueNextLoadImage();
        }
    }

    protected void removeExpiredAds(List<PicksInterstatialAd> list) {
        if (list != null && list.size() != 0) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                INativeAd iNativeAd = (INativeAd) it.next();
                if (iNativeAd == null || iNativeAd.hasExpired()) {
                    it.remove();
                }
            }
        }
    }

    public void onLoadSuccess(List list) {
        if (!(list == null || list.isEmpty())) {
            for (Object next : list) {
                if (next instanceof Ad) {
                    this.mAdPool.add(new PicksInterstatialAd((Ad) next, this.mContext));
                }
            }
        }
        issueNextLoadImage();
    }

    public void issueNextLoadImage() {
        g.a(Const.TAG, "issueNextLoadImage");
        if (this.mAdPool.isEmpty()) {
            g.a(Const.TAG, "interstial ad poll is null");
            notifyNativeAdFailed("no valid ad");
            return;
        }
        final PicksInterstatialAd picksInterstatialAd = (PicksInterstatialAd) this.mAdPool.remove(0);
        if (picksInterstatialAd == null || TextUtils.isEmpty(picksInterstatialAd.getAdCoverImageUrl())) {
            g.a(Const.TAG, "interstitial ad  cover image is null");
            issueNextLoadImage();
        } else if (picksInterstatialAd == null || CMAdManagerFactory.getImageDownloadListener() == null) {
            notifyNativeAdFailed("no imageloader, interstitial must setimageloader");
        } else {
            CMAdManagerFactory.getImageDownloadListener().getBitmap(picksInterstatialAd.getAdCoverImageUrl(), new BitmapListener() {
                public void onFailed(String s) {
                    g.a(Const.TAG, "interstitial ad " + picksInterstatialAd.getAdTitle() + " cover image load fail");
                    PicksInterstatialAdapter.this.issueNextLoadImage();
                }

                public void onSuccessed(Bitmap bitmap) {
                    g.a(Const.TAG, "interstitial ad " + picksInterstatialAd.getAdTitle() + " cover image load success");
                    PicksInterstatialAdapter.this.notifyNativeAdLoaded(picksInterstatialAd);
                }
            });
            CMAdManagerFactory.getImageDownloadListener().getBitmap(picksInterstatialAd.getAdIconUrl(), new BitmapListener() {
                public void onFailed(String s) {
                    g.a(Const.TAG, "interstitial ad " + picksInterstatialAd.getAdTitle() + " icon load fail");
                }

                public void onSuccessed(Bitmap bitmap) {
                    g.a(Const.TAG, "interstitial ad" + picksInterstatialAd.getAdTitle() + " icon load success");
                }
            });
        }
    }
}

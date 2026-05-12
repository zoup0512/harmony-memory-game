package com.cmcm.adsdk.nativead;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import com.cmcm.adsdk.BitmapListener;
import com.cmcm.adsdk.CMAdManagerFactory;
import com.cmcm.adsdk.Const;
import com.cmcm.adsdk.config.PosBean;
import com.cmcm.baseapi.ads.INativeAd;
import com.cmcm.utils.g;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class NativeAdManagerInternalEx extends NativeAdManagerInternal {
    private boolean mBreakRequest = false;
    private List<ImageINativeAd> mCacheAdList = new ArrayList();

    static class ImageINativeAd implements Comparable<ImageINativeAd> {
        private int mAdLevelIndex;
        private boolean mHasImageCached = false;
        private INativeAd mNativeAd;

        public ImageINativeAd(INativeAd nativeAd, int index) {
            this.mAdLevelIndex = index;
            this.mNativeAd = nativeAd;
        }

        public INativeAd getAdObject() {
            return this.mNativeAd;
        }

        public void setHasCacheImage() {
            this.mHasImageCached = true;
        }

        public boolean hasCacheImage() {
            return this.mHasImageCached;
        }

        public int getAdLevelIndex() {
            return this.mAdLevelIndex;
        }

        public int compareTo(ImageINativeAd another) {
            return Integer.valueOf(this.mAdLevelIndex).compareTo(Integer.valueOf(another.getAdLevelIndex()));
        }
    }

    public NativeAdManagerInternalEx(Context context, String posId) {
        super(context, posId);
    }

    public void loadAd() {
        removeExpiredAd();
        if (!(this.mIsPreload && hasHighPriorityAd()) && (this.mIsPreload || this.mCacheAdList.isEmpty())) {
            this.mBreakRequest = false;
            super.loadAd();
            return;
        }
        super.notifyAdLoaded();
    }

    private void removeExpiredAd() {
        if (!this.mCacheAdList.isEmpty()) {
            Iterator it = this.mCacheAdList.iterator();
            while (it.hasNext()) {
                ImageINativeAd imageINativeAd = (ImageINativeAd) it.next();
                if (imageINativeAd.getAdObject() == null || imageINativeAd.getAdObject().hasExpired()) {
                    it.remove();
                }
            }
        }
    }

    public boolean hasHighPriorityAd() {
        if (this.mCacheAdList.isEmpty() || this.mConfigBeans.isEmpty()) {
            return false;
        }
        if (!((ImageINativeAd) this.mCacheAdList.get(0)).getAdObject().getAdTypeName().equals(((PosBean) this.mConfigBeans.get(0)).name)) {
            return false;
        }
        g.a(Const.TAG, "has high ad ,break load new ad");
        return true;
    }

    protected boolean requestBean(PosBean bean) {
        if (this.mBreakRequest) {
            return false;
        }
        if (!this.mIsPreload || this.mCacheAdList.isEmpty()) {
            return super.requestBean(bean);
        }
        INativeAd adObject = ((ImageINativeAd) this.mCacheAdList.get(0)).getAdObject();
        if (adObject == null || !adObject.getAdTypeName().equalsIgnoreCase(bean.name)) {
            return super.requestBean(bean);
        }
        g.a(Const.TAG, "this ad type has cache ad, beak requestBean");
        this.mBreakRequest = true;
        super.notifyAdLoaded();
        return false;
    }

    public INativeAd getAd() {
        return getAd(false);
    }

    public INativeAd getAd(boolean forceImage) {
        removeExpiredAd();
        if (this.mCacheAdList.isEmpty()) {
            return null;
        }
        if (!forceImage) {
            return ((ImageINativeAd) this.mCacheAdList.remove(0)).getAdObject();
        }
        Iterator it = this.mCacheAdList.iterator();
        while (it.hasNext()) {
            ImageINativeAd imageINativeAd = (ImageINativeAd) it.next();
            if (imageINativeAd.hasCacheImage()) {
                it.remove();
                return imageINativeAd.getAdObject();
            }
            preloadNativeAdImage(imageINativeAd);
        }
        return null;
    }

    protected void notifyAdLoaded() {
        INativeAd ad = super.getAd();
        if (ad != null) {
            ImageINativeAd imageINativeAd = new ImageINativeAd(ad, getAdTypeNameIndex(ad.getAdTypeName()));
            preloadNativeAdImage(imageINativeAd);
            this.mCacheAdList.add(imageINativeAd);
            Collections.sort(this.mCacheAdList);
        }
        super.notifyAdLoaded();
    }

    private boolean preloadNativeAdImage(final ImageINativeAd imageINativeAd) {
        if (imageINativeAd == null || TextUtils.isEmpty(imageINativeAd.getAdObject().getAdCoverImageUrl())) {
            return false;
        }
        g.a(Const.TAG, "preload image ad title:" + imageINativeAd.getAdObject().getAdTitle() + ",ad type is " + imageINativeAd.getAdObject().getAdTypeName());
        if (CMAdManagerFactory.getImageDownloadListener() == null) {
            return false;
        }
        CMAdManagerFactory.getImageDownloadListener().getBitmap(imageINativeAd.getAdObject().getAdIconUrl(), null);
        CMAdManagerFactory.getImageDownloadListener().getBitmap(imageINativeAd.getAdObject().getAdCoverImageUrl(), new BitmapListener() {
            public void onFailed(String errorCode) {
                g.a(Const.TAG, "preload image ad title:" + imageINativeAd.getAdObject().getAdTitle() + " failed");
            }

            public void onSuccessed(Bitmap bitmap) {
                imageINativeAd.setHasCacheImage();
                g.a(Const.TAG, "preload image ad title:" + imageINativeAd.getAdObject().getAdTitle() + " successed");
            }
        });
        return true;
    }
}

package com.cmcm.adsdk.banner;

import com.cmcm.adsdk.CMRequestParams;

public class CMBannerParams extends CMRequestParams {
    public void setBannerViewSize(CMBannerAdSize mBannerAdSize) {
        if (this.mParams != null) {
            this.mParams.put(CMRequestParams.KEY_BANNER_VIEW_SIZE, mBannerAdSize);
        }
    }

    public CMBannerAdSize getCMBannerAdSize() {
        if (this.mParams != null) {
            Object obj = this.mParams.get(CMRequestParams.KEY_BANNER_VIEW_SIZE);
            if (obj != null) {
                return (CMBannerAdSize) obj;
            }
        }
        return null;
    }
}

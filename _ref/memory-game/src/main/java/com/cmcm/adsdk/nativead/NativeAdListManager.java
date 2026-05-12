package com.cmcm.adsdk.nativead;

import android.content.Context;
import com.cmcm.adsdk.config.PosBean;
import com.cmcm.baseapi.ads.INativeAd;
import com.cmcm.utils.ThreadHelper;
import java.util.List;
import java.util.concurrent.Callable;

public class NativeAdListManager {
    private NativeAdsManagerInternal mRequest;

    public NativeAdListManager(Context context, String posid, INativeAdListListener listener) {
        this.mRequest = new NativeAdsManagerInternal(context, posid);
        this.mRequest.setAdListener(listener);
    }

    public void loadAds(int num) {
        this.mRequest.loadAds(num);
    }

    public void setOpenPriority(boolean openPriority) {
        this.mRequest.setOpenPriority(openPriority);
    }

    public List<INativeAd> getAdList() {
        return (List) ThreadHelper.runOnUiThreadBlockingNoException(new Callable<List<INativeAd>>() {
            public List<INativeAd> call() throws Exception {
                if (NativeAdListManager.this.mRequest != null) {
                    return NativeAdListManager.this.mRequest.getAdList();
                }
                return null;
            }
        });
    }

    public List<PosBean> getPosBeans() {
        return this.mRequest.getPosBeans();
    }

    public String getRequestLastError() {
        if (this.mRequest != null) {
            return this.mRequest.mRequestLogger.getLastResult();
        }
        return null;
    }

    public String getRequestErrorInfo() {
        if (this.mRequest != null) {
            return this.mRequest.mRequestLogger.getRequestErrorInfo();
        }
        return null;
    }
}

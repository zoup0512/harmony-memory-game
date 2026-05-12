package com.cmcm.adsdk.nativead;

import android.content.Context;
import com.cmcm.adsdk.config.PosBean;
import com.cmcm.baseapi.ads.INativeAd;
import com.cmcm.utils.ThreadHelper;
import java.util.List;
import java.util.concurrent.Callable;

public class NativeAdManagerEx extends NativeAdManager {
    private NativeAdManagerInternalEx managerInternalEx;

    public NativeAdManagerEx(Context context, String posid) {
        super(context, posid);
        this.managerInternalEx = new NativeAdManagerInternalEx(context, posid);
    }

    public INativeAd getAd(final boolean forceImageSuccess) {
        return (INativeAd) ThreadHelper.runOnUiThreadBlockingNoException(new Callable<INativeAd>() {
            public INativeAd call() throws Exception {
                if (NativeAdManagerEx.this.managerInternalEx != null) {
                    return NativeAdManagerEx.this.managerInternalEx.getAd(forceImageSuccess);
                }
                return null;
            }
        });
    }

    public boolean hasHighPriorityAd() {
        return this.managerInternalEx.hasHighPriorityAd();
    }

    public String getHighPriorityType() {
        List posBeans = this.managerInternalEx.getPosBeans();
        if (posBeans == null || posBeans.isEmpty()) {
            return null;
        }
        return ((PosBean) posBeans.get(0)).getAdName();
    }
}

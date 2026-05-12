package com.cmcm.adsdk.nativead;

import android.content.Context;
import android.text.TextUtils;
import com.cmcm.adsdk.CMAdManager;
import com.cmcm.adsdk.Const;
import com.cmcm.adsdk.config.PosBean;
import com.cmcm.baseapi.ads.INativeAd.IAdOnClickListener;
import com.cmcm.utils.g;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NativeAdLoaderMap {
    final List<String> mFailedLoaderNames = new ArrayList();
    private final Map<String, CMNativeAdLoader> mLoaderCacheMap = new HashMap();
    List<PosBean> oldBeanList = new ArrayList();

    private boolean assurePosbeanSame(List<PosBean> newPosbean, List<PosBean> oldPosbean) {
        if (newPosbean.size() != oldPosbean.size()) {
            return false;
        }
        for (int i = 0; i < oldPosbean.size(); i++) {
            PosBean posBean = (PosBean) newPosbean.get(i);
            PosBean posBean2 = (PosBean) oldPosbean.get(i);
            if (posBean.name == null || posBean.parameter == null || !posBean.name.equalsIgnoreCase(posBean2.name) || !posBean.parameter.equalsIgnoreCase(posBean2.parameter)) {
                return false;
            }
        }
        return true;
    }

    public void updateLoaders(Context context, List<PosBean> posBeans, IAdOnClickListener onClickListener) {
        if (!assurePosbeanSame(posBeans, this.oldBeanList)) {
            this.oldBeanList = posBeans;
            this.mLoaderCacheMap.clear();
        }
        this.mFailedLoaderNames.clear();
        for (PosBean posBean : posBeans) {
            CMNativeAdLoader adLoader = getAdLoader(context, posBean, onClickListener);
            this.mLoaderCacheMap.put(posBean.name, adLoader);
            if (adLoader == null) {
                this.mFailedLoaderNames.add(posBean.name);
            }
        }
        g.a(Const.TAG, "mConfigBeans size:" + posBeans.size() + " mLoaderCacheMap size:" + this.mLoaderCacheMap.size());
    }

    public CMNativeAdLoader getAdLoader(Context context, PosBean posBean, IAdOnClickListener onClickListener) {
        if (posBean == null || TextUtils.isEmpty(posBean.name) || !posBean.isValidInfo()) {
            return null;
        }
        if (this.mLoaderCacheMap.containsKey(posBean.name)) {
            return (CMNativeAdLoader) this.mLoaderCacheMap.get(posBean.name);
        }
        CMNativeAdLoader cMNativeAdLoader = (CMNativeAdLoader) CMAdManager.getFactory().createAdLoader(context, posBean);
        if (cMNativeAdLoader == null) {
            return cMNativeAdLoader;
        }
        cMNativeAdLoader.setAdClickListener(onClickListener);
        this.mLoaderCacheMap.put(posBean.name, cMNativeAdLoader);
        return cMNativeAdLoader;
    }

    public CMNativeAdLoader getAdLoader(Object key) {
        if (this.mLoaderCacheMap.containsKey(key)) {
            return (CMNativeAdLoader) this.mLoaderCacheMap.get(key);
        }
        return null;
    }

    public boolean containsKey(Object key) {
        return this.mLoaderCacheMap.containsKey(key);
    }
}

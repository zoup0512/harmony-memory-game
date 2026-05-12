package com.cmcm.adsdk;

import android.content.Context;
import android.text.TextUtils;
import com.cmcm.adsdk.adapter.NativeloaderAdapter;
import com.cmcm.adsdk.config.PosBean;
import com.cmcm.adsdk.config.RequestConfig;
import com.cmcm.adsdk.nativead.CMNativeAdLoader;
import com.cmcm.picks.vastvideo.b;
import com.cmcm.utils.ThreadHelper;
import com.cmcm.utils.g;
import io.fabric.sdk.android.services.events.EventsFilesManager;

public class CMAdManagerFactory extends CMBaseFactory {
    private static ImageDownloadListener sImageDownloadListener;

    public CMAdManagerFactory() {
        this.mNativeAdLoaderClassMap.put(Const.KEY_FB, "com.cmcm.adsdk.adapter.FacebookNativeAdapter");
        this.mNativeAdLoaderClassMap.put(Const.KEY_YH, "com.cmcm.adsdk.adapter.YahooNativeAdapter");
        this.mNativeAdLoaderClassMap.put(Const.KEY_MP, "com.cmcm.adsdk.adapter.MopubNativeAdapter");
        this.mNativeAdLoaderClassMap.put(Const.KEY_AB, "com.cmcm.adsdk.adapter.AdmobNativeAdapter");
        this.mNativeAdLoaderClassMap.put(Const.KEY_CM_BANNER, "com.cmcm.adsdk.adapter.PicksBannerAdapter");
        this.mNativeAdLoaderClassMap.put(Const.KEY_MP_BANNER, "com.cmcm.adsdk.adapter.MopubBannerAdapter");
    }

    public void initConfig() {
        RequestConfig.getInstance().init(CMAdManager.getContext(), CMAdManager.getMid());
        RequestConfig.getInstance().requestConfig(false);
    }

    public void clearVastCache(final Context context) {
        ThreadHelper.postDelay(new Runnable() {
            public void run() {
                b.a().a(context);
            }
        }, 30000);
    }

    public static void setDefaultConfig(String defaultConfig, boolean force) {
        RequestConfig.getInstance().setDefaultConfig(defaultConfig, force);
    }

    public Object createAdLoader(Context context, Object posBean) {
        if (!(posBean instanceof PosBean)) {
            return null;
        }
        PosBean posBean2 = (PosBean) posBean;
        if (posBean == null || TextUtils.isEmpty(posBean2.name)) {
            return null;
        }
        try {
            String[] split = posBean2.name.split(EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR);
            if (split.length == 0) {
                g.a(Const.TAG, "config type:" + posBean2.name + ",has error");
                return null;
            }
            Object createObject;
            String toLowerCase = split[0].toLowerCase();
            String valueOf = String.valueOf(posBean2.placeid);
            String str = posBean2.parameter;
            String str2 = posBean2.name;
            if (this.mNativeAdLoaderClassMap.containsKey(toLowerCase)) {
                g.a(Const.TAG, "create NativeAdapter:" + str2 + " [ loaderName:" + toLowerCase + "]");
                createObject = createObject((String) this.mNativeAdLoaderClassMap.get(toLowerCase));
            } else {
                g.c(Const.TAG, "unmatched native adtype:" + str2);
                createObject = null;
            }
            if (createObject != null) {
                return new CMNativeAdLoader(context, valueOf, str2, str, posBean2, (NativeloaderAdapter) createObject);
            }
            return null;
        } catch (Exception e) {
            g.c(Const.TAG, e.toString());
        }
    }

    public static void setImageDownloadListener(ImageDownloadListener listener) {
        sImageDownloadListener = listener;
    }

    public static ImageDownloadListener getImageDownloadListener() {
        return sImageDownloadListener;
    }

    private static Object createObject(String className) {
        Object obj = null;
        try {
            obj = Class.forName(className).getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception e) {
            g.c(Const.TAG, e.toString());
        }
        return obj;
    }
}

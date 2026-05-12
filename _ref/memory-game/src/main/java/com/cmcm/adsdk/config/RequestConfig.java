package com.cmcm.adsdk.config;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.cmcm.adsdk.CMAdErrorManager;
import com.cmcm.adsdk.CMAdManager;
import com.cmcm.adsdk.Const;
import com.cmcm.adsdk.config.ConfigResponse.AdPosInfo;
import com.cmcm.adsdk.config.RequestAction.RequestListener;
import com.cmcm.adsdk.utils.BackgroundHandler;
import com.cmcm.adsdk.utils.PerferenceUtil;
import com.cmcm.utils.Commons;
import com.cmcm.utils.ThreadHelper;
import com.cmcm.utils.g;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RequestConfig {
    private static final int DEFAULT_INTERVAL = 7200;
    private static final String KEY_CONFIG_LOADED_TIME = "config_loaded_time";
    private static final String KEY_DEFAULT_CONFIG = "default_config";
    private static final String KEY_SPLASH_FREQUENCY = "splash_frequency";
    private static final String TAG = "RequestConfig";
    private static RequestConfig sInstance;
    private List<QueueTask> mBackupQueueTask = new ArrayList();
    boolean mConfigLoaded = false;
    private Map<String, AdPosInfo> mConfigMap = new HashMap();
    private Context mContext;
    private String mDefaultConfig = null;
    private boolean mForceDefaultConfig = false;
    private volatile boolean mIsLoading = false;
    private String mMid;
    private final RequestAction requestAction = new RequestAction();

    public interface ICallBack {
        void onConfigLoaded(String str, List<PosBean> list);
    }

    static class QueueTask {
        ICallBack mCallback;
        String mPosId;

        QueueTask(String posId, ICallBack callback) {
            this.mPosId = posId;
            this.mCallback = callback;
        }
    }

    public void init(Context context, String publishId) {
        this.mContext = context;
        this.mMid = publishId;
        PerferenceUtil.init(context, publishId);
        ConfigChangeMonitor.getInstance(context).start(this.mMid);
    }

    public static RequestConfig getInstance() {
        if (sInstance == null) {
            sInstance = new RequestConfig();
        }
        return sInstance;
    }

    private RequestConfig() {
    }

    public void setDefaultConfig(String strConfig, boolean force) {
        this.mDefaultConfig = strConfig;
        this.mForceDefaultConfig = force;
    }

    public void requestConfig(boolean forceLoad) {
        if (this.mContext != null) {
            if (getConfigLoadedTime() > 0 && !this.mConfigLoaded) {
                loadfromLocal();
            }
            if (this.mForceDefaultConfig || forceLoad || shouldRequstConfig()) {
                loadFromNetwork();
            }
        }
    }

    public void getBeans(final String placeId, final ICallBack callback) {
        if (ThreadHelper.runningOnUiThread()) {
            getBeansOnUI(placeId, callback);
        } else {
            ThreadHelper.postOnUiThread(new Runnable() {
                public void run() {
                    RequestConfig.this.getBeansOnUI(placeId, callback);
                }
            });
        }
    }

    private void getBeansOnUI(String placeId, ICallBack callback) {
        if (this.mConfigLoaded) {
            getBeansSync(placeId, callback);
            return;
        }
        loadfromLocal();
        this.mBackupQueueTask.add(new QueueTask(placeId, callback));
    }

    private void getBeansSync(String placeId, ICallBack callback) {
        if (callback != null) {
            List list;
            AdPosInfo adPosInfo = (AdPosInfo) this.mConfigMap.get(placeId);
            if (adPosInfo != null) {
                list = adPosInfo.orders;
            } else {
                list = null;
            }
            callback.onConfigLoaded(placeId, list);
        }
    }

    private void loadfromLocal() {
        if (!this.mIsLoading) {
            this.mIsLoading = true;
            updateToLocalAsync(null);
        }
    }

    private void loadFromNetwork() {
        if (this.requestAction != null) {
            this.mIsLoading = true;
            this.requestAction.requestConfig(Const.CONFIG_URL, buildParams(this.mMid), new RequestListener() {
                public void onFailed(String reson) {
                    g.d(RequestConfig.TAG, "request failed..." + reson);
                    RequestConfig.this.updateToLocalAsync(null);
                }

                public void onSuccess(String obj) {
                    if (ConfigResponse.isValidResponse(obj)) {
                        RequestConfig.this.updateToLocalAsync(obj);
                        return;
                    }
                    g.d(RequestConfig.TAG, "request config failed...response is invalid");
                    RequestConfig.this.updateToLocalAsync(null);
                }
            });
        }
    }

    private boolean shouldRequstConfig() {
        if (this.mForceDefaultConfig) {
            return true;
        }
        long currentTimeMillis = (System.currentTimeMillis() / 1000) - getConfigLoadedTime();
        if (currentTimeMillis < 7200) {
            return false;
        }
        g.a(TAG, "time:" + currentTimeMillis);
        return true;
    }

    private void updateToLocalAsync(final String config) {
        g.a(TAG, "update config in db");
        BackgroundHandler.executeAsyncTask(new AsyncTask<Void, Void, ConfigResponse>() {
            protected ConfigResponse doInBackground(Void... Object) {
                return RequestConfig.this.updateToLocal(config);
            }

            protected void onPostExecute(ConfigResponse configResponse) {
                g.a(RequestConfig.TAG, "onPostExecute isSuccess:" + configResponse);
                if (!(configResponse == null || configResponse.getPosConfigMap() == null)) {
                    RequestConfig.this.mConfigMap = configResponse.getPosConfigMap();
                    if (RequestUFS.getInstance(RequestConfig.this.mContext).isOpenUFSSetting(RequestConfig.this.mConfigMap)) {
                        RequestUFS.getInstance(RequestConfig.this.mContext).setMid(RequestConfig.this.mMid);
                        RequestUFS.getInstance(RequestConfig.this.mContext).requestUFSInfo();
                    }
                }
                RequestConfig.this.notifyLoaded();
            }
        }, new Void[0]);
    }

    private void notifyLoaded() {
        this.mIsLoading = false;
        this.mConfigLoaded = true;
        issueWaitingQueries();
        CMAdErrorManager.setIsCompleteConfig(true);
    }

    private void issueWaitingQueries() {
        for (QueueTask queueTask : this.mBackupQueueTask) {
            if (queueTask.mCallback != null) {
                getBeansSync(queueTask.mPosId, queueTask.mCallback);
            }
        }
        this.mBackupQueueTask.clear();
    }

    private ConfigResponse updateToLocal(String obj) {
        if (TextUtils.isEmpty(obj)) {
            g.a(TAG, "request server config failed, use last local config");
            obj = PerferenceUtil.getCacheJsonStr("");
        }
        if (this.mForceDefaultConfig || (TextUtils.isEmpty(obj) && shouldTryDefaultConfig())) {
            setDefaultConfigUsed(true);
            g.a(TAG, "request server config failed, use default config");
            obj = this.mDefaultConfig;
        }
        if (TextUtils.isEmpty(obj)) {
            g.a(TAG, "request server config and default config failed, update config failed");
            return null;
        }
        g.a(TAG, "save config to shareprefrence:" + obj);
        putConfigLoadedTime(System.currentTimeMillis() / 1000);
        PerferenceUtil.saveCacheJsonStr(obj);
        ConfigResponse createFrom = ConfigResponse.createFrom(obj);
        g.a(TAG, "reponse:" + createFrom);
        return createFrom;
    }

    private boolean shouldTryDefaultConfig() {
        return (TextUtils.isEmpty(this.mDefaultConfig) || getDefaultConfigUsed()) ? false : true;
    }

    public void destory() {
        if (this.requestAction != null) {
            this.requestAction.destory();
        }
    }

    public void putConfigLoadedTime(long value) {
        PerferenceUtil.putLong(KEY_CONFIG_LOADED_TIME, value);
    }

    public long getConfigLoadedTime() {
        return PerferenceUtil.getLong(KEY_CONFIG_LOADED_TIME, 0);
    }

    public boolean getDefaultConfigUsed() {
        return PerferenceUtil.getBoolean(KEY_DEFAULT_CONFIG, false);
    }

    public void setDefaultConfigUsed(boolean value) {
        PerferenceUtil.putBoolean(KEY_DEFAULT_CONFIG, value);
    }

    public static String buildParams(String mid) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("action=pos_config");
        stringBuilder.append("&postype=1");
        stringBuilder.append("&mid=" + mid);
        stringBuilder.append("&posid=");
        stringBuilder.append("&cver=" + Commons.getAppVersionCode(CMAdManager.getContext()));
        stringBuilder.append("&lan=" + Commons.getCountry(CMAdManager.getContext()) + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + Commons.getLanguage(CMAdManager.getContext()));
        stringBuilder.append("&v=18");
        stringBuilder.append("&sdkv=3.4.7");
        return stringBuilder.toString();
    }
}

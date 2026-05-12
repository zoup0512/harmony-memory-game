package com.cmcm.picks.mixad;

import android.content.Context;
import android.text.TextUtils;
import com.cmcm.adsdk.utils.BackgroundHandler;
import com.cmcm.utils.ThreadHelper;
import java.util.ArrayList;
import java.util.List;

public class MixBoxManager {
    private static final int MIXBOX_DEFAULT_LOAD_NUM = -1;
    private Context mContext;
    private boolean mDataLoading = false;
    private List<String> mListData;
    private String mPosId;
    private POS_TYPE mPosType = null;

    public enum POS_TYPE {
        enum_box,
        enum_common_card,
        enum_splash,
        enum_all
    }

    private class ParseTask implements Runnable {
        private int mAdCount;
        private IMixBoxListener mCallback;
        private Context mContext;
        private int mDays;
        private boolean mIsList;
        private List<String> mListData;
        private String mPosId;

        private ParseTask(Context context, boolean isList, int count, String posId, List<String> listData, IMixBoxListener callback) {
            this.mPosId = posId;
            this.mAdCount = count;
            this.mContext = context;
            this.mIsList = isList;
            this.mListData = listData;
            this.mCallback = callback;
        }

        private void setDays(int days) {
            this.mDays = days;
        }

        public void run() {
            List analyzeListStr = MixBoxAdHelper.analyzeListStr(this.mPosId, this.mListData, this.mContext);
            MixBoxAdHelper.clearUselessPkg(this.mPosId, analyzeListStr);
            resultPostMainThread(cleanInvalidAd(analyzeListStr, this.mAdCount));
        }

        protected void resultPostMainThread(final List<IAd> adList) {
            ThreadHelper.postOnUiThread(new Runnable() {
                public void run() {
                    MixBoxManager.this.mDataLoading = false;
                    if (ParseTask.this.mCallback != null) {
                        if (adList == null || adList.isEmpty() || ParseTask.this.mContext == null || TextUtils.isEmpty(ParseTask.this.mPosId)) {
                            ParseTask.this.mCallback.onFailed(IAdError.NO_VALID_CONFIG);
                        } else if (ParseTask.this.mIsList) {
                            ParseTask.this.mCallback.onAdListLoaded(adList);
                        } else {
                            ParseTask.this.mCallback.onAdLoaded((IAd) adList.get(0));
                        }
                    }
                }
            });
        }

        private List<IAd> cleanInvalidAd(List<MixBeans> listAd, int adCount) {
            List<IAd> arrayList = new ArrayList();
            if (listAd != null && !listAd.isEmpty()) {
                int i = 0;
                for (MixBeans mixBeans : listAd) {
                    if (mixBeans != null) {
                        if (mixBeans.isValid(MixBoxManager.this.mPosType, this.mDays)) {
                            arrayList.add(new IMixBoxAd(this.mContext, this.mPosId, mixBeans));
                            if (adCount >= 0) {
                                i++;
                                if (i >= adCount) {
                                    break;
                                }
                            }
                        }
                        i = i;
                    }
                }
            }
            return arrayList;
        }
    }

    public MixBoxManager(Context context, String posId, List<String> listData, POS_TYPE posType) {
        if (context == null || TextUtils.isEmpty(posId)) {
            throw new IllegalArgumentException("Context or posId can't be null");
        }
        this.mContext = context.getApplicationContext();
        this.mPosId = posId;
        this.mListData = listData;
        this.mPosType = posType;
        MixSPUtil.init(context);
    }

    public void loadAd(IMixBoxListener callback) {
        loadAd(false, -1, 0, callback);
    }

    public void loadAdList(int nAdCount, IMixBoxListener callback) {
        loadAd(true, nAdCount, 0, callback);
    }

    public void loadAdList(int nAdCount, int days, IMixBoxListener callback) {
        loadAd(true, nAdCount, days, callback);
    }

    public void setListData(List<String> listData) {
        this.mListData = listData;
    }

    private void loadAd(boolean isList, int nAdCount, int days, IMixBoxListener callback) {
        if (callback != null) {
            List list = this.mListData;
            if (list == null || list.isEmpty()) {
                callback.onFailed(IAdError.NO_CONFIG_ERROR);
            } else if (this.mDataLoading) {
                callback.onFailed(IAdError.REQUEST_TOO_FREQUENTLY);
            } else {
                getValidBean(isList, nAdCount, days, list, callback);
            }
        }
    }

    private void getValidBean(boolean isList, int nAdCount, int days, List<String> listData, IMixBoxListener callback) {
        this.mDataLoading = true;
        Runnable parseTask = new ParseTask(this.mContext, isList, nAdCount, this.mPosId, listData, callback);
        parseTask.setDays(days);
        BackgroundHandler.sBackgroudHandler.post(parseTask);
    }
}

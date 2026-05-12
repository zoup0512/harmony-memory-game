package com.cmcm.adsdk.nativead;

import android.text.TextUtils;
import com.cmcm.adsdk.Const;
import com.cmcm.utils.g;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class RequestResultLogger {
    String mLastResult;
    private Map<String, Model> mRequestResultMap = new HashMap();

    public static class Model {
        public static final String KEY_ADTYPE = "Adtype";
        public static final String KEY_ERRORINFO = "ErrorInfo";
        public static final String KEY_IS_SUCCESS = "IsSuccess";
        public static final String KEY_loadtime = "time";
        private String mFailReason;
        boolean mFinished;
        private boolean mIsSuccess;
        long mRequestBegin;
        long mRequestEnd;

        public Model() {
            this.mFinished = false;
            this.mRequestBegin = 0;
            this.mRequestEnd = 0;
            this.mFinished = false;
            this.mRequestBegin = System.currentTimeMillis();
        }

        public void update(boolean isSuccess, String failReason) {
            this.mIsSuccess = isSuccess;
            this.mFailReason = failReason;
            this.mFinished = true;
            this.mRequestEnd = System.currentTimeMillis();
        }

        public boolean isSuccess() {
            return this.mIsSuccess;
        }

        public String getFailReason() {
            return this.mFailReason;
        }
    }

    void reset() {
        this.mLastResult = null;
        this.mRequestResultMap.clear();
    }

    public Model getFinishedItem(Object key) {
        Model model = (Model) this.mRequestResultMap.get(key);
        return (model == null || !model.mFinished) ? null : model;
    }

    public boolean requestBegin(String adTypeName) {
        if (TextUtils.isEmpty(adTypeName)) {
            return false;
        }
        if (this.mRequestResultMap.containsKey(adTypeName)) {
            g.d(Const.TAG, adTypeName + " has begin load");
            return false;
        }
        g.a(Const.TAG, "begin load " + adTypeName + " to result map");
        this.mRequestResultMap.put(adTypeName, new Model());
        return true;
    }

    public boolean requestEnd(String adTypeName, boolean isSuccess, String errorString) {
        if (TextUtils.isEmpty(adTypeName)) {
            return false;
        }
        if (this.mRequestResultMap.containsKey(adTypeName)) {
            g.a(Const.TAG, "push " + adTypeName + " to result map ,is scuccess:" + isSuccess);
            ((Model) this.mRequestResultMap.get(adTypeName)).update(isSuccess, errorString);
            return true;
        }
        g.d(Const.TAG, adTypeName + "not-begin-yet, fail");
        return false;
    }

    public void setRequestResult(String result) {
        this.mLastResult = result;
    }

    public String getLastResult() {
        return this.mLastResult;
    }

    public String getRequestErrorInfo() {
        JSONArray jSONArray = new JSONArray();
        for (String str : this.mRequestResultMap.keySet()) {
            Model model = (Model) this.mRequestResultMap.get(str);
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(Model.KEY_ADTYPE, str);
                if (model.mFinished) {
                    jSONObject.put(Model.KEY_IS_SUCCESS, model.isSuccess());
                    jSONObject.put(Model.KEY_ERRORINFO, model.getFailReason());
                    jSONObject.put(Model.KEY_loadtime, model.mRequestEnd - model.mRequestBegin);
                }
                jSONArray.put(jSONObject);
            } catch (Exception e) {
                if (g.a) {
                    e.printStackTrace();
                }
            }
        }
        return jSONArray.toString();
    }
}

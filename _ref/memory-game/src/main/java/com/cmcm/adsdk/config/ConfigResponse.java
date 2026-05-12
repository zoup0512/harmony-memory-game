package com.cmcm.adsdk.config;

import android.text.TextUtils;
import com.cmcm.utils.g;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONObject;

public class ConfigResponse {
    private static final String KEY_ADTYPE = "adtype";
    private static final String KEY_CONFIG_POSLIST = "poslist";
    private static final String KEY_INFO = "info";
    private static final String KEY_NAME = "name";
    private static final String KEY_PARAMETER = "parameter";
    private static final String KEY_PLACEID = "placeid";
    private static final String KEY_WEIGHT = "weight";
    private static final String TAG = "ConfigResponse";
    private List<PosBean> list = new ArrayList();
    private final Map<String, AdPosInfo> mAdPosConfigMap = new HashMap();

    public static class AdPosInfo {
        public int adType;
        public List<PosBean> orders = new ArrayList();
        public String placementId;
    }

    public Map<String, AdPosInfo> getPosConfigMap() {
        return this.mAdPosConfigMap;
    }

    public List<PosBean> getPostList() {
        return this.list;
    }

    public static boolean isValidResponse(String json) {
        try {
            JSONObject jSONObject = new JSONObject(json);
            if (jSONObject != null) {
                return jSONObject.has(KEY_CONFIG_POSLIST);
            }
        } catch (Exception e) {
        }
        return false;
    }

    public static ConfigResponse createFrom(String json) {
        Exception e;
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        ConfigResponse configResponse;
        try {
            configResponse = new ConfigResponse();
            try {
                JSONArray jSONArray = new JSONObject(json).getJSONArray(KEY_CONFIG_POSLIST);
                if (jSONArray == null) {
                    return configResponse;
                }
                for (int i = 0; i < jSONArray.length(); i++) {
                    AdPosInfo adPosInfo = new AdPosInfo();
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    adPosInfo.adType = jSONObject.optInt(KEY_ADTYPE);
                    adPosInfo.placementId = jSONObject.optString(KEY_PLACEID);
                    JSONArray jSONArray2 = jSONObject.getJSONArray(KEY_INFO);
                    if (jSONArray2 != null) {
                        for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                            JSONObject jSONObject2 = jSONArray2.getJSONObject(i2);
                            PosBean posBean = new PosBean();
                            posBean.name = jSONObject2.optString("name");
                            posBean.parameter = jSONObject2.optString("parameter");
                            posBean.weight = Integer.valueOf(jSONObject2.optInt(KEY_WEIGHT));
                            posBean.adtype = adPosInfo.adType;
                            posBean.placeid = adPosInfo.placementId;
                            if (posBean.weight.intValue() > 0) {
                                adPosInfo.orders.add(posBean);
                            }
                        }
                    }
                    Collections.sort(adPosInfo.orders);
                    configResponse.mAdPosConfigMap.put(adPosInfo.placementId, adPosInfo);
                    configResponse.list.addAll(adPosInfo.orders);
                }
                return configResponse;
            } catch (Exception e2) {
                e = e2;
                g.d(TAG, "ConfigResponse create error..." + e.getMessage());
                return configResponse;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            configResponse = null;
            e = exception;
            g.d(TAG, "ConfigResponse create error..." + e.getMessage());
            return configResponse;
        }
    }

    public AdPosInfo findAdPosInfo(String placementId) {
        if (this.mAdPosConfigMap == null || this.mAdPosConfigMap.isEmpty()) {
            return null;
        }
        return (AdPosInfo) this.mAdPosConfigMap.get(placementId);
    }

    public static Map<String, AdPosInfo> convertToPosConfigMap(List<PosBean> posBeans) {
        Map<String, AdPosInfo> hashMap = new HashMap();
        if (posBeans == null) {
            return hashMap;
        }
        for (PosBean posBean : posBeans) {
            if (posBean.weight.intValue() > 0) {
                AdPosInfo adPosInfo;
                String valueOf = String.valueOf(posBean.placeid);
                if (hashMap.containsKey(valueOf)) {
                    adPosInfo = (AdPosInfo) hashMap.get(valueOf);
                } else {
                    adPosInfo = new AdPosInfo();
                    hashMap.put(valueOf, adPosInfo);
                }
                adPosInfo.orders.add(posBean);
            }
        }
        for (Entry value : hashMap.entrySet()) {
            Collections.sort(((AdPosInfo) value.getValue()).orders);
        }
        return hashMap;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(":poslist{");
        for (PosBean obj : this.list) {
            stringBuilder.append(obj.toString());
            stringBuilder.append(",");
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}

package com.unity3d.ads2.api;

import android.content.Intent;
import com.unity3d.ads2.adunit.AdUnitActivity;
import com.unity3d.ads2.adunit.AdUnitError;
import com.unity3d.ads2.adunit.AdUnitSoftwareActivity;
import com.unity3d.ads2.log.DeviceLog;
import com.unity3d.ads2.misc.Utilities;
import com.unity3d.ads2.properties.ClientProperties;
import com.unity3d.ads2.webview.bridge.WebViewCallback;
import com.unity3d.ads2.webview.bridge.WebViewExposed;
import java.util.ArrayList;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONException;

public class AdUnit {
    private static AdUnitActivity _adUnitActivity;
    private static int _currentActivityId = -1;

    private AdUnit() {
    }

    public static void setAdUnitActivity(AdUnitActivity adUnitActivity) {
        _adUnitActivity = adUnitActivity;
    }

    public static AdUnitActivity getAdUnitActivity() {
        return _adUnitActivity;
    }

    public static int getCurrentAdUnitActivityId() {
        return _currentActivityId;
    }

    public static void setCurrentAdUnitActivityId(int i) {
        _currentActivityId = i;
    }

    @WebViewExposed
    public static void open(Integer num, JSONArray jSONArray, Integer num2, WebViewCallback webViewCallback) {
        open(num, jSONArray, num2, null, webViewCallback);
    }

    @WebViewExposed
    public static void open(Integer num, JSONArray jSONArray, Integer num2, JSONArray jSONArray2, WebViewCallback webViewCallback) {
        open(num, jSONArray, num2, jSONArray2, Integer.valueOf(0), Boolean.valueOf(true), webViewCallback);
    }

    @WebViewExposed
    public static void open(Integer num, JSONArray jSONArray, Integer num2, JSONArray jSONArray2, Integer num3, Boolean bool, WebViewCallback webViewCallback) {
        Intent intent;
        if (bool.booleanValue()) {
            DeviceLog.debug("Unity Ads opening new hardware accelerated ad unit activity");
            intent = new Intent(ClientProperties.getActivity(), AdUnitActivity.class);
        } else {
            DeviceLog.debug("Unity Ads opening new ad unit activity, hardware acceleration disabled");
            intent = new Intent(ClientProperties.getActivity(), AdUnitSoftwareActivity.class);
        }
        intent.addFlags(268500992);
        if (num != null) {
            try {
                intent.putExtra("activityId", num.intValue());
                setCurrentAdUnitActivityId(num.intValue());
                try {
                    intent.putExtra("views", getViewList(jSONArray));
                    if (jSONArray2 != null) {
                        try {
                            intent.putExtra("keyEvents", getKeyEventList(jSONArray2));
                        } catch (Exception e) {
                            DeviceLog.exception("Error parsing views from viewList", e);
                            webViewCallback.error(AdUnitError.CORRUPTED_KEYEVENTLIST, new Object[]{jSONArray2, e.getMessage()});
                            return;
                        }
                    }
                    intent.putExtra("systemUiVisibility", num3);
                    intent.putExtra("orientation", num2);
                    ClientProperties.getActivity().startActivity(intent);
                    DeviceLog.debug("Opened AdUnitActivity with: " + jSONArray.toString());
                    webViewCallback.invoke(new Object[0]);
                    return;
                } catch (Exception e2) {
                    DeviceLog.exception("Error parsing views from viewList", e2);
                    webViewCallback.error(AdUnitError.CORRUPTED_VIEWLIST, new Object[]{jSONArray, e2.getMessage()});
                    return;
                }
            } catch (Exception e22) {
                DeviceLog.exception("Could not set activityId for intent", e22);
                webViewCallback.error(AdUnitError.ACTIVITY_ID, new Object[]{Integer.valueOf(num.intValue()), e22.getMessage()});
                return;
            }
        }
        DeviceLog.error("Activity ID is NULL");
        webViewCallback.error(AdUnitError.ACTIVITY_ID, new Object[]{"Activity ID NULL"});
    }

    @WebViewExposed
    public static void close(WebViewCallback webViewCallback) {
        if (getAdUnitActivity() != null) {
            getAdUnitActivity().finish();
            webViewCallback.invoke(new Object[0]);
            return;
        }
        webViewCallback.error(AdUnitError.ACTIVITY_NULL, new Object[0]);
    }

    @WebViewExposed
    public static void setViews(JSONArray jSONArray, WebViewCallback webViewCallback) {
        int i;
        try {
            getViewList(jSONArray);
            i = 0;
        } catch (JSONException e) {
            webViewCallback.error(AdUnitError.CORRUPTED_VIEWLIST, new Object[]{jSONArray});
            i = 1;
        }
        if (i == 0) {
            Utilities.runOnUiThread(new 1(jSONArray));
        }
        if (getAdUnitActivity() != null) {
            webViewCallback.invoke(new Object[]{jSONArray});
            return;
        }
        webViewCallback.error(AdUnitError.ACTIVITY_NULL, new Object[0]);
    }

    @WebViewExposed
    public static void getViews(WebViewCallback webViewCallback) {
        if (getAdUnitActivity() != null) {
            webViewCallback.invoke(new Object[]{new JSONArray(Arrays.asList(getAdUnitActivity().getViews()))});
            return;
        }
        webViewCallback.error(AdUnitError.ACTIVITY_NULL, new Object[0]);
    }

    @WebViewExposed
    public static void setOrientation(Integer num, WebViewCallback webViewCallback) {
        Utilities.runOnUiThread(new 2(num));
        if (getAdUnitActivity() != null) {
            webViewCallback.invoke(new Object[]{num});
            return;
        }
        webViewCallback.error(AdUnitError.ACTIVITY_NULL, new Object[0]);
    }

    @WebViewExposed
    public static void getOrientation(WebViewCallback webViewCallback) {
        if (getAdUnitActivity() != null) {
            webViewCallback.invoke(new Object[]{Integer.valueOf(getAdUnitActivity().getRequestedOrientation())});
            return;
        }
        webViewCallback.error(AdUnitError.ACTIVITY_NULL, new Object[0]);
    }

    @WebViewExposed
    public static void setKeepScreenOn(Boolean bool, WebViewCallback webViewCallback) {
        Utilities.runOnUiThread(new 3(bool));
        if (getAdUnitActivity() != null) {
            webViewCallback.invoke(new Object[0]);
        } else {
            webViewCallback.error(AdUnitError.ACTIVITY_NULL, new Object[0]);
        }
    }

    @WebViewExposed
    public static void setSystemUiVisibility(Integer num, WebViewCallback webViewCallback) {
        Utilities.runOnUiThread(new 4(num));
        if (getAdUnitActivity() != null) {
            webViewCallback.invoke(new Object[]{num});
            return;
        }
        webViewCallback.error(AdUnitError.ACTIVITY_NULL, new Object[0]);
    }

    @WebViewExposed
    public static void setKeyEventList(JSONArray jSONArray, WebViewCallback webViewCallback) {
        if (getAdUnitActivity() != null) {
            try {
                getAdUnitActivity().setKeyEventList(getKeyEventList(jSONArray));
                webViewCallback.invoke(new Object[]{jSONArray});
                return;
            } catch (Exception e) {
                DeviceLog.exception("Error parsing views from viewList", e);
                webViewCallback.error(AdUnitError.CORRUPTED_KEYEVENTLIST, new Object[]{jSONArray, e.getMessage()});
                return;
            }
        }
        webViewCallback.error(AdUnitError.ACTIVITY_NULL, new Object[0]);
    }

    private static String[] getViewList(JSONArray jSONArray) {
        String[] strArr = new String[jSONArray.length()];
        for (int i = 0; i < jSONArray.length(); i++) {
            strArr[i] = jSONArray.getString(i);
        }
        return strArr;
    }

    private static ArrayList<Integer> getKeyEventList(JSONArray jSONArray) {
        ArrayList<Integer> arrayList = new ArrayList();
        for (Integer valueOf = Integer.valueOf(0); valueOf.intValue() < jSONArray.length(); valueOf = Integer.valueOf(valueOf.intValue() + 1)) {
            arrayList.add(Integer.valueOf(jSONArray.getInt(valueOf.intValue())));
        }
        return arrayList;
    }
}

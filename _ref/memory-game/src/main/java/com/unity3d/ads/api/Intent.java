package com.unity3d.ads.api;

import android.app.Activity;
import android.net.Uri;
import com.facebook.applinks.AppLinkData;
import com.facebook.internal.NativeProtocol;
import com.facebook.share.internal.ShareConstants;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import com.unity3d.ads.log.DeviceLog;
import com.unity3d.ads.properties.ClientProperties;
import com.unity3d.ads.webview.bridge.WebViewCallback;
import com.unity3d.ads.webview.bridge.WebViewExposed;
import org.json.JSONArray;
import org.json.JSONObject;

public class Intent {
    @WebViewExposed
    public static void launch(JSONObject jSONObject, WebViewCallback webViewCallback) {
        android.content.Intent launchIntentForPackage;
        String str = (String) jSONObject.opt("className");
        String str2 = (String) jSONObject.opt("packageName");
        String str3 = (String) jSONObject.opt(NativeProtocol.WEB_DIALOG_ACTION);
        String str4 = (String) jSONObject.opt(ShareConstants.MEDIA_URI);
        String str5 = (String) jSONObject.opt("mimeType");
        JSONArray jSONArray = (JSONArray) jSONObject.opt("categories");
        Integer num = (Integer) jSONObject.opt("flags");
        JSONArray jSONArray2 = (JSONArray) jSONObject.opt(AppLinkData.ARGUMENTS_EXTRAS_KEY);
        if (str2 != null && str == null && str3 == null && str5 == null) {
            launchIntentForPackage = ClientProperties.getApplicationContext().getPackageManager().getLaunchIntentForPackage(str2);
            if (launchIntentForPackage != null && num.intValue() > -1) {
                launchIntentForPackage.addFlags(num.intValue());
            }
        } else {
            android.content.Intent intent = new android.content.Intent();
            if (!(str == null || str2 == null)) {
                intent.setClassName(str2, str);
            }
            if (str3 != null) {
                intent.setAction(str3);
            }
            if (str4 != null) {
                intent.setData(Uri.parse(str4));
            }
            if (str5 != null) {
                intent.setType(str5);
            }
            if (num != null && num.intValue() > -1) {
                intent.setFlags(num.intValue());
            }
            if (!setCategories(intent, jSONArray)) {
                webViewCallback.error(IntentError.COULDNT_PARSE_CATEGORIES, new Object[]{jSONArray});
            }
            if (!setExtras(intent, jSONArray2)) {
                webViewCallback.error(IntentError.COULDNT_PARSE_EXTRAS, new Object[]{jSONArray2});
            }
            launchIntentForPackage = intent;
        }
        if (launchIntentForPackage == null) {
            webViewCallback.error(IntentError.INTENT_WAS_NULL, new Object[0]);
        } else if (getStartingActivity() != null) {
            getStartingActivity().startActivity(launchIntentForPackage);
            webViewCallback.invoke(new Object[0]);
        } else {
            webViewCallback.error(IntentError.ACTIVITY_WAS_NULL, new Object[0]);
        }
    }

    private static boolean setCategories(android.content.Intent intent, JSONArray jSONArray) {
        if (jSONArray != null && jSONArray.length() > 0) {
            int i = 0;
            while (i < jSONArray.length()) {
                try {
                    intent.addCategory(jSONArray.getString(i));
                    i++;
                } catch (Exception e) {
                    DeviceLog.exception("Couldn't parse categories for intent", e);
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean setExtras(android.content.Intent intent, JSONArray jSONArray) {
        if (jSONArray != null) {
            int i = 0;
            while (i < jSONArray.length()) {
                try {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    if (!setExtra(intent, jSONObject.getString(TransferTable.COLUMN_KEY), jSONObject.get(Param.VALUE))) {
                        return false;
                    }
                    i++;
                } catch (Exception e) {
                    DeviceLog.exception("Couldn't parse extras", e);
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean setExtra(android.content.Intent intent, String str, Object obj) {
        if (obj instanceof String) {
            intent.putExtra(str, (String) obj);
        } else if (obj instanceof Integer) {
            intent.putExtra(str, ((Integer) obj).intValue());
        } else if (obj instanceof Double) {
            intent.putExtra(str, ((Double) obj).doubleValue());
        } else if (obj instanceof Boolean) {
            intent.putExtra(str, ((Boolean) obj).booleanValue());
        } else {
            DeviceLog.error("Unable to parse launch intent extra " + str);
            return false;
        }
        return true;
    }

    private static Activity getStartingActivity() {
        if (AdUnit.getAdUnitActivity() != null) {
            return AdUnit.getAdUnitActivity();
        }
        if (ClientProperties.getActivity() != null) {
            return ClientProperties.getActivity();
        }
        return null;
    }
}

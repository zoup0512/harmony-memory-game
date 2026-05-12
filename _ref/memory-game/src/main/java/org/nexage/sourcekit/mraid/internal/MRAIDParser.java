package org.nexage.sourcekit.mraid.internal;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.nexage.sourcekit.mraid.MRAIDNativeFeature;

public class MRAIDParser {
    private static final String TAG = "MRAIDParser";

    public Map<String, String> parseCommandUrl(String str) {
        String substring;
        MRAIDLog.d(TAG, "parseCommandUrl " + str);
        String substring2 = str.substring(8);
        Map hashMap = new HashMap();
        int indexOf = substring2.indexOf(63);
        if (indexOf != -1) {
            substring = substring2.substring(0, indexOf);
            for (String str2 : substring2.substring(indexOf + 1).split("&")) {
                int indexOf2 = str2.indexOf(61);
                hashMap.put(str2.substring(0, indexOf2), str2.substring(indexOf2 + 1));
            }
        } else {
            substring = substring2;
        }
        if (!isValidCommand(substring)) {
            MRAIDLog.w("command " + substring + " is unknown");
            return null;
        } else if (checkParamsForCommand(substring, hashMap)) {
            Map<String, String> hashMap2 = new HashMap();
            hashMap2.put("command", substring);
            hashMap2.putAll(hashMap);
            return hashMap2;
        } else {
            MRAIDLog.w("command URL " + str + " is missing parameters");
            return null;
        }
    }

    private boolean isValidCommand(String str) {
        return Arrays.asList(new String[]{"close", "createCalendarEvent", "expand", "open", "playVideo", "resize", "setOrientationProperties", "setResizeProperties", MRAIDNativeFeature.STORE_PICTURE, "useCustomClose", "noFill", "AdStarted", "AdStopped", "AdSkipped", "AdSkippableStateChange", "AdVideoStart", "AdVideoFirstQuartile", "AdVideoMidpoint", "AdVideoThirdQuartile", "AdVideoComplete", "AdUserClose", "AdPaused", "AdPlaying", "AdClickThru", "AdLog", "AdError"}).contains(str);
    }

    private boolean checkParamsForCommand(String str, Map<String, String> map) {
        if (str.equals("createCalendarEvent")) {
            return map.containsKey("eventJSON");
        }
        if (str.equals("open") || str.equals("playVideo") || str.equals(MRAIDNativeFeature.STORE_PICTURE)) {
            return map.containsKey("url");
        }
        if (str.equals("setOrientationProperties")) {
            if (map.containsKey("allowOrientationChange") && map.containsKey("forceOrientation")) {
                return true;
            }
            return false;
        } else if (str.equals("setResizeProperties")) {
            if (map.containsKey("width") && map.containsKey("height") && map.containsKey("offsetX") && map.containsKey("offsetY") && map.containsKey("customClosePosition") && map.containsKey("allowOffscreen")) {
                return true;
            }
            return false;
        } else if (str.equals("useCustomClose")) {
            return map.containsKey("useCustomClose");
        } else {
            return true;
        }
    }
}

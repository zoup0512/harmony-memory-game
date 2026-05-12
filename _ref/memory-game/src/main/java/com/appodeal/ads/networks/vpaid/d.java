package com.appodeal.ads.networks.vpaid;

import com.cmcm.adsdk.nativead.RequestResultLogger.Model;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class d {
    public Map<String, String> a(String str) {
        String substring;
        c.a("VPAIDParser", "parseCommandUrl " + str);
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
        if (!b(substring)) {
            c.a("command " + substring + " is unknown");
            return null;
        } else if (a(substring, hashMap)) {
            Map<String, String> hashMap2 = new HashMap();
            hashMap2.put("command", substring);
            hashMap2.putAll(hashMap);
            return hashMap2;
        } else {
            c.a("command URL " + str + " is missing parameters");
            return null;
        }
    }

    private boolean b(String str) {
        return Arrays.asList(new String[]{"AdStarted", "AdStopped", "AdSkipped", "AdLoaded", "AdLinearChange", "AdSizeChange", "AdExpandedChange", "AdSkippableStateChange", "AdDurationChange", "AdVolumeChange", "AdImpression", "AdClickThru", "AdInteraction", "AdVideoStart", "AdVideoFirstQuartile", "AdVideoMidpoint", "AdVideoThirdQuartile", "AdVideoComplete", "AdUserAcceptInvitation", "AdUserMinimize", "AdUserClose", "AdPaused", "AdPlaying", "AdError", "AdLog", "AdRemainingTime", "useCustomClose"}).contains(str);
    }

    private boolean a(String str, Map<String, String> map) {
        if (str.equals("AdError") || str.equals("AdError")) {
            return map.containsKey("msg");
        }
        if (str.equals("AdClickThru")) {
            return map.containsKey("url");
        }
        if (str.equals("AdVolumeChange") || str.equals("AdDurationChange") || str.equals("AdSkippableStateChange")) {
            return map.containsKey("state");
        }
        if (str.equals("AdRemainingTime")) {
            return map.containsKey(Model.KEY_loadtime);
        }
        if (str.equals("useCustomClose")) {
            return map.containsKey("useCustomClose");
        }
        return true;
    }
}

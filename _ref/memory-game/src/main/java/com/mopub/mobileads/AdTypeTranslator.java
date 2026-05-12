package com.mopub.mobileads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.AdFormat;
import com.mopub.common.AdType;
import com.mopub.common.util.ResponseHeader;
import com.mopub.network.HeaderUtils;
import java.util.Map;

public class AdTypeTranslator {
    public static final String BANNER_SUFFIX = "_banner";
    public static final String INTERSTITIAL_SUFFIX = "_interstitial";

    public enum CustomEventType {
        GOOGLE_PLAY_SERVICES_BANNER("admob_native_banner", "com.mopub.mobileads.GooglePlayServicesBanner"),
        GOOGLE_PLAY_SERVICES_INTERSTITIAL("admob_full_interstitial", "com.mopub.mobileads.GooglePlayServicesInterstitial"),
        MILLENNIAL_BANNER("millennial_native_banner", "com.mopub.mobileads.MillennialBanner"),
        MILLENNIAL_INTERSTITIAL("millennial_full_interstitial", "com.mopub.mobileads.MillennialInterstitial"),
        MRAID_BANNER("mraid_banner", "com.mopub.mraid.MraidBanner"),
        MRAID_INTERSTITIAL("mraid_interstitial", "com.mopub.mraid.MraidInterstitial"),
        HTML_BANNER("html_banner", "com.mopub.mobileads.HtmlBanner"),
        HTML_INTERSTITIAL("html_interstitial", "com.mopub.mobileads.HtmlInterstitial"),
        VAST_VIDEO_INTERSTITIAL("vast_interstitial", "com.mopub.mobileads.VastVideoInterstitial"),
        MOPUB_NATIVE("mopub_native", "com.mopub.nativeads.MoPubCustomEventNative"),
        MOPUB_VIDEO_NATIVE("mopub_video_native", "com.mopub.nativeads.MoPubCustomEventVideoNative"),
        MOPUB_REWARDED_VIDEO(AdType.REWARDED_VIDEO, "com.mopub.mobileads.MoPubRewardedVideo"),
        UNSPECIFIED("", null);
        
        private final String mClassName;
        private final String mKey;

        private CustomEventType(String str, String str2) {
            this.mKey = str;
            this.mClassName = str2;
        }

        private static CustomEventType fromString(String str) {
            for (CustomEventType customEventType : values()) {
                if (customEventType.mKey.equals(str)) {
                    return customEventType;
                }
            }
            return UNSPECIFIED;
        }

        public String toString() {
            return this.mClassName;
        }
    }

    static String getAdNetworkType(String str, String str2) {
        String str3;
        if (AdType.INTERSTITIAL.equals(str)) {
            str3 = str2;
        } else {
            str3 = str;
        }
        return str3 != null ? str3 : "unknown";
    }

    public static String getCustomEventName(@NonNull AdFormat adFormat, @NonNull String str, @Nullable String str2, @NonNull Map<String, String> map) {
        if (AdType.CUSTOM.equalsIgnoreCase(str)) {
            return HeaderUtils.extractHeader((Map) map, ResponseHeader.CUSTOM_EVENT_NAME);
        }
        if (AdType.STATIC_NATIVE.equalsIgnoreCase(str)) {
            return CustomEventType.MOPUB_NATIVE.toString();
        }
        if (AdType.VIDEO_NATIVE.equalsIgnoreCase(str)) {
            return CustomEventType.MOPUB_VIDEO_NATIVE.toString();
        }
        if (AdType.REWARDED_VIDEO.equalsIgnoreCase(str)) {
            return CustomEventType.MOPUB_REWARDED_VIDEO.toString();
        }
        if (AdType.HTML.equalsIgnoreCase(str) || AdType.MRAID.equalsIgnoreCase(str)) {
            CustomEventType access$000;
            if (AdFormat.INTERSTITIAL.equals(adFormat)) {
                access$000 = CustomEventType.fromString(str + INTERSTITIAL_SUFFIX);
            } else {
                access$000 = CustomEventType.fromString(str + BANNER_SUFFIX);
            }
            return access$000.toString();
        } else if (AdType.INTERSTITIAL.equalsIgnoreCase(str)) {
            return CustomEventType.fromString(str2 + INTERSTITIAL_SUFFIX).toString();
        } else {
            return CustomEventType.fromString(str + BANNER_SUFFIX).toString();
        }
    }
}

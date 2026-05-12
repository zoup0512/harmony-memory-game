package com.mopub.nativeads;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.mopub.common.DataKeys;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Numbers;
import com.mopub.nativeads.CustomEventNative.CustomEventNativeListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

public class MoPubCustomEventNative extends CustomEventNative {

    static class MoPubStaticNativeAd extends StaticNativeAd {
        @VisibleForTesting
        static final String PRIVACY_INFORMATION_CLICKTHROUGH_URL = "https://www.mopub.com/optout";
        @NonNull
        private final Context mContext;
        @NonNull
        private final CustomEventNativeListener mCustomEventNativeListener;
        @NonNull
        private final ImpressionTracker mImpressionTracker;
        @NonNull
        private final JSONObject mJsonObject;
        @NonNull
        private final NativeClickHandler mNativeClickHandler;

        enum Parameter {
            IMPRESSION_TRACKER("imptracker", true),
            CLICK_TRACKER("clktracker", true),
            TITLE("title", false),
            TEXT("text", false),
            MAIN_IMAGE("mainimage", false),
            ICON_IMAGE("iconimage", false),
            CLICK_DESTINATION("clk", false),
            FALLBACK("fallback", false),
            CALL_TO_ACTION("ctatext", false),
            STAR_RATING("starrating", false);
            
            @NonNull
            @VisibleForTesting
            static final Set<String> requiredKeys = null;
            @NonNull
            final String name;
            final boolean required;

            static {
                requiredKeys = new HashSet();
                Parameter[] values = values();
                int length = values.length;
                int i;
                while (i < length) {
                    Parameter parameter = values[i];
                    if (parameter.required) {
                        requiredKeys.add(parameter.name);
                    }
                    i++;
                }
            }

            private Parameter(String str, boolean z) {
                this.name = str;
                this.required = z;
            }

            @Nullable
            static Parameter from(@NonNull String str) {
                for (Parameter parameter : values()) {
                    if (parameter.name.equals(str)) {
                        return parameter;
                    }
                }
                return null;
            }
        }

        MoPubStaticNativeAd(@NonNull Context context, @NonNull JSONObject jSONObject, @NonNull ImpressionTracker impressionTracker, @NonNull NativeClickHandler nativeClickHandler, @NonNull CustomEventNativeListener customEventNativeListener) {
            this.mJsonObject = jSONObject;
            this.mContext = context.getApplicationContext();
            this.mImpressionTracker = impressionTracker;
            this.mNativeClickHandler = nativeClickHandler;
            this.mCustomEventNativeListener = customEventNativeListener;
        }

        void loadAd() {
            if (containsRequiredKeys(this.mJsonObject)) {
                Iterator keys = this.mJsonObject.keys();
                while (keys.hasNext()) {
                    String str = (String) keys.next();
                    Parameter from = Parameter.from(str);
                    if (from != null) {
                        try {
                            addInstanceVariable(from, this.mJsonObject.opt(str));
                        } catch (ClassCastException e) {
                            throw new IllegalArgumentException("JSONObject key (" + str + ") contained unexpected value.");
                        }
                    }
                    addExtra(str, this.mJsonObject.opt(str));
                }
                setPrivacyInformationIconClickThroughUrl(PRIVACY_INFORMATION_CLICKTHROUGH_URL);
                this.mCustomEventNativeListener.onNativeAdLoaded(this);
                return;
            }
            throw new IllegalArgumentException("JSONObject did not contain required keys.");
        }

        private boolean containsRequiredKeys(@NonNull JSONObject jSONObject) {
            Set hashSet = new HashSet();
            Iterator keys = jSONObject.keys();
            while (keys.hasNext()) {
                hashSet.add(keys.next());
            }
            return hashSet.containsAll(Parameter.requiredKeys);
        }

        private void addInstanceVariable(@NonNull Parameter parameter, @Nullable Object obj) {
            try {
                switch (parameter) {
                    case MAIN_IMAGE:
                        setMainImageUrl((String) obj);
                        return;
                    case ICON_IMAGE:
                        setIconImageUrl((String) obj);
                        return;
                    case IMPRESSION_TRACKER:
                        addImpressionTrackers(obj);
                        return;
                    case CLICK_DESTINATION:
                        setClickDestinationUrl((String) obj);
                        return;
                    case CLICK_TRACKER:
                        parseClickTrackers(obj);
                        return;
                    case CALL_TO_ACTION:
                        setCallToAction((String) obj);
                        return;
                    case TITLE:
                        setTitle((String) obj);
                        return;
                    case TEXT:
                        setText((String) obj);
                        return;
                    case STAR_RATING:
                        setStarRating(Numbers.parseDouble(obj));
                        return;
                    default:
                        MoPubLog.d("Unable to add JSON key to internal mapping: " + parameter.name);
                        return;
                }
            } catch (ClassCastException e) {
                if (parameter.required) {
                    throw e;
                }
                MoPubLog.d("Ignoring class cast exception for optional key: " + parameter.name);
                return;
            }
            if (parameter.required) {
                MoPubLog.d("Ignoring class cast exception for optional key: " + parameter.name);
                return;
            }
            throw e;
        }

        private void parseClickTrackers(@NonNull Object obj) {
            if (obj instanceof JSONArray) {
                addClickTrackers(obj);
            } else {
                addClickTracker((String) obj);
            }
        }

        private boolean isImageKey(@Nullable String str) {
            return str != null && str.toLowerCase(Locale.US).endsWith("image");
        }

        @NonNull
        List<String> getExtrasImageUrls() {
            List<String> arrayList = new ArrayList(getExtras().size());
            for (Entry entry : getExtras().entrySet()) {
                if (isImageKey((String) entry.getKey()) && (entry.getValue() instanceof String)) {
                    arrayList.add((String) entry.getValue());
                }
            }
            return arrayList;
        }

        @NonNull
        List<String> getAllImageUrls() {
            List<String> arrayList = new ArrayList();
            if (getMainImageUrl() != null) {
                arrayList.add(getMainImageUrl());
            }
            if (getIconImageUrl() != null) {
                arrayList.add(getIconImageUrl());
            }
            arrayList.addAll(getExtrasImageUrls());
            return arrayList;
        }

        public void prepare(@NonNull View view) {
            this.mImpressionTracker.addView(view, this);
            this.mNativeClickHandler.setOnClickListener(view, (ClickInterface) this);
        }

        public void clear(@NonNull View view) {
            this.mImpressionTracker.removeView(view);
            this.mNativeClickHandler.clearOnClickListener(view);
        }

        public void destroy() {
            this.mImpressionTracker.destroy();
        }

        public void recordImpression(@NonNull View view) {
            notifyAdImpressed();
        }

        public void handleClick(@Nullable View view) {
            notifyAdClicked();
            this.mNativeClickHandler.openClickDestinationUrl(getClickDestinationUrl(), view);
        }
    }

    protected void loadNativeAd(@NonNull Activity activity, @NonNull CustomEventNativeListener customEventNativeListener, @NonNull Map<String, Object> map, @NonNull Map<String, String> map2) {
        Object obj = map.get(DataKeys.JSON_BODY_KEY);
        if (obj instanceof JSONObject) {
            try {
                new MoPubStaticNativeAd(activity, (JSONObject) obj, new ImpressionTracker(activity), new NativeClickHandler(activity), customEventNativeListener).loadAd();
                return;
            } catch (IllegalArgumentException e) {
                customEventNativeListener.onNativeAdFailed(NativeErrorCode.UNSPECIFIED);
                return;
            }
        }
        customEventNativeListener.onNativeAdFailed(NativeErrorCode.INVALID_RESPONSE);
    }
}

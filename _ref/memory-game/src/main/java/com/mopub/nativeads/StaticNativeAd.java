package com.mopub.nativeads;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.mopub.common.Preconditions.NoThrow;
import com.mopub.common.logging.MoPubLog;
import com.yalantis.ucrop.util.FileUtils;
import java.util.HashMap;
import java.util.Map;

public abstract class StaticNativeAd extends BaseNativeAd implements ClickInterface, ImpressionInterface {
    private static final int IMPRESSION_MIN_PERCENTAGE_VIEWED = 50;
    static final double MAX_STAR_RATING = 5.0d;
    static final double MIN_STAR_RATING = 0.0d;
    @Nullable
    private String mCallToAction;
    @Nullable
    private String mClickDestinationUrl;
    @NonNull
    private final Map<String, Object> mExtras = new HashMap();
    @Nullable
    private String mIconImageUrl;
    private int mImpressionMinTimeViewed = 1000;
    private boolean mImpressionRecorded;
    @Nullable
    private String mMainImageUrl;
    @Nullable
    private String mPrivacyInformationIconClickThroughUrl;
    @Nullable
    private String mPrivacyInformationIconImageUrl;
    @Nullable
    private Double mStarRating;
    @Nullable
    private String mText;
    @Nullable
    private String mTitle;

    @Nullable
    public final String getTitle() {
        return this.mTitle;
    }

    @Nullable
    public final String getText() {
        return this.mText;
    }

    @Nullable
    public final String getMainImageUrl() {
        return this.mMainImageUrl;
    }

    @Nullable
    public final String getIconImageUrl() {
        return this.mIconImageUrl;
    }

    @Nullable
    public final String getCallToAction() {
        return this.mCallToAction;
    }

    @Nullable
    public final Double getStarRating() {
        return this.mStarRating;
    }

    @Nullable
    public final String getPrivacyInformationIconClickThroughUrl() {
        return this.mPrivacyInformationIconClickThroughUrl;
    }

    @Nullable
    public String getPrivacyInformationIconImageUrl() {
        return this.mPrivacyInformationIconImageUrl;
    }

    @Nullable
    public final Object getExtra(@NonNull String str) {
        if (NoThrow.checkNotNull(str, "getExtra key is not allowed to be null")) {
            return this.mExtras.get(str);
        }
        return null;
    }

    @NonNull
    public final Map<String, Object> getExtras() {
        return new HashMap(this.mExtras);
    }

    @Nullable
    public final String getClickDestinationUrl() {
        return this.mClickDestinationUrl;
    }

    public final void setMainImageUrl(@Nullable String str) {
        this.mMainImageUrl = str;
    }

    public final void setIconImageUrl(@Nullable String str) {
        this.mIconImageUrl = str;
    }

    public final void setClickDestinationUrl(@Nullable String str) {
        this.mClickDestinationUrl = str;
    }

    public final void setCallToAction(@Nullable String str) {
        this.mCallToAction = str;
    }

    public final void setTitle(@Nullable String str) {
        this.mTitle = str;
    }

    public final void setText(@Nullable String str) {
        this.mText = str;
    }

    public final void setStarRating(@Nullable Double d) {
        if (d == null) {
            this.mStarRating = null;
        } else if (d.doubleValue() < 0.0d || d.doubleValue() > MAX_STAR_RATING) {
            MoPubLog.d("Ignoring attempt to set invalid star rating (" + d + "). Must be between " + 0.0d + " and " + MAX_STAR_RATING + FileUtils.HIDDEN_PREFIX);
        } else {
            this.mStarRating = d;
        }
    }

    public final void setPrivacyInformationIconClickThroughUrl(@Nullable String str) {
        this.mPrivacyInformationIconClickThroughUrl = str;
    }

    public final void setPrivacyInformationIconImageUrl(@Nullable String str) {
        this.mPrivacyInformationIconImageUrl = str;
    }

    public final void addExtra(@NonNull String str, @Nullable Object obj) {
        if (NoThrow.checkNotNull(str, "addExtra key is not allowed to be null")) {
            this.mExtras.put(str, obj);
        }
    }

    public final void setImpressionMinTimeViewed(int i) {
        if (i >= 0) {
            this.mImpressionMinTimeViewed = i;
        }
    }

    public void prepare(@NonNull View view) {
    }

    public void clear(@NonNull View view) {
    }

    public void destroy() {
    }

    public void recordImpression(@NonNull View view) {
    }

    public final int getImpressionMinPercentageViewed() {
        return 50;
    }

    public final int getImpressionMinTimeViewed() {
        return this.mImpressionMinTimeViewed;
    }

    public final boolean isImpressionRecorded() {
        return this.mImpressionRecorded;
    }

    public final void setImpressionRecorded() {
        this.mImpressionRecorded = true;
    }

    public void handleClick(@NonNull View view) {
    }
}

package com.mopub.nativeads;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import java.lang.ref.WeakReference;

@Deprecated
public final class AdapterHelper {
    @NonNull
    private final WeakReference<Activity> mActivity;
    @NonNull
    private final Context mApplicationContext;
    private final int mInterval;
    private final int mStart;

    @Deprecated
    public AdapterHelper(@NonNull Context context, int i, int i2) {
        boolean z = true;
        Preconditions.checkNotNull(context, "Context cannot be null.");
        Preconditions.checkArgument(context instanceof Activity, "Context must be an Activity.");
        Preconditions.checkArgument(i >= 0, "start position must be non-negative");
        if (i2 < 2) {
            z = false;
        }
        Preconditions.checkArgument(z, "interval must be at least 2");
        this.mActivity = new WeakReference((Activity) context);
        this.mApplicationContext = context.getApplicationContext();
        this.mStart = i;
        this.mInterval = i2;
    }

    @Deprecated
    @NonNull
    public View getAdView(@Nullable View view, @Nullable ViewGroup viewGroup, @Nullable NativeAd nativeAd, @Nullable ViewBinder viewBinder) {
        Activity activity = (Activity) this.mActivity.get();
        if (activity != null) {
            return NativeAdViewHelper.getAdView(view, viewGroup, activity, nativeAd, viewBinder);
        }
        MoPubLog.w("Weak reference to Activity Context in AdapterHelper became null. Returning empty view.");
        return new View(this.mApplicationContext);
    }

    @Deprecated
    public int shiftedCount(int i) {
        return numberOfAdsThatCouldFitWithContent(i) + i;
    }

    @Deprecated
    public int shiftedPosition(int i) {
        return i - numberOfAdsSeenUpToPosition(i);
    }

    @Deprecated
    public boolean isAdPosition(int i) {
        if (i >= this.mStart && (i - this.mStart) % this.mInterval == 0) {
            return true;
        }
        return false;
    }

    private int numberOfAdsSeenUpToPosition(int i) {
        if (i <= this.mStart) {
            return 0;
        }
        return ((int) Math.floor(((double) (i - this.mStart)) / ((double) this.mInterval))) + 1;
    }

    private int numberOfAdsThatCouldFitWithContent(int i) {
        if (i <= this.mStart) {
            return 0;
        }
        int i2 = this.mInterval - 1;
        if ((i - this.mStart) % i2 == 0) {
            return (i - this.mStart) / i2;
        }
        return ((int) Math.floor(((double) (i - this.mStart)) / ((double) i2))) + 1;
    }

    @Deprecated
    @VisibleForTesting
    void clearActivityContext() {
        this.mActivity.clear();
    }
}

package com.mopub.mobileads;

import android.support.annotation.NonNull;
import com.mopub.common.Preconditions;
import java.io.Serializable;

public class VastTracker implements Serializable {
    private static final long serialVersionUID = 0;
    private boolean mCalled;
    private boolean mIsRepeatable;
    @NonNull
    protected final String mTrackingUrl;

    public VastTracker(@NonNull String str) {
        Preconditions.checkNotNull(str);
        this.mTrackingUrl = str;
    }

    public VastTracker(@NonNull String str, boolean z) {
        this(str);
        this.mIsRepeatable = z;
    }

    @NonNull
    public String getTrackingUrl() {
        return this.mTrackingUrl;
    }

    public void setTracked() {
        this.mCalled = true;
    }

    public boolean isTracked() {
        return this.mCalled;
    }

    public boolean isRepeatable() {
        return this.mIsRepeatable;
    }
}

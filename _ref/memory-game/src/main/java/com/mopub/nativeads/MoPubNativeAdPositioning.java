package com.mopub.nativeads;

import android.support.annotation.NonNull;
import com.mopub.common.Preconditions;
import com.mopub.common.Preconditions.NoThrow;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class MoPubNativeAdPositioning {

    public static class MoPubClientPositioning {
        public static final int NO_REPEAT = Integer.MAX_VALUE;
        @NonNull
        private final ArrayList<Integer> mFixedPositions = new ArrayList();
        private int mRepeatInterval = Integer.MAX_VALUE;

        @NonNull
        public MoPubClientPositioning addFixedPosition(int i) {
            if (NoThrow.checkArgument(i >= 0)) {
                int binarySearch = Collections.binarySearch(this.mFixedPositions, Integer.valueOf(i));
                if (binarySearch < 0) {
                    this.mFixedPositions.add(binarySearch ^ -1, Integer.valueOf(i));
                }
            }
            return this;
        }

        @NonNull
        List<Integer> getFixedPositions() {
            return this.mFixedPositions;
        }

        @NonNull
        public MoPubClientPositioning enableRepeatingPositions(int i) {
            boolean z = true;
            if (i <= 1) {
                z = false;
            }
            if (NoThrow.checkArgument(z, "Repeating interval must be greater than 1")) {
                this.mRepeatInterval = i;
            } else {
                this.mRepeatInterval = Integer.MAX_VALUE;
            }
            return this;
        }

        int getRepeatingInterval() {
            return this.mRepeatInterval;
        }
    }

    public static class MoPubServerPositioning {
    }

    @NonNull
    static MoPubClientPositioning clone(@NonNull MoPubClientPositioning moPubClientPositioning) {
        Preconditions.checkNotNull(moPubClientPositioning);
        MoPubClientPositioning moPubClientPositioning2 = new MoPubClientPositioning();
        moPubClientPositioning2.mFixedPositions.addAll(moPubClientPositioning.mFixedPositions);
        moPubClientPositioning2.mRepeatInterval = moPubClientPositioning.mRepeatInterval;
        return moPubClientPositioning2;
    }

    @NonNull
    public static MoPubClientPositioning clientPositioning() {
        return new MoPubClientPositioning();
    }

    @NonNull
    public static MoPubServerPositioning serverPositioning() {
        return new MoPubServerPositioning();
    }
}

package com.mopub.common.event;

import android.support.annotation.NonNull;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Random;

public class EventSampler {
    private static final int CAPACITY = 135;
    private static final float LOAD_FACTOR = 0.75f;
    @VisibleForTesting
    static final int MAX_SIZE = 100;
    @NonNull
    private Random mRandom;
    @NonNull
    private LinkedHashMap<String, Boolean> mSampleDecisionsCache;

    public EventSampler() {
        this(new Random());
    }

    @VisibleForTesting
    public EventSampler(@NonNull Random random) {
        this.mRandom = random;
        this.mSampleDecisionsCache = new LinkedHashMap<String, Boolean>(CAPACITY, LOAD_FACTOR, true) {
            protected boolean removeEldestEntry(Entry<String, Boolean> entry) {
                return size() > 100;
            }
        };
    }

    boolean sample(@NonNull BaseEvent baseEvent) {
        boolean z = true;
        Preconditions.checkNotNull(baseEvent);
        String requestId = baseEvent.getRequestId();
        if (requestId == null) {
            return this.mRandom.nextDouble() < baseEvent.getSamplingRate();
        } else {
            Boolean bool = (Boolean) this.mSampleDecisionsCache.get(requestId);
            if (bool != null) {
                return bool.booleanValue();
            }
            if (this.mRandom.nextDouble() >= baseEvent.getSamplingRate()) {
                z = false;
            }
            this.mSampleDecisionsCache.put(requestId, Boolean.valueOf(z));
            return z;
        }
    }

    @VisibleForTesting
    int getCacheSize() {
        return this.mSampleDecisionsCache.size();
    }
}

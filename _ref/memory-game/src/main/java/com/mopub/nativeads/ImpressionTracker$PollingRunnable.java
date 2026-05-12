package com.mopub.nativeads;

import android.support.annotation.NonNull;
import android.view.View;
import com.mopub.common.VisibleForTesting;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

@VisibleForTesting
class ImpressionTracker$PollingRunnable implements Runnable {
    @NonNull
    private final ArrayList<View> mRemovedViews = new ArrayList();
    final /* synthetic */ ImpressionTracker this$0;

    ImpressionTracker$PollingRunnable(ImpressionTracker impressionTracker) {
        this.this$0 = impressionTracker;
    }

    public void run() {
        for (Entry entry : ImpressionTracker.access$100(this.this$0).entrySet()) {
            View view = (View) entry.getKey();
            TimestampWrapper timestampWrapper = (TimestampWrapper) entry.getValue();
            if (ImpressionTracker.access$200(this.this$0).hasRequiredTimeElapsed(timestampWrapper.mCreatedTimestamp, ((ImpressionInterface) timestampWrapper.mInstance).getImpressionMinTimeViewed())) {
                ((ImpressionInterface) timestampWrapper.mInstance).recordImpression(view);
                ((ImpressionInterface) timestampWrapper.mInstance).setImpressionRecorded();
                this.mRemovedViews.add(view);
            }
        }
        Iterator it = this.mRemovedViews.iterator();
        while (it.hasNext()) {
            this.this$0.removeView((View) it.next());
        }
        this.mRemovedViews.clear();
        if (!ImpressionTracker.access$100(this.this$0).isEmpty()) {
            this.this$0.scheduleNextPoll();
        }
    }
}

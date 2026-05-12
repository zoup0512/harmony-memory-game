package com.mopub.nativeads;

import android.support.annotation.NonNull;
import android.view.View;
import java.util.List;

class ImpressionTracker$1 implements VisibilityTrackerListener {
    final /* synthetic */ ImpressionTracker this$0;

    ImpressionTracker$1(ImpressionTracker impressionTracker) {
        this.this$0 = impressionTracker;
    }

    public void onVisibilityChanged(@NonNull List<View> list, @NonNull List<View> list2) {
        for (View view : list) {
            ImpressionInterface impressionInterface = (ImpressionInterface) ImpressionTracker.access$000(this.this$0).get(view);
            if (impressionInterface == null) {
                this.this$0.removeView(view);
            } else {
                TimestampWrapper timestampWrapper = (TimestampWrapper) ImpressionTracker.access$100(this.this$0).get(view);
                if (timestampWrapper == null || !impressionInterface.equals(timestampWrapper.mInstance)) {
                    ImpressionTracker.access$100(this.this$0).put(view, new TimestampWrapper(impressionInterface));
                }
            }
        }
        for (View view2 : list2) {
            ImpressionTracker.access$100(this.this$0).remove(view2);
        }
        this.this$0.scheduleNextPoll();
    }
}

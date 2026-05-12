package com.mopub.nativeads;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

class VisibilityTracker {
    @VisibleForTesting
    static final int NUM_ACCESSES_BEFORE_TRIMMING = 50;
    private static final int VISIBILITY_THROTTLE_MILLIS = 100;
    private long mAccessCounter;
    private boolean mIsVisibilityScheduled;
    @Nullable
    @VisibleForTesting
    OnPreDrawListener mOnPreDrawListener;
    @NonNull
    @VisibleForTesting
    final WeakReference<View> mRootView;
    @NonNull
    private final Map<View, TrackingInfo> mTrackedViews;
    @NonNull
    private final ArrayList<View> mTrimmedViews;
    @NonNull
    private final VisibilityChecker mVisibilityChecker;
    @NonNull
    private final Handler mVisibilityHandler;
    @NonNull
    private final VisibilityRunnable mVisibilityRunnable;
    @Nullable
    private VisibilityTrackerListener mVisibilityTrackerListener;

    interface VisibilityTrackerListener {
        void onVisibilityChanged(List<View> list, List<View> list2);
    }

    static class TrackingInfo {
        long mAccessOrder;
        int mMaxInvisiblePercent;
        int mMinViewablePercent;
        View mRootView;

        TrackingInfo() {
        }
    }

    static class VisibilityChecker {
        private final Rect mClipRect = new Rect();

        VisibilityChecker() {
        }

        boolean hasRequiredTimeElapsed(long j, int i) {
            return SystemClock.uptimeMillis() - j >= ((long) i);
        }

        boolean isVisible(@Nullable View view, @Nullable View view2, int i) {
            if (view2 == null || view2.getVisibility() != 0 || view.getParent() == null || !view2.getGlobalVisibleRect(this.mClipRect)) {
                return false;
            }
            long height = ((long) this.mClipRect.height()) * ((long) this.mClipRect.width());
            long height2 = ((long) view2.getHeight()) * ((long) view2.getWidth());
            if (height2 <= 0 || height * 100 < height2 * ((long) i)) {
                return false;
            }
            return true;
        }
    }

    class VisibilityRunnable implements Runnable {
        @NonNull
        private final ArrayList<View> mInvisibleViews = new ArrayList();
        @NonNull
        private final ArrayList<View> mVisibleViews = new ArrayList();

        VisibilityRunnable() {
        }

        public void run() {
            VisibilityTracker.this.mIsVisibilityScheduled = false;
            for (Entry entry : VisibilityTracker.this.mTrackedViews.entrySet()) {
                View view = (View) entry.getKey();
                int i = ((TrackingInfo) entry.getValue()).mMinViewablePercent;
                int i2 = ((TrackingInfo) entry.getValue()).mMaxInvisiblePercent;
                View view2 = ((TrackingInfo) entry.getValue()).mRootView;
                if (VisibilityTracker.this.mVisibilityChecker.isVisible(view2, view, i)) {
                    this.mVisibleViews.add(view);
                } else if (!VisibilityTracker.this.mVisibilityChecker.isVisible(view2, view, i2)) {
                    this.mInvisibleViews.add(view);
                }
            }
            if (VisibilityTracker.this.mVisibilityTrackerListener != null) {
                VisibilityTracker.this.mVisibilityTrackerListener.onVisibilityChanged(this.mVisibleViews, this.mInvisibleViews);
            }
            this.mVisibleViews.clear();
            this.mInvisibleViews.clear();
        }
    }

    public VisibilityTracker(@NonNull Activity activity) {
        this(activity, new WeakHashMap(10), new VisibilityChecker(), new Handler());
    }

    @VisibleForTesting
    VisibilityTracker(@NonNull Activity activity, @NonNull Map<View, TrackingInfo> map, @NonNull VisibilityChecker visibilityChecker, @NonNull Handler handler) {
        this.mAccessCounter = 0;
        this.mTrackedViews = map;
        this.mVisibilityChecker = visibilityChecker;
        this.mVisibilityHandler = handler;
        this.mVisibilityRunnable = new VisibilityRunnable();
        this.mTrimmedViews = new ArrayList(50);
        View decorView = activity.getWindow().getDecorView();
        this.mRootView = new WeakReference(decorView);
        ViewTreeObserver viewTreeObserver = decorView.getViewTreeObserver();
        if (viewTreeObserver.isAlive()) {
            this.mOnPreDrawListener = new OnPreDrawListener() {
                public boolean onPreDraw() {
                    VisibilityTracker.this.scheduleVisibilityCheck();
                    return true;
                }
            };
            viewTreeObserver.addOnPreDrawListener(this.mOnPreDrawListener);
            return;
        }
        MoPubLog.w("Visibility Tracker was unable to track views because the root view tree observer was not alive");
    }

    void setVisibilityTrackerListener(@Nullable VisibilityTrackerListener visibilityTrackerListener) {
        this.mVisibilityTrackerListener = visibilityTrackerListener;
    }

    void addView(@NonNull View view, int i) {
        addView(view, view, i);
    }

    void addView(@NonNull View view, @NonNull View view2, int i) {
        addView(view, view2, i, i);
    }

    void addView(@NonNull View view, @NonNull View view2, int i, int i2) {
        TrackingInfo trackingInfo = (TrackingInfo) this.mTrackedViews.get(view2);
        if (trackingInfo == null) {
            trackingInfo = new TrackingInfo();
            this.mTrackedViews.put(view2, trackingInfo);
            scheduleVisibilityCheck();
        }
        int min = Math.min(i2, i);
        trackingInfo.mRootView = view;
        trackingInfo.mMinViewablePercent = i;
        trackingInfo.mMaxInvisiblePercent = min;
        trackingInfo.mAccessOrder = this.mAccessCounter;
        this.mAccessCounter++;
        if (this.mAccessCounter % 50 == 0) {
            trimTrackedViews(this.mAccessCounter - 50);
        }
    }

    private void trimTrackedViews(long j) {
        for (Entry entry : this.mTrackedViews.entrySet()) {
            if (((TrackingInfo) entry.getValue()).mAccessOrder < j) {
                this.mTrimmedViews.add(entry.getKey());
            }
        }
        Iterator it = this.mTrimmedViews.iterator();
        while (it.hasNext()) {
            removeView((View) it.next());
        }
        this.mTrimmedViews.clear();
    }

    void removeView(@NonNull View view) {
        this.mTrackedViews.remove(view);
    }

    void clear() {
        this.mTrackedViews.clear();
        this.mVisibilityHandler.removeMessages(0);
        this.mIsVisibilityScheduled = false;
    }

    void destroy() {
        clear();
        View view = (View) this.mRootView.get();
        if (!(view == null || this.mOnPreDrawListener == null)) {
            ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
            if (viewTreeObserver.isAlive()) {
                viewTreeObserver.removeOnPreDrawListener(this.mOnPreDrawListener);
            }
            this.mOnPreDrawListener = null;
        }
        this.mVisibilityTrackerListener = null;
    }

    void scheduleVisibilityCheck() {
        if (!this.mIsVisibilityScheduled) {
            this.mIsVisibilityScheduled = true;
            this.mVisibilityHandler.postDelayed(this.mVisibilityRunnable, 100);
        }
    }
}

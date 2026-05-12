package com.mopub.mraid;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewTreeObserver.OnPreDrawListener;
import com.mopub.common.VisibleForTesting;

@VisibleForTesting
class MraidController$ScreenMetricsWaiter {
    @NonNull
    private final Handler mHandler = new Handler();
    @Nullable
    private WaitRequest mLastWaitRequest;

    static class WaitRequest {
        @NonNull
        private final Handler mHandler;
        @Nullable
        private Runnable mSuccessRunnable;
        @NonNull
        private final View[] mViews;
        int mWaitCount;
        private final Runnable mWaitingRunnable;

        private WaitRequest(@NonNull Handler handler, @NonNull View[] viewArr) {
            this.mWaitingRunnable = new Runnable() {
                public void run() {
                    for (final View view : WaitRequest.this.mViews) {
                        if (view.getHeight() > 0 || view.getWidth() > 0) {
                            WaitRequest.this.countDown();
                        } else {
                            view.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
                                public boolean onPreDraw() {
                                    view.getViewTreeObserver().removeOnPreDrawListener(this);
                                    WaitRequest.this.countDown();
                                    return true;
                                }
                            });
                        }
                    }
                }
            };
            this.mHandler = handler;
            this.mViews = viewArr;
        }

        private void countDown() {
            this.mWaitCount--;
            if (this.mWaitCount == 0 && this.mSuccessRunnable != null) {
                this.mSuccessRunnable.run();
                this.mSuccessRunnable = null;
            }
        }

        void start(@NonNull Runnable runnable) {
            this.mSuccessRunnable = runnable;
            this.mWaitCount = this.mViews.length;
            this.mHandler.post(this.mWaitingRunnable);
        }

        void cancel() {
            this.mHandler.removeCallbacks(this.mWaitingRunnable);
            this.mSuccessRunnable = null;
        }
    }

    MraidController$ScreenMetricsWaiter() {
    }

    WaitRequest waitFor(@NonNull View... viewArr) {
        this.mLastWaitRequest = new WaitRequest(this.mHandler, viewArr);
        return this.mLastWaitRequest;
    }

    void cancelLastRequest() {
        if (this.mLastWaitRequest != null) {
            this.mLastWaitRequest.cancel();
            this.mLastWaitRequest = null;
        }
    }
}

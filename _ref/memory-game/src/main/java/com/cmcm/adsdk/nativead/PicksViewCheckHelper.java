package com.cmcm.adsdk.nativead;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Handler;
import android.view.View;
import com.cmcm.adsdk.Const;
import com.cmcm.baseapi.ads.INativeAd.ImpressionListener;
import com.cmcm.utils.Commons;
import com.cmcm.utils.ReceiverUtils;
import com.cmcm.utils.g;
import java.lang.ref.WeakReference;

public class PicksViewCheckHelper {
    private static final int SCHEDULE_CHECK_VIEW_TIME = 1000;
    private static final String TAG = "PicksViewCheckHelper";
    private static final float VIEW_ALPHA_VALUE = 0.9f;
    private static final float VIEW_AREA_VALUE = 0.1f;
    private static final float VIEW_AREA_YAHOO_VALUE = 0.5f;
    private Context mContext;
    public Handler mHandler;
    private boolean mImpressionRetryScheduled;
    private boolean mIsYahoo;
    private WeakReference<ImpressionListener> mListener;
    private boolean mShowed = false;
    private WeakReference<View> mView;
    private float mViewAreaValue = VIEW_AREA_VALUE;
    private Runnable sendImpressionRunnable = new Runnable() {
        public void run() {
            if (PicksViewCheckHelper.this.mImpressionRetryScheduled) {
                PicksViewCheckHelper.this.checkView();
                if (PicksViewCheckHelper.this.mHandler != null) {
                    PicksViewCheckHelper.this.mHandler.postDelayed(this, 1000);
                }
            }
        }
    };

    public PicksViewCheckHelper(Context context, View adView, ImpressionListener listener, boolean isYahoo) {
        float f = VIEW_AREA_VALUE;
        this.mContext = context.getApplicationContext();
        this.mView = new WeakReference(adView);
        this.mIsYahoo = isYahoo;
        this.mListener = new WeakReference(listener);
        if (isYahoo) {
            f = VIEW_AREA_YAHOO_VALUE;
        }
        this.mViewAreaValue = f;
        this.mImpressionRetryScheduled = true;
        this.mHandler = new Handler();
        ReceiverUtils.a.add(this);
    }

    public void startWork() {
        g.a(TAG, "start check view");
        if (!this.mIsYahoo) {
            g.a(TAG, "is no yahoo ad, check view");
            checkView();
        }
        this.mHandler.postDelayed(this.sendImpressionRunnable, 1000);
        if (!this.mShowed && !Commons.isScreenOn(this.mContext)) {
            g.a(TAG, "lock screen,cancel schedule check view");
            cancelImpressionRetry();
        }
    }

    public void stopWork(String reason) {
        g.a(TAG, "stop check view: " + reason);
        cancelImpressionRetry();
        this.mView = null;
    }

    private void checkView() {
        g.a(TAG, "to check view is on screen");
        ImpressionListener impressionListener = (ImpressionListener) this.mListener.get();
        View view = (View) this.mView.get();
        if (view == null || impressionListener == null) {
            stopWork("view.released");
        } else if (isViewOnScreen(view)) {
            impressionListener.onLoggingImpression();
            this.mShowed = true;
            stopWork("view.onscreen");
        }
    }

    private boolean isViewOnScreen(View view) {
        if (view == null || view.getVisibility() != 0 || view.getParent() == null || !isValidAlpha(view)) {
            return false;
        }
        Rect rect = new Rect();
        if (!view.getGlobalVisibleRect(rect)) {
            return false;
        }
        double height = (double) (rect.height() * rect.width());
        double width = (double) (view.getWidth() * view.getHeight());
        g.a(TAG, "is yahoo?" + this.mIsYahoo + " area value :" + this.mViewAreaValue);
        if (height >= width * ((double) this.mViewAreaValue)) {
            return true;
        }
        return false;
    }

    @SuppressLint({"NewApi"})
    private boolean isValidAlpha(View view) {
        if (VERSION.SDK_INT < 11 || view.getAlpha() > VIEW_ALPHA_VALUE) {
            return true;
        }
        return false;
    }

    public synchronized void scheduleImpressionRetry() {
        g.a(Const.TAG, "scheduleImpressionRetry");
        if (this.mImpressionRetryScheduled) {
            this.mHandler.postDelayed(this.sendImpressionRunnable, 1000);
        }
    }

    public synchronized void cancelImpressionRetry() {
        g.a(Const.TAG, "cancelImpressionRetry");
        if (this.mImpressionRetryScheduled) {
            this.mHandler.removeCallbacks(this.sendImpressionRunnable);
            this.mImpressionRetryScheduled = false;
        }
    }

    public void onScreenOn() {
        if (!this.mShowed) {
            scheduleImpressionRetry();
        }
    }

    public void onScreenOff() {
        cancelImpressionRetry();
    }
}

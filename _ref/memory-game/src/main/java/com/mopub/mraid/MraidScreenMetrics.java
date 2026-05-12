package com.mopub.mraid;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import com.mopub.common.util.Dips;

class MraidScreenMetrics {
    @NonNull
    private final Context mContext;
    @NonNull
    private final Rect mCurrentAdRect = new Rect();
    @NonNull
    private final Rect mCurrentAdRectDips = new Rect();
    @NonNull
    private final Rect mDefaultAdRect = new Rect();
    @NonNull
    private final Rect mDefaultAdRectDips = new Rect();
    private final float mDensity;
    @NonNull
    private final Rect mRootViewRect = new Rect();
    @NonNull
    private final Rect mRootViewRectDips = new Rect();
    @NonNull
    private final Rect mScreenRect = new Rect();
    @NonNull
    private final Rect mScreenRectDips = new Rect();

    MraidScreenMetrics(Context context, float f) {
        this.mContext = context.getApplicationContext();
        this.mDensity = f;
    }

    private void convertToDips(Rect rect, Rect rect2) {
        rect2.set(Dips.pixelsToIntDips((float) rect.left, this.mContext), Dips.pixelsToIntDips((float) rect.top, this.mContext), Dips.pixelsToIntDips((float) rect.right, this.mContext), Dips.pixelsToIntDips((float) rect.bottom, this.mContext));
    }

    public float getDensity() {
        return this.mDensity;
    }

    void setScreenSize(int i, int i2) {
        this.mScreenRect.set(0, 0, i, i2);
        convertToDips(this.mScreenRect, this.mScreenRectDips);
    }

    @NonNull
    Rect getScreenRect() {
        return this.mScreenRect;
    }

    @NonNull
    Rect getScreenRectDips() {
        return this.mScreenRectDips;
    }

    void setRootViewPosition(int i, int i2, int i3, int i4) {
        this.mRootViewRect.set(i, i2, i + i3, i2 + i4);
        convertToDips(this.mRootViewRect, this.mRootViewRectDips);
    }

    @NonNull
    Rect getRootViewRect() {
        return this.mRootViewRect;
    }

    @NonNull
    Rect getRootViewRectDips() {
        return this.mRootViewRectDips;
    }

    void setCurrentAdPosition(int i, int i2, int i3, int i4) {
        this.mCurrentAdRect.set(i, i2, i + i3, i2 + i4);
        convertToDips(this.mCurrentAdRect, this.mCurrentAdRectDips);
    }

    @NonNull
    Rect getCurrentAdRect() {
        return this.mCurrentAdRect;
    }

    @NonNull
    Rect getCurrentAdRectDips() {
        return this.mCurrentAdRectDips;
    }

    void setDefaultAdPosition(int i, int i2, int i3, int i4) {
        this.mDefaultAdRect.set(i, i2, i + i3, i2 + i4);
        convertToDips(this.mDefaultAdRect, this.mDefaultAdRectDips);
    }

    @NonNull
    Rect getDefaultAdRect() {
        return this.mDefaultAdRect;
    }

    @NonNull
    Rect getDefaultAdRectDips() {
        return this.mDefaultAdRectDips;
    }
}

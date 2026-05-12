package com.my.target.core.ui.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;

public class FramedCacheImageView extends FrameLayout {
    private final CacheImageView a;

    public FramedCacheImageView(Context context) {
        super(context);
        this.a = new CacheImageView(context);
        LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        addView(this.a, layoutParams);
    }

    public void setImageBitmap(Bitmap bitmap) {
        this.a.setImageBitmap(bitmap);
    }
}

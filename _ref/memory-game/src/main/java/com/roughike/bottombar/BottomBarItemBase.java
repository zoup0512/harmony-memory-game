package com.roughike.bottombar;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.AppCompatDrawableManager;

class BottomBarItemBase {
    protected int color;
    protected Drawable icon;
    protected int iconResource;
    protected String title;
    protected int titleResource;

    BottomBarItemBase() {
    }

    protected Drawable getIcon(Context context) {
        if (this.iconResource != 0) {
            return AppCompatDrawableManager.get().getDrawable(context, this.iconResource);
        }
        return this.icon;
    }

    protected String getTitle(Context context) {
        if (this.titleResource != 0) {
            return context.getString(this.titleResource);
        }
        return this.title;
    }
}

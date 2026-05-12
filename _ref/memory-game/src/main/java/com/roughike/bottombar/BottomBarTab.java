package com.roughike.bottombar;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

public class BottomBarTab extends BottomBarItemBase {
    protected int id = -1;

    public BottomBarTab(@DrawableRes int iconResource, @NonNull String title) {
        this.iconResource = iconResource;
        this.title = title;
    }

    public BottomBarTab(Drawable icon, @NonNull String title) {
        this.icon = icon;
        this.title = title;
    }

    public BottomBarTab(Drawable icon, @StringRes int titleResource) {
        this.icon = icon;
        this.titleResource = titleResource;
    }

    public BottomBarTab(@DrawableRes int iconResource, @StringRes int titleResource) {
        this.iconResource = iconResource;
        this.titleResource = titleResource;
    }
}

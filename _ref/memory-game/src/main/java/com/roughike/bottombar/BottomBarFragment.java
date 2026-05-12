package com.roughike.bottombar;

import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

@Deprecated
public class BottomBarFragment extends BottomBarItemBase {
    private Fragment fragment;
    private android.support.v4.app.Fragment supportFragment;

    public BottomBarFragment(Fragment fragment, @DrawableRes int iconResource, @NonNull String title) {
        this.fragment = fragment;
        this.iconResource = iconResource;
        this.title = title;
    }

    public BottomBarFragment(Fragment fragment, Drawable icon, @NonNull String title) {
        this.fragment = fragment;
        this.icon = icon;
        this.title = title;
    }

    public BottomBarFragment(Fragment fragment, Drawable icon, @StringRes int titleResource) {
        this.fragment = fragment;
        this.icon = icon;
        this.titleResource = titleResource;
    }

    public BottomBarFragment(Fragment fragment, @DrawableRes int iconResource, @StringRes int titleResource) {
        this.fragment = fragment;
        this.iconResource = iconResource;
        this.titleResource = titleResource;
    }

    public BottomBarFragment(android.support.v4.app.Fragment fragment, @DrawableRes int iconResource, @NonNull String title) {
        this.supportFragment = fragment;
        this.iconResource = iconResource;
        this.title = title;
    }

    public BottomBarFragment(android.support.v4.app.Fragment fragment, Drawable icon, @NonNull String title) {
        this.supportFragment = fragment;
        this.icon = icon;
        this.title = title;
    }

    public BottomBarFragment(android.support.v4.app.Fragment fragment, Drawable icon, @StringRes int titleResource) {
        this.supportFragment = fragment;
        this.icon = icon;
        this.titleResource = titleResource;
    }

    public BottomBarFragment(android.support.v4.app.Fragment fragment, @DrawableRes int iconResource, @StringRes int titleResource) {
        this.supportFragment = fragment;
        this.iconResource = iconResource;
        this.titleResource = titleResource;
    }

    protected Fragment getFragment() {
        return this.fragment;
    }

    protected android.support.v4.app.Fragment getSupportFragment() {
        return this.supportFragment;
    }
}

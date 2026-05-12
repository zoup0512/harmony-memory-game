package com.roughike.bottombar;

import android.support.annotation.IdRes;

public interface OnMenuTabClickListener {
    void onMenuTabReSelected(@IdRes int i);

    void onMenuTabSelected(@IdRes int i);
}

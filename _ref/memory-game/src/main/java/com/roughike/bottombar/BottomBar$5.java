package com.roughike.bottombar;

import android.os.Build.VERSION;
import android.support.design.widget.CoordinatorLayout.LayoutParams;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import com.roughike.bottombar.scrollsweetness.BottomNavigationBehavior;

class BottomBar$5 implements OnGlobalLayoutListener {
    final /* synthetic */ BottomBar val$bottomBar;
    final /* synthetic */ int val$navBarHeightCopy;
    final /* synthetic */ View val$outerContainer;

    BottomBar$5(BottomBar bottomBar, View view, int i) {
        this.val$bottomBar = bottomBar;
        this.val$outerContainer = view;
        this.val$navBarHeightCopy = i;
    }

    public void onGlobalLayout() {
        this.val$bottomBar.shyHeightAlreadyCalculated();
        int newHeight = this.val$outerContainer.getHeight() + this.val$navBarHeightCopy;
        this.val$outerContainer.getLayoutParams().height = newHeight;
        if (this.val$bottomBar.isShy()) {
            int defaultOffset = this.val$bottomBar.useExtraOffset() ? this.val$navBarHeightCopy : 0;
            this.val$bottomBar.setTranslationY((float) defaultOffset);
            ((LayoutParams) this.val$bottomBar.getLayoutParams()).setBehavior(new BottomNavigationBehavior(newHeight, defaultOffset, this.val$bottomBar.isShy(), BottomBar.access$200(this.val$bottomBar)));
        }
        ViewTreeObserver obs = this.val$outerContainer.getViewTreeObserver();
        if (VERSION.SDK_INT >= 16) {
            obs.removeOnGlobalLayoutListener(this);
        } else {
            obs.removeGlobalOnLayoutListener(this);
        }
    }
}

package com.roughike.bottombar;

import android.os.Build.VERSION;
import android.support.design.widget.CoordinatorLayout.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import com.roughike.bottombar.scrollsweetness.BottomNavigationBehavior;

class BottomBar$4 implements OnGlobalLayoutListener {
    final /* synthetic */ BottomBar this$0;

    BottomBar$4(BottomBar this$0) {
        this.this$0 = this$0;
    }

    public void onGlobalLayout() {
        if (!BottomBar.access$400(this.this$0)) {
            ((LayoutParams) this.this$0.getLayoutParams()).setBehavior(new BottomNavigationBehavior(this.this$0.getOuterContainer().getHeight(), 0, this.this$0.isShy(), BottomBar.access$200(this.this$0)));
        }
        ViewTreeObserver obs = this.this$0.getViewTreeObserver();
        if (VERSION.SDK_INT >= 16) {
            obs.removeOnGlobalLayoutListener(this);
        } else {
            obs.removeGlobalOnLayoutListener(this);
        }
    }
}

package com.roughike.bottombar;

import android.view.View;
import android.view.View.OnLongClickListener;

class BottomBar$2 implements OnLongClickListener {
    final /* synthetic */ BottomBar this$0;
    final /* synthetic */ View val$tab;

    BottomBar$2(BottomBar this$0, View view) {
        this.this$0 = this$0;
        this.val$tab = view;
    }

    public boolean onLongClick(View v) {
        return BottomBar.access$100(this.this$0, (View) this.val$tab.getParent());
    }
}

package com.roughike.bottombar;

import android.view.View;
import android.view.View.OnClickListener;

class BottomBar$1 implements OnClickListener {
    final /* synthetic */ BottomBar this$0;
    final /* synthetic */ View val$tab;

    BottomBar$1(BottomBar this$0, View view) {
        this.this$0 = this$0;
        this.val$tab = view;
    }

    public void onClick(View v) {
        BottomBar.access$000(this.this$0, (View) this.val$tab.getParent());
    }
}

package com.mopub.common;

import android.view.View;
import android.view.View.OnClickListener;

class MoPubBrowser$4 implements OnClickListener {
    final /* synthetic */ MoPubBrowser this$0;

    MoPubBrowser$4(MoPubBrowser moPubBrowser) {
        this.this$0 = moPubBrowser;
    }

    public void onClick(View view) {
        MoPubBrowser.access$000(this.this$0).reload();
    }
}

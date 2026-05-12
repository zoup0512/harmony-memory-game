package com.mopub.common;

import android.view.View;
import android.view.View.OnClickListener;

class MoPubBrowser$3 implements OnClickListener {
    final /* synthetic */ MoPubBrowser this$0;

    MoPubBrowser$3(MoPubBrowser moPubBrowser) {
        this.this$0 = moPubBrowser;
    }

    public void onClick(View view) {
        if (MoPubBrowser.access$000(this.this$0).canGoForward()) {
            MoPubBrowser.access$000(this.this$0).goForward();
        }
    }
}

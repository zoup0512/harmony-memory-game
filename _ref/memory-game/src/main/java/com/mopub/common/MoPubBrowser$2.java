package com.mopub.common;

import android.view.View;
import android.view.View.OnClickListener;

class MoPubBrowser$2 implements OnClickListener {
    final /* synthetic */ MoPubBrowser this$0;

    MoPubBrowser$2(MoPubBrowser moPubBrowser) {
        this.this$0 = moPubBrowser;
    }

    public void onClick(View view) {
        if (MoPubBrowser.access$000(this.this$0).canGoBack()) {
            MoPubBrowser.access$000(this.this$0).goBack();
        }
    }
}

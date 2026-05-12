package org.nexage.sourcekit.vast.activity;

import android.content.Context;
import android.webkit.JavascriptInterface;

public class VPAIDActivity$WebAppInterface {
    Context mContext;
    final /* synthetic */ VPAIDActivity this$0;

    VPAIDActivity$WebAppInterface(VPAIDActivity vPAIDActivity, Context context) {
        this.this$0 = vPAIDActivity;
        this.mContext = context;
    }

    @JavascriptInterface
    public void finish() {
        this.this$0.runOnUiThread(new 1(this));
    }

    @JavascriptInterface
    public void close() {
        this.this$0.runOnUiThread(new 2(this));
    }

    @JavascriptInterface
    public void started() {
        this.this$0.runOnUiThread(new 3(this));
    }

    @JavascriptInterface
    public void loaded() {
        this.this$0.runOnUiThread(new 4(this));
    }
}

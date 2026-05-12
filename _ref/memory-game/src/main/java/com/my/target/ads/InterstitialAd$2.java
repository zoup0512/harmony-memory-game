package com.my.target.ads;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import com.my.target.core.ui.a;

class InterstitialAd$2 implements OnDismissListener {
    final /* synthetic */ InterstitialAd this$0;

    InterstitialAd$2(InterstitialAd interstitialAd) {
        this.this$0 = interstitialAd;
    }

    public void onDismiss(DialogInterface dialogInterface) {
        a aVar = (a) dialogInterface;
        aVar.setOnDismissListener(null);
        if (aVar == InterstitialAd.access$100(this.this$0)) {
            InterstitialAd.access$102(this.this$0, null);
            if (InterstitialAd.access$000(this.this$0) != null) {
                InterstitialAd.access$000(this.this$0).onDismiss(this.this$0);
            }
        }
    }
}

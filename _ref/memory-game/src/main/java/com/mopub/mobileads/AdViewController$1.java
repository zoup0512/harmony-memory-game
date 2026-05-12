package com.mopub.mobileads;

import com.mopub.network.AdRequest.Listener;
import com.mopub.network.AdResponse;
import com.mopub.volley.VolleyError;

class AdViewController$1 implements Listener {
    final /* synthetic */ AdViewController this$0;

    AdViewController$1(AdViewController adViewController) {
        this.this$0 = adViewController;
    }

    public void onSuccess(AdResponse adResponse) {
        this.this$0.onAdLoadSuccess(adResponse);
    }

    public void onErrorResponse(VolleyError volleyError) {
        this.this$0.onAdLoadError(volleyError);
    }
}

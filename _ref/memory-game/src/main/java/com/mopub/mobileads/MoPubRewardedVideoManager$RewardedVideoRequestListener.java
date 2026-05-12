package com.mopub.mobileads;

import com.mopub.network.AdRequest.Listener;
import com.mopub.network.AdResponse;
import com.mopub.volley.VolleyError;

public class MoPubRewardedVideoManager$RewardedVideoRequestListener implements Listener {
    public final String adUnitId;
    private final MoPubRewardedVideoManager mVideoManager;

    public MoPubRewardedVideoManager$RewardedVideoRequestListener(MoPubRewardedVideoManager moPubRewardedVideoManager, String str) {
        this.adUnitId = str;
        this.mVideoManager = moPubRewardedVideoManager;
    }

    public void onSuccess(AdResponse adResponse) {
        MoPubRewardedVideoManager.access$000(this.mVideoManager, adResponse, this.adUnitId);
    }

    public void onErrorResponse(VolleyError volleyError) {
        MoPubRewardedVideoManager.access$100(this.mVideoManager, volleyError, this.adUnitId);
    }
}

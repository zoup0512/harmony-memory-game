package com.cmcm.picks.mixad;

import java.util.List;

public interface IMixBoxListener {
    void onAdListLoaded(List<IAd> list);

    void onAdLoaded(IAd iAd);

    void onFailed(String str);
}

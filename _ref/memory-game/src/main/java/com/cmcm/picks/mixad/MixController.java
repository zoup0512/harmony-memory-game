package com.cmcm.picks.mixad;

import android.content.Context;

public class MixController {
    private Context mContext;
    private IMixBoxReddotListener mReddotListener;

    public MixController(Context context) {
        this.mContext = context;
    }

    public MixController setReddotListener(IMixBoxReddotListener listener) {
        this.mReddotListener = listener;
        return this;
    }

    IMixBoxReddotListener getReddotListener() {
        return this.mReddotListener;
    }
}

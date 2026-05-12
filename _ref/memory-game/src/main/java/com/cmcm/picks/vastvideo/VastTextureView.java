package com.cmcm.picks.vastvideo;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.TextureView;

@TargetApi(14)
public class VastTextureView extends TextureView {
    public VastTextureView(Context context) {
        super(context);
    }

    public VastTextureView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public VastTextureView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}

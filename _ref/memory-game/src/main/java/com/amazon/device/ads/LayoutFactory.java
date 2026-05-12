package com.amazon.device.ads;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

class LayoutFactory {

    enum LayoutType {
        RELATIVE_LAYOUT,
        LINEAR_LAYOUT,
        FRAME_LAYOUT
    }

    LayoutFactory() {
    }

    public ViewGroup createLayout(Context context, LayoutType layoutType, String str) {
        ViewGroup relativeLayout;
        switch (layoutType) {
            case RELATIVE_LAYOUT:
                relativeLayout = new RelativeLayout(context);
                break;
            case FRAME_LAYOUT:
                relativeLayout = new FrameLayout(context);
                break;
            default:
                relativeLayout = new LinearLayout(context);
                break;
        }
        relativeLayout.setContentDescription(str);
        return relativeLayout;
    }
}

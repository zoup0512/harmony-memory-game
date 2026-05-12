package com.amazon.device.ads;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageButton;

class ImageButtonFactory implements ImageViewFactory {
    ImageButtonFactory() {
    }

    public BitmapDrawable createBitmapDrawable(Resources resources, String str) {
        return new BitmapDrawable(resources, str);
    }

    public ImageButton createImageView(Context context, String str) {
        ImageButton imageButton = new ImageButton(context);
        imageButton.setContentDescription(str);
        return imageButton;
    }
}

package com.roughike.bottombar;

import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;

public class BadgeCircle {
    protected static ShapeDrawable make(int size, int color) {
        ShapeDrawable indicator = new ShapeDrawable(new OvalShape());
        indicator.setIntrinsicWidth(size);
        indicator.setIntrinsicHeight(size);
        indicator.getPaint().setColor(color);
        return indicator;
    }
}

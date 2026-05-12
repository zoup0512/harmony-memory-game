package com.my.target.core.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build.VERSION;
import android.support.v4.view.ViewCompat;
import android.util.StateSet;
import android.util.TypedValue;
import android.view.View;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: UIutils */
public final class l {
    private static final AtomicInteger b = new AtomicInteger(1);
    private final Context a;

    public l(Context context) {
        this.a = context;
    }

    public final int a(int i) {
        return (int) TypedValue.applyDimension(1, (float) i, this.a.getResources().getDisplayMetrics());
    }

    public final int a() {
        return (int) TypedValue.applyDimension(2, 16.0f, this.a.getResources().getDisplayMetrics());
    }

    public static boolean b(int i) {
        return VERSION.SDK_INT >= i;
    }

    private static int d(int i) {
        float[] fArr = new float[3];
        Color.colorToHSV(i, fArr);
        fArr[2] = fArr[2] * 0.7f;
        return Color.HSVToColor(fArr);
    }

    public static int c(int i) {
        float[] fArr = new float[3];
        Color.colorToHSV(i, fArr);
        return Color.HSVToColor(128, fArr);
    }

    public static void a(View view, int i, int i2) {
        Drawable colorDrawable = new ColorDrawable(i);
        Drawable colorDrawable2 = new ColorDrawable(i2);
        Drawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842919}, colorDrawable2);
        stateListDrawable.addState(StateSet.WILD_CARD, colorDrawable);
        if (b(21)) {
            r1 = new int[2][];
            r1[0] = new int[]{16842919};
            r1[1] = StateSet.WILD_CARD;
            view.setBackground(new RippleDrawable(new ColorStateList(r1, new int[]{d(i2), d(i)}), stateListDrawable, null));
        } else if (b(18)) {
            view.setBackground(stateListDrawable);
        } else {
            view.setBackgroundDrawable(stateListDrawable);
        }
    }

    public static void a(View view, int i, int i2, int i3) {
        a(view, i, i2, 0, 0, i3);
    }

    public static void a(View view, int i, int i2, int i3, int i4, int i5) {
        Drawable gradientDrawable = new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{i, i});
        gradientDrawable.setCornerRadius((float) i5);
        Drawable gradientDrawable2 = new GradientDrawable(Orientation.TOP_BOTTOM, new int[]{i2, i2});
        gradientDrawable2.setCornerRadius((float) i5);
        if (i3 != 0) {
            gradientDrawable.setStroke(i4, i3);
            gradientDrawable2.setStroke(i4, i3);
        }
        Drawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{16842919}, gradientDrawable2);
        stateListDrawable.addState(StateSet.WILD_CARD, gradientDrawable);
        if (b(21)) {
            r1 = new int[2][];
            r1[0] = new int[]{16842919};
            r1[1] = StateSet.WILD_CARD;
            view.setBackground(new RippleDrawable(new ColorStateList(r1, new int[]{d(i2), d(i)}), stateListDrawable, null));
        } else if (b(16)) {
            view.setBackground(stateListDrawable);
        } else {
            view.setBackgroundDrawable(stateListDrawable);
        }
    }

    public static int b() {
        if (VERSION.SDK_INT >= 17) {
            return View.generateViewId();
        }
        int i;
        int i2;
        do {
            i = b.get();
            i2 = i + 1;
            if (i2 > ViewCompat.MEASURED_SIZE_MASK) {
                i2 = 1;
            }
        } while (!b.compareAndSet(i, i2));
        return i;
    }

    @SuppressLint({"DefaultLocale"})
    public static String a(float f) {
        return String.format("%d:%02d", new Object[]{Long.valueOf(TimeUnit.MILLISECONDS.toMinutes((long) (f * 1000.0f))), Long.valueOf(TimeUnit.MILLISECONDS.toSeconds((long) (f * 1000.0f)) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long) (1000.0f * f))))});
    }
}

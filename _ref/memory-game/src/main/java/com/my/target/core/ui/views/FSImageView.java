package com.my.target.core.ui.views;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.util.TypedValue;
import android.view.View.MeasureSpec;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.mopub.volley.DefaultRetryPolicy;
import com.my.target.core.resources.a;
import com.my.target.core.ui.views.controls.IconButton;

public class FSImageView extends RelativeLayout {
    private Bitmap a;
    private Bitmap b;
    private IconButton c;
    private LayoutParams d;
    private ImageView e;
    private LayoutParams f;
    private BorderedTextView g;
    private LayoutParams h;
    private final int i = 256;

    public final IconButton a() {
        return this.c;
    }

    public final ImageView b() {
        return this.e;
    }

    public FSImageView(Context context) {
        super(context);
        setBackgroundColor(0);
        this.e = new ImageView(getContext());
        this.e.setId(256);
        this.f = new LayoutParams(-2, -2);
        this.f.addRule(13);
        this.e.setLayoutParams(this.f);
        addView(this.e);
        this.c = new IconButton(context);
        this.c.setBitmap(a.a((int) TypedValue.applyDimension(1, 28.0f, context.getResources().getDisplayMetrics())), Boolean.valueOf(false));
        this.d = new LayoutParams(-2, -2);
        this.d.addRule(7, 256);
        this.d.addRule(6, 256);
        this.c.setLayoutParams(this.d);
        addView(this.c);
    }

    public void setImages(Bitmap bitmap, Bitmap bitmap2, Bitmap bitmap3) {
        this.b = bitmap;
        this.a = bitmap2;
        if (bitmap3 != null) {
            this.c.setBitmap(bitmap3, Boolean.valueOf(true));
            LayoutParams layoutParams = this.d;
            int i = -this.c.getMeasuredWidth();
            this.d.leftMargin = i;
            layoutParams.bottomMargin = i;
        }
        requestLayout();
    }

    public void setAgeRestrictions(String str) {
        if (this.g == null) {
            this.g = new BorderedTextView(getContext());
            this.g.setBorder(1, -7829368);
            this.g.setPadding(a(2), 0, 0, 0);
            this.h = new LayoutParams(-2, -2);
            LayoutParams layoutParams = this.h;
            LayoutParams layoutParams2 = this.h;
            int a = a(10);
            layoutParams2.topMargin = a;
            layoutParams.leftMargin = a;
            this.h.addRule(5, 256);
            this.h.addRule(6, 256);
            this.g.setLayoutParams(this.h);
            this.g.setTextColor(-1118482);
            this.g.setBorder(1, -1118482, a(3));
            this.g.setBackgroundColor(1711276032);
            addView(this.g);
        }
        this.g.setText(str);
    }

    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        requestLayout();
    }

    private int a(int i) {
        return (int) TypedValue.applyDimension(1, (float) i, getContext().getResources().getDisplayMetrics());
    }

    protected void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        if (!(this.b == null && this.a == null)) {
            Bitmap bitmap = ((float) size) / ((float) size2) > DefaultRetryPolicy.DEFAULT_BACKOFF_MULT ? this.b : this.a;
            if (bitmap == null) {
                bitmap = this.b != null ? this.b : this.a;
            }
            int width = bitmap.getWidth();
            int height = bitmap.getHeight();
            if (width > size || height > size2) {
                float max = Math.max(((float) bitmap.getWidth()) / ((float) size), ((float) bitmap.getHeight()) / ((float) size2));
                width = (int) (((float) width) / max);
                height = (int) (((float) height) / max);
            }
            this.e.setImageBitmap(bitmap);
            this.f.width = width;
            this.f.height = height;
        }
        super.onMeasure(i, i2);
    }
}

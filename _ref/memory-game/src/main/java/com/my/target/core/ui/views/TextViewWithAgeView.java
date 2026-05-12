package com.my.target.core.ui.views;

import android.content.Context;
import android.text.TextUtils.TruncateAt;
import android.view.View.MeasureSpec;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.mopub.mobileads.resource.DrawableConstants.RadialCountdown;
import com.my.target.core.utils.l;

public class TextViewWithAgeView extends RelativeLayout {
    private static final int a = l.b();
    private final l b;
    private final LayoutParams c = new LayoutParams(-2, -2);
    private final LayoutParams d = new LayoutParams(-2, -2);
    private final TextView e;
    private final BorderedTextView f;

    public TextViewWithAgeView(Context context) {
        super(context);
        this.b = new l(context);
        this.e = new TextView(context);
        this.f = new BorderedTextView(context);
        this.e.setId(a);
        this.f.setSingleLine();
        this.e.setTextSize(2, RadialCountdown.TEXT_SIZE_SP);
        this.e.setSingleLine();
        this.e.setHorizontallyScrolling(true);
        this.e.setEllipsize(TruncateAt.END);
        this.e.setMaxLines(1);
        this.e.setTextColor(-1);
        this.d.setMargins(this.b.a(8), 0, this.b.a(8), 0);
        this.d.addRule(15, -1);
        if (l.b(18)) {
            this.d.addRule(17, a);
        } else {
            this.d.addRule(1, a);
        }
        this.f.setLayoutParams(this.d);
        this.e.setLayoutParams(this.c);
        addView(this.e);
        addView(this.f);
    }

    public final TextView a() {
        return this.e;
    }

    public final BorderedTextView b() {
        return this.f;
    }

    protected void onMeasure(int i, int i2) {
        int measuredWidth;
        int i3 = 0;
        if (getChildAt(0) != null) {
            measuredWidth = getChildAt(0).getMeasuredWidth();
        } else {
            measuredWidth = 0;
        }
        if (getChildAt(1) != null) {
            i3 = getChildAt(1).getMeasuredWidth();
        }
        if (!(measuredWidth == 0 || i3 == 0 || getChildCount() != 2)) {
            int size = MeasureSpec.getSize(i);
            if (measuredWidth + i3 > size) {
                this.c.width = (size - i3) - this.b.a(8);
                this.e.setLayoutParams(this.c);
            }
        }
        super.onMeasure(i, i2);
    }
}

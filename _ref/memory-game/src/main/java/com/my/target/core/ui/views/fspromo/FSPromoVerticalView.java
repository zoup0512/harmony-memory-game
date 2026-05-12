package com.my.target.core.ui.views.fspromo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.my.target.core.models.banners.e;
import com.my.target.core.ui.views.BorderedTextView;
import com.my.target.core.utils.l;

@SuppressLint({"ViewConstructor"})
public class FSPromoVerticalView extends RelativeLayout {
    private static final int a = l.b();
    private static final int b = l.b();
    private static final int c = l.b();
    private static final int d = l.b();
    private final FSPromoBodyView e;
    private final Button f;
    private final BorderedTextView g;
    private final FSPromoFooterView h;
    private final l i;
    private final boolean j;
    private boolean k;

    public FSPromoVerticalView(Context context, l lVar, boolean z) {
        super(context);
        this.i = lVar;
        this.j = z;
        this.h = new FSPromoFooterView(context, lVar, z);
        this.e = new FSPromoBodyView(context, lVar, z);
        this.f = new Button(context);
        this.g = new BorderedTextView(context);
    }

    public final void a(int i, int i2) {
        boolean z;
        int a;
        if (i + i2 < 1280) {
            z = true;
        } else {
            z = false;
        }
        int max = Math.max(i2, i) / 8;
        this.e.a(z);
        this.h.a();
        View view = new View(getContext());
        view.setBackgroundColor(-5592406);
        view.setLayoutParams(new LayoutParams(-1, 1));
        this.h.setId(b);
        this.h.a(max, z);
        this.f.setId(c);
        this.f.setPadding(this.i.a(15), 0, this.i.a(15), 0);
        this.f.setMinimumWidth(this.i.a(100));
        this.f.setTransformationMethod(null);
        this.f.setSingleLine();
        this.f.setEllipsize(TruncateAt.END);
        this.g.setId(a);
        this.g.setBorder(1, -7829368);
        this.g.setPadding(this.i.a(2), 0, 0, 0);
        this.g.setTextColor(-1118482);
        this.g.setBorder(1, -1118482, this.i.a(3));
        this.g.setBackgroundColor(1711276032);
        this.e.setId(d);
        this.e.setOrientation(1);
        this.e.setGravity(14);
        if (z) {
            this.e.setPadding(this.i.a(4), this.i.a(4), this.i.a(4), this.i.a(4));
        } else {
            this.e.setPadding(this.i.a(16), this.i.a(16), this.i.a(16), this.i.a(16));
        }
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -1);
        layoutParams.addRule(2, b);
        this.e.setLayoutParams(layoutParams);
        layoutParams = new LayoutParams(-2, -2);
        layoutParams.setMargins(this.i.a(16), this.i.a(16), this.i.a(16), this.i.a(4));
        if (l.b(18)) {
            layoutParams.addRule(21, -1);
        } else {
            layoutParams.addRule(11, -1);
        }
        this.g.setLayoutParams(layoutParams);
        if (this.j) {
            a = this.i.a(64);
        } else {
            a = this.i.a(52);
        }
        ViewGroup.LayoutParams layoutParams2 = new LayoutParams(-2, a);
        layoutParams2.addRule(14, -1);
        layoutParams2.addRule(8, d);
        if (z) {
            layoutParams2.bottomMargin = (-this.i.a(52)) - this.i.a(4);
        } else {
            layoutParams2.bottomMargin = (-this.i.a(52)) / 2;
        }
        this.f.setLayoutParams(layoutParams2);
        ViewGroup.LayoutParams layoutParams3 = new LayoutParams(-1, max);
        layoutParams3.addRule(12, -1);
        this.h.setLayoutParams(layoutParams3);
        addView(this.e);
        addView(view);
        addView(this.g);
        addView(this.h);
        addView(this.f);
        setClickable(true);
        if (this.j) {
            this.f.setTextSize(2, 32.0f);
        } else {
            this.f.setTextSize(2, 22.0f);
        }
    }

    public void setBanner(e eVar) {
        this.e.setBanner(eVar);
        this.h.setBanner(eVar);
        this.k = eVar.t();
        this.f.setText(eVar.getCtaText());
        this.h.setBackgroundColor(eVar.n());
        if (TextUtils.isEmpty(eVar.getAgeRestrictions())) {
            this.g.setVisibility(8);
        } else {
            this.g.setText(eVar.getAgeRestrictions());
        }
        int p = eVar.p();
        int q = eVar.q();
        int r = eVar.r();
        l.a(this.f, p, q, this.i.a(2));
        this.f.setTextColor(r);
    }

    public void setOnCTAClickListener(OnClickListener onClickListener) {
        if (this.k) {
            setOnClickListener(onClickListener);
            l.a(this, -1, -3806472);
            setClickable(true);
        } else {
            setBackgroundColor(-1);
        }
        this.f.setOnClickListener(onClickListener);
    }
}

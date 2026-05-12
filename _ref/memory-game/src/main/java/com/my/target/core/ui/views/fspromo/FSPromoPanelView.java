package com.my.target.core.ui.views.fspromo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.my.target.core.models.banners.e;
import com.my.target.core.ui.views.CacheImageView;
import com.my.target.core.ui.views.TextViewWithAgeView;
import com.my.target.core.utils.l;
import com.my.target.nativeads.banners.NavigationType;
import com.my.target.nativeads.views.StarsRatingView;

@SuppressLint({"ViewConstructor"})
public class FSPromoPanelView extends RelativeLayout {
    private static final int a = l.b();
    private static final int b = l.b();
    private static final int c = l.b();
    private static final int d = l.b();
    private static final int e = l.b();
    private final RelativeLayout f;
    private final TextView g;
    private final TextViewWithAgeView h;
    private final TextView i;
    private final LinearLayout j;
    private final StarsRatingView k;
    private final TextView l;
    private final TextView m;
    private final Button n;
    private final CacheImageView o;
    private final l p;
    private final a q = new a(this.o, this.n, this.g, this.i, this.j, this, this.f, this.m);
    private LayoutParams r;
    private LayoutParams s;
    private boolean t;

    public FSPromoPanelView(Context context, l lVar) {
        super(context);
        this.p = lVar;
        this.n = new Button(context);
        this.o = new CacheImageView(context);
        this.h = new TextViewWithAgeView(context);
        this.g = new TextView(context);
        this.f = new RelativeLayout(context);
        this.i = new TextView(context);
        this.j = new LinearLayout(context);
        this.k = new StarsRatingView(context);
        this.l = new TextView(context);
        this.m = new TextView(context);
    }

    public final void a(int i, int i2) {
        int i3;
        if (i + i2 < 1280) {
            i3 = 1;
        } else {
            i3 = 0;
        }
        if (i3 != 0) {
            this.q.a();
        } else {
            this.q.a(this.p.a(4));
        }
        setBackgroundColor(1711276032);
        this.f.setPadding(this.p.a(16), 0, this.p.a(16), 0);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
        if (l.b(18)) {
            layoutParams.addRule(17, a);
            layoutParams.addRule(16, c);
        } else {
            layoutParams.addRule(1, a);
            layoutParams.addRule(0, c);
        }
        this.f.setLayoutParams(layoutParams);
        this.h.setId(e);
        layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.gravity = 16;
        this.h.setLayoutParams(layoutParams);
        this.g.setId(d);
        this.g.setTextColor(-2236963);
        this.g.setEllipsize(TruncateAt.END);
        layoutParams = new LayoutParams(-2, -2);
        layoutParams.addRule(3, e);
        this.g.setLayoutParams(layoutParams);
        this.m.setTextColor(-6710887);
        layoutParams = new LayoutParams(-2, -2);
        this.m.setVisibility(4);
        layoutParams.addRule(3, e);
        this.m.setLayoutParams(layoutParams);
        Drawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(0);
        gradientDrawable.setStroke(1, -3355444);
        ViewGroup.LayoutParams layoutParams2 = new LayoutParams(-2, -2);
        layoutParams2.addRule(3, d);
        this.i.setPadding(this.p.a(4), this.p.a(4), this.p.a(4), this.p.a(4));
        this.i.setBackgroundDrawable(gradientDrawable);
        this.i.setTextSize(2, 12.0f);
        this.i.setTextColor(-3355444);
        this.i.setVisibility(8);
        this.i.setLayoutParams(layoutParams2);
        this.s = new LayoutParams(-2, this.p.a(52));
        this.s.rightMargin = this.p.a(16);
        this.s.topMargin = this.p.a(4);
        if (l.b(18)) {
            this.s.addRule(21, -1);
        } else {
            this.s.addRule(11, -1);
        }
        this.n.setLayoutParams(this.s);
        this.n.setContentDescription("fspc");
        this.j.setOrientation(0);
        layoutParams = new LayoutParams(-2, -2);
        layoutParams.addRule(3, e);
        this.j.setLayoutParams(layoutParams);
        this.j.setVisibility(4);
        layoutParams = new LinearLayout.LayoutParams(this.p.a(73), this.p.a(12));
        layoutParams.topMargin = this.p.a(4);
        layoutParams.gravity = 48;
        this.k.setLayoutParams(layoutParams);
        layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 16;
        this.l.setTextColor(-6710887);
        this.l.setGravity(15);
        this.l.setTextSize(2, 14.0f);
        this.l.setLayoutParams(layoutParams);
        this.o.setId(a);
        this.o.setContentDescription("fspi");
        this.r = new LayoutParams(-2, -2);
        this.r.leftMargin = this.p.a(16);
        this.n.setId(c);
        this.n.setPadding(this.p.a(15), 0, this.p.a(15), 0);
        this.n.setMinimumWidth(this.p.a(100));
        this.n.setTransformationMethod(null);
        this.n.setTextSize(2, 22.0f);
        this.n.setMaxWidth(this.p.a(200));
        this.n.setSingleLine();
        this.n.setEllipsize(TruncateAt.END);
        this.h.b().setId(b);
        this.h.b().setBorder(1, -7829368);
        this.h.b().setPadding(this.p.a(2), 0, 0, 0);
        this.h.b().setTextColor(-1118482);
        this.h.b().setBorder(1, -1118482, this.p.a(3));
        this.h.b().setBackgroundColor(1711276032);
        this.j.addView(this.k);
        this.j.addView(this.l);
        this.j.setVisibility(8);
        this.m.setVisibility(8);
        this.f.addView(this.h);
        this.f.addView(this.j);
        this.f.addView(this.m);
        this.f.addView(this.g);
        this.f.addView(this.i);
        addView(this.f);
        addView(this.o);
        addView(this.n);
    }

    public void setBanner(e eVar) {
        this.t = eVar.t();
        this.k.setRating(eVar.getRating());
        this.l.setText(String.valueOf(eVar.getVotes()));
        this.h.a().setText(eVar.getTitle());
        this.g.setText(eVar.getDescription());
        CharSequence disclaimer = eVar.getDisclaimer();
        if (!TextUtils.isEmpty(disclaimer)) {
            this.i.setVisibility(0);
            this.i.setText(disclaimer);
        }
        this.o.setImageBitmap((Bitmap) eVar.getIcon().getData());
        this.n.setText(eVar.getCtaText());
        int width = eVar.getIcon().getWidth();
        int height = eVar.getIcon().getHeight();
        if (!(width == 0 || height == 0)) {
            float f = ((float) height) / ((float) width);
            this.r.width = this.p.a(64);
            this.r.height = (int) (f * ((float) this.p.a(64)));
        }
        this.r.topMargin = this.p.a(4);
        if (l.b(18)) {
            this.r.addRule(20);
        } else {
            this.r.addRule(9);
        }
        this.o.setLayoutParams(this.r);
        if (eVar.getAgeRestrictions() == null || eVar.getAgeRestrictions().equals("")) {
            this.h.b().setVisibility(8);
        } else {
            this.h.b().setText(eVar.getAgeRestrictions());
        }
        width = eVar.p();
        height = eVar.q();
        int r = eVar.r();
        l.a(this.n, width, height, this.p.a(2));
        this.n.setTextColor(r);
        if (!NavigationType.STORE.equals(eVar.getNavigationType())) {
            this.j.setVisibility(8);
            this.m.setText(eVar.getDomain());
            this.j.setVisibility(8);
        } else if (eVar.getVotes() == 0 || eVar.getRating() <= 0.0f) {
            this.j.setVisibility(8);
            this.q.b();
        } else {
            this.j.setVisibility(0);
        }
        if (eVar.k() == null || !eVar.k().q()) {
            this.j.setVisibility(8);
            this.m.setVisibility(8);
        }
        post(new Runnable(this) {
            final /* synthetic */ FSPromoPanelView a;

            {
                this.a = r1;
            }

            public final void run() {
                if (this.a.getResources().getConfiguration().orientation == 2 && this.a.h.a().getLayout() != null) {
                    int ellipsisStart = this.a.h.a().getLayout().getEllipsisStart(0);
                    int length = this.a.h.a().getText().length();
                    this.a.h.a().getTextSize();
                    if (ellipsisStart != 0 && ellipsisStart < length) {
                        float textSize = (((float) ellipsisStart) / ((float) length)) * this.a.h.a().getTextSize();
                        if (((float) this.a.p.a()) < textSize) {
                            this.a.h.a().setTextSize(0, textSize);
                            return;
                        }
                        this.a.s.rightMargin = this.a.p.a(2);
                        this.a.s.topMargin = this.a.p.a(4);
                        this.a.n.setLayoutParams(this.a.s);
                        this.a.r.leftMargin = this.a.p.a(2);
                        this.a.o.setLayoutParams(this.a.r);
                        this.a.f.setPadding(this.a.p.a(2), 0, this.a.p.a(2), 0);
                        this.a.h.a().setTextSize(2, 16.0f);
                        this.a.g.setTextSize(2, 14.0f);
                    }
                }
            }
        });
    }

    public final void a(View... viewArr) {
        if (getVisibility() == 0) {
            this.q.c(viewArr);
        }
    }

    final void b(View... viewArr) {
        if (getVisibility() == 0) {
            this.q.a(viewArr);
        }
    }

    final void c(View... viewArr) {
        this.q.b(viewArr);
    }

    public void setOnCTAClickListener(OnClickListener onClickListener) {
        this.n.setOnClickListener(onClickListener);
        if (this.t) {
            setOnClickListener(onClickListener);
        } else {
            setOnClickListener(null);
        }
    }
}

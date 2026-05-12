package com.my.target.core.ui.views.fspromo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.mopub.mobileads.resource.DrawableConstants.CloseButton;
import com.mopub.mobileads.resource.DrawableConstants.RadialCountdown;
import com.my.target.core.models.banners.e;
import com.my.target.core.utils.l;
import com.my.target.nativeads.banners.NavigationType;
import com.my.target.nativeads.views.StarsRatingView;

@SuppressLint({"ViewConstructor"})
public class FSPromoBodyView extends LinearLayout {
    protected final TextView a;
    protected final TextView b;
    protected final TextView c;
    protected final LinearLayout d;
    protected final TextView e;
    protected final StarsRatingView f;
    protected final TextView g;
    protected LayoutParams h;
    protected LayoutParams i;
    protected LayoutParams j;
    protected LayoutParams k;
    protected LayoutParams l;
    protected LayoutParams m;
    private final l n;
    private final boolean o;

    public FSPromoBodyView(Context context, l lVar, boolean z) {
        super(context);
        this.a = new TextView(context);
        this.b = new TextView(context);
        this.c = new TextView(context);
        this.d = new LinearLayout(context);
        this.e = new TextView(context);
        this.f = new StarsRatingView(context);
        this.g = new TextView(context);
        this.n = lVar;
        this.o = z;
    }

    final void a(boolean z) {
        this.a.setGravity(1);
        this.a.setTextColor(-16777216);
        this.h = new LayoutParams(-2, -2);
        this.h.gravity = 1;
        this.h.leftMargin = this.n.a(8);
        this.h.rightMargin = this.n.a(8);
        if (z) {
            this.h.topMargin = this.n.a(4);
        } else {
            this.h.topMargin = this.n.a(32);
        }
        this.a.setLayoutParams(this.h);
        this.i = new LayoutParams(-2, -2);
        this.i.gravity = 1;
        this.b.setLayoutParams(this.i);
        this.c.setGravity(1);
        this.c.setTextColor(-16777216);
        this.j = new LayoutParams(-2, -2);
        if (z) {
            this.j.topMargin = this.n.a(4);
        } else {
            this.j.topMargin = this.n.a(8);
        }
        this.j.gravity = 1;
        if (z) {
            this.j.leftMargin = this.n.a(4);
            this.j.rightMargin = this.n.a(4);
        } else {
            this.j.leftMargin = this.n.a(16);
            this.j.rightMargin = this.n.a(16);
        }
        this.c.setLayoutParams(this.j);
        this.d.setOrientation(0);
        this.l = new LayoutParams(-2, -2);
        this.l.gravity = 1;
        this.d.setLayoutParams(this.l);
        this.k = new LayoutParams(this.n.a(73), this.n.a(12));
        this.k.topMargin = this.n.a(4);
        this.f.setLayoutParams(this.k);
        this.g.setTextColor(-6710887);
        this.g.setTextSize(2, 14.0f);
        this.e.setTextColor(-6710887);
        this.e.setGravity(1);
        this.m = new LayoutParams(-2, -2);
        this.m.gravity = 1;
        if (z) {
            this.m.leftMargin = this.n.a(4);
            this.m.rightMargin = this.n.a(4);
        } else {
            this.m.leftMargin = this.n.a(16);
            this.m.rightMargin = this.n.a(16);
        }
        this.m.gravity = 1;
        this.e.setLayoutParams(this.m);
        addView(this.a);
        addView(this.b);
        addView(this.d);
        addView(this.c);
        addView(this.e);
        this.d.addView(this.f);
        this.d.addView(this.g);
    }

    public void setBanner(e eVar) {
        this.a.setText(eVar.getTitle());
        this.c.setText(eVar.getDescription());
        this.f.setRating(eVar.getRating());
        this.g.setText(String.valueOf(eVar.getVotes()));
        if (NavigationType.STORE.equals(eVar.getNavigationType())) {
            Object category = eVar.getCategory();
            Object subcategory = eVar.getSubcategory();
            CharSequence charSequence = "";
            if (!TextUtils.isEmpty(category)) {
                charSequence = charSequence + category;
            }
            if (!(TextUtils.isEmpty(charSequence) || TextUtils.isEmpty(subcategory))) {
                charSequence = charSequence + ", ";
            }
            if (!TextUtils.isEmpty(subcategory)) {
                charSequence = charSequence + subcategory;
            }
            if (TextUtils.isEmpty(charSequence)) {
                this.b.setVisibility(8);
            } else {
                this.b.setText(charSequence);
                this.b.setVisibility(0);
            }
            this.d.setVisibility(0);
            if (eVar.getVotes() == 0 || eVar.getRating() <= 0.0f) {
                this.d.setVisibility(8);
            } else {
                this.d.setVisibility(0);
            }
            this.b.setTextColor(-3355444);
        } else {
            this.d.setVisibility(8);
            this.b.setText(eVar.getDomain());
            this.d.setVisibility(8);
            this.b.setTextColor(-16733198);
        }
        if (TextUtils.isEmpty(eVar.getDisclaimer())) {
            this.e.setVisibility(8);
        } else {
            this.e.setVisibility(0);
            this.e.setText(eVar.getDisclaimer());
        }
        if (this.o) {
            this.a.setTextSize(2, 32.0f);
            this.c.setTextSize(2, 24.0f);
            this.e.setTextSize(2, RadialCountdown.TEXT_SIZE_SP);
            this.b.setTextSize(2, RadialCountdown.TEXT_SIZE_SP);
            return;
        }
        this.a.setTextSize(2, CloseButton.TEXT_SIZE_SP);
        this.c.setTextSize(2, 16.0f);
        this.e.setTextSize(2, 14.0f);
        this.b.setTextSize(2, 16.0f);
    }
}

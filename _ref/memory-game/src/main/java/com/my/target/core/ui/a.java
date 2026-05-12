package com.my.target.core.ui;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import com.my.target.core.engines.b;
import com.my.target.core.facades.g;
import com.my.target.core.factories.c;
import com.my.target.core.ui.views.AdTitle;
import com.my.target.nativeads.NativeAppwallAd;

/* compiled from: InterstitialAdDialog */
public final class a extends Dialog implements com.my.target.core.engines.b.a {
    private g a;
    private b b;

    public a(g gVar, boolean z, Context context) {
        super(context);
        this.a = gVar;
        requestWindowFeature(1);
        if (z) {
            getWindow().setFlags(1024, 1024);
        }
    }

    protected final void onCreate(Bundle bundle) {
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        View linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(1);
        if (this.a instanceof NativeAppwallAd) {
            NativeAppwallAd nativeAppwallAd = (NativeAppwallAd) this.a;
            View adTitle = new AdTitle(getContext());
            adTitle.setLabel(nativeAppwallAd.getTitle());
            adTitle.setCloseClickListener(this);
            adTitle.setLayoutParams(new LayoutParams(-1, (int) TypedValue.applyDimension(1, 52.0f, getContext().getResources().getDisplayMetrics())));
            adTitle.setStripeColor(nativeAppwallAd.getTitleSupplementaryColor());
            adTitle.setMainColor(nativeAppwallAd.getTitleBackgroundColor());
            adTitle.setTitleColor(nativeAppwallAd.getTitleTextColor());
            linearLayout.addView(adTitle);
        }
        this.b = c.a(this.a, linearLayout, getContext());
        setContentView(linearLayout);
        getWindow().setLayout(-1, -1);
        this.b.a((com.my.target.core.engines.b.a) this);
        this.b.d();
        this.b.b();
        super.onCreate(bundle);
    }

    public final void onClick(boolean z) {
        if (z) {
            dismiss();
        }
    }

    public final void onCloseClick() {
        dismiss();
    }

    public final void dismiss() {
        if (this.b != null) {
            this.b.a();
            this.b.c();
            this.b.f();
        }
        super.dismiss();
    }
}

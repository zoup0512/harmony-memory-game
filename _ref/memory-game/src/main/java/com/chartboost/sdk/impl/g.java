package com.chartboost.sdk.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Libraries.k;
import com.mopub.mobileads.resource.DrawableConstants.CloseButton;

@SuppressLint({"ViewConstructor"})
public class g extends j {
    private LinearLayout b;
    private ay c;
    private TextView d;

    public g(Context context, f fVar) {
        super(context, fVar);
    }

    protected View a() {
        Context context = getContext();
        int round = Math.round(getContext().getResources().getDisplayMetrics().density * CloseButton.STROKE_WIDTH);
        this.b = new LinearLayout(context);
        this.b.setOrientation(0);
        this.b.setGravity(17);
        int a = CBUtility.a(36, context);
        this.c = new ay(context);
        this.c.setPadding(round, round, round, round);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(a, a);
        this.c.setScaleType(ScaleType.FIT_CENTER);
        this.d = new TextView(context);
        this.d.setPadding(round / 2, round, round, round);
        this.d.setTextColor(-15264491);
        this.d.setTextSize(2, 16.0f);
        this.d.setTypeface(null, 1);
        this.d.setGravity(17);
        this.b.addView(this.c, layoutParams);
        this.b.addView(this.d, new LinearLayout.LayoutParams(-2, -1));
        return this.b;
    }

    public void a(k kVar) {
        this.c.a(kVar);
        this.c.setScaleType(ScaleType.FIT_CENTER);
    }

    public void a(String str) {
        this.d.setText(str);
    }

    protected int b() {
        return 48;
    }
}

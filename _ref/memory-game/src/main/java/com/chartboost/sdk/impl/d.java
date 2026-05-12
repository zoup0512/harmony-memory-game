package com.chartboost.sdk.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.GravityCompat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.mopub.volley.DefaultRetryPolicy;

@SuppressLint({"ViewConstructor"})
public class d extends j {
    private LinearLayout b;
    private LinearLayout c;
    private ay d;
    private az e;
    private TextView f;
    private TextView g;

    public d(Context context, f fVar) {
        super(context, fVar);
    }

    protected View a() {
        Context context = getContext();
        int round = Math.round(getContext().getResources().getDisplayMetrics().density * 6.0f);
        this.b = new LinearLayout(context);
        this.b.setOrientation(0);
        this.b.setGravity(17);
        this.c = new LinearLayout(context);
        this.c.setOrientation(1);
        this.c.setGravity(8388627);
        this.d = new ay(context);
        this.d.setPadding(round, round, round, round);
        if (this.a.H.e()) {
            this.d.a(this.a.H);
        }
        this.e = new az(this, context) {
            final /* synthetic */ d a;

            protected void a(MotionEvent motionEvent) {
                this.a.a.r().b(motionEvent.getX(), motionEvent.getY(), (float) super.getWidth(), (float) super.getHeight());
            }
        };
        this.e.setPadding(round, round, round, round);
        if (this.a.I.e()) {
            this.e.a(this.a.I);
        }
        this.f = new TextView(getContext());
        this.f.setTextColor(-15264491);
        this.f.setTypeface(null, 1);
        this.f.setGravity(GravityCompat.START);
        this.f.setPadding(round, round, round, round / 2);
        this.g = new TextView(getContext());
        this.g.setTextColor(-15264491);
        this.g.setTypeface(null, 1);
        this.g.setGravity(GravityCompat.START);
        this.g.setPadding(round, 0, round, round);
        this.f.setTextSize(2, 14.0f);
        this.g.setTextSize(2, 11.0f);
        this.c.addView(this.f);
        this.c.addView(this.g);
        this.b.addView(this.d);
        this.b.addView(this.c, new LayoutParams(0, -2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        this.b.addView(this.e);
        return this.b;
    }

    public void a(String str, String str2) {
        this.f.setText(str);
        this.g.setText(str2);
    }

    protected int b() {
        return 72;
    }
}

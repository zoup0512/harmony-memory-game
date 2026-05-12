package com.chartboost.sdk.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.MotionEvent;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Libraries.e.a;
import com.chartboost.sdk.Libraries.k;
import com.chartboost.sdk.f;
import com.chartboost.sdk.h;
import com.mopub.mobileads.VastIconXmlManager;
import com.mopub.mobileads.resource.DrawableConstants.RadialCountdown;
import com.mopub.volley.DefaultRetryPolicy;
import com.yalantis.ucrop.view.CropImageView;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;

@SuppressLint({"ViewConstructor"})
public class o extends m {
    private t a;
    private TextView b;
    private TextView c;
    private TextView d;
    private LinearLayout e;
    private r f;
    private az g;
    private int h;
    private Point i;
    private k j;
    private OnClickListener k;

    public o(t tVar, Context context) {
        super(context);
        this.a = tVar;
        this.e = new LinearLayout(context);
        this.e.setOrientation(1);
        setGravity(16);
        boolean a = h.a(context);
        this.b = new TextView(context);
        this.b.setTypeface(null, 1);
        this.b.setTextSize(2, a ? 21.0f : 16.0f);
        this.b.setTextColor(-16777216);
        this.b.setSingleLine();
        this.b.setEllipsize(TruncateAt.END);
        this.c = new TextView(context);
        this.c.setTextSize(2, a ? 16.0f : CropImageView.DEFAULT_MAX_SCALE_MULTIPLIER);
        this.c.setTextColor(-16777216);
        this.c.setSingleLine();
        this.c.setEllipsize(TruncateAt.END);
        this.d = new TextView(context);
        this.d.setTextSize(2, a ? RadialCountdown.TEXT_SIZE_SP : 11.0f);
        this.d.setTextColor(-16777216);
        this.d.setMaxLines(2);
        this.d.setEllipsize(TruncateAt.END);
        this.g = new az(this, context) {
            final /* synthetic */ o a;

            protected void a(MotionEvent motionEvent) {
                this.a.k.onClick(this.a.g);
            }
        };
        this.g.a(ScaleType.FIT_CENTER);
        this.f = new r(context);
        setFocusable(false);
        setGravity(16);
        addView(this.f);
        addView(this.e, new LayoutParams(0, -2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        addView(this.g);
        setBackgroundColor(0);
        this.e.addView(this.b, new LayoutParams(-1, -2));
        this.e.addView(this.c, new LayoutParams(-1, -2));
        this.e.addView(this.d, new LayoutParams(-1, -1));
    }

    public void setOnClickListener(OnClickListener clickListener) {
        super.setOnClickListener(clickListener);
        this.k = clickListener;
    }

    public void a(a aVar, int i) {
        this.b.setText(aVar.a("name").d("Unknown App"));
        if (TextUtils.isEmpty(aVar.e("publisher"))) {
            this.c.setVisibility(8);
        } else {
            this.c.setText(aVar.e("publisher"));
        }
        if (TextUtils.isEmpty(aVar.e("description"))) {
            this.d.setVisibility(8);
        } else {
            this.d.setText(aVar.e("description"));
        }
        this.h = aVar.b("border-color") ? -4802890 : h.a(aVar.e("border-color"));
        if (aVar.c(VastIconXmlManager.OFFSET)) {
            this.i = new Point(aVar.a(VastIconXmlManager.OFFSET).f("x"), aVar.a(VastIconXmlManager.OFFSET).f("y"));
        } else {
            this.i = new Point(0, 0);
        }
        this.j = null;
        if (aVar.c("deep-link") && af.a(aVar.e("deep-link"))) {
            if (this.a.m.e()) {
                this.j = this.a.m;
            } else {
                this.g.a("Play");
            }
        } else if (this.a.l.e()) {
            this.j = this.a.l;
        } else {
            this.g.a("Install");
        }
        int a = CBUtility.a(h.a(getContext()) ? 14 : 7, getContext());
        if (this.j != null) {
            this.g.a(this.j);
            a = (a * 2) + Math.round((((float) this.j.b()) * ((float) c())) / ((float) this.j.c()));
        } else {
            this.g.a().setTextColor(-14571545);
            a = CBUtility.a(8, getContext());
            this.g.a().setPadding(a, a, a, a);
            a = CBUtility.a(100, getContext());
        }
        this.g.setLayoutParams(new LayoutParams(a, c()));
        removeView(this.f);
        this.f = new r(getContext());
        addView(this.f, 0);
        a(this.f, i, aVar.a("assets").a(SettingsJsonConstants.APP_ICON_KEY));
        this.f.a(this.h);
        this.f.a(0.16666667f);
        b();
    }

    private void a(ay ayVar, int i, a aVar) {
        if (!aVar.b()) {
            Bundle bundle = new Bundle();
            bundle.putInt("index", i);
            String str = "";
            if (!(aVar.e("checksum") == null || aVar.e("checksum").isEmpty())) {
                str = aVar.e("checksum");
            }
            f.m().a(aVar.e("url"), str, null, ayVar, bundle);
        }
    }

    protected void b() {
        int a = CBUtility.a(h.a(getContext()) ? 14 : 7, getContext());
        ViewGroup.LayoutParams layoutParams = new LayoutParams(a() - (a * 2), a() - (a * 2));
        layoutParams.setMargins(a, a, a, a);
        this.e.setPadding(0, a, 0, a);
        this.g.setPadding((this.i.x * 2) + a, this.i.y * 2, a, 0);
        this.f.setLayoutParams(layoutParams);
        this.f.setScaleType(ScaleType.FIT_CENTER);
    }

    public int a() {
        int i = 134;
        if (CBUtility.a().b()) {
            if (!h.a(getContext())) {
                i = 75;
            }
        } else if (!h.a(getContext())) {
            i = 77;
        }
        return CBUtility.a(i, getContext());
    }

    private int c() {
        int i = 74;
        if (CBUtility.a().b()) {
            if (!h.a(getContext())) {
                i = 41;
            }
        } else if (!h.a(getContext())) {
            i = 41;
        }
        return CBUtility.a(i, getContext());
    }
}

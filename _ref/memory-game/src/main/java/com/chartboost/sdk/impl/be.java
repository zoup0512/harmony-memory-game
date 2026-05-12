package com.chartboost.sdk.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.chartboost.sdk.Model.a.b;
import com.chartboost.sdk.h.a;

@SuppressLint({"ViewConstructor"})
public class be extends RelativeLayout {
    private a a;
    private ax b;
    private ax c;
    private bd d;
    private com.chartboost.sdk.Model.a e = null;

    public be(Context context, com.chartboost.sdk.Model.a aVar) {
        super(context);
        this.e = aVar;
        if (aVar.a == b.NATIVE) {
            this.b = new ax(context);
            addView(this.b, new LayoutParams(-1, -1));
            this.c = new ax(context);
            addView(this.c, new LayoutParams(-1, -1));
            this.c.setVisibility(8);
        }
    }

    public void a() {
        if (this.a == null) {
            this.a = this.e.l();
            if (this.a != null) {
                addView(this.a, new LayoutParams(-1, -1));
                this.a.a();
            }
        }
        c();
    }

    public void b() {
        boolean z = !this.e.p;
        this.e.p = true;
        if (this.d == null) {
            this.d = new bd(getContext());
            this.d.setVisibility(8);
            addView(this.d, new LayoutParams(-1, -1));
        } else {
            if (!(this.c == null || this.b == null)) {
                this.c.bringToFront();
                this.c.setVisibility(0);
                this.c.a();
                aw.a(false, this.b);
            }
            this.d.bringToFront();
            this.d.a();
        }
        if (!g()) {
            this.d.setVisibility(0);
            if (z) {
                if (!(this.c == null || this.b == null)) {
                    e().a();
                }
                aw.a(true, this.d);
            }
        }
    }

    public void c() {
        if (this.d != null) {
            this.d.clearAnimation();
            this.d.setVisibility(8);
        }
    }

    public void d() {
    }

    public boolean onTouchEvent(MotionEvent ev) {
        return true;
    }

    public ax e() {
        return this.b;
    }

    public View f() {
        return this.a;
    }

    public boolean g() {
        return this.d != null && this.d.getVisibility() == 0;
    }

    public com.chartboost.sdk.Model.a h() {
        return this.e;
    }
}

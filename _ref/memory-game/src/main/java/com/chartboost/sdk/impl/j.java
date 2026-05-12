package com.chartboost.sdk.impl;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;

public abstract class j extends RelativeLayout {
    private static final String b = j.class.getSimpleName();
    protected f a;
    private k c;
    private a d = a.BOTTOM;

    public enum a {
        TOP,
        BOTTOM,
        LEFT,
        RIGHT
    }

    protected abstract View a();

    protected abstract int b();

    public j(Context context, f fVar) {
        super(context);
        this.a = fVar;
        a(context);
    }

    public void a(a aVar) {
        if (aVar == null) {
            CBLogging.b(b, "Side object cannot be null");
            return;
        }
        this.d = aVar;
        LayoutParams layoutParams = null;
        setClickable(false);
        int b = b();
        switch (this.d) {
            case TOP:
                layoutParams = new RelativeLayout.LayoutParams(-1, CBUtility.a(b, getContext()));
                layoutParams.addRule(10);
                this.c.b(1);
                break;
            case BOTTOM:
                layoutParams = new RelativeLayout.LayoutParams(-1, CBUtility.a(b, getContext()));
                layoutParams.addRule(12);
                this.c.b(4);
                break;
            case LEFT:
                layoutParams = new RelativeLayout.LayoutParams(CBUtility.a(b, getContext()), -1);
                layoutParams.addRule(9);
                this.c.b(8);
                break;
            case RIGHT:
                layoutParams = new RelativeLayout.LayoutParams(CBUtility.a(b, getContext()), -1);
                layoutParams.addRule(11);
                this.c.b(2);
                break;
        }
        setLayoutParams(layoutParams);
    }

    private void a(Context context) {
        Context context2 = getContext();
        setGravity(17);
        this.c = new k(context2);
        this.c.a(-1);
        this.c.setBackgroundColor(-855638017);
        addView(this.c, new RelativeLayout.LayoutParams(-1, -1));
        addView(a(), new RelativeLayout.LayoutParams(-1, -1));
    }

    public void a(boolean z) {
        a(z, 500);
    }

    private void a(final boolean z, long j) {
        this.a.A = z;
        if (!z || getVisibility() != 0) {
            if (z || getVisibility() != 8) {
                Animation translateAnimation;
                Runnable anonymousClass1 = new Runnable(this) {
                    final /* synthetic */ j b;

                    public void run() {
                        if (!z) {
                            this.b.setVisibility(8);
                            this.b.clearAnimation();
                        }
                        this.b.a.i.remove(Integer.valueOf(hashCode()));
                    }
                };
                if (z) {
                    setVisibility(0);
                }
                float a = CBUtility.a((float) b(), getContext());
                float f;
                switch (this.d) {
                    case TOP:
                        if (z) {
                            f = -a;
                        } else {
                            f = 0.0f;
                        }
                        translateAnimation = new TranslateAnimation(0.0f, 0.0f, f, z ? 0.0f : -a);
                        break;
                    case BOTTOM:
                        if (z) {
                            f = a;
                        } else {
                            f = 0.0f;
                        }
                        if (z) {
                            a = 0.0f;
                        }
                        translateAnimation = new TranslateAnimation(0.0f, 0.0f, f, a);
                        break;
                    case LEFT:
                        if (z) {
                            f = -a;
                        } else {
                            f = 0.0f;
                        }
                        translateAnimation = new TranslateAnimation(f, z ? 0.0f : -a, 0.0f, 0.0f);
                        break;
                    case RIGHT:
                        f = z ? a : 0.0f;
                        if (z) {
                            a = 0.0f;
                        }
                        translateAnimation = new TranslateAnimation(f, a, 0.0f, 0.0f);
                        break;
                    default:
                        translateAnimation = null;
                        break;
                }
                translateAnimation.setDuration(j);
                translateAnimation.setFillAfter(!z);
                startAnimation(translateAnimation);
                this.a.i.put(Integer.valueOf(hashCode()), anonymousClass1);
                a.a().a.postDelayed(anonymousClass1, j);
            }
        }
    }
}

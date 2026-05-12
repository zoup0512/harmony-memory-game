package com.appodeal.ads;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewManager;
import android.view.ViewParent;
import android.widget.FrameLayout;
import com.appodeal.ads.utils.t;
import com.appodeal.ads.utils.t.b;
import com.mopub.volley.DefaultRetryPolicy;
import org.nexage.sourcekit.mraid.rtb.RtbInfo;

public abstract class z extends d {
    public abstract void a(Activity activity, int i, int i2);

    protected abstract void a(View view);

    public abstract ViewGroup c();

    protected int d() {
        return -1;
    }

    protected int e() {
        return -2;
    }

    public void a(final Activity activity, final w wVar, final int i, boolean z) {
        final View view = v.p;
        if (!z || view != null) {
            View c;
            LayoutParams layoutParams = new FrameLayout.LayoutParams(d(), e());
            if (view == null || !z) {
                c = c();
            } else {
                c = view;
            }
            if (c != null) {
                if (c.equals(view)) {
                    b(view);
                }
                View findViewById = activity.findViewById(v.o);
                if (findViewById == null) {
                    findViewById = v.r;
                }
                if (findViewById != null) {
                    MrecView mrecView = (MrecView) findViewById;
                    mrecView.addView(c, layoutParams);
                    mrecView.setVisibility(0);
                }
                t.a(this, c, v.B, new b(this) {
                    final /* synthetic */ z c;

                    public void a() {
                        y.a(i, wVar);
                    }

                    public void b() {
                        y.b(i, wVar);
                    }
                });
                v.p = c;
                v.q = i;
                v.p.setVisibility(0);
                if (VERSION.SDK_INT >= 16) {
                    v.p.setAlpha(0.0f);
                    v.p.animate().alpha(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT).setDuration(800).withLayer().setListener(new AnimatorListenerAdapter(this) {
                        final /* synthetic */ z d;

                        public void onAnimationEnd(Animator animator) {
                            super.onAnimationEnd(animator);
                            if (!c.equals(view)) {
                                try {
                                    this.d.b(view);
                                    this.d.a(activity, view);
                                } catch (Throwable e) {
                                    Appodeal.a(e);
                                }
                            }
                        }
                    });
                } else if (!c.equals(view)) {
                    try {
                        b(view);
                        a(activity, view);
                    } catch (Throwable e) {
                        Appodeal.a(e);
                    }
                }
            }
        }
    }

    private void b(View view) {
        if (view != null) {
            t.a(view);
            ViewParent parent = view.getParent();
            if (parent != null && (parent instanceof ViewManager)) {
                ((ViewManager) parent).removeView(view);
            }
        }
    }

    private void a(Activity activity, View view) {
        for (w wVar : v.a((Context) activity)) {
            if (wVar.f() != null) {
                wVar.f().a(view);
            }
        }
    }

    public void a(Activity activity, int i) {
    }

    public void b(Activity activity, int i) {
    }

    public void f() {
    }

    public boolean g() {
        return false;
    }

    public RtbInfo a(String str, int i) {
        try {
            return new RtbInfo(this.a, ((aa) v.t.get(i)).l.getString("id"), ((aa) v.t.get(i)).m, str, 256);
        } catch (Throwable e) {
            Appodeal.a(e);
            return null;
        }
    }
}

package com.my.target.core.ui.views.fspromo;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.mopub.volley.DefaultRetryPolicy;
import java.util.ArrayList;
import java.util.Collection;

/* compiled from: FSHeaderAnimationProcessor */
public final class a {
    private int a;
    private final View b;
    private final View c;
    private final View d;
    private final TextView e;
    private final View f;
    private final View g;
    private final View h;
    private final TextView i;
    private boolean j;
    private boolean k;

    public a(View view, View view2, View view3, TextView textView, View view4, View view5, View view6, TextView textView2) {
        this.b = view;
        this.c = view2;
        this.d = view3;
        this.e = textView;
        this.f = view4;
        this.g = view5;
        this.h = view6;
        this.i = textView2;
    }

    public final void a(View... viewArr) {
        a(300, viewArr);
    }

    private void a(int i, View... viewArr) {
        if (!this.j && VERSION.SDK_INT >= 14) {
            int height;
            float width = ((float) this.b.getWidth()) - (((float) this.b.getWidth()) * 0.7f);
            this.b.setPivotX(0.0f);
            this.b.setPivotY(0.0f);
            this.c.setPivotX((float) this.c.getWidth());
            this.c.setPivotY(0.0f);
            if (TextUtils.isEmpty(this.i.getText().toString())) {
                height = this.f.getHeight();
            } else {
                height = this.i.getHeight();
            }
            int height2 = ((this.e.getHeight() + this.d.getHeight()) - height) - this.a;
            height = (int) (((float) this.b.getHeight()) - (((float) this.b.getHeight()) * 0.7f));
            if (height2 > height) {
                height = height2;
            }
            height2 = ((int) (((float) this.b.getHeight()) * 0.7f)) + (this.a * 2);
            int height3 = ((int) (((float) this.c.getHeight()) * 0.7f)) + (this.a * 2);
            if (height > this.g.getHeight() - height2) {
                height = this.g.getHeight() - height2;
            } else if (height > this.g.getHeight() - height3) {
                height = this.g.getHeight() - height3;
            }
            Collection arrayList = new ArrayList();
            arrayList.add(ObjectAnimator.ofFloat(this.c, View.SCALE_X, new float[]{0.7f}));
            arrayList.add(ObjectAnimator.ofFloat(this.c, View.SCALE_Y, new float[]{0.7f}));
            arrayList.add(ObjectAnimator.ofFloat(this.b, View.SCALE_X, new float[]{0.7f}));
            arrayList.add(ObjectAnimator.ofFloat(this.b, View.SCALE_Y, new float[]{0.7f}));
            arrayList.add(ObjectAnimator.ofFloat(this.d, View.ALPHA, new float[]{0.0f}));
            arrayList.add(ObjectAnimator.ofFloat(this.e, View.ALPHA, new float[]{0.0f}));
            arrayList.add(ObjectAnimator.ofFloat(this.f, View.ALPHA, new float[]{DefaultRetryPolicy.DEFAULT_BACKOFF_MULT}));
            arrayList.add(ObjectAnimator.ofFloat(this.g, View.ALPHA, new float[]{0.6f}));
            arrayList.add(ObjectAnimator.ofFloat(this.h, View.TRANSLATION_X, new float[]{-width}));
            arrayList.add(ObjectAnimator.ofFloat(this.g, View.TRANSLATION_Y, new float[]{(float) height}));
            for (Object ofFloat : viewArr) {
                arrayList.add(ObjectAnimator.ofFloat(ofFloat, View.TRANSLATION_Y, new float[]{(float) height}));
            }
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.addListener(new AnimatorListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public final void onAnimationStart(Animator animator) {
                    if (!TextUtils.isEmpty(this.a.i.getText().toString())) {
                        this.a.i.setVisibility(0);
                    } else if (!this.a.k) {
                        this.a.f.setVisibility(0);
                    }
                }

                public final void onAnimationEnd(Animator animator) {
                    if (!TextUtils.isEmpty(this.a.e.getText().toString())) {
                        this.a.e.setVisibility(4);
                    }
                    this.a.d.setVisibility(4);
                }

                public final void onAnimationCancel(Animator animator) {
                }

                public final void onAnimationRepeat(Animator animator) {
                }
            });
            animatorSet.playTogether(arrayList);
            animatorSet.setDuration((long) i);
            animatorSet.start();
        }
    }

    public final void b(View... viewArr) {
        if (!this.j && VERSION.SDK_INT >= 14) {
            Collection arrayList = new ArrayList();
            arrayList.add(ObjectAnimator.ofFloat(this.c, View.SCALE_Y, new float[]{DefaultRetryPolicy.DEFAULT_BACKOFF_MULT}));
            arrayList.add(ObjectAnimator.ofFloat(this.c, View.SCALE_X, new float[]{DefaultRetryPolicy.DEFAULT_BACKOFF_MULT}));
            arrayList.add(ObjectAnimator.ofFloat(this.b, View.SCALE_Y, new float[]{DefaultRetryPolicy.DEFAULT_BACKOFF_MULT}));
            arrayList.add(ObjectAnimator.ofFloat(this.b, View.SCALE_X, new float[]{DefaultRetryPolicy.DEFAULT_BACKOFF_MULT}));
            arrayList.add(ObjectAnimator.ofFloat(this.d, View.ALPHA, new float[]{DefaultRetryPolicy.DEFAULT_BACKOFF_MULT}));
            arrayList.add(ObjectAnimator.ofFloat(this.e, View.ALPHA, new float[]{DefaultRetryPolicy.DEFAULT_BACKOFF_MULT}));
            arrayList.add(ObjectAnimator.ofFloat(this.f, View.ALPHA, new float[]{0.0f}));
            arrayList.add(ObjectAnimator.ofFloat(this.g, View.ALPHA, new float[]{DefaultRetryPolicy.DEFAULT_BACKOFF_MULT}));
            arrayList.add(ObjectAnimator.ofFloat(this.h, View.TRANSLATION_X, new float[]{0.0f}));
            arrayList.add(ObjectAnimator.ofFloat(this.g, View.TRANSLATION_Y, new float[]{0.0f}));
            for (Object ofFloat : viewArr) {
                arrayList.add(ObjectAnimator.ofFloat(ofFloat, View.TRANSLATION_Y, new float[]{0.0f}));
            }
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(arrayList);
            animatorSet.addListener(new AnimatorListener(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public final void onAnimationStart(Animator animator) {
                    if (!TextUtils.isEmpty(this.a.e.getText().toString())) {
                        this.a.e.setVisibility(0);
                    }
                    this.a.d.setVisibility(0);
                }

                public final void onAnimationEnd(Animator animator) {
                    this.a.f.setVisibility(4);
                    this.a.i.setVisibility(4);
                }

                public final void onAnimationCancel(Animator animator) {
                }

                public final void onAnimationRepeat(Animator animator) {
                }
            });
            animatorSet.setDuration(300);
            animatorSet.start();
        }
    }

    public final void a(int i) {
        this.a = i;
    }

    public final void a() {
        this.j = true;
    }

    public final void c(View... viewArr) {
        a(0, viewArr);
    }

    public final void b() {
        this.k = true;
    }
}

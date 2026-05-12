package com.appodeal.ads.utils;

import android.os.Build.VERSION;
import android.os.Handler;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.an;
import com.appodeal.ads.d;
import java.util.HashMap;
import java.util.Map;

public class t {
    private static Handler a = new Handler();
    private static Map<View, a> b = new HashMap();
    private static Map<d, View> c = new HashMap();

    public interface b {
        void a();

        void b();
    }

    private static class a implements Runnable {
        private View a;
        private b b;
        private int c;
        private int d;
        private boolean e;

        a(View view, int i, b bVar) {
            this.a = view;
            this.b = bVar;
            this.d = i;
        }

        public void run() {
            if (an.a(Appodeal.b, this.a)) {
                this.c += Callback.DEFAULT_SWIPE_ANIMATION_DURATION;
                if (!(this.e || this.b == null)) {
                    this.e = true;
                    this.b.a();
                }
            } else {
                this.c = 0;
            }
            if (this.c <= 0 || this.c < this.d) {
                t.a.postDelayed(this, 250);
                return;
            }
            t.a(this.a);
            if (this.b != null && this.d > 0) {
                this.b.b();
            }
        }
    }

    public static void a(d dVar, final View view, int i, b bVar) {
        if (c.containsKey(dVar)) {
            View view2 = (View) c.get(dVar);
            if (!view2.equals(view)) {
                if (b.containsKey(view2)) {
                    a(view2);
                }
                c.remove(dVar);
            }
        }
        if (!b.containsKey(view)) {
            Runnable aVar = new a(view, i, bVar);
            c.put(dVar, view);
            b.put(view, aVar);
            a.postDelayed(aVar, 250);
            if (VERSION.SDK_INT >= 12) {
                view.addOnAttachStateChangeListener(new OnAttachStateChangeListener() {
                    public void onViewAttachedToWindow(View view) {
                    }

                    public void onViewDetachedFromWindow(View view) {
                        t.a(view);
                    }
                });
            }
        }
    }

    public static void a(View view) {
        a aVar = (a) b.get(view);
        if (aVar != null) {
            a.removeCallbacks(aVar);
            b.remove(view);
        }
    }

    public static void a(d dVar) {
        View view = (View) c.get(dVar);
        if (view != null) {
            a(view);
        }
    }
}

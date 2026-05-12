package com.chartboost.sdk;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.RelativeLayout;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Libraries.f;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.impl.aw;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class h {
    public final Handler a = CBUtility.c();
    public boolean b = false;
    protected com.chartboost.sdk.Libraries.e.a c;
    protected final List<b> d = new ArrayList();
    protected final List<b> e = new ArrayList();
    protected com.chartboost.sdk.Libraries.e.a f;
    protected final com.chartboost.sdk.Model.a g;
    protected f h;
    public final Map<Integer, Runnable> i = Collections.synchronizedMap(new HashMap());
    protected boolean j = true;
    protected boolean k = true;
    private boolean l;
    private a m;

    public interface b {
        boolean a();
    }

    public abstract class a extends RelativeLayout {
        final /* synthetic */ h a;
        private boolean b = false;
        private int c = -1;
        private int d = -1;
        private int e = -1;
        private int f = -1;
        private f g = null;

        protected abstract void a(int i, int i2);

        public a(h hVar, Context context) {
            this.a = hVar;
            super(context);
            hVar.m = this;
            hVar.l = false;
            setFocusableInTouchMode(true);
            requestFocus();
        }

        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            this.e = w;
            this.f = h;
            if (this.c != -1 && this.d != -1 && this.a.g != null && this.a.g.a == com.chartboost.sdk.Model.a.b.NATIVE) {
                a();
            }
        }

        private boolean b(int i, int i2) {
            boolean z = true;
            if (this.a.g != null && this.a.g.a == com.chartboost.sdk.Model.a.b.WEB) {
                return true;
            }
            if (this.b) {
                return false;
            }
            f a = CBUtility.a();
            if (this.c == i && this.d == i2 && this.g == a) {
                return true;
            }
            this.b = true;
            try {
                if (this.a.j && a.a()) {
                    this.a.h = a;
                } else if (this.a.k && a.b()) {
                    this.a.h = a;
                }
                a(i, i2);
                post(new Runnable(this) {
                    final /* synthetic */ a a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.requestLayout();
                    }
                });
                this.c = i;
                this.d = i2;
                this.g = a;
            } catch (Exception e) {
                CBLogging.b("CBViewProtocol", "Exception raised while layouting Subviews", e);
                com.chartboost.sdk.Tracking.a.a(getClass(), "tryLayout", e);
                z = false;
            }
            this.b = false;
            return z;
        }

        public final void a() {
            a(false);
        }

        public final void a(boolean z) {
            if (z) {
                this.g = null;
            }
            a((Activity) com.chartboost.sdk.impl.a.a().a((View) this));
        }

        public void b() {
        }

        public boolean a(Activity activity) {
            if (this.e == -1 || this.f == -1) {
                int width;
                int height;
                try {
                    width = getWidth();
                    height = getHeight();
                    if (width == 0 || height == 0) {
                        View findViewById = activity.getWindow().findViewById(16908290);
                        if (findViewById == null) {
                            findViewById = activity.getWindow().getDecorView();
                        }
                        width = findViewById.getWidth();
                        height = findViewById.getHeight();
                    }
                } catch (Exception e) {
                    height = 0;
                    width = 0;
                }
                if (width == 0 || r0 == 0) {
                    DisplayMetrics displayMetrics = new DisplayMetrics();
                    activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                    width = displayMetrics.widthPixels;
                    height = displayMetrics.heightPixels;
                }
                this.e = width;
                this.f = height;
            }
            return b(this.e, this.f);
        }

        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            for (int i = 0; i < this.a.i.size(); i++) {
                this.a.a.removeCallbacks((Runnable) this.a.i.get(Integer.valueOf(i)));
            }
            this.a.i.clear();
        }

        public final void a(View view) {
            int i = 200;
            if (200 == getId()) {
                i = 201;
            }
            int i2 = i;
            View findViewById = findViewById(i);
            while (findViewById != null) {
                i2++;
                findViewById = findViewById(i2);
            }
            view.setId(i2);
            view.setSaveEnabled(false);
        }

        protected boolean c() {
            return h.a(getContext());
        }
    }

    protected abstract a b(Context context);

    public static boolean a(Context context) {
        return (context.getResources().getConfiguration().screenLayout & 15) >= 4;
    }

    public h(com.chartboost.sdk.Model.a aVar) {
        this.g = aVar;
        this.m = null;
        this.h = CBUtility.a();
        this.l = false;
    }

    public f a() {
        return this.h;
    }

    public boolean a(com.chartboost.sdk.Libraries.e.a aVar) {
        this.f = aVar.a("assets");
        if (!this.f.b()) {
            return true;
        }
        CBLogging.b("CBViewProtocol", "Media got from the response is null or empty");
        a(CBImpressionError.INVALID_RESPONSE);
        return false;
    }

    public void a(b bVar) {
        if (bVar.a()) {
            this.e.remove(bVar);
        }
        this.d.remove(bVar);
        if (this.d.isEmpty() && !b()) {
            CBLogging.b("CBViewProtocol", "Error while downloading the assets");
            a(CBImpressionError.ASSETS_DOWNLOAD_FAILURE);
        }
    }

    public boolean b() {
        if (this.e.isEmpty()) {
            i();
            return true;
        }
        CBLogging.d("CBViewProtocol", "not completed loading assets for impression");
        return false;
    }

    public CBImpressionError c() {
        Activity f = f.q().f();
        if (f == null) {
            this.m = null;
            return CBImpressionError.NO_HOST_ACTIVITY;
        } else if (!this.k && !this.j) {
            return CBImpressionError.WRONG_ORIENTATION;
        } else {
            if (this.m == null) {
                this.m = b((Context) f);
            }
            if (this.g.a != com.chartboost.sdk.Model.a.b.NATIVE || this.m.a(f)) {
                return null;
            }
            this.m = null;
            return CBImpressionError.ERROR_CREATING_VIEW;
        }
    }

    public void d() {
        f();
        for (int i = 0; i < this.i.size(); i++) {
            this.a.removeCallbacks((Runnable) this.i.get(Integer.valueOf(i)));
        }
        this.i.clear();
    }

    public a e() {
        return this.m;
    }

    public void f() {
        if (this.m != null) {
            this.m.b();
        }
        this.m = null;
    }

    public com.chartboost.sdk.Libraries.e.a g() {
        return this.f;
    }

    public void b(b bVar) {
        this.d.add(bVar);
        this.e.add(bVar);
    }

    protected void a(CBImpressionError cBImpressionError) {
        this.g.a(cBImpressionError);
    }

    protected void h() {
        if (!this.l) {
            this.l = true;
            this.g.b();
        }
    }

    protected void i() {
        this.g.c();
    }

    public boolean a(String str, com.chartboost.sdk.Libraries.e.a aVar) {
        return this.g.a(str, aVar);
    }

    public void a(boolean z, View view) {
        a(z, view, true);
    }

    public void a(final boolean z, final View view, boolean z2) {
        int i = 8;
        if (((z && view.getVisibility() == 0) || (!z && view.getVisibility() == 8)) && this.i.get(Integer.valueOf(view.hashCode())) == null) {
            return;
        }
        if (z2) {
            Runnable anonymousClass1 = new Runnable(this) {
                final /* synthetic */ h c;

                public void run() {
                    if (!z) {
                        view.setVisibility(8);
                        view.setClickable(false);
                    }
                    this.c.i.remove(Integer.valueOf(view.hashCode()));
                }
            };
            if (this.g.a == com.chartboost.sdk.Model.a.b.WEB) {
                aw.a(z, view, 500);
                a(view, anonymousClass1, 500);
            } else {
                aw.a(z, view, 500);
                a(view, anonymousClass1, 500);
            }
            return;
        }
        if (z) {
            i = 0;
        }
        view.setVisibility(i);
        view.setClickable(z);
    }

    protected void a(View view, Runnable runnable, long j) {
        Runnable runnable2 = (Runnable) this.i.get(Integer.valueOf(view.hashCode()));
        if (runnable2 != null) {
            this.a.removeCallbacks(runnable2);
        }
        this.i.put(Integer.valueOf(view.hashCode()), runnable);
        this.a.postDelayed(runnable, j);
    }

    public static int a(String str) {
        int i = 0;
        if (str != null) {
            if (!str.startsWith("#")) {
                try {
                    i = Color.parseColor(str);
                } catch (IllegalArgumentException e) {
                    str = "#" + str;
                }
            }
            if (str.length() == 4 || str.length() == 5) {
                StringBuilder stringBuilder = new StringBuilder((str.length() * 2) + 1);
                stringBuilder.append("#");
                for (int i2 = i; i2 < str.length() - 1; i2++) {
                    stringBuilder.append(str.charAt(i2 + 1));
                    stringBuilder.append(str.charAt(i2 + 1));
                }
                str = stringBuilder.toString();
            }
            try {
                i = Color.parseColor(str);
            } catch (Throwable e2) {
                CBLogging.d("CBViewProtocol", "error parsing color " + str, e2);
            }
        }
        return i;
    }

    public float j() {
        return 0.0f;
    }

    public float k() {
        return 0.0f;
    }

    public boolean l() {
        return false;
    }

    public void m() {
        if (this.b) {
            this.b = false;
        }
        if (e() != null && CBUtility.a() != e().g) {
            e().a(false);
        }
    }

    public void n() {
        this.b = true;
    }
}

package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ImageView.ScaleType;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Libraries.f;
import com.chartboost.sdk.Libraries.k;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.c;
import com.chartboost.sdk.h;
import com.facebook.internal.AnalyticsEvents;
import com.mopub.mobileads.resource.DrawableConstants.RadialCountdown;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class t extends h {
    private String A;
    protected final k l = new k(this);
    protected final k m = new k(this);
    private List<com.chartboost.sdk.Libraries.e.a> n = new ArrayList();
    private final k o = new k(this);
    private final k p = new k(this);
    private final k q = new k(this);
    private final k r = new k(this);
    private final k s = new k(this);
    private final k t = new k(this);
    private final k u = new k(this);
    private Set<k> v;
    private int w;
    private com.chartboost.sdk.Libraries.e.a x;
    private int y;
    private int z;

    public class a extends com.chartboost.sdk.h.a {
        final /* synthetic */ t b;
        private az c;
        private ay d;
        private final TextView e;
        private final RelativeLayout f;
        private ListView g;
        private final a h;

        public class a extends ArrayAdapter<com.chartboost.sdk.Libraries.e.a> {
            final /* synthetic */ a a;
            private final Context b;

            public /* synthetic */ Object getItem(int x0) {
                return a(x0);
            }

            public a(a aVar, Context context) {
                this.a = aVar;
                super(context, 0, aVar.b.n);
                this.b = context;
            }

            public View getView(int position, View convertView, ViewGroup parent) {
                int i = 0;
                final com.chartboost.sdk.Libraries.e.a a = a(position);
                com.chartboost.sdk.Libraries.e.a a2 = a.a("type");
                if (convertView == null) {
                    View view;
                    b[] values = b.values();
                    int length = values.length;
                    while (i < length) {
                        b bVar = values[i];
                        if (a2.equals(bVar.e)) {
                            try {
                                view = (m) bVar.f.getConstructor(new Class[]{t.class, Context.class}).newInstance(new Object[]{this.a.b, this.b});
                                break;
                            } catch (Exception e) {
                                CBLogging.b(this, "error in more apps list", e);
                                com.chartboost.sdk.Tracking.a.a(getClass(), "getView cell constructor", e);
                                view = null;
                            }
                        } else {
                            i++;
                        }
                    }
                    view = null;
                    convertView = view;
                } else if (!(convertView instanceof m)) {
                    return convertView;
                } else {
                    m convertView2 = (m) convertView;
                }
                if (convertView == null) {
                    return new View(getContext());
                }
                convertView.a(a, position);
                LayoutParams layoutParams = convertView.getLayoutParams();
                if (layoutParams == null || !(layoutParams instanceof AbsListView.LayoutParams)) {
                    layoutParams = new AbsListView.LayoutParams(-1, convertView.a());
                } else {
                    layoutParams = (AbsListView.LayoutParams) layoutParams;
                    layoutParams.width = -1;
                    layoutParams.height = convertView.a();
                }
                convertView.setLayoutParams(layoutParams);
                convertView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ a c;

                    public void onClick(View v) {
                        String e = a.e("deep-link");
                        if (TextUtils.isEmpty(e) || !af.a(e)) {
                            e = a.e("link");
                        }
                        if (VERSION.SDK_INT >= 11) {
                            a.a("x", Float.valueOf(this.c.a.getX()));
                            a.a("y", Float.valueOf(this.c.a.getY()));
                            a.a("width", Integer.valueOf(convertView.getHeight()));
                            a.a("height", Integer.valueOf(convertView.getWidth()));
                        }
                        this.c.a.b.a(e, a);
                    }
                });
                return convertView;
            }

            public int getCount() {
                return this.a.b.n.size();
            }

            public com.chartboost.sdk.Libraries.e.a a(int i) {
                return (com.chartboost.sdk.Libraries.e.a) this.a.b.n.get(i);
            }

            public int getItemViewType(int position) {
                com.chartboost.sdk.Libraries.e.a a = a(position).a("type");
                b[] values = b.values();
                for (int i = 0; i < values.length; i++) {
                    if (a.equals(values[i].e)) {
                        return i;
                    }
                }
                return 0;
            }

            public int getViewTypeCount() {
                return b.values().length;
            }
        }

        private a(final t tVar, Context context) {
            this.b = tVar;
            super(tVar, context);
            setBackgroundColor(-1);
            this.d = new ay(context);
            this.c = new az(this, context) {
                final /* synthetic */ a b;

                protected void a(MotionEvent motionEvent) {
                    this.b.b.h();
                }
            };
            this.c.setContentDescription("CBClose");
            this.e = new TextView(context);
            this.e.setBackgroundColor(tVar.y);
            this.e.setText(tVar.A);
            this.e.setTextColor(tVar.z);
            this.e.setTextSize(2, c() ? 30.0f : RadialCountdown.TEXT_SIZE_SP);
            this.e.setGravity(17);
            this.g = new ListView(context);
            this.g.setBackgroundColor(-1);
            this.g.setDividerHeight(0);
            a(this.g);
            addView(this.g);
            this.d.setFocusable(false);
            this.c.setFocusable(false);
            this.c.setClickable(true);
            this.d.setScaleType(ScaleType.CENTER_CROP);
            this.c.a(ScaleType.FIT_CENTER);
            this.f = new RelativeLayout(context);
            this.f.addView(this.d, new RelativeLayout.LayoutParams(-1, -1));
            this.f.addView(this.e, new RelativeLayout.LayoutParams(-1, -1));
            addView(this.f, new RelativeLayout.LayoutParams(-1, -1));
            addView(this.c, new RelativeLayout.LayoutParams(-1, -1));
            a(this.f);
            this.h = new a(this, context);
        }

        protected void a(int i, int i2) {
            k e;
            int i3;
            Context context = getContext();
            f a = CBUtility.a();
            if (a.a() && this.b.r.e()) {
                e = this.b.r;
            } else if (a.b() && this.b.s.e()) {
                e = this.b.s;
            } else if (this.b.u.e()) {
                e = this.b.u;
            } else {
                e = null;
            }
            if (e != null) {
                this.b.w = e.i();
                if (e.h() < i) {
                    this.b.w = Math.round(((float) this.b.w) * (((float) i) / ((float) e.h())));
                }
                this.e.setVisibility(8);
                this.d.a(e);
            } else {
                this.b.w = CBUtility.a(c() ? 80 : 40, context);
                this.e.setVisibility(0);
            }
            if (this.b.x.c()) {
                this.b.w = CBUtility.a(this.b.x.l(), context);
            }
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
            LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, this.b.w);
            if (this.b.p.e() && a.a()) {
                e = this.b.p;
            } else if (this.b.q.e() && a.b()) {
                e = this.b.q;
            } else if (this.b.o.e()) {
                e = this.b.o;
            } else {
                e = null;
            }
            if (e != null) {
                this.c.a(e, layoutParams2);
                if (c()) {
                    i3 = 14;
                } else {
                    i3 = 7;
                }
                i3 = CBUtility.a(i3, context);
            } else {
                this.c.a("X");
                this.c.a().setTextSize(2, c() ? 26.0f : 16.0f);
                this.c.a().setTextColor(this.b.z);
                this.c.a().setTypeface(Typeface.SANS_SERIF, 1);
                layoutParams2.width = this.b.w / 2;
                layoutParams2.height = this.b.w / 3;
                if (c()) {
                    i3 = 30;
                } else {
                    i3 = 20;
                }
                i3 = CBUtility.a(i3, context);
            }
            int round = Math.round(((float) (this.b.w - layoutParams2.height)) / 2.0f);
            layoutParams2.rightMargin = i3;
            layoutParams2.topMargin = round;
            layoutParams2.addRule(11);
            layoutParams.width = -1;
            layoutParams.height = -1;
            layoutParams.addRule(3, this.f.getId());
            this.g.setAdapter(this.h);
            this.g.setLayoutParams(layoutParams);
            this.c.setLayoutParams(layoutParams2);
            this.f.setLayoutParams(layoutParams3);
        }

        public void b() {
            super.b();
            this.c = null;
            this.d = null;
            this.g = null;
        }
    }

    private enum b {
        FEATURED("featured", n.class),
        REGULAR("regular", o.class),
        WEBVIEW("webview", q.class),
        VIDEO(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO, p.class);
        
        private final String e;
        private final Class<? extends m> f;

        private b(String str, Class<? extends m> cls) {
            this.e = str;
            this.f = cls;
        }
    }

    public t(com.chartboost.sdk.Model.a aVar) {
        super(aVar);
    }

    protected com.chartboost.sdk.h.a b(Context context) {
        return new a(context);
    }

    public boolean a(com.chartboost.sdk.Libraries.e.a aVar) {
        int i = 0;
        if (!super.a(aVar)) {
            return false;
        }
        com.chartboost.sdk.Libraries.e.a a = aVar.a("cells");
        if (a.b()) {
            a(CBImpressionError.INVALID_RESPONSE);
            return false;
        }
        this.v = new HashSet();
        while (i < a.p()) {
            com.chartboost.sdk.Libraries.e.a c = a.c(i);
            this.n.add(c);
            com.chartboost.sdk.Libraries.e.a a2 = c.a("type");
            if (a2.equals("regular")) {
                c = c.a("assets");
                if (c.c()) {
                    a(c, SettingsJsonConstants.APP_ICON_KEY);
                }
            } else if (a2.equals("featured")) {
                c = c.a("assets");
                if (c.c()) {
                    a(c, DeviceInfo.ORIENTATION_PORTRAIT);
                    a(c, DeviceInfo.ORIENTATION_LANDSCAPE);
                }
            } else if (a2.equals("webview")) {
            }
            i++;
        }
        this.o.a("close");
        this.q.a("close-landscape");
        this.p.a("close-portrait");
        this.u.a("header-center");
        this.r.a("header-portrait");
        this.s.a("header-landscape");
        this.t.a("header-tile");
        this.m.a("play-button");
        this.l.a("install-button");
        this.x = this.f.a("header-height");
        if (this.x.c()) {
            this.w = this.x.l();
        } else {
            this.w = h.a(c.x()) ? 80 : 40;
        }
        this.y = this.f.c("background-color") ? h.a(this.f.e("background-color")) : -14571545;
        this.A = this.f.c("header-text") ? this.f.e("header-text") : "More Free Games";
        this.z = this.f.c("text-color") ? h.a(this.f.e("text-color")) : -1;
        return true;
    }

    private void a(com.chartboost.sdk.Libraries.e.a aVar, String str) {
        if (!aVar.b(str)) {
            k kVar = new k(this);
            this.v.add(kVar);
            kVar.a(aVar, str, new Bundle());
        }
    }

    public void d() {
        super.d();
        this.n = null;
        for (k d : this.v) {
            d.d();
        }
        this.v.clear();
        this.o.d();
        this.q.d();
        this.p.d();
        this.u.d();
        this.t.d();
        this.r.d();
        this.s.d();
        this.m.d();
        this.l.d();
    }
}

package com.appodeal.ads;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.appodeal.ads.Native.NativeAdType;
import com.appodeal.ads.d.h;
import com.appodeal.ads.utils.a;
import com.appodeal.ads.utils.t;
import com.appodeal.ads.utils.t.b;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.io.File;
import org.json.JSONObject;
import org.nexage.sourcekit.vast.model.VASTModel;

public abstract class ab extends d implements OnClickListener, NativeAd {
    public View b;
    protected Bitmap c;
    protected Bitmap d;
    protected String e;
    protected String f;
    private final int g;
    private final ac h;
    private u i;
    private Uri j;
    private VASTModel k;
    private boolean l;

    protected abstract void a(View view);

    protected abstract void b(View view);

    public ab(int i, ac acVar) {
        this.g = i;
        this.h = acVar;
    }

    public ab(int i, ac acVar, String str, String str2) {
        this.g = i;
        this.h = acVar;
        this.e = str;
        this.f = str2;
    }

    public String getCallToAction() {
        return "Install";
    }

    public void registerViewForInteraction(View view) {
        if (this.b != null) {
            this.b.setOnClickListener(null);
        }
        view.setOnClickListener(this);
        if (view instanceof ViewGroup) {
            a((ViewGroup) view);
        }
        this.b = view;
        if (!this.l) {
            t.a(this, this.b, Native.v, new b(this) {
                final /* synthetic */ ab a;

                {
                    this.a = r1;
                }

                public void a() {
                    this.a.l = true;
                    this.a.b(this.a.b);
                    ae.a(this.a.g, this.a.h, this.a);
                    this.a.e();
                }

                public void b() {
                    ae.b(this.a.g, this.a.h, this.a);
                }
            });
        }
        if (this.i != null) {
            this.i.b();
            if (Native.B && Native.A != NativeAdType.NoVideo) {
                this.i.c();
            }
        }
    }

    private void a(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            if (!(childAt instanceof u)) {
                if (childAt instanceof Button) {
                    ((Button) childAt).setOnClickListener(this);
                }
                if (childAt instanceof ViewGroup) {
                    a((ViewGroup) childAt);
                }
            }
        }
    }

    public float getRating() {
        return 5.0f;
    }

    public void unregisterViewForInteraction() {
        if (this.b != null) {
            this.b.setOnClickListener(null);
        }
        t.a(this.b);
        c();
        if (this.i != null) {
            this.i.d();
        }
    }

    public void c() {
        c(getImage());
        c(getIcon());
        this.c = null;
        this.d = null;
        n();
    }

    private void c(Bitmap bitmap) {
        if (bitmap != null) {
            try {
                bitmap.recycle();
            } catch (Throwable e) {
                Appodeal.a("Problem recycling Native bitmap");
                Appodeal.a(e);
            }
        }
    }

    private void n() {
        if (this.j != null) {
            File file = new File(this.j.getPath());
            if (file.exists()) {
                file.delete();
            }
            this.j = null;
        }
    }

    public final void onClick(View view) {
        a(view);
        ae.c(this.g, this.h, this);
    }

    public Bitmap getImage() {
        return this.c;
    }

    public Bitmap getIcon() {
        return this.d;
    }

    public View getProviderView(Context context) {
        return null;
    }

    public String getMainImageUrl() {
        return this.e;
    }

    public String getIconUrl() {
        return this.f;
    }

    public void a(Bitmap bitmap) {
        this.c = bitmap;
    }

    public void b(Bitmap bitmap) {
        this.d = bitmap;
    }

    @Nullable
    public String getAgeRestrictions() {
        return null;
    }

    public String d() {
        return null;
    }

    public void e() {
        if (Native.s) {
            String f = f();
            ag agVar = (ag) Native.l.get(this.g);
            String str = agVar.o;
            if (!(agVar.A == null || agVar.A.h() == null)) {
                h h = agVar.A.h();
                if (h.e() != null && h.f()) {
                    str = h.e();
                }
            }
            new a(Appodeal.b, agVar.n, str, agVar.p, f, 512).b();
        }
    }

    String f() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("title", getTitle());
            jSONObject.put("description", getDescription());
            jSONObject.put("cta", getCallToAction());
            jSONObject.put("rating", (double) getRating());
            jSONObject.put("image", getMainImageUrl());
            jSONObject.put(SettingsJsonConstants.APP_ICON_KEY, getIconUrl());
            jSONObject.put("url", d());
        } catch (Throwable e) {
            Appodeal.a(e);
        }
        return jSONObject.toString();
    }

    @Nullable
    protected String g() {
        return null;
    }

    @Nullable
    protected String h() {
        return null;
    }

    public void a(VASTModel vASTModel) {
        this.k = vASTModel;
    }

    public VASTModel i() {
        return this.k;
    }

    public void setAppodealMediaView(AppodealMediaView appodealMediaView) {
        this.i = new u(appodealMediaView.getContext());
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams.addRule(13, -1);
        appodealMediaView.addView(this.i, layoutParams);
        this.i.setNativeAd(this);
    }

    public Uri j() {
        return this.j;
    }

    public void a(Uri uri) {
        this.j = uri;
    }

    public boolean k() {
        return false;
    }

    public boolean containsVideo() {
        return false;
    }

    public void l() {
    }

    public int m() {
        return hashCode();
    }
}

package com.my.target.core.ui.views.fspromo;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import com.mopub.volley.DefaultRetryPolicy;
import com.my.target.core.models.banners.e;
import com.my.target.core.ui.views.controls.IconButton;
import com.my.target.core.utils.l;

public abstract class FSPromoView extends RelativeLayout {
    private int a;
    private final Bitmap b;
    private final Bitmap c;

    public interface a {
        void a();

        void b();

        void c();
    }

    public abstract void a(int i);

    public abstract void a(boolean z);

    public abstract boolean a();

    public abstract IconButton b();

    public abstract void c();

    public abstract void d();

    public abstract void e();

    public abstract void f();

    public abstract boolean g();

    public abstract void h();

    public abstract void setCloseListener(OnClickListener onClickListener);

    public abstract void setOnCTAClickListener(OnClickListener onClickListener);

    public abstract void setOnVideoClickListener(a aVar);

    public abstract void setTimeChanged(float f, float f2);

    public abstract void setVideoListener(com.my.target.core.ui.views.VideoTextureView.a aVar);

    public void setBanner(e eVar) {
        if (b() != null) {
            b().setBitmap(this.b, Boolean.valueOf(false));
        }
    }

    public FSPromoView(Context context) {
        super(context);
        l lVar = new l(context);
        this.b = com.my.target.core.resources.a.d(lVar.a(28));
        this.c = com.my.target.core.resources.a.c(lVar.a(28));
    }

    protected void onMeasure(int i, int i2) {
        int i3;
        if (((float) MeasureSpec.getSize(i)) / ((float) MeasureSpec.getSize(i2)) > DefaultRetryPolicy.DEFAULT_BACKOFF_MULT) {
            i3 = 2;
        } else {
            i3 = 1;
        }
        if (i3 != this.a) {
            b(i3);
        }
        super.onMeasure(i, i2);
    }

    protected void b(int i) {
        this.a = i;
    }

    public final void b(boolean z) {
        if (z) {
            b().setBitmap(this.c, Boolean.valueOf(false));
        } else {
            b().setBitmap(this.b, Boolean.valueOf(false));
        }
    }
}

package com.my.target.core.engines;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.my.target.core.engines.b.a;
import com.my.target.core.facades.e;
import com.my.target.core.ui.views.FSImageView;
import com.my.target.core.ui.views.controls.IconButton;

/* compiled from: FSImageAdEngine */
public final class d extends a implements OnClickListener {
    private FSImageView c = new FSImageView(this.b);
    private a d;
    private e e;

    public d(e eVar, ViewGroup viewGroup, Context context) {
        Bitmap bitmap;
        Bitmap bitmap2;
        Bitmap bitmap3 = null;
        super(viewGroup, context);
        this.e = eVar;
        this.c.b().setOnClickListener(this);
        this.c.a().setOnClickListener(this);
        com.my.target.core.models.banners.d b = this.e.b();
        if (b.o() != null) {
            bitmap = (Bitmap) b.o().getData();
        } else {
            bitmap = null;
        }
        if (b.n() != null) {
            bitmap2 = (Bitmap) b.n().getData();
        } else {
            bitmap2 = null;
        }
        if (b.k() != null) {
            bitmap3 = b.k().getBitmap();
        }
        this.c.setImages(bitmap, bitmap2, bitmap3);
        if (!(b.getAgeRestrictions() == null || b.getAgeRestrictions().equals(""))) {
            this.c.setAgeRestrictions(b.getAgeRestrictions());
        }
        this.a.addView(this.c, new LayoutParams(-1, -1));
        this.e.c();
    }

    public final void a(a aVar) {
        this.d = aVar;
    }

    public final void onClick(View view) {
        if (!(view instanceof IconButton)) {
            this.e.d();
            if (this.d != null) {
                this.d.onClick(true);
            }
        } else if (this.d != null) {
            this.d.onCloseClick();
        }
    }

    public final void e() {
        super.e();
        this.e.e();
    }
}

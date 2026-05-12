package com.my.target.core.ui;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout.LayoutParams;
import com.my.target.core.models.banners.f;
import com.my.target.core.ui.views.VideoDialogView;
import com.my.target.core.ui.views.VideoTextureView;
import com.my.target.core.ui.views.controls.IconButton;
import com.my.target.core.utils.l;
import com.my.target.nativeads.models.VideoData;

/* compiled from: VideoDialog */
public final class b extends Dialog {
    private final VideoDialogView a = new VideoDialogView(getContext());
    private final Bitmap b;
    private final Bitmap c;

    /* compiled from: VideoDialog */
    public interface a {
        void a();

        void a(View view);

        void b();

        void c();
    }

    public final IconButton a() {
        return this.a.a();
    }

    public b(Context context) {
        super(context);
        l lVar = new l(getContext());
        this.b = com.my.target.core.resources.a.d(lVar.a(28));
        this.c = com.my.target.core.resources.a.c(lVar.a(28));
    }

    protected final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setBackgroundDrawable(new ColorDrawable(-16777216));
        requestWindowFeature(1);
        this.a.setLayoutParams(new LayoutParams(-1, -1));
        setContentView(this.a);
        getWindow().setLayout(-1, -1);
    }

    public final void a(a aVar) {
        this.a.setDialogListener(aVar);
        this.a.setDismissButtonListener(new OnClickListener(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public final void onClick(View view) {
                this.a.dismiss();
            }
        });
    }

    public final void a(f fVar, VideoData videoData) {
        this.a.setData(fVar, videoData);
    }

    public final void b() {
        this.a.b();
    }

    public final void c() {
        this.a.f();
    }

    public final void d() {
        this.a.d();
    }

    public final void e() {
        this.a.e();
    }

    public final void a(VideoTextureView videoTextureView) {
        this.a.a(videoTextureView);
    }

    public final void f() {
        this.a.h();
    }

    public final void a(float f, float f2) {
        this.a.a(f, f2);
    }

    public final void a(boolean z) {
        if (z) {
            this.a.a().setBitmap(this.c, Boolean.valueOf(false));
        } else {
            this.a.a().setBitmap(this.b, Boolean.valueOf(false));
        }
    }
}

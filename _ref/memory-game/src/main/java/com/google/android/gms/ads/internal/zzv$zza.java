package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.ViewSwitcher;
import com.google.android.gms.internal.zzkd;
import com.google.android.gms.internal.zzkk;
import com.google.android.gms.internal.zzku;
import com.google.android.gms.internal.zzlh;
import java.util.ArrayList;
import java.util.List;

public class zzv$zza extends ViewSwitcher {
    private final zzkk zzaqf;
    @Nullable
    private final zzku zzaqg;

    public zzv$zza(Context context, OnGlobalLayoutListener onGlobalLayoutListener, OnScrollChangedListener onScrollChangedListener) {
        super(context);
        this.zzaqf = new zzkk(context);
        if (context instanceof Activity) {
            this.zzaqg = new zzku((Activity) context, this, onGlobalLayoutListener, onScrollChangedListener);
        } else {
            this.zzaqg = new zzku(null, this, onGlobalLayoutListener, onScrollChangedListener);
        }
        this.zzaqg.zzts();
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.zzaqg != null) {
            this.zzaqg.onAttachedToWindow();
        }
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.zzaqg != null) {
            this.zzaqg.onDetachedFromWindow();
        }
    }

    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        this.zzaqf.zze(motionEvent);
        return false;
    }

    public void removeAllViews() {
        List<zzlh> arrayList = new ArrayList();
        for (int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            if (childAt != null && (childAt instanceof zzlh)) {
                arrayList.add((zzlh) childAt);
            }
        }
        super.removeAllViews();
        for (zzlh destroy : arrayList) {
            destroy.destroy();
        }
    }

    public void zzgr() {
        zzkd.v("Disable position monitoring on adFrame.");
        if (this.zzaqg != null) {
            this.zzaqg.zztt();
        }
    }

    public zzkk zzgv() {
        return this.zzaqf;
    }
}

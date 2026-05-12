package com.google.android.gms.internal;

import android.view.View;
import android.view.ViewTreeObserver;
import java.lang.ref.WeakReference;

@zzin
abstract class zzlf {
    private final WeakReference<View> zzcoh;

    public zzlf(View view) {
        this.zzcoh = new WeakReference(view);
    }

    public final void detach() {
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver != null) {
            zzb(viewTreeObserver);
        }
    }

    protected ViewTreeObserver getViewTreeObserver() {
        View view = (View) this.zzcoh.get();
        if (view == null) {
            return null;
        }
        ViewTreeObserver viewTreeObserver = view.getViewTreeObserver();
        return (viewTreeObserver == null || !viewTreeObserver.isAlive()) ? null : viewTreeObserver;
    }

    protected abstract void zza(ViewTreeObserver viewTreeObserver);

    protected abstract void zzb(ViewTreeObserver viewTreeObserver);

    public final void zzua() {
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        if (viewTreeObserver != null) {
            zza(viewTreeObserver);
        }
    }
}

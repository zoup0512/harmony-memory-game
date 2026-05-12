package com.google.android.gms.internal;

import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import java.lang.ref.WeakReference;

@zzin
class zzle extends zzlf implements OnScrollChangedListener {
    private final WeakReference<OnScrollChangedListener> zzcog;

    public zzle(View view, OnScrollChangedListener onScrollChangedListener) {
        super(view);
        this.zzcog = new WeakReference(onScrollChangedListener);
    }

    public void onScrollChanged() {
        OnScrollChangedListener onScrollChangedListener = (OnScrollChangedListener) this.zzcog.get();
        if (onScrollChangedListener != null) {
            onScrollChangedListener.onScrollChanged();
        } else {
            detach();
        }
    }

    protected void zza(ViewTreeObserver viewTreeObserver) {
        viewTreeObserver.addOnScrollChangedListener(this);
    }

    protected void zzb(ViewTreeObserver viewTreeObserver) {
        viewTreeObserver.removeOnScrollChangedListener(this);
    }
}

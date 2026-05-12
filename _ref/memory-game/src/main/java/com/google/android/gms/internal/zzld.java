package com.google.android.gms.internal;

import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import com.google.android.gms.ads.internal.zzu;
import java.lang.ref.WeakReference;

@zzin
class zzld extends zzlf implements OnGlobalLayoutListener {
    private final WeakReference<OnGlobalLayoutListener> zzcog;

    public zzld(View view, OnGlobalLayoutListener onGlobalLayoutListener) {
        super(view);
        this.zzcog = new WeakReference(onGlobalLayoutListener);
    }

    public void onGlobalLayout() {
        OnGlobalLayoutListener onGlobalLayoutListener = (OnGlobalLayoutListener) this.zzcog.get();
        if (onGlobalLayoutListener != null) {
            onGlobalLayoutListener.onGlobalLayout();
        } else {
            detach();
        }
    }

    protected void zza(ViewTreeObserver viewTreeObserver) {
        viewTreeObserver.addOnGlobalLayoutListener(this);
    }

    protected void zzb(ViewTreeObserver viewTreeObserver) {
        zzu.zzfs().zza(viewTreeObserver, (OnGlobalLayoutListener) this);
    }
}

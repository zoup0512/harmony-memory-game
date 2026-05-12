package com.google.android.gms.internal;

import android.view.View;
import java.lang.ref.WeakReference;

public class zzcd$zzd implements zzck {
    private final WeakReference<View> zzare;
    private final WeakReference<zzju> zzarf;

    public zzcd$zzd(View view, zzju com_google_android_gms_internal_zzju) {
        this.zzare = new WeakReference(view);
        this.zzarf = new WeakReference(com_google_android_gms_internal_zzju);
    }

    public View zzhh() {
        return (View) this.zzare.get();
    }

    public boolean zzhi() {
        return this.zzare.get() == null || this.zzarf.get() == null;
    }

    public zzck zzhj() {
        return new zzcd$zzc((View) this.zzare.get(), (zzju) this.zzarf.get());
    }
}

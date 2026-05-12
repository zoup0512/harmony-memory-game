package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.util.client.zzc;
import com.google.android.gms.ads.internal.zzu;

@zzin
public final class zzkq extends zzkc {
    private final String zzae;
    private final zzc zzcmr;

    public zzkq(Context context, String str, String str2) {
        this(str2, zzu.zzfq().zzg(context, str));
    }

    public zzkq(String str, String str2) {
        this.zzcmr = new zzc(str2);
        this.zzae = str;
    }

    public void onStop() {
    }

    public void zzew() {
        this.zzcmr.zzcr(this.zzae);
    }
}

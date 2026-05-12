package com.google.android.gms.ads.internal.formats;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzdr.zza;
import com.google.android.gms.internal.zzin;

@zzin
public class zzc extends zza {
    private final Uri mUri;
    private final Drawable zzbfe;
    private final double zzbff;

    public zzc(Drawable drawable, Uri uri, double d) {
        this.zzbfe = drawable;
        this.mUri = uri;
        this.zzbff = d;
    }

    public double getScale() {
        return this.zzbff;
    }

    public Uri getUri() throws RemoteException {
        return this.mUri;
    }

    public zzd zzkt() throws RemoteException {
        return zze.zzac(this.zzbfe);
    }
}

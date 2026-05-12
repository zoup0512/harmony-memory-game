package com.google.android.gms.analytics;

import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import android.util.LogPrinter;
import com.facebook.share.internal.ShareConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public final class zzd implements zzk {
    private static final Uri zzcss;
    private final LogPrinter zzcst = new LogPrinter(4, "GA/LogCatTransport");

    static {
        Builder builder = new Builder();
        builder.scheme(ShareConstants.MEDIA_URI);
        builder.authority("local");
        zzcss = builder.build();
    }

    public void zzb(zze com_google_android_gms_analytics_zze) {
        List<zzg> arrayList = new ArrayList(com_google_android_gms_analytics_zze.zzwg());
        Collections.sort(arrayList, new Comparator<zzg>(this) {
            final /* synthetic */ zzd zzcsu;

            {
                this.zzcsu = r1;
            }

            public /* synthetic */ int compare(Object obj, Object obj2) {
                return zza((zzg) obj, (zzg) obj2);
            }

            public int zza(zzg com_google_android_gms_analytics_zzg, zzg com_google_android_gms_analytics_zzg2) {
                return com_google_android_gms_analytics_zzg.getClass().getCanonicalName().compareTo(com_google_android_gms_analytics_zzg2.getClass().getCanonicalName());
            }
        });
        StringBuilder stringBuilder = new StringBuilder();
        for (zzg obj : arrayList) {
            Object obj2 = obj.toString();
            if (!TextUtils.isEmpty(obj2)) {
                if (stringBuilder.length() != 0) {
                    stringBuilder.append(", ");
                }
                stringBuilder.append(obj2);
            }
        }
        this.zzcst.println(stringBuilder.toString());
    }

    public Uri zzvu() {
        return zzcss;
    }
}

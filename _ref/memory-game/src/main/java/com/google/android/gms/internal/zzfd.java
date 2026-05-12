package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.common.api.Releasable;
import java.lang.ref.WeakReference;
import java.util.Map;

@zzin
public abstract class zzfd implements Releasable {
    protected Context mContext;
    protected String zzbjf;
    protected WeakReference<zzlh> zzbjg;

    public zzfd(zzlh com_google_android_gms_internal_zzlh) {
        this.mContext = com_google_android_gms_internal_zzlh.getContext();
        this.zzbjf = zzu.zzfq().zzg(this.mContext, com_google_android_gms_internal_zzlh.zzum().zzcs);
        this.zzbjg = new WeakReference(com_google_android_gms_internal_zzlh);
    }

    private void zza(String str, Map<String, String> map) {
        zzlh com_google_android_gms_internal_zzlh = (zzlh) this.zzbjg.get();
        if (com_google_android_gms_internal_zzlh != null) {
            com_google_android_gms_internal_zzlh.zza(str, (Map) map);
        }
    }

    private String zzbb(String str) {
        String str2 = "internal";
        Object obj = -1;
        switch (str.hashCode()) {
            case -1396664534:
                if (str.equals("badUrl")) {
                    obj = 6;
                    break;
                }
                break;
            case -1347010958:
                if (str.equals("inProgress")) {
                    obj = 2;
                    break;
                }
                break;
            case -918817863:
                if (str.equals("downloadTimeout")) {
                    obj = 7;
                    break;
                }
                break;
            case -659376217:
                if (str.equals("contentLengthMissing")) {
                    obj = 3;
                    break;
                }
                break;
            case -642208130:
                if (str.equals("playerFailed")) {
                    obj = 1;
                    break;
                }
                break;
            case -354048396:
                if (str.equals("sizeExceeded")) {
                    obj = 8;
                    break;
                }
                break;
            case -32082395:
                if (str.equals("externalAbort")) {
                    obj = 9;
                    break;
                }
                break;
            case 96784904:
                if (str.equals("error")) {
                    obj = null;
                    break;
                }
                break;
            case 580119100:
                if (str.equals("expireFailed")) {
                    obj = 5;
                    break;
                }
                break;
            case 725497484:
                if (str.equals("noCacheDir")) {
                    obj = 4;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
            case 1:
            case 2:
            case 3:
                return "internal";
            case 4:
            case 5:
                return "io";
            case 6:
            case 7:
                return "network";
            case 8:
            case 9:
                return "policy";
            default:
                return str2;
        }
    }

    public abstract void abort();

    public void release() {
    }

    protected void zza(String str, String str2, int i) {
        zza.zzcnb.post(new 2(this, str, str2, i));
    }

    protected void zza(String str, String str2, int i, int i2, boolean z) {
        zza.zzcnb.post(new 1(this, str, str2, i, i2, z));
    }

    protected void zza(String str, String str2, String str3, String str4) {
        zza.zzcnb.post(new 3(this, str, str2, str3, str4));
    }

    public abstract boolean zzaz(String str);

    protected String zzba(String str) {
        return zzm.zziw().zzcu(str);
    }
}

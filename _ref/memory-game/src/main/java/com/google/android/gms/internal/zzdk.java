package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.facebook.internal.NativeProtocol;
import com.google.android.gms.ads.internal.zzu;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@zzin
public class zzdk {
    private final Object zzail = new Object();
    boolean zzbdo;
    private final List<zzdi> zzbef = new LinkedList();
    private final Map<String, String> zzbeg = new LinkedHashMap();
    private String zzbeh;
    private zzdi zzbei;
    @Nullable
    private zzdk zzbej;

    public zzdk(boolean z, String str, String str2) {
        this.zzbdo = z;
        this.zzbeg.put(NativeProtocol.WEB_DIALOG_ACTION, str);
        this.zzbeg.put("ad_format", str2);
    }

    public boolean zza(zzdi com_google_android_gms_internal_zzdi, long j, String... strArr) {
        synchronized (this.zzail) {
            for (String com_google_android_gms_internal_zzdi2 : strArr) {
                this.zzbef.add(new zzdi(j, com_google_android_gms_internal_zzdi2, com_google_android_gms_internal_zzdi));
            }
        }
        return true;
    }

    public boolean zza(@Nullable zzdi com_google_android_gms_internal_zzdi, String... strArr) {
        return (!this.zzbdo || com_google_android_gms_internal_zzdi == null) ? false : zza(com_google_android_gms_internal_zzdi, zzu.zzfu().elapsedRealtime(), strArr);
    }

    public void zzas(String str) {
        if (this.zzbdo) {
            synchronized (this.zzail) {
                this.zzbeh = str;
            }
        }
    }

    @Nullable
    public zzdi zzc(long j) {
        return !this.zzbdo ? null : new zzdi(j, null, null);
    }

    public void zzc(@Nullable zzdk com_google_android_gms_internal_zzdk) {
        synchronized (this.zzail) {
            this.zzbej = com_google_android_gms_internal_zzdk;
        }
    }

    public void zzh(String str, String str2) {
        if (this.zzbdo && !TextUtils.isEmpty(str2)) {
            zzde zzsl = zzu.zzft().zzsl();
            if (zzsl != null) {
                synchronized (this.zzail) {
                    zzsl.zzaq(str).zza(this.zzbeg, str, str2);
                }
            }
        }
    }

    public zzdi zzkg() {
        return zzc(zzu.zzfu().elapsedRealtime());
    }

    public void zzkh() {
        synchronized (this.zzail) {
            this.zzbei = zzkg();
        }
    }

    public String zzki() {
        String stringBuilder;
        StringBuilder stringBuilder2 = new StringBuilder();
        synchronized (this.zzail) {
            for (zzdi com_google_android_gms_internal_zzdi : this.zzbef) {
                long time = com_google_android_gms_internal_zzdi.getTime();
                String zzkd = com_google_android_gms_internal_zzdi.zzkd();
                zzdi com_google_android_gms_internal_zzdi2 = com_google_android_gms_internal_zzdi2.zzke();
                if (com_google_android_gms_internal_zzdi2 != null && time > 0) {
                    stringBuilder2.append(zzkd).append('.').append(time - com_google_android_gms_internal_zzdi2.getTime()).append(',');
                }
            }
            this.zzbef.clear();
            if (!TextUtils.isEmpty(this.zzbeh)) {
                stringBuilder2.append(this.zzbeh);
            } else if (stringBuilder2.length() > 0) {
                stringBuilder2.setLength(stringBuilder2.length() - 1);
            }
            stringBuilder = stringBuilder2.toString();
        }
        return stringBuilder;
    }

    public zzdi zzkj() {
        zzdi com_google_android_gms_internal_zzdi;
        synchronized (this.zzail) {
            com_google_android_gms_internal_zzdi = this.zzbei;
        }
        return com_google_android_gms_internal_zzdi;
    }

    Map<String, String> zzm() {
        Map<String, String> map;
        synchronized (this.zzail) {
            zzde zzsl = zzu.zzft().zzsl();
            if (zzsl == null || this.zzbej == null) {
                map = this.zzbeg;
            } else {
                map = zzsl.zza(this.zzbeg, this.zzbej.zzm());
            }
        }
        return map;
    }
}

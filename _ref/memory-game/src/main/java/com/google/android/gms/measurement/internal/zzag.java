package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.internal.zzuf.zzf;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

class zzag {
    final boolean ajV;
    final int amo;
    final boolean amp;
    final String amq;
    final List<String> amr;
    final String ams;

    public zzag(zzf com_google_android_gms_internal_zzuf_zzf) {
        boolean z;
        boolean z2 = false;
        zzab.zzy(com_google_android_gms_internal_zzuf_zzf);
        if (com_google_android_gms_internal_zzuf_zzf.amV == null || com_google_android_gms_internal_zzuf_zzf.amV.intValue() == 0) {
            z = false;
        } else {
            if (com_google_android_gms_internal_zzuf_zzf.amV.intValue() == 6) {
                if (com_google_android_gms_internal_zzuf_zzf.amY == null || com_google_android_gms_internal_zzuf_zzf.amY.length == 0) {
                    z = false;
                }
            } else if (com_google_android_gms_internal_zzuf_zzf.amW == null) {
                z = false;
            }
            z = true;
        }
        if (z) {
            this.amo = com_google_android_gms_internal_zzuf_zzf.amV.intValue();
            if (com_google_android_gms_internal_zzuf_zzf.amX != null && com_google_android_gms_internal_zzuf_zzf.amX.booleanValue()) {
                z2 = true;
            }
            this.amp = z2;
            if (this.amp || this.amo == 1 || this.amo == 6) {
                this.amq = com_google_android_gms_internal_zzuf_zzf.amW;
            } else {
                this.amq = com_google_android_gms_internal_zzuf_zzf.amW.toUpperCase(Locale.ENGLISH);
            }
            this.amr = com_google_android_gms_internal_zzuf_zzf.amY == null ? null : zza(com_google_android_gms_internal_zzuf_zzf.amY, this.amp);
            if (this.amo == 1) {
                this.ams = this.amq;
            } else {
                this.ams = null;
            }
        } else {
            this.amo = 0;
            this.amp = false;
            this.amq = null;
            this.amr = null;
            this.ams = null;
        }
        this.ajV = z;
    }

    private List<String> zza(String[] strArr, boolean z) {
        if (z) {
            return Arrays.asList(strArr);
        }
        List<String> arrayList = new ArrayList();
        for (String toUpperCase : strArr) {
            arrayList.add(toUpperCase.toUpperCase(Locale.ENGLISH));
        }
        return arrayList;
    }

    public Boolean zzmi(String str) {
        if (!this.ajV || str == null) {
            return null;
        }
        if (!(this.amp || this.amo == 1)) {
            str = str.toUpperCase(Locale.ENGLISH);
        }
        switch (this.amo) {
            case 1:
                return Boolean.valueOf(Pattern.compile(this.ams, this.amp ? 0 : 66).matcher(str).matches());
            case 2:
                return Boolean.valueOf(str.startsWith(this.amq));
            case 3:
                return Boolean.valueOf(str.endsWith(this.amq));
            case 4:
                return Boolean.valueOf(str.contains(this.amq));
            case 5:
                return Boolean.valueOf(str.equals(this.amq));
            case 6:
                return Boolean.valueOf(this.amr.contains(str));
            default:
                return null;
        }
    }
}

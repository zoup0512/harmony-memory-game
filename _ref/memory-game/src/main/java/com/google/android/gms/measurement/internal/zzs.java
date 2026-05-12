package com.google.android.gms.measurement.internal;

import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.internal.zzuf.zzd;

class zzs {
    final boolean ajN;
    final int ajO;
    long ajP;
    double ajQ;
    long ajR;
    double ajS;
    long ajT;
    double ajU;
    final boolean ajV;

    public zzs(zzd com_google_android_gms_internal_zzuf_zzd) {
        boolean z;
        boolean z2 = true;
        zzab.zzy(com_google_android_gms_internal_zzuf_zzd);
        if (com_google_android_gms_internal_zzuf_zzd.amN == null || com_google_android_gms_internal_zzuf_zzd.amN.intValue() == 0) {
            z = false;
        } else {
            if (com_google_android_gms_internal_zzuf_zzd.amN.intValue() != 4) {
                if (com_google_android_gms_internal_zzuf_zzd.amP == null) {
                    z = false;
                }
            } else if (com_google_android_gms_internal_zzuf_zzd.amQ == null || com_google_android_gms_internal_zzuf_zzd.amR == null) {
                z = false;
            }
            z = true;
        }
        if (z) {
            this.ajO = com_google_android_gms_internal_zzuf_zzd.amN.intValue();
            if (com_google_android_gms_internal_zzuf_zzd.amO == null || !com_google_android_gms_internal_zzuf_zzd.amO.booleanValue()) {
                z2 = false;
            }
            this.ajN = z2;
            if (com_google_android_gms_internal_zzuf_zzd.amN.intValue() == 4) {
                if (this.ajN) {
                    this.ajS = Double.parseDouble(com_google_android_gms_internal_zzuf_zzd.amQ);
                    this.ajU = Double.parseDouble(com_google_android_gms_internal_zzuf_zzd.amR);
                } else {
                    this.ajR = Long.parseLong(com_google_android_gms_internal_zzuf_zzd.amQ);
                    this.ajT = Long.parseLong(com_google_android_gms_internal_zzuf_zzd.amR);
                }
            } else if (this.ajN) {
                this.ajQ = Double.parseDouble(com_google_android_gms_internal_zzuf_zzd.amP);
            } else {
                this.ajP = Long.parseLong(com_google_android_gms_internal_zzuf_zzd.amP);
            }
        } else {
            this.ajO = 0;
            this.ajN = false;
        }
        this.ajV = z;
    }

    public Boolean zzbk(long j) {
        boolean z = true;
        if (!this.ajV) {
            return null;
        }
        if (this.ajN) {
            return null;
        }
        switch (this.ajO) {
            case 1:
                if (j >= this.ajP) {
                    z = false;
                }
                return Boolean.valueOf(z);
            case 2:
                if (j <= this.ajP) {
                    z = false;
                }
                return Boolean.valueOf(z);
            case 3:
                if (j != this.ajP) {
                    z = false;
                }
                return Boolean.valueOf(z);
            case 4:
                if (j < this.ajR || j > this.ajT) {
                    z = false;
                }
                return Boolean.valueOf(z);
            default:
                return null;
        }
    }

    public Boolean zzj(double d) {
        boolean z = true;
        boolean z2 = false;
        if (!this.ajV) {
            return null;
        }
        if (!this.ajN) {
            return null;
        }
        switch (this.ajO) {
            case 1:
                if (d >= this.ajQ) {
                    z = false;
                }
                return Boolean.valueOf(z);
            case 2:
                if (d <= this.ajQ) {
                    z = false;
                }
                return Boolean.valueOf(z);
            case 3:
                if (d == this.ajQ || Math.abs(d - this.ajQ) < 2.0d * Math.max(Math.ulp(d), Math.ulp(this.ajQ))) {
                    z2 = true;
                }
                return Boolean.valueOf(z2);
            case 4:
                if (d < this.ajS || d > this.ajU) {
                    z = false;
                }
                return Boolean.valueOf(z);
            default:
                return null;
        }
    }
}

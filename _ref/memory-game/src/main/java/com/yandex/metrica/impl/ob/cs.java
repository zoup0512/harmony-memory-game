package com.yandex.metrica.impl.ob;

import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellSignalStrength;

public final class cs {
    private Integer a;
    private final Integer b;
    private final Integer c;
    private final Integer d;
    private final Integer e;
    private final String f;
    private final String g;
    private final boolean h;
    private final int i;
    private final Integer j;

    static abstract class b {
        static final Integer a = Integer.valueOf(Integer.MAX_VALUE);
        static final Integer b = Integer.valueOf(Integer.MAX_VALUE);
        static final Integer c = Integer.valueOf(Integer.MAX_VALUE);
        static final Integer d = Integer.valueOf(Integer.MAX_VALUE);
        static final Integer e = Integer.valueOf(Integer.MAX_VALUE);

        abstract cs a(CellInfo cellInfo);

        b() {
        }

        protected cs a(Integer num, Integer num2, CellSignalStrength cellSignalStrength, Integer num3, Integer num4, boolean z, int i, Integer num5) {
            Integer num6 = null;
            if (num != null) {
                if (num == a) {
                    num = null;
                }
                num6 = num;
            }
            Integer num7 = null;
            if (num2 != null) {
                if (num2 == b) {
                    num2 = null;
                }
                num7 = num2;
            }
            Integer valueOf = cellSignalStrength != null ? Integer.valueOf(cellSignalStrength.getDbm()) : null;
            Integer num8 = null;
            if (num4 != null) {
                if (num4 == c) {
                    num4 = null;
                }
                num8 = num4;
            }
            Integer num9 = null;
            if (num3 != null) {
                if (num3 == d) {
                    num3 = null;
                }
                num9 = num3;
            }
            Integer num10 = (num5 == null || num5 == e) ? null : num5;
            return new cs(num8, num9, num7, num6, null, null, valueOf, z, i, num10);
        }
    }

    static class a extends b {
        a() {
        }

        cs a(CellInfo cellInfo) {
            CellInfoCdma cellInfoCdma = (CellInfoCdma) cellInfo;
            return a(null, null, cellInfoCdma.getCellSignalStrength(), null, null, cellInfoCdma.isRegistered(), 2, null);
        }
    }

    static class c extends b {
        c() {
        }

        cs a(CellInfo cellInfo) {
            CellInfoGsm cellInfoGsm = (CellInfoGsm) cellInfo;
            CellIdentityGsm cellIdentity = cellInfoGsm.getCellIdentity();
            return a(Integer.valueOf(cellIdentity.getCid()), Integer.valueOf(cellIdentity.getLac()), cellInfoGsm.getCellSignalStrength(), Integer.valueOf(cellIdentity.getMnc()), Integer.valueOf(cellIdentity.getMcc()), cellInfoGsm.isRegistered(), 1, null);
        }
    }

    static class d extends b {
        d() {
        }

        cs a(CellInfo cellInfo) {
            CellInfoLte cellInfoLte = (CellInfoLte) cellInfo;
            CellIdentityLte cellIdentity = cellInfoLte.getCellIdentity();
            return a(Integer.valueOf(cellIdentity.getCi()), Integer.valueOf(cellIdentity.getTac()), cellInfoLte.getCellSignalStrength(), Integer.valueOf(cellIdentity.getMnc()), Integer.valueOf(cellIdentity.getMcc()), cellInfoLte.isRegistered(), 4, Integer.valueOf(cellIdentity.getPci()));
        }
    }

    static class e extends b {
        e() {
        }

        cs a(CellInfo cellInfo) {
            CellInfoWcdma cellInfoWcdma = (CellInfoWcdma) cellInfo;
            CellIdentityWcdma cellIdentity = cellInfoWcdma.getCellIdentity();
            return a(Integer.valueOf(cellIdentity.getCid()), Integer.valueOf(cellIdentity.getLac()), cellInfoWcdma.getCellSignalStrength(), Integer.valueOf(cellIdentity.getMnc()), Integer.valueOf(cellIdentity.getMcc()), cellInfoWcdma.isRegistered(), 3, Integer.valueOf(cellIdentity.getPsc()));
        }
    }

    public cs(Integer num, Integer num2, Integer num3, Integer num4, String str, String str2, Integer num5, boolean z, int i, Integer num6) {
        this.b = num;
        this.c = num2;
        this.d = num3;
        this.e = num4;
        this.f = str;
        this.g = str2;
        this.a = num5;
        this.h = z;
        this.i = i;
        this.j = num6;
    }

    public Integer a() {
        return this.a;
    }

    public Integer b() {
        return this.b;
    }

    public Integer c() {
        return this.c;
    }

    public Integer d() {
        return this.d;
    }

    public Integer e() {
        return this.e;
    }

    public String f() {
        return this.f;
    }

    public String g() {
        return this.g;
    }

    public boolean h() {
        return this.h;
    }

    public void a(Integer num) {
        this.a = num;
    }

    public int i() {
        return this.i;
    }

    public Integer j() {
        return this.j;
    }

    public String toString() {
        return "CellDescription{mSignalStrength=" + this.a + ", mMobileCountryCode=" + this.b + ", mMobileNetworkCode=" + this.c + ", mLocationAreaCode=" + this.d + ", mCellId=" + this.e + ", mOperatorName='" + this.f + '\'' + ", mNetworkType='" + this.g + '\'' + ", mConnected=" + this.h + ", mCellType=" + this.i + ", mPci=" + this.j + '}';
    }
}

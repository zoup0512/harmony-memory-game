package com.yandex.metrica.impl.ob;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.telephony.CellInfo;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.SparseArray;
import com.facebook.places.model.PlaceFields;
import com.yandex.metrica.impl.ah;
import com.yandex.metrica.impl.bg;
import com.yandex.metrica.impl.d;
import io.branch.referral.BranchError;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

final class cu extends cr implements d {
    private static final SparseArray<String> a = new SparseArray<String>() {
        {
            put(0, null);
            put(7, "1xRTT");
            put(4, "CDMA");
            put(2, "EDGE");
            put(14, "eHRPD");
            put(5, "EVDO rev.0");
            put(6, "EVDO rev.A");
            put(12, "EVDO rev.B");
            put(1, "GPRS");
            put(8, "HSDPA");
            put(10, "HSPA");
            put(15, "HSPA+");
            put(9, "HSUPA");
            put(11, "iDen");
            put(3, "UMTS");
            put(12, "EVDO rev.B");
            if (bg.a(11)) {
                put(14, "eHRPD");
                put(13, "LTE");
                if (bg.a(13)) {
                    put(15, "HSPA+");
                }
            }
        }
    };
    private final TelephonyManager b;
    private PhoneStateListener c;
    private boolean d = false;
    private final com.yandex.metrica.impl.d.a<cz> e = new com.yandex.metrica.impl.d.a();
    private final com.yandex.metrica.impl.d.a<cs[]> f = new com.yandex.metrica.impl.d.a();
    private final Handler g;
    private final Context h;

    private class a extends PhoneStateListener {
        final /* synthetic */ cu a;

        private a(cu cuVar) {
            this.a = cuVar;
        }

        public void onSignalStrengthsChanged(SignalStrength signalStrength) {
            super.onSignalStrengthsChanged(signalStrength);
            this.a.a(signalStrength);
        }
    }

    protected cu(Context context) {
        this.h = context;
        this.b = (TelephonyManager) context.getSystemService(PlaceFields.PHONE);
        HandlerThread handlerThread = new HandlerThread("TelephonyProviderThread");
        handlerThread.start();
        this.g = new Handler(handlerThread.getLooper());
        this.g.post(new Runnable(this) {
            final /* synthetic */ cu a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.c = new a();
            }
        });
    }

    public synchronized void a() {
        this.g.post(new Runnable(this) {
            final /* synthetic */ cu a;

            {
                this.a = r1;
            }

            public void run() {
                if (!this.a.d) {
                    this.a.d = true;
                    try {
                        if (this.a.c != null) {
                            this.a.b.listen(this.a.c, 256);
                        }
                    } catch (Exception e) {
                    }
                }
            }
        });
    }

    public synchronized void b() {
        this.g.post(new Runnable(this) {
            final /* synthetic */ cu a;

            {
                this.a = r1;
            }

            public void run() {
                if (this.a.d) {
                    this.a.d = false;
                    try {
                        if (this.a.c != null) {
                            this.a.b.listen(this.a.c, 0);
                        }
                    } catch (Exception e) {
                    }
                }
            }
        });
    }

    public synchronized void a(da daVar) {
        if (daVar != null) {
            daVar.a(c());
        }
    }

    public synchronized void a(ct ctVar) {
        if (ctVar != null) {
            ctVar.a(g());
        }
    }

    synchronized cz c() {
        cz czVar;
        if (this.e.c() || this.e.d()) {
            cz czVar2 = new cz(d(), e(), f());
            if (czVar2.b().a() == null && !this.e.c()) {
                czVar2.b().a(((cz) this.e.b()).b().a());
            }
            this.e.a(czVar2);
            czVar = czVar2;
        } else {
            czVar = (cz) this.e.b();
        }
        return czVar;
    }

    private synchronized cs[] g() {
        cs[] csVarArr;
        if (this.f.c() || this.f.d()) {
            List arrayList = new ArrayList();
            if (bg.a(17) && ah.a(this.h, "android.permission.ACCESS_COARSE_LOCATION")) {
                Collection allCellInfo = this.b.getAllCellInfo();
                if (!bg.a(allCellInfo)) {
                    for (int i = 0; i < allCellInfo.size(); i++) {
                        Object obj;
                        CellInfo cellInfo = (CellInfo) allCellInfo.get(i);
                        b cVar = cellInfo instanceof CellInfoGsm ? new c() : cellInfo instanceof CellInfoCdma ? new a() : cellInfo instanceof CellInfoLte ? new d() : (bg.a(18) && (cellInfo instanceof CellInfoWcdma)) ? new e() : null;
                        if (cVar == null) {
                            obj = null;
                        } else {
                            obj = cVar.a(cellInfo);
                        }
                        if (obj != null) {
                            arrayList.add(obj);
                        }
                    }
                }
            }
            csVarArr = arrayList.size() <= 0 ? new cs[]{c().b()} : (cs[]) arrayList.toArray(new cs[arrayList.size()]);
            this.f.a(csVarArr);
        } else {
            csVarArr = (cs[]) this.f.b();
        }
        return csVarArr;
    }

    private synchronized void a(SignalStrength signalStrength) {
        if (!(this.e.c() || this.e.d())) {
            int gsmSignalStrength;
            cs b = ((cz) this.e.b()).b();
            if (signalStrength.isGsm()) {
                gsmSignalStrength = signalStrength.getGsmSignalStrength();
                gsmSignalStrength = 99 == gsmSignalStrength ? -1 : (gsmSignalStrength * 2) + BranchError.ERR_BRANCH_NO_CONNECTIVITY_STATUS;
            } else {
                gsmSignalStrength = signalStrength.getCdmaDbm();
                int evdoDbm = signalStrength.getEvdoDbm();
                if (-120 != evdoDbm) {
                    gsmSignalStrength = -120 == gsmSignalStrength ? evdoDbm : Math.min(gsmSignalStrength, evdoDbm);
                }
            }
            b.a(Integer.valueOf(gsmSignalStrength));
        }
    }

    private Integer h() {
        Integer num = null;
        try {
            Object substring = this.b.getNetworkOperator().substring(0, 3);
            if (!TextUtils.isEmpty(substring)) {
                num = Integer.valueOf(Integer.parseInt(substring));
            }
        } catch (Exception e) {
        }
        return num;
    }

    private Integer i() {
        Integer num = null;
        try {
            Object substring = this.b.getNetworkOperator().substring(3);
            if (!TextUtils.isEmpty(substring)) {
                num = Integer.valueOf(Integer.parseInt(substring));
            }
        } catch (Exception e) {
        }
        return num;
    }

    private Integer j() {
        Integer num = null;
        try {
            Object substring = this.b.getSimOperator().substring(0, 3);
            if (!TextUtils.isEmpty(substring)) {
                num = Integer.valueOf(Integer.parseInt(substring));
            }
        } catch (Exception e) {
        }
        return num;
    }

    private Integer k() {
        Integer num = null;
        try {
            Object substring = this.b.getSimOperator().substring(3);
            if (!TextUtils.isEmpty(substring)) {
                num = Integer.valueOf(Integer.parseInt(substring));
            }
        } catch (Exception e) {
        }
        return num;
    }

    private Integer l() {
        try {
            int cid = ((GsmCellLocation) this.b.getCellLocation()).getCid();
            return -1 != cid ? Integer.valueOf(cid) : null;
        } catch (Exception e) {
            return null;
        }
    }

    private Integer m() {
        try {
            int lac = ((GsmCellLocation) this.b.getCellLocation()).getLac();
            return -1 != lac ? Integer.valueOf(lac) : null;
        } catch (Exception e) {
            return null;
        }
    }

    private String n() {
        String str = "unknown";
        try {
            return (String) a.get(this.b.getNetworkType(), str);
        } catch (Exception e) {
            return str;
        }
    }

    private String o() {
        String str = null;
        try {
            if (ah.a(this.h, "android.permission.READ_PHONE_STATE")) {
                str = this.b.getDeviceId();
            }
        } catch (Exception e) {
        }
        return str;
    }

    private List<String> p() {
        Collection hashSet = new HashSet();
        try {
            if (ah.a(this.h, "android.permission.READ_PHONE_STATE")) {
                for (int i = 0; i < 10; i++) {
                    String deviceId = this.b.getDeviceId(i);
                    if (deviceId != null) {
                        hashSet.add(deviceId);
                    }
                }
            }
        } catch (Exception e) {
        }
        return new ArrayList(hashSet);
    }

    private boolean q() {
        if (ah.a(this.h, "android.permission.READ_PHONE_STATE")) {
            try {
                return this.b.isNetworkRoaming();
            } catch (Exception e) {
            }
        }
        return false;
    }

    cs d() {
        return new cs(h(), i(), m(), l(), this.b.getNetworkOperatorName(), n(), null, true, 0, null);
    }

    List<cx> e() {
        List<cx> arrayList = new ArrayList();
        if (bg.a(23)) {
            arrayList.addAll(s());
            if (arrayList.size() == 0) {
                arrayList.add(r());
            }
        } else {
            arrayList.add(r());
        }
        return arrayList;
    }

    List<String> f() {
        List<String> arrayList = new ArrayList();
        if (bg.a(23)) {
            arrayList.addAll(p());
        } else {
            arrayList.add(o());
        }
        return arrayList;
    }

    private cx r() {
        return new cx(j(), k(), q(), this.b.getSimOperatorName(), null);
    }

    private List<cx> s() {
        List<cx> arrayList = new ArrayList();
        if (ah.a(this.h, "android.permission.READ_PHONE_STATE")) {
            try {
                List<SubscriptionInfo> activeSubscriptionInfoList = SubscriptionManager.from(this.h).getActiveSubscriptionInfoList();
                if (activeSubscriptionInfoList != null) {
                    for (SubscriptionInfo cxVar : activeSubscriptionInfoList) {
                        arrayList.add(new cx(cxVar));
                    }
                }
            } catch (Exception e) {
            }
        }
        return arrayList;
    }
}

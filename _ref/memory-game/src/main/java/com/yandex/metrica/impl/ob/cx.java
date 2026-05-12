package com.yandex.metrica.impl.ob;

import android.telephony.SubscriptionInfo;

public final class cx {
    private final Integer a;
    private final Integer b;
    private final boolean c;
    private final String d;
    private final String e;

    public cx(Integer num, Integer num2, boolean z, String str, String str2) {
        this.a = num;
        this.b = num2;
        this.c = z;
        this.d = str;
        this.e = str2;
    }

    public cx(SubscriptionInfo subscriptionInfo) {
        boolean z = true;
        this.a = Integer.valueOf(subscriptionInfo.getMcc());
        this.b = Integer.valueOf(subscriptionInfo.getMnc());
        if (subscriptionInfo.getDataRoaming() != 1) {
            z = false;
        }
        this.c = z;
        this.d = subscriptionInfo.getCarrierName().toString();
        this.e = subscriptionInfo.getIccId();
    }

    public Integer a() {
        return this.a;
    }

    public Integer b() {
        return this.b;
    }

    public boolean c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public String e() {
        return this.e;
    }
}

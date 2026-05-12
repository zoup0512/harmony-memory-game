package com.google.android.gms.ads.search;

import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.ads.internal.client.zzad;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;

public final class DynamicHeightSearchAdRequest {
    private final SearchAdRequest zzcqy;

    private DynamicHeightSearchAdRequest(Builder builder) {
        this.zzcqy = Builder.zza(builder).build();
    }

    public <T extends CustomEvent> Bundle getCustomEventExtrasBundle(Class<T> cls) {
        return this.zzcqy.getCustomEventExtrasBundle(cls);
    }

    @Deprecated
    public <T extends NetworkExtras> T getNetworkExtras(Class<T> cls) {
        return this.zzcqy.getNetworkExtras(cls);
    }

    public <T extends MediationAdapter> Bundle getNetworkExtrasBundle(Class<T> cls) {
        return this.zzcqy.getNetworkExtrasBundle(cls);
    }

    public String getQuery() {
        return this.zzcqy.getQuery();
    }

    public boolean isTestDevice(Context context) {
        return this.zzcqy.isTestDevice(context);
    }

    zzad zzdc() {
        return this.zzcqy.zzdc();
    }
}

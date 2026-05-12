package com.google.android.gms.ads;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.ads.internal.client.zzad;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import java.util.Date;
import java.util.Set;

public final class AdRequest {
    public static final String DEVICE_ID_EMULATOR = zzad.DEVICE_ID_EMULATOR;
    public static final int ERROR_CODE_INTERNAL_ERROR = 0;
    public static final int ERROR_CODE_INVALID_REQUEST = 1;
    public static final int ERROR_CODE_NETWORK_ERROR = 2;
    public static final int ERROR_CODE_NO_FILL = 3;
    public static final int GENDER_FEMALE = 2;
    public static final int GENDER_MALE = 1;
    public static final int GENDER_UNKNOWN = 0;
    public static final int MAX_CONTENT_URL_LENGTH = 512;
    private final zzad zzaic;

    private AdRequest(Builder builder) {
        this.zzaic = new zzad(Builder.zza(builder));
    }

    public Date getBirthday() {
        return this.zzaic.getBirthday();
    }

    public String getContentUrl() {
        return this.zzaic.getContentUrl();
    }

    public <T extends CustomEvent> Bundle getCustomEventExtrasBundle(Class<T> cls) {
        return this.zzaic.getCustomEventExtrasBundle(cls);
    }

    public int getGender() {
        return this.zzaic.getGender();
    }

    public Set<String> getKeywords() {
        return this.zzaic.getKeywords();
    }

    public Location getLocation() {
        return this.zzaic.getLocation();
    }

    @Deprecated
    public <T extends NetworkExtras> T getNetworkExtras(Class<T> cls) {
        return this.zzaic.getNetworkExtras(cls);
    }

    public <T extends MediationAdapter> Bundle getNetworkExtrasBundle(Class<T> cls) {
        return this.zzaic.getNetworkExtrasBundle(cls);
    }

    public boolean isTestDevice(Context context) {
        return this.zzaic.isTestDevice(context);
    }

    public zzad zzdc() {
        return this.zzaic;
    }
}

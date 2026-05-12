package com.google.android.gms.ads.search;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.ads.internal.client.zzad;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;

public final class SearchAdRequest {
    public static final int BORDER_TYPE_DASHED = 1;
    public static final int BORDER_TYPE_DOTTED = 2;
    public static final int BORDER_TYPE_NONE = 0;
    public static final int BORDER_TYPE_SOLID = 3;
    public static final int CALL_BUTTON_COLOR_DARK = 2;
    public static final int CALL_BUTTON_COLOR_LIGHT = 0;
    public static final int CALL_BUTTON_COLOR_MEDIUM = 1;
    public static final String DEVICE_ID_EMULATOR = zzad.DEVICE_ID_EMULATOR;
    public static final int ERROR_CODE_INTERNAL_ERROR = 0;
    public static final int ERROR_CODE_INVALID_REQUEST = 1;
    public static final int ERROR_CODE_NETWORK_ERROR = 2;
    public static final int ERROR_CODE_NO_FILL = 3;
    private final int mBackgroundColor;
    private final zzad zzaic;
    private final String zzanr;
    private final int zzcrb;
    private final int zzcrc;
    private final int zzcrd;
    private final int zzcre;
    private final int zzcrf;
    private final int zzcrg;
    private final int zzcrh;
    private final String zzcri;
    private final int zzcrj;
    private final String zzcrk;
    private final int zzcrl;
    private final int zzcrm;

    private SearchAdRequest(Builder builder) {
        this.zzcrb = Builder.zza(builder);
        this.mBackgroundColor = Builder.zzb(builder);
        this.zzcrc = Builder.zzc(builder);
        this.zzcrd = Builder.zzd(builder);
        this.zzcre = Builder.zze(builder);
        this.zzcrf = Builder.zzf(builder);
        this.zzcrg = Builder.zzg(builder);
        this.zzcrh = Builder.zzh(builder);
        this.zzcri = Builder.zzi(builder);
        this.zzcrj = Builder.zzj(builder);
        this.zzcrk = Builder.zzk(builder);
        this.zzcrl = Builder.zzl(builder);
        this.zzcrm = Builder.zzm(builder);
        this.zzanr = Builder.zzn(builder);
        this.zzaic = new zzad(Builder.zzo(builder), this);
    }

    public int getAnchorTextColor() {
        return this.zzcrb;
    }

    public int getBackgroundColor() {
        return this.mBackgroundColor;
    }

    public int getBackgroundGradientBottom() {
        return this.zzcrc;
    }

    public int getBackgroundGradientTop() {
        return this.zzcrd;
    }

    public int getBorderColor() {
        return this.zzcre;
    }

    public int getBorderThickness() {
        return this.zzcrf;
    }

    public int getBorderType() {
        return this.zzcrg;
    }

    public int getCallButtonColor() {
        return this.zzcrh;
    }

    public String getCustomChannels() {
        return this.zzcri;
    }

    public <T extends CustomEvent> Bundle getCustomEventExtrasBundle(Class<T> cls) {
        return this.zzaic.getCustomEventExtrasBundle(cls);
    }

    public int getDescriptionTextColor() {
        return this.zzcrj;
    }

    public String getFontFace() {
        return this.zzcrk;
    }

    public int getHeaderTextColor() {
        return this.zzcrl;
    }

    public int getHeaderTextSize() {
        return this.zzcrm;
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

    public String getQuery() {
        return this.zzanr;
    }

    public boolean isTestDevice(Context context) {
        return this.zzaic.isTestDevice(context);
    }

    zzad zzdc() {
        return this.zzaic;
    }
}

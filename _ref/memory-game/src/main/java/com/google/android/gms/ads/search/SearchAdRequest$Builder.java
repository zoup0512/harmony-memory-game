package com.google.android.gms.ads.search;

import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.ads.internal.client.zzad.zza;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;

public final class SearchAdRequest$Builder {
    private int mBackgroundColor;
    private final zza zzaid = new zza();
    private String zzanr;
    private int zzcrb;
    private int zzcrc;
    private int zzcrd;
    private int zzcre;
    private int zzcrf;
    private int zzcrg = 0;
    private int zzcrh;
    private String zzcri;
    private int zzcrj;
    private String zzcrk;
    private int zzcrl;
    private int zzcrm;

    public SearchAdRequest$Builder addCustomEventExtrasBundle(Class<? extends CustomEvent> cls, Bundle bundle) {
        this.zzaid.zzb(cls, bundle);
        return this;
    }

    public SearchAdRequest$Builder addNetworkExtras(NetworkExtras networkExtras) {
        this.zzaid.zza(networkExtras);
        return this;
    }

    public SearchAdRequest$Builder addNetworkExtrasBundle(Class<? extends MediationAdapter> cls, Bundle bundle) {
        this.zzaid.zza(cls, bundle);
        return this;
    }

    public SearchAdRequest$Builder addTestDevice(String str) {
        this.zzaid.zzag(str);
        return this;
    }

    public SearchAdRequest build() {
        return new SearchAdRequest(this, null);
    }

    public SearchAdRequest$Builder setAnchorTextColor(int i) {
        this.zzcrb = i;
        return this;
    }

    public SearchAdRequest$Builder setBackgroundColor(int i) {
        this.mBackgroundColor = i;
        this.zzcrc = Color.argb(0, 0, 0, 0);
        this.zzcrd = Color.argb(0, 0, 0, 0);
        return this;
    }

    public SearchAdRequest$Builder setBackgroundGradient(int i, int i2) {
        this.mBackgroundColor = Color.argb(0, 0, 0, 0);
        this.zzcrc = i2;
        this.zzcrd = i;
        return this;
    }

    public SearchAdRequest$Builder setBorderColor(int i) {
        this.zzcre = i;
        return this;
    }

    public SearchAdRequest$Builder setBorderThickness(int i) {
        this.zzcrf = i;
        return this;
    }

    public SearchAdRequest$Builder setBorderType(int i) {
        this.zzcrg = i;
        return this;
    }

    public SearchAdRequest$Builder setCallButtonColor(int i) {
        this.zzcrh = i;
        return this;
    }

    public SearchAdRequest$Builder setCustomChannels(String str) {
        this.zzcri = str;
        return this;
    }

    public SearchAdRequest$Builder setDescriptionTextColor(int i) {
        this.zzcrj = i;
        return this;
    }

    public SearchAdRequest$Builder setFontFace(String str) {
        this.zzcrk = str;
        return this;
    }

    public SearchAdRequest$Builder setHeaderTextColor(int i) {
        this.zzcrl = i;
        return this;
    }

    public SearchAdRequest$Builder setHeaderTextSize(int i) {
        this.zzcrm = i;
        return this;
    }

    public SearchAdRequest$Builder setLocation(Location location) {
        this.zzaid.zzb(location);
        return this;
    }

    public SearchAdRequest$Builder setQuery(String str) {
        this.zzanr = str;
        return this;
    }

    public SearchAdRequest$Builder setRequestAgent(String str) {
        this.zzaid.zzak(str);
        return this;
    }

    public SearchAdRequest$Builder tagForChildDirectedTreatment(boolean z) {
        this.zzaid.zzn(z);
        return this;
    }
}

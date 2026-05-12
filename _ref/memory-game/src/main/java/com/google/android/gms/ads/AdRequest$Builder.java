package com.google.android.gms.ads;

import android.location.Location;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.internal.client.zzad.zza;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.common.internal.zzab;
import java.util.Date;

public final class AdRequest$Builder {
    private final zza zzaid = new zza();

    public AdRequest$Builder() {
        this.zzaid.zzag(AdRequest.DEVICE_ID_EMULATOR);
    }

    public AdRequest$Builder addCustomEventExtrasBundle(Class<? extends CustomEvent> cls, Bundle bundle) {
        this.zzaid.zzb(cls, bundle);
        return this;
    }

    public AdRequest$Builder addKeyword(String str) {
        this.zzaid.zzaf(str);
        return this;
    }

    public AdRequest$Builder addNetworkExtras(NetworkExtras networkExtras) {
        this.zzaid.zza(networkExtras);
        return this;
    }

    public AdRequest$Builder addNetworkExtrasBundle(Class<? extends MediationAdapter> cls, Bundle bundle) {
        this.zzaid.zza(cls, bundle);
        if (cls.equals(AdMobAdapter.class) && bundle.getBoolean("_emulatorLiveAds")) {
            this.zzaid.zzah(AdRequest.DEVICE_ID_EMULATOR);
        }
        return this;
    }

    public AdRequest$Builder addTestDevice(String str) {
        this.zzaid.zzag(str);
        return this;
    }

    public AdRequest build() {
        return new AdRequest(this, null);
    }

    public AdRequest$Builder setBirthday(Date date) {
        this.zzaid.zza(date);
        return this;
    }

    public AdRequest$Builder setContentUrl(String str) {
        zzab.zzb((Object) str, (Object) "Content URL must be non-null.");
        zzab.zzh(str, "Content URL must be non-empty.");
        zzab.zzb(str.length() <= 512, "Content URL must not exceed %d in length.  Provided length was %d.", Integer.valueOf(512), Integer.valueOf(str.length()));
        this.zzaid.zzai(str);
        return this;
    }

    public AdRequest$Builder setGender(int i) {
        this.zzaid.zzt(i);
        return this;
    }

    public AdRequest$Builder setIsDesignedForFamilies(boolean z) {
        this.zzaid.zzo(z);
        return this;
    }

    public AdRequest$Builder setLocation(Location location) {
        this.zzaid.zzb(location);
        return this;
    }

    public AdRequest$Builder setRequestAgent(String str) {
        this.zzaid.zzak(str);
        return this;
    }

    public AdRequest$Builder tagForChildDirectedTreatment(boolean z) {
        this.zzaid.zzn(z);
        return this;
    }
}

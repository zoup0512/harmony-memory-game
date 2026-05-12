package com.google.android.gms.ads.doubleclick;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.android.gms.ads.internal.client.zzad;
import com.google.android.gms.ads.internal.client.zzad.zza;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzy;
import java.util.Date;
import java.util.List;
import java.util.Set;

public final class PublisherAdRequest {
    public static final String DEVICE_ID_EMULATOR = zzad.DEVICE_ID_EMULATOR;
    public static final int ERROR_CODE_INTERNAL_ERROR = 0;
    public static final int ERROR_CODE_INVALID_REQUEST = 1;
    public static final int ERROR_CODE_NETWORK_ERROR = 2;
    public static final int ERROR_CODE_NO_FILL = 3;
    public static final int GENDER_FEMALE = 2;
    public static final int GENDER_MALE = 1;
    public static final int GENDER_UNKNOWN = 0;
    private final zzad zzaic;

    public static final class Builder {
        private final zza zzaid = new zza();

        public Builder addCategoryExclusion(String str) {
            this.zzaid.zzal(str);
            return this;
        }

        public Builder addCustomEventExtrasBundle(Class<? extends CustomEvent> cls, Bundle bundle) {
            this.zzaid.zzb(cls, bundle);
            return this;
        }

        public Builder addCustomTargeting(String str, String str2) {
            this.zzaid.zzf(str, str2);
            return this;
        }

        public Builder addCustomTargeting(String str, List<String> list) {
            if (list != null) {
                this.zzaid.zzf(str, zzy.zzhq(",").zza(list));
            }
            return this;
        }

        public Builder addKeyword(String str) {
            this.zzaid.zzaf(str);
            return this;
        }

        public Builder addNetworkExtras(NetworkExtras networkExtras) {
            this.zzaid.zza(networkExtras);
            return this;
        }

        public Builder addNetworkExtrasBundle(Class<? extends MediationAdapter> cls, Bundle bundle) {
            this.zzaid.zza(cls, bundle);
            return this;
        }

        public Builder addTestDevice(String str) {
            this.zzaid.zzag(str);
            return this;
        }

        public PublisherAdRequest build() {
            return new PublisherAdRequest();
        }

        public Builder setBirthday(Date date) {
            this.zzaid.zza(date);
            return this;
        }

        public Builder setContentUrl(String str) {
            zzab.zzb(str, "Content URL must be non-null.");
            zzab.zzh(str, "Content URL must be non-empty.");
            zzab.zzb(str.length() <= 512, "Content URL must not exceed %d in length.  Provided length was %d.", new Object[]{Integer.valueOf(512), Integer.valueOf(str.length())});
            this.zzaid.zzai(str);
            return this;
        }

        public Builder setGender(int i) {
            this.zzaid.zzt(i);
            return this;
        }

        public Builder setIsDesignedForFamilies(boolean z) {
            this.zzaid.zzo(z);
            return this;
        }

        public Builder setLocation(Location location) {
            this.zzaid.zzb(location);
            return this;
        }

        @Deprecated
        public Builder setManualImpressionsEnabled(boolean z) {
            this.zzaid.setManualImpressionsEnabled(z);
            return this;
        }

        public Builder setPublisherProvidedId(String str) {
            this.zzaid.zzaj(str);
            return this;
        }

        public Builder setRequestAgent(String str) {
            this.zzaid.zzak(str);
            return this;
        }

        public Builder tagForChildDirectedTreatment(boolean z) {
            this.zzaid.zzn(z);
            return this;
        }
    }

    private PublisherAdRequest(Builder builder) {
        this.zzaic = new zzad(builder.zzaid);
    }

    public static void updateCorrelator() {
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

    public Bundle getCustomTargeting() {
        return this.zzaic.getCustomTargeting();
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

    public boolean getManualImpressionsEnabled() {
        return this.zzaic.getManualImpressionsEnabled();
    }

    @Deprecated
    public <T extends NetworkExtras> T getNetworkExtras(Class<T> cls) {
        return this.zzaic.getNetworkExtras(cls);
    }

    public <T extends MediationAdapter> Bundle getNetworkExtrasBundle(Class<T> cls) {
        return this.zzaic.getNetworkExtrasBundle(cls);
    }

    public String getPublisherProvidedId() {
        return this.zzaic.getPublisherProvidedId();
    }

    public boolean isTestDevice(Context context) {
        return this.zzaic.isTestDevice(context);
    }

    public zzad zzdc() {
        return this.zzaic;
    }
}

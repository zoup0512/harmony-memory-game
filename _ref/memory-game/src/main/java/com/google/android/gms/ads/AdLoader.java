package com.google.android.gms.ads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeAppInstallAd.OnAppInstallAdLoadedListener;
import com.google.android.gms.ads.formats.NativeContentAd.OnContentAdLoadedListener;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd.OnCustomClickListener;
import com.google.android.gms.ads.formats.NativeCustomTemplateAd.OnCustomTemplateAdLoadedListener;
import com.google.android.gms.ads.internal.client.zzad;
import com.google.android.gms.ads.internal.client.zzc;
import com.google.android.gms.ads.internal.client.zzh;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.ads.internal.client.zzr;
import com.google.android.gms.ads.internal.client.zzs;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.internal.zzeg;
import com.google.android.gms.internal.zzeh;
import com.google.android.gms.internal.zzei;
import com.google.android.gms.internal.zzej;
import com.google.android.gms.internal.zzgi;

public class AdLoader {
    private final Context mContext;
    private final zzh zzahz;
    private final zzr zzaia;

    public static class Builder {
        private final Context mContext;
        private final zzs zzaib;

        Builder(Context context, zzs com_google_android_gms_ads_internal_client_zzs) {
            this.mContext = context;
            this.zzaib = com_google_android_gms_ads_internal_client_zzs;
        }

        public Builder(Context context, String str) {
            this((Context) zzab.zzb((Object) context, (Object) "context cannot be null"), zzm.zzix().zzb(context, str, new zzgi()));
        }

        public AdLoader build() {
            try {
                return new AdLoader(this.mContext, this.zzaib.zzes());
            } catch (Throwable e) {
                zzb.zzb("Failed to build AdLoader.", e);
                return null;
            }
        }

        public Builder forAppInstallAd(OnAppInstallAdLoadedListener onAppInstallAdLoadedListener) {
            try {
                this.zzaib.zza(new zzeg(onAppInstallAdLoadedListener));
            } catch (Throwable e) {
                zzb.zzd("Failed to add app install ad listener", e);
            }
            return this;
        }

        public Builder forContentAd(OnContentAdLoadedListener onContentAdLoadedListener) {
            try {
                this.zzaib.zza(new zzeh(onContentAdLoadedListener));
            } catch (Throwable e) {
                zzb.zzd("Failed to add content ad listener", e);
            }
            return this;
        }

        public Builder forCustomTemplateAd(String str, OnCustomTemplateAdLoadedListener onCustomTemplateAdLoadedListener, OnCustomClickListener onCustomClickListener) {
            try {
                this.zzaib.zza(str, new zzej(onCustomTemplateAdLoadedListener), onCustomClickListener == null ? null : new zzei(onCustomClickListener));
            } catch (Throwable e) {
                zzb.zzd("Failed to add custom template ad listener", e);
            }
            return this;
        }

        public Builder withAdListener(AdListener adListener) {
            try {
                this.zzaib.zzb(new zzc(adListener));
            } catch (Throwable e) {
                zzb.zzd("Failed to set AdListener.", e);
            }
            return this;
        }

        public Builder withCorrelator(@NonNull Correlator correlator) {
            zzab.zzy(correlator);
            try {
                this.zzaib.zzb(correlator.zzdd());
            } catch (Throwable e) {
                zzb.zzd("Failed to set correlator.", e);
            }
            return this;
        }

        public Builder withNativeAdOptions(NativeAdOptions nativeAdOptions) {
            try {
                this.zzaib.zza(new NativeAdOptionsParcel(nativeAdOptions));
            } catch (Throwable e) {
                zzb.zzd("Failed to specify native ad options", e);
            }
            return this;
        }
    }

    AdLoader(Context context, zzr com_google_android_gms_ads_internal_client_zzr) {
        this(context, com_google_android_gms_ads_internal_client_zzr, zzh.zzih());
    }

    AdLoader(Context context, zzr com_google_android_gms_ads_internal_client_zzr, zzh com_google_android_gms_ads_internal_client_zzh) {
        this.mContext = context;
        this.zzaia = com_google_android_gms_ads_internal_client_zzr;
        this.zzahz = com_google_android_gms_ads_internal_client_zzh;
    }

    private void zza(zzad com_google_android_gms_ads_internal_client_zzad) {
        try {
            this.zzaia.zzf(this.zzahz.zza(this.mContext, com_google_android_gms_ads_internal_client_zzad));
        } catch (Throwable e) {
            zzb.zzb("Failed to load ad.", e);
        }
    }

    public String getMediationAdapterClassName() {
        try {
            return this.zzaia.getMediationAdapterClassName();
        } catch (Throwable e) {
            zzb.zzd("Failed to get the mediation adapter class name.", e);
            return null;
        }
    }

    public boolean isLoading() {
        try {
            return this.zzaia.isLoading();
        } catch (Throwable e) {
            zzb.zzd("Failed to check if ad is loading.", e);
            return false;
        }
    }

    @RequiresPermission("android.permission.INTERNET")
    public void loadAd(AdRequest adRequest) {
        zza(adRequest.zzdc());
    }

    public void loadAd(PublisherAdRequest publisherAdRequest) {
        zza(publisherAdRequest.zzdc());
    }
}

package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.internal.reward.client.RewardedVideoAdRequestParcel;
import com.google.android.gms.ads.search.SearchAdRequest;
import com.google.android.gms.internal.zzin;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@zzin
public class zzh {
    public static final zzh zzauq = new zzh();

    protected zzh() {
    }

    public static zzh zzih() {
        return zzauq;
    }

    public AdRequestParcel zza(Context context, zzad com_google_android_gms_ads_internal_client_zzad) {
        Date birthday = com_google_android_gms_ads_internal_client_zzad.getBirthday();
        long time = birthday != null ? birthday.getTime() : -1;
        String contentUrl = com_google_android_gms_ads_internal_client_zzad.getContentUrl();
        int gender = com_google_android_gms_ads_internal_client_zzad.getGender();
        Collection keywords = com_google_android_gms_ads_internal_client_zzad.getKeywords();
        List unmodifiableList = !keywords.isEmpty() ? Collections.unmodifiableList(new ArrayList(keywords)) : null;
        boolean isTestDevice = com_google_android_gms_ads_internal_client_zzad.isTestDevice(context);
        int zzji = com_google_android_gms_ads_internal_client_zzad.zzji();
        Location location = com_google_android_gms_ads_internal_client_zzad.getLocation();
        Bundle networkExtrasBundle = com_google_android_gms_ads_internal_client_zzad.getNetworkExtrasBundle(AdMobAdapter.class);
        boolean manualImpressionsEnabled = com_google_android_gms_ads_internal_client_zzad.getManualImpressionsEnabled();
        String publisherProvidedId = com_google_android_gms_ads_internal_client_zzad.getPublisherProvidedId();
        SearchAdRequest zzjf = com_google_android_gms_ads_internal_client_zzad.zzjf();
        SearchAdRequestParcel searchAdRequestParcel = zzjf != null ? new SearchAdRequestParcel(zzjf) : null;
        String str = null;
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            str = zzm.zziw().zza(Thread.currentThread().getStackTrace(), applicationContext.getPackageName());
        }
        return new AdRequestParcel(7, time, networkExtrasBundle, gender, unmodifiableList, isTestDevice, zzji, manualImpressionsEnabled, publisherProvidedId, searchAdRequestParcel, location, contentUrl, com_google_android_gms_ads_internal_client_zzad.zzjh(), com_google_android_gms_ads_internal_client_zzad.getCustomTargeting(), Collections.unmodifiableList(new ArrayList(com_google_android_gms_ads_internal_client_zzad.zzjj())), com_google_android_gms_ads_internal_client_zzad.zzje(), str, com_google_android_gms_ads_internal_client_zzad.isDesignedForFamilies());
    }

    public RewardedVideoAdRequestParcel zza(Context context, zzad com_google_android_gms_ads_internal_client_zzad, String str) {
        return new RewardedVideoAdRequestParcel(zza(context, com_google_android_gms_ads_internal_client_zzad), str);
    }
}

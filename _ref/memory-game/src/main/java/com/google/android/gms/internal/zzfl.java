package com.google.android.gms.internal;

import android.os.Bundle;
import com.amazonaws.services.s3.internal.Constants;
import com.facebook.applinks.AppLinkData;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@zzin
class zzfl {
    private final Object[] mParams;

    zzfl(AdRequestParcel adRequestParcel, String str, int i) {
        this.mParams = zza(adRequestParcel, str, i);
    }

    private static Object[] zza(AdRequestParcel adRequestParcel, String str, int i) {
        Set hashSet = new HashSet(Arrays.asList(((String) zzdc.zzbag.get()).split(",")));
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        if (hashSet.contains("networkType")) {
            arrayList.add(Integer.valueOf(i));
        }
        if (hashSet.contains("birthday")) {
            arrayList.add(Long.valueOf(adRequestParcel.zzatm));
        }
        if (hashSet.contains(AppLinkData.ARGUMENTS_EXTRAS_KEY)) {
            arrayList.add(zzd(adRequestParcel.extras));
        }
        if (hashSet.contains("gender")) {
            arrayList.add(Integer.valueOf(adRequestParcel.zzatn));
        }
        if (hashSet.contains("keywords")) {
            if (adRequestParcel.zzato != null) {
                arrayList.add(adRequestParcel.zzato.toString());
            } else {
                arrayList.add(null);
            }
        }
        if (hashSet.contains("isTestDevice")) {
            arrayList.add(Boolean.valueOf(adRequestParcel.zzatp));
        }
        if (hashSet.contains("tagForChildDirectedTreatment")) {
            arrayList.add(Integer.valueOf(adRequestParcel.zzatq));
        }
        if (hashSet.contains("manualImpressionsEnabled")) {
            arrayList.add(Boolean.valueOf(adRequestParcel.zzatr));
        }
        if (hashSet.contains("publisherProvidedId")) {
            arrayList.add(adRequestParcel.zzats);
        }
        if (hashSet.contains("location")) {
            if (adRequestParcel.zzatu != null) {
                arrayList.add(adRequestParcel.zzatu.toString());
            } else {
                arrayList.add(null);
            }
        }
        if (hashSet.contains("contentUrl")) {
            arrayList.add(adRequestParcel.zzatv);
        }
        if (hashSet.contains("networkExtras")) {
            arrayList.add(zzd(adRequestParcel.zzatw));
        }
        if (hashSet.contains("customTargeting")) {
            arrayList.add(zzd(adRequestParcel.zzatx));
        }
        if (hashSet.contains("categoryExclusions")) {
            if (adRequestParcel.zzaty != null) {
                arrayList.add(adRequestParcel.zzaty.toString());
            } else {
                arrayList.add(null);
            }
        }
        if (hashSet.contains("requestAgent")) {
            arrayList.add(adRequestParcel.zzatz);
        }
        if (hashSet.contains("requestPackage")) {
            arrayList.add(adRequestParcel.zzaua);
        }
        return arrayList.toArray();
    }

    private static String zzd(Bundle bundle) {
        if (bundle == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        Collections.sort(new ArrayList(bundle.keySet()));
        for (String str : bundle.keySet()) {
            Object obj = bundle.get(str);
            String str2 = obj == null ? Constants.NULL_VERSION_ID : obj instanceof Bundle ? zzd((Bundle) obj) : obj.toString();
            stringBuilder.append(str2);
        }
        return stringBuilder.toString();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof zzfl)) {
            return false;
        }
        return Arrays.equals(this.mParams, ((zzfl) obj).mParams);
    }

    public int hashCode() {
        return Arrays.hashCode(this.mParams);
    }

    public String toString() {
        String valueOf = String.valueOf(Arrays.toString(this.mParams));
        return new StringBuilder(String.valueOf(valueOf).length() + 24).append("[InterstitialAdPoolKey ").append(valueOf).append("]").toString();
    }
}

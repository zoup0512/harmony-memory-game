package com.google.android.gms.internal;

import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.AdRequest.Gender;
import com.google.ads.AdSize;
import com.google.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.zza;
import java.util.Date;
import java.util.HashSet;

@zzin
public final class zzgx {
    public static int zza(ErrorCode errorCode) {
        switch (1.zzbpt[errorCode.ordinal()]) {
            case 2:
                return 1;
            case 3:
                return 2;
            case 4:
                return 3;
            default:
                return 0;
        }
    }

    public static Gender zzab(int i) {
        switch (i) {
            case 1:
                return Gender.MALE;
            case 2:
                return Gender.FEMALE;
            default:
                return Gender.UNKNOWN;
        }
    }

    public static AdSize zzc(AdSizeParcel adSizeParcel) {
        int i = 0;
        AdSize[] adSizeArr = new AdSize[]{AdSize.SMART_BANNER, AdSize.BANNER, AdSize.IAB_MRECT, AdSize.IAB_BANNER, AdSize.IAB_LEADERBOARD, AdSize.IAB_WIDE_SKYSCRAPER};
        while (i < 6) {
            if (adSizeArr[i].getWidth() == adSizeParcel.width && adSizeArr[i].getHeight() == adSizeParcel.height) {
                return adSizeArr[i];
            }
            i++;
        }
        return new AdSize(zza.zza(adSizeParcel.width, adSizeParcel.height, adSizeParcel.zzaur));
    }

    public static MediationAdRequest zzp(AdRequestParcel adRequestParcel) {
        return new MediationAdRequest(new Date(adRequestParcel.zzatm), zzab(adRequestParcel.zzatn), adRequestParcel.zzato != null ? new HashSet(adRequestParcel.zzato) : null, adRequestParcel.zzatp, adRequestParcel.zzatu);
    }
}

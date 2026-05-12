package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzaa.zza;
import com.google.android.gms.common.internal.zzab;

public class PlaceReport extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<PlaceReport> CREATOR = new zzi();
    private final String adn;
    private final String mTag;
    final int mVersionCode;
    private final String zzcup;

    PlaceReport(int i, String str, String str2, String str3) {
        this.mVersionCode = i;
        this.adn = str;
        this.mTag = str2;
        this.zzcup = str3;
    }

    public static PlaceReport create(String str, String str2) {
        return zzk(str, str2, "unknown");
    }

    public static PlaceReport zzk(String str, String str2, String str3) {
        zzab.zzy(str);
        zzab.zzhr(str2);
        zzab.zzhr(str3);
        zzab.zzb(zzkp(str3), (Object) "Invalid source");
        return new PlaceReport(1, str, str2, str3);
    }

    private static boolean zzkp(String str) {
        boolean z = true;
        switch (str.hashCode()) {
            case -1436706272:
                if (str.equals("inferredGeofencing")) {
                    z = true;
                    break;
                }
                break;
            case -1194968642:
                if (str.equals("userReported")) {
                    z = true;
                    break;
                }
                break;
            case -284840886:
                if (str.equals("unknown")) {
                    z = false;
                    break;
                }
                break;
            case -262743844:
                if (str.equals("inferredReverseGeocoding")) {
                    z = true;
                    break;
                }
                break;
            case 1164924125:
                if (str.equals("inferredSnappedToRoad")) {
                    z = true;
                    break;
                }
                break;
            case 1287171955:
                if (str.equals("inferredRadioSignals")) {
                    z = true;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
            case true:
            case true:
            case true:
            case true:
            case true:
                return true;
            default:
                return false;
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PlaceReport)) {
            return false;
        }
        PlaceReport placeReport = (PlaceReport) obj;
        return zzaa.equal(this.adn, placeReport.adn) && zzaa.equal(this.mTag, placeReport.mTag) && zzaa.equal(this.zzcup, placeReport.zzcup);
    }

    public String getPlaceId() {
        return this.adn;
    }

    public String getSource() {
        return this.zzcup;
    }

    public String getTag() {
        return this.mTag;
    }

    public int hashCode() {
        return zzaa.hashCode(this.adn, this.mTag, this.zzcup);
    }

    public String toString() {
        zza zzx = zzaa.zzx(this);
        zzx.zzg("placeId", this.adn);
        zzx.zzg("tag", this.mTag);
        if (!"unknown".equals(this.zzcup)) {
            zzx.zzg(ShareConstants.FEED_SOURCE_PARAM, this.zzcup);
        }
        return zzx.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzi.zza(this, parcel, i);
    }
}

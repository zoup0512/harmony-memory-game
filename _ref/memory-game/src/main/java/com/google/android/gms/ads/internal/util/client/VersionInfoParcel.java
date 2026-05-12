package com.google.android.gms.ads.internal.util.client;

import android.os.Parcel;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.internal.zzin;
import com.yalantis.ucrop.util.FileUtils;

@zzin
public final class VersionInfoParcel extends AbstractSafeParcelable {
    public static final zzd CREATOR = new zzd();
    public final int versionCode;
    public int zzcnk;
    public int zzcnl;
    public boolean zzcnm;
    public String zzcs;

    public VersionInfoParcel(int i, int i2, boolean z) {
        this(i, i2, z, false);
    }

    public VersionInfoParcel(int i, int i2, boolean z, boolean z2) {
        String valueOf = String.valueOf("afma-sdk-a-v");
        String str = z ? AppEventsConstants.EVENT_PARAM_VALUE_NO : z2 ? "2" : AppEventsConstants.EVENT_PARAM_VALUE_YES;
        this(1, new StringBuilder((String.valueOf(valueOf).length() + 24) + String.valueOf(str).length()).append(valueOf).append(i).append(FileUtils.HIDDEN_PREFIX).append(i2).append(FileUtils.HIDDEN_PREFIX).append(str).toString(), i, i2, z);
    }

    VersionInfoParcel(int i, String str, int i2, int i3, boolean z) {
        this.versionCode = i;
        this.zzcs = str;
        this.zzcnk = i2;
        this.zzcnl = i3;
        this.zzcnm = z;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzd.zza(this, parcel, i);
    }
}

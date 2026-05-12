package com.google.android.gms.ads.internal;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.request.AutoClickProtectionConfigurationParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzju.zza;

@zzin
public class zze {
    private final Context mContext;
    private final AutoClickProtectionConfigurationParcel zzakn;
    private boolean zzako;

    public zze(Context context) {
        this(context, false);
    }

    public zze(Context context, @Nullable zza com_google_android_gms_internal_zzju_zza) {
        this.mContext = context;
        if (com_google_android_gms_internal_zzju_zza == null || com_google_android_gms_internal_zzju_zza.zzciq.zzccr == null) {
            this.zzakn = new AutoClickProtectionConfigurationParcel();
        } else {
            this.zzakn = com_google_android_gms_internal_zzju_zza.zzciq.zzccr;
        }
    }

    public zze(Context context, boolean z) {
        this.mContext = context;
        this.zzakn = new AutoClickProtectionConfigurationParcel(z);
    }

    public void recordClick() {
        this.zzako = true;
    }

    public boolean zzel() {
        return !this.zzakn.zzccu || this.zzako;
    }

    public void zzt(@Nullable String str) {
        if (str == null) {
            str = "";
        }
        zzb.zzcw("Action was blocked because no touch was detected.");
        if (this.zzakn.zzccu && this.zzakn.zzccv != null) {
            for (String str2 : this.zzakn.zzccv) {
                if (!TextUtils.isEmpty(str2)) {
                    zzu.zzfq().zzc(this.mContext, "", str2.replace("{NAVIGATION_URL}", Uri.encode(str)));
                }
            }
        }
    }
}

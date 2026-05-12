package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import java.util.Map;

@zzin
public class zzhb {
    private final zzlh zzbgf;
    private final boolean zzbqs;
    private final String zzbqt;

    public zzhb(zzlh com_google_android_gms_internal_zzlh, Map<String, String> map) {
        this.zzbgf = com_google_android_gms_internal_zzlh;
        this.zzbqt = (String) map.get("forceOrientation");
        if (map.containsKey("allowOrientationChange")) {
            this.zzbqs = Boolean.parseBoolean((String) map.get("allowOrientationChange"));
        } else {
            this.zzbqs = true;
        }
    }

    public void execute() {
        if (this.zzbgf == null) {
            zzb.zzcx("AdWebView is null");
            return;
        }
        int zztk = DeviceInfo.ORIENTATION_PORTRAIT.equalsIgnoreCase(this.zzbqt) ? zzu.zzfs().zztk() : DeviceInfo.ORIENTATION_LANDSCAPE.equalsIgnoreCase(this.zzbqt) ? zzu.zzfs().zztj() : this.zzbqs ? -1 : zzu.zzfs().zztl();
        this.zzbgf.setRequestedOrientation(zztk);
    }
}

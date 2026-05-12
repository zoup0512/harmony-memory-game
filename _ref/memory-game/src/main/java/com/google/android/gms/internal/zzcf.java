package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import org.json.JSONObject;

@zzin
public final class zzcf {
    private final String zzarg;
    private final JSONObject zzarh;
    private final String zzari;
    private final String zzarj;
    private final boolean zzark;
    private final boolean zzarl;

    public zzcf(String str, VersionInfoParcel versionInfoParcel, String str2, JSONObject jSONObject, boolean z, boolean z2) {
        this.zzarj = versionInfoParcel.zzcs;
        this.zzarh = jSONObject;
        this.zzari = str;
        this.zzarg = str2;
        this.zzark = z;
        this.zzarl = z2;
    }

    public String zzhk() {
        return this.zzarg;
    }

    public String zzhl() {
        return this.zzarj;
    }

    public JSONObject zzhm() {
        return this.zzarh;
    }

    public String zzhn() {
        return this.zzari;
    }

    public boolean zzho() {
        return this.zzark;
    }

    public boolean zzhp() {
        return this.zzarl;
    }
}

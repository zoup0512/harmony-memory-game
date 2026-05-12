package com.google.android.gms.internal;

import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.ServerProtocol;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@zzin
class zziu {
    private String zzae;
    private final String zzbvq;
    private int zzbyi;
    private final List<String> zzcfu;
    private final List<String> zzcfv;
    private final String zzcfw;
    private final String zzcfx;
    private final String zzcfy;
    private final String zzcfz;
    private final boolean zzcga;
    private final boolean zzcgb;
    private final String zzcgc;

    public zziu(int i, Map<String, String> map) {
        this.zzae = (String) map.get("url");
        this.zzcfx = (String) map.get("base_uri");
        this.zzcfy = (String) map.get("post_parameters");
        this.zzcga = parseBoolean((String) map.get("drt_include"));
        this.zzcgb = parseBoolean((String) map.get("pan_include"));
        this.zzcfw = (String) map.get("activation_overlay_url");
        this.zzcfv = zzce((String) map.get("check_packages"));
        this.zzbvq = (String) map.get("request_id");
        this.zzcfz = (String) map.get("type");
        this.zzcfu = zzce((String) map.get("errors"));
        this.zzbyi = i;
        this.zzcgc = (String) map.get("fetched_ad");
    }

    private static boolean parseBoolean(String str) {
        return str != null && (str.equals(AppEventsConstants.EVENT_PARAM_VALUE_YES) || str.equals(ServerProtocol.DIALOG_RETURN_SCOPES_TRUE));
    }

    private List<String> zzce(String str) {
        return str == null ? null : Arrays.asList(str.split(","));
    }

    public int getErrorCode() {
        return this.zzbyi;
    }

    public String getRequestId() {
        return this.zzbvq;
    }

    public String getType() {
        return this.zzcfz;
    }

    public String getUrl() {
        return this.zzae;
    }

    public void setUrl(String str) {
        this.zzae = str;
    }

    public List<String> zzrj() {
        return this.zzcfu;
    }

    public String zzrk() {
        return this.zzcfy;
    }

    public boolean zzrl() {
        return this.zzcga;
    }

    public String zzrm() {
        return this.zzcgc;
    }
}

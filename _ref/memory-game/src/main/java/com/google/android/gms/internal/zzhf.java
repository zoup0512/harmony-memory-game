package com.google.android.gms.internal;

import com.facebook.internal.NativeProtocol;
import com.google.android.gms.ads.internal.util.client.zzb;
import org.json.JSONObject;

@zzin
public class zzhf {
    private final zzlh zzbgf;
    private final String zzbrm;

    public zzhf(zzlh com_google_android_gms_internal_zzlh) {
        this(com_google_android_gms_internal_zzlh, "");
    }

    public zzhf(zzlh com_google_android_gms_internal_zzlh, String str) {
        this.zzbgf = com_google_android_gms_internal_zzlh;
        this.zzbrm = str;
    }

    public void zza(int i, int i2, int i3, int i4, float f, int i5) {
        try {
            this.zzbgf.zzb("onScreenInfoChanged", new JSONObject().put("width", i).put("height", i2).put("maxSizeWidth", i3).put("maxSizeHeight", i4).put("density", (double) f).put("rotation", i5));
        } catch (Throwable e) {
            zzb.zzb("Error occured while obtaining screen information.", e);
        }
    }

    public void zzb(int i, int i2, int i3, int i4) {
        try {
            this.zzbgf.zzb("onSizeChanged", new JSONObject().put("x", i).put("y", i2).put("width", i3).put("height", i4));
        } catch (Throwable e) {
            zzb.zzb("Error occured while dispatching size change.", e);
        }
    }

    public void zzbt(String str) {
        try {
            this.zzbgf.zzb("onError", new JSONObject().put("message", str).put(NativeProtocol.WEB_DIALOG_ACTION, this.zzbrm));
        } catch (Throwable e) {
            zzb.zzb("Error occurred while dispatching error event.", e);
        }
    }

    public void zzbu(String str) {
        try {
            this.zzbgf.zzb("onReadyEventReceived", new JSONObject().put("js", str));
        } catch (Throwable e) {
            zzb.zzb("Error occured while dispatching ready Event.", e);
        }
    }

    public void zzbv(String str) {
        try {
            this.zzbgf.zzb("onStateChanged", new JSONObject().put("state", str));
        } catch (Throwable e) {
            zzb.zzb("Error occured while dispatching state change.", e);
        }
    }

    public void zzc(int i, int i2, int i3, int i4) {
        try {
            this.zzbgf.zzb("onDefaultPositionReceived", new JSONObject().put("x", i).put("y", i2).put("width", i3).put("height", i4));
        } catch (Throwable e) {
            zzb.zzb("Error occured while dispatching default position.", e);
        }
    }
}

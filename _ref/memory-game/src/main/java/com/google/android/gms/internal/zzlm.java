package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import com.facebook.appevents.AppEventsConstants;
import com.facebook.internal.NativeProtocol;
import com.google.android.gms.ads.internal.client.zzab.zza;
import com.google.android.gms.ads.internal.client.zzac;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.common.util.zzf;
import java.util.HashMap;
import java.util.Map;

@zzin
public class zzlm extends zza {
    private final Object zzail = new Object();
    private boolean zzaio = true;
    private final zzlh zzbgf;
    private final float zzcpy;
    private int zzcpz;
    private zzac zzcqa;
    private boolean zzcqb;
    private boolean zzcqc;
    private float zzcqd;

    public zzlm(zzlh com_google_android_gms_internal_zzlh, float f) {
        this.zzbgf = com_google_android_gms_internal_zzlh;
        this.zzcpy = f;
    }

    private void zzd(String str, @Nullable Map<String, String> map) {
        Map hashMap = map == null ? new HashMap() : new HashMap(map);
        hashMap.put(NativeProtocol.WEB_DIALOG_ACTION, str);
        zzu.zzfq().runOnUiThread(new 1(this, hashMap));
    }

    private void zzdd(String str) {
        zzd(str, null);
    }

    private void zzi(int i, int i2) {
        zzu.zzfq().runOnUiThread(new 2(this, i, i2));
    }

    public int getPlaybackState() {
        int i;
        synchronized (this.zzail) {
            i = this.zzcpz;
        }
        return i;
    }

    public boolean isMuted() {
        boolean z;
        synchronized (this.zzail) {
            z = this.zzcqc;
        }
        return z;
    }

    public void pause() {
        zzdd("pause");
    }

    public void play() {
        zzdd("play");
    }

    public void zza(float f, int i, boolean z) {
        int i2;
        synchronized (this.zzail) {
            this.zzcqd = f;
            this.zzcqc = z;
            i2 = this.zzcpz;
            this.zzcpz = i;
        }
        zzi(i2, i);
    }

    public void zza(zzac com_google_android_gms_ads_internal_client_zzac) {
        synchronized (this.zzail) {
            this.zzcqa = com_google_android_gms_ads_internal_client_zzac;
        }
    }

    public void zzam(boolean z) {
        synchronized (this.zzail) {
            this.zzaio = z;
        }
        zzd("initialState", zzf.zze("muteStart", z ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO));
    }

    public float zziz() {
        return this.zzcpy;
    }

    public float zzja() {
        float f;
        synchronized (this.zzail) {
            f = this.zzcqd;
        }
        return f;
    }

    public void zzm(boolean z) {
        zzdd(z ? "mute" : "unmute");
    }
}

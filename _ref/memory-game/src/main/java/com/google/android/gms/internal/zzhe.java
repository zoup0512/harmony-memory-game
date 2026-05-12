package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.internal.zzhd.zza;
import java.util.Map;

@zzin
public class zzhe extends zzhf implements zzep {
    private final Context mContext;
    private final WindowManager zzaqm;
    private final zzlh zzbgf;
    private final zzcu zzbrc;
    DisplayMetrics zzbrd;
    private float zzbre;
    int zzbrf = -1;
    int zzbrg = -1;
    private int zzbrh;
    int zzbri = -1;
    int zzbrj = -1;
    int zzbrk = -1;
    int zzbrl = -1;

    public zzhe(zzlh com_google_android_gms_internal_zzlh, Context context, zzcu com_google_android_gms_internal_zzcu) {
        super(com_google_android_gms_internal_zzlh);
        this.zzbgf = com_google_android_gms_internal_zzlh;
        this.mContext = context;
        this.zzbrc = com_google_android_gms_internal_zzcu;
        this.zzaqm = (WindowManager) context.getSystemService("window");
    }

    private void zzmz() {
        this.zzbrd = new DisplayMetrics();
        Display defaultDisplay = this.zzaqm.getDefaultDisplay();
        defaultDisplay.getMetrics(this.zzbrd);
        this.zzbre = this.zzbrd.density;
        this.zzbrh = defaultDisplay.getRotation();
    }

    private void zzne() {
        int[] iArr = new int[2];
        this.zzbgf.getLocationOnScreen(iArr);
        zze(zzm.zziw().zzb(this.mContext, iArr[0]), zzm.zziw().zzb(this.mContext, iArr[1]));
    }

    private zzhd zznh() {
        return new zza().zzu(this.zzbrc.zzjp()).zzt(this.zzbrc.zzjq()).zzv(this.zzbrc.zzju()).zzw(this.zzbrc.zzjr()).zzx(this.zzbrc.zzjs()).zzmy();
    }

    public void zza(zzlh com_google_android_gms_internal_zzlh, Map<String, String> map) {
        zznc();
    }

    public void zze(int i, int i2) {
        zzc(i, i2 - (this.mContext instanceof Activity ? zzu.zzfq().zzk((Activity) this.mContext)[0] : 0), this.zzbrk, this.zzbrl);
        this.zzbgf.zzuj().zzd(i, i2);
    }

    void zzna() {
        this.zzbrf = zzm.zziw().zzb(this.zzbrd, this.zzbrd.widthPixels);
        this.zzbrg = zzm.zziw().zzb(this.zzbrd, this.zzbrd.heightPixels);
        Activity zzue = this.zzbgf.zzue();
        if (zzue == null || zzue.getWindow() == null) {
            this.zzbri = this.zzbrf;
            this.zzbrj = this.zzbrg;
            return;
        }
        int[] zzh = zzu.zzfq().zzh(zzue);
        this.zzbri = zzm.zziw().zzb(this.zzbrd, zzh[0]);
        this.zzbrj = zzm.zziw().zzb(this.zzbrd, zzh[1]);
    }

    void zznb() {
        if (this.zzbgf.zzdn().zzaus) {
            this.zzbrk = this.zzbrf;
            this.zzbrl = this.zzbrg;
            return;
        }
        this.zzbgf.measure(0, 0);
        this.zzbrk = zzm.zziw().zzb(this.mContext, this.zzbgf.getMeasuredWidth());
        this.zzbrl = zzm.zziw().zzb(this.mContext, this.zzbgf.getMeasuredHeight());
    }

    public void zznc() {
        zzmz();
        zzna();
        zznb();
        zznf();
        zzng();
        zzne();
        zznd();
    }

    void zznd() {
        if (zzb.zzaz(2)) {
            zzb.zzcw("Dispatching Ready Event.");
        }
        zzbu(this.zzbgf.zzum().zzcs);
    }

    void zznf() {
        zza(this.zzbrf, this.zzbrg, this.zzbri, this.zzbrj, this.zzbre, this.zzbrh);
    }

    void zzng() {
        this.zzbgf.zzb("onDeviceFeaturesReceived", zznh().toJson());
    }
}

package com.google.android.gms.internal;

import android.app.Activity;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.common.util.zzf;
import io.branch.referral.Branch;
import java.util.Map;
import java.util.Set;

@zzin
public class zzha extends zzhf {
    static final Set<String> zzbqe = zzf.zzc(new String[]{"top-left", "top-right", "top-center", "center", "bottom-left", "bottom-right", "bottom-center"});
    private int zzaie = -1;
    private int zzaif = -1;
    private final Object zzail = new Object();
    private AdSizeParcel zzani;
    private final zzlh zzbgf;
    private final Activity zzbpu;
    private String zzbqf = "top-right";
    private boolean zzbqg = true;
    private int zzbqh = 0;
    private int zzbqi = 0;
    private int zzbqj = 0;
    private int zzbqk = 0;
    private ImageView zzbql;
    private LinearLayout zzbqm;
    private zzhg zzbqn;
    private PopupWindow zzbqo;
    private RelativeLayout zzbqp;
    private ViewGroup zzbqq;

    public zzha(zzlh com_google_android_gms_internal_zzlh, zzhg com_google_android_gms_internal_zzhg) {
        super(com_google_android_gms_internal_zzlh, "resize");
        this.zzbgf = com_google_android_gms_internal_zzlh;
        this.zzbpu = com_google_android_gms_internal_zzlh.zzue();
        this.zzbqn = com_google_android_gms_internal_zzhg;
    }

    private void zzi(Map<String, String> map) {
        if (!TextUtils.isEmpty((CharSequence) map.get("width"))) {
            this.zzaie = zzu.zzfq().zzcp((String) map.get("width"));
        }
        if (!TextUtils.isEmpty((CharSequence) map.get("height"))) {
            this.zzaif = zzu.zzfq().zzcp((String) map.get("height"));
        }
        if (!TextUtils.isEmpty((CharSequence) map.get("offsetX"))) {
            this.zzbqj = zzu.zzfq().zzcp((String) map.get("offsetX"));
        }
        if (!TextUtils.isEmpty((CharSequence) map.get("offsetY"))) {
            this.zzbqk = zzu.zzfq().zzcp((String) map.get("offsetY"));
        }
        if (!TextUtils.isEmpty((CharSequence) map.get("allowOffscreen"))) {
            this.zzbqg = Boolean.parseBoolean((String) map.get("allowOffscreen"));
        }
        String str = (String) map.get("customClosePosition");
        if (!TextUtils.isEmpty(str)) {
            this.zzbqf = str;
        }
    }

    private int[] zzmv() {
        if (!zzmx()) {
            return null;
        }
        if (this.zzbqg) {
            return new int[]{this.zzbqh + this.zzbqj, this.zzbqi + this.zzbqk};
        }
        int[] zzi = zzu.zzfq().zzi(this.zzbpu);
        int[] zzk = zzu.zzfq().zzk(this.zzbpu);
        int i = zzi[0];
        int i2 = this.zzbqh + this.zzbqj;
        int i3 = this.zzbqi + this.zzbqk;
        if (i2 < 0) {
            i2 = 0;
        } else if (this.zzaie + i2 > i) {
            i2 = i - this.zzaie;
        }
        if (i3 < zzk[0]) {
            i3 = zzk[0];
        } else if (this.zzaif + i3 > zzk[1]) {
            i3 = zzk[1] - this.zzaif;
        }
        return new int[]{i2, i3};
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void execute(java.util.Map<java.lang.String, java.lang.String> r13) {
        /*
        r12 = this;
        r4 = -1;
        r5 = 1;
        r3 = 0;
        r6 = r12.zzail;
        monitor-enter(r6);
        r1 = r12.zzbpu;	 Catch:{ all -> 0x0020 }
        if (r1 != 0) goto L_0x0011;
    L_0x000a:
        r1 = "Not an activity context. Cannot resize.";
        r12.zzbt(r1);	 Catch:{ all -> 0x0020 }
        monitor-exit(r6);	 Catch:{ all -> 0x0020 }
    L_0x0010:
        return;
    L_0x0011:
        r1 = r12.zzbgf;	 Catch:{ all -> 0x0020 }
        r1 = r1.zzdn();	 Catch:{ all -> 0x0020 }
        if (r1 != 0) goto L_0x0023;
    L_0x0019:
        r1 = "Webview is not yet available, size is not set.";
        r12.zzbt(r1);	 Catch:{ all -> 0x0020 }
        monitor-exit(r6);	 Catch:{ all -> 0x0020 }
        goto L_0x0010;
    L_0x0020:
        r1 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x0020 }
        throw r1;
    L_0x0023:
        r1 = r12.zzbgf;	 Catch:{ all -> 0x0020 }
        r1 = r1.zzdn();	 Catch:{ all -> 0x0020 }
        r1 = r1.zzaus;	 Catch:{ all -> 0x0020 }
        if (r1 == 0) goto L_0x0034;
    L_0x002d:
        r1 = "Is interstitial. Cannot resize an interstitial.";
        r12.zzbt(r1);	 Catch:{ all -> 0x0020 }
        monitor-exit(r6);	 Catch:{ all -> 0x0020 }
        goto L_0x0010;
    L_0x0034:
        r1 = r12.zzbgf;	 Catch:{ all -> 0x0020 }
        r1 = r1.zzun();	 Catch:{ all -> 0x0020 }
        if (r1 == 0) goto L_0x0043;
    L_0x003c:
        r1 = "Cannot resize an expanded banner.";
        r12.zzbt(r1);	 Catch:{ all -> 0x0020 }
        monitor-exit(r6);	 Catch:{ all -> 0x0020 }
        goto L_0x0010;
    L_0x0043:
        r12.zzi(r13);	 Catch:{ all -> 0x0020 }
        r1 = r12.zzmu();	 Catch:{ all -> 0x0020 }
        if (r1 != 0) goto L_0x0053;
    L_0x004c:
        r1 = "Invalid width and height options. Cannot resize.";
        r12.zzbt(r1);	 Catch:{ all -> 0x0020 }
        monitor-exit(r6);	 Catch:{ all -> 0x0020 }
        goto L_0x0010;
    L_0x0053:
        r1 = r12.zzbpu;	 Catch:{ all -> 0x0020 }
        r7 = r1.getWindow();	 Catch:{ all -> 0x0020 }
        if (r7 == 0) goto L_0x0061;
    L_0x005b:
        r1 = r7.getDecorView();	 Catch:{ all -> 0x0020 }
        if (r1 != 0) goto L_0x0068;
    L_0x0061:
        r1 = "Activity context is not ready, cannot get window or decor view.";
        r12.zzbt(r1);	 Catch:{ all -> 0x0020 }
        monitor-exit(r6);	 Catch:{ all -> 0x0020 }
        goto L_0x0010;
    L_0x0068:
        r8 = r12.zzmv();	 Catch:{ all -> 0x0020 }
        if (r8 != 0) goto L_0x0075;
    L_0x006e:
        r1 = "Resize location out of screen or close button is not visible.";
        r12.zzbt(r1);	 Catch:{ all -> 0x0020 }
        monitor-exit(r6);	 Catch:{ all -> 0x0020 }
        goto L_0x0010;
    L_0x0075:
        r1 = com.google.android.gms.ads.internal.client.zzm.zziw();	 Catch:{ all -> 0x0020 }
        r2 = r12.zzbpu;	 Catch:{ all -> 0x0020 }
        r9 = r12.zzaie;	 Catch:{ all -> 0x0020 }
        r9 = r1.zza(r2, r9);	 Catch:{ all -> 0x0020 }
        r1 = com.google.android.gms.ads.internal.client.zzm.zziw();	 Catch:{ all -> 0x0020 }
        r2 = r12.zzbpu;	 Catch:{ all -> 0x0020 }
        r10 = r12.zzaif;	 Catch:{ all -> 0x0020 }
        r10 = r1.zza(r2, r10);	 Catch:{ all -> 0x0020 }
        r1 = r12.zzbgf;	 Catch:{ all -> 0x0020 }
        r1 = r1.getView();	 Catch:{ all -> 0x0020 }
        r2 = r1.getParent();	 Catch:{ all -> 0x0020 }
        if (r2 == 0) goto L_0x01d5;
    L_0x0099:
        r1 = r2 instanceof android.view.ViewGroup;	 Catch:{ all -> 0x0020 }
        if (r1 == 0) goto L_0x01d5;
    L_0x009d:
        r0 = r2;
        r0 = (android.view.ViewGroup) r0;	 Catch:{ all -> 0x0020 }
        r1 = r0;
        r11 = r12.zzbgf;	 Catch:{ all -> 0x0020 }
        r11 = r11.getView();	 Catch:{ all -> 0x0020 }
        r1.removeView(r11);	 Catch:{ all -> 0x0020 }
        r1 = r12.zzbqo;	 Catch:{ all -> 0x0020 }
        if (r1 != 0) goto L_0x01ce;
    L_0x00ae:
        r2 = (android.view.ViewGroup) r2;	 Catch:{ all -> 0x0020 }
        r12.zzbqq = r2;	 Catch:{ all -> 0x0020 }
        r1 = com.google.android.gms.ads.internal.zzu.zzfq();	 Catch:{ all -> 0x0020 }
        r2 = r12.zzbgf;	 Catch:{ all -> 0x0020 }
        r2 = r2.getView();	 Catch:{ all -> 0x0020 }
        r1 = r1.zzk(r2);	 Catch:{ all -> 0x0020 }
        r2 = new android.widget.ImageView;	 Catch:{ all -> 0x0020 }
        r11 = r12.zzbpu;	 Catch:{ all -> 0x0020 }
        r2.<init>(r11);	 Catch:{ all -> 0x0020 }
        r12.zzbql = r2;	 Catch:{ all -> 0x0020 }
        r2 = r12.zzbql;	 Catch:{ all -> 0x0020 }
        r2.setImageBitmap(r1);	 Catch:{ all -> 0x0020 }
        r1 = r12.zzbgf;	 Catch:{ all -> 0x0020 }
        r1 = r1.zzdn();	 Catch:{ all -> 0x0020 }
        r12.zzani = r1;	 Catch:{ all -> 0x0020 }
        r1 = r12.zzbqq;	 Catch:{ all -> 0x0020 }
        r2 = r12.zzbql;	 Catch:{ all -> 0x0020 }
        r1.addView(r2);	 Catch:{ all -> 0x0020 }
    L_0x00dd:
        r1 = new android.widget.RelativeLayout;	 Catch:{ all -> 0x0020 }
        r2 = r12.zzbpu;	 Catch:{ all -> 0x0020 }
        r1.<init>(r2);	 Catch:{ all -> 0x0020 }
        r12.zzbqp = r1;	 Catch:{ all -> 0x0020 }
        r1 = r12.zzbqp;	 Catch:{ all -> 0x0020 }
        r2 = 0;
        r1.setBackgroundColor(r2);	 Catch:{ all -> 0x0020 }
        r1 = r12.zzbqp;	 Catch:{ all -> 0x0020 }
        r2 = new android.view.ViewGroup$LayoutParams;	 Catch:{ all -> 0x0020 }
        r2.<init>(r9, r10);	 Catch:{ all -> 0x0020 }
        r1.setLayoutParams(r2);	 Catch:{ all -> 0x0020 }
        r1 = com.google.android.gms.ads.internal.zzu.zzfq();	 Catch:{ all -> 0x0020 }
        r2 = r12.zzbqp;	 Catch:{ all -> 0x0020 }
        r11 = 0;
        r1 = r1.zza(r2, r9, r10, r11);	 Catch:{ all -> 0x0020 }
        r12.zzbqo = r1;	 Catch:{ all -> 0x0020 }
        r1 = r12.zzbqo;	 Catch:{ all -> 0x0020 }
        r2 = 1;
        r1.setOutsideTouchable(r2);	 Catch:{ all -> 0x0020 }
        r1 = r12.zzbqo;	 Catch:{ all -> 0x0020 }
        r2 = 1;
        r1.setTouchable(r2);	 Catch:{ all -> 0x0020 }
        r2 = r12.zzbqo;	 Catch:{ all -> 0x0020 }
        r1 = r12.zzbqg;	 Catch:{ all -> 0x0020 }
        if (r1 != 0) goto L_0x01dd;
    L_0x0115:
        r1 = r5;
    L_0x0116:
        r2.setClippingEnabled(r1);	 Catch:{ all -> 0x0020 }
        r1 = r12.zzbqp;	 Catch:{ all -> 0x0020 }
        r2 = r12.zzbgf;	 Catch:{ all -> 0x0020 }
        r2 = r2.getView();	 Catch:{ all -> 0x0020 }
        r9 = -1;
        r10 = -1;
        r1.addView(r2, r9, r10);	 Catch:{ all -> 0x0020 }
        r1 = new android.widget.LinearLayout;	 Catch:{ all -> 0x0020 }
        r2 = r12.zzbpu;	 Catch:{ all -> 0x0020 }
        r1.<init>(r2);	 Catch:{ all -> 0x0020 }
        r12.zzbqm = r1;	 Catch:{ all -> 0x0020 }
        r2 = new android.widget.RelativeLayout$LayoutParams;	 Catch:{ all -> 0x0020 }
        r1 = com.google.android.gms.ads.internal.client.zzm.zziw();	 Catch:{ all -> 0x0020 }
        r9 = r12.zzbpu;	 Catch:{ all -> 0x0020 }
        r10 = 50;
        r1 = r1.zza(r9, r10);	 Catch:{ all -> 0x0020 }
        r9 = com.google.android.gms.ads.internal.client.zzm.zziw();	 Catch:{ all -> 0x0020 }
        r10 = r12.zzbpu;	 Catch:{ all -> 0x0020 }
        r11 = 50;
        r9 = r9.zza(r10, r11);	 Catch:{ all -> 0x0020 }
        r2.<init>(r1, r9);	 Catch:{ all -> 0x0020 }
        r1 = r12.zzbqf;	 Catch:{ all -> 0x0020 }
        r9 = r1.hashCode();	 Catch:{ all -> 0x0020 }
        switch(r9) {
            case -1364013995: goto L_0x01f6;
            case -1012429441: goto L_0x01e0;
            case -655373719: goto L_0x0201;
            case 1163912186: goto L_0x0217;
            case 1288627767: goto L_0x020c;
            case 1755462605: goto L_0x01eb;
            default: goto L_0x0155;
        };	 Catch:{ all -> 0x0020 }
    L_0x0155:
        r1 = r4;
    L_0x0156:
        switch(r1) {
            case 0: goto L_0x0222;
            case 1: goto L_0x022e;
            case 2: goto L_0x023a;
            case 3: goto L_0x0241;
            case 4: goto L_0x024d;
            case 5: goto L_0x0259;
            default: goto L_0x0159;
        };	 Catch:{ all -> 0x0020 }
    L_0x0159:
        r1 = 10;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
        r1 = 11;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
    L_0x0163:
        r1 = r12.zzbqm;	 Catch:{ all -> 0x0020 }
        r3 = new com.google.android.gms.internal.zzha$1;	 Catch:{ all -> 0x0020 }
        r3.<init>(r12);	 Catch:{ all -> 0x0020 }
        r1.setOnClickListener(r3);	 Catch:{ all -> 0x0020 }
        r1 = r12.zzbqm;	 Catch:{ all -> 0x0020 }
        r3 = "Close button";
        r1.setContentDescription(r3);	 Catch:{ all -> 0x0020 }
        r1 = r12.zzbqp;	 Catch:{ all -> 0x0020 }
        r3 = r12.zzbqm;	 Catch:{ all -> 0x0020 }
        r1.addView(r3, r2);	 Catch:{ all -> 0x0020 }
        r1 = r12.zzbqo;	 Catch:{ RuntimeException -> 0x0265 }
        r2 = r7.getDecorView();	 Catch:{ RuntimeException -> 0x0265 }
        r3 = 0;
        r4 = com.google.android.gms.ads.internal.client.zzm.zziw();	 Catch:{ RuntimeException -> 0x0265 }
        r5 = r12.zzbpu;	 Catch:{ RuntimeException -> 0x0265 }
        r7 = 0;
        r7 = r8[r7];	 Catch:{ RuntimeException -> 0x0265 }
        r4 = r4.zza(r5, r7);	 Catch:{ RuntimeException -> 0x0265 }
        r5 = com.google.android.gms.ads.internal.client.zzm.zziw();	 Catch:{ RuntimeException -> 0x0265 }
        r7 = r12.zzbpu;	 Catch:{ RuntimeException -> 0x0265 }
        r9 = 1;
        r9 = r8[r9];	 Catch:{ RuntimeException -> 0x0265 }
        r5 = r5.zza(r7, r9);	 Catch:{ RuntimeException -> 0x0265 }
        r1.showAtLocation(r2, r3, r4, r5);	 Catch:{ RuntimeException -> 0x0265 }
        r1 = 0;
        r1 = r8[r1];	 Catch:{ all -> 0x0020 }
        r2 = 1;
        r2 = r8[r2];	 Catch:{ all -> 0x0020 }
        r12.zzb(r1, r2);	 Catch:{ all -> 0x0020 }
        r1 = r12.zzbgf;	 Catch:{ all -> 0x0020 }
        r2 = new com.google.android.gms.ads.internal.client.AdSizeParcel;	 Catch:{ all -> 0x0020 }
        r3 = r12.zzbpu;	 Catch:{ all -> 0x0020 }
        r4 = new com.google.android.gms.ads.AdSize;	 Catch:{ all -> 0x0020 }
        r5 = r12.zzaie;	 Catch:{ all -> 0x0020 }
        r7 = r12.zzaif;	 Catch:{ all -> 0x0020 }
        r4.<init>(r5, r7);	 Catch:{ all -> 0x0020 }
        r2.<init>(r3, r4);	 Catch:{ all -> 0x0020 }
        r1.zza(r2);	 Catch:{ all -> 0x0020 }
        r1 = 0;
        r1 = r8[r1];	 Catch:{ all -> 0x0020 }
        r2 = 1;
        r2 = r8[r2];	 Catch:{ all -> 0x0020 }
        r12.zzc(r1, r2);	 Catch:{ all -> 0x0020 }
        r1 = "resized";
        r12.zzbv(r1);	 Catch:{ all -> 0x0020 }
        monitor-exit(r6);	 Catch:{ all -> 0x0020 }
        goto L_0x0010;
    L_0x01ce:
        r1 = r12.zzbqo;	 Catch:{ all -> 0x0020 }
        r1.dismiss();	 Catch:{ all -> 0x0020 }
        goto L_0x00dd;
    L_0x01d5:
        r1 = "Webview is detached, probably in the middle of a resize or expand.";
        r12.zzbt(r1);	 Catch:{ all -> 0x0020 }
        monitor-exit(r6);	 Catch:{ all -> 0x0020 }
        goto L_0x0010;
    L_0x01dd:
        r1 = r3;
        goto L_0x0116;
    L_0x01e0:
        r5 = "top-left";
        r1 = r1.equals(r5);	 Catch:{ all -> 0x0020 }
        if (r1 == 0) goto L_0x0155;
    L_0x01e8:
        r1 = r3;
        goto L_0x0156;
    L_0x01eb:
        r3 = "top-center";
        r1 = r1.equals(r3);	 Catch:{ all -> 0x0020 }
        if (r1 == 0) goto L_0x0155;
    L_0x01f3:
        r1 = r5;
        goto L_0x0156;
    L_0x01f6:
        r3 = "center";
        r1 = r1.equals(r3);	 Catch:{ all -> 0x0020 }
        if (r1 == 0) goto L_0x0155;
    L_0x01fe:
        r1 = 2;
        goto L_0x0156;
    L_0x0201:
        r3 = "bottom-left";
        r1 = r1.equals(r3);	 Catch:{ all -> 0x0020 }
        if (r1 == 0) goto L_0x0155;
    L_0x0209:
        r1 = 3;
        goto L_0x0156;
    L_0x020c:
        r3 = "bottom-center";
        r1 = r1.equals(r3);	 Catch:{ all -> 0x0020 }
        if (r1 == 0) goto L_0x0155;
    L_0x0214:
        r1 = 4;
        goto L_0x0156;
    L_0x0217:
        r3 = "bottom-right";
        r1 = r1.equals(r3);	 Catch:{ all -> 0x0020 }
        if (r1 == 0) goto L_0x0155;
    L_0x021f:
        r1 = 5;
        goto L_0x0156;
    L_0x0222:
        r1 = 10;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
        r1 = 9;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
        goto L_0x0163;
    L_0x022e:
        r1 = 10;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
        r1 = 14;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
        goto L_0x0163;
    L_0x023a:
        r1 = 13;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
        goto L_0x0163;
    L_0x0241:
        r1 = 12;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
        r1 = 9;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
        goto L_0x0163;
    L_0x024d:
        r1 = 12;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
        r1 = 14;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
        goto L_0x0163;
    L_0x0259:
        r1 = 12;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
        r1 = 11;
        r2.addRule(r1);	 Catch:{ all -> 0x0020 }
        goto L_0x0163;
    L_0x0265:
        r1 = move-exception;
        r2 = "Cannot show popup window: ";
        r1 = r1.getMessage();	 Catch:{ all -> 0x0020 }
        r1 = java.lang.String.valueOf(r1);	 Catch:{ all -> 0x0020 }
        r3 = r1.length();	 Catch:{ all -> 0x0020 }
        if (r3 == 0) goto L_0x02a8;
    L_0x0276:
        r1 = r2.concat(r1);	 Catch:{ all -> 0x0020 }
    L_0x027a:
        r12.zzbt(r1);	 Catch:{ all -> 0x0020 }
        r1 = r12.zzbqp;	 Catch:{ all -> 0x0020 }
        r2 = r12.zzbgf;	 Catch:{ all -> 0x0020 }
        r2 = r2.getView();	 Catch:{ all -> 0x0020 }
        r1.removeView(r2);	 Catch:{ all -> 0x0020 }
        r1 = r12.zzbqq;	 Catch:{ all -> 0x0020 }
        if (r1 == 0) goto L_0x02a5;
    L_0x028c:
        r1 = r12.zzbqq;	 Catch:{ all -> 0x0020 }
        r2 = r12.zzbql;	 Catch:{ all -> 0x0020 }
        r1.removeView(r2);	 Catch:{ all -> 0x0020 }
        r1 = r12.zzbqq;	 Catch:{ all -> 0x0020 }
        r2 = r12.zzbgf;	 Catch:{ all -> 0x0020 }
        r2 = r2.getView();	 Catch:{ all -> 0x0020 }
        r1.addView(r2);	 Catch:{ all -> 0x0020 }
        r1 = r12.zzbgf;	 Catch:{ all -> 0x0020 }
        r2 = r12.zzani;	 Catch:{ all -> 0x0020 }
        r1.zza(r2);	 Catch:{ all -> 0x0020 }
    L_0x02a5:
        monitor-exit(r6);	 Catch:{ all -> 0x0020 }
        goto L_0x0010;
    L_0x02a8:
        r1 = new java.lang.String;	 Catch:{ all -> 0x0020 }
        r1.<init>(r2);	 Catch:{ all -> 0x0020 }
        goto L_0x027a;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzha.execute(java.util.Map):void");
    }

    public void zza(int i, int i2, boolean z) {
        synchronized (this.zzail) {
            this.zzbqh = i;
            this.zzbqi = i2;
            if (this.zzbqo != null && z) {
                int[] zzmv = zzmv();
                if (zzmv != null) {
                    this.zzbqo.update(zzm.zziw().zza(this.zzbpu, zzmv[0]), zzm.zziw().zza(this.zzbpu, zzmv[1]), this.zzbqo.getWidth(), this.zzbqo.getHeight());
                    zzc(zzmv[0], zzmv[1]);
                } else {
                    zzs(true);
                }
            }
        }
    }

    void zzb(int i, int i2) {
        if (this.zzbqn != null) {
            this.zzbqn.zza(i, i2, this.zzaie, this.zzaif);
        }
    }

    void zzc(int i, int i2) {
        zzb(i, i2 - zzu.zzfq().zzk(this.zzbpu)[0], this.zzaie, this.zzaif);
    }

    public void zzd(int i, int i2) {
        this.zzbqh = i;
        this.zzbqi = i2;
    }

    boolean zzmu() {
        return this.zzaie > -1 && this.zzaif > -1;
    }

    public boolean zzmw() {
        boolean z;
        synchronized (this.zzail) {
            z = this.zzbqo != null;
        }
        return z;
    }

    boolean zzmx() {
        int[] zzi = zzu.zzfq().zzi(this.zzbpu);
        int[] zzk = zzu.zzfq().zzk(this.zzbpu);
        int i = zzi[0];
        int i2 = zzi[1];
        if (this.zzaie < 50 || this.zzaie > i) {
            zzb.zzcx("Width is too small or too large.");
            return false;
        } else if (this.zzaif < 50 || this.zzaif > i2) {
            zzb.zzcx("Height is too small or too large.");
            return false;
        } else if (this.zzaif == i2 && this.zzaie == i) {
            zzb.zzcx("Cannot resize to a full-screen ad.");
            return false;
        } else {
            if (this.zzbqg) {
                int i3;
                String str = this.zzbqf;
                boolean z = true;
                switch (str.hashCode()) {
                    case -1364013995:
                        if (str.equals("center")) {
                            z = true;
                            break;
                        }
                        break;
                    case -1012429441:
                        if (str.equals("top-left")) {
                            z = false;
                            break;
                        }
                        break;
                    case -655373719:
                        if (str.equals("bottom-left")) {
                            z = true;
                            break;
                        }
                        break;
                    case 1163912186:
                        if (str.equals("bottom-right")) {
                            z = true;
                            break;
                        }
                        break;
                    case 1288627767:
                        if (str.equals("bottom-center")) {
                            z = true;
                            break;
                        }
                        break;
                    case 1755462605:
                        if (str.equals("top-center")) {
                            z = true;
                            break;
                        }
                        break;
                }
                switch (z) {
                    case false:
                        i3 = this.zzbqj + this.zzbqh;
                        i2 = this.zzbqi + this.zzbqk;
                        break;
                    case true:
                        i3 = ((this.zzbqh + this.zzbqj) + (this.zzaie / 2)) - 25;
                        i2 = this.zzbqi + this.zzbqk;
                        break;
                    case true:
                        i3 = ((this.zzbqh + this.zzbqj) + (this.zzaie / 2)) - 25;
                        i2 = ((this.zzbqi + this.zzbqk) + (this.zzaif / 2)) - 25;
                        break;
                    case true:
                        i3 = this.zzbqj + this.zzbqh;
                        i2 = ((this.zzbqi + this.zzbqk) + this.zzaif) - 50;
                        break;
                    case true:
                        i3 = ((this.zzbqh + this.zzbqj) + (this.zzaie / 2)) - 25;
                        i2 = ((this.zzbqi + this.zzbqk) + this.zzaif) - 50;
                        break;
                    case true:
                        i3 = ((this.zzbqh + this.zzbqj) + this.zzaie) - 50;
                        i2 = ((this.zzbqi + this.zzbqk) + this.zzaif) - 50;
                        break;
                    default:
                        i3 = ((this.zzbqh + this.zzbqj) + this.zzaie) - 50;
                        i2 = this.zzbqi + this.zzbqk;
                        break;
                }
                if (i3 < 0 || i3 + 50 > i || r2 < zzk[0] || r2 + 50 > zzk[1]) {
                    return false;
                }
            }
            return true;
        }
    }

    public void zzs(boolean z) {
        synchronized (this.zzail) {
            if (this.zzbqo != null) {
                this.zzbqo.dismiss();
                this.zzbqp.removeView(this.zzbgf.getView());
                if (this.zzbqq != null) {
                    this.zzbqq.removeView(this.zzbql);
                    this.zzbqq.addView(this.zzbgf.getView());
                    this.zzbgf.zza(this.zzani);
                }
                if (z) {
                    zzbv(Branch.REFERRAL_BUCKET_DEFAULT);
                    if (this.zzbqn != null) {
                        this.zzbqn.zzej();
                    }
                }
                this.zzbqo = null;
                this.zzbqp = null;
                this.zzbqq = null;
                this.zzbqm = null;
            }
        }
    }
}

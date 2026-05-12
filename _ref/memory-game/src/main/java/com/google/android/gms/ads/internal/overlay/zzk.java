package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.InputDeviceCompat;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import com.cmcm.adsdk.nativead.RequestResultLogger.Model;
import com.google.android.gms.common.internal.zzb;
import com.google.android.gms.internal.zzdi;
import com.google.android.gms.internal.zzdk;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzkh;
import com.google.android.gms.internal.zzlh;
import com.mopub.mobileads.VastIconXmlManager;
import java.util.HashMap;
import java.util.Map;

@zzin
public class zzk extends FrameLayout implements zzh {
    private final zzlh zzbgf;
    private String zzbjc;
    private final FrameLayout zzbtw;
    private final zzy zzbtx;
    @Nullable
    private zzi zzbty;
    private boolean zzbtz;
    private boolean zzbua;
    private TextView zzbub;
    private long zzbuc;
    private long zzbud;
    private String zzbue;

    public zzk(Context context, zzlh com_google_android_gms_internal_zzlh, int i, boolean z, zzdk com_google_android_gms_internal_zzdk, zzdi com_google_android_gms_internal_zzdi) {
        super(context);
        this.zzbgf = com_google_android_gms_internal_zzlh;
        this.zzbtw = new FrameLayout(context);
        addView(this.zzbtw, new LayoutParams(-1, -1));
        zzb.zzu(com_google_android_gms_internal_zzlh.zzug());
        this.zzbty = com_google_android_gms_internal_zzlh.zzug().zzakk.zza(context, com_google_android_gms_internal_zzlh, i, z, com_google_android_gms_internal_zzdk, com_google_android_gms_internal_zzdi);
        if (this.zzbty != null) {
            this.zzbtw.addView(this.zzbty, new LayoutParams(-1, -1, 17));
        }
        this.zzbub = new TextView(context);
        this.zzbub.setBackgroundColor(-16777216);
        zzop();
        this.zzbtx = new zzy(this);
        this.zzbtx.zzpk();
        if (this.zzbty != null) {
            this.zzbty.zza((zzh) this);
        }
        if (this.zzbty == null) {
            zzl("AdVideoUnderlay Error", "Allocating player failed.");
        }
    }

    private void zza(String str, String... strArr) {
        Map hashMap = new HashMap();
        hashMap.put("event", str);
        int length = strArr.length;
        int i = 0;
        Object obj = null;
        while (i < length) {
            Object obj2 = strArr[i];
            if (obj != null) {
                hashMap.put(obj, obj2);
                obj2 = null;
            }
            i++;
            obj = obj2;
        }
        this.zzbgf.zza("onVideoEvent", hashMap);
    }

    public static void zzh(zzlh com_google_android_gms_internal_zzlh) {
        Map hashMap = new HashMap();
        hashMap.put("event", "no_video_view");
        com_google_android_gms_internal_zzlh.zza("onVideoEvent", hashMap);
    }

    private void zzop() {
        if (!zzor()) {
            this.zzbtw.addView(this.zzbub, new LayoutParams(-1, -1));
            this.zzbtw.bringChildToFront(this.zzbub);
        }
    }

    private void zzoq() {
        if (zzor()) {
            this.zzbtw.removeView(this.zzbub);
        }
    }

    private boolean zzor() {
        return this.zzbub.getParent() != null;
    }

    private void zzos() {
        if (this.zzbgf.zzue() != null && !this.zzbtz) {
            this.zzbua = (this.zzbgf.zzue().getWindow().getAttributes().flags & 128) != 0;
            if (!this.zzbua) {
                this.zzbgf.zzue().getWindow().addFlags(128);
                this.zzbtz = true;
            }
        }
    }

    private void zzot() {
        if (this.zzbgf.zzue() != null && this.zzbtz && !this.zzbua) {
            this.zzbgf.zzue().getWindow().clearFlags(128);
            this.zzbtz = false;
        }
    }

    public void destroy() {
        this.zzbtx.cancel();
        if (this.zzbty != null) {
            this.zzbty.stop();
        }
        zzot();
    }

    public void onPaused() {
        zza("pause", new String[0]);
        zzot();
    }

    public void pause() {
        if (this.zzbty != null) {
            this.zzbty.pause();
        }
    }

    public void play() {
        if (this.zzbty != null) {
            this.zzbty.play();
        }
    }

    public void seekTo(int i) {
        if (this.zzbty != null) {
            this.zzbty.seekTo(i);
        }
    }

    public void setMimeType(String str) {
        this.zzbue = str;
    }

    public void zza(float f) {
        if (this.zzbty != null) {
            this.zzbty.zza(f);
        }
    }

    public void zza(float f, float f2) {
        if (this.zzbty != null) {
            this.zzbty.zza(f, f2);
        }
    }

    public void zzbw(String str) {
        this.zzbjc = str;
    }

    public void zzd(int i, int i2, int i3, int i4) {
        if (i3 != 0 && i4 != 0) {
            ViewGroup.LayoutParams layoutParams = new LayoutParams(i3 + 2, i4 + 2);
            layoutParams.setMargins(i - 1, i2 - 1, 0, 0);
            this.zzbtw.setLayoutParams(layoutParams);
            requestLayout();
        }
    }

    public void zzd(MotionEvent motionEvent) {
        if (this.zzbty != null) {
            this.zzbty.dispatchTouchEvent(motionEvent);
        }
    }

    public void zzl(String str, String str2) {
        zza("error", "what", str, "extra", str2);
    }

    public void zzlv() {
        if (this.zzbty != null) {
            if (TextUtils.isEmpty(this.zzbjc)) {
                zza("no_src", new String[0]);
                return;
            }
            this.zzbty.setMimeType(this.zzbue);
            this.zzbty.setVideoPath(this.zzbjc);
        }
    }

    public void zzno() {
        if (this.zzbty != null) {
            this.zzbty.zzno();
        }
    }

    public void zznp() {
        if (this.zzbty != null) {
            this.zzbty.zznp();
        }
    }

    public void zzoi() {
        zzkh.zzclc.post(new 1(this));
    }

    public void zzoj() {
        if (this.zzbty != null && this.zzbud == 0) {
            float duration = ((float) this.zzbty.getDuration()) / 1000.0f;
            int videoWidth = this.zzbty.getVideoWidth();
            int videoHeight = this.zzbty.getVideoHeight();
            zza("canplaythrough", VastIconXmlManager.DURATION, String.valueOf(duration), "videoWidth", String.valueOf(videoWidth), "videoHeight", String.valueOf(videoHeight));
        }
    }

    public void zzok() {
        zzos();
    }

    public void zzol() {
        zza("ended", new String[0]);
        zzot();
    }

    public void zzom() {
        zzop();
        this.zzbud = this.zzbuc;
        zzkh.zzclc.post(new 2(this));
    }

    public void zzon() {
        if (this.zzbty != null) {
            View textView = new TextView(this.zzbty.getContext());
            String str = "AdMob - ";
            String valueOf = String.valueOf(this.zzbty.zzni());
            textView.setText(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            textView.setTextColor(SupportMenu.CATEGORY_MASK);
            textView.setBackgroundColor(InputDeviceCompat.SOURCE_ANY);
            this.zzbtw.addView(textView, new LayoutParams(-2, -2, 17));
            this.zzbtw.bringChildToFront(textView);
        }
    }

    void zzoo() {
        if (this.zzbty != null) {
            long currentPosition = (long) this.zzbty.getCurrentPosition();
            if (this.zzbuc != currentPosition && currentPosition > 0) {
                zzoq();
                float f = ((float) currentPosition) / 1000.0f;
                zza("timeupdate", Model.KEY_loadtime, String.valueOf(f));
                this.zzbuc = currentPosition;
            }
        }
    }
}

package com.google.android.gms.ads.internal.overlay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.Window;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.internal.zzdc;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzkc;
import com.google.android.gms.internal.zzkh;
import com.google.android.gms.internal.zzkk;
import com.google.android.gms.internal.zzlh;
import com.google.android.gms.internal.zzli;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.Future;

@zzin
public class zzd extends com.google.android.gms.internal.zzhi.zza implements zzu {
    static final int zzbsn = Color.argb(0, 0, 0, 0);
    private final Activity mActivity;
    zzlh zzbgf;
    AdOverlayInfoParcel zzbso;
    zzc zzbsp;
    zzo zzbsq;
    boolean zzbsr = false;
    FrameLayout zzbss;
    CustomViewCallback zzbst;
    boolean zzbsu = false;
    boolean zzbsv = false;
    zzb zzbsw;
    boolean zzbsx = false;
    int zzbsy = 0;
    zzl zzbsz;
    private boolean zzbta;
    private boolean zzbtb = false;
    private boolean zzbtc = true;

    @zzin
    private static final class zza extends Exception {
        public zza(String str) {
            super(str);
        }
    }

    @zzin
    static class zzb extends RelativeLayout {
        zzkk zzaqf;
        boolean zzbte;

        public zzb(Context context, String str) {
            super(context);
            this.zzaqf = new zzkk(context, str);
        }

        void disable() {
            this.zzbte = true;
        }

        public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
            if (!this.zzbte) {
                this.zzaqf.zze(motionEvent);
            }
            return false;
        }
    }

    @zzin
    public static class zzc {
        public final int index;
        public final Context zzagf;
        public final LayoutParams zzbtf;
        public final ViewGroup zzbtg;

        public zzc(zzlh com_google_android_gms_internal_zzlh) throws zza {
            this.zzbtf = com_google_android_gms_internal_zzlh.getLayoutParams();
            ViewParent parent = com_google_android_gms_internal_zzlh.getParent();
            this.zzagf = com_google_android_gms_internal_zzlh.zzuf();
            if (parent == null || !(parent instanceof ViewGroup)) {
                throw new zza("Could not get the parent of the WebView for an overlay.");
            }
            this.zzbtg = (ViewGroup) parent;
            this.index = this.zzbtg.indexOfChild(com_google_android_gms_internal_zzlh.getView());
            this.zzbtg.removeView(com_google_android_gms_internal_zzlh.getView());
            com_google_android_gms_internal_zzlh.zzah(true);
        }
    }

    @zzin
    private class zzd extends zzkc {
        final /* synthetic */ zzd zzbtd;

        private zzd(zzd com_google_android_gms_ads_internal_overlay_zzd) {
            this.zzbtd = com_google_android_gms_ads_internal_overlay_zzd;
        }

        public void onStop() {
        }

        public void zzew() {
            Bitmap zza = zzu.zzgh().zza(Integer.valueOf(this.zzbtd.zzbso.zzbtv.zzamj));
            if (zza != null) {
                zzkh.zzclc.post(new 1(this, zzu.zzfs().zza(this.zzbtd.mActivity, zza, this.zzbtd.zzbso.zzbtv.zzamh, this.zzbtd.zzbso.zzbtv.zzami)));
            }
        }
    }

    public zzd(Activity activity) {
        this.mActivity = activity;
        this.zzbsz = new zzs();
    }

    public void close() {
        this.zzbsy = 2;
        this.mActivity.finish();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
    }

    public void onBackPressed() {
        this.zzbsy = 0;
    }

    public void onCreate(Bundle bundle) {
        boolean z = false;
        this.mActivity.requestWindowFeature(1);
        if (bundle != null) {
            z = bundle.getBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", false);
        }
        this.zzbsu = z;
        try {
            this.zzbso = AdOverlayInfoParcel.zzb(this.mActivity.getIntent());
            if (this.zzbso == null) {
                throw new zza("Could not get info for ad overlay.");
            }
            if (this.zzbso.zzaow.zzcnl > 7500000) {
                this.zzbsy = 3;
            }
            if (this.mActivity.getIntent() != null) {
                this.zzbtc = this.mActivity.getIntent().getBooleanExtra("shouldCallOnOverlayOpened", true);
            }
            if (this.zzbso.zzbtv != null) {
                this.zzbsv = this.zzbso.zzbtv.zzame;
            } else {
                this.zzbsv = false;
            }
            if (((Boolean) zzdc.zzbca.get()).booleanValue() && this.zzbsv && this.zzbso.zzbtv.zzamj != -1) {
                Future future = (Future) new zzd().zzpy();
            }
            if (bundle == null) {
                if (this.zzbso.zzbtl != null && this.zzbtc) {
                    this.zzbso.zzbtl.zzdy();
                }
                if (!(this.zzbso.zzbts == 1 || this.zzbso.zzbtk == null)) {
                    this.zzbso.zzbtk.onAdClicked();
                }
            }
            this.zzbsw = new zzb(this.mActivity, this.zzbso.zzbtu);
            this.zzbsw.setId(1000);
            switch (this.zzbso.zzbts) {
                case 1:
                    zzaa(false);
                    return;
                case 2:
                    this.zzbsp = new zzc(this.zzbso.zzbtm);
                    zzaa(false);
                    return;
                case 3:
                    zzaa(true);
                    return;
                case 4:
                    if (this.zzbsu) {
                        this.zzbsy = 3;
                        this.mActivity.finish();
                        return;
                    } else if (!zzu.zzfn().zza(this.mActivity, this.zzbso.zzbtj, this.zzbso.zzbtr)) {
                        this.zzbsy = 3;
                        this.mActivity.finish();
                        return;
                    } else {
                        return;
                    }
                default:
                    throw new zza("Could not determine ad overlay type.");
            }
        } catch (zza e) {
            com.google.android.gms.ads.internal.util.client.zzb.zzcx(e.getMessage());
            this.zzbsy = 3;
            this.mActivity.finish();
        }
    }

    public void onDestroy() {
        if (this.zzbgf != null) {
            this.zzbsw.removeView(this.zzbgf.getView());
        }
        zzny();
    }

    public void onPause() {
        this.zzbsz.pause();
        zznu();
        if (this.zzbso.zzbtl != null) {
            this.zzbso.zzbtl.onPause();
        }
        if (this.zzbgf != null && (!this.mActivity.isFinishing() || this.zzbsp == null)) {
            zzu.zzfs().zzi(this.zzbgf);
        }
        zzny();
    }

    public void onRestart() {
    }

    public void onResume() {
        if (this.zzbso != null && this.zzbso.zzbts == 4) {
            if (this.zzbsu) {
                this.zzbsy = 3;
                this.mActivity.finish();
            } else {
                this.zzbsu = true;
            }
        }
        if (this.zzbso.zzbtl != null) {
            this.zzbso.zzbtl.onResume();
        }
        if (this.zzbgf == null || this.zzbgf.isDestroyed()) {
            com.google.android.gms.ads.internal.util.client.zzb.zzcx("The webview does not exit. Ignoring action.");
        } else {
            zzu.zzfs().zzj(this.zzbgf);
        }
        this.zzbsz.resume();
    }

    public void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean("com.google.android.gms.ads.internal.overlay.hasResumed", this.zzbsu);
    }

    public void onStart() {
    }

    public void onStop() {
        zzny();
    }

    public void setRequestedOrientation(int i) {
        this.mActivity.setRequestedOrientation(i);
    }

    public void zza(View view, CustomViewCallback customViewCallback) {
        this.zzbss = new FrameLayout(this.mActivity);
        this.zzbss.setBackgroundColor(-16777216);
        this.zzbss.addView(view, -1, -1);
        this.mActivity.setContentView(this.zzbss);
        zzdb();
        this.zzbst = customViewCallback;
        this.zzbsr = true;
    }

    public void zza(boolean z, boolean z2) {
        if (this.zzbsq != null) {
            this.zzbsq.zza(z, z2);
        }
    }

    protected void zzaa(boolean z) throws zza {
        if (!this.zzbta) {
            this.mActivity.requestWindowFeature(1);
        }
        Window window = this.mActivity.getWindow();
        if (window == null) {
            throw new zza("Invalid activity, no window available.");
        }
        if (!this.zzbsv || (this.zzbso.zzbtv != null && this.zzbso.zzbtv.zzamf)) {
            window.setFlags(1024, 1024);
        }
        zzli zzuj = this.zzbso.zzbtm.zzuj();
        boolean zzho = zzuj != null ? zzuj.zzho() : false;
        this.zzbsx = false;
        if (zzho) {
            if (this.zzbso.orientation == zzu.zzfs().zztj()) {
                this.zzbsx = this.mActivity.getResources().getConfiguration().orientation == 1;
            } else if (this.zzbso.orientation == zzu.zzfs().zztk()) {
                this.zzbsx = this.mActivity.getResources().getConfiguration().orientation == 2;
            }
        }
        com.google.android.gms.ads.internal.util.client.zzb.zzcv("Delay onShow to next orientation change: " + this.zzbsx);
        setRequestedOrientation(this.zzbso.orientation);
        if (zzu.zzfs().zza(window)) {
            com.google.android.gms.ads.internal.util.client.zzb.zzcv("Hardware acceleration on the AdActivity window enabled.");
        }
        if (this.zzbsv) {
            this.zzbsw.setBackgroundColor(zzbsn);
        } else {
            this.zzbsw.setBackgroundColor(-16777216);
        }
        this.mActivity.setContentView(this.zzbsw);
        zzdb();
        if (z) {
            this.zzbgf = zzu.zzfr().zza(this.mActivity, this.zzbso.zzbtm.zzdn(), true, zzho, null, this.zzbso.zzaow, null, null, this.zzbso.zzbtm.zzug());
            this.zzbgf.zzuj().zza(null, null, this.zzbso.zzbtn, this.zzbso.zzbtr, true, this.zzbso.zzbtt, null, this.zzbso.zzbtm.zzuj().zzux(), null, null);
            this.zzbgf.zzuj().zza(new 1(this));
            if (this.zzbso.url != null) {
                this.zzbgf.loadUrl(this.zzbso.url);
            } else if (this.zzbso.zzbtq != null) {
                this.zzbgf.loadDataWithBaseURL(this.zzbso.zzbto, this.zzbso.zzbtq, "text/html", "UTF-8", null);
            } else {
                throw new zza("No URL or HTML to display in ad overlay.");
            }
            if (this.zzbso.zzbtm != null) {
                this.zzbso.zzbtm.zzc(this);
            }
        } else {
            this.zzbgf = this.zzbso.zzbtm;
            this.zzbgf.setContext(this.mActivity);
        }
        this.zzbgf.zzb(this);
        ViewParent parent = this.zzbgf.getParent();
        if (parent != null && (parent instanceof ViewGroup)) {
            ((ViewGroup) parent).removeView(this.zzbgf.getView());
        }
        if (this.zzbsv) {
            this.zzbgf.setBackgroundColor(zzbsn);
        }
        this.zzbsw.addView(this.zzbgf.getView(), -1, -1);
        if (!(z || this.zzbsx)) {
            zzoa();
        }
        zzz(zzho);
        if (this.zzbgf.zzuk()) {
            zza(zzho, true);
        }
        com.google.android.gms.ads.internal.zzd zzug = this.zzbgf.zzug();
        zzm com_google_android_gms_ads_internal_overlay_zzm = zzug != null ? zzug.zzakl : null;
        if (com_google_android_gms_ads_internal_overlay_zzm != null) {
            this.zzbsz = com_google_android_gms_ads_internal_overlay_zzm.zza(this.mActivity, this.zzbgf, this.zzbsw);
        } else {
            com.google.android.gms.ads.internal.util.client.zzb.zzcx("Appstreaming controller is null.");
        }
    }

    protected void zzaf(int i) {
        this.zzbgf.zzaf(i);
    }

    public void zzdb() {
        this.zzbta = true;
    }

    public void zzf(zzlh com_google_android_gms_internal_zzlh, Map<String, String> map) {
        this.zzbsz.zzf(com_google_android_gms_internal_zzlh, map);
    }

    public void zznu() {
        if (this.zzbso != null && this.zzbsr) {
            setRequestedOrientation(this.zzbso.orientation);
        }
        if (this.zzbss != null) {
            this.mActivity.setContentView(this.zzbsw);
            zzdb();
            this.zzbss.removeAllViews();
            this.zzbss = null;
        }
        if (this.zzbst != null) {
            this.zzbst.onCustomViewHidden();
            this.zzbst = null;
        }
        this.zzbsr = false;
    }

    public void zznv() {
        this.zzbsy = 1;
        this.mActivity.finish();
    }

    public boolean zznw() {
        boolean z = true;
        this.zzbsy = 0;
        if (this.zzbgf != null) {
            if (!(this.zzbgf.zzou() && this.zzbsz.zzou())) {
                z = false;
            }
            if (!z) {
                this.zzbgf.zza("onbackblocked", Collections.emptyMap());
            }
        }
        return z;
    }

    public void zznx() {
        this.zzbsw.removeView(this.zzbsq);
        zzz(true);
    }

    protected void zzny() {
        if (this.mActivity.isFinishing() && !this.zzbtb) {
            this.zzbtb = true;
            if (this.zzbgf != null) {
                zzaf(this.zzbsy);
                this.zzbsw.removeView(this.zzbgf.getView());
                if (this.zzbsp != null) {
                    this.zzbgf.setContext(this.zzbsp.zzagf);
                    this.zzbgf.zzah(false);
                    this.zzbsp.zzbtg.addView(this.zzbgf.getView(), this.zzbsp.index, this.zzbsp.zzbtf);
                    this.zzbsp = null;
                } else if (this.mActivity.getApplicationContext() != null) {
                    this.zzbgf.setContext(this.mActivity.getApplicationContext());
                }
                this.zzbgf = null;
            }
            if (!(this.zzbso == null || this.zzbso.zzbtl == null)) {
                this.zzbso.zzbtl.zzdx();
            }
            this.zzbsz.destroy();
        }
    }

    public void zznz() {
        if (this.zzbsx) {
            this.zzbsx = false;
            zzoa();
        }
    }

    protected void zzoa() {
        this.zzbgf.zzoa();
    }

    public void zzob() {
        this.zzbsw.disable();
    }

    public void zzz(boolean z) {
        this.zzbsq = new zzo(this.mActivity, z ? 50 : 32, this);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(10);
        layoutParams.addRule(z ? 11 : 9);
        this.zzbsq.zza(z, this.zzbso.zzbtp);
        this.zzbsw.addView(this.zzbsq, layoutParams);
    }
}

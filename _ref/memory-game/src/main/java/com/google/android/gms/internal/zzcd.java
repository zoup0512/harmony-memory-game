package com.google.android.gms.internal;

import android.app.KeyguardManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import java.lang.ref.WeakReference;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzin
public abstract class zzcd implements OnGlobalLayoutListener, OnScrollChangedListener {
    protected final Object zzail = new Object();
    private boolean zzane = false;
    private zzkr zzaqb;
    private final WeakReference<zzju> zzaqh;
    private WeakReference<ViewTreeObserver> zzaqi;
    private final zzck zzaqj;
    protected final zzcf zzaqk;
    private final Context zzaql;
    private final WindowManager zzaqm;
    private final PowerManager zzaqn;
    private final KeyguardManager zzaqo;
    @Nullable
    private zzch zzaqp;
    private boolean zzaqq;
    private boolean zzaqr = false;
    private boolean zzaqs;
    private boolean zzaqt;
    private boolean zzaqu;
    @Nullable
    BroadcastReceiver zzaqv;
    private final HashSet<zzce> zzaqw = new HashSet();
    private final zzep zzaqx = new 2(this);
    private final zzep zzaqy = new 3(this);
    private final zzep zzaqz = new 4(this);

    public zzcd(Context context, AdSizeParcel adSizeParcel, zzju com_google_android_gms_internal_zzju, VersionInfoParcel versionInfoParcel, zzck com_google_android_gms_internal_zzck) {
        this.zzaqh = new WeakReference(com_google_android_gms_internal_zzju);
        this.zzaqj = com_google_android_gms_internal_zzck;
        this.zzaqi = new WeakReference(null);
        this.zzaqs = true;
        this.zzaqu = false;
        this.zzaqb = new zzkr(200);
        this.zzaqk = new zzcf(UUID.randomUUID().toString(), versionInfoParcel, adSizeParcel.zzaur, com_google_android_gms_internal_zzju.zzcie, com_google_android_gms_internal_zzju.zzho(), adSizeParcel.zzauu);
        this.zzaqm = (WindowManager) context.getSystemService("window");
        this.zzaqn = (PowerManager) context.getApplicationContext().getSystemService("power");
        this.zzaqo = (KeyguardManager) context.getSystemService("keyguard");
        this.zzaql = context;
    }

    protected void destroy() {
        synchronized (this.zzail) {
            zzhc();
            zzgx();
            this.zzaqs = false;
            zzgz();
        }
    }

    boolean isScreenOn() {
        return this.zzaqn.isScreenOn();
    }

    public void onGlobalLayout() {
        zzk(2);
    }

    public void onScrollChanged() {
        zzk(1);
    }

    public void pause() {
        synchronized (this.zzail) {
            this.zzane = true;
            zzk(3);
        }
    }

    public void resume() {
        synchronized (this.zzail) {
            this.zzane = false;
            zzk(3);
        }
    }

    public void stop() {
        synchronized (this.zzail) {
            this.zzaqr = true;
            zzk(3);
        }
    }

    protected int zza(int i, DisplayMetrics displayMetrics) {
        return (int) (((float) i) / displayMetrics.density);
    }

    protected void zza(View view, Map<String, String> map) {
        zzk(3);
    }

    public void zza(zzce com_google_android_gms_internal_zzce) {
        this.zzaqw.add(com_google_android_gms_internal_zzce);
    }

    public void zza(zzch com_google_android_gms_internal_zzch) {
        synchronized (this.zzail) {
            this.zzaqp = com_google_android_gms_internal_zzch;
        }
    }

    protected void zza(JSONObject jSONObject) {
        try {
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject2 = new JSONObject();
            jSONArray.put(jSONObject);
            jSONObject2.put("units", jSONArray);
            zzb(jSONObject2);
        } catch (Throwable th) {
            zzb.zzb("Skipping active view message.", th);
        }
    }

    protected abstract void zzb(JSONObject jSONObject);

    protected boolean zzb(@Nullable Map<String, String> map) {
        if (map == null) {
            return false;
        }
        String str = (String) map.get("hashCode");
        boolean z = !TextUtils.isEmpty(str) && str.equals(this.zzaqk.zzhn());
        return z;
    }

    protected void zzc(zzft com_google_android_gms_internal_zzft) {
        com_google_android_gms_internal_zzft.zza("/updateActiveView", this.zzaqx);
        com_google_android_gms_internal_zzft.zza("/untrackActiveViewUnit", this.zzaqy);
        com_google_android_gms_internal_zzft.zza("/visibilityChanged", this.zzaqz);
    }

    protected JSONObject zzd(@Nullable View view) throws JSONException {
        if (view == null) {
            return zzhf();
        }
        boolean isAttachedToWindow = zzu.zzfs().isAttachedToWindow(view);
        int[] iArr = new int[2];
        int[] iArr2 = new int[2];
        try {
            view.getLocationOnScreen(iArr);
            view.getLocationInWindow(iArr2);
        } catch (Throwable e) {
            zzb.zzb("Failure getting view location.", e);
        }
        DisplayMetrics displayMetrics = view.getContext().getResources().getDisplayMetrics();
        Rect rect = new Rect();
        rect.left = iArr[0];
        rect.top = iArr[1];
        rect.right = rect.left + view.getWidth();
        rect.bottom = rect.top + view.getHeight();
        Rect rect2 = new Rect();
        rect2.right = this.zzaqm.getDefaultDisplay().getWidth();
        rect2.bottom = this.zzaqm.getDefaultDisplay().getHeight();
        Rect rect3 = new Rect();
        boolean globalVisibleRect = view.getGlobalVisibleRect(rect3, null);
        Rect rect4 = new Rect();
        boolean localVisibleRect = view.getLocalVisibleRect(rect4);
        Rect rect5 = new Rect();
        view.getHitRect(rect5);
        JSONObject zzhd = zzhd();
        zzhd.put("windowVisibility", view.getWindowVisibility()).put("isAttachedToWindow", isAttachedToWindow).put("viewBox", new JSONObject().put("top", zza(rect2.top, displayMetrics)).put("bottom", zza(rect2.bottom, displayMetrics)).put("left", zza(rect2.left, displayMetrics)).put("right", zza(rect2.right, displayMetrics))).put("adBox", new JSONObject().put("top", zza(rect.top, displayMetrics)).put("bottom", zza(rect.bottom, displayMetrics)).put("left", zza(rect.left, displayMetrics)).put("right", zza(rect.right, displayMetrics))).put("globalVisibleBox", new JSONObject().put("top", zza(rect3.top, displayMetrics)).put("bottom", zza(rect3.bottom, displayMetrics)).put("left", zza(rect3.left, displayMetrics)).put("right", zza(rect3.right, displayMetrics))).put("globalVisibleBoxVisible", globalVisibleRect).put("localVisibleBox", new JSONObject().put("top", zza(rect4.top, displayMetrics)).put("bottom", zza(rect4.bottom, displayMetrics)).put("left", zza(rect4.left, displayMetrics)).put("right", zza(rect4.right, displayMetrics))).put("localVisibleBoxVisible", localVisibleRect).put("hitBox", new JSONObject().put("top", zza(rect5.top, displayMetrics)).put("bottom", zza(rect5.bottom, displayMetrics)).put("left", zza(rect5.left, displayMetrics)).put("right", zza(rect5.right, displayMetrics))).put("screenDensity", (double) displayMetrics.density).put("isVisible", zzu.zzfq().zza(view, this.zzaqn, this.zzaqo));
        return zzhd;
    }

    protected void zzd(zzft com_google_android_gms_internal_zzft) {
        com_google_android_gms_internal_zzft.zzb("/visibilityChanged", this.zzaqz);
        com_google_android_gms_internal_zzft.zzb("/untrackActiveViewUnit", this.zzaqy);
        com_google_android_gms_internal_zzft.zzb("/updateActiveView", this.zzaqx);
    }

    protected void zzgw() {
        synchronized (this.zzail) {
            if (this.zzaqv != null) {
                return;
            }
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.SCREEN_ON");
            intentFilter.addAction("android.intent.action.SCREEN_OFF");
            this.zzaqv = new 1(this);
            this.zzaql.registerReceiver(this.zzaqv, intentFilter);
        }
    }

    protected void zzgx() {
        synchronized (this.zzail) {
            if (this.zzaqv != null) {
                try {
                    this.zzaql.unregisterReceiver(this.zzaqv);
                } catch (Throwable e) {
                    zzb.zzb("Failed trying to unregister the receiver", e);
                } catch (Throwable e2) {
                    zzu.zzft().zzb(e2, true);
                }
                this.zzaqv = null;
            }
        }
    }

    public void zzgy() {
        synchronized (this.zzail) {
            if (this.zzaqs) {
                this.zzaqt = true;
                try {
                    zza(zzhg());
                } catch (Throwable e) {
                    zzb.zzb("JSON failure while processing active view data.", e);
                } catch (Throwable e2) {
                    zzb.zzb("Failure while processing active view data.", e2);
                }
                String str = "Untracking ad unit: ";
                String valueOf = String.valueOf(this.zzaqk.zzhn());
                zzb.zzcv(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            }
        }
    }

    protected void zzgz() {
        if (this.zzaqp != null) {
            this.zzaqp.zza(this);
        }
    }

    public boolean zzha() {
        boolean z;
        synchronized (this.zzail) {
            z = this.zzaqs;
        }
        return z;
    }

    protected void zzhb() {
        View zzhh = this.zzaqj.zzhj().zzhh();
        if (zzhh != null) {
            ViewTreeObserver viewTreeObserver = (ViewTreeObserver) this.zzaqi.get();
            ViewTreeObserver viewTreeObserver2 = zzhh.getViewTreeObserver();
            if (viewTreeObserver2 != viewTreeObserver) {
                zzhc();
                if (!this.zzaqq || (viewTreeObserver != null && viewTreeObserver.isAlive())) {
                    this.zzaqq = true;
                    viewTreeObserver2.addOnScrollChangedListener(this);
                    viewTreeObserver2.addOnGlobalLayoutListener(this);
                }
                this.zzaqi = new WeakReference(viewTreeObserver2);
            }
        }
    }

    protected void zzhc() {
        ViewTreeObserver viewTreeObserver = (ViewTreeObserver) this.zzaqi.get();
        if (viewTreeObserver != null && viewTreeObserver.isAlive()) {
            viewTreeObserver.removeOnScrollChangedListener(this);
            viewTreeObserver.removeGlobalOnLayoutListener(this);
        }
    }

    protected JSONObject zzhd() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("afmaVersion", this.zzaqk.zzhl()).put("activeViewJSON", this.zzaqk.zzhm()).put("timestamp", zzu.zzfu().elapsedRealtime()).put("adFormat", this.zzaqk.zzhk()).put("hashCode", this.zzaqk.zzhn()).put("isMraid", this.zzaqk.zzho()).put("isStopped", this.zzaqr).put("isPaused", this.zzane).put("isScreenOn", isScreenOn()).put("isNative", this.zzaqk.zzhp());
        return jSONObject;
    }

    protected abstract boolean zzhe();

    protected JSONObject zzhf() throws JSONException {
        return zzhd().put("isAttachedToWindow", false).put("isScreenOn", isScreenOn()).put("isVisible", false);
    }

    protected JSONObject zzhg() throws JSONException {
        JSONObject zzhd = zzhd();
        zzhd.put("doneReasonCode", "u");
        return zzhd;
    }

    protected void zzj(boolean z) {
        Iterator it = this.zzaqw.iterator();
        while (it.hasNext()) {
            ((zzce) it.next()).zza(this, z);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void zzk(int r8) {
        /*
        r7 = this;
        r0 = 0;
        r1 = 1;
        r3 = r7.zzail;
        monitor-enter(r3);
        r2 = r7.zzhe();	 Catch:{ all -> 0x0043 }
        if (r2 == 0) goto L_0x000f;
    L_0x000b:
        r2 = r7.zzaqs;	 Catch:{ all -> 0x0043 }
        if (r2 != 0) goto L_0x0011;
    L_0x000f:
        monitor-exit(r3);	 Catch:{ all -> 0x0043 }
    L_0x0010:
        return;
    L_0x0011:
        r2 = r7.zzaqj;	 Catch:{ all -> 0x0043 }
        r4 = r2.zzhh();	 Catch:{ all -> 0x0043 }
        if (r4 == 0) goto L_0x0046;
    L_0x0019:
        r2 = com.google.android.gms.ads.internal.zzu.zzfq();	 Catch:{ all -> 0x0043 }
        r5 = r7.zzaqn;	 Catch:{ all -> 0x0043 }
        r6 = r7.zzaqo;	 Catch:{ all -> 0x0043 }
        r2 = r2.zza(r4, r5, r6);	 Catch:{ all -> 0x0043 }
        if (r2 == 0) goto L_0x0046;
    L_0x0027:
        r2 = new android.graphics.Rect;	 Catch:{ all -> 0x0043 }
        r2.<init>();	 Catch:{ all -> 0x0043 }
        r5 = 0;
        r2 = r4.getGlobalVisibleRect(r2, r5);	 Catch:{ all -> 0x0043 }
        if (r2 == 0) goto L_0x0046;
    L_0x0033:
        r2 = r1;
    L_0x0034:
        r7.zzaqu = r2;	 Catch:{ all -> 0x0043 }
        r5 = r7.zzaqj;	 Catch:{ all -> 0x0043 }
        r5 = r5.zzhi();	 Catch:{ all -> 0x0043 }
        if (r5 == 0) goto L_0x0048;
    L_0x003e:
        r7.zzgy();	 Catch:{ all -> 0x0043 }
        monitor-exit(r3);	 Catch:{ all -> 0x0043 }
        goto L_0x0010;
    L_0x0043:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0043 }
        throw r0;
    L_0x0046:
        r2 = r0;
        goto L_0x0034;
    L_0x0048:
        if (r8 != r1) goto L_0x004b;
    L_0x004a:
        r0 = r1;
    L_0x004b:
        if (r0 == 0) goto L_0x005b;
    L_0x004d:
        r0 = r7.zzaqb;	 Catch:{ all -> 0x0043 }
        r0 = r0.tryAcquire();	 Catch:{ all -> 0x0043 }
        if (r0 != 0) goto L_0x005b;
    L_0x0055:
        r0 = r7.zzaqu;	 Catch:{ all -> 0x0043 }
        if (r2 != r0) goto L_0x005b;
    L_0x0059:
        monitor-exit(r3);	 Catch:{ all -> 0x0043 }
        goto L_0x0010;
    L_0x005b:
        if (r2 != 0) goto L_0x0065;
    L_0x005d:
        r0 = r7.zzaqu;	 Catch:{ all -> 0x0043 }
        if (r0 != 0) goto L_0x0065;
    L_0x0061:
        if (r8 != r1) goto L_0x0065;
    L_0x0063:
        monitor-exit(r3);	 Catch:{ all -> 0x0043 }
        goto L_0x0010;
    L_0x0065:
        r0 = r7.zzd(r4);	 Catch:{ JSONException -> 0x007b, RuntimeException -> 0x0074 }
        r7.zza(r0);	 Catch:{ JSONException -> 0x007b, RuntimeException -> 0x0074 }
    L_0x006c:
        r7.zzhb();	 Catch:{ all -> 0x0043 }
        r7.zzgz();	 Catch:{ all -> 0x0043 }
        monitor-exit(r3);	 Catch:{ all -> 0x0043 }
        goto L_0x0010;
    L_0x0074:
        r0 = move-exception;
    L_0x0075:
        r1 = "Active view update failed.";
        com.google.android.gms.ads.internal.util.client.zzb.zza(r1, r0);	 Catch:{ all -> 0x0043 }
        goto L_0x006c;
    L_0x007b:
        r0 = move-exception;
        goto L_0x0075;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcd.zzk(int):void");
    }
}

package com.google.android.gms.ads.internal.formats;

import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzdc;
import com.google.android.gms.internal.zzdt.zza;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzkh;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

@zzin
public class zzk extends zza implements OnClickListener, OnTouchListener, OnGlobalLayoutListener, OnScrollChangedListener {
    private final Object zzail = new Object();
    @Nullable
    private FrameLayout zzaiz;
    @Nullable
    private zzh zzbfp;
    private final FrameLayout zzbgt;
    private Map<String, WeakReference<View>> zzbgu = new HashMap();
    @Nullable
    private zzb zzbgv;
    boolean zzbgw = false;
    int zzbgx;
    int zzbgy;

    public zzk(FrameLayout frameLayout, FrameLayout frameLayout2) {
        this.zzbgt = frameLayout;
        this.zzaiz = frameLayout2;
        zzu.zzgk().zza(this.zzbgt, (OnGlobalLayoutListener) this);
        zzu.zzgk().zza(this.zzbgt, (OnScrollChangedListener) this);
        this.zzbgt.setOnTouchListener(this);
        this.zzbgt.setOnClickListener(this);
    }

    public void destroy() {
        synchronized (this.zzail) {
            if (this.zzaiz != null) {
                this.zzaiz.removeAllViews();
            }
            this.zzaiz = null;
            this.zzbgu = null;
            this.zzbgv = null;
            this.zzbfp = null;
        }
    }

    int getMeasuredHeight() {
        return this.zzbgt.getMeasuredHeight();
    }

    int getMeasuredWidth() {
        return this.zzbgt.getMeasuredWidth();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onClick(android.view.View r10) {
        /*
        r9 = this;
        r6 = r9.zzail;
        monitor-enter(r6);
        r0 = r9.zzbfp;	 Catch:{ all -> 0x0093 }
        if (r0 != 0) goto L_0x0009;
    L_0x0007:
        monitor-exit(r6);	 Catch:{ all -> 0x0093 }
    L_0x0008:
        return;
    L_0x0009:
        r3 = new org.json.JSONObject;	 Catch:{ all -> 0x0093 }
        r3.<init>();	 Catch:{ all -> 0x0093 }
        r0 = r9.zzbgu;	 Catch:{ all -> 0x0093 }
        r0 = r0.entrySet();	 Catch:{ all -> 0x0093 }
        r2 = r0.iterator();	 Catch:{ all -> 0x0093 }
    L_0x0018:
        r0 = r2.hasNext();	 Catch:{ all -> 0x0093 }
        if (r0 == 0) goto L_0x009c;
    L_0x001e:
        r0 = r2.next();	 Catch:{ all -> 0x0093 }
        r0 = (java.util.Map.Entry) r0;	 Catch:{ all -> 0x0093 }
        r1 = r0.getValue();	 Catch:{ all -> 0x0093 }
        r1 = (java.lang.ref.WeakReference) r1;	 Catch:{ all -> 0x0093 }
        r1 = r1.get();	 Catch:{ all -> 0x0093 }
        r1 = (android.view.View) r1;	 Catch:{ all -> 0x0093 }
        if (r1 == 0) goto L_0x0018;
    L_0x0032:
        r4 = r9.zzi(r1);	 Catch:{ all -> 0x0093 }
        r5 = new org.json.JSONObject;	 Catch:{ all -> 0x0093 }
        r5.<init>();	 Catch:{ all -> 0x0093 }
        r7 = "width";
        r8 = r1.getWidth();	 Catch:{ JSONException -> 0x0078 }
        r8 = r9.zzx(r8);	 Catch:{ JSONException -> 0x0078 }
        r5.put(r7, r8);	 Catch:{ JSONException -> 0x0078 }
        r7 = "height";
        r1 = r1.getHeight();	 Catch:{ JSONException -> 0x0078 }
        r1 = r9.zzx(r1);	 Catch:{ JSONException -> 0x0078 }
        r5.put(r7, r1);	 Catch:{ JSONException -> 0x0078 }
        r1 = "x";
        r7 = r4.x;	 Catch:{ JSONException -> 0x0078 }
        r7 = r9.zzx(r7);	 Catch:{ JSONException -> 0x0078 }
        r5.put(r1, r7);	 Catch:{ JSONException -> 0x0078 }
        r1 = "y";
        r4 = r4.y;	 Catch:{ JSONException -> 0x0078 }
        r4 = r9.zzx(r4);	 Catch:{ JSONException -> 0x0078 }
        r5.put(r1, r4);	 Catch:{ JSONException -> 0x0078 }
        r1 = r0.getKey();	 Catch:{ JSONException -> 0x0078 }
        r1 = (java.lang.String) r1;	 Catch:{ JSONException -> 0x0078 }
        r3.put(r1, r5);	 Catch:{ JSONException -> 0x0078 }
        goto L_0x0018;
    L_0x0078:
        r1 = move-exception;
        r1 = "Unable to get view rectangle for view ";
        r0 = r0.getKey();	 Catch:{ all -> 0x0093 }
        r0 = (java.lang.String) r0;	 Catch:{ all -> 0x0093 }
        r0 = java.lang.String.valueOf(r0);	 Catch:{ all -> 0x0093 }
        r4 = r0.length();	 Catch:{ all -> 0x0093 }
        if (r4 == 0) goto L_0x0096;
    L_0x008b:
        r0 = r1.concat(r0);	 Catch:{ all -> 0x0093 }
    L_0x008f:
        com.google.android.gms.ads.internal.util.client.zzb.zzcx(r0);	 Catch:{ all -> 0x0093 }
        goto L_0x0018;
    L_0x0093:
        r0 = move-exception;
        monitor-exit(r6);	 Catch:{ all -> 0x0093 }
        throw r0;
    L_0x0096:
        r0 = new java.lang.String;	 Catch:{ all -> 0x0093 }
        r0.<init>(r1);	 Catch:{ all -> 0x0093 }
        goto L_0x008f;
    L_0x009c:
        r4 = new org.json.JSONObject;	 Catch:{ all -> 0x0093 }
        r4.<init>();	 Catch:{ all -> 0x0093 }
        r0 = "x";
        r1 = r9.zzbgx;	 Catch:{ JSONException -> 0x0109 }
        r1 = r9.zzx(r1);	 Catch:{ JSONException -> 0x0109 }
        r4.put(r0, r1);	 Catch:{ JSONException -> 0x0109 }
        r0 = "y";
        r1 = r9.zzbgy;	 Catch:{ JSONException -> 0x0109 }
        r1 = r9.zzx(r1);	 Catch:{ JSONException -> 0x0109 }
        r4.put(r0, r1);	 Catch:{ JSONException -> 0x0109 }
    L_0x00b9:
        r5 = new org.json.JSONObject;	 Catch:{ all -> 0x0093 }
        r5.<init>();	 Catch:{ all -> 0x0093 }
        r0 = "width";
        r1 = r9.getMeasuredWidth();	 Catch:{ JSONException -> 0x0110 }
        r1 = r9.zzx(r1);	 Catch:{ JSONException -> 0x0110 }
        r5.put(r0, r1);	 Catch:{ JSONException -> 0x0110 }
        r0 = "height";
        r1 = r9.getMeasuredHeight();	 Catch:{ JSONException -> 0x0110 }
        r1 = r9.zzx(r1);	 Catch:{ JSONException -> 0x0110 }
        r5.put(r0, r1);	 Catch:{ JSONException -> 0x0110 }
    L_0x00d9:
        r0 = r9.zzbgv;	 Catch:{ all -> 0x0093 }
        if (r0 == 0) goto L_0x011f;
    L_0x00dd:
        r0 = r9.zzbgv;	 Catch:{ all -> 0x0093 }
        r0 = r0.zzks();	 Catch:{ all -> 0x0093 }
        r0 = r0.equals(r10);	 Catch:{ all -> 0x0093 }
        if (r0 == 0) goto L_0x011f;
    L_0x00e9:
        r0 = r9.zzbfp;	 Catch:{ all -> 0x0093 }
        r0 = r0 instanceof com.google.android.gms.ads.internal.formats.zzg;	 Catch:{ all -> 0x0093 }
        if (r0 == 0) goto L_0x0117;
    L_0x00ef:
        r0 = r9.zzbfp;	 Catch:{ all -> 0x0093 }
        r0 = (com.google.android.gms.ads.internal.formats.zzg) r0;	 Catch:{ all -> 0x0093 }
        r0 = r0.zzla();	 Catch:{ all -> 0x0093 }
        if (r0 == 0) goto L_0x0117;
    L_0x00f9:
        r0 = r9.zzbfp;	 Catch:{ all -> 0x0093 }
        r0 = (com.google.android.gms.ads.internal.formats.zzg) r0;	 Catch:{ all -> 0x0093 }
        r0 = r0.zzla();	 Catch:{ all -> 0x0093 }
        r1 = "1007";
        r0.zza(r1, r3, r4, r5);	 Catch:{ all -> 0x0093 }
    L_0x0106:
        monitor-exit(r6);	 Catch:{ all -> 0x0093 }
        goto L_0x0008;
    L_0x0109:
        r0 = move-exception;
        r0 = "Unable to get click location";
        com.google.android.gms.ads.internal.util.client.zzb.zzcx(r0);	 Catch:{ all -> 0x0093 }
        goto L_0x00b9;
    L_0x0110:
        r0 = move-exception;
        r0 = "Unable to get native ad view bounding box";
        com.google.android.gms.ads.internal.util.client.zzb.zzcx(r0);	 Catch:{ all -> 0x0093 }
        goto L_0x00d9;
    L_0x0117:
        r0 = r9.zzbfp;	 Catch:{ all -> 0x0093 }
        r1 = "1007";
        r0.zza(r1, r3, r4, r5);	 Catch:{ all -> 0x0093 }
        goto L_0x0106;
    L_0x011f:
        r0 = r9.zzbfp;	 Catch:{ all -> 0x0093 }
        r2 = r9.zzbgu;	 Catch:{ all -> 0x0093 }
        r1 = r10;
        r0.zza(r1, r2, r3, r4, r5);	 Catch:{ all -> 0x0093 }
        goto L_0x0106;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.formats.zzk.onClick(android.view.View):void");
    }

    public void onGlobalLayout() {
        synchronized (this.zzail) {
            if (this.zzbgw) {
                int measuredWidth = getMeasuredWidth();
                int measuredHeight = getMeasuredHeight();
                if (!(measuredWidth == 0 || measuredHeight == 0 || this.zzaiz == null)) {
                    this.zzaiz.setLayoutParams(new LayoutParams(measuredWidth, measuredHeight));
                    this.zzbgw = false;
                }
            }
            if (this.zzbfp != null) {
                this.zzbfp.zzg(this.zzbgt);
            }
        }
    }

    public void onScrollChanged() {
        synchronized (this.zzail) {
            if (this.zzbfp != null) {
                this.zzbfp.zzg(this.zzbgt);
            }
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        synchronized (this.zzail) {
            if (this.zzbfp == null) {
            } else {
                Point zzc = zzc(motionEvent);
                this.zzbgx = zzc.x;
                this.zzbgy = zzc.y;
                MotionEvent obtain = MotionEvent.obtain(motionEvent);
                obtain.setLocation((float) zzc.x, (float) zzc.y);
                this.zzbfp.zzb(obtain);
                obtain.recycle();
            }
        }
        return false;
    }

    public zzd zzap(String str) {
        zzd zzac;
        synchronized (this.zzail) {
            Object obj;
            WeakReference weakReference = (WeakReference) this.zzbgu.get(str);
            if (weakReference == null) {
                obj = null;
            } else {
                View view = (View) weakReference.get();
            }
            zzac = zze.zzac(obj);
        }
        return zzac;
    }

    Point zzc(MotionEvent motionEvent) {
        int[] iArr = new int[2];
        this.zzbgt.getLocationOnScreen(iArr);
        return new Point((int) (motionEvent.getRawX() - ((float) iArr[0])), (int) (motionEvent.getRawY() - ((float) iArr[1])));
    }

    @Nullable
    zzb zzc(zzi com_google_android_gms_ads_internal_formats_zzi) {
        return com_google_android_gms_ads_internal_formats_zzi.zza((OnClickListener) this);
    }

    public void zzc(String str, zzd com_google_android_gms_dynamic_zzd) {
        View view = (View) zze.zzad(com_google_android_gms_dynamic_zzd);
        synchronized (this.zzail) {
            if (view == null) {
                this.zzbgu.remove(str);
            } else {
                this.zzbgu.put(str, new WeakReference(view));
                view.setOnTouchListener(this);
                view.setClickable(true);
                view.setOnClickListener(this);
            }
        }
    }

    public void zze(zzd com_google_android_gms_dynamic_zzd) {
        synchronized (this.zzail) {
            zzh(null);
            Object zzad = zze.zzad(com_google_android_gms_dynamic_zzd);
            if (zzad instanceof zzi) {
                if (this.zzaiz != null) {
                    this.zzaiz.setLayoutParams(new LayoutParams(0, 0));
                    this.zzbgt.requestLayout();
                }
                this.zzbgw = true;
                zzi com_google_android_gms_ads_internal_formats_zzi = (zzi) zzad;
                if (this.zzbfp != null && ((Boolean) zzdc.zzbch.get()).booleanValue()) {
                    this.zzbfp.zzb(this.zzbgt, this.zzbgu);
                }
                if ((this.zzbfp instanceof zzg) && ((zzg) this.zzbfp).zzkz()) {
                    ((zzg) this.zzbfp).zzc(com_google_android_gms_ads_internal_formats_zzi);
                } else {
                    this.zzbfp = com_google_android_gms_ads_internal_formats_zzi;
                    if (com_google_android_gms_ads_internal_formats_zzi instanceof zzg) {
                        ((zzg) com_google_android_gms_ads_internal_formats_zzi).zzc(null);
                    }
                }
                if (((Boolean) zzdc.zzbch.get()).booleanValue()) {
                    this.zzaiz.setClickable(false);
                }
                this.zzaiz.removeAllViews();
                this.zzbgv = zzc(com_google_android_gms_ads_internal_formats_zzi);
                if (this.zzbgv != null) {
                    this.zzbgu.put("1007", new WeakReference(this.zzbgv.zzks()));
                    this.zzaiz.addView(this.zzbgv);
                }
                zzkh.zzclc.post(new 1(this, com_google_android_gms_ads_internal_formats_zzi));
                com_google_android_gms_ads_internal_formats_zzi.zza(this.zzbgt, this.zzbgu, (OnTouchListener) this, (OnClickListener) this);
                zzh(this.zzbgt);
                return;
            }
            zzb.zzcx("Not an instance of native engine. This is most likely a transient error");
        }
    }

    void zzh(@Nullable View view) {
        if (this.zzbfp != null) {
            zzh zzla = this.zzbfp instanceof zzg ? ((zzg) this.zzbfp).zzla() : this.zzbfp;
            if (zzla != null) {
                zzla.zzh(view);
            }
        }
    }

    Point zzi(View view) {
        if (this.zzbgv == null || !this.zzbgv.zzks().equals(view)) {
            Point point = new Point();
            view.getGlobalVisibleRect(new Rect(), point);
            return point;
        }
        Point point2 = new Point();
        this.zzbgt.getGlobalVisibleRect(new Rect(), point2);
        Point point3 = new Point();
        view.getGlobalVisibleRect(new Rect(), point3);
        return new Point(point3.x - point2.x, point3.y - point2.y);
    }

    int zzx(int i) {
        return zzm.zziw().zzb(this.zzbfp.getContext(), i);
    }
}

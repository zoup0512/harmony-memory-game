package com.google.android.gms.ads.internal.formats;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.FrameLayout.LayoutParams;
import com.cmcm.adsdk.Const;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.formats.zzh.zza;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.internal.zzas;
import com.google.android.gms.internal.zzdc;
import com.google.android.gms.internal.zzih;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzlh;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONObject;

@zzin
public class zzi implements zzh {
    private final Context mContext;
    private final Object zzail = new Object();
    @Nullable
    private final VersionInfoParcel zzalo;
    private final zzq zzbfx;
    @Nullable
    private final JSONObject zzbga;
    @Nullable
    private final zzih zzbgb;
    @Nullable
    private final zza zzbgc;
    private final zzas zzbgd;
    private boolean zzbge;
    private zzlh zzbgf;
    private String zzbgg;
    @Nullable
    private String zzbgh;
    private WeakReference<View> zzbgi = null;

    public zzi(Context context, zzq com_google_android_gms_ads_internal_zzq, @Nullable zzih com_google_android_gms_internal_zzih, zzas com_google_android_gms_internal_zzas, @Nullable JSONObject jSONObject, @Nullable zza com_google_android_gms_ads_internal_formats_zzh_zza, @Nullable VersionInfoParcel versionInfoParcel, @Nullable String str) {
        this.mContext = context;
        this.zzbfx = com_google_android_gms_ads_internal_zzq;
        this.zzbgb = com_google_android_gms_internal_zzih;
        this.zzbgd = com_google_android_gms_internal_zzas;
        this.zzbga = jSONObject;
        this.zzbgc = com_google_android_gms_ads_internal_formats_zzh_zza;
        this.zzalo = versionInfoParcel;
        this.zzbgh = str;
    }

    public Context getContext() {
        return this.mContext;
    }

    public void recordImpression() {
        zzab.zzhi("recordImpression must be called on the main UI thread.");
        zzq(true);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(Const.KEY_JUHE, this.zzbga);
            jSONObject.put("ads_id", this.zzbgh);
            this.zzbgb.zza(new 2(this, jSONObject));
        } catch (Throwable e) {
            zzb.zzb("Unable to create impression JSON.", e);
        }
        this.zzbfx.zza((zzh) this);
    }

    public zzb zza(OnClickListener onClickListener) {
        zza zzkx = this.zzbgc.zzkx();
        if (zzkx == null) {
            return null;
        }
        zzb com_google_android_gms_ads_internal_formats_zzb = new zzb(this.mContext, zzkx);
        com_google_android_gms_ads_internal_formats_zzb.setLayoutParams(new LayoutParams(-1, -1));
        com_google_android_gms_ads_internal_formats_zzb.zzks().setOnClickListener(onClickListener);
        com_google_android_gms_ads_internal_formats_zzb.zzks().setContentDescription("Ad attribution icon");
        return com_google_android_gms_ads_internal_formats_zzb;
    }

    public void zza(View view, Map<String, WeakReference<View>> map, OnTouchListener onTouchListener, OnClickListener onClickListener) {
        if (((Boolean) zzdc.zzbci.get()).booleanValue()) {
            view.setOnTouchListener(onTouchListener);
            view.setClickable(true);
            view.setOnClickListener(onClickListener);
            for (Entry value : map.entrySet()) {
                View view2 = (View) ((WeakReference) value.getValue()).get();
                if (view2 != null) {
                    view2.setOnTouchListener(onTouchListener);
                    view2.setClickable(true);
                    view2.setOnClickListener(onClickListener);
                }
            }
        }
    }

    public void zza(View view, Map<String, WeakReference<View>> map, JSONObject jSONObject, JSONObject jSONObject2, JSONObject jSONObject3) {
        zzab.zzhi("performClick must be called on the main UI thread.");
        for (Entry entry : map.entrySet()) {
            if (view.equals((View) ((WeakReference) entry.getValue()).get())) {
                zza((String) entry.getKey(), jSONObject, jSONObject2, jSONObject3);
                return;
            }
        }
        if ("2".equals(this.zzbgc.zzkw())) {
            zza("2099", jSONObject, jSONObject2, jSONObject3);
        } else if (AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(this.zzbgc.zzkw())) {
            zza("1099", jSONObject, jSONObject2, jSONObject3);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void zza(java.lang.String r7, @android.support.annotation.Nullable org.json.JSONObject r8, @android.support.annotation.Nullable org.json.JSONObject r9, @android.support.annotation.Nullable org.json.JSONObject r10) {
        /*
        r6 = this;
        r0 = "performClick must be called on the main UI thread.";
        com.google.android.gms.common.internal.zzab.zzhi(r0);
        r1 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0096 }
        r1.<init>();	 Catch:{ JSONException -> 0x0096 }
        r0 = "asset";
        r1.put(r0, r7);	 Catch:{ JSONException -> 0x0096 }
        r0 = "template";
        r2 = r6.zzbgc;	 Catch:{ JSONException -> 0x0096 }
        r2 = r2.zzkw();	 Catch:{ JSONException -> 0x0096 }
        r1.put(r0, r2);	 Catch:{ JSONException -> 0x0096 }
        r2 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0096 }
        r2.<init>();	 Catch:{ JSONException -> 0x0096 }
        r0 = "ad";
        r3 = r6.zzbga;	 Catch:{ JSONException -> 0x0096 }
        r2.put(r0, r3);	 Catch:{ JSONException -> 0x0096 }
        r0 = "click";
        r2.put(r0, r1);	 Catch:{ JSONException -> 0x0096 }
        r3 = "has_custom_click_handler";
        r0 = r6.zzbfx;	 Catch:{ JSONException -> 0x0096 }
        r4 = r6.zzbgc;	 Catch:{ JSONException -> 0x0096 }
        r4 = r4.getCustomTemplateId();	 Catch:{ JSONException -> 0x0096 }
        r0 = r0.zzv(r4);	 Catch:{ JSONException -> 0x0096 }
        if (r0 == 0) goto L_0x008d;
    L_0x003b:
        r0 = 1;
    L_0x003c:
        r2.put(r3, r0);	 Catch:{ JSONException -> 0x0096 }
        if (r8 == 0) goto L_0x0047;
    L_0x0041:
        r0 = "view_rectangles";
        r2.put(r0, r8);	 Catch:{ JSONException -> 0x0096 }
    L_0x0047:
        if (r9 == 0) goto L_0x004e;
    L_0x0049:
        r0 = "click_point";
        r2.put(r0, r9);	 Catch:{ JSONException -> 0x0096 }
    L_0x004e:
        if (r10 == 0) goto L_0x0055;
    L_0x0050:
        r0 = "native_view_rectangle";
        r2.put(r0, r10);	 Catch:{ JSONException -> 0x0096 }
    L_0x0055:
        r0 = r6.zzbga;	 Catch:{ Exception -> 0x008f }
        r3 = "tracking_urls_and_actions";
        r0 = r0.optJSONObject(r3);	 Catch:{ Exception -> 0x008f }
        if (r0 != 0) goto L_0x0064;
    L_0x005f:
        r0 = new org.json.JSONObject;	 Catch:{ Exception -> 0x008f }
        r0.<init>();	 Catch:{ Exception -> 0x008f }
    L_0x0064:
        r3 = "click_string";
        r0 = r0.optString(r3);	 Catch:{ Exception -> 0x008f }
        r3 = "click_signals";
        r4 = r6.zzbgd;	 Catch:{ Exception -> 0x008f }
        r4 = r4.zzaw();	 Catch:{ Exception -> 0x008f }
        r5 = r6.mContext;	 Catch:{ Exception -> 0x008f }
        r0 = r4.zzb(r5, r0);	 Catch:{ Exception -> 0x008f }
        r1.put(r3, r0);	 Catch:{ Exception -> 0x008f }
    L_0x007b:
        r0 = "ads_id";
        r1 = r6.zzbgh;	 Catch:{ JSONException -> 0x0096 }
        r2.put(r0, r1);	 Catch:{ JSONException -> 0x0096 }
        r0 = r6.zzbgb;	 Catch:{ JSONException -> 0x0096 }
        r1 = new com.google.android.gms.ads.internal.formats.zzi$1;	 Catch:{ JSONException -> 0x0096 }
        r1.<init>(r6, r2);	 Catch:{ JSONException -> 0x0096 }
        r0.zza(r1);	 Catch:{ JSONException -> 0x0096 }
    L_0x008c:
        return;
    L_0x008d:
        r0 = 0;
        goto L_0x003c;
    L_0x008f:
        r0 = move-exception;
        r1 = "Exception obtaining click signals";
        com.google.android.gms.ads.internal.util.client.zzb.zzb(r1, r0);	 Catch:{ JSONException -> 0x0096 }
        goto L_0x007b;
    L_0x0096:
        r0 = move-exception;
        r1 = "Unable to create click JSON.";
        com.google.android.gms.ads.internal.util.client.zzb.zzb(r1, r0);
        goto L_0x008c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.formats.zzi.zza(java.lang.String, org.json.JSONObject, org.json.JSONObject, org.json.JSONObject):void");
    }

    public void zzb(MotionEvent motionEvent) {
        this.zzbgd.zza(motionEvent);
    }

    public void zzb(View view, Map<String, WeakReference<View>> map) {
        view.setOnTouchListener(null);
        view.setClickable(false);
        view.setOnClickListener(null);
        for (Entry value : map.entrySet()) {
            View view2 = (View) ((WeakReference) value.getValue()).get();
            if (view2 != null) {
                view2.setOnTouchListener(null);
                view2.setClickable(false);
                view2.setOnClickListener(null);
            }
        }
    }

    public void zzg(View view) {
        synchronized (this.zzail) {
            if (this.zzbge) {
            } else if (!view.isShown()) {
            } else if (view.getGlobalVisibleRect(new Rect(), null)) {
                recordImpression();
            }
        }
    }

    public void zzh(View view) {
        this.zzbgi = new WeakReference(view);
    }

    public zzlh zzlb() {
        this.zzbgf = zzld();
        this.zzbgf.getView().setVisibility(8);
        this.zzbgb.zza(new 3(this));
        return this.zzbgf;
    }

    public View zzlc() {
        return this.zzbgi != null ? (View) this.zzbgi.get() : null;
    }

    zzlh zzld() {
        return zzu.zzfr().zza(this.mContext, AdSizeParcel.zzk(this.mContext), false, false, this.zzbgd, this.zzalo);
    }

    protected void zzq(boolean z) {
        this.zzbge = z;
    }
}

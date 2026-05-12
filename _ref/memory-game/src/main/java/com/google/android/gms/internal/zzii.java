package com.google.android.gms.internal;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.RemoteException;
import android.text.TextUtils;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.internal.formats.zzc;
import com.google.android.gms.ads.internal.formats.zzf;
import com.google.android.gms.ads.internal.formats.zzh;
import com.google.android.gms.ads.internal.formats.zzi;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzq;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.internal.zzju.zza;
import com.yalantis.ucrop.util.FileUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzin
public class zzii implements Callable<zzju> {
    private static final long zzbyt = TimeUnit.SECONDS.toMillis(60);
    private final Context mContext;
    private final Object zzail = new Object();
    private final zzih zzbgb;
    private final zzas zzbgd;
    private final zza zzbxr;
    private int zzbyi;
    private final zzkn zzbzc;
    private final zzq zzbzd;
    private boolean zzbze;
    private List<String> zzbzf;
    private JSONObject zzbzg;

    public zzii(Context context, zzq com_google_android_gms_ads_internal_zzq, zzkn com_google_android_gms_internal_zzkn, zzas com_google_android_gms_internal_zzas, zza com_google_android_gms_internal_zzju_zza) {
        this.mContext = context;
        this.zzbzd = com_google_android_gms_ads_internal_zzq;
        this.zzbzc = com_google_android_gms_internal_zzkn;
        this.zzbxr = com_google_android_gms_internal_zzju_zza;
        this.zzbgd = com_google_android_gms_internal_zzas;
        this.zzbgb = zza(context, com_google_android_gms_internal_zzju_zza, com_google_android_gms_ads_internal_zzq, com_google_android_gms_internal_zzas);
        this.zzbgb.zzqg();
        this.zzbze = false;
        this.zzbyi = -2;
        this.zzbzf = null;
    }

    private zzh.zza zza(zza com_google_android_gms_internal_zzii_zza, JSONObject jSONObject, String str) throws ExecutionException, InterruptedException, JSONException {
        if (zzqs()) {
            return null;
        }
        JSONObject jSONObject2 = jSONObject.getJSONObject("tracking_urls_and_actions");
        String[] zzc = zzc(jSONObject2, "impression_tracking_urls");
        this.zzbzf = zzc == null ? null : Arrays.asList(zzc);
        this.zzbzg = jSONObject2.optJSONObject("active_view");
        zzh.zza zza = com_google_android_gms_internal_zzii_zza.zza(this, jSONObject);
        if (zza == null) {
            zzb.e("Failed to retrieve ad assets.");
            return null;
        }
        zza.zzb(new zzi(this.mContext, this.zzbzd, this.zzbgb, this.zzbgd, jSONObject, zza, this.zzbxr.zzcip.zzaow, str));
        return zza;
    }

    private zzky<zzc> zza(JSONObject jSONObject, boolean z, boolean z2) throws JSONException {
        String string = z ? jSONObject.getString("url") : jSONObject.optString("url");
        double optDouble = jSONObject.optDouble("scale", 1.0d);
        if (!TextUtils.isEmpty(string)) {
            return z2 ? new zzkw(new zzc(null, Uri.parse(string), optDouble)) : this.zzbzc.zza(string, new 6(this, z, optDouble, string));
        } else {
            zza(0, z);
            return new zzkw(null);
        }
    }

    private void zza(zzh.zza com_google_android_gms_ads_internal_formats_zzh_zza) {
        if (com_google_android_gms_ads_internal_formats_zzh_zza instanceof zzf) {
            zzf com_google_android_gms_ads_internal_formats_zzf = (zzf) com_google_android_gms_ads_internal_formats_zzh_zza;
            zzb com_google_android_gms_internal_zzii_zzb = new zzb(this);
            zzep 3 = new 3(this, com_google_android_gms_ads_internal_formats_zzf);
            com_google_android_gms_internal_zzii_zzb.zzbzz = 3;
            this.zzbgb.zza(new 4(this, 3));
        }
    }

    private zzju zzb(zzh.zza com_google_android_gms_ads_internal_formats_zzh_zza) {
        int i;
        synchronized (this.zzail) {
            i = this.zzbyi;
            if (com_google_android_gms_ads_internal_formats_zzh_zza == null && this.zzbyi == -2) {
                i = 0;
            }
        }
        return new zzju(this.zzbxr.zzcip.zzcar, null, this.zzbxr.zzciq.zzbnm, i, this.zzbxr.zzciq.zzbnn, this.zzbzf, this.zzbxr.zzciq.orientation, this.zzbxr.zzciq.zzbns, this.zzbxr.zzcip.zzcau, false, null, null, null, null, null, 0, this.zzbxr.zzapa, this.zzbxr.zzciq.zzcbx, this.zzbxr.zzcik, this.zzbxr.zzcil, this.zzbxr.zzciq.zzccd, this.zzbzg, i != -2 ? null : com_google_android_gms_ads_internal_formats_zzh_zza, null, null, null, this.zzbxr.zzciq.zzccq, this.zzbxr.zzciq.zzccr, null, this.zzbxr.zzciq.zzbnp);
    }

    private Integer zzb(JSONObject jSONObject, String str) {
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject(str);
            return Integer.valueOf(Color.rgb(jSONObject2.getInt("r"), jSONObject2.getInt("g"), jSONObject2.getInt("b")));
        } catch (JSONException e) {
            return null;
        }
    }

    private void zzb(zzdz com_google_android_gms_internal_zzdz, String str) {
        try {
            zzed zzv = this.zzbzd.zzv(com_google_android_gms_internal_zzdz.getCustomTemplateId());
            if (zzv != null) {
                zzv.zza(com_google_android_gms_internal_zzdz, str);
            }
        } catch (Throwable e) {
            zzb.zzd(new StringBuilder(String.valueOf(str).length() + 40).append("Failed to call onCustomClick for asset ").append(str).append(FileUtils.HIDDEN_PREFIX).toString(), e);
        }
    }

    private String[] zzc(JSONObject jSONObject, String str) throws JSONException {
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray == null) {
            return null;
        }
        String[] strArr = new String[optJSONArray.length()];
        for (int i = 0; i < optJSONArray.length(); i++) {
            strArr[i] = optJSONArray.getString(i);
        }
        return strArr;
    }

    private JSONObject zzcb(String str) throws ExecutionException, InterruptedException, TimeoutException, JSONException {
        if (zzqs()) {
            return null;
        }
        zzkv com_google_android_gms_internal_zzkv = new zzkv();
        this.zzbgb.zza(new 1(this, new zzb(this), com_google_android_gms_internal_zzkv, str));
        return (JSONObject) com_google_android_gms_internal_zzkv.get(zzbyt, TimeUnit.MILLISECONDS);
    }

    private static List<Drawable> zzh(List<zzc> list) throws RemoteException {
        List<Drawable> arrayList = new ArrayList();
        for (zzc zzkt : list) {
            arrayList.add((Drawable) zze.zzad(zzkt.zzkt()));
        }
        return arrayList;
    }

    public /* synthetic */ Object call() throws Exception {
        return zzqr();
    }

    zzih zza(Context context, zza com_google_android_gms_internal_zzju_zza, zzq com_google_android_gms_ads_internal_zzq, zzas com_google_android_gms_internal_zzas) {
        return new zzih(context, com_google_android_gms_internal_zzju_zza, com_google_android_gms_ads_internal_zzq, com_google_android_gms_internal_zzas);
    }

    public zzky<zzc> zza(JSONObject jSONObject, String str, boolean z, boolean z2) throws JSONException {
        JSONObject jSONObject2 = z ? jSONObject.getJSONObject(str) : jSONObject.optJSONObject(str);
        if (jSONObject2 == null) {
            jSONObject2 = new JSONObject();
        }
        return zza(jSONObject2, z, z2);
    }

    public List<zzky<zzc>> zza(JSONObject jSONObject, String str, boolean z, boolean z2, boolean z3) throws JSONException {
        JSONArray jSONArray = z ? jSONObject.getJSONArray(str) : jSONObject.optJSONArray(str);
        List<zzky<zzc>> arrayList = new ArrayList();
        if (jSONArray == null || jSONArray.length() == 0) {
            zza(0, z);
            return arrayList;
        }
        int length = z3 ? jSONArray.length() : 1;
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject2 = jSONArray.getJSONObject(i);
            if (jSONObject2 == null) {
                jSONObject2 = new JSONObject();
            }
            arrayList.add(zza(jSONObject2, z, z2));
        }
        return arrayList;
    }

    public Future<zzc> zza(JSONObject jSONObject, String str, boolean z) throws JSONException {
        JSONObject jSONObject2 = jSONObject.getJSONObject(str);
        boolean optBoolean = jSONObject2.optBoolean("require", true);
        if (jSONObject2 == null) {
            jSONObject2 = new JSONObject();
        }
        return zza(jSONObject2, optBoolean, z);
    }

    public void zza(int i, boolean z) {
        if (z) {
            zzan(i);
        }
    }

    public void zzan(int i) {
        synchronized (this.zzail) {
            this.zzbze = true;
            this.zzbyi = i;
        }
    }

    protected zza zzf(JSONObject jSONObject) throws ExecutionException, InterruptedException, JSONException, TimeoutException {
        if (zzqs()) {
            return null;
        }
        String string = jSONObject.getString("template_id");
        boolean z = this.zzbxr.zzcip.zzapo != null ? this.zzbxr.zzcip.zzapo.zzbgp : false;
        boolean z2 = this.zzbxr.zzcip.zzapo != null ? this.zzbxr.zzcip.zzapo.zzbgr : false;
        if ("2".equals(string)) {
            return new zzij(z, z2);
        }
        if (AppEventsConstants.EVENT_PARAM_VALUE_YES.equals(string)) {
            return new zzik(z, z2);
        }
        if ("3".equals(string)) {
            String string2 = jSONObject.getString("custom_template_id");
            zzkv com_google_android_gms_internal_zzkv = new zzkv();
            zzkh.zzclc.post(new 2(this, com_google_android_gms_internal_zzkv, string2));
            if (com_google_android_gms_internal_zzkv.get(zzbyt, TimeUnit.MILLISECONDS) != null) {
                return new zzil(z);
            }
            string2 = "No handler for custom template: ";
            String valueOf = String.valueOf(jSONObject.getString("custom_template_id"));
            zzb.e(valueOf.length() != 0 ? string2.concat(valueOf) : new String(string2));
        } else {
            zzan(0);
        }
        return null;
    }

    public zzky<com.google.android.gms.ads.internal.formats.zza> zzg(JSONObject jSONObject) throws JSONException {
        JSONObject optJSONObject = jSONObject.optJSONObject("attribution");
        if (optJSONObject == null) {
            return new zzkw(null);
        }
        String optString = optJSONObject.optString("text");
        int optInt = optJSONObject.optInt("text_size", -1);
        Integer zzb = zzb(optJSONObject, "text_color");
        Integer zzb2 = zzb(optJSONObject, "bg_color");
        int optInt2 = optJSONObject.optInt("animation_ms", 1000);
        int optInt3 = optJSONObject.optInt("presentation_ms", 4000);
        int i = (this.zzbxr.zzcip.zzapo == null || this.zzbxr.zzcip.zzapo.versionCode < 2) ? 1 : this.zzbxr.zzcip.zzapo.zzbgs;
        List arrayList = new ArrayList();
        if (optJSONObject.optJSONArray("images") != null) {
            arrayList = zza(optJSONObject, "images", false, false, true);
        } else {
            arrayList.add(zza(optJSONObject, "image", false, false));
        }
        return zzkx.zza(zzkx.zzn(arrayList), new 5(this, optString, zzb2, zzb, optInt, optInt3, optInt2, i));
    }

    public zzju zzqr() {
        try {
            this.zzbgb.zzqh();
            String uuid = UUID.randomUUID().toString();
            JSONObject zzcb = zzcb(uuid);
            zzh.zza zza = zza(zzf(zzcb), zzcb, uuid);
            zza(zza);
            return zzb(zza);
        } catch (CancellationException e) {
            if (!this.zzbze) {
                zzan(0);
            }
            return zzb(null);
        } catch (ExecutionException e2) {
            if (this.zzbze) {
                zzan(0);
            }
            return zzb(null);
        } catch (InterruptedException e3) {
            if (this.zzbze) {
                zzan(0);
            }
            return zzb(null);
        } catch (Throwable e4) {
            zzb.zzd("Malformed native JSON response.", e4);
            if (this.zzbze) {
                zzan(0);
            }
            return zzb(null);
        } catch (Throwable e42) {
            zzb.zzd("Timeout when loading native ad.", e42);
            if (this.zzbze) {
                zzan(0);
            }
            return zzb(null);
        }
    }

    public boolean zzqs() {
        boolean z;
        synchronized (this.zzail) {
            z = this.zzbze;
        }
        return z;
    }
}

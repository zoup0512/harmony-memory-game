package com.google.firebase.iid;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.facebook.appevents.AppEventsConstants;
import java.io.IOException;
import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;

public class zzd {
    static Map<String, zzd> aap = new HashMap();
    static String aav;
    private static zzg baF;
    private static zzf baG;
    KeyPair aas;
    String aat = "";
    long aau;
    Context mContext;

    protected zzd(Context context, String str, Bundle bundle) {
        this.mContext = context.getApplicationContext();
        this.aat = str;
    }

    public static synchronized zzd zzb(Context context, Bundle bundle) {
        zzd com_google_firebase_iid_zzd;
        synchronized (zzd.class) {
            String string = bundle == null ? "" : bundle.getString("subtype");
            String str = string == null ? "" : string;
            Context applicationContext = context.getApplicationContext();
            if (baF == null) {
                baF = new zzg(applicationContext);
                baG = new zzf(applicationContext);
            }
            aav = Integer.toString(FirebaseInstanceId.zzdf(applicationContext));
            com_google_firebase_iid_zzd = (zzd) aap.get(str);
            if (com_google_firebase_iid_zzd == null) {
                com_google_firebase_iid_zzd = new zzd(applicationContext, str, bundle);
                aap.put(str, com_google_firebase_iid_zzd);
            }
        }
        return com_google_firebase_iid_zzd;
    }

    public long getCreationTime() {
        if (this.aau == 0) {
            String str = baF.get(this.aat, "cre");
            if (str != null) {
                this.aau = Long.parseLong(str);
            }
        }
        return this.aau;
    }

    public String getToken(String str, String str2, Bundle bundle) throws IOException {
        Object obj = null;
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        Object obj2 = 1;
        String zzi = zzbma() ? null : baF.zzi(this.aat, str, str2);
        if (zzi == null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            if (bundle.getString("ttl") != null) {
                obj2 = null;
            }
            if (!"jwt".equals(bundle.getString("type"))) {
                obj = obj2;
            }
            zzi = zzc(str, str2, bundle);
            if (!(zzi == null || r1 == null)) {
                baF.zza(this.aat, str, str2, zzi, aav);
            }
        }
        return zzi;
    }

    public void zzb(String str, String str2, Bundle bundle) throws IOException {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            throw new IOException("MAIN_THREAD");
        }
        baF.zzj(this.aat, str, str2);
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putString("sender", str);
        if (str2 != null) {
            bundle.putString("scope", str2);
        }
        bundle.putString("subscription", str);
        bundle.putString("delete", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        bundle.putString("X-delete", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        bundle.putString("subtype", "".equals(this.aat) ? str : this.aat);
        String str3 = "X-subtype";
        if (!"".equals(this.aat)) {
            str = this.aat;
        }
        bundle.putString(str3, str);
        baG.zzt(baG.zza(bundle, zzblw()));
    }

    KeyPair zzblw() {
        if (this.aas == null) {
            this.aas = baF.zzkh(this.aat);
        }
        if (this.aas == null) {
            this.aau = System.currentTimeMillis();
            this.aas = baF.zze(this.aat, this.aau);
        }
        return this.aas;
    }

    public void zzblx() {
        this.aau = 0;
        baF.zzki(this.aat);
        this.aas = null;
    }

    boolean zzbma() {
        String str = baF.get("appVersion");
        if (str == null || !str.equals(aav)) {
            return true;
        }
        str = baF.get("lastToken");
        if (str == null) {
            return true;
        }
        return (System.currentTimeMillis() / 1000) - Long.valueOf(Long.parseLong(str)).longValue() > 604800;
    }

    public String zzc(String str, String str2, Bundle bundle) throws IOException {
        if (str2 != null) {
            bundle.putString("scope", str2);
        }
        bundle.putString("sender", str);
        String str3 = "".equals(this.aat) ? str : this.aat;
        if (!bundle.containsKey("legacy.register")) {
            bundle.putString("subscription", str);
            bundle.putString("subtype", str3);
            bundle.putString("X-subscription", str);
            bundle.putString("X-subtype", str3);
        }
        return baG.zzt(baG.zza(bundle, zzblw()));
    }

    public zzg zzcwy() {
        return baF;
    }

    public zzf zzcwz() {
        return baG;
    }
}

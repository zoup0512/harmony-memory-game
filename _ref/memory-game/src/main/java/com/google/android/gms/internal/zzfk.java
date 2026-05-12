package com.google.android.gms.internal;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.Nullable;
import android.util.Base64;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzu;
import com.google.android.gms.internal.zzfm.zza;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

@zzin
public class zzfk {
    private final Map<zzfl, zzfm> zzbko = new HashMap();
    private final LinkedList<zzfl> zzbkp = new LinkedList();
    @Nullable
    private zzfh zzbkq;

    private static void zza(String str, zzfl com_google_android_gms_internal_zzfl) {
        if (zzb.zzaz(2)) {
            zzkd.v(String.format(str, new Object[]{com_google_android_gms_internal_zzfl}));
        }
    }

    private String[] zzbe(String str) {
        try {
            String[] split = str.split("\u0000");
            for (int i = 0; i < split.length; i++) {
                split[i] = new String(Base64.decode(split[i], 0), "UTF-8");
            }
            return split;
        } catch (UnsupportedEncodingException e) {
            return new String[0];
        }
    }

    private boolean zzbf(String str) {
        try {
            return Pattern.matches((String) zzdc.zzbal.get(), str);
        } catch (Throwable e) {
            zzu.zzft().zzb(e, true);
            return false;
        }
    }

    private static void zzc(Bundle bundle, String str) {
        String[] split = str.split("/", 2);
        if (split.length != 0) {
            String str2 = split[0];
            if (split.length == 1) {
                bundle.remove(str2);
                return;
            }
            Bundle bundle2 = bundle.getBundle(str2);
            if (bundle2 != null) {
                zzc(bundle2, split[1]);
            }
        }
    }

    @Nullable
    static Bundle zzi(AdRequestParcel adRequestParcel) {
        Bundle bundle = adRequestParcel.zzatw;
        return bundle == null ? null : bundle.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
    }

    static AdRequestParcel zzj(AdRequestParcel adRequestParcel) {
        Parcel obtain = Parcel.obtain();
        adRequestParcel.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        AdRequestParcel adRequestParcel2 = (AdRequestParcel) AdRequestParcel.CREATOR.createFromParcel(obtain);
        obtain.recycle();
        Bundle zzi = zzi(adRequestParcel2);
        if (zzi == null) {
            zzi = new Bundle();
            adRequestParcel2.zzatw.putBundle("com.google.ads.mediation.admob.AdMobAdapter", zzi);
        }
        zzi.putBoolean("_skipMediation", true);
        return adRequestParcel2;
    }

    static boolean zzk(AdRequestParcel adRequestParcel) {
        Bundle bundle = adRequestParcel.zzatw;
        if (bundle == null) {
            return false;
        }
        bundle = bundle.getBundle("com.google.ads.mediation.admob.AdMobAdapter");
        return bundle != null && bundle.containsKey("_skipMediation");
    }

    private static AdRequestParcel zzl(AdRequestParcel adRequestParcel) {
        Parcel obtain = Parcel.obtain();
        adRequestParcel.writeToParcel(obtain, 0);
        obtain.setDataPosition(0);
        AdRequestParcel adRequestParcel2 = (AdRequestParcel) AdRequestParcel.CREATOR.createFromParcel(obtain);
        obtain.recycle();
        for (String zzc : ((String) zzdc.zzbah.get()).split(",")) {
            zzc(adRequestParcel2.zzatw, zzc);
        }
        return adRequestParcel2;
    }

    private String zzlp() {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            Iterator it = this.zzbkp.iterator();
            while (it.hasNext()) {
                stringBuilder.append(Base64.encodeToString(((zzfl) it.next()).toString().getBytes("UTF-8"), 0));
                if (it.hasNext()) {
                    stringBuilder.append("\u0000");
                }
            }
            return stringBuilder.toString();
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    void flush() {
        while (this.zzbkp.size() > 0) {
            zzfl com_google_android_gms_internal_zzfl = (zzfl) this.zzbkp.remove();
            zzfm com_google_android_gms_internal_zzfm = (zzfm) this.zzbko.get(com_google_android_gms_internal_zzfl);
            zza("Flushing interstitial queue for %s.", com_google_android_gms_internal_zzfl);
            while (com_google_android_gms_internal_zzfm.size() > 0) {
                com_google_android_gms_internal_zzfm.zzm(null).zzbkv.zzeu();
            }
            this.zzbko.remove(com_google_android_gms_internal_zzfl);
        }
    }

    void restore() {
        Throwable e;
        if (this.zzbkq != null) {
            zzfl com_google_android_gms_internal_zzfl;
            SharedPreferences sharedPreferences = this.zzbkq.getApplicationContext().getSharedPreferences("com.google.android.gms.ads.internal.interstitial.InterstitialAdPool", 0);
            flush();
            Map hashMap = new HashMap();
            for (Entry entry : sharedPreferences.getAll().entrySet()) {
                try {
                    if (!((String) entry.getKey()).equals("PoolKeys")) {
                        zzfo com_google_android_gms_internal_zzfo = new zzfo((String) entry.getValue());
                        com_google_android_gms_internal_zzfl = new zzfl(com_google_android_gms_internal_zzfo.zzanc, com_google_android_gms_internal_zzfo.zzaln, com_google_android_gms_internal_zzfo.zzbkt);
                        if (!this.zzbko.containsKey(com_google_android_gms_internal_zzfl)) {
                            this.zzbko.put(com_google_android_gms_internal_zzfl, new zzfm(com_google_android_gms_internal_zzfo.zzanc, com_google_android_gms_internal_zzfo.zzaln, com_google_android_gms_internal_zzfo.zzbkt));
                            hashMap.put(com_google_android_gms_internal_zzfl.toString(), com_google_android_gms_internal_zzfl);
                            zza("Restored interstitial queue for %s.", com_google_android_gms_internal_zzfl);
                        }
                    }
                } catch (IOException e2) {
                    e = e2;
                    zzb.zzd("Malformed preferences value for InterstitialAdPool.", e);
                } catch (ClassCastException e3) {
                    e = e3;
                    zzb.zzd("Malformed preferences value for InterstitialAdPool.", e);
                }
            }
            for (Object obj : zzbe(sharedPreferences.getString("PoolKeys", ""))) {
                com_google_android_gms_internal_zzfl = (zzfl) hashMap.get(obj);
                if (this.zzbko.containsKey(com_google_android_gms_internal_zzfl)) {
                    this.zzbkp.add(com_google_android_gms_internal_zzfl);
                }
            }
        }
    }

    void save() {
        if (this.zzbkq != null) {
            Editor edit = this.zzbkq.getApplicationContext().getSharedPreferences("com.google.android.gms.ads.internal.interstitial.InterstitialAdPool", 0).edit();
            edit.clear();
            for (Entry entry : this.zzbko.entrySet()) {
                zzfl com_google_android_gms_internal_zzfl = (zzfl) entry.getKey();
                zzfm com_google_android_gms_internal_zzfm = (zzfm) entry.getValue();
                if (com_google_android_gms_internal_zzfm.zzlu()) {
                    edit.putString(com_google_android_gms_internal_zzfl.toString(), new zzfo(com_google_android_gms_internal_zzfm).zzlx());
                    zza("Saved interstitial queue for %s.", com_google_android_gms_internal_zzfl);
                }
            }
            edit.putString("PoolKeys", zzlp());
            edit.apply();
        }
    }

    @Nullable
    zza zza(AdRequestParcel adRequestParcel, String str) {
        if (zzbf(str)) {
            return null;
        }
        zzfm com_google_android_gms_internal_zzfm;
        int i = new zziv.zza(this.zzbkq.getApplicationContext()).zzrn().zzcgp;
        AdRequestParcel zzl = zzl(adRequestParcel);
        zzfl com_google_android_gms_internal_zzfl = new zzfl(zzl, str, i);
        zzfm com_google_android_gms_internal_zzfm2 = (zzfm) this.zzbko.get(com_google_android_gms_internal_zzfl);
        if (com_google_android_gms_internal_zzfm2 == null) {
            zza("Interstitial pool created at %s.", com_google_android_gms_internal_zzfl);
            com_google_android_gms_internal_zzfm2 = new zzfm(zzl, str, i);
            this.zzbko.put(com_google_android_gms_internal_zzfl, com_google_android_gms_internal_zzfm2);
            com_google_android_gms_internal_zzfm = com_google_android_gms_internal_zzfm2;
        } else {
            com_google_android_gms_internal_zzfm = com_google_android_gms_internal_zzfm2;
        }
        this.zzbkp.remove(com_google_android_gms_internal_zzfl);
        this.zzbkp.add(com_google_android_gms_internal_zzfl);
        com_google_android_gms_internal_zzfm.zzlt();
        while (this.zzbkp.size() > ((Integer) zzdc.zzbai.get()).intValue()) {
            zzfl com_google_android_gms_internal_zzfl2 = (zzfl) this.zzbkp.remove();
            zzfm com_google_android_gms_internal_zzfm3 = (zzfm) this.zzbko.get(com_google_android_gms_internal_zzfl2);
            zza("Evicting interstitial queue for %s.", com_google_android_gms_internal_zzfl2);
            while (com_google_android_gms_internal_zzfm3.size() > 0) {
                com_google_android_gms_internal_zzfm3.zzm(null).zzbkv.zzeu();
            }
            this.zzbko.remove(com_google_android_gms_internal_zzfl2);
        }
        while (com_google_android_gms_internal_zzfm.size() > 0) {
            zza zzm = com_google_android_gms_internal_zzfm.zzm(zzl);
            if (!zzm.zzbkz || zzu.zzfu().currentTimeMillis() - zzm.zzbky <= 1000 * ((long) ((Integer) zzdc.zzbak.get()).intValue())) {
                String str2 = zzm.zzbkw != null ? " (inline) " : " ";
                zza(new StringBuilder(String.valueOf(str2).length() + 34).append("Pooled interstitial").append(str2).append("returned at %s.").toString(), com_google_android_gms_internal_zzfl);
                return zzm;
            }
            zza("Expired interstitial at %s.", com_google_android_gms_internal_zzfl);
        }
        return null;
    }

    void zza(zzfh com_google_android_gms_internal_zzfh) {
        if (this.zzbkq == null) {
            this.zzbkq = com_google_android_gms_internal_zzfh.zzln();
            restore();
        }
    }

    void zzb(AdRequestParcel adRequestParcel, String str) {
        if (this.zzbkq != null) {
            int i = new zziv.zza(this.zzbkq.getApplicationContext()).zzrn().zzcgp;
            AdRequestParcel zzl = zzl(adRequestParcel);
            zzfl com_google_android_gms_internal_zzfl = new zzfl(zzl, str, i);
            zzfm com_google_android_gms_internal_zzfm = (zzfm) this.zzbko.get(com_google_android_gms_internal_zzfl);
            if (com_google_android_gms_internal_zzfm == null) {
                zza("Interstitial pool created at %s.", com_google_android_gms_internal_zzfl);
                com_google_android_gms_internal_zzfm = new zzfm(zzl, str, i);
                this.zzbko.put(com_google_android_gms_internal_zzfl, com_google_android_gms_internal_zzfm);
            }
            com_google_android_gms_internal_zzfm.zza(this.zzbkq, adRequestParcel);
            com_google_android_gms_internal_zzfm.zzlt();
            zza("Inline entry added to the queue at %s.", com_google_android_gms_internal_zzfl);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    void zzlo() {
        /*
        r9 = this;
        r8 = 2;
        r0 = r9.zzbkq;
        if (r0 != 0) goto L_0x0006;
    L_0x0005:
        return;
    L_0x0006:
        r0 = r9.zzbko;
        r0 = r0.entrySet();
        r3 = r0.iterator();
    L_0x0010:
        r0 = r3.hasNext();
        if (r0 == 0) goto L_0x0076;
    L_0x0016:
        r0 = r3.next();
        r0 = (java.util.Map.Entry) r0;
        r1 = r0.getKey();
        r1 = (com.google.android.gms.internal.zzfl) r1;
        r0 = r0.getValue();
        r0 = (com.google.android.gms.internal.zzfm) r0;
        r2 = com.google.android.gms.ads.internal.util.client.zzb.zzaz(r8);
        if (r2 == 0) goto L_0x0056;
    L_0x002e:
        r2 = r0.size();
        r4 = r0.zzlr();
        if (r4 >= r2) goto L_0x0056;
    L_0x0038:
        r5 = "Loading %s/%s pooled interstitials for %s.";
        r6 = 3;
        r6 = new java.lang.Object[r6];
        r7 = 0;
        r4 = r2 - r4;
        r4 = java.lang.Integer.valueOf(r4);
        r6[r7] = r4;
        r4 = 1;
        r2 = java.lang.Integer.valueOf(r2);
        r6[r4] = r2;
        r6[r8] = r1;
        r2 = java.lang.String.format(r5, r6);
        com.google.android.gms.internal.zzkd.v(r2);
    L_0x0056:
        r0.zzls();
    L_0x0059:
        r4 = r0.size();
        r2 = com.google.android.gms.internal.zzdc.zzbaj;
        r2 = r2.get();
        r2 = (java.lang.Integer) r2;
        r2 = r2.intValue();
        if (r4 >= r2) goto L_0x0010;
    L_0x006b:
        r2 = "Pooling and loading one new interstitial for %s.";
        zza(r2, r1);
        r2 = r9.zzbkq;
        r0.zzb(r2);
        goto L_0x0059;
    L_0x0076:
        r9.save();
        goto L_0x0005;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzfk.zzlo():void");
    }
}

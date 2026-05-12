package com.google.android.gms.analytics;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.analytics.internal.zzad;
import com.google.android.gms.analytics.internal.zzan;
import com.google.android.gms.analytics.internal.zzao;
import com.google.android.gms.analytics.internal.zzd;
import com.google.android.gms.analytics.internal.zzf;
import com.google.android.gms.common.internal.zzab;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class Tracker extends zzd {
    private final Map<String, String> zzbeg = new HashMap();
    private boolean zzctt;
    private final Map<String, String> zzctu = new HashMap();
    private final zzad zzctv;
    private final zza zzctw;
    private ExceptionReporter zzctx;
    private zzan zzcty;

    Tracker(zzf com_google_android_gms_analytics_internal_zzf, String str, zzad com_google_android_gms_analytics_internal_zzad) {
        super(com_google_android_gms_analytics_internal_zzf);
        if (str != null) {
            this.zzbeg.put("&tid", str);
        }
        this.zzbeg.put("useSecure", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        this.zzbeg.put("&a", Integer.toString(new Random().nextInt(Integer.MAX_VALUE) + 1));
        if (com_google_android_gms_analytics_internal_zzad == null) {
            this.zzctv = new zzad("tracking", zzyw());
        } else {
            this.zzctv = com_google_android_gms_analytics_internal_zzad;
        }
        this.zzctw = new zza(this, com_google_android_gms_analytics_internal_zzf);
    }

    private static boolean zza(Entry<String, String> entry) {
        String str = (String) entry.getKey();
        String str2 = (String) entry.getValue();
        return str.startsWith("&") && str.length() >= 2;
    }

    private static String zzb(Entry<String, String> entry) {
        return !zza((Entry) entry) ? null : ((String) entry.getKey()).substring(1);
    }

    private static void zzb(Map<String, String> map, Map<String, String> map2) {
        zzab.zzy(map2);
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                String zzb = zzb(entry);
                if (zzb != null) {
                    map2.put(zzb, (String) entry.getValue());
                }
            }
        }
    }

    private static void zzc(Map<String, String> map, Map<String, String> map2) {
        zzab.zzy(map2);
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                String zzb = zzb(entry);
                if (!(zzb == null || map2.containsKey(zzb))) {
                    map2.put(zzb, (String) entry.getValue());
                }
            }
        }
    }

    static String zzq(Activity activity) {
        zzab.zzy(activity);
        Intent intent = activity.getIntent();
        if (intent == null) {
            return null;
        }
        CharSequence stringExtra = intent.getStringExtra("android.intent.extra.REFERRER_NAME");
        return !TextUtils.isEmpty(stringExtra) ? stringExtra : null;
    }

    private boolean zzww() {
        return this.zzctx != null;
    }

    public void enableAdvertisingIdCollection(boolean z) {
        this.zzctt = z;
    }

    public void enableAutoActivityTracking(boolean z) {
        this.zzctw.enableAutoActivityTracking(z);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void enableExceptionReporting(boolean r4) {
        /*
        r3 = this;
        monitor-enter(r3);
        r0 = r3.zzww();	 Catch:{ all -> 0x0026 }
        if (r0 != r4) goto L_0x0009;
    L_0x0007:
        monitor-exit(r3);	 Catch:{ all -> 0x0026 }
    L_0x0008:
        return;
    L_0x0009:
        if (r4 == 0) goto L_0x0029;
    L_0x000b:
        r0 = r3.getContext();	 Catch:{ all -> 0x0026 }
        r1 = java.lang.Thread.getDefaultUncaughtExceptionHandler();	 Catch:{ all -> 0x0026 }
        r2 = new com.google.android.gms.analytics.ExceptionReporter;	 Catch:{ all -> 0x0026 }
        r2.<init>(r3, r1, r0);	 Catch:{ all -> 0x0026 }
        r3.zzctx = r2;	 Catch:{ all -> 0x0026 }
        r0 = r3.zzctx;	 Catch:{ all -> 0x0026 }
        java.lang.Thread.setDefaultUncaughtExceptionHandler(r0);	 Catch:{ all -> 0x0026 }
        r0 = "Uncaught exceptions will be reported to Google Analytics";
        r3.zzeh(r0);	 Catch:{ all -> 0x0026 }
    L_0x0024:
        monitor-exit(r3);	 Catch:{ all -> 0x0026 }
        goto L_0x0008;
    L_0x0026:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0026 }
        throw r0;
    L_0x0029:
        r0 = r3.zzctx;	 Catch:{ all -> 0x0026 }
        r0 = r0.zzvy();	 Catch:{ all -> 0x0026 }
        java.lang.Thread.setDefaultUncaughtExceptionHandler(r0);	 Catch:{ all -> 0x0026 }
        r0 = "Uncaught exceptions will not be reported to Google Analytics";
        r3.zzeh(r0);	 Catch:{ all -> 0x0026 }
        goto L_0x0024;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.analytics.Tracker.enableExceptionReporting(boolean):void");
    }

    public String get(String str) {
        zzzg();
        return TextUtils.isEmpty(str) ? null : this.zzbeg.containsKey(str) ? (String) this.zzbeg.get(str) : str.equals("&ul") ? zzao.zza(Locale.getDefault()) : str.equals("&cid") ? zzzc().zzaav() : str.equals("&sr") ? zzzf().zzacl() : str.equals("&aid") ? zzze().zzaad().zzsh() : str.equals("&an") ? zzze().zzaad().zzxb() : str.equals("&av") ? zzze().zzaad().zzxc() : str.equals("&aiid") ? zzze().zzaad().zzxd() : null;
    }

    public void send(Map<String, String> map) {
        long currentTimeMillis = zzyw().currentTimeMillis();
        if (zzvx().getAppOptOut()) {
            zzei("AppOptOut is set to true. Not sending Google Analytics hit");
            return;
        }
        boolean isDryRunEnabled = zzvx().isDryRunEnabled();
        Map hashMap = new HashMap();
        zzb(this.zzbeg, hashMap);
        zzb(map, hashMap);
        boolean zzi = zzao.zzi((String) this.zzbeg.get("useSecure"), true);
        zzc(this.zzctu, hashMap);
        this.zzctu.clear();
        String str = (String) hashMap.get("t");
        if (TextUtils.isEmpty(str)) {
            zzyx().zzh(hashMap, "Missing hit type parameter");
            return;
        }
        String str2 = (String) hashMap.get("tid");
        if (TextUtils.isEmpty(str2)) {
            zzyx().zzh(hashMap, "Missing tracking id parameter");
            return;
        }
        boolean zzwx = zzwx();
        synchronized (this) {
            if ("screenview".equalsIgnoreCase(str) || "pageview".equalsIgnoreCase(str) || "appview".equalsIgnoreCase(str) || TextUtils.isEmpty(str)) {
                int parseInt = Integer.parseInt((String) this.zzbeg.get("&a")) + 1;
                if (parseInt >= Integer.MAX_VALUE) {
                    parseInt = 1;
                }
                this.zzbeg.put("&a", Integer.toString(parseInt));
            }
        }
        zzyz().zzg(new 1(this, hashMap, zzwx, str, currentTimeMillis, isDryRunEnabled, zzi, str2));
    }

    public void set(String str, String str2) {
        zzab.zzb(str, "Key should be non-null");
        if (!TextUtils.isEmpty(str)) {
            this.zzbeg.put(str, str2);
        }
    }

    public void setAnonymizeIp(boolean z) {
        set("&aip", zzao.zzat(z));
    }

    public void setAppId(String str) {
        set("&aid", str);
    }

    public void setAppInstallerId(String str) {
        set("&aiid", str);
    }

    public void setAppName(String str) {
        set("&an", str);
    }

    public void setAppVersion(String str) {
        set("&av", str);
    }

    public void setCampaignParamsOnNextHit(Uri uri) {
        if (uri != null && !uri.isOpaque()) {
            CharSequence queryParameter = uri.getQueryParameter("referrer");
            if (!TextUtils.isEmpty(queryParameter)) {
                String str = "http://hostname/?";
                String valueOf = String.valueOf(queryParameter);
                Uri parse = Uri.parse(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                str = parse.getQueryParameter("utm_id");
                if (str != null) {
                    this.zzctu.put("&ci", str);
                }
                str = parse.getQueryParameter("anid");
                if (str != null) {
                    this.zzctu.put("&anid", str);
                }
                str = parse.getQueryParameter("utm_campaign");
                if (str != null) {
                    this.zzctu.put("&cn", str);
                }
                str = parse.getQueryParameter("utm_content");
                if (str != null) {
                    this.zzctu.put("&cc", str);
                }
                str = parse.getQueryParameter("utm_medium");
                if (str != null) {
                    this.zzctu.put("&cm", str);
                }
                str = parse.getQueryParameter("utm_source");
                if (str != null) {
                    this.zzctu.put("&cs", str);
                }
                str = parse.getQueryParameter("utm_term");
                if (str != null) {
                    this.zzctu.put("&ck", str);
                }
                str = parse.getQueryParameter("dclid");
                if (str != null) {
                    this.zzctu.put("&dclid", str);
                }
                str = parse.getQueryParameter("gclid");
                if (str != null) {
                    this.zzctu.put("&gclid", str);
                }
                valueOf = parse.getQueryParameter("aclid");
                if (valueOf != null) {
                    this.zzctu.put("&aclid", valueOf);
                }
            }
        }
    }

    public void setClientId(String str) {
        set("&cid", str);
    }

    public void setEncoding(String str) {
        set("&de", str);
    }

    public void setHostname(String str) {
        set("&dh", str);
    }

    public void setLanguage(String str) {
        set("&ul", str);
    }

    public void setLocation(String str) {
        set("&dl", str);
    }

    public void setPage(String str) {
        set("&dp", str);
    }

    public void setReferrer(String str) {
        set("&dr", str);
    }

    public void setSampleRate(double d) {
        set("&sf", Double.toString(d));
    }

    public void setScreenColors(String str) {
        set("&sd", str);
    }

    public void setScreenName(String str) {
        set("&cd", str);
    }

    public void setScreenResolution(int i, int i2) {
        if (i >= 0 || i2 >= 0) {
            set("&sr", i + "x" + i2);
        } else {
            zzek("Invalid width or height. The values should be non-negative.");
        }
    }

    public void setSessionTimeout(long j) {
        this.zzctw.setSessionTimeout(1000 * j);
    }

    public void setTitle(String str) {
        set("&dt", str);
    }

    public void setUseSecure(boolean z) {
        set("useSecure", zzao.zzat(z));
    }

    public void setViewportSize(String str) {
        set("&vp", str);
    }

    void zza(zzan com_google_android_gms_analytics_internal_zzan) {
        zzeh("Loading Tracker config values");
        this.zzcty = com_google_android_gms_analytics_internal_zzan;
        if (this.zzcty.zzaeb()) {
            String trackingId = this.zzcty.getTrackingId();
            set("&tid", trackingId);
            zza("trackingId loaded", trackingId);
        }
        if (this.zzcty.zzaec()) {
            trackingId = Double.toString(this.zzcty.zzaed());
            set("&sf", trackingId);
            zza("Sample frequency loaded", trackingId);
        }
        if (this.zzcty.zzaee()) {
            int sessionTimeout = this.zzcty.getSessionTimeout();
            setSessionTimeout((long) sessionTimeout);
            zza("Session timeout loaded", Integer.valueOf(sessionTimeout));
        }
        if (this.zzcty.zzaef()) {
            boolean zzaeg = this.zzcty.zzaeg();
            enableAutoActivityTracking(zzaeg);
            zza("Auto activity tracking loaded", Boolean.valueOf(zzaeg));
        }
        if (this.zzcty.zzaeh()) {
            zzaeg = this.zzcty.zzaei();
            if (zzaeg) {
                set("&aip", AppEventsConstants.EVENT_PARAM_VALUE_YES);
            }
            zza("Anonymize ip loaded", Boolean.valueOf(zzaeg));
        }
        enableExceptionReporting(this.zzcty.zzaej());
    }

    protected void zzwv() {
        this.zzctw.initialize();
        String zzxb = zzwe().zzxb();
        if (zzxb != null) {
            set("&an", zzxb);
        }
        zzxb = zzwe().zzxc();
        if (zzxb != null) {
            set("&av", zzxb);
        }
    }

    boolean zzwx() {
        return this.zzctt;
    }
}

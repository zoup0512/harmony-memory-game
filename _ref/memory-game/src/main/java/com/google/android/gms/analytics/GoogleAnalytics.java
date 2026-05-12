package com.google.android.gms.analytics;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.RequiresPermission;
import android.util.Log;
import com.google.android.gms.analytics.internal.zzae;
import com.google.android.gms.analytics.internal.zzam;
import com.google.android.gms.analytics.internal.zzan;
import com.google.android.gms.analytics.internal.zzap;
import com.google.android.gms.analytics.internal.zzb;
import com.google.android.gms.analytics.internal.zzf;
import com.google.android.gms.analytics.internal.zzy;
import com.google.android.gms.common.internal.zzab;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public final class GoogleAnalytics extends zza {
    private static List<Runnable> zzcsg = new ArrayList();
    private boolean zzamt;
    private Set<zza> zzcsh = new HashSet();
    private boolean zzcsi;
    private boolean zzcsj;
    private volatile boolean zzcsk;
    private boolean zzcsl;

    public GoogleAnalytics(zzf com_google_android_gms_analytics_internal_zzf) {
        super(com_google_android_gms_analytics_internal_zzf);
    }

    @RequiresPermission(allOf = {"android.permission.INTERNET", "android.permission.ACCESS_NETWORK_STATE"})
    public static GoogleAnalytics getInstance(Context context) {
        return zzf.zzay(context).zzzk();
    }

    public static void zzwa() {
        synchronized (GoogleAnalytics.class) {
            if (zzcsg != null) {
                for (Runnable run : zzcsg) {
                    run.run();
                }
                zzcsg = null;
            }
        }
    }

    private zzb zzwd() {
        return zzvq().zzwd();
    }

    private zzap zzwe() {
        return zzvq().zzwe();
    }

    public void dispatchLocalHits() {
        zzwd().zzyp();
    }

    @TargetApi(14)
    public void enableAutoActivityReports(Application application) {
        if (VERSION.SDK_INT >= 14 && !this.zzcsi) {
            application.registerActivityLifecycleCallbacks(new zzb(this));
            this.zzcsi = true;
        }
    }

    public boolean getAppOptOut() {
        return this.zzcsk;
    }

    @Deprecated
    public Logger getLogger() {
        return zzae.getLogger();
    }

    public void initialize() {
        zzvz();
        this.zzamt = true;
    }

    public boolean isDryRunEnabled() {
        return this.zzcsj;
    }

    public boolean isInitialized() {
        return this.zzamt;
    }

    public Tracker newTracker(int i) {
        Tracker tracker;
        synchronized (this) {
            tracker = new Tracker(zzvq(), null, null);
            if (i > 0) {
                zzan com_google_android_gms_analytics_internal_zzan = (zzan) new zzam(zzvq()).zzbx(i);
                if (com_google_android_gms_analytics_internal_zzan != null) {
                    tracker.zza(com_google_android_gms_analytics_internal_zzan);
                }
            }
            tracker.initialize();
        }
        return tracker;
    }

    public Tracker newTracker(String str) {
        Tracker tracker;
        synchronized (this) {
            tracker = new Tracker(zzvq(), str, null);
            tracker.initialize();
        }
        return tracker;
    }

    public void reportActivityStart(Activity activity) {
        if (!this.zzcsi) {
            zzm(activity);
        }
    }

    public void reportActivityStop(Activity activity) {
        if (!this.zzcsi) {
            zzn(activity);
        }
    }

    public void setAppOptOut(boolean z) {
        this.zzcsk = z;
        if (this.zzcsk) {
            zzwd().zzyo();
        }
    }

    public void setDryRun(boolean z) {
        this.zzcsj = z;
    }

    public void setLocalDispatchPeriod(int i) {
        zzwd().setLocalDispatchPeriod(i);
    }

    @Deprecated
    public void setLogger(Logger logger) {
        zzae.setLogger(logger);
        if (!this.zzcsl) {
            String str = (String) zzy.zzczn.get();
            Log.i((String) zzy.zzczn.get(), new StringBuilder(String.valueOf(str).length() + 112).append("GoogleAnalytics.setLogger() is deprecated. To enable debug logging, please run:\nadb shell setprop log.tag.").append(str).append(" DEBUG").toString());
            this.zzcsl = true;
        }
    }

    void zza(zza com_google_android_gms_analytics_GoogleAnalytics_zza) {
        this.zzcsh.add(com_google_android_gms_analytics_GoogleAnalytics_zza);
        Context context = zzvq().getContext();
        if (context instanceof Application) {
            enableAutoActivityReports((Application) context);
        }
    }

    void zzb(zza com_google_android_gms_analytics_GoogleAnalytics_zza) {
        this.zzcsh.remove(com_google_android_gms_analytics_GoogleAnalytics_zza);
    }

    void zzm(Activity activity) {
        for (zza zzo : this.zzcsh) {
            zzo.zzo(activity);
        }
    }

    void zzn(Activity activity) {
        for (zza zzp : this.zzcsh) {
            zzp.zzp(activity);
        }
    }

    void zzvz() {
        zzap zzwe = zzwe();
        if (zzwe.zzacr()) {
            getLogger().setLogLevel(zzwe.getLogLevel());
        }
        if (zzwe.zzacv()) {
            setDryRun(zzwe.zzacw());
        }
        if (zzwe.zzacr()) {
            Logger logger = zzae.getLogger();
            if (logger != null) {
                logger.setLogLevel(zzwe.getLogLevel());
            }
        }
    }

    public String zzwb() {
        zzab.zzhj("getClientId can not be called from the main thread");
        return zzvq().zzzn().zzaav();
    }

    void zzwc() {
        zzwd().zzyq();
    }
}

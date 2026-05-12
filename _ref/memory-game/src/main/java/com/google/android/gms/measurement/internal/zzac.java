package com.google.android.gms.measurement.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.applovin.sdk.AppLovinEventTypes;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.util.zze;
import com.google.android.gms.common.util.zzf;
import com.google.android.gms.measurement.AppMeasurement$zzb;
import com.google.android.gms.measurement.AppMeasurement$zzc;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class zzac extends zzaa {
    private AppMeasurement$zzb alA;
    private final Set<AppMeasurement$zzc> alB = new HashSet();
    private boolean alC;
    private zza alz;

    @MainThread
    @TargetApi(14)
    private class zza implements ActivityLifecycleCallbacks {
        final /* synthetic */ zzac alD;

        private zza(zzac com_google_android_gms_measurement_internal_zzac) {
            this.alD = com_google_android_gms_measurement_internal_zzac;
        }

        private boolean zzmh(String str) {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            this.alD.zzd("auto", "_ldl", str);
            return true;
        }

        private boolean zzs(Uri uri) {
            Object queryParameter = uri.getQueryParameter("utm_campaign");
            Object queryParameter2 = uri.getQueryParameter("utm_source");
            Object queryParameter3 = uri.getQueryParameter("utm_medium");
            Object queryParameter4 = uri.getQueryParameter("gclid");
            if (TextUtils.isEmpty(queryParameter) && TextUtils.isEmpty(queryParameter2) && TextUtils.isEmpty(queryParameter3) && TextUtils.isEmpty(queryParameter4)) {
                return false;
            }
            Bundle bundle = new Bundle();
            if (!TextUtils.isEmpty(queryParameter)) {
                bundle.putString("campaign", queryParameter);
            }
            if (!TextUtils.isEmpty(queryParameter2)) {
                bundle.putString(ShareConstants.FEED_SOURCE_PARAM, queryParameter2);
            }
            if (!TextUtils.isEmpty(queryParameter3)) {
                bundle.putString("medium", queryParameter3);
            }
            if (!TextUtils.isEmpty(queryParameter4)) {
                bundle.putString("gclid", queryParameter4);
            }
            queryParameter = uri.getQueryParameter("utm_term");
            if (!TextUtils.isEmpty(queryParameter)) {
                bundle.putString("term", queryParameter);
            }
            queryParameter = uri.getQueryParameter("utm_content");
            if (!TextUtils.isEmpty(queryParameter)) {
                bundle.putString(AppLovinEventTypes.USER_VIEWED_CONTENT, queryParameter);
            }
            queryParameter = uri.getQueryParameter("aclid");
            if (!TextUtils.isEmpty(queryParameter)) {
                bundle.putString("aclid", queryParameter);
            }
            queryParameter = uri.getQueryParameter("cp1");
            if (!TextUtils.isEmpty(queryParameter)) {
                bundle.putString("cp1", queryParameter);
            }
            queryParameter = uri.getQueryParameter("anid");
            if (!TextUtils.isEmpty(queryParameter)) {
                bundle.putString("anid", queryParameter);
            }
            this.alD.zze("auto", "_cmp", bundle);
            return true;
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
            try {
                this.alD.zzbsd().zzbtc().log("onActivityCreated");
                Intent intent = activity.getIntent();
                if (intent != null) {
                    Uri data = intent.getData();
                    if (data != null && data.isHierarchical()) {
                        if (bundle == null) {
                            zzs(data);
                        }
                        String queryParameter = data.getQueryParameter("referrer");
                        if (!TextUtils.isEmpty(queryParameter)) {
                            if (queryParameter.contains("gclid")) {
                                this.alD.zzbsd().zzbtb().zzj("Activity created with referrer", queryParameter);
                                zzmh(queryParameter);
                                return;
                            }
                            this.alD.zzbsd().zzbtb().log("Activity created with data 'referrer' param without gclid");
                        }
                    }
                }
            } catch (Throwable th) {
                this.alD.zzbsd().zzbsv().zzj("Throwable caught in onActivityCreated", th);
            }
        }

        public void onActivityDestroyed(Activity activity) {
        }

        @MainThread
        public void onActivityPaused(Activity activity) {
            this.alD.zzbsb().zzbuz();
        }

        @MainThread
        public void onActivityResumed(Activity activity) {
            this.alD.zzbsb().zzbux();
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityStarted(Activity activity) {
        }

        public void onActivityStopped(Activity activity) {
        }
    }

    protected zzac(zzx com_google_android_gms_measurement_internal_zzx) {
        super(com_google_android_gms_measurement_internal_zzx);
    }

    private void zza(String str, String str2, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        zza(str, str2, zzyw().currentTimeMillis(), bundle, z, z2, z3, str3);
    }

    @WorkerThread
    private void zza(String str, String str2, Object obj, long j) {
        zzab.zzhr(str);
        zzab.zzhr(str2);
        zzwu();
        zzyv();
        zzzg();
        if (!this.ahD.isEnabled()) {
            zzbsd().zzbtb().log("User property not set since app measurement is disabled");
        } else if (this.ahD.zzbto()) {
            zzbsd().zzbtb().zze("Setting user property (FE)", str2, obj);
            zzbrx().zza(new UserAttributeParcel(str2, j, obj, str));
        }
    }

    @WorkerThread
    private void zzb(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        zzab.zzhr(str);
        zzab.zzhr(str2);
        zzab.zzy(bundle);
        zzwu();
        zzzg();
        if (this.ahD.isEnabled()) {
            if (!this.alC) {
                this.alC = true;
                zzbup();
            }
            boolean zzmt = zzal.zzmt(str2);
            if (z && this.alA != null && !zzmt) {
                zzbsd().zzbtb().zze("Passing event to registered event handler (FE)", str2, bundle);
                this.alA.zzb(str, str2, bundle, j);
                return;
            } else if (this.ahD.zzbto()) {
                int zzml = zzbrz().zzml(str2);
                if (zzml != 0) {
                    this.ahD.zzbrz().zze(zzml, "_ev", zzbrz().zza(str2, zzbsf().zzbqn(), true));
                    return;
                }
                bundle.putString("_o", str);
                Bundle zza = zzbrz().zza(str2, bundle, zzf.zzz("_o"), z3);
                Bundle zzal = z2 ? zzal(zza) : zza;
                zzbsd().zzbtb().zze("Logging event (FE)", str2, zzal);
                zzbrx().zzc(new EventParcel(str2, new EventParams(zzal), str, j), str3);
                for (AppMeasurement$zzc zzc : this.alB) {
                    zzc.zzc(str, str2, zzal, j);
                }
                return;
            } else {
                return;
            }
        }
        zzbsd().zzbtb().log("Event not sent since app measurement is disabled");
    }

    @WorkerThread
    private void zzbup() {
        try {
            zzg(Class.forName(zzbuq()));
        } catch (ClassNotFoundException e) {
            zzbsd().zzbta().log("Tag Manager is not found and thus will not be used");
        }
    }

    private String zzbuq() {
        return "com.google.android.gms.tagmanager.TagManagerService";
    }

    @WorkerThread
    private void zzcd(boolean z) {
        zzwu();
        zzyv();
        zzzg();
        zzbsd().zzbtb().zzj("Setting app measurement enabled (FE)", Boolean.valueOf(z));
        zzbse().setMeasurementEnabled(z);
        zzbrx().zzbur();
    }

    public /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public void setMeasurementEnabled(final boolean z) {
        zzzg();
        zzyv();
        zzbsc().zzm(new Runnable(this) {
            final /* synthetic */ zzac alD;

            public void run() {
                this.alD.zzcd(z);
            }
        });
    }

    public void setMinimumSessionDuration(final long j) {
        zzyv();
        zzbsc().zzm(new Runnable(this) {
            final /* synthetic */ zzac alD;

            public void run() {
                this.alD.zzbse().akh.set(j);
                this.alD.zzbsd().zzbtb().zzj("Minimum session duration set", Long.valueOf(j));
            }
        });
    }

    public void setSessionTimeoutDuration(final long j) {
        zzyv();
        zzbsc().zzm(new Runnable(this) {
            final /* synthetic */ zzac alD;

            public void run() {
                this.alD.zzbse().aki.set(j);
                this.alD.zzbsd().zzbtb().zzj("Session timeout duration set", Long.valueOf(j));
            }
        });
    }

    @WorkerThread
    public void zza(AppMeasurement$zzb appMeasurement$zzb) {
        zzwu();
        zzyv();
        zzzg();
        if (!(appMeasurement$zzb == null || appMeasurement$zzb == this.alA)) {
            zzab.zza(this.alA == null, (Object) "EventInterceptor already set.");
        }
        this.alA = appMeasurement$zzb;
    }

    @WorkerThread
    public void zza(AppMeasurement$zzc appMeasurement$zzc) {
        zzwu();
        zzyv();
        zzzg();
        zzab.zzy(appMeasurement$zzc);
        if (this.alB.contains(appMeasurement$zzc)) {
            throw new IllegalStateException("OnEventListener already registered.");
        }
        this.alB.add(appMeasurement$zzc);
    }

    protected void zza(String str, String str2, long j, Bundle bundle, boolean z, boolean z2, boolean z3, String str3) {
        final Bundle bundle2 = bundle != null ? new Bundle(bundle) : new Bundle();
        final String str4 = str;
        final String str5 = str2;
        final long j2 = j;
        final boolean z4 = z;
        final boolean z5 = z2;
        final boolean z6 = z3;
        final String str6 = str3;
        zzbsc().zzm(new Runnable(this) {
            final /* synthetic */ zzac alD;

            public void run() {
                this.alD.zzb(str4, str5, j2, bundle2, z4, z5, z6, str6);
            }
        });
    }

    void zza(String str, String str2, long j, Object obj) {
        final String str3 = str;
        final String str4 = str2;
        final Object obj2 = obj;
        final long j2 = j;
        zzbsc().zzm(new Runnable(this) {
            final /* synthetic */ zzac alD;

            public void run() {
                this.alD.zza(str3, str4, obj2, j2);
            }
        });
    }

    public void zza(String str, String str2, Bundle bundle, boolean z) {
        zzyv();
        boolean z2 = this.alA == null || zzal.zzmt(str2);
        zza(str, str2, bundle, true, z2, z, null);
    }

    Bundle zzal(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        if (bundle != null) {
            for (String str : bundle.keySet()) {
                Object zzl = zzbrz().zzl(str, bundle.get(str));
                if (zzl == null) {
                    zzbsd().zzbsx().zzj("Param value can't be null", str);
                } else if ((!(zzl instanceof String) && !(zzl instanceof Character) && !(zzl instanceof CharSequence)) || !TextUtils.isEmpty(String.valueOf(zzl))) {
                    zzbrz().zza(bundle2, str, zzl);
                }
            }
        }
        return bundle2;
    }

    public /* bridge */ /* synthetic */ void zzbrs() {
        super.zzbrs();
    }

    public /* bridge */ /* synthetic */ zzc zzbrt() {
        return super.zzbrt();
    }

    public /* bridge */ /* synthetic */ zzac zzbru() {
        return super.zzbru();
    }

    public /* bridge */ /* synthetic */ zzn zzbrv() {
        return super.zzbrv();
    }

    public /* bridge */ /* synthetic */ zzg zzbrw() {
        return super.zzbrw();
    }

    public /* bridge */ /* synthetic */ zzad zzbrx() {
        return super.zzbrx();
    }

    public /* bridge */ /* synthetic */ zze zzbry() {
        return super.zzbry();
    }

    public /* bridge */ /* synthetic */ zzal zzbrz() {
        return super.zzbrz();
    }

    public /* bridge */ /* synthetic */ zzv zzbsa() {
        return super.zzbsa();
    }

    public /* bridge */ /* synthetic */ zzaf zzbsb() {
        return super.zzbsb();
    }

    public /* bridge */ /* synthetic */ zzw zzbsc() {
        return super.zzbsc();
    }

    public /* bridge */ /* synthetic */ zzp zzbsd() {
        return super.zzbsd();
    }

    public /* bridge */ /* synthetic */ zzt zzbse() {
        return super.zzbse();
    }

    public /* bridge */ /* synthetic */ zzd zzbsf() {
        return super.zzbsf();
    }

    @TargetApi(14)
    public void zzbun() {
        if (getContext().getApplicationContext() instanceof Application) {
            Application application = (Application) getContext().getApplicationContext();
            if (this.alz == null) {
                this.alz = new zza();
            }
            application.unregisterActivityLifecycleCallbacks(this.alz);
            application.registerActivityLifecycleCallbacks(this.alz);
            zzbsd().zzbtc().log("Registered activity lifecycle callback");
        }
    }

    @WorkerThread
    public void zzbuo() {
        zzwu();
        zzyv();
        zzzg();
        if (this.ahD.zzbto()) {
            zzbrx().zzbuo();
            String zzbtl = zzbse().zzbtl();
            if (!TextUtils.isEmpty(zzbtl) && !zzbtl.equals(zzbrw().zzbso())) {
                Bundle bundle = new Bundle();
                bundle.putString("_po", zzbtl);
                zze("auto", "_ou", bundle);
            }
        }
    }

    @Nullable
    @WorkerThread
    public List<UserAttributeParcel> zzce(final boolean z) {
        zzyv();
        zzzg();
        zzbsd().zzbtb().log("Fetching user attributes (FE)");
        if (Looper.myLooper() == Looper.getMainLooper()) {
            zzbsd().zzbsx().log("getUserProperties called from main thread.");
            return null;
        }
        final AtomicReference atomicReference = new AtomicReference();
        synchronized (atomicReference) {
            this.ahD.zzbsc().zzm(new Runnable(this) {
                final /* synthetic */ zzac alD;

                public void run() {
                    this.alD.zzbrx().zza(atomicReference, z);
                }
            });
            try {
                atomicReference.wait(5000);
            } catch (InterruptedException e) {
                zzbsd().zzbsx().zzj("Interrupted waiting for get user properties", e);
            }
        }
        List<UserAttributeParcel> list = (List) atomicReference.get();
        if (list != null) {
            return list;
        }
        zzbsd().zzbsx().log("Timed out waiting for get user properties");
        return null;
    }

    public void zzd(String str, String str2, Bundle bundle, long j) {
        zzyv();
        zza(str, str2, j, bundle, false, true, true, null);
    }

    public void zzd(String str, String str2, Object obj) {
        zzab.zzhr(str);
        long currentTimeMillis = zzyw().currentTimeMillis();
        int zzmn = zzbrz().zzmn(str2);
        if (zzmn != 0) {
            this.ahD.zzbrz().zze(zzmn, "_ev", zzbrz().zza(str2, zzbsf().zzbqo(), true));
        } else if (obj != null) {
            zzmn = zzbrz().zzm(str2, obj);
            if (zzmn != 0) {
                this.ahD.zzbrz().zze(zzmn, "_ev", zzbrz().zza(str2, zzbsf().zzbqo(), true));
                return;
            }
            Object zzn = zzbrz().zzn(str2, obj);
            if (zzn != null) {
                zza(str, str2, currentTimeMillis, zzn);
            }
        } else {
            zza(str, str2, currentTimeMillis, null);
        }
    }

    public void zze(String str, String str2, Bundle bundle) {
        zzyv();
        boolean z = this.alA == null || zzal.zzmt(str2);
        zza(str, str2, bundle, true, z, false, null);
    }

    @WorkerThread
    public void zzg(Class<?> cls) {
        try {
            cls.getDeclaredMethod("initialize", new Class[]{Context.class}).invoke(null, new Object[]{getContext()});
        } catch (Exception e) {
            zzbsd().zzbsx().zzj("Failed to invoke Tag Manager's initialize() method", e);
        }
    }

    public /* bridge */ /* synthetic */ void zzwu() {
        super.zzwu();
    }

    protected void zzwv() {
    }

    public /* bridge */ /* synthetic */ void zzyv() {
        super.zzyv();
    }

    public /* bridge */ /* synthetic */ zze zzyw() {
        return super.zzyw();
    }
}

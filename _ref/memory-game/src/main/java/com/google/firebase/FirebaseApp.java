package com.google.firebase;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzy;
import com.google.android.gms.common.util.zzc;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.internal.zzalo;
import com.google.android.gms.internal.zzalp;
import com.google.android.gms.internal.zzalq;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class FirebaseApp {
    public static final String DEFAULT_APP_NAME = "[DEFAULT]";
    private static final List<String> aMv = Arrays.asList(new String[]{"com.google.firebase.auth.FirebaseAuth", "com.google.firebase.iid.FirebaseInstanceId"});
    private static final List<String> aMw = Collections.singletonList("com.google.firebase.crash.FirebaseCrash");
    private static final List<String> aMx = Arrays.asList(new String[]{"com.google.android.gms.measurement.AppMeasurement"});
    private static final Set<String> aMy = Collections.emptySet();
    static final Map<String, FirebaseApp> aap = new ArrayMap();
    private static final Object zzamr = new Object();
    private final AtomicBoolean aMA = new AtomicBoolean(true);
    private final AtomicBoolean aMB = new AtomicBoolean();
    private final List<zza> aMC = new CopyOnWriteArrayList();
    private final List<zzb> aMD = new CopyOnWriteArrayList();
    private final List<Object> aME = new CopyOnWriteArrayList();
    protected zzalq aMF;
    private final FirebaseOptions aMz;
    private final String mName;
    private final Context zzaql;

    public interface zza {
        void zzb(@NonNull zzalq com_google_android_gms_internal_zzalq, @Nullable FirebaseUser firebaseUser);
    }

    public interface zzb {
        void zzcl(boolean z);
    }

    protected FirebaseApp(Context context, String str, FirebaseOptions firebaseOptions) {
        this.zzaql = (Context) zzab.zzy(context);
        this.mName = zzab.zzhr(str);
        this.aMz = (FirebaseOptions) zzab.zzy(firebaseOptions);
    }

    public static List<FirebaseApp> getApps(Context context) {
        List<FirebaseApp> arrayList;
        zzalp zzeq = zzalp.zzeq(context);
        synchronized (zzamr) {
            arrayList = new ArrayList(aap.values());
            Set<String> zzcxd = zzalp.zzcxc().zzcxd();
            zzcxd.removeAll(aap.keySet());
            for (String str : zzcxd) {
                arrayList.add(initializeApp(context, zzeq.zzta(str), str));
            }
        }
        return arrayList;
    }

    @Nullable
    public static FirebaseApp getInstance() {
        return getInstance(DEFAULT_APP_NAME);
    }

    public static FirebaseApp getInstance(@NonNull String str) {
        FirebaseApp firebaseApp;
        synchronized (zzamr) {
            firebaseApp = (FirebaseApp) aap.get(zzra(str));
            if (firebaseApp != null) {
            } else {
                String str2;
                Iterable zzckd = zzckd();
                if (zzckd.isEmpty()) {
                    str2 = "";
                } else {
                    String str3 = "Available app names: ";
                    str2 = String.valueOf(zzy.zzhq(", ").zza(zzckd));
                    str2 = str2.length() != 0 ? str3.concat(str2) : new String(str3);
                }
                throw new IllegalStateException(String.format("FirebaseApp with name %s doesn't exist. %s", new Object[]{str, str2}));
            }
        }
        return firebaseApp;
    }

    public static FirebaseApp initializeApp(Context context, FirebaseOptions firebaseOptions) {
        return initializeApp(context, firebaseOptions, DEFAULT_APP_NAME);
    }

    public static FirebaseApp initializeApp(Context context, FirebaseOptions firebaseOptions, String str) {
        FirebaseApp firebaseApp;
        zzalp zzeq = zzalp.zzeq(context);
        zzel(context);
        String zzra = zzra(str);
        Object applicationContext = context.getApplicationContext();
        synchronized (zzamr) {
            zzab.zza(!aap.containsKey(zzra), new StringBuilder(String.valueOf(zzra).length() + 33).append("FirebaseApp name ").append(zzra).append(" already exists!").toString());
            zzab.zzb(applicationContext, (Object) "Application context cannot be null.");
            firebaseApp = new FirebaseApp(applicationContext, zzra, firebaseOptions);
            aap.put(zzra, firebaseApp);
        }
        zzeq.zzf(firebaseApp);
        zza(FirebaseApp.class, firebaseApp, aMv);
        if (firebaseApp.zzckb()) {
            zza(FirebaseApp.class, firebaseApp, aMw);
            zza(Context.class, firebaseApp.getApplicationContext(), aMx);
        }
        return firebaseApp;
    }

    private static <T> void zza(Class<T> cls, T t, Iterable<String> iterable) {
        for (String str : iterable) {
            String str2;
            try {
                Method method = Class.forName(str2).getMethod("getInstance", new Class[]{cls});
                int modifiers = method.getModifiers();
                if (Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers)) {
                    method.invoke(null, new Object[]{t});
                }
            } catch (ClassNotFoundException e) {
                if (aMy.contains(str2)) {
                    throw new IllegalStateException(String.valueOf(str2).concat(" is missing, but is required. Check if it has been removed by Proguard."));
                }
                Log.d("FirebaseApp", String.valueOf(str2).concat(" is not linked. Skipping initialization."));
            } catch (NoSuchMethodException e2) {
                throw new IllegalStateException(String.valueOf(str2).concat("#getInstance has been removed by Proguard. Add keep rule to prevent it."));
            } catch (Throwable e3) {
                Log.wtf("FirebaseApp", "Firebase API initialization failure.", e3);
            } catch (Throwable e4) {
                String str3 = "FirebaseApp";
                String str4 = "Failed to initialize ";
                str2 = String.valueOf(str2);
                Log.wtf(str3, str2.length() != 0 ? str4.concat(str2) : new String(str4), e4);
            }
        }
    }

    private void zzcka() {
        zzab.zza(!this.aMB.get(), (Object) "FirebaseApp was deleted");
    }

    private static List<String> zzckd() {
        Collection com_google_android_gms_common_util_zza = new com.google.android.gms.common.util.zza();
        synchronized (zzamr) {
            for (FirebaseApp name : aap.values()) {
                com_google_android_gms_common_util_zza.add(name.getName());
            }
            zzalp zzcxc = zzalp.zzcxc();
            if (zzcxc != null) {
                com_google_android_gms_common_util_zza.addAll(zzcxc.zzcxd());
            }
        }
        List<String> arrayList = new ArrayList(com_google_android_gms_common_util_zza);
        Collections.sort(arrayList);
        return arrayList;
    }

    public static void zzcl(boolean z) {
        synchronized (zzamr) {
            Iterator it = new ArrayList(aap.values()).iterator();
            while (it.hasNext()) {
                FirebaseApp firebaseApp = (FirebaseApp) it.next();
                if (firebaseApp.aMA.get()) {
                    firebaseApp.zzcm(z);
                }
            }
        }
    }

    private void zzcm(boolean z) {
        Log.d("FirebaseApp", "Notifying background state change listeners.");
        for (zzb zzcl : this.aMD) {
            zzcl.zzcl(z);
        }
    }

    public static FirebaseApp zzek(Context context) {
        FirebaseOptions fromResource = FirebaseOptions.fromResource(context);
        return fromResource == null ? null : initializeApp(context, fromResource);
    }

    @TargetApi(14)
    private static void zzel(Context context) {
        if (zzs.zzavq() && (context.getApplicationContext() instanceof Application)) {
            zzalo.zza((Application) context.getApplicationContext());
        }
    }

    private static String zzra(@NonNull String str) {
        return str.trim();
    }

    public boolean equals(Object obj) {
        return !(obj instanceof FirebaseApp) ? false : this.mName.equals(((FirebaseApp) obj).getName());
    }

    @NonNull
    public Context getApplicationContext() {
        zzcka();
        return this.zzaql;
    }

    @NonNull
    public String getName() {
        zzcka();
        return this.mName;
    }

    @NonNull
    public FirebaseOptions getOptions() {
        zzcka();
        return this.aMz;
    }

    public Task<GetTokenResult> getToken(boolean z) {
        zzcka();
        return this.aMF == null ? Tasks.forException(new FirebaseApiNotAvailableException("firebase-auth is not linked, please fall back to unauthenticated mode.")) : this.aMF.zza(this.aMF.getCurrentUser(), z);
    }

    public int hashCode() {
        return this.mName.hashCode();
    }

    public String toString() {
        return zzaa.zzx(this).zzg("name", this.mName).zzg("options", this.aMz).toString();
    }

    public void zza(@NonNull zzalq com_google_android_gms_internal_zzalq) {
        this.aMF = (zzalq) zzab.zzy(com_google_android_gms_internal_zzalq);
    }

    @UiThread
    public void zza(zzalq com_google_android_gms_internal_zzalq, FirebaseUser firebaseUser) {
        Log.d("FirebaseApp", "Notifying auth state listeners.");
        int i = 0;
        for (zza zzb : this.aMC) {
            zzb.zzb(com_google_android_gms_internal_zzalq, firebaseUser);
            i++;
        }
        Log.d("FirebaseApp", String.format("Notified %d auth state listeners.", new Object[]{Integer.valueOf(i)}));
    }

    public void zza(@NonNull zza com_google_firebase_FirebaseApp_zza) {
        zzcka();
        zzab.zzy(com_google_firebase_FirebaseApp_zza);
        this.aMC.add(com_google_firebase_FirebaseApp_zza);
    }

    public zzalq zzcjz() {
        zzcka();
        return this.aMF;
    }

    public boolean zzckb() {
        return DEFAULT_APP_NAME.equals(getName());
    }

    public String zzckc() {
        String valueOf = String.valueOf(zzc.zzr(getName().getBytes()));
        String valueOf2 = String.valueOf(zzc.zzr(getOptions().getApplicationId().getBytes()));
        return new StringBuilder((String.valueOf(valueOf).length() + 1) + String.valueOf(valueOf2).length()).append(valueOf).append("+").append(valueOf2).toString();
    }
}

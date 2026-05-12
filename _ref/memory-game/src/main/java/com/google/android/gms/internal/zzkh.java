package com.google.android.gms.internal;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.app.AlertDialog.Builder;
import android.app.KeyguardManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Debug;
import android.os.Debug.MemoryInfo;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.os.PowerManager;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.PopupWindow;
import com.amazonaws.services.s3.internal.Constants;
import com.google.android.gms.ads.AdActivity;
import com.google.android.gms.ads.internal.ClientApi;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.zzm;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzo;
import com.google.android.gms.ads.internal.zzu;
import com.mopub.volley.DefaultRetryPolicy;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Future;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzin
public class zzkh {
    public static final Handler zzclc = new zzke(Looper.getMainLooper());
    private final Object zzail = new Object();
    private String zzbjf;
    private zzfs zzcea;
    private boolean zzcld = true;
    private boolean zzcle = false;

    private JSONArray zza(Collection<?> collection) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (Object zza : collection) {
            zza(jSONArray, zza);
        }
        return jSONArray;
    }

    private void zza(JSONArray jSONArray, Object obj) throws JSONException {
        if (obj instanceof Bundle) {
            jSONArray.put(zzh((Bundle) obj));
        } else if (obj instanceof Map) {
            jSONArray.put(zzam((Map) obj));
        } else if (obj instanceof Collection) {
            jSONArray.put(zza((Collection) obj));
        } else if (obj instanceof Object[]) {
            jSONArray.put(zza((Object[]) obj));
        } else {
            jSONArray.put(obj);
        }
    }

    private void zza(JSONObject jSONObject, String str, Object obj) throws JSONException {
        if (obj instanceof Bundle) {
            jSONObject.put(str, zzh((Bundle) obj));
        } else if (obj instanceof Map) {
            jSONObject.put(str, zzam((Map) obj));
        } else if (obj instanceof Collection) {
            if (str == null) {
                str = Constants.NULL_VERSION_ID;
            }
            jSONObject.put(str, zza((Collection) obj));
        } else if (obj instanceof Object[]) {
            jSONObject.put(str, zza(Arrays.asList((Object[]) obj)));
        } else {
            jSONObject.put(str, obj);
        }
    }

    private boolean zza(KeyguardManager keyguardManager) {
        return keyguardManager == null ? false : keyguardManager.inKeyguardRestrictedInputMode();
    }

    private boolean zza(PowerManager powerManager) {
        return powerManager == null || powerManager.isScreenOn();
    }

    public static void zzb(Runnable runnable) {
        if (Looper.getMainLooper().getThread() != Thread.currentThread()) {
            runnable.run();
        } else {
            zzkg.zza(runnable);
        }
    }

    private JSONObject zzh(Bundle bundle) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (String str : bundle.keySet()) {
            zza(jSONObject, str, bundle.get(str));
        }
        return jSONObject;
    }

    private boolean zzj(Context context) {
        PowerManager powerManager = (PowerManager) context.getSystemService("power");
        return powerManager == null ? false : powerManager.isScreenOn();
    }

    private Bitmap zzl(@NonNull View view) {
        try {
            int width = view.getWidth();
            int height = view.getHeight();
            if (width == 0 || height == 0) {
                zzb.zzcx("Width or height of view is zero");
                return null;
            }
            Bitmap createBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Config.RGB_565);
            Canvas canvas = new Canvas(createBitmap);
            view.layout(0, 0, width, height);
            view.draw(canvas);
            return createBitmap;
        } catch (Throwable e) {
            zzb.zzb("Fail to capture the webview", e);
            return null;
        }
    }

    private Bitmap zzm(@NonNull View view) {
        Bitmap drawingCache;
        Throwable e;
        try {
            boolean isDrawingCacheEnabled = view.isDrawingCacheEnabled();
            view.setDrawingCacheEnabled(true);
            drawingCache = view.getDrawingCache();
            drawingCache = drawingCache != null ? Bitmap.createBitmap(drawingCache) : null;
            try {
                view.setDrawingCacheEnabled(isDrawingCacheEnabled);
            } catch (RuntimeException e2) {
                e = e2;
                zzb.zzb("Fail to capture the web view", e);
                return drawingCache;
            }
        } catch (Throwable e3) {
            Throwable th = e3;
            drawingCache = null;
            e = th;
            zzb.zzb("Fail to capture the web view", e);
            return drawingCache;
        }
        return drawingCache;
    }

    public void runOnUiThread(Runnable runnable) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            runnable.run();
        } else {
            zzclc.post(runnable);
        }
    }

    public DisplayMetrics zza(WindowManager windowManager) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics;
    }

    public PopupWindow zza(View view, int i, int i2, boolean z) {
        return new PopupWindow(view, i, i2, z);
    }

    public String zza(Context context, View view, AdSizeParcel adSizeParcel) {
        String str = null;
        if (((Boolean) zzdc.zzazx.get()).booleanValue()) {
            try {
                JSONObject jSONObject = new JSONObject();
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("width", adSizeParcel.width);
                jSONObject2.put("height", adSizeParcel.height);
                jSONObject.put("size", jSONObject2);
                jSONObject.put("activity", zzah(context));
                if (!adSizeParcel.zzaus) {
                    JSONArray jSONArray = new JSONArray();
                    while (view != null) {
                        ViewParent parent = view.getParent();
                        if (parent != null) {
                            int i = -1;
                            if (parent instanceof ViewGroup) {
                                i = ((ViewGroup) parent).indexOfChild(view);
                            }
                            JSONObject jSONObject3 = new JSONObject();
                            jSONObject3.put("type", parent.getClass().getName());
                            jSONObject3.put("index_of_child", i);
                            jSONArray.put(jSONObject3);
                        }
                        View view2 = (parent == null || !(parent instanceof View)) ? null : (View) parent;
                        view = view2;
                    }
                    if (jSONArray.length() > 0) {
                        jSONObject.put("parents", jSONArray);
                    }
                }
                str = jSONObject.toString();
            } catch (Throwable e) {
                zzb.zzd("Fail to get view hierarchy json", e);
            }
        }
        return str;
    }

    public String zza(Context context, zzas com_google_android_gms_internal_zzas, String str) {
        if (com_google_android_gms_internal_zzas != null) {
            try {
                Uri parse = Uri.parse(str);
                if (com_google_android_gms_internal_zzas.zzd(parse)) {
                    parse = com_google_android_gms_internal_zzas.zzb(parse, context);
                }
                str = parse.toString();
            } catch (Exception e) {
            }
        }
        return str;
    }

    public String zza(zzlh com_google_android_gms_internal_zzlh, String str) {
        return zza(com_google_android_gms_internal_zzlh.getContext(), com_google_android_gms_internal_zzlh.zzul(), str);
    }

    public String zza(InputStreamReader inputStreamReader) throws IOException {
        StringBuilder stringBuilder = new StringBuilder(8192);
        char[] cArr = new char[2048];
        while (true) {
            int read = inputStreamReader.read(cArr);
            if (read == -1) {
                return stringBuilder.toString();
            }
            stringBuilder.append(cArr, 0, read);
        }
    }

    JSONArray zza(Object[] objArr) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (Object zza : objArr) {
            zza(jSONArray, zza);
        }
        return jSONArray;
    }

    public void zza(Activity activity, OnGlobalLayoutListener onGlobalLayoutListener) {
        Window window = activity.getWindow();
        if (window != null && window.getDecorView() != null && window.getDecorView().getViewTreeObserver() != null) {
            window.getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(onGlobalLayoutListener);
        }
    }

    public void zza(Activity activity, OnScrollChangedListener onScrollChangedListener) {
        Window window = activity.getWindow();
        if (window != null && window.getDecorView() != null && window.getDecorView().getViewTreeObserver() != null) {
            window.getDecorView().getViewTreeObserver().addOnScrollChangedListener(onScrollChangedListener);
        }
    }

    public void zza(Context context, String str, WebSettings webSettings) {
        webSettings.setUserAgentString(zzg(context, str));
    }

    public void zza(Context context, @Nullable String str, String str2, Bundle bundle, boolean z) {
        if (z) {
            bundle.putString("device", zzu.zzfq().zztg());
            bundle.putString("eids", TextUtils.join(",", zzdc.zzjx()));
        }
        zzm.zziw().zza(context, str, str2, bundle, z, new 3(this, context, str));
    }

    public void zza(Context context, String str, List<String> list) {
        for (String com_google_android_gms_internal_zzkq : list) {
            Future future = (Future) new zzkq(context, str, com_google_android_gms_internal_zzkq).zzpy();
        }
    }

    public void zza(Context context, String str, boolean z, HttpURLConnection httpURLConnection) {
        zza(context, str, z, httpURLConnection, false);
    }

    public void zza(Context context, String str, boolean z, HttpURLConnection httpURLConnection, boolean z2) {
        httpURLConnection.setConnectTimeout(60000);
        httpURLConnection.setInstanceFollowRedirects(z);
        httpURLConnection.setReadTimeout(60000);
        httpURLConnection.setRequestProperty("User-Agent", zzg(context, str));
        httpURLConnection.setUseCaches(z2);
    }

    public void zza(Context context, List<String> list) {
        if (!(context instanceof Activity) || TextUtils.isEmpty(zzaqa.zzex((Activity) context))) {
            return;
        }
        if (list == null) {
            zzkd.v("Cannot ping urls: empty list.");
        } else if (zzdq.zzo(context)) {
            zzdq com_google_android_gms_internal_zzdq = new zzdq();
            com_google_android_gms_internal_zzdq.zza(new 1(this, list, com_google_android_gms_internal_zzdq, context));
            com_google_android_gms_internal_zzdq.zze((Activity) context);
        } else {
            zzkd.v("Cannot ping url because custom tabs is not supported");
        }
    }

    public void zza(List<String> list, String str) {
        for (String com_google_android_gms_internal_zzkq : list) {
            Future future = (Future) new zzkq(com_google_android_gms_internal_zzkq, str).zzpy();
        }
    }

    public boolean zza(PackageManager packageManager, String str, String str2) {
        return packageManager.checkPermission(str2, str) == 0;
    }

    public boolean zza(View view, Context context) {
        KeyguardManager keyguardManager = null;
        Context applicationContext = context.getApplicationContext();
        PowerManager powerManager = applicationContext != null ? (PowerManager) applicationContext.getSystemService("power") : null;
        Object systemService = context.getSystemService("keyguard");
        if (systemService != null && (systemService instanceof KeyguardManager)) {
            keyguardManager = (KeyguardManager) systemService;
        }
        return zza(view, powerManager, keyguardManager);
    }

    public boolean zza(View view, PowerManager powerManager, KeyguardManager keyguardManager) {
        boolean z = zzu.zzfq().zztc() || !zza(keyguardManager);
        return view.getVisibility() == 0 && view.isShown() && zza(powerManager) && z && (!((Boolean) zzdc.zzbaq.get()).booleanValue() || view.getLocalVisibleRect(new Rect()) || view.getGlobalVisibleRect(new Rect()));
    }

    public boolean zza(ClassLoader classLoader, Class<?> cls, String str) {
        boolean z = false;
        try {
            z = cls.isAssignableFrom(Class.forName(str, false, classLoader));
        } catch (Throwable th) {
        }
        return z;
    }

    public boolean zzac(Context context) {
        Intent intent = new Intent();
        intent.setClassName(context, AdActivity.CLASS_NAME);
        ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 65536);
        if (resolveActivity == null || resolveActivity.activityInfo == null) {
            zzb.zzcx("Could not find com.google.android.gms.ads.AdActivity, please make sure it is declared in AndroidManifest.xml.");
            return false;
        }
        boolean z;
        String str = "com.google.android.gms.ads.AdActivity requires the android:configChanges value to contain \"%s\".";
        if ((resolveActivity.activityInfo.configChanges & 16) == 0) {
            zzb.zzcx(String.format(str, new Object[]{"keyboard"}));
            z = false;
        } else {
            z = true;
        }
        if ((resolveActivity.activityInfo.configChanges & 32) == 0) {
            zzb.zzcx(String.format(str, new Object[]{"keyboardHidden"}));
            z = false;
        }
        if ((resolveActivity.activityInfo.configChanges & 128) == 0) {
            zzb.zzcx(String.format(str, new Object[]{"orientation"}));
            z = false;
        }
        if ((resolveActivity.activityInfo.configChanges & 256) == 0) {
            zzb.zzcx(String.format(str, new Object[]{"screenLayout"}));
            z = false;
        }
        if ((resolveActivity.activityInfo.configChanges & 512) == 0) {
            zzb.zzcx(String.format(str, new Object[]{"uiMode"}));
            z = false;
        }
        if ((resolveActivity.activityInfo.configChanges & 1024) == 0) {
            zzb.zzcx(String.format(str, new Object[]{"screenSize"}));
            z = false;
        }
        if ((resolveActivity.activityInfo.configChanges & 2048) != 0) {
            return z;
        }
        zzb.zzcx(String.format(str, new Object[]{"smallestScreenSize"}));
        return false;
    }

    public boolean zzad(Context context) {
        if (this.zzcle) {
            return false;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.USER_PRESENT");
        intentFilter.addAction("android.intent.action.SCREEN_OFF");
        context.getApplicationContext().registerReceiver(new zza(this, null), intentFilter);
        this.zzcle = true;
        return true;
    }

    protected String zzae(Context context) {
        return new WebView(context).getSettings().getUserAgentString();
    }

    public Builder zzaf(Context context) {
        return new Builder(context);
    }

    public zzcu zzag(Context context) {
        return new zzcu(context);
    }

    public String zzah(Context context) {
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            if (activityManager == null) {
                return null;
            }
            List runningTasks = activityManager.getRunningTasks(1);
            if (!(runningTasks == null || runningTasks.isEmpty())) {
                RunningTaskInfo runningTaskInfo = (RunningTaskInfo) runningTasks.get(0);
                if (!(runningTaskInfo == null || runningTaskInfo.topActivity == null)) {
                    return runningTaskInfo.topActivity.getClassName();
                }
            }
            return null;
        } catch (Exception e) {
        }
    }

    public boolean zzai(Context context) {
        try {
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService("keyguard");
            if (activityManager == null || keyguardManager == null) {
                return false;
            }
            List<RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
            if (runningAppProcesses == null) {
                return false;
            }
            for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (Process.myPid() == runningAppProcessInfo.pid) {
                    if (runningAppProcessInfo.importance == 100 && !keyguardManager.inKeyguardRestrictedInputMode() && zzj(context)) {
                        return true;
                    }
                    return false;
                }
            }
            return false;
        } catch (Throwable th) {
            return false;
        }
    }

    public Bitmap zzaj(Context context) {
        if (!(context instanceof Activity)) {
            return null;
        }
        Bitmap zzm;
        try {
            if (((Boolean) zzdc.zzbcb.get()).booleanValue()) {
                Window window = ((Activity) context).getWindow();
                if (window != null) {
                    zzm = zzm(window.getDecorView().getRootView());
                }
                zzm = null;
            } else {
                zzm = zzl(((Activity) context).getWindow().getDecorView());
            }
        } catch (Throwable e) {
            zzb.zzb("Fail to capture screen shot", e);
        }
        return zzm;
    }

    public AudioManager zzak(Context context) {
        return (AudioManager) context.getSystemService("audio");
    }

    public float zzal(Context context) {
        AudioManager zzak = zzak(context);
        if (zzak == null) {
            return 0.0f;
        }
        int streamMaxVolume = zzak.getStreamMaxVolume(3);
        return streamMaxVolume != 0 ? ((float) zzak.getStreamVolume(3)) / ((float) streamMaxVolume) : 0.0f;
    }

    public int zzam(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        return applicationInfo == null ? 0 : applicationInfo.targetSdkVersion;
    }

    public JSONObject zzam(Map<String, ?> map) throws JSONException {
        String valueOf;
        try {
            JSONObject jSONObject = new JSONObject();
            for (String valueOf2 : map.keySet()) {
                zza(jSONObject, valueOf2, map.get(valueOf2));
            }
            return jSONObject;
        } catch (ClassCastException e) {
            String str = "Could not convert map to JSON: ";
            valueOf2 = String.valueOf(e.getMessage());
            throw new JSONException(valueOf2.length() != 0 ? str.concat(valueOf2) : new String(str));
        }
    }

    public boolean zzan(Context context) {
        try {
            context.getClassLoader().loadClass(ClientApi.class.getName());
            return false;
        } catch (ClassNotFoundException e) {
            return true;
        }
    }

    public String zzb(String str, Map<String, String> map) {
        for (String str2 : map.keySet()) {
            str = str.replaceAll(String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", new Object[]{str2}), String.format("$1%s$2", new Object[]{Uri.encode((String) map.get(str2))}));
        }
        return str.replaceAll(String.format("(?<!@)((?:@@)*)@%s(?<!@)((?:@@)*)@", new Object[]{"[^@]+"}), String.format("$1%s$2", new Object[]{""})).replaceAll("@@", "@");
    }

    public void zzb(Activity activity, OnScrollChangedListener onScrollChangedListener) {
        Window window = activity.getWindow();
        if (window != null && window.getDecorView() != null && window.getDecorView().getViewTreeObserver() != null) {
            window.getDecorView().getViewTreeObserver().removeOnScrollChangedListener(onScrollChangedListener);
        }
    }

    public void zzb(Context context, Intent intent) {
        try {
            context.startActivity(intent);
        } catch (Throwable th) {
            intent.addFlags(268435456);
            context.startActivity(intent);
        }
    }

    public void zzb(Context context, String str, String str2, Bundle bundle, boolean z) {
        if (((Boolean) zzdc.zzbau.get()).booleanValue()) {
            zza(context, str, str2, bundle, z);
        }
    }

    public zzfs zzc(Context context, VersionInfoParcel versionInfoParcel) {
        zzfs com_google_android_gms_internal_zzfs;
        synchronized (this.zzail) {
            if (this.zzcea == null) {
                if (context.getApplicationContext() != null) {
                    context = context.getApplicationContext();
                }
                this.zzcea = new zzfs(context, versionInfoParcel, (String) zzdc.zzaxy.get());
            }
            com_google_android_gms_internal_zzfs = this.zzcea;
        }
        return com_google_android_gms_internal_zzfs;
    }

    public void zzc(Context context, String str, String str2) {
        List arrayList = new ArrayList();
        arrayList.add(str2);
        zza(context, str, arrayList);
    }

    public String zzco(String str) {
        return Uri.parse(str).buildUpon().query(null).build().toString();
    }

    public int zzcp(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            String valueOf = String.valueOf(e);
            zzb.zzcx(new StringBuilder(String.valueOf(valueOf).length() + 22).append("Could not parse value:").append(valueOf).toString());
            return 0;
        }
    }

    public boolean zzcq(String str) {
        return TextUtils.isEmpty(str) ? false : str.matches("([^\\s]+(\\.(?i)(jpg|png|gif|bmp|webp))$)");
    }

    public float zzey() {
        zzo zzex = zzu.zzgg().zzex();
        return (zzex == null || !zzex.zzez()) ? DefaultRetryPolicy.DEFAULT_BACKOFF_MULT : zzex.zzey();
    }

    public Map<String, String> zzf(Uri uri) {
        if (uri == null) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (String str : zzu.zzfs().zzg(uri)) {
            hashMap.put(str, uri.getQueryParameter(str));
        }
        return hashMap;
    }

    public boolean zzfa() {
        zzo zzex = zzu.zzgg().zzex();
        return zzex != null ? zzex.zzfa() : false;
    }

    public String zzg(Context context, String str) {
        String str2;
        synchronized (this.zzail) {
            if (this.zzbjf != null) {
                str2 = this.zzbjf;
            } else {
                try {
                    this.zzbjf = zzu.zzfs().getDefaultUserAgent(context);
                } catch (Exception e) {
                }
                if (TextUtils.isEmpty(this.zzbjf)) {
                    if (zzm.zziw().zztx()) {
                        try {
                            this.zzbjf = zzae(context);
                        } catch (Exception e2) {
                            this.zzbjf = zztd();
                        }
                    } else {
                        this.zzbjf = null;
                        zzclc.post(new 2(this, context));
                        while (this.zzbjf == null) {
                            try {
                                this.zzail.wait();
                            } catch (InterruptedException e3) {
                                this.zzbjf = zztd();
                                String str3 = "Interrupted, use default user agent: ";
                                str2 = String.valueOf(this.zzbjf);
                                zzb.zzcx(str2.length() != 0 ? str3.concat(str2) : new String(str3));
                            }
                        }
                    }
                }
                str2 = String.valueOf(this.zzbjf);
                this.zzbjf = new StringBuilder((String.valueOf(str2).length() + 11) + String.valueOf(str).length()).append(str2).append(" (Mobile; ").append(str).append(")").toString();
                str2 = this.zzbjf;
            }
        }
        return str2;
    }

    public int[] zzh(Activity activity) {
        Window window = activity.getWindow();
        if (window == null || window.findViewById(16908290) == null) {
            return zzth();
        }
        return new int[]{window.findViewById(16908290).getWidth(), window.findViewById(16908290).getHeight()};
    }

    public int[] zzi(Activity activity) {
        int[] zzh = zzh(activity);
        return new int[]{zzm.zziw().zzb((Context) activity, zzh[0]), zzm.zziw().zzb((Context) activity, zzh[1])};
    }

    public int[] zzj(Activity activity) {
        Window window = activity.getWindow();
        if (window == null || window.findViewById(16908290) == null) {
            return zzth();
        }
        return new int[]{window.findViewById(16908290).getTop(), window.findViewById(16908290).getBottom()};
    }

    public Bitmap zzk(View view) {
        view.setDrawingCacheEnabled(true);
        Bitmap createBitmap = Bitmap.createBitmap(view.getDrawingCache());
        view.setDrawingCacheEnabled(false);
        return createBitmap;
    }

    public int[] zzk(Activity activity) {
        int[] zzj = zzj(activity);
        return new int[]{zzm.zziw().zzb((Context) activity, zzj[0]), zzm.zziw().zzb((Context) activity, zzj[1])};
    }

    public int zzn(@Nullable View view) {
        if (view == null) {
            return -1;
        }
        ViewParent parent = view.getParent();
        while (parent != null && !(parent instanceof AdapterView)) {
            parent = parent.getParent();
        }
        return parent == null ? -1 : ((AdapterView) parent).getPositionForView(view);
    }

    public boolean zztc() {
        return this.zzcld;
    }

    String zztd() {
        StringBuffer stringBuffer = new StringBuffer(256);
        stringBuffer.append("Mozilla/5.0 (Linux; U; Android");
        if (VERSION.RELEASE != null) {
            stringBuffer.append(" ").append(VERSION.RELEASE);
        }
        stringBuffer.append("; ").append(Locale.getDefault());
        if (Build.DEVICE != null) {
            stringBuffer.append("; ").append(Build.DEVICE);
            if (Build.DISPLAY != null) {
                stringBuffer.append(" Build/").append(Build.DISPLAY);
            }
        }
        stringBuffer.append(") AppleWebKit/533 Version/4.0 Safari/533");
        return stringBuffer.toString();
    }

    public String zzte() {
        return UUID.randomUUID().toString();
    }

    public String zztf() {
        UUID randomUUID = UUID.randomUUID();
        byte[] toByteArray = BigInteger.valueOf(randomUUID.getLeastSignificantBits()).toByteArray();
        byte[] toByteArray2 = BigInteger.valueOf(randomUUID.getMostSignificantBits()).toByteArray();
        String bigInteger = new BigInteger(1, toByteArray).toString();
        for (int i = 0; i < 2; i++) {
            try {
                MessageDigest instance = MessageDigest.getInstance(CommonUtils.MD5_INSTANCE);
                instance.update(toByteArray);
                instance.update(toByteArray2);
                Object obj = new byte[8];
                System.arraycopy(instance.digest(), 0, obj, 0, 8);
                bigInteger = new BigInteger(1, obj).toString();
            } catch (NoSuchAlgorithmException e) {
            }
        }
        return bigInteger;
    }

    public String zztg() {
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        return str2.startsWith(str) ? str2 : new StringBuilder((String.valueOf(str).length() + 1) + String.valueOf(str2).length()).append(str).append(" ").append(str2).toString();
    }

    protected int[] zzth() {
        return new int[]{0, 0};
    }

    public Bundle zzti() {
        Bundle bundle = new Bundle();
        try {
            Parcelable memoryInfo = new MemoryInfo();
            Debug.getMemoryInfo(memoryInfo);
            bundle.putParcelable("debug_memory_info", memoryInfo);
            Runtime runtime = Runtime.getRuntime();
            bundle.putLong("runtime_free_memory", runtime.freeMemory());
            bundle.putLong("runtime_max_memory", runtime.maxMemory());
            bundle.putLong("runtime_total_memory", runtime.totalMemory());
        } catch (Throwable e) {
            zzb.zzd("Unable to gather memory stats", e);
        }
        return bundle;
    }
}

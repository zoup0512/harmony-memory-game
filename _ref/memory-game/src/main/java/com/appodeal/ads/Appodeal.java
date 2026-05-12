package com.appodeal.ads;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.appodeal.ads.Native.NativeAdType;
import com.appodeal.ads.utils.Log.LogLevel;
import com.appodeal.ads.utils.PermissionsHelper;
import com.appodeal.ads.utils.PermissionsHelper.AppodealPermissionCallbacks;
import com.appodeal.ads.utils.a.b;
import com.appodeal.ads.utils.b.a;
import com.appodeal.ads.utils.c;
import com.appodeal.ads.utils.d;
import com.appodeal.ads.utils.f;
import com.appodeal.ads.utils.g;
import com.appodeal.ads.utils.p;
import com.mopub.mobileads.resource.DrawableConstants.RadialCountdown;
import io.branch.referral.Branch;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import org.json.JSONObject;

public class Appodeal {
    public static final int BANNER = 4;
    public static final int BANNER_BOTTOM = 8;
    public static final int BANNER_TOP = 16;
    public static final int BANNER_VIEW = 64;
    public static final int INTERSTITIAL = 1;
    public static final int MREC = 256;
    public static final int NATIVE = 512;
    public static final int NONE = 0;
    public static final int NON_SKIPPABLE_VIDEO = 128;
    public static final int REWARDED_VIDEO = 128;
    public static final int SKIPPABLE_VIDEO = 2;
    static boolean a = false;
    public static Activity b;
    public static LoaderActivity c;
    public static p d;
    static f e = new f();
    private static boolean f = false;
    private static boolean g = false;

    private static boolean a() {
        return VERSION.SDK_INT < 9;
    }

    @SuppressLint({"NewApi"})
    public static void initialize(@NonNull final Activity activity, @NonNull String str, int i) {
        if (activity == null) {
            a(new a("Unable to initialize Appodeal: activity = null"));
        } else if (str == null) {
            a(new a("Unable to initialize Appodeal: appKey = null"));
        } else if (!a()) {
            b = activity;
            if (!(f || g)) {
                f = true;
                try {
                    g.a((Context) activity);
                    an.a(activity, str);
                    an.a(activity);
                    d.a((Context) activity);
                    c.a(activity);
                    c.e(activity);
                    c.f(activity);
                    f.a(activity);
                    b.c(activity);
                    if (VERSION.SDK_INT >= 14) {
                        activity.getApplication().registerActivityLifecycleCallbacks(new a());
                        activity.getApplication().registerComponentCallbacks(new a());
                    }
                    m.a();
                    e = new f();
                    a(String.format("SDK v%s initialized, appKey: %s, package name: %s", new Object[]{"1.15.7", str, activity.getPackageName()}));
                    a(String.format("Google play services version: %s", new Object[]{an.b(activity)}));
                    g = true;
                } catch (Throwable e) {
                    a(e);
                }
                f = false;
            }
            if ((i & 1) > 0) {
                n.a(activity);
            }
            if ((i & 128) > 0) {
                ak.a(activity);
            }
            if ((i & 2) > 0) {
                if (!ah.d) {
                    activity.runOnUiThread(new Runnable() {
                        @SuppressLint({"SetTextI18n"})
                        public void run() {
                            Builder builder = new Builder(activity);
                            View textView = new TextView(activity);
                            textView.setText("To produce higher eCPM we recommend using Appodeal.NON_SKIPPABLE_VIDEO. If you're sure that you want to use cheaper skippable videos hide this popup by calling Appodeal.confirm(Appodeal.SKIPPABLE_VIDEO) before initialization");
                            textView.setPadding(20, 20, 20, 0);
                            textView.setGravity(1);
                            textView.setTextSize(RadialCountdown.TEXT_SIZE_SP);
                            builder.setView(textView).setCancelable(true).setPositiveButton("OK", new OnClickListener(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            });
                            builder.create().show();
                        }
                    });
                }
                ah.a(activity);
            }
            if ((i & 92) > 0) {
                g.a(activity);
            }
            if ((i & 256) > 0) {
                v.a(activity);
            }
            if ((i & 512) > 0) {
                Native.a(activity);
            }
        }
    }

    public static void setInterstitialCallbacks(InterstitialCallbacks interstitialCallbacks) {
        n.e = interstitialCallbacks;
    }

    public static void setSkippableVideoCallbacks(SkippableVideoCallbacks skippableVideoCallbacks) {
        ah.e = skippableVideoCallbacks;
    }

    public static void setRewardedVideoCallbacks(RewardedVideoCallbacks rewardedVideoCallbacks) {
        ak.d = rewardedVideoCallbacks;
    }

    public static void setNonSkippableVideoCallbacks(NonSkippableVideoCallbacks nonSkippableVideoCallbacks) {
        ak.e = nonSkippableVideoCallbacks;
    }

    public static void setBannerCallbacks(BannerCallbacks bannerCallbacks) {
        g.d = bannerCallbacks;
    }

    public static void setMrecCallbacks(MrecCallbacks mrecCallbacks) {
        v.d = mrecCallbacks;
    }

    public static void setNativeCallbacks(NativeCallbacks nativeCallbacks) {
        Native.d = nativeCallbacks;
    }

    public static void setNativeAdType(@NonNull NativeAdType nativeAdType) {
        if (nativeAdType == null) {
            a(new a("Unable to set NativeAdType to null"));
            return;
        }
        a(String.format("Set NativeAd type: %s", new Object[]{nativeAdType.toString()}), LogLevel.verbose);
        Native.A = nativeAdType;
    }

    public static void cache(@NonNull Activity activity, int i) {
        cache(activity, i, 1);
    }

    public static void cache(@NonNull Activity activity, int i, int i2) {
        int i3 = 10;
        int i4 = 1;
        if (activity == null) {
            a(new a("Unable to cache an ad: activity = null"));
        } else if (!a()) {
            ar a;
            b = activity;
            if ((i & 1) > 0 && !n.m) {
                s a2 = n.a();
                if (a2 == null || a2.b() || n.l) {
                    n.b(activity);
                } else if (a2.s && n.e != null) {
                    activity.runOnUiThread(new Runnable() {
                        public void run() {
                            if (n.e != null) {
                                n.e.onInterstitialLoaded(false);
                            }
                        }
                    });
                }
            }
            if ((i & 2) > 0 && !ah.k) {
                a = ah.a();
                if (a == null || a.b() || ah.j) {
                    ah.b(activity);
                } else if (a.r && ah.e != null) {
                    activity.runOnUiThread(new Runnable() {
                        public void run() {
                            if (ah.e != null) {
                                ah.e.onSkippableVideoLoaded();
                            }
                        }
                    });
                }
            }
            if ((i & 128) > 0 && !ak.k) {
                a = ak.a();
                if (a == null || a.b() || ak.j) {
                    ak.b(activity);
                } else if (a.r) {
                    if (ak.d != null) {
                        activity.runOnUiThread(new Runnable() {
                            public void run() {
                                if (ak.d != null) {
                                    ak.d.onRewardedVideoLoaded();
                                }
                            }
                        });
                    }
                    if (ak.e != null) {
                        activity.runOnUiThread(new Runnable() {
                            public void run() {
                                if (ak.e != null) {
                                    ak.e.onNonSkippableVideoLoaded();
                                }
                            }
                        });
                    }
                }
            }
            if ((i & 92) > 0 && !g.l) {
                final l a3 = g.a();
                if (a3 == null || a3.b() || g.k) {
                    g.b(activity);
                } else if (a3.s && g.d != null) {
                    activity.runOnUiThread(new Runnable() {
                        public void run() {
                            if (g.d != null) {
                                g.d.onBannerLoaded(a3.q.f().b, false);
                            }
                        }
                    });
                }
            }
            if ((i & 256) > 0 && !v.l) {
                aa a4 = v.a();
                if (a4 == null || a4.b() || v.k) {
                    v.b(activity);
                } else if (a4.r && v.d != null) {
                    activity.runOnUiThread(new Runnable() {
                        public void run() {
                            if (v.d != null) {
                                v.d.onMrecLoaded(false);
                            }
                        }
                    });
                }
            }
            if ((i & 512) > 0) {
                if (i2 <= 10) {
                    i3 = i2;
                }
                if (i3 >= 1) {
                    i4 = i3;
                }
                Native.m = i4;
                Native.b(activity);
            }
        }
    }

    public static boolean show(@NonNull Activity activity, int i) {
        return show(activity, i, Branch.REFERRAL_BUCKET_DEFAULT);
    }

    public static boolean show(@NonNull Activity activity, int i, @NonNull String str) {
        if (activity == null) {
            a(new a("Unable to show an ad: activity = null"));
            return false;
        } else if (str == null) {
            a(new a("Unable to show an ad: placement = null"));
            return false;
        } else if (a()) {
            return false;
        } else {
            b = activity;
            m.a().b();
            try {
                com.appodeal.ads.f.c a = com.appodeal.ads.f.d.a(str);
                switch (i) {
                    case 1:
                        return n.a(activity, a);
                    case 2:
                        return ah.a(activity, a);
                    case 4:
                    case 8:
                        return g.a(activity, a, g.b.BOTTOM);
                    case 16:
                        return g.a(activity, a, g.b.TOP);
                    case 64:
                        return g.a(activity, a, g.b.VIEW);
                    case 128:
                        return ak.a(activity, a);
                    case 256:
                        return v.a(activity, a);
                    default:
                        TreeMap treeMap = new TreeMap();
                        e a2 = g.a();
                        if ((i & 92) > 0 && a2 != null && a2.d() && a.a(4, a2)) {
                            if ((i & 12) > 0) {
                                treeMap.put(Double.valueOf(a2.b), Integer.valueOf(8));
                            }
                            if ((i & 16) > 0) {
                                treeMap.put(Double.valueOf(a2.b), Integer.valueOf(16));
                            }
                            if ((i & 64) > 0) {
                                treeMap.put(Double.valueOf(a2.b), Integer.valueOf(64));
                            }
                        }
                        a2 = v.a();
                        if ((i & 256) > 0 && a2 != null && a2.d() && a.a(256, a2)) {
                            treeMap.put(Double.valueOf(a2.b), Integer.valueOf(256));
                        }
                        a2 = n.a();
                        if ((i & 1) > 0 && a2 != null && a2.d() && a.a(1, a2)) {
                            treeMap.put(Double.valueOf(a2.b), Integer.valueOf(1));
                        }
                        a2 = ak.a();
                        if ((i & 128) > 0 && a2 != null && a2.d() && a.a(128, a2)) {
                            treeMap.put(Double.valueOf(a2.b), Integer.valueOf(128));
                        }
                        a2 = ah.a();
                        if ((i & 2) > 0 && a2 != null && a2.d() && a.a(2, a2)) {
                            treeMap.put(Double.valueOf(a2.b), Integer.valueOf(2));
                        }
                        if (!treeMap.isEmpty()) {
                            switch (((Integer) treeMap.lastEntry().getValue()).intValue()) {
                                case 1:
                                    return n.a(activity, a);
                                case 2:
                                    return ah.a(activity, a);
                                case 4:
                                case 8:
                                    return g.a(activity, a, g.b.BOTTOM);
                                case 16:
                                    return g.a(activity, a, g.b.TOP);
                                case 64:
                                    return g.a(activity, a, g.b.VIEW);
                                case 128:
                                    return ak.a(activity, a);
                                case 256:
                                    return v.a(activity, a);
                            }
                        } else if ((i & 1) > 0) {
                            return n.a(activity, a);
                        } else {
                            return false;
                        }
                        break;
                }
            } catch (Throwable e) {
                a(e);
            }
            return false;
        }
    }

    public static void hide(@NonNull Activity activity, int i) {
        if (activity == null) {
            a(new a("Unable to hide an ad: activity = null"));
        } else if (!a()) {
            b = activity;
            if ((i & 92) > 0) {
                g.c(activity);
            }
            if ((i & 256) > 0) {
                v.c(activity);
            }
            a(String.format("Hide %s", new Object[]{an.a(i)}), LogLevel.verbose);
        }
    }

    public static void setAutoCache(int i, boolean z) {
        if ((i & 1) > 0) {
            n.m = z;
        }
        if ((i & 2) > 0) {
            ah.k = z;
        }
        if ((i & 128) > 0) {
            ak.k = z;
        }
        if ((i & 92) > 0) {
            g.l = z;
        }
        if ((i & 256) > 0) {
            v.l = z;
        }
        if ((i & 512) > 0) {
            Native.j = z;
        }
        a(String.format("Set auto cache for %s: %s", new Object[]{an.a(i), Boolean.valueOf(z)}), LogLevel.verbose);
    }

    public static void setOnLoadedTriggerBoth(int i, boolean z) {
        if ((i & 1) > 0) {
            n.n = z;
        }
        if ((i & 92) > 0) {
            g.m = z;
        }
        if ((i & 256) > 0) {
            v.m = z;
        }
        a(String.format("Set onLoadedTriggerBoth for %s: %s", new Object[]{an.a(i), Boolean.valueOf(z)}), LogLevel.verbose);
    }

    public static boolean isLoaded(int i) {
        if (a()) {
            return false;
        }
        if ((i & 1) > 0) {
            try {
                s a = n.a();
                if (a == null || !a.d()) {
                    return false;
                }
                return true;
            } catch (Throwable e) {
                a(e);
            }
        } else if ((i & 2) > 0) {
            r2 = ah.a();
            if (r2 == null || !r2.d()) {
                return false;
            }
            return true;
        } else if ((i & 128) > 0) {
            r2 = ak.a();
            if (r2 == null || !r2.d()) {
                return false;
            }
            return true;
        } else if ((i & 92) > 0) {
            l a2 = g.a();
            if (a2 == null || !a2.d()) {
                return false;
            }
            return true;
        } else if ((i & 256) > 0) {
            aa a3 = v.a();
            if (a3 == null || !a3.d()) {
                return false;
            }
            return true;
        } else {
            if ((i & 512) > 0) {
                ag a4 = Native.a();
                if (a4 == null || !a4.d()) {
                    return false;
                }
                return true;
            }
            return false;
        }
    }

    public static boolean isPrecache(int i) {
        if (a()) {
            return false;
        }
        switch (i) {
            case 1:
                try {
                    if (!n.p.isEmpty() && ((s) n.p.get(n.p.size() - 1)).e()) {
                        return true;
                    }
                } catch (Throwable e) {
                    a(e);
                    break;
                }
            case 2:
            case 128:
                return false;
            case 4:
            case 8:
            case 16:
            case 64:
                if (!g.x.isEmpty() && ((l) g.x.get(g.x.size() - 1)).e()) {
                    return true;
                }
            case 256:
                if (!v.t.isEmpty() && ((aa) v.t.get(v.t.size() - 1)).e()) {
                    return true;
                }
        }
        return false;
    }

    public static void confirm(int i) {
        a(i, true);
    }

    private static void a(int i, boolean z) {
        if ((i & 2) > 0) {
            a(String.format("Set confirm for Video: %s", new Object[]{Boolean.valueOf(z)}), LogLevel.verbose);
            ah.d = z;
        }
    }

    public static void setBannerViewId(int i) {
        a(String.format("Set BannerViewId: %s", new Object[]{Integer.valueOf(i)}), LogLevel.verbose);
        g.o = i;
        g.v = null;
    }

    private static void a(@NonNull BannerView bannerView) {
        if (bannerView == null) {
            a(new a("Unable to set BannerView to null"));
            return;
        }
        g.o = -1;
        g.v = bannerView;
    }

    public static BannerView getBannerView(@NonNull Activity activity) {
        a("Get BannerView");
        if (activity == null) {
            a(new a("Unable to getBannerView: activity = null"));
            return null;
        }
        BannerView bannerView = new BannerView(activity, null);
        a(bannerView);
        return bannerView;
    }

    public static void setSmartBanners(boolean z) {
        a(String.format("Set smart Banners: %s", new Object[]{Boolean.valueOf(z)}), LogLevel.verbose);
        g.s = z;
    }

    public static void set728x90Banners(boolean z) {
        a(String.format("Set 728x90 Banners: %s", new Object[]{Boolean.valueOf(z)}), LogLevel.verbose);
        g.t = z;
    }

    public static void setBannerAnimation(boolean z) {
        a(String.format("Set Banners animation: %s", new Object[]{Boolean.valueOf(z)}), LogLevel.verbose);
        g.G = z;
    }

    public static void setMrecViewId(int i) {
        a(String.format("Set setMrecViewId: %s", new Object[]{Integer.valueOf(i)}), LogLevel.verbose);
        v.o = i;
        v.r = null;
    }

    private static void a(@NonNull MrecView mrecView) {
        if (mrecView == null) {
            a(new a("Unable to set MrecView to null"));
            return;
        }
        v.o = -1;
        v.r = mrecView;
    }

    public static MrecView getMrecView(@NonNull Activity activity) {
        a("Get MrecView", LogLevel.verbose);
        if (activity == null) {
            a(new a("Unable to get MrecView: activity = null"));
            return null;
        }
        MrecView mrecView = new MrecView(activity, null);
        a(mrecView);
        return mrecView;
    }

    public static void setAutoCacheNativeMedia(boolean z) {
        a(String.format("Set auto cache NativeAd media: %s", new Object[]{Boolean.valueOf(z)}), LogLevel.verbose);
        Native.n = z;
    }

    public static void setAutoCacheNativeIcons(boolean z) {
        a(String.format("Set auto cache NativeAd icons: %s", new Object[]{Boolean.valueOf(z)}), LogLevel.verbose);
        Native.o = z;
    }

    public static void onResume(@NonNull Activity activity, int i) {
        if (activity == null) {
            a(new a("Unable to resume Appodeal: activity = null"));
        } else if (!a()) {
            b = activity;
            if ((i & 92) > 0 && g.w == g.d.VISIBLE) {
                g.a(activity, g.A, g.r);
            }
            if ((i & 256) > 0 && v.s == v.c.VISIBLE) {
                v.a(activity, v.w);
            }
            a(String.format("onResume called for %s", new Object[]{an.a(i)}), LogLevel.verbose);
        }
    }

    public static void trackInAppPurchase(@NonNull Context context, double d, @NonNull String str) {
        if (context == null) {
            a(new a("Unable to track inapp purchase: context = null"));
        } else if (str == null) {
            a(new a("Unable to track inapp purchase: currency = null"));
        } else {
            a(String.format("Track inapp purchase, amount: %s, currency: %s", new Object[]{Double.valueOf(d), str}), LogLevel.verbose);
            try {
                SharedPreferences sharedPreferences = context.getSharedPreferences("appodeal", 0);
                JSONObject jSONObject = new JSONObject(sharedPreferences.getString("inapps", "{}"));
                double d2 = 0.0d;
                if (jSONObject.has(str)) {
                    d2 = jSONObject.getDouble(str);
                }
                jSONObject.put(str, d2 + d);
                sharedPreferences.edit().putString("inapps", jSONObject.toString()).apply();
            } catch (Throwable e) {
                a(e);
            }
        }
    }

    public static List<String> a(Context context, int i) {
        Collection hashSet = new HashSet();
        if ((i & 1) > 0) {
            for (o a : n.a(context)) {
                hashSet.add(a.a());
            }
        }
        if ((i & 2) > 0) {
            for (ap a2 : ah.a(context)) {
                hashSet.add(a2.a());
            }
        }
        if ((i & 128) > 0) {
            for (ap a22 : ak.a(context)) {
                hashSet.add(a22.a());
            }
        }
        if ((i & 92) > 0) {
            for (h a3 : g.a(context)) {
                hashSet.add(a3.a());
            }
        }
        if ((i & 256) > 0) {
            for (w a4 : v.a(context)) {
                hashSet.add(a4.a());
            }
        }
        if ((i & 512) > 0) {
            for (ac a5 : Native.a(context)) {
                hashSet.add(a5.a());
            }
        }
        List<String> arrayList = new ArrayList(hashSet);
        Collections.sort(arrayList);
        return arrayList;
    }

    public static void disableNetwork(@NonNull Context context, @NonNull String str) {
        disableNetwork(context, str, 1023);
    }

    public static void disableNetwork(@NonNull Context context, @NonNull String str, int i) {
        if (context == null) {
            a(new a("Unable to disable network: context = null"));
        } else if (str == null) {
            a(new a("Unable to disable network: name = null"));
        } else if (!a()) {
            if (context instanceof Activity) {
                b = (Activity) context;
            }
            if ((i & 1) > 0 && !n.c) {
                n.b.add(str);
                if (n.a != null) {
                    n.a.remove(a(n.a, str));
                }
            }
            if ((i & 2) > 0 && !ah.b) {
                ah.u.add(str);
                if (ah.a != null) {
                    ah.a.remove(a(ah.a, str));
                }
            }
            if ((i & 128) > 0 && !ak.b) {
                ak.v.add(str);
                if (ak.a != null) {
                    ak.a.remove(a(ak.a, str));
                }
            }
            if ((i & 92) > 0 && !g.b) {
                g.E.add(str);
                if (g.a != null) {
                    g.a.remove(a(g.a, str));
                }
            }
            if ((i & 256) > 0 && !v.b) {
                v.A.add(str);
                if (v.a != null) {
                    v.a.remove(a(v.a, str));
                }
            }
            if ((i & 512) > 0 && !Native.b) {
                Native.u.add(str);
                if (Native.a != null) {
                    Native.a.remove(a(Native.a, str));
                }
            }
            a(String.format("Disable %s: %s", new Object[]{str, an.a(i)}));
        }
    }

    static c a(Set<? extends c> set, String str) {
        for (c cVar : set) {
            if (cVar.a().equals(str)) {
                return cVar;
            }
        }
        return null;
    }

    public static void disableLocationPermissionCheck() {
        PermissionsHelper.b = false;
        c.a();
        a("disableLocationPermissionCheck", LogLevel.verbose);
    }

    public static void disableWriteExternalStoragePermissionCheck() {
        PermissionsHelper.a = false;
        c.b();
        a("disableWriteExternalStoragePermissionCheck", LogLevel.verbose);
    }

    public static UserSettings getUserSettings(@NonNull Context context) {
        if (context == null) {
            a(new a("Unable to get user settings: context = null"));
            return null;
        }
        a("Get user settings", LogLevel.verbose);
        return new UserSettings(context);
    }

    public static String getVersion() {
        return "1.15.7";
    }

    public static void setTesting(boolean z) {
        a(String.format("Set testing: %s", new Object[]{Boolean.valueOf(z)}), LogLevel.verbose);
        AppodealSettings.a = z;
    }

    public static void setLogLevel(LogLevel logLevel) {
        Log.d("Appodeal", String.format("Set log level: %s", new Object[]{logLevel}));
        AppodealSettings.c = logLevel;
    }

    public static LogLevel getLogLevel() {
        return LogLevel.fromInteger(Integer.valueOf(g.a()));
    }

    public static void setCustomRule(@NonNull String str, boolean z) {
        if (str == null) {
            a(new a("Unable to set custom rule: name = null"));
            return;
        }
        a(String.format("Set custom rule name: %s, value: %s", new Object[]{str, Boolean.valueOf(z)}), LogLevel.verbose);
        com.appodeal.ads.f.g.a(str, Boolean.valueOf(z));
    }

    public static void setCustomRule(@NonNull String str, int i) {
        if (str == null) {
            a(new a("Unable to set custom rule: name = null"));
            return;
        }
        a(String.format("Set custom rule name: %s, value: %s", new Object[]{str, Integer.valueOf(i)}), LogLevel.verbose);
        com.appodeal.ads.f.g.a(str, Integer.valueOf(i));
    }

    public static void setCustomRule(@NonNull String str, double d) {
        if (str == null) {
            a(new a("Unable to set custom rule: name = null"));
            return;
        }
        a(String.format("Set custom rule name: %s, value: %s", new Object[]{str, Double.valueOf(d)}), LogLevel.verbose);
        com.appodeal.ads.f.g.a(str, Double.valueOf(d));
    }

    public static void setCustomRule(@NonNull String str, @NonNull String str2) {
        if (str == null) {
            a(new a("Unable to set custom rule: name = null"));
        } else if (str2 == null) {
            a(new a("Unable to set custom rule: value = null"));
        } else {
            a(String.format("Set custom rule name: %s, value: %s", new Object[]{str, str2}), LogLevel.verbose);
            com.appodeal.ads.f.g.a(str, (Object) str2);
        }
    }

    public static void a(String str) {
        a(str, LogLevel.debug);
    }

    public static void a(String str, LogLevel logLevel) {
        com.appodeal.ads.utils.Log.a(str, logLevel);
    }

    public static void a(Throwable th) {
        m.a().a(th);
        com.appodeal.ads.utils.Log.a(th);
    }

    public static void requestAndroidMPermissions(@NonNull Activity activity, AppodealPermissionCallbacks appodealPermissionCallbacks) {
        if (activity == null) {
            a(new a("Unable to request Android M permissions: activity = null"));
            return;
        }
        a("Request Android M permissions", LogLevel.verbose);
        PermissionsHelper.a().a(activity, appodealPermissionCallbacks);
    }

    public static NativeAdBox getNativeAdBox() {
        a("Get NativeAdBox", LogLevel.verbose);
        if (Native.C == null) {
            Native.C = new NativeAdBox();
        }
        return Native.C;
    }
}

package com.appodeal.ads.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build.VERSION;
import android.os.Bundle;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.ah;
import com.appodeal.ads.ak;
import com.appodeal.ads.an;
import com.appodeal.ads.ap;
import com.appodeal.ads.n;
import com.appodeal.ads.o;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class c {
    private static final ArrayList<String> a = new ArrayList<String>() {
        {
            add("android.permission.ACCESS_NETWORK_STATE");
            add("android.permission.INTERNET");
            add("android.permission.ACCESS_COARSE_LOCATION");
            add("android.permission.WRITE_EXTERNAL_STORAGE");
        }
    };
    private static final ArrayList<String> b = new ArrayList<String>() {
        {
            add("com.appodeal.ads.InterstitialActivity");
            add("com.appodeal.ads.LoaderActivity");
            add("org.nexage.sourcekit.mraid.MRAIDBrowser");
        }
    };
    private static final ArrayList<String> c = new ArrayList<String>() {
        {
            add("com.appodeal.ads.VideoActivity");
            add("com.appodeal.ads.LoaderActivity");
        }
    };
    private static final ArrayList<String> d = new ArrayList<String>() {
        {
            add("com.google.android.gms.version");
            add("com.appodeal.framework");
        }
    };
    private static PackageInfo e;

    public static void a(Activity activity) {
        try {
            PackageInfo packageInfo = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 4096);
            List<String> arrayList = new ArrayList(a);
            if (packageInfo.requestedPermissions != null) {
                arrayList.removeAll(Arrays.asList(packageInfo.requestedPermissions));
            }
            if (!arrayList.isEmpty()) {
                String str = "Missing permissions:";
                for (String str2 : arrayList) {
                    str = String.format("%s\n%s", new Object[]{str, str2});
                }
                Appodeal.a(str);
                an.b(activity, str);
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    public static void a() {
        a.remove("android.permission.ACCESS_COARSE_LOCATION");
    }

    public static void b() {
        a.remove("android.permission.WRITE_EXTERNAL_STORAGE");
    }

    public static void b(Activity activity) {
        try {
            PackageInfo a = a(activity.getPackageManager(), activity.getPackageName());
            Set<String> hashSet = new HashSet(b);
            for (o c : n.a((Context) activity)) {
                Collections.addAll(hashSet, c.c());
            }
            if (a.activities != null) {
                for (ActivityInfo activityInfo : a.activities) {
                    hashSet.remove(activityInfo.name);
                }
            }
            if (!hashSet.isEmpty()) {
                String str = "Missing activities:";
                for (String str2 : hashSet) {
                    str = String.format("%s\n%s", new Object[]{str, str2});
                }
                Appodeal.a(str);
                an.b(activity, str);
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    public static void c(Activity activity) {
        try {
            PackageInfo a = a(activity.getPackageManager(), activity.getPackageName());
            Set<String> hashSet = new HashSet(c);
            for (ap c : ah.a((Context) activity)) {
                Collections.addAll(hashSet, c.c());
            }
            if (a.activities != null) {
                for (ActivityInfo activityInfo : a.activities) {
                    hashSet.remove(activityInfo.name);
                }
            }
            if (!hashSet.isEmpty()) {
                String str = "Missing activities:";
                for (String str2 : hashSet) {
                    str = String.format("%s\n%s", new Object[]{str, str2});
                }
                Appodeal.a(str);
                an.b(activity, str);
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    public static void d(Activity activity) {
        try {
            PackageInfo a = a(activity.getPackageManager(), activity.getPackageName());
            Set<String> hashSet = new HashSet(c);
            for (ap c : ak.a((Context) activity)) {
                Collections.addAll(hashSet, c.c());
            }
            if (a.activities != null) {
                for (ActivityInfo activityInfo : a.activities) {
                    hashSet.remove(activityInfo.name);
                }
            }
            if (!hashSet.isEmpty()) {
                String str = "Missing activities:";
                for (String str2 : hashSet) {
                    str = String.format("%s\n%s", new Object[]{str, str2});
                }
                Appodeal.a(str);
                an.b(activity, str);
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    private static PackageInfo a(PackageManager packageManager, String str) {
        if (e == null) {
            e = packageManager.getPackageInfo(str, 1);
        }
        return e;
    }

    public static void e(Activity activity) {
        Object obj = null;
        try {
            Bundle bundle = activity.getPackageManager().getApplicationInfo(activity.getPackageName(), 128).metaData;
            String str = "Missing meta-data:";
            Iterator it = d.iterator();
            while (it.hasNext()) {
                Object obj2;
                String str2;
                if (bundle.containsKey((String) it.next())) {
                    obj2 = obj;
                    str2 = str;
                } else {
                    str2 = String.format("%s\n%s", new Object[]{str, (String) it.next()});
                    obj2 = 1;
                }
                str = str2;
                obj = obj2;
            }
            if (obj != null) {
                Appodeal.a(str);
                an.b(activity, str);
            }
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    public static void f(Activity activity) {
        try {
            Class.forName("android.support.v4.app.Fragment", false, activity.getClass().getClassLoader());
            Class.forName("android.support.v4.app.FragmentActivity", false, activity.getClass().getClassLoader());
            Class.forName("android.support.v4.app.FragmentManager", false, activity.getClass().getClassLoader());
            Class.forName("android.support.v4.app.FragmentTransaction", false, activity.getClass().getClassLoader());
            Class.forName("android.support.v4.content.LocalBroadcastManager", false, activity.getClass().getClassLoader());
            Class.forName("android.support.v4.util.LruCache", false, activity.getClass().getClassLoader());
            Class.forName("android.support.v4.view.PagerAdapter", false, activity.getClass().getClassLoader());
            Class.forName("android.support.v4.view.ViewPager", false, activity.getClass().getClassLoader());
            Class.forName("android.support.v4.content.ContextCompat", false, activity.getClass().getClassLoader());
        } catch (ClassNotFoundException e) {
            String str = "android-support-v4.jar is missing";
            Appodeal.a("android-support-v4.jar is missing");
            an.b(activity, "android-support-v4.jar is missing");
        }
    }

    public static void g(Activity activity) {
        try {
            Class.forName("android.support.v7.widget.RecyclerView", false, activity.getClass().getClassLoader());
            Class.forName("android.support.v7.widget.LinearLayoutManager", false, activity.getClass().getClassLoader());
        } catch (ClassNotFoundException e) {
            String str = "android-support-v7-recyclerview.jar is missing";
            Appodeal.a("android-support-v7-recyclerview.jar is missing");
            an.b(activity, "android-support-v7-recyclerview.jar is missing");
        }
    }

    @TargetApi(23)
    public static boolean a(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096);
            if (packageInfo.requestedPermissions == null || !Arrays.asList(packageInfo.requestedPermissions).contains("android.permission.WRITE_EXTERNAL_STORAGE")) {
                return false;
            }
            if (VERSION.SDK_INT < 23 || context.checkSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0) {
                return true;
            }
            return false;
        } catch (Throwable e) {
            Appodeal.a(e);
            return false;
        }
    }
}

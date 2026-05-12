package com.appodeal.ads.utils;

import android.content.Context;
import android.os.Build.VERSION;
import android.os.Environment;
import android.util.Log;
import android.util.SparseIntArray;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.AppodealSettings;
import com.appodeal.ads.utils.Log.LogLevel;
import com.cmcm.adsdk.Const.res;
import com.cube.memorygames.Games;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

public class g {
    public static final String[] a = new String[]{"api.appodeal.com", "dev.appodeal.com", "appodeal.local", "staging.appodeal.com", "staging2.appodeal.com"};
    public static final Integer[] b = new Integer[]{Integer.valueOf(443), Integer.valueOf(80), Integer.valueOf(res.facebook), Integer.valueOf(8080), Integer.valueOf(8081)};
    private static SparseIntArray c;
    private static boolean d = false;
    private static boolean e = false;

    enum a {
        DEBUG(0),
        LOG(1),
        SERVER(2),
        PORT(3),
        LOG_LEVEL(4);
        
        private final int f;

        private a(int i) {
            this.f = i;
        }

        public int a() {
            return this.f;
        }
    }

    private static SparseIntArray f() {
        if (c == null) {
            c = new SparseIntArray();
        }
        return c;
    }

    public static int a() {
        int value;
        if (b(a.LOG.a()) != 1) {
            value = LogLevel.none.getValue();
        } else if (b(a.LOG_LEVEL.a()) == 2) {
            value = LogLevel.verbose.getValue();
        } else {
            value = LogLevel.debug.getValue();
        }
        return Math.max(value, AppodealSettings.c.getValue());
    }

    private static int g() {
        int b = b(a.SERVER.a());
        if (b >= a.length) {
            return 0;
        }
        return b;
    }

    private static int h() {
        int b = b(a.PORT.a());
        if (b >= b.length) {
            return 0;
        }
        return b;
    }

    public static String b() {
        return a[g()];
    }

    public static String a(int i) {
        return i == 443 ? "https://" : "http://";
    }

    public static int c() {
        return b[h()].intValue();
    }

    public static void a(Context context) {
        for (a a : a.values()) {
            a(context, a.a());
        }
    }

    private static int b(int i) {
        return a(null, i);
    }

    private static int a(Context context, int i) {
        int i2;
        if (f().indexOfKey(i) > 0) {
            return f().get(i);
        }
        try {
            if (e()) {
                File file = new File(Environment.getExternalStorageDirectory(), "appodeal.txt");
                if (file.exists()) {
                    String readLine;
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    try {
                        readLine = bufferedReader.readLine();
                        String readLine2 = bufferedReader.readLine();
                        if (!(context == null || d)) {
                            d = true;
                            if (!(readLine2 == null || readLine2.isEmpty())) {
                                String[] split = readLine2.split(",");
                                List<String> a = Appodeal.a(context, 1023);
                                a.removeAll(Arrays.asList(split));
                                for (String readLine22 : a) {
                                    Appodeal.disableNetwork(context, readLine22);
                                }
                            }
                        }
                    } catch (Throwable e) {
                        Appodeal.a(e);
                    } catch (Throwable th) {
                        try {
                            bufferedReader.close();
                        } catch (Throwable e2) {
                            Appodeal.a(e2);
                        }
                    }
                    if (readLine != null) {
                        for (a aVar : a.values()) {
                            if (aVar.a() <= readLine.length() - 1) {
                                f().put(aVar.a(), Character.getNumericValue(readLine.charAt(aVar.a())));
                            }
                        }
                        if (f().indexOfKey(i) > 0) {
                            i2 = f().get(i);
                            try {
                                bufferedReader.close();
                                return i2;
                            } catch (Throwable e22) {
                                Appodeal.a(e22);
                                return i2;
                            }
                        }
                    }
                    try {
                        bufferedReader.close();
                    } catch (Throwable e3) {
                        Appodeal.a(e3);
                    }
                }
            }
        } catch (Throwable e32) {
            if (VERSION.SDK_INT < 23 || !(e32 instanceof FileNotFoundException)) {
                Log.d("Appodeal", "Invalid debug file");
                Log.d("Appodeal", "Exception", e32);
            } else if (!e) {
                e = true;
                Log.d("Appodeal", "WRITE_EXTERNAL_STORAGE permission is missing");
            }
        }
        f().put(i, 0);
        return 0;
    }

    public static boolean d() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    public static boolean e() {
        String externalStorageState = Environment.getExternalStorageState();
        return "mounted".equals(externalStorageState) || "mounted_ro".equals(externalStorageState);
    }

    public static void a(ArrayList<u> arrayList, ArrayList<JSONObject> arrayList2) {
        a(arrayList, arrayList2, false, 0);
    }

    public static int a(ArrayList<u> arrayList, ArrayList<JSONObject> arrayList2, boolean z, int i) {
        Iterator it = arrayList2.iterator();
        int i2 = i;
        while (it.hasNext()) {
            JSONObject jSONObject = (JSONObject) it.next();
            try {
                String[] split;
                String str;
                Object uVar;
                if (jSONObject.has("package_name")) {
                    split = jSONObject.getString("package_name").split(":");
                } else {
                    split = jSONObject.getString("status").split(":");
                }
                if (split.length > 1) {
                    str = split[1];
                } else {
                    str = Games.SMART_PROMO_GAME_ID;
                }
                if (jSONObject.optBoolean("offer")) {
                    uVar = new u(i2, split[0], str, jSONObject.getString("ecpm"), z, true);
                } else {
                    uVar = new u(i2, split[0], str, jSONObject.getString("ecpm"), z, false);
                }
                arrayList.add(uVar);
                i2++;
            } catch (Throwable e) {
                Appodeal.a(e);
            }
        }
        return i2;
    }
}

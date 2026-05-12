package com.appodeal.ads.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.an;
import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class i {
    public static String a = "optimized";
    public static String b = "working";
    private static final HashMap<String, b> c = new HashMap();
    private static final HashMap<String, List<Runnable>> d = new HashMap();

    private static class a extends AsyncTask<Void, Void, Boolean> {
        private final Context a;
        private final String b;

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((Boolean) obj);
        }

        public a(Context context, String str) {
            this.a = context;
            this.b = str;
        }

        protected Boolean a(Void... voidArr) {
            try {
                int priority = Thread.currentThread().getPriority();
                Thread.currentThread().setPriority(1);
                i.c(this.a, this.b);
                Thread.currentThread().setPriority(priority);
                return Boolean.valueOf(true);
            } catch (Throwable e) {
                Appodeal.a(e);
                i.b(this.b);
                return Boolean.valueOf(false);
            } catch (Throwable e2) {
                Appodeal.a(e2);
                return Boolean.valueOf(false);
            }
        }

        protected void a(Boolean bool) {
            b bVar = (b) i.c.get(this.b);
            if (bool.booleanValue()) {
                bVar.a = false;
                bVar.b = true;
            } else {
                bVar.a = false;
                bVar.b = false;
            }
            i.c.put(this.b, bVar);
            List<Runnable> synchronizedList = Collections.synchronizedList((List) i.d.get(this.b));
            synchronized (synchronizedList) {
                for (Runnable run : synchronizedList) {
                    run.run();
                }
                synchronizedList.clear();
            }
        }
    }

    public static class b {
        boolean a = false;
        boolean b = false;
    }

    public static void a(final Context context, final String str, String str2, Runnable runnable) {
        if (an.a(str2)) {
            runnable.run();
            return;
        }
        List list;
        b bVar;
        if (d.containsKey(str)) {
            list = (List) d.get(str);
        } else {
            ArrayList arrayList = new ArrayList();
            d.put(str, arrayList);
            list = arrayList;
        }
        list.add(runnable);
        b bVar2;
        if (c.containsKey(str)) {
            bVar2 = (b) c.get(str);
            if (!bVar2.a) {
                if (bVar2.b) {
                    if (an.a(str2)) {
                        list.remove(runnable);
                        runnable.run();
                        return;
                    }
                }
                bVar = bVar2;
            } else {
                return;
            }
        }
        bVar2 = new b();
        c.put(str, bVar2);
        bVar = bVar2;
        bVar.a = true;
        c.put(str, bVar);
        if (VERSION.SDK_INT >= 14) {
            if (!an.a(str2)) {
                if (!b(context, str)) {
                    try {
                        if (VERSION.SDK_INT >= 14) {
                            if (!an.a(str2)) {
                                c(context, str);
                            }
                        }
                    } catch (Throwable e) {
                        Appodeal.a(e);
                        b(str);
                    } catch (Throwable e2) {
                        Appodeal.a(e2);
                    }
                    bVar.a = false;
                    if (an.a(str2)) {
                        bVar.b = true;
                        runnable.run();
                        ((List) d.get(str)).remove(runnable);
                    }
                    c.put(str, bVar);
                } else if (context instanceof Activity) {
                    ((Activity) context).runOnUiThread(new Runnable() {
                        @TargetApi(11)
                        public void run() {
                            new a(context, str).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
                        }
                    });
                } else {
                    new a(context, str).executeOnExecutor(AsyncTask.SERIAL_EXECUTOR, new Void[0]);
                }
            }
        }
    }

    private static boolean b(Context context, String str) {
        try {
            if (new File(context.getDir(a, 0), str).exists()) {
                return false;
            }
            return true;
        } catch (Throwable e) {
            Appodeal.a(e);
            return true;
        }
    }

    @TargetApi(14)
    private static void c(Context context, String str) {
        Throwable th;
        File file = new File(context.getDir(b, 0), str);
        File file2 = new File(context.getDir(a, 0), str);
        InputStream open = context.getAssets().open(String.format("dex/%s", new Object[]{str}));
        long available = (long) open.available();
        if (file.length() != available) {
            an.a(file2);
        }
        if (!(file.exists() && file.length() == available)) {
            file.getParentFile().mkdirs();
            file.createNewFile();
            FileOutputStream fileOutputStream;
            try {
                fileOutputStream = new FileOutputStream(file);
                try {
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int read = open.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, read);
                    }
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (Throwable e) {
                            Appodeal.a(e);
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = null;
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Throwable e2) {
                        Appodeal.a(e2);
                    }
                }
                throw th;
            }
        }
        open.close();
        file2.mkdirs();
        ClassLoader classLoader = i.class.getClassLoader();
        BaseDexClassLoader dexClassLoader = new DexClassLoader(file.getAbsolutePath(), file2.getAbsolutePath(), null, classLoader);
        if (classLoader instanceof BaseDexClassLoader) {
            Object a = a((BaseDexClassLoader) classLoader);
            a((BaseDexClassLoader) classLoader, a(a(dexClassLoader), a));
            return;
        }
        throw new UnsupportedOperationException("Class loader not supported");
    }

    private static void a(BaseDexClassLoader baseDexClassLoader, Object obj) {
        Field declaredField = BaseDexClassLoader.class.getDeclaredField("pathList");
        declaredField.setAccessible(true);
        Object obj2 = declaredField.get(baseDexClassLoader);
        Field declaredField2 = obj2.getClass().getDeclaredField("dexElements");
        declaredField2.setAccessible(true);
        declaredField2.set(obj2, obj);
    }

    private static Object a(BaseDexClassLoader baseDexClassLoader) {
        Field declaredField = BaseDexClassLoader.class.getDeclaredField("pathList");
        declaredField.setAccessible(true);
        Object obj = declaredField.get(baseDexClassLoader);
        Field declaredField2 = obj.getClass().getDeclaredField("dexElements");
        declaredField2.setAccessible(true);
        return declaredField2.get(obj);
    }

    private static Object a(Object obj, Object obj2) {
        int i = 0;
        Class componentType = obj.getClass().getComponentType();
        if (componentType != obj2.getClass().getComponentType()) {
            throw new IllegalArgumentException();
        }
        int length = Array.getLength(obj);
        int length2 = Array.getLength(obj2);
        Object newInstance = Array.newInstance(componentType, length + length2);
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            Array.set(newInstance, i3, Array.get(obj, i2));
            i2++;
            i3++;
        }
        while (i < length2) {
            Array.set(newInstance, i3, Array.get(obj2, i));
            i++;
            i3++;
        }
        return newInstance;
    }

    private static void b(String str) {
        if (Appodeal.b != null) {
            String format = String.format("ERROR: %s not found", new Object[]{an.a(str.split("\\.")[0])});
            Appodeal.a(format);
            an.b(Appodeal.b, format);
        }
    }
}

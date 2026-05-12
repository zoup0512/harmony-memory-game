package com.yandex.metrica.impl.ob;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.net.LocalServerSocket;
import android.net.Uri;
import android.text.TextUtils;
import com.yandex.metrica.YandexMetrica;
import com.yandex.metrica.impl.bg;
import com.yandex.metrica.impl.r;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class br {
    private final Object a;
    private final a b;
    private final bt c;
    private bq d;

    static class a {
        br a;
        private LocalServerSocket b;

        private a(br brVar) {
            this.a = brVar;
        }

        br a() {
            return this.a;
        }

        boolean b() {
            try {
                this.b = new LocalServerSocket("com.yandex.metrica.synchronization.deviceid");
                return true;
            } catch (IOException e) {
                return false;
            }
        }

        public String a(Context context, String str) {
            String str2 = null;
            TextUtils.isEmpty(str);
            a().f().a(context);
            ck ckVar = new ck(12);
            do {
                if (b()) {
                    str2 = a(context, str, a().f().a(context));
                    if (this.b != null) {
                        try {
                            this.b.close();
                            this.b = null;
                        } catch (IOException e) {
                        }
                    }
                } else {
                    ckVar.a();
                    ckVar.c();
                }
            } while (ckVar.b());
            return str2;
        }

        String a(Context context, String str, String str2) {
            if (TextUtils.isEmpty(str)) {
                if (TextUtils.isEmpty(str2)) {
                    return null;
                }
                br.a(a(), context, str2);
                return str2;
            } else if (str.equals(str2)) {
                br.a(a(), context, str);
                YandexMetrica.getReporter(context, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("update_snapshot", new c(context, str2, str));
                return str;
            } else if (TextUtils.isEmpty(str2)) {
                br.a(a(), context, str);
                YandexMetrica.getReporter(context, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("wtf_situation. App has id and elector hasn't", new c(context, str2, str));
                return str;
            } else {
                br.a(a(), context, str2);
                YandexMetrica.getReporter(context, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("overlapping_device_id", new c(context, str2, str));
                return str2;
            }
        }
    }

    private static class b {
        private static final br a = new br();
    }

    private static class c extends HashMap<String, Object> {
        public c(Context context, String str) {
            String packageName = context.getPackageName();
            put("passed_id", str);
            put("package_name", packageName);
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
                put("version_code", Integer.valueOf(packageInfo.versionCode));
                put("version_name", packageInfo.versionName);
            } catch (NameNotFoundException e) {
            }
        }

        public c(Context context, String str, String str2) {
            this(context, str);
            put("stored_device_id", str2);
        }
    }

    public static br a() {
        return b.a;
    }

    private br() {
        this.a = new Object();
        this.b = new a();
        this.c = new bt(this);
    }

    a b() {
        return this.b;
    }

    bq c() {
        return this.d;
    }

    public String d() {
        bq c = c();
        if (c == null) {
            return null;
        }
        return c.c();
    }

    bq a(Context context, String str) {
        return a(context, str, context.getFileStreamPath("credentials.dat"));
    }

    bq b(Context context, String str) {
        return a(context, str, new File(context.getNoBackupFilesDir(), "credentials.dat"));
    }

    private bq a(Context context, String str, File file) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(str, 8192);
            if (applicationInfo != null) {
                return g(context, file.getAbsolutePath().replace(context.getApplicationInfo().dataDir, applicationInfo.dataDir));
            }
        } catch (NameNotFoundException e) {
        }
        return null;
    }

    private bq g(Context context, String str) {
        try {
            File file = new File(str);
            if (!file.exists()) {
                return null;
            }
            String a;
            synchronized (this.a) {
                a = r.a(context, file);
            }
            if (a == null) {
                return null;
            }
            return new bq(new JSONObject(a), file.lastModified());
        } catch (JSONException e) {
            return null;
        } catch (Exception e2) {
            return null;
        }
    }

    public String c(Context context, String str) {
        return h(context, str);
    }

    boolean e() {
        return bg.a(21);
    }

    void d(Context context, String str) {
        try {
            synchronized (this.a) {
                this.d = new bq(str, new bs(context), System.currentTimeMillis());
                String a = this.d.a();
                if (e()) {
                    e(context, a);
                }
                String str2 = "credentials.dat";
                synchronized (this.a) {
                    r.a(context, str2, a);
                }
            }
        } catch (JSONException e) {
        }
    }

    void e(Context context, String str) {
        synchronized (this.a) {
            r.b(context, "credentials.dat", str);
        }
    }

    public String a(Context context) {
        return h(context, null);
    }

    private String h(Context context, String str) {
        String a;
        synchronized (this.a) {
            if (c() == null) {
                bq a2 = a(context, context.getPackageName());
                if (a2 == null) {
                    a = b().a(context, str);
                } else if (e()) {
                    bq b = b(context, context.getPackageName());
                    if (a2.a(b) && b.e()) {
                        this.d = a2;
                        a = b.c();
                    } else {
                        a = b().a(context, a2.c());
                    }
                } else if (a2.e()) {
                    this.d = a2;
                    a = a2.c();
                } else {
                    a = b().a(context, a2.c());
                }
            } else {
                a = c().c();
            }
        }
        return a;
    }

    String f(Context context, String str) {
        Cursor cursor;
        Throwable th;
        ProviderInfo resolveContentProvider = context.getPackageManager().resolveContentProvider(str + ".MetricaContentProvider", 0);
        if (resolveContentProvider == null || !resolveContentProvider.enabled) {
            return null;
        }
        Cursor query;
        try {
            String string;
            query = context.getContentResolver().query(Uri.parse(String.format(Locale.US, "content://%s.MetricaContentProvider/DEVICE_ID", new Object[]{str})), null, null, null, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        string = query.getString(query.getColumnIndex("DEVICE_ID"));
                        bg.a(query);
                        return string;
                    }
                } catch (Exception e) {
                    cursor = query;
                    bg.a(cursor);
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    bg.a(query);
                    throw th;
                }
            }
            string = null;
            bg.a(query);
            return string;
        } catch (Exception e2) {
            cursor = null;
            bg.a(cursor);
            return null;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            bg.a(query);
            throw th;
        }
    }

    bt f() {
        return this.c;
    }

    static /* synthetic */ void a(br brVar, Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            YandexMetrica.getReporter(context, "20799a27-fa80-4b36-b2db-0f8141f24180").reportEvent("saving_empty_device_id", new c(context, str));
        } else {
            brVar.d(context, str);
        }
    }
}

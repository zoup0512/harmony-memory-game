package com.yandex.metrica.impl.ob;

import android.text.TextUtils;
import android.util.Log;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class dg implements do {
    private static final String a = dg.class.getSimpleName();
    private final dl b = new dl();
    private File c;

    public dg(String str, String str2) throws IOException {
        b(str, str2);
    }

    public synchronized Set<String> a(String str) {
        return this.b.a(str);
    }

    public synchronized void a(String str, String[] strArr) {
        if (this.b.a(str) == null) {
            long lastModified = this.c.lastModified();
            a(str, new HashSet(Arrays.asList(strArr)));
            this.c.setLastModified(lastModified);
        }
    }

    public synchronized boolean a(String str, String str2) {
        boolean a;
        a = this.b.a(str, str2);
        d();
        return a;
    }

    public synchronized void a(String str, Set<String> set) {
        this.b.a(str, (Set) set);
        d();
    }

    public synchronized long a() {
        return this.c.lastModified();
    }

    public void b() {
        this.c.setLastModified(System.currentTimeMillis());
    }

    private synchronized void b(String str, String str2) throws IOException {
        Map hashMap;
        this.c = new File(str, "sslpinningv1-" + str2);
        if (this.c.createNewFile()) {
            hashMap = new HashMap();
            a(hashMap);
            this.c.setLastModified(0);
        } else {
            hashMap = c();
        }
        this.b.a(hashMap);
    }

    private synchronized Map<String, Set<String>> c() throws IOException {
        Map<String, Set<String>> hashMap;
        Throwable th;
        Set set = null;
        synchronized (this) {
            hashMap = new HashMap();
            BufferedReader bufferedReader;
            try {
                bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(this.c)));
                try {
                    for (CharSequence readLine = bufferedReader.readLine(); readLine != null; readLine = bufferedReader.readLine()) {
                        if (readLine.contains("type-")) {
                            String substring = readLine.substring(5);
                            set = new HashSet();
                            hashMap.put(substring, set);
                        } else if (!TextUtils.isEmpty(readLine)) {
                            set.add(readLine);
                        }
                    }
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        Log.e(a, e.getMessage());
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedReader != null) {
                        try {
                            bufferedReader.close();
                        } catch (IOException e2) {
                            Log.e(a, e2.getMessage());
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                bufferedReader = null;
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                throw th;
            }
        }
        return hashMap;
    }

    private synchronized void a(Map<String, Set<String>> map) {
        BufferedWriter bufferedWriter;
        IOException e;
        Throwable th;
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.c)));
            try {
                for (String str : map.keySet()) {
                    bufferedWriter.write("type-" + str);
                    bufferedWriter.newLine();
                    for (String str2 : (Set) map.get(str2)) {
                        bufferedWriter.write(str2);
                        bufferedWriter.newLine();
                    }
                }
                try {
                    bufferedWriter.close();
                } catch (IOException e2) {
                    Log.e(a, e2.getMessage());
                }
            } catch (IOException e3) {
                e2 = e3;
                try {
                    Log.e(a, e2.getMessage());
                    if (bufferedWriter != null) {
                        try {
                            bufferedWriter.close();
                        } catch (IOException e22) {
                            Log.e(a, e22.getMessage());
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (bufferedWriter != null) {
                        try {
                            bufferedWriter.close();
                        } catch (IOException e4) {
                            Log.e(a, e4.getMessage());
                        }
                    }
                    throw th;
                }
            }
        } catch (IOException e5) {
            e22 = e5;
            bufferedWriter = null;
            Log.e(a, e22.getMessage());
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
        } catch (Throwable th3) {
            th = th3;
            bufferedWriter = null;
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            throw th;
        }
    }

    private synchronized void d() {
        a(this.b.c());
    }
}

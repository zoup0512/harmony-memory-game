package com.appodeal.ads.utils.a;

import android.content.Context;
import android.os.Environment;
import android.util.Base64;
import com.appodeal.ads.Appodeal;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import org.json.JSONObject;

public class d implements c {
    private final String a;

    public d(String str) {
        this.a = str;
    }

    public JSONObject a(Context context) {
        JSONObject b = b();
        if (b == null || !b.has(this.a)) {
            return null;
        }
        try {
            return new JSONObject(b.getString(this.a));
        } catch (Throwable e) {
            Appodeal.a(e);
            return null;
        }
    }

    public void a(Context context, JSONObject jSONObject) {
        try {
            JSONObject b = b();
            if (b == null) {
                b = new JSONObject();
            }
            b.put(this.a, jSONObject.toString());
            a(b.toString());
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    private static JSONObject b() {
        BufferedReader bufferedReader;
        try {
            File file = new File(Environment.getExternalStorageDirectory(), ".appodeal");
            if (file.exists()) {
                bufferedReader = new BufferedReader(new FileReader(file));
                StringBuilder stringBuilder = new StringBuilder();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        stringBuilder.append(readLine);
                    } else {
                        JSONObject jSONObject = new JSONObject(new String(Base64.decode(stringBuilder.toString(), 0), "UTF-8"));
                        try {
                            bufferedReader.close();
                            return jSONObject;
                        } catch (Throwable e) {
                            Appodeal.a(e);
                            return jSONObject;
                        }
                    }
                }
            }
        } catch (Throwable e2) {
            Appodeal.a(e2);
        } catch (Throwable th) {
            try {
                bufferedReader.close();
            } catch (Throwable e3) {
                Appodeal.a(e3);
            }
        }
        return null;
    }

    private static void a(String str) {
        BufferedWriter bufferedWriter;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(new File(Environment.getExternalStorageDirectory(), ".appodeal")));
            bufferedWriter.write(Base64.encodeToString(str.getBytes("UTF-8"), 0));
            try {
                bufferedWriter.close();
            } catch (Throwable e) {
                Appodeal.a(e);
            }
        } catch (Throwable e2) {
            Appodeal.a(e2);
        } catch (Throwable th) {
            try {
                bufferedWriter.close();
            } catch (Throwable e3) {
                Appodeal.a(e3);
            }
        }
    }

    public static JSONObject a() {
        return b();
    }

    public static void a(JSONObject jSONObject) {
        a(jSONObject.toString());
    }
}

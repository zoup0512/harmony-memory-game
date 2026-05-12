package com.appodeal.ads.utils.a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.appodeal.ads.Appodeal;
import java.util.Iterator;
import java.util.Map.Entry;
import org.json.JSONObject;

public class a implements c {
    private final String a;

    public a(String str) {
        this.a = str;
    }

    public JSONObject a(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("freq", 0);
        if (sharedPreferences.contains(this.a)) {
            try {
                return new JSONObject(sharedPreferences.getString(this.a, null));
            } catch (Throwable e) {
                Appodeal.a(e);
            }
        }
        return null;
    }

    public void a(Context context, JSONObject jSONObject) {
        try {
            context.getSharedPreferences("freq", 0).edit().putString(this.a, jSONObject.toString()).apply();
        } catch (Throwable e) {
            Appodeal.a(e);
        }
    }

    public static JSONObject b(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("freq", 0);
        JSONObject jSONObject = new JSONObject();
        for (Entry entry : sharedPreferences.getAll().entrySet()) {
            try {
                jSONObject.put((String) entry.getKey(), new JSONObject((String) entry.getValue()));
            } catch (Throwable e) {
                Appodeal.a(e);
            }
        }
        return jSONObject;
    }

    public static void b(Context context, JSONObject jSONObject) {
        Editor edit = context.getSharedPreferences("freq", 0).edit();
        edit.clear();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            try {
                String str = (String) keys.next();
                edit.putString(str, jSONObject.getString(str));
            } catch (Throwable e) {
                Appodeal.a(e);
            }
        }
        edit.apply();
    }
}

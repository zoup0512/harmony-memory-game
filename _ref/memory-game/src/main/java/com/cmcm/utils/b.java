package com.cmcm.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.webkit.WebSettings;
import android.webkit.WebView;
import java.lang.reflect.Constructor;

/* compiled from: AdWebViewUtils */
public class b {
    private static String a = null;

    public static String a(Context context) {
        if (a == null) {
            if (VERSION.SDK_INT >= 17) {
                try {
                    a = c(context);
                } catch (Exception e) {
                    a = b(context);
                }
            } else {
                a = b(context);
            }
        }
        return a;
    }

    private static String b(Context context) {
        String str = "";
        try {
            return a(context, "android.webkit.WebSettings", "android.webkit.WebView");
        } catch (Exception e) {
            try {
                return a(context, "android.webkit.WebSettingsClassic", "android.webkit.WebViewClassic");
            } catch (Exception e2) {
                WebView webView = new WebView(context.getApplicationContext());
                str = webView.getSettings().getUserAgentString();
                webView.destroy();
                return str;
            }
        }
    }

    private static String a(Context context, String str, String str2) throws Exception {
        Class cls = Class.forName(str);
        Constructor declaredConstructor = cls.getDeclaredConstructor(new Class[]{Context.class, Class.forName(str2)});
        declaredConstructor.setAccessible(true);
        try {
            String str3 = (String) cls.getMethod("getUserAgentString", new Class[0]).invoke(declaredConstructor.newInstance(new Object[]{context, null}), new Object[0]);
            return str3;
        } finally {
            declaredConstructor.setAccessible(false);
        }
    }

    @TargetApi(17)
    private static String c(Context context) {
        return WebSettings.getDefaultUserAgent(context);
    }
}

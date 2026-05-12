package com.appodeal.ads.utils;

import android.os.Build.VERSION;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.d.h;

public class w {

    public interface a {
        void a(String str, h hVar);
    }

    public static void a(ViewGroup viewGroup, final h hVar, final a aVar) {
        if (VERSION.SDK_INT >= 19) {
            WebView a = a((View) viewGroup);
            if (a != null) {
                try {
                    a.evaluateJavascript("(function() { return ('<html>'+document.getElementsByTagName('html')[0].innerHTML+'</html>'); })();", new ValueCallback<String>() {
                        public /* synthetic */ void onReceiveValue(Object obj) {
                            a((String) obj);
                        }

                        public void a(String str) {
                            if (str != null && !str.isEmpty() && aVar != null) {
                                aVar.a(w.b(str), hVar);
                            }
                        }
                    });
                } catch (Throwable e) {
                    Appodeal.a(e);
                }
            }
        }
    }

    private static WebView a(View view) {
        if (view instanceof WebView) {
            return (WebView) view;
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                WebView a = a(viewGroup.getChildAt(i));
                if (a != null) {
                    return a;
                }
            }
        }
        return null;
    }

    private static String b(String str) {
        String replace = str.replace("\\u003C", "<").replace("\\u003E", ">").replace("\n", "").replace("\\", "");
        if (replace.charAt(0) == '\"') {
            replace = replace.substring(1, replace.length());
        }
        if (replace.charAt(replace.length() - 1) == '\"') {
            return replace.substring(0, replace.length() - 2);
        }
        return replace;
    }
}

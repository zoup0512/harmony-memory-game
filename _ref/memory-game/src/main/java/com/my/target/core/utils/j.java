package com.my.target.core.utils;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.text.TextUtils;
import com.my.target.Tracer;
import com.my.target.ads.MyTargetActivity;
import com.my.target.core.async.commands.b;
import com.my.target.core.async.commands.b.a;
import com.my.target.core.models.banners.c;
import com.my.target.nativeads.banners.NavigationType;
import java.util.HashMap;
import java.util.Map;

/* compiled from: NavigationHelper */
public final class j implements a<String> {
    private final Map<b<String>, c> a = new HashMap();
    private Context b;

    public final /* synthetic */ void a(b bVar, Object obj) {
        String str = (String) obj;
        c cVar = (c) this.a.get(bVar);
        if (!(cVar == null || this.b == null)) {
            a(cVar, str);
        }
        if (this.a.containsKey(bVar)) {
            this.a.remove(bVar);
        }
    }

    public final void a(c cVar, Context context) {
        this.b = context;
        if (!this.a.containsValue(cVar)) {
            String c;
            Object obj = 1;
            if (cVar.getNavigationType().equals(NavigationType.STORE)) {
                Intent intent;
                c = cVar.c();
                if (this.b == null) {
                    intent = null;
                } else {
                    intent = this.b.getPackageManager().getLaunchIntentForPackage(c);
                }
                if (intent != null) {
                    if (a(cVar.c(), cVar.h())) {
                        com.my.target.core.async.a.a(cVar.i(), "deeplinkClick", context);
                        return;
                    } else if (a(intent, cVar.c(), cVar.b())) {
                        obj = null;
                    }
                }
            }
            if (this.b != null) {
                c = cVar.e();
                if (obj == null || !m.a(c)) {
                    b a = com.my.target.core.factories.b.a(c, this.b);
                    if (obj != null) {
                        a.a(this);
                        this.a.put(a, cVar);
                    }
                    a.b();
                } else {
                    a(cVar, c);
                }
                com.my.target.core.async.a.a(cVar.i(), "click", this.b);
            }
        }
    }

    private boolean a(Intent intent, String str, String str2) {
        if (this.b == null) {
            return false;
        }
        boolean z;
        if (!(TextUtils.isEmpty(str) || TextUtils.isEmpty(str2))) {
            try {
                Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse(str2));
                intent2.addFlags(268435456);
                intent2.setPackage(str);
                this.b.startActivity(intent2);
                z = true;
            } catch (Exception e) {
            }
            if (intent == null) {
                return false;
            }
            if (!z) {
                try {
                    intent.addFlags(268435456);
                    this.b.startActivity(intent);
                    return true;
                } catch (Exception e2) {
                }
            }
            return z;
        }
        z = false;
        if (intent == null) {
            return false;
        }
        if (z) {
            intent.addFlags(268435456);
            this.b.startActivity(intent);
            return true;
        }
        return z;
    }

    private boolean a(String str, String str2) {
        if (this.b == null || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str2));
            intent.addFlags(268435456);
            intent.setPackage(str);
            this.b.startActivity(intent);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void a(c cVar, String str) {
        if (this.b != null) {
            if (m.a(str)) {
                boolean z = false;
                if (m.b(str)) {
                    try {
                        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
                        intent.addFlags(268435456);
                        this.b.startActivity(intent);
                        z = true;
                    } catch (Exception e) {
                    }
                } else {
                    z = a(cVar.j(), str);
                }
                if (z) {
                    return;
                }
            }
            if (this.b == null) {
                return;
            }
            Intent intent2;
            if (cVar.g()) {
                intent2 = new Intent("android.intent.action.VIEW", Uri.parse(str));
                intent2.addFlags(268435456);
                this.b.startActivity(intent2);
            } else if (!l.b(18)) {
                a(str);
            } else if (this.b != null) {
                try {
                    intent2 = new Intent("android.intent.action.VIEW", Uri.parse(str));
                    Bundle bundle = new Bundle();
                    bundle.putBinder(CustomTabsIntent.EXTRA_SESSION, null);
                    if (!(this.b instanceof Activity)) {
                        Tracer.d("Starting chrome tab from outside activity Context, creating new task");
                        intent2.addFlags(268435456);
                    }
                    intent2.setPackage("com.android.chrome");
                    intent2.putExtras(bundle);
                    this.b.startActivity(intent2);
                } catch (ActivityNotFoundException e2) {
                    a(str);
                }
            }
        }
    }

    private boolean a(boolean z, String str) {
        if (this.b == null) {
            return false;
        }
        if (z) {
            try {
                Intent launchIntentForPackage = this.b.getPackageManager().getLaunchIntentForPackage("com.android.vending");
                launchIntentForPackage.setComponent(new ComponentName("com.android.vending", "com.google.android.finsky.activities.LaunchUrlHandlerActivity"));
                launchIntentForPackage.setFlags(268435456);
                launchIntentForPackage.setData(Uri.parse(str));
                this.b.startActivity(launchIntentForPackage);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        launchIntentForPackage = new Intent("android.intent.action.VIEW", Uri.parse(str));
        launchIntentForPackage.setFlags(268435456);
        this.b.startActivity(launchIntentForPackage);
        return true;
    }

    private void a(String str) {
        if (this.b != null) {
            Intent intent = new Intent(this.b, MyTargetActivity.class);
            intent.setAction("com.my.target.actions.webview");
            intent.putExtra(MyTargetActivity.WEB_VIEW_URL, str);
            intent.addFlags(268435456);
            this.b.startActivity(intent);
        }
    }
}

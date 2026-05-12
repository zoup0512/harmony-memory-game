package com.chartboost.sdk.Libraries;

import android.app.Activity;
import android.content.Context;
import com.chartboost.sdk.c;
import java.lang.ref.WeakReference;

public final class l extends WeakReference<Activity> {
    private static l b;
    private final int a;

    private l(Activity activity) {
        super(activity);
        this.a = activity.hashCode();
    }

    public static l a(Activity activity) {
        if (b == null || b.a != activity.hashCode()) {
            b = new l(activity);
        }
        return b;
    }

    public boolean b(Activity activity) {
        if (activity != null && activity.hashCode() == this.a) {
            return true;
        }
        return false;
    }

    public boolean a(l lVar) {
        if (lVar != null && lVar.a() == this.a) {
            return true;
        }
        return false;
    }

    public int a() {
        return this.a;
    }

    public int hashCode() {
        return a();
    }

    public Context b() {
        Context context = (Context) get();
        if (context == null) {
            return c.x();
        }
        return context;
    }
}

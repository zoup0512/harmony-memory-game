package com.flurry.sdk;

import android.content.Context;
import com.flurry.sdk.lq.a;
import java.lang.Thread.UncaughtExceptionHandler;

public class md implements kp, a, UncaughtExceptionHandler {
    private static final String a = md.class.getSimpleName();
    private boolean b;

    public final void a(Context context) {
        lq a = lp.a();
        this.b = ((Boolean) a.a("CaptureUncaughtExceptions")).booleanValue();
        a.a("CaptureUncaughtExceptions", (a) this);
        km.a(4, a, "initSettings, CrashReportingEnabled = " + this.b);
        me a2 = me.a();
        synchronized (a2.b) {
            a2.b.put(this, null);
        }
    }

    public final void a(String str, Object obj) {
        if (str.equals("CaptureUncaughtExceptions")) {
            this.b = ((Boolean) obj).booleanValue();
            km.a(4, a, "onSettingUpdate, CrashReportingEnabled = " + this.b);
            return;
        }
        km.a(6, a, "onSettingUpdate internal error!");
    }

    public void uncaughtException(Thread thread, Throwable th) {
        th.printStackTrace();
        if (this.b) {
            String str = "";
            StackTraceElement[] stackTrace = th.getStackTrace();
            if (stackTrace != null && stackTrace.length > 0) {
                StringBuilder stringBuilder = new StringBuilder();
                if (th.getMessage() != null) {
                    stringBuilder.append(" (").append(th.getMessage()).append(")\n");
                }
                str = stringBuilder.toString();
            } else if (th.getMessage() != null) {
                str = th.getMessage();
            }
            hr.a();
            hr.a("uncaught", str, th);
        }
        lm.a().d();
        jp.a().f();
    }
}

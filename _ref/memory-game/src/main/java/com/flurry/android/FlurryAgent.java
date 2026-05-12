package com.flurry.android;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.flurry.sdk.hr;
import com.flurry.sdk.jh;
import com.flurry.sdk.jk;
import com.flurry.sdk.jx;
import com.flurry.sdk.jy;
import com.flurry.sdk.jz;
import com.flurry.sdk.ka;
import com.flurry.sdk.kh;
import com.flurry.sdk.ki;
import com.flurry.sdk.km;
import com.flurry.sdk.ll;
import com.flurry.sdk.lm;
import com.flurry.sdk.lp;
import com.flurry.sdk.ly;
import com.flurry.sdk.mf;
import java.util.Date;
import java.util.Map;

public final class FlurryAgent {
    private static final String a = FlurryAgent.class.getSimpleName();
    private static FlurryAgentListener b = null;
    private static final kh<ll> c = new 1();
    private static boolean d = false;
    private static int e = 5;
    private static long f = 10000;
    private static boolean g = true;
    private static boolean h = false;
    private static String i = null;

    private FlurryAgent() {
    }

    @Deprecated
    public static void setFlurryAgentListener(FlurryAgentListener flurryAgentListener) {
        if (VERSION.SDK_INT < 10) {
            km.b(a, "Device SDK Version older than 10");
        } else if (flurryAgentListener == null) {
            km.b(a, "Listener cannot be null");
            ki.a().b("com.flurry.android.sdk.FlurrySessionEvent", c);
        } else {
            b = flurryAgentListener;
            ki.a().a("com.flurry.android.sdk.FlurrySessionEvent", c);
        }
    }

    @Deprecated
    public static void setLogEnabled(boolean z) {
        if (VERSION.SDK_INT < 10) {
            km.b(a, "Device SDK Version older than 10");
        } else if (z) {
            km.b();
        } else {
            km.a();
        }
    }

    @Deprecated
    public static void setLogLevel(int i) {
        if (VERSION.SDK_INT < 10) {
            km.b(a, "Device SDK Version older than 10");
        } else {
            km.a(i);
        }
    }

    @Deprecated
    public static void setContinueSessionMillis(long j) {
        if (VERSION.SDK_INT < 10) {
            km.b(a, "Device SDK Version older than 10");
        } else if (j < 5000) {
            km.b(a, "Invalid time set for session resumption: " + j);
        } else {
            lp.a().a("ContinueSessionMillis", Long.valueOf(j));
        }
    }

    @Deprecated
    public static void setCaptureUncaughtExceptions(boolean z) {
        if (VERSION.SDK_INT < 10) {
            km.b(a, "Device SDK Version older than 10");
        } else {
            lp.a().a("CaptureUncaughtExceptions", Boolean.valueOf(z));
        }
    }

    @Deprecated
    public static void setPulseEnabled(boolean z) {
        if (VERSION.SDK_INT < 10) {
            km.b(a, "Device SDK Version older than 10");
            return;
        }
        lp.a().a("ProtonEnabled", Boolean.valueOf(z));
        if (!z) {
            lp.a().a("analyticsEnabled", Boolean.valueOf(true));
        }
    }

    @Deprecated
    public static synchronized void init(Context context, String str) {
        synchronized (FlurryAgent.class) {
            if (VERSION.SDK_INT < 10) {
                km.b(a, "Device SDK Version older than 10");
            } else if (context == null) {
                throw new NullPointerException("Null context");
            } else if (TextUtils.isEmpty(str)) {
                throw new IllegalArgumentException("API key not specified");
            } else {
                if (jy.a() != null) {
                    km.e(a, "Flurry is already initialized");
                }
                try {
                    mf.a();
                    jy.a(context, str);
                } catch (Throwable th) {
                    km.a(a, "", th);
                }
            }
        }
    }

    public static int getAgentVersion() {
        return jz.b();
    }

    public static String getReleaseVersion() {
        return jz.a();
    }

    public static void setVersionName(String str) {
        if (VERSION.SDK_INT < 10) {
            km.b(a, "Device SDK Version older than 10");
        } else if (str == null) {
            km.b(a, "String versionName passed to setVersionName was null.");
        } else {
            lp.a().a("VersionName", str);
        }
    }

    public static void setReportLocation(boolean z) {
        if (VERSION.SDK_INT < 10) {
            km.b(a, "Device SDK Version older than 10");
        } else {
            lp.a().a("ReportLocation", Boolean.valueOf(z));
        }
    }

    public static void setLocation(float f, float f2) {
        if (VERSION.SDK_INT < 10) {
            km.b(a, "Device SDK Version older than 10");
            return;
        }
        Location location = new Location("Explicit");
        location.setLatitude((double) f);
        location.setLongitude((double) f2);
        lp.a().a("ExplicitLocation", location);
    }

    public static void clearLocation() {
        if (VERSION.SDK_INT < 10) {
            km.b(a, "Device SDK Version older than 10");
        } else {
            lp.a().a("ExplicitLocation", null);
        }
    }

    public static void setLogEvents(boolean z) {
        if (VERSION.SDK_INT < 10) {
            km.b(a, "Device SDK Version older than 10");
        } else {
            lp.a().a("LogEvents", Boolean.valueOf(z));
        }
    }

    public static void addOrigin(String str, String str2) {
        addOrigin(str, str2, null);
    }

    public static void addOrigin(String str, String str2, Map<String, String> map) {
        if (VERSION.SDK_INT < 10) {
            km.b(a, "Device SDK Version older than 10");
        } else if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("originName not specified");
        } else if (TextUtils.isEmpty(str2)) {
            throw new IllegalArgumentException("originVersion not specified");
        } else {
            try {
                ka.a().a(str, str2, map);
            } catch (Throwable th) {
                km.a(a, "", th);
            }
        }
    }

    @Deprecated
    public static void onStartSession(Context context, String str) {
        onStartSession(context);
    }

    public static void onStartSession(Context context) {
        if (VERSION.SDK_INT < 10) {
            km.b(a, "Device SDK Version older than 10");
        } else if (context == null) {
            throw new NullPointerException("Null context");
        } else if (jy.a() == null) {
            throw new IllegalStateException("Flurry SDK must be initialized before starting a session");
        } else {
            try {
                lm.a().b(context);
            } catch (Throwable th) {
                km.a(a, "", th);
            }
        }
    }

    public static void onEndSession(Context context) {
        if (VERSION.SDK_INT < 10) {
            km.b(a, "Device SDK Version older than 10");
        } else if (context == null) {
            throw new NullPointerException("Null context");
        } else if (jy.a() == null) {
            throw new IllegalStateException("Flurry SDK must be initialized before ending a session");
        } else {
            try {
                lm.a().c(context);
            } catch (Throwable th) {
                km.a(a, "", th);
            }
        }
    }

    public static boolean isSessionActive() {
        boolean z = false;
        if (VERSION.SDK_INT < 10) {
            km.b(a, "Device SDK Version older than 10");
        } else {
            try {
                z = lm.a().c();
            } catch (Throwable th) {
                km.a(a, "", th);
            }
        }
        return z;
    }

    public static String getSessionId() {
        String str = null;
        if (VERSION.SDK_INT < 10) {
            km.b(a, "Device SDK Version older than 10");
        } else if (jy.a() == null) {
            throw new IllegalStateException("Flurry SDK must be initialized before starting a session");
        } else {
            try {
                jk.a();
                str = jk.b();
            } catch (Throwable th) {
                km.a(a, "", th);
            }
        }
        return str;
    }

    public static FlurryEventRecordStatus logEvent(String str) {
        FlurryEventRecordStatus flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventFailed;
        if (VERSION.SDK_INT < 10) {
            km.b(a, "Device SDK Version older than 10");
            return flurryEventRecordStatus;
        } else if (str == null) {
            km.b(a, "String eventId passed to logEvent was null.");
            return flurryEventRecordStatus;
        } else {
            FlurryEventRecordStatus flurryEventRecordStatus2;
            try {
                hr.a();
                jh b = hr.b();
                flurryEventRecordStatus2 = FlurryEventRecordStatus.kFlurryEventFailed;
                if (b != null) {
                    flurryEventRecordStatus2 = b.a(str, null, false);
                }
            } catch (Throwable th) {
                km.a(a, "Failed to log event: " + str, th);
                flurryEventRecordStatus2 = flurryEventRecordStatus;
            }
            return flurryEventRecordStatus2;
        }
    }

    public static FlurryEventRecordStatus logEvent(String str, Map<String, String> map) {
        FlurryEventRecordStatus flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventFailed;
        if (VERSION.SDK_INT < 10) {
            km.b(a, "Device SDK Version older than 10");
            return flurryEventRecordStatus;
        } else if (str == null) {
            km.b(a, "String eventId passed to logEvent was null.");
            return flurryEventRecordStatus;
        } else if (map == null) {
            km.b(a, "String parameters passed to logEvent was null.");
            return flurryEventRecordStatus;
        } else {
            FlurryEventRecordStatus flurryEventRecordStatus2;
            try {
                hr.a();
                jh b = hr.b();
                flurryEventRecordStatus2 = FlurryEventRecordStatus.kFlurryEventFailed;
                if (b != null) {
                    flurryEventRecordStatus2 = b.a(str, map, false);
                }
            } catch (Throwable th) {
                km.a(a, "Failed to log event: " + str, th);
                flurryEventRecordStatus2 = flurryEventRecordStatus;
            }
            return flurryEventRecordStatus2;
        }
    }

    public static FlurryEventRecordStatus logEvent(FlurrySyndicationEventName flurrySyndicationEventName, String str, Map<String, String> map) {
        FlurryEventRecordStatus flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventFailed;
        if (VERSION.SDK_INT < 10) {
            km.b(a, "Device SDK Version older than 10");
            return flurryEventRecordStatus;
        } else if (flurrySyndicationEventName == null) {
            km.b(a, "String eventName passed to logEvent was null.");
            return flurryEventRecordStatus;
        } else if (TextUtils.isEmpty(str)) {
            km.b(a, "String syndicationId passed to logEvent was null or empty.");
            return flurryEventRecordStatus;
        } else if (map == null) {
            km.b(a, "String parameters passed to logEvent was null.");
            return flurryEventRecordStatus;
        } else {
            FlurryEventRecordStatus flurryEventRecordStatus2;
            try {
                hr.a();
                String flurrySyndicationEventName2 = flurrySyndicationEventName.toString();
                jh b = hr.b();
                flurryEventRecordStatus2 = FlurryEventRecordStatus.kFlurryEventFailed;
                if (b != null) {
                    flurryEventRecordStatus2 = b.a(flurrySyndicationEventName2, str, map);
                }
            } catch (Throwable th) {
                km.a(a, "Failed to log event: " + flurrySyndicationEventName.toString(), th);
                flurryEventRecordStatus2 = flurryEventRecordStatus;
            }
            return flurryEventRecordStatus2;
        }
    }

    public static FlurryEventRecordStatus logEvent(String str, boolean z) {
        FlurryEventRecordStatus flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventFailed;
        if (VERSION.SDK_INT < 10) {
            km.b(a, "Device SDK Version older than 10");
            return flurryEventRecordStatus;
        } else if (str == null) {
            km.b(a, "String eventId passed to logEvent was null.");
            return flurryEventRecordStatus;
        } else {
            FlurryEventRecordStatus flurryEventRecordStatus2;
            try {
                hr.a();
                jh b = hr.b();
                flurryEventRecordStatus2 = FlurryEventRecordStatus.kFlurryEventFailed;
                if (b != null) {
                    flurryEventRecordStatus2 = b.a(str, null, z);
                }
            } catch (Throwable th) {
                km.a(a, "Failed to log event: " + str, th);
                flurryEventRecordStatus2 = flurryEventRecordStatus;
            }
            return flurryEventRecordStatus2;
        }
    }

    public static FlurryEventRecordStatus logEvent(String str, Map<String, String> map, boolean z) {
        FlurryEventRecordStatus flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventFailed;
        if (VERSION.SDK_INT < 10) {
            km.b(a, "Device SDK Version older than 10");
            return flurryEventRecordStatus;
        } else if (str == null) {
            km.b(a, "String eventId passed to logEvent was null.");
            return flurryEventRecordStatus;
        } else if (map == null) {
            km.b(a, "String parameters passed to logEvent was null.");
            return flurryEventRecordStatus;
        } else {
            FlurryEventRecordStatus flurryEventRecordStatus2;
            try {
                hr.a();
                jh b = hr.b();
                flurryEventRecordStatus2 = FlurryEventRecordStatus.kFlurryEventFailed;
                if (b != null) {
                    flurryEventRecordStatus2 = b.a(str, map, z);
                }
            } catch (Throwable th) {
                km.a(a, "Failed to log event: " + str, th);
                flurryEventRecordStatus2 = flurryEventRecordStatus;
            }
            return flurryEventRecordStatus2;
        }
    }

    public static void endTimedEvent(String str) {
        if (VERSION.SDK_INT < 10) {
            km.b(a, "Device SDK Version older than 10");
        } else if (str == null) {
            km.b(a, "String eventId passed to endTimedEvent was null.");
        } else {
            try {
                hr.a();
                jh b = hr.b();
                if (b != null) {
                    b.a(str, null);
                }
            } catch (Throwable th) {
                km.a(a, "Failed to signify the end of event: " + str, th);
            }
        }
    }

    public static void endTimedEvent(String str, Map<String, String> map) {
        if (VERSION.SDK_INT < 10) {
            km.b(a, "Device SDK Version older than 10");
        } else if (str == null) {
            km.b(a, "String eventId passed to endTimedEvent was null.");
        } else if (map == null) {
            km.b(a, "String eventId passed to endTimedEvent was null.");
        } else {
            try {
                hr.a();
                jh b = hr.b();
                if (b != null) {
                    b.a(str, map);
                }
            } catch (Throwable th) {
                km.a(a, "Failed to signify the end of event: " + str, th);
            }
        }
    }

    @Deprecated
    public static void onError(String str, String str2, String str3) {
        if (VERSION.SDK_INT < 10) {
            km.b(a, "Device SDK Version older than 10");
        } else if (str == null) {
            km.b(a, "String errorId passed to onError was null.");
        } else if (str2 == null) {
            km.b(a, "String message passed to onError was null.");
        } else if (str3 == null) {
            km.b(a, "String errorClass passed to onError was null.");
        } else {
            try {
                StackTraceElement[] stackTraceElementArr;
                hr.a();
                Object stackTrace = Thread.currentThread().getStackTrace();
                if (stackTrace == null || stackTrace.length <= 2) {
                    Object obj = stackTrace;
                } else {
                    stackTraceElementArr = new StackTraceElement[(stackTrace.length - 2)];
                    System.arraycopy(stackTrace, 2, stackTraceElementArr, 0, stackTraceElementArr.length);
                }
                Throwable th = new Throwable(str2);
                th.setStackTrace(stackTraceElementArr);
                jh b = hr.b();
                if (b != null) {
                    b.a(str, str2, str3, th);
                }
            } catch (Throwable th2) {
                km.a(a, "", th2);
            }
        }
    }

    public static void onError(String str, String str2, Throwable th) {
        if (VERSION.SDK_INT < 10) {
            km.b(a, "Device SDK Version older than 10");
        } else if (str == null) {
            km.b(a, "String errorId passed to onError was null.");
        } else if (str2 == null) {
            km.b(a, "String message passed to onError was null.");
        } else if (th == null) {
            km.b(a, "Throwable passed to onError was null.");
        } else {
            try {
                hr.a();
                hr.a(str, str2, th);
            } catch (Throwable th2) {
                km.a(a, "", th2);
            }
        }
    }

    @Deprecated
    public static void onEvent(String str) {
        if (VERSION.SDK_INT < 10) {
            km.b(a, "Device SDK Version older than 10");
        } else if (str == null) {
            km.b(a, "String eventId passed to onEvent was null.");
        } else {
            try {
                hr.a();
                jh b = hr.b();
                if (b != null) {
                    b.a(str, null, false);
                }
            } catch (Throwable th) {
                km.a(a, "", th);
            }
        }
    }

    @Deprecated
    public static void onEvent(String str, Map<String, String> map) {
        if (VERSION.SDK_INT < 10) {
            km.b(a, "Device SDK Version older than 10");
        } else if (str == null) {
            km.b(a, "String eventId passed to onEvent was null.");
        } else if (map == null) {
            km.b(a, "Parameters Map passed to onEvent was null.");
        } else {
            try {
                hr.a();
                jh b = hr.b();
                if (b != null) {
                    b.a(str, map, false);
                }
            } catch (Throwable th) {
                km.a(a, "", th);
            }
        }
    }

    public static void onPageView() {
        if (VERSION.SDK_INT < 10) {
            km.b(a, "Device SDK Version older than 10");
            return;
        }
        try {
            hr.a();
            jh b = hr.b();
            if (b != null) {
                b.d();
            }
        } catch (Throwable th) {
            km.a(a, "", th);
        }
    }

    @Deprecated
    public static void setLocationCriteria(Criteria criteria) {
        if (VERSION.SDK_INT < 10) {
            km.b(a, "Device SDK Version older than 10");
        }
    }

    public static void setAge(int i) {
        if (VERSION.SDK_INT < 10) {
            km.b(a, "Device SDK Version older than 10");
        } else if (i > 0 && i < 110) {
            lp.a().a("Age", Long.valueOf(new Date(new Date(System.currentTimeMillis() - (((long) i) * 31449600000L)).getYear(), 1, 1).getTime()));
        }
    }

    public static void setGender(byte b) {
        if (VERSION.SDK_INT < 10) {
            km.b(a, "Device SDK Version older than 10");
            return;
        }
        switch (b) {
            case (byte) 0:
            case (byte) 1:
                lp.a().a("Gender", Byte.valueOf(b));
                return;
            default:
                lp.a().a("Gender", Byte.valueOf((byte) -1));
                return;
        }
    }

    public static void setUserId(String str) {
        if (VERSION.SDK_INT < 10) {
            km.b(a, "Device SDK Version older than 10");
        } else if (str == null) {
            km.b(a, "String userId passed to setUserId was null.");
        } else {
            lp.a().a("UserId", ly.b(str));
        }
    }

    public static void setSessionOrigin(String str, String str2) {
        if (VERSION.SDK_INT < 10) {
            km.b(a, "Device SDK Version older than 10");
        } else if (TextUtils.isEmpty(str)) {
            km.b(a, "String originName passed to setSessionOrigin was null or empty.");
        } else if (jy.a() == null) {
            throw new IllegalStateException("Flurry SDK must be initialized before starting a session");
        } else {
            jk.a();
            jx c = jk.c();
            if (c != null) {
                c.a(str);
            }
            jk.a();
            c = jk.c();
            if (c != null) {
                c.b(str2);
            }
        }
    }

    public static void addSessionProperty(String str, String str2) {
        if (VERSION.SDK_INT < 10) {
            km.b(a, "Device SDK Version older than 10");
        } else if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            km.b(a, "String name or value passed to addSessionProperty was null or empty.");
        } else if (jy.a() == null) {
            throw new IllegalStateException("Flurry SDK must be initialized before starting a session");
        } else {
            jk.a();
            jx c = jk.c();
            if (c != null) {
                c.a(str, str2);
            }
        }
    }

    static /* synthetic */ void a(FlurryAgentListener flurryAgentListener, boolean z, int i, long j, boolean z2, boolean z3, Context context, String str) {
        b = flurryAgentListener;
        setFlurryAgentListener(flurryAgentListener);
        d = z;
        setLogEnabled(z);
        e = i;
        setLogLevel(i);
        f = j;
        setContinueSessionMillis(j);
        g = z2;
        setCaptureUncaughtExceptions(z2);
        h = z3;
        setPulseEnabled(z3);
        i = str;
        init(context, i);
    }
}

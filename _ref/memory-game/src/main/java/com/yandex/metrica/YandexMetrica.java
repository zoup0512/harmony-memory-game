package com.yandex.metrica;

import android.app.Activity;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.location.Location;
import com.yandex.metrica.impl.bg;
import com.yandex.metrica.impl.bk;
import com.yandex.metrica.impl.utils.f;
import java.util.Map;

public final class YandexMetrica {
    private YandexMetrica() {
    }

    public static void activate(Context context, String apiKey) {
        bk.a(context, e.a(apiKey).b());
    }

    public static void activate(Context context, YandexMetricaConfig config) {
        bk.a(context, e.a(config));
    }

    public static void onResumeActivity(Activity activity) {
        bk.b().a(activity);
    }

    public static void onPauseActivity(Activity activity) {
        bk.b().b(activity);
    }

    public static void enableActivityAutoTracking(Application application) {
        bk.b().a(application);
    }

    public static void reportEvent(String eventName) {
        bk.b().b(eventName);
    }

    public static void reportError(String message, Throwable error) {
        bk.b().a(message, error);
    }

    public static void reportUnhandledException(Throwable exception) {
        bk.b().a(exception);
    }

    public static void reportNativeCrash(String nativeCrash) {
        bk.b().c(nativeCrash);
    }

    public static void reportEvent(String eventName, String jsonValue) {
        bk.b().a(eventName, jsonValue);
    }

    public static void reportEvent(String eventName, Map<String, Object> attributes) {
        bk.b().a(eventName, (Map) attributes);
    }

    public static void setSessionTimeout(int sessionTimeout) {
        bk.a(sessionTimeout);
    }

    public static void setReportCrashesEnabled(boolean enabled) {
        bk.a(enabled);
    }

    public static void setReportNativeCrashesEnabled(boolean enabled) {
        bk.b(enabled);
    }

    public static void setLocation(Location location) {
        bk.a(location);
    }

    public static void setTrackLocationEnabled(boolean enabled) {
        bk.c(enabled);
    }

    public static void setCustomAppVersion(String appVersion) {
        bk.e(appVersion);
    }

    public static void setLogEnabled() {
        f.e().a();
    }

    public static void setCollectInstalledApps(boolean collect) {
        bk.d(collect);
    }

    public static IReporter getReporter(Context context, String apiKey) {
        bg.b(apiKey);
        bk.a(context);
        return bk.b().a(apiKey);
    }

    public static void setEnvironmentValue(String key, String value) {
        bk.b(key, value);
    }

    public static String getLibraryVersion() {
        return "2.51";
    }

    public static int getLibraryApiLevel() {
        return 48;
    }

    public static boolean isCollectInstalledApps() {
        return bk.d();
    }

    public static void registerReferrerBroadcastReceivers(BroadcastReceiver... anotherReferrerReceivers) {
        MetricaEventHandler.a(anotherReferrerReceivers);
    }
}

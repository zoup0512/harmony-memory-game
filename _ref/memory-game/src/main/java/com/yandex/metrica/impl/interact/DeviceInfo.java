package com.yandex.metrica.impl.interact;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.Settings.Secure;
import android.util.DisplayMetrics;
import com.yandex.metrica.a;
import com.yandex.metrica.impl.ai;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class DeviceInfo {
    private static volatile DeviceInfo a;
    public final String appPlatform = AbstractSpiCall.ANDROID_CLIENT_TYPE;
    private final Context b;
    public final String deviceRootStatus;
    public final List<String> deviceRootStatusMarkers;
    public final String deviceType;
    public String locale = ai.b(this.b);
    public final String manufacturer = Build.MANUFACTURER;
    public final String model = Build.MODEL;
    public final String osVersion = VERSION.RELEASE;
    public final String platform = AbstractSpiCall.ANDROID_CLIENT_TYPE;
    public final String platformDeviceId = Secure.getString(this.b.getContentResolver(), "android_id");
    public final float scaleFactor = this.b.getResources().getDisplayMetrics().density;
    public final int screenDpi = this.b.getResources().getDisplayMetrics().densityDpi;
    public final int screenHeight = ai.a(this.b).y;
    public final int screenWidth = ai.a(this.b).x;

    public static DeviceInfo getInstance(Context context) {
        if (a == null) {
            synchronized (DeviceInfo.class) {
                if (a == null) {
                    a = new DeviceInfo(context.getApplicationContext());
                }
            }
        }
        return a;
    }

    private DeviceInfo(Context context) {
        Object obj;
        a aVar;
        this.b = context;
        Context context2 = this.b;
        DisplayMetrics displayMetrics = context2.getResources().getDisplayMetrics();
        Point a = ai.a(context2);
        int i = a.x;
        int i2 = a.y;
        float f = displayMetrics.density;
        float min = Math.min(((float) i) / f, ((float) i2) / f);
        f *= 160.0f;
        float f2 = ((float) i) / f;
        f = ((float) i2) / f;
        double sqrt = Math.sqrt((double) ((f * f) + (f2 * f2)));
        if (sqrt < 15.0d || context2.getPackageManager().hasSystemFeature("android.hardware.touchscreen")) {
            obj = null;
        } else {
            obj = 1;
        }
        if (obj != null) {
            aVar = a.TV;
        } else if (sqrt >= 7.0d || min >= 600.0f) {
            aVar = a.TABLET;
        } else {
            aVar = a.PHONE;
        }
        this.deviceType = aVar.name().toLowerCase(Locale.US);
        this.deviceRootStatus = String.valueOf(ai.a.c());
        this.deviceRootStatusMarkers = Collections.unmodifiableList(new ArrayList<String>() {
            {
                if (ai.a.a()) {
                    add("Superuser.apk");
                }
                if (ai.a.b()) {
                    add("su.so");
                }
            }
        });
    }

    public String getLocale() {
        this.locale = ai.b(this.b);
        return this.locale;
    }
}

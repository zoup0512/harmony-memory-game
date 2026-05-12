package com.my.target.core.providers;

import android.content.Context;
import android.os.Looper;
import com.mopub.common.GpsHelper;
import com.my.target.Tracer;
import java.lang.reflect.Method;

/* compiled from: GoogleAIdDataProvider */
public final class e extends a {
    private boolean a = false;

    public final synchronized void a(Context context) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Tracer.d("You must not call collectData method from main thread");
        } else if (!this.a) {
            Tracer.d("get google AId");
            try {
                Class cls = Class.forName("com.google.android.gms.ads.identifier.AdvertisingIdClient");
                if (cls != null) {
                    Method method = cls.getMethod("getAdvertisingIdInfo", new Class[]{Context.class});
                    if (method != null) {
                        Object invoke = method.invoke(null, new Object[]{context});
                        if (invoke != null) {
                            method = invoke.getClass().getMethod("getId", new Class[0]);
                            if (method != null) {
                                String str = (String) method.invoke(invoke, new Object[0]);
                                addParam("advertising_id", str);
                                Tracer.d("google AId: " + str);
                            }
                            method = invoke.getClass().getMethod(GpsHelper.IS_LIMIT_AD_TRACKING_ENABLED_KEY, new Class[0]);
                            if (method != null) {
                                boolean z;
                                boolean booleanValue = ((Boolean) method.invoke(invoke, new Object[0])).booleanValue();
                                addParam("advertising_tracking_enabled", (booleanValue ? 0 : 1));
                                StringBuilder stringBuilder = new StringBuilder("ad tracking enabled: ");
                                if (booleanValue) {
                                    z = false;
                                } else {
                                    z = true;
                                }
                                Tracer.d(stringBuilder.append(z).toString());
                            }
                        }
                    }
                }
            } catch (Throwable th) {
                Tracer.d(th.toString());
                Tracer.d("failed to get google AId");
            }
            this.a = true;
        }
    }
}

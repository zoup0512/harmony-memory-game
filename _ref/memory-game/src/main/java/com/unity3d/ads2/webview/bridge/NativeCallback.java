package com.unity3d.ads2.webview.bridge;

import com.unity3d.ads2.log.DeviceLog;
import com.unity3d.ads2.webview.WebViewApp;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

public class NativeCallback {
    private static AtomicInteger _callbackCount = new AtomicInteger(0);
    private Method _callback;
    private String _id = (this._callback.getName().toUpperCase(Locale.US) + EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR + _callbackCount.getAndIncrement());

    public NativeCallback(Method method) {
        this._callback = method;
    }

    public String getId() {
        return this._id;
    }

    public void invoke(String str, Object... objArr) {
        try {
            Object[] objArr2;
            CallbackStatus valueOf = CallbackStatus.valueOf(str);
            if (objArr == null) {
                objArr2 = new Object[]{valueOf};
            } else {
                ArrayList arrayList = new ArrayList(Arrays.asList(objArr));
                arrayList.add(0, valueOf);
                objArr2 = arrayList.toArray();
            }
            this._callback.invoke(null, objArr2);
            WebViewApp.getCurrentApp().removeCallback(this);
        } catch (Exception e) {
            DeviceLog.error("Illegal status");
            WebViewApp.getCurrentApp().removeCallback(this);
            throw e;
        }
    }
}

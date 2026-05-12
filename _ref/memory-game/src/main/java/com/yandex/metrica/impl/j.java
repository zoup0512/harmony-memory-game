package com.yandex.metrica.impl;

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import com.yandex.metrica.impl.ob.cn;
import com.yandex.metrica.impl.utils.h;
import com.yandex.metrica.impl.utils.i;

public class j extends ResultReceiver {
    private a a;

    interface a {
        void a(int i, Bundle bundle);
    }

    public j(Handler handler) {
        super(handler);
    }

    void a(a aVar) {
        this.a = aVar;
    }

    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if (this.a != null) {
            this.a.a(resultCode, resultData);
        }
    }

    public static void a(ResultReceiver resultReceiver, av avVar, a aVar) {
        if (resultReceiver != null) {
            Bundle bundle = new Bundle();
            bundle.putString("UuId", avVar.b());
            bundle.putString("DeviceId", avVar.c());
            bundle.putString("AdUrlGet", avVar.A());
            bundle.putString("AdUrlReport", avVar.B());
            bundle.putLong("ServerTimeOffset", i.a());
            bundle.putString("Clids", bg.b(h.a(avVar.y())));
            bundle.putString("CookieBrowsers", aVar.j().a());
            bundle.putString("BindIdUrl", aVar.k());
            resultReceiver.send(1, bundle);
        }
    }

    public static void a(ResultReceiver resultReceiver, cn cnVar) {
        if (resultReceiver != null) {
            resultReceiver.send(2, cnVar.a(new Bundle()));
        }
    }
}

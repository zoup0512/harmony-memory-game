package com.google.android.gms.measurement;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.MainThread;
import com.google.android.gms.measurement.internal.zzu;

public final class AppMeasurementReceiver extends BroadcastReceiver {
    private zzu ahH;

    private zzu zzbpp() {
        if (this.ahH == null) {
            this.ahH = new zzu();
        }
        return this.ahH;
    }

    @MainThread
    public void onReceive(Context context, Intent intent) {
        zzbpp().onReceive(context, intent);
    }
}

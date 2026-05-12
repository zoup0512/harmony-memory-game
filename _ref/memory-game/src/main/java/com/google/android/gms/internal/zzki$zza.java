package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.DownloadManager.Request;

@TargetApi(9)
public class zzki$zza extends zzki {
    public zzki$zza() {
        super(null);
    }

    public boolean zza(Request request) {
        request.setShowRunningNotification(true);
        return true;
    }

    public int zztj() {
        return 6;
    }

    public int zztk() {
        return 7;
    }
}

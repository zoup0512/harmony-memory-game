package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.Pair;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

public class zzpo$zza<R extends Result> extends Handler {
    public zzpo$zza() {
        this(Looper.getMainLooper());
    }

    public zzpo$zza(Looper looper) {
        super(looper);
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                Pair pair = (Pair) message.obj;
                zzb((ResultCallback) pair.first, (Result) pair.second);
                return;
            case 2:
                ((zzpo) message.obj).zzaa(Status.st);
                return;
            default:
                Log.wtf("BasePendingResult", "Don't know how to handle message: " + message.what, new Exception());
                return;
        }
    }

    public void zza(ResultCallback<? super R> resultCallback, R r) {
        sendMessage(obtainMessage(1, new Pair(resultCallback, r)));
    }

    public void zza(zzpo<R> com_google_android_gms_internal_zzpo_R, long j) {
        sendMessageDelayed(obtainMessage(2, com_google_android_gms_internal_zzpo_R), j);
    }

    public void zzaoz() {
        removeMessages(2);
    }

    protected void zzb(ResultCallback<? super R> resultCallback, R r) {
        try {
            resultCallback.onResult(r);
        } catch (RuntimeException e) {
            zzpo.zze(r);
            throw e;
        }
    }
}

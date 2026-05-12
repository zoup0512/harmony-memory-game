package com.google.android.gms.internal;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

public class zzalp {
    private static final AtomicReference<zzalp> baL = new AtomicReference();

    zzalp(Context context) {
    }

    @Nullable
    public static zzalp zzcxc() {
        return (zzalp) baL.get();
    }

    public static zzalp zzeq(Context context) {
        baL.compareAndSet(null, new zzalp(context));
        return (zzalp) baL.get();
    }

    public Set<String> zzcxd() {
        return Collections.emptySet();
    }

    public void zzf(@NonNull FirebaseApp firebaseApp) {
    }

    public FirebaseOptions zzta(@NonNull String str) {
        return null;
    }
}

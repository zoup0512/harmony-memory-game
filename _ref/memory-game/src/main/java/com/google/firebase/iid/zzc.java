package com.google.firebase.iid;

import android.support.annotation.Nullable;

public class zzc {
    private final FirebaseInstanceId bas;

    private zzc(FirebaseInstanceId firebaseInstanceId) {
        this.bas = firebaseInstanceId;
    }

    public static zzc zzcwr() {
        return new zzc(FirebaseInstanceId.getInstance());
    }

    public String getId() {
        return this.bas.getId();
    }

    @Nullable
    public String getToken() {
        return this.bas.getToken();
    }
}

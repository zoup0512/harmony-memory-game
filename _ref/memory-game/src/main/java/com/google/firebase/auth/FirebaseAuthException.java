package com.google.firebase.auth;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzab;
import com.google.firebase.FirebaseException;

public class FirebaseAuthException extends FirebaseException {
    private final String aNc;

    public FirebaseAuthException(@NonNull String str, @NonNull String str2) {
        super(str2);
        this.aNc = zzab.zzhr(str);
    }

    @NonNull
    public String getErrorCode() {
        return this.aNc;
    }
}

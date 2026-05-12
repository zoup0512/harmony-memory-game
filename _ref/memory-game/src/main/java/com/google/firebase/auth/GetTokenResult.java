package com.google.firebase.auth;

import android.support.annotation.Nullable;

public class GetTokenResult {
    private String co;

    public GetTokenResult(String str) {
        this.co = str;
    }

    @Nullable
    public String getToken() {
        return this.co;
    }
}

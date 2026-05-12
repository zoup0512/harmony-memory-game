package com.flurry.sdk;

import android.text.TextUtils;

public abstract class kg {
    protected String f = "com.flurry.android.sdk.ReplaceMeWithAProperEventName";

    public kg(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Event must have a name!");
        }
        this.f = str;
    }

    public final String a() {
        return this.f;
    }

    public final void b() {
        ki.a().a(this);
    }
}

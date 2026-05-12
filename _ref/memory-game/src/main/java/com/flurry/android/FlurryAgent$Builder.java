package com.flurry.android;

import android.content.Context;

public class FlurryAgent$Builder {
    private static FlurryAgentListener a = null;
    private boolean b = false;
    private int c = 5;
    private long d = 10000;
    private boolean e = true;
    private boolean f = false;

    public FlurryAgent$Builder withListener(FlurryAgentListener flurryAgentListener) {
        a = flurryAgentListener;
        return this;
    }

    public FlurryAgent$Builder withLogEnabled(boolean z) {
        this.b = z;
        return this;
    }

    public FlurryAgent$Builder withLogLevel(int i) {
        this.c = i;
        return this;
    }

    public FlurryAgent$Builder withContinueSessionMillis(long j) {
        this.d = j;
        return this;
    }

    public FlurryAgent$Builder withCaptureUncaughtExceptions(boolean z) {
        this.e = z;
        return this;
    }

    public FlurryAgent$Builder withPulseEnabled(boolean z) {
        this.f = z;
        return this;
    }

    public void build(Context context, String str) {
        FlurryAgent.a(a, this.b, this.c, this.d, this.e, this.f, context, str);
    }
}

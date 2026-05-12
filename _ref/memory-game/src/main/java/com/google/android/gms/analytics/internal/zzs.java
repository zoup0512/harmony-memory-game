package com.google.android.gms.analytics.internal;

import android.util.Log;
import com.google.android.gms.analytics.Logger;

class zzs implements Logger {
    private boolean zzcsl;
    private int zzcze = 2;

    zzs() {
    }

    public void error(Exception exception) {
    }

    public void error(String str) {
    }

    public int getLogLevel() {
        return this.zzcze;
    }

    public void info(String str) {
    }

    public void setLogLevel(int i) {
        this.zzcze = i;
        if (!this.zzcsl) {
            String str = (String) zzy.zzczn.get();
            Log.i((String) zzy.zzczn.get(), new StringBuilder(String.valueOf(str).length() + 91).append("Logger is deprecated. To enable debug logging, please run:\nadb shell setprop log.tag.").append(str).append(" DEBUG").toString());
            this.zzcsl = true;
        }
    }

    public void verbose(String str) {
    }

    public void warn(String str) {
    }
}

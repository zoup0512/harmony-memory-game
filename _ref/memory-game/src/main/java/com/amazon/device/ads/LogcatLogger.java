package com.amazon.device.ads;

import android.util.Log;

class LogcatLogger implements Logger {
    private String logTag;

    LogcatLogger() {
    }

    public LogcatLogger withLogTag(String str) {
        this.logTag = str;
        return this;
    }

    public void i(String str) {
        Log.i(this.logTag, str);
    }

    public void v(String str) {
        Log.v(this.logTag, str);
    }

    public void d(String str) {
        Log.d(this.logTag, str);
    }

    public void w(String str) {
        Log.w(this.logTag, str);
    }

    public void e(String str) {
        Log.e(this.logTag, str);
    }
}

package com.google.android.gms.tagmanager;

import android.util.Log;

public class zzy implements zzbo {
    private int zzcze = 5;

    public void e(String str) {
        if (this.zzcze <= 6) {
            Log.e("GoogleTagManager", str);
        }
    }

    public void setLogLevel(int i) {
        this.zzcze = i;
    }

    public void v(String str) {
        if (this.zzcze <= 2) {
            Log.v("GoogleTagManager", str);
        }
    }

    public void zzb(String str, Throwable th) {
        if (this.zzcze <= 6) {
            Log.e("GoogleTagManager", str, th);
        }
    }

    public void zzcv(String str) {
        if (this.zzcze <= 3) {
            Log.d("GoogleTagManager", str);
        }
    }

    public void zzcw(String str) {
        if (this.zzcze <= 4) {
            Log.i("GoogleTagManager", str);
        }
    }

    public void zzcx(String str) {
        if (this.zzcze <= 5) {
            Log.w("GoogleTagManager", str);
        }
    }

    public void zzd(String str, Throwable th) {
        if (this.zzcze <= 5) {
            Log.w("GoogleTagManager", str, th);
        }
    }
}

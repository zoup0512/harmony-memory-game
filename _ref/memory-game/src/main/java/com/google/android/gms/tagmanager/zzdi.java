package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Logger;
import com.google.android.gms.analytics.Tracker;

public class zzdi {
    private Context mContext;
    private Tracker zzcsd;
    private GoogleAnalytics zzcsf;

    static class zza implements Logger {
        zza() {
        }

        private static int zzzn(int i) {
            switch (i) {
                case 2:
                    return 0;
                case 3:
                case 4:
                    return 1;
                case 5:
                    return 2;
                default:
                    return 3;
            }
        }

        public void error(Exception exception) {
            zzbn.zzb("", exception);
        }

        public void error(String str) {
            zzbn.e(str);
        }

        public int getLogLevel() {
            return zzzn(zzbn.getLogLevel());
        }

        public void info(String str) {
            zzbn.zzcw(str);
        }

        public void setLogLevel(int i) {
            zzbn.zzcx("GA uses GTM logger. Please use TagManager.setLogLevel(int) instead.");
        }

        public void verbose(String str) {
            zzbn.v(str);
        }

        public void warn(String str) {
            zzbn.zzcx(str);
        }
    }

    public zzdi(Context context) {
        this.mContext = context;
    }

    private synchronized void zzpg(String str) {
        if (this.zzcsf == null) {
            this.zzcsf = GoogleAnalytics.getInstance(this.mContext);
            this.zzcsf.setLogger(new zza());
            this.zzcsd = this.zzcsf.newTracker(str);
        }
    }

    public Tracker zzpf(String str) {
        zzpg(str);
        return this.zzcsd;
    }
}

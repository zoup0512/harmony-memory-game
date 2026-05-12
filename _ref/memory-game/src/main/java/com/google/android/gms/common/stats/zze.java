package com.google.android.gms.common.stats;

import android.os.SystemClock;
import android.support.v4.util.SimpleArrayMap;
import android.util.Log;

public class zze {
    private final long AH;
    private final int AI;
    private final SimpleArrayMap<String, Long> AJ;

    public zze() {
        this.AH = 60000;
        this.AI = 10;
        this.AJ = new SimpleArrayMap(10);
    }

    public zze(int i, long j) {
        this.AH = j;
        this.AI = i;
        this.AJ = new SimpleArrayMap();
    }

    private void zze(long j, long j2) {
        for (int size = this.AJ.size() - 1; size >= 0; size--) {
            if (j2 - ((Long) this.AJ.valueAt(size)).longValue() > j) {
                this.AJ.removeAt(size);
            }
        }
    }

    public Long zzhx(String str) {
        Long l;
        long elapsedRealtime = SystemClock.elapsedRealtime();
        long j = this.AH;
        synchronized (this) {
            while (this.AJ.size() >= this.AI) {
                zze(j, elapsedRealtime);
                j /= 2;
                Log.w("ConnectionTracker", "The max capacity " + this.AI + " is not enough. Current durationThreshold is: " + j);
            }
            l = (Long) this.AJ.put(str, Long.valueOf(elapsedRealtime));
        }
        return l;
    }

    public boolean zzhy(String str) {
        boolean z;
        synchronized (this) {
            z = this.AJ.remove(str) != null;
        }
        return z;
    }
}

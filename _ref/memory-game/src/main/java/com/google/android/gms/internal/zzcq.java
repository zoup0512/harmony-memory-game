package com.google.android.gms.internal;

import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.internal.zzct.zza;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.PriorityQueue;

@zzin
public class zzcq {
    private final int zzata;
    private final int zzatb;
    private final int zzatc;
    private final zzcp zzatd = new zzcs();

    public zzcq(int i) {
        this.zzatb = i;
        this.zzata = 6;
        this.zzatc = 0;
    }

    public String zza(ArrayList<String> arrayList) {
        StringBuffer stringBuffer = new StringBuffer();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            stringBuffer.append(((String) it.next()).toLowerCase(Locale.US));
            stringBuffer.append('\n');
        }
        return zzab(stringBuffer.toString());
    }

    String zzab(String str) {
        String[] split = str.split("\n");
        if (split.length == 0) {
            return "";
        }
        zza zzif = zzif();
        PriorityQueue priorityQueue = new PriorityQueue(this.zzatb, new 1(this));
        for (String zzad : split) {
            String[] zzad2 = zzcr.zzad(zzad);
            if (zzad2.length != 0) {
                zzct.zza(zzad2, this.zzatb, this.zzata, priorityQueue);
            }
        }
        Iterator it = priorityQueue.iterator();
        while (it.hasNext()) {
            try {
                zzif.write(this.zzatd.zzaa(((zza) it.next()).zzati));
            } catch (Throwable e) {
                zzb.zzb("Error while writing hash to byteStream", e);
            }
        }
        return zzif.toString();
    }

    zza zzif() {
        return new zza();
    }
}

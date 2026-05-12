package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.SystemClock;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.zzu;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

@zzin
public class zzjv {
    private final Object zzail;
    private final zzjx zzaob;
    private boolean zzcfa;
    private final LinkedList<zza> zzcir;
    private final String zzcis;
    private final String zzcit;
    private long zzciu;
    private long zzciv;
    private long zzciw;
    private long zzcix;
    private long zzciy;
    private long zzciz;

    @zzin
    private static final class zza {
        private long zzcja = -1;
        private long zzcjb = -1;

        public Bundle toBundle() {
            Bundle bundle = new Bundle();
            bundle.putLong("topen", this.zzcja);
            bundle.putLong("tclose", this.zzcjb);
            return bundle;
        }

        public long zzsb() {
            return this.zzcjb;
        }

        public void zzsc() {
            this.zzcjb = SystemClock.elapsedRealtime();
        }

        public void zzsd() {
            this.zzcja = SystemClock.elapsedRealtime();
        }
    }

    public zzjv(zzjx com_google_android_gms_internal_zzjx, String str, String str2) {
        this.zzail = new Object();
        this.zzciu = -1;
        this.zzciv = -1;
        this.zzcfa = false;
        this.zzciw = -1;
        this.zzcix = 0;
        this.zzciy = -1;
        this.zzciz = -1;
        this.zzaob = com_google_android_gms_internal_zzjx;
        this.zzcis = str;
        this.zzcit = str2;
        this.zzcir = new LinkedList();
    }

    public zzjv(String str, String str2) {
        this(zzu.zzft(), str, str2);
    }

    public Bundle toBundle() {
        Bundle bundle;
        synchronized (this.zzail) {
            bundle = new Bundle();
            bundle.putString("seq_num", this.zzcis);
            bundle.putString("slotid", this.zzcit);
            bundle.putBoolean("ismediation", this.zzcfa);
            bundle.putLong("treq", this.zzciy);
            bundle.putLong("tresponse", this.zzciz);
            bundle.putLong("timp", this.zzciv);
            bundle.putLong("tload", this.zzciw);
            bundle.putLong("pcc", this.zzcix);
            bundle.putLong("tfetch", this.zzciu);
            ArrayList arrayList = new ArrayList();
            Iterator it = this.zzcir.iterator();
            while (it.hasNext()) {
                arrayList.add(((zza) it.next()).toBundle());
            }
            bundle.putParcelableArrayList("tclick", arrayList);
        }
        return bundle;
    }

    public void zzac(boolean z) {
        synchronized (this.zzail) {
            if (this.zzciz != -1) {
                this.zzciw = SystemClock.elapsedRealtime();
                if (!z) {
                    this.zzciv = this.zzciw;
                    this.zzaob.zza(this);
                }
            }
        }
    }

    public void zzad(boolean z) {
        synchronized (this.zzail) {
            if (this.zzciz != -1) {
                this.zzcfa = z;
                this.zzaob.zza(this);
            }
        }
    }

    public void zzl(long j) {
        synchronized (this.zzail) {
            this.zzciz = j;
            if (this.zzciz != -1) {
                this.zzaob.zza(this);
            }
        }
    }

    public void zzm(long j) {
        synchronized (this.zzail) {
            if (this.zzciz != -1) {
                this.zzciu = j;
                this.zzaob.zza(this);
            }
        }
    }

    public void zzq(AdRequestParcel adRequestParcel) {
        synchronized (this.zzail) {
            this.zzciy = SystemClock.elapsedRealtime();
            this.zzaob.zzsk().zzb(adRequestParcel, this.zzciy);
        }
    }

    public void zzry() {
        synchronized (this.zzail) {
            if (this.zzciz != -1 && this.zzciv == -1) {
                this.zzciv = SystemClock.elapsedRealtime();
                this.zzaob.zza(this);
            }
            this.zzaob.zzsk().zzry();
        }
    }

    public void zzrz() {
        synchronized (this.zzail) {
            if (this.zzciz != -1) {
                zza com_google_android_gms_internal_zzjv_zza = new zza();
                com_google_android_gms_internal_zzjv_zza.zzsd();
                this.zzcir.add(com_google_android_gms_internal_zzjv_zza);
                this.zzcix++;
                this.zzaob.zzsk().zzrz();
                this.zzaob.zza(this);
            }
        }
    }

    public void zzsa() {
        synchronized (this.zzail) {
            if (!(this.zzciz == -1 || this.zzcir.isEmpty())) {
                zza com_google_android_gms_internal_zzjv_zza = (zza) this.zzcir.getLast();
                if (com_google_android_gms_internal_zzjv_zza.zzsb() == -1) {
                    com_google_android_gms_internal_zzjv_zza.zzsc();
                    this.zzaob.zza(this);
                }
            }
        }
    }
}

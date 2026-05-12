package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.ArrayList;
import java.util.Iterator;

@zzin
public class zzcl {
    private final Object zzail = new Object();
    private final int zzarv;
    private final int zzarw;
    private final int zzarx;
    private final zzcq zzary;
    private ArrayList<String> zzarz = new ArrayList();
    private ArrayList<String> zzasa = new ArrayList();
    private int zzasb = 0;
    private int zzasc = 0;
    private int zzasd = 0;
    private int zzase;
    private String zzasf = "";
    private String zzasg = "";

    public zzcl(int i, int i2, int i3, int i4) {
        this.zzarv = i;
        this.zzarw = i2;
        this.zzarx = i3;
        this.zzary = new zzcq(i4);
    }

    private String zza(ArrayList<String> arrayList, int i) {
        if (arrayList.isEmpty()) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            stringBuffer.append((String) it.next());
            stringBuffer.append(' ');
            if (stringBuffer.length() > i) {
                break;
            }
        }
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        String stringBuffer2 = stringBuffer.toString();
        return stringBuffer2.length() >= i ? stringBuffer2.substring(0, i) : stringBuffer2;
    }

    private void zzf(@Nullable String str, boolean z) {
        if (str != null && str.length() >= this.zzarx) {
            synchronized (this.zzail) {
                this.zzarz.add(str);
                this.zzasb += str.length();
                if (z) {
                    this.zzasa.add(str);
                }
            }
        }
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof zzcl)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        zzcl com_google_android_gms_internal_zzcl = (zzcl) obj;
        return com_google_android_gms_internal_zzcl.zzhr() != null && com_google_android_gms_internal_zzcl.zzhr().equals(zzhr());
    }

    public int getScore() {
        return this.zzase;
    }

    public int hashCode() {
        return zzhr().hashCode();
    }

    public String toString() {
        int i = this.zzasc;
        int i2 = this.zzase;
        int i3 = this.zzasb;
        String valueOf = String.valueOf(zza(this.zzarz, 100));
        String valueOf2 = String.valueOf(zza(this.zzasa, 100));
        String str = this.zzasf;
        String str2 = this.zzasg;
        return new StringBuilder((((String.valueOf(valueOf).length() + 133) + String.valueOf(valueOf2).length()) + String.valueOf(str).length()) + String.valueOf(str2).length()).append("ActivityContent fetchId: ").append(i).append(" score:").append(i2).append(" total_length:").append(i3).append("\n text: ").append(valueOf).append("\n viewableText").append(valueOf2).append("\n signture: ").append(str).append("\n viewableSignture: ").append(str2).toString();
    }

    int zza(int i, int i2) {
        return (this.zzarv * i) + (this.zzarw * i2);
    }

    public void zzd(String str, boolean z) {
        zzf(str, z);
        synchronized (this.zzail) {
            if (this.zzasd < 0) {
                zzb.zzcv("ActivityContent: negative number of WebViews.");
            }
            zzhw();
        }
    }

    public void zze(String str, boolean z) {
        zzf(str, z);
    }

    public boolean zzhq() {
        boolean z;
        synchronized (this.zzail) {
            z = this.zzasd == 0;
        }
        return z;
    }

    public String zzhr() {
        return this.zzasf;
    }

    public String zzhs() {
        return this.zzasg;
    }

    public void zzht() {
        synchronized (this.zzail) {
            this.zzase -= 100;
        }
    }

    public void zzhu() {
        synchronized (this.zzail) {
            this.zzasd--;
        }
    }

    public void zzhv() {
        synchronized (this.zzail) {
            this.zzasd++;
        }
    }

    public void zzhw() {
        synchronized (this.zzail) {
            int zza = zza(this.zzasb, this.zzasc);
            if (zza > this.zzase) {
                this.zzase = zza;
                this.zzasf = this.zzary.zza(this.zzarz);
                this.zzasg = this.zzary.zza(this.zzasa);
            }
        }
    }

    int zzhx() {
        return this.zzasb;
    }

    public void zzl(int i) {
        this.zzasc = i;
    }
}

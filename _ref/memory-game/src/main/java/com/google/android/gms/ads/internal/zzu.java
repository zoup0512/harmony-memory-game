package com.google.android.gms.ads.internal;

import android.os.Build.VERSION;
import com.google.android.gms.ads.internal.overlay.zze;
import com.google.android.gms.ads.internal.overlay.zzq;
import com.google.android.gms.ads.internal.overlay.zzr;
import com.google.android.gms.ads.internal.purchase.zzi;
import com.google.android.gms.ads.internal.request.zza;
import com.google.android.gms.common.util.zzh;
import com.google.android.gms.internal.zzcz;
import com.google.android.gms.internal.zzda;
import com.google.android.gms.internal.zzdb;
import com.google.android.gms.internal.zzdf;
import com.google.android.gms.internal.zzfc;
import com.google.android.gms.internal.zzfk;
import com.google.android.gms.internal.zzgf;
import com.google.android.gms.internal.zzic;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zziw;
import com.google.android.gms.internal.zzjx;
import com.google.android.gms.internal.zzkh;
import com.google.android.gms.internal.zzki;
import com.google.android.gms.internal.zzko;
import com.google.android.gms.internal.zzkp;
import com.google.android.gms.internal.zzlc;
import com.google.android.gms.internal.zzlj;

@zzin
public class zzu {
    private static final Object zzamr = new Object();
    private static zzu zzant;
    private final zza zzanu = new zza();
    private final com.google.android.gms.ads.internal.overlay.zza zzanv = new com.google.android.gms.ads.internal.overlay.zza();
    private final zze zzanw = new zze();
    private final zzic zzanx = new zzic();
    private final zzkh zzany = new zzkh();
    private final zzlj zzanz = new zzlj();
    private final zzki zzaoa = zzki.zzay(VERSION.SDK_INT);
    private final zzjx zzaob = new zzjx(this.zzany);
    private final com.google.android.gms.common.util.zze zzaoc = new zzh();
    private final zzdf zzaod = new zzdf();
    private final zziw zzaoe = new zziw();
    private final zzda zzaof = new zzda();
    private final zzcz zzaog = new zzcz();
    private final zzdb zzaoh = new zzdb();
    private final zzi zzaoi = new zzi();
    private final zzfk zzaoj = new zzfk();
    private final zzko zzaok = new zzko();
    private final zzq zzaol = new zzq();
    private final zzr zzaom = new zzr();
    private final zzgf zzaon = new zzgf();
    private final zzkp zzaoo = new zzkp();
    private final zzg zzaop = new zzg();
    private final zzp zzaoq = new zzp();
    private final zzfc zzaor = new zzfc();
    private final zzlc zzaos = new zzlc();

    static {
        zza(new zzu());
    }

    protected zzu() {
    }

    protected static void zza(zzu com_google_android_gms_ads_internal_zzu) {
        synchronized (zzamr) {
            zzant = com_google_android_gms_ads_internal_zzu;
        }
    }

    private static zzu zzfl() {
        zzu com_google_android_gms_ads_internal_zzu;
        synchronized (zzamr) {
            com_google_android_gms_ads_internal_zzu = zzant;
        }
        return com_google_android_gms_ads_internal_zzu;
    }

    public static zza zzfm() {
        return zzfl().zzanu;
    }

    public static com.google.android.gms.ads.internal.overlay.zza zzfn() {
        return zzfl().zzanv;
    }

    public static zze zzfo() {
        return zzfl().zzanw;
    }

    public static zzic zzfp() {
        return zzfl().zzanx;
    }

    public static zzkh zzfq() {
        return zzfl().zzany;
    }

    public static zzlj zzfr() {
        return zzfl().zzanz;
    }

    public static zzki zzfs() {
        return zzfl().zzaoa;
    }

    public static zzjx zzft() {
        return zzfl().zzaob;
    }

    public static com.google.android.gms.common.util.zze zzfu() {
        return zzfl().zzaoc;
    }

    public static zzdf zzfv() {
        return zzfl().zzaod;
    }

    public static zziw zzfw() {
        return zzfl().zzaoe;
    }

    public static zzda zzfx() {
        return zzfl().zzaof;
    }

    public static zzcz zzfy() {
        return zzfl().zzaog;
    }

    public static zzdb zzfz() {
        return zzfl().zzaoh;
    }

    public static zzi zzga() {
        return zzfl().zzaoi;
    }

    public static zzfk zzgb() {
        return zzfl().zzaoj;
    }

    public static zzko zzgc() {
        return zzfl().zzaok;
    }

    public static zzq zzgd() {
        return zzfl().zzaol;
    }

    public static zzr zzge() {
        return zzfl().zzaom;
    }

    public static zzgf zzgf() {
        return zzfl().zzaon;
    }

    public static zzp zzgg() {
        return zzfl().zzaoq;
    }

    public static zzkp zzgh() {
        return zzfl().zzaoo;
    }

    public static zzg zzgi() {
        return zzfl().zzaop;
    }

    public static zzfc zzgj() {
        return zzfl().zzaor;
    }

    public static zzlc zzgk() {
        return zzfl().zzaos;
    }
}

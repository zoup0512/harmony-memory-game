package com.google.android.gms.tagmanager;

import android.content.Context;

public class zzz implements zzas {
    private static final Object aus = new Object();
    private static zzz avF;
    private zzck auV;
    private zzat avG;

    private zzz(Context context) {
        this(zzau.zzec(context), new zzcz());
    }

    zzz(zzat com_google_android_gms_tagmanager_zzat, zzck com_google_android_gms_tagmanager_zzck) {
        this.avG = com_google_android_gms_tagmanager_zzat;
        this.auV = com_google_android_gms_tagmanager_zzck;
    }

    public static zzas zzdv(Context context) {
        zzas com_google_android_gms_tagmanager_zzas;
        synchronized (aus) {
            if (avF == null) {
                avF = new zzz(context);
            }
            com_google_android_gms_tagmanager_zzas = avF;
        }
        return com_google_android_gms_tagmanager_zzas;
    }

    public boolean zzor(String str) {
        if (this.auV.zzade()) {
            this.avG.zzov(str);
            return true;
        }
        zzbn.zzcx("Too many urls sent too quickly with the TagManagerSender, rate limiting invoked.");
        return false;
    }
}

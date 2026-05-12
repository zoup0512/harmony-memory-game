package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.zzaf;
import com.google.android.gms.internal.zzai.zza;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public class zzbi extends zzal {
    private static final String ID = zzaf.LANGUAGE.toString();

    public zzbi() {
        super(ID, new String[0]);
    }

    public zza zzav(Map<String, zza> map) {
        Locale locale = Locale.getDefault();
        if (locale == null) {
            return zzdl.zzcdu();
        }
        String language = locale.getLanguage();
        return language == null ? zzdl.zzcdu() : zzdl.zzap(language.toLowerCase());
    }

    public boolean zzcag() {
        return false;
    }

    public /* bridge */ /* synthetic */ String zzcbp() {
        return super.zzcbp();
    }

    public /* bridge */ /* synthetic */ Set zzcbq() {
        return super.zzcbq();
    }
}

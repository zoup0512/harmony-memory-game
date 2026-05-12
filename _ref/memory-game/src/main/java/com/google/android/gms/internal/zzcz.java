package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@zzin
public class zzcz {
    private final Collection<zzcy> zzaxr = new ArrayList();
    private final Collection<zzcy<String>> zzaxs = new ArrayList();
    private final Collection<zzcy<String>> zzaxt = new ArrayList();

    public void zza(zzcy com_google_android_gms_internal_zzcy) {
        this.zzaxr.add(com_google_android_gms_internal_zzcy);
    }

    public void zzb(zzcy<String> com_google_android_gms_internal_zzcy_java_lang_String) {
        this.zzaxs.add(com_google_android_gms_internal_zzcy_java_lang_String);
    }

    public void zzc(zzcy<String> com_google_android_gms_internal_zzcy_java_lang_String) {
        this.zzaxt.add(com_google_android_gms_internal_zzcy_java_lang_String);
    }

    public List<String> zzjx() {
        List<String> arrayList = new ArrayList();
        for (zzcy com_google_android_gms_internal_zzcy : this.zzaxs) {
            String str = (String) com_google_android_gms_internal_zzcy.get();
            if (str != null) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    public List<String> zzjy() {
        List<String> zzjx = zzjx();
        for (zzcy com_google_android_gms_internal_zzcy : this.zzaxt) {
            String str = (String) com_google_android_gms_internal_zzcy.get();
            if (str != null) {
                zzjx.add(str);
            }
        }
        return zzjx;
    }
}

package com.google.android.gms.analytics.internal;

import android.text.TextUtils;
import com.facebook.appevents.AppEventsConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class zzab {
    private final List<Command> K;
    private final long L;
    private final long M;
    private final int N;
    private final boolean O;
    private final String P;
    private final Map<String, String> zzbeg;

    public zzab(zzc com_google_android_gms_analytics_internal_zzc, Map<String, String> map, long j, boolean z) {
        this(com_google_android_gms_analytics_internal_zzc, map, j, z, 0, 0, null);
    }

    public zzab(zzc com_google_android_gms_analytics_internal_zzc, Map<String, String> map, long j, boolean z, long j2, int i) {
        this(com_google_android_gms_analytics_internal_zzc, map, j, z, j2, i, null);
    }

    public zzab(zzc com_google_android_gms_analytics_internal_zzc, Map<String, String> map, long j, boolean z, long j2, int i, List<Command> list) {
        com.google.android.gms.common.internal.zzab.zzy(com_google_android_gms_analytics_internal_zzc);
        com.google.android.gms.common.internal.zzab.zzy(map);
        this.M = j;
        this.O = z;
        this.L = j2;
        this.N = i;
        this.K = list != null ? list : Collections.emptyList();
        this.P = zzr(list);
        Map hashMap = new HashMap();
        for (Entry entry : map.entrySet()) {
            String zza;
            if (zzl(entry.getKey())) {
                zza = zza(com_google_android_gms_analytics_internal_zzc, entry.getKey());
                if (zza != null) {
                    hashMap.put(zza, zzb(com_google_android_gms_analytics_internal_zzc, entry.getValue()));
                }
            }
        }
        for (Entry entry2 : map.entrySet()) {
            if (!zzl(entry2.getKey())) {
                zza = zza(com_google_android_gms_analytics_internal_zzc, entry2.getKey());
                if (zza != null) {
                    hashMap.put(zza, zzb(com_google_android_gms_analytics_internal_zzc, entry2.getValue()));
                }
            }
        }
        if (!TextUtils.isEmpty(this.P)) {
            zzao.zzc(hashMap, "_v", this.P);
            if (this.P.equals("ma4.0.0") || this.P.equals("ma4.0.1")) {
                hashMap.remove("adid");
            }
        }
        this.zzbeg = Collections.unmodifiableMap(hashMap);
    }

    public static zzab zza(zzc com_google_android_gms_analytics_internal_zzc, zzab com_google_android_gms_analytics_internal_zzab, Map<String, String> map) {
        return new zzab(com_google_android_gms_analytics_internal_zzc, map, com_google_android_gms_analytics_internal_zzab.zzacz(), com_google_android_gms_analytics_internal_zzab.zzadb(), com_google_android_gms_analytics_internal_zzab.zzacy(), com_google_android_gms_analytics_internal_zzab.zzacx(), com_google_android_gms_analytics_internal_zzab.zzada());
    }

    private static String zza(zzc com_google_android_gms_analytics_internal_zzc, Object obj) {
        if (obj == null) {
            return null;
        }
        Object obj2 = obj.toString();
        if (obj2.startsWith("&")) {
            obj2 = obj2.substring(1);
        }
        int length = obj2.length();
        if (length > 256) {
            obj2 = obj2.substring(0, 256);
            com_google_android_gms_analytics_internal_zzc.zzc("Hit param name is too long and will be trimmed", Integer.valueOf(length), obj2);
        }
        return TextUtils.isEmpty(obj2) ? null : obj2;
    }

    private static String zzb(zzc com_google_android_gms_analytics_internal_zzc, Object obj) {
        String obj2 = obj == null ? "" : obj.toString();
        int length = obj2.length();
        if (length <= 8192) {
            return obj2;
        }
        obj2 = obj2.substring(0, 8192);
        com_google_android_gms_analytics_internal_zzc.zzc("Hit param value is too long and will be trimmed", Integer.valueOf(length), obj2);
        return obj2;
    }

    private static boolean zzl(Object obj) {
        return obj == null ? false : obj.toString().startsWith("&");
    }

    private static String zzr(List<Command> list) {
        CharSequence value;
        if (list != null) {
            for (Command command : list) {
                if ("appendVersion".equals(command.getId())) {
                    value = command.getValue();
                    break;
                }
            }
        }
        value = null;
        return TextUtils.isEmpty(value) ? null : value;
    }

    private String zzs(String str, String str2) {
        com.google.android.gms.common.internal.zzab.zzhr(str);
        com.google.android.gms.common.internal.zzab.zzb(!str.startsWith("&"), (Object) "Short param name required");
        String str3 = (String) this.zzbeg.get(str);
        return str3 != null ? str3 : str2;
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("ht=").append(this.M);
        if (this.L != 0) {
            stringBuffer.append(", dbId=").append(this.L);
        }
        if (this.N != 0) {
            stringBuffer.append(", appUID=").append(this.N);
        }
        List<String> arrayList = new ArrayList(this.zzbeg.keySet());
        Collections.sort(arrayList);
        for (String str : arrayList) {
            stringBuffer.append(", ");
            stringBuffer.append(str);
            stringBuffer.append("=");
            stringBuffer.append((String) this.zzbeg.get(str));
        }
        return stringBuffer.toString();
    }

    public int zzacx() {
        return this.N;
    }

    public long zzacy() {
        return this.L;
    }

    public long zzacz() {
        return this.M;
    }

    public List<Command> zzada() {
        return this.K;
    }

    public boolean zzadb() {
        return this.O;
    }

    public long zzadc() {
        return zzao.zzey(zzs("_s", AppEventsConstants.EVENT_PARAM_VALUE_NO));
    }

    public String zzadd() {
        return zzs("_m", "");
    }

    public Map<String, String> zzm() {
        return this.zzbeg;
    }
}

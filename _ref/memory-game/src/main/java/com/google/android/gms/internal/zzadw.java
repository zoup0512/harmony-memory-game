package com.google.android.gms.internal;

import com.cube.memorygames.pushes.PushPayloadProcessor;
import com.google.android.gms.internal.zzah.zzh;
import com.google.android.gms.tagmanager.zzbn;
import com.google.android.gms.tagmanager.zzdl;
import com.yalantis.ucrop.util.FileUtils;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class zzadw {

    public static class zza {
        private final Map<String, com.google.android.gms.internal.zzai.zza> aCn;
        private final com.google.android.gms.internal.zzai.zza axG;

        private zza(Map<String, com.google.android.gms.internal.zzai.zza> map, com.google.android.gms.internal.zzai.zza com_google_android_gms_internal_zzai_zza) {
            this.aCn = map;
            this.axG = com_google_android_gms_internal_zzai_zza;
        }

        public static zzb zzcgy() {
            return new zzb();
        }

        public String toString() {
            String valueOf = String.valueOf(zzcfx());
            String valueOf2 = String.valueOf(this.axG);
            return new StringBuilder((String.valueOf(valueOf).length() + 32) + String.valueOf(valueOf2).length()).append("Properties: ").append(valueOf).append(" pushAfterEvaluate: ").append(valueOf2).toString();
        }

        public void zza(String str, com.google.android.gms.internal.zzai.zza com_google_android_gms_internal_zzai_zza) {
            this.aCn.put(str, com_google_android_gms_internal_zzai_zza);
        }

        public com.google.android.gms.internal.zzai.zza zzccv() {
            return this.axG;
        }

        public Map<String, com.google.android.gms.internal.zzai.zza> zzcfx() {
            return Collections.unmodifiableMap(this.aCn);
        }
    }

    public static class zzb {
        private final Map<String, com.google.android.gms.internal.zzai.zza> aCn;
        private com.google.android.gms.internal.zzai.zza axG;

        private zzb() {
            this.aCn = new HashMap();
        }

        public zzb zzb(String str, com.google.android.gms.internal.zzai.zza com_google_android_gms_internal_zzai_zza) {
            this.aCn.put(str, com_google_android_gms_internal_zzai_zza);
            return this;
        }

        public zza zzcgz() {
            return new zza(this.aCn, this.axG);
        }

        public zzb zzq(com.google.android.gms.internal.zzai.zza com_google_android_gms_internal_zzai_zza) {
            this.axG = com_google_android_gms_internal_zzai_zza;
            return this;
        }
    }

    public static class zzc {
        private final List<zze> aCk;
        private final Map<String, List<zza>> aCl;
        private final int aCm;
        private final String oi;

        private zzc(List<zze> list, Map<String, List<zza>> map, String str, int i) {
            this.aCk = Collections.unmodifiableList(list);
            this.aCl = Collections.unmodifiableMap(map);
            this.oi = str;
            this.aCm = i;
        }

        public static zzd zzcha() {
            return new zzd();
        }

        public String getVersion() {
            return this.oi;
        }

        public String toString() {
            String valueOf = String.valueOf(zzcfv());
            String valueOf2 = String.valueOf(this.aCl);
            return new StringBuilder((String.valueOf(valueOf).length() + 17) + String.valueOf(valueOf2).length()).append("Rules: ").append(valueOf).append("  Macros: ").append(valueOf2).toString();
        }

        public List<zze> zzcfv() {
            return this.aCk;
        }

        public Map<String, List<zza>> zzchb() {
            return this.aCl;
        }
    }

    public static class zzd {
        private final List<zze> aCk;
        private final Map<String, List<zza>> aCl;
        private int aCm;
        private String oi;

        private zzd() {
            this.aCk = new ArrayList();
            this.aCl = new HashMap();
            this.oi = "";
            this.aCm = 0;
        }

        public zzd zzb(zze com_google_android_gms_internal_zzadw_zze) {
            this.aCk.add(com_google_android_gms_internal_zzadw_zze);
            return this;
        }

        public zzd zzc(zza com_google_android_gms_internal_zzadw_zza) {
            String zzg = zzdl.zzg((com.google.android.gms.internal.zzai.zza) com_google_android_gms_internal_zzadw_zza.zzcfx().get(zzag.INSTANCE_NAME.toString()));
            List list = (List) this.aCl.get(zzg);
            if (list == null) {
                list = new ArrayList();
                this.aCl.put(zzg, list);
            }
            list.add(com_google_android_gms_internal_zzadw_zza);
            return this;
        }

        public zzc zzchc() {
            return new zzc(this.aCk, this.aCl, this.oi, this.aCm);
        }

        public zzd zzqs(String str) {
            this.oi = str;
            return this;
        }

        public zzd zzzt(int i) {
            this.aCm = i;
            return this;
        }
    }

    public static class zze {
        private final List<zza> aCY;
        private final List<zza> aCZ;
        private final List<zza> aCp;
        private final List<zza> aCq;
        private final List<zza> aCr;
        private final List<zza> aCs;
        private final List<String> aDa;
        private final List<String> aDb;
        private final List<String> aDc;
        private final List<String> aDd;

        private zze(List<zza> list, List<zza> list2, List<zza> list3, List<zza> list4, List<zza> list5, List<zza> list6, List<String> list7, List<String> list8, List<String> list9, List<String> list10) {
            this.aCp = Collections.unmodifiableList(list);
            this.aCq = Collections.unmodifiableList(list2);
            this.aCr = Collections.unmodifiableList(list3);
            this.aCs = Collections.unmodifiableList(list4);
            this.aCY = Collections.unmodifiableList(list5);
            this.aCZ = Collections.unmodifiableList(list6);
            this.aDa = Collections.unmodifiableList(list7);
            this.aDb = Collections.unmodifiableList(list8);
            this.aDc = Collections.unmodifiableList(list9);
            this.aDd = Collections.unmodifiableList(list10);
        }

        public static zzf zzchd() {
            return new zzf();
        }

        public String toString() {
            String valueOf = String.valueOf(zzcfz());
            String valueOf2 = String.valueOf(zzcga());
            String valueOf3 = String.valueOf(zzcgb());
            String valueOf4 = String.valueOf(zzcgc());
            String valueOf5 = String.valueOf(zzche());
            String valueOf6 = String.valueOf(zzchj());
            return new StringBuilder((((((String.valueOf(valueOf).length() + 102) + String.valueOf(valueOf2).length()) + String.valueOf(valueOf3).length()) + String.valueOf(valueOf4).length()) + String.valueOf(valueOf5).length()) + String.valueOf(valueOf6).length()).append("Positive predicates: ").append(valueOf).append("  Negative predicates: ").append(valueOf2).append("  Add tags: ").append(valueOf3).append("  Remove tags: ").append(valueOf4).append("  Add macros: ").append(valueOf5).append("  Remove macros: ").append(valueOf6).toString();
        }

        public List<zza> zzcfz() {
            return this.aCp;
        }

        public List<zza> zzcga() {
            return this.aCq;
        }

        public List<zza> zzcgb() {
            return this.aCr;
        }

        public List<zza> zzcgc() {
            return this.aCs;
        }

        public List<zza> zzche() {
            return this.aCY;
        }

        public List<String> zzchf() {
            return this.aDa;
        }

        public List<String> zzchg() {
            return this.aDb;
        }

        public List<String> zzchh() {
            return this.aDc;
        }

        public List<String> zzchi() {
            return this.aDd;
        }

        public List<zza> zzchj() {
            return this.aCZ;
        }
    }

    public static class zzf {
        private final List<zza> aCY;
        private final List<zza> aCZ;
        private final List<zza> aCp;
        private final List<zza> aCq;
        private final List<zza> aCr;
        private final List<zza> aCs;
        private final List<String> aDa;
        private final List<String> aDb;
        private final List<String> aDc;
        private final List<String> aDd;

        private zzf() {
            this.aCp = new ArrayList();
            this.aCq = new ArrayList();
            this.aCr = new ArrayList();
            this.aCs = new ArrayList();
            this.aCY = new ArrayList();
            this.aCZ = new ArrayList();
            this.aDa = new ArrayList();
            this.aDb = new ArrayList();
            this.aDc = new ArrayList();
            this.aDd = new ArrayList();
        }

        public zze zzchk() {
            return new zze(this.aCp, this.aCq, this.aCr, this.aCs, this.aCY, this.aCZ, this.aDa, this.aDb, this.aDc, this.aDd);
        }

        public zzf zzd(zza com_google_android_gms_internal_zzadw_zza) {
            this.aCp.add(com_google_android_gms_internal_zzadw_zza);
            return this;
        }

        public zzf zze(zza com_google_android_gms_internal_zzadw_zza) {
            this.aCq.add(com_google_android_gms_internal_zzadw_zza);
            return this;
        }

        public zzf zzf(zza com_google_android_gms_internal_zzadw_zza) {
            this.aCr.add(com_google_android_gms_internal_zzadw_zza);
            return this;
        }

        public zzf zzg(zza com_google_android_gms_internal_zzadw_zza) {
            this.aCs.add(com_google_android_gms_internal_zzadw_zza);
            return this;
        }

        public zzf zzh(zza com_google_android_gms_internal_zzadw_zza) {
            this.aCY.add(com_google_android_gms_internal_zzadw_zza);
            return this;
        }

        public zzf zzi(zza com_google_android_gms_internal_zzadw_zza) {
            this.aCZ.add(com_google_android_gms_internal_zzadw_zza);
            return this;
        }

        public zzf zzqt(String str) {
            this.aDc.add(str);
            return this;
        }

        public zzf zzqu(String str) {
            this.aDd.add(str);
            return this;
        }

        public zzf zzqv(String str) {
            this.aDa.add(str);
            return this;
        }

        public zzf zzqw(String str) {
            this.aDb.add(str);
            return this;
        }
    }

    public static class zzg extends Exception {
        public zzg(String str) {
            super(str);
        }
    }

    private static zza zza(com.google.android.gms.internal.zzah.zzb com_google_android_gms_internal_zzah_zzb, com.google.android.gms.internal.zzah.zzf com_google_android_gms_internal_zzah_zzf, com.google.android.gms.internal.zzai.zza[] com_google_android_gms_internal_zzai_zzaArr, int i) throws zzg {
        zzb zzcgy = zza.zzcgy();
        for (int valueOf : com_google_android_gms_internal_zzah_zzb.zzuq) {
            com.google.android.gms.internal.zzah.zze com_google_android_gms_internal_zzah_zze = (com.google.android.gms.internal.zzah.zze) zza(com_google_android_gms_internal_zzah_zzf.zzvg, Integer.valueOf(valueOf).intValue(), "properties");
            String str = (String) zza(com_google_android_gms_internal_zzah_zzf.zzve, com_google_android_gms_internal_zzah_zze.key, "keys");
            com.google.android.gms.internal.zzai.zza com_google_android_gms_internal_zzai_zza = (com.google.android.gms.internal.zzai.zza) zza(com_google_android_gms_internal_zzai_zzaArr, com_google_android_gms_internal_zzah_zze.value, PushPayloadProcessor.PROPERTY_MESSAGE_VALUES);
            if (zzag.PUSH_AFTER_EVALUATE.toString().equals(str)) {
                zzcgy.zzq(com_google_android_gms_internal_zzai_zza);
            } else {
                zzcgy.zzb(str, com_google_android_gms_internal_zzai_zza);
            }
        }
        return zzcgy.zzcgz();
    }

    private static zze zza(com.google.android.gms.internal.zzah.zzg com_google_android_gms_internal_zzah_zzg, List<zza> list, List<zza> list2, List<zza> list3, com.google.android.gms.internal.zzah.zzf com_google_android_gms_internal_zzah_zzf) {
        zzf zzchd = zze.zzchd();
        for (int valueOf : com_google_android_gms_internal_zzah_zzg.zzvu) {
            zzchd.zzd((zza) list3.get(Integer.valueOf(valueOf).intValue()));
        }
        for (int valueOf2 : com_google_android_gms_internal_zzah_zzg.zzvv) {
            zzchd.zze((zza) list3.get(Integer.valueOf(valueOf2).intValue()));
        }
        for (int valueOf22 : com_google_android_gms_internal_zzah_zzg.zzvw) {
            zzchd.zzf((zza) list.get(Integer.valueOf(valueOf22).intValue()));
        }
        for (int valueOf3 : com_google_android_gms_internal_zzah_zzg.zzvy) {
            zzchd.zzqt(com_google_android_gms_internal_zzah_zzf.zzvf[Integer.valueOf(valueOf3).intValue()].string);
        }
        for (int valueOf222 : com_google_android_gms_internal_zzah_zzg.zzvx) {
            zzchd.zzg((zza) list.get(Integer.valueOf(valueOf222).intValue()));
        }
        for (int valueOf32 : com_google_android_gms_internal_zzah_zzg.zzvz) {
            zzchd.zzqu(com_google_android_gms_internal_zzah_zzf.zzvf[Integer.valueOf(valueOf32).intValue()].string);
        }
        for (int valueOf2222 : com_google_android_gms_internal_zzah_zzg.zzwa) {
            zzchd.zzh((zza) list2.get(Integer.valueOf(valueOf2222).intValue()));
        }
        for (int valueOf322 : com_google_android_gms_internal_zzah_zzg.zzwc) {
            zzchd.zzqv(com_google_android_gms_internal_zzah_zzf.zzvf[Integer.valueOf(valueOf322).intValue()].string);
        }
        for (int valueOf22222 : com_google_android_gms_internal_zzah_zzg.zzwb) {
            zzchd.zzi((zza) list2.get(Integer.valueOf(valueOf22222).intValue()));
        }
        for (int valueOf4 : com_google_android_gms_internal_zzah_zzg.zzwd) {
            zzchd.zzqw(com_google_android_gms_internal_zzah_zzf.zzvf[Integer.valueOf(valueOf4).intValue()].string);
        }
        return zzchd.zzchk();
    }

    private static com.google.android.gms.internal.zzai.zza zza(int i, com.google.android.gms.internal.zzah.zzf com_google_android_gms_internal_zzah_zzf, com.google.android.gms.internal.zzai.zza[] com_google_android_gms_internal_zzai_zzaArr, Set<Integer> set) throws zzg {
        int i2 = 0;
        if (set.contains(Integer.valueOf(i))) {
            String valueOf = String.valueOf(set);
            zzqd(new StringBuilder(String.valueOf(valueOf).length() + 90).append("Value cycle detected.  Current value reference: ").append(i).append(".  Previous value references: ").append(valueOf).append(FileUtils.HIDDEN_PREFIX).toString());
        }
        com.google.android.gms.internal.zzai.zza com_google_android_gms_internal_zzai_zza = (com.google.android.gms.internal.zzai.zza) zza(com_google_android_gms_internal_zzah_zzf.zzvf, i, PushPayloadProcessor.PROPERTY_MESSAGE_VALUES);
        if (com_google_android_gms_internal_zzai_zzaArr[i] != null) {
            return com_google_android_gms_internal_zzai_zzaArr[i];
        }
        com.google.android.gms.internal.zzai.zza com_google_android_gms_internal_zzai_zza2 = null;
        set.add(Integer.valueOf(i));
        zzh zzp;
        int[] iArr;
        int length;
        int i3;
        int i4;
        switch (com_google_android_gms_internal_zzai_zza.type) {
            case 1:
            case 5:
            case 6:
            case 8:
                com_google_android_gms_internal_zzai_zza2 = com_google_android_gms_internal_zzai_zza;
                break;
            case 2:
                zzp = zzp(com_google_android_gms_internal_zzai_zza);
                com_google_android_gms_internal_zzai_zza2 = zzo(com_google_android_gms_internal_zzai_zza);
                com_google_android_gms_internal_zzai_zza2.zzwu = new com.google.android.gms.internal.zzai.zza[zzp.zzwg.length];
                iArr = zzp.zzwg;
                length = iArr.length;
                i3 = 0;
                while (i2 < length) {
                    i4 = i3 + 1;
                    com_google_android_gms_internal_zzai_zza2.zzwu[i3] = zza(iArr[i2], com_google_android_gms_internal_zzah_zzf, com_google_android_gms_internal_zzai_zzaArr, (Set) set);
                    i2++;
                    i3 = i4;
                }
                break;
            case 3:
                com_google_android_gms_internal_zzai_zza2 = zzo(com_google_android_gms_internal_zzai_zza);
                zzh zzp2 = zzp(com_google_android_gms_internal_zzai_zza);
                if (zzp2.zzwh.length != zzp2.zzwi.length) {
                    i3 = zzp2.zzwh.length;
                    zzqd("Uneven map keys (" + i3 + ") and map values (" + zzp2.zzwi.length + ")");
                }
                com_google_android_gms_internal_zzai_zza2.zzwv = new com.google.android.gms.internal.zzai.zza[zzp2.zzwh.length];
                com_google_android_gms_internal_zzai_zza2.zzww = new com.google.android.gms.internal.zzai.zza[zzp2.zzwh.length];
                int[] iArr2 = zzp2.zzwh;
                int length2 = iArr2.length;
                i3 = 0;
                i4 = 0;
                while (i3 < length2) {
                    int i5 = i4 + 1;
                    com_google_android_gms_internal_zzai_zza2.zzwv[i4] = zza(iArr2[i3], com_google_android_gms_internal_zzah_zzf, com_google_android_gms_internal_zzai_zzaArr, (Set) set);
                    i3++;
                    i4 = i5;
                }
                iArr = zzp2.zzwi;
                length = iArr.length;
                i3 = 0;
                while (i2 < length) {
                    i4 = i3 + 1;
                    com_google_android_gms_internal_zzai_zza2.zzww[i3] = zza(iArr[i2], com_google_android_gms_internal_zzah_zzf, com_google_android_gms_internal_zzai_zzaArr, (Set) set);
                    i2++;
                    i3 = i4;
                }
                break;
            case 4:
                com_google_android_gms_internal_zzai_zza2 = zzo(com_google_android_gms_internal_zzai_zza);
                com_google_android_gms_internal_zzai_zza2.zzwx = zzdl.zzg(zza(zzp(com_google_android_gms_internal_zzai_zza).zzwl, com_google_android_gms_internal_zzah_zzf, com_google_android_gms_internal_zzai_zzaArr, (Set) set));
                break;
            case 7:
                com_google_android_gms_internal_zzai_zza2 = zzo(com_google_android_gms_internal_zzai_zza);
                zzp = zzp(com_google_android_gms_internal_zzai_zza);
                com_google_android_gms_internal_zzai_zza2.zzxb = new com.google.android.gms.internal.zzai.zza[zzp.zzwk.length];
                iArr = zzp.zzwk;
                length = iArr.length;
                i3 = 0;
                while (i2 < length) {
                    i4 = i3 + 1;
                    com_google_android_gms_internal_zzai_zza2.zzxb[i3] = zza(iArr[i2], com_google_android_gms_internal_zzah_zzf, com_google_android_gms_internal_zzai_zzaArr, (Set) set);
                    i2++;
                    i3 = i4;
                }
                break;
        }
        if (com_google_android_gms_internal_zzai_zza2 == null) {
            valueOf = String.valueOf(com_google_android_gms_internal_zzai_zza);
            zzqd(new StringBuilder(String.valueOf(valueOf).length() + 15).append("Invalid value: ").append(valueOf).toString());
        }
        com_google_android_gms_internal_zzai_zzaArr[i] = com_google_android_gms_internal_zzai_zza2;
        set.remove(Integer.valueOf(i));
        return com_google_android_gms_internal_zzai_zza2;
    }

    private static <T> T zza(T[] tArr, int i, String str) throws zzg {
        if (i < 0 || i >= tArr.length) {
            zzqd(new StringBuilder(String.valueOf(str).length() + 45).append("Index out of bounds detected: ").append(i).append(" in ").append(str).toString());
        }
        return tArr[i];
    }

    public static zzc zzb(com.google.android.gms.internal.zzah.zzf com_google_android_gms_internal_zzah_zzf) throws zzg {
        int i;
        int i2 = 0;
        com.google.android.gms.internal.zzai.zza[] com_google_android_gms_internal_zzai_zzaArr = new com.google.android.gms.internal.zzai.zza[com_google_android_gms_internal_zzah_zzf.zzvf.length];
        for (i = 0; i < com_google_android_gms_internal_zzah_zzf.zzvf.length; i++) {
            zza(i, com_google_android_gms_internal_zzah_zzf, com_google_android_gms_internal_zzai_zzaArr, new HashSet(0));
        }
        zzd zzcha = zzc.zzcha();
        List arrayList = new ArrayList();
        for (i = 0; i < com_google_android_gms_internal_zzah_zzf.zzvi.length; i++) {
            arrayList.add(zza(com_google_android_gms_internal_zzah_zzf.zzvi[i], com_google_android_gms_internal_zzah_zzf, com_google_android_gms_internal_zzai_zzaArr, i));
        }
        List arrayList2 = new ArrayList();
        for (i = 0; i < com_google_android_gms_internal_zzah_zzf.zzvj.length; i++) {
            arrayList2.add(zza(com_google_android_gms_internal_zzah_zzf.zzvj[i], com_google_android_gms_internal_zzah_zzf, com_google_android_gms_internal_zzai_zzaArr, i));
        }
        List arrayList3 = new ArrayList();
        for (i = 0; i < com_google_android_gms_internal_zzah_zzf.zzvh.length; i++) {
            zza zza = zza(com_google_android_gms_internal_zzah_zzf.zzvh[i], com_google_android_gms_internal_zzah_zzf, com_google_android_gms_internal_zzai_zzaArr, i);
            zzcha.zzc(zza);
            arrayList3.add(zza);
        }
        com.google.android.gms.internal.zzah.zzg[] com_google_android_gms_internal_zzah_zzgArr = com_google_android_gms_internal_zzah_zzf.zzvk;
        int length = com_google_android_gms_internal_zzah_zzgArr.length;
        while (i2 < length) {
            zzcha.zzb(zza(com_google_android_gms_internal_zzah_zzgArr[i2], arrayList, arrayList3, arrayList2, com_google_android_gms_internal_zzah_zzf));
            i2++;
        }
        zzcha.zzqs(com_google_android_gms_internal_zzah_zzf.version);
        zzcha.zzzt(com_google_android_gms_internal_zzah_zzf.zzvs);
        return zzcha.zzchc();
    }

    public static void zzc(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }

    public static com.google.android.gms.internal.zzai.zza zzo(com.google.android.gms.internal.zzai.zza com_google_android_gms_internal_zzai_zza) {
        com.google.android.gms.internal.zzai.zza com_google_android_gms_internal_zzai_zza2 = new com.google.android.gms.internal.zzai.zza();
        com_google_android_gms_internal_zzai_zza2.type = com_google_android_gms_internal_zzai_zza.type;
        com_google_android_gms_internal_zzai_zza2.zzxc = (int[]) com_google_android_gms_internal_zzai_zza.zzxc.clone();
        if (com_google_android_gms_internal_zzai_zza.zzxd) {
            com_google_android_gms_internal_zzai_zza2.zzxd = com_google_android_gms_internal_zzai_zza.zzxd;
        }
        return com_google_android_gms_internal_zzai_zza2;
    }

    private static zzh zzp(com.google.android.gms.internal.zzai.zza com_google_android_gms_internal_zzai_zza) throws zzg {
        if (((zzh) com_google_android_gms_internal_zzai_zza.zza(zzh.zzwe)) == null) {
            String valueOf = String.valueOf(com_google_android_gms_internal_zzai_zza);
            zzqd(new StringBuilder(String.valueOf(valueOf).length() + 54).append("Expected a ServingValue and didn't get one. Value is: ").append(valueOf).toString());
        }
        return (zzh) com_google_android_gms_internal_zzai_zza.zza(zzh.zzwe);
    }

    private static void zzqd(String str) throws zzg {
        zzbn.e(str);
        throw new zzg(str);
    }
}

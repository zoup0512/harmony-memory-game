package com.google.android.gms.tagmanager;

import android.content.Context;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.gms.internal.zzadw;
import com.google.android.gms.internal.zzadw.zze;
import com.google.android.gms.internal.zzag;
import com.google.android.gms.internal.zzah.zzi;
import com.yalantis.ucrop.util.FileUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class zzcw {
    private static final zzcd<com.google.android.gms.internal.zzai.zza> axo = new zzcd(zzdl.zzcdu(), true);
    private final DataLayer auG;
    private final com.google.android.gms.internal.zzadw.zzc axp;
    private final zzai axq;
    private final Map<String, zzal> axr;
    private final Map<String, zzal> axs;
    private final Map<String, zzal> axt;
    private final zzl<com.google.android.gms.internal.zzadw.zza, zzcd<com.google.android.gms.internal.zzai.zza>> axu;
    private final zzl<String, zzb> axv;
    private final Set<zze> axw;
    private final Map<String, zzc> axx;
    private volatile String axy;
    private int axz;

    interface zza {
        void zza(zze com_google_android_gms_internal_zzadw_zze, Set<com.google.android.gms.internal.zzadw.zza> set, Set<com.google.android.gms.internal.zzadw.zza> set2, zzcr com_google_android_gms_tagmanager_zzcr);
    }

    private static class zzb {
        private zzcd<com.google.android.gms.internal.zzai.zza> axF;
        private com.google.android.gms.internal.zzai.zza axG;

        public zzb(zzcd<com.google.android.gms.internal.zzai.zza> com_google_android_gms_tagmanager_zzcd_com_google_android_gms_internal_zzai_zza, com.google.android.gms.internal.zzai.zza com_google_android_gms_internal_zzai_zza) {
            this.axF = com_google_android_gms_tagmanager_zzcd_com_google_android_gms_internal_zzai_zza;
            this.axG = com_google_android_gms_internal_zzai_zza;
        }

        public int getSize() {
            return (this.axG == null ? 0 : this.axG.aL()) + ((com.google.android.gms.internal.zzai.zza) this.axF.getObject()).aL();
        }

        public zzcd<com.google.android.gms.internal.zzai.zza> zzccu() {
            return this.axF;
        }

        public com.google.android.gms.internal.zzai.zza zzccv() {
            return this.axG;
        }
    }

    private static class zzc {
        private final Map<zze, List<com.google.android.gms.internal.zzadw.zza>> axH = new HashMap();
        private final Map<zze, List<com.google.android.gms.internal.zzadw.zza>> axI = new HashMap();
        private final Map<zze, List<String>> axJ = new HashMap();
        private final Map<zze, List<String>> axK = new HashMap();
        private com.google.android.gms.internal.zzadw.zza axL;
        private final Set<zze> axw = new HashSet();

        public void zza(zze com_google_android_gms_internal_zzadw_zze) {
            this.axw.add(com_google_android_gms_internal_zzadw_zze);
        }

        public void zza(zze com_google_android_gms_internal_zzadw_zze, com.google.android.gms.internal.zzadw.zza com_google_android_gms_internal_zzadw_zza) {
            List list = (List) this.axH.get(com_google_android_gms_internal_zzadw_zze);
            if (list == null) {
                list = new ArrayList();
                this.axH.put(com_google_android_gms_internal_zzadw_zze, list);
            }
            list.add(com_google_android_gms_internal_zzadw_zza);
        }

        public void zza(zze com_google_android_gms_internal_zzadw_zze, String str) {
            List list = (List) this.axJ.get(com_google_android_gms_internal_zzadw_zze);
            if (list == null) {
                list = new ArrayList();
                this.axJ.put(com_google_android_gms_internal_zzadw_zze, list);
            }
            list.add(str);
        }

        public void zzb(com.google.android.gms.internal.zzadw.zza com_google_android_gms_internal_zzadw_zza) {
            this.axL = com_google_android_gms_internal_zzadw_zza;
        }

        public void zzb(zze com_google_android_gms_internal_zzadw_zze, com.google.android.gms.internal.zzadw.zza com_google_android_gms_internal_zzadw_zza) {
            List list = (List) this.axI.get(com_google_android_gms_internal_zzadw_zze);
            if (list == null) {
                list = new ArrayList();
                this.axI.put(com_google_android_gms_internal_zzadw_zze, list);
            }
            list.add(com_google_android_gms_internal_zzadw_zza);
        }

        public void zzb(zze com_google_android_gms_internal_zzadw_zze, String str) {
            List list = (List) this.axK.get(com_google_android_gms_internal_zzadw_zze);
            if (list == null) {
                list = new ArrayList();
                this.axK.put(com_google_android_gms_internal_zzadw_zze, list);
            }
            list.add(str);
        }

        public Set<zze> zzccw() {
            return this.axw;
        }

        public Map<zze, List<com.google.android.gms.internal.zzadw.zza>> zzccx() {
            return this.axH;
        }

        public Map<zze, List<String>> zzccy() {
            return this.axJ;
        }

        public Map<zze, List<String>> zzccz() {
            return this.axK;
        }

        public Map<zze, List<com.google.android.gms.internal.zzadw.zza>> zzcda() {
            return this.axI;
        }

        public com.google.android.gms.internal.zzadw.zza zzcdb() {
            return this.axL;
        }
    }

    public zzcw(Context context, com.google.android.gms.internal.zzadw.zzc com_google_android_gms_internal_zzadw_zzc, DataLayer dataLayer, com.google.android.gms.tagmanager.zzt.zza com_google_android_gms_tagmanager_zzt_zza, com.google.android.gms.tagmanager.zzt.zza com_google_android_gms_tagmanager_zzt_zza2, zzai com_google_android_gms_tagmanager_zzai) {
        if (com_google_android_gms_internal_zzadw_zzc == null) {
            throw new NullPointerException("resource cannot be null");
        }
        this.axp = com_google_android_gms_internal_zzadw_zzc;
        this.axw = new HashSet(com_google_android_gms_internal_zzadw_zzc.zzcfv());
        this.auG = dataLayer;
        this.axq = com_google_android_gms_tagmanager_zzai;
        this.axu = new zzm().zza(1048576, new com.google.android.gms.tagmanager.zzm.zza<com.google.android.gms.internal.zzadw.zza, zzcd<com.google.android.gms.internal.zzai.zza>>(this) {
            final /* synthetic */ zzcw axA;

            {
                this.axA = r1;
            }

            public /* synthetic */ int sizeOf(Object obj, Object obj2) {
                return zza((com.google.android.gms.internal.zzadw.zza) obj, (zzcd) obj2);
            }

            public int zza(com.google.android.gms.internal.zzadw.zza com_google_android_gms_internal_zzadw_zza, zzcd<com.google.android.gms.internal.zzai.zza> com_google_android_gms_tagmanager_zzcd_com_google_android_gms_internal_zzai_zza) {
                return ((com.google.android.gms.internal.zzai.zza) com_google_android_gms_tagmanager_zzcd_com_google_android_gms_internal_zzai_zza.getObject()).aL();
            }
        });
        this.axv = new zzm().zza(1048576, new com.google.android.gms.tagmanager.zzm.zza<String, zzb>(this) {
            final /* synthetic */ zzcw axA;

            {
                this.axA = r1;
            }

            public /* synthetic */ int sizeOf(Object obj, Object obj2) {
                return zza((String) obj, (zzb) obj2);
            }

            public int zza(String str, zzb com_google_android_gms_tagmanager_zzcw_zzb) {
                return str.length() + com_google_android_gms_tagmanager_zzcw_zzb.getSize();
            }
        });
        this.axr = new HashMap();
        zzb(new zzj(context));
        zzb(new zzt(com_google_android_gms_tagmanager_zzt_zza2));
        zzb(new zzx(dataLayer));
        zzb(new zzdm(context, dataLayer));
        this.axs = new HashMap();
        zzc(new zzr());
        zzc(new zzaf());
        zzc(new zzag());
        zzc(new zzan());
        zzc(new zzao());
        zzc(new zzbj());
        zzc(new zzbk());
        zzc(new zzcm());
        zzc(new zzdf());
        this.axt = new HashMap();
        zza(new zzb(context));
        zza(new zzc(context));
        zza(new zze(context));
        zza(new zzf(context));
        zza(new zzg(context));
        zza(new zzh(context));
        zza(new zzi(context));
        zza(new zzn());
        zza(new zzq(this.axp.getVersion()));
        zza(new zzt(com_google_android_gms_tagmanager_zzt_zza));
        zza(new zzv(dataLayer));
        zza(new zzaa(context));
        zza(new zzab());
        zza(new zzae());
        zza(new zzaj(this));
        zza(new zzap());
        zza(new zzaq());
        zza(new zzbd(context));
        zza(new zzbf());
        zza(new zzbi());
        zza(new zzbp());
        zza(new zzbr(context));
        zza(new zzce());
        zza(new zzcg());
        zza(new zzcj());
        zza(new zzcl());
        zza(new zzcn(context));
        zza(new zzcx());
        zza(new zzcy());
        zza(new zzdh());
        zza(new zzdn());
        this.axx = new HashMap();
        for (zze com_google_android_gms_internal_zzadw_zze : this.axw) {
            if (com_google_android_gms_tagmanager_zzai.zzcbo()) {
                zza(com_google_android_gms_internal_zzadw_zze.zzche(), com_google_android_gms_internal_zzadw_zze.zzchf(), "add macro");
                zza(com_google_android_gms_internal_zzadw_zze.zzchj(), com_google_android_gms_internal_zzadw_zze.zzchg(), "remove macro");
                zza(com_google_android_gms_internal_zzadw_zze.zzcgb(), com_google_android_gms_internal_zzadw_zze.zzchh(), "add tag");
                zza(com_google_android_gms_internal_zzadw_zze.zzcgc(), com_google_android_gms_internal_zzadw_zze.zzchi(), "remove tag");
            }
            int i = 0;
            while (i < com_google_android_gms_internal_zzadw_zze.zzche().size()) {
                com.google.android.gms.internal.zzadw.zza com_google_android_gms_internal_zzadw_zza = (com.google.android.gms.internal.zzadw.zza) com_google_android_gms_internal_zzadw_zze.zzche().get(i);
                String str = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
                if (com_google_android_gms_tagmanager_zzai.zzcbo() && i < com_google_android_gms_internal_zzadw_zze.zzchf().size()) {
                    str = (String) com_google_android_gms_internal_zzadw_zze.zzchf().get(i);
                }
                zzc zzi = zzi(this.axx, zza(com_google_android_gms_internal_zzadw_zza));
                zzi.zza(com_google_android_gms_internal_zzadw_zze);
                zzi.zza(com_google_android_gms_internal_zzadw_zze, com_google_android_gms_internal_zzadw_zza);
                zzi.zza(com_google_android_gms_internal_zzadw_zze, str);
                i++;
            }
            i = 0;
            while (i < com_google_android_gms_internal_zzadw_zze.zzchj().size()) {
                com_google_android_gms_internal_zzadw_zza = (com.google.android.gms.internal.zzadw.zza) com_google_android_gms_internal_zzadw_zze.zzchj().get(i);
                str = AnalyticsEvents.PARAMETER_DIALOG_OUTCOME_VALUE_UNKNOWN;
                if (com_google_android_gms_tagmanager_zzai.zzcbo() && i < com_google_android_gms_internal_zzadw_zze.zzchg().size()) {
                    str = (String) com_google_android_gms_internal_zzadw_zze.zzchg().get(i);
                }
                zzi = zzi(this.axx, zza(com_google_android_gms_internal_zzadw_zza));
                zzi.zza(com_google_android_gms_internal_zzadw_zze);
                zzi.zzb(com_google_android_gms_internal_zzadw_zze, com_google_android_gms_internal_zzadw_zza);
                zzi.zzb(com_google_android_gms_internal_zzadw_zze, str);
                i++;
            }
        }
        for (Entry entry : this.axp.zzchb().entrySet()) {
            for (com.google.android.gms.internal.zzadw.zza com_google_android_gms_internal_zzadw_zza2 : (List) entry.getValue()) {
                if (!zzdl.zzk((com.google.android.gms.internal.zzai.zza) com_google_android_gms_internal_zzadw_zza2.zzcfx().get(zzag.NOT_DEFAULT_MACRO.toString())).booleanValue()) {
                    zzi(this.axx, (String) entry.getKey()).zzb(com_google_android_gms_internal_zzadw_zza2);
                }
            }
        }
    }

    private zzcd<com.google.android.gms.internal.zzai.zza> zza(com.google.android.gms.internal.zzai.zza com_google_android_gms_internal_zzai_zza, Set<String> set, zzdo com_google_android_gms_tagmanager_zzdo) {
        if (!com_google_android_gms_internal_zzai_zza.zzxd) {
            return new zzcd(com_google_android_gms_internal_zzai_zza, true);
        }
        com.google.android.gms.internal.zzai.zza zzo;
        int i;
        zzcd zza;
        String str;
        String valueOf;
        switch (com_google_android_gms_internal_zzai_zza.type) {
            case 2:
                zzo = zzadw.zzo(com_google_android_gms_internal_zzai_zza);
                zzo.zzwu = new com.google.android.gms.internal.zzai.zza[com_google_android_gms_internal_zzai_zza.zzwu.length];
                for (i = 0; i < com_google_android_gms_internal_zzai_zza.zzwu.length; i++) {
                    zza = zza(com_google_android_gms_internal_zzai_zza.zzwu[i], (Set) set, com_google_android_gms_tagmanager_zzdo.zzzh(i));
                    if (zza == axo) {
                        return axo;
                    }
                    zzo.zzwu[i] = (com.google.android.gms.internal.zzai.zza) zza.getObject();
                }
                return new zzcd(zzo, false);
            case 3:
                zzo = zzadw.zzo(com_google_android_gms_internal_zzai_zza);
                if (com_google_android_gms_internal_zzai_zza.zzwv.length != com_google_android_gms_internal_zzai_zza.zzww.length) {
                    str = "Invalid serving value: ";
                    valueOf = String.valueOf(com_google_android_gms_internal_zzai_zza.toString());
                    zzbn.e(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                    return axo;
                }
                zzo.zzwv = new com.google.android.gms.internal.zzai.zza[com_google_android_gms_internal_zzai_zza.zzwv.length];
                zzo.zzww = new com.google.android.gms.internal.zzai.zza[com_google_android_gms_internal_zzai_zza.zzwv.length];
                for (i = 0; i < com_google_android_gms_internal_zzai_zza.zzwv.length; i++) {
                    zza = zza(com_google_android_gms_internal_zzai_zza.zzwv[i], (Set) set, com_google_android_gms_tagmanager_zzdo.zzzi(i));
                    zzcd zza2 = zza(com_google_android_gms_internal_zzai_zza.zzww[i], (Set) set, com_google_android_gms_tagmanager_zzdo.zzzj(i));
                    if (zza == axo || zza2 == axo) {
                        return axo;
                    }
                    zzo.zzwv[i] = (com.google.android.gms.internal.zzai.zza) zza.getObject();
                    zzo.zzww[i] = (com.google.android.gms.internal.zzai.zza) zza2.getObject();
                }
                return new zzcd(zzo, false);
            case 4:
                if (set.contains(com_google_android_gms_internal_zzai_zza.zzwx)) {
                    valueOf = String.valueOf(com_google_android_gms_internal_zzai_zza.zzwx);
                    str = String.valueOf(set.toString());
                    zzbn.e(new StringBuilder((String.valueOf(valueOf).length() + 79) + String.valueOf(str).length()).append("Macro cycle detected.  Current macro reference: ").append(valueOf).append(".  Previous macro references: ").append(str).append(FileUtils.HIDDEN_PREFIX).toString());
                    return axo;
                }
                set.add(com_google_android_gms_internal_zzai_zza.zzwx);
                zzcd<com.google.android.gms.internal.zzai.zza> zza3 = zzdp.zza(zza(com_google_android_gms_internal_zzai_zza.zzwx, (Set) set, com_google_android_gms_tagmanager_zzdo.zzccc()), com_google_android_gms_internal_zzai_zza.zzxc);
                set.remove(com_google_android_gms_internal_zzai_zza.zzwx);
                return zza3;
            case 7:
                zzo = zzadw.zzo(com_google_android_gms_internal_zzai_zza);
                zzo.zzxb = new com.google.android.gms.internal.zzai.zza[com_google_android_gms_internal_zzai_zza.zzxb.length];
                for (i = 0; i < com_google_android_gms_internal_zzai_zza.zzxb.length; i++) {
                    zza = zza(com_google_android_gms_internal_zzai_zza.zzxb[i], (Set) set, com_google_android_gms_tagmanager_zzdo.zzzk(i));
                    if (zza == axo) {
                        return axo;
                    }
                    zzo.zzxb[i] = (com.google.android.gms.internal.zzai.zza) zza.getObject();
                }
                return new zzcd(zzo, false);
            default:
                zzbn.e("Unknown type: " + com_google_android_gms_internal_zzai_zza.type);
                return axo;
        }
    }

    private zzcd<com.google.android.gms.internal.zzai.zza> zza(String str, Set<String> set, zzbq com_google_android_gms_tagmanager_zzbq) {
        this.axz++;
        zzb com_google_android_gms_tagmanager_zzcw_zzb = (zzb) this.axv.get(str);
        if (com_google_android_gms_tagmanager_zzcw_zzb == null || this.axq.zzcbo()) {
            zzc com_google_android_gms_tagmanager_zzcw_zzc = (zzc) this.axx.get(str);
            String valueOf;
            if (com_google_android_gms_tagmanager_zzcw_zzc == null) {
                valueOf = String.valueOf(zzcct());
                zzbn.e(new StringBuilder((String.valueOf(valueOf).length() + 15) + String.valueOf(str).length()).append(valueOf).append("Invalid macro: ").append(str).toString());
                this.axz--;
                return axo;
            }
            com.google.android.gms.internal.zzadw.zza zzcdb;
            zzcd zza = zza(str, com_google_android_gms_tagmanager_zzcw_zzc.zzccw(), com_google_android_gms_tagmanager_zzcw_zzc.zzccx(), com_google_android_gms_tagmanager_zzcw_zzc.zzccy(), com_google_android_gms_tagmanager_zzcw_zzc.zzcda(), com_google_android_gms_tagmanager_zzcw_zzc.zzccz(), set, com_google_android_gms_tagmanager_zzbq.zzcba());
            if (((Set) zza.getObject()).isEmpty()) {
                zzcdb = com_google_android_gms_tagmanager_zzcw_zzc.zzcdb();
            } else {
                if (((Set) zza.getObject()).size() > 1) {
                    valueOf = String.valueOf(zzcct());
                    zzbn.zzcx(new StringBuilder((String.valueOf(valueOf).length() + 37) + String.valueOf(str).length()).append(valueOf).append("Multiple macros active for macroName ").append(str).toString());
                }
                zzcdb = (com.google.android.gms.internal.zzadw.zza) ((Set) zza.getObject()).iterator().next();
            }
            if (zzcdb == null) {
                this.axz--;
                return axo;
            }
            zzcd zza2 = zza(this.axt, zzcdb, (Set) set, com_google_android_gms_tagmanager_zzbq.zzcbu());
            boolean z = zza.zzccd() && zza2.zzccd();
            zzcd<com.google.android.gms.internal.zzai.zza> com_google_android_gms_tagmanager_zzcd = zza2 == axo ? axo : new zzcd((com.google.android.gms.internal.zzai.zza) zza2.getObject(), z);
            com.google.android.gms.internal.zzai.zza zzccv = zzcdb.zzccv();
            if (com_google_android_gms_tagmanager_zzcd.zzccd()) {
                this.axv.zzi(str, new zzb(com_google_android_gms_tagmanager_zzcd, zzccv));
            }
            zza(zzccv, (Set) set);
            this.axz--;
            return com_google_android_gms_tagmanager_zzcd;
        }
        zza(com_google_android_gms_tagmanager_zzcw_zzb.zzccv(), (Set) set);
        this.axz--;
        return com_google_android_gms_tagmanager_zzcw_zzb.zzccu();
    }

    private zzcd<com.google.android.gms.internal.zzai.zza> zza(Map<String, zzal> map, com.google.android.gms.internal.zzadw.zza com_google_android_gms_internal_zzadw_zza, Set<String> set, zzco com_google_android_gms_tagmanager_zzco) {
        boolean z = true;
        com.google.android.gms.internal.zzai.zza com_google_android_gms_internal_zzai_zza = (com.google.android.gms.internal.zzai.zza) com_google_android_gms_internal_zzadw_zza.zzcfx().get(zzag.FUNCTION.toString());
        if (com_google_android_gms_internal_zzai_zza == null) {
            zzbn.e("No function id in properties");
            return axo;
        }
        String str = com_google_android_gms_internal_zzai_zza.zzwy;
        zzal com_google_android_gms_tagmanager_zzal = (zzal) map.get(str);
        if (com_google_android_gms_tagmanager_zzal == null) {
            zzbn.e(String.valueOf(str).concat(" has no backing implementation."));
            return axo;
        }
        zzcd<com.google.android.gms.internal.zzai.zza> com_google_android_gms_tagmanager_zzcd_com_google_android_gms_internal_zzai_zza = (zzcd) this.axu.get(com_google_android_gms_internal_zzadw_zza);
        if (com_google_android_gms_tagmanager_zzcd_com_google_android_gms_internal_zzai_zza != null && !this.axq.zzcbo()) {
            return com_google_android_gms_tagmanager_zzcd_com_google_android_gms_internal_zzai_zza;
        }
        Map hashMap = new HashMap();
        boolean z2 = true;
        for (Entry entry : com_google_android_gms_internal_zzadw_zza.zzcfx().entrySet()) {
            zzcd zza = zza((com.google.android.gms.internal.zzai.zza) entry.getValue(), (Set) set, com_google_android_gms_tagmanager_zzco.zzoy((String) entry.getKey()).zze((com.google.android.gms.internal.zzai.zza) entry.getValue()));
            if (zza == axo) {
                return axo;
            }
            boolean z3;
            if (zza.zzccd()) {
                com_google_android_gms_internal_zzadw_zza.zza((String) entry.getKey(), (com.google.android.gms.internal.zzai.zza) zza.getObject());
                z3 = z2;
            } else {
                z3 = false;
            }
            hashMap.put((String) entry.getKey(), (com.google.android.gms.internal.zzai.zza) zza.getObject());
            z2 = z3;
        }
        if (com_google_android_gms_tagmanager_zzal.zzf(hashMap.keySet())) {
            if (!(z2 && com_google_android_gms_tagmanager_zzal.zzcag())) {
                z = false;
            }
            com_google_android_gms_tagmanager_zzcd_com_google_android_gms_internal_zzai_zza = new zzcd(com_google_android_gms_tagmanager_zzal.zzav(hashMap), z);
            if (z) {
                this.axu.zzi(com_google_android_gms_internal_zzadw_zza, com_google_android_gms_tagmanager_zzcd_com_google_android_gms_internal_zzai_zza);
            }
            com_google_android_gms_tagmanager_zzco.zzd((com.google.android.gms.internal.zzai.zza) com_google_android_gms_tagmanager_zzcd_com_google_android_gms_internal_zzai_zza.getObject());
            return com_google_android_gms_tagmanager_zzcd_com_google_android_gms_internal_zzai_zza;
        }
        String valueOf = String.valueOf(com_google_android_gms_tagmanager_zzal.zzcbq());
        String valueOf2 = String.valueOf(hashMap.keySet());
        zzbn.e(new StringBuilder(((String.valueOf(str).length() + 43) + String.valueOf(valueOf).length()) + String.valueOf(valueOf2).length()).append("Incorrect keys for function ").append(str).append(" required ").append(valueOf).append(" had ").append(valueOf2).toString());
        return axo;
    }

    private zzcd<Set<com.google.android.gms.internal.zzadw.zza>> zza(Set<zze> set, Set<String> set2, zza com_google_android_gms_tagmanager_zzcw_zza, zzcv com_google_android_gms_tagmanager_zzcv) {
        Set hashSet = new HashSet();
        Collection hashSet2 = new HashSet();
        boolean z = true;
        for (zze com_google_android_gms_internal_zzadw_zze : set) {
            zzcr zzccb = com_google_android_gms_tagmanager_zzcv.zzccb();
            zzcd zza = zza(com_google_android_gms_internal_zzadw_zze, (Set) set2, zzccb);
            if (((Boolean) zza.getObject()).booleanValue()) {
                com_google_android_gms_tagmanager_zzcw_zza.zza(com_google_android_gms_internal_zzadw_zze, hashSet, hashSet2, zzccb);
            }
            boolean z2 = z && zza.zzccd();
            z = z2;
        }
        hashSet.removeAll(hashSet2);
        com_google_android_gms_tagmanager_zzcv.zzg(hashSet);
        return new zzcd(hashSet, z);
    }

    private static String zza(com.google.android.gms.internal.zzadw.zza com_google_android_gms_internal_zzadw_zza) {
        return zzdl.zzg((com.google.android.gms.internal.zzai.zza) com_google_android_gms_internal_zzadw_zza.zzcfx().get(zzag.INSTANCE_NAME.toString()));
    }

    private void zza(com.google.android.gms.internal.zzai.zza com_google_android_gms_internal_zzai_zza, Set<String> set) {
        if (com_google_android_gms_internal_zzai_zza != null) {
            zzcd zza = zza(com_google_android_gms_internal_zzai_zza, (Set) set, new zzcb());
            if (zza != axo) {
                Object zzl = zzdl.zzl((com.google.android.gms.internal.zzai.zza) zza.getObject());
                if (zzl instanceof Map) {
                    this.auG.push((Map) zzl);
                } else if (zzl instanceof List) {
                    for (Object zzl2 : (List) zzl2) {
                        if (zzl2 instanceof Map) {
                            this.auG.push((Map) zzl2);
                        } else {
                            zzbn.zzcx("pushAfterEvaluate: value not a Map");
                        }
                    }
                } else {
                    zzbn.zzcx("pushAfterEvaluate: value not a Map or List");
                }
            }
        }
    }

    private static void zza(List<com.google.android.gms.internal.zzadw.zza> list, List<String> list2, String str) {
        if (list.size() != list2.size()) {
            zzbn.zzcw(new StringBuilder(String.valueOf(str).length() + 102).append("Invalid resource: imbalance of rule names of functions for ").append(str).append(" operation. Using default rule name instead").toString());
        }
    }

    private static void zza(Map<String, zzal> map, zzal com_google_android_gms_tagmanager_zzal) {
        if (map.containsKey(com_google_android_gms_tagmanager_zzal.zzcbp())) {
            String str = "Duplicate function type name: ";
            String valueOf = String.valueOf(com_google_android_gms_tagmanager_zzal.zzcbp());
            throw new IllegalArgumentException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        }
        map.put(com_google_android_gms_tagmanager_zzal.zzcbp(), com_google_android_gms_tagmanager_zzal);
    }

    private String zzcct() {
        if (this.axz <= 1) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Integer.toString(this.axz));
        for (int i = 2; i < this.axz; i++) {
            stringBuilder.append(' ');
        }
        stringBuilder.append(": ");
        return stringBuilder.toString();
    }

    private static zzc zzi(Map<String, zzc> map, String str) {
        zzc com_google_android_gms_tagmanager_zzcw_zzc = (zzc) map.get(str);
        if (com_google_android_gms_tagmanager_zzcw_zzc != null) {
            return com_google_android_gms_tagmanager_zzcw_zzc;
        }
        com_google_android_gms_tagmanager_zzcw_zzc = new zzc();
        map.put(str, com_google_android_gms_tagmanager_zzcw_zzc);
        return com_google_android_gms_tagmanager_zzcw_zzc;
    }

    zzcd<Boolean> zza(com.google.android.gms.internal.zzadw.zza com_google_android_gms_internal_zzadw_zza, Set<String> set, zzco com_google_android_gms_tagmanager_zzco) {
        zzcd zza = zza(this.axs, com_google_android_gms_internal_zzadw_zza, (Set) set, com_google_android_gms_tagmanager_zzco);
        Boolean zzk = zzdl.zzk((com.google.android.gms.internal.zzai.zza) zza.getObject());
        com_google_android_gms_tagmanager_zzco.zzd(zzdl.zzap(zzk));
        return new zzcd(zzk, zza.zzccd());
    }

    zzcd<Boolean> zza(zze com_google_android_gms_internal_zzadw_zze, Set<String> set, zzcr com_google_android_gms_tagmanager_zzcr) {
        boolean z = true;
        for (com.google.android.gms.internal.zzadw.zza zza : com_google_android_gms_internal_zzadw_zze.zzcga()) {
            zzcd zza2 = zza(zza, (Set) set, com_google_android_gms_tagmanager_zzcr.zzcbv());
            if (((Boolean) zza2.getObject()).booleanValue()) {
                com_google_android_gms_tagmanager_zzcr.zzf(zzdl.zzap(Boolean.valueOf(false)));
                return new zzcd(Boolean.valueOf(false), zza2.zzccd());
            }
            boolean z2 = z && zza2.zzccd();
            z = z2;
        }
        for (com.google.android.gms.internal.zzadw.zza zza3 : com_google_android_gms_internal_zzadw_zze.zzcfz()) {
            zza2 = zza(zza3, (Set) set, com_google_android_gms_tagmanager_zzcr.zzcbw());
            if (((Boolean) zza2.getObject()).booleanValue()) {
                z = z && zza2.zzccd();
            } else {
                com_google_android_gms_tagmanager_zzcr.zzf(zzdl.zzap(Boolean.valueOf(false)));
                return new zzcd(Boolean.valueOf(false), zza2.zzccd());
            }
        }
        com_google_android_gms_tagmanager_zzcr.zzf(zzdl.zzap(Boolean.valueOf(true)));
        return new zzcd(Boolean.valueOf(true), z);
    }

    zzcd<Set<com.google.android.gms.internal.zzadw.zza>> zza(String str, Set<zze> set, Map<zze, List<com.google.android.gms.internal.zzadw.zza>> map, Map<zze, List<String>> map2, Map<zze, List<com.google.android.gms.internal.zzadw.zza>> map3, Map<zze, List<String>> map4, Set<String> set2, zzcv com_google_android_gms_tagmanager_zzcv) {
        final Map<zze, List<com.google.android.gms.internal.zzadw.zza>> map5 = map;
        final Map<zze, List<String>> map6 = map2;
        final Map<zze, List<com.google.android.gms.internal.zzadw.zza>> map7 = map3;
        final Map<zze, List<String>> map8 = map4;
        return zza((Set) set, (Set) set2, new zza(this) {
            final /* synthetic */ zzcw axA;

            public void zza(zze com_google_android_gms_internal_zzadw_zze, Set<com.google.android.gms.internal.zzadw.zza> set, Set<com.google.android.gms.internal.zzadw.zza> set2, zzcr com_google_android_gms_tagmanager_zzcr) {
                List list = (List) map5.get(com_google_android_gms_internal_zzadw_zze);
                List list2 = (List) map6.get(com_google_android_gms_internal_zzadw_zze);
                if (list != null) {
                    set.addAll(list);
                    com_google_android_gms_tagmanager_zzcr.zzcbx().zzc(list, list2);
                }
                list = (List) map7.get(com_google_android_gms_internal_zzadw_zze);
                list2 = (List) map8.get(com_google_android_gms_internal_zzadw_zze);
                if (list != null) {
                    set2.addAll(list);
                    com_google_android_gms_tagmanager_zzcr.zzcby().zzc(list, list2);
                }
            }
        }, com_google_android_gms_tagmanager_zzcv);
    }

    zzcd<Set<com.google.android.gms.internal.zzadw.zza>> zza(Set<zze> set, zzcv com_google_android_gms_tagmanager_zzcv) {
        return zza((Set) set, new HashSet(), new zza(this) {
            final /* synthetic */ zzcw axA;

            {
                this.axA = r1;
            }

            public void zza(zze com_google_android_gms_internal_zzadw_zze, Set<com.google.android.gms.internal.zzadw.zza> set, Set<com.google.android.gms.internal.zzadw.zza> set2, zzcr com_google_android_gms_tagmanager_zzcr) {
                set.addAll(com_google_android_gms_internal_zzadw_zze.zzcgb());
                set2.addAll(com_google_android_gms_internal_zzadw_zze.zzcgc());
                com_google_android_gms_tagmanager_zzcr.zzcbz().zzc(com_google_android_gms_internal_zzadw_zze.zzcgb(), com_google_android_gms_internal_zzadw_zze.zzchh());
                com_google_android_gms_tagmanager_zzcr.zzcca().zzc(com_google_android_gms_internal_zzadw_zze.zzcgc(), com_google_android_gms_internal_zzadw_zze.zzchi());
            }
        }, com_google_android_gms_tagmanager_zzcv);
    }

    void zza(zzal com_google_android_gms_tagmanager_zzal) {
        zza(this.axt, com_google_android_gms_tagmanager_zzal);
    }

    public synchronized void zzaj(List<zzi> list) {
        for (zzi com_google_android_gms_internal_zzah_zzi : list) {
            if (com_google_android_gms_internal_zzah_zzi.name == null || !com_google_android_gms_internal_zzah_zzi.name.startsWith("gaExperiment:")) {
                String valueOf = String.valueOf(com_google_android_gms_internal_zzah_zzi);
                zzbn.v(new StringBuilder(String.valueOf(valueOf).length() + 22).append("Ignored supplemental: ").append(valueOf).toString());
            } else {
                zzak.zza(this.auG, com_google_android_gms_internal_zzah_zzi);
            }
        }
    }

    void zzb(zzal com_google_android_gms_tagmanager_zzal) {
        zza(this.axr, com_google_android_gms_tagmanager_zzal);
    }

    void zzc(zzal com_google_android_gms_tagmanager_zzal) {
        zza(this.axs, com_google_android_gms_tagmanager_zzal);
    }

    synchronized String zzccs() {
        return this.axy;
    }

    public synchronized void zzog(String str) {
        zzpd(str);
        zzah zzot = this.axq.zzot(str);
        zzu zzcbm = zzot.zzcbm();
        for (com.google.android.gms.internal.zzadw.zza zza : (Set) zza(this.axw, zzcbm.zzcba()).getObject()) {
            zza(this.axr, zza, new HashSet(), zzcbm.zzcaz());
        }
        zzot.zzcbn();
        zzpd(null);
    }

    public zzcd<com.google.android.gms.internal.zzai.zza> zzpc(String str) {
        this.axz = 0;
        zzah zzos = this.axq.zzos(str);
        zzcd<com.google.android.gms.internal.zzai.zza> zza = zza(str, new HashSet(), zzos.zzcbl());
        zzos.zzcbn();
        return zza;
    }

    synchronized void zzpd(String str) {
        this.axy = str;
    }
}

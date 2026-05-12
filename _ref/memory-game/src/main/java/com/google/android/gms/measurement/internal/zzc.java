package com.google.android.gms.measurement.internal;

import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.amazonaws.services.s3.internal.Constants;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.internal.zzuf.zza;
import com.google.android.gms.internal.zzuf.zzb;
import com.google.android.gms.internal.zzuf.zze;
import com.google.android.gms.internal.zzuh;
import com.google.android.gms.internal.zzuh.zzf;
import com.google.android.gms.internal.zzuh.zzg;
import com.google.android.gms.measurement.AppMeasurement$zza;
import com.google.android.gms.measurement.AppMeasurement$zzd;
import com.google.android.gms.measurement.AppMeasurement$zze;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

class zzc extends zzaa {
    zzc(zzx com_google_android_gms_measurement_internal_zzx) {
        super(com_google_android_gms_measurement_internal_zzx);
    }

    private Boolean zza(zzb com_google_android_gms_internal_zzuf_zzb, zzuh.zzb com_google_android_gms_internal_zzuh_zzb, long j) {
        Boolean zzbk;
        if (com_google_android_gms_internal_zzuf_zzb.amH != null) {
            zzbk = new zzs(com_google_android_gms_internal_zzuf_zzb.amH).zzbk(j);
            if (zzbk == null) {
                return null;
            }
            if (!zzbk.booleanValue()) {
                return Boolean.valueOf(false);
            }
        }
        Set hashSet = new HashSet();
        for (com.google.android.gms.internal.zzuf.zzc com_google_android_gms_internal_zzuf_zzc : com_google_android_gms_internal_zzuf_zzb.amF) {
            if (TextUtils.isEmpty(com_google_android_gms_internal_zzuf_zzc.amM)) {
                zzbsd().zzbsx().zzj("null or empty param name in filter. event", com_google_android_gms_internal_zzuh_zzb.name);
                return null;
            }
            hashSet.add(com_google_android_gms_internal_zzuf_zzc.amM);
        }
        Map arrayMap = new ArrayMap();
        for (com.google.android.gms.internal.zzuh.zzc com_google_android_gms_internal_zzuh_zzc : com_google_android_gms_internal_zzuh_zzb.ann) {
            if (hashSet.contains(com_google_android_gms_internal_zzuh_zzc.name)) {
                if (com_google_android_gms_internal_zzuh_zzc.anr != null) {
                    arrayMap.put(com_google_android_gms_internal_zzuh_zzc.name, com_google_android_gms_internal_zzuh_zzc.anr);
                } else if (com_google_android_gms_internal_zzuh_zzc.amw != null) {
                    arrayMap.put(com_google_android_gms_internal_zzuh_zzc.name, com_google_android_gms_internal_zzuh_zzc.amw);
                } else if (com_google_android_gms_internal_zzuh_zzc.zD != null) {
                    arrayMap.put(com_google_android_gms_internal_zzuh_zzc.name, com_google_android_gms_internal_zzuh_zzc.zD);
                } else {
                    zzbsd().zzbsx().zze("Unknown value for param. event, param", com_google_android_gms_internal_zzuh_zzb.name, com_google_android_gms_internal_zzuh_zzc.name);
                    return null;
                }
            }
        }
        for (com.google.android.gms.internal.zzuf.zzc com_google_android_gms_internal_zzuf_zzc2 : com_google_android_gms_internal_zzuf_zzb.amF) {
            boolean equals = Boolean.TRUE.equals(com_google_android_gms_internal_zzuf_zzc2.amL);
            CharSequence charSequence = com_google_android_gms_internal_zzuf_zzc2.amM;
            if (TextUtils.isEmpty(charSequence)) {
                zzbsd().zzbsx().zzj("Event has empty param name. event", com_google_android_gms_internal_zzuh_zzb.name);
                return null;
            }
            Object obj = arrayMap.get(charSequence);
            if (obj instanceof Long) {
                if (com_google_android_gms_internal_zzuf_zzc2.amK == null) {
                    zzbsd().zzbsx().zze("No number filter for long param. event, param", com_google_android_gms_internal_zzuh_zzb.name, charSequence);
                    return null;
                }
                zzbk = new zzs(com_google_android_gms_internal_zzuf_zzc2.amK).zzbk(((Long) obj).longValue());
                if (zzbk == null) {
                    return null;
                }
                if (((!zzbk.booleanValue() ? 1 : 0) ^ equals) != 0) {
                    return Boolean.valueOf(false);
                }
            } else if (obj instanceof Double) {
                if (com_google_android_gms_internal_zzuf_zzc2.amK == null) {
                    zzbsd().zzbsx().zze("No number filter for double param. event, param", com_google_android_gms_internal_zzuh_zzb.name, charSequence);
                    return null;
                }
                zzbk = new zzs(com_google_android_gms_internal_zzuf_zzc2.amK).zzj(((Double) obj).doubleValue());
                if (zzbk == null) {
                    return null;
                }
                if (((!zzbk.booleanValue() ? 1 : 0) ^ equals) != 0) {
                    return Boolean.valueOf(false);
                }
            } else if (obj instanceof String) {
                if (com_google_android_gms_internal_zzuf_zzc2.amJ == null) {
                    zzbsd().zzbsx().zze("No string filter for String param. event, param", com_google_android_gms_internal_zzuh_zzb.name, charSequence);
                    return null;
                }
                zzbk = new zzag(com_google_android_gms_internal_zzuf_zzc2.amJ).zzmi((String) obj);
                if (zzbk == null) {
                    return null;
                }
                if (((!zzbk.booleanValue() ? 1 : 0) ^ equals) != 0) {
                    return Boolean.valueOf(false);
                }
            } else if (obj == null) {
                zzbsd().zzbtc().zze("Missing param for filter. event, param", com_google_android_gms_internal_zzuh_zzb.name, charSequence);
                return Boolean.valueOf(false);
            } else {
                zzbsd().zzbsx().zze("Unknown param type. event, param", com_google_android_gms_internal_zzuh_zzb.name, charSequence);
                return null;
            }
        }
        return Boolean.valueOf(true);
    }

    private Boolean zza(zze com_google_android_gms_internal_zzuf_zze, zzg com_google_android_gms_internal_zzuh_zzg) {
        Boolean bool = null;
        com.google.android.gms.internal.zzuf.zzc com_google_android_gms_internal_zzuf_zzc = com_google_android_gms_internal_zzuf_zze.amU;
        if (com_google_android_gms_internal_zzuf_zzc == null) {
            zzbsd().zzbsx().zzj("Missing property filter. property", com_google_android_gms_internal_zzuh_zzg.name);
            return bool;
        }
        boolean equals = Boolean.TRUE.equals(com_google_android_gms_internal_zzuf_zzc.amL);
        if (com_google_android_gms_internal_zzuh_zzg.anr != null) {
            if (com_google_android_gms_internal_zzuf_zzc.amK != null) {
                return zza(new zzs(com_google_android_gms_internal_zzuf_zzc.amK).zzbk(com_google_android_gms_internal_zzuh_zzg.anr.longValue()), equals);
            }
            zzbsd().zzbsx().zzj("No number filter for long property. property", com_google_android_gms_internal_zzuh_zzg.name);
            return bool;
        } else if (com_google_android_gms_internal_zzuh_zzg.amw != null) {
            if (com_google_android_gms_internal_zzuf_zzc.amK != null) {
                return zza(new zzs(com_google_android_gms_internal_zzuf_zzc.amK).zzj(com_google_android_gms_internal_zzuh_zzg.amw.doubleValue()), equals);
            }
            zzbsd().zzbsx().zzj("No number filter for double property. property", com_google_android_gms_internal_zzuh_zzg.name);
            return bool;
        } else if (com_google_android_gms_internal_zzuh_zzg.zD == null) {
            zzbsd().zzbsx().zzj("User property has no value, property", com_google_android_gms_internal_zzuh_zzg.name);
            return bool;
        } else if (com_google_android_gms_internal_zzuf_zzc.amJ != null) {
            return zza(new zzag(com_google_android_gms_internal_zzuf_zzc.amJ).zzmi(com_google_android_gms_internal_zzuh_zzg.zD), equals);
        } else {
            if (com_google_android_gms_internal_zzuf_zzc.amK == null) {
                zzbsd().zzbsx().zzj("No string or number filter defined. property", com_google_android_gms_internal_zzuh_zzg.name);
                return bool;
            }
            zzs com_google_android_gms_measurement_internal_zzs = new zzs(com_google_android_gms_internal_zzuf_zzc.amK);
            if (com_google_android_gms_internal_zzuf_zzc.amK.amO == null || !com_google_android_gms_internal_zzuf_zzc.amK.amO.booleanValue()) {
                if (zzld(com_google_android_gms_internal_zzuh_zzg.zD)) {
                    try {
                        return zza(com_google_android_gms_measurement_internal_zzs.zzbk(Long.parseLong(com_google_android_gms_internal_zzuh_zzg.zD)), equals);
                    } catch (NumberFormatException e) {
                        zzbsd().zzbsx().zze("User property value exceeded Long value range. property, value", com_google_android_gms_internal_zzuh_zzg.name, com_google_android_gms_internal_zzuh_zzg.zD);
                        return bool;
                    }
                }
                zzbsd().zzbsx().zze("Invalid user property value for Long number filter. property, value", com_google_android_gms_internal_zzuh_zzg.name, com_google_android_gms_internal_zzuh_zzg.zD);
                return bool;
            } else if (zzle(com_google_android_gms_internal_zzuh_zzg.zD)) {
                try {
                    double parseDouble = Double.parseDouble(com_google_android_gms_internal_zzuh_zzg.zD);
                    if (!Double.isInfinite(parseDouble)) {
                        return zza(com_google_android_gms_measurement_internal_zzs.zzj(parseDouble), equals);
                    }
                    zzbsd().zzbsx().zze("User property value exceeded Double value range. property, value", com_google_android_gms_internal_zzuh_zzg.name, com_google_android_gms_internal_zzuh_zzg.zD);
                    return bool;
                } catch (NumberFormatException e2) {
                    zzbsd().zzbsx().zze("User property value exceeded Double value range. property, value", com_google_android_gms_internal_zzuh_zzg.name, com_google_android_gms_internal_zzuh_zzg.zD);
                    return bool;
                }
            } else {
                zzbsd().zzbsx().zze("Invalid user property value for Double number filter. property, value", com_google_android_gms_internal_zzuh_zzg.name, com_google_android_gms_internal_zzuh_zzg.zD);
                return bool;
            }
        }
    }

    static Boolean zza(Boolean bool, boolean z) {
        return bool == null ? null : Boolean.valueOf(bool.booleanValue() ^ z);
    }

    @WorkerThread
    void zza(String str, zza[] com_google_android_gms_internal_zzuf_zzaArr) {
        zzab.zzy(com_google_android_gms_internal_zzuf_zzaArr);
        for (zza com_google_android_gms_internal_zzuf_zza : com_google_android_gms_internal_zzuf_zzaArr) {
            for (zzb com_google_android_gms_internal_zzuf_zzb : com_google_android_gms_internal_zzuf_zza.amB) {
                String str2 = (String) AppMeasurement$zza.ahE.get(com_google_android_gms_internal_zzuf_zzb.amE);
                if (str2 != null) {
                    com_google_android_gms_internal_zzuf_zzb.amE = str2;
                }
                for (com.google.android.gms.internal.zzuf.zzc com_google_android_gms_internal_zzuf_zzc : com_google_android_gms_internal_zzuf_zzb.amF) {
                    str2 = (String) AppMeasurement$zzd.ahF.get(com_google_android_gms_internal_zzuf_zzc.amM);
                    if (str2 != null) {
                        com_google_android_gms_internal_zzuf_zzc.amM = str2;
                    }
                }
            }
            for (zze com_google_android_gms_internal_zzuf_zze : com_google_android_gms_internal_zzuf_zza.amA) {
                str2 = (String) AppMeasurement$zze.ahG.get(com_google_android_gms_internal_zzuf_zze.amT);
                if (str2 != null) {
                    com_google_android_gms_internal_zzuf_zze.amT = str2;
                }
            }
        }
        zzbry().zzb(str, com_google_android_gms_internal_zzuf_zzaArr);
    }

    @WorkerThread
    zzuh.zza[] zza(String str, zzuh.zzb[] com_google_android_gms_internal_zzuh_zzbArr, zzg[] com_google_android_gms_internal_zzuh_zzgArr) {
        int intValue;
        BitSet bitSet;
        BitSet bitSet2;
        Map map;
        Map map2;
        Boolean zza;
        Object obj;
        zzab.zzhr(str);
        Set hashSet = new HashSet();
        ArrayMap arrayMap = new ArrayMap();
        Map arrayMap2 = new ArrayMap();
        ArrayMap arrayMap3 = new ArrayMap();
        Map zzlr = zzbry().zzlr(str);
        if (zzlr != null) {
            for (Integer intValue2 : zzlr.keySet()) {
                intValue = intValue2.intValue();
                zzf com_google_android_gms_internal_zzuh_zzf = (zzf) zzlr.get(Integer.valueOf(intValue));
                bitSet = (BitSet) arrayMap2.get(Integer.valueOf(intValue));
                bitSet2 = (BitSet) arrayMap3.get(Integer.valueOf(intValue));
                if (bitSet == null) {
                    bitSet = new BitSet();
                    arrayMap2.put(Integer.valueOf(intValue), bitSet);
                    bitSet2 = new BitSet();
                    arrayMap3.put(Integer.valueOf(intValue), bitSet2);
                }
                for (int i = 0; i < com_google_android_gms_internal_zzuh_zzf.anT.length * 64; i++) {
                    if (zzal.zza(com_google_android_gms_internal_zzuh_zzf.anT, i)) {
                        zzbsd().zzbtc().zze("Filter already evaluated. audience ID, filter ID", Integer.valueOf(intValue), Integer.valueOf(i));
                        bitSet2.set(i);
                        if (zzal.zza(com_google_android_gms_internal_zzuh_zzf.anU, i)) {
                            bitSet.set(i);
                        }
                    }
                }
                zzuh.zza com_google_android_gms_internal_zzuh_zza = new zzuh.zza();
                arrayMap.put(Integer.valueOf(intValue), com_google_android_gms_internal_zzuh_zza);
                com_google_android_gms_internal_zzuh_zza.anl = Boolean.valueOf(false);
                com_google_android_gms_internal_zzuh_zza.ank = com_google_android_gms_internal_zzuh_zzf;
                com_google_android_gms_internal_zzuh_zza.anj = new zzf();
                com_google_android_gms_internal_zzuh_zza.anj.anU = zzal.zza(bitSet);
                com_google_android_gms_internal_zzuh_zza.anj.anT = zzal.zza(bitSet2);
            }
        }
        if (com_google_android_gms_internal_zzuh_zzbArr != null) {
            ArrayMap arrayMap4 = new ArrayMap();
            for (zzuh.zzb com_google_android_gms_internal_zzuh_zzb : com_google_android_gms_internal_zzuh_zzbArr) {
                zzi com_google_android_gms_measurement_internal_zzi;
                zzi zzaq = zzbry().zzaq(str, com_google_android_gms_internal_zzuh_zzb.name);
                if (zzaq == null) {
                    zzbsd().zzbsx().zzj("Event aggregate wasn't created during raw event logging. event", com_google_android_gms_internal_zzuh_zzb.name);
                    com_google_android_gms_measurement_internal_zzi = new zzi(str, com_google_android_gms_internal_zzuh_zzb.name, 1, 1, com_google_android_gms_internal_zzuh_zzb.ano.longValue());
                } else {
                    com_google_android_gms_measurement_internal_zzi = zzaq.zzbsr();
                }
                zzbry().zza(com_google_android_gms_measurement_internal_zzi);
                long j = com_google_android_gms_measurement_internal_zzi.aiC;
                map = (Map) arrayMap4.get(com_google_android_gms_internal_zzuh_zzb.name);
                if (map == null) {
                    map = zzbry().zzat(str, com_google_android_gms_internal_zzuh_zzb.name);
                    if (map == null) {
                        map = new ArrayMap();
                    }
                    arrayMap4.put(com_google_android_gms_internal_zzuh_zzb.name, map);
                    map2 = map;
                } else {
                    map2 = map;
                }
                zzbsd().zzbtc().zze("event, affected audience count", com_google_android_gms_internal_zzuh_zzb.name, Integer.valueOf(map2.size()));
                for (Integer intValue22 : map2.keySet()) {
                    int intValue3 = intValue22.intValue();
                    if (hashSet.contains(Integer.valueOf(intValue3))) {
                        zzbsd().zzbtc().zzj("Skipping failed audience ID", Integer.valueOf(intValue3));
                    } else {
                        bitSet = (BitSet) arrayMap2.get(Integer.valueOf(intValue3));
                        bitSet2 = (BitSet) arrayMap3.get(Integer.valueOf(intValue3));
                        if (((zzuh.zza) arrayMap.get(Integer.valueOf(intValue3))) == null) {
                            zzuh.zza com_google_android_gms_internal_zzuh_zza2 = new zzuh.zza();
                            arrayMap.put(Integer.valueOf(intValue3), com_google_android_gms_internal_zzuh_zza2);
                            com_google_android_gms_internal_zzuh_zza2.anl = Boolean.valueOf(true);
                            bitSet = new BitSet();
                            arrayMap2.put(Integer.valueOf(intValue3), bitSet);
                            bitSet2 = new BitSet();
                            arrayMap3.put(Integer.valueOf(intValue3), bitSet2);
                        }
                        for (zzb com_google_android_gms_internal_zzuf_zzb : (List) map2.get(Integer.valueOf(intValue3))) {
                            if (zzbsd().zzaz(2)) {
                                zzbsd().zzbtc().zzd("Evaluating filter. audience, filter, event", Integer.valueOf(intValue3), com_google_android_gms_internal_zzuf_zzb.amD, com_google_android_gms_internal_zzuf_zzb.amE);
                                zzbsd().zzbtc().zzj("Filter definition", zzal.zza(com_google_android_gms_internal_zzuf_zzb));
                            }
                            if (com_google_android_gms_internal_zzuf_zzb.amD == null || com_google_android_gms_internal_zzuf_zzb.amD.intValue() > 256) {
                                zzbsd().zzbsx().zzj("Invalid event filter ID. id", String.valueOf(com_google_android_gms_internal_zzuf_zzb.amD));
                            } else if (bitSet.get(com_google_android_gms_internal_zzuf_zzb.amD.intValue())) {
                                zzbsd().zzbtc().zze("Event filter already evaluated true. audience ID, filter ID", Integer.valueOf(intValue3), com_google_android_gms_internal_zzuf_zzb.amD);
                            } else {
                                zza = zza(com_google_android_gms_internal_zzuf_zzb, com_google_android_gms_internal_zzuh_zzb, j);
                                zzp.zza zzbtc = zzbsd().zzbtc();
                                String str2 = "Event filter result";
                                if (zza == null) {
                                    obj = Constants.NULL_VERSION_ID;
                                } else {
                                    Boolean bool = zza;
                                }
                                zzbtc.zzj(str2, obj);
                                if (zza == null) {
                                    hashSet.add(Integer.valueOf(intValue3));
                                } else {
                                    bitSet2.set(com_google_android_gms_internal_zzuf_zzb.amD.intValue());
                                    if (zza.booleanValue()) {
                                        bitSet.set(com_google_android_gms_internal_zzuf_zzb.amD.intValue());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (com_google_android_gms_internal_zzuh_zzgArr != null) {
            Map arrayMap5 = new ArrayMap();
            for (zzg com_google_android_gms_internal_zzuh_zzg : com_google_android_gms_internal_zzuh_zzgArr) {
                map = (Map) arrayMap5.get(com_google_android_gms_internal_zzuh_zzg.name);
                if (map == null) {
                    map = zzbry().zzau(str, com_google_android_gms_internal_zzuh_zzg.name);
                    if (map == null) {
                        map = new ArrayMap();
                    }
                    arrayMap5.put(com_google_android_gms_internal_zzuh_zzg.name, map);
                    map2 = map;
                } else {
                    map2 = map;
                }
                zzbsd().zzbtc().zze("property, affected audience count", com_google_android_gms_internal_zzuh_zzg.name, Integer.valueOf(map2.size()));
                for (Integer intValue222 : map2.keySet()) {
                    int intValue4 = intValue222.intValue();
                    if (hashSet.contains(Integer.valueOf(intValue4))) {
                        zzbsd().zzbtc().zzj("Skipping failed audience ID", Integer.valueOf(intValue4));
                    } else {
                        bitSet = (BitSet) arrayMap2.get(Integer.valueOf(intValue4));
                        bitSet2 = (BitSet) arrayMap3.get(Integer.valueOf(intValue4));
                        if (((zzuh.zza) arrayMap.get(Integer.valueOf(intValue4))) == null) {
                            com_google_android_gms_internal_zzuh_zza2 = new zzuh.zza();
                            arrayMap.put(Integer.valueOf(intValue4), com_google_android_gms_internal_zzuh_zza2);
                            com_google_android_gms_internal_zzuh_zza2.anl = Boolean.valueOf(true);
                            bitSet = new BitSet();
                            arrayMap2.put(Integer.valueOf(intValue4), bitSet);
                            bitSet2 = new BitSet();
                            arrayMap3.put(Integer.valueOf(intValue4), bitSet2);
                        }
                        for (zze com_google_android_gms_internal_zzuf_zze : (List) map2.get(Integer.valueOf(intValue4))) {
                            if (zzbsd().zzaz(2)) {
                                zzbsd().zzbtc().zzd("Evaluating filter. audience, filter, property", Integer.valueOf(intValue4), com_google_android_gms_internal_zzuf_zze.amD, com_google_android_gms_internal_zzuf_zze.amT);
                                zzbsd().zzbtc().zzj("Filter definition", zzal.zza(com_google_android_gms_internal_zzuf_zze));
                            }
                            if (com_google_android_gms_internal_zzuf_zze.amD == null || com_google_android_gms_internal_zzuf_zze.amD.intValue() > 256) {
                                zzbsd().zzbsx().zzj("Invalid property filter ID. id", String.valueOf(com_google_android_gms_internal_zzuf_zze.amD));
                                hashSet.add(Integer.valueOf(intValue4));
                                break;
                            } else if (bitSet.get(com_google_android_gms_internal_zzuf_zze.amD.intValue())) {
                                zzbsd().zzbtc().zze("Property filter already evaluated true. audience ID, filter ID", Integer.valueOf(intValue4), com_google_android_gms_internal_zzuf_zze.amD);
                            } else {
                                zza = zza(com_google_android_gms_internal_zzuf_zze, com_google_android_gms_internal_zzuh_zzg);
                                zzp.zza zzbtc2 = zzbsd().zzbtc();
                                String str3 = "Property filter result";
                                if (zza == null) {
                                    obj = Constants.NULL_VERSION_ID;
                                } else {
                                    bool = zza;
                                }
                                zzbtc2.zzj(str3, obj);
                                if (zza == null) {
                                    hashSet.add(Integer.valueOf(intValue4));
                                } else {
                                    bitSet2.set(com_google_android_gms_internal_zzuf_zze.amD.intValue());
                                    if (zza.booleanValue()) {
                                        bitSet.set(com_google_android_gms_internal_zzuf_zze.amD.intValue());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        zzuh.zza[] com_google_android_gms_internal_zzuh_zzaArr = new zzuh.zza[arrayMap2.size()];
        int i2 = 0;
        for (Integer intValue2222 : arrayMap2.keySet()) {
            intValue = intValue2222.intValue();
            if (!hashSet.contains(Integer.valueOf(intValue))) {
                com_google_android_gms_internal_zzuh_zza2 = (zzuh.zza) arrayMap.get(Integer.valueOf(intValue));
                com_google_android_gms_internal_zzuh_zza = com_google_android_gms_internal_zzuh_zza2 == null ? new zzuh.zza() : com_google_android_gms_internal_zzuh_zza2;
                int i3 = i2 + 1;
                com_google_android_gms_internal_zzuh_zzaArr[i2] = com_google_android_gms_internal_zzuh_zza;
                com_google_android_gms_internal_zzuh_zza.amz = Integer.valueOf(intValue);
                com_google_android_gms_internal_zzuh_zza.anj = new zzf();
                com_google_android_gms_internal_zzuh_zza.anj.anU = zzal.zza((BitSet) arrayMap2.get(Integer.valueOf(intValue)));
                com_google_android_gms_internal_zzuh_zza.anj.anT = zzal.zza((BitSet) arrayMap3.get(Integer.valueOf(intValue)));
                zzbry().zza(str, intValue, com_google_android_gms_internal_zzuh_zza.anj);
                i2 = i3;
            }
        }
        return (zzuh.zza[]) Arrays.copyOf(com_google_android_gms_internal_zzuh_zzaArr, i2);
    }

    boolean zzld(String str) {
        return Pattern.matches("[+-]?[0-9]+", str);
    }

    boolean zzle(String str) {
        return Pattern.matches("[+-]?(([0-9]+\\.?)|([0-9]*\\.[0-9]+))", str);
    }

    protected void zzwv() {
    }
}

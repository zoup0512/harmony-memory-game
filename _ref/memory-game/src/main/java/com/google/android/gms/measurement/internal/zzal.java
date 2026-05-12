package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import bolts.MeasurementEvent;
import com.amazonaws.services.s3.internal.Constants;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.internal.zzapo;
import com.google.android.gms.internal.zzuf.zzb;
import com.google.android.gms.internal.zzuf.zzc;
import com.google.android.gms.internal.zzuf.zzd;
import com.google.android.gms.internal.zzuf.zze;
import com.google.android.gms.internal.zzuf.zzf;
import com.google.android.gms.internal.zzuh;
import com.google.android.gms.internal.zzuh.zza;
import com.google.android.gms.internal.zzuh.zzg;
import com.google.android.gms.measurement.AppMeasurement$zza;
import com.google.android.gms.measurement.AppMeasurement$zze;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.events.EventsFilesManager;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.BitSet;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class zzal extends zzz {
    zzal(zzx com_google_android_gms_measurement_internal_zzx) {
        super(com_google_android_gms_measurement_internal_zzx);
    }

    private Object zza(int i, Object obj, boolean z) {
        if (obj == null) {
            return null;
        }
        if ((obj instanceof Long) || (obj instanceof Double)) {
            return obj;
        }
        if (obj instanceof Integer) {
            return Long.valueOf((long) ((Integer) obj).intValue());
        }
        if (obj instanceof Byte) {
            return Long.valueOf((long) ((Byte) obj).byteValue());
        }
        if (obj instanceof Short) {
            return Long.valueOf((long) ((Short) obj).shortValue());
        }
        if (!(obj instanceof Boolean)) {
            return obj instanceof Float ? Double.valueOf(((Float) obj).doubleValue()) : ((obj instanceof String) || (obj instanceof Character) || (obj instanceof CharSequence)) ? zza(String.valueOf(obj), i, z) : null;
        } else {
            return Long.valueOf(((Boolean) obj).booleanValue() ? 1 : 0);
        }
    }

    public static String zza(zzb com_google_android_gms_internal_zzuf_zzb) {
        int i = 0;
        if (com_google_android_gms_internal_zzuf_zzb == null) {
            return Constants.NULL_VERSION_ID;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nevent_filter {\n");
        zza(stringBuilder, 0, "filter_id", com_google_android_gms_internal_zzuf_zzb.amD);
        zza(stringBuilder, 0, MeasurementEvent.MEASUREMENT_EVENT_NAME_KEY, com_google_android_gms_internal_zzuf_zzb.amE);
        zza(stringBuilder, 1, "event_count_filter", com_google_android_gms_internal_zzuf_zzb.amH);
        stringBuilder.append("  filters {\n");
        zzc[] com_google_android_gms_internal_zzuf_zzcArr = com_google_android_gms_internal_zzuf_zzb.amF;
        int length = com_google_android_gms_internal_zzuf_zzcArr.length;
        while (i < length) {
            zza(stringBuilder, 2, com_google_android_gms_internal_zzuf_zzcArr[i]);
            i++;
        }
        zza(stringBuilder, 1);
        stringBuilder.append("}\n}\n");
        return stringBuilder.toString();
    }

    public static String zza(zze com_google_android_gms_internal_zzuf_zze) {
        if (com_google_android_gms_internal_zzuf_zze == null) {
            return Constants.NULL_VERSION_ID;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nproperty_filter {\n");
        zza(stringBuilder, 0, "filter_id", com_google_android_gms_internal_zzuf_zze.amD);
        zza(stringBuilder, 0, "property_name", com_google_android_gms_internal_zzuf_zze.amT);
        zza(stringBuilder, 1, com_google_android_gms_internal_zzuf_zze.amU);
        stringBuilder.append("}\n");
        return stringBuilder.toString();
    }

    private static void zza(StringBuilder stringBuilder, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            stringBuilder.append("  ");
        }
    }

    private static void zza(StringBuilder stringBuilder, int i, zzc com_google_android_gms_internal_zzuf_zzc) {
        if (com_google_android_gms_internal_zzuf_zzc != null) {
            zza(stringBuilder, i);
            stringBuilder.append("filter {\n");
            zza(stringBuilder, i, "complement", com_google_android_gms_internal_zzuf_zzc.amL);
            zza(stringBuilder, i, "param_name", com_google_android_gms_internal_zzuf_zzc.amM);
            zza(stringBuilder, i + 1, "string_filter", com_google_android_gms_internal_zzuf_zzc.amJ);
            zza(stringBuilder, i + 1, "number_filter", com_google_android_gms_internal_zzuf_zzc.amK);
            zza(stringBuilder, i);
            stringBuilder.append("}\n");
        }
    }

    private static void zza(StringBuilder stringBuilder, int i, zzuh.zze com_google_android_gms_internal_zzuh_zze) {
        if (com_google_android_gms_internal_zzuh_zze != null) {
            zza(stringBuilder, i);
            stringBuilder.append("bundle {\n");
            zza(stringBuilder, i, "protocol_version", com_google_android_gms_internal_zzuh_zze.anu);
            zza(stringBuilder, i, "platform", com_google_android_gms_internal_zzuh_zze.anC);
            zza(stringBuilder, i, "gmp_version", com_google_android_gms_internal_zzuh_zze.anG);
            zza(stringBuilder, i, "uploading_gmp_version", com_google_android_gms_internal_zzuh_zze.anH);
            zza(stringBuilder, i, "gmp_app_id", com_google_android_gms_internal_zzuh_zze.aic);
            zza(stringBuilder, i, "app_id", com_google_android_gms_internal_zzuh_zze.zzck);
            zza(stringBuilder, i, "app_version", com_google_android_gms_internal_zzuh_zze.aav);
            zza(stringBuilder, i, "app_version_major", com_google_android_gms_internal_zzuh_zze.anP);
            zza(stringBuilder, i, "firebase_instance_id", com_google_android_gms_internal_zzuh_zze.aik);
            zza(stringBuilder, i, "dev_cert_hash", com_google_android_gms_internal_zzuh_zze.anL);
            zza(stringBuilder, i, "app_store", com_google_android_gms_internal_zzuh_zze.aid);
            zza(stringBuilder, i, "upload_timestamp_millis", com_google_android_gms_internal_zzuh_zze.anx);
            zza(stringBuilder, i, "start_timestamp_millis", com_google_android_gms_internal_zzuh_zze.any);
            zza(stringBuilder, i, "end_timestamp_millis", com_google_android_gms_internal_zzuh_zze.anz);
            zza(stringBuilder, i, "previous_bundle_start_timestamp_millis", com_google_android_gms_internal_zzuh_zze.anA);
            zza(stringBuilder, i, "previous_bundle_end_timestamp_millis", com_google_android_gms_internal_zzuh_zze.anB);
            zza(stringBuilder, i, "app_instance_id", com_google_android_gms_internal_zzuh_zze.anK);
            zza(stringBuilder, i, "resettable_device_id", com_google_android_gms_internal_zzuh_zze.anI);
            zza(stringBuilder, i, "device_id", com_google_android_gms_internal_zzuh_zze.anS);
            zza(stringBuilder, i, "limited_ad_tracking", com_google_android_gms_internal_zzuh_zze.anJ);
            zza(stringBuilder, i, "os_version", com_google_android_gms_internal_zzuh_zze.zzct);
            zza(stringBuilder, i, "device_model", com_google_android_gms_internal_zzuh_zze.anD);
            zza(stringBuilder, i, "user_default_language", com_google_android_gms_internal_zzuh_zze.anE);
            zza(stringBuilder, i, "time_zone_offset_minutes", com_google_android_gms_internal_zzuh_zze.anF);
            zza(stringBuilder, i, "bundle_sequential_index", com_google_android_gms_internal_zzuh_zze.anM);
            zza(stringBuilder, i, "service_upload", com_google_android_gms_internal_zzuh_zze.anN);
            zza(stringBuilder, i, "health_monitor", com_google_android_gms_internal_zzuh_zze.aig);
            zza(stringBuilder, i, com_google_android_gms_internal_zzuh_zze.anw);
            zza(stringBuilder, i, com_google_android_gms_internal_zzuh_zze.anO);
            zza(stringBuilder, i, com_google_android_gms_internal_zzuh_zze.anv);
            zza(stringBuilder, i);
            stringBuilder.append("}\n");
        }
    }

    private static void zza(StringBuilder stringBuilder, int i, String str, zzd com_google_android_gms_internal_zzuf_zzd) {
        if (com_google_android_gms_internal_zzuf_zzd != null) {
            zza(stringBuilder, i);
            stringBuilder.append(str);
            stringBuilder.append(" {\n");
            if (com_google_android_gms_internal_zzuf_zzd.amN != null) {
                Object obj = "UNKNOWN_COMPARISON_TYPE";
                switch (com_google_android_gms_internal_zzuf_zzd.amN.intValue()) {
                    case 1:
                        obj = "LESS_THAN";
                        break;
                    case 2:
                        obj = "GREATER_THAN";
                        break;
                    case 3:
                        obj = "EQUAL";
                        break;
                    case 4:
                        obj = "BETWEEN";
                        break;
                }
                zza(stringBuilder, i, "comparison_type", obj);
            }
            zza(stringBuilder, i, "match_as_float", com_google_android_gms_internal_zzuf_zzd.amO);
            zza(stringBuilder, i, "comparison_value", com_google_android_gms_internal_zzuf_zzd.amP);
            zza(stringBuilder, i, "min_comparison_value", com_google_android_gms_internal_zzuf_zzd.amQ);
            zza(stringBuilder, i, "max_comparison_value", com_google_android_gms_internal_zzuf_zzd.amR);
            zza(stringBuilder, i);
            stringBuilder.append("}\n");
        }
    }

    private static void zza(StringBuilder stringBuilder, int i, String str, zzf com_google_android_gms_internal_zzuf_zzf) {
        if (com_google_android_gms_internal_zzuf_zzf != null) {
            zza(stringBuilder, i);
            stringBuilder.append(str);
            stringBuilder.append(" {\n");
            if (com_google_android_gms_internal_zzuf_zzf.amV != null) {
                Object obj = "UNKNOWN_MATCH_TYPE";
                switch (com_google_android_gms_internal_zzuf_zzf.amV.intValue()) {
                    case 1:
                        obj = "REGEXP";
                        break;
                    case 2:
                        obj = "BEGINS_WITH";
                        break;
                    case 3:
                        obj = "ENDS_WITH";
                        break;
                    case 4:
                        obj = "PARTIAL";
                        break;
                    case 5:
                        obj = "EXACT";
                        break;
                    case 6:
                        obj = "IN_LIST";
                        break;
                }
                zza(stringBuilder, i, "match_type", obj);
            }
            zza(stringBuilder, i, "expression", com_google_android_gms_internal_zzuf_zzf.amW);
            zza(stringBuilder, i, "case_sensitive", com_google_android_gms_internal_zzuf_zzf.amX);
            if (com_google_android_gms_internal_zzuf_zzf.amY.length > 0) {
                zza(stringBuilder, i + 1);
                stringBuilder.append("expression_list {\n");
                for (String str2 : com_google_android_gms_internal_zzuf_zzf.amY) {
                    zza(stringBuilder, i + 2);
                    stringBuilder.append(str2);
                    stringBuilder.append("\n");
                }
                stringBuilder.append("}\n");
            }
            zza(stringBuilder, i);
            stringBuilder.append("}\n");
        }
    }

    private static void zza(StringBuilder stringBuilder, int i, String str, zzuh.zzf com_google_android_gms_internal_zzuh_zzf) {
        int i2 = 0;
        if (com_google_android_gms_internal_zzuh_zzf != null) {
            int i3;
            int i4;
            int i5 = i + 1;
            zza(stringBuilder, i5);
            stringBuilder.append(str);
            stringBuilder.append(" {\n");
            if (com_google_android_gms_internal_zzuh_zzf.anU != null) {
                zza(stringBuilder, i5 + 1);
                stringBuilder.append("results: ");
                long[] jArr = com_google_android_gms_internal_zzuh_zzf.anU;
                int length = jArr.length;
                i3 = 0;
                i4 = 0;
                while (i3 < length) {
                    Long valueOf = Long.valueOf(jArr[i3]);
                    int i6 = i4 + 1;
                    if (i4 != 0) {
                        stringBuilder.append(", ");
                    }
                    stringBuilder.append(valueOf);
                    i3++;
                    i4 = i6;
                }
                stringBuilder.append('\n');
            }
            if (com_google_android_gms_internal_zzuh_zzf.anT != null) {
                zza(stringBuilder, i5 + 1);
                stringBuilder.append("status: ");
                long[] jArr2 = com_google_android_gms_internal_zzuh_zzf.anT;
                int length2 = jArr2.length;
                i3 = 0;
                while (i2 < length2) {
                    Long valueOf2 = Long.valueOf(jArr2[i2]);
                    i4 = i3 + 1;
                    if (i3 != 0) {
                        stringBuilder.append(", ");
                    }
                    stringBuilder.append(valueOf2);
                    i2++;
                    i3 = i4;
                }
                stringBuilder.append('\n');
            }
            zza(stringBuilder, i5);
            stringBuilder.append("}\n");
        }
    }

    private static void zza(StringBuilder stringBuilder, int i, String str, Object obj) {
        if (obj != null) {
            zza(stringBuilder, i + 1);
            stringBuilder.append(str);
            stringBuilder.append(": ");
            stringBuilder.append(obj);
            stringBuilder.append('\n');
        }
    }

    private static void zza(StringBuilder stringBuilder, int i, zza[] com_google_android_gms_internal_zzuh_zzaArr) {
        if (com_google_android_gms_internal_zzuh_zzaArr != null) {
            int i2 = i + 1;
            for (zza com_google_android_gms_internal_zzuh_zza : com_google_android_gms_internal_zzuh_zzaArr) {
                if (com_google_android_gms_internal_zzuh_zza != null) {
                    zza(stringBuilder, i2);
                    stringBuilder.append("audience_membership {\n");
                    zza(stringBuilder, i2, "audience_id", com_google_android_gms_internal_zzuh_zza.amz);
                    zza(stringBuilder, i2, "new_audience", com_google_android_gms_internal_zzuh_zza.anl);
                    zza(stringBuilder, i2, "current_data", com_google_android_gms_internal_zzuh_zza.anj);
                    zza(stringBuilder, i2, "previous_data", com_google_android_gms_internal_zzuh_zza.ank);
                    zza(stringBuilder, i2);
                    stringBuilder.append("}\n");
                }
            }
        }
    }

    private static void zza(StringBuilder stringBuilder, int i, zzuh.zzb[] com_google_android_gms_internal_zzuh_zzbArr) {
        if (com_google_android_gms_internal_zzuh_zzbArr != null) {
            int i2 = i + 1;
            for (zzuh.zzb com_google_android_gms_internal_zzuh_zzb : com_google_android_gms_internal_zzuh_zzbArr) {
                if (com_google_android_gms_internal_zzuh_zzb != null) {
                    zza(stringBuilder, i2);
                    stringBuilder.append("event {\n");
                    zza(stringBuilder, i2, "name", com_google_android_gms_internal_zzuh_zzb.name);
                    zza(stringBuilder, i2, "timestamp_millis", com_google_android_gms_internal_zzuh_zzb.ano);
                    zza(stringBuilder, i2, "previous_timestamp_millis", com_google_android_gms_internal_zzuh_zzb.anp);
                    zza(stringBuilder, i2, "count", com_google_android_gms_internal_zzuh_zzb.count);
                    zza(stringBuilder, i2, com_google_android_gms_internal_zzuh_zzb.ann);
                    zza(stringBuilder, i2);
                    stringBuilder.append("}\n");
                }
            }
        }
    }

    private static void zza(StringBuilder stringBuilder, int i, zzuh.zzc[] com_google_android_gms_internal_zzuh_zzcArr) {
        if (com_google_android_gms_internal_zzuh_zzcArr != null) {
            int i2 = i + 1;
            for (zzuh.zzc com_google_android_gms_internal_zzuh_zzc : com_google_android_gms_internal_zzuh_zzcArr) {
                if (com_google_android_gms_internal_zzuh_zzc != null) {
                    zza(stringBuilder, i2);
                    stringBuilder.append("param {\n");
                    zza(stringBuilder, i2, "name", com_google_android_gms_internal_zzuh_zzc.name);
                    zza(stringBuilder, i2, "string_value", com_google_android_gms_internal_zzuh_zzc.zD);
                    zza(stringBuilder, i2, "int_value", com_google_android_gms_internal_zzuh_zzc.anr);
                    zza(stringBuilder, i2, "double_value", com_google_android_gms_internal_zzuh_zzc.amw);
                    zza(stringBuilder, i2);
                    stringBuilder.append("}\n");
                }
            }
        }
    }

    private static void zza(StringBuilder stringBuilder, int i, zzg[] com_google_android_gms_internal_zzuh_zzgArr) {
        if (com_google_android_gms_internal_zzuh_zzgArr != null) {
            int i2 = i + 1;
            for (zzg com_google_android_gms_internal_zzuh_zzg : com_google_android_gms_internal_zzuh_zzgArr) {
                if (com_google_android_gms_internal_zzuh_zzg != null) {
                    zza(stringBuilder, i2);
                    stringBuilder.append("user_property {\n");
                    zza(stringBuilder, i2, "set_timestamp_millis", com_google_android_gms_internal_zzuh_zzg.anW);
                    zza(stringBuilder, i2, "name", com_google_android_gms_internal_zzuh_zzg.name);
                    zza(stringBuilder, i2, "string_value", com_google_android_gms_internal_zzuh_zzg.zD);
                    zza(stringBuilder, i2, "int_value", com_google_android_gms_internal_zzuh_zzg.anr);
                    zza(stringBuilder, i2, "double_value", com_google_android_gms_internal_zzuh_zzg.amw);
                    zza(stringBuilder, i2);
                    stringBuilder.append("}\n");
                }
            }
        }
    }

    public static boolean zza(long[] jArr, int i) {
        return i < jArr.length * 64 && (jArr[i / 64] & (1 << (i % 64))) != 0;
    }

    public static long[] zza(BitSet bitSet) {
        int length = (bitSet.length() + 63) / 64;
        long[] jArr = new long[length];
        int i = 0;
        while (i < length) {
            jArr[i] = 0;
            int i2 = 0;
            while (i2 < 64 && (i * 64) + i2 < bitSet.length()) {
                if (bitSet.get((i * 64) + i2)) {
                    jArr[i] = jArr[i] | (1 << i2);
                }
                i2++;
            }
            i++;
        }
        return jArr;
    }

    public static boolean zzam(Bundle bundle) {
        return bundle.getLong("_c") == 1;
    }

    public static String zzb(zzuh.zzd com_google_android_gms_internal_zzuh_zzd) {
        if (com_google_android_gms_internal_zzuh_zzd == null) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\nbatch {\n");
        if (com_google_android_gms_internal_zzuh_zzd.ans != null) {
            for (zzuh.zze com_google_android_gms_internal_zzuh_zze : com_google_android_gms_internal_zzuh_zzd.ans) {
                if (com_google_android_gms_internal_zzuh_zze != null) {
                    zza(stringBuilder, 1, com_google_android_gms_internal_zzuh_zze);
                }
            }
        }
        stringBuilder.append("}\n");
        return stringBuilder.toString();
    }

    public static boolean zzb(Context context, String str, boolean z) {
        try {
            ActivityInfo receiverInfo = context.getPackageManager().getReceiverInfo(new ComponentName(context, str), 2);
            if (receiverInfo != null && receiverInfo.enabled && (!z || receiverInfo.exported)) {
                return true;
            }
        } catch (NameNotFoundException e) {
        }
        return false;
    }

    public static boolean zzbb(String str, String str2) {
        return (str == null && str2 == null) ? true : str == null ? false : str.equals(str2);
    }

    static MessageDigest zzfa(String str) {
        int i = 0;
        while (i < 2) {
            try {
                MessageDigest instance = MessageDigest.getInstance(str);
                if (instance != null) {
                    return instance;
                }
                i++;
            } catch (NoSuchAlgorithmException e) {
            }
        }
        return null;
    }

    public static boolean zzj(Context context, String str) {
        try {
            ServiceInfo serviceInfo = context.getPackageManager().getServiceInfo(new ComponentName(context, str), 4);
            if (serviceInfo != null && serviceInfo.enabled) {
                return true;
            }
        } catch (NameNotFoundException e) {
        }
        return false;
    }

    static boolean zzmj(String str) {
        zzab.zzhr(str);
        return str.charAt(0) != '_';
    }

    private int zzms(String str) {
        return "_ldl".equals(str) ? zzbsf().zzbqt() : zzbsf().zzbqs();
    }

    public static boolean zzmt(String str) {
        return !TextUtils.isEmpty(str) && str.startsWith(EventsFilesManager.ROLL_OVER_FILE_NAME_SEPARATOR);
    }

    static long zzx(byte[] bArr) {
        long j = null;
        zzab.zzy(bArr);
        zzab.zzbn(bArr.length > 0);
        long j2 = 0;
        int length = bArr.length - 1;
        while (length >= 0 && length >= bArr.length - 8) {
            j2 += (((long) bArr[length]) & 255) << j;
            j += 8;
            length--;
        }
        return j2;
    }

    public /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public Bundle zza(String str, Bundle bundle, @Nullable List<String> list, boolean z) {
        if (bundle == null) {
            return null;
        }
        Bundle bundle2 = new Bundle(bundle);
        int zzbqm = zzbsf().zzbqm();
        int i = 0;
        for (String str2 : bundle.keySet()) {
            int zzmo;
            if (list == null || !list.contains(str2)) {
                zzmo = z ? zzmo(str2) : 0;
                if (zzmo == 0) {
                    zzmo = zzmp(str2);
                }
            } else {
                zzmo = 0;
            }
            if (zzmo != 0) {
                if (zzd(bundle2, zzmo)) {
                    bundle2.putString("_ev", zza(str2, zzbsf().zzbqp(), true));
                }
                bundle2.remove(str2);
            } else if (zzk(str2, bundle.get(str2)) || "_ev".equals(str2)) {
                if (zzmj(str2)) {
                    i++;
                    if (i > zzbqm) {
                        zzbsd().zzbsv().zze("Event can't contain more then " + zzbqm + " params", str, bundle);
                        zzd(bundle2, 5);
                        bundle2.remove(str2);
                    }
                }
                i = i;
            } else {
                if (zzd(bundle2, 4)) {
                    bundle2.putString("_ev", zza(str2, zzbsf().zzbqp(), true));
                }
                bundle2.remove(str2);
            }
        }
        return bundle2;
    }

    public String zza(String str, int i, boolean z) {
        return str.length() > i ? z ? String.valueOf(str.substring(0, i)).concat("...") : null : str;
    }

    public void zza(Bundle bundle, String str, Object obj) {
        if (bundle != null) {
            if (obj instanceof Long) {
                bundle.putLong(str, ((Long) obj).longValue());
            } else if (obj instanceof String) {
                bundle.putString(str, String.valueOf(obj));
            } else if (obj instanceof Double) {
                bundle.putDouble(str, ((Double) obj).doubleValue());
            } else if (str != null) {
                zzbsd().zzbsz().zze("Not putting event parameter. Invalid value type. name, type", str, obj != null ? obj.getClass().getSimpleName() : null);
            }
        }
    }

    public void zza(zzuh.zzc com_google_android_gms_internal_zzuh_zzc, Object obj) {
        zzab.zzy(obj);
        com_google_android_gms_internal_zzuh_zzc.zD = null;
        com_google_android_gms_internal_zzuh_zzc.anr = null;
        com_google_android_gms_internal_zzuh_zzc.amw = null;
        if (obj instanceof String) {
            com_google_android_gms_internal_zzuh_zzc.zD = (String) obj;
        } else if (obj instanceof Long) {
            com_google_android_gms_internal_zzuh_zzc.anr = (Long) obj;
        } else if (obj instanceof Double) {
            com_google_android_gms_internal_zzuh_zzc.amw = (Double) obj;
        } else {
            zzbsd().zzbsv().zzj("Ignoring invalid (type) event param value", obj);
        }
    }

    public void zza(zzg com_google_android_gms_internal_zzuh_zzg, Object obj) {
        zzab.zzy(obj);
        com_google_android_gms_internal_zzuh_zzg.zD = null;
        com_google_android_gms_internal_zzuh_zzg.anr = null;
        com_google_android_gms_internal_zzuh_zzg.amw = null;
        if (obj instanceof String) {
            com_google_android_gms_internal_zzuh_zzg.zD = (String) obj;
        } else if (obj instanceof Long) {
            com_google_android_gms_internal_zzuh_zzg.anr = (Long) obj;
        } else if (obj instanceof Double) {
            com_google_android_gms_internal_zzuh_zzg.amw = (Double) obj;
        } else {
            zzbsd().zzbsv().zzj("Ignoring invalid (type) user attribute value", obj);
        }
    }

    boolean zza(String str, String str2, int i, Object obj) {
        if (obj == null || (obj instanceof Long) || (obj instanceof Float) || (obj instanceof Integer) || (obj instanceof Byte) || (obj instanceof Short) || (obj instanceof Boolean) || (obj instanceof Double)) {
            return true;
        }
        if (!(obj instanceof String) && !(obj instanceof Character) && !(obj instanceof CharSequence)) {
            return false;
        }
        String valueOf = String.valueOf(obj);
        if (valueOf.length() <= i) {
            return true;
        }
        zzbsd().zzbsx().zzd("Value is too long; discarded. Value kind, name, value length", str, str2, Integer.valueOf(valueOf.length()));
        return false;
    }

    public byte[] zza(zzuh.zzd com_google_android_gms_internal_zzuh_zzd) {
        try {
            byte[] bArr = new byte[com_google_android_gms_internal_zzuh_zzd.aM()];
            zzapo zzbe = zzapo.zzbe(bArr);
            com_google_android_gms_internal_zzuh_zzd.zza(zzbe);
            zzbe.az();
            return bArr;
        } catch (IOException e) {
            zzbsd().zzbsv().zzj("Data loss. Failed to serialize batch", e);
            return null;
        }
    }

    boolean zzaz(String str, String str2) {
        if (str2 == null) {
            zzbsd().zzbsv().zzj("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.length() == 0) {
            zzbsd().zzbsv().zzj("Name is required and can't be empty. Type", str);
            return false;
        } else if (Character.isLetter(str2.charAt(0))) {
            int i = 1;
            while (i < str2.length()) {
                char charAt = str2.charAt(i);
                if (charAt == '_' || Character.isLetterOrDigit(charAt)) {
                    i++;
                } else {
                    zzbsd().zzbsv().zze("Name must consist of letters, digits or _ (underscores). Type, name", str, str2);
                    return false;
                }
            }
            return true;
        } else {
            zzbsd().zzbsv().zze("Name must start with a letter. Type, name", str, str2);
            return false;
        }
    }

    boolean zzba(String str, String str2) {
        if (str2 == null) {
            zzbsd().zzbsv().zzj("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.length() == 0) {
            zzbsd().zzbsv().zzj("Name is required and can't be empty. Type", str);
            return false;
        } else {
            char charAt = str2.charAt(0);
            if (Character.isLetter(charAt) || charAt == '_') {
                int i = 1;
                while (i < str2.length()) {
                    char charAt2 = str2.charAt(i);
                    if (charAt2 == '_' || Character.isLetterOrDigit(charAt2)) {
                        i++;
                    } else {
                        zzbsd().zzbsv().zze("Name must start with a letter or _ (underscores). Type, name", str, str2);
                        return false;
                    }
                }
                return true;
            }
            zzbsd().zzbsv().zze("Name must start with a letter or _ (underscores). Type, name", str, str2);
            return false;
        }
    }

    public /* bridge */ /* synthetic */ void zzbrs() {
        super.zzbrs();
    }

    public /* bridge */ /* synthetic */ zzc zzbrt() {
        return super.zzbrt();
    }

    public /* bridge */ /* synthetic */ zzac zzbru() {
        return super.zzbru();
    }

    public /* bridge */ /* synthetic */ zzn zzbrv() {
        return super.zzbrv();
    }

    public /* bridge */ /* synthetic */ zzg zzbrw() {
        return super.zzbrw();
    }

    public /* bridge */ /* synthetic */ zzad zzbrx() {
        return super.zzbrx();
    }

    public /* bridge */ /* synthetic */ zze zzbry() {
        return super.zzbry();
    }

    public /* bridge */ /* synthetic */ zzal zzbrz() {
        return super.zzbrz();
    }

    public /* bridge */ /* synthetic */ zzv zzbsa() {
        return super.zzbsa();
    }

    public /* bridge */ /* synthetic */ zzaf zzbsb() {
        return super.zzbsb();
    }

    public /* bridge */ /* synthetic */ zzw zzbsc() {
        return super.zzbsc();
    }

    public /* bridge */ /* synthetic */ zzp zzbsd() {
        return super.zzbsd();
    }

    public /* bridge */ /* synthetic */ zzt zzbse() {
        return super.zzbse();
    }

    public /* bridge */ /* synthetic */ zzd zzbsf() {
        return super.zzbsf();
    }

    boolean zzc(String str, int i, String str2) {
        if (str2 == null) {
            zzbsd().zzbsv().zzj("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.length() <= i) {
            return true;
        } else {
            zzbsd().zzbsv().zzd("Name is too long. Type, maximum supported length, name", str, Integer.valueOf(i), str2);
            return false;
        }
    }

    boolean zzc(String str, Map<String, String> map, String str2) {
        if (str2 == null) {
            zzbsd().zzbsv().zzj("Name is required and can't be null. Type", str);
            return false;
        } else if (str2.startsWith("firebase_")) {
            zzbsd().zzbsv().zze("Name starts with reserved prefix. Type, name", str, str2);
            return false;
        } else if (map == null || !map.containsKey(str2)) {
            return true;
        } else {
            zzbsd().zzbsv().zze("Name is reserved. Type, name", str, str2);
            return false;
        }
    }

    public boolean zzd(Bundle bundle, int i) {
        if (bundle == null || bundle.getLong("_err") != 0) {
            return false;
        }
        bundle.putLong("_err", (long) i);
        return true;
    }

    public void zze(int i, String str, String str2) {
        Bundle bundle = new Bundle();
        zzd(bundle, i);
        if (!TextUtils.isEmpty(str)) {
            bundle.putString(str, str2);
        }
        this.ahD.zzbru().zze("auto", "_err", bundle);
    }

    @WorkerThread
    public boolean zzeo(String str) {
        zzwu();
        if (getContext().checkCallingOrSelfPermission(str) == 0) {
            return true;
        }
        zzbsd().zzbtb().zzj("Permission not granted", str);
        return false;
    }

    public boolean zzg(long j, long j2) {
        return j == 0 || j2 <= 0 || Math.abs(zzyw().currentTimeMillis() - j) > j2;
    }

    public byte[] zzj(byte[] bArr) throws IOException {
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(bArr);
            gZIPOutputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            zzbsd().zzbsv().zzj("Failed to gzip content", e);
            throw e;
        }
    }

    public boolean zzk(String str, Object obj) {
        return zzmt(str) ? zza("param", str, zzbsf().zzbqr(), obj) : zza("param", str, zzbsf().zzbqq(), obj);
    }

    public Object zzl(String str, Object obj) {
        if ("_ev".equals(str)) {
            return zza(zzbsf().zzbqr(), obj, true);
        }
        return zza(zzmt(str) ? zzbsf().zzbqr() : zzbsf().zzbqq(), obj, false);
    }

    public int zzm(String str, Object obj) {
        return "_ldl".equals(str) ? zza("user property referrer", str, zzms(str), obj) : zza("user property", str, zzms(str), obj) ? 0 : 7;
    }

    public int zzmk(String str) {
        return !zzaz("event", str) ? 2 : !zzc("event", AppMeasurement$zza.ahE, str) ? 13 : zzc("event", zzbsf().zzbqn(), str) ? 0 : 2;
    }

    public int zzml(String str) {
        return !zzba("event", str) ? 2 : !zzc("event", AppMeasurement$zza.ahE, str) ? 13 : zzc("event", zzbsf().zzbqn(), str) ? 0 : 2;
    }

    public int zzmm(String str) {
        return !zzaz("user property", str) ? 6 : !zzc("user property", AppMeasurement$zze.ahG, str) ? 15 : zzc("user property", zzbsf().zzbqo(), str) ? 0 : 6;
    }

    public int zzmn(String str) {
        return !zzba("user property", str) ? 6 : !zzc("user property", AppMeasurement$zze.ahG, str) ? 15 : zzc("user property", zzbsf().zzbqo(), str) ? 0 : 6;
    }

    public int zzmo(String str) {
        return !zzaz("event param", str) ? 3 : !zzc("event param", null, str) ? 14 : zzc("event param", zzbsf().zzbqp(), str) ? 0 : 3;
    }

    public int zzmp(String str) {
        return !zzba("event param", str) ? 3 : !zzc("event param", null, str) ? 14 : zzc("event param", zzbsf().zzbqp(), str) ? 0 : 3;
    }

    public boolean zzmq(String str) {
        if (TextUtils.isEmpty(str)) {
            zzbsd().zzbsv().log("Measurement Service called without google_app_id");
            return false;
        } else if (!str.startsWith("1:")) {
            zzbsd().zzbsx().zzj("Measurement Service called with unknown id version", str);
            return true;
        } else if (zzmr(str)) {
            return true;
        } else {
            zzbsd().zzbsv().zzj("Invalid google_app_id. Firebase Analytics disabled. See", "https://goo.gl/FZRIUV");
            return false;
        }
    }

    boolean zzmr(String str) {
        zzab.zzy(str);
        return str.matches("^1:\\d+:android:[a-f0-9]+$");
    }

    public Object zzn(String str, Object obj) {
        return "_ldl".equals(str) ? zza(zzms(str), obj, true) : zza(zzms(str), obj, false);
    }

    public byte[] zzw(byte[] bArr) throws IOException {
        try {
            InputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            byte[] bArr2 = new byte[1024];
            while (true) {
                int read = gZIPInputStream.read(bArr2);
                if (read <= 0) {
                    gZIPInputStream.close();
                    byteArrayInputStream.close();
                    return byteArrayOutputStream.toByteArray();
                }
                byteArrayOutputStream.write(bArr2, 0, read);
            }
        } catch (IOException e) {
            zzbsd().zzbsv().zzj("Failed to ungzip content", e);
            throw e;
        }
    }

    public /* bridge */ /* synthetic */ void zzwu() {
        super.zzwu();
    }

    public long zzy(byte[] bArr) {
        zzab.zzy(bArr);
        MessageDigest zzfa = zzfa(CommonUtils.MD5_INSTANCE);
        if (zzfa != null) {
            return zzx(zzfa.digest(bArr));
        }
        zzbsd().zzbsv().log("Failed to get MD5");
        return 0;
    }

    public /* bridge */ /* synthetic */ void zzyv() {
        super.zzyv();
    }

    public /* bridge */ /* synthetic */ com.google.android.gms.common.util.zze zzyw() {
        return super.zzyw();
    }
}

package com.google.android.gms.internal;

import android.content.ContentResolver;
import android.content.Context;
import android.util.Log;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzab;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public class zzpg implements com.google.android.gms.clearcut.zzb.zzb {
    private static final Charset UTF_8 = Charset.forName("UTF-8");
    static Boolean qU = null;
    final zza qV;

    static class zza {
        final ContentResolver mContentResolver;

        zza(Context context) {
            if (context == null || !zzbm(context)) {
                this.mContentResolver = null;
                return;
            }
            this.mContentResolver = context.getContentResolver();
            zzaeo.zzb(this.mContentResolver, "gms:playlog:service:sampling_");
        }

        private static boolean zzbm(Context context) {
            if (zzpg.qU == null) {
                zzpg.qU = Boolean.valueOf(context.checkCallingOrSelfPermission("com.google.android.providers.gsf.permission.READ_GSERVICES") == 0);
            }
            return zzpg.qU.booleanValue();
        }

        long zzane() {
            return this.mContentResolver == null ? 0 : zzaeo.getLong(this.mContentResolver, "android_id", 0);
        }

        String zzgu(String str) {
            if (this.mContentResolver == null) {
                return null;
            }
            ContentResolver contentResolver = this.mContentResolver;
            String valueOf = String.valueOf("gms:playlog:service:sampling_");
            String valueOf2 = String.valueOf(str);
            return zzaeo.zza(contentResolver, valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf), null);
        }
    }

    static class zzb {
        public final String qW;
        public final long qX;
        public final long qY;

        public zzb(String str, long j, long j2) {
            this.qW = str;
            this.qX = j;
            this.qY = j2;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzb)) {
                return false;
            }
            zzb com_google_android_gms_internal_zzpg_zzb = (zzb) obj;
            return zzaa.equal(this.qW, com_google_android_gms_internal_zzpg_zzb.qW) && zzaa.equal(Long.valueOf(this.qX), Long.valueOf(com_google_android_gms_internal_zzpg_zzb.qX)) && zzaa.equal(Long.valueOf(this.qY), Long.valueOf(com_google_android_gms_internal_zzpg_zzb.qY));
        }

        public int hashCode() {
            return zzaa.hashCode(this.qW, Long.valueOf(this.qX), Long.valueOf(this.qY));
        }
    }

    public zzpg() {
        this(new zza(null));
    }

    public zzpg(Context context) {
        this(new zza(context));
    }

    zzpg(zza com_google_android_gms_internal_zzpg_zza) {
        this.qV = (zza) zzab.zzy(com_google_android_gms_internal_zzpg_zza);
    }

    static boolean zza(long j, long j2, long j3) {
        if (j2 >= 0 && j3 >= 0) {
            return j3 > 0 && zzph.zzd(j, j3) < j2;
        } else {
            throw new IllegalArgumentException("negative values not supported: " + j2 + "/" + j3);
        }
    }

    static long zzag(long j) {
        return zzpd.zzm(ByteBuffer.allocate(8).putLong(j).array());
    }

    static long zzd(String str, long j) {
        if (str == null || str.isEmpty()) {
            return zzag(j);
        }
        byte[] bytes = str.getBytes(UTF_8);
        ByteBuffer allocate = ByteBuffer.allocate(bytes.length + 8);
        allocate.put(bytes);
        allocate.putLong(j);
        return zzpd.zzm(allocate.array());
    }

    static zzb zzgt(String str) {
        int i = 0;
        if (str == null) {
            return null;
        }
        String str2 = "";
        int indexOf = str.indexOf(44);
        if (indexOf >= 0) {
            str2 = str.substring(0, indexOf);
            i = indexOf + 1;
        }
        int indexOf2 = str.indexOf(47, i);
        if (indexOf2 <= 0) {
            str2 = "LogSamplerImpl";
            String str3 = "Failed to parse the rule: ";
            String valueOf = String.valueOf(str);
            Log.e(str2, valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            return null;
        }
        try {
            long parseLong = Long.parseLong(str.substring(i, indexOf2));
            long parseLong2 = Long.parseLong(str.substring(indexOf2 + 1));
            if (parseLong >= 0 && parseLong2 >= 0) {
                return new zzb(str2, parseLong, parseLong2);
            }
            Log.e("LogSamplerImpl", "negative values not supported: " + parseLong + "/" + parseLong2);
            return null;
        } catch (Throwable e) {
            Throwable th = e;
            str3 = "LogSamplerImpl";
            String str4 = "parseLong() failed while parsing: ";
            valueOf = String.valueOf(str);
            Log.e(str3, valueOf.length() != 0 ? str4.concat(valueOf) : new String(str4), th);
            return null;
        }
    }

    public boolean zzg(String str, int i) {
        if (str == null || str.isEmpty()) {
            str = i >= 0 ? String.valueOf(i) : null;
        }
        if (str == null) {
            return true;
        }
        long zzane = this.qV.zzane();
        zzb zzgt = zzgt(this.qV.zzgu(str));
        return zzgt != null ? zza(zzd(zzgt.qW, zzane), zzgt.qX, zzgt.qY) : true;
    }
}

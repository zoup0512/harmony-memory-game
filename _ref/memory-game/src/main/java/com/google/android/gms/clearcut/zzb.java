package com.google.android.gms.clearcut;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Looper;
import android.util.Log;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResults;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzg;
import com.google.android.gms.common.util.zzh;
import com.google.android.gms.internal.zzpb;
import com.google.android.gms.internal.zzpc;
import com.google.android.gms.internal.zzpg;
import com.google.android.gms.playlog.internal.PlayLoggerContext;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TimeZone;

public final class zzb {
    public static final Api<NoOptions> API = new Api("ClearcutLogger.API", bK, bJ);
    public static final zzf<zzpc> bJ = new zzf();
    public static final com.google.android.gms.common.api.Api.zza<zzpc, NoOptions> bK = new com.google.android.gms.common.api.Api.zza<zzpc, NoOptions>() {
        public /* synthetic */ zze zza(Context context, Looper looper, zzg com_google_android_gms_common_internal_zzg, Object obj, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return zze(context, looper, com_google_android_gms_common_internal_zzg, (NoOptions) obj, connectionCallbacks, onConnectionFailedListener);
        }

        public zzpc zze(Context context, Looper looper, zzg com_google_android_gms_common_internal_zzg, NoOptions noOptions, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return new zzpc(context, looper, com_google_android_gms_common_internal_zzg, connectionCallbacks, onConnectionFailedListener);
        }
    };
    public static final zzc pZ = new zzpb();
    private final String aM;
    private final Context mContext;
    private final int qa;
    private String qb;
    private int qc;
    private String qd;
    private String qe;
    private final boolean qf;
    private int qg;
    private final zzc qh;
    private final zza qi;
    private zzd qj;
    private final zzb qk;
    private final com.google.android.gms.common.util.zze zzaoc;

    public class zza {
        private String qb;
        private int qc;
        private String qd;
        private String qe;
        private int qg;
        private final zzc ql;
        private ArrayList<Integer> qm;
        private ArrayList<String> qn;
        private ArrayList<Integer> qo;
        private ArrayList<byte[]> qp;
        private boolean qq;
        private final com.google.android.gms.internal.zzapz.zzd qr;
        private boolean qs;
        final /* synthetic */ zzb qt;

        private zza(zzb com_google_android_gms_clearcut_zzb, byte[] bArr) {
            this(com_google_android_gms_clearcut_zzb, bArr, null);
        }

        private zza(zzb com_google_android_gms_clearcut_zzb, byte[] bArr, zzc com_google_android_gms_clearcut_zzb_zzc) {
            this.qt = com_google_android_gms_clearcut_zzb;
            this.qc = this.qt.qc;
            this.qb = this.qt.qb;
            this.qd = this.qt.qd;
            this.qe = this.qt.qe;
            this.qg = 0;
            this.qm = null;
            this.qn = null;
            this.qo = null;
            this.qp = null;
            this.qq = true;
            this.qr = new com.google.android.gms.internal.zzapz.zzd();
            this.qs = false;
            this.qd = com_google_android_gms_clearcut_zzb.qd;
            this.qe = com_google_android_gms_clearcut_zzb.qe;
            this.qr.bka = com_google_android_gms_clearcut_zzb.zzaoc.currentTimeMillis();
            this.qr.bkb = com_google_android_gms_clearcut_zzb.zzaoc.elapsedRealtime();
            this.qr.bks = (long) com_google_android_gms_clearcut_zzb.qi.zzbk(com_google_android_gms_clearcut_zzb.mContext);
            this.qr.bkm = com_google_android_gms_clearcut_zzb.qj.zzae(this.qr.bka);
            if (bArr != null) {
                this.qr.bkh = bArr;
            }
            this.ql = com_google_android_gms_clearcut_zzb_zzc;
        }

        public LogEventParcelable zzana() {
            return new LogEventParcelable(new PlayLoggerContext(this.qt.aM, this.qt.qa, this.qc, this.qb, this.qd, this.qe, this.qt.qf, this.qg), this.qr, this.ql, null, zzb.zzb(null), zzb.zzc(null), zzb.zzb(null), zzb.zzd(null), this.qq);
        }

        public PendingResult<Status> zze(GoogleApiClient googleApiClient) {
            if (this.qs) {
                throw new IllegalStateException("do not reuse LogEventBuilder");
            }
            this.qs = true;
            PlayLoggerContext playLoggerContext = zzana().qu;
            return this.qt.qk.zzg(playLoggerContext.arv, playLoggerContext.arr) ? this.qt.qh.zza(googleApiClient, zzana()) : PendingResults.immediatePendingResult(Status.sq);
        }

        public zza zzey(int i) {
            this.qr.bkd = i;
            return this;
        }

        public zza zzez(int i) {
            this.qr.zzahl = i;
            return this;
        }
    }

    public interface zzb {
        boolean zzg(String str, int i);
    }

    public interface zzc {
        byte[] zzanb();
    }

    public static class zzd {
        public long zzae(long j) {
            return (long) (TimeZone.getDefault().getOffset(j) / 1000);
        }
    }

    public zzb(Context context, int i, String str, String str2, String str3, boolean z, zzc com_google_android_gms_clearcut_zzc, com.google.android.gms.common.util.zze com_google_android_gms_common_util_zze, zzd com_google_android_gms_clearcut_zzb_zzd, zza com_google_android_gms_clearcut_zza, zzb com_google_android_gms_clearcut_zzb_zzb) {
        this.qc = -1;
        this.qg = 0;
        Context applicationContext = context.getApplicationContext();
        if (applicationContext == null) {
            applicationContext = context;
        }
        this.mContext = applicationContext;
        this.aM = context.getPackageName();
        this.qa = zzbl(context);
        this.qc = i;
        this.qb = str;
        this.qd = str2;
        this.qe = str3;
        this.qf = z;
        this.qh = com_google_android_gms_clearcut_zzc;
        this.zzaoc = com_google_android_gms_common_util_zze;
        if (com_google_android_gms_clearcut_zzb_zzd == null) {
            com_google_android_gms_clearcut_zzb_zzd = new zzd();
        }
        this.qj = com_google_android_gms_clearcut_zzb_zzd;
        this.qi = com_google_android_gms_clearcut_zza;
        this.qg = 0;
        this.qk = com_google_android_gms_clearcut_zzb_zzb;
        if (this.qf) {
            zzab.zzb(this.qd == null, (Object) "can't be anonymous with an upload account");
        }
    }

    public zzb(Context context, String str, String str2) {
        this(context, -1, str, str2, null, false, pZ, zzh.zzavm(), null, zza.pY, new zzpg(context));
    }

    private static int[] zzb(ArrayList<Integer> arrayList) {
        if (arrayList == null) {
            return null;
        }
        int[] iArr = new int[arrayList.size()];
        Iterator it = arrayList.iterator();
        int i = 0;
        while (it.hasNext()) {
            int i2 = i + 1;
            iArr[i] = ((Integer) it.next()).intValue();
            i = i2;
        }
        return iArr;
    }

    private int zzbl(Context context) {
        int i = 0;
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            Log.wtf("ClearcutLogger", "This can't happen.");
            return i;
        }
    }

    private static String[] zzc(ArrayList<String> arrayList) {
        return arrayList == null ? null : (String[]) arrayList.toArray(new String[0]);
    }

    private static byte[][] zzd(ArrayList<byte[]> arrayList) {
        return arrayList == null ? null : (byte[][]) arrayList.toArray(new byte[0][]);
    }

    public zza zzl(byte[] bArr) {
        return new zza(bArr);
    }
}

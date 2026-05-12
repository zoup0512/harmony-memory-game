package com.google.android.gms.tagmanager;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.util.zzh;
import com.google.android.gms.internal.zzadv;
import com.google.android.gms.internal.zzah.zzj;
import com.google.android.gms.internal.zzpo;

public class zzp extends zzpo<ContainerHolder> {
    private final String auF;
    private long auK;
    private final TagManager auR;
    private final zzd auU;
    private final zzck auV;
    private final int auW;
    private zzf auX;
    private zzadv auY;
    private volatile zzo auZ;
    private volatile boolean ava;
    private zzj avb;
    private String avc;
    private zze avd;
    private zza ave;
    private final Context mContext;
    private final Looper zzahv;
    private final com.google.android.gms.common.util.zze zzaoc;

    interface zze extends Releasable {
        void zza(zzbm<zzj> com_google_android_gms_tagmanager_zzbm_com_google_android_gms_internal_zzah_zzj);

        void zzf(long j, String str);

        void zzol(String str);
    }

    interface zzf extends Releasable {
        void zza(zzbm<com.google.android.gms.internal.zzadu.zza> com_google_android_gms_tagmanager_zzbm_com_google_android_gms_internal_zzadu_zza);

        void zzb(com.google.android.gms.internal.zzadu.zza com_google_android_gms_internal_zzadu_zza);

        void zzcav();

        com.google.android.gms.internal.zzadw.zzc zzze(int i);
    }

    class AnonymousClass1 {
    }

    interface zza {
        boolean zzb(Container container);
    }

    private class zzb implements zzbm<com.google.android.gms.internal.zzadu.zza> {
        final /* synthetic */ zzp avf;

        private zzb(zzp com_google_android_gms_tagmanager_zzp) {
            this.avf = com_google_android_gms_tagmanager_zzp;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            zza((com.google.android.gms.internal.zzadu.zza) obj);
        }

        public void zza(com.google.android.gms.internal.zzadu.zza com_google_android_gms_internal_zzadu_zza) {
            zzj com_google_android_gms_internal_zzah_zzj;
            if (com_google_android_gms_internal_zzadu_zza.aCW != null) {
                com_google_android_gms_internal_zzah_zzj = com_google_android_gms_internal_zzadu_zza.aCW;
            } else {
                com.google.android.gms.internal.zzah.zzf com_google_android_gms_internal_zzah_zzf = com_google_android_gms_internal_zzadu_zza.zzwr;
                com_google_android_gms_internal_zzah_zzj = new zzj();
                com_google_android_gms_internal_zzah_zzj.zzwr = com_google_android_gms_internal_zzah_zzf;
                com_google_android_gms_internal_zzah_zzj.zzwq = null;
                com_google_android_gms_internal_zzah_zzj.zzws = com_google_android_gms_internal_zzah_zzf.version;
            }
            this.avf.zza(com_google_android_gms_internal_zzah_zzj, com_google_android_gms_internal_zzadu_zza.aCV, true);
        }

        public void zza(com.google.android.gms.tagmanager.zzbm.zza com_google_android_gms_tagmanager_zzbm_zza) {
            if (!this.avf.ava) {
                this.avf.zzbs(0);
            }
        }

        public void zzcau() {
        }
    }

    private class zzc implements zzbm<zzj> {
        final /* synthetic */ zzp avf;

        private zzc(zzp com_google_android_gms_tagmanager_zzp) {
            this.avf = com_google_android_gms_tagmanager_zzp;
        }

        public /* synthetic */ void onSuccess(Object obj) {
            zzb((zzj) obj);
        }

        public void zza(com.google.android.gms.tagmanager.zzbm.zza com_google_android_gms_tagmanager_zzbm_zza) {
            synchronized (this.avf) {
                if (!this.avf.isReady()) {
                    if (this.avf.auZ != null) {
                        this.avf.zzc(this.avf.auZ);
                    } else {
                        this.avf.zzc(this.avf.zzec(Status.st));
                    }
                }
            }
            this.avf.zzbs(3600000);
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void zzb(com.google.android.gms.internal.zzah.zzj r6) {
            /*
            r5 = this;
            r1 = r5.avf;
            monitor-enter(r1);
            r0 = r6.zzwr;	 Catch:{ all -> 0x0067 }
            if (r0 != 0) goto L_0x002a;
        L_0x0007:
            r0 = r5.avf;	 Catch:{ all -> 0x0067 }
            r0 = r0.avb;	 Catch:{ all -> 0x0067 }
            r0 = r0.zzwr;	 Catch:{ all -> 0x0067 }
            if (r0 != 0) goto L_0x0020;
        L_0x0011:
            r0 = "Current resource is null; network resource is also null";
            com.google.android.gms.tagmanager.zzbn.e(r0);	 Catch:{ all -> 0x0067 }
            r0 = r5.avf;	 Catch:{ all -> 0x0067 }
            r2 = 3600000; // 0x36ee80 float:5.044674E-39 double:1.7786363E-317;
            r0.zzbs(r2);	 Catch:{ all -> 0x0067 }
            monitor-exit(r1);	 Catch:{ all -> 0x0067 }
        L_0x001f:
            return;
        L_0x0020:
            r0 = r5.avf;	 Catch:{ all -> 0x0067 }
            r0 = r0.avb;	 Catch:{ all -> 0x0067 }
            r0 = r0.zzwr;	 Catch:{ all -> 0x0067 }
            r6.zzwr = r0;	 Catch:{ all -> 0x0067 }
        L_0x002a:
            r0 = r5.avf;	 Catch:{ all -> 0x0067 }
            r2 = r5.avf;	 Catch:{ all -> 0x0067 }
            r2 = r2.zzaoc;	 Catch:{ all -> 0x0067 }
            r2 = r2.currentTimeMillis();	 Catch:{ all -> 0x0067 }
            r4 = 0;
            r0.zza(r6, r2, r4);	 Catch:{ all -> 0x0067 }
            r0 = r5.avf;	 Catch:{ all -> 0x0067 }
            r2 = r0.auK;	 Catch:{ all -> 0x0067 }
            r0 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0067 }
            r4 = 58;
            r0.<init>(r4);	 Catch:{ all -> 0x0067 }
            r4 = "setting refresh time to current time: ";
            r0 = r0.append(r4);	 Catch:{ all -> 0x0067 }
            r0 = r0.append(r2);	 Catch:{ all -> 0x0067 }
            r0 = r0.toString();	 Catch:{ all -> 0x0067 }
            com.google.android.gms.tagmanager.zzbn.v(r0);	 Catch:{ all -> 0x0067 }
            r0 = r5.avf;	 Catch:{ all -> 0x0067 }
            r0 = r0.zzcat();	 Catch:{ all -> 0x0067 }
            if (r0 != 0) goto L_0x0065;
        L_0x0060:
            r0 = r5.avf;	 Catch:{ all -> 0x0067 }
            r0.zza(r6);	 Catch:{ all -> 0x0067 }
        L_0x0065:
            monitor-exit(r1);	 Catch:{ all -> 0x0067 }
            goto L_0x001f;
        L_0x0067:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0067 }
            throw r0;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzp.zzc.zzb(com.google.android.gms.internal.zzah$zzj):void");
        }

        public void zzcau() {
        }
    }

    private class zzd implements com.google.android.gms.tagmanager.zzo.zza {
        final /* synthetic */ zzp avf;

        private zzd(zzp com_google_android_gms_tagmanager_zzp) {
            this.avf = com_google_android_gms_tagmanager_zzp;
        }

        public String zzcan() {
            return this.avf.zzcan();
        }

        public void zzcap() {
            if (this.avf.auV.zzade()) {
                this.avf.zzbs(0);
            }
        }

        public void zzoi(String str) {
            this.avf.zzoi(str);
        }
    }

    zzp(Context context, TagManager tagManager, Looper looper, String str, int i, zzf com_google_android_gms_tagmanager_zzp_zzf, zze com_google_android_gms_tagmanager_zzp_zze, zzadv com_google_android_gms_internal_zzadv, com.google.android.gms.common.util.zze com_google_android_gms_common_util_zze, zzck com_google_android_gms_tagmanager_zzck) {
        super(looper == null ? Looper.getMainLooper() : looper);
        this.mContext = context;
        this.auR = tagManager;
        if (looper == null) {
            looper = Looper.getMainLooper();
        }
        this.zzahv = looper;
        this.auF = str;
        this.auW = i;
        this.auX = com_google_android_gms_tagmanager_zzp_zzf;
        this.avd = com_google_android_gms_tagmanager_zzp_zze;
        this.auY = com_google_android_gms_internal_zzadv;
        this.auU = new zzd();
        this.avb = new zzj();
        this.zzaoc = com_google_android_gms_common_util_zze;
        this.auV = com_google_android_gms_tagmanager_zzck;
        if (zzcat()) {
            zzoi(zzci.zzcci().zzcck());
        }
    }

    public zzp(Context context, TagManager tagManager, Looper looper, String str, int i, zzs com_google_android_gms_tagmanager_zzs) {
        this(context, tagManager, looper, str, i, new zzcu(context, str), new zzct(context, str, com_google_android_gms_tagmanager_zzs), new zzadv(context), zzh.zzavm(), new zzbl(30, 900000, 5000, "refreshing", zzh.zzavm()));
        this.auY.zzqi(com_google_android_gms_tagmanager_zzs.zzcaw());
    }

    private synchronized void zza(zzj com_google_android_gms_internal_zzah_zzj) {
        if (this.auX != null) {
            com.google.android.gms.internal.zzadu.zza com_google_android_gms_internal_zzadu_zza = new com.google.android.gms.internal.zzadu.zza();
            com_google_android_gms_internal_zzadu_zza.aCV = this.auK;
            com_google_android_gms_internal_zzadu_zza.zzwr = new com.google.android.gms.internal.zzah.zzf();
            com_google_android_gms_internal_zzadu_zza.aCW = com_google_android_gms_internal_zzah_zzj;
            this.auX.zzb(com_google_android_gms_internal_zzadu_zza);
        }
    }

    private synchronized void zza(zzj com_google_android_gms_internal_zzah_zzj, long j, boolean z) {
        Container container;
        if (z) {
            boolean z2 = this.ava;
        }
        if (!isReady() || this.auZ == null) {
            this.avb = com_google_android_gms_internal_zzah_zzj;
            this.auK = j;
            zzbs(Math.max(0, Math.min(43200000, (this.auK + 43200000) - this.zzaoc.currentTimeMillis())));
            container = new Container(this.mContext, this.auR.getDataLayer(), this.auF, j, com_google_android_gms_internal_zzah_zzj);
        } else {
            this.avb = com_google_android_gms_internal_zzah_zzj;
            this.auK = j;
            zzbs(Math.max(0, Math.min(43200000, (this.auK + 43200000) - this.zzaoc.currentTimeMillis())));
            container = new Container(this.mContext, this.auR.getDataLayer(), this.auF, j, com_google_android_gms_internal_zzah_zzj);
        }
        if (this.auZ == null) {
            this.auZ = new zzo(this.auR, this.zzahv, container, this.auU);
        } else {
            this.auZ.zza(container);
        }
        if (!isReady() && this.ave.zzb(container)) {
            zzc(this.auZ);
        }
    }

    private synchronized void zzbs(long j) {
        if (this.avd == null) {
            zzbn.zzcx("Refresh requested, but no network load scheduler.");
        } else {
            this.avd.zzf(j, this.avb.zzws);
        }
    }

    private boolean zzcat() {
        zzci zzcci = zzci.zzcci();
        return (zzcci.zzccj() == zza.CONTAINER || zzcci.zzccj() == zza.CONTAINER_DEBUG) && this.auF.equals(zzcci.getContainerId());
    }

    private void zzcg(final boolean z) {
        this.auX.zza(new zzb());
        this.avd.zza(new zzc());
        com.google.android.gms.internal.zzadw.zzc zzze = this.auX.zzze(this.auW);
        if (zzze != null) {
            this.auZ = new zzo(this.auR, this.zzahv, new Container(this.mContext, this.auR.getDataLayer(), this.auF, 0, zzze), this.auU);
        }
        this.ave = new zza(this) {
            final /* synthetic */ zzp avf;

            public boolean zzb(Container container) {
                return z ? container.getLastRefreshTime() + 43200000 >= this.avf.zzaoc.currentTimeMillis() : !container.isDefault();
            }
        };
        if (zzcat()) {
            this.avd.zzf(0, "");
        } else {
            this.auX.zzcav();
        }
    }

    protected /* synthetic */ Result zzc(Status status) {
        return zzec(status);
    }

    synchronized String zzcan() {
        return this.avc;
    }

    public void zzcaq() {
        com.google.android.gms.internal.zzadw.zzc zzze = this.auX.zzze(this.auW);
        if (zzze != null) {
            zzc(new zzo(this.auR, this.zzahv, new Container(this.mContext, this.auR.getDataLayer(), this.auF, 0, zzze), new com.google.android.gms.tagmanager.zzo.zza(this) {
                final /* synthetic */ zzp avf;

                {
                    this.avf = r1;
                }

                public String zzcan() {
                    return this.avf.zzcan();
                }

                public void zzcap() {
                    zzbn.zzcx("Refresh ignored: container loaded as default only.");
                }

                public void zzoi(String str) {
                    this.avf.zzoi(str);
                }
            }));
        } else {
            String str = "Default was requested, but no default container was found";
            zzbn.e(str);
            zzc(zzec(new Status(10, str, null)));
        }
        this.avd = null;
        this.auX = null;
    }

    public void zzcar() {
        zzcg(false);
    }

    public void zzcas() {
        zzcg(true);
    }

    protected ContainerHolder zzec(Status status) {
        if (this.auZ != null) {
            return this.auZ;
        }
        if (status == Status.st) {
            zzbn.e("timer expired: setting result to failure");
        }
        return new zzo(status);
    }

    synchronized void zzoi(String str) {
        this.avc = str;
        if (this.avd != null) {
            this.avd.zzol(str);
        }
    }
}

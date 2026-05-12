package com.google.android.gms.internal;

import com.my.target.ads.MyTargetVideoView;
import java.io.IOException;

public interface zzae {

    public static final class zza extends zzapp<zza> {
        public String zzcs;
        public String zzct;
        public Long zzcu;
        public Long zzcv;
        public Long zzcw;
        public Long zzcx;
        public Long zzcy;
        public Long zzcz;
        public Long zzda;
        public Long zzdb;
        public Long zzdc;
        public Long zzdd;
        public String zzde;
        public Long zzdf;
        public Long zzdg;
        public Long zzdh;
        public Long zzdi;
        public Long zzdj;
        public Long zzdk;
        public Long zzdl;
        public Long zzdm;
        public Long zzdn;
        public String zzdo;
        public String zzdp;
        public Long zzdq;
        public Long zzdr;
        public Long zzds;
        public String zzdt;
        public Long zzdu;
        public Long zzdv;
        public Long zzdw;
        public zzb zzdx;
        public Long zzdy;
        public Long zzdz;
        public Long zzea;
        public Long zzeb;
        public Long zzec;
        public Long zzed;
        public zza[] zzee;
        public Long zzef;
        public String zzeg;
        public Integer zzeh;
        public Boolean zzei;
        public String zzej;
        public Long zzek;
        public zze zzel;

        public static final class zza extends zzapp<zza> {
            private static volatile zza[] zzem;
            public Long zzdf;
            public Long zzdg;

            public zza() {
                this.zzdf = null;
                this.zzdg = null;
                this.bjG = -1;
            }

            public static zza[] zzy() {
                if (zzem == null) {
                    synchronized (zzapt.bjF) {
                        if (zzem == null) {
                            zzem = new zza[0];
                        }
                    }
                }
                return zzem;
            }

            public void zza(zzapo com_google_android_gms_internal_zzapo) throws IOException {
                if (this.zzdf != null) {
                    com_google_android_gms_internal_zzapo.zzb(1, this.zzdf.longValue());
                }
                if (this.zzdg != null) {
                    com_google_android_gms_internal_zzapo.zzb(2, this.zzdg.longValue());
                }
                super.zza(com_google_android_gms_internal_zzapo);
            }

            public /* synthetic */ zzapv zzb(zzapn com_google_android_gms_internal_zzapn) throws IOException {
                return zzd(com_google_android_gms_internal_zzapn);
            }

            public zza zzd(zzapn com_google_android_gms_internal_zzapn) throws IOException {
                while (true) {
                    int ah = com_google_android_gms_internal_zzapn.ah();
                    switch (ah) {
                        case 0:
                            break;
                        case 8:
                            this.zzdf = Long.valueOf(com_google_android_gms_internal_zzapn.ak());
                            continue;
                        case 16:
                            this.zzdg = Long.valueOf(com_google_android_gms_internal_zzapn.ak());
                            continue;
                        default:
                            if (!super.zza(com_google_android_gms_internal_zzapn, ah)) {
                                break;
                            }
                            continue;
                    }
                    return this;
                }
            }

            protected int zzx() {
                int zzx = super.zzx();
                if (this.zzdf != null) {
                    zzx += zzapo.zze(1, this.zzdf.longValue());
                }
                return this.zzdg != null ? zzx + zzapo.zze(2, this.zzdg.longValue()) : zzx;
            }
        }

        public zza() {
            this.zzct = null;
            this.zzcs = null;
            this.zzcu = null;
            this.zzcv = null;
            this.zzcw = null;
            this.zzcx = null;
            this.zzcy = null;
            this.zzcz = null;
            this.zzda = null;
            this.zzdb = null;
            this.zzdc = null;
            this.zzdd = null;
            this.zzde = null;
            this.zzdf = null;
            this.zzdg = null;
            this.zzdh = null;
            this.zzdi = null;
            this.zzdj = null;
            this.zzdk = null;
            this.zzdl = null;
            this.zzdm = null;
            this.zzdn = null;
            this.zzdo = null;
            this.zzdp = null;
            this.zzdq = null;
            this.zzdr = null;
            this.zzds = null;
            this.zzdt = null;
            this.zzdu = null;
            this.zzdv = null;
            this.zzdw = null;
            this.zzdy = null;
            this.zzdz = null;
            this.zzea = null;
            this.zzeb = null;
            this.zzec = null;
            this.zzed = null;
            this.zzee = zza.zzy();
            this.zzef = null;
            this.zzeg = null;
            this.zzeh = null;
            this.zzei = null;
            this.zzej = null;
            this.zzek = null;
            this.bjG = -1;
        }

        public static zza zzc(byte[] bArr) throws zzapu {
            return (zza) zzapv.zza(new zza(), bArr);
        }

        public void zza(zzapo com_google_android_gms_internal_zzapo) throws IOException {
            if (this.zzct != null) {
                com_google_android_gms_internal_zzapo.zzr(1, this.zzct);
            }
            if (this.zzcs != null) {
                com_google_android_gms_internal_zzapo.zzr(2, this.zzcs);
            }
            if (this.zzcu != null) {
                com_google_android_gms_internal_zzapo.zzb(3, this.zzcu.longValue());
            }
            if (this.zzcv != null) {
                com_google_android_gms_internal_zzapo.zzb(4, this.zzcv.longValue());
            }
            if (this.zzcw != null) {
                com_google_android_gms_internal_zzapo.zzb(5, this.zzcw.longValue());
            }
            if (this.zzcx != null) {
                com_google_android_gms_internal_zzapo.zzb(6, this.zzcx.longValue());
            }
            if (this.zzcy != null) {
                com_google_android_gms_internal_zzapo.zzb(7, this.zzcy.longValue());
            }
            if (this.zzcz != null) {
                com_google_android_gms_internal_zzapo.zzb(8, this.zzcz.longValue());
            }
            if (this.zzda != null) {
                com_google_android_gms_internal_zzapo.zzb(9, this.zzda.longValue());
            }
            if (this.zzdb != null) {
                com_google_android_gms_internal_zzapo.zzb(10, this.zzdb.longValue());
            }
            if (this.zzdc != null) {
                com_google_android_gms_internal_zzapo.zzb(11, this.zzdc.longValue());
            }
            if (this.zzdd != null) {
                com_google_android_gms_internal_zzapo.zzb(12, this.zzdd.longValue());
            }
            if (this.zzde != null) {
                com_google_android_gms_internal_zzapo.zzr(13, this.zzde);
            }
            if (this.zzdf != null) {
                com_google_android_gms_internal_zzapo.zzb(14, this.zzdf.longValue());
            }
            if (this.zzdg != null) {
                com_google_android_gms_internal_zzapo.zzb(15, this.zzdg.longValue());
            }
            if (this.zzdh != null) {
                com_google_android_gms_internal_zzapo.zzb(16, this.zzdh.longValue());
            }
            if (this.zzdi != null) {
                com_google_android_gms_internal_zzapo.zzb(17, this.zzdi.longValue());
            }
            if (this.zzdj != null) {
                com_google_android_gms_internal_zzapo.zzb(18, this.zzdj.longValue());
            }
            if (this.zzdk != null) {
                com_google_android_gms_internal_zzapo.zzb(19, this.zzdk.longValue());
            }
            if (this.zzdl != null) {
                com_google_android_gms_internal_zzapo.zzb(20, this.zzdl.longValue());
            }
            if (this.zzef != null) {
                com_google_android_gms_internal_zzapo.zzb(21, this.zzef.longValue());
            }
            if (this.zzdm != null) {
                com_google_android_gms_internal_zzapo.zzb(22, this.zzdm.longValue());
            }
            if (this.zzdn != null) {
                com_google_android_gms_internal_zzapo.zzb(23, this.zzdn.longValue());
            }
            if (this.zzeg != null) {
                com_google_android_gms_internal_zzapo.zzr(24, this.zzeg);
            }
            if (this.zzek != null) {
                com_google_android_gms_internal_zzapo.zzb(25, this.zzek.longValue());
            }
            if (this.zzeh != null) {
                com_google_android_gms_internal_zzapo.zzae(26, this.zzeh.intValue());
            }
            if (this.zzdo != null) {
                com_google_android_gms_internal_zzapo.zzr(27, this.zzdo);
            }
            if (this.zzei != null) {
                com_google_android_gms_internal_zzapo.zzj(28, this.zzei.booleanValue());
            }
            if (this.zzdp != null) {
                com_google_android_gms_internal_zzapo.zzr(29, this.zzdp);
            }
            if (this.zzej != null) {
                com_google_android_gms_internal_zzapo.zzr(30, this.zzej);
            }
            if (this.zzdq != null) {
                com_google_android_gms_internal_zzapo.zzb(31, this.zzdq.longValue());
            }
            if (this.zzdr != null) {
                com_google_android_gms_internal_zzapo.zzb(32, this.zzdr.longValue());
            }
            if (this.zzds != null) {
                com_google_android_gms_internal_zzapo.zzb(33, this.zzds.longValue());
            }
            if (this.zzdt != null) {
                com_google_android_gms_internal_zzapo.zzr(34, this.zzdt);
            }
            if (this.zzdu != null) {
                com_google_android_gms_internal_zzapo.zzb(35, this.zzdu.longValue());
            }
            if (this.zzdv != null) {
                com_google_android_gms_internal_zzapo.zzb(36, this.zzdv.longValue());
            }
            if (this.zzdw != null) {
                com_google_android_gms_internal_zzapo.zzb(37, this.zzdw.longValue());
            }
            if (this.zzdx != null) {
                com_google_android_gms_internal_zzapo.zza(38, this.zzdx);
            }
            if (this.zzdy != null) {
                com_google_android_gms_internal_zzapo.zzb(39, this.zzdy.longValue());
            }
            if (this.zzdz != null) {
                com_google_android_gms_internal_zzapo.zzb(40, this.zzdz.longValue());
            }
            if (this.zzea != null) {
                com_google_android_gms_internal_zzapo.zzb(41, this.zzea.longValue());
            }
            if (this.zzeb != null) {
                com_google_android_gms_internal_zzapo.zzb(42, this.zzeb.longValue());
            }
            if (this.zzee != null && this.zzee.length > 0) {
                for (zzapv com_google_android_gms_internal_zzapv : this.zzee) {
                    if (com_google_android_gms_internal_zzapv != null) {
                        com_google_android_gms_internal_zzapo.zza(43, com_google_android_gms_internal_zzapv);
                    }
                }
            }
            if (this.zzec != null) {
                com_google_android_gms_internal_zzapo.zzb(44, this.zzec.longValue());
            }
            if (this.zzed != null) {
                com_google_android_gms_internal_zzapo.zzb(45, this.zzed.longValue());
            }
            if (this.zzel != null) {
                com_google_android_gms_internal_zzapo.zza(201, this.zzel);
            }
            super.zza(com_google_android_gms_internal_zzapo);
        }

        public /* synthetic */ zzapv zzb(zzapn com_google_android_gms_internal_zzapn) throws IOException {
            return zzc(com_google_android_gms_internal_zzapn);
        }

        public zza zzc(zzapn com_google_android_gms_internal_zzapn) throws IOException {
            while (true) {
                int ah = com_google_android_gms_internal_zzapn.ah();
                switch (ah) {
                    case 0:
                        break;
                    case 10:
                        this.zzct = com_google_android_gms_internal_zzapn.readString();
                        continue;
                    case 18:
                        this.zzcs = com_google_android_gms_internal_zzapn.readString();
                        continue;
                    case 24:
                        this.zzcu = Long.valueOf(com_google_android_gms_internal_zzapn.ak());
                        continue;
                    case 32:
                        this.zzcv = Long.valueOf(com_google_android_gms_internal_zzapn.ak());
                        continue;
                    case 40:
                        this.zzcw = Long.valueOf(com_google_android_gms_internal_zzapn.ak());
                        continue;
                    case 48:
                        this.zzcx = Long.valueOf(com_google_android_gms_internal_zzapn.ak());
                        continue;
                    case 56:
                        this.zzcy = Long.valueOf(com_google_android_gms_internal_zzapn.ak());
                        continue;
                    case 64:
                        this.zzcz = Long.valueOf(com_google_android_gms_internal_zzapn.ak());
                        continue;
                    case 72:
                        this.zzda = Long.valueOf(com_google_android_gms_internal_zzapn.ak());
                        continue;
                    case 80:
                        this.zzdb = Long.valueOf(com_google_android_gms_internal_zzapn.ak());
                        continue;
                    case 88:
                        this.zzdc = Long.valueOf(com_google_android_gms_internal_zzapn.ak());
                        continue;
                    case 96:
                        this.zzdd = Long.valueOf(com_google_android_gms_internal_zzapn.ak());
                        continue;
                    case 106:
                        this.zzde = com_google_android_gms_internal_zzapn.readString();
                        continue;
                    case 112:
                        this.zzdf = Long.valueOf(com_google_android_gms_internal_zzapn.ak());
                        continue;
                    case 120:
                        this.zzdg = Long.valueOf(com_google_android_gms_internal_zzapn.ak());
                        continue;
                    case 128:
                        this.zzdh = Long.valueOf(com_google_android_gms_internal_zzapn.ak());
                        continue;
                    case 136:
                        this.zzdi = Long.valueOf(com_google_android_gms_internal_zzapn.ak());
                        continue;
                    case 144:
                        this.zzdj = Long.valueOf(com_google_android_gms_internal_zzapn.ak());
                        continue;
                    case 152:
                        this.zzdk = Long.valueOf(com_google_android_gms_internal_zzapn.ak());
                        continue;
                    case 160:
                        this.zzdl = Long.valueOf(com_google_android_gms_internal_zzapn.ak());
                        continue;
                    case 168:
                        this.zzef = Long.valueOf(com_google_android_gms_internal_zzapn.ak());
                        continue;
                    case 176:
                        this.zzdm = Long.valueOf(com_google_android_gms_internal_zzapn.ak());
                        continue;
                    case 184:
                        this.zzdn = Long.valueOf(com_google_android_gms_internal_zzapn.ak());
                        continue;
                    case 194:
                        this.zzeg = com_google_android_gms_internal_zzapn.readString();
                        continue;
                    case 200:
                        this.zzek = Long.valueOf(com_google_android_gms_internal_zzapn.ak());
                        continue;
                    case 208:
                        ah = com_google_android_gms_internal_zzapn.al();
                        switch (ah) {
                            case 0:
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                                this.zzeh = Integer.valueOf(ah);
                                break;
                            default:
                                continue;
                        }
                    case 218:
                        this.zzdo = com_google_android_gms_internal_zzapn.readString();
                        continue;
                    case 224:
                        this.zzei = Boolean.valueOf(com_google_android_gms_internal_zzapn.an());
                        continue;
                    case 234:
                        this.zzdp = com_google_android_gms_internal_zzapn.readString();
                        continue;
                    case 242:
                        this.zzej = com_google_android_gms_internal_zzapn.readString();
                        continue;
                    case 248:
                        this.zzdq = Long.valueOf(com_google_android_gms_internal_zzapn.ak());
                        continue;
                    case 256:
                        this.zzdr = Long.valueOf(com_google_android_gms_internal_zzapn.ak());
                        continue;
                    case 264:
                        this.zzds = Long.valueOf(com_google_android_gms_internal_zzapn.ak());
                        continue;
                    case 274:
                        this.zzdt = com_google_android_gms_internal_zzapn.readString();
                        continue;
                    case 280:
                        this.zzdu = Long.valueOf(com_google_android_gms_internal_zzapn.ak());
                        continue;
                    case 288:
                        this.zzdv = Long.valueOf(com_google_android_gms_internal_zzapn.ak());
                        continue;
                    case 296:
                        this.zzdw = Long.valueOf(com_google_android_gms_internal_zzapn.ak());
                        continue;
                    case 306:
                        if (this.zzdx == null) {
                            this.zzdx = new zzb();
                        }
                        com_google_android_gms_internal_zzapn.zza(this.zzdx);
                        continue;
                    case 312:
                        this.zzdy = Long.valueOf(com_google_android_gms_internal_zzapn.ak());
                        continue;
                    case 320:
                        this.zzdz = Long.valueOf(com_google_android_gms_internal_zzapn.ak());
                        continue;
                    case 328:
                        this.zzea = Long.valueOf(com_google_android_gms_internal_zzapn.ak());
                        continue;
                    case 336:
                        this.zzeb = Long.valueOf(com_google_android_gms_internal_zzapn.ak());
                        continue;
                    case 346:
                        int zzc = zzapy.zzc(com_google_android_gms_internal_zzapn, 346);
                        ah = this.zzee == null ? 0 : this.zzee.length;
                        Object obj = new zza[(zzc + ah)];
                        if (ah != 0) {
                            System.arraycopy(this.zzee, 0, obj, 0, ah);
                        }
                        while (ah < obj.length - 1) {
                            obj[ah] = new zza();
                            com_google_android_gms_internal_zzapn.zza(obj[ah]);
                            com_google_android_gms_internal_zzapn.ah();
                            ah++;
                        }
                        obj[ah] = new zza();
                        com_google_android_gms_internal_zzapn.zza(obj[ah]);
                        this.zzee = obj;
                        continue;
                    case 352:
                        this.zzec = Long.valueOf(com_google_android_gms_internal_zzapn.ak());
                        continue;
                    case MyTargetVideoView.DEFAULT_VIDEO_QUALITY /*360*/:
                        this.zzed = Long.valueOf(com_google_android_gms_internal_zzapn.ak());
                        continue;
                    case 1610:
                        if (this.zzel == null) {
                            this.zzel = new zze();
                        }
                        com_google_android_gms_internal_zzapn.zza(this.zzel);
                        continue;
                    default:
                        if (!super.zza(com_google_android_gms_internal_zzapn, ah)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        protected int zzx() {
            int zzx = super.zzx();
            if (this.zzct != null) {
                zzx += zzapo.zzs(1, this.zzct);
            }
            if (this.zzcs != null) {
                zzx += zzapo.zzs(2, this.zzcs);
            }
            if (this.zzcu != null) {
                zzx += zzapo.zze(3, this.zzcu.longValue());
            }
            if (this.zzcv != null) {
                zzx += zzapo.zze(4, this.zzcv.longValue());
            }
            if (this.zzcw != null) {
                zzx += zzapo.zze(5, this.zzcw.longValue());
            }
            if (this.zzcx != null) {
                zzx += zzapo.zze(6, this.zzcx.longValue());
            }
            if (this.zzcy != null) {
                zzx += zzapo.zze(7, this.zzcy.longValue());
            }
            if (this.zzcz != null) {
                zzx += zzapo.zze(8, this.zzcz.longValue());
            }
            if (this.zzda != null) {
                zzx += zzapo.zze(9, this.zzda.longValue());
            }
            if (this.zzdb != null) {
                zzx += zzapo.zze(10, this.zzdb.longValue());
            }
            if (this.zzdc != null) {
                zzx += zzapo.zze(11, this.zzdc.longValue());
            }
            if (this.zzdd != null) {
                zzx += zzapo.zze(12, this.zzdd.longValue());
            }
            if (this.zzde != null) {
                zzx += zzapo.zzs(13, this.zzde);
            }
            if (this.zzdf != null) {
                zzx += zzapo.zze(14, this.zzdf.longValue());
            }
            if (this.zzdg != null) {
                zzx += zzapo.zze(15, this.zzdg.longValue());
            }
            if (this.zzdh != null) {
                zzx += zzapo.zze(16, this.zzdh.longValue());
            }
            if (this.zzdi != null) {
                zzx += zzapo.zze(17, this.zzdi.longValue());
            }
            if (this.zzdj != null) {
                zzx += zzapo.zze(18, this.zzdj.longValue());
            }
            if (this.zzdk != null) {
                zzx += zzapo.zze(19, this.zzdk.longValue());
            }
            if (this.zzdl != null) {
                zzx += zzapo.zze(20, this.zzdl.longValue());
            }
            if (this.zzef != null) {
                zzx += zzapo.zze(21, this.zzef.longValue());
            }
            if (this.zzdm != null) {
                zzx += zzapo.zze(22, this.zzdm.longValue());
            }
            if (this.zzdn != null) {
                zzx += zzapo.zze(23, this.zzdn.longValue());
            }
            if (this.zzeg != null) {
                zzx += zzapo.zzs(24, this.zzeg);
            }
            if (this.zzek != null) {
                zzx += zzapo.zze(25, this.zzek.longValue());
            }
            if (this.zzeh != null) {
                zzx += zzapo.zzag(26, this.zzeh.intValue());
            }
            if (this.zzdo != null) {
                zzx += zzapo.zzs(27, this.zzdo);
            }
            if (this.zzei != null) {
                zzx += zzapo.zzk(28, this.zzei.booleanValue());
            }
            if (this.zzdp != null) {
                zzx += zzapo.zzs(29, this.zzdp);
            }
            if (this.zzej != null) {
                zzx += zzapo.zzs(30, this.zzej);
            }
            if (this.zzdq != null) {
                zzx += zzapo.zze(31, this.zzdq.longValue());
            }
            if (this.zzdr != null) {
                zzx += zzapo.zze(32, this.zzdr.longValue());
            }
            if (this.zzds != null) {
                zzx += zzapo.zze(33, this.zzds.longValue());
            }
            if (this.zzdt != null) {
                zzx += zzapo.zzs(34, this.zzdt);
            }
            if (this.zzdu != null) {
                zzx += zzapo.zze(35, this.zzdu.longValue());
            }
            if (this.zzdv != null) {
                zzx += zzapo.zze(36, this.zzdv.longValue());
            }
            if (this.zzdw != null) {
                zzx += zzapo.zze(37, this.zzdw.longValue());
            }
            if (this.zzdx != null) {
                zzx += zzapo.zzc(38, this.zzdx);
            }
            if (this.zzdy != null) {
                zzx += zzapo.zze(39, this.zzdy.longValue());
            }
            if (this.zzdz != null) {
                zzx += zzapo.zze(40, this.zzdz.longValue());
            }
            if (this.zzea != null) {
                zzx += zzapo.zze(41, this.zzea.longValue());
            }
            if (this.zzeb != null) {
                zzx += zzapo.zze(42, this.zzeb.longValue());
            }
            if (this.zzee != null && this.zzee.length > 0) {
                int i = zzx;
                for (zzapv com_google_android_gms_internal_zzapv : this.zzee) {
                    if (com_google_android_gms_internal_zzapv != null) {
                        i += zzapo.zzc(43, com_google_android_gms_internal_zzapv);
                    }
                }
                zzx = i;
            }
            if (this.zzec != null) {
                zzx += zzapo.zze(44, this.zzec.longValue());
            }
            if (this.zzed != null) {
                zzx += zzapo.zze(45, this.zzed.longValue());
            }
            return this.zzel != null ? zzx + zzapo.zzc(201, this.zzel) : zzx;
        }
    }

    public static final class zzb extends zzapp<zzb> {
        public Long zzen;
        public Integer zzeo;
        public Boolean zzep;
        public int[] zzeq;

        public zzb() {
            this.zzen = null;
            this.zzeo = null;
            this.zzep = null;
            this.zzeq = zzapy.bjH;
            this.bjG = -1;
        }

        public void zza(zzapo com_google_android_gms_internal_zzapo) throws IOException {
            if (this.zzen != null) {
                com_google_android_gms_internal_zzapo.zzb(1, this.zzen.longValue());
            }
            if (this.zzeo != null) {
                com_google_android_gms_internal_zzapo.zzae(2, this.zzeo.intValue());
            }
            if (this.zzep != null) {
                com_google_android_gms_internal_zzapo.zzj(3, this.zzep.booleanValue());
            }
            if (this.zzeq != null && this.zzeq.length > 0) {
                for (int zzae : this.zzeq) {
                    com_google_android_gms_internal_zzapo.zzae(4, zzae);
                }
            }
            super.zza(com_google_android_gms_internal_zzapo);
        }

        public /* synthetic */ zzapv zzb(zzapn com_google_android_gms_internal_zzapn) throws IOException {
            return zze(com_google_android_gms_internal_zzapn);
        }

        public zzb zze(zzapn com_google_android_gms_internal_zzapn) throws IOException {
            while (true) {
                int ah = com_google_android_gms_internal_zzapn.ah();
                int zzc;
                switch (ah) {
                    case 0:
                        break;
                    case 8:
                        this.zzen = Long.valueOf(com_google_android_gms_internal_zzapn.ak());
                        continue;
                    case 16:
                        this.zzeo = Integer.valueOf(com_google_android_gms_internal_zzapn.al());
                        continue;
                    case 24:
                        this.zzep = Boolean.valueOf(com_google_android_gms_internal_zzapn.an());
                        continue;
                    case 32:
                        zzc = zzapy.zzc(com_google_android_gms_internal_zzapn, 32);
                        ah = this.zzeq == null ? 0 : this.zzeq.length;
                        Object obj = new int[(zzc + ah)];
                        if (ah != 0) {
                            System.arraycopy(this.zzeq, 0, obj, 0, ah);
                        }
                        while (ah < obj.length - 1) {
                            obj[ah] = com_google_android_gms_internal_zzapn.al();
                            com_google_android_gms_internal_zzapn.ah();
                            ah++;
                        }
                        obj[ah] = com_google_android_gms_internal_zzapn.al();
                        this.zzeq = obj;
                        continue;
                    case 34:
                        int zzafr = com_google_android_gms_internal_zzapn.zzafr(com_google_android_gms_internal_zzapn.aq());
                        zzc = com_google_android_gms_internal_zzapn.getPosition();
                        ah = 0;
                        while (com_google_android_gms_internal_zzapn.av() > 0) {
                            com_google_android_gms_internal_zzapn.al();
                            ah++;
                        }
                        com_google_android_gms_internal_zzapn.zzaft(zzc);
                        zzc = this.zzeq == null ? 0 : this.zzeq.length;
                        Object obj2 = new int[(ah + zzc)];
                        if (zzc != 0) {
                            System.arraycopy(this.zzeq, 0, obj2, 0, zzc);
                        }
                        while (zzc < obj2.length) {
                            obj2[zzc] = com_google_android_gms_internal_zzapn.al();
                            zzc++;
                        }
                        this.zzeq = obj2;
                        com_google_android_gms_internal_zzapn.zzafs(zzafr);
                        continue;
                    default:
                        if (!super.zza(com_google_android_gms_internal_zzapn, ah)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        protected int zzx() {
            int i = 0;
            int zzx = super.zzx();
            if (this.zzen != null) {
                zzx += zzapo.zze(1, this.zzen.longValue());
            }
            if (this.zzeo != null) {
                zzx += zzapo.zzag(2, this.zzeo.intValue());
            }
            if (this.zzep != null) {
                zzx += zzapo.zzk(3, this.zzep.booleanValue());
            }
            if (this.zzeq == null || this.zzeq.length <= 0) {
                return zzx;
            }
            int i2 = 0;
            while (i < this.zzeq.length) {
                i2 += zzapo.zzafx(this.zzeq[i]);
                i++;
            }
            return (zzx + i2) + (this.zzeq.length * 1);
        }
    }

    public static final class zzc extends zzapp<zzc> {
        public byte[] zzer;
        public byte[] zzes;

        public zzc() {
            this.zzer = null;
            this.zzes = null;
            this.bjG = -1;
        }

        public void zza(zzapo com_google_android_gms_internal_zzapo) throws IOException {
            if (this.zzer != null) {
                com_google_android_gms_internal_zzapo.zza(1, this.zzer);
            }
            if (this.zzes != null) {
                com_google_android_gms_internal_zzapo.zza(2, this.zzes);
            }
            super.zza(com_google_android_gms_internal_zzapo);
        }

        public /* synthetic */ zzapv zzb(zzapn com_google_android_gms_internal_zzapn) throws IOException {
            return zzf(com_google_android_gms_internal_zzapn);
        }

        public zzc zzf(zzapn com_google_android_gms_internal_zzapn) throws IOException {
            while (true) {
                int ah = com_google_android_gms_internal_zzapn.ah();
                switch (ah) {
                    case 0:
                        break;
                    case 10:
                        this.zzer = com_google_android_gms_internal_zzapn.readBytes();
                        continue;
                    case 18:
                        this.zzes = com_google_android_gms_internal_zzapn.readBytes();
                        continue;
                    default:
                        if (!super.zza(com_google_android_gms_internal_zzapn, ah)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        protected int zzx() {
            int zzx = super.zzx();
            if (this.zzer != null) {
                zzx += zzapo.zzb(1, this.zzer);
            }
            return this.zzes != null ? zzx + zzapo.zzb(2, this.zzes) : zzx;
        }
    }

    public static final class zzd extends zzapp<zzd> {
        public byte[] data;
        public byte[] zzet;
        public byte[] zzeu;
        public byte[] zzev;

        public zzd() {
            this.data = null;
            this.zzet = null;
            this.zzeu = null;
            this.zzev = null;
            this.bjG = -1;
        }

        public static zzd zzd(byte[] bArr) throws zzapu {
            return (zzd) zzapv.zza(new zzd(), bArr);
        }

        public void zza(zzapo com_google_android_gms_internal_zzapo) throws IOException {
            if (this.data != null) {
                com_google_android_gms_internal_zzapo.zza(1, this.data);
            }
            if (this.zzet != null) {
                com_google_android_gms_internal_zzapo.zza(2, this.zzet);
            }
            if (this.zzeu != null) {
                com_google_android_gms_internal_zzapo.zza(3, this.zzeu);
            }
            if (this.zzev != null) {
                com_google_android_gms_internal_zzapo.zza(4, this.zzev);
            }
            super.zza(com_google_android_gms_internal_zzapo);
        }

        public /* synthetic */ zzapv zzb(zzapn com_google_android_gms_internal_zzapn) throws IOException {
            return zzg(com_google_android_gms_internal_zzapn);
        }

        public zzd zzg(zzapn com_google_android_gms_internal_zzapn) throws IOException {
            while (true) {
                int ah = com_google_android_gms_internal_zzapn.ah();
                switch (ah) {
                    case 0:
                        break;
                    case 10:
                        this.data = com_google_android_gms_internal_zzapn.readBytes();
                        continue;
                    case 18:
                        this.zzet = com_google_android_gms_internal_zzapn.readBytes();
                        continue;
                    case 26:
                        this.zzeu = com_google_android_gms_internal_zzapn.readBytes();
                        continue;
                    case 34:
                        this.zzev = com_google_android_gms_internal_zzapn.readBytes();
                        continue;
                    default:
                        if (!super.zza(com_google_android_gms_internal_zzapn, ah)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        protected int zzx() {
            int zzx = super.zzx();
            if (this.data != null) {
                zzx += zzapo.zzb(1, this.data);
            }
            if (this.zzet != null) {
                zzx += zzapo.zzb(2, this.zzet);
            }
            if (this.zzeu != null) {
                zzx += zzapo.zzb(3, this.zzeu);
            }
            return this.zzev != null ? zzx + zzapo.zzb(4, this.zzev) : zzx;
        }
    }

    public static final class zze extends zzapp<zze> {
        public Long zzen;
        public String zzew;
        public byte[] zzex;

        public zze() {
            this.zzen = null;
            this.zzew = null;
            this.zzex = null;
            this.bjG = -1;
        }

        public void zza(zzapo com_google_android_gms_internal_zzapo) throws IOException {
            if (this.zzen != null) {
                com_google_android_gms_internal_zzapo.zzb(1, this.zzen.longValue());
            }
            if (this.zzew != null) {
                com_google_android_gms_internal_zzapo.zzr(3, this.zzew);
            }
            if (this.zzex != null) {
                com_google_android_gms_internal_zzapo.zza(4, this.zzex);
            }
            super.zza(com_google_android_gms_internal_zzapo);
        }

        public /* synthetic */ zzapv zzb(zzapn com_google_android_gms_internal_zzapn) throws IOException {
            return zzh(com_google_android_gms_internal_zzapn);
        }

        public zze zzh(zzapn com_google_android_gms_internal_zzapn) throws IOException {
            while (true) {
                int ah = com_google_android_gms_internal_zzapn.ah();
                switch (ah) {
                    case 0:
                        break;
                    case 8:
                        this.zzen = Long.valueOf(com_google_android_gms_internal_zzapn.ak());
                        continue;
                    case 26:
                        this.zzew = com_google_android_gms_internal_zzapn.readString();
                        continue;
                    case 34:
                        this.zzex = com_google_android_gms_internal_zzapn.readBytes();
                        continue;
                    default:
                        if (!super.zza(com_google_android_gms_internal_zzapn, ah)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        protected int zzx() {
            int zzx = super.zzx();
            if (this.zzen != null) {
                zzx += zzapo.zze(1, this.zzen.longValue());
            }
            if (this.zzew != null) {
                zzx += zzapo.zzs(3, this.zzew);
            }
            return this.zzex != null ? zzx + zzapo.zzb(4, this.zzex) : zzx;
        }
    }

    public static final class zzf extends zzapp<zzf> {
        public byte[] zzet;
        public byte[][] zzey;
        public Integer zzez;
        public Integer zzfa;

        public zzf() {
            this.zzey = zzapy.bjN;
            this.zzet = null;
            this.zzez = null;
            this.zzfa = null;
            this.bjG = -1;
        }

        public void zza(zzapo com_google_android_gms_internal_zzapo) throws IOException {
            if (this.zzey != null && this.zzey.length > 0) {
                for (byte[] bArr : this.zzey) {
                    if (bArr != null) {
                        com_google_android_gms_internal_zzapo.zza(1, bArr);
                    }
                }
            }
            if (this.zzet != null) {
                com_google_android_gms_internal_zzapo.zza(2, this.zzet);
            }
            if (this.zzez != null) {
                com_google_android_gms_internal_zzapo.zzae(3, this.zzez.intValue());
            }
            if (this.zzfa != null) {
                com_google_android_gms_internal_zzapo.zzae(4, this.zzfa.intValue());
            }
            super.zza(com_google_android_gms_internal_zzapo);
        }

        public /* synthetic */ zzapv zzb(zzapn com_google_android_gms_internal_zzapn) throws IOException {
            return zzi(com_google_android_gms_internal_zzapn);
        }

        public zzf zzi(zzapn com_google_android_gms_internal_zzapn) throws IOException {
            while (true) {
                int ah = com_google_android_gms_internal_zzapn.ah();
                switch (ah) {
                    case 0:
                        break;
                    case 10:
                        int zzc = zzapy.zzc(com_google_android_gms_internal_zzapn, 10);
                        ah = this.zzey == null ? 0 : this.zzey.length;
                        Object obj = new byte[(zzc + ah)][];
                        if (ah != 0) {
                            System.arraycopy(this.zzey, 0, obj, 0, ah);
                        }
                        while (ah < obj.length - 1) {
                            obj[ah] = com_google_android_gms_internal_zzapn.readBytes();
                            com_google_android_gms_internal_zzapn.ah();
                            ah++;
                        }
                        obj[ah] = com_google_android_gms_internal_zzapn.readBytes();
                        this.zzey = obj;
                        continue;
                    case 18:
                        this.zzet = com_google_android_gms_internal_zzapn.readBytes();
                        continue;
                    case 24:
                        ah = com_google_android_gms_internal_zzapn.al();
                        switch (ah) {
                            case 0:
                            case 1:
                                this.zzez = Integer.valueOf(ah);
                                break;
                            default:
                                continue;
                        }
                    case 32:
                        ah = com_google_android_gms_internal_zzapn.al();
                        switch (ah) {
                            case 0:
                            case 1:
                                this.zzfa = Integer.valueOf(ah);
                                break;
                            default:
                                continue;
                        }
                    default:
                        if (!super.zza(com_google_android_gms_internal_zzapn, ah)) {
                            break;
                        }
                        continue;
                }
                return this;
            }
        }

        protected int zzx() {
            int i = 0;
            int zzx = super.zzx();
            if (this.zzey == null || this.zzey.length <= 0) {
                i = zzx;
            } else {
                int i2 = 0;
                int i3 = 0;
                while (i < this.zzey.length) {
                    byte[] bArr = this.zzey[i];
                    if (bArr != null) {
                        i3++;
                        i2 += zzapo.zzbg(bArr);
                    }
                    i++;
                }
                i = (zzx + i2) + (i3 * 1);
            }
            if (this.zzet != null) {
                i += zzapo.zzb(2, this.zzet);
            }
            if (this.zzez != null) {
                i += zzapo.zzag(3, this.zzez.intValue());
            }
            return this.zzfa != null ? i + zzapo.zzag(4, this.zzfa.intValue()) : i;
        }
    }
}

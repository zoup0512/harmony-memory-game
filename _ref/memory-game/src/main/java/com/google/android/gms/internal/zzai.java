package com.google.android.gms.internal;

import java.io.IOException;

public interface zzai {

    public static final class zza extends zzapp<zza> {
        private static volatile zza[] zzwt;
        public String string;
        public int type;
        public zza[] zzwu;
        public zza[] zzwv;
        public zza[] zzww;
        public String zzwx;
        public String zzwy;
        public long zzwz;
        public boolean zzxa;
        public zza[] zzxb;
        public int[] zzxc;
        public boolean zzxd;

        public zza() {
            zzaq();
        }

        public static zza[] zzap() {
            if (zzwt == null) {
                synchronized (zzapt.bjF) {
                    if (zzwt == null) {
                        zzwt = new zza[0];
                    }
                }
            }
            return zzwt;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza com_google_android_gms_internal_zzai_zza = (zza) obj;
            if (this.type != com_google_android_gms_internal_zzai_zza.type) {
                return false;
            }
            if (this.string == null) {
                if (com_google_android_gms_internal_zzai_zza.string != null) {
                    return false;
                }
            } else if (!this.string.equals(com_google_android_gms_internal_zzai_zza.string)) {
                return false;
            }
            if (!zzapt.equals(this.zzwu, com_google_android_gms_internal_zzai_zza.zzwu) || !zzapt.equals(this.zzwv, com_google_android_gms_internal_zzai_zza.zzwv) || !zzapt.equals(this.zzww, com_google_android_gms_internal_zzai_zza.zzww)) {
                return false;
            }
            if (this.zzwx == null) {
                if (com_google_android_gms_internal_zzai_zza.zzwx != null) {
                    return false;
                }
            } else if (!this.zzwx.equals(com_google_android_gms_internal_zzai_zza.zzwx)) {
                return false;
            }
            if (this.zzwy == null) {
                if (com_google_android_gms_internal_zzai_zza.zzwy != null) {
                    return false;
                }
            } else if (!this.zzwy.equals(com_google_android_gms_internal_zzai_zza.zzwy)) {
                return false;
            }
            return (this.zzwz == com_google_android_gms_internal_zzai_zza.zzwz && this.zzxa == com_google_android_gms_internal_zzai_zza.zzxa && zzapt.equals(this.zzxb, com_google_android_gms_internal_zzai_zza.zzxb) && zzapt.equals(this.zzxc, com_google_android_gms_internal_zzai_zza.zzxc) && this.zzxd == com_google_android_gms_internal_zzai_zza.zzxd) ? (this.bjx == null || this.bjx.isEmpty()) ? com_google_android_gms_internal_zzai_zza.bjx == null || com_google_android_gms_internal_zzai_zza.bjx.isEmpty() : this.bjx.equals(com_google_android_gms_internal_zzai_zza.bjx) : false;
        }

        public int hashCode() {
            int i = 1231;
            int i2 = 0;
            int hashCode = ((((((this.zzxa ? 1231 : 1237) + (((((this.zzwy == null ? 0 : this.zzwy.hashCode()) + (((this.zzwx == null ? 0 : this.zzwx.hashCode()) + (((((((((this.string == null ? 0 : this.string.hashCode()) + ((((getClass().getName().hashCode() + 527) * 31) + this.type) * 31)) * 31) + zzapt.hashCode(this.zzwu)) * 31) + zzapt.hashCode(this.zzwv)) * 31) + zzapt.hashCode(this.zzww)) * 31)) * 31)) * 31) + ((int) (this.zzwz ^ (this.zzwz >>> 32)))) * 31)) * 31) + zzapt.hashCode(this.zzxb)) * 31) + zzapt.hashCode(this.zzxc)) * 31;
            if (!this.zzxd) {
                i = 1237;
            }
            hashCode = (hashCode + i) * 31;
            if (!(this.bjx == null || this.bjx.isEmpty())) {
                i2 = this.bjx.hashCode();
            }
            return hashCode + i2;
        }

        public void zza(zzapo com_google_android_gms_internal_zzapo) throws IOException {
            int i = 0;
            com_google_android_gms_internal_zzapo.zzae(1, this.type);
            if (!this.string.equals("")) {
                com_google_android_gms_internal_zzapo.zzr(2, this.string);
            }
            if (this.zzwu != null && this.zzwu.length > 0) {
                for (zzapv com_google_android_gms_internal_zzapv : this.zzwu) {
                    if (com_google_android_gms_internal_zzapv != null) {
                        com_google_android_gms_internal_zzapo.zza(3, com_google_android_gms_internal_zzapv);
                    }
                }
            }
            if (this.zzwv != null && this.zzwv.length > 0) {
                for (zzapv com_google_android_gms_internal_zzapv2 : this.zzwv) {
                    if (com_google_android_gms_internal_zzapv2 != null) {
                        com_google_android_gms_internal_zzapo.zza(4, com_google_android_gms_internal_zzapv2);
                    }
                }
            }
            if (this.zzww != null && this.zzww.length > 0) {
                for (zzapv com_google_android_gms_internal_zzapv22 : this.zzww) {
                    if (com_google_android_gms_internal_zzapv22 != null) {
                        com_google_android_gms_internal_zzapo.zza(5, com_google_android_gms_internal_zzapv22);
                    }
                }
            }
            if (!this.zzwx.equals("")) {
                com_google_android_gms_internal_zzapo.zzr(6, this.zzwx);
            }
            if (!this.zzwy.equals("")) {
                com_google_android_gms_internal_zzapo.zzr(7, this.zzwy);
            }
            if (this.zzwz != 0) {
                com_google_android_gms_internal_zzapo.zzb(8, this.zzwz);
            }
            if (this.zzxd) {
                com_google_android_gms_internal_zzapo.zzj(9, this.zzxd);
            }
            if (this.zzxc != null && this.zzxc.length > 0) {
                for (int zzae : this.zzxc) {
                    com_google_android_gms_internal_zzapo.zzae(10, zzae);
                }
            }
            if (this.zzxb != null && this.zzxb.length > 0) {
                while (i < this.zzxb.length) {
                    zzapv com_google_android_gms_internal_zzapv3 = this.zzxb[i];
                    if (com_google_android_gms_internal_zzapv3 != null) {
                        com_google_android_gms_internal_zzapo.zza(11, com_google_android_gms_internal_zzapv3);
                    }
                    i++;
                }
            }
            if (this.zzxa) {
                com_google_android_gms_internal_zzapo.zzj(12, this.zzxa);
            }
            super.zza(com_google_android_gms_internal_zzapo);
        }

        public zza zzaq() {
            this.type = 1;
            this.string = "";
            this.zzwu = zzap();
            this.zzwv = zzap();
            this.zzww = zzap();
            this.zzwx = "";
            this.zzwy = "";
            this.zzwz = 0;
            this.zzxa = false;
            this.zzxb = zzap();
            this.zzxc = zzapy.bjH;
            this.zzxd = false;
            this.bjx = null;
            this.bjG = -1;
            return this;
        }

        public /* synthetic */ zzapv zzb(zzapn com_google_android_gms_internal_zzapn) throws IOException {
            return zzt(com_google_android_gms_internal_zzapn);
        }

        public zza zzt(zzapn com_google_android_gms_internal_zzapn) throws IOException {
            while (true) {
                int ah = com_google_android_gms_internal_zzapn.ah();
                int zzc;
                Object obj;
                int i;
                switch (ah) {
                    case 0:
                        break;
                    case 8:
                        ah = com_google_android_gms_internal_zzapn.al();
                        switch (ah) {
                            case 1:
                            case 2:
                            case 3:
                            case 4:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                                this.type = ah;
                                break;
                            default:
                                continue;
                        }
                    case 18:
                        this.string = com_google_android_gms_internal_zzapn.readString();
                        continue;
                    case 26:
                        zzc = zzapy.zzc(com_google_android_gms_internal_zzapn, 26);
                        ah = this.zzwu == null ? 0 : this.zzwu.length;
                        obj = new zza[(zzc + ah)];
                        if (ah != 0) {
                            System.arraycopy(this.zzwu, 0, obj, 0, ah);
                        }
                        while (ah < obj.length - 1) {
                            obj[ah] = new zza();
                            com_google_android_gms_internal_zzapn.zza(obj[ah]);
                            com_google_android_gms_internal_zzapn.ah();
                            ah++;
                        }
                        obj[ah] = new zza();
                        com_google_android_gms_internal_zzapn.zza(obj[ah]);
                        this.zzwu = obj;
                        continue;
                    case 34:
                        zzc = zzapy.zzc(com_google_android_gms_internal_zzapn, 34);
                        ah = this.zzwv == null ? 0 : this.zzwv.length;
                        obj = new zza[(zzc + ah)];
                        if (ah != 0) {
                            System.arraycopy(this.zzwv, 0, obj, 0, ah);
                        }
                        while (ah < obj.length - 1) {
                            obj[ah] = new zza();
                            com_google_android_gms_internal_zzapn.zza(obj[ah]);
                            com_google_android_gms_internal_zzapn.ah();
                            ah++;
                        }
                        obj[ah] = new zza();
                        com_google_android_gms_internal_zzapn.zza(obj[ah]);
                        this.zzwv = obj;
                        continue;
                    case 42:
                        zzc = zzapy.zzc(com_google_android_gms_internal_zzapn, 42);
                        ah = this.zzww == null ? 0 : this.zzww.length;
                        obj = new zza[(zzc + ah)];
                        if (ah != 0) {
                            System.arraycopy(this.zzww, 0, obj, 0, ah);
                        }
                        while (ah < obj.length - 1) {
                            obj[ah] = new zza();
                            com_google_android_gms_internal_zzapn.zza(obj[ah]);
                            com_google_android_gms_internal_zzapn.ah();
                            ah++;
                        }
                        obj[ah] = new zza();
                        com_google_android_gms_internal_zzapn.zza(obj[ah]);
                        this.zzww = obj;
                        continue;
                    case 50:
                        this.zzwx = com_google_android_gms_internal_zzapn.readString();
                        continue;
                    case 58:
                        this.zzwy = com_google_android_gms_internal_zzapn.readString();
                        continue;
                    case 64:
                        this.zzwz = com_google_android_gms_internal_zzapn.ak();
                        continue;
                    case 72:
                        this.zzxd = com_google_android_gms_internal_zzapn.an();
                        continue;
                    case 80:
                        int zzc2 = zzapy.zzc(com_google_android_gms_internal_zzapn, 80);
                        Object obj2 = new int[zzc2];
                        i = 0;
                        zzc = 0;
                        while (i < zzc2) {
                            if (i != 0) {
                                com_google_android_gms_internal_zzapn.ah();
                            }
                            int al = com_google_android_gms_internal_zzapn.al();
                            switch (al) {
                                case 1:
                                case 2:
                                case 3:
                                case 4:
                                case 5:
                                case 6:
                                case 7:
                                case 8:
                                case 9:
                                case 10:
                                case 11:
                                case 12:
                                case 13:
                                case 14:
                                case 15:
                                case 16:
                                case 17:
                                    ah = zzc + 1;
                                    obj2[zzc] = al;
                                    break;
                                default:
                                    ah = zzc;
                                    break;
                            }
                            i++;
                            zzc = ah;
                        }
                        if (zzc != 0) {
                            ah = this.zzxc == null ? 0 : this.zzxc.length;
                            if (ah != 0 || zzc != zzc2) {
                                Object obj3 = new int[(ah + zzc)];
                                if (ah != 0) {
                                    System.arraycopy(this.zzxc, 0, obj3, 0, ah);
                                }
                                System.arraycopy(obj2, 0, obj3, ah, zzc);
                                this.zzxc = obj3;
                                break;
                            }
                            this.zzxc = obj2;
                            break;
                        }
                        continue;
                    case 82:
                        i = com_google_android_gms_internal_zzapn.zzafr(com_google_android_gms_internal_zzapn.aq());
                        zzc = com_google_android_gms_internal_zzapn.getPosition();
                        ah = 0;
                        while (com_google_android_gms_internal_zzapn.av() > 0) {
                            switch (com_google_android_gms_internal_zzapn.al()) {
                                case 1:
                                case 2:
                                case 3:
                                case 4:
                                case 5:
                                case 6:
                                case 7:
                                case 8:
                                case 9:
                                case 10:
                                case 11:
                                case 12:
                                case 13:
                                case 14:
                                case 15:
                                case 16:
                                case 17:
                                    ah++;
                                    break;
                                default:
                                    break;
                            }
                        }
                        if (ah != 0) {
                            com_google_android_gms_internal_zzapn.zzaft(zzc);
                            zzc = this.zzxc == null ? 0 : this.zzxc.length;
                            Object obj4 = new int[(ah + zzc)];
                            if (zzc != 0) {
                                System.arraycopy(this.zzxc, 0, obj4, 0, zzc);
                            }
                            while (com_google_android_gms_internal_zzapn.av() > 0) {
                                int al2 = com_google_android_gms_internal_zzapn.al();
                                switch (al2) {
                                    case 1:
                                    case 2:
                                    case 3:
                                    case 4:
                                    case 5:
                                    case 6:
                                    case 7:
                                    case 8:
                                    case 9:
                                    case 10:
                                    case 11:
                                    case 12:
                                    case 13:
                                    case 14:
                                    case 15:
                                    case 16:
                                    case 17:
                                        ah = zzc + 1;
                                        obj4[zzc] = al2;
                                        zzc = ah;
                                        break;
                                    default:
                                        break;
                                }
                            }
                            this.zzxc = obj4;
                        }
                        com_google_android_gms_internal_zzapn.zzafs(i);
                        continue;
                    case 90:
                        zzc = zzapy.zzc(com_google_android_gms_internal_zzapn, 90);
                        ah = this.zzxb == null ? 0 : this.zzxb.length;
                        obj = new zza[(zzc + ah)];
                        if (ah != 0) {
                            System.arraycopy(this.zzxb, 0, obj, 0, ah);
                        }
                        while (ah < obj.length - 1) {
                            obj[ah] = new zza();
                            com_google_android_gms_internal_zzapn.zza(obj[ah]);
                            com_google_android_gms_internal_zzapn.ah();
                            ah++;
                        }
                        obj[ah] = new zza();
                        com_google_android_gms_internal_zzapn.zza(obj[ah]);
                        this.zzxb = obj;
                        continue;
                    case 96:
                        this.zzxa = com_google_android_gms_internal_zzapn.an();
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
            int i;
            int i2 = 0;
            int zzx = super.zzx() + zzapo.zzag(1, this.type);
            if (!this.string.equals("")) {
                zzx += zzapo.zzs(2, this.string);
            }
            if (this.zzwu != null && this.zzwu.length > 0) {
                i = zzx;
                for (zzapv com_google_android_gms_internal_zzapv : this.zzwu) {
                    if (com_google_android_gms_internal_zzapv != null) {
                        i += zzapo.zzc(3, com_google_android_gms_internal_zzapv);
                    }
                }
                zzx = i;
            }
            if (this.zzwv != null && this.zzwv.length > 0) {
                i = zzx;
                for (zzapv com_google_android_gms_internal_zzapv2 : this.zzwv) {
                    if (com_google_android_gms_internal_zzapv2 != null) {
                        i += zzapo.zzc(4, com_google_android_gms_internal_zzapv2);
                    }
                }
                zzx = i;
            }
            if (this.zzww != null && this.zzww.length > 0) {
                i = zzx;
                for (zzapv com_google_android_gms_internal_zzapv22 : this.zzww) {
                    if (com_google_android_gms_internal_zzapv22 != null) {
                        i += zzapo.zzc(5, com_google_android_gms_internal_zzapv22);
                    }
                }
                zzx = i;
            }
            if (!this.zzwx.equals("")) {
                zzx += zzapo.zzs(6, this.zzwx);
            }
            if (!this.zzwy.equals("")) {
                zzx += zzapo.zzs(7, this.zzwy);
            }
            if (this.zzwz != 0) {
                zzx += zzapo.zze(8, this.zzwz);
            }
            if (this.zzxd) {
                zzx += zzapo.zzk(9, this.zzxd);
            }
            if (this.zzxc != null && this.zzxc.length > 0) {
                int i3 = 0;
                for (int zzafx : this.zzxc) {
                    i3 += zzapo.zzafx(zzafx);
                }
                zzx = (zzx + i3) + (this.zzxc.length * 1);
            }
            if (this.zzxb != null && this.zzxb.length > 0) {
                while (i2 < this.zzxb.length) {
                    zzapv com_google_android_gms_internal_zzapv3 = this.zzxb[i2];
                    if (com_google_android_gms_internal_zzapv3 != null) {
                        zzx += zzapo.zzc(11, com_google_android_gms_internal_zzapv3);
                    }
                    i2++;
                }
            }
            return this.zzxa ? zzx + zzapo.zzk(12, this.zzxa) : zzx;
        }
    }
}

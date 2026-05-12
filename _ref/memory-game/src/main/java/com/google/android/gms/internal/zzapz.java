package com.google.android.gms.internal;

import android.support.v4.media.TransportMediator;
import java.io.IOException;
import java.util.Arrays;

public interface zzapz {

    public static final class zza extends zzapp<zza> implements Cloneable {
        public String[] bjP;
        public String[] bjQ;
        public int[] bjR;
        public long[] bjS;
        public long[] bjT;

        public zza() {
            aN();
        }

        public /* synthetic */ zzapp aA() throws CloneNotSupportedException {
            return (zza) clone();
        }

        public /* synthetic */ zzapv aB() throws CloneNotSupportedException {
            return (zza) clone();
        }

        public zza aN() {
            this.bjP = zzapy.bjM;
            this.bjQ = zzapy.bjM;
            this.bjR = zzapy.bjH;
            this.bjS = zzapy.bjI;
            this.bjT = zzapy.bjI;
            this.bjx = null;
            this.bjG = -1;
            return this;
        }

        public zza aO() {
            try {
                zza com_google_android_gms_internal_zzapz_zza = (zza) super.aA();
                if (this.bjP != null && this.bjP.length > 0) {
                    com_google_android_gms_internal_zzapz_zza.bjP = (String[]) this.bjP.clone();
                }
                if (this.bjQ != null && this.bjQ.length > 0) {
                    com_google_android_gms_internal_zzapz_zza.bjQ = (String[]) this.bjQ.clone();
                }
                if (this.bjR != null && this.bjR.length > 0) {
                    com_google_android_gms_internal_zzapz_zza.bjR = (int[]) this.bjR.clone();
                }
                if (this.bjS != null && this.bjS.length > 0) {
                    com_google_android_gms_internal_zzapz_zza.bjS = (long[]) this.bjS.clone();
                }
                if (this.bjT != null && this.bjT.length > 0) {
                    com_google_android_gms_internal_zzapz_zza.bjT = (long[]) this.bjT.clone();
                }
                return com_google_android_gms_internal_zzapz_zza;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(e);
            }
        }

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            return aO();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zza)) {
                return false;
            }
            zza com_google_android_gms_internal_zzapz_zza = (zza) obj;
            return (zzapt.equals(this.bjP, com_google_android_gms_internal_zzapz_zza.bjP) && zzapt.equals(this.bjQ, com_google_android_gms_internal_zzapz_zza.bjQ) && zzapt.equals(this.bjR, com_google_android_gms_internal_zzapz_zza.bjR) && zzapt.equals(this.bjS, com_google_android_gms_internal_zzapz_zza.bjS) && zzapt.equals(this.bjT, com_google_android_gms_internal_zzapz_zza.bjT)) ? (this.bjx == null || this.bjx.isEmpty()) ? com_google_android_gms_internal_zzapz_zza.bjx == null || com_google_android_gms_internal_zzapz_zza.bjx.isEmpty() : this.bjx.equals(com_google_android_gms_internal_zzapz_zza.bjx) : false;
        }

        public int hashCode() {
            int hashCode = (((((((((((getClass().getName().hashCode() + 527) * 31) + zzapt.hashCode(this.bjP)) * 31) + zzapt.hashCode(this.bjQ)) * 31) + zzapt.hashCode(this.bjR)) * 31) + zzapt.hashCode(this.bjS)) * 31) + zzapt.hashCode(this.bjT)) * 31;
            int hashCode2 = (this.bjx == null || this.bjx.isEmpty()) ? 0 : this.bjx.hashCode();
            return hashCode2 + hashCode;
        }

        public void zza(zzapo com_google_android_gms_internal_zzapo) throws IOException {
            int i = 0;
            if (this.bjP != null && this.bjP.length > 0) {
                for (String str : this.bjP) {
                    if (str != null) {
                        com_google_android_gms_internal_zzapo.zzr(1, str);
                    }
                }
            }
            if (this.bjQ != null && this.bjQ.length > 0) {
                for (String str2 : this.bjQ) {
                    if (str2 != null) {
                        com_google_android_gms_internal_zzapo.zzr(2, str2);
                    }
                }
            }
            if (this.bjR != null && this.bjR.length > 0) {
                for (int zzae : this.bjR) {
                    com_google_android_gms_internal_zzapo.zzae(3, zzae);
                }
            }
            if (this.bjS != null && this.bjS.length > 0) {
                for (long zzb : this.bjS) {
                    com_google_android_gms_internal_zzapo.zzb(4, zzb);
                }
            }
            if (this.bjT != null && this.bjT.length > 0) {
                while (i < this.bjT.length) {
                    com_google_android_gms_internal_zzapo.zzb(5, this.bjT[i]);
                    i++;
                }
            }
            super.zza(com_google_android_gms_internal_zzapo);
        }

        public /* synthetic */ zzapv zzb(zzapn com_google_android_gms_internal_zzapn) throws IOException {
            return zzch(com_google_android_gms_internal_zzapn);
        }

        public zza zzch(zzapn com_google_android_gms_internal_zzapn) throws IOException {
            while (true) {
                int ah = com_google_android_gms_internal_zzapn.ah();
                int zzc;
                Object obj;
                int zzafr;
                Object obj2;
                switch (ah) {
                    case 0:
                        break;
                    case 10:
                        zzc = zzapy.zzc(com_google_android_gms_internal_zzapn, 10);
                        ah = this.bjP == null ? 0 : this.bjP.length;
                        obj = new String[(zzc + ah)];
                        if (ah != 0) {
                            System.arraycopy(this.bjP, 0, obj, 0, ah);
                        }
                        while (ah < obj.length - 1) {
                            obj[ah] = com_google_android_gms_internal_zzapn.readString();
                            com_google_android_gms_internal_zzapn.ah();
                            ah++;
                        }
                        obj[ah] = com_google_android_gms_internal_zzapn.readString();
                        this.bjP = obj;
                        continue;
                    case 18:
                        zzc = zzapy.zzc(com_google_android_gms_internal_zzapn, 18);
                        ah = this.bjQ == null ? 0 : this.bjQ.length;
                        obj = new String[(zzc + ah)];
                        if (ah != 0) {
                            System.arraycopy(this.bjQ, 0, obj, 0, ah);
                        }
                        while (ah < obj.length - 1) {
                            obj[ah] = com_google_android_gms_internal_zzapn.readString();
                            com_google_android_gms_internal_zzapn.ah();
                            ah++;
                        }
                        obj[ah] = com_google_android_gms_internal_zzapn.readString();
                        this.bjQ = obj;
                        continue;
                    case 24:
                        zzc = zzapy.zzc(com_google_android_gms_internal_zzapn, 24);
                        ah = this.bjR == null ? 0 : this.bjR.length;
                        obj = new int[(zzc + ah)];
                        if (ah != 0) {
                            System.arraycopy(this.bjR, 0, obj, 0, ah);
                        }
                        while (ah < obj.length - 1) {
                            obj[ah] = com_google_android_gms_internal_zzapn.al();
                            com_google_android_gms_internal_zzapn.ah();
                            ah++;
                        }
                        obj[ah] = com_google_android_gms_internal_zzapn.al();
                        this.bjR = obj;
                        continue;
                    case 26:
                        zzafr = com_google_android_gms_internal_zzapn.zzafr(com_google_android_gms_internal_zzapn.aq());
                        zzc = com_google_android_gms_internal_zzapn.getPosition();
                        ah = 0;
                        while (com_google_android_gms_internal_zzapn.av() > 0) {
                            com_google_android_gms_internal_zzapn.al();
                            ah++;
                        }
                        com_google_android_gms_internal_zzapn.zzaft(zzc);
                        zzc = this.bjR == null ? 0 : this.bjR.length;
                        obj2 = new int[(ah + zzc)];
                        if (zzc != 0) {
                            System.arraycopy(this.bjR, 0, obj2, 0, zzc);
                        }
                        while (zzc < obj2.length) {
                            obj2[zzc] = com_google_android_gms_internal_zzapn.al();
                            zzc++;
                        }
                        this.bjR = obj2;
                        com_google_android_gms_internal_zzapn.zzafs(zzafr);
                        continue;
                    case 32:
                        zzc = zzapy.zzc(com_google_android_gms_internal_zzapn, 32);
                        ah = this.bjS == null ? 0 : this.bjS.length;
                        obj = new long[(zzc + ah)];
                        if (ah != 0) {
                            System.arraycopy(this.bjS, 0, obj, 0, ah);
                        }
                        while (ah < obj.length - 1) {
                            obj[ah] = com_google_android_gms_internal_zzapn.ak();
                            com_google_android_gms_internal_zzapn.ah();
                            ah++;
                        }
                        obj[ah] = com_google_android_gms_internal_zzapn.ak();
                        this.bjS = obj;
                        continue;
                    case 34:
                        zzafr = com_google_android_gms_internal_zzapn.zzafr(com_google_android_gms_internal_zzapn.aq());
                        zzc = com_google_android_gms_internal_zzapn.getPosition();
                        ah = 0;
                        while (com_google_android_gms_internal_zzapn.av() > 0) {
                            com_google_android_gms_internal_zzapn.ak();
                            ah++;
                        }
                        com_google_android_gms_internal_zzapn.zzaft(zzc);
                        zzc = this.bjS == null ? 0 : this.bjS.length;
                        obj2 = new long[(ah + zzc)];
                        if (zzc != 0) {
                            System.arraycopy(this.bjS, 0, obj2, 0, zzc);
                        }
                        while (zzc < obj2.length) {
                            obj2[zzc] = com_google_android_gms_internal_zzapn.ak();
                            zzc++;
                        }
                        this.bjS = obj2;
                        com_google_android_gms_internal_zzapn.zzafs(zzafr);
                        continue;
                    case 40:
                        zzc = zzapy.zzc(com_google_android_gms_internal_zzapn, 40);
                        ah = this.bjT == null ? 0 : this.bjT.length;
                        obj = new long[(zzc + ah)];
                        if (ah != 0) {
                            System.arraycopy(this.bjT, 0, obj, 0, ah);
                        }
                        while (ah < obj.length - 1) {
                            obj[ah] = com_google_android_gms_internal_zzapn.ak();
                            com_google_android_gms_internal_zzapn.ah();
                            ah++;
                        }
                        obj[ah] = com_google_android_gms_internal_zzapn.ak();
                        this.bjT = obj;
                        continue;
                    case 42:
                        zzafr = com_google_android_gms_internal_zzapn.zzafr(com_google_android_gms_internal_zzapn.aq());
                        zzc = com_google_android_gms_internal_zzapn.getPosition();
                        ah = 0;
                        while (com_google_android_gms_internal_zzapn.av() > 0) {
                            com_google_android_gms_internal_zzapn.ak();
                            ah++;
                        }
                        com_google_android_gms_internal_zzapn.zzaft(zzc);
                        zzc = this.bjT == null ? 0 : this.bjT.length;
                        obj2 = new long[(ah + zzc)];
                        if (zzc != 0) {
                            System.arraycopy(this.bjT, 0, obj2, 0, zzc);
                        }
                        while (zzc < obj2.length) {
                            obj2[zzc] = com_google_android_gms_internal_zzapn.ak();
                            zzc++;
                        }
                        this.bjT = obj2;
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
            int i;
            int i2;
            int i3;
            int i4 = 0;
            int zzx = super.zzx();
            if (this.bjP == null || this.bjP.length <= 0) {
                i = zzx;
            } else {
                i2 = 0;
                i3 = 0;
                for (String str : this.bjP) {
                    if (str != null) {
                        i3++;
                        i2 += zzapo.zztx(str);
                    }
                }
                i = (zzx + i2) + (i3 * 1);
            }
            if (this.bjQ != null && this.bjQ.length > 0) {
                i3 = 0;
                zzx = 0;
                for (String str2 : this.bjQ) {
                    if (str2 != null) {
                        zzx++;
                        i3 += zzapo.zztx(str2);
                    }
                }
                i = (i + i3) + (zzx * 1);
            }
            if (this.bjR != null && this.bjR.length > 0) {
                i3 = 0;
                for (int zzx2 : this.bjR) {
                    i3 += zzapo.zzafx(zzx2);
                }
                i = (i + i3) + (this.bjR.length * 1);
            }
            if (this.bjS != null && this.bjS.length > 0) {
                i3 = 0;
                for (long zzcy : this.bjS) {
                    i3 += zzapo.zzcy(zzcy);
                }
                i = (i + i3) + (this.bjS.length * 1);
            }
            if (this.bjT == null || this.bjT.length <= 0) {
                return i;
            }
            i2 = 0;
            while (i4 < this.bjT.length) {
                i2 += zzapo.zzcy(this.bjT[i4]);
                i4++;
            }
            return (i + i2) + (this.bjT.length * 1);
        }
    }

    public static final class zzb extends zzapp<zzb> implements Cloneable {
        public int bjU;
        public String bjV;
        public String version;

        public zzb() {
            aP();
        }

        public /* synthetic */ zzapp aA() throws CloneNotSupportedException {
            return (zzb) clone();
        }

        public /* synthetic */ zzapv aB() throws CloneNotSupportedException {
            return (zzb) clone();
        }

        public zzb aP() {
            this.bjU = 0;
            this.bjV = "";
            this.version = "";
            this.bjx = null;
            this.bjG = -1;
            return this;
        }

        public zzb aQ() {
            try {
                return (zzb) super.aA();
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(e);
            }
        }

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            return aQ();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzb)) {
                return false;
            }
            zzb com_google_android_gms_internal_zzapz_zzb = (zzb) obj;
            if (this.bjU != com_google_android_gms_internal_zzapz_zzb.bjU) {
                return false;
            }
            if (this.bjV == null) {
                if (com_google_android_gms_internal_zzapz_zzb.bjV != null) {
                    return false;
                }
            } else if (!this.bjV.equals(com_google_android_gms_internal_zzapz_zzb.bjV)) {
                return false;
            }
            if (this.version == null) {
                if (com_google_android_gms_internal_zzapz_zzb.version != null) {
                    return false;
                }
            } else if (!this.version.equals(com_google_android_gms_internal_zzapz_zzb.version)) {
                return false;
            }
            return (this.bjx == null || this.bjx.isEmpty()) ? com_google_android_gms_internal_zzapz_zzb.bjx == null || com_google_android_gms_internal_zzapz_zzb.bjx.isEmpty() : this.bjx.equals(com_google_android_gms_internal_zzapz_zzb.bjx);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.version == null ? 0 : this.version.hashCode()) + (((this.bjV == null ? 0 : this.bjV.hashCode()) + ((((getClass().getName().hashCode() + 527) * 31) + this.bjU) * 31)) * 31)) * 31;
            if (!(this.bjx == null || this.bjx.isEmpty())) {
                i = this.bjx.hashCode();
            }
            return hashCode + i;
        }

        public void zza(zzapo com_google_android_gms_internal_zzapo) throws IOException {
            if (this.bjU != 0) {
                com_google_android_gms_internal_zzapo.zzae(1, this.bjU);
            }
            if (!this.bjV.equals("")) {
                com_google_android_gms_internal_zzapo.zzr(2, this.bjV);
            }
            if (!this.version.equals("")) {
                com_google_android_gms_internal_zzapo.zzr(3, this.version);
            }
            super.zza(com_google_android_gms_internal_zzapo);
        }

        public /* synthetic */ zzapv zzb(zzapn com_google_android_gms_internal_zzapn) throws IOException {
            return zzci(com_google_android_gms_internal_zzapn);
        }

        public zzb zzci(zzapn com_google_android_gms_internal_zzapn) throws IOException {
            while (true) {
                int ah = com_google_android_gms_internal_zzapn.ah();
                switch (ah) {
                    case 0:
                        break;
                    case 8:
                        this.bjU = com_google_android_gms_internal_zzapn.al();
                        continue;
                    case 18:
                        this.bjV = com_google_android_gms_internal_zzapn.readString();
                        continue;
                    case 26:
                        this.version = com_google_android_gms_internal_zzapn.readString();
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
            if (this.bjU != 0) {
                zzx += zzapo.zzag(1, this.bjU);
            }
            if (!this.bjV.equals("")) {
                zzx += zzapo.zzs(2, this.bjV);
            }
            return !this.version.equals("") ? zzx + zzapo.zzs(3, this.version) : zzx;
        }
    }

    public static final class zzc extends zzapp<zzc> implements Cloneable {
        public byte[] bjW;
        public String bjX;
        public byte[][] bjY;
        public boolean bjZ;

        public zzc() {
            aR();
        }

        public /* synthetic */ zzapp aA() throws CloneNotSupportedException {
            return (zzc) clone();
        }

        public /* synthetic */ zzapv aB() throws CloneNotSupportedException {
            return (zzc) clone();
        }

        public zzc aR() {
            this.bjW = zzapy.bjO;
            this.bjX = "";
            this.bjY = zzapy.bjN;
            this.bjZ = false;
            this.bjx = null;
            this.bjG = -1;
            return this;
        }

        public zzc aS() {
            try {
                zzc com_google_android_gms_internal_zzapz_zzc = (zzc) super.aA();
                if (this.bjY != null && this.bjY.length > 0) {
                    com_google_android_gms_internal_zzapz_zzc.bjY = (byte[][]) this.bjY.clone();
                }
                return com_google_android_gms_internal_zzapz_zzc;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(e);
            }
        }

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            return aS();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzc)) {
                return false;
            }
            zzc com_google_android_gms_internal_zzapz_zzc = (zzc) obj;
            if (!Arrays.equals(this.bjW, com_google_android_gms_internal_zzapz_zzc.bjW)) {
                return false;
            }
            if (this.bjX == null) {
                if (com_google_android_gms_internal_zzapz_zzc.bjX != null) {
                    return false;
                }
            } else if (!this.bjX.equals(com_google_android_gms_internal_zzapz_zzc.bjX)) {
                return false;
            }
            return (zzapt.zza(this.bjY, com_google_android_gms_internal_zzapz_zzc.bjY) && this.bjZ == com_google_android_gms_internal_zzapz_zzc.bjZ) ? (this.bjx == null || this.bjx.isEmpty()) ? com_google_android_gms_internal_zzapz_zzc.bjx == null || com_google_android_gms_internal_zzapz_zzc.bjx.isEmpty() : this.bjx.equals(com_google_android_gms_internal_zzapz_zzc.bjx) : false;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.bjZ ? 1231 : 1237) + (((((this.bjX == null ? 0 : this.bjX.hashCode()) + ((((getClass().getName().hashCode() + 527) * 31) + Arrays.hashCode(this.bjW)) * 31)) * 31) + zzapt.zzb(this.bjY)) * 31)) * 31;
            if (!(this.bjx == null || this.bjx.isEmpty())) {
                i = this.bjx.hashCode();
            }
            return hashCode + i;
        }

        public void zza(zzapo com_google_android_gms_internal_zzapo) throws IOException {
            if (!Arrays.equals(this.bjW, zzapy.bjO)) {
                com_google_android_gms_internal_zzapo.zza(1, this.bjW);
            }
            if (this.bjY != null && this.bjY.length > 0) {
                for (byte[] bArr : this.bjY) {
                    if (bArr != null) {
                        com_google_android_gms_internal_zzapo.zza(2, bArr);
                    }
                }
            }
            if (this.bjZ) {
                com_google_android_gms_internal_zzapo.zzj(3, this.bjZ);
            }
            if (!this.bjX.equals("")) {
                com_google_android_gms_internal_zzapo.zzr(4, this.bjX);
            }
            super.zza(com_google_android_gms_internal_zzapo);
        }

        public /* synthetic */ zzapv zzb(zzapn com_google_android_gms_internal_zzapn) throws IOException {
            return zzcj(com_google_android_gms_internal_zzapn);
        }

        public zzc zzcj(zzapn com_google_android_gms_internal_zzapn) throws IOException {
            while (true) {
                int ah = com_google_android_gms_internal_zzapn.ah();
                switch (ah) {
                    case 0:
                        break;
                    case 10:
                        this.bjW = com_google_android_gms_internal_zzapn.readBytes();
                        continue;
                    case 18:
                        int zzc = zzapy.zzc(com_google_android_gms_internal_zzapn, 18);
                        ah = this.bjY == null ? 0 : this.bjY.length;
                        Object obj = new byte[(zzc + ah)][];
                        if (ah != 0) {
                            System.arraycopy(this.bjY, 0, obj, 0, ah);
                        }
                        while (ah < obj.length - 1) {
                            obj[ah] = com_google_android_gms_internal_zzapn.readBytes();
                            com_google_android_gms_internal_zzapn.ah();
                            ah++;
                        }
                        obj[ah] = com_google_android_gms_internal_zzapn.readBytes();
                        this.bjY = obj;
                        continue;
                    case 24:
                        this.bjZ = com_google_android_gms_internal_zzapn.an();
                        continue;
                    case 34:
                        this.bjX = com_google_android_gms_internal_zzapn.readString();
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
            if (!Arrays.equals(this.bjW, zzapy.bjO)) {
                zzx += zzapo.zzb(1, this.bjW);
            }
            if (this.bjY != null && this.bjY.length > 0) {
                int i2 = 0;
                int i3 = 0;
                while (i < this.bjY.length) {
                    byte[] bArr = this.bjY[i];
                    if (bArr != null) {
                        i3++;
                        i2 += zzapo.zzbg(bArr);
                    }
                    i++;
                }
                zzx = (zzx + i2) + (i3 * 1);
            }
            if (this.bjZ) {
                zzx += zzapo.zzk(3, this.bjZ);
            }
            return !this.bjX.equals("") ? zzx + zzapo.zzs(4, this.bjX) : zzx;
        }
    }

    public static final class zzd extends zzapp<zzd> implements Cloneable {
        public boolean aTs;
        public long bka;
        public long bkb;
        public long bkc;
        public int bkd;
        public zze[] bke;
        public byte[] bkf;
        public zzb bkg;
        public byte[] bkh;
        public String bki;
        public String bkj;
        public zza bkk;
        public String bkl;
        public long bkm;
        public zzc bkn;
        public byte[] bko;
        public String bkp;
        public int bkq;
        public int[] bkr;
        public long bks;
        public zzf bkt;
        public String tag;
        public int zzahl;

        public zzd() {
            aT();
        }

        public /* synthetic */ zzapp aA() throws CloneNotSupportedException {
            return (zzd) clone();
        }

        public /* synthetic */ zzapv aB() throws CloneNotSupportedException {
            return (zzd) clone();
        }

        public zzd aT() {
            this.bka = 0;
            this.bkb = 0;
            this.bkc = 0;
            this.tag = "";
            this.bkd = 0;
            this.zzahl = 0;
            this.aTs = false;
            this.bke = zze.aV();
            this.bkf = zzapy.bjO;
            this.bkg = null;
            this.bkh = zzapy.bjO;
            this.bki = "";
            this.bkj = "";
            this.bkk = null;
            this.bkl = "";
            this.bkm = 180000;
            this.bkn = null;
            this.bko = zzapy.bjO;
            this.bkp = "";
            this.bkq = 0;
            this.bkr = zzapy.bjH;
            this.bks = 0;
            this.bkt = null;
            this.bjx = null;
            this.bjG = -1;
            return this;
        }

        public zzd aU() {
            try {
                zzd com_google_android_gms_internal_zzapz_zzd = (zzd) super.aA();
                if (this.bke != null && this.bke.length > 0) {
                    com_google_android_gms_internal_zzapz_zzd.bke = new zze[this.bke.length];
                    for (int i = 0; i < this.bke.length; i++) {
                        if (this.bke[i] != null) {
                            com_google_android_gms_internal_zzapz_zzd.bke[i] = (zze) this.bke[i].clone();
                        }
                    }
                }
                if (this.bkg != null) {
                    com_google_android_gms_internal_zzapz_zzd.bkg = (zzb) this.bkg.clone();
                }
                if (this.bkk != null) {
                    com_google_android_gms_internal_zzapz_zzd.bkk = (zza) this.bkk.clone();
                }
                if (this.bkn != null) {
                    com_google_android_gms_internal_zzapz_zzd.bkn = (zzc) this.bkn.clone();
                }
                if (this.bkr != null && this.bkr.length > 0) {
                    com_google_android_gms_internal_zzapz_zzd.bkr = (int[]) this.bkr.clone();
                }
                if (this.bkt != null) {
                    com_google_android_gms_internal_zzapz_zzd.bkt = (zzf) this.bkt.clone();
                }
                return com_google_android_gms_internal_zzapz_zzd;
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(e);
            }
        }

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            return aU();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzd)) {
                return false;
            }
            zzd com_google_android_gms_internal_zzapz_zzd = (zzd) obj;
            if (this.bka != com_google_android_gms_internal_zzapz_zzd.bka || this.bkb != com_google_android_gms_internal_zzapz_zzd.bkb || this.bkc != com_google_android_gms_internal_zzapz_zzd.bkc) {
                return false;
            }
            if (this.tag == null) {
                if (com_google_android_gms_internal_zzapz_zzd.tag != null) {
                    return false;
                }
            } else if (!this.tag.equals(com_google_android_gms_internal_zzapz_zzd.tag)) {
                return false;
            }
            if (this.bkd != com_google_android_gms_internal_zzapz_zzd.bkd || this.zzahl != com_google_android_gms_internal_zzapz_zzd.zzahl || this.aTs != com_google_android_gms_internal_zzapz_zzd.aTs || !zzapt.equals(this.bke, com_google_android_gms_internal_zzapz_zzd.bke) || !Arrays.equals(this.bkf, com_google_android_gms_internal_zzapz_zzd.bkf)) {
                return false;
            }
            if (this.bkg == null) {
                if (com_google_android_gms_internal_zzapz_zzd.bkg != null) {
                    return false;
                }
            } else if (!this.bkg.equals(com_google_android_gms_internal_zzapz_zzd.bkg)) {
                return false;
            }
            if (!Arrays.equals(this.bkh, com_google_android_gms_internal_zzapz_zzd.bkh)) {
                return false;
            }
            if (this.bki == null) {
                if (com_google_android_gms_internal_zzapz_zzd.bki != null) {
                    return false;
                }
            } else if (!this.bki.equals(com_google_android_gms_internal_zzapz_zzd.bki)) {
                return false;
            }
            if (this.bkj == null) {
                if (com_google_android_gms_internal_zzapz_zzd.bkj != null) {
                    return false;
                }
            } else if (!this.bkj.equals(com_google_android_gms_internal_zzapz_zzd.bkj)) {
                return false;
            }
            if (this.bkk == null) {
                if (com_google_android_gms_internal_zzapz_zzd.bkk != null) {
                    return false;
                }
            } else if (!this.bkk.equals(com_google_android_gms_internal_zzapz_zzd.bkk)) {
                return false;
            }
            if (this.bkl == null) {
                if (com_google_android_gms_internal_zzapz_zzd.bkl != null) {
                    return false;
                }
            } else if (!this.bkl.equals(com_google_android_gms_internal_zzapz_zzd.bkl)) {
                return false;
            }
            if (this.bkm != com_google_android_gms_internal_zzapz_zzd.bkm) {
                return false;
            }
            if (this.bkn == null) {
                if (com_google_android_gms_internal_zzapz_zzd.bkn != null) {
                    return false;
                }
            } else if (!this.bkn.equals(com_google_android_gms_internal_zzapz_zzd.bkn)) {
                return false;
            }
            if (!Arrays.equals(this.bko, com_google_android_gms_internal_zzapz_zzd.bko)) {
                return false;
            }
            if (this.bkp == null) {
                if (com_google_android_gms_internal_zzapz_zzd.bkp != null) {
                    return false;
                }
            } else if (!this.bkp.equals(com_google_android_gms_internal_zzapz_zzd.bkp)) {
                return false;
            }
            if (this.bkq != com_google_android_gms_internal_zzapz_zzd.bkq || !zzapt.equals(this.bkr, com_google_android_gms_internal_zzapz_zzd.bkr) || this.bks != com_google_android_gms_internal_zzapz_zzd.bks) {
                return false;
            }
            if (this.bkt == null) {
                if (com_google_android_gms_internal_zzapz_zzd.bkt != null) {
                    return false;
                }
            } else if (!this.bkt.equals(com_google_android_gms_internal_zzapz_zzd.bkt)) {
                return false;
            }
            return (this.bjx == null || this.bjx.isEmpty()) ? com_google_android_gms_internal_zzapz_zzd.bjx == null || com_google_android_gms_internal_zzapz_zzd.bjx.isEmpty() : this.bjx.equals(com_google_android_gms_internal_zzapz_zzd.bjx);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.bkt == null ? 0 : this.bkt.hashCode()) + (((((((((this.bkp == null ? 0 : this.bkp.hashCode()) + (((((this.bkn == null ? 0 : this.bkn.hashCode()) + (((((this.bkl == null ? 0 : this.bkl.hashCode()) + (((this.bkk == null ? 0 : this.bkk.hashCode()) + (((this.bkj == null ? 0 : this.bkj.hashCode()) + (((this.bki == null ? 0 : this.bki.hashCode()) + (((((this.bkg == null ? 0 : this.bkg.hashCode()) + (((((((this.aTs ? 1231 : 1237) + (((((((this.tag == null ? 0 : this.tag.hashCode()) + ((((((((getClass().getName().hashCode() + 527) * 31) + ((int) (this.bka ^ (this.bka >>> 32)))) * 31) + ((int) (this.bkb ^ (this.bkb >>> 32)))) * 31) + ((int) (this.bkc ^ (this.bkc >>> 32)))) * 31)) * 31) + this.bkd) * 31) + this.zzahl) * 31)) * 31) + zzapt.hashCode(this.bke)) * 31) + Arrays.hashCode(this.bkf)) * 31)) * 31) + Arrays.hashCode(this.bkh)) * 31)) * 31)) * 31)) * 31)) * 31) + ((int) (this.bkm ^ (this.bkm >>> 32)))) * 31)) * 31) + Arrays.hashCode(this.bko)) * 31)) * 31) + this.bkq) * 31) + zzapt.hashCode(this.bkr)) * 31) + ((int) (this.bks ^ (this.bks >>> 32)))) * 31)) * 31;
            if (!(this.bjx == null || this.bjx.isEmpty())) {
                i = this.bjx.hashCode();
            }
            return hashCode + i;
        }

        public void zza(zzapo com_google_android_gms_internal_zzapo) throws IOException {
            int i = 0;
            if (this.bka != 0) {
                com_google_android_gms_internal_zzapo.zzb(1, this.bka);
            }
            if (!this.tag.equals("")) {
                com_google_android_gms_internal_zzapo.zzr(2, this.tag);
            }
            if (this.bke != null && this.bke.length > 0) {
                for (zzapv com_google_android_gms_internal_zzapv : this.bke) {
                    if (com_google_android_gms_internal_zzapv != null) {
                        com_google_android_gms_internal_zzapo.zza(3, com_google_android_gms_internal_zzapv);
                    }
                }
            }
            if (!Arrays.equals(this.bkf, zzapy.bjO)) {
                com_google_android_gms_internal_zzapo.zza(4, this.bkf);
            }
            if (!Arrays.equals(this.bkh, zzapy.bjO)) {
                com_google_android_gms_internal_zzapo.zza(6, this.bkh);
            }
            if (this.bkk != null) {
                com_google_android_gms_internal_zzapo.zza(7, this.bkk);
            }
            if (!this.bki.equals("")) {
                com_google_android_gms_internal_zzapo.zzr(8, this.bki);
            }
            if (this.bkg != null) {
                com_google_android_gms_internal_zzapo.zza(9, this.bkg);
            }
            if (this.aTs) {
                com_google_android_gms_internal_zzapo.zzj(10, this.aTs);
            }
            if (this.bkd != 0) {
                com_google_android_gms_internal_zzapo.zzae(11, this.bkd);
            }
            if (this.zzahl != 0) {
                com_google_android_gms_internal_zzapo.zzae(12, this.zzahl);
            }
            if (!this.bkj.equals("")) {
                com_google_android_gms_internal_zzapo.zzr(13, this.bkj);
            }
            if (!this.bkl.equals("")) {
                com_google_android_gms_internal_zzapo.zzr(14, this.bkl);
            }
            if (this.bkm != 180000) {
                com_google_android_gms_internal_zzapo.zzd(15, this.bkm);
            }
            if (this.bkn != null) {
                com_google_android_gms_internal_zzapo.zza(16, this.bkn);
            }
            if (this.bkb != 0) {
                com_google_android_gms_internal_zzapo.zzb(17, this.bkb);
            }
            if (!Arrays.equals(this.bko, zzapy.bjO)) {
                com_google_android_gms_internal_zzapo.zza(18, this.bko);
            }
            if (this.bkq != 0) {
                com_google_android_gms_internal_zzapo.zzae(19, this.bkq);
            }
            if (this.bkr != null && this.bkr.length > 0) {
                while (i < this.bkr.length) {
                    com_google_android_gms_internal_zzapo.zzae(20, this.bkr[i]);
                    i++;
                }
            }
            if (this.bkc != 0) {
                com_google_android_gms_internal_zzapo.zzb(21, this.bkc);
            }
            if (this.bks != 0) {
                com_google_android_gms_internal_zzapo.zzb(22, this.bks);
            }
            if (this.bkt != null) {
                com_google_android_gms_internal_zzapo.zza(23, this.bkt);
            }
            if (!this.bkp.equals("")) {
                com_google_android_gms_internal_zzapo.zzr(24, this.bkp);
            }
            super.zza(com_google_android_gms_internal_zzapo);
        }

        public /* synthetic */ zzapv zzb(zzapn com_google_android_gms_internal_zzapn) throws IOException {
            return zzck(com_google_android_gms_internal_zzapn);
        }

        public zzd zzck(zzapn com_google_android_gms_internal_zzapn) throws IOException {
            while (true) {
                int ah = com_google_android_gms_internal_zzapn.ah();
                int zzc;
                Object obj;
                switch (ah) {
                    case 0:
                        break;
                    case 8:
                        this.bka = com_google_android_gms_internal_zzapn.ak();
                        continue;
                    case 18:
                        this.tag = com_google_android_gms_internal_zzapn.readString();
                        continue;
                    case 26:
                        zzc = zzapy.zzc(com_google_android_gms_internal_zzapn, 26);
                        ah = this.bke == null ? 0 : this.bke.length;
                        obj = new zze[(zzc + ah)];
                        if (ah != 0) {
                            System.arraycopy(this.bke, 0, obj, 0, ah);
                        }
                        while (ah < obj.length - 1) {
                            obj[ah] = new zze();
                            com_google_android_gms_internal_zzapn.zza(obj[ah]);
                            com_google_android_gms_internal_zzapn.ah();
                            ah++;
                        }
                        obj[ah] = new zze();
                        com_google_android_gms_internal_zzapn.zza(obj[ah]);
                        this.bke = obj;
                        continue;
                    case 34:
                        this.bkf = com_google_android_gms_internal_zzapn.readBytes();
                        continue;
                    case 50:
                        this.bkh = com_google_android_gms_internal_zzapn.readBytes();
                        continue;
                    case 58:
                        if (this.bkk == null) {
                            this.bkk = new zza();
                        }
                        com_google_android_gms_internal_zzapn.zza(this.bkk);
                        continue;
                    case 66:
                        this.bki = com_google_android_gms_internal_zzapn.readString();
                        continue;
                    case 74:
                        if (this.bkg == null) {
                            this.bkg = new zzb();
                        }
                        com_google_android_gms_internal_zzapn.zza(this.bkg);
                        continue;
                    case 80:
                        this.aTs = com_google_android_gms_internal_zzapn.an();
                        continue;
                    case 88:
                        this.bkd = com_google_android_gms_internal_zzapn.al();
                        continue;
                    case 96:
                        this.zzahl = com_google_android_gms_internal_zzapn.al();
                        continue;
                    case 106:
                        this.bkj = com_google_android_gms_internal_zzapn.readString();
                        continue;
                    case 114:
                        this.bkl = com_google_android_gms_internal_zzapn.readString();
                        continue;
                    case 120:
                        this.bkm = com_google_android_gms_internal_zzapn.ap();
                        continue;
                    case TransportMediator.KEYCODE_MEDIA_RECORD /*130*/:
                        if (this.bkn == null) {
                            this.bkn = new zzc();
                        }
                        com_google_android_gms_internal_zzapn.zza(this.bkn);
                        continue;
                    case 136:
                        this.bkb = com_google_android_gms_internal_zzapn.ak();
                        continue;
                    case 146:
                        this.bko = com_google_android_gms_internal_zzapn.readBytes();
                        continue;
                    case 152:
                        ah = com_google_android_gms_internal_zzapn.al();
                        switch (ah) {
                            case 0:
                            case 1:
                            case 2:
                                this.bkq = ah;
                                break;
                            default:
                                continue;
                        }
                    case 160:
                        zzc = zzapy.zzc(com_google_android_gms_internal_zzapn, 160);
                        ah = this.bkr == null ? 0 : this.bkr.length;
                        obj = new int[(zzc + ah)];
                        if (ah != 0) {
                            System.arraycopy(this.bkr, 0, obj, 0, ah);
                        }
                        while (ah < obj.length - 1) {
                            obj[ah] = com_google_android_gms_internal_zzapn.al();
                            com_google_android_gms_internal_zzapn.ah();
                            ah++;
                        }
                        obj[ah] = com_google_android_gms_internal_zzapn.al();
                        this.bkr = obj;
                        continue;
                    case 162:
                        int zzafr = com_google_android_gms_internal_zzapn.zzafr(com_google_android_gms_internal_zzapn.aq());
                        zzc = com_google_android_gms_internal_zzapn.getPosition();
                        ah = 0;
                        while (com_google_android_gms_internal_zzapn.av() > 0) {
                            com_google_android_gms_internal_zzapn.al();
                            ah++;
                        }
                        com_google_android_gms_internal_zzapn.zzaft(zzc);
                        zzc = this.bkr == null ? 0 : this.bkr.length;
                        Object obj2 = new int[(ah + zzc)];
                        if (zzc != 0) {
                            System.arraycopy(this.bkr, 0, obj2, 0, zzc);
                        }
                        while (zzc < obj2.length) {
                            obj2[zzc] = com_google_android_gms_internal_zzapn.al();
                            zzc++;
                        }
                        this.bkr = obj2;
                        com_google_android_gms_internal_zzapn.zzafs(zzafr);
                        continue;
                    case 168:
                        this.bkc = com_google_android_gms_internal_zzapn.ak();
                        continue;
                    case 176:
                        this.bks = com_google_android_gms_internal_zzapn.ak();
                        continue;
                    case 186:
                        if (this.bkt == null) {
                            this.bkt = new zzf();
                        }
                        com_google_android_gms_internal_zzapn.zza(this.bkt);
                        continue;
                    case 194:
                        this.bkp = com_google_android_gms_internal_zzapn.readString();
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
            int zzx = super.zzx();
            if (this.bka != 0) {
                zzx += zzapo.zze(1, this.bka);
            }
            if (!this.tag.equals("")) {
                zzx += zzapo.zzs(2, this.tag);
            }
            if (this.bke != null && this.bke.length > 0) {
                i = zzx;
                for (zzapv com_google_android_gms_internal_zzapv : this.bke) {
                    if (com_google_android_gms_internal_zzapv != null) {
                        i += zzapo.zzc(3, com_google_android_gms_internal_zzapv);
                    }
                }
                zzx = i;
            }
            if (!Arrays.equals(this.bkf, zzapy.bjO)) {
                zzx += zzapo.zzb(4, this.bkf);
            }
            if (!Arrays.equals(this.bkh, zzapy.bjO)) {
                zzx += zzapo.zzb(6, this.bkh);
            }
            if (this.bkk != null) {
                zzx += zzapo.zzc(7, this.bkk);
            }
            if (!this.bki.equals("")) {
                zzx += zzapo.zzs(8, this.bki);
            }
            if (this.bkg != null) {
                zzx += zzapo.zzc(9, this.bkg);
            }
            if (this.aTs) {
                zzx += zzapo.zzk(10, this.aTs);
            }
            if (this.bkd != 0) {
                zzx += zzapo.zzag(11, this.bkd);
            }
            if (this.zzahl != 0) {
                zzx += zzapo.zzag(12, this.zzahl);
            }
            if (!this.bkj.equals("")) {
                zzx += zzapo.zzs(13, this.bkj);
            }
            if (!this.bkl.equals("")) {
                zzx += zzapo.zzs(14, this.bkl);
            }
            if (this.bkm != 180000) {
                zzx += zzapo.zzg(15, this.bkm);
            }
            if (this.bkn != null) {
                zzx += zzapo.zzc(16, this.bkn);
            }
            if (this.bkb != 0) {
                zzx += zzapo.zze(17, this.bkb);
            }
            if (!Arrays.equals(this.bko, zzapy.bjO)) {
                zzx += zzapo.zzb(18, this.bko);
            }
            if (this.bkq != 0) {
                zzx += zzapo.zzag(19, this.bkq);
            }
            if (this.bkr != null && this.bkr.length > 0) {
                i = 0;
                while (i2 < this.bkr.length) {
                    i += zzapo.zzafx(this.bkr[i2]);
                    i2++;
                }
                zzx = (zzx + i) + (this.bkr.length * 2);
            }
            if (this.bkc != 0) {
                zzx += zzapo.zze(21, this.bkc);
            }
            if (this.bks != 0) {
                zzx += zzapo.zze(22, this.bks);
            }
            if (this.bkt != null) {
                zzx += zzapo.zzc(23, this.bkt);
            }
            return !this.bkp.equals("") ? zzx + zzapo.zzs(24, this.bkp) : zzx;
        }
    }

    public static final class zze extends zzapp<zze> implements Cloneable {
        private static volatile zze[] bku;
        public String value;
        public String zzcb;

        public zze() {
            aW();
        }

        public static zze[] aV() {
            if (bku == null) {
                synchronized (zzapt.bjF) {
                    if (bku == null) {
                        bku = new zze[0];
                    }
                }
            }
            return bku;
        }

        public /* synthetic */ zzapp aA() throws CloneNotSupportedException {
            return (zze) clone();
        }

        public /* synthetic */ zzapv aB() throws CloneNotSupportedException {
            return (zze) clone();
        }

        public zze aW() {
            this.zzcb = "";
            this.value = "";
            this.bjx = null;
            this.bjG = -1;
            return this;
        }

        public zze aX() {
            try {
                return (zze) super.aA();
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(e);
            }
        }

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            return aX();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zze)) {
                return false;
            }
            zze com_google_android_gms_internal_zzapz_zze = (zze) obj;
            if (this.zzcb == null) {
                if (com_google_android_gms_internal_zzapz_zze.zzcb != null) {
                    return false;
                }
            } else if (!this.zzcb.equals(com_google_android_gms_internal_zzapz_zze.zzcb)) {
                return false;
            }
            if (this.value == null) {
                if (com_google_android_gms_internal_zzapz_zze.value != null) {
                    return false;
                }
            } else if (!this.value.equals(com_google_android_gms_internal_zzapz_zze.value)) {
                return false;
            }
            return (this.bjx == null || this.bjx.isEmpty()) ? com_google_android_gms_internal_zzapz_zze.bjx == null || com_google_android_gms_internal_zzapz_zze.bjx.isEmpty() : this.bjx.equals(com_google_android_gms_internal_zzapz_zze.bjx);
        }

        public int hashCode() {
            int i = 0;
            int hashCode = ((this.value == null ? 0 : this.value.hashCode()) + (((this.zzcb == null ? 0 : this.zzcb.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31;
            if (!(this.bjx == null || this.bjx.isEmpty())) {
                i = this.bjx.hashCode();
            }
            return hashCode + i;
        }

        public void zza(zzapo com_google_android_gms_internal_zzapo) throws IOException {
            if (!this.zzcb.equals("")) {
                com_google_android_gms_internal_zzapo.zzr(1, this.zzcb);
            }
            if (!this.value.equals("")) {
                com_google_android_gms_internal_zzapo.zzr(2, this.value);
            }
            super.zza(com_google_android_gms_internal_zzapo);
        }

        public /* synthetic */ zzapv zzb(zzapn com_google_android_gms_internal_zzapn) throws IOException {
            return zzcl(com_google_android_gms_internal_zzapn);
        }

        public zze zzcl(zzapn com_google_android_gms_internal_zzapn) throws IOException {
            while (true) {
                int ah = com_google_android_gms_internal_zzapn.ah();
                switch (ah) {
                    case 0:
                        break;
                    case 10:
                        this.zzcb = com_google_android_gms_internal_zzapn.readString();
                        continue;
                    case 18:
                        this.value = com_google_android_gms_internal_zzapn.readString();
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
            if (!this.zzcb.equals("")) {
                zzx += zzapo.zzs(1, this.zzcb);
            }
            return !this.value.equals("") ? zzx + zzapo.zzs(2, this.value) : zzx;
        }
    }

    public static final class zzf extends zzapp<zzf> implements Cloneable {
        public int bkv;

        public zzf() {
            aY();
        }

        public /* synthetic */ zzapp aA() throws CloneNotSupportedException {
            return (zzf) clone();
        }

        public /* synthetic */ zzapv aB() throws CloneNotSupportedException {
            return (zzf) clone();
        }

        public zzf aY() {
            this.bkv = -1;
            this.bjx = null;
            this.bjG = -1;
            return this;
        }

        public zzf aZ() {
            try {
                return (zzf) super.aA();
            } catch (CloneNotSupportedException e) {
                throw new AssertionError(e);
            }
        }

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            return aZ();
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (!(obj instanceof zzf)) {
                return false;
            }
            zzf com_google_android_gms_internal_zzapz_zzf = (zzf) obj;
            return this.bkv == com_google_android_gms_internal_zzapz_zzf.bkv ? (this.bjx == null || this.bjx.isEmpty()) ? com_google_android_gms_internal_zzapz_zzf.bjx == null || com_google_android_gms_internal_zzapz_zzf.bjx.isEmpty() : this.bjx.equals(com_google_android_gms_internal_zzapz_zzf.bjx) : false;
        }

        public int hashCode() {
            int hashCode = (((getClass().getName().hashCode() + 527) * 31) + this.bkv) * 31;
            int hashCode2 = (this.bjx == null || this.bjx.isEmpty()) ? 0 : this.bjx.hashCode();
            return hashCode2 + hashCode;
        }

        public void zza(zzapo com_google_android_gms_internal_zzapo) throws IOException {
            if (this.bkv != -1) {
                com_google_android_gms_internal_zzapo.zzae(1, this.bkv);
            }
            super.zza(com_google_android_gms_internal_zzapo);
        }

        public /* synthetic */ zzapv zzb(zzapn com_google_android_gms_internal_zzapn) throws IOException {
            return zzcm(com_google_android_gms_internal_zzapn);
        }

        public zzf zzcm(zzapn com_google_android_gms_internal_zzapn) throws IOException {
            while (true) {
                int ah = com_google_android_gms_internal_zzapn.ah();
                switch (ah) {
                    case 0:
                        break;
                    case 8:
                        ah = com_google_android_gms_internal_zzapn.al();
                        switch (ah) {
                            case -1:
                            case 0:
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
                                this.bkv = ah;
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
            int zzx = super.zzx();
            return this.bkv != -1 ? zzx + zzapo.zzag(1, this.bkv) : zzx;
        }
    }
}

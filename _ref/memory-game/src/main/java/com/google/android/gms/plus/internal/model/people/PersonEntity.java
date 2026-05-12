package com.google.android.gms.plus.internal.model.people;

import android.os.Parcel;
import com.facebook.internal.FacebookRequestErrorClassification;
import com.facebook.places.model.PlaceFields;
import com.google.android.gms.common.server.converter.StringToIntConverter;
import com.google.android.gms.common.server.response.FastJsonResponse.Field;
import com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.plus.model.people.Person.AgeRange;
import com.google.android.gms.plus.model.people.Person.Cover;
import com.google.android.gms.plus.model.people.Person.Cover.CoverInfo;
import com.google.android.gms.plus.model.people.Person.Cover.CoverPhoto;
import com.google.android.gms.plus.model.people.Person.Image;
import com.google.android.gms.plus.model.people.Person.Name;
import com.google.android.gms.plus.model.people.Person.Organizations;
import com.google.android.gms.plus.model.people.Person.PlacesLived;
import com.google.android.gms.plus.model.people.Person.Urls;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class PersonEntity extends FastSafeParcelableJsonResponse implements Person {
    public static final zza CREATOR = new zza();
    private static final HashMap<String, Field<?, ?>> asf = new HashMap();
    final Set<Integer> asg;
    String ash;
    AgeRangeEntity asi;
    String asj;
    String ask;
    int asl;
    CoverEntity asm;
    String asn;
    ImageEntity aso;
    boolean asp;
    NameEntity asq;
    String asr;
    int ass;
    List<OrganizationsEntity> ast;
    List<PlacesLivedEntity> asu;
    int asv;
    int asw;
    String asx;
    List<UrlsEntity> asy;
    boolean asz;
    String dC;
    final int mVersionCode;
    String zzae;
    int zzaud;
    String zzbgg;
    String zzcuy;

    public static final class AgeRangeEntity extends FastSafeParcelableJsonResponse implements AgeRange {
        public static final zzb CREATOR = new zzb();
        private static final HashMap<String, Field<?, ?>> asf = new HashMap();
        int asA;
        int asB;
        final Set<Integer> asg;
        final int mVersionCode;

        static {
            asf.put("max", Field.zzj("max", 2));
            asf.put("min", Field.zzj("min", 3));
        }

        public AgeRangeEntity() {
            this.mVersionCode = 1;
            this.asg = new HashSet();
        }

        AgeRangeEntity(Set<Integer> set, int i, int i2, int i3) {
            this.asg = set;
            this.mVersionCode = i;
            this.asA = i2;
            this.asB = i3;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof AgeRangeEntity)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            AgeRangeEntity ageRangeEntity = (AgeRangeEntity) obj;
            for (Field field : asf.values()) {
                if (zza(field)) {
                    if (!ageRangeEntity.zza(field)) {
                        return false;
                    }
                    if (!zzb(field).equals(ageRangeEntity.zzb(field))) {
                        return false;
                    }
                } else if (ageRangeEntity.zza(field)) {
                    return false;
                }
            }
            return true;
        }

        public /* synthetic */ Object freeze() {
            return zzbyp();
        }

        public int getMax() {
            return this.asA;
        }

        public int getMin() {
            return this.asB;
        }

        public boolean hasMax() {
            return this.asg.contains(Integer.valueOf(2));
        }

        public boolean hasMin() {
            return this.asg.contains(Integer.valueOf(3));
        }

        public int hashCode() {
            int i = 0;
            for (Field field : asf.values()) {
                int hashCode;
                if (zza(field)) {
                    hashCode = zzb(field).hashCode() + (i + field.zzaub());
                } else {
                    hashCode = i;
                }
                i = hashCode;
            }
            return i;
        }

        public boolean isDataValid() {
            return true;
        }

        public void writeToParcel(Parcel parcel, int i) {
            zzb com_google_android_gms_plus_internal_model_people_zzb = CREATOR;
            zzb.zza(this, parcel, i);
        }

        protected boolean zza(Field field) {
            return this.asg.contains(Integer.valueOf(field.zzaub()));
        }

        public /* synthetic */ Map zzatv() {
            return zzbyn();
        }

        protected Object zzb(Field field) {
            switch (field.zzaub()) {
                case 2:
                    return Integer.valueOf(this.asA);
                case 3:
                    return Integer.valueOf(this.asB);
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + field.zzaub());
            }
        }

        public HashMap<String, Field<?, ?>> zzbyn() {
            return asf;
        }

        public AgeRangeEntity zzbyp() {
            return this;
        }
    }

    public static final class CoverEntity extends FastSafeParcelableJsonResponse implements Cover {
        public static final zzc CREATOR = new zzc();
        private static final HashMap<String, Field<?, ?>> asf = new HashMap();
        CoverInfoEntity asC;
        CoverPhotoEntity asD;
        int asE;
        final Set<Integer> asg;
        final int mVersionCode;

        public static final class CoverInfoEntity extends FastSafeParcelableJsonResponse implements CoverInfo {
            public static final zzd CREATOR = new zzd();
            private static final HashMap<String, Field<?, ?>> asf = new HashMap();
            int asF;
            int asG;
            final Set<Integer> asg;
            final int mVersionCode;

            static {
                asf.put("leftImageOffset", Field.zzj("leftImageOffset", 2));
                asf.put("topImageOffset", Field.zzj("topImageOffset", 3));
            }

            public CoverInfoEntity() {
                this.mVersionCode = 1;
                this.asg = new HashSet();
            }

            CoverInfoEntity(Set<Integer> set, int i, int i2, int i3) {
                this.asg = set;
                this.mVersionCode = i;
                this.asF = i2;
                this.asG = i3;
            }

            public boolean equals(Object obj) {
                if (!(obj instanceof CoverInfoEntity)) {
                    return false;
                }
                if (this == obj) {
                    return true;
                }
                CoverInfoEntity coverInfoEntity = (CoverInfoEntity) obj;
                for (Field field : asf.values()) {
                    if (zza(field)) {
                        if (!coverInfoEntity.zza(field)) {
                            return false;
                        }
                        if (!zzb(field).equals(coverInfoEntity.zzb(field))) {
                            return false;
                        }
                    } else if (coverInfoEntity.zza(field)) {
                        return false;
                    }
                }
                return true;
            }

            public /* synthetic */ Object freeze() {
                return zzbyr();
            }

            public int getLeftImageOffset() {
                return this.asF;
            }

            public int getTopImageOffset() {
                return this.asG;
            }

            public boolean hasLeftImageOffset() {
                return this.asg.contains(Integer.valueOf(2));
            }

            public boolean hasTopImageOffset() {
                return this.asg.contains(Integer.valueOf(3));
            }

            public int hashCode() {
                int i = 0;
                for (Field field : asf.values()) {
                    int hashCode;
                    if (zza(field)) {
                        hashCode = zzb(field).hashCode() + (i + field.zzaub());
                    } else {
                        hashCode = i;
                    }
                    i = hashCode;
                }
                return i;
            }

            public boolean isDataValid() {
                return true;
            }

            public void writeToParcel(Parcel parcel, int i) {
                zzd com_google_android_gms_plus_internal_model_people_zzd = CREATOR;
                zzd.zza(this, parcel, i);
            }

            protected boolean zza(Field field) {
                return this.asg.contains(Integer.valueOf(field.zzaub()));
            }

            public /* synthetic */ Map zzatv() {
                return zzbyn();
            }

            protected Object zzb(Field field) {
                switch (field.zzaub()) {
                    case 2:
                        return Integer.valueOf(this.asF);
                    case 3:
                        return Integer.valueOf(this.asG);
                    default:
                        throw new IllegalStateException("Unknown safe parcelable id=" + field.zzaub());
                }
            }

            public HashMap<String, Field<?, ?>> zzbyn() {
                return asf;
            }

            public CoverInfoEntity zzbyr() {
                return this;
            }
        }

        public static final class CoverPhotoEntity extends FastSafeParcelableJsonResponse implements CoverPhoto {
            public static final zze CREATOR = new zze();
            private static final HashMap<String, Field<?, ?>> asf = new HashMap();
            final Set<Integer> asg;
            final int mVersionCode;
            String zzae;
            int zzaie;
            int zzaif;

            static {
                asf.put("height", Field.zzj("height", 2));
                asf.put("url", Field.zzl("url", 3));
                asf.put("width", Field.zzj("width", 4));
            }

            public CoverPhotoEntity() {
                this.mVersionCode = 1;
                this.asg = new HashSet();
            }

            CoverPhotoEntity(Set<Integer> set, int i, int i2, String str, int i3) {
                this.asg = set;
                this.mVersionCode = i;
                this.zzaif = i2;
                this.zzae = str;
                this.zzaie = i3;
            }

            public boolean equals(Object obj) {
                if (!(obj instanceof CoverPhotoEntity)) {
                    return false;
                }
                if (this == obj) {
                    return true;
                }
                CoverPhotoEntity coverPhotoEntity = (CoverPhotoEntity) obj;
                for (Field field : asf.values()) {
                    if (zza(field)) {
                        if (!coverPhotoEntity.zza(field)) {
                            return false;
                        }
                        if (!zzb(field).equals(coverPhotoEntity.zzb(field))) {
                            return false;
                        }
                    } else if (coverPhotoEntity.zza(field)) {
                        return false;
                    }
                }
                return true;
            }

            public /* synthetic */ Object freeze() {
                return zzbys();
            }

            public int getHeight() {
                return this.zzaif;
            }

            public String getUrl() {
                return this.zzae;
            }

            public int getWidth() {
                return this.zzaie;
            }

            public boolean hasHeight() {
                return this.asg.contains(Integer.valueOf(2));
            }

            public boolean hasUrl() {
                return this.asg.contains(Integer.valueOf(3));
            }

            public boolean hasWidth() {
                return this.asg.contains(Integer.valueOf(4));
            }

            public int hashCode() {
                int i = 0;
                for (Field field : asf.values()) {
                    int hashCode;
                    if (zza(field)) {
                        hashCode = zzb(field).hashCode() + (i + field.zzaub());
                    } else {
                        hashCode = i;
                    }
                    i = hashCode;
                }
                return i;
            }

            public boolean isDataValid() {
                return true;
            }

            public void writeToParcel(Parcel parcel, int i) {
                zze com_google_android_gms_plus_internal_model_people_zze = CREATOR;
                zze.zza(this, parcel, i);
            }

            protected boolean zza(Field field) {
                return this.asg.contains(Integer.valueOf(field.zzaub()));
            }

            public /* synthetic */ Map zzatv() {
                return zzbyn();
            }

            protected Object zzb(Field field) {
                switch (field.zzaub()) {
                    case 2:
                        return Integer.valueOf(this.zzaif);
                    case 3:
                        return this.zzae;
                    case 4:
                        return Integer.valueOf(this.zzaie);
                    default:
                        throw new IllegalStateException("Unknown safe parcelable id=" + field.zzaub());
                }
            }

            public HashMap<String, Field<?, ?>> zzbyn() {
                return asf;
            }

            public CoverPhotoEntity zzbys() {
                return this;
            }
        }

        static {
            asf.put("coverInfo", Field.zza("coverInfo", 2, CoverInfoEntity.class));
            asf.put("coverPhoto", Field.zza("coverPhoto", 3, CoverPhotoEntity.class));
            asf.put("layout", Field.zza("layout", 4, new StringToIntConverter().zzi("banner", 0), false));
        }

        public CoverEntity() {
            this.mVersionCode = 1;
            this.asg = new HashSet();
        }

        CoverEntity(Set<Integer> set, int i, CoverInfoEntity coverInfoEntity, CoverPhotoEntity coverPhotoEntity, int i2) {
            this.asg = set;
            this.mVersionCode = i;
            this.asC = coverInfoEntity;
            this.asD = coverPhotoEntity;
            this.asE = i2;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof CoverEntity)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            CoverEntity coverEntity = (CoverEntity) obj;
            for (Field field : asf.values()) {
                if (zza(field)) {
                    if (!coverEntity.zza(field)) {
                        return false;
                    }
                    if (!zzb(field).equals(coverEntity.zzb(field))) {
                        return false;
                    }
                } else if (coverEntity.zza(field)) {
                    return false;
                }
            }
            return true;
        }

        public /* synthetic */ Object freeze() {
            return zzbyq();
        }

        public CoverInfo getCoverInfo() {
            return this.asC;
        }

        public CoverPhoto getCoverPhoto() {
            return this.asD;
        }

        public int getLayout() {
            return this.asE;
        }

        public boolean hasCoverInfo() {
            return this.asg.contains(Integer.valueOf(2));
        }

        public boolean hasCoverPhoto() {
            return this.asg.contains(Integer.valueOf(3));
        }

        public boolean hasLayout() {
            return this.asg.contains(Integer.valueOf(4));
        }

        public int hashCode() {
            int i = 0;
            for (Field field : asf.values()) {
                int hashCode;
                if (zza(field)) {
                    hashCode = zzb(field).hashCode() + (i + field.zzaub());
                } else {
                    hashCode = i;
                }
                i = hashCode;
            }
            return i;
        }

        public boolean isDataValid() {
            return true;
        }

        public void writeToParcel(Parcel parcel, int i) {
            zzc com_google_android_gms_plus_internal_model_people_zzc = CREATOR;
            zzc.zza(this, parcel, i);
        }

        protected boolean zza(Field field) {
            return this.asg.contains(Integer.valueOf(field.zzaub()));
        }

        public /* synthetic */ Map zzatv() {
            return zzbyn();
        }

        protected Object zzb(Field field) {
            switch (field.zzaub()) {
                case 2:
                    return this.asC;
                case 3:
                    return this.asD;
                case 4:
                    return Integer.valueOf(this.asE);
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + field.zzaub());
            }
        }

        public HashMap<String, Field<?, ?>> zzbyn() {
            return asf;
        }

        public CoverEntity zzbyq() {
            return this;
        }
    }

    public static final class ImageEntity extends FastSafeParcelableJsonResponse implements Image {
        public static final zzf CREATOR = new zzf();
        private static final HashMap<String, Field<?, ?>> asf = new HashMap();
        final Set<Integer> asg;
        final int mVersionCode;
        String zzae;

        static {
            asf.put("url", Field.zzl("url", 2));
        }

        public ImageEntity() {
            this.mVersionCode = 1;
            this.asg = new HashSet();
        }

        public ImageEntity(String str) {
            this.asg = new HashSet();
            this.mVersionCode = 1;
            this.zzae = str;
            this.asg.add(Integer.valueOf(2));
        }

        ImageEntity(Set<Integer> set, int i, String str) {
            this.asg = set;
            this.mVersionCode = i;
            this.zzae = str;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ImageEntity)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            ImageEntity imageEntity = (ImageEntity) obj;
            for (Field field : asf.values()) {
                if (zza(field)) {
                    if (!imageEntity.zza(field)) {
                        return false;
                    }
                    if (!zzb(field).equals(imageEntity.zzb(field))) {
                        return false;
                    }
                } else if (imageEntity.zza(field)) {
                    return false;
                }
            }
            return true;
        }

        public /* synthetic */ Object freeze() {
            return zzbyt();
        }

        public String getUrl() {
            return this.zzae;
        }

        public boolean hasUrl() {
            return this.asg.contains(Integer.valueOf(2));
        }

        public int hashCode() {
            int i = 0;
            for (Field field : asf.values()) {
                int hashCode;
                if (zza(field)) {
                    hashCode = zzb(field).hashCode() + (i + field.zzaub());
                } else {
                    hashCode = i;
                }
                i = hashCode;
            }
            return i;
        }

        public boolean isDataValid() {
            return true;
        }

        public void writeToParcel(Parcel parcel, int i) {
            zzf com_google_android_gms_plus_internal_model_people_zzf = CREATOR;
            zzf.zza(this, parcel, i);
        }

        protected boolean zza(Field field) {
            return this.asg.contains(Integer.valueOf(field.zzaub()));
        }

        public /* synthetic */ Map zzatv() {
            return zzbyn();
        }

        protected Object zzb(Field field) {
            switch (field.zzaub()) {
                case 2:
                    return this.zzae;
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + field.zzaub());
            }
        }

        public HashMap<String, Field<?, ?>> zzbyn() {
            return asf;
        }

        public ImageEntity zzbyt() {
            return this;
        }
    }

    public static final class NameEntity extends FastSafeParcelableJsonResponse implements Name {
        public static final zzg CREATOR = new zzg();
        private static final HashMap<String, Field<?, ?>> asf = new HashMap();
        String asH;
        String asI;
        String asJ;
        String asK;
        final Set<Integer> asg;
        String dH;
        String dI;
        final int mVersionCode;

        static {
            asf.put("familyName", Field.zzl("familyName", 2));
            asf.put("formatted", Field.zzl("formatted", 3));
            asf.put("givenName", Field.zzl("givenName", 4));
            asf.put("honorificPrefix", Field.zzl("honorificPrefix", 5));
            asf.put("honorificSuffix", Field.zzl("honorificSuffix", 6));
            asf.put("middleName", Field.zzl("middleName", 7));
        }

        public NameEntity() {
            this.mVersionCode = 1;
            this.asg = new HashSet();
        }

        NameEntity(Set<Integer> set, int i, String str, String str2, String str3, String str4, String str5, String str6) {
            this.asg = set;
            this.mVersionCode = i;
            this.dI = str;
            this.asH = str2;
            this.dH = str3;
            this.asI = str4;
            this.asJ = str5;
            this.asK = str6;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof NameEntity)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            NameEntity nameEntity = (NameEntity) obj;
            for (Field field : asf.values()) {
                if (zza(field)) {
                    if (!nameEntity.zza(field)) {
                        return false;
                    }
                    if (!zzb(field).equals(nameEntity.zzb(field))) {
                        return false;
                    }
                } else if (nameEntity.zza(field)) {
                    return false;
                }
            }
            return true;
        }

        public /* synthetic */ Object freeze() {
            return zzbyu();
        }

        public String getFamilyName() {
            return this.dI;
        }

        public String getFormatted() {
            return this.asH;
        }

        public String getGivenName() {
            return this.dH;
        }

        public String getHonorificPrefix() {
            return this.asI;
        }

        public String getHonorificSuffix() {
            return this.asJ;
        }

        public String getMiddleName() {
            return this.asK;
        }

        public boolean hasFamilyName() {
            return this.asg.contains(Integer.valueOf(2));
        }

        public boolean hasFormatted() {
            return this.asg.contains(Integer.valueOf(3));
        }

        public boolean hasGivenName() {
            return this.asg.contains(Integer.valueOf(4));
        }

        public boolean hasHonorificPrefix() {
            return this.asg.contains(Integer.valueOf(5));
        }

        public boolean hasHonorificSuffix() {
            return this.asg.contains(Integer.valueOf(6));
        }

        public boolean hasMiddleName() {
            return this.asg.contains(Integer.valueOf(7));
        }

        public int hashCode() {
            int i = 0;
            for (Field field : asf.values()) {
                int hashCode;
                if (zza(field)) {
                    hashCode = zzb(field).hashCode() + (i + field.zzaub());
                } else {
                    hashCode = i;
                }
                i = hashCode;
            }
            return i;
        }

        public boolean isDataValid() {
            return true;
        }

        public void writeToParcel(Parcel parcel, int i) {
            zzg com_google_android_gms_plus_internal_model_people_zzg = CREATOR;
            zzg.zza(this, parcel, i);
        }

        protected boolean zza(Field field) {
            return this.asg.contains(Integer.valueOf(field.zzaub()));
        }

        public /* synthetic */ Map zzatv() {
            return zzbyn();
        }

        protected Object zzb(Field field) {
            switch (field.zzaub()) {
                case 2:
                    return this.dI;
                case 3:
                    return this.asH;
                case 4:
                    return this.dH;
                case 5:
                    return this.asI;
                case 6:
                    return this.asJ;
                case 7:
                    return this.asK;
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + field.zzaub());
            }
        }

        public HashMap<String, Field<?, ?>> zzbyn() {
            return asf;
        }

        public NameEntity zzbyu() {
            return this;
        }
    }

    public static final class OrganizationsEntity extends FastSafeParcelableJsonResponse implements Organizations {
        public static final zzh CREATOR = new zzh();
        private static final HashMap<String, Field<?, ?>> asf = new HashMap();
        String DZ;
        String asL;
        String asM;
        String asN;
        boolean asO;
        String asP;
        final Set<Integer> asg;
        int iq;
        String mName;
        final int mVersionCode;
        String zzcvf;

        static {
            asf.put("department", Field.zzl("department", 2));
            asf.put("description", Field.zzl("description", 3));
            asf.put("endDate", Field.zzl("endDate", 4));
            asf.put("location", Field.zzl("location", 5));
            asf.put("name", Field.zzl("name", 6));
            asf.put("primary", Field.zzk("primary", 7));
            asf.put("startDate", Field.zzl("startDate", 8));
            asf.put("title", Field.zzl("title", 9));
            asf.put("type", Field.zza("type", 10, new StringToIntConverter().zzi("work", 0).zzi("school", 1), false));
        }

        public OrganizationsEntity() {
            this.mVersionCode = 1;
            this.asg = new HashSet();
        }

        OrganizationsEntity(Set<Integer> set, int i, String str, String str2, String str3, String str4, String str5, boolean z, String str6, String str7, int i2) {
            this.asg = set;
            this.mVersionCode = i;
            this.asL = str;
            this.zzcvf = str2;
            this.asM = str3;
            this.asN = str4;
            this.mName = str5;
            this.asO = z;
            this.asP = str6;
            this.DZ = str7;
            this.iq = i2;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof OrganizationsEntity)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            OrganizationsEntity organizationsEntity = (OrganizationsEntity) obj;
            for (Field field : asf.values()) {
                if (zza(field)) {
                    if (!organizationsEntity.zza(field)) {
                        return false;
                    }
                    if (!zzb(field).equals(organizationsEntity.zzb(field))) {
                        return false;
                    }
                } else if (organizationsEntity.zza(field)) {
                    return false;
                }
            }
            return true;
        }

        public /* synthetic */ Object freeze() {
            return zzbyv();
        }

        public String getDepartment() {
            return this.asL;
        }

        public String getDescription() {
            return this.zzcvf;
        }

        public String getEndDate() {
            return this.asM;
        }

        public String getLocation() {
            return this.asN;
        }

        public String getName() {
            return this.mName;
        }

        public String getStartDate() {
            return this.asP;
        }

        public String getTitle() {
            return this.DZ;
        }

        public int getType() {
            return this.iq;
        }

        public boolean hasDepartment() {
            return this.asg.contains(Integer.valueOf(2));
        }

        public boolean hasDescription() {
            return this.asg.contains(Integer.valueOf(3));
        }

        public boolean hasEndDate() {
            return this.asg.contains(Integer.valueOf(4));
        }

        public boolean hasLocation() {
            return this.asg.contains(Integer.valueOf(5));
        }

        public boolean hasName() {
            return this.asg.contains(Integer.valueOf(6));
        }

        public boolean hasPrimary() {
            return this.asg.contains(Integer.valueOf(7));
        }

        public boolean hasStartDate() {
            return this.asg.contains(Integer.valueOf(8));
        }

        public boolean hasTitle() {
            return this.asg.contains(Integer.valueOf(9));
        }

        public boolean hasType() {
            return this.asg.contains(Integer.valueOf(10));
        }

        public int hashCode() {
            int i = 0;
            for (Field field : asf.values()) {
                int hashCode;
                if (zza(field)) {
                    hashCode = zzb(field).hashCode() + (i + field.zzaub());
                } else {
                    hashCode = i;
                }
                i = hashCode;
            }
            return i;
        }

        public boolean isDataValid() {
            return true;
        }

        public boolean isPrimary() {
            return this.asO;
        }

        public void writeToParcel(Parcel parcel, int i) {
            zzh com_google_android_gms_plus_internal_model_people_zzh = CREATOR;
            zzh.zza(this, parcel, i);
        }

        protected boolean zza(Field field) {
            return this.asg.contains(Integer.valueOf(field.zzaub()));
        }

        public /* synthetic */ Map zzatv() {
            return zzbyn();
        }

        protected Object zzb(Field field) {
            switch (field.zzaub()) {
                case 2:
                    return this.asL;
                case 3:
                    return this.zzcvf;
                case 4:
                    return this.asM;
                case 5:
                    return this.asN;
                case 6:
                    return this.mName;
                case 7:
                    return Boolean.valueOf(this.asO);
                case 8:
                    return this.asP;
                case 9:
                    return this.DZ;
                case 10:
                    return Integer.valueOf(this.iq);
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + field.zzaub());
            }
        }

        public HashMap<String, Field<?, ?>> zzbyn() {
            return asf;
        }

        public OrganizationsEntity zzbyv() {
            return this;
        }
    }

    public static final class PlacesLivedEntity extends FastSafeParcelableJsonResponse implements PlacesLived {
        public static final zzi CREATOR = new zzi();
        private static final HashMap<String, Field<?, ?>> asf = new HashMap();
        boolean asO;
        final Set<Integer> asg;
        String mValue;
        final int mVersionCode;

        static {
            asf.put("primary", Field.zzk("primary", 2));
            asf.put(Param.VALUE, Field.zzl(Param.VALUE, 3));
        }

        public PlacesLivedEntity() {
            this.mVersionCode = 1;
            this.asg = new HashSet();
        }

        PlacesLivedEntity(Set<Integer> set, int i, boolean z, String str) {
            this.asg = set;
            this.mVersionCode = i;
            this.asO = z;
            this.mValue = str;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof PlacesLivedEntity)) {
                return false;
            }
            if (this == obj) {
                return true;
            }
            PlacesLivedEntity placesLivedEntity = (PlacesLivedEntity) obj;
            for (Field field : asf.values()) {
                if (zza(field)) {
                    if (!placesLivedEntity.zza(field)) {
                        return false;
                    }
                    if (!zzb(field).equals(placesLivedEntity.zzb(field))) {
                        return false;
                    }
                } else if (placesLivedEntity.zza(field)) {
                    return false;
                }
            }
            return true;
        }

        public /* synthetic */ Object freeze() {
            return zzbyw();
        }

        public String getValue() {
            return this.mValue;
        }

        public boolean hasPrimary() {
            return this.asg.contains(Integer.valueOf(2));
        }

        public boolean hasValue() {
            return this.asg.contains(Integer.valueOf(3));
        }

        public int hashCode() {
            int i = 0;
            for (Field field : asf.values()) {
                int hashCode;
                if (zza(field)) {
                    hashCode = zzb(field).hashCode() + (i + field.zzaub());
                } else {
                    hashCode = i;
                }
                i = hashCode;
            }
            return i;
        }

        public boolean isDataValid() {
            return true;
        }

        public boolean isPrimary() {
            return this.asO;
        }

        public void writeToParcel(Parcel parcel, int i) {
            zzi com_google_android_gms_plus_internal_model_people_zzi = CREATOR;
            zzi.zza(this, parcel, i);
        }

        protected boolean zza(Field field) {
            return this.asg.contains(Integer.valueOf(field.zzaub()));
        }

        public /* synthetic */ Map zzatv() {
            return zzbyn();
        }

        protected Object zzb(Field field) {
            switch (field.zzaub()) {
                case 2:
                    return Boolean.valueOf(this.asO);
                case 3:
                    return this.mValue;
                default:
                    throw new IllegalStateException("Unknown safe parcelable id=" + field.zzaub());
            }
        }

        public HashMap<String, Field<?, ?>> zzbyn() {
            return asf;
        }

        public PlacesLivedEntity zzbyw() {
            return this;
        }
    }

    public static class zza {
        public static int zznf(String str) {
            if (str.equals("person")) {
                return 0;
            }
            if (str.equals("page")) {
                return 1;
            }
            String str2 = "Unknown objectType string: ";
            String valueOf = String.valueOf(str);
            throw new IllegalArgumentException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        }
    }

    static {
        asf.put("aboutMe", Field.zzl("aboutMe", 2));
        asf.put("ageRange", Field.zza("ageRange", 3, AgeRangeEntity.class));
        asf.put("birthday", Field.zzl("birthday", 4));
        asf.put("braggingRights", Field.zzl("braggingRights", 5));
        asf.put("circledByCount", Field.zzj("circledByCount", 6));
        asf.put(PlaceFields.COVER, Field.zza(PlaceFields.COVER, 7, CoverEntity.class));
        asf.put("currentLocation", Field.zzl("currentLocation", 8));
        asf.put("displayName", Field.zzl("displayName", 9));
        asf.put("gender", Field.zza("gender", 12, new StringToIntConverter().zzi("male", 0).zzi("female", 1).zzi(FacebookRequestErrorClassification.KEY_OTHER, 2), false));
        asf.put("id", Field.zzl("id", 14));
        asf.put("image", Field.zza("image", 15, ImageEntity.class));
        asf.put("isPlusUser", Field.zzk("isPlusUser", 16));
        asf.put("language", Field.zzl("language", 18));
        asf.put("name", Field.zza("name", 19, NameEntity.class));
        asf.put("nickname", Field.zzl("nickname", 20));
        asf.put("objectType", Field.zza("objectType", 21, new StringToIntConverter().zzi("person", 0).zzi("page", 1), false));
        asf.put("organizations", Field.zzb("organizations", 22, OrganizationsEntity.class));
        asf.put("placesLived", Field.zzb("placesLived", 23, PlacesLivedEntity.class));
        asf.put("plusOneCount", Field.zzj("plusOneCount", 24));
        asf.put("relationshipStatus", Field.zza("relationshipStatus", 25, new StringToIntConverter().zzi("single", 0).zzi("in_a_relationship", 1).zzi("engaged", 2).zzi("married", 3).zzi("its_complicated", 4).zzi("open_relationship", 5).zzi("widowed", 6).zzi("in_domestic_partnership", 7).zzi("in_civil_union", 8), false));
        asf.put("tagline", Field.zzl("tagline", 26));
        asf.put("url", Field.zzl("url", 27));
        asf.put("urls", Field.zzb("urls", 28, UrlsEntity.class));
        asf.put("verified", Field.zzk("verified", 29));
    }

    public PersonEntity() {
        this.mVersionCode = 1;
        this.asg = new HashSet();
    }

    public PersonEntity(String str, String str2, ImageEntity imageEntity, int i, String str3) {
        this.mVersionCode = 1;
        this.asg = new HashSet();
        this.dC = str;
        this.asg.add(Integer.valueOf(9));
        this.zzbgg = str2;
        this.asg.add(Integer.valueOf(14));
        this.aso = imageEntity;
        this.asg.add(Integer.valueOf(15));
        this.ass = i;
        this.asg.add(Integer.valueOf(21));
        this.zzae = str3;
        this.asg.add(Integer.valueOf(27));
    }

    PersonEntity(Set<Integer> set, int i, String str, AgeRangeEntity ageRangeEntity, String str2, String str3, int i2, CoverEntity coverEntity, String str4, String str5, int i3, String str6, ImageEntity imageEntity, boolean z, String str7, NameEntity nameEntity, String str8, int i4, List<OrganizationsEntity> list, List<PlacesLivedEntity> list2, int i5, int i6, String str9, String str10, List<UrlsEntity> list3, boolean z2) {
        this.asg = set;
        this.mVersionCode = i;
        this.ash = str;
        this.asi = ageRangeEntity;
        this.asj = str2;
        this.ask = str3;
        this.asl = i2;
        this.asm = coverEntity;
        this.asn = str4;
        this.dC = str5;
        this.zzaud = i3;
        this.zzbgg = str6;
        this.aso = imageEntity;
        this.asp = z;
        this.zzcuy = str7;
        this.asq = nameEntity;
        this.asr = str8;
        this.ass = i4;
        this.ast = list;
        this.asu = list2;
        this.asv = i5;
        this.asw = i6;
        this.asx = str9;
        this.zzae = str10;
        this.asy = list3;
        this.asz = z2;
    }

    public static PersonEntity zzae(byte[] bArr) {
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(bArr, 0, bArr.length);
        obtain.setDataPosition(0);
        PersonEntity personEntity = (PersonEntity) CREATOR.createFromParcel(obtain);
        obtain.recycle();
        return personEntity;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PersonEntity)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        PersonEntity personEntity = (PersonEntity) obj;
        for (Field field : asf.values()) {
            if (zza(field)) {
                if (!personEntity.zza(field)) {
                    return false;
                }
                if (!zzb(field).equals(personEntity.zzb(field))) {
                    return false;
                }
            } else if (personEntity.zza(field)) {
                return false;
            }
        }
        return true;
    }

    public /* synthetic */ Object freeze() {
        return zzbyo();
    }

    public String getAboutMe() {
        return this.ash;
    }

    public AgeRange getAgeRange() {
        return this.asi;
    }

    public String getBirthday() {
        return this.asj;
    }

    public String getBraggingRights() {
        return this.ask;
    }

    public int getCircledByCount() {
        return this.asl;
    }

    public Cover getCover() {
        return this.asm;
    }

    public String getCurrentLocation() {
        return this.asn;
    }

    public String getDisplayName() {
        return this.dC;
    }

    public int getGender() {
        return this.zzaud;
    }

    public String getId() {
        return this.zzbgg;
    }

    public Image getImage() {
        return this.aso;
    }

    public String getLanguage() {
        return this.zzcuy;
    }

    public Name getName() {
        return this.asq;
    }

    public String getNickname() {
        return this.asr;
    }

    public int getObjectType() {
        return this.ass;
    }

    public List<Organizations> getOrganizations() {
        return (ArrayList) this.ast;
    }

    public List<PlacesLived> getPlacesLived() {
        return (ArrayList) this.asu;
    }

    public int getPlusOneCount() {
        return this.asv;
    }

    public int getRelationshipStatus() {
        return this.asw;
    }

    public String getTagline() {
        return this.asx;
    }

    public String getUrl() {
        return this.zzae;
    }

    public List<Urls> getUrls() {
        return (ArrayList) this.asy;
    }

    public boolean hasAboutMe() {
        return this.asg.contains(Integer.valueOf(2));
    }

    public boolean hasAgeRange() {
        return this.asg.contains(Integer.valueOf(3));
    }

    public boolean hasBirthday() {
        return this.asg.contains(Integer.valueOf(4));
    }

    public boolean hasBraggingRights() {
        return this.asg.contains(Integer.valueOf(5));
    }

    public boolean hasCircledByCount() {
        return this.asg.contains(Integer.valueOf(6));
    }

    public boolean hasCover() {
        return this.asg.contains(Integer.valueOf(7));
    }

    public boolean hasCurrentLocation() {
        return this.asg.contains(Integer.valueOf(8));
    }

    public boolean hasDisplayName() {
        return this.asg.contains(Integer.valueOf(9));
    }

    public boolean hasGender() {
        return this.asg.contains(Integer.valueOf(12));
    }

    public boolean hasId() {
        return this.asg.contains(Integer.valueOf(14));
    }

    public boolean hasImage() {
        return this.asg.contains(Integer.valueOf(15));
    }

    public boolean hasIsPlusUser() {
        return this.asg.contains(Integer.valueOf(16));
    }

    public boolean hasLanguage() {
        return this.asg.contains(Integer.valueOf(18));
    }

    public boolean hasName() {
        return this.asg.contains(Integer.valueOf(19));
    }

    public boolean hasNickname() {
        return this.asg.contains(Integer.valueOf(20));
    }

    public boolean hasObjectType() {
        return this.asg.contains(Integer.valueOf(21));
    }

    public boolean hasOrganizations() {
        return this.asg.contains(Integer.valueOf(22));
    }

    public boolean hasPlacesLived() {
        return this.asg.contains(Integer.valueOf(23));
    }

    public boolean hasPlusOneCount() {
        return this.asg.contains(Integer.valueOf(24));
    }

    public boolean hasRelationshipStatus() {
        return this.asg.contains(Integer.valueOf(25));
    }

    public boolean hasTagline() {
        return this.asg.contains(Integer.valueOf(26));
    }

    public boolean hasUrl() {
        return this.asg.contains(Integer.valueOf(27));
    }

    public boolean hasUrls() {
        return this.asg.contains(Integer.valueOf(28));
    }

    public boolean hasVerified() {
        return this.asg.contains(Integer.valueOf(29));
    }

    public int hashCode() {
        int i = 0;
        for (Field field : asf.values()) {
            int hashCode;
            if (zza(field)) {
                hashCode = zzb(field).hashCode() + (i + field.zzaub());
            } else {
                hashCode = i;
            }
            i = hashCode;
        }
        return i;
    }

    public boolean isDataValid() {
        return true;
    }

    public boolean isPlusUser() {
        return this.asp;
    }

    public boolean isVerified() {
        return this.asz;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zza com_google_android_gms_plus_internal_model_people_zza = CREATOR;
        zza.zza(this, parcel, i);
    }

    protected boolean zza(Field field) {
        return this.asg.contains(Integer.valueOf(field.zzaub()));
    }

    public /* synthetic */ Map zzatv() {
        return zzbyn();
    }

    protected Object zzb(Field field) {
        switch (field.zzaub()) {
            case 2:
                return this.ash;
            case 3:
                return this.asi;
            case 4:
                return this.asj;
            case 5:
                return this.ask;
            case 6:
                return Integer.valueOf(this.asl);
            case 7:
                return this.asm;
            case 8:
                return this.asn;
            case 9:
                return this.dC;
            case 12:
                return Integer.valueOf(this.zzaud);
            case 14:
                return this.zzbgg;
            case 15:
                return this.aso;
            case 16:
                return Boolean.valueOf(this.asp);
            case 18:
                return this.zzcuy;
            case 19:
                return this.asq;
            case 20:
                return this.asr;
            case 21:
                return Integer.valueOf(this.ass);
            case 22:
                return this.ast;
            case 23:
                return this.asu;
            case 24:
                return Integer.valueOf(this.asv);
            case 25:
                return Integer.valueOf(this.asw);
            case 26:
                return this.asx;
            case 27:
                return this.zzae;
            case 28:
                return this.asy;
            case 29:
                return Boolean.valueOf(this.asz);
            default:
                throw new IllegalStateException("Unknown safe parcelable id=" + field.zzaub());
        }
    }

    public HashMap<String, Field<?, ?>> zzbyn() {
        return asf;
    }

    public PersonEntity zzbyo() {
        return this;
    }
}

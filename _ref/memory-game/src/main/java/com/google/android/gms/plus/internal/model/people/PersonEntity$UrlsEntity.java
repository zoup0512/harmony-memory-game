package com.google.android.gms.plus.internal.model.people;

import android.os.Parcel;
import com.facebook.internal.FacebookRequestErrorClassification;
import com.facebook.places.model.PlaceFields;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.server.converter.StringToIntConverter;
import com.google.android.gms.common.server.response.FastJsonResponse.Field;
import com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse;
import com.google.android.gms.plus.PlusShare;
import com.google.android.gms.plus.model.people.Person.Urls;
import com.google.firebase.analytics.FirebaseAnalytics.Param;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class PersonEntity$UrlsEntity extends FastSafeParcelableJsonResponse implements Urls {
    public static final zzj CREATOR = new zzj();
    private static final HashMap<String, Field<?, ?>> asf = new HashMap();
    private final int asQ;
    final Set<Integer> asg;
    int iq;
    String mValue;
    final int mVersionCode;
    String zzcvd;

    static {
        asf.put(PlusShare.KEY_CALL_TO_ACTION_LABEL, Field.zzl(PlusShare.KEY_CALL_TO_ACTION_LABEL, 5));
        asf.put("type", Field.zza("type", 6, new StringToIntConverter().zzi("home", 0).zzi("work", 1).zzi("blog", 2).zzi(Scopes.PROFILE, 3).zzi(FacebookRequestErrorClassification.KEY_OTHER, 4).zzi("otherProfile", 5).zzi("contributor", 6).zzi(PlaceFields.WEBSITE, 7), false));
        asf.put(Param.VALUE, Field.zzl(Param.VALUE, 4));
    }

    public PersonEntity$UrlsEntity() {
        this.asQ = 4;
        this.mVersionCode = 1;
        this.asg = new HashSet();
    }

    PersonEntity$UrlsEntity(Set<Integer> set, int i, String str, int i2, String str2, int i3) {
        this.asQ = 4;
        this.asg = set;
        this.mVersionCode = i;
        this.zzcvd = str;
        this.iq = i2;
        this.mValue = str2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PersonEntity$UrlsEntity)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        PersonEntity$UrlsEntity personEntity$UrlsEntity = (PersonEntity$UrlsEntity) obj;
        for (Field field : asf.values()) {
            if (zza(field)) {
                if (!personEntity$UrlsEntity.zza(field)) {
                    return false;
                }
                if (!zzb(field).equals(personEntity$UrlsEntity.zzb(field))) {
                    return false;
                }
            } else if (personEntity$UrlsEntity.zza(field)) {
                return false;
            }
        }
        return true;
    }

    public /* synthetic */ Object freeze() {
        return zzbyy();
    }

    public String getLabel() {
        return this.zzcvd;
    }

    public int getType() {
        return this.iq;
    }

    public String getValue() {
        return this.mValue;
    }

    public boolean hasLabel() {
        return this.asg.contains(Integer.valueOf(5));
    }

    public boolean hasType() {
        return this.asg.contains(Integer.valueOf(6));
    }

    public boolean hasValue() {
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
        zzj com_google_android_gms_plus_internal_model_people_zzj = CREATOR;
        zzj.zza(this, parcel, i);
    }

    protected boolean zza(Field field) {
        return this.asg.contains(Integer.valueOf(field.zzaub()));
    }

    public /* synthetic */ Map zzatv() {
        return zzbyn();
    }

    protected Object zzb(Field field) {
        switch (field.zzaub()) {
            case 4:
                return this.mValue;
            case 5:
                return this.zzcvd;
            case 6:
                return Integer.valueOf(this.iq);
            default:
                throw new IllegalStateException("Unknown safe parcelable id=" + field.zzaub());
        }
    }

    public HashMap<String, Field<?, ?>> zzbyn() {
        return asf;
    }

    @Deprecated
    public int zzbyx() {
        return 4;
    }

    public PersonEntity$UrlsEntity zzbyy() {
        return this;
    }
}

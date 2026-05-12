package com.google.firebase;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzaa;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzai;
import com.google.android.gms.common.util.zzw;

public final class FirebaseOptions {
    private final String aMG;
    private final String aMH;
    private final String gp;
    private final String ve;
    private final String vh;
    private final String vi;

    public static final class Builder {
        private String aMG;
        private String aMH;
        private String gp;
        private String ve;
        private String vh;
        private String vi;

        public Builder(FirebaseOptions firebaseOptions) {
            this.gp = firebaseOptions.gp;
            this.ve = firebaseOptions.ve;
            this.aMG = firebaseOptions.aMG;
            this.aMH = firebaseOptions.aMH;
            this.vh = firebaseOptions.vh;
            this.vi = firebaseOptions.vi;
        }

        public FirebaseOptions build() {
            return new FirebaseOptions(this.gp, this.ve, this.aMG, this.aMH, this.vh, this.vi);
        }

        public Builder setApiKey(@NonNull String str) {
            this.ve = zzab.zzh(str, "ApiKey must be set.");
            return this;
        }

        public Builder setApplicationId(@NonNull String str) {
            this.gp = zzab.zzh(str, "ApplicationId must be set.");
            return this;
        }

        public Builder setDatabaseUrl(@Nullable String str) {
            this.aMG = str;
            return this;
        }

        public Builder setGcmSenderId(@Nullable String str) {
            this.vh = str;
            return this;
        }

        public Builder setStorageBucket(@Nullable String str) {
            this.vi = str;
            return this;
        }
    }

    private FirebaseOptions(@NonNull String str, @NonNull String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5, @Nullable String str6) {
        zzab.zza(!zzw.zzib(str), (Object) "ApplicationId must be set.");
        this.gp = str;
        this.ve = str2;
        this.aMG = str3;
        this.aMH = str4;
        this.vh = str5;
        this.vi = str6;
    }

    public static FirebaseOptions fromResource(Context context) {
        zzai com_google_android_gms_common_internal_zzai = new zzai(context);
        Object string = com_google_android_gms_common_internal_zzai.getString("google_app_id");
        return TextUtils.isEmpty(string) ? null : new FirebaseOptions(string, com_google_android_gms_common_internal_zzai.getString("google_api_key"), com_google_android_gms_common_internal_zzai.getString("firebase_database_url"), com_google_android_gms_common_internal_zzai.getString("ga_trackingId"), com_google_android_gms_common_internal_zzai.getString("gcm_defaultSenderId"), com_google_android_gms_common_internal_zzai.getString("google_storage_bucket"));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof FirebaseOptions)) {
            return false;
        }
        FirebaseOptions firebaseOptions = (FirebaseOptions) obj;
        return zzaa.equal(this.gp, firebaseOptions.gp) && zzaa.equal(this.ve, firebaseOptions.ve) && zzaa.equal(this.aMG, firebaseOptions.aMG) && zzaa.equal(this.aMH, firebaseOptions.aMH) && zzaa.equal(this.vh, firebaseOptions.vh) && zzaa.equal(this.vi, firebaseOptions.vi);
    }

    public String getApiKey() {
        return this.ve;
    }

    public String getApplicationId() {
        return this.gp;
    }

    public String getDatabaseUrl() {
        return this.aMG;
    }

    public String getGcmSenderId() {
        return this.vh;
    }

    public String getStorageBucket() {
        return this.vi;
    }

    public int hashCode() {
        return zzaa.hashCode(this.gp, this.ve, this.aMG, this.aMH, this.vh, this.vi);
    }

    public String toString() {
        return zzaa.zzx(this).zzg("applicationId", this.gp).zzg("apiKey", this.ve).zzg("databaseUrl", this.aMG).zzg("gcmSenderId", this.vh).zzg("storageBucket", this.vi).toString();
    }
}

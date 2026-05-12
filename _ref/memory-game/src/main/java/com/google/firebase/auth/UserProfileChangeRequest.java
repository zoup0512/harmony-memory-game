package com.google.firebase.auth;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

public class UserProfileChangeRequest extends AbstractSafeParcelable {
    public static final Creator<UserProfileChangeRequest> CREATOR = new zza();
    private String JF;
    private boolean aNe;
    private boolean aNf;
    private Uri aNg;
    private String dC;
    public final int mVersionCode;

    public static class Builder {
        private boolean aNe;
        private boolean aNf;
        private Uri aNg;
        private String dC;

        public UserProfileChangeRequest build() {
            return new UserProfileChangeRequest(1, this.dC, this.aNg == null ? null : this.aNg.toString(), this.aNe, this.aNf);
        }

        public Builder setDisplayName(@Nullable String str) {
            if (str == null) {
                this.aNe = true;
            } else {
                this.dC = str;
            }
            return this;
        }

        public Builder setPhotoUri(@Nullable Uri uri) {
            if (uri == null) {
                this.aNf = true;
            } else {
                this.aNg = uri;
            }
            return this;
        }
    }

    UserProfileChangeRequest(int i, String str, String str2, boolean z, boolean z2) {
        this.mVersionCode = i;
        this.dC = str;
        this.JF = str2;
        this.aNe = z;
        this.aNf = z2;
        this.aNg = TextUtils.isEmpty(str2) ? null : Uri.parse(str2);
    }

    @Nullable
    public String getDisplayName() {
        return this.dC;
    }

    @Nullable
    public Uri getPhotoUri() {
        return this.aNg;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zza.zza(this, parcel, i);
    }

    public String zzckv() {
        return this.JF;
    }

    public boolean zzckw() {
        return this.aNe;
    }

    public boolean zzckx() {
        return this.aNf;
    }
}

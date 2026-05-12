package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.IntentSender.SendIntentException;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzaa;

public final class Status extends AbstractSafeParcelable implements Result, ReflectedParcelable {
    public static final Creator<Status> CREATOR = new zzf();
    public static final Status sq = new Status(0);
    public static final Status sr = new Status(14);
    public static final Status ss = new Status(8);
    public static final Status st = new Status(15);
    public static final Status su = new Status(16);
    public static final Status sv = new Status(17);
    public static final Status sw = new Status(18);
    private final PendingIntent mPendingIntent;
    private final int mVersionCode;
    private final int ok;
    private final String rc;

    public Status(int i) {
        this(i, null);
    }

    Status(int i, int i2, String str, PendingIntent pendingIntent) {
        this.mVersionCode = i;
        this.ok = i2;
        this.rc = str;
        this.mPendingIntent = pendingIntent;
    }

    public Status(int i, String str) {
        this(1, i, str, null);
    }

    public Status(int i, String str, PendingIntent pendingIntent) {
        this(1, i, str, pendingIntent);
    }

    private String zzaom() {
        return this.rc != null ? this.rc : CommonStatusCodes.getStatusCodeString(this.ok);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Status)) {
            return false;
        }
        Status status = (Status) obj;
        return this.mVersionCode == status.mVersionCode && this.ok == status.ok && zzaa.equal(this.rc, status.rc) && zzaa.equal(this.mPendingIntent, status.mPendingIntent);
    }

    public PendingIntent getResolution() {
        return this.mPendingIntent;
    }

    public Status getStatus() {
        return this;
    }

    public int getStatusCode() {
        return this.ok;
    }

    @Nullable
    public String getStatusMessage() {
        return this.rc;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public boolean hasResolution() {
        return this.mPendingIntent != null;
    }

    public int hashCode() {
        return zzaa.hashCode(Integer.valueOf(this.mVersionCode), Integer.valueOf(this.ok), this.rc, this.mPendingIntent);
    }

    public boolean isCanceled() {
        return this.ok == 16;
    }

    public boolean isInterrupted() {
        return this.ok == 14;
    }

    public boolean isSuccess() {
        return this.ok <= 0;
    }

    public void startResolutionForResult(Activity activity, int i) throws SendIntentException {
        if (hasResolution()) {
            activity.startIntentSenderForResult(this.mPendingIntent.getIntentSender(), i, null, 0, 0, 0);
        }
    }

    public String toString() {
        return zzaa.zzx(this).zzg("statusCode", zzaom()).zzg("resolution", this.mPendingIntent).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzf.zza(this, parcel, i);
    }

    PendingIntent zzaol() {
        return this.mPendingIntent;
    }
}

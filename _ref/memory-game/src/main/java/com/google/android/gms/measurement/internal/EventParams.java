package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.zzab;
import java.util.Iterator;

public class EventParams extends AbstractSafeParcelable implements Iterable<String> {
    public static final zzj CREATOR = new zzj();
    private final Bundle aiF;
    public final int versionCode;

    EventParams(int i, Bundle bundle) {
        this.versionCode = i;
        this.aiF = bundle;
    }

    EventParams(Bundle bundle) {
        zzab.zzy(bundle);
        this.aiF = bundle;
        this.versionCode = 1;
    }

    Object get(String str) {
        return this.aiF.get(str);
    }

    public Iterator<String> iterator() {
        return new Iterator<String>(this) {
            Iterator<String> aiG = this.aiH.aiF.keySet().iterator();
            final /* synthetic */ EventParams aiH;

            {
                this.aiH = r2;
            }

            public boolean hasNext() {
                return this.aiG.hasNext();
            }

            public String next() {
                return (String) this.aiG.next();
            }

            public void remove() {
                throw new UnsupportedOperationException("Remove not supported");
            }
        };
    }

    public int size() {
        return this.aiF.size();
    }

    public String toString() {
        return this.aiF.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzj.zza(this, parcel, i);
    }

    public Bundle zzbss() {
        return new Bundle(this.aiF);
    }
}

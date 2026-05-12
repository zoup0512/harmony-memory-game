package com.google.android.gms.ads.internal.request;

import android.os.Parcel;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.internal.zzin;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

@zzin
public final class AutoClickProtectionConfigurationParcel extends AbstractSafeParcelable {
    public static final zzi CREATOR = new zzi();
    public final int versionCode;
    public final boolean zzccu;
    @Nullable
    public final List<String> zzccv;

    public AutoClickProtectionConfigurationParcel() {
        this(1, false, Collections.emptyList());
    }

    public AutoClickProtectionConfigurationParcel(int i, boolean z, List<String> list) {
        this.versionCode = i;
        this.zzccu = z;
        this.zzccv = list;
    }

    public AutoClickProtectionConfigurationParcel(boolean z) {
        this(1, z, Collections.emptyList());
    }

    public AutoClickProtectionConfigurationParcel(boolean z, List<String> list) {
        this(1, z, list);
    }

    @Nullable
    public static AutoClickProtectionConfigurationParcel zzh(JSONObject jSONObject) {
        if (jSONObject == null) {
            return new AutoClickProtectionConfigurationParcel();
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("reporting_urls");
        List arrayList = new ArrayList();
        if (optJSONArray != null) {
            for (int i = 0; i < optJSONArray.length(); i++) {
                try {
                    arrayList.add(optJSONArray.getString(i));
                } catch (Throwable e) {
                    zzb.zzd("Error grabbing url from json.", e);
                }
            }
        }
        return new AutoClickProtectionConfigurationParcel(jSONObject.optBoolean("enable_protection"), arrayList);
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzi.zza(this, parcel, i);
    }
}

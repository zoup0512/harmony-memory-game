package com.google.android.gms.common.internal;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.gms.R$string;

public class zzai {
    private final Resources zo;
    private final String zp = this.zo.getResourcePackageName(R$string.common_google_play_services_unknown_issue);

    public zzai(Context context) {
        zzab.zzy(context);
        this.zo = context.getResources();
    }

    public String getString(String str) {
        int identifier = this.zo.getIdentifier(str, "string", this.zp);
        return identifier == 0 ? null : this.zo.getString(identifier);
    }
}

package com.google.android.gms.measurement;

import com.amazonaws.mobileconnectors.s3.transferutility.TransferTable;
import com.facebook.AccessToken;
import com.google.android.gms.common.util.zzf;
import java.util.Map;

public final class AppMeasurement$zze {
    public static final Map<String, String> ahG = zzf.zzb(new String[]{"firebase_last_notification", "first_open_time", "last_deep_link_referrer", AccessToken.USER_ID_KEY}, new String[]{"_ln", "_fot", "_ldl", TransferTable.COLUMN_ID});
}

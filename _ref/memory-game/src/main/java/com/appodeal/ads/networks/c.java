package com.appodeal.ads.networks;

import android.content.Context;
import android.provider.Settings.Secure;
import com.appodeal.ads.Appodeal;
import com.google.android.gms.ads.AdRequest;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Locale;

public class c {
    public static String a(Context context) {
        try {
            return new BigInteger(1, MessageDigest.getInstance(CommonUtils.MD5_INSTANCE).digest(Secure.getString(context.getContentResolver(), "android_id").getBytes("UTF-8"))).toString(16).toUpperCase(Locale.ENGLISH);
        } catch (Throwable e) {
            Appodeal.a(e);
            return AdRequest.DEVICE_ID_EMULATOR;
        }
    }
}

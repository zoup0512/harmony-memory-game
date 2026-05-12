package com.chartboost.sdk.Libraries;

import com.chartboost.sdk.Tracking.a;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Locale;

public final class b {
    private b() {
    }

    public static synchronized byte[] a(byte[] bArr) {
        byte[] bArr2 = null;
        synchronized (b.class) {
            if (bArr != null) {
                try {
                    MessageDigest instance = MessageDigest.getInstance(CommonUtils.SHA1_INSTANCE);
                    instance.update(bArr);
                    bArr2 = instance.digest();
                } catch (Exception e) {
                    a.a(b.class, "sha1", e);
                } catch (Exception e2) {
                    a.a(b.class, "sha1", e2);
                }
            }
        }
        return bArr2;
    }

    public static String b(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        BigInteger bigInteger = new BigInteger(1, bArr);
        return String.format(Locale.US, "%0" + (bArr.length << 1) + "x", new Object[]{bigInteger});
    }
}

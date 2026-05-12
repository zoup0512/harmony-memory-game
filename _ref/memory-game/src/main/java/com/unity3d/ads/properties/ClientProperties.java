package com.unity3d.ads.properties;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import com.unity3d.ads.IUnityAdsListener;
import com.unity3d.ads.log.DeviceLog;
import java.io.ByteArrayInputStream;
import java.lang.ref.WeakReference;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import javax.security.auth.x500.X500Principal;

public class ClientProperties {
    private static final X500Principal DEBUG_CERT = new X500Principal("CN=Android Debug,O=Android,C=US");
    private static WeakReference<Activity> _activity;
    private static Context _applicationContext;
    private static String _gameId;
    private static IUnityAdsListener _listener;

    public static Activity getActivity() {
        return (Activity) _activity.get();
    }

    public static void setActivity(Activity activity) {
        _activity = new WeakReference(activity);
    }

    public static Context getApplicationContext() {
        return _applicationContext;
    }

    public static void setApplicationContext(Context context) {
        _applicationContext = context;
    }

    public static IUnityAdsListener getListener() {
        return _listener;
    }

    public static void setListener(IUnityAdsListener iUnityAdsListener) {
        _listener = iUnityAdsListener;
    }

    public static String getGameId() {
        return _gameId;
    }

    public static void setGameId(String str) {
        _gameId = str;
    }

    public static String getAppName() {
        return _applicationContext.getPackageName();
    }

    public static String getAppVersion() {
        try {
            return getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0).versionName;
        } catch (Exception e) {
            DeviceLog.exception("Error getting package info", e);
            return null;
        }
    }

    public static boolean isAppDebuggable() {
        Exception exception;
        Exception exception2;
        boolean z = true;
        if (getApplicationContext() == null) {
            return false;
        }
        boolean z2;
        PackageManager packageManager = getApplicationContext().getPackageManager();
        String packageName = getApplicationContext().getPackageName();
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(packageName, 0);
            int i = applicationInfo.flags & 2;
            applicationInfo.flags = i;
            if (i == 0) {
                z = false;
            }
            z2 = false;
        } catch (Exception e) {
            DeviceLog.exception("Could not find name", e);
            z2 = true;
            z = false;
        }
        if (!z2) {
            return z;
        }
        try {
            Signature[] signatureArr = packageManager.getPackageInfo(packageName, 64).signatures;
            int length = signatureArr.length;
            int i2 = 0;
            boolean z3 = z;
            while (i2 < length) {
                try {
                    z = ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(signatureArr[i2].toByteArray()))).getSubjectX500Principal().equals(DEBUG_CERT);
                    if (z) {
                        return z;
                    }
                    i2++;
                    z3 = z;
                } catch (Exception e2) {
                    exception = e2;
                    z = z3;
                    exception2 = exception;
                } catch (Exception e22) {
                    exception = e22;
                    z = z3;
                    exception2 = exception;
                }
            }
            return z3;
        } catch (NameNotFoundException e3) {
            exception2 = e3;
            DeviceLog.exception("Could not find name", exception2);
            return z;
        } catch (CertificateException e4) {
            exception2 = e4;
            DeviceLog.exception("Certificate exception", exception2);
            return z;
        }
    }
}

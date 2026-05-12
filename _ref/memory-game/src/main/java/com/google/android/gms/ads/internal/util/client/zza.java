package com.google.android.gms.ads.internal.util.client;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri.Builder;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings.Secure;
import android.support.annotation.Nullable;
import android.support.v4.internal.view.SupportMenu;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.TextView;
import com.cmcm.adsdk.base.CMBaseNativeAd;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.doubleclick.PublisherAdView;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.search.SearchAdView;
import com.google.android.gms.common.internal.zze;
import com.google.android.gms.common.util.zzs;
import com.google.android.gms.common.zzc;
import com.google.android.gms.internal.zzin;
import com.mopub.common.Constants;
import com.yalantis.ucrop.util.FileUtils;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import io.fabric.sdk.android.services.common.CommonUtils;
import io.fabric.sdk.android.services.settings.SettingsJsonConstants;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;
import java.util.StringTokenizer;

@zzin
public class zza {
    public static final Handler zzcnb = new Handler(Looper.getMainLooper());
    private static final String zzcnc = AdView.class.getName();
    private static final String zzcnd = InterstitialAd.class.getName();
    private static final String zzcne = PublisherAdView.class.getName();
    private static final String zzcnf = PublisherInterstitialAd.class.getName();
    private static final String zzcng = SearchAdView.class.getName();
    private static final String zzcnh = AdLoader.class.getName();

    private void zza(ViewGroup viewGroup, AdSizeParcel adSizeParcel, String str, int i, int i2) {
        if (viewGroup.getChildCount() == 0) {
            Context context = viewGroup.getContext();
            View textView = new TextView(context);
            textView.setGravity(17);
            textView.setText(str);
            textView.setTextColor(i);
            textView.setBackgroundColor(i2);
            View frameLayout = new FrameLayout(context);
            frameLayout.setBackgroundColor(i);
            int zza = zza(context, 3);
            frameLayout.addView(textView, new LayoutParams(adSizeParcel.widthPixels - zza, adSizeParcel.heightPixels - zza, 17));
            viewGroup.addView(frameLayout, adSizeParcel.widthPixels, adSizeParcel.heightPixels);
        }
    }

    public int zza(Context context, int i) {
        return zza(context.getResources().getDisplayMetrics(), i);
    }

    public int zza(DisplayMetrics displayMetrics, int i) {
        return (int) TypedValue.applyDimension(1, (float) i, displayMetrics);
    }

    @Nullable
    public String zza(StackTraceElement[] stackTraceElementArr, String str) {
        String className;
        for (int i = 0; i + 1 < stackTraceElementArr.length; i++) {
            StackTraceElement stackTraceElement = stackTraceElementArr[i];
            String className2 = stackTraceElement.getClassName();
            if ("loadAd".equalsIgnoreCase(stackTraceElement.getMethodName()) && (zzcnc.equalsIgnoreCase(className2) || zzcnd.equalsIgnoreCase(className2) || zzcne.equalsIgnoreCase(className2) || zzcnf.equalsIgnoreCase(className2) || zzcng.equalsIgnoreCase(className2) || zzcnh.equalsIgnoreCase(className2))) {
                className = stackTraceElementArr[i + 1].getClassName();
                break;
            }
        }
        className = null;
        if (str != null) {
            CharSequence zzb = zzb(str, FileUtils.HIDDEN_PREFIX, 3);
            if (!(className == null || className.contains(zzb))) {
                return className;
            }
        }
        return null;
    }

    public void zza(Context context, @Nullable String str, String str2, Bundle bundle, boolean z) {
        zza(context, str, str2, bundle, z, new 1(this));
    }

    public void zza(Context context, @Nullable String str, String str2, Bundle bundle, boolean z, zza com_google_android_gms_ads_internal_util_client_zza_zza) {
        if (z) {
            Context applicationContext = context.getApplicationContext();
            if (applicationContext == null) {
                applicationContext = context;
            }
            bundle.putString("os", VERSION.RELEASE);
            bundle.putString("api", String.valueOf(VERSION.SDK_INT));
            bundle.putString(CMBaseNativeAd.KEY_APP_ID, applicationContext.getPackageName());
            if (str == null) {
                str = zzc.zzang().zzbn(context) + FileUtils.HIDDEN_PREFIX + zze.xM;
            }
            bundle.putString("js", str);
        }
        Builder appendQueryParameter = new Builder().scheme(Constants.HTTPS).path("//pagead2.googlesyndication.com/pagead/gen_204").appendQueryParameter("id", str2);
        for (String str3 : bundle.keySet()) {
            appendQueryParameter.appendQueryParameter(str3, bundle.getString(str3));
        }
        com_google_android_gms_ads_internal_util_client_zza_zza.zzcr(appendQueryParameter.toString());
    }

    public void zza(ViewGroup viewGroup, AdSizeParcel adSizeParcel, String str) {
        zza(viewGroup, adSizeParcel, str, -16777216, -1);
    }

    public void zza(ViewGroup viewGroup, AdSizeParcel adSizeParcel, String str, String str2) {
        zzb.zzcx(str2);
        zza(viewGroup, adSizeParcel, str, (int) SupportMenu.CATEGORY_MASK, -16777216);
    }

    public void zza(boolean z, HttpURLConnection httpURLConnection, @Nullable String str) {
        httpURLConnection.setConnectTimeout(60000);
        httpURLConnection.setInstanceFollowRedirects(z);
        httpURLConnection.setReadTimeout(60000);
        if (str != null) {
            httpURLConnection.setRequestProperty("User-Agent", str);
        }
        httpURLConnection.setUseCaches(false);
    }

    public String zzaq(Context context) {
        ContentResolver contentResolver = context.getContentResolver();
        String string = contentResolver == null ? null : Secure.getString(contentResolver, "android_id");
        if (string == null || zztw()) {
            string = "emulator";
        }
        return zzcu(string);
    }

    public boolean zzar(Context context) {
        return zzc.zzang().isGooglePlayServicesAvailable(context) == 0;
    }

    public boolean zzas(Context context) {
        if (context.getResources().getConfiguration().orientation != 2) {
            return false;
        }
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return ((int) (((float) displayMetrics.heightPixels) / displayMetrics.density)) < SettingsJsonConstants.ANALYTICS_FLUSH_INTERVAL_SECS_DEFAULT;
    }

    public boolean zzat(Context context) {
        int i;
        int i2;
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        if (zzs.zzavs()) {
            defaultDisplay.getRealMetrics(displayMetrics);
            i = displayMetrics.heightPixels;
            i2 = displayMetrics.widthPixels;
        } else {
            try {
                i = ((Integer) Display.class.getMethod("getRawHeight", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue();
                i2 = ((Integer) Display.class.getMethod("getRawWidth", new Class[0]).invoke(defaultDisplay, new Object[0])).intValue();
            } catch (Exception e) {
                return false;
            }
        }
        defaultDisplay.getMetrics(displayMetrics);
        boolean z = displayMetrics.heightPixels == i && displayMetrics.widthPixels == i2;
        return z;
    }

    public int zzau(Context context) {
        int identifier = context.getResources().getIdentifier("navigation_bar_width", "dimen", AbstractSpiCall.ANDROID_CLIENT_TYPE);
        return identifier > 0 ? context.getResources().getDimensionPixelSize(identifier) : 0;
    }

    public int zzb(Context context, int i) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        return zzb(displayMetrics, i);
    }

    public int zzb(DisplayMetrics displayMetrics, int i) {
        return Math.round(((float) i) / displayMetrics.density);
    }

    String zzb(String str, String str2, int i) {
        StringTokenizer stringTokenizer = new StringTokenizer(str, str2);
        StringBuilder stringBuilder = new StringBuilder();
        int i2 = i - 1;
        if (i <= 0 || !stringTokenizer.hasMoreElements()) {
            return str;
        }
        stringBuilder.append(stringTokenizer.nextToken());
        while (true) {
            int i3 = i2 - 1;
            if (i2 > 0 && stringTokenizer.hasMoreElements()) {
                stringBuilder.append(FileUtils.HIDDEN_PREFIX).append(stringTokenizer.nextToken());
                i2 = i3;
            }
        }
        return stringBuilder.toString();
    }

    public String zzcu(String str) {
        int i = 0;
        while (i < 2) {
            try {
                MessageDigest.getInstance(CommonUtils.MD5_INSTANCE).update(str.getBytes());
                return String.format(Locale.US, "%032X", new Object[]{new BigInteger(1, r1.digest())});
            } catch (NoSuchAlgorithmException e) {
                i++;
            }
        }
        return null;
    }

    public boolean zztw() {
        return Build.DEVICE.startsWith("generic");
    }

    public boolean zztx() {
        return Looper.myLooper() == Looper.getMainLooper();
    }
}

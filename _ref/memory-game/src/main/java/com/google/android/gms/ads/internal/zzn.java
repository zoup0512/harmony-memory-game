package com.google.android.gms.ads.internal;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.internal.formats.zzd;
import com.google.android.gms.ads.internal.formats.zze;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzf.zza;
import com.google.android.gms.internal.zzdr;
import com.google.android.gms.internal.zzep;
import com.google.android.gms.internal.zzge;
import com.google.android.gms.internal.zzgn;
import com.google.android.gms.internal.zzgo;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzju;
import com.google.android.gms.internal.zzlh;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import org.json.JSONException;
import org.json.JSONObject;

@zzin
public class zzn {
    private static zzd zza(zzgn com_google_android_gms_internal_zzgn) throws RemoteException {
        return new zzd(com_google_android_gms_internal_zzgn.getHeadline(), com_google_android_gms_internal_zzgn.getImages(), com_google_android_gms_internal_zzgn.getBody(), com_google_android_gms_internal_zzgn.zzku(), com_google_android_gms_internal_zzgn.getCallToAction(), com_google_android_gms_internal_zzgn.getStarRating(), com_google_android_gms_internal_zzgn.getStore(), com_google_android_gms_internal_zzgn.getPrice(), null, com_google_android_gms_internal_zzgn.getExtras());
    }

    private static zze zza(zzgo com_google_android_gms_internal_zzgo) throws RemoteException {
        return new zze(com_google_android_gms_internal_zzgo.getHeadline(), com_google_android_gms_internal_zzgo.getImages(), com_google_android_gms_internal_zzgo.getBody(), com_google_android_gms_internal_zzgo.zzky(), com_google_android_gms_internal_zzgo.getCallToAction(), com_google_android_gms_internal_zzgo.getAdvertiser(), null, com_google_android_gms_internal_zzgo.getExtras());
    }

    static zzep zza(@Nullable zzgn com_google_android_gms_internal_zzgn, @Nullable zzgo com_google_android_gms_internal_zzgo, zza com_google_android_gms_ads_internal_zzf_zza) {
        return new 5(com_google_android_gms_internal_zzgn, com_google_android_gms_ads_internal_zzf_zza, com_google_android_gms_internal_zzgo);
    }

    static zzep zza(CountDownLatch countDownLatch) {
        return new 3(countDownLatch);
    }

    private static String zza(@Nullable Bitmap bitmap) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (bitmap == null) {
            zzb.zzcx("Bitmap is null. Returning empty string");
            return "";
        }
        bitmap.compress(CompressFormat.PNG, 100, byteArrayOutputStream);
        String encodeToString = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
        String valueOf = String.valueOf("data:image/png;base64,");
        encodeToString = String.valueOf(encodeToString);
        return encodeToString.length() != 0 ? valueOf.concat(encodeToString) : new String(valueOf);
    }

    static String zza(@Nullable zzdr com_google_android_gms_internal_zzdr) {
        if (com_google_android_gms_internal_zzdr == null) {
            zzb.zzcx("Image is null. Returning empty string");
            return "";
        }
        try {
            Uri uri = com_google_android_gms_internal_zzdr.getUri();
            if (uri != null) {
                return uri.toString();
            }
        } catch (RemoteException e) {
            zzb.zzcx("Unable to get image uri. Trying data uri next");
        }
        return zzb(com_google_android_gms_internal_zzdr);
    }

    private static JSONObject zza(@Nullable Bundle bundle, String str) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (bundle == null || TextUtils.isEmpty(str)) {
            return jSONObject;
        }
        JSONObject jSONObject2 = new JSONObject(str);
        Iterator keys = jSONObject2.keys();
        while (keys.hasNext()) {
            String str2 = (String) keys.next();
            if (bundle.containsKey(str2)) {
                if ("image".equals(jSONObject2.getString(str2))) {
                    Object obj = bundle.get(str2);
                    if (obj instanceof Bitmap) {
                        jSONObject.put(str2, zza((Bitmap) obj));
                    } else {
                        zzb.zzcx("Invalid type. An image type extra should return a bitmap");
                    }
                } else if (bundle.get(str2) instanceof Bitmap) {
                    zzb.zzcx("Invalid asset type. Bitmap should be returned only for image type");
                } else {
                    jSONObject.put(str2, String.valueOf(bundle.get(str2)));
                }
            }
        }
        return jSONObject;
    }

    public static void zza(@Nullable zzju com_google_android_gms_internal_zzju, zza com_google_android_gms_ads_internal_zzf_zza) {
        zzgo com_google_android_gms_internal_zzgo = null;
        if (com_google_android_gms_internal_zzju != null && zzg(com_google_android_gms_internal_zzju)) {
            zzlh com_google_android_gms_internal_zzlh = com_google_android_gms_internal_zzju.zzbtm;
            Object view = com_google_android_gms_internal_zzlh != null ? com_google_android_gms_internal_zzlh.getView() : null;
            if (view == null) {
                zzb.zzcx("AdWebView is null");
                return;
            }
            try {
                List list = com_google_android_gms_internal_zzju.zzbon != null ? com_google_android_gms_internal_zzju.zzbon.zzbni : null;
                if (list == null || list.isEmpty()) {
                    zzb.zzcx("No template ids present in mediation response");
                    return;
                }
                zzgn zzmo = com_google_android_gms_internal_zzju.zzboo != null ? com_google_android_gms_internal_zzju.zzboo.zzmo() : null;
                if (com_google_android_gms_internal_zzju.zzboo != null) {
                    com_google_android_gms_internal_zzgo = com_google_android_gms_internal_zzju.zzboo.zzmp();
                }
                if (list.contains("2") && zzmo != null) {
                    zzmo.zzl(com.google.android.gms.dynamic.zze.zzac(view));
                    if (!zzmo.getOverrideImpressionRecording()) {
                        zzmo.recordImpression();
                    }
                    com_google_android_gms_internal_zzlh.zzuj().zza("/nativeExpressViewClicked", zza(zzmo, null, com_google_android_gms_ads_internal_zzf_zza));
                } else if (!list.contains(AppEventsConstants.EVENT_PARAM_VALUE_YES) || com_google_android_gms_internal_zzgo == null) {
                    zzb.zzcx("No matching template id and mapper");
                } else {
                    com_google_android_gms_internal_zzgo.zzl(com.google.android.gms.dynamic.zze.zzac(view));
                    if (!com_google_android_gms_internal_zzgo.getOverrideImpressionRecording()) {
                        com_google_android_gms_internal_zzgo.recordImpression();
                    }
                    com_google_android_gms_internal_zzlh.zzuj().zza("/nativeExpressViewClicked", zza(null, com_google_android_gms_internal_zzgo, com_google_android_gms_ads_internal_zzf_zza));
                }
            } catch (Throwable e) {
                zzb.zzd("Error occurred while recording impression and registering for clicks", e);
            }
        }
    }

    private static void zza(zzlh com_google_android_gms_internal_zzlh) {
        OnClickListener zzuw = com_google_android_gms_internal_zzlh.zzuw();
        if (zzuw != null) {
            zzuw.onClick(com_google_android_gms_internal_zzlh.getView());
        }
    }

    private static void zza(zzlh com_google_android_gms_internal_zzlh, zzd com_google_android_gms_ads_internal_formats_zzd, String str) {
        com_google_android_gms_internal_zzlh.zzuj().zza(new 1(com_google_android_gms_ads_internal_formats_zzd, str, com_google_android_gms_internal_zzlh));
    }

    private static void zza(zzlh com_google_android_gms_internal_zzlh, zze com_google_android_gms_ads_internal_formats_zze, String str) {
        com_google_android_gms_internal_zzlh.zzuj().zza(new 2(com_google_android_gms_ads_internal_formats_zze, str, com_google_android_gms_internal_zzlh));
    }

    private static void zza(zzlh com_google_android_gms_internal_zzlh, CountDownLatch countDownLatch) {
        com_google_android_gms_internal_zzlh.zzuj().zza("/nativeExpressAssetsLoaded", zza(countDownLatch));
        com_google_android_gms_internal_zzlh.zzuj().zza("/nativeExpressAssetsLoadingFailed", zzb(countDownLatch));
    }

    public static boolean zza(zzlh com_google_android_gms_internal_zzlh, zzge com_google_android_gms_internal_zzge, CountDownLatch countDownLatch) {
        boolean z = false;
        try {
            z = zzb(com_google_android_gms_internal_zzlh, com_google_android_gms_internal_zzge, countDownLatch);
        } catch (Throwable e) {
            zzb.zzd("Unable to invoke load assets", e);
        } catch (RuntimeException e2) {
            countDownLatch.countDown();
            throw e2;
        }
        if (!z) {
            countDownLatch.countDown();
        }
        return z;
    }

    static zzep zzb(CountDownLatch countDownLatch) {
        return new 4(countDownLatch);
    }

    private static String zzb(zzdr com_google_android_gms_internal_zzdr) {
        try {
            com.google.android.gms.dynamic.zzd zzkt = com_google_android_gms_internal_zzdr.zzkt();
            if (zzkt == null) {
                zzb.zzcx("Drawable is null. Returning empty string");
                return "";
            }
            Drawable drawable = (Drawable) com.google.android.gms.dynamic.zze.zzad(zzkt);
            if (drawable instanceof BitmapDrawable) {
                return zza(((BitmapDrawable) drawable).getBitmap());
            }
            zzb.zzcx("Drawable is not an instance of BitmapDrawable. Returning empty string");
            return "";
        } catch (RemoteException e) {
            zzb.zzcx("Unable to get drawable. Returning empty string");
            return "";
        }
    }

    private static boolean zzb(zzlh com_google_android_gms_internal_zzlh, zzge com_google_android_gms_internal_zzge, CountDownLatch countDownLatch) throws RemoteException {
        View view = com_google_android_gms_internal_zzlh.getView();
        if (view == null) {
            zzb.zzcx("AdWebView is null");
            return false;
        }
        view.setVisibility(4);
        List list = com_google_android_gms_internal_zzge.zzbon.zzbni;
        if (list == null || list.isEmpty()) {
            zzb.zzcx("No template ids present in mediation response");
            return false;
        }
        zza(com_google_android_gms_internal_zzlh, countDownLatch);
        zzgn zzmo = com_google_android_gms_internal_zzge.zzboo.zzmo();
        zzgo zzmp = com_google_android_gms_internal_zzge.zzboo.zzmp();
        if (list.contains("2") && zzmo != null) {
            zza(com_google_android_gms_internal_zzlh, zza(zzmo), com_google_android_gms_internal_zzge.zzbon.zzbnh);
        } else if (!list.contains(AppEventsConstants.EVENT_PARAM_VALUE_YES) || zzmp == null) {
            zzb.zzcx("No matching template id and mapper");
            return false;
        } else {
            zza(com_google_android_gms_internal_zzlh, zza(zzmp), com_google_android_gms_internal_zzge.zzbon.zzbnh);
        }
        String str = com_google_android_gms_internal_zzge.zzbon.zzbnf;
        String str2 = com_google_android_gms_internal_zzge.zzbon.zzbng;
        if (str2 != null) {
            com_google_android_gms_internal_zzlh.loadDataWithBaseURL(str2, str, "text/html", "UTF-8", null);
        } else {
            com_google_android_gms_internal_zzlh.loadData(str, "text/html", "UTF-8");
        }
        return true;
    }

    @Nullable
    private static zzdr zze(Object obj) {
        return obj instanceof IBinder ? zzdr.zza.zzy((IBinder) obj) : null;
    }

    @Nullable
    public static View zzf(@Nullable zzju com_google_android_gms_internal_zzju) {
        if (com_google_android_gms_internal_zzju == null) {
            zzb.e("AdState is null");
            return null;
        } else if (zzg(com_google_android_gms_internal_zzju) && com_google_android_gms_internal_zzju.zzbtm != null) {
            return com_google_android_gms_internal_zzju.zzbtm.getView();
        } else {
            try {
                com.google.android.gms.dynamic.zzd view = com_google_android_gms_internal_zzju.zzboo != null ? com_google_android_gms_internal_zzju.zzboo.getView() : null;
                if (view != null) {
                    return (View) com.google.android.gms.dynamic.zze.zzad(view);
                }
                zzb.zzcx("View in mediation adapter is null.");
                return null;
            } catch (Throwable e) {
                zzb.zzd("Could not get View from mediation adapter.", e);
                return null;
            }
        }
    }

    public static boolean zzg(@Nullable zzju com_google_android_gms_internal_zzju) {
        return (com_google_android_gms_internal_zzju == null || !com_google_android_gms_internal_zzju.zzcby || com_google_android_gms_internal_zzju.zzbon == null || com_google_android_gms_internal_zzju.zzbon.zzbnf == null) ? false : true;
    }
}

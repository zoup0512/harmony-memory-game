package com.appodeal.ads.networks;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.appodeal.ads.AppodealSettings;
import com.appodeal.ads.UserSettings.Gender;
import com.appodeal.ads.utils.j;
import com.appodeal.ads.utils.r;
import com.cmcm.adsdk.BitmapListener;
import com.cmcm.adsdk.CMAdManager;
import com.cmcm.adsdk.ImageDownloadListener;

public class i {
    public static boolean a = false;

    public static class a implements ImageDownloadListener {
        public void getBitmap(String str, final BitmapListener bitmapListener) {
            if (!TextUtils.isEmpty(str)) {
                j jVar = new j(new com.appodeal.ads.utils.j.a(this) {
                    final /* synthetic */ a b;

                    public void a(Bitmap bitmap) {
                        if (bitmapListener == null) {
                            return;
                        }
                        if (bitmap != null) {
                            bitmapListener.onSuccessed(bitmap);
                        } else {
                            bitmapListener.onFailed("");
                        }
                    }
                }, str, false);
                if (VERSION.SDK_INT >= 11) {
                    jVar.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new com.appodeal.ads.utils.j.a[0]);
                } else {
                    jVar.execute(new com.appodeal.ads.utils.j.a[0]);
                }
            } else if (bitmapListener != null) {
                bitmapListener.onFailed("url is null");
            }
        }
    }

    public static void a(Activity activity, String str, String str2) {
        if (!a) {
            CMAdManager.applicationInit(activity, str, str2);
            a = true;
            Gender a = r.a((Context) activity);
            if (a == Gender.FEMALE) {
                CMAdManager.setGender(CMAdManager.Gender.FEMAL);
            } else if (a == Gender.MALE) {
                CMAdManager.setGender(CMAdManager.Gender.MALE);
            }
            Integer b = r.b((Context) activity);
            if (b != null) {
                CMAdManager.setAge(b.intValue());
            }
            if (AppodealSettings.a) {
                CMAdManager.enableTestCountry();
            }
            CMAdManager.enableLog();
        }
    }
}

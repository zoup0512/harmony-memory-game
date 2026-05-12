package io.branch.referral;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import io.branch.referral.Defines.Jsonkey;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;

public class InstallListener extends BroadcastReceiver {
    private static IInstallReferrerEvents callback_ = null;
    private static String installID_ = "bnc_no_value";
    private static boolean isWaitingForReferrer;

    public static void startInstallReferrerTime(long delay) {
        isWaitingForReferrer = true;
        new Handler().postDelayed(new Runnable() {
            public void run() {
                if (InstallListener.callback_ != null) {
                    InstallListener.callback_.onInstallReferrerEventsFinished();
                    InstallListener.callback_ = null;
                    InstallListener.isWaitingForReferrer = false;
                }
            }
        }, delay);
    }

    public void onReceive(Context context, Intent intent) {
        String rawReferrerString = intent.getStringExtra("referrer");
        if (rawReferrerString != null) {
            try {
                rawReferrerString = URLDecoder.decode(rawReferrerString, "UTF-8");
                HashMap<String, String> referrerMap = new HashMap();
                for (String referrerParam : rawReferrerString.split("&")) {
                    String[] keyValue = referrerParam.split("=");
                    if (keyValue.length > 1) {
                        referrerMap.put(URLDecoder.decode(keyValue[0], "UTF-8"), URLDecoder.decode(keyValue[1], "UTF-8"));
                    }
                }
                PrefHelper prefHelper = PrefHelper.getInstance(context);
                if (isWaitingForReferrer) {
                    if (referrerMap.containsKey(Jsonkey.LinkClickID.getKey())) {
                        installID_ = (String) referrerMap.get(Jsonkey.LinkClickID.getKey());
                        prefHelper.setLinkClickIdentifier(installID_);
                    }
                    if (referrerMap.containsKey(Jsonkey.IsFullAppConv.getKey()) && referrerMap.containsKey(Jsonkey.ReferringLink.getKey())) {
                        prefHelper.setIsFullAppConversion(Boolean.parseBoolean((String) referrerMap.get(Jsonkey.IsFullAppConv.getKey())));
                        prefHelper.setAppLink((String) referrerMap.get(Jsonkey.ReferringLink.getKey()));
                    }
                }
                if (referrerMap.containsKey(Jsonkey.GoogleSearchInstallReferrer.getKey())) {
                    prefHelper.setGoogleSearchInstallIdentifier((String) referrerMap.get(Jsonkey.GoogleSearchInstallReferrer.getKey()));
                }
                if (callback_ != null) {
                    callback_.onInstallReferrerEventsFinished();
                    callback_ = null;
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e2) {
                e2.printStackTrace();
                Log.w("BranchSDK", "Illegal characters in url encoded string");
            }
        }
    }

    public static String getInstallationID() {
        return installID_;
    }

    public static void setListener(IInstallReferrerEvents installReferrerFetch) {
        callback_ = installReferrerFetch;
    }
}

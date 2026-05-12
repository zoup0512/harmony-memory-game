package com.amazon.device.ads;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.amazon.device.ads.WebRequest.WebRequestException;
import com.amazon.device.ads.WebRequest.WebRequestFactory;
import com.mopub.common.Constants;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.IllegalCharsetNameException;
import java.util.Locale;

class WebUtils {
    private static final String LOGTAG = WebUtils.class.getSimpleName();
    private static final MobileAdsLogger logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);

    WebUtils() {
    }

    public static boolean launchActivityForIntentLink(String str, Context context) {
        if (str == null || str.equals("")) {
            str = "about:blank";
        }
        logger.d("Launch Intent: " + str);
        Intent intent = new Intent();
        if (str.startsWith("intent:")) {
            try {
                intent = Intent.parseUri(str, 1);
            } catch (URISyntaxException e) {
                return false;
            }
        }
        intent.setData(Uri.parse(str));
        intent.setAction("android.intent.action.VIEW");
        intent.setFlags(268435456);
        try {
            context.startActivity(intent);
            return true;
        } catch (ActivityNotFoundException e2) {
            String action = intent.getAction();
            logger.w("Could not handle " + (action.startsWith("market://") ? "market" : Constants.INTENT_SCHEME) + " action: " + action);
            return false;
        }
    }

    public static final String getURLEncodedString(String str) {
        if (str == null) {
            return null;
        }
        try {
            return URLEncoder.encode(str, "UTF-8").replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
        } catch (UnsupportedEncodingException e) {
            logger.d("getURLEncodedString threw: %s", e);
            return str;
        } catch (IllegalCharsetNameException e2) {
            logger.d("getURLEncodedString threw: %s", e2);
            return str;
        }
    }

    public static final String getURLDecodedString(String str) {
        if (str == null) {
            return null;
        }
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.d("getURLDecodedString threw: %s", e);
            return str;
        }
    }

    public static final String getScheme(String str) {
        String scheme = Uri.parse(str).getScheme();
        if (scheme != null) {
            return scheme.toLowerCase(Locale.US);
        }
        return scheme;
    }

    public static final String encloseHtml(String str, boolean z) {
        if (str == null) {
            return str;
        }
        if (str.indexOf("<html>") == -1) {
            str = "<html>" + str + "</html>";
        }
        if (z && str.indexOf("<!DOCTYPE html>") == -1) {
            return "<!DOCTYPE html>" + str;
        }
        return str;
    }

    public static final void executeWebRequestInThread(final String str, final boolean z) {
        ThreadUtils.scheduleRunnable(new Runnable() {
            public void run() {
                WebRequest createWebRequest = new WebRequestFactory().createWebRequest();
                createWebRequest.enableLog(true);
                createWebRequest.setUrlString(str);
                createWebRequest.setDisconnectEnabled(z);
                try {
                    createWebRequest.makeCall();
                } catch (WebRequestException e) {
                }
            }
        });
    }
}

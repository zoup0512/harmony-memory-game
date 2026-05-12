package com.amazon.device.ads;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.appodeal.ads.a.e;
import com.facebook.places.model.PlaceFields;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.mopub.common.Constants;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

class AdWebViewClient extends WebViewClient {
    protected static final String AAX_REDIRECT_BETA = "aax-beta.integ.amazon.com";
    protected static final String AAX_REDIRECT_GAMMA = "aax-us-east.amazon-adsystem.com";
    protected static final String AAX_REDIRECT_PROD = "aax-us-east.amazon-adsystem.com";
    public static final String AMAZON_MOBILE = "amazonmobile";
    protected static final String CORNERSTONE_BEST_ENDPOINT_BETA = "d16g-cornerstone-bes.integ.amazon.com";
    protected static final String CORNERSTONE_BEST_ENDPOINT_PROD = "pda-bes.amazon.com";
    public static final String GEO = "geo";
    public static final String GOOGLE_STREETVIEW = "google.streetview";
    private static final String LOGTAG = AdWebViewClient.class.getSimpleName();
    public static final String MAILTO = "mailto";
    public static final String SMS = "sms";
    public static final String TELEPHONE = "tel";
    public static final String VOICEMAIL = "voicemail";
    protected static final HashSet<String> intentSchemes = new HashSet();
    protected static Set<String> redirectHosts = new HashSet();
    private final AdControlAccessor adControlAccessor;
    private final AndroidBuildInfo androidBuildInfo;
    private final AdSDKBridgeList bridgeList;
    private final Context context;
    private AdWebViewClientListener listener;
    private final MobileAdsLogger logger;
    private final MobileAdsLoggerFactory loggerFactory;
    private CopyOnWriteArrayList<String> resourceList = new CopyOnWriteArrayList();
    private final HashMap<String, UrlExecutor> urlExecutors;
    private final WebUtils2 webUtils;

    interface AdWebViewClientListener {
        void onLoadResource(WebView webView, String str);

        void onPageFinished(WebView webView, String str);

        void onPageStarted(WebView webView, String str);

        void onReceivedError(WebView webView, int i, String str, String str2);
    }

    interface UrlExecutor {
        boolean execute(String str);
    }

    static class AmazonMobileExecutor implements UrlExecutor {
        private final Context context;
        private final boolean isInterstitial;
        private final AmazonDeviceLauncher launcher;
        private final MobileAdsLogger logger;
        private final WebUtils2 webUtils;

        AmazonMobileExecutor(Context context) {
            this(context, new MobileAdsLoggerFactory(), new AmazonDeviceLauncher(), new WebUtils2(), true);
        }

        AmazonMobileExecutor(Context context, MobileAdsLoggerFactory mobileAdsLoggerFactory, AmazonDeviceLauncher amazonDeviceLauncher, WebUtils2 webUtils2, boolean z) {
            this.context = context;
            this.logger = mobileAdsLoggerFactory.createMobileAdsLogger(AdWebViewClient.LOGTAG);
            this.launcher = amazonDeviceLauncher;
            this.webUtils = webUtils2;
            this.isInterstitial = z;
        }

        public boolean execute(String str) {
            specialUrlClicked(str);
            return true;
        }

        public void specialUrlClicked(String str) {
            this.logger.d("Executing AmazonMobile Intent");
            Uri parse = Uri.parse(str);
            List queryParameters;
            try {
                queryParameters = parse.getQueryParameters(Constants.INTENT_SCHEME);
            } catch (UnsupportedOperationException e) {
                queryParameters = null;
            }
            String queryParameter;
            if (r0 != null && r0.size() > 0) {
                for (String queryParameter2 : r0) {
                    if (launchExternalActivity(queryParameter2)) {
                        return;
                    }
                }
                handleApplicationDefinedSpecialURL(str);
            } else if (!this.launcher.isWindowshopPresent(this.context) || this.launcher.isInWindowshopApp(this.context)) {
                handleApplicationDefinedSpecialURL(str);
            } else if (parse.getHost().equals("shopping")) {
                queryParameter2 = parse.getQueryParameter("app-action");
                if (queryParameter2 != null && queryParameter2.length() != 0) {
                    if (queryParameter2.equals(ProductAction.ACTION_DETAIL)) {
                        queryParameter2 = parse.getQueryParameter("asin");
                        if (queryParameter2 != null && queryParameter2.length() != 0) {
                            this.launcher.launchWindowshopDetailPage(this.context, queryParameter2);
                        }
                    } else if (queryParameter2.equals("search")) {
                        queryParameter2 = parse.getQueryParameter("keyword");
                        if (queryParameter2 != null && queryParameter2.length() != 0) {
                            this.launcher.launchWindowshopSearchPage(this.context, queryParameter2);
                        }
                    } else if (queryParameter2.equals("webview")) {
                        handleApplicationDefinedSpecialURL(str);
                    }
                }
            }
        }

        protected void handleApplicationDefinedSpecialURL(String str) {
            this.logger.i("Special url clicked, but was not handled by SDK. Url: %s", str);
        }

        protected boolean launchExternalActivity(String str) {
            if (!this.isInterstitial) {
                if (((e) e.h().f()).c != null) {
                    ((e) e.h().f()).c.a();
                }
                if (((com.appodeal.ads.c.e) com.appodeal.ads.c.e.h().f()).b != null) {
                    ((com.appodeal.ads.c.e) com.appodeal.ads.c.e.h().f()).b.a();
                }
            } else if (((com.appodeal.ads.b.e) com.appodeal.ads.b.e.f().g()).b != null) {
                ((com.appodeal.ads.b.e) com.appodeal.ads.b.e.f().g()).b.b();
            }
            return this.webUtils.launchActivityForIntentLink(str, this.context);
        }
    }

    static class DefaultExecutor implements UrlExecutor {
        private final Context context;
        private final boolean isInterstitial;

        public DefaultExecutor(Context context, boolean z) {
            this.context = context;
            this.isInterstitial = z;
        }

        public boolean execute(String str) {
            if (!this.isInterstitial) {
                if (((e) e.h().f()).c != null) {
                    ((e) e.h().f()).c.a();
                }
                if (((com.appodeal.ads.c.e) com.appodeal.ads.c.e.h().f()).b != null) {
                    ((com.appodeal.ads.c.e) com.appodeal.ads.c.e.h().f()).b.a();
                }
            } else if (((com.appodeal.ads.b.e) com.appodeal.ads.b.e.f().g()).b != null) {
                ((com.appodeal.ads.b.e) com.appodeal.ads.b.e.f().g()).b.b();
            }
            WebUtils.launchActivityForIntentLink(str, this.context);
            return true;
        }
    }

    static {
        intentSchemes.add("tel");
        intentSchemes.add(VOICEMAIL);
        intentSchemes.add("sms");
        intentSchemes.add(MAILTO);
        intentSchemes.add(GEO);
        intentSchemes.add(GOOGLE_STREETVIEW);
        redirectHosts.add("aax-us-east.amazon-adsystem.com");
        redirectHosts.add("aax-us-east.amazon-adsystem.com");
        redirectHosts.add(AAX_REDIRECT_BETA);
        redirectHosts.add(CORNERSTONE_BEST_ENDPOINT_PROD);
        redirectHosts.add(CORNERSTONE_BEST_ENDPOINT_BETA);
    }

    public AdWebViewClient(Context context, AdSDKBridgeList adSDKBridgeList, AdControlAccessor adControlAccessor, WebUtils2 webUtils2, MobileAdsLoggerFactory mobileAdsLoggerFactory, AndroidBuildInfo androidBuildInfo) {
        this.context = context;
        this.urlExecutors = new HashMap();
        this.bridgeList = adSDKBridgeList;
        this.adControlAccessor = adControlAccessor;
        this.webUtils = webUtils2;
        this.loggerFactory = mobileAdsLoggerFactory;
        this.logger = this.loggerFactory.createMobileAdsLogger(LOGTAG);
        this.androidBuildInfo = androidBuildInfo;
        setupUrlExecutors();
    }

    public void setListener(AdWebViewClientListener adWebViewClientListener) {
        this.listener = adWebViewClientListener;
    }

    private void setupUrlExecutors() {
        this.urlExecutors.put(AMAZON_MOBILE, new AmazonMobileExecutor(this.context, this.loggerFactory, new AmazonDeviceLauncher(), this.webUtils, this.adControlAccessor.isInterstitial()));
        UrlExecutor defaultExecutor = new DefaultExecutor(this.context, this.adControlAccessor.isInterstitial());
        Iterator it = intentSchemes.iterator();
        while (it.hasNext()) {
            putUrlExecutor((String) it.next(), defaultExecutor);
        }
    }

    public void putUrlExecutor(String str, UrlExecutor urlExecutor) {
        this.urlExecutors.put(str, urlExecutor);
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        this.logger.e("Error: %s", str);
        super.onReceivedError(webView, i, str, str2);
        this.listener.onReceivedError(webView, i, str, str2);
    }

    public boolean shouldOverrideUrlLoading(WebView webView, String str) {
        return openUrl(str);
    }

    public boolean openUrl(String str) {
        boolean z;
        if (!redirectHosts.contains(Uri.parse(str).getHost()) || isHoneycombVersion()) {
            z = true;
        } else {
            z = false;
        }
        if (interpretScheme(str, getScheme(str))) {
            return true;
        }
        return z;
    }

    protected String getScheme(String str) {
        return this.webUtils.getScheme(str);
    }

    protected boolean interpretScheme(String str, String str2) {
        if (str2 == null) {
            return false;
        }
        if (str2.equals(PlaceFields.ABOUT) && str.equalsIgnoreCase("about:blank")) {
            return false;
        }
        if (this.urlExecutors.containsKey(str2)) {
            return ((UrlExecutor) this.urlExecutors.get(str2)).execute(str);
        }
        if (!this.adControlAccessor.isInterstitial()) {
            if (((e) e.h().f()).c != null) {
                ((e) e.h().f()).c.a();
            }
            if (((com.appodeal.ads.c.e) com.appodeal.ads.c.e.h().f()).b != null) {
                ((com.appodeal.ads.c.e) com.appodeal.ads.c.e.h().f()).b.a();
            }
        } else if (((com.appodeal.ads.b.e) com.appodeal.ads.b.e.f().g()).b != null) {
            ((com.appodeal.ads.b.e) com.appodeal.ads.b.e.f().g()).b.b();
        }
        this.logger.d("Scheme %s unrecognized. Launching as intent.", str2);
        return this.webUtils.launchActivityForIntentLink(str, this.context);
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
        this.listener.onPageStarted(webView, str);
    }

    public void onPageFinished(WebView webView, String str) {
        this.logger.d("Page Finished %s", str);
        if (!checkResources()) {
            if (this.listener == null) {
                this.logger.w("Call to onPageFinished() ignored because listener is null.");
            } else {
                this.listener.onPageFinished(webView, str);
            }
        }
    }

    public void onLoadResource(WebView webView, String str) {
        this.resourceList.add(str);
        this.logger.d("Loading resource: %s", str);
        this.listener.onLoadResource(webView, str);
    }

    private boolean checkResources() {
        Iterator it = this.resourceList.iterator();
        boolean z = false;
        while (it.hasNext()) {
            Set<AdSDKBridgeFactory> bridgeFactoriesForResourceLoad = BridgeSelector.getInstance().getBridgeFactoriesForResourceLoad((String) it.next());
            if (bridgeFactoriesForResourceLoad.size() > 0) {
                for (AdSDKBridgeFactory createAdSDKBridge : bridgeFactoriesForResourceLoad) {
                    AdSDKBridge createAdSDKBridge2 = createAdSDKBridge.createAdSDKBridge(this.adControlAccessor);
                    if (!this.bridgeList.contains(createAdSDKBridge2)) {
                        z = true;
                        this.bridgeList.addBridge(createAdSDKBridge2);
                    }
                }
            }
        }
        if (z) {
            ThreadUtils.executeOnMainThread(new Runnable() {
                public void run() {
                    AdWebViewClient.this.adControlAccessor.reload();
                }
            });
        }
        return z;
    }

    boolean isHoneycombVersion() {
        return AndroidTargetUtils.isBetweenAndroidAPIs(this.androidBuildInfo, 11, 13);
    }
}

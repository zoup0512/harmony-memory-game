package io.branch.referral;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Resources;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;
import com.facebook.internal.ServerProtocol;
import com.mopub.common.Constants;
import io.branch.indexing.BranchUniversalObject;
import io.branch.indexing.BranchUniversalObject.RegisterViewStatusListener;
import io.branch.referral.Defines.Jsonkey;
import io.branch.referral.ServerRequest.PROCESS_WAIT_LOCK;
import io.branch.referral.util.CommerceEvent;
import io.branch.referral.util.LinkProperties;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.json.JSONException;
import org.json.JSONObject;

public class Branch implements BranchViewHandler$IBranchViewEvents, SystemObserver$GAdsParamsFetchEvents, InstallListener$IInstallReferrerEvents {
    public static final String ALWAYS_DEEPLINK = "$always_deeplink";
    private static final String AUTO_DEEP_LINKED = "io.branch.sdk.auto_linked";
    private static final String AUTO_DEEP_LINK_DISABLE = "io.branch.sdk.auto_link_disable";
    private static final String AUTO_DEEP_LINK_KEY = "io.branch.sdk.auto_link_keys";
    private static final String AUTO_DEEP_LINK_PATH = "io.branch.sdk.auto_link_path";
    private static final String AUTO_DEEP_LINK_REQ_CODE = "io.branch.sdk.auto_link_request_code";
    public static final String DEEPLINK_PATH = "$deeplink_path";
    private static final int DEF_AUTO_DEEP_LINK_REQ_CODE = 1501;
    private static final String[] EXTERNAL_INTENT_EXTRA_KEY_WHITE_LIST = new String[]{"extra_launch_uri"};
    private static final String FABRIC_BRANCH_API_KEY = "io.branch.apiKey";
    public static final String FEATURE_TAG_DEAL = "deal";
    public static final String FEATURE_TAG_GIFT = "gift";
    public static final String FEATURE_TAG_INVITE = "invite";
    public static final String FEATURE_TAG_REFERRAL = "referral";
    public static final String FEATURE_TAG_SHARE = "share";
    private static int LATCH_WAIT_UNTIL = 2500;
    public static final int LINK_TYPE_ONE_TIME_USE = 1;
    public static final int LINK_TYPE_UNLIMITED_USE = 0;
    public static final String OG_APP_ID = "$og_app_id";
    public static final String OG_DESC = "$og_description";
    public static final String OG_IMAGE_URL = "$og_image_url";
    public static final String OG_TITLE = "$og_title";
    public static final String OG_URL = "$og_url";
    public static final String OG_VIDEO = "$og_video";
    private static long PLAYSTORE_REFERRAL_FETCH_WAIT_FOR = 5000;
    private static final int PREVENT_CLOSE_TIMEOUT = 500;
    public static final String REDEEM_CODE = "$redeem_code";
    public static final String REDIRECT_ANDROID_URL = "$android_url";
    public static final String REDIRECT_BLACKBERRY_URL = "$blackberry_url";
    public static final String REDIRECT_DESKTOP_URL = "$desktop_url";
    public static final String REDIRECT_FIRE_URL = "$fire_url";
    public static final String REDIRECT_IOS_URL = "$ios_url";
    public static final String REDIRECT_IPAD_URL = "$ipad_url";
    public static final String REDIRECT_WINDOWS_PHONE_URL = "$windows_phone_url";
    public static final String REFERRAL_BUCKET_DEFAULT = "default";
    public static final String REFERRAL_CODE = "referral_code";
    public static final int REFERRAL_CODE_AWARD_UNIQUE = 0;
    public static final int REFERRAL_CODE_AWARD_UNLIMITED = 1;
    public static final int REFERRAL_CODE_LOCATION_BOTH = 3;
    public static final int REFERRAL_CODE_LOCATION_REFERREE = 0;
    public static final int REFERRAL_CODE_LOCATION_REFERRING_USER = 2;
    public static final String REFERRAL_CODE_TYPE = "credit";
    public static final int REFERRAL_CREATION_SOURCE_SDK = 2;
    private static final int SESSION_KEEPALIVE = 2000;
    private static final String TAG = "BranchSDK";
    private static Branch branchReferral_;
    private static boolean checkInstallReferrer_ = false;
    private static String cookieBasedMatchDomain_ = "app.link";
    private static CUSTOM_REFERRABLE_SETTINGS customReferrableSettings_ = CUSTOM_REFERRABLE_SETTINGS.USE_DEFAULT;
    private static boolean disableDeviceIDFetch_;
    private static boolean isActivityLifeCycleCallbackRegistered_ = false;
    private static boolean isAutoSessionMode_ = false;
    private static Boolean isInstantApp = null;
    private static boolean isLogging_ = false;
    private static boolean isSimulatingInstalls_;
    private static Context lastApplicationContext = null;
    private ScheduledFuture<?> appListingSchedule_;
    private Context context_;
    WeakReference<Activity> currentActivityReference_;
    private JSONObject deeplinkDebugParams_;
    private boolean enableFacebookAppLinkCheck_ = false;
    private List<String> externalUriWhiteList_;
    private CountDownLatch getFirstReferringParamsLatch = null;
    private CountDownLatch getLatestReferringParamsLatch = null;
    private boolean handleDelayedNewIntents_ = false;
    private boolean hasNetwork_;
    private SESSION_STATE initState_ = SESSION_STATE.UNINITIALISED;
    private final ConcurrentHashMap<String, String> instrumentationExtraData_;
    private INTENT_STATE intentState_ = INTENT_STATE.PENDING;
    private boolean isGAParamsFetchInProgress_ = false;
    private boolean isInitReportedThroughCallBack = false;
    private BranchRemoteInterface kRemoteInterface_;
    private Map<BranchLinkData, String> linkCache_;
    final Object lock;
    private int networkCount_;
    private boolean performCookieBasedStrongMatchingOnGAIDAvailable = false;
    private PrefHelper prefHelper_;
    private ServerRequestQueue requestQueue_;
    private Semaphore serverSema_;
    String sessionReferredLink_;
    private ShareLinkManager shareLinkManager_;
    private List<String> skipExternalUriHosts_;
    private final SystemObserver systemObserver_;

    private Branch(@NonNull Context context) {
        this.prefHelper_ = PrefHelper.getInstance(context);
        this.kRemoteInterface_ = new BranchRemoteInterface(context);
        this.systemObserver_ = new SystemObserver(context);
        this.requestQueue_ = ServerRequestQueue.getInstance(context);
        this.serverSema_ = new Semaphore(1);
        this.lock = new Object();
        this.networkCount_ = 0;
        this.hasNetwork_ = true;
        this.linkCache_ = new HashMap();
        this.instrumentationExtraData_ = new ConcurrentHashMap();
        this.isGAParamsFetchInProgress_ = this.systemObserver_.prefetchGAdsParams(this);
        InstallListener.setListener(this);
        if (VERSION.SDK_INT >= 15) {
            this.handleDelayedNewIntents_ = true;
            this.intentState_ = INTENT_STATE.PENDING;
        } else {
            this.handleDelayedNewIntents_ = false;
            this.intentState_ = INTENT_STATE.READY;
        }
        this.externalUriWhiteList_ = new ArrayList();
        this.skipExternalUriHosts_ = new ArrayList();
    }

    public static void enableTestMode() {
        BranchUtil.isCustomDebugEnabled_ = true;
    }

    public static void disableTestMode() {
        BranchUtil.isCustomDebugEnabled_ = false;
    }

    public void setDebug() {
        enableTestMode();
    }

    public static void enablePlayStoreReferrer(long delay) {
        checkInstallReferrer_ = true;
        PLAYSTORE_REFERRAL_FETCH_WAIT_FOR = delay;
    }

    static boolean checkPlayStoreReferrer() {
        return checkInstallReferrer_;
    }

    public static long getReferralFetchWaitTime() {
        return PLAYSTORE_REFERRAL_FETCH_WAIT_FOR;
    }

    @TargetApi(14)
    public static Branch getInstance() {
        if (branchReferral_ == null) {
            Log.e(TAG, "Branch instance is not created yet. Make sure you have initialised Branch. [Consider Calling getInstance(Context ctx) if you still have issue.]");
        } else if (isAutoSessionMode_ && !isActivityLifeCycleCallbackRegistered_) {
            Log.e(TAG, "Branch instance is not properly initialised. Make sure your Application class is extending BranchApp class. If you are not extending BranchApp class make sure you are initialising Branch in your Applications onCreate()");
        }
        return branchReferral_;
    }

    public static Branch getInstance(@NonNull Context context, @NonNull String branchKey) {
        if (branchReferral_ == null) {
            branchReferral_ = initInstance(context);
        }
        branchReferral_.context_ = context.getApplicationContext();
        if (!branchKey.startsWith("key_")) {
            Log.e(TAG, "Branch Key is invalid.Please check your BranchKey");
        } else if (branchReferral_.prefHelper_.setBranchKey(branchKey)) {
            branchReferral_.linkCache_.clear();
            branchReferral_.requestQueue_.clear();
        }
        return branchReferral_;
    }

    private static Branch getBranchInstance(@NonNull Context context, boolean isLive) {
        if (branchReferral_ == null) {
            boolean isNewBranchKeySet;
            branchReferral_ = initInstance(context);
            String branchKey = branchReferral_.prefHelper_.readBranchKey(isLive);
            if (branchKey == null || branchKey.equalsIgnoreCase("bnc_no_value")) {
                String fabricBranchApiKey = null;
                try {
                    Resources resources = context.getResources();
                    fabricBranchApiKey = resources.getString(resources.getIdentifier(FABRIC_BRANCH_API_KEY, "string", context.getPackageName()));
                } catch (Exception e) {
                }
                if (TextUtils.isEmpty(fabricBranchApiKey)) {
                    Log.i(TAG, "Branch Warning: Please enter your branch_key in your project's Manifest file!");
                    isNewBranchKeySet = branchReferral_.prefHelper_.setBranchKey("bnc_no_value");
                } else {
                    isNewBranchKeySet = branchReferral_.prefHelper_.setBranchKey(fabricBranchApiKey);
                }
            } else {
                isNewBranchKeySet = branchReferral_.prefHelper_.setBranchKey(branchKey);
            }
            if (isNewBranchKeySet) {
                branchReferral_.linkCache_.clear();
                branchReferral_.requestQueue_.clear();
            }
            branchReferral_.context_ = context.getApplicationContext();
            if (context instanceof Application) {
                isAutoSessionMode_ = true;
                branchReferral_.setActivityLifeCycleObserver((Application) context);
            }
        }
        return branchReferral_;
    }

    public static Branch getInstance(@NonNull Context context) {
        return getBranchInstance(context, true);
    }

    public static Branch getTestInstance(@NonNull Context context) {
        return getBranchInstance(context, false);
    }

    @TargetApi(14)
    public static Branch getAutoInstance(@NonNull Context context) {
        boolean isLive = true;
        isAutoSessionMode_ = true;
        customReferrableSettings_ = CUSTOM_REFERRABLE_SETTINGS.USE_DEFAULT;
        if (BranchUtil.isTestModeEnabled(context)) {
            isLive = false;
        }
        getBranchInstance(context, isLive);
        return branchReferral_;
    }

    @TargetApi(14)
    public static Branch getAutoInstance(@NonNull Context context, boolean isReferrable) {
        isAutoSessionMode_ = true;
        customReferrableSettings_ = isReferrable ? CUSTOM_REFERRABLE_SETTINGS.REFERRABLE : CUSTOM_REFERRABLE_SETTINGS.NON_REFERRABLE;
        getBranchInstance(context, !BranchUtil.isTestModeEnabled(context));
        return branchReferral_;
    }

    @TargetApi(14)
    public static Branch getAutoInstance(@NonNull Context context, @NonNull String branchKey) {
        boolean isLive = true;
        isAutoSessionMode_ = true;
        customReferrableSettings_ = CUSTOM_REFERRABLE_SETTINGS.USE_DEFAULT;
        if (BranchUtil.isTestModeEnabled(context)) {
            isLive = false;
        }
        getBranchInstance(context, isLive);
        if (!branchKey.startsWith("key_")) {
            Log.e(TAG, "Branch Key is invalid.Please check your BranchKey");
        } else if (branchReferral_.prefHelper_.setBranchKey(branchKey)) {
            branchReferral_.linkCache_.clear();
            branchReferral_.requestQueue_.clear();
        }
        return branchReferral_;
    }

    @TargetApi(14)
    public static Branch getAutoTestInstance(@NonNull Context context) {
        isAutoSessionMode_ = true;
        customReferrableSettings_ = CUSTOM_REFERRABLE_SETTINGS.USE_DEFAULT;
        getBranchInstance(context, false);
        return branchReferral_;
    }

    @TargetApi(14)
    public static Branch getAutoTestInstance(@NonNull Context context, boolean isReferrable) {
        isAutoSessionMode_ = true;
        customReferrableSettings_ = isReferrable ? CUSTOM_REFERRABLE_SETTINGS.REFERRABLE : CUSTOM_REFERRABLE_SETTINGS.NON_REFERRABLE;
        getBranchInstance(context, false);
        return branchReferral_;
    }

    private static Branch initInstance(@NonNull Context context) {
        return new Branch(context.getApplicationContext());
    }

    public void resetUserSession() {
        this.initState_ = SESSION_STATE.UNINITIALISED;
    }

    public void setRetryCount(int retryCount) {
        if (this.prefHelper_ != null && retryCount >= 0) {
            this.prefHelper_.setRetryCount(retryCount);
        }
    }

    public void setRetryInterval(int retryInterval) {
        if (this.prefHelper_ != null && retryInterval > 0) {
            this.prefHelper_.setRetryInterval(retryInterval);
        }
    }

    public void setNetworkTimeout(int timeout) {
        if (this.prefHelper_ != null && timeout > 0) {
            this.prefHelper_.setTimeout(timeout);
        }
    }

    public static void disableDeviceIDFetch(Boolean deviceIdFetch) {
        disableDeviceIDFetch_ = deviceIdFetch.booleanValue();
    }

    public static boolean isDeviceIDFetchDisabled() {
        return disableDeviceIDFetch_;
    }

    public void setDeepLinkDebugMode(JSONObject debugParams) {
        this.deeplinkDebugParams_ = debugParams;
    }

    public void disableAppList() {
        this.prefHelper_.disableExternAppListing();
    }

    public void enableFacebookAppLinkCheck() {
        this.enableFacebookAppLinkCheck_ = true;
    }

    public void setRequestMetadata(@NonNull String key, @NonNull String value) {
        this.prefHelper_.setRequestMetadata(key, value);
    }

    public boolean initSession(BranchUniversalReferralInitListener callback) {
        return initSession(callback, (Activity) null);
    }

    public boolean initSession(BranchReferralInitListener callback) {
        return initSession(callback, (Activity) null);
    }

    public boolean initSession(BranchUniversalReferralInitListener callback, Activity activity) {
        if (customReferrableSettings_ == CUSTOM_REFERRABLE_SETTINGS.USE_DEFAULT) {
            initUserSessionInternal(callback, activity, true);
        } else {
            initUserSessionInternal(callback, activity, customReferrableSettings_ == CUSTOM_REFERRABLE_SETTINGS.REFERRABLE);
        }
        return true;
    }

    public boolean initSession(BranchReferralInitListener callback, Activity activity) {
        if (customReferrableSettings_ == CUSTOM_REFERRABLE_SETTINGS.USE_DEFAULT) {
            initUserSessionInternal(callback, activity, true);
        } else {
            initUserSessionInternal(callback, activity, customReferrableSettings_ == CUSTOM_REFERRABLE_SETTINGS.REFERRABLE);
        }
        return true;
    }

    public boolean initSession(BranchUniversalReferralInitListener callback, @NonNull Uri data) {
        return initSession(callback, data, null);
    }

    public boolean initSession(BranchReferralInitListener callback, @NonNull Uri data) {
        return initSession(callback, data, null);
    }

    public boolean initSession(BranchUniversalReferralInitListener callback, @NonNull Uri data, Activity activity) {
        readAndStripParam(data, activity);
        initSession(callback, activity);
        return true;
    }

    public boolean initSession(BranchReferralInitListener callback, @NonNull Uri data, Activity activity) {
        readAndStripParam(data, activity);
        return initSession(callback, activity);
    }

    public boolean initSession() {
        return initSession((Activity) null);
    }

    public boolean initSession(Activity activity) {
        return initSession((BranchReferralInitListener) null, activity);
    }

    public boolean initSessionWithData(@NonNull Uri data) {
        return initSessionWithData(data, null);
    }

    public boolean initSessionWithData(Uri data, Activity activity) {
        readAndStripParam(data, activity);
        return initSession((BranchReferralInitListener) null, activity);
    }

    public boolean initSession(boolean isReferrable) {
        return initSession((BranchReferralInitListener) null, isReferrable, (Activity) null);
    }

    public boolean initSession(boolean isReferrable, @NonNull Activity activity) {
        return initSession((BranchReferralInitListener) null, isReferrable, activity);
    }

    public boolean initSession(BranchUniversalReferralInitListener callback, boolean isReferrable, Uri data) {
        return initSession(callback, isReferrable, data, null);
    }

    public boolean initSession(BranchReferralInitListener callback, boolean isReferrable, @NonNull Uri data) {
        return initSession(callback, isReferrable, data, null);
    }

    public boolean initSession(BranchUniversalReferralInitListener callback, boolean isReferrable, @NonNull Uri data, Activity activity) {
        readAndStripParam(data, activity);
        return initSession(callback, isReferrable, activity);
    }

    public boolean initSession(BranchReferralInitListener callback, boolean isReferrable, @NonNull Uri data, Activity activity) {
        readAndStripParam(data, activity);
        return initSession(callback, isReferrable, activity);
    }

    public boolean initSession(BranchUniversalReferralInitListener callback, boolean isReferrable) {
        return initSession(callback, isReferrable, (Activity) null);
    }

    public boolean initSession(BranchReferralInitListener callback, boolean isReferrable) {
        return initSession(callback, isReferrable, (Activity) null);
    }

    public boolean initSession(BranchUniversalReferralInitListener callback, boolean isReferrable, Activity activity) {
        initUserSessionInternal(callback, activity, isReferrable);
        return true;
    }

    public boolean initSession(BranchReferralInitListener callback, boolean isReferrable, Activity activity) {
        initUserSessionInternal(callback, activity, isReferrable);
        return true;
    }

    private void initUserSessionInternal(BranchUniversalReferralInitListener callback, Activity activity, boolean isReferrable) {
        initUserSessionInternal(new BranchUniversalReferralInitWrapper(callback), activity, isReferrable);
    }

    private void initUserSessionInternal(BranchReferralInitListener callback, Activity activity, boolean isReferrable) {
        if (activity != null) {
            this.currentActivityReference_ = new WeakReference(activity);
        }
        if (!hasUser() || !hasSession() || this.initState_ != SESSION_STATE.INITIALISED) {
            if (isReferrable) {
                this.prefHelper_.setIsReferrable();
            } else {
                this.prefHelper_.clearIsReferrable();
            }
            if (this.initState_ != SESSION_STATE.INITIALISING) {
                this.initState_ = SESSION_STATE.INITIALISING;
                initializeSession(callback);
            } else if (callback != null) {
                this.requestQueue_.setInstallOrOpenCallback(callback);
            }
        } else if (callback == null) {
        } else {
            if (!isAutoSessionMode_) {
                callback.onInitFinished(new JSONObject(), null);
            } else if (this.isInitReportedThroughCallBack) {
                callback.onInitFinished(new JSONObject(), null);
            } else {
                callback.onInitFinished(getLatestReferringParams(), null);
                this.isInitReportedThroughCallBack = true;
            }
        }
    }

    public void closeSession() {
        Log.w(TAG, "closeSession() method is deprecated from SDK v1.14.6.Session is  automatically handled by Branch.In case you need to handle sessions manually inorder to support minimum sdk version less than 14 please consider using  SDK version 1.14.5");
    }

    private void closeSessionInternal() {
        executeClose();
        this.sessionReferredLink_ = null;
        if (this.prefHelper_.getExternAppListing() && this.appListingSchedule_ == null) {
            scheduleListOfApps();
        }
    }

    public static void enableCookieBasedMatching(String cookieMatchDomain) {
        cookieBasedMatchDomain_ = cookieMatchDomain;
    }

    public static void enableCookieBasedMatching(String cookieMatchDomain, int delay) {
        cookieBasedMatchDomain_ = cookieMatchDomain;
        BranchStrongMatchHelper.getInstance().setStrongMatchUrlHitDelay(delay);
    }

    private void executeClose() {
        if (this.initState_ != SESSION_STATE.UNINITIALISED) {
            if (!this.hasNetwork_) {
                ServerRequest req = this.requestQueue_.peek();
                if ((req != null && (req instanceof ServerRequestRegisterInstall)) || (req instanceof ServerRequestRegisterOpen)) {
                    this.requestQueue_.dequeue();
                }
            } else if (!this.requestQueue_.containsClose()) {
                handleNewRequest(new ServerRequestRegisterClose(this.context_));
            }
            this.initState_ = SESSION_STATE.UNINITIALISED;
        }
    }

    private boolean readAndStripParam(Uri data, Activity activity) {
        if (this.intentState_ == INTENT_STATE.READY) {
            if (data != null) {
                boolean skipThisHost = false;
                try {
                    boolean foundSchemeMatch;
                    if (this.externalUriWhiteList_.size() > 0) {
                        foundSchemeMatch = this.externalUriWhiteList_.contains(data.getScheme());
                    } else {
                        foundSchemeMatch = true;
                    }
                    if (this.skipExternalUriHosts_.size() > 0) {
                        for (String host : this.skipExternalUriHosts_) {
                            String externalHost = data.getHost();
                            if (externalHost != null && externalHost.equals(host)) {
                                skipThisHost = true;
                                break;
                            }
                        }
                    }
                    if (foundSchemeMatch && !skipThisHost) {
                        this.sessionReferredLink_ = data.toString();
                        this.prefHelper_.setExternalIntentUri(data.toString());
                        if (!(activity == null || activity.getIntent() == null || activity.getIntent().getExtras() == null)) {
                            Bundle bundle = activity.getIntent().getExtras();
                            Set<String> extraKeys = bundle.keySet();
                            if (extraKeys.size() > 0) {
                                JSONObject extrasJson = new JSONObject();
                                for (String key : EXTERNAL_INTENT_EXTRA_KEY_WHITE_LIST) {
                                    if (extraKeys.contains(key)) {
                                        extrasJson.put(key, bundle.get(key));
                                    }
                                }
                                if (extrasJson.length() > 0) {
                                    this.prefHelper_.setExternalIntentExtra(extrasJson.toString());
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                }
            }
            if (activity != null) {
                try {
                    if (!(activity.getIntent() == null || activity.getIntent().getExtras() == null || activity.getIntent().getExtras().getBoolean(Jsonkey.BranchLinkUsed.getKey()))) {
                        String pushIdentifier = activity.getIntent().getExtras().getString(Jsonkey.AndroidPushNotificationKey.getKey());
                        if (pushIdentifier != null && pushIdentifier.length() > 0) {
                            this.prefHelper_.setPushIdentifier(pushIdentifier);
                            Intent thisIntent = activity.getIntent();
                            thisIntent.putExtra(Jsonkey.BranchLinkUsed.getKey(), true);
                            activity.setIntent(thisIntent);
                            return false;
                        }
                    }
                } catch (Exception e2) {
                }
            }
            if (!(data == null || !data.isHierarchical() || activity == null)) {
                try {
                    if (data.getQueryParameter(Jsonkey.LinkClickID.getKey()) != null) {
                        this.prefHelper_.setLinkClickIdentifier(data.getQueryParameter(Jsonkey.LinkClickID.getKey()));
                        String paramString = "link_click_id=" + data.getQueryParameter(Jsonkey.LinkClickID.getKey());
                        String uriString = null;
                        if (activity.getIntent() != null) {
                            uriString = activity.getIntent().getDataString();
                        }
                        if (data.getQuery().length() == paramString.length()) {
                            paramString = "\\?" + paramString;
                        } else if (uriString == null || uriString.length() - paramString.length() != uriString.indexOf(paramString)) {
                            paramString = paramString + "&";
                        } else {
                            paramString = "&" + paramString;
                        }
                        if (uriString != null) {
                            activity.getIntent().setData(Uri.parse(uriString.replaceFirst(paramString, "")));
                        } else {
                            Log.w(TAG, "Branch Warning. URI for the launcher activity is null. Please make sure that intent data is not set to null before calling Branch#InitSession ");
                        }
                        return true;
                    }
                    String scheme = data.getScheme();
                    Intent intent = activity.getIntent();
                    if (scheme != null && intent != null && (intent.getFlags() & 1048576) == 0 && ((scheme.equalsIgnoreCase(Constants.HTTP) || scheme.equalsIgnoreCase(Constants.HTTPS)) && data.getHost() != null && data.getHost().length() > 0 && !intent.getBooleanExtra(Jsonkey.BranchLinkUsed.getKey(), false))) {
                        this.prefHelper_.setAppLink(data.toString());
                        intent.putExtra(Jsonkey.BranchLinkUsed.getKey(), true);
                        activity.setIntent(intent);
                        return false;
                    }
                } catch (Exception e3) {
                }
            }
        }
        return false;
    }

    public void onGAdsFetchFinished() {
        this.isGAParamsFetchInProgress_ = false;
        this.requestQueue_.unlockProcessWait(PROCESS_WAIT_LOCK.GAID_FETCH_WAIT_LOCK);
        if (this.performCookieBasedStrongMatchingOnGAIDAvailable) {
            performCookieBasedStrongMatch();
            this.performCookieBasedStrongMatchingOnGAIDAvailable = false;
            return;
        }
        processNextQueueItem();
    }

    public void onInstallReferrerEventsFinished() {
        this.requestQueue_.unlockProcessWait(PROCESS_WAIT_LOCK.INSTALL_REFERRER_FETCH_WAIT_LOCK);
        processNextQueueItem();
    }

    public Branch addWhiteListedScheme(String uriScheme) {
        if (uriScheme != null) {
            this.externalUriWhiteList_.add(uriScheme.replace("://", ""));
        }
        return this;
    }

    public Branch setWhiteListedSchemes(List<String> uriSchemes) {
        this.externalUriWhiteList_ = uriSchemes;
        return this;
    }

    public Branch addUriHostsToSkip(String hostName) {
        if (!(hostName == null || hostName.equals(""))) {
            this.skipExternalUriHosts_.add(hostName);
        }
        return this;
    }

    public void setIdentity(@NonNull String userId) {
        setIdentity(userId, null);
    }

    public void setIdentity(@NonNull String userId, @Nullable BranchReferralInitListener callback) {
        ServerRequest req = new ServerRequestIdentifyUserRequest(this.context_, callback, userId);
        if (!req.constructError_ && !req.handleErrors(this.context_)) {
            handleNewRequest(req);
        } else if (((ServerRequestIdentifyUserRequest) req).isExistingID()) {
            ((ServerRequestIdentifyUserRequest) req).handleUserExist(branchReferral_);
        }
    }

    public boolean isUserIdentified() {
        return !this.prefHelper_.getIdentity().equals("bnc_no_value");
    }

    public void logout() {
        logout(null);
    }

    public void logout(LogoutStatusListener callback) {
        ServerRequest req = new ServerRequestLogout(this.context_, callback);
        if (!req.constructError_ && !req.handleErrors(this.context_)) {
            handleNewRequest(req);
        }
    }

    public void loadRewards() {
        loadRewards(null);
    }

    public void loadRewards(BranchReferralStateChangedListener callback) {
        ServerRequest req = new ServerRequestGetRewards(this.context_, callback);
        if (!req.constructError_ && !req.handleErrors(this.context_)) {
            handleNewRequest(req);
        }
    }

    public int getCredits() {
        return this.prefHelper_.getCreditCount();
    }

    public int getCreditsForBucket(String bucket) {
        return this.prefHelper_.getCreditCount(bucket);
    }

    public void redeemRewards(int count) {
        redeemRewards(Jsonkey.DefaultBucket.getKey(), count, null);
    }

    public void redeemRewards(int count, BranchReferralStateChangedListener callback) {
        redeemRewards(Jsonkey.DefaultBucket.getKey(), count, callback);
    }

    public void redeemRewards(@NonNull String bucket, int count) {
        redeemRewards(bucket, count, null);
    }

    public void redeemRewards(@NonNull String bucket, int count, BranchReferralStateChangedListener callback) {
        ServerRequestRedeemRewards req = new ServerRequestRedeemRewards(this.context_, bucket, count, callback);
        if (!req.constructError_ && !req.handleErrors(this.context_)) {
            handleNewRequest(req);
        }
    }

    public void getCreditHistory(BranchListResponseListener callback) {
        getCreditHistory(null, null, 100, CreditHistoryOrder.kMostRecentFirst, callback);
    }

    public void getCreditHistory(@NonNull String bucket, BranchListResponseListener callback) {
        getCreditHistory(bucket, null, 100, CreditHistoryOrder.kMostRecentFirst, callback);
    }

    public void getCreditHistory(@NonNull String afterId, int length, @NonNull CreditHistoryOrder order, BranchListResponseListener callback) {
        getCreditHistory(null, afterId, length, order, callback);
    }

    public void getCreditHistory(String bucket, String afterId, int length, @NonNull CreditHistoryOrder order, BranchListResponseListener callback) {
        ServerRequest req = new ServerRequestGetRewardHistory(this.context_, bucket, afterId, length, order, callback);
        if (!req.constructError_ && !req.handleErrors(this.context_)) {
            handleNewRequest(req);
        }
    }

    public void userCompletedAction(@NonNull String action, JSONObject metadata) {
        userCompletedAction(action, metadata, null);
    }

    public void userCompletedAction(String action) {
        userCompletedAction(action, null, null);
    }

    public void userCompletedAction(String action, BranchViewHandler$IBranchViewEvents callback) {
        userCompletedAction(action, null, callback);
    }

    public void userCompletedAction(@NonNull String action, JSONObject metadata, BranchViewHandler$IBranchViewEvents callback) {
        if (metadata != null) {
            metadata = BranchUtil.filterOutBadCharacters(metadata);
        }
        ServerRequest req = new ServerRequestActionCompleted(this.context_, action, metadata, callback);
        if (!req.constructError_ && !req.handleErrors(this.context_)) {
            handleNewRequest(req);
        }
    }

    public void sendCommerceEvent(@NonNull CommerceEvent commerceEvent, JSONObject metadata, BranchViewHandler$IBranchViewEvents callback) {
        if (metadata != null) {
            metadata = BranchUtil.filterOutBadCharacters(metadata);
        }
        ServerRequest req = new ServerRequestRActionCompleted(this.context_, commerceEvent, metadata, callback);
        if (!req.constructError_ && !req.handleErrors(this.context_)) {
            handleNewRequest(req);
        }
    }

    public void sendCommerceEvent(@NonNull CommerceEvent commerceEvent) {
        sendCommerceEvent(commerceEvent, null, null);
    }

    public JSONObject getFirstReferringParams() {
        return appendDebugParams(convertParamsStringToDictionary(this.prefHelper_.getInstallParams()));
    }

    public JSONObject getFirstReferringParamsSync() {
        this.getFirstReferringParamsLatch = new CountDownLatch(1);
        if (this.prefHelper_.getInstallParams().equals("bnc_no_value")) {
            try {
                this.getFirstReferringParamsLatch.await((long) LATCH_WAIT_UNTIL, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
            }
        }
        JSONObject firstReferringParams = appendDebugParams(convertParamsStringToDictionary(this.prefHelper_.getInstallParams()));
        this.getFirstReferringParamsLatch = null;
        return firstReferringParams;
    }

    public JSONObject getLatestReferringParams() {
        return appendDebugParams(convertParamsStringToDictionary(this.prefHelper_.getSessionParams()));
    }

    public JSONObject getLatestReferringParamsSync() {
        this.getLatestReferringParamsLatch = new CountDownLatch(1);
        try {
            if (this.initState_ != SESSION_STATE.INITIALISED) {
                this.getLatestReferringParamsLatch.await((long) LATCH_WAIT_UNTIL, TimeUnit.MILLISECONDS);
            }
        } catch (InterruptedException e) {
        }
        JSONObject latestParams = appendDebugParams(convertParamsStringToDictionary(this.prefHelper_.getSessionParams()));
        this.getLatestReferringParamsLatch = null;
        return latestParams;
    }

    private JSONObject appendDebugParams(JSONObject originalParams) {
        if (originalParams != null) {
            try {
                if (this.deeplinkDebugParams_ != null) {
                    if (this.deeplinkDebugParams_.length() > 0) {
                        Log.w(TAG, "You're currently in deep link debug mode. Please comment out 'setDeepLinkDebugMode' to receive the deep link parameters from a real Branch link");
                    }
                    Iterator<String> keys = this.deeplinkDebugParams_.keys();
                    while (keys.hasNext()) {
                        String key = (String) keys.next();
                        originalParams.put(key, this.deeplinkDebugParams_.get(key));
                    }
                }
            } catch (Exception e) {
            }
        }
        return originalParams;
    }

    public JSONObject getDeeplinkDebugParams() {
        if (this.deeplinkDebugParams_ != null && this.deeplinkDebugParams_.length() > 0) {
            Log.w(TAG, "You're currently in deep link debug mode. Please comment out 'setDeepLinkDebugMode' to receive the deep link parameters from a real Branch link");
        }
        return this.deeplinkDebugParams_;
    }

    String generateShortLinkInternal(ServerRequestCreateUrl req) {
        if (!(req.constructError_ || req.handleErrors(this.context_))) {
            if (this.linkCache_.containsKey(req.getLinkPost())) {
                String url = (String) this.linkCache_.get(req.getLinkPost());
                req.onUrlAvailable(url);
                return url;
            } else if (!req.isAsync()) {
                return generateShortLinkSync(req);
            } else {
                generateShortLinkAsync(req);
            }
        }
        return null;
    }

    private void shareLink(ShareLinkBuilder builder) {
        if (this.shareLinkManager_ != null) {
            this.shareLinkManager_.cancelShareLinkDialog(true);
        }
        this.shareLinkManager_ = new ShareLinkManager();
        this.shareLinkManager_.shareLink(builder);
    }

    public void cancelShareLinkDialog(boolean animateClose) {
        if (this.shareLinkManager_ != null) {
            this.shareLinkManager_.cancelShareLinkDialog(animateClose);
        }
    }

    private String convertDate(Date date) {
        return DateFormat.format("yyyy-MM-dd", date).toString();
    }

    private String generateShortLinkSync(ServerRequestCreateUrl req) {
        String url = null;
        if (this.initState_ == SESSION_STATE.INITIALISED) {
            ServerResponse response = null;
            try {
                int timeOut = this.prefHelper_.getTimeout() + SESSION_KEEPALIVE;
                response = (ServerResponse) new getShortLinkTask(this, null).execute(new ServerRequest[]{req}).get((long) timeOut, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
            } catch (ExecutionException e2) {
            } catch (TimeoutException e3) {
            }
            url = null;
            if (req.isDefaultToLongUrl()) {
                url = req.getLongUrl();
            }
            if (response != null && response.getStatusCode() == 200) {
                try {
                    url = response.getObject().getString("url");
                    if (req.getLinkPost() != null) {
                        this.linkCache_.put(req.getLinkPost(), url);
                    }
                } catch (JSONException e4) {
                    e4.printStackTrace();
                }
            }
        } else {
            Log.i(TAG, "Branch Warning: User session has not been initialized");
        }
        return url;
    }

    private void generateShortLinkAsync(ServerRequest req) {
        handleNewRequest(req);
    }

    private JSONObject convertParamsStringToDictionary(String paramString) {
        if (paramString.equals("bnc_no_value")) {
            return new JSONObject();
        }
        try {
            return new JSONObject(paramString);
        } catch (JSONException e) {
            try {
                return new JSONObject(new String(Base64.decode(paramString.getBytes(), 2)));
            } catch (JSONException ex) {
                ex.printStackTrace();
                return new JSONObject();
            }
        }
    }

    private void scheduleListOfApps() {
        ScheduledThreadPoolExecutor scheduler = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);
        Runnable periodicTask = new 1(this);
        Date date = new Date();
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        int days = 7 - calendar.get(7);
        int hours = 2 - calendar.get(11);
        if (days == 0 && hours < 0) {
            days = 7;
        }
        this.appListingSchedule_ = scheduler.scheduleAtFixedRate(periodicTask, (long) ((((days * 24) + hours) * 60) * 60), (long) 604800, TimeUnit.SECONDS);
    }

    private void processNextQueueItem() {
        try {
            this.serverSema_.acquire();
            if (this.networkCount_ != 0 || this.requestQueue_.getSize() <= 0) {
                this.serverSema_.release();
                return;
            }
            this.networkCount_ = 1;
            ServerRequest req = this.requestQueue_.peek();
            this.serverSema_.release();
            if (req == null) {
                this.requestQueue_.remove(null);
            } else if (req.isWaitingOnProcessToFinish()) {
                this.networkCount_ = 0;
            } else if (!(req instanceof ServerRequestRegisterInstall) && !hasUser()) {
                Log.i(TAG, "Branch Error: User session has not been initialized!");
                this.networkCount_ = 0;
                handleFailure(this.requestQueue_.getSize() - 1, (int) BranchError.ERR_NO_SESSION);
            } else if ((req instanceof ServerRequestInitSession) || (hasSession() && hasDeviceFingerPrint())) {
                new BranchPostTask(this, req).executeTask(new Void[0]);
            } else {
                this.networkCount_ = 0;
                handleFailure(this.requestQueue_.getSize() - 1, (int) BranchError.ERR_NO_SESSION);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleFailure(int index, int statusCode) {
        ServerRequest req;
        if (index >= this.requestQueue_.getSize()) {
            req = this.requestQueue_.peekAt(this.requestQueue_.getSize() - 1);
        } else {
            req = this.requestQueue_.peekAt(index);
        }
        handleFailure(req, statusCode);
    }

    private void handleFailure(ServerRequest req, int statusCode) {
        if (req != null) {
            req.handleFailure(statusCode, "");
        }
    }

    private void updateAllRequestsInQueue() {
        int i = 0;
        while (i < this.requestQueue_.getSize()) {
            try {
                ServerRequest req = this.requestQueue_.peekAt(i);
                if (req != null) {
                    JSONObject reqJson = req.getPost();
                    if (reqJson != null) {
                        if (reqJson.has(Jsonkey.SessionID.getKey())) {
                            req.getPost().put(Jsonkey.SessionID.getKey(), this.prefHelper_.getSessionID());
                        }
                        if (reqJson.has(Jsonkey.IdentityID.getKey())) {
                            req.getPost().put(Jsonkey.IdentityID.getKey(), this.prefHelper_.getIdentityID());
                        }
                        if (reqJson.has(Jsonkey.DeviceFingerprintID.getKey())) {
                            req.getPost().put(Jsonkey.DeviceFingerprintID.getKey(), this.prefHelper_.getDeviceFingerPrintID());
                        }
                    }
                }
                i++;
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
    }

    private boolean hasSession() {
        return !this.prefHelper_.getSessionID().equals("bnc_no_value");
    }

    private boolean hasDeviceFingerPrint() {
        return !this.prefHelper_.getDeviceFingerPrintID().equals("bnc_no_value");
    }

    private boolean hasUser() {
        return !this.prefHelper_.getIdentityID().equals("bnc_no_value");
    }

    private void insertRequestAtFront(ServerRequest req) {
        if (this.networkCount_ == 0) {
            this.requestQueue_.insert(req, 0);
        } else {
            this.requestQueue_.insert(req, 1);
        }
    }

    private void registerInstallOrOpen(ServerRequest req, BranchReferralInitListener callback) {
        if (this.requestQueue_.containsInstallOrOpen()) {
            if (callback != null) {
                this.requestQueue_.setInstallOrOpenCallback(callback);
            }
            this.requestQueue_.moveInstallOrOpenToFront(req, this.networkCount_, callback);
        } else {
            insertRequestAtFront(req);
        }
        processNextQueueItem();
    }

    private void initializeSession(BranchReferralInitListener callback) {
        if (this.prefHelper_.getBranchKey() == null || this.prefHelper_.getBranchKey().equalsIgnoreCase("bnc_no_value")) {
            this.initState_ = SESSION_STATE.UNINITIALISED;
            if (callback != null) {
                callback.onInitFinished(null, new BranchError("Trouble initializing Branch.", RemoteInterface.NO_BRANCH_KEY_STATUS));
            }
            Log.i(TAG, "Branch Warning: Please enter your branch_key in your project's res/values/strings.xml!");
            return;
        }
        if (this.prefHelper_.getBranchKey() != null && this.prefHelper_.getBranchKey().startsWith("key_test_")) {
            Log.i(TAG, "Branch Warning: You are using your test app's Branch Key. Remember to change it to live Branch Key during deployment.");
        }
        if (!this.prefHelper_.getExternalIntentUri().equals("bnc_no_value") || !this.enableFacebookAppLinkCheck_) {
            registerAppInit(callback, null);
        } else if (DeferredAppLinkDataHandler.fetchDeferredAppLinkData(this.context_, new 2(this)).booleanValue()) {
            registerAppInit(callback, PROCESS_WAIT_LOCK.FB_APP_LINK_WAIT_LOCK);
        } else {
            registerAppInit(callback, null);
        }
    }

    private void registerAppInit(BranchReferralInitListener callback, PROCESS_WAIT_LOCK lock) {
        ServerRequest request;
        if (hasUser()) {
            request = new ServerRequestRegisterOpen(this.context_, callback, this.kRemoteInterface_.getSystemObserver());
        } else {
            request = new ServerRequestRegisterInstall(this.context_, callback, this.kRemoteInterface_.getSystemObserver(), InstallListener.getInstallationID());
        }
        request.addProcessWaitLock(lock);
        if (this.isGAParamsFetchInProgress_) {
            request.addProcessWaitLock(PROCESS_WAIT_LOCK.GAID_FETCH_WAIT_LOCK);
        }
        if (this.intentState_ != INTENT_STATE.READY) {
            request.addProcessWaitLock(PROCESS_WAIT_LOCK.INTENT_PENDING_WAIT_LOCK);
        }
        if (checkPlayStoreReferrer() && (request instanceof ServerRequestRegisterInstall)) {
            request.addProcessWaitLock(PROCESS_WAIT_LOCK.INSTALL_REFERRER_FETCH_WAIT_LOCK);
            InstallListener.startInstallReferrerTime(PLAYSTORE_REFERRAL_FETCH_WAIT_FOR);
        }
        registerInstallOrOpen(request, callback);
    }

    private void onIntentReady(Activity activity) {
        this.requestQueue_.unlockProcessWait(PROCESS_WAIT_LOCK.INTENT_PENDING_WAIT_LOCK);
        if (activity.getIntent() != null) {
            readAndStripParam(activity.getIntent().getData(), activity);
            if (cookieBasedMatchDomain_ == null || this.prefHelper_.getBranchKey() == null || this.prefHelper_.getBranchKey().equalsIgnoreCase("bnc_no_value")) {
                processNextQueueItem();
                return;
            } else if (this.isGAParamsFetchInProgress_) {
                this.performCookieBasedStrongMatchingOnGAIDAvailable = true;
                return;
            } else {
                performCookieBasedStrongMatch();
                return;
            }
        }
        processNextQueueItem();
    }

    private void performCookieBasedStrongMatch() {
        boolean simulateInstall = this.prefHelper_.getExternDebug() || isSimulatingInstalls();
        DeviceInfo deviceInfo = DeviceInfo.getInstance(simulateInstall, this.systemObserver_, disableDeviceIDFetch_);
        Activity currentActivity = null;
        if (this.currentActivityReference_ != null) {
            currentActivity = (Activity) this.currentActivityReference_.get();
        }
        Context context = currentActivity != null ? currentActivity.getApplicationContext() : null;
        if (context != null) {
            this.requestQueue_.setStrongMatchWaitLock();
            BranchStrongMatchHelper.getInstance().checkForStrongMatch(context, cookieBasedMatchDomain_, deviceInfo, this.prefHelper_, this.systemObserver_, new 3(this));
        }
    }

    public void handleNewRequest(ServerRequest req) {
        boolean isReferrable = true;
        if (!(this.initState_ == SESSION_STATE.INITIALISED || (req instanceof ServerRequestInitSession))) {
            if (req instanceof ServerRequestLogout) {
                req.handleFailure(BranchError.ERR_NO_SESSION, "");
                Log.i(TAG, "Branch is not initialized, cannot logout");
                return;
            } else if (req instanceof ServerRequestRegisterClose) {
                Log.i(TAG, "Branch is not initialized, cannot close session");
                return;
            } else {
                Activity currentActivity = null;
                if (this.currentActivityReference_ != null) {
                    currentActivity = (Activity) this.currentActivityReference_.get();
                }
                if (customReferrableSettings_ == CUSTOM_REFERRABLE_SETTINGS.USE_DEFAULT) {
                    initUserSessionInternal((BranchReferralInitListener) null, currentActivity, true);
                } else {
                    if (customReferrableSettings_ != CUSTOM_REFERRABLE_SETTINGS.REFERRABLE) {
                        isReferrable = false;
                    }
                    initUserSessionInternal((BranchReferralInitListener) null, currentActivity, isReferrable);
                }
            }
        }
        this.requestQueue_.enqueue(req);
        req.onRequestQueued();
        processNextQueueItem();
    }

    @TargetApi(14)
    private void setActivityLifeCycleObserver(Application application) {
        try {
            BranchActivityLifeCycleObserver activityLifeCycleObserver = new BranchActivityLifeCycleObserver(this, null);
            application.unregisterActivityLifecycleCallbacks(activityLifeCycleObserver);
            application.registerActivityLifecycleCallbacks(activityLifeCycleObserver);
            isActivityLifeCycleCallbackRegistered_ = true;
            return;
        } catch (NoSuchMethodError e) {
        } catch (NoClassDefFoundError e2) {
        }
        isActivityLifeCycleCallbackRegistered_ = false;
        isAutoSessionMode_ = false;
        Log.w(TAG, new BranchError("", BranchError.ERR_API_LVL_14_NEEDED).getMessage());
    }

    private void startSession(Activity activity) {
        Uri intentData = null;
        if (activity.getIntent() != null) {
            intentData = activity.getIntent().getData();
        }
        initSessionWithData(intentData, activity);
    }

    private boolean checkIntentForSessionRestart(Intent intent) {
        boolean isRestartSessionRequested = false;
        if (intent != null) {
            isRestartSessionRequested = intent.getBooleanExtra(Jsonkey.ForceNewBranchSession.getKey(), false);
            if (isRestartSessionRequested) {
                intent.putExtra(Jsonkey.ForceNewBranchSession.getKey(), false);
            }
        }
        return isRestartSessionRequested;
    }

    public static boolean isAutoDeepLinkLaunch(Activity activity) {
        return activity.getIntent().getStringExtra(AUTO_DEEP_LINKED) != null;
    }

    private void checkForAutoDeepLinkConfiguration() {
        JSONObject latestParams = getLatestReferringParams();
        String deepLinkActivity = null;
        try {
            if (latestParams.has(Jsonkey.Clicked_Branch_Link.getKey()) && latestParams.getBoolean(Jsonkey.Clicked_Branch_Link.getKey()) && latestParams.length() > 0) {
                ApplicationInfo appInfo = this.context_.getPackageManager().getApplicationInfo(this.context_.getPackageName(), 128);
                if (appInfo.metaData == null || !appInfo.metaData.getBoolean(AUTO_DEEP_LINK_DISABLE, false)) {
                    ActivityInfo[] activityInfos = this.context_.getPackageManager().getPackageInfo(this.context_.getPackageName(), 129).activities;
                    int deepLinkActivityReqCode = DEF_AUTO_DEEP_LINK_REQ_CODE;
                    if (activityInfos != null) {
                        for (ActivityInfo activityInfo : activityInfos) {
                            if (activityInfo != null && activityInfo.metaData != null && ((activityInfo.metaData.getString(AUTO_DEEP_LINK_KEY) != null || activityInfo.metaData.getString(AUTO_DEEP_LINK_PATH) != null) && (checkForAutoDeepLinkKeys(latestParams, activityInfo) || checkForAutoDeepLinkPath(latestParams, activityInfo)))) {
                                deepLinkActivity = activityInfo.name;
                                deepLinkActivityReqCode = activityInfo.metaData.getInt(AUTO_DEEP_LINK_REQ_CODE, DEF_AUTO_DEEP_LINK_REQ_CODE);
                                break;
                            }
                        }
                    }
                    if (deepLinkActivity != null && this.currentActivityReference_ != null) {
                        Activity currentActivity = (Activity) this.currentActivityReference_.get();
                        if (currentActivity != null) {
                            Intent intent = new Intent(currentActivity, Class.forName(deepLinkActivity));
                            intent.putExtra(AUTO_DEEP_LINKED, ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                            intent.putExtra(Jsonkey.ReferringData.getKey(), latestParams.toString());
                            Iterator<?> keys = latestParams.keys();
                            while (keys.hasNext()) {
                                String key = (String) keys.next();
                                intent.putExtra(key, latestParams.getString(key));
                            }
                            currentActivity.startActivityForResult(intent, deepLinkActivityReqCode);
                            return;
                        }
                        Log.w(TAG, "No activity reference to launch deep linked activity");
                    }
                }
            }
        } catch (NameNotFoundException e) {
            Log.i(TAG, "Branch Warning: Please make sure Activity names set for auto deep link are correct!");
        } catch (ClassNotFoundException e2) {
            Log.i(TAG, "Branch Warning: Please make sure Activity names set for auto deep link are correct! Error while looking for activity " + deepLinkActivity);
        } catch (Exception e3) {
        }
    }

    private boolean checkForAutoDeepLinkKeys(JSONObject params, ActivityInfo activityInfo) {
        if (activityInfo.metaData.getString(AUTO_DEEP_LINK_KEY) == null) {
            return false;
        }
        for (String activityLinkKey : activityInfo.metaData.getString(AUTO_DEEP_LINK_KEY).split(",")) {
            if (params.has(activityLinkKey)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkForAutoDeepLinkPath(JSONObject params, ActivityInfo activityInfo) {
        String deepLinkPath = null;
        try {
            if (params.has(Jsonkey.AndroidDeepLinkPath.getKey())) {
                deepLinkPath = params.getString(Jsonkey.AndroidDeepLinkPath.getKey());
            } else if (params.has(Jsonkey.DeepLinkPath.getKey())) {
                deepLinkPath = params.getString(Jsonkey.DeepLinkPath.getKey());
            }
        } catch (JSONException e) {
        }
        if (activityInfo.metaData.getString(AUTO_DEEP_LINK_PATH) == null || deepLinkPath == null) {
            return false;
        }
        for (String activityLinkPath : activityInfo.metaData.getString(AUTO_DEEP_LINK_PATH).split(",")) {
            if (pathMatch(activityLinkPath.trim(), deepLinkPath)) {
                return true;
            }
        }
        return false;
    }

    private boolean pathMatch(String templatePath, String path) {
        boolean matched = true;
        String[] pathSegmentsTemplate = templatePath.split("\\?")[0].split("/");
        String[] pathSegmentsTarget = path.split("\\?")[0].split("/");
        if (pathSegmentsTemplate.length != pathSegmentsTarget.length) {
            return false;
        }
        int i = 0;
        while (i < pathSegmentsTemplate.length && i < pathSegmentsTarget.length) {
            String pathSegmentTemplate = pathSegmentsTemplate[i];
            if (!pathSegmentTemplate.equals(pathSegmentsTarget[i]) && !pathSegmentTemplate.contains("*")) {
                matched = false;
                break;
            }
            i++;
        }
        return matched;
    }

    public static void enableSimulateInstalls() {
        isSimulatingInstalls_ = true;
    }

    public static void disableSimulateInstalls() {
        isSimulatingInstalls_ = false;
    }

    public static boolean isSimulatingInstalls() {
        return isSimulatingInstalls_;
    }

    public static void enableLogging() {
        isLogging_ = true;
    }

    public static void disableLogging() {
        isLogging_ = false;
    }

    public static boolean getIsLogging() {
        return isLogging_;
    }

    public void registerView(BranchUniversalObject branchUniversalObject, RegisterViewStatusListener callback) {
        if (this.context_ != null) {
            ServerRequest req = new ServerRequestRegisterView(this.context_, branchUniversalObject, this.systemObserver_, callback);
            if (!req.constructError_ && !req.handleErrors(this.context_)) {
                handleNewRequest(req);
            }
        }
    }

    public void addExtraInstrumentationData(HashMap<String, String> instrumentationData) {
        this.instrumentationExtraData_.putAll(instrumentationData);
    }

    public void addExtraInstrumentationData(String key, String value) {
        this.instrumentationExtraData_.put(key, value);
    }

    public void onBranchViewVisible(String action, String branchViewID) {
    }

    public void onBranchViewAccepted(String action, String branchViewID) {
        if (ServerRequestInitSession.isInitSessionAction(action)) {
            checkForAutoDeepLinkConfiguration();
        }
    }

    public void onBranchViewCancelled(String action, String branchViewID) {
        if (ServerRequestInitSession.isInitSessionAction(action)) {
            checkForAutoDeepLinkConfiguration();
        }
    }

    public void onBranchViewError(int errorCode, String errorMsg, String action) {
        if (ServerRequestInitSession.isInitSessionAction(action)) {
            checkForAutoDeepLinkConfiguration();
        }
    }

    public static boolean isInstantApp(@NonNull Context context) {
        try {
            Context applicationContext = context.getApplicationContext();
            if (isInstantApp != null && applicationContext.equals(lastApplicationContext)) {
                return isInstantApp.booleanValue();
            }
            isInstantApp = null;
            lastApplicationContext = applicationContext;
            applicationContext.getClassLoader().loadClass("com.google.android.instantapps.supervisor.InstantAppsRuntime");
            isInstantApp = Boolean.valueOf(true);
            return isInstantApp.booleanValue();
        } catch (Exception e) {
            isInstantApp = Boolean.valueOf(false);
        }
    }

    public static boolean showInstallPrompt(@NonNull Activity activity, int requestCode) {
        String installReferrerString = "";
        if (getInstance() != null) {
            JSONObject latestReferringParams = getInstance().getLatestReferringParams();
            String referringLinkKey = "~" + Jsonkey.ReferringLink.getKey();
            if (latestReferringParams != null && latestReferringParams.has(referringLinkKey)) {
                try {
                    installReferrerString = Jsonkey.IsFullAppConv.getKey() + "=true&" + Jsonkey.ReferringLink.getKey() + "=" + latestReferringParams.getString(referringLinkKey);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return showInstallPrompt(activity, requestCode, installReferrerString);
    }

    public static boolean showInstallPrompt(@NonNull Activity activity, int requestCode, @Nullable String referrer) {
        return showInstallPrompt(activity, requestCode, Jsonkey.IsFullAppConv.getKey() + "=true&" + referrer);
    }

    public static boolean showInstallPrompt(@NonNull Activity activity, int requestCode, @NonNull BranchUniversalObject buo) {
        if (buo == null) {
            return false;
        }
        String installReferrerString = Jsonkey.ReferringLink.getKey() + "=" + buo.getShortUrl(activity, new LinkProperties());
        if (TextUtils.isEmpty(installReferrerString)) {
            return showInstallPrompt(activity, requestCode, "");
        }
        return showInstallPrompt(activity, requestCode, installReferrerString);
    }

    private static boolean doShowInstallPrompt(@NonNull Activity activity, int requestCode, @Nullable String referrer) {
        if (activity == null) {
            Log.e(TAG, "Unable to show install prompt. Activity is null");
            return false;
        } else if (isInstantApp(activity)) {
            Intent intent = new Intent("android.intent.action.VIEW").setPackage("com.android.vending").addCategory("android.intent.category.DEFAULT").putExtra("callerId", activity.getPackageName()).putExtra("overlay", true);
            Builder uriBuilder = new Builder().scheme("market").authority("details").appendQueryParameter("id", activity.getPackageName());
            if (!TextUtils.isEmpty(referrer)) {
                uriBuilder.appendQueryParameter("referrer", referrer);
            }
            intent.setData(uriBuilder.build());
            activity.startActivityForResult(intent, requestCode);
            return true;
        } else {
            Log.e(TAG, "Unable to show install prompt. Application is not an instant app");
            return false;
        }
    }
}

package com.cube.memorygames;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.RewardedVideoCallbacks;
import com.cmcm.adsdk.CMAdError;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;
import com.cube.memorygames.api.local.LocalDataManager;
import com.cube.memorygames.billing.IabHelper;
import com.cube.memorygames.billing.IabHelper.IabAsyncInProgressException;
import com.cube.memorygames.billing.IabHelper.OnConsumeFinishedListener;
import com.cube.memorygames.billing.IabHelper.OnIabPurchaseFinishedListener;
import com.cube.memorygames.billing.IabResult;
import com.cube.memorygames.billing.Purchase;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.internal.AnalyticsEvents;
import com.facebook.share.Sharer.Result;
import com.facebook.share.internal.ShareConstants;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.InterstitialAd;
import com.memory.brain.training.games.R;
import java.text.SimpleDateFormat;
import java.util.Date;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SharingDialog extends Dialog implements FacebookCallback<Result>, OnIabPurchaseFinishedListener, OnConsumeFinishedListener {
    public static final int DOLLAR1_COINS = 700;
    public static final int DOLLAR2_COINS = 1600;
    public static final int DOLLAR3_COINS = 2500;
    private static final String GOOGLE_PLAY_URL = "https://play.google.com/store/apps/details?id=com.memory.brain.training.games";
    private static final String PREF_FACEBOOK_SHARE = "prefFbShare";
    private static final String PREF_VIDEO_DAY = "prefVideoDay";
    private static final String PREF_VIDEO_WATCHED = "prefVideoWatched";
    private static final int SHARE_COINS = 200;
    public static final String SKU1 = "1dollars";
    public static final String SKU2 = "2dollars";
    public static final String SKU3 = "3dollars";
    private static final String TAG_ADS_ADMOB = "AdMob";
    private static final String TAG_ADS_APPODEAL = "Appodeal";
    private static final String TAG_AVAILABLE = "available";
    private static final String TAG_NO_INTERNET = "noInternet";
    private static final String TAG_UNAVAILABLE = "unavailable";
    private static final String TAG_VIEW_LIMIT = "viewLimit";
    private static final int VIDEO_DEFAULT_COINS = 30;
    private static final int VIDEO_LIMIT = 7;
    private static final int VIDEO_MORE_COINS = 150;
    private Activity activity;
    private boolean allowMoreCoins;
    private MemoryApplicationModel application = MemoryApplicationModel.getInstance();
    private CallbackManager callbackManager;
    @Bind({2131624294})
    TextView dollar1Coins;
    @Bind({2131624293})
    TextView dollar1Count;
    @Bind({2131624298})
    TextView dollar2Coins;
    @Bind({2131624297})
    TextView dollar2Count;
    @Bind({2131624302})
    TextView dollar3Coins;
    @Bind({2131624301})
    TextView dollar3Count;
    @Bind({2131624286})
    TextView fbCoins;
    @Bind({2131624283})
    View fbContainer;
    @Bind({2131624282})
    TextView hint;
    private IabHelper iabHelper;
    private IabStatus iabStatus;
    private StatisticListener listener;
    private LocalDataManager localDataManager;
    private InterstitialAd mInterstitialAd;
    private SharedPreferences preferences;
    private ProgressDialog progressDialog;
    @Bind({2131624074})
    TextView ratingView;
    @Bind({2131624285})
    TextView shareText;
    @Bind({2131624072})
    TextView starsView;
    @Bind({2131624290})
    TextView videoCoins;
    @Bind({2131624287})
    View videoContainer;
    @Bind({2131624289})
    TextView videoText;

    public interface StatisticListener {
        void onStatisticUpdated();
    }

    public interface IabStatus {
        boolean isIabSetupFinished();
    }

    public SharingDialog(CallbackManager callbackManager, Activity activity, StatisticListener listener, IabHelper iabHelper, IabStatus iabStatus, Integer message, boolean allowMoreCoins) {
        super(CalligraphyContextWrapper.wrap(activity), R.style.GdxTheme);
        setContentView(R.layout.dialog_sharing);
        ButterKnife.bind((Object) this, (Dialog) this);
        this.activity = activity;
        this.listener = listener;
        this.iabHelper = iabHelper;
        this.iabStatus = iabStatus;
        this.callbackManager = callbackManager;
        this.allowMoreCoins = allowMoreCoins;
        this.preferences = PreferenceManager.getDefaultSharedPreferences(activity);
        this.localDataManager = MemoryApplicationModel.getInstance().getLocalDataManager();
        setCancelable(true);
        this.starsView.setText(String.valueOf(this.localDataManager.getLocalUser().money));
        this.ratingView.setText(String.valueOf(MainMenuActivity.round((double) this.localDataManager.getLocalUser().rating, 1)));
        this.fbCoins.setText(String.valueOf(200));
        this.videoCoins.setText(String.valueOf(allowMoreCoins ? 150 : 30));
        this.dollar1Coins.setText(String.valueOf(DOLLAR1_COINS));
        this.dollar2Coins.setText(String.valueOf(DOLLAR2_COINS));
        this.dollar3Coins.setText(String.valueOf(2500));
        this.dollar1Count.setText("1$");
        this.dollar2Count.setText("2$");
        this.dollar3Count.setText("3$");
        if (message == null) {
            this.hint.setVisibility(8);
        } else {
            this.hint.setVisibility(0);
            this.hint.setText(message.intValue());
        }
        Appodeal.setRewardedVideoCallbacks(new RewardedVideoCallbacks() {
            public void onRewardedVideoLoaded() {
                SharingDialog.this.videoText.setTextColor(-16777216);
                SharingDialog.this.videoCoins.setTextColor(-1);
                SharingDialog.this.videoContainer.setTag(SharingDialog.TAG_ADS_APPODEAL);
            }

            public void onRewardedVideoFailedToLoad() {
            }

            public void onRewardedVideoShown() {
            }

            public void onRewardedVideoFinished(int i, String s) {
            }

            public void onRewardedVideoClosed(boolean finished) {
                SharingDialog.this.videoWatchedSuccess(SharingDialog.TAG_ADS_APPODEAL);
            }
        });
        this.mInterstitialAd = new InterstitialAd(getContext());
        this.mInterstitialAd.setAdUnitId(MemoryApplicationModel.APP_UNIT_ID);
        this.mInterstitialAd.setAdListener(new AdListener() {
            public void onAdClosed() {
                SharingDialog.this.requestNewInterstitial();
                SharingDialog.this.videoWatchedSuccess(SharingDialog.TAG_ADS_ADMOB);
            }

            public void onAdLoaded() {
                SharingDialog.this.videoText.setTextColor(-16777216);
                SharingDialog.this.videoCoins.setTextColor(-1);
                SharingDialog.this.videoContainer.setTag(SharingDialog.TAG_ADS_ADMOB);
            }
        });
        requestNewInterstitial();
        updateButtons(true, message == null ? "topMenu" : "lockedGame");
    }

    private void requestNewInterstitial() {
        this.mInterstitialAd.loadAd(new Builder().build());
    }

    private void updateButtons(boolean log, String from) {
        String videoParam;
        String fbParam;
        int disabledColor = ContextCompat.getColor(this.activity, R.color.disabled_text);
        boolean isConnected = this.application.isNetworkAvailable();
        this.starsView.setText(String.valueOf(this.localDataManager.getLocalUser().money));
        if (!isConnected) {
            this.videoText.setTextColor(disabledColor);
            this.videoCoins.setTextColor(disabledColor);
            this.videoContainer.setTag(TAG_NO_INTERNET);
            videoParam = "No internet";
        } else if (isVideoLimitReached()) {
            this.videoText.setTextColor(disabledColor);
            this.videoCoins.setTextColor(disabledColor);
            this.videoContainer.setTag(TAG_VIEW_LIMIT);
            videoParam = "LimitReached";
        } else if (Appodeal.isLoaded(128)) {
            this.videoText.setTextColor(-16777216);
            this.videoCoins.setTextColor(-1);
            this.videoContainer.setTag(TAG_ADS_APPODEAL);
            videoParam = TAG_ADS_APPODEAL;
        } else if (this.mInterstitialAd.isLoaded()) {
            this.videoText.setTextColor(-16777216);
            this.videoCoins.setTextColor(-1);
            this.videoContainer.setTag(TAG_ADS_ADMOB);
            videoParam = TAG_ADS_ADMOB;
        } else {
            this.videoText.setTextColor(disabledColor);
            this.videoCoins.setTextColor(disabledColor);
            this.videoContainer.setTag(TAG_UNAVAILABLE);
            videoParam = "Unavailable";
        }
        long fbShare = this.preferences.getLong(PREF_FACEBOOK_SHARE, 0);
        long current = new Date().getTime();
        if (!isConnected) {
            this.shareText.setTextColor(disabledColor);
            this.fbCoins.setTextColor(disabledColor);
            this.fbContainer.setTag(TAG_NO_INTERNET);
            fbParam = "No internet";
        } else if (current - fbShare > 1209600000) {
            fbParam = TAG_AVAILABLE;
            this.shareText.setTextColor(-16777216);
            this.fbCoins.setTextColor(-1);
            this.fbContainer.setTag(TAG_AVAILABLE);
        } else {
            fbParam = TAG_UNAVAILABLE;
            this.shareText.setTextColor(disabledColor);
            this.fbCoins.setTextColor(disabledColor);
            this.fbContainer.setTag(TAG_UNAVAILABLE);
        }
        if (log) {
            this.application.logEvent(this.activity, "Ads", MemoryApplicationModel.ANALYTICS_EVENT_ADS_GET_COINS_DIALOG);
            Answers.getInstance().logCustom((CustomEvent) ((CustomEvent) ((CustomEvent) new CustomEvent(MemoryApplicationModel.ANALYTICS_EVENT_ADS_GET_COINS_DIALOG).putCustomAttribute(AnalyticsEvents.PARAMETER_SHARE_DIALOG_CONTENT_VIDEO, videoParam)).putCustomAttribute("facebook", fbParam)).putCustomAttribute(ShareConstants.FEED_SOURCE_PARAM, from));
        }
    }

    private void videoWatchedSuccess(String ads) {
        MemoryApplicationModel.getInstance().getLocalDataManager().addCoinsTransaction(LocalDataManager.TYPE_SHARE_DIALOG_VIDEO, this.allowMoreCoins ? 150 : 30);
        this.videoCoins.setText("+30");
        this.starsView.setText(String.valueOf(this.localDataManager.getLocalUser().money));
        if (this.listener != null) {
            this.listener.onStatisticUpdated();
        }
        this.preferences.edit().putInt(PREF_VIDEO_WATCHED, this.preferences.getInt(PREF_VIDEO_WATCHED, 0) + 1).apply();
        this.preferences.edit().putLong(PREF_VIDEO_DAY, new Date().getTime()).apply();
        this.application.logEvent(this.activity, "Ads", MemoryApplicationModel.ANALYTICS_EVENT_ADS_GET_COINS_DIALOG_ADS_WATCHED);
        Answers.getInstance().logCustom((CustomEvent) new CustomEvent(MemoryApplicationModel.ANALYTICS_EVENT_ADS_GET_COINS_DIALOG_ADS_WATCHED).putCustomAttribute("ads", ads));
        updateButtons(false, null);
        if (this.allowMoreCoins) {
            dismiss();
        }
    }

    @OnClick({2131624115})
    void backClick() {
        dismiss();
    }

    @OnClick({2131624075, 2131624074})
    public void topClick() {
        if (TextUtils.isEmpty(this.localDataManager.getLocalUser().displayName)) {
            showLogin(false);
        } else {
            showTop();
        }
    }

    private void showTop() {
        this.application.logEvent(this.activity, MemoryApplicationModel.ANALYTICS_CATEGORY_GENERAL, MemoryApplicationModel.ANALYTICS_EVENT_TOP_CLICKED);
        this.activity.startActivity(TopRanksActivity.newIntent(this.activity, 1));
    }

    private void showLogin(boolean type) {
        this.activity.startActivityForResult(LoginActivity.newIntent(getContext(), type), 1001);
    }

    @OnClick({2131624291})
    void dollar1ContainerClick() {
        buyCoins(SKU1);
    }

    @OnClick({2131624295})
    void dollar2ContainerClick() {
        buyCoins(SKU2);
    }

    @OnClick({2131624299})
    void dollar3ContainerClick() {
        buyCoins(SKU3);
    }

    private void buyCoins(String sku) {
        if (this.iabStatus.isIabSetupFinished()) {
            try {
                this.iabHelper.launchPurchaseFlow(this.activity, sku, CMAdError.EXTERNAL_CONFIG_ERROR, this, this.application.getLocalDataManager().getLocalUser().objectId);
                this.progressDialog = ProgressDialog.show(getContext(), null, "Loading...", false, false);
            } catch (IabAsyncInProgressException e) {
                if (this.progressDialog != null) {
                    this.progressDialog.dismiss();
                }
            }
        }
    }

    public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
        if (!result.isFailure()) {
            try {
                this.iabHelper.consumeAsync(purchase, (OnConsumeFinishedListener) this);
            } catch (IabAsyncInProgressException e) {
                if (this.progressDialog != null) {
                    this.progressDialog.dismiss();
                }
            }
        } else if (this.progressDialog != null) {
            this.progressDialog.dismiss();
        }
    }

    public void onConsumeFinished(Purchase purchase, IabResult result) {
        if (this.progressDialog != null) {
            this.progressDialog.dismiss();
        }
        if (result.isSuccess()) {
            int money = 0;
            String sku = purchase.getSku();
            boolean z = true;
            switch (sku.hashCode()) {
                case -1216851259:
                    if (sku.equals(SKU2)) {
                        z = true;
                        break;
                    }
                    break;
                case 525959076:
                    if (sku.equals(SKU3)) {
                        z = true;
                        break;
                    }
                    break;
                case 1335305702:
                    if (sku.equals(SKU1)) {
                        z = false;
                        break;
                    }
                    break;
            }
            switch (z) {
                case false:
                    money = DOLLAR1_COINS;
                    break;
                case true:
                    money = DOLLAR2_COINS;
                    break;
                case true:
                    money = 2500;
                    break;
            }
            this.application.logEvent(this.activity, "Ads", MemoryApplicationModel.ANALYTICS_EVENT_ADS_GET_COINS_DIALOG_COINS);
            Answers.getInstance().logCustom((CustomEvent) new CustomEvent(MemoryApplicationModel.ANALYTICS_EVENT_ADS_GET_COINS_DIALOG_COINS).putCustomAttribute("coins", "" + money));
            MemoryApplicationModel.getInstance().getLocalDataManager().addCoinsTransaction(LocalDataManager.TYPE_BUY, money);
            if (this.listener != null) {
                this.listener.onStatisticUpdated();
            }
            updateButtons(false, null);
        }
    }

    @OnClick({2131624283})
    void fbShareClick() {
        Object tag = this.fbContainer.getTag();
        if (tag != null) {
            String action = tag.toString();
            if (action.equals(TAG_AVAILABLE)) {
                ShareLinkContent content = ((ShareLinkContent.Builder) new ShareLinkContent.Builder().setQuote(this.activity.getResources().getString(R.string.facebook_share_messages)).setContentUrl(Uri.parse(GOOGLE_PLAY_URL))).build();
                ShareDialog shareDialog = new ShareDialog(this.activity);
                shareDialog.registerCallback(this.callbackManager, this);
                shareDialog.show(content);
                Answers.getInstance().logCustom(new CustomEvent(MemoryApplicationModel.ANALYTICS_EVENT_ADS_GET_COINS_DIALOG_FB));
                return;
            }
            String message = null;
            int i = -1;
            switch (action.hashCode()) {
                case -665462704:
                    if (action.equals(TAG_UNAVAILABLE)) {
                        i = 1;
                        break;
                    }
                    break;
                case 1729423394:
                    if (action.equals(TAG_NO_INTERNET)) {
                        i = 0;
                        break;
                    }
                    break;
            }
            switch (i) {
                case 0:
                    message = this.activity.getResources().getString(R.string.no_internet_message);
                    break;
                case 1:
                    message = this.activity.getResources().getString(R.string.already_shared);
                    break;
            }
            if (message != null) {
                Toast.makeText(this.activity, message, 1).show();
            }
        }
    }

    @OnClick({2131624287})
    void videoClick() {
        Object tag = this.videoContainer.getTag();
        if (tag != null) {
            String action = tag.toString();
            if (TAG_ADS_APPODEAL.equals(action)) {
                Appodeal.show(this.activity, 128);
                this.application.logEvent(this.activity, "Ads", MemoryApplicationModel.ANALYTICS_EVENT_ADS_GET_COINS_DIALOG_ADS);
                Answers.getInstance().logCustom((CustomEvent) new CustomEvent(MemoryApplicationModel.ANALYTICS_EVENT_ADS_GET_COINS_DIALOG_ADS).putCustomAttribute("type", TAG_ADS_APPODEAL));
            } else if (TAG_ADS_ADMOB.equals(action)) {
                this.mInterstitialAd.show();
                this.application.logEvent(this.activity, "Ads", MemoryApplicationModel.ANALYTICS_EVENT_ADS_GET_COINS_DIALOG_ADS);
                Answers.getInstance().logCustom((CustomEvent) new CustomEvent(MemoryApplicationModel.ANALYTICS_EVENT_ADS_GET_COINS_DIALOG_ADS).putCustomAttribute("type", TAG_ADS_ADMOB));
            } else {
                String message = null;
                int i = -1;
                switch (action.hashCode()) {
                    case -1590886378:
                        if (action.equals(TAG_VIEW_LIMIT)) {
                            i = 1;
                            break;
                        }
                        break;
                    case -665462704:
                        if (action.equals(TAG_UNAVAILABLE)) {
                            i = 2;
                            break;
                        }
                        break;
                    case 1729423394:
                        if (action.equals(TAG_NO_INTERNET)) {
                            i = 0;
                            break;
                        }
                        break;
                }
                switch (i) {
                    case 0:
                        message = this.activity.getResources().getString(R.string.no_internet_message);
                        break;
                    case 1:
                        message = this.activity.getResources().getString(R.string.video_limit_message);
                        break;
                    case 2:
                        message = this.activity.getResources().getString(R.string.no_ads_message);
                        break;
                }
                if (message != null) {
                    Toast.makeText(this.activity, message, 1).show();
                }
            }
        }
    }

    private boolean isVideoLimitReached() {
        int viewedVideo = this.preferences.getInt(PREF_VIDEO_WATCHED, 0);
        long time = this.preferences.getLong(PREF_VIDEO_DAY, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        if (!sdf.format(new Date(time)).equals(sdf.format(new Date()))) {
            this.preferences.edit().putLong(PREF_VIDEO_DAY, new Date().getTime()).apply();
            this.preferences.edit().putInt(PREF_VIDEO_WATCHED, 0).apply();
            return false;
        } else if (viewedVideo >= 7) {
            return true;
        } else {
            return false;
        }
    }

    public void onSuccess(Result result) {
        this.preferences.edit().putLong(PREF_FACEBOOK_SHARE, new Date().getTime()).apply();
        MemoryApplicationModel.getInstance().getLocalDataManager().addCoinsTransaction(LocalDataManager.TYPE_SHARE_DIALOG_FB, 200);
        if (this.listener != null) {
            this.listener.onStatisticUpdated();
        }
        updateButtons(false, null);
        Answers.getInstance().logCustom(new CustomEvent(MemoryApplicationModel.ANALYTICS_EVENT_ADS_GET_COINS_DIALOG_FB_SUCCESS));
    }

    public void onCancel() {
    }

    public void onError(FacebookException error) {
    }
}

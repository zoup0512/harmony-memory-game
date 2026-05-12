package com.cube.memorygames;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;
import com.activeandroid.ActiveAndroid;
import com.activeandroid.Configuration;
import com.crashlytics.android.Crashlytics.Builder;
import com.crashlytics.android.core.CrashlyticsCore;
import com.cube.memorygames.R.drawable;
import com.cube.memorygames.activity.OnShowMoneyDialogListener;
import com.cube.memorygames.api.local.LocalDataManager;
import com.cube.memorygames.api.local.challenge.ChallengeDbGame;
import com.cube.memorygames.api.local.model.LocalCoinsTransaction;
import com.cube.memorygames.api.local.model.LocalGameSession;
import com.cube.memorygames.api.local.model.LocalGameStats;
import com.cube.memorygames.api.local.model.LocalUser;
import com.cube.memorygames.api.local.model.LocalWorkoutGame;
import com.cube.memorygames.api.network.APIService;
import com.cube.memorygames.api.network.ServiceGenerator;
import com.cube.memorygames.pushes.event.ErrorEvent;
import com.cube.memorygames.pushes.event.GameEndedEvent;
import com.cube.memorygames.pushes.event.IntermediateResultsEvent;
import com.cube.memorygames.pushes.event.MatchFoundEvent;
import com.cube.memorygames.pushes.event.OnlineEvent;
import com.cube.memorygames.pushes.event.OnlineEventListener;
import com.cube.memorygames.pushes.event.StartGameEvent;
import com.cube.memorygames.pushes.event.UserConfirmedGameEvent;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders.EventBuilder;
import com.google.android.gms.analytics.Tracker;
import com.google.gson.Gson;
import com.memory.brain.training.games.R;
import io.branch.referral.Branch;
import io.fabric.sdk.android.Fabric;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class MemoryApplicationModel extends MultiDexApplication implements OnlineEventListener {
    public static final String ANALYTICS_CATEGORY_ADS = "Ads";
    public static final String ANALYTICS_CATEGORY_BONUSES = "Bonuses";
    public static final String ANALYTICS_CATEGORY_CHALLENGE = "Challenge";
    public static final String ANALYTICS_CATEGORY_GAMEPLAY_PREFIX = "Gameplay. Game ";
    public static final String ANALYTICS_CATEGORY_GENERAL = "General";
    public static final String ANALYTICS_CATEGORY_OFFERS = "Offers";
    public static final String ANALYTICS_CATEGORY_ONLINE = "Online Games";
    public static final String ANALYTICS_CATEGORY_PROMO = "Promo";
    public static final String ANALYTICS_CATEGORY_RATING = "Rating";
    public static final String ANALYTICS_CATEGORY_REMINDERS = "Reminders";
    public static final String ANALYTICS_CATEGORY_SUGGESTION = "Game Suggestions";
    public static final String ANALYTICS_CATEGORY_UNLOCK = "Unlock";
    public static final String ANALYTICS_CATEGORY_WORKOUT = "Workout";
    public static final String ANALYTICS_EVENT_ADS_ADMOB_WATCHED = "Ads watched AdMob";
    public static final String ANALYTICS_EVENT_ADS_APPODEAL_WATCHED = "Ads watched Appodeal";
    public static final String ANALYTICS_EVENT_ADS_AVAILABLE_ADMOB = "Video available AdMob";
    public static final String ANALYTICS_EVENT_ADS_AVAILABLE_APPODEAL = "Video available Appodeal";
    public static final String ANALYTICS_EVENT_ADS_CHALLENGE = "Challenge Ad clicked";
    public static final String ANALYTICS_EVENT_ADS_CHALLENGE_FAIL_SHOWED = "Challenge fail dialog. Internet ";
    public static final String ANALYTICS_EVENT_ADS_FAIL_SHOWED = "Fail dialog. Internet ";
    public static final String ANALYTICS_EVENT_ADS_GAME_FINISHED = "Game Finished";
    public static final String ANALYTICS_EVENT_ADS_GET_COINS_DIALOG = "Get coins dialog";
    public static final String ANALYTICS_EVENT_ADS_GET_COINS_DIALOG_ADS = "Get coins dialog. ads clicked";
    public static final String ANALYTICS_EVENT_ADS_GET_COINS_DIALOG_ADS_WATCHED = "Get coins dialog. ads watched";
    public static final String ANALYTICS_EVENT_ADS_GET_COINS_DIALOG_COINS = "Get coins dialog. coins added";
    public static final String ANALYTICS_EVENT_ADS_GET_COINS_DIALOG_FB = "Get coins dialog. fb clicked";
    public static final String ANALYTICS_EVENT_ADS_GET_COINS_DIALOG_FB_SUCCESS = "Get coins dialog. fb success";
    public static final String ANALYTICS_EVENT_ADS_MAX_VIDEO_WATCHED = "Video limit reached";
    public static final String ANALYTICS_EVENT_ADS_REPLAY = "Replay for Ad clicked";
    public static final String ANALYTICS_EVENT_ADS_REPLAY_NEW = "Replay for Ad New";
    public static final String ANALYTICS_EVENT_ADS_UNAVAILABLE = "Video unavailable";
    public static final String ANALYTICS_EVENT_CHALLENGE_GAME_STARTED = "Game Started ";
    public static final String ANALYTICS_EVENT_CHALLENGE_NEW_LEVEL = "New Level ";
    public static final String ANALYTICS_EVENT_CHALLENGE_PREV_LEVEL = "PREV Level ";
    public static final String ANALYTICS_EVENT_CHALLENGE_TAB_CLICKED = "Challenge Tab Clicked";
    public static final String ANALYTICS_EVENT_FACEBOOK_LOGIN = "Facebook login";
    public static final String ANALYTICS_EVENT_FACEBOOK_REGISTER = "Facebook register";
    public static final String ANALYTICS_EVENT_GAME_NEW_TROPHY = "New Trophy";
    public static final String ANALYTICS_EVENT_GAME_OPEN_TROPHIES = "Open Trophies";
    public static final String ANALYTICS_EVENT_GAME_STARTED_PREFIX = "Started Game ";
    public static final String ANALYTICS_EVENT_LEVEL_REACHED_PREFIX = "User reached level ";
    public static final String ANALYTICS_EVENT_NICK_ENTERED = "Nick entered";
    public static final String ANALYTICS_EVENT_NICK_SKIPPED = "Nick skipped";
    public static final String ANALYTICS_EVENT_OFFER_NEW_PRO = "Offer Dialog. New. Pro. Showed";
    public static final String ANALYTICS_EVENT_OFFER_NEW_PRO_CLICKED = "Offer Dialog. New. Pro. Clicked";
    public static final String ANALYTICS_EVENT_OFFER_NEW_PRO_DISCOUNT = "Offer Dialog. New. Pro Discount. Showed";
    public static final String ANALYTICS_EVENT_OFFER_NEW_PRO_DISCOUNT_CLICKED = "Offer Dialog. New. Pro Discount. Clicked";
    public static final String ANALYTICS_EVENT_OFFER_NEW_PRO_DISCOUNT_SUCCESS = "Offer Dialog. New. Pro Discount. Success";
    public static final String ANALYTICS_EVENT_OFFER_NEW_PRO_SUCCESS = "Offer Dialog. New. Pro. Success";
    public static final String ANALYTICS_EVENT_OFFER_NEW_REMOVE_ADS = "Offer Dialog. New. Remove Ads. Showed";
    public static final String ANALYTICS_EVENT_OFFER_NEW_REMOVE_ADS_CLICKED = "Offer Dialog. New. Remove Ads. Clicked";
    public static final String ANALYTICS_EVENT_OFFER_NEW_REMOVE_ADS_SUCCESS = "Offer Dialog. New. Remove Ads. Success";
    public static final String ANALYTICS_EVENT_OFFER_NEW_SECRET_GAME = "Offer Dialog. New. Secret Game. Showed";
    public static final String ANALYTICS_EVENT_OFFER_NEW_SECRET_GAME_CLICKED = "Offer Dialog. New. Secret Game. Clicked";
    public static final String ANALYTICS_EVENT_OFFER_NEW_SECRET_GAME_SUCCESS = "Offer Dialog. New. Secret Game. Success";
    public static final String ANALYTICS_EVENT_OFFER_NEW_UNLIMITED_ONLINE = "Offer Dialog. New. Unlimited Online. Showed";
    public static final String ANALYTICS_EVENT_OFFER_NEW_UNLIMITED_ONLINE_CLICKED = "Offer Dialog. New. Unlimited Online. Clicked";
    public static final String ANALYTICS_EVENT_OFFER_NEW_UNLIMITED_ONLINE_SUCCESS = "Offer Dialog. New. Unlimited Online. Success";
    public static final String ANALYTICS_EVENT_OFFER_OLD_PRO = "Offer Dialog. Old. Pro. Showed";
    public static final String ANALYTICS_EVENT_OFFER_OLD_PRO_DISCOUNT = "Offer Dialog. Old. Pro Discount. Showed";
    public static final String ANALYTICS_EVENT_OFFER_OLD_REMOVE_ADS = "Offer Dialog. Old. Remove Ads. Showed";
    public static final String ANALYTICS_EVENT_OFFER_OLD_SECRET_GAME = "Offer Dialog. Old. Secret Game. Showed";
    public static final String ANALYTICS_EVENT_OFFER_OLD_UNLIMITED_ONLINE = "Offer Dialog. Old. Unlimited Online. Showed";
    public static final String ANALYTICS_EVENT_ONLINE_ADS_OPENED = "Online Ads Opened";
    public static final String ANALYTICS_EVENT_ONLINE_BUY_CLICKED = "Online Buy Clicked";
    public static final String ANALYTICS_EVENT_ONLINE_CANCEL_AFTER_MATH_CLICKED = "Online Cancel Game After Match";
    public static final String ANALYTICS_EVENT_ONLINE_CANCEL_BEFORE_MATH_CLICKED = "Online Cancel Game Before Match";
    public static final String ANALYTICS_EVENT_ONLINE_GAME_STARTED = "Online Game Started";
    public static final String ANALYTICS_EVENT_ONLINE_HISTORY_CLICKED = "Online History Clicked";
    public static final String ANALYTICS_EVENT_ONLINE_NICK_CHANGED = "Online Nick Changed";
    public static final String ANALYTICS_EVENT_ONLINE_PHOTO_CHANGED = "Online Photo Changed";
    public static final String ANALYTICS_EVENT_ONLINE_PLAY_CLICKED = "Online Play Clicked";
    public static final String ANALYTICS_EVENT_ONLINE_PROFILE_OPENED = "Online Profile Opened";
    public static final String ANALYTICS_EVENT_ONLINE_RANKS_CLICKED = "Online Ranks Clicked";
    public static final String ANALYTICS_EVENT_ONLINE_REPLAY_CLICKED = "Online Replay Clicked";
    public static final String ANALYTICS_EVENT_ONLINE_TAB_CLICKED = "Online Tab Clicked";
    public static final String ANALYTICS_EVENT_REMINDER_SHOWED = "Reminder Showed ";
    public static final String ANALYTICS_EVENT_REMINDER_WORKED = "Reminder Worked ";
    public static final String ANALYTICS_EVENT_REPLAY_USED_PREFIX = "Replay activated. Game ";
    public static final String ANALYTICS_EVENT_SOUND_OFF = "Sound OFF";
    public static final String ANALYTICS_EVENT_SOUND_ON = "Sound ON";
    public static final String ANALYTICS_EVENT_SUGGESTION_CLICKED_PREFIX = "Suggestion Clicked. ";
    public static final String ANALYTICS_EVENT_TOP_CLICKED = "Top clicked";
    public static final String ANALYTICS_EVENT_TRY_UNLOCK_GAME_PREFIX = "Unlock Clicked. ";
    public static final String ANALYTICS_EVENT_UNLOCKED_GAME_PREFIX = "Unlocked. game ";
    public static final String ANALYTICS_EVENT_WORKOUT_DAY_GENERATED = "New Day Generated";
    public static final String ANALYTICS_EVENT_WORKOUT_DIALOG_CLICKED = "Dialog Clicked";
    public static final String ANALYTICS_EVENT_WORKOUT_DIALOG_SHOWED = "Dialog Showed";
    public static final String ANALYTICS_EVENT_WORKOUT_FINISHED = "Workout Finished";
    public static final String ANALYTICS_EVENT_WORKOUT_GAME_STARTED = "Game Started ";
    public static final String ANALYTICS_EVENT_WORKOUT_HELP_CLICKED = "Help Clicked";
    public static final String ANALYTICS_EVENT_WORKOUT_NEW_LEVEL = "New Level ";
    public static final String ANALYTICS_EVENT_WORKOUT_TAB_CLICKED = "Workout Tab Clicked";
    public static final String APP_UNIT_ID = "ca-app-pub-4691137536275604/6533978409";
    public static final String BASE_64_ENCODED_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAmCjV13UZxmCRVFFsK/4/qCNbKlGV5mXLDx7emPqcyVtJwZYRIgCZEUwKzBeyQ3qwv4w7UP+TgvyEDf9eXFSNQaotD5zs8DwufOo6BeFDZ+LL3e5SO5mX2HV45E+4bbOa9BpeOscxLik5kvuYksANl7myOygDdvLyXFK8vZiR12TC5LKxLK7UmRHXwHZcsTsD94YpXQAdv87iaG0Aduhg+w0EqoF7Lphl1kXiQKhJRmxdPiuDsDXDNk0xOneqtz3xFbfj4kbXbyz1Pkf3TxyypTWBr1bhjMluNBT7JYESWNe25xKng2DkEWgrfAelluzQP1xgxYNFGlOHdm5Y3RKvjQIDAQAB";
    private static final String PREFERENCE_LAST_GAME_PLAYED = "lastPlayedGameId";
    private static final String PREFERENCE_ONLINE_GAME_COUNT = "preferenceOnlineGameCount";
    private static final String PREF_AUTHENTICATION = "authentication";
    private static final String PREF_TOKEN = "token";
    private static MemoryApplicationModel instance;
    private ArrayList<Integer> allCelldrawableIds;
    private String authentication;
    private IntermediateResultsEvent currentOnlineIntermediateResults;
    private Editor editor;
    private Gson gsonParser;
    private boolean isNicknameSkipped = false;
    private String lastPlayedGameId;
    private LocalDataManager localDataManager;
    private Tracker mTracker;
    private OnShowMoneyDialogListener onShowMoneyDialogListener;
    private Set<OnlineEventListener> onlineEventListeners = new LinkedHashSet();
    private OnlineModeStatus onlineModeStatus = OnlineModeStatus.NONE;
    private SharedPreferences preferences;
    private APIService service;
    private String token;
    private Handler uiHandler;

    public enum OnlineModeStatus {
        NONE,
        SEARCHING,
        MATCH_FOUND,
        ACCEPTED,
        STARTED,
        FINISHED
    }

    public void onCreate() {
        super.onCreate();
        instance = this;
        Fabric.with(this, new Builder().core(new CrashlyticsCore.Builder().disabled(false).build()).build());
        FacebookSdk.sdkInitialize(getApplicationContext());
        Branch.getAutoInstance(this);
        initializeDB();
        createService();
        SoundUtils.initSounds(getApplicationContext());
        this.localDataManager = new LocalDataManager();
        initWithPreferences();
        this.mTracker = GoogleAnalytics.getInstance(this).newTracker((int) R.xml.global_tracker);
        this.mTracker.enableAdvertisingIdCollection(true);
        this.gsonParser = new Gson();
        this.uiHandler = new Handler();
        addOnlineEventListener(this);
    }

    protected void initializeDB() {
        Configuration.Builder configurationBuilder = new Configuration.Builder(this);
        configurationBuilder.addModelClass(LocalUser.class);
        configurationBuilder.addModelClass(LocalGameSession.class);
        configurationBuilder.addModelClass(LocalCoinsTransaction.class);
        configurationBuilder.addModelClass(LocalGameStats.class);
        configurationBuilder.addModelClass(LocalWorkoutGame.class);
        configurationBuilder.addModelClass(ChallengeDbGame.class);
        ActiveAndroid.initialize(configurationBuilder.create());
    }

    public void onTerminate() {
        super.onTerminate();
        ActiveAndroid.dispose();
    }

    public void logEvent(String screenName, String category, String action) {
        this.mTracker.setScreenName(screenName);
        this.mTracker.send(new EventBuilder().setCategory(category).setAction(action).build());
    }

    public void logEvent(Activity activity, String category, String action) {
        logEvent(activity.getClass().getSimpleName(), category, action);
    }

    public static MemoryApplicationModel getInstance() {
        return instance;
    }

    public List<Integer> getAllCellDrawableIds() {
        if (this.allCelldrawableIds == null) {
            this.allCelldrawableIds = new ArrayList();
            for (Field f : drawable.class.getFields()) {
                try {
                    if (f.getName().contains("icg_")) {
                        this.allCelldrawableIds.add(Integer.valueOf(f.getInt(null)));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return this.allCelldrawableIds;
    }

    public LocalDataManager getLocalDataManager() {
        return this.localDataManager;
    }

    private void initWithPreferences() {
        this.preferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.editor = this.preferences.edit();
        this.lastPlayedGameId = this.preferences.getString(PREFERENCE_LAST_GAME_PLAYED, AppEventsConstants.EVENT_PARAM_VALUE_NO);
    }

    public String getLastPlayedGameId() {
        return this.lastPlayedGameId;
    }

    public void setLastGamePlayed(String lastPlayedGameId) {
        this.lastPlayedGameId = lastPlayedGameId;
        this.editor.putString(PREFERENCE_LAST_GAME_PLAYED, lastPlayedGameId).commit();
    }

    public boolean isNicknameSkipped() {
        return this.isNicknameSkipped;
    }

    public void setNicknameSkipped(boolean nicknameSkipped) {
        this.isNicknameSkipped = nicknameSkipped;
    }

    public void createService() {
        if (this.token == null) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            this.token = preferences.getString(PREF_TOKEN, null);
            this.authentication = preferences.getString(PREF_AUTHENTICATION, null);
        }
        this.service = (APIService) ServiceGenerator.createService(APIService.class, this.token);
    }

    public APIService getService() {
        return this.service;
    }

    public void saveToken(String token) {
        this.token = token;
        PreferenceManager.getDefaultSharedPreferences(this).edit().putString(PREF_TOKEN, token).apply();
        createService();
    }

    public boolean isTokenAvailable() {
        if (this.token == null) {
            this.token = PreferenceManager.getDefaultSharedPreferences(this).getString(PREF_TOKEN, null);
        }
        return this.token != null;
    }

    public void saveAuthentication(String authentication) {
        this.authentication = authentication;
        PreferenceManager.getDefaultSharedPreferences(this).edit().putString(PREF_AUTHENTICATION, authentication).apply();
    }

    public String getAuthentication() {
        if (this.authentication == null) {
            this.authentication = PreferenceManager.getDefaultSharedPreferences(this).getString(PREF_AUTHENTICATION, null);
        }
        return this.authentication;
    }

    public boolean isAuthenticationAvailable() {
        return getAuthentication() != null;
    }

    public void onlineEventReceived(final OnlineEvent event) {
        this.uiHandler.post(new Runnable() {
            public void run() {
                for (OnlineEventListener listener : MemoryApplicationModel.this.onlineEventListeners) {
                    listener.onOnlineEventReceived(event);
                }
            }
        });
    }

    public void onOnlineEventReceived(OnlineEvent event) {
        if (!(event instanceof MatchFoundEvent) && !(event instanceof UserConfirmedGameEvent) && !(event instanceof StartGameEvent)) {
            if (event instanceof GameEndedEvent) {
                incrementOnlineGameCount();
                setGameResults((GameEndedEvent) event);
            } else if (!(event instanceof ErrorEvent) && (event instanceof IntermediateResultsEvent)) {
                this.currentOnlineIntermediateResults = (IntermediateResultsEvent) event;
            }
        }
    }

    private void setGameResults(GameEndedEvent gameEndedEvent) {
        List<Integer> player1Results = parseGameResult(gameEndedEvent.getResultsUser1());
        List<Integer> player2Results = parseGameResult(gameEndedEvent.getResultsUser2());
        int sum1 = getSum(player1Results);
        int sum2 = getSum(player2Results);
        player1Results.add(Integer.valueOf(sum1));
        player2Results.add(Integer.valueOf(sum2));
        if (sum1 > sum2) {
            if (getLocalDataManager().getLocalUser().unlimitedOnline) {
                getLocalDataManager().addCoinsTransaction(LocalDataManager.TYPE_WIN_ONLINE, 25);
            } else {
                getLocalDataManager().addCoinsTransaction(LocalDataManager.TYPE_WIN_ONLINE, 55);
            }
        } else if (sum1 == sum2 && !getLocalDataManager().getLocalUser().unlimitedOnline) {
            getLocalDataManager().addCoinsTransaction(LocalDataManager.TYPE_DRAW_LEVEL, 30);
        }
        LocalUser localUser = getLocalDataManager().getLocalUser();
        localUser.onlineRank = gameEndedEvent.getNewRank();
        localUser.save();
    }

    private List<Integer> parseGameResult(String result) {
        String[] data = result.split(",");
        List<Integer> gameResult = new ArrayList();
        for (String str : data) {
            try {
                if (!TextUtils.isEmpty(str)) {
                    gameResult.add(Integer.valueOf(Integer.parseInt(str)));
                }
            } catch (NumberFormatException e) {
            }
        }
        return gameResult;
    }

    private int getSum(List<Integer> list) {
        int sum = 0;
        for (Integer integer : list) {
            sum += integer.intValue();
        }
        return sum;
    }

    public void addOnlineEventListener(OnlineEventListener onlineEventListener) {
        this.onlineEventListeners.add(onlineEventListener);
    }

    public void removeOnlineEventListener(OnlineEventListener onlineEventListener) {
        this.onlineEventListeners.remove(onlineEventListener);
    }

    public Gson getGsonParser() {
        return this.gsonParser;
    }

    public OnShowMoneyDialogListener getOnShowMoneyDialogListener() {
        return this.onShowMoneyDialogListener;
    }

    public void setOnShowMoneyDialogListener(OnShowMoneyDialogListener onShowMoneyDialogListener) {
        this.onShowMoneyDialogListener = onShowMoneyDialogListener;
    }

    public int getOnlineGameCount() {
        return PreferenceManager.getDefaultSharedPreferences(this).getInt(PREFERENCE_ONLINE_GAME_COUNT, 0);
    }

    public void incrementOnlineGameCount() {
        PreferenceManager.getDefaultSharedPreferences(this).edit().putInt(PREFERENCE_ONLINE_GAME_COUNT, getOnlineGameCount() + 1).apply();
    }

    public OnlineModeStatus getOnlineModeStatus() {
        return this.onlineModeStatus;
    }

    public void setOnlineModeStatus(OnlineModeStatus onlineModeStatus) {
        this.onlineModeStatus = onlineModeStatus;
        if (onlineModeStatus == OnlineModeStatus.STARTED || onlineModeStatus == OnlineModeStatus.NONE) {
            this.currentOnlineIntermediateResults = null;
        }
    }

    public IntermediateResultsEvent getCurrentOpponentIntermediateResults() {
        return this.currentOnlineIntermediateResults;
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService("connectivity");
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}

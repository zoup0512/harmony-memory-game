package com.cube.memorygames;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.IdRes;
import android.support.v4.content.ContextCompat;
import android.support.v4.internal.view.SupportMenu;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.cmcm.adsdk.CMAdError;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;
import com.cube.memorygames.OfferDialog.PurchaseDialogType;
import com.cube.memorygames.SharingDialog.IabStatus;
import com.cube.memorygames.SharingDialog.StatisticListener;
import com.cube.memorygames.api.local.LocalDataManager;
import com.cube.memorygames.api.local.challenge.ChallengeJsonGame;
import com.cube.memorygames.api.local.model.LocalUser;
import com.cube.memorygames.api.network.SyncDataAsyncTask;
import com.cube.memorygames.api.network.SyncDataAsyncTask.OnFinishListener;
import com.cube.memorygames.billing.IabHelper;
import com.cube.memorygames.billing.IabHelper.IabAsyncInProgressException;
import com.cube.memorygames.billing.IabHelper.OnConsumeMultiFinishedListener;
import com.cube.memorygames.billing.IabHelper.OnIabPurchaseFinishedListener;
import com.cube.memorygames.billing.IabHelper.OnIabSetupFinishedListener;
import com.cube.memorygames.billing.IabHelper.QueryInventoryFinishedListener;
import com.cube.memorygames.billing.IabResult;
import com.cube.memorygames.billing.Inventory;
import com.cube.memorygames.billing.Purchase;
import com.cube.memorygames.model.GameInfo;
import com.cube.memorygames.ui.AppRater;
import com.cube.memorygames.ui.GridSpaceItemDecorator;
import com.cube.memorygames.ui.MenuAdapter;
import com.cube.memorygames.ui.OnlineAdapter;
import com.cube.memorygames.ui.WorkoutAdapter;
import com.facebook.CallbackManager;
import com.facebook.CallbackManager.Factory;
import com.github.florent37.viewanimator.AnimationListener.Stop;
import com.github.florent37.viewanimator.ViewAnimator;
import com.google.gson.Gson;
import com.memory.brain.training.games.R;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarBadge;
import com.roughike.bottombar.OnMenuTabClickListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainMenuActivity extends AppCompatActivity implements StatisticListener, IabStatus, OnIabPurchaseFinishedListener {
    public static final String EXTRA_CHALLENGE_GAME_INFO = "challengeLaunchGameInfo";
    public static final String EXTRA_CHECK_APP_RATE = "checkAppRate";
    public static final String EXTRA_GAME_INFO = "launchGameInfo";
    public static final String EXTRA_ONLINE = "onlineMode";
    public static final String EXTRA_SHOW_SECRET_GAME = "showSecretGame";
    public static final int HINT_COINS_GAME_COUNT = 2;
    public static final int HINT_TOP_GAME_COUNT = 4;
    public static final String PREF_BADGE_CHALLENGE_CLICKED = "badgeChallengeClicked2";
    private static final String PREF_COINS_BUBBLE_SHOWED = "coinsBubbleShowedPrefs";
    public static final String PREF_GAMES_PLAYED = "gamesPlayedPrefs";
    private static final String PREF_LAST_TAB_POSITION = "lastTabPosition";
    private static final String PREF_TOP_BUBBLE_SHOWED = "topBubbleShowedPrefs";
    public static final int RESULT_LOGIN = 1001;
    private static final String SMART_PACKAGE_NAME = "com.pixign.smart.brain.games&referrer=utm_source%3Dmemory_games";
    private static final int TAB_CHALLENGE = 2;
    private static final int TAB_MENU = 4;
    private static final int TAB_ONLINE = 3;
    private static final int TAB_SPRINT = 0;
    private static final int TAB_WORKOUT = 1;
    private MemoryApplicationModel application;
    CallbackManager callbackManager;
    private ChallengeAdapter challengeAdapter;
    private ChallengeCLickListener challengeCLickListener = new ChallengeCLickListener();
    @Bind({2131624113})
    View coinsHint;
    @Bind({2131624076})
    View divider;
    private GameClickListener gameClickListener = new GameClickListener();
    private GameListAdapter gameListAdapter;
    private GridSpaceItemDecorator gridSpaceItemDecorator;
    private IabHelper iabHelper;
    private boolean isSetupFinished;
    private LocalDataManager localDataManager;
    private BottomBar mBottomBar;
    private MenuAdapter menuAdapter;
    private OnlineAdapter onlineAdapter;
    @Bind({2131624071})
    View panelHeader;
    private ProgressDialog progressDialog;
    @Bind({2131624074})
    TextView ratingView;
    @Bind({2131624112})
    RecyclerView recyclerView;
    @Bind({2131624080})
    View root;
    @Bind({2131624073})
    View star;
    @Bind({2131624072})
    TextView starsView;
    @Bind({2131624114})
    View topHint;
    private WorkoutAdapter workoutAdapter;

    private class ChallengeCLickListener implements OnClickListener {
        private ChallengeCLickListener() {
        }

        public void onClick(View v) {
            new ChallengeStartDialog(MainMenuActivity.this, (ChallengeJsonGame) v.getTag()).show();
        }
    }

    private class GameClickListener implements OnClickListener {
        private GameClickListener() {
        }

        public void onClick(final View v) {
            final GameInfo gameInfo = (GameInfo) v.getTag();
            String gameId = gameInfo.getId();
            if (gameId.equals(Games.SMART_PROMO_GAME_ID)) {
                try {
                    MainMenuActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.pixign.smart.brain.games&referrer=utm_source%3Dmemory_games")));
                    MainMenuActivity.this.application.logEvent(MainMenuActivity.this, MemoryApplicationModel.ANALYTICS_CATEGORY_PROMO, "Smart from main menu clicked");
                } catch (ActivityNotFoundException e) {
                    MainMenuActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://play.google.com/store/apps/details?id=com.pixign.smart.brain.games&referrer=utm_source%3Dmemory_games")));
                }
            } else if (gameId.equals(Games.UNLOCK_ALL_GAMES_ID)) {
                MainMenuActivity.this.application.logEvent(MainMenuActivity.this, MemoryApplicationModel.ANALYTICS_CATEGORY_PROMO, "Unlock games from main menu clicked");
                Answers.getInstance().logCustom(new CustomEvent("Unlock games from main menu clicked"));
                MainMenuActivity.this.buyProduct(MenuAdapter.SKU_UNLOCK_GAMES);
            } else {
                LocalUser localUser = MainMenuActivity.this.localDataManager.getLocalUser();
                String buyGameId = "game" + (Integer.parseInt(gameId) + 1);
                if (!gameInfo.hasLock() || localUser.parseUnlockedContent().contains(buyGameId) || localUser.vip || localUser.gamesUnlocked) {
                    MainMenuActivity.this.launchGame(gameInfo);
                } else if (gameInfo.isSecretGame()) {
                    OfferDialogSelector.showDialog(MainMenuActivity.this, MainMenuActivity.this.iabHelper, MainMenuActivity.this, PurchaseDialogType.SECRET_GAME);
                } else if (MainMenuActivity.this.localDataManager.getLocalUser().money >= gameInfo.getStarsToUnlock()) {
                    MainMenuActivity.this.application.logEvent(MainMenuActivity.this, MemoryApplicationModel.ANALYTICS_CATEGORY_UNLOCK, MemoryApplicationModel.ANALYTICS_EVENT_TRY_UNLOCK_GAME_PREFIX + gameInfo.getId());
                    new UnlockDialog(MainMenuActivity.this, new OnClickListener() {
                        public void onClick(View view) {
                            MainMenuActivity.this.unlockGame(gameInfo, v);
                        }
                    }).show();
                } else {
                    new SharingDialog(MainMenuActivity.this.callbackManager, MainMenuActivity.this, MainMenuActivity.this, MainMenuActivity.this.iabHelper, MainMenuActivity.this, Integer.valueOf(R.string.dialog_unlock_warning), false).show();
                }
            }
        }
    }

    class UnlockDialog extends Dialog {
        @Bind({2131624320})
        TextView cancel;
        @Bind({2131624318})
        TextView message;
        @Bind({2131624319})
        TextView ok;

        public UnlockDialog(Context context, final OnClickListener onClickListener) {
            super(context);
            setCancelable(false);
            getWindow().setBackgroundDrawableResource(17170445);
            getWindow().requestFeature(1);
            setContentView(R.layout.dialog_unlock);
            ButterKnife.bind((Object) this, (Dialog) this);
            Typeface typefaceLight = Typeface.createFromAsset(MainMenuActivity.this.getAssets(), "Roboto-Light.ttf");
            this.ok.setTypeface(typefaceLight);
            this.cancel.setTypeface(typefaceLight);
            this.message.setTypeface(typefaceLight, 2);
            this.message.setText(MainMenuActivity.this.getString(R.string.dialog_unlock_title) + " ");
            this.ok.setOnClickListener(new OnClickListener(MainMenuActivity.this) {
                public void onClick(View v) {
                    UnlockDialog.this.dismiss();
                    onClickListener.onClick(null);
                }
            });
            this.cancel.setOnClickListener(new OnClickListener(MainMenuActivity.this) {
                public void onClick(View v) {
                    UnlockDialog.this.dismiss();
                }
            });
        }
    }

    public static Intent newIntent(Context context, boolean showSecretGame) {
        Intent intent = new Intent(context, MainMenuActivity.class);
        intent.putExtra(EXTRA_CHECK_APP_RATE, true);
        intent.putExtra(EXTRA_SHOW_SECRET_GAME, showSecretGame);
        return intent;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        this.application = MemoryApplicationModel.getInstance();
        this.callbackManager = Factory.create();
        this.localDataManager = this.application.getLocalDataManager();
        setContentView((int) R.layout.activity_main_menu);
        ButterKnife.bind((Activity) this);
        if (getIntent().getBooleanExtra(EXTRA_CHECK_APP_RATE, false)) {
            AppRater.app_launched(this, true);
        } else {
            AppRater.app_launched(this, false);
        }
        Typeface typefaceLight = Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf");
        this.starsView.setTypeface(typefaceLight);
        this.ratingView.setTypeface(typefaceLight);
        showVerticalList();
        configureList(false);
        this.recyclerView.setAdapter(this.gameListAdapter);
        if (!displayCoinsHint()) {
            displayTopHint();
        }
        setupBottomBar(savedInstanceState);
        updateStatistics();
        this.iabHelper = new IabHelper(this, MemoryApplicationModel.BASE_64_ENCODED_PUBLIC_KEY);
        this.isSetupFinished = false;
        this.iabHelper.startSetup(new OnIabSetupFinishedListener() {
            public void onIabSetupFinished(IabResult result) {
                MainMenuActivity.this.isSetupFinished = result.isSuccess();
                if (result.isSuccess()) {
                    MainMenuActivity.this.checkPurchases();
                }
            }
        });
        if (getIntent().getBooleanExtra(EXTRA_SHOW_SECRET_GAME, false)) {
            SecretGameDialog.showDialogIfNeeded(this);
        }
        SyncDataAsyncTask syncDataAsyncTask = new SyncDataAsyncTask(this.application.getLocalDataManager());
        syncDataAsyncTask.setOnFinishListener(new OnFinishListener() {
            public void onFinishUpload(boolean result) {
                if (result && !MainMenuActivity.this.isFinishing()) {
                    SecretGameDialog.showDialogIfNeeded(MainMenuActivity.this, new OnClickListener() {
                        public void onClick(View v) {
                            int prev = MainMenuActivity.this.mBottomBar.getCurrentTabPosition();
                            MainMenuActivity.this.mBottomBar.selectTabAtPosition(4, false);
                            MainMenuActivity.this.mBottomBar.selectTabAtPosition(prev, false);
                        }
                    });
                }
            }
        });
        syncDataAsyncTask.execute(new Void[0]);
        suggestWorkout();
    }

    private void suggestWorkout() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        int startsCount = prefs.getInt(DialogTryWorkout.STARTS_COUNT_BEFORE_SHOW_KEY, 3);
        if (MemoryApplicationModel.getInstance().getLocalDataManager().getWorkoutRating() > 0.0f) {
            prefs.edit().putBoolean(DialogTryWorkout.TRY_WORKOUT_DIALOG_SHOW_KEY, false).apply();
        } else if (startsCount > 0) {
            prefs.edit().putInt(DialogTryWorkout.STARTS_COUNT_BEFORE_SHOW_KEY, startsCount - 1).apply();
        } else if (prefs.getBoolean(DialogTryWorkout.TRY_WORKOUT_DIALOG_SHOW_KEY, true)) {
            MemoryApplicationModel.getInstance().logEvent((Activity) this, MemoryApplicationModel.ANALYTICS_CATEGORY_WORKOUT, MemoryApplicationModel.ANALYTICS_EVENT_WORKOUT_DIALOG_SHOWED);
            Answers.getInstance().logCustom(new CustomEvent("Workout Dialog Showed"));
            new DialogTryWorkout(this, new OnClickListener() {
                public void onClick(View view) {
                    MemoryApplicationModel.getInstance().logEvent(MainMenuActivity.this, MemoryApplicationModel.ANALYTICS_CATEGORY_WORKOUT, MemoryApplicationModel.ANALYTICS_EVENT_WORKOUT_DIALOG_CLICKED);
                    Answers.getInstance().logCustom(new CustomEvent("Workout Dialog Clicked"));
                    MainMenuActivity.this.mBottomBar.selectTabAtPosition(1, false);
                }
            }).show();
        }
    }

    public void onBackPressed() {
        if (AppRater.checkGame248Offer(this)) {
            super.onBackPressed();
        }
    }

    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void checkPurchases() {
        if (this.iabHelper != null) {
            try {
                List<String> moreSkus = new ArrayList();
                moreSkus.add(MenuAdapter.SKU_UNLOCK_GAMES);
                moreSkus.add(MenuAdapter.SKU_PRO_VERSION);
                moreSkus.add(MenuAdapter.SKU_PRO_VERSION_DISCOUNT);
                moreSkus.add(MenuAdapter.SKU_REMOVE_ADS);
                moreSkus.add(MenuAdapter.SKU_UNLIMITEDONLINE);
                this.iabHelper.queryInventoryAsync(true, moreSkus, null, new QueryInventoryFinishedListener() {
                    public void onQueryInventoryFinished(IabResult result, Inventory inv) {
                        if (!result.isFailure()) {
                            MainMenuActivity.this.savePrices(inv);
                            LocalUser localUser = MainMenuActivity.this.localDataManager.getLocalUser();
                            boolean needUpdate = false;
                            if (inv.hasPurchase(MenuAdapter.SKU_UNLOCK_GAMES) && !localUser.gamesUnlocked) {
                                localUser.gamesUnlocked = true;
                                needUpdate = true;
                            }
                            if (!inv.hasPurchase(MenuAdapter.SKU_UNLOCK_GAMES) && localUser.gamesUnlocked) {
                                localUser.gamesUnlocked = false;
                                needUpdate = true;
                            }
                            if ((inv.hasPurchase(MenuAdapter.SKU_PRO_VERSION) || inv.hasPurchase(MenuAdapter.SKU_PRO_VERSION_DISCOUNT)) && !localUser.vip) {
                                localUser.vip = true;
                                needUpdate = true;
                            }
                            if (!(inv.hasPurchase(MenuAdapter.SKU_PRO_VERSION) || inv.hasPurchase(MenuAdapter.SKU_PRO_VERSION_DISCOUNT) || !localUser.vip)) {
                                localUser.vip = false;
                                needUpdate = true;
                            }
                            if (inv.hasPurchase(MenuAdapter.SKU_REMOVE_ADS) && !localUser.adsRemoved) {
                                localUser.adsRemoved = true;
                                needUpdate = true;
                            }
                            if (!inv.hasPurchase(MenuAdapter.SKU_REMOVE_ADS) && localUser.adsRemoved) {
                                localUser.adsRemoved = false;
                                needUpdate = true;
                            }
                            if (inv.hasPurchase(MenuAdapter.SKU_UNLIMITEDONLINE) && !localUser.unlimitedOnline) {
                                localUser.unlimitedOnline = true;
                                needUpdate = true;
                            }
                            if (!inv.hasPurchase(MenuAdapter.SKU_UNLIMITEDONLINE) && localUser.unlimitedOnline) {
                                localUser.unlimitedOnline = false;
                                needUpdate = true;
                            }
                            if (needUpdate) {
                                localUser.save();
                                MainMenuActivity.this.updateStatistics();
                            }
                            List purchases = new ArrayList();
                            if (inv.hasPurchase(SharingDialog.SKU1)) {
                                purchases.add(inv.getPurchase(SharingDialog.SKU1));
                            }
                            if (inv.hasPurchase(SharingDialog.SKU2)) {
                                purchases.add(inv.getPurchase(SharingDialog.SKU2));
                            }
                            if (inv.hasPurchase(SharingDialog.SKU3)) {
                                purchases.add(inv.getPurchase(SharingDialog.SKU3));
                            }
                            if (!purchases.isEmpty()) {
                                try {
                                    if (MainMenuActivity.this.iabHelper != null) {
                                        MainMenuActivity.this.iabHelper.consumeAsync(purchases, new OnConsumeMultiFinishedListener() {
                                            public void onConsumeMultiFinished(List<Purchase> purchases, List<IabResult> results) {
                                                int money = 0;
                                                for (int i = 0; i < results.size(); i++) {
                                                    Purchase purchase = (Purchase) purchases.get(i);
                                                    if (((IabResult) results.get(i)).isSuccess()) {
                                                        String sku = purchase.getSku();
                                                        Object obj = -1;
                                                        switch (sku.hashCode()) {
                                                            case -1216851259:
                                                                if (sku.equals(SharingDialog.SKU2)) {
                                                                    obj = 1;
                                                                    break;
                                                                }
                                                                break;
                                                            case 525959076:
                                                                if (sku.equals(SharingDialog.SKU3)) {
                                                                    obj = 2;
                                                                    break;
                                                                }
                                                                break;
                                                            case 1335305702:
                                                                if (sku.equals(SharingDialog.SKU1)) {
                                                                    obj = null;
                                                                    break;
                                                                }
                                                                break;
                                                        }
                                                        switch (obj) {
                                                            case null:
                                                                money += SharingDialog.DOLLAR1_COINS;
                                                                break;
                                                            case 1:
                                                                money += SharingDialog.DOLLAR2_COINS;
                                                                break;
                                                            case 2:
                                                                money += 2500;
                                                                break;
                                                            default:
                                                                break;
                                                        }
                                                    }
                                                }
                                                MemoryApplicationModel.getInstance().getLocalDataManager().addCoinsTransaction(LocalDataManager.TYPE_BUY, money);
                                                MainMenuActivity.this.updateStatistics();
                                            }
                                        });
                                    }
                                } catch (IabAsyncInProgressException e) {
                                }
                            }
                        }
                    }
                });
            } catch (IabAsyncInProgressException e) {
            }
        }
    }

    private void savePrices(Inventory inv) {
        Object map = new HashMap();
        map.put(MenuAdapter.SKU_UNLOCK_GAMES, inv.getSkuDetails(MenuAdapter.SKU_UNLOCK_GAMES));
        map.put(MenuAdapter.SKU_PRO_VERSION, inv.getSkuDetails(MenuAdapter.SKU_PRO_VERSION));
        map.put(MenuAdapter.SKU_PRO_VERSION_DISCOUNT, inv.getSkuDetails(MenuAdapter.SKU_PRO_VERSION_DISCOUNT));
        map.put(MenuAdapter.SKU_REMOVE_ADS, inv.getSkuDetails(MenuAdapter.SKU_REMOVE_ADS));
        map.put(MenuAdapter.SKU_UNLIMITEDONLINE, inv.getSkuDetails(MenuAdapter.SKU_UNLIMITEDONLINE));
        PreferenceManager.getDefaultSharedPreferences(this).edit().putString(MenuAdapter.PREF_SKU_DETAILS, new Gson().toJson(map)).apply();
    }

    public boolean isIabSetupFinished() {
        return this.isSetupFinished;
    }

    private void setupBottomBar(Bundle savedInstanceState) {
        this.mBottomBar = BottomBar.attach((Activity) this, savedInstanceState);
        this.mBottomBar.setMaxFixedTabs(3);
        this.mBottomBar.useOnlyStatusBarTopOffset();
        this.mBottomBar.noTabletGoodness();
        this.mBottomBar.setItems((int) R.menu.bottombar_menu);
        this.mBottomBar.setOnMenuTabClickListener(new OnMenuTabClickListener() {
            public void onMenuTabSelected(@IdRes final int menuItemId) {
                int animationPadding = MainMenuActivity.this.getResources().getDimensionPixelOffset(R.dimen.padding_big);
                ViewAnimator.animate(MainMenuActivity.this.recyclerView).duration((long) 100).fadeOut().onStop(new Stop() {
                    public void onStop() {
                        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MainMenuActivity.this);
                        int newTab = 0;
                        if (menuItemId == R.id.bottomBarCup) {
                            newTab = 0;
                            MainMenuActivity.this.configureList(false);
                            if (MainMenuActivity.this.recyclerView.getAdapter() != MainMenuActivity.this.gameListAdapter) {
                                MainMenuActivity.this.recyclerView.setAdapter(MainMenuActivity.this.gameListAdapter);
                            }
                            MainMenuActivity.this.recyclerView.setBackgroundColor(ContextCompat.getColor(MainMenuActivity.this, R.color.grid_stroke));
                            MainMenuActivity.this.gridSpaceItemDecorator.setSpace(MainMenuActivity.this.getResources().getDimensionPixelSize(R.dimen.cell_stroke_width));
                            MainMenuActivity.this.recyclerView.invalidateItemDecorations();
                            List<GameInfo> gameInfoList = Games.get().getSprintGames();
                            if (MainMenuActivity.this.localDataManager.getLocalUser().vip || MainMenuActivity.this.localDataManager.getLocalUser().gamesUnlocked) {
                                gameInfoList.remove(Games.get().getGameBiId(Games.UNLOCK_ALL_GAMES_ID));
                            }
                            MainMenuActivity.this.gameListAdapter.setList(gameInfoList);
                            MainMenuActivity.this.gameListAdapter.updateStatistics();
                            MainMenuActivity.this.panelHeader.setVisibility(0);
                            MainMenuActivity.this.divider.setVisibility(0);
                        } else if (menuItemId == R.id.bottomBarChallenge) {
                            newTab = 2;
                            MainMenuActivity.this.configureList(true);
                            if (MainMenuActivity.this.challengeAdapter == null) {
                                MainMenuActivity.this.challengeAdapter = new ChallengeAdapter(MainMenuActivity.this, MainMenuActivity.this.challengeCLickListener, (ChallengeJsonGame) MainMenuActivity.this.getIntent().getParcelableExtra(MainMenuActivity.EXTRA_CHALLENGE_GAME_INFO));
                            }
                            MainMenuActivity.this.recyclerView.setAdapter(MainMenuActivity.this.challengeAdapter);
                            MainMenuActivity.this.recyclerView.setBackgroundColor(ContextCompat.getColor(MainMenuActivity.this, R.color.grid_stroke));
                            MainMenuActivity.this.gridSpaceItemDecorator.setSpace(0);
                            MainMenuActivity.this.recyclerView.invalidateItemDecorations();
                            MainMenuActivity.this.panelHeader.setVisibility(0);
                            MainMenuActivity.this.divider.setVisibility(0);
                            MainMenuActivity.this.application.logEvent(MainMenuActivity.this, MemoryApplicationModel.ANALYTICS_CATEGORY_CHALLENGE, MemoryApplicationModel.ANALYTICS_EVENT_CHALLENGE_TAB_CLICKED);
                            Answers.getInstance().logCustom(new CustomEvent(MemoryApplicationModel.ANALYTICS_EVENT_CHALLENGE_TAB_CLICKED));
                        } else if (menuItemId == R.id.bottomBarMenu) {
                            newTab = 4;
                            MainMenuActivity.this.configureList(true);
                            if (MainMenuActivity.this.menuAdapter == null) {
                                MainMenuActivity.this.menuAdapter = new MenuAdapter(MainMenuActivity.this, MainMenuActivity.this.iabHelper, MainMenuActivity.this);
                            }
                            MainMenuActivity.this.recyclerView.setAdapter(MainMenuActivity.this.menuAdapter);
                            MainMenuActivity.this.recyclerView.setBackgroundColor(ContextCompat.getColor(MainMenuActivity.this, R.color.background3));
                            MainMenuActivity.this.gridSpaceItemDecorator.setSpace(MainMenuActivity.this.getResources().getDimensionPixelSize(R.dimen.cell_stroke_width));
                            MainMenuActivity.this.recyclerView.invalidateItemDecorations();
                            MainMenuActivity.this.panelHeader.setVisibility(8);
                            MainMenuActivity.this.divider.setVisibility(8);
                        } else if (menuItemId == R.id.bottomBarWorkout) {
                            MainMenuActivity.this.configureList(true);
                            newTab = 1;
                            if (MainMenuActivity.this.workoutAdapter == null) {
                                MainMenuActivity.this.workoutAdapter = new WorkoutAdapter(MainMenuActivity.this);
                            }
                            MainMenuActivity.this.recyclerView.setAdapter(MainMenuActivity.this.workoutAdapter);
                            MainMenuActivity.this.recyclerView.setBackgroundColor(-1);
                            MainMenuActivity.this.gridSpaceItemDecorator.setSpace(MainMenuActivity.this.getResources().getDimensionPixelSize(R.dimen.cell_stroke_width));
                            MainMenuActivity.this.recyclerView.invalidateItemDecorations();
                            MainMenuActivity.this.panelHeader.setVisibility(8);
                            MainMenuActivity.this.divider.setVisibility(8);
                            sharedPreferences.edit().putBoolean(MainMenuActivity.PREF_BADGE_CHALLENGE_CLICKED, true).apply();
                            MainMenuActivity.this.application.logEvent(MainMenuActivity.this, MemoryApplicationModel.ANALYTICS_CATEGORY_WORKOUT, MemoryApplicationModel.ANALYTICS_EVENT_WORKOUT_TAB_CLICKED);
                            Answers.getInstance().logCustom(new CustomEvent(MemoryApplicationModel.ANALYTICS_EVENT_WORKOUT_TAB_CLICKED));
                        } else if (menuItemId == R.id.bottomBarOnline) {
                            MainMenuActivity.this.configureList(true);
                            newTab = 3;
                            if (MainMenuActivity.this.onlineAdapter == null) {
                                MainMenuActivity.this.onlineAdapter = new OnlineAdapter(MainMenuActivity.this);
                            }
                            MainMenuActivity.this.recyclerView.setAdapter(MainMenuActivity.this.onlineAdapter);
                            MainMenuActivity.this.recyclerView.setBackgroundColor(ContextCompat.getColor(MainMenuActivity.this, R.color.background3));
                            MainMenuActivity.this.gridSpaceItemDecorator.setSpace(MainMenuActivity.this.getResources().getDimensionPixelSize(R.dimen.cell_stroke_width));
                            MainMenuActivity.this.recyclerView.invalidateItemDecorations();
                            MainMenuActivity.this.panelHeader.setVisibility(0);
                            MainMenuActivity.this.divider.setVisibility(0);
                            MainMenuActivity.this.application.logEvent(MainMenuActivity.this, MemoryApplicationModel.ANALYTICS_CATEGORY_ONLINE, MemoryApplicationModel.ANALYTICS_EVENT_ONLINE_TAB_CLICKED);
                            Answers.getInstance().logCustom(new CustomEvent(MemoryApplicationModel.ANALYTICS_EVENT_ONLINE_TAB_CLICKED));
                        }
                        sharedPreferences.edit().putInt(MainMenuActivity.PREF_LAST_TAB_POSITION, newTab).apply();
                    }
                }).thenAnimate(MainMenuActivity.this.recyclerView).duration((long) 100).fadeIn().andAnimate(MainMenuActivity.this.recyclerView).duration((long) 100).interpolator(new DecelerateInterpolator()).translationY((float) animationPadding, 0.0f).start();
            }

            public void onMenuTabReSelected(@IdRes int menuItemId) {
            }
        });
        this.mBottomBar.mapColorForTab(0, ContextCompat.getColor(this, R.color.tab1));
        this.mBottomBar.mapColorForTab(2, ContextCompat.getColor(this, R.color.tab2));
        this.mBottomBar.mapColorForTab(3, ContextCompat.getColor(this, R.color.tab3));
        this.mBottomBar.mapColorForTab(4, ContextCompat.getColor(this, R.color.tab4));
        this.mBottomBar.mapColorForTab(1, ContextCompat.getColor(this, R.color.tab1));
        int selectedTab = PreferenceManager.getDefaultSharedPreferences(this).getInt(PREF_LAST_TAB_POSITION, 0);
        if (getIntent() != null && getIntent().getBooleanExtra(EXTRA_ONLINE, false)) {
            selectedTab = 3;
        }
        showBadge();
        this.mBottomBar.selectTabAtPosition(selectedTab, false);
    }

    private void showBadge() {
        if (!PreferenceManager.getDefaultSharedPreferences(this).getBoolean(PREF_BADGE_CHALLENGE_CLICKED, false)) {
            ViewGroup mItemContainer = (ViewGroup) this.mBottomBar.findViewById(R.id.bb_bottom_bar_item_container);
            this.mBottomBar.makeBadgeForTabAt(2, (int) SupportMenu.CATEGORY_MASK, 1);
            ViewGroup tab = (ViewGroup) mItemContainer.getChildAt(1);
            for (int i = 0; i < tab.getChildCount(); i++) {
                View view = tab.getChildAt(i);
                if (view instanceof BottomBarBadge) {
                    BottomBarBadge bottomBarBadge = (BottomBarBadge) view;
                    bottomBarBadge.setText(" New ");
                    bottomBarBadge.setTextSize(12.0f);
                    bottomBarBadge.setBackgroundResource(R.drawable.new_badge);
                }
            }
        }
    }

    private void showVerticalList() {
        this.gridSpaceItemDecorator = new GridSpaceItemDecorator(getResources().getDimensionPixelSize(R.dimen.cell_stroke_width));
        this.recyclerView.addItemDecoration(this.gridSpaceItemDecorator);
        List<GameInfo> gameInfoList = Games.get().getSprintGames();
        if (this.localDataManager.getLocalUser().vip || this.localDataManager.getLocalUser().gamesUnlocked) {
            gameInfoList.remove(Games.get().getGameBiId(Games.UNLOCK_ALL_GAMES_ID));
        }
        this.gameListAdapter = new GameListAdapter(this, gameInfoList, this.gameClickListener);
        this.recyclerView.setClipToPadding(true);
        this.recyclerView.setPadding(0, 0, 0, 0);
        this.recyclerView.clearOnScrollListeners();
    }

    private void configureList(boolean alwaysList) {
        if (!getResources().getBoolean(R.bool.isTablet) || alwaysList) {
            this.recyclerView.setLayoutManager(new LinearLayoutManager(this, OrientationHelper. VERTICAL, false));
        } else {
            this.recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }
    }

    protected void onResume() {
        super.onResume();
        updateStatistics();
    }

    protected void onDestroy() {
        hideProgress();
        super.onDestroy();
        if (this.iabHelper != null) {
            try {
                this.iabHelper.dispose();
            } catch (IabAsyncInProgressException e) {
            } catch (IllegalArgumentException e2) {
            }
        }
        this.iabHelper = null;
    }

    private void showProgress() {
        this.progressDialog = ProgressDialog.show(this, null, getResources().getString(R.string.loading), true, false);
    }

    private void hideProgress() {
        if (this.progressDialog != null && this.progressDialog.isShowing()) {
            this.progressDialog.cancel();
        }
    }

    private void launchGame(GameInfo gameInfo) {
        Intent intent = new Intent(this, StartGameActivity.class);
        intent.putExtra(StartGameActivity.EXTRA_CHALLENGE, false);
        intent.putExtra(EXTRA_GAME_INFO, gameInfo);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_to_left, R.anim.no_change);
        this.application.setLastGamePlayed(gameInfo.getId());
    }

    public void showLogin(boolean type) {
        startActivityForResult(LoginActivity.newIntent(this, type), 1001);
    }

    @OnClick({2131624075})
    public void topClick() {
        if (TextUtils.isEmpty(this.localDataManager.getLocalUser().displayName)) {
            showLogin(false);
        } else {
            showTop();
        }
    }

    @OnClick({2131624074})
    protected void topClick2() {
        topClick();
    }

    private void showTop() {
        this.application.logEvent((Activity) this, MemoryApplicationModel.ANALYTICS_CATEGORY_GENERAL, MemoryApplicationModel.ANALYTICS_EVENT_TOP_CLICKED);
        startActivity(TopRanksActivity.newIntent(this, 1));
    }

    public void updateStatistics() {
        if (!isFinishing()) {
            this.starsView.setText(String.valueOf(this.localDataManager.getLocalUser().money));
            this.ratingView.setText(String.valueOf(round((double) this.localDataManager.getLocalUser().rating, 1)));
            if (this.mBottomBar.getCurrentTabPosition() == 0) {
                this.gameListAdapter.updateStatistics();
            } else if (this.mBottomBar.getCurrentTabPosition() == 2) {
                if (this.challengeAdapter != null) {
                    this.challengeAdapter.notifyDataSetChanged();
                }
            } else if (this.mBottomBar.getCurrentTabPosition() == 3) {
                if (this.onlineAdapter != null) {
                    this.onlineAdapter.notifyDataSetChanged();
                }
            } else if (this.mBottomBar.getCurrentTabPosition() == 4) {
                if (this.menuAdapter != null) {
                    this.menuAdapter.fillMenuItems();
                    this.menuAdapter.notifyDataSetChanged();
                }
            } else if (this.mBottomBar.getCurrentTabPosition() == 1 && this.workoutAdapter != null) {
                this.workoutAdapter.notifyDataSetChanged();
            }
        }
    }

    public Point getWindowSize() {
        Display display = ((WindowManager) getSystemService("window")).getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }

    public void onStatisticUpdated() {
        updateStatistics();
    }

    private void buyProduct(String sku) {
        if (isIabSetupFinished()) {
            try {
                this.iabHelper.launchPurchaseFlow(this, sku, CMAdError.EXTERNAL_CONFIG_ERROR, this, MemoryApplicationModel.getInstance().getLocalDataManager().getLocalUser().objectId);
                this.progressDialog = ProgressDialog.show(this, null, "Loading...", false, false);
            } catch (IabAsyncInProgressException e) {
                if (this.progressDialog != null) {
                    this.progressDialog.dismiss();
                }
            }
        }
    }

    public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
        if (this.progressDialog != null) {
            this.progressDialog.dismiss();
        }
        if (!result.isFailure()) {
            String sku = purchase.getSku();
            LocalUser localUser = MemoryApplicationModel.getInstance().getLocalDataManager().getLocalUser();
            Object obj = -1;
            switch (sku.hashCode()) {
                case -949882362:
                    if (sku.equals(MenuAdapter.SKU_UNLOCK_GAMES)) {
                        obj = null;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    localUser.gamesUnlocked = true;
                    Answers.getInstance().logCustom(new CustomEvent(MenuAdapter.SKU_UNLOCK_GAMES));
                    this.application.logEvent((Activity) this, MemoryApplicationModel.ANALYTICS_CATEGORY_PROMO, "Unlock games from main menu finished");
                    Answers.getInstance().logCustom(new CustomEvent("Unlock games from main menu finished"));
                    SecretGameDialog.showDialogIfNeeded(this);
                    break;
            }
            localUser.save();
            List<GameInfo> gameInfoList = Games.get().getSprintGames();
            if (this.localDataManager.getLocalUser().vip || this.localDataManager.getLocalUser().gamesUnlocked) {
                gameInfoList.remove(Games.get().getGameBiId(Games.UNLOCK_ALL_GAMES_ID));
            }
            this.gameListAdapter.setList(gameInfoList);
        }
    }

    private void unlockGame(GameInfo gameInfo, View view) {
        if (!(view == null || view.getParent() == null)) {
            View lockView = ((ViewGroup) view.getParent()).findViewById(R.id.lock_container);
            if (lockView != null) {
                lockView.setVisibility(8);
            }
        }
        this.localDataManager.addCoinsTransaction(LocalDataManager.TYPE_BUY_GAME + gameInfo.getId(), -gameInfo.getStarsToUnlock(), "game" + (Integer.parseInt(gameInfo.getId()) + 1));
        updateStatistics();
        this.application.logEvent((Activity) this, MemoryApplicationModel.ANALYTICS_CATEGORY_UNLOCK, MemoryApplicationModel.ANALYTICS_EVENT_UNLOCKED_GAME_PREFIX + gameInfo.getId());
    }

    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        long factor = (long) Math.pow(10.0d, (double) places);
        return ((double) Math.round(value * ((double) factor))) / ((double) factor);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.iabHelper.handleActivityResult(requestCode, resultCode, data);
        this.callbackManager.onActivityResult(requestCode, resultCode, data);
        updateStatistics();
    }

    public void showMoneyDialog(boolean fromOnline) {
        Integer message = null;
        if (fromOnline) {
            message = Integer.valueOf(R.string.dialog_unlock_online_warning);
        }
        new SharingDialog(this.callbackManager, this, this, this.iabHelper, this, message, fromOnline).show();
    }

    @OnClick({2131624073})
    void showMoneyDialogClick() {
        showMoneyDialog(false);
    }

    @OnClick({2131624072})
    void showMoneyDialogFromStarClick() {
        showMoneyDialog(false);
    }

    private boolean displayCoinsHint() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (sharedPreferences.getBoolean(PREF_COINS_BUBBLE_SHOWED, false)) {
            return false;
        }
        int gamePlayed = sharedPreferences.getStringSet(PREF_GAMES_PLAYED, new HashSet()).size();
        System.err.println("gamePlayed = " + gamePlayed);
        if (gamePlayed < 2) {
            return false;
        }
        this.coinsHint.setVisibility(0);
        this.coinsHint.bringToFront();
        MarginLayoutParams layoutParams = (MarginLayoutParams) this.coinsHint.getLayoutParams();
        layoutParams.topMargin = getResources().getDimensionPixelSize(R.dimen.top_bar_height);
        this.coinsHint.setLayoutParams(layoutParams);
        float distance = getResources().getDimension(R.dimen.anim_distance);
        ViewAnimator.animate(this.coinsHint).fadeIn().duration(1000).thenAnimate(this.coinsHint).interpolator(new LinearInterpolator()).translationY(0.0f, distance, 0.0f, distance, 0.0f, distance, 0.0f, distance, 0.0f, distance, 0.0f).duration(3000).thenAnimate(this.coinsHint).startDelay(4000).fadeOut().duration(1000).start();
        sharedPreferences.edit().putBoolean(PREF_COINS_BUBBLE_SHOWED, true).apply();
        return true;
    }

    public void startVipActivity() {
        OfferDialogSelector.showDialog(this, this.iabHelper, this, PurchaseDialogType.PURCHASE_PRO);
    }

    private boolean displayTopHint() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if (sharedPreferences.getBoolean(PREF_TOP_BUBBLE_SHOWED, false)) {
            return false;
        }
        int gamePlayed = sharedPreferences.getStringSet(PREF_GAMES_PLAYED, new HashSet()).size();
        System.err.println("gamePlayed = " + gamePlayed);
        if (gamePlayed < 4) {
            return false;
        }
        this.topHint.setVisibility(0);
        this.topHint.bringToFront();
        MarginLayoutParams layoutParams = (MarginLayoutParams) this.topHint.getLayoutParams();
        layoutParams.topMargin = getResources().getDimensionPixelSize(R.dimen.top_bar_height);
        this.topHint.setLayoutParams(layoutParams);
        float distance = getResources().getDimension(R.dimen.anim_distance);
        ViewAnimator.animate(this.topHint).fadeIn().duration(1000).thenAnimate(this.topHint).interpolator(new LinearInterpolator()).translationY(0.0f, distance, 0.0f, distance, 0.0f, distance, 0.0f, distance, 0.0f, distance, 0.0f).duration(3000).thenAnimate(this.topHint).startDelay(4000).fadeOut().duration(1000).start();
        sharedPreferences.edit().putBoolean(PREF_TOP_BUBBLE_SHOWED, true).apply();
        return true;
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.mBottomBar.onSaveInstanceState(outState);
    }
}

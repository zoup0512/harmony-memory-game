package com.cube.memorygames.activity;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.appodeal.ads.Appodeal;
import com.appodeal.ads.InterstitialCallbacks;
import com.appodeal.ads.RewardedVideoCallbacks;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;
import com.cube.memorygames.Games;
import com.cube.memorygames.MainMenuActivity;
import com.cube.memorygames.MemoryApplicationModel;
import com.cube.memorygames.MemoryApplicationModel.OnlineModeStatus;
import com.cube.memorygames.NotificationManager;
import com.cube.memorygames.NotificationManager.OnNotificationListener;
import com.cube.memorygames.OfferDialog.PurchaseDialogType;
import com.cube.memorygames.OfferDialogSelector;
import com.cube.memorygames.SharingDialog.IabStatus;
import com.cube.memorygames.activity.fragments.GameListFragment;
import com.cube.memorygames.activity.fragments.GameResultsFragment;
import com.cube.memorygames.activity.fragments.MatchFoundFragment;
import com.cube.memorygames.activity.fragments.SearchFragment;
import com.cube.memorygames.api.local.model.LocalUser;
import com.cube.memorygames.api.network.body.BodyNoResults;
import com.cube.memorygames.api.network.body.BodyOnlineQueueIdUserId;
import com.cube.memorygames.api.network.body.BodyOnlineResults;
import com.cube.memorygames.api.network.body.BodyRoundResults;
import com.cube.memorygames.api.network.body.BodyUserId;
import com.cube.memorygames.billing.IabHelper;
import com.cube.memorygames.billing.IabHelper.IabAsyncInProgressException;
import com.cube.memorygames.billing.IabHelper.OnIabSetupFinishedListener;
import com.cube.memorygames.billing.IabResult;
import com.cube.memorygames.model.GameInfo;
import com.cube.memorygames.pushes.event.ErrorEvent;
import com.cube.memorygames.pushes.event.GameEndedEvent;
import com.cube.memorygames.pushes.event.IntermediateResultsEvent;
import com.cube.memorygames.pushes.event.MatchFoundEvent;
import com.cube.memorygames.pushes.event.OnlineEvent;
import com.cube.memorygames.pushes.event.OnlineEventListener;
import com.cube.memorygames.pushes.event.StartGameEvent;
import com.cube.memorygames.pushes.event.UserConfirmedGameEvent;
import com.cube.memorygames.pushes.model.OnlineMatchUser;
import com.cube.memorygames.ui.OnlineResultDialog;
import com.cube.memorygames.ui.TimerView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest.Builder;
import com.google.android.gms.ads.InterstitialAd;
import com.google.gson.Gson;
import com.memory.brain.training.games.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Picasso.LoadedFrom;
import com.squareup.picasso.Target;
import java.util.ArrayList;
import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class PlayOnlineActivity extends AppCompatActivity implements OnlineEventListener, OnNotificationListener, IabStatus {
    private static final int ADS_TYPE_EXIT = 2;
    private static final int ADS_TYPE_NEXT_DUEL = 1;
    private static final int ADS_TYPE_NONE = 0;
    private static final String CURRENT_QUEUE_ID_KEY = "current_queue_id_key";
    private static final String EXTRA_CURRENT_GAMES_LIST = "current_games_list";
    private static final String EXTRA_CURRENT_GAME_ID = "current_game_id";
    private static final String EXTRA_CURRENT_GAME_SEED = "current_game_seed";
    private static final String EXTRA_CURRENT_QUEUE_ID = "current_queue_id";
    private static final String EXTRA_CURRENT_RESULTS_LIST = "current_results_list";
    private static final String EXTRA_CURRENT_ROUND_NUMBER = "current_round_number";
    public static final String EXTRA_CURRENT_STATE = "current_state";
    public static final String EXTRA_GAME_SEED = "EXTRA_GAME_SEED";
    private static final String EXTRA_IS_TUTORIAL_NEEDED = "is_tutorial_needed";
    public static final String EXTRA_LEVEL = "EXTRA_LEVEL";
    private static final String EXTRA_MY_ONLINE_MATCH_USER = "my_online_match_user";
    public static final String EXTRA_ONLINE_GAME_NUMBER = "EXTRA_ONLINE_GAME_NUMBER";
    private static final String EXTRA_ONLINE_MATCH_USER = "online_match_user";
    public static final String EXTRA_ONLINE_MODE = "EXTRA_ONLINE_MODE";
    public static final String EXTRA_ONLINE_PROGRESS_TIMEOUT = "EXTRA_ONLINE_PROGRESS_TIMEOUT";
    public static final String EXTRA_ONLINE_SCORE = "EXTRA_ONLINE_SCORE";
    public static final String EXTRA_ONLINE_TOTAL_GAMES = "EXTRA_ONLINE_TOTAL_GAMES";
    private static final String EXTRA_PLAYER_1_RESULTS = "player1_results";
    private static final String EXTRA_PLAYER_2_RESULTS = "player2_results";
    private static final String MY_ONLINE_MATCH_USER_KEY = "my_online_match_user_key";
    private static final int NO_RESULTS_TIMEOUT = 24000;
    public static final int ONLINE_GAME_COUNT = 9;
    private static final String ONLINE_MATCH_USER_KEY = "online_match_user_key";
    public static final int ONLINE_MONEY_PRICE = 30;
    public static final int ONLINE_MONEY_WIN = 25;
    private static final String PREF_COUNT_BEFORE_AD = "prefCountBeforeAd";
    private static final String PREF_TUTORIAL_SHOW = "OnlineTutorialShowed";
    private static final int PROGRESS_DELAY = 4000;
    private static final int PROGRESS_DELAY_TUTORIAL = 2000;
    private static final int REQUEST_CODE_ONLINE_GAME = 141;
    private static final int REQUEST_CODE_TUTORIAL = 142;
    private static final int RETRY_TIMEOUT = 15000;
    private static final String SEARCH_NEXT_USER = "is_searching_next_user_key";
    public static final String SELECTED_AD_TYPE = "selectedAdType";
    private static final int START_TIMEOUT = 5000;
    private static final int START_TIMEOUT_TUTORIAL = 2000;
    private static final int TUTORIAL_COUNT = 2;
    private static final int VIDEO_ADS_INTERVAL = 10;
    private static final int WAITING_TIMEOUT = 15000;
    private MemoryApplicationModel application;
    @Bind({2131624115})
    View backButton;
    @Bind({2131624138})
    View bottomDivider;
    @Bind({2131624142})
    TextView buttonCancel;
    @Bind({2131624141})
    TextView buttonNext;
    @Bind({2131624140})
    TextView buttonStart;
    @Bind({2131624139})
    View buttonsContainer;
    private String currentGameId;
    private long currentGameSeed;
    private List<String> currentGamesList;
    private String currentQueueId;
    private ArrayList<Integer> currentResultsList;
    private int currentRoundNumber;
    private UIState currentState;
    private Handler endGameHandler;
    private Runnable endGameRunnable;
    private OnClickListener exitClickListener = new OnClickListener() {
        public void onClick(View view) {
            Log.d("finish", "exitClickListener");
            PlayOnlineActivity.this.clearMatchEventFromSharedPrefs();
            PlayOnlineActivity.this.finish();
        }
    };
    private IabHelper iabHelper;
    private boolean isSetupFinished;
    private boolean isTutorialNeeded;
    private InterstitialAd mInterstitialAd;
    private OnlineMatchUser myOnlineMatchUser;
    private OnClickListener nextDuelClickListener = new OnClickListener() {
        public void onClick(View view) {
            LocalUser localUser = PlayOnlineActivity.this.application.getLocalDataManager().getLocalUser();
            if (localUser.unlimitedOnline || localUser.money >= 30) {
                PlayOnlineActivity.this.startActivity(new Intent(PlayOnlineActivity.this, PlayOnlineActivity.class));
            } else if (PlayOnlineActivity.this.application.getOnShowMoneyDialogListener() != null) {
                PlayOnlineActivity.this.application.getOnShowMoneyDialogListener().onShowMoneyDialog();
            }
            Log.d("finish", "nextDuelClickListener");
            PlayOnlineActivity.this.clearMatchEventFromSharedPrefs();
            PlayOnlineActivity.this.finish();
        }
    };
    private OnlineMatchUser onlineMatchUser;
    private List<Integer> player1Results;
    private List<Integer> player2Results;
    private Target profilePicture2Target;
    private Handler rejectedHandler;
    private Runnable rejectedRunnable;
    private Handler retryHandler;
    private Runnable retryRunnable;
    private int selectedAdType;
    @Bind({2131624072})
    TextView starsCount;
    private Handler startHandler;
    private Runnable startRunnable;
    @Bind({2131624092})
    View timerContainer;
    private Handler timerHandler = new Handler();
    @Bind({2131624376})
    TextView timerLabel;
    private Runnable timerRunnable = new Runnable() {
        public void run() {
            PlayOnlineActivity.this.resendResult();
        }
    };
    @Bind({2131624375})
    TimerView timerView2;
    private Handler waitHandler;
    private Runnable waitRunnable;

    private enum UIState {
        UI_SEARCH,
        UI_MATCH,
        UI_ACCEPTED,
        UI_CONFIRMED,
        UI_GAME_LIST,
        UI_RESULTS
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.application = MemoryApplicationModel.getInstance();
        setContentView((int) R.layout.activity_play_online);
        ButterKnife.bind((Activity) this);
        this.application.addOnlineEventListener(this);
        NotificationManager.getInstance().addNotificationListener(this);
        this.starsCount.setText(String.valueOf(MemoryApplicationModel.getInstance().getLocalDataManager().getLocalUser().money));
        this.retryHandler = new Handler();
        this.retryRunnable = new Runnable() {
            public void run() {
                PlayOnlineActivity.this.retryFindMatch();
            }
        };
        this.startHandler = new Handler();
        this.startRunnable = new Runnable() {
            public void run() {
                PlayOnlineActivity.this.startRound();
                PlayOnlineActivity.this.application.setOnlineModeStatus(OnlineModeStatus.STARTED);
                PlayOnlineActivity.this.player1Results = new ArrayList();
                PlayOnlineActivity.this.player2Results = new ArrayList();
                PlayOnlineActivity.this.showGameResults();
            }
        };
        this.waitHandler = new Handler();
        this.waitRunnable = new Runnable() {
            public void run() {
                Log.d("wait runnable", "backclick");
                PlayOnlineActivity.this.backClick(PlayOnlineActivity.this.backButton);
            }
        };
        this.rejectedHandler = new Handler();
        this.rejectedRunnable = new Runnable() {
            public void run() {
                Toast.makeText(PlayOnlineActivity.this.application, R.string.toast_game_not_accepted, 0).show();
                PlayOnlineActivity.this.retryHandler.removeCallbacks(PlayOnlineActivity.this.retryRunnable);
                PlayOnlineActivity.this.startHandler.removeCallbacks(PlayOnlineActivity.this.startRunnable);
                PlayOnlineActivity.this.waitHandler.removeCallbacks(PlayOnlineActivity.this.waitRunnable);
                PlayOnlineActivity.this.refundForOnline();
                PlayOnlineActivity.this.searchNextUser();
            }
        };
        this.endGameHandler = new Handler();
        this.endGameRunnable = new Runnable() {
            public void run() {
                PlayOnlineActivity.this.sendNoResultsReceived();
            }
        };
        if (PreferenceManager.getDefaultSharedPreferences(this).getBoolean(SEARCH_NEXT_USER, true)) {
            searchNextUser();
        } else {
            restoreMatchEventFromSharedPrefs();
            showMatch();
        }
        this.selectedAdType = 0;
        setUpPurchases();
        this.mInterstitialAd = new InterstitialAd(this);
        this.mInterstitialAd.setAdUnitId(MemoryApplicationModel.APP_UNIT_ID);
        requestNewInterstitial();
    }

    private void requestNewInterstitial() {
        this.mInterstitialAd.loadAd(new Builder().build());
    }

    private void refundForOnline() {
        this.application.getLocalDataManager().refundForOnline();
        this.starsCount.setText(String.valueOf(this.application.getLocalDataManager().getLocalUser().money));
    }

    private void searchNextUser() {
        this.application.setOnlineModeStatus(OnlineModeStatus.SEARCHING);
        this.buttonStart.setEnabled(true);
        this.buttonStart.setVisibility(8);
        this.buttonCancel.setVisibility(0);
        hideAcceptedTimer();
        LocalUser localUser = this.application.getLocalDataManager().getLocalUser();
        if (!localUser.unlimitedOnline && localUser.money < 30) {
            if (this.application.getOnShowMoneyDialogListener() != null) {
                this.application.getOnShowMoneyDialogListener().onShowMoneyDialog();
            }
            Log.d("finish", "searchNextUser");
            this.application.setOnlineModeStatus(OnlineModeStatus.NONE);
            clearMatchEventFromSharedPrefs();
            finish();
        }
        replaceFragment(UIState.UI_SEARCH);
        this.currentQueueId = null;
        PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean(SEARCH_NEXT_USER, true).apply();
        NotificationManager.getInstance().showCancelNotifications(getApplicationContext());
        this.application.getService().addToQueue(new BodyUserId(this.application.getLocalDataManager().getLocalUser().objectId)).enqueue(new Callback<ResponseBody>() {
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                System.err.println("response = " + response.toString());
            }

            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (PlayOnlineActivity.this.application.isNetworkAvailable()) {
                    Toast.makeText(PlayOnlineActivity.this, "onFailure " + t.getMessage(), 0).show();
                } else {
                    Toast.makeText(PlayOnlineActivity.this, R.string.internet_warning, 0).show();
                }
            }
        });
        this.retryHandler.postDelayed(this.retryRunnable, 15000);
    }

    private void retryFindMatch() {
        if (this.currentState == UIState.UI_SEARCH) {
            this.application.getService().checkAgainForMatch(new BodyUserId(this.application.getLocalDataManager().getLocalUser().objectId)).enqueue(new Callback<ResponseBody>() {
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    System.err.println("response = " + response.toString());
                }

                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    if (PlayOnlineActivity.this.application.isNetworkAvailable()) {
                        Toast.makeText(PlayOnlineActivity.this, "onFailure " + t.getMessage(), 0).show();
                    } else {
                        Toast.makeText(PlayOnlineActivity.this, R.string.internet_warning, 0).show();
                    }
                }
            });
        }
    }

    protected void onDestroy() {
        Log.d(getClass().getSimpleName(), "onDestroy");
        super.onDestroy();
        destroyPurchases();
        NotificationManager.getInstance().hideNotifications(this);
        this.retryHandler.removeCallbacks(this.retryRunnable);
        this.startHandler.removeCallbacks(this.startRunnable);
        this.waitHandler.removeCallbacks(this.waitRunnable);
        this.rejectedHandler.removeCallbacks(this.rejectedRunnable);
        this.application.removeOnlineEventListener(this);
        NotificationManager.getInstance().removeNotificationListener(this);
        PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean(SEARCH_NEXT_USER, true).apply();
        this.timerHandler.removeCallbacks(this.timerRunnable);
        if (this.application.getOnlineModeStatus() == OnlineModeStatus.STARTED) {
            this.application.setOnlineModeStatus(OnlineModeStatus.FINISHED);
            if (this.currentResultsList == null) {
                this.currentResultsList = new ArrayList();
            }
            while (this.currentResultsList.size() < 2) {
                this.currentResultsList.add(Integer.valueOf(0));
            }
            this.application.getService().submitMatchResults(new BodyOnlineResults(this.application.getLocalDataManager().getLocalUser().objectId, this.currentGameId, this.currentResultsList)).enqueue(new Callback<ResponseBody>() {
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    PlayOnlineActivity.this.application.setOnlineModeStatus(OnlineModeStatus.NONE);
                    PlayOnlineActivity.this.currentQueueId = null;
                }

                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    if (PlayOnlineActivity.this.application.isNetworkAvailable()) {
                        Toast.makeText(PlayOnlineActivity.this, "onFailure " + t.getMessage(), 0).show();
                    } else {
                        Toast.makeText(PlayOnlineActivity.this, R.string.internet_warning, 0).show();
                    }
                }
            });
        }
    }

    @OnClick({2131624142})
    public void cancelSearch(View v) {
        Call<ResponseBody> call;
        if (this.buttonStart.getVisibility() == 0 && !this.buttonStart.isEnabled()) {
            refundForOnline();
        }
        this.buttonCancel.setEnabled(false);
        this.backButton.setEnabled(false);
        if (this.currentState == UIState.UI_SEARCH) {
            MemoryApplicationModel.getInstance().logEvent((Activity) this, MemoryApplicationModel.ANALYTICS_CATEGORY_ONLINE, MemoryApplicationModel.ANALYTICS_EVENT_ONLINE_CANCEL_BEFORE_MATH_CLICKED);
            Answers.getInstance().logCustom(new CustomEvent(MemoryApplicationModel.ANALYTICS_EVENT_ONLINE_CANCEL_BEFORE_MATH_CLICKED));
        } else {
            MemoryApplicationModel.getInstance().logEvent((Activity) this, MemoryApplicationModel.ANALYTICS_CATEGORY_ONLINE, MemoryApplicationModel.ANALYTICS_EVENT_ONLINE_CANCEL_AFTER_MATH_CLICKED);
            Answers.getInstance().logCustom(new CustomEvent(MemoryApplicationModel.ANALYTICS_EVENT_ONLINE_CANCEL_AFTER_MATH_CLICKED));
        }
        if (this.currentState == UIState.UI_SEARCH) {
            call = this.application.getService().removeFromQueue(new BodyUserId(this.application.getLocalDataManager().getLocalUser().objectId));
        } else {
            call = this.application.getService().cancelFoundGame(new BodyOnlineQueueIdUserId(this.currentQueueId, this.application.getLocalDataManager().getLocalUser().objectId));
        }
        call.enqueue(new Callback<ResponseBody>() {
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("finish", "cancelSearch");
            }

            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("finish", "cancelSearch");
            }
        });
        clearMatchEventFromSharedPrefs();
        finish();
        this.application.setOnlineModeStatus(OnlineModeStatus.NONE);
    }

    @OnClick({2131624140})
    public void play(View v) {
        clearMatchEventFromSharedPrefs();
        this.waitHandler.removeCallbacks(this.waitRunnable);
        this.buttonStart.setEnabled(false);
        NotificationManager.getInstance().hideNotifications(this);
        this.application.getService().startGame(new BodyOnlineQueueIdUserId(this.currentQueueId, this.application.getLocalDataManager().getLocalUser().objectId)).enqueue(new Callback<ResponseBody>() {
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (PlayOnlineActivity.this.buttonStart.getVisibility() == 0) {
                    PlayOnlineActivity.this.showAcceptedTimer(R.string.opponent_waiting);
                    PlayOnlineActivity.this.rejectedHandler.postDelayed(PlayOnlineActivity.this.rejectedRunnable, 15000);
                }
                PlayOnlineActivity.this.application.setOnlineModeStatus(OnlineModeStatus.ACCEPTED);
                PlayOnlineActivity.this.application.getLocalDataManager().chargeForOnline();
                PlayOnlineActivity.this.starsCount.setText(String.valueOf(PlayOnlineActivity.this.application.getLocalDataManager().getLocalUser().money));
            }

            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (PlayOnlineActivity.this.application.isNetworkAvailable()) {
                    Toast.makeText(PlayOnlineActivity.this, "onFailure " + t.getMessage(), 0).show();
                } else {
                    Toast.makeText(PlayOnlineActivity.this, R.string.internet_warning, 0).show();
                }
                PlayOnlineActivity.this.buttonStart.setEnabled(true);
            }
        });
    }

    private void showAcceptedTimer(@StringRes int labelRes) {
        this.timerContainer.setVisibility(0);
        this.timerView2.showTimer(15000, false);
        this.timerLabel.setText(labelRes);
    }

    private void hideAcceptedTimer() {
        this.timerContainer.setVisibility(8);
    }

    private void startOnlineGame() {
        if (this.currentGamesList.isEmpty()) {
            Log.d("cancelSearch", "startOnlineGame");
            cancelSearch(null);
        }
        if (this.currentResultsList == null) {
            this.currentResultsList = new ArrayList();
        } else {
            this.currentResultsList.clear();
        }
        this.currentRoundNumber = 0;
        showGames();
    }

    private void startRound() {
        GameInfo gameInfo = Games.get().getGameBiId((String) this.currentGamesList.get(this.currentRoundNumber));
        if (PreferenceManager.getDefaultSharedPreferences(this).getInt(PREF_TUTORIAL_SHOW + gameInfo.getId(), 0) >= 2) {
            startRoundWithoutTutorial(gameInfo);
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(MainMenuActivity.EXTRA_GAME_INFO, gameInfo);
        intent.setClass(this, OnlineGameTutorialActivity.class);
        startActivityForResult(intent, REQUEST_CODE_TUTORIAL);
    }

    private void startRoundWithoutTutorial(GameInfo gameInfo) {
        Intent intent = new Intent();
        intent.setClass(this, Games.get().getGameActivity(gameInfo));
        intent.putExtra(MainMenuActivity.EXTRA_GAME_INFO, gameInfo);
        intent.putExtra(EXTRA_GAME_SEED, this.currentGameSeed);
        intent.putExtra(EXTRA_ONLINE_MODE, true);
        intent.putExtra(EXTRA_ONLINE_GAME_NUMBER, this.currentRoundNumber + 1);
        intent.putExtra(EXTRA_ONLINE_TOTAL_GAMES, this.currentGamesList.size());
        intent.putExtra(EXTRA_ONLINE_PROGRESS_TIMEOUT, this.isTutorialNeeded ? 2000 : PROGRESS_DELAY);
        startActivityForResult(intent, REQUEST_CODE_ONLINE_GAME);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.iabHelper.handleActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_ONLINE_GAME && resultCode == -1) {
            Integer reachedLevel = Integer.valueOf(data.getIntExtra(EXTRA_LEVEL, 0));
            int score = data.getIntExtra(EXTRA_ONLINE_SCORE, 0);
            if (this.currentResultsList == null) {
                this.currentResultsList = new ArrayList();
            }
            this.currentResultsList.add(Integer.valueOf(score));
            this.currentRoundNumber++;
            if (this.currentRoundNumber < this.currentGamesList.size()) {
                sendIntermediateResults(this.currentRoundNumber, score);
                startRound();
                return;
            }
            sendResults();
        } else if (requestCode == REQUEST_CODE_TUTORIAL && resultCode == -1) {
            GameInfo gameInfo = Games.get().getGameBiId((String) this.currentGamesList.get(this.currentRoundNumber));
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            preferences.edit().putInt(PREF_TUTORIAL_SHOW + gameInfo.getId(), preferences.getInt(PREF_TUTORIAL_SHOW + gameInfo.getId(), 0) + 1).apply();
            startRoundWithoutTutorial(gameInfo);
        }
    }

    private void sendIntermediateResults(int roundNumber, int score) {
        this.application.getService().sendRoundResults(new BodyRoundResults(this.application.getLocalDataManager().getLocalUser().objectId, this.currentGameId, roundNumber, score)).enqueue(new Callback<ResponseBody>() {
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("ONLINE", "Sending intermediate results SUCCESS!");
            }

            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("ONLINE", "Sending intermediate results FAILED!");
            }
        });
    }

    private void sendResults() {
        this.application.setOnlineModeStatus(OnlineModeStatus.FINISHED);
        this.player1Results = new ArrayList();
        this.player1Results.addAll(this.currentResultsList);
        this.player1Results.add(Integer.valueOf(getSum(this.currentResultsList)));
        showGameResults();
        resendResult();
    }

    @OnClick({2131624115})
    void backClick(final View v) {
        if (this.buttonNext.getVisibility() == 0) {
            showAdsIfNeeded(2);
        } else if (this.application.getOnlineModeStatus() == OnlineModeStatus.FINISHED || this.application.getOnlineModeStatus() == OnlineModeStatus.STARTED) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage((int) R.string.dialog_lose_bet);
            builder.setNegativeButton((int) R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    PlayOnlineActivity.this.resendResult();
                    dialog.cancel();
                }
            });
            builder.setPositiveButton((int) R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Log.d("cancel search", "dialog");
                    PlayOnlineActivity.this.cancelSearch(v);
                    dialog.dismiss();
                }
            });
            builder.show();
        } else {
            Log.d("cancelSearch", "backClick");
            cancelSearch(v);
        }
    }

    public void onBackPressed() {
        if (this.buttonNext.getVisibility() == 0) {
            showAdsIfNeeded(2);
        } else if (this.application.getOnlineModeStatus() == OnlineModeStatus.FINISHED || this.application.getOnlineModeStatus() == OnlineModeStatus.STARTED) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage((int) R.string.dialog_lose_bet);
            builder.setNegativeButton((int) R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    PlayOnlineActivity.this.resendResult();
                    dialog.cancel();
                }
            });
            builder.setPositiveButton((int) R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Log.d("cancel search", "dialog");
                    PlayOnlineActivity.this.cancelSearch(null);
                    dialog.dismiss();
                }
            });
            builder.show();
        } else if (this.buttonStart.getVisibility() == 0) {
            Log.d("cancelSearch", "onBackPressed");
            cancelSearch(null);
        }
    }

    @OnClick({2131624141})
    public void nextClick(View v) {
        showAdsIfNeeded(1);
    }

    public void onOnlineEventReceived(OnlineEvent event) {
        Log.d(getClass().getSimpleName(), event.getClass().getSimpleName());
        if (event instanceof MatchFoundEvent) {
            if (this.currentState == UIState.UI_SEARCH) {
                this.application.setOnlineModeStatus(OnlineModeStatus.MATCH_FOUND);
                this.currentQueueId = ((MatchFoundEvent) event).getQueueEntryId();
                this.onlineMatchUser = ((MatchFoundEvent) event).getOnlineUserInfo();
                this.myOnlineMatchUser = ((MatchFoundEvent) event).getMyOnlineUserInfo();
                saveMatchEventToSharedPrefs(this.currentQueueId, this.onlineMatchUser, this.myOnlineMatchUser);
                showMatchNotification();
                showMatch();
            }
        } else if (event instanceof UserConfirmedGameEvent) {
            if (this.currentQueueId != null && this.currentQueueId.equals(((UserConfirmedGameEvent) event).getQueueEntryId())) {
                showConfirmed();
            }
        } else if (event instanceof StartGameEvent) {
            StartGameEvent startGameEvent = (StartGameEvent) event;
            this.currentGameId = startGameEvent.getGameId();
            this.currentGameSeed = startGameEvent.getGameSeed();
            this.currentGamesList = startGameEvent.getGamesList();
            MemoryApplicationModel.getInstance().logEvent((Activity) this, MemoryApplicationModel.ANALYTICS_CATEGORY_ONLINE, MemoryApplicationModel.ANALYTICS_EVENT_ONLINE_GAME_STARTED);
            Answers.getInstance().logCustom(new CustomEvent(MemoryApplicationModel.ANALYTICS_EVENT_ONLINE_GAME_STARTED));
            startOnlineGame();
        } else if (event instanceof GameEndedEvent) {
            GameEndedEvent gameEndedEvent = (GameEndedEvent) event;
            if (this.currentGameId != null && this.currentGameId.equals(gameEndedEvent.getGameId())) {
                this.myOnlineMatchUser.setOnlineRating((float) gameEndedEvent.getNewRank());
                this.player1Results = parseGameResult(gameEndedEvent.getResultsUser1());
                this.player2Results = parseGameResult(gameEndedEvent.getResultsUser2());
                int sum1 = getSum(this.player1Results);
                int sum2 = getSum(this.player2Results);
                this.player1Results.add(Integer.valueOf(sum1));
                this.player2Results.add(Integer.valueOf(sum2));
                showGameResults();
                this.buttonNext.setVisibility(0);
                this.buttonsContainer.setVisibility(0);
                this.bottomDivider.setVisibility(0);
                setAppodealListeners();
                showOnlineResultDialog(sum1, sum2, gameEndedEvent);
                this.starsCount.setText(String.valueOf(this.application.getLocalDataManager().getLocalUser().money));
                this.application.setOnlineModeStatus(OnlineModeStatus.NONE);
                this.currentQueueId = null;
                this.currentGameId = null;
            }
        } else if (event instanceof ErrorEvent) {
            if (this.currentState != UIState.UI_SEARCH) {
                if (this.buttonStart.getVisibility() == 0 && !this.buttonStart.isEnabled()) {
                    refundForOnline();
                }
                this.application.setOnlineModeStatus(OnlineModeStatus.NONE);
                Log.d("finish", "ErrorEvent");
                clearMatchEventFromSharedPrefs();
                finish();
            }
        } else if ((event instanceof IntermediateResultsEvent) && this.currentGameId != null && this.currentGameId.equals(((IntermediateResultsEvent) event).getGameId())) {
            this.player2Results = parseGameResult(((IntermediateResultsEvent) event).getResultsUser2());
            showGameResults();
        }
    }

    private void saveMatchEventToSharedPrefs(String currentQueueId, OnlineMatchUser onlineMatchUser, OnlineMatchUser myOnlineMatchUser) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.edit().putString(CURRENT_QUEUE_ID_KEY, currentQueueId).apply();
        prefs.edit().putString(ONLINE_MATCH_USER_KEY, new Gson().toJson((Object) onlineMatchUser)).apply();
        prefs.edit().putString(MY_ONLINE_MATCH_USER_KEY, new Gson().toJson((Object) myOnlineMatchUser)).apply();
        prefs.edit().putBoolean(SEARCH_NEXT_USER, false).apply();
    }

    private void restoreMatchEventFromSharedPrefs() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        this.currentQueueId = prefs.getString(CURRENT_QUEUE_ID_KEY, "");
        this.onlineMatchUser = (OnlineMatchUser) new Gson().fromJson(prefs.getString(ONLINE_MATCH_USER_KEY, ""), OnlineMatchUser.class);
        this.myOnlineMatchUser = (OnlineMatchUser) new Gson().fromJson(prefs.getString(MY_ONLINE_MATCH_USER_KEY, ""), OnlineMatchUser.class);
        prefs.edit().putBoolean(SEARCH_NEXT_USER, true).apply();
    }

    private void clearMatchEventFromSharedPrefs() {
        PreferenceManager.getDefaultSharedPreferences(this).edit().remove(CURRENT_QUEUE_ID_KEY).remove(ONLINE_MATCH_USER_KEY).remove(MY_ONLINE_MATCH_USER_KEY).remove(SEARCH_NEXT_USER).apply();
    }

    private void showOnlineResultDialog(int sum1, int sum2, GameEndedEvent gameEndedEvent) {
        int doodleResId;
        int titleResId;
        int newCoins;
        boolean showFreeOnline = false;
        if (sum1 > sum2) {
            doodleResId = R.drawable.doodle_33;
            titleResId = R.string.online_win;
            newCoins = 25;
            if (this.application.getOnlineGameCount() >= 9) {
                showFreeOnline = true;
            }
        } else if (sum1 < sum2) {
            doodleResId = R.drawable.doodle_42;
            titleResId = R.string.online_lose;
            newCoins = -30;
        } else {
            doodleResId = R.drawable.doodle_26;
            titleResId = R.string.online_draw;
            newCoins = 0;
        }
        LocalUser localUser = this.application.getLocalDataManager().getLocalUser();
        if (localUser.unlimitedOnline) {
            showFreeOnline = false;
        }
        this.starsCount.setText(String.valueOf(localUser.money));
        new OnlineResultDialog(this, doodleResId, titleResId, newCoins, localUser.money, gameEndedEvent.getChangeRankUser1(), gameEndedEvent.getNewRank(), showFreeOnline, this.iabHelper, this).show();
        if (!showFreeOnline && newCoins == 25 && !showProDialog() && !showRemoveAdsDialog() && !showProDiscountDialog() && !localUser.fromDeepLink && !localUser.deepLinkSent && !localUser.vip && !localUser.gamesUnlocked) {
            showSecretGameDialog();
        }
    }

    private boolean showProDialog() {
        if (this.application.getLocalDataManager().getLocalUser().vip) {
            return false;
        }
        int sprintGamesCount = MemoryApplicationModel.getInstance().getLocalDataManager().getGamesSessionCount();
        int onlineGamesCount = MemoryApplicationModel.getInstance().getOnlineGameCount();
        boolean isTimeForProDialog = System.currentTimeMillis() - MemoryApplicationModel.getInstance().getLocalDataManager().getLocalUser().createdAt.getTime() > 86400000;
        boolean wasProDialogShown = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("was_pro_dialog_shown", false);
        boolean isMinGamesPlayed = onlineGamesCount + sprintGamesCount >= 25;
        if (!isTimeForProDialog || wasProDialogShown || !isMinGamesPlayed) {
            return false;
        }
        OfferDialogSelector.showDialog(this, this.iabHelper, this, PurchaseDialogType.PURCHASE_PRO);
        PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean("was_pro_dialog_shown", true).apply();
        return true;
    }

    private boolean showRemoveAdsDialog() {
        if (this.application.getLocalDataManager().getLocalUser().vip || this.application.getLocalDataManager().getLocalUser().adsRemoved) {
            return false;
        }
        int sprintGamesCount = MemoryApplicationModel.getInstance().getLocalDataManager().getGamesSessionCount();
        int onlineGamesCount = MemoryApplicationModel.getInstance().getOnlineGameCount();
        boolean isTimeForProDiscountDialog = System.currentTimeMillis() - MemoryApplicationModel.getInstance().getLocalDataManager().getLocalUser().createdAt.getTime() > 345600000;
        boolean wasVipDialogShown = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("was_remove_ads_dialog_shown", false);
        boolean isMinGamesPlayed = onlineGamesCount + sprintGamesCount >= 35;
        if (!isTimeForProDiscountDialog || wasVipDialogShown || !isMinGamesPlayed) {
            return false;
        }
        OfferDialogSelector.showDialog(this, this.iabHelper, this, PurchaseDialogType.PURCHASE_REMOVE_ADS);
        PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean("was_remove_ads_dialog_shown", true).apply();
        return true;
    }

    private boolean showProDiscountDialog() {
        if (this.application.getLocalDataManager().getLocalUser().vip) {
            return false;
        }
        int sprintGamesCount = MemoryApplicationModel.getInstance().getLocalDataManager().getGamesSessionCount();
        int onlineGamesCount = MemoryApplicationModel.getInstance().getOnlineGameCount();
        boolean isTimeForProDiscountDialog = System.currentTimeMillis() - MemoryApplicationModel.getInstance().getLocalDataManager().getLocalUser().createdAt.getTime() > 604800000;
        boolean wasVipDialogShown = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("was_pro_discount_dialog_shown", false);
        boolean isMinGamesPlayed = onlineGamesCount + sprintGamesCount >= 50;
        if (!isTimeForProDiscountDialog || wasVipDialogShown || !isMinGamesPlayed) {
            return false;
        }
        OfferDialogSelector.showDialog(this, this.iabHelper, this, PurchaseDialogType.PURCHASE_PRO_DISCOUNT);
        PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean("was_pro_discount_dialog_shown", true).apply();
        return true;
    }

    private boolean showSecretGameDialog() {
        int sprintGamesCount = MemoryApplicationModel.getInstance().getLocalDataManager().getGamesSessionCount();
        int onlineGamesCount = MemoryApplicationModel.getInstance().getOnlineGameCount();
        boolean isTimeForSecretDialog = System.currentTimeMillis() - MemoryApplicationModel.getInstance().getLocalDataManager().getLocalUser().createdAt.getTime() > 86400000;
        boolean wasSecretGameDialogShown = PreferenceManager.getDefaultSharedPreferences(this).getBoolean("was_secret_game_dialog_shown", false);
        boolean isMinGamesPlayed = onlineGamesCount + sprintGamesCount >= 1;
        if (!isTimeForSecretDialog || wasSecretGameDialogShown || !isMinGamesPlayed) {
            return false;
        }
        OfferDialogSelector.showDialog(this, this.iabHelper, this, PurchaseDialogType.SECRET_GAME);
        PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean("was_secret_game_dialog_shown", true).apply();
        return true;
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

    public void onCancelClicked() {
        Log.d("cancelSearch", "onCancelClicked");
        cancelSearch(null);
        hideSystemDialogs();
    }

    public void onPlayClicked() {
        play(null);
        hideSystemDialogs();
        bringToForeground();
    }

    public void onViewClicked() {
        hideSystemDialogs();
        bringToForeground();
    }

    private void bringToForeground() {
        Intent newIntent = new Intent(this, PlayOnlineActivity.class);
        newIntent.addFlags(805306368);
        startActivity(newIntent);
    }

    private void hideSystemDialogs() {
        sendBroadcast(new Intent("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
    }

    private void setAppodealListeners() {
        this.mInterstitialAd.setAdListener(new AdListener() {
            public void onAdClosed() {
                PlayOnlineActivity.this.requestNewInterstitial();
                PlayOnlineActivity.this.adsFinished(MemoryApplicationModel.ANALYTICS_EVENT_ADS_ADMOB_WATCHED);
            }
        });
        Appodeal.setInterstitialCallbacks(new InterstitialCallbacks() {
            public void onInterstitialLoaded(boolean isPrecache) {
            }

            public void onInterstitialFailedToLoad() {
            }

            public void onInterstitialShown() {
            }

            public void onInterstitialClicked() {
            }

            public void onInterstitialClosed() {
                PlayOnlineActivity.this.adsFinished(MemoryApplicationModel.ANALYTICS_EVENT_ADS_APPODEAL_WATCHED);
            }
        });
        Appodeal.setRewardedVideoCallbacks(new RewardedVideoCallbacks() {
            public void onRewardedVideoLoaded() {
            }

            public void onRewardedVideoFailedToLoad() {
            }

            public void onRewardedVideoShown() {
            }

            public void onRewardedVideoFinished(int i, String s) {
            }

            public void onRewardedVideoClosed(boolean b) {
                PlayOnlineActivity.this.adsFinished(MemoryApplicationModel.ANALYTICS_EVENT_ADS_APPODEAL_WATCHED);
            }
        });
    }

    private void adsFinished(String ads) {
        OnClickListener onClickListener;
        MemoryApplicationModel.getInstance().logEvent((Activity) this, "Ads", ads);
        Answers.getInstance().logCustom(new CustomEvent(ads));
        switch (this.selectedAdType) {
            case 1:
                onClickListener = this.nextDuelClickListener;
                break;
            case 2:
                onClickListener = this.exitClickListener;
                break;
            default:
                onClickListener = null;
                break;
        }
        this.selectedAdType = 0;
        if (onClickListener != null) {
            onClickListener.onClick(null);
        }
    }

    protected void onSaveInstanceState(Bundle outState) {
        Log.d(getClass().getSimpleName(), "onSaveInstanceState");
        outState.putInt("selectedAdType", this.selectedAdType);
        outState.putSerializable(EXTRA_CURRENT_STATE, this.currentState);
        outState.putParcelable(EXTRA_ONLINE_MATCH_USER, this.onlineMatchUser);
        outState.putParcelable(EXTRA_MY_ONLINE_MATCH_USER, this.myOnlineMatchUser);
        outState.putString(EXTRA_CURRENT_QUEUE_ID, this.currentQueueId);
        outState.putString(EXTRA_CURRENT_GAME_ID, this.currentGameId);
        outState.putLong(EXTRA_CURRENT_GAME_SEED, this.currentGameSeed);
        outState.putStringArrayList(EXTRA_CURRENT_GAMES_LIST, (ArrayList) this.currentGamesList);
        outState.putInt(EXTRA_CURRENT_ROUND_NUMBER, this.currentRoundNumber);
        outState.putIntegerArrayList(EXTRA_CURRENT_RESULTS_LIST, this.currentResultsList);
        outState.putIntegerArrayList(EXTRA_PLAYER_1_RESULTS, (ArrayList) this.player1Results);
        outState.putIntegerArrayList(EXTRA_PLAYER_2_RESULTS, (ArrayList) this.player2Results);
        outState.putBoolean(EXTRA_IS_TUTORIAL_NEEDED, this.isTutorialNeeded);
        super.onSaveInstanceState(outState);
    }

    private void showAdsIfNeeded(int type) {
        OnClickListener onClickListener;
        this.selectedAdType = 0;
        switch (type) {
            case 1:
                onClickListener = this.nextDuelClickListener;
                break;
            case 2:
                onClickListener = this.exitClickListener;
                break;
            default:
                return;
        }
        LocalUser localUser = MemoryApplicationModel.getInstance().getLocalDataManager().getLocalUser();
        if (localUser.vip || localUser.adsRemoved) {
            onClickListener.onClick(null);
            return;
        }
        int firstCheckAds;
        int secondCheckAds;
        switch (type) {
            case 1:
                SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
                int countBeforeAds = sharedPreferences.getInt(PREF_COUNT_BEFORE_AD, 10) - 1;
                if (countBeforeAds <= 0) {
                    firstCheckAds = 128;
                    secondCheckAds = 1;
                    countBeforeAds = 10;
                } else {
                    firstCheckAds = 1;
                    secondCheckAds = 0;
                }
                sharedPreferences.edit().putInt(PREF_COUNT_BEFORE_AD, countBeforeAds).apply();
                break;
            case 2:
                firstCheckAds = 1;
                secondCheckAds = 0;
                break;
            default:
                return;
        }
        if (Appodeal.isLoaded(firstCheckAds)) {
            MemoryApplicationModel.getInstance().logEvent((Activity) this, MemoryApplicationModel.ANALYTICS_CATEGORY_ONLINE, MemoryApplicationModel.ANALYTICS_EVENT_ONLINE_ADS_OPENED);
            Answers.getInstance().logCustom(new CustomEvent(MemoryApplicationModel.ANALYTICS_EVENT_ONLINE_ADS_OPENED));
            Appodeal.show(this, firstCheckAds);
            this.selectedAdType = type;
        } else if (secondCheckAds != 0 && Appodeal.isLoaded(secondCheckAds)) {
            MemoryApplicationModel.getInstance().logEvent((Activity) this, MemoryApplicationModel.ANALYTICS_CATEGORY_ONLINE, MemoryApplicationModel.ANALYTICS_EVENT_ONLINE_ADS_OPENED);
            Answers.getInstance().logCustom(new CustomEvent(MemoryApplicationModel.ANALYTICS_EVENT_ONLINE_ADS_OPENED));
            Appodeal.show(this, secondCheckAds);
            this.selectedAdType = type;
        } else if (this.mInterstitialAd.isLoaded()) {
            MemoryApplicationModel.getInstance().logEvent((Activity) this, MemoryApplicationModel.ANALYTICS_CATEGORY_ONLINE, MemoryApplicationModel.ANALYTICS_EVENT_ONLINE_ADS_OPENED);
            Answers.getInstance().logCustom(new CustomEvent(MemoryApplicationModel.ANALYTICS_EVENT_ONLINE_ADS_OPENED));
            this.mInterstitialAd.show();
            this.selectedAdType = type;
        } else {
            onClickListener.onClick(null);
        }
    }

    private void setUpPurchases() {
        this.iabHelper = new IabHelper(this, MemoryApplicationModel.BASE_64_ENCODED_PUBLIC_KEY);
        this.isSetupFinished = false;
        this.iabHelper.startSetup(new OnIabSetupFinishedListener() {
            public void onIabSetupFinished(IabResult result) {
                PlayOnlineActivity.this.isSetupFinished = result.isSuccess();
            }
        });
    }

    private void destroyPurchases() {
        if (this.iabHelper != null) {
            try {
                this.iabHelper.dispose();
            } catch (IabAsyncInProgressException e) {
            } catch (IllegalArgumentException e2) {
            }
        }
        this.iabHelper = null;
    }

    public boolean isIabSetupFinished() {
        return this.isSetupFinished;
    }

    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void resendResult() {
        this.application.getService().submitMatchResults(new BodyOnlineResults(this.application.getLocalDataManager().getLocalUser().objectId, this.currentGameId, this.currentResultsList)).enqueue(new Callback<ResponseBody>() {
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                PlayOnlineActivity.this.player1Results = new ArrayList();
                PlayOnlineActivity.this.player1Results.addAll(PlayOnlineActivity.this.currentResultsList);
                PlayOnlineActivity.this.player1Results.add(Integer.valueOf(PlayOnlineActivity.this.getSum(PlayOnlineActivity.this.currentResultsList)));
                if (PlayOnlineActivity.this.application.getCurrentOpponentIntermediateResults() != null) {
                    PlayOnlineActivity.this.player2Results = PlayOnlineActivity.this.parseGameResult(PlayOnlineActivity.this.application.getCurrentOpponentIntermediateResults().getResultsUser2());
                }
                PlayOnlineActivity.this.showGameResults();
                PlayOnlineActivity.this.endGameHandler.removeCallbacks(PlayOnlineActivity.this.endGameRunnable);
                PlayOnlineActivity.this.endGameHandler.postDelayed(PlayOnlineActivity.this.endGameRunnable, 24000);
            }

            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (!PlayOnlineActivity.this.isFinishing()) {
                    if (PlayOnlineActivity.this.application.isNetworkAvailable()) {
                        Toast.makeText(PlayOnlineActivity.this, "onFailure " + t.getMessage(), 0).show();
                    } else {
                        Toast.makeText(PlayOnlineActivity.this, R.string.internet_warning, 0).show();
                    }
                    PlayOnlineActivity.this.timerHandler.postDelayed(PlayOnlineActivity.this.timerRunnable, 2000);
                }
            }
        });
    }

    private void sendNoResultsReceived() {
        if (this.currentGameId != null) {
            this.application.getService().noResultsReceived(new BodyNoResults(this.application.getLocalDataManager().getLocalUser().objectId, this.currentGameId)).enqueue(new Callback<ResponseBody>() {
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    Log.d(PlayOnlineActivity.class.getSimpleName(), "no results received sent.");
                }

                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    if (PlayOnlineActivity.this.application.isNetworkAvailable()) {
                        Toast.makeText(PlayOnlineActivity.this, "onFailure " + t.getMessage(), 0).show();
                    } else {
                        Toast.makeText(PlayOnlineActivity.this, R.string.internet_warning, 0).show();
                    }
                }
            });
        }
    }

    private void replaceFragment(UIState state) {
        if (!isFinishing()) {
            this.currentState = state;
            Fragment fragment = null;
            switch (state) {
                case UI_SEARCH:
                    fragment = new SearchFragment();
                    break;
                case UI_MATCH:
                    fragment = MatchFoundFragment.getFragment(this.myOnlineMatchUser, this.onlineMatchUser);
                    break;
                case UI_ACCEPTED:
                    showAcceptedTimer(R.string.opponent_waiting);
                    break;
                case UI_CONFIRMED:
                    showAcceptedTimer(R.string.opponent_is_ready);
                    break;
                case UI_GAME_LIST:
                    fragment = GameListFragment.getFragment(this.currentGamesList, this.myOnlineMatchUser, this.onlineMatchUser, this.isTutorialNeeded ? 2000 : START_TIMEOUT);
                    break;
                case UI_RESULTS:
                    fragment = getResultFragment();
                    break;
            }
            if (fragment != null) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment, "fragment-" + state.toString()).commitAllowingStateLoss();
            }
        }
    }

    private GameResultsFragment getResultFragment() {
        GameResultsFragment fragment = (GameResultsFragment) getSupportFragmentManager().findFragmentByTag("fragment-" + UIState.UI_RESULTS.toString());
        if (fragment == null) {
            return GameResultsFragment.getFragment(this.currentGamesList, this.myOnlineMatchUser, this.onlineMatchUser);
        }
        return fragment;
    }

    private void showMatch() {
        this.retryHandler.removeCallbacks(this.retryRunnable);
        this.buttonCancel.setVisibility(8);
        this.buttonStart.setVisibility(0);
        hideAcceptedTimer();
        replaceFragment(UIState.UI_MATCH);
    }

    private void showConfirmed() {
        replaceFragment(UIState.UI_CONFIRMED);
        this.waitHandler.postDelayed(this.waitRunnable, 15000);
    }

    private void showGames() {
        this.buttonStart.setEnabled(true);
        this.buttonsContainer.setVisibility(8);
        this.bottomDivider.setVisibility(8);
        this.buttonStart.setVisibility(8);
        hideAcceptedTimer();
        if (this.currentGamesList != null) {
            for (String gameId : this.currentGamesList) {
                if (PreferenceManager.getDefaultSharedPreferences(this).getInt(PREF_TUTORIAL_SHOW + Games.get().getGameBiId(gameId).getId(), 0) < 2) {
                    this.isTutorialNeeded = true;
                    break;
                }
            }
        }
        replaceFragment(UIState.UI_GAME_LIST);
        this.waitHandler.removeCallbacks(this.waitRunnable);
        this.rejectedHandler.removeCallbacks(this.rejectedRunnable);
        this.startHandler.postDelayed(this.startRunnable, this.isTutorialNeeded ? 2000 : 5000);
    }

    private void showGameResults() {
        hideAcceptedTimer();
        if (!isFinishing() && this.currentGamesList != null) {
            replaceFragment(UIState.UI_RESULTS);
            getSupportFragmentManager().executePendingTransactions();
            GameResultsFragment fragment = getResultFragment();
            fragment.updateUserRank(this.myOnlineMatchUser.getOnlineRating());
            if (this.player1Results != null) {
                fragment.setPlayer1Results(this.player1Results);
            }
            if (this.player2Results != null) {
                fragment.setPlayer2Results(this.player2Results);
            }
            fragment.notifyDataSetChanged();
        }
    }

    private void restoreActivity(Bundle savedInstanceState) {
        Log.d(getClass().getSimpleName(), "restoreActivity");
        this.currentState = (UIState) savedInstanceState.getSerializable(EXTRA_CURRENT_STATE);
        this.onlineMatchUser = (OnlineMatchUser) savedInstanceState.getParcelable(EXTRA_ONLINE_MATCH_USER);
        this.myOnlineMatchUser = (OnlineMatchUser) savedInstanceState.getParcelable(EXTRA_MY_ONLINE_MATCH_USER);
        this.currentQueueId = savedInstanceState.getString(EXTRA_CURRENT_QUEUE_ID);
        this.currentGameId = savedInstanceState.getString(EXTRA_CURRENT_GAME_ID);
        this.currentGameSeed = savedInstanceState.getLong(EXTRA_CURRENT_GAME_SEED);
        this.currentGamesList = savedInstanceState.getStringArrayList(EXTRA_CURRENT_GAMES_LIST);
        this.currentRoundNumber = savedInstanceState.getInt(EXTRA_CURRENT_ROUND_NUMBER);
        this.currentResultsList = savedInstanceState.getIntegerArrayList(EXTRA_CURRENT_RESULTS_LIST);
        this.player1Results = savedInstanceState.getIntegerArrayList(EXTRA_PLAYER_1_RESULTS);
        this.player2Results = savedInstanceState.getIntegerArrayList(EXTRA_PLAYER_2_RESULTS);
        this.isTutorialNeeded = savedInstanceState.getBoolean(EXTRA_IS_TUTORIAL_NEEDED);
        switch (this.currentState) {
            case UI_SEARCH:
                searchNextUser();
                return;
            case UI_MATCH:
                showMatch();
                return;
            case UI_GAME_LIST:
            case UI_RESULTS:
                showGameResults();
                this.buttonCancel.setVisibility(8);
                this.buttonNext.setVisibility(0);
                this.buttonsContainer.setVisibility(0);
                this.bottomDivider.setVisibility(0);
                return;
            default:
                return;
        }
    }

    private void showMatchNotification() {
        NotificationManager.getInstance().showPlayNotifications(this.application.getApplicationContext(), this.onlineMatchUser.getDisplayName());
        int size = getResources().getDimensionPixelSize(R.dimen.avatar_size);
        this.profilePicture2Target = new Target() {
            public void onBitmapLoaded(Bitmap bitmap, LoadedFrom from) {
                NotificationManager.getInstance().showPlayWithPhotoNotifications(PlayOnlineActivity.this.application.getApplicationContext(), PlayOnlineActivity.this.onlineMatchUser.getDisplayName(), bitmap);
            }

            public void onBitmapFailed(Drawable errorDrawable) {
            }

            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        };
        Picasso.with(this).load(this.onlineMatchUser.getPhotoUrl()).resize(size, size).centerCrop().noFade().into(this.profilePicture2Target);
    }
}

package com.cube.memorygames.api.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;
import com.crashlytics.android.answers.Answers;
import com.crashlytics.android.answers.CustomEvent;
import com.cube.memorygames.Games;
import com.cube.memorygames.MemoryApplicationModel;
import com.cube.memorygames.api.local.challenge.ChallengeDbGame;
import com.cube.memorygames.api.local.challenge.ChallengeJsonGame;
import com.cube.memorygames.api.local.challenge.ChallengeJsonLevel;
import com.cube.memorygames.api.local.model.LocalCoinsTransaction;
import com.cube.memorygames.api.local.model.LocalGameSession;
import com.cube.memorygames.api.local.model.LocalGameStats;
import com.cube.memorygames.api.local.model.LocalUser;
import com.cube.memorygames.api.local.model.LocalWorkoutGame;
import com.cube.memorygames.api.local.workout.DayInfo;
import com.cube.memorygames.api.local.workout.DbLevelInfo;
import com.cube.memorygames.api.local.workout.LevelInfo;
import com.cube.memorygames.api.network.SyncDataAsyncTask;
import com.cube.memorygames.model.GameInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.memory.brain.training.games.R;
import com.mopub.volley.DefaultRetryPolicy;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class LocalDataManager {
    private static final int DAYS_IN_WEAK = 7;
    private static final int MAX_CHALLENGE_LEVEL = 10;
    private static final String PREFIX_IS_PLAYED = "is_played_challenge_";
    private static final String PREF_LEVEL_NUMBER = "challengeLevelNumberNew";
    private static final String PREF_NEED_CHALLENGE_SYNCHRONIZATION = "needChallengeSynchronization";
    public static final String TYPE_BUY = "BuyCoins";
    public static final String TYPE_BUY_GAME = "UnlockGame-";
    public static final String TYPE_BUY_LEVEL = "BuyLevel";
    public static final String TYPE_DRAW_LEVEL = "DrawOnline";
    public static final String TYPE_FACEBOOK = "Facebook";
    public static final String TYPE_FIRST_START = "FirstStart";
    public static final String TYPE_GAME_EARNED = "GameEarned";
    public static final String TYPE_PRO = "BecomePro";
    public static final String TYPE_SHARE_DIALOG_FB = "ShareDialogFb";
    public static final String TYPE_SHARE_DIALOG_VIDEO = "ShareDialogVideo";
    public static final String TYPE_START_ONLINE = "StartOnline";
    public static final String TYPE_USERNAME = "UserName";
    public static final String TYPE_WIN_ONLINE = "WinOnline";
    public static final int WORKOUT_GAME_COUNT = 4;
    private List<ChallengeJsonGame> challengeJsonGames;
    private Integer currentLevelNumber;
    private LocalUser localUser = ((LocalUser) new Select().from(LocalUser.class).executeSingle());
    private Double totalChallengeRating;
    private Integer totalChallengeScore;
    private Integer totalChallengeStars;

    public LocalDataManager() {
        if (this.localUser == null) {
            this.localUser = new LocalUser();
            this.localUser.createdAt = new Date();
            this.localUser.updatedAt = new Date();
            this.localUser.save();
            addCoinsTransaction(TYPE_FIRST_START, 60);
        }
    }

    public void addGameSession(Context context, LocalGameSession localGameSession) {
        if (localGameSession.createdAt == null) {
            localGameSession.createdAt = new Date();
        }
        if (localGameSession.updatedAt == null) {
            localGameSession.updatedAt = new Date();
        }
        localGameSession.save();
        LocalGameStats localGameStats = (LocalGameStats) new Select().from(LocalGameStats.class).where("game = ?", localGameSession.game).executeSingle();
        if (localGameStats == null) {
            localGameStats = new LocalGameStats();
            localGameStats.game = localGameSession.game;
            localGameStats.avgLevel = 0.0f;
            localGameStats.maxLevel = 0;
        }
        if (localGameSession.endLevel > localGameStats.maxLevel) {
            localGameStats.maxLevel = localGameSession.endLevel;
        }
        List<LocalGameSession> localGameSessions = new Select().from(LocalGameSession.class).where("game = ?", localGameSession.game).orderBy("createdAt DESC").limit(7).execute();
        float total = 0.0f;
        for (LocalGameSession session : localGameSessions) {
            total += (float) session.endLevel;
        }
        localGameStats.avgLevel = total / ((float) localGameSessions.size());
        localGameStats.save();
        float rating = 0.0f;
        for (LocalGameStats stat : new Select().from(LocalGameStats.class).execute()) {
            rating += stat.avgLevel;
        }
        rating *= 2.0f;
        if (((double) this.localUser.challengeRating) < getTotalChallengeRating(context)) {
            this.localUser.challengeRating = (float) getTotalChallengeRating(context);
        }
        this.localUser.rating = this.localUser.challengeRating + rating;
        this.localUser.save();
    }

    public List<LocalGameSession> getLastGames(String gameId) {
        return new Select().from(LocalGameSession.class).where("game = ?", gameId).orderBy("createdAt DESC").limit(7).execute();
    }

    public Map<String, LocalGameStats> getLocalGameStats() {
        List<LocalGameStats> stats = new Select().from(LocalGameStats.class).execute();
        Map<String, LocalGameStats> result = new HashMap();
        for (LocalGameStats stat : stats) {
            result.put(stat.game, stat);
        }
        return result;
    }

    public void addCoinsTransaction(String item, int amount) {
        addCoinsTransaction(item, amount, null);
    }

    public void addCoinsTransaction(String item, int amount, String unlockedContent) {
        if (!(item.equals(TYPE_GAME_EARNED) || item.equals(TYPE_BUY_LEVEL))) {
            LocalCoinsTransaction coinsTransaction = new LocalCoinsTransaction();
            coinsTransaction.item = item;
            coinsTransaction.amount = amount;
            coinsTransaction.createdAt = new Date();
            coinsTransaction.updatedAt = new Date();
            if (!TextUtils.isEmpty(unlockedContent)) {
                this.localUser.addUnlockedContent(unlockedContent);
            }
            coinsTransaction.save();
            new SyncDataAsyncTask(this).execute(new Void[0]);
        }
        LocalUser localUser = this.localUser;
        localUser.money += amount;
        this.localUser.save();
    }

    public LocalUser getLocalUser() {
        return this.localUser;
    }

    public void setDisplayName(String displayName) {
        this.localUser.displayName = displayName;
        this.localUser.updatedAt = new Date();
        this.localUser.save();
    }

    public void setFbId(String facebookId) {
        this.localUser.facebookId = facebookId;
        this.localUser.updatedAt = new Date();
        this.localUser.save();
    }

    public List<LocalGameSession> getNewGameSessions() {
        return new Select().from(LocalGameSession.class).where("uploaded = ?", Boolean.valueOf(false)).execute();
    }

    public List<LocalCoinsTransaction> getNewLocalCoinsTransaction() {
        return new Select().from(LocalCoinsTransaction.class).where("uploaded = ?", Boolean.valueOf(false)).execute();
    }

    public void chargeForOnline() {
        if (!getLocalUser().unlimitedOnline) {
            addCoinsTransaction(TYPE_START_ONLINE, -30);
        }
    }

    public void refundForOnline() {
        if (!getLocalUser().unlimitedOnline) {
            addCoinsTransaction(TYPE_START_ONLINE, 30);
        }
    }

    public int getGamesSessionCount() {
        return new Select().from(LocalGameSession.class).count();
    }

    public void resetWorkoutDay() {
        String date = getCurrentDate();
        new Delete().from(LocalWorkoutGame.class).where("date = ?", date).execute();
    }

    public void addWorkoutResult(String finishedGameId, int levelNumber) {
        Object gameIds;
        Gson gson = new Gson();
        LocalWorkoutGame localWorkoutGame = (LocalWorkoutGame) new Select().from(LocalWorkoutGame.class).where("date = ?", getCurrentDate()).executeSingle();
        if (localWorkoutGame != null) {
            gameIds = (List) gson.fromJson(localWorkoutGame.games, new TypeToken<List<DbLevelInfo>>() {
            }.getType());
        } else {
            List<GameInfo> gameInfoList = new ArrayList(Games.get().getSprintGames());
            gameInfoList.remove(Games.get().getGameBiId(Games.SMART_PROMO_GAME_ID));
            gameInfoList.remove(Games.get().getGameBiId(Games.UNLOCK_ALL_GAMES_ID));
            Collections.shuffle(gameInfoList);
            gameInfoList = gameInfoList.subList(0, 4);
            gameIds = new ArrayList();
            for (GameInfo gameInfo : gameInfoList) {
                gameIds.add(new DbLevelInfo(gameInfo.getId(), 0));
            }
            localWorkoutGame = new LocalWorkoutGame();
            localWorkoutGame.date = date;
            localWorkoutGame.games = gson.toJson(gameIds);
            localWorkoutGame.save();
        }
        int i = 0;
        while (i < gameIds.size()) {
            DbLevelInfo gameData = (DbLevelInfo) gameIds.get(i);
            if (!gameData.getGameId().equals(finishedGameId)) {
                i++;
            } else if (levelNumber > gameData.getLevel()) {
                gameData.setLevel(levelNumber);
                localWorkoutGame.games = gson.toJson(gameIds);
                localWorkoutGame.save();
                return;
            } else {
                return;
            }
        }
    }

    public List<LevelInfo> getWorkoutGames() {
        Gson gson = new Gson();
        LocalWorkoutGame localWorkoutGame = (LocalWorkoutGame) new Select().from(LocalWorkoutGame.class).where("date = ?", getCurrentDate()).executeSingle();
        if (localWorkoutGame != null) {
            List<DbLevelInfo> gameIds = (List) gson.fromJson(localWorkoutGame.games, new TypeToken<List<DbLevelInfo>>() {
            }.getType());
        } else {
            List<GameInfo> gameInfoList = new ArrayList(Games.get().getSprintGames());
            gameInfoList.remove(Games.get().getGameBiId(Games.SMART_PROMO_GAME_ID));
            gameInfoList.remove(Games.get().getGameBiId(Games.UNLOCK_ALL_GAMES_ID));
            Collections.shuffle(gameInfoList);
            gameInfoList = gameInfoList.subList(0, 4);
            Object gameIds2 = new ArrayList();
            for (GameInfo gameInfo : gameInfoList) {
                gameIds2.add(new DbLevelInfo(gameInfo.getId(), 0));
            }
            localWorkoutGame = new LocalWorkoutGame();
            localWorkoutGame.date = date;
            localWorkoutGame.games = gson.toJson(gameIds2);
            localWorkoutGame.save();
        }
        List<LevelInfo> result = new ArrayList();
        for (DbLevelInfo gameData : gameIds) {
            result.add(new LevelInfo(Games.get().getGameBiId(gameData.getGameId()), gameData.getLevel()));
        }
        return result;
    }

    public List<DayInfo> getCurrentWeekGames() {
        Gson gson = new Gson();
        GregorianCalendar calendar = new GregorianCalendar();
        while (calendar.get(7) != 2) {
            calendar.add(7, -1);
        }
        String query = "";
        String[] dates = new String[7];
        List<DayInfo> result = new ArrayList();
        String[] weekdays = new DateFormatSymbols().getShortWeekdays();
        for (int i = 0; i < dates.length; i++) {
            query = query + "date = ?";
            dates[i] = getFormattedDate(calendar.getTime());
            DayInfo dayInfo = new DayInfo();
            dayInfo.setDate(dates[i]);
            dayInfo.setDayOfWeek(weekdays[calendar.get(7)]);
            result.add(dayInfo);
            calendar.add(7, 1);
            if (i < dates.length - 1) {
                query = query + " or ";
            }
        }
        List<LocalWorkoutGame> localWorkoutGames = new Select().from(LocalWorkoutGame.class).where(query, dates).execute();
        if (localWorkoutGames == null) {
            localWorkoutGames = new ArrayList();
        }
        for (DayInfo dayInfo2 : result) {
            for (LocalWorkoutGame localWorkoutGame : localWorkoutGames) {
                if (dayInfo2.getDate().equals(localWorkoutGame.date)) {
                    List<DbLevelInfo> dbLevelInfoList = (List) gson.fromJson(localWorkoutGame.games, new TypeToken<List<DbLevelInfo>>() {
                    }.getType());
                    if (dbLevelInfoList == null) {
                        dbLevelInfoList = new ArrayList();
                    }
                    dayInfo2.setDayGames(dbLevelInfoList);
                }
            }
        }
        return result;
    }

    public float getWorkoutRating() {
        Gson gson = new Gson();
        List<LocalWorkoutGame> localWorkoutGames = new Select().from(LocalWorkoutGame.class).orderBy("date").execute();
        if (localWorkoutGames == null) {
            localWorkoutGames = new ArrayList();
        }
        Map<String, List<Integer>> totalResults = new HashMap();
        for (LocalWorkoutGame localWorkoutGame : localWorkoutGames) {
            List<DbLevelInfo> dbLevelInfoList = (List) gson.fromJson(localWorkoutGame.games, new TypeToken<List<DbLevelInfo>>() {
            }.getType());
            if (dbLevelInfoList == null) {
                dbLevelInfoList = new ArrayList();
            }
            for (DbLevelInfo levelInfo : dbLevelInfoList) {
                List<Integer> gameResults = (List) totalResults.get(levelInfo.getGameId());
                if (gameResults == null) {
                    gameResults = new ArrayList();
                    totalResults.put(levelInfo.getGameId(), gameResults);
                }
                if (levelInfo.getLevel() > 0) {
                    gameResults.add(Integer.valueOf(levelInfo.getLevel()));
                }
            }
        }
        float rating = 0.0f;
        for (GameInfo gameInfo : Games.get().getSprintGames()) {
            gameResults = (List) totalResults.get(gameInfo.getId());
            if (gameResults != null) {
                gameResults = gameResults.subList(Math.max(gameResults.size() - 3, 0), gameResults.size());
                if (!gameResults.isEmpty()) {
                    float levelRating = 0.0f;
                    for (Integer integer : gameResults) {
                        levelRating += (float) integer.intValue();
                    }
                    rating += 2.0f * (levelRating / ((float) gameResults.size()));
                }
            }
        }
        return rating;
    }

    public float getAverageWorkoutLevel(String finishedGameId) {
        Gson gson = new Gson();
        List<LocalWorkoutGame> localWorkoutGames = new Select().from(LocalWorkoutGame.class).orderBy("date").execute();
        if (localWorkoutGames == null) {
            localWorkoutGames = new ArrayList();
        }
        List<Integer> gameResults = new ArrayList();
        for (LocalWorkoutGame localWorkoutGame : localWorkoutGames) {
            List<DbLevelInfo> dbLevelInfoList = (List) gson.fromJson(localWorkoutGame.games, new TypeToken<List<DbLevelInfo>>() {
            }.getType());
            if (dbLevelInfoList == null) {
                dbLevelInfoList = new ArrayList();
            }
            for (DbLevelInfo levelInfo : dbLevelInfoList) {
                if (finishedGameId.equals(levelInfo.getGameId()) && levelInfo.getLevel() > 0) {
                    gameResults.add(Integer.valueOf(levelInfo.getLevel()));
                }
            }
        }
        if (gameResults.isEmpty()) {
            return DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        }
        float levelRating = 0.0f;
        for (Integer integer : gameResults) {
            levelRating += (float) integer.intValue();
        }
        return levelRating / ((float) gameResults.size());
    }

    public boolean isWorkoutGameGenerated() {
        if (((LocalWorkoutGame) new Select().from(LocalWorkoutGame.class).where("date = ?", getCurrentDate()).executeSingle()) != null) {
            return true;
        }
        return false;
    }

    private String getCurrentDate() {
        return getFormattedDate(new Date());
    }

    private String getFormattedDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(date);
    }

    public int getChallengeLevelNumber(Context context) {
        if (this.currentLevelNumber == null) {
            this.currentLevelNumber = Integer.valueOf(PreferenceManager.getDefaultSharedPreferences(context).getInt(PREF_LEVEL_NUMBER, 0));
        }
        if (this.currentLevelNumber.intValue() > 10) {
            this.currentLevelNumber = Integer.valueOf(10);
            PreferenceManager.getDefaultSharedPreferences(context).edit().putInt(PREF_LEVEL_NUMBER, this.currentLevelNumber.intValue()).apply();
        }
        return this.currentLevelNumber.intValue();
    }

    public int prevChallengeLevelNumber(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (this.currentLevelNumber == null) {
            this.currentLevelNumber = Integer.valueOf(preferences.getInt(PREF_LEVEL_NUMBER, 0));
        }
        Integer num = this.currentLevelNumber;
        this.currentLevelNumber = Integer.valueOf(this.currentLevelNumber.intValue() - 1);
        preferences.edit().putInt(PREF_LEVEL_NUMBER, this.currentLevelNumber.intValue()).apply();
        resetInformation();
        MemoryApplicationModel.getInstance().logEvent("", MemoryApplicationModel.ANALYTICS_CATEGORY_CHALLENGE, MemoryApplicationModel.ANALYTICS_EVENT_CHALLENGE_PREV_LEVEL + this.currentLevelNumber);
        Answers.getInstance().logCustom((CustomEvent) new CustomEvent("Challenge PREV Level ").putCustomAttribute("level", this.currentLevelNumber));
        return this.currentLevelNumber.intValue();
    }

    public int nextChallengeLevelNumber(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (this.currentLevelNumber == null) {
            this.currentLevelNumber = Integer.valueOf(preferences.getInt(PREF_LEVEL_NUMBER, 0));
        }
        Integer num = this.currentLevelNumber;
        this.currentLevelNumber = Integer.valueOf(this.currentLevelNumber.intValue() + 1);
        if (this.currentLevelNumber.intValue() > 10) {
            this.currentLevelNumber = Integer.valueOf(10);
        }
        preferences.edit().putInt(PREF_LEVEL_NUMBER, this.currentLevelNumber.intValue()).apply();
        resetInformation();
        MemoryApplicationModel.getInstance().logEvent("", MemoryApplicationModel.ANALYTICS_CATEGORY_CHALLENGE, "New Level " + this.currentLevelNumber);
        Answers.getInstance().logCustom((CustomEvent) new CustomEvent("Challenge New Level ").putCustomAttribute("level", this.currentLevelNumber));
        return this.currentLevelNumber.intValue();
    }

    public String getCurrentChallengeLevelName(Context context) {
        return context.getString(R.string.level_number_prefix) + " " + (getChallengeLevelNumber(context) + 1);
    }

    public String getNextChallengeLevelName(Context context) {
        int levelNumber = getChallengeLevelNumber(context);
        if (levelNumber >= 10) {
            return null;
        }
        return context.getString(R.string.level_number_prefix) + " " + (levelNumber + 2);
    }

    public int addGameAndGetStars(Context context, int levelNumber, String gameId, int score, int goal, int level) {
        ChallengeDbGame challengeDbGame = (ChallengeDbGame) new Select().from(ChallengeDbGame.class).where("levelNumber = ?", Integer.valueOf(levelNumber)).executeSingle();
        if (challengeDbGame == null) {
            challengeDbGame = new ChallengeDbGame();
            challengeDbGame.createdAt = new Date();
            challengeDbGame.levelNumber = levelNumber;
        } else if (challengeDbGame.goal == goal && challengeDbGame.score >= score) {
            return getStars(score, goal, gameId, level);
        }
        challengeDbGame.gameId = gameId;
        challengeDbGame.updatedAt = new Date();
        challengeDbGame.score = score;
        challengeDbGame.goal = goal;
        challengeDbGame.save();
        resetInformation();
        setNeedChallengeSynchronization(true);
        if (this.localUser.challengeRating < ((float) getTotalChallengeRating(context))) {
            float sprintRating = this.localUser.rating - this.localUser.challengeRating;
            this.localUser.challengeRating = (float) getTotalChallengeRating(context);
            this.localUser.rating = this.localUser.challengeRating + sprintRating;
        }
        this.localUser.save();
        return getStars(score, goal, gameId, level);
    }

    public ChallengeJsonGame getGame(Context context, int levelNumber) {
        updateInformation(context);
        for (ChallengeJsonGame challengeJsonGame : this.challengeJsonGames) {
            if (levelNumber == challengeJsonGame.getLevelNumber()) {
                return challengeJsonGame;
            }
        }
        return null;
    }

    private void resetInformation() {
        this.challengeJsonGames = null;
        this.totalChallengeScore = null;
        this.totalChallengeStars = null;
        this.totalChallengeRating = null;
    }

    public int getTotalChallengeScore(Context context) {
        updateInformation(context);
        return this.totalChallengeScore.intValue();
    }

    public int getTotalChallengeStars(Context context) {
        updateInformation(context);
        return this.totalChallengeStars.intValue();
    }

    public double getTotalChallengeRating(Context context) {
        updateInformation(context);
        return this.totalChallengeRating.doubleValue();
    }

    public List<ChallengeJsonGame> getChallengeGames(Context context) {
        updateInformation(context);
        return this.challengeJsonGames;
    }

    private void updateInformation(Context context) {
        IOException ex;
        TypeToken<List<ChallengeJsonGame>> anonymousClass6;
        List<ChallengeJsonGame> challengeJsonGames;
        TypeToken<List<ChallengeJsonLevel>> anonymousClass7;
        List<ChallengeJsonLevel> challengeJsonLevels;
        List<ChallengeDbGame> savedGames;
        Map<Integer, ChallengeDbGame> map;
        ChallengeDbGame challengeDbGame;
        int totalScore;
        int totalStars;
        double totalRating;
        int i;
        ChallengeJsonGame challengeJsonGame;
        int stars;
        double rating;
        int levelNumber;
        int startGame;
        if (this.challengeJsonGames == null || this.totalChallengeScore == null || this.totalChallengeStars == null || this.totalChallengeRating == null) {
            String jsonGames = null;
            String jsonLevels = null;
            try {
                InputStream is = context.getAssets().open("challenge.json");
                byte[] buffer = new byte[is.available()];
                is.read(buffer);
                is.close();
                String jsonGames2 = new String(buffer, "UTF-8");
                try {
                    is = context.getAssets().open("challenge_levels.json");
                    buffer = new byte[is.available()];
                    is.read(buffer);
                    is.close();
                    jsonLevels = new String(buffer, "UTF-8");
                    jsonGames = jsonGames2;
                } catch (IOException e) {
                    ex = e;
                    jsonGames = jsonGames2;
                    ex.printStackTrace();
                    anonymousClass6 = new TypeToken<List<ChallengeJsonGame>>() {
                    };
                    challengeJsonGames = (List) new Gson().fromJson(jsonGames, anonymousClass6.getType());
                    anonymousClass7 = new TypeToken<List<ChallengeJsonLevel>>() {
                    };
                    challengeJsonLevels = (List) new Gson().fromJson(jsonLevels, anonymousClass7.getType());
                    savedGames = new Select().from(ChallengeDbGame.class).execute();
                    map = new HashMap();
                    for (ChallengeDbGame challengeDbGame2 : savedGames) {
                        map.put(Integer.valueOf(challengeDbGame2.levelNumber), challengeDbGame2);
                    }
                    totalScore = 0;
                    totalStars = 0;
                    totalRating = 0.0d;
                    for (i = 0; i < challengeJsonGames.size(); i++) {
                        challengeJsonGame = (ChallengeJsonGame) challengeJsonGames.get(i);
                        challengeJsonGame.setLevelNumber(i + 1);
                        challengeDbGame2 = (ChallengeDbGame) map.get(Integer.valueOf(challengeJsonGame.getLevelNumber()));
                        if (challengeDbGame2 == null) {
                            stars = getStars(challengeDbGame2.score, challengeDbGame2.goal, challengeDbGame2.gameId, challengeJsonGame.getLevel());
                            challengeJsonGame.setStars(stars);
                            rating = 1.0d + (((double) i) * 0.3d);
                            if (stars != 3) {
                                totalRating += rating;
                            } else if (stars != 2) {
                                totalRating += 0.9d * rating;
                            } else if (stars == 1) {
                                totalRating += 0.8d * rating;
                            }
                            totalStars += stars;
                            totalScore += challengeDbGame2.score;
                        }
                    }
                    levelNumber = getChallengeLevelNumber(context);
                    startGame = 0;
                    for (i = 0; i < levelNumber; i++) {
                        startGame += ((ChallengeJsonLevel) challengeJsonLevels.get(i)).getGames();
                    }
                    this.challengeJsonGames = challengeJsonGames.subList(startGame, startGame + ((ChallengeJsonLevel) challengeJsonLevels.get(levelNumber)).getGames());
                    this.totalChallengeRating = Double.valueOf(totalRating);
                    this.totalChallengeStars = Integer.valueOf(totalStars);
                    this.totalChallengeScore = Integer.valueOf(totalScore);
                }
            } catch (IOException e2) {
                ex = e2;
                ex.printStackTrace();
                anonymousClass6 = /* anonymous class already generated */;
                challengeJsonGames = (List) new Gson().fromJson(jsonGames, anonymousClass6.getType());
                anonymousClass7 = /* anonymous class already generated */;
                challengeJsonLevels = (List) new Gson().fromJson(jsonLevels, anonymousClass7.getType());
                savedGames = new Select().from(ChallengeDbGame.class).execute();
                map = new HashMap();
                for (ChallengeDbGame challengeDbGame22 : savedGames) {
                    map.put(Integer.valueOf(challengeDbGame22.levelNumber), challengeDbGame22);
                }
                totalScore = 0;
                totalStars = 0;
                totalRating = 0.0d;
                for (i = 0; i < challengeJsonGames.size(); i++) {
                    challengeJsonGame = (ChallengeJsonGame) challengeJsonGames.get(i);
                    challengeJsonGame.setLevelNumber(i + 1);
                    challengeDbGame22 = (ChallengeDbGame) map.get(Integer.valueOf(challengeJsonGame.getLevelNumber()));
                    if (challengeDbGame22 == null) {
                        stars = getStars(challengeDbGame22.score, challengeDbGame22.goal, challengeDbGame22.gameId, challengeJsonGame.getLevel());
                        challengeJsonGame.setStars(stars);
                        rating = 1.0d + (((double) i) * 0.3d);
                        if (stars != 3) {
                            totalRating += rating;
                        } else if (stars != 2) {
                            totalRating += 0.9d * rating;
                        } else if (stars == 1) {
                            totalRating += 0.8d * rating;
                        }
                        totalStars += stars;
                        totalScore += challengeDbGame22.score;
                    }
                }
                levelNumber = getChallengeLevelNumber(context);
                startGame = 0;
                for (i = 0; i < levelNumber; i++) {
                    startGame += ((ChallengeJsonLevel) challengeJsonLevels.get(i)).getGames();
                }
                this.challengeJsonGames = challengeJsonGames.subList(startGame, startGame + ((ChallengeJsonLevel) challengeJsonLevels.get(levelNumber)).getGames());
                this.totalChallengeRating = Double.valueOf(totalRating);
                this.totalChallengeStars = Integer.valueOf(totalStars);
                this.totalChallengeScore = Integer.valueOf(totalScore);
            }
            anonymousClass6 = /* anonymous class already generated */;
            challengeJsonGames = (List) new Gson().fromJson(jsonGames, anonymousClass6.getType());
            anonymousClass7 = /* anonymous class already generated */;
            challengeJsonLevels = (List) new Gson().fromJson(jsonLevels, anonymousClass7.getType());
            savedGames = new Select().from(ChallengeDbGame.class).execute();
            map = new HashMap();
            for (ChallengeDbGame challengeDbGame222 : savedGames) {
                map.put(Integer.valueOf(challengeDbGame222.levelNumber), challengeDbGame222);
            }
            totalScore = 0;
            totalStars = 0;
            totalRating = 0.0d;
            for (i = 0; i < challengeJsonGames.size(); i++) {
                challengeJsonGame = (ChallengeJsonGame) challengeJsonGames.get(i);
                challengeJsonGame.setLevelNumber(i + 1);
                challengeDbGame222 = (ChallengeDbGame) map.get(Integer.valueOf(challengeJsonGame.getLevelNumber()));
                if (challengeDbGame222 == null) {
                    stars = getStars(challengeDbGame222.score, challengeDbGame222.goal, challengeDbGame222.gameId, challengeJsonGame.getLevel());
                    challengeJsonGame.setStars(stars);
                    rating = 1.0d + (((double) i) * 0.3d);
                    if (stars != 3) {
                        totalRating += rating;
                    } else if (stars != 2) {
                        totalRating += 0.9d * rating;
                    } else if (stars == 1) {
                        totalRating += 0.8d * rating;
                    }
                    totalStars += stars;
                    totalScore += challengeDbGame222.score;
                }
            }
            levelNumber = getChallengeLevelNumber(context);
            startGame = 0;
            for (i = 0; i < levelNumber; i++) {
                startGame += ((ChallengeJsonLevel) challengeJsonLevels.get(i)).getGames();
            }
            this.challengeJsonGames = challengeJsonGames.subList(startGame, startGame + ((ChallengeJsonLevel) challengeJsonLevels.get(levelNumber)).getGames());
            this.totalChallengeRating = Double.valueOf(totalRating);
            this.totalChallengeStars = Integer.valueOf(totalStars);
            this.totalChallengeScore = Integer.valueOf(totalScore);
        }
    }

    private static int getStars(int score, int goal, String gameId, int level) {
        if (score >= goal) {
            return 3;
        }
        double result;
        if (level < 23 && isAdvancedStarsCount(gameId)) {
            result = (((double) score) * 100.0d) / ((double) goal);
            if (result >= 91.0d) {
                return 2;
            }
            if (result >= 80.0d) {
                return 1;
            }
            return 0;
        } else if (level >= 23 && isAdvancedStarsCount(gameId)) {
            result = (((double) score) * 100.0d) / ((double) goal);
            if (result >= 95.0d) {
                return 2;
            }
            if (result >= 90.0d) {
                return 1;
            }
            return 0;
        } else if (score >= ((int) (((double) goal) * 0.83d))) {
            return 2;
        } else {
            if (score >= ((int) (((double) goal) * 0.665d))) {
                return 1;
            }
            return 0;
        }
    }

    private static boolean isAdvancedStarsCount(String gameId) {
        boolean z = true;
        switch (gameId.hashCode()) {
            case 1571:
                if (gameId.equals("14")) {
                    z = false;
                    break;
                }
                break;
            case 1572:
                if (gameId.equals("15")) {
                    z = true;
                    break;
                }
                break;
            case 1576:
                if (gameId.equals("19")) {
                    z = true;
                    break;
                }
                break;
        }
        switch (z) {
            case false:
            case true:
            case true:
                return true;
            default:
                return false;
        }
    }

    public boolean wasChallengeTutorialShowed(Context context, String gameName) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(PREFIX_IS_PLAYED + gameName.toLowerCase(), false);
    }

    public void setShowChallengeTutorial(Context context, String gameName) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean(PREFIX_IS_PLAYED + gameName.toLowerCase(), true).apply();
    }

    public boolean isNeedChallengeSynchronization() {
        return PreferenceManager.getDefaultSharedPreferences(MemoryApplicationModel.getInstance()).getBoolean(PREF_NEED_CHALLENGE_SYNCHRONIZATION, true);
    }

    public void setNeedChallengeSynchronization(boolean needChallengeSynchronization) {
        PreferenceManager.getDefaultSharedPreferences(MemoryApplicationModel.getInstance()).edit().putBoolean(PREF_NEED_CHALLENGE_SYNCHRONIZATION, needChallengeSynchronization).apply();
    }
}

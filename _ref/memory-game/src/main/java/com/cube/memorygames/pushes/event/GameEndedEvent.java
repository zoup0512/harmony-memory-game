package com.cube.memorygames.pushes.event;

import com.cube.memorygames.MemoryApplicationModel;
import java.util.HashMap;

public class GameEndedEvent extends OnlineEvent {
    private static final String FIELD_GAME_ID = "FIELD_GAME_ID";
    private static final String FIELD_NEW_RANK = "FIELD_NEW_RANK";
    private static final String FIELD_RATING_CHANGE_USER1 = "FIELD_RATING_CHANGE_USER1";
    private static final String FIELD_RESULTS_USER1 = "FIELD_RESULTS_USER1";
    private static final String FIELD_RESULTS_USER2 = "FIELD_RESULTS_USER2";
    private static final String FIELD_WINNER_USER = "FIELD_WINNER_USER";
    private int changeRankUser1;
    private int changeRankUser2;
    private String gameId;
    private int newRank;
    private String resultsUser1;
    private String resultsUser2;
    private String winnerUser;

    public GameEndedEvent(String gameId, String resultsUser1, String resultsUser2, String winnerUser, int newRank, int changeRankUser1, int changeRankUser2) {
        this.gameId = gameId;
        this.resultsUser1 = resultsUser1;
        this.resultsUser2 = resultsUser2;
        this.winnerUser = winnerUser;
        this.newRank = newRank;
        this.changeRankUser1 = changeRankUser1;
        this.changeRankUser2 = changeRankUser2;
    }

    public String getResultsUser1() {
        return this.resultsUser1;
    }

    public String getResultsUser2() {
        return this.resultsUser2;
    }

    public String getWinnerUser() {
        return this.winnerUser;
    }

    public int getNewRank() {
        return this.newRank;
    }

    public int getChangeRankUser1() {
        return this.changeRankUser1;
    }

    public int getChangeRankUser2() {
        return this.changeRankUser2;
    }

    public String getGameId() {
        return this.gameId;
    }

    public static GameEndedEvent create(String s) {
        if (s == null) {
            return null;
        }
        HashMap hashMap = (HashMap) MemoryApplicationModel.getInstance().getGsonParser().fromJson(s, HashMap.class);
        return new GameEndedEvent((String) hashMap.get(FIELD_GAME_ID), (String) hashMap.get(FIELD_RESULTS_USER1), (String) hashMap.get(FIELD_RESULTS_USER2), (String) hashMap.get(FIELD_WINNER_USER), ((Double) hashMap.get(FIELD_NEW_RANK)).intValue(), ((Double) hashMap.get(FIELD_RATING_CHANGE_USER1)).intValue(), ((Double) hashMap.get(FIELD_RATING_CHANGE_USER1)).intValue());
    }
}
